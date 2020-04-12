package com.virgil.vblog.component;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:VirgilWong
 * @Description:
 * @Date: Create in 9:38 2020/4/12
 * 认证成功处理类，可以为不同权限的用户显示不同的页面
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
public class AuthenticationSucessHandler extends SimpleUrlAuthenticationSuccessHandler {

    //Spring Security 通过RedirectStrategy对象来负责所有重定向事务
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    //重写handle方法，来指定重定向的url
    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        redirectStrategy.sendRedirect(request,response,"/admin");
    }
}
