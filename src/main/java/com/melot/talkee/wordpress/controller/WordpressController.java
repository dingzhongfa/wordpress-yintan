package com.melot.talkee.wordpress.controller;

import com.alibaba.fastjson.JSONObject;
import com.melot.talkee.wordpress.enums.WordpressTagCodeEnum;
import com.melot.talkee.wordpress.response.Response;
import com.melot.talkee.wordpress.service.WordPressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * wordpress
 * </p>
 * <p>Company: www.kktalkee.com</p>
 *
 * @author dingzhongfa
 * @date 2018-10-16 上午10:39
 */
@RestController
@RequestMapping("wordpress")
public class WordpressController {

    private static final Logger logger = LoggerFactory.getLogger(WordpressController.class);

    @Resource
    private WordPressService wordPressService;

    /**
     * 获取所有分类
     *
     * @return
     */
    @GetMapping(value = "terms", produces = MediaType.TEXT_PLAIN_VALUE)
    public String terms(@RequestParam(value = "type") String type, @RequestParam("parent") Integer parent,
                        @RequestParam(value = "callback", required = false) String callback) {
        try {
            if (callback != null) {
                return callback + "(" + JSONObject.toJSONString(wordPressService.getTerms(type, parent)) + ")";
            }
            return JSONObject.toJSONString(wordPressService.getTerms(type, parent));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            if (callback != null) {
                return callback + "(" + JSONObject.toJSONString(Response.getInstance().setWordpressTagCodeEnum(WordpressTagCodeEnum.DATA_DEAL_FAILED)) + ")";
            }
            return JSONObject.toJSONString(Response.getInstance().setWordpressTagCodeEnum(WordpressTagCodeEnum.DATA_DEAL_FAILED));
        }
    }

    @GetMapping(value = "terms/{termId}/articles", produces = MediaType.TEXT_PLAIN_VALUE)
    public String articles(@PathVariable("termId") Integer termId,
                           @RequestParam(value = "current", required = false) Integer current,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                           @RequestParam(value = "callback", required = false) String callback) {
        Assert.isTrue(termId > 0, "参数异常，分类ID小于等于0");
        try {
            if (callback != null) {
                return callback + "(" + JSONObject.toJSONString(wordPressService.articles(termId, current, pageSize)) + ")";
            }
            return JSONObject.toJSONString(wordPressService.articles(termId, current, pageSize));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            if (callback != null) {
                return callback + "(" + JSONObject.toJSONString(Response.getInstance().setWordpressTagCodeEnum(WordpressTagCodeEnum.DATA_DEAL_FAILED)) + ")";
            }
            return JSONObject.toJSONString(Response.getInstance().setWordpressTagCodeEnum(WordpressTagCodeEnum.DATA_DEAL_FAILED));
        }
    }

    @GetMapping(value = "terms/{termId}/articles/{postId}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String articleDetail(@PathVariable("postId") Integer postId, @PathVariable("termId") Integer termId,
                                @RequestParam(value = "callback", required = false) String callback) {
        try {
            if (callback != null) {
                return callback + "(" + JSONObject.toJSONString(wordPressService.articleDetail(postId, termId)) + ")";
            }
            return JSONObject.toJSONString(wordPressService.articleDetail(postId, termId));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            if (callback != null) {
                return callback + "(" + JSONObject.toJSONString(Response.getInstance().setWordpressTagCodeEnum(WordpressTagCodeEnum.DATA_DEAL_FAILED)) + ")";
            }
            return JSONObject.toJSONString(Response.getInstance().setWordpressTagCodeEnum(WordpressTagCodeEnum.DATA_DEAL_FAILED));
        }
    }

    @GetMapping("articles")
    public Response allArticles(
            @RequestParam(value = "current", required = false) Integer current,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        try {
            return wordPressService.allArticles(current, pageSize);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Response.getInstance().setWordpressTagCodeEnum(WordpressTagCodeEnum.DATA_DEAL_FAILED);
        }
    }

    @GetMapping("articles/{postId}")
    public Response aiiArticleDetail(@PathVariable("postId") Integer postId) {
        try {
            return wordPressService.allArticleDetail(postId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Response.getInstance().setWordpressTagCodeEnum(WordpressTagCodeEnum.DATA_DEAL_FAILED);
        }
    }

    @GetMapping("terms/{termId}/info")
    public Response getTermById(@PathVariable("termId") Integer termId) {
        try {
            return wordPressService.getTermById(termId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Response.getInstance().setWordpressTagCodeEnum(WordpressTagCodeEnum.DATA_DEAL_FAILED);
        }
    }

    @GetMapping("terms/slug")
    public Response getTermBySlug(@RequestParam("slug") String slug) {
        try {
            return wordPressService.getTermBySlug(slug);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Response.getInstance().setWordpressTagCodeEnum(WordpressTagCodeEnum.DATA_DEAL_FAILED);
        }
    }

}
