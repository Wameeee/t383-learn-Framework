package com.ktjiaoyu.mapper.supplier;

import com.ktjiaoyu.entity.Supplier;
import com.ktjiaoyu.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

class SupplierMapperTest {

    private static final Logger logger = Logger.getLogger(SupplierMapperTest.class);

    @Test
    void getSupplierList() throws Exception {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        List<Supplier> supplierList = null;

        try {
            supplierList = sqlSession.selectList("com.ktjiaoyu.mapper.supplier.SupplierMapper.getSupplierList");

            for (Supplier supplier : supplierList) {
                System.out.println(supplier.getSupName());
            }

        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }
    }

    @Test
    void supplierCount() throws Exception {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        InputStream is = null;

        try {
            // 3. 执行查询
            int count = sqlSession.getMapper(SupplierMapper.class).count();
            logger.debug("供货商统计结果: " + count);

        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
            if (is != null) {
                is.close();
            }
        }
    }
}