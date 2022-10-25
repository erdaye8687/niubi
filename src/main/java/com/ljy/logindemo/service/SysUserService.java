package com.ljy.logindemo.service;

import com.ljy.logindemo.pojo.vo.LoginUser;
import com.ljy.logindemo.pojo.vo.SysUserVo;

import java.util.List;

/**
 * @author liangjy
 * @Description TODO
 * @createTime 2022/10/18
 */
public interface SysUserService {
    String login(LoginUser loginUser);

    List<SysUserVo> findUserList(SysUserVo sysUserVo);
}
