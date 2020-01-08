package com.example.demo;

import com.example.demo.oj.RunJudge;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FileUploadController {
    List<Message> messages = new ArrayList<>();
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, Model model){
        if(file.isEmpty()){
          model.addAttribute("up","文件为空上传失败");
          return"upload";
        }
    String fileName = file.getOriginalFilename();
        String filePath = "F:\\CollegeTime_PKU\\1B003-Java 程序设计\\project\\第十组源码\\rundir\\";
        File dest = new File(filePath+fileName);

        try{
            file.transferTo(dest);
//            model.addAttribute("up","上传成功,正在判题");
            messages.add(RunJudge.run());
            System.out.println(RunJudge.run().toString());
            model.addAttribute("messaage",messages);
            return "upload";
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("up","上传失败");
        return "upload";
    }


}
