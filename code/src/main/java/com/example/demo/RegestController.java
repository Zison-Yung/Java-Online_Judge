package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegestController {
    @Autowired
    private UserService userService;
    @RequestMapping("addUser")
    public String addUser(Model model,@RequestParam("userName")String userName, @RequestParam("passWord")String passWord){
        user u = new user();
        u.setUserName(userName);
        u.setPassWord(passWord);
       if (userService.save(u)!=null){

            model.addAttribute("m","注册成功");
       }
       else
           model.addAttribute("m","注册失败");

        System.out.println(u.toString());
        return "zhuce";
    }


}
