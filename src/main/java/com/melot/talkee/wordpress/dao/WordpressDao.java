package com.melot.talkee.wordpress.dao;

import com.melot.talkee.wordpress.domain.ArticleDO;
import com.melot.talkee.wordpress.domain.TermDO;
import com.melot.talkee.wordpress.dto.ArticleSimpleDTO;
import com.melot.talkee.wordpress.dto.TermDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 * <p>Company: www.kktalkee.com</p>
 *
 * @author dingzhongfa
 * @date 2018-10-16 上午11:28
 */
@Mapper
public interface WordpressDao {

    /**
     * 获取子专题
     *
     * @param type   类型
     * @param parent 父级
     * @return List<TermDO>
     */
    List<TermDTO> getTerms(@Param("type") String type, @Param("parent") Integer parent, @Param("monthTermId") Integer monthTermId);

    List<Integer> getPostIdList(@Param("termIds") List<Integer> termIds);

    String getFirstPicture(Integer parent);

    int getArticleCount(@Param("postIds") List<Integer> postIds);

    int getArticleAllCount(@Param("monthPostIdList") List<Integer> monthPostIdList);

    List<ArticleDO> getArticles(@Param("postIds") List<Integer> postIds, @Param("limit") Integer limit, @Param("offset") Integer offset);

    List<ArticleSimpleDTO> getArticlesSimpleInfo(@Param("postIds") List<Integer> postIds, @Param("limit") Integer limit, @Param("offset") Integer offset);

    List<ArticleDO> getArticlesAll(@Param("monthPostIdList") List<Integer> monthPostIdList, @Param("limit") Integer limit, @Param("offset") Integer offset);


    ArticleDO articleDetail(Integer postId);

    ArticleDO articleSimple(Integer postId);

    List<Integer> getPostIdAllList(@Param("monthPostIdList") List<Integer> monthPostIdList);

    List<Integer> getMonthPostIdlist(@Param("monthTermId") Integer monthTermId);

    TermDO getTerm(@Param("postId") Integer postId);

    TermDO getParent(@Param("termId")Integer termId);

    List<Map<String,String>> getSeoInfo(@Param("postId")Integer postId);

    TermDO getTermById(@Param("termId")Integer termId);

    TermDO getTermBySlug(@Param("slug") String slug);
}
