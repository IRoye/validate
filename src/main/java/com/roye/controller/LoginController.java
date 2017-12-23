package com.roye.controller;

import com.roye.domain.User;
import com.roye.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping("/jsp/logon")
    public String logon(@RequestParam("username") String username, @RequestParam("pass") String pass) {

        // 1.创建subject 实例：
        Subject currentUser = SecurityUtils.getSubject();

       /* // 2. 判断当前用户是否登录：
        if(currentUser.isAuthenticated() == false){

            // 将用户名和密码封装：
            UsernamePasswordToken token = new UsernamePasswordToken(username, pass);
            try{
                // 查询数据库
                currentUser.login(token);
            }catch(AuthenticationException e){
                System.out.println("登录失败！");
            }

        }*/

        User user = userService.getUserById(1);

        return "success";
    }

}
