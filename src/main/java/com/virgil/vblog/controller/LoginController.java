package com.virgil.vblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * @Author:VirgilWong
 * @Description:
 * @Date: Create in 4:59 2020/4/12
 * 类的设计原则：
 * 1.一定要保证数据的私有
 * 2.一定要对数据初始化，让程序阅读性更好
 * 3.不要过多的使用基本类型，可以把要用的基本类型封装成一个对象
 * 4.不是所有的域都需要独立的域访问器或域更改器：比如有些数据不需要被人访问的
 * 5.将职责过多的类进行分解
 * 6.类名要体现该类的职责
 * 7.优先使用不可变的类
 */
@Controller
public class LoginController {
    @GetMapping("/authentication/login")
    public String authenticationLogin() throws IOException{
        return "login";
    }

    @GetMapping("/admin/index")
    public String indexPage(){
        return "index";
    }
}
