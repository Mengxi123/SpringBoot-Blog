package com.org.service;

import com.org.po.User;

/**
 * @author Create by MengXi on 2021/10/12 15:52.
 */
public interface UserService {
    /**
     * 检查用户名密码
     * @param username
     * @param password
     * @return
     */
    User checkUser(String username, String password);
}
