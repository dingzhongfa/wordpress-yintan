package com.melot.talkee.wordpress.dto;

import com.melot.talkee.wordpress.domain.TermDO;

import java.util.List;

/**
 * <p>
 * 专题
 * </p>
 * <p>Company: www.kktalkee.com</p>
 *
 * @author dingzhongfa
 * @date 2018-11-19 上午9:54
 */
public class TermDTO extends TermDO {

    private List<TermDTO> children;

    private List<ArticleSimpleDTO> articles;

    public List<TermDTO> getChildren() {
        return children;
    }

    public void setChildren(List<TermDTO> children) {
        this.children = children;
    }

    public List<ArticleSimpleDTO> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleSimpleDTO> articles) {
        this.articles = articles;
    }
}
