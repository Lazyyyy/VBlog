package com.virgil.vblog.service;

import com.virgil.vblog.component.User;
import com.virgil.vblog.dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:VirgilWong
 * @Description:
 * @Date: Create in 5:28 2020/4/12
 * 类的设计原则：
 * 1.一定要保证数据的私有
 * 2.一定要对数据初始化，让程序阅读性更好
 * 3.不要过多的使用基本类型，可以把要用的基本类型封装成一个对象
 * 4.不是所有的域都需要独立的域访问器或域更改器：比如有些数据不需要被人访问的
 * 5.将职责过多的类进行分解
 * 6.类名要体现该类的职责
 * 7.优先使用不可变的类
 */
@Service
@Transactional
public class AuthencationService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    //实现该接口来查询对应的用户
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> userList= userMapper.select();
        User user = null;
        for (User userone : userList){
            if (userone.getUsername().equals(username)){
                user = userone;
                break;
            }
        }

        if (user == null){
            System.out.println("不存在该用户");
            throw new UsernameNotFoundException("用户不存在");
        }

        //添加用户权限。此处应该实现一个与User关联的Role表，以指定用户权限
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));

        return new org.springframework.security.core.userdetails.User(user.getUsername()
                ,user.getPasswd(),authorities);
    }
}
