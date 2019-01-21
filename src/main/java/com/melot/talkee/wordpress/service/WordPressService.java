package com.melot.talkee.wordpress.service;

import com.melot.talkee.wordpress.response.Response;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * wordpress
 * </p>
 * <p>Company: www.kktalkee.com</p>
 *
 * @author dingzhongfa
 * @date 2018-10-16 上午10:46
 */
public interface WordPressService {

    /**
     * 获取所有分类
     *
     * @return Response
     */
    Response getTerms(String type, Integer parent);

    /**
     * 获取分类下的文章
     *
     * @param termId   分类id
     * @param current  当前页码
     * @param pageSize 页码长度
     * @return Response
     */
    Response articles(Integer termId, Integer current, Integer pageSize);

    /**
     * 获取文章详情
     *
     * @param postId 文章id
     * @return
     */
    Response articleDetail(Integer postId, Integer termId);

    /**
     * 获取所有文章
     *
     * @param current  当前页码
     * @param pageSize 页码长度
     * @return Response
     */
    Response allArticles(Integer current, Integer pageSize);

    /**
     * 获取文章详情
     *
     * @param postId 文章id
     * @return Response
     */
    Response allArticleDetail(Integer postId);

    /**
     * 获取专题信息
     *
     * @param termId 专题id
     * @return TermDO
     */
    Response getTermById(@Param("termId") Integer termId);

    /**
     * 获取专题信息
     *
     * @param slug 专题别名
     * @return TermDO
     */
    Response getTermBySlug(@Param("slug") String slug);
}
