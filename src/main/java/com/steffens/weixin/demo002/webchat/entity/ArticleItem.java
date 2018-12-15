package com.steffens.weixin.demo002.webchat.entity;

/**
 * @创建人 steffens
 * @创建时间 2018/12/15
 * @描述 文件创建
 */
public class ArticleItem {
    private String Title;
    private String Description;
    private String PicUrl;
    private String Url;
    public String getTitle() {
        return Title;
    }
    public void setTitle(String title) {
        Title = title;
    }
    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }
    public String getPicUrl() {
        return PicUrl;
    }
    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
    public String getUrl() {
        return Url;
    }
    public void setUrl(String url) {
        Url = url;
    }

}