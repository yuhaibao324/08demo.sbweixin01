package com.steffens.weixin.demo003.config;

/**
 * @创建人 steffens
 * @创建时间 2018/12/16
 * @描述 文件创建
 */

public enum MsgType {

    Text("text"),
    Image("image"),
    Music("music"),
    Video("video"),
    Voice("voice"),
    Location("location"),
    Link("link");
    private String msgType = "";

    MsgType(String msgType) {
        this.msgType = msgType;
    }

    /**
     * @return the msgType
     */
    @Override
    public String toString() {
        return msgType;
    }

}