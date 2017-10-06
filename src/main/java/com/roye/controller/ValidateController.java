package com.roye.controller;

import com.alibaba.fastjson.JSONObject;
import com.roye.constant.Status;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ValidateController {

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

}
