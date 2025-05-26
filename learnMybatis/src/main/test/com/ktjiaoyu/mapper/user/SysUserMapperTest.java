package com.ktjiaoyu.mapper.user;

import com.ktjiaoyu.entity.Address;
import com.ktjiaoyu.entity.SysUser;
import com.ktjiaoyu.utils.MyBatisUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

class SysUserMapperTest {
    private static final Logger logger = Logger.getLogger(SysUserMapperTest.class);

    @Test
    void count() throws Exception {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        InputStream is = null;

        try {
            // 1. 加载 MyBatis 配置文件
            String resource = "mybatis-config.xml";
            is = Resources.getResourceAsStream(resource);

            // 2. 创建 SqlSessionFactory
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
            sqlSession = factory.openSession();

            // 3. 执行查询
            int count = sqlSession.getMapper(SysUserMapper.class).count();
            logger.debug("用户数量统计结果: " + count);

        } catch (Exception e) {
            logger.error("执行count测试时出错", e);
            throw e; // 重新抛出异常使测试失败
        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }
        if (is != null) {
            is.close();
        }
    }


    @Test
    public void testUserListQuery() throws Exception {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        List<SysUser> userList = null;
        try {
            // 读取配置文件
            String resource = "mybatis-config.xml";
            // 获取配置文件输入流
            InputStream is = Resources.getResourceAsStream(resource);
            // 使用SqlSessionFactoryBuilder读取配置文件并构建 ， SqlSessionFactory实例
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
            // 创建sqlSession实例
            sqlSession = factory.openSession();
            //  创建mapper实例，调用查询语句
            userList = sqlSession.getMapper(SysUserMapper.class).getUserList();
        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }
        if (userList != null) {
            //日志循环打印
            for (SysUser user : userList) {
                logger.debug("testUserListQuery account" + user.getAccount() + " and realName:" + user.getRealName());
            }
        }
    }

    @Test
    public void getUserListByName() throws Exception {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        try {
            List<SysUser> userList = sqlSession.getMapper(SysUserMapper.class).getUserListByName("李");
            for (SysUser user : userList) {
                logger.debug("testUserListQuery account:" + user.getAccount() + " and realName:" + user.getRealName());
            }
        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }
    }

    @Test
    public void getUserListByEntity() throws Exception {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        try {
            SysUser user = new SysUser();
            user.setRealName("李");
            user.setRoleId(3);
            List<SysUser> userList = sqlSession.getMapper(SysUserMapper.class).getUserListByEntity(user);
            for (SysUser user1 : userList) {
                logger.debug("testUserListQuery account:" + user1.getAccount() + " and realName:" + user1.getRealName());
            }
        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }
    }

    @Test
    public void getUserByMap() throws Exception {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        try {
            Map<String, Object> userMap = new HashMap<String, Object>();
            userMap.put("rName", "赵");
            userMap.put("rId", 2);
            List<SysUser> userList = sqlSession.getMapper(SysUserMapper.class).getUserListByMap(userMap);
        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }
    }

    @Test
    public void getUserListByParam() throws Exception {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        try {
            List<SysUser> userList = sqlSession.getMapper(SysUserMapper.class).getUserListByParams("赵", 2);
            for (SysUser user1 : userList) {
                logger.debug("testUserListQuery 账号:" + user1.getAccount() + "，和真实姓名:" + user1.getRealName());
            }
        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }

    }

    @Test
    public void testGetUserListWithRoleName() throws Exception {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        try {
            // 创建查询参数
            SysUser queryUser = new SysUser();
            queryUser.setRealName("李"); // 设置查询条件：姓名包含"李"
            queryUser.setRoleId(3);     // 设置查询条件：角色ID为3

            // 调用Mapper接口方法
            List<SysUser> userList = sqlSession.getMapper(SysUserMapper.class).getUserListWithRoleName(queryUser);

            // 验证并打印结果
            if (userList != null && !userList.isEmpty()) {
                for (SysUser user : userList) {
                    logger.debug("用户信息 - 账号:" + user.getAccount() + ", 姓名:" + user.getRealName() + ", 角色名称:" + user.getUserRoleName());
                }
                logger.debug("查询到 " + userList.size() + " 条符合条件的用户记录");
            } else {
                logger.debug("未查询到符合条件的用户记录");
            }
        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }
    }

    @Test
    public void testGetUserListWithRoleId() throws Exception {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        try {
            List<SysUser> userList = sqlSession.getMapper(SysUserMapper.class).getUserListByRoleId(2);
            for (SysUser user : userList) {
                logger.debug("testGetUserList 用户id：" + user.getId() + " ,姓名：" + user.getRealName() + ",职位id：" + user.getSysRole().getId() + ",code：" + user.getSysRole().getCode() + ",职位名称：" + user.getSysRole().getRoleName());
            }
        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }
    }

    @Test
    public void testGetUserListAndAddressList() throws Exception {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        try {
            List<SysUser> userList = sqlSession.getMapper(SysUserMapper.class).getUserAndAddressByUserId(1);
            for (SysUser user : userList) {
                logger.debug("userList ===> 账号:" + user.getAccount() + ",姓名:" + user.getRealName());
                for (Address address : user.getAddressList()) {
                    logger.debug("address ===> 地址Id:" + address.getId() + ",联系人姓名:" + address.getContact() + ",详情地址:" + address.getAddressDesc() + ",电话" + address.getTel() + ",邮政编码" + address.getPostCode());
                }
            }
        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }
    }

    @Test
    public void testAddUser() throws Exception {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        try {
            // 创建一个新的SysUser对象
            SysUser user = new SysUser();
            user.setAccount("testuser");
            user.setRealName("测试用户");
            user.setPassword("123456");
            user.setSex(1);
            user.setBirthday(new Date());
            user.setPhone("13800138000");
            user.setAddress("北京市海淀区");
            user.setRoleId(1);
            user.setCreatedUserId(1);
            user.setCreatedTime(new Date());

            // 调用addUser方法
            int result = sqlSession.getMapper(SysUserMapper.class).addUser(user);

            // 提交事务
            sqlSession.commit();

            // 验证结果
            logger.debug("添加用户结果: " + result);
            if (result > 0) {
                logger.debug("添加用户成功，用户账号: " + user.getAccount() + ", 真实姓名: " + user.getRealName());
            } else {
                logger.debug("添加用户失败");
            }
        } finally {
            // 关闭SqlSession
            MyBatisUtils.closeSqlSession(sqlSession);
        }
    }

    @Test
    public void testUpdateUser() throws Exception {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        try {
            // 创建一个新的SysUser对象
            SysUser user = new SysUser();
            // 设置要更新的用户ID
            user.setId(1); // 假设ID为1的用户已存在
            user.setAccount("updateduser");
            user.setRealName("更新的用户");
            user.setPassword("654321");
            user.setSex(1);
            user.setBirthday(new Date());
            user.setPhone("13900139000");
            user.setAddress("上海市浦东新区");
            user.setRoleId(2);
            user.setUpdateUserId(1);
            user.setUpdateTime(new Date());

            // 调用updateUser方法
            int result = sqlSession.getMapper(SysUserMapper.class).updateUser(user);

            // 提交事务
            sqlSession.commit();

            // 验证结果
            logger.debug("更新用户结果: " + result);
            if (result > 0) {
                logger.debug("更新用户成功，用户ID: " + user.getId() + ", 账号: " + user.getAccount() + ", 真实姓名: " + user.getRealName());
            } else {
                logger.debug("更新用户失败");
            }
        } finally {
            // 关闭SqlSession
            MyBatisUtils.closeSqlSession(sqlSession);
        }
    }

    @Test
    void testDeleteUserById() throws Exception {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        try {
            int result = sqlSession.getMapper(SysUserMapper.class).deleteUserById(1);
            sqlSession.commit();
            logger.debug("删除用户结果:" + result);
            if (result > 0) {
                logger.debug("删除用户成功");
            } else {
                logger.debug("删除用户失败");
            }
        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }
    }

    @Test
    void testGetUserList() throws Exception {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        try {
            String realName = null;
            Integer roleId = null;
            List<SysUser> userList = sqlSession.getMapper(SysUserMapper.class).selectList(realName, roleId);
            logger.debug("查询到的用户数量:" + userList.size());
            for (SysUser user : userList) {
                logger.debug("userList ===> <查询到的用户信息>:" + user.toString());
            }
        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }
    }

    @Test
    void testGetUserListByChoose() throws Exception {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        List<SysUser> userList = new ArrayList<SysUser>();
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            String realName = "";
            Integer roleId = null;
            String account = "";
            Date createdTime = new SimpleDateFormat("yyyy-MM-dd").parse("2024-11-11");
            userList = sqlSession.getMapper(SysUserMapper.class)
                    .selectListByChoose(realName, account, roleId, createdTime);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }
        logger.info("查询到的用户数量:" + userList.size());
        for (SysUser user : userList) {
            logger.debug("查询到的用户信息:" + user);
        }
    }

    @Test
    void testGetUserByRoleIdByArray() throws Exception {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        List<SysUser> userList = null;
        Integer[] roleIds = {2, 3};
        try {
            userList = sqlSession.getMapper(SysUserMapper.class).selectListByRoleArray(roleIds);
        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }
        logger.info("查询到的用户数量:" + userList.size());
        for (SysUser user : userList) {
            logger.debug("查询到的用户信息:" + user);
        }
    }

    @Test
    void testGetUserByRoleIdList() throws Exception {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        List<SysUser> userList = null;
        List<Integer> roleList = new ArrayList<Integer>();
        roleList.add(1);
        roleList.add(2);
        try {
            userList = sqlSession.getMapper(SysUserMapper.class).getUserByRoleIdList(roleList);
        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }
        logger.info("获取到的用户数量:" + userList.size());
        for (SysUser user : userList) {
            logger.info("获取到的用户信息：" + user);
        }
    }

    @Test
    void testGetUserByRoleIdMap() throws Exception {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        List<SysUser> userList = null;
        List<Integer> roleList = new ArrayList<Integer>();
        roleList.add(1);
        roleList.add(2);
        Map<String, Object> roleMap = new HashMap<String, Object>();
        roleMap.put("roleIdList", roleList);
        try {
            userList = sqlSession.getMapper(SysUserMapper.class).getUserByRoleIdMap(roleMap);
        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }
        logger.info("获取到的用户数量:" + userList.size());
        for (SysUser user : userList) {
            logger.info("获取到的用户信息：" + user);
        }
    }
}


