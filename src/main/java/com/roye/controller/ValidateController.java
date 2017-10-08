package com.roye.controller;

import com.alibaba.fastjson.JSONObject;

import com.google.code.kaptcha.Producer;

import com.roye.constant.Status;
import com.roye.constant.ThirdPartyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class ValidateController {


    @Autowired
    private Producer captchaProducer = null;

    //发送验证码的请求路径URL
    private static final String
            SERVER_URL="https://api.netease.im/sms/sendcode.action";
    //网易云信分配的账号，请替换你在管理后台应用下申请的Appkey
    private static final String
            APP_KEY="8ec7d12b788178710772b5c6d68b15ed";
    //网易云信分配的密钥，请替换你在管理后台应用下申请的appSecret
    private static final String APP_SECRET="ce85111cd77c";

    /***
     *  点击发送按钮之后调用云信接口发送短信
     * @param request 请求
     * @return 返回发送的状态给注册页面
     */
    @RequestMapping(value="/sendSMS", method= RequestMethod.POST)
    @ResponseBody
    public JSONObject sendSMS(HttpServletRequest request, HttpServletResponse response){
    String phone = request.getParameter("phone");
    JSONObject obj = new JSONObject();
//    System.out.print("进来了吗");
//    System.out.println(phone);
    if("".equals(phone) || phone == null){
        obj.put("code", Status.PARAM_ERROR_CODE);
        obj.put("msg", "请输入验证码");
        return obj;
    }else{
        //逻辑代码
        // 处理网易云信的SDK, 随机生成， 发送， 储存在session中去


        obj.put("code", Status.SUCCESS_CODE);
        obj.put("msg", "成功");
    }
     return obj;
   }

    /**
     * 图片的请求
     * @param request
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "/captchaImage")
   public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {

        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");


        String capText = captchaProducer.createText();

        //存储在session 中
        request.getSession().setAttribute(ThirdPartyConstants.SESSION_IMAGE_CODE, capText);
        // 设置有效期为3分钟
        request.getSession().setMaxInactiveInterval(3000);

        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            ImageIO.write(bi, "jpg", out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(out != null){
                out.close();
            }
        }
    }


    /**
     * 检查验证码
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkCaptchaImage")
    public JSONObject  checkKaptchaImage(HttpServletRequest request, HttpServletResponse response){

        String imgCode = request.getParameter("imgCode");
        JSONObject obj = new JSONObject();

        if(imgCode == null || "".equals(imgCode)){
            obj.put("code", Status.PARAM_ERROR_CODE);
            obj.put("msg", "请输入图形验证码！123");
            return obj;
        }

        imgCode = imgCode.toLowerCase();

        HttpSession session = request.getSession();
        String sessionCode = (String)session.getAttribute(ThirdPartyConstants.SESSION_IMAGE_CODE);
        if(sessionCode == null || !(imgCode.equals(sessionCode.toLowerCase()))){
            obj.put("code", Status.PARAM_ERROR_CODE);
            obj.put("msg", "图形验证码错误或者验证码失效！123");
            return obj;
        }

        obj.put("code", Status.SUCCESS_CODE);
        return obj;
    }

}
