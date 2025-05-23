package com.ktjiaoyu.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtils {
    private static final Logger logger = Logger.getLogger(MyBatisUtils.class);
    private static SqlSessionFactory factory;

    static {
        try {
            logger.debug("初始化MyBatis SqlSessionFactory...");
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            factory = new SqlSessionFactoryBuilder().build(is);
            logger.debug("MyBatis SqlSessionFactory初始化成功");
        } catch (IOException e) {
            logger.error("初始化SqlSessionFactory失败", e);
            throw new RuntimeException("初始化失败", e);
        }
    }

    /**
     * 创建默认的SqlSession（手动提交事务）
     *
     * @return SqlSession实例
     */
    public static SqlSession createSqlSession() {
        logger.debug("创建默认的SqlSession（手动提交事务）");
        return factory.openSession(false);
    }

    /**
     * 创建自动提交事务的SqlSession
     *
     * @return SqlSession实例
     */
    public static SqlSession createAutoCommitSqlSession() {
        logger.debug("创建自动提交事务的SqlSession");
        return factory.openSession(true);
    }

    /**
     * 安全关闭SqlSession
     * @param sqlSession 要关闭的SqlSession
     */
    public static void closeSqlSession(SqlSession sqlSession) {
        if (sqlSession != null) {
            try {
                logger.debug("关闭SqlSession");
                sqlSession.close();
            } catch (Exception e) {
                logger.error("关闭SqlSession失败", e);
            }
        }
    }

    /**
     * 安全提交事务并关闭SqlSession
     *
     * @param sqlSession 要提交和关闭的SqlSession
     */
    public static void commitAndClose(SqlSession sqlSession) {
        if (sqlSession != null) {
            try {
                logger.debug("提交事务");
                sqlSession.commit();
                logger.debug("关闭SqlSession");
                sqlSession.close();
            } catch (Exception e) {
                logger.error("提交事务或关闭SqlSession失败", e);
            }
        }
    }

    /**
     * 安全回滚事务并关闭SqlSession
     *
     * @param sqlSession 要回滚和关闭的SqlSession
     */
    public static void rollbackAndClose(SqlSession sqlSession) {
        if (sqlSession != null) {
            try {
                logger.debug("回滚事务");
                sqlSession.rollback();
                logger.debug("关闭SqlSession");
                sqlSession.close();
            } catch (Exception e) {
                logger.error("回滚事务或关闭SqlSession失败", e);
            }
        }
    }
}
