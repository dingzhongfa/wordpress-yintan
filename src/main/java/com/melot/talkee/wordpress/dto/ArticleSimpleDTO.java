package com.melot.talkee.wordpress.dto;

import java.util.Date;

/**
 * <p>
 * 文章简单信息
 * </p>
 * <p>Company: www.kktalkee.com</p>
 *
 * @author dingzhongfa
 * @date 2018-11-19 上午10:03
 */
public class ArticleSimpleDTO {

    private Integer ID;

    private String postTitle;

    private Date postDate;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }
}
