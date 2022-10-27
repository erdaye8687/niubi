package com.ljy.logindemo.service.impl;

import com.ljy.logindemo.pojo.vo.SysUserVo;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

/**
 * @author liangjy
 * @Description TODO
 * @createTime 2022/10/25
 */
@SpringBootTest
public class LombokT {

    @Test
    public void lombokTest() {
        SysUserVo sysUserVo = SysUserVo.builder()
                .userName("ergouzi")
                .password("87532867")
                .createBy(new Date().toString())
                .delFlag("0").build();
        System.out.println(sysUserVo.toString());
        SysUserVo sysUserVo1 = sysUserVo.withSex("1");
        System.out.println(sysUserVo1.toString());
    }
}
