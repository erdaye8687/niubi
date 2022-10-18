package com.ljy.logindemo.controller;

import com.ljy.logindemo.common.CommonResult;
import com.ljy.logindemo.pojo.vo.LoginUser;
import com.ljy.logindemo.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liangjy
 * @Description TODO
 * @createTime 2022/10/18
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys/user")
public class SysAdminController {
    private final SysUserService sysUserService;

    @PostMapping("/login")
    public CommonResult login(@RequestBody LoginUser loginUser) {
        String token = sysUserService.login(loginUser);


        return CommonResult.success(token);
    }
}
