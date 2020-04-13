package com.virgil.vblog.config.provider;

import com.virgil.vblog.service.AuthencationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:VirgilWong
 * @Description:
 * @Date: Create in 9:49 2020/4/13
 * 类的设计原则：
 * 1.一定要保证数据的私有
 * 2.一定要对数据初始化，让程序阅读性更好
 * 3.不要过多的使用基本类型，可以把要用的基本类型封装成一个对象
 * 4.不是所有的域都需要独立的域访问器或域更改器：比如有些数据不需要被人访问的
 * 5.将职责过多的类进行分解
 * 6.类名要体现该类的职责
 * 7.优先使用不可变的类
 */
@Component
public class CustmProvider implements AuthenticationProvider {
    @Autowired
    AuthencationService service;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)
                authentication;
        String username = token.getName();//前台用户名
        String passwd = token.getCredentials().toString();//前台的密码
        System.out.println("前台密码："+passwd);
        User user = (User) service.loadUserByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("Could not find the user!");
        }
        if (!user.getPassword().equals(passwd)){
            throw new BadCredentialsException("Password wrong！");
        }
        System.out.println("验证通过");
        return new UsernamePasswordAuthenticationToken(user,user.getPassword(),user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
