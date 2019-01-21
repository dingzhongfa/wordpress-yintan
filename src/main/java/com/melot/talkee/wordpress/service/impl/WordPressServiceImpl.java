package com.melot.talkee.wordpress.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.melot.talkee.wordpress.constant.WordpressConstants;
import com.melot.talkee.wordpress.dao.WordpressDao;
import com.melot.talkee.wordpress.domain.ArticleDO;
import com.melot.talkee.wordpress.domain.SeoDO;
import com.melot.talkee.wordpress.domain.TermDO;
import com.melot.talkee.wordpress.dto.TermDTO;
import com.melot.talkee.wordpress.enums.WordpressTagCodeEnum;
import com.melot.talkee.wordpress.response.Response;
import com.melot.talkee.wordpress.service.WordPressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.melot.talkee.wordpress.constant.WordpressConstants.*;

/**
 * <p>
 * wordpress
 * </p>
 * <p>Company: www.kktalkee.com</p>
 *
 * @author dingzhongfa
 * @date 2018-10-16 上午10:47
 */
@Service("wordPressService")
public class WordPressServiceImpl implements WordPressService {

    private static final Logger logger = LoggerFactory.getLogger(WordPressServiceImpl.class);

    @Resource
    private WordpressDao wordpressDao;

    @Override
    public Response getTerms(String type, Integer parent) {
        if (type.equals(WordpressConstants.TERM_POST_TAG) && parent != 0) {
            return Response.getInstance().setWordpressTagCodeEnum(WordpressTagCodeEnum.PARAMETER_ERROR);
        }
        List<TermDTO> terms = wordpressDao.getTerms(type, parent, WORD_OF_MONTH_TERM_ID);
        for (TermDTO term : terms) {
            term.setChildren(wordpressDao.getTerms(type, term.getTermId(), WORD_OF_MONTH_TERM_ID));
            List<Integer> postIdList = getPostIdList(term.getTermId());
            if (!postIdList.isEmpty()) {
                term.setArticles(wordpressDao.getArticlesSimpleInfo(postIdList, 8, 0));
            }
        }
        return Response.getInstance().setData(terms);
    }

    @Override
    public Response articles(Integer termId, Integer current, Integer pageSize) {
        List<Integer> postIds = getPostIdList(termId);
        if (postIds == null || postIds.isEmpty()) {
            return Response.getInstance();
        }
        Integer offset = null;
        if (current != null) {
            offset = (current - 1) * pageSize;
        }
        List<ArticleDO> articles = wordpressDao.getArticles(postIds, pageSize, offset);
        for (ArticleDO articleDO : articles) {
            articleDO.setFirstPicture(wordpressDao.getFirstPicture(articleDO.getID()));
            articleDO.setTerm(getTerm(articleDO.getID()));
        }
        int count = wordpressDao.getArticleCount(postIds);
        return Response.getInstance().setData(articles).setTotal(count);
    }

    @Override
    public Response allArticles(Integer current, Integer pageSize) {
        Integer offset = null;
        if (current != null) {
            offset = (current - 1) * pageSize;
        }
        List<Integer> monthPostIdList = wordpressDao.getMonthPostIdlist(WORD_OF_MONTH_TERM_ID);
        List<ArticleDO> articles = wordpressDao.getArticlesAll(monthPostIdList, pageSize, offset);
        for (ArticleDO articleDO : articles) {
            articleDO.setFirstPicture(wordpressDao.getFirstPicture(articleDO.getID()));
            TermDO termDO = getTerm(articleDO.getID());
            logger.info("article title==>   " + articleDO.getPostTitle() + "   term==>   " + JSONObject.toJSONString(termDO));
            articleDO.setTerm(termDO);
        }
        int count = wordpressDao.getArticleAllCount(monthPostIdList);
        return Response.getInstance().setData(articles).setTotal(count);
    }

    @Override
    public Response allArticleDetail(Integer postId) {
        ArticleDO article = wordpressDao.articleDetail(postId);
        article.setFirstPicture(wordpressDao.getFirstPicture(article.getID()));
        List<Integer> monthPostIdList = wordpressDao.getMonthPostIdlist(WORD_OF_MONTH_TERM_ID);
        List<Integer> postIdList = wordpressDao.getPostIdAllList(monthPostIdList);
        completeArticleInfo(article, postIdList);
        return Response.getInstance().setData(article);
    }

    @Override
    public Response getTermById(Integer termId) {
        return Response.getInstance().setData(wordpressDao.getTermById(termId));
    }

    @Override
    public Response getTermBySlug(String slug) {
        TermDO termDO = wordpressDao.getTermBySlug(slug);
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(termDO));
        if (termDO != null) {
            jsonObject.put("parent", wordpressDao.getParent(termDO.getTermId()));
        }
        return Response.getInstance().setData(jsonObject);
    }

    @Override
    public Response articleDetail(Integer postId, Integer termId) {
        ArticleDO article = wordpressDao.articleDetail(postId);
        article.setFirstPicture(wordpressDao.getFirstPicture(article.getID()));
        List<Integer> postIdList = getPostIdList(termId);
        completeArticleInfo(article, postIdList);
        return Response.getInstance().setData(article);
    }

    /**
     * 完善文章信息
     *
     * @param article    文章
     * @param postIdList 文章id List
     */
    private void completeArticleInfo(ArticleDO article, List<Integer> postIdList) {
        for (int i = 0; i < postIdList.size(); i++) {
            if (!(postIdList.get(i).intValue() == article.getID().intValue())) {
                continue;
            }
            if (i > 0) {
                ArticleDO lastArticle = wordpressDao.articleSimple(postIdList.get(i - 1));
                article.setLastArticle(lastArticle);
            }
            if (i < postIdList.size() - 1) {
                ArticleDO nextArticle = wordpressDao.articleSimple(postIdList.get(i + 1));
                article.setNextArticle(nextArticle);
            }
            article.setTerm(getTerm(article.getID()));
            break;
        }
        List<Map<String, String>> seoMap = wordpressDao.getSeoInfo(article.getID());
        SeoDO seoDO = new SeoDO();
        if (seoMap != null && seoMap.size() > 0) {
            for (Map<String, String> map : seoMap) {
                String metaKey = map.get("meta_key");
                String metaValue = map.get("meta_value");
                if (metaKey.equals(_yoast_wpseo_primary_category)) {
                    seoDO.setPrimaryCategory(metaValue);
                }
                if (metaKey.equals(_yoast_wpseo_title)) {
                    seoDO.setTitle(metaValue);
                }
                if (metaKey.equals(_yoast_wpseo_focuskw)) {
                    seoDO.setFocuskw(metaValue);
                }
                if (metaKey.equals(_yoast_wpseo_content_score)) {
                    seoDO.setContentScore(metaValue);
                }
                if (metaKey.equals(_yoast_wpseo_metadesc)) {
                    seoDO.setMetadesc(metaValue);
                }
                if (metaKey.equals(_yoast_wpseo_linkdex)) {
                    seoDO.setLinkdex(metaValue);
                }
            }
        }
        article.setSeo(seoDO);
    }

    private TermDO getTerm(Integer postId) {
        TermDO termDO = wordpressDao.getTerm(postId);
        if (termDO != null && termDO.getName().endsWith(INDO_END)) {
            termDO = wordpressDao.getParent(termDO.getTermId());
        }
        return termDO;
    }

    /**
     * 获取专题下所有的文章id
     *
     * @param termId
     * @return
     */
    private List<Integer> getPostIdList(Integer termId) {
        List<Integer> termIds = new ArrayList<>();
        termIds.add(termId);
        List<TermDTO> termDOS = wordpressDao.getTerms(WordpressConstants.TERM_CATEGORY, termId, WORD_OF_MONTH_TERM_ID);
        if (termDOS != null && termDOS.size() > 0) {
            for (TermDTO term : termDOS) {
                termIds.add(term.getTermId());
                List<TermDTO> termDOsIn = wordpressDao.getTerms(WordpressConstants.TERM_CATEGORY, term.getTermId(), WORD_OF_MONTH_TERM_ID);
                if (termDOsIn == null || termDOsIn.size() == 0) {
                    continue;
                }
                for (TermDTO termDOin : termDOsIn) {
                    termIds.add(termDOin.getTermId());
                }
            }
        }
        return wordpressDao.getPostIdList(termIds);
    }

}
