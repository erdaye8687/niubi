package com.ljy.logindemo.service;

import com.ljy.logindemo.pojo.vo.LoginUser;

/**
 * @author liangjy
 * @Description TODO
 * @createTime 2022/10/18
 */
public interface SysUserService {
    String login(LoginUser loginUser);
}
