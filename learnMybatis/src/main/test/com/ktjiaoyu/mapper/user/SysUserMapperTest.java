package com.ktjiaoyu.mapper.user;

import com.ktjiaoyu.entity.SysUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

class SysUserMapperTest {
    private static final Logger logger = Logger.getLogger(SysUserMapperTest.class);

    @Test
    void count() throws Exception {
        SqlSession sqlSession = null;
        InputStream is = null;

        try {
            // 1. 加载 MyBatis 配置文件
            String resource = "mybatis-config.xml";
            is = Resources.getResourceAsStream(resource);

            if (is == null) {
                throw new RuntimeException("找不到 mybatis-config.xml 配置文件");
            }

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
            // 4. 安全关闭资源
            if (sqlSession != null) {
                try {
                    sqlSession.close();
                } catch (Exception e) {
                    logger.error("关闭sqlSession时出错", e);
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                    logger.error("关闭输入流时出错", e);
                }
            }
        }
    }

    @Test
    public void testUserListQuery() throws Exception {
        SqlSession sqlSession = null;
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
            // 关闭SqlSession
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        if (userList != null) {
            //日志循环打印
            for (SysUser user : userList) {
                logger.debug("testUserListQuery account" + user.getAccount() + " and realName:" + user.getRealName());
            }
        }
    }
}