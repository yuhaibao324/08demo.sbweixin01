package com.steffens.weixin.demo003.entity;

/**
 * @创建人 steffens
 * @创建时间 2018/12/16
 * @描述 文件创建
 */
import com.steffens.weixin.demo003.config.XStreamCDATA;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class wechatOutputMessage {

    @XStreamAlias("ToUserName")
    @XStreamCDATA
    private String ToUserName;

    @XStreamAlias("FromUserName")
    @XStreamCDATA
    private String FromUserName;

    @XStreamAlias("CreateTime")
    private Long CreateTime;

    @XStreamAlias("MsgType")
    @XStreamCDATA
    private String MsgType = "text";

    private ImageMessage Image;

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

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public ImageMessage getImage() {
        return Image;
    }

    public void setImage(ImageMessage image) {
        Image = image;
    }
}
