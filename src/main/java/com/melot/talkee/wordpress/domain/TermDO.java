package com.melot.talkee.wordpress.domain;

/**
 * <p>
 * 分类
 * </p>
 * <p>Company: www.kktalkee.com</p>
 *
 * @author dingzhongfa
 * @date 2018-10-16 上午11:29
 */
public class TermDO {

    private Integer termId;

    private String name;

    private String slug;

    private Integer termGroup;

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getTermGroup() {
        return termGroup;
    }

    public void setTermGroup(Integer termGroup) {
        this.termGroup = termGroup;
    }
}
