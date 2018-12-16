package com.steffens.weixin.demo001.exampletest;

/**
 * @创建人 steffens
 * @创建时间 2018/12/16
 * @描述 文件创建
 */

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

@Service
public class CoreService {


    public static String processRequest(HttpServletRequest request)
            throws Exception {
// 返回的xml数据
        String resqXml = null;
// 返回的文本内容
        String resqContext = null;


// 将request交给消息处理类
        Map<String, String> map = MessageUtils.parseXml(request);


// 获取消息类型
        String msgType = map.get("MsgType");


// 封装返回消息
        RespMessage_Text responseText = new RespMessage_Text();
        responseText.setFromUserName(map.get("ToUserName"));
        responseText.setToUserName(map.get("FromUserName"));
        responseText.setCreateTime(new Date().getTime());



        // 根据不同消息类型做出相应的处理
        // 文本消息
        if (msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_TEXT)) {
            String strDate = new SimpleDateFormat("yyyy-HH-dd hh:MM:ss").format(new Date());
            resqContext = "你发送的消息：("+strDate+") " + map.get("Content");
            responseText.setMsgType(MessageUtils.REQ_MESSAGE_TYPE_TEXT);
        }


        // 图片消息
        if (msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_IMAGE)) {
            resqContext = "您发送了图片消息";
            responseText.setMsgType(MessageUtils.REQ_MESSAGE_TYPE_IMAGE);

        }

        //事件消息
        if (msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_EVENT)) {
            //获取事件类型
            String eventType = map.get("EVENT");

            if (eventType.equals(MessageUtils.EVENT_TYPE_SUBSCRIBE)) {
                //关注
                resqContext = "谢谢你的关注";
                responseText.setMsgType(MessageUtils.EVENT_TYPE_SUBSCRIBE);
            }

            if (eventType.equals(MessageUtils.EVENT_TYPE_UNSUBSCRIBE)) {
                //TODO取消关注
                resqContext = "谢谢你的取消";
                responseText.setMsgType(MessageUtils.EVENT_TYPE_UNSUBSCRIBE);
            }

            if (eventType.equals(MessageUtils.EVENT_TYPE_CLICK)) {
                //TODO自定义菜单
                resqContext = "点击菜单";
                responseText.setMsgType(MessageUtils.EVENT_TYPE_CLICK);

            }

            if (eventType.equals(MessageUtils.EVENT_TYPE_SCAN)) {
                //TODO扫描二维码
                resqContext = "点击搜索";
                responseText.setMsgType(MessageUtils.EVENT_TYPE_SCAN);
            }
        }

    //设置返回内容
        responseText.setContent(resqContext);

    //将对象转为xml数据
        resqXml = MessageUtils.messageToXml(responseText);


        return resqXml;
    }


}
