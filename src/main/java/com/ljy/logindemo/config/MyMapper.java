package com.ljy.logindemo.config;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author liangjy
 * @Description TODO
 * @createTime 2022/10/18
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
