package com.roye.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @RequestMapping("/jsp/logon")
    public String logon(@RequestParam("username") String username, @RequestParam("pass") String pass) {

        System.out.println(username);
        if ("root".equals(username) && "123".equals(pass)) {
            return "success";
        }
        return "unauthorized";
    }

}
