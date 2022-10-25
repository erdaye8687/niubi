package com.ljy.logindemo.service.impl;

import com.alibaba.fastjson.JSON;
import com.ljy.logindemo.dao.SysUserMapper;
import com.ljy.logindemo.pojo.po.SysUser;
import com.ljy.logindemo.pojo.po.SysUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author liangjy
 * @Description TODO
 * @createTime 2022/10/19
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if (StringUtils.isBlank(userName)) {
            log.warn("用户登陆用户名为空:{}", userName);
            throw new UsernameNotFoundException("用户名不能为空");
        }
        SysUser sysUser = new SysUser();
        sysUser.setUserName(userName);
        sysUser = sysUserMapper.selectOne(sysUser);
        if (sysUser == null) {
            log.warn("根据用户名没有查找到用户:{}", userName);
        }
        log.info("根据用户名:{}获取用户登陆信息:{}", userName, JSON.toJSONString(sysUser));

        SysUserDetails sysUserDetails = new SysUserDetails(sysUser);
        return sysUserDetails;
    }
}
