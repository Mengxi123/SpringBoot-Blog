package com.org.service;

import com.org.dao.UserRepository;
import com.org.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Create by MengXi on 2021/10/12 15:54.
 *
 * @Service  用于业务层的注解，其就是@Component的别名，将资源交给spring进行管理
 * @Autowired 注入属性，自动按照类型注入。
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        return user;
    }
}
