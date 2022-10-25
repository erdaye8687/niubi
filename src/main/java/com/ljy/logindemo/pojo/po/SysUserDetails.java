package com.ljy.logindemo.pojo.po;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

/**
 * SpringSecurity需要的用户详情
 * Created on 2018/4/26.
 */
public class SysUserDetails implements UserDetails {
    private SysUser sysUser;
//    private List<UmsPermission> permissionList;

    public SysUserDetails(SysUser sysUser) {
        this.sysUser = sysUser;
    }
//    public SysUserDetails(UmsAdmin umsAdmin, List<UmsPermission> permissionList) {
//        this.sysUser = umsAdmin;
//        this.permissionList = permissionList;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        /*return permissionList.stream()
                .filter(permission -> permission.getValue()!=null)
                .map(permission ->new SimpleGrantedAuthority(permission.getValue()))
                .collect(Collectors.toList());*/
        return Arrays.asList(new SimpleGrantedAuthority("TEST"));
    }

    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return sysUser.getStatus().equals(1);
    }
}
