package com.example.demo.oj;

import com.example.demo.Message;
import com.example.demo.judge;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RunJudge
{


    public static void main(String[] args) throws IOException, InterruptedException
    {
        long starttime = System.currentTimeMillis();
        Message message = test1();
        long endtime = System.currentTimeMillis();
        message.setTime(endtime-starttime);
//        System.out.println(message.getErr().equals(""));
//        System.out.println(!message.getErr().equals("")&&message.getResult().equals("Failed"));
        if (!message.getErr().equals("")&&message.getResult().equals("Failed")){message.setCompare("");}
        System.out.println(message.toString());

        /**
            以下增加测试用例
         */
        //tsst2();
        //test3();
    }

    static String runPath = "F:\\CollegeTime_PKU\\1B003-Java 程序设计\\project\\第十组源码\\rundir\\"; //用户程序运行地址
    static String testPath = "F:\\CollegeTime_PKU\\1B003-Java 程序设计\\project\\OJ_File\\";  //测试用例(.in,.out)存放地址

    public static Message run(){

        long starttime = System.currentTimeMillis();
        Message message = null;
        try {
            message = test1();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endtime = System.currentTimeMillis();
        message.setTime(endtime-starttime);
        if (!message.getErr().equals("")&&message.getResult().equals("Failed")){message.setCompare("");}
        return message;
    }

    public static String getOut(String outPath)  //读入测试用例输出、临时结果输出
    {
        BufferedReader bf_out;
        String line_out = "";
        try
        {
            Reader r_out = new FileReader(outPath);
            bf_out = new BufferedReader(r_out);
            String line = bf_out.readLine();
            while (line != null)
            {
                line_out += line + " ";
                line = bf_out.readLine();
            }
            bf_out.close();
            r_out.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return line_out.trim();
    }

    public static String compileMain0(String runPath) throws IOException  //编译用户程序Main0
    {
        List<String> command = new ArrayList<>();
        command.add("javac");
        command.add("Main0.java");  //Main0文件为用户程序
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.directory(new File(runPath));  //改变运行目录
        Process p = pb.start();

        //接收并打印错误流信息
        InputStream err1 = p.getErrorStream();
        BufferedReader br1 = new BufferedReader(new InputStreamReader(err1,"GBK"),4096);
        String line = br1.readLine();
        StringBuffer sb = new StringBuffer();
        if (line != null)
        {

            sb = sb.append(line);
            System.out.println("错误信息:"+line);
            line = br1.readLine();

        }
        return sb.toString();
    }

    public static String getResult(String testInPath,String runPath,String tempOutPath) throws IOException, InterruptedException   //运行用户程序，得到临时.out文件
    {
        ProcessBuilder pb = new ProcessBuilder();
        pb.directory(new File(runPath)); //程序运行目录
        pb.redirectErrorStream(true);  //合并错误流和标准输出流
        //进程输入重定向为x.in文件，输出重定向为x_temp.out文件
        pb.command("java","Main0").redirectInput(new File(testInPath)).redirectOutput(new File(tempOutPath));
        Process p = pb.start();

        //处理报错信息（已和标准输出合并）
        InputStream err = p.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(err,"GBK"),4096);
        String line = br.readLine();
        if (line != null)
        {
            System.out.println("合并之后:"+line);
            line = br.readLine();
        }

        //10s运行不完，显示超时
        boolean state = p.waitFor(10,TimeUnit.SECONDS);
        if (state == false)
        {
            System.out.println("运行超时！");
            return "运行超时";
        }

        br.close();
        err.close();
        return"";
    }

    public static judge judge(String testOut,String outContent)  //判断对错文件
    {
        judge judge = new judge();
        if (testOut.equals(outContent))
        {
            judge.setJudge("Accepted");
            judge.setCompare("");

           return judge;
//            System.out.println("Accepted");
        }
        else
        {
            judge.setJudge("Failed");
            judge.setCompare("正确结果:"+testOut+"实际结果:"+outContent);
//            System.out.println("Failed");
            return judge;

        }


    }

    /**
     * 以下为测试用例
     * @throws IOException
     * @throws InterruptedException
     */
    public static Message test1() throws IOException, InterruptedException
    {
        Message message = new Message();
        //获取测试用例输出
        String testOut = getOut(testPath+"x.out");
        //System.out.println("测试用例" + testOut);

        //编译用户文件
      String a = compileMain0(runPath);

        //运行用户文件，得到临时输出文件temp.out
      String b =  getResult(testPath+"x.in",runPath,runPath+"x_temp.out");

        //获得临时输出
        String tempOut = getOut(runPath+"x_temp.out");
        //System.out.println("实际结果" + tempOut);

        //判断正确
        judge(testOut,tempOut);
        message.setResult(judge(testOut,tempOut).getJudge());
        message.setErr(a+b);
        message.setDate(new Date());

        message.setCompare(judge(testOut,tempOut).getCompare());
       return message;
    }

    /**
        需要新增，在下列填充
     */
    
//    public static void test2()
//    {}
//
//    public static void test3()
//    {}
//
//    public static void test4()
//    {}
}
