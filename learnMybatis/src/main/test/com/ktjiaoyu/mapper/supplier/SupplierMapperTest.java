package com.ktjiaoyu.mapper.supplier;

import com.ktjiaoyu.entity.Supplier;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

class SupplierMapperTest {

    private static final Logger logger = Logger.getLogger(SupplierMapperTest.class);

    @Test
    void getSupplierList() throws Exception {
        SqlSession sqlSession = null;
        List<Supplier> supplierList = null;

        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            supplierList = sqlSession.selectList("com.ktjiaoyu.mapper.supplier.SupplierMapper.getSupplierList");

            for (Supplier supplier : supplierList) {
                System.out.println(supplier.getSupName());
            }

        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    @Test
    void supplierCount() throws Exception {
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
            int count = sqlSession.getMapper(SupplierMapper.class).supplierCount();
            logger.debug("供货商统计结果: " + count);

        } finally {
            // 4. 安全关闭资源
            if (sqlSession != null) {
                sqlSession.close();
            }
            if (is != null) {
                is.close();
            }
        }
    }
}