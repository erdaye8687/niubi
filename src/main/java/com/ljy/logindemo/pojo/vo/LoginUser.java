package com.ljy.logindemo.pojo.vo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotEmpty;
import java.util.Collection;

/**
 * @author liangjy
 * @Description TODO
 * @createTime 2022/10/18
 */
@Data
public class LoginUser {

    @NotEmpty(message = "用户名不能为空")
    private String userName;
    @NotEmpty(message = "密码不能为空")
    private String password;
}
