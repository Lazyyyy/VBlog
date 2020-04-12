package com.virgil.vblog.config;

import com.virgil.vblog.service.AuthencationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author:VirgilWong
 * @Description:
 * @Date: Create in 5:03 2020/4/12
 * 类的设计原则：
 * 1.一定要保证数据的私有
 * 2.一定要对数据初始化，让程序阅读性更好
 * 3.不要过多的使用基本类型，可以把要用的基本类型封装成一个对象
 * 4.不是所有的域都需要独立的域访问器或域更改器：比如有些数据不需要被人访问的
 * 5.将职责过多的类进行分解
 * 6.类名要体现该类的职责
 * 7.优先使用不可变的类
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthencationService authencationService;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //设置密码的加密方式
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //设置不要隐藏Not Found异常
        provider.setHideUserNotFoundExceptions(false);
        //重写config方法来添加自定义的认证方式
        provider.setUserDetailsService(authencationService);
        //设置密码加密程序认证
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/authentication/*","/login")
                .permitAll()
                .antMatchers("/user/**").hasAnyRole("USER")
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/authentication/login")//设置登录的路径（和Controller种的Mapping路径一致）
                .loginProcessingUrl("/authentication/form")//暂不知道该方法的意义
                .defaultSuccessUrl("/user/index");//设置登录成功跳转的界面
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }
}
