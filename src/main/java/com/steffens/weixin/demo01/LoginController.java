package com.steffens.weixin.demo01;

/**
 * @创建人 steffens
 * @创建时间 2018/12/15
 * @描述 文件创建
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * 类名称: LoginController
 * 类描述: 与微信对接登陆验证
 * @author yuanjun
 * 创建时间:2017年12月5日上午10:52:13
 */
@RestController
@RequestMapping(value="/weixin")
public class LoginController {

    @GetMapping(value = "test")
    public String validate1() {
        return "login...test....";
    }

    @RequestMapping(value = "login",method={RequestMethod.GET,RequestMethod.POST})
    public void login(HttpServletRequest request, HttpServletResponse response){

        System.out.println("success");

        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        System.out.println("echostr:" + echostr);

        PrintWriter out = null;
        try {
            out = response.getWriter();
            if(CheckUtil.checkSignature(signature, timestamp, nonce)){
                out.write("GOOD");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            out.close();
        }

    }
}
