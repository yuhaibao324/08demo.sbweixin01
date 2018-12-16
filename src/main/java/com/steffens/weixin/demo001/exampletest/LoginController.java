package com.steffens.weixin.demo001.exampletest;

/**
 * @创建人 steffens
 * @创建时间 2018/12/15
 * @描述 文件创建
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

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

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    LoginServiceRequest loginServiceRequest;

    HttpServletRequestReader httpServletRequestReader;

    @GetMapping(value = "test")
    @ResponseBody
    public Map<String, String> validate1() {

        System.out.println("测试Reset接口: /weixin/test");
        Map<String, String> retMap = new HashMap();
        retMap.put("测试接口:", "weixin/test");
        System.out.println("---------------------------------------- ");
        return retMap;
    }


    /**
     * 消息的处理和响应
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "handle", method = RequestMethod.POST)
    public void messageHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //将请求、响应的编码设置为utf-8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        //调用核心业务逻辑
        String respXml = CoreService.processRequest(request);

        //响应消息
        PrintWriter out = response.getWriter();
        out.print(respXml);
        out.close();
    }


    @RequestMapping(value = "login", method = {RequestMethod.GET})
    public String loginCheckSign(HttpServletRequest request, HttpServletResponse response) {

        System.out.println(" RequestMethod.GET: login(HttpServletRequest request, HttpServletResponse response).\n");

        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr1 = request.getParameter("echostr");

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
        System.out.println("格式化输出：" + sdf.format(d));

        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        System.out.println("Asia/Shanghai:" + sdf.format(d));

        System.out.println("request.getParameter(\"signature\")：" + signature);
        System.out.println("request.getParameter(\"timestamp\"): " + timestamp);
        System.out.println("request.getParameter(\"nonce\")：" + nonce);
        System.out.println("request.getParameter(\"echostr\"): " + echostr1);

        //PrintWriter out = null;
        // try {
        //out = response.getWriter();
        if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
            //out.write(echostr1);
            System.out.println("成功返回 echostr：" + echostr1);
            System.out.println("-------------------------");
            return echostr1;
            // return  (CheckUtil.checkSignature(signature, timestamp, nonce) ? echostr1 : null);
        } else {
            System.out.println("失败返回 echostr：" + echostr1);
            System.out.println("-------------------------");
            return "";
        }


        //} catch (Exception e) {

        // }


    }

    @RequestMapping(value = "login123", method = {RequestMethod.POST})
    public void textMessage(@RequestBody String xml) {
        System.out.println(" POST: textMessage.\n");
        logger.debug("收到的xml：{}", xml);

    }

    /**
     * 此处是处理微信服务器的消息转发的
     */
    @RequestMapping(value = "login1", method = {RequestMethod.POST})
    public void login1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 调用核心服务类接收处理请求
        System.out.println(" loginMessage - RequestMethod.POST: processRequest(request).\n");

        //获取参数
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr1 = request.getParameter("echostr");
        System.out.println("request.getParameter(\"signature\")：" + signature);
        System.out.println("request.getParameter(\"timestamp\")：" + timestamp);
        System.out.println("request.getParameter(\"nonce\")：" + nonce);
        System.out.println("request.getParameter(\"echostr\"): " + echostr1);
        System.out.println("---------------------------------------- ");


        String reqBody = httpServletRequestReader.ReadAsChars(request);

        /*
        System.out.println("开发者微信号：" + inputMsg.getToUserName());
        System.out.println("发送方帐号：" + inputMsg.getFromUserName());
        System.out.println("消息创建时间：" + inputMsg.getCreateTime() + new Date(createTime * 1000l));
        System.out.println("消息内容：" + inputMsg.getContent());
        System.out.println("消息Id：" + inputMsg.getMsgId());

        StringBuffer str = new StringBuffer();
        str.append("<xml>");
        str.append("<ToUserName><![CDATA[" + custermname + "]]></ToUserName>");
        str.append("<FromUserName><![CDATA[" + servername + "]]></FromUserName>");
        str.append("<CreateTime>" + returnTime + "</CreateTime>");
        str.append("<MsgType><![CDATA[" + msgType + "]]></MsgType>");
        str.append("<Content><![CDATA[你说的是：" + inputMsg.getContent() + "，吗？]]></Content>");
        str.append("</xml>");
        System.out.println(str.toString());
*/

        // response.getWriter().write(reqBody);
        System.out.println(reqBody);
        response.getWriter().write(reqBody);

        /*
        Enumeration headerNames = request.getHeaderNames();

        while(headerNames.hasMoreElements()) {
            String paramName = (String)headerNames.nextElement();
            System.out.print("" + paramName + ":::"+request.getHeader(paramName)+"\n");
            System.out.println("===========================\n");
        }

        */

        //返回测试
        Map<String, Object> maptest = new HashMap<String, Object>();
        maptest.put("ToUserName", "来自steffens");
        maptest.put("FromUserName", "发送给steffens");
        maptest.put("MsgType", "text");
        maptest.put("CreateTime", new Date().getTime());
        maptest.put("Content", "你好");

        // return sendTextMsg(maptest, "welcome");
    }

    /**
     * 此处是处理微信服务器的消息转发的
     */
    @RequestMapping(value = "login", method = {RequestMethod.POST})
    public void login2(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 调用核心服务类接收处理请求
        System.out.println(" loginMessage - 调用核心服务类接收处理请求.\n");

        //将请求、响应的编码设置为utf-8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        //调用核心业务逻辑
        String respXml = CoreService.processRequest(request);
        System.out.println( respXml );

        //响应消息
        PrintWriter out = response.getWriter();
        out.print(respXml);

        out.close();

    }

    /**
     * 回复文本消息
     *
     * @param requestMap
     * @param content
     * @return
     */
    public static String sendTextMsg(Map<String, Object> requestMap, String content) {
        return mapToXML(requestMap);
    }

    public static String mapToXML(Map map) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        mapToXML2(map, sb);
        sb.append("</xml>");
        try {
            return sb.toString();
        } catch (Exception e) {
        }
        return null;
    }

    private static void mapToXML2(Map map, StringBuffer sb) {
        Set set = map.keySet();
        for (Iterator it = set.iterator(); it.hasNext(); ) {
            String key = (String) it.next();
            Object value = map.get(key);
            if (null == value)
                value = "";
            if (value.getClass().getName().equals("java.util.ArrayList")) {
                ArrayList list = (ArrayList) map.get(key);
                sb.append("<" + key + ">");
                for (int i = 0; i < list.size(); i++) {
                    HashMap hm = (HashMap) list.get(i);
                    mapToXML2(hm, sb);
                }
                sb.append("</" + key + ">");

            } else {
                if (value instanceof HashMap) {
                    sb.append("<" + key + ">");
                    mapToXML2((HashMap) value, sb);
                    sb.append("</" + key + ">");
                } else {
                    sb.append("<" + key + "><![CDATA[" + value + "]]></" + key + ">");
                }

            }

        }
    }

}
