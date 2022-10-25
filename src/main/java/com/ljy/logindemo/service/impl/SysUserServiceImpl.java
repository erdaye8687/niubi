package com.ljy.logindemo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljy.logindemo.dao.SysUserMapper;
import com.ljy.logindemo.pojo.po.SysUser;
import com.ljy.logindemo.pojo.po.SysUserDetails;
import com.ljy.logindemo.pojo.vo.LoginUser;
import com.ljy.logindemo.pojo.vo.SysUserVo;
import com.ljy.logindemo.service.SysUserService;
import com.ljy.logindemo.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author liangjy
 * @Description TODO
 * @createTime 2022/10/18
 */
@RequiredArgsConstructor
@Service
public class SysUserServiceImpl implements SysUserService {
    private final SysUserMapper sysUserMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public String login(LoginUser loginUser) {
        UserDetails userDetails = loadUserByUsername(loginUser.getUserName());
        if(!passwordEncoder.matches(loginUser.getPassword(),userDetails.getPassword())){
            throw new BadCredentialsException("密码不正确");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    @Override
    public List<SysUserVo> findUserList(SysUserVo sysUserVo) {
        PageHelper.startPage(sysUserVo.getPageNum(), sysUserVo.getPageSize());
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserVo, sysUser);
        List<SysUser> userList = sysUserMapper.select(sysUser);
        PageInfo<SysUser> sysUserPageInfo = new PageInfo<>(userList);
        List<SysUserVo> sysUserVos = BeanUtil.copyToList(userList, SysUserVo.class);
        return sysUserVos;
    }

    public UserDetails loadUserByUsername(String username){
        //获取用户信息
        SysUser sysUser = getAdminByUsername(username);
        if (sysUser != null) {
//            List<UmsPermission> permissionList = getPermissionList(admin.getId());
            return new SysUserDetails(sysUser);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    public SysUser getAdminByUsername(String username) {
        Example example = new Example(SysUser.class);
        example.createCriteria().andEqualTo("userName", username);
        List<SysUser> adminList = sysUserMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            return adminList.get(0);
        }
        return null;
    }
}
