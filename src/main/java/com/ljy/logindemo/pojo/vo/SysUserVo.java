package com.ljy.logindemo.pojo.vo;

import lombok.Builder;
import lombok.Data;
import lombok.With;

import java.util.Date;

/**
 * @author liangjy
 * @Description TODO
 * @createTime 2022/10/18
 */
@With
@Builder
@Data
public class SysUserVo {
    private Long userId;

    private Long deptId;

    private String userName;

    private String nickName;

    private String userType;

    private String email;

    private String phonenumber;

    private String sex;

    private String avatar;

    private String password;

    private String status;

    private String delFlag;

    private String loginIp;

    private Date loginDate;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;
    private Integer pageNum;
    private Integer pageSize;
}
