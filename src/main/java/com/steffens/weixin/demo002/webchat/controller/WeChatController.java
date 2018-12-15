package com.steffens.weixin.demo002.webchat.controller;

import com.webchat.service.WeChatService;
import com.webchat.util.WeChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@ServletComponentScan("com.webchat.controller")
/**
 * @创建人 steffens
 * @创建时间 2018/12/15
 * @描述 文件创建
 */
@RestController
public class WeChatController {
    @Autowired
    private WeChatService weChatService;


    /**
     * 处理微信服务器发来的get请求，进行签名的验证
     *
     * signature 微信端发来的签名
     * timestamp 微信端发来的时间戳
     * nonce     微信端发来的随机字符串
     * echostr   微信端发来的验证字符串
     */
    @GetMapping(value = "wechat")
    public String validate(@RequestParam(value = "signature") String signature,
                           @RequestParam(value = "timestamp") String timestamp,
                           @RequestParam(value = "nonce") String nonce,
                           @RequestParam(value = "echostr") String echostr) {
        return WeChatUtil.checkSignature(signature, timestamp, nonce) ? echostr : null;

    }
    /**
     * 此处是处理微信服务器的消息转发的
     */
    @PostMapping(value = "wechat")
    public String processMsg(HttpServletRequest request) {
        // 调用核心服务类接收处理请求
        return weChatService.processRequest(request);
    }
}
