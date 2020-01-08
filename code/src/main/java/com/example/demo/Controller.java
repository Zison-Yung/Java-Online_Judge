package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.stereotype.Controller
public class Controller {
//    @RequestMapping("/")
//    public String index(){
//        return "login_good";
//    }
//    @RequestMapping("zhuce")
//    public  String M(){
//        return "zhuce";
//    }
//    @RequestMapping("targetAdd")
//    public String login(@RequestParam(value = "username") String username,@RequestParam(value = "password") String password){
//        if(username.isEmpty()||password.isEmpty()) return "login_good";
//        else if (username.equals("zxc")&&password.equals("123456")) return "main";
//        else
//        return "login_good";
//    }

    @RequestMapping("dati")
    public  String dati(){
//        String answer = "运行成功";
//        model.addAttribute("answer",answer);
        return "upload";
    }



}
