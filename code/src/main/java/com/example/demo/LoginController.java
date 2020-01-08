package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    UserService userService;
    @RequestMapping("denglu")
    public String denglu(Model model, @RequestParam("userName")String userName, @RequestParam("passWord")String passWord){
        if(userService.find(userName)!=null&&userService.find(userName).getPassWord().equals(passWord))
            return"main";
        else{
            model.addAttribute("m","用户名或者密码错误");
            return"index";
        }




    }
}
