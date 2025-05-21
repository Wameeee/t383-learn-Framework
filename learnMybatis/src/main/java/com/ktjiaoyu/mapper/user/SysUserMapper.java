package com.ktjiaoyu.mapper.user;

import com.ktjiaoyu.entity.SysUser;

import java.util.List;

/**
 * 系统用户数据访问接口
 */
public interface SysUserMapper {
    /**
     * 统计系统用户总数
     *
     * @return 用户总数
     */
    int count();

    /**
     * 查询用户列表
     *
     * @return 用户列表
     */
    List<SysUser> getUserList();
}
