package com.melot.talkee.wordpress.domain;

/**
 * <p>
 * seo参数
 * </p>
 * <p>Company: www.kktalkee.com</p>
 *
 * @author dingzhongfa
 * @date 2018-10-19 下午4:17
 */
public class SeoDO {

    private String contentScore;

    private String primaryCategory;

    private String title;

    private String focuskw;

    private String metadesc;

    private String linkdex;

    public String getLinkdex() {
        return linkdex;
    }

    public void setLinkdex(String linkdex) {
        this.linkdex = linkdex;
    }

    public String getContentScore() {
        return contentScore;
    }

    public void setContentScore(String contentScore) {
        this.contentScore = contentScore;
    }

    public String getPrimaryCategory() {
        return primaryCategory;
    }

    public void setPrimaryCategory(String primaryCategory) {
        this.primaryCategory = primaryCategory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFocuskw() {
        return focuskw;
    }

    public void setFocuskw(String focuskw) {
        this.focuskw = focuskw;
    }

    public String getMetadesc() {
        return metadesc;
    }

    public void setMetadesc(String metadesc) {
        this.metadesc = metadesc;
    }
}
