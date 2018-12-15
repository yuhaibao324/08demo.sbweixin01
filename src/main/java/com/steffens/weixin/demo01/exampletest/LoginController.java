package com.steffens.weixin.demo01.exampletest;

/**
 * @创建人 steffens
 * @创建时间 2018/12/15
 * @描述 文件创建
 */

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.Date;

/**
 * 类名称: LoginController
 * 类描述: 与微信对接登陆验证
 *
 * @author yuanjun
 * 创建时间:2017年12月5日上午10:52:13
 */
@RestController
@RequestMapping(value = "/weixin")
public class LoginController {

    @GetMapping(value = "test")
    @ResponseBody
    public Map<String, String> validate1() {

        System.out.println("测试Reset接口: /weixin/test");
        Map<String, String> retMap = new HashMap();
        retMap.put("测试接口:", "weixin/test");

        return retMap;
    }

    @RequestMapping(value = "login", method = {RequestMethod.GET, RequestMethod.POST})
    public void login(HttpServletRequest request, HttpServletResponse response) {

        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
        System.out.println("格式化输出：" + sdf.format(d));

        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        System.out.println("Asia/Shanghai:" + sdf.format(d));

        System.out.println("request.getParameter(\"timestamp\")：" + timestamp);
        System.out.println("request.getParameter(\"echostr\"): " + echostr);
        System.out.println("---------------------------------------- ");

        PrintWriter out = null;
        try {
            out = response.getWriter();
            if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
                out.write("GOOD");
                System.out.println("成功返回 echostr：" + echostr);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            System.out.println("失败 认证");
            out.close();
        }

    }
}
