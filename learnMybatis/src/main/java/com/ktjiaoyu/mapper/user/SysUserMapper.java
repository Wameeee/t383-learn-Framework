package com.ktjiaoyu.mapper.user;

import com.ktjiaoyu.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    /**
     *  根据用户姓名模糊查询列表
     *
     * @return 用户列表
     */
   List<SysUser> getUserListByName(String realName);

    /**
     * 查询用户列表
     * @param sysUser
     * @return 用户列表
     */
   List<SysUser> getUserListByEntity(SysUser sysUser);

    /**
     * 使用MAP查询用户列表
     * @param map
     * @return 根据map查询出的用户列表
     */
   List<SysUser> getUserListByMap(Map<String,Object> map);

    /**
     * 使用Param查询用户信息
     * @param realName
     * @param roleId
     * @return 用户信息
     */
   List<SysUser> getUserListByParams(@Param("realName") String realName, @Param("roleId") int roleId);

    /**
     * 查询用户列表，包括角色名称
     * @param sysUser
     * @return 用户列表，包括角色名称
     */
   List<SysUser> getUserListWithRoleName(SysUser sysUser);
}
