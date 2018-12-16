package com.steffens.weixin.demo001.exampletest;

/**
 * @创建人 steffens
 * @创建时间 2018/12/16
 * @描述 文件创建
 */
public class RespMessage_Base {
    // 接收方帐号（收到的OpenID）
    private String ToUserName;
    // 开发者微信号
    private String FromUserName;
    // 消息创建时间 （整型）
    private long CreateTime;
    // 消息类型
    private String MsgType;


    public String getToUserName() {
        return ToUserName;
    }


    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }


    public String getFromUserName() {
        return FromUserName;
    }


    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }


    public long getCreateTime() {
        return CreateTime;
    }


    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }


    public String getMsgType() {
        return MsgType;
    }


    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
}

