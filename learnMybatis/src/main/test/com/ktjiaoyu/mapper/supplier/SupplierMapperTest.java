package com.ktjiaoyu.mapper.supplier;

import com.ktjiaoyu.entity.StorageRecord;
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

    @Test
    void testGetSupplierListAndStorageRecordListBySupId() throws Exception {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        try {
            List<Supplier> supplierList = sqlSession.getMapper(SupplierMapper.class).getSupplierListAndStorageRecordListBySupId(100);
            for (Supplier supplier : supplierList) {
                logger.debug("supplier===>  供货商Id:" + supplier.getId() +
                        ",供货商编码:" + supplier.getSupCode() +
                        ",供货商名称:" + supplier.getSupName() +
                        ",联系人:" + supplier.getSupContact() +
                        ",联系电话:" + supplier.getSupPhone()
                );
                for (StorageRecord storageRecord : supplier.getStorageRecord()) {
                    logger.debug("storageRecord===> 入库记录编码:" + storageRecord.getSrCode() +
                            ",商品名称:" + storageRecord.getGoodsName() +
                            ",商品总额:" + storageRecord.getTotalAmount() +
                            ",支付状态:" + storageRecord.getPayStatus()
                    );
                }
            }
        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }
    }
}