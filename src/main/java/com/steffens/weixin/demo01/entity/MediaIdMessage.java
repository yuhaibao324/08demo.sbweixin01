package com.steffens.weixin.demo01.entity;

/**
 * @创建人 steffens
 * @创建时间 2018/12/16
 * @描述 文件创建
 */
import com.steffens.weixin.demo01.config.XStreamCDATA;
import com.thoughtworks.xstream.annotations.XStreamAlias;

public class MediaIdMessage {
    @XStreamAlias("MediaId")
    @XStreamCDATA
    private String MediaId;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}

