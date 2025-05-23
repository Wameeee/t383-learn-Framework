package com.ktjiaoyu.mapper.storage_record;

import com.ktjiaoyu.entity.StorageRecord;
import com.ktjiaoyu.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.List;


class StorageRecordMapperTest {
    private static final Logger logger = Logger.getLogger(StorageRecordMapperTest.class);

    @Test
    void getStorageRecordList() throws Exception {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        try {
            List<StorageRecord> storageRecordList =
                    sqlSession.getMapper(storage_recordMapper.class).getStorageRecordList("牛奶", 100, 1);
            for (StorageRecord list : storageRecordList) {
                logger.debug("TestStorage_recordQuery 商品详情:" + list.getGoodsDesc() + "，和价格:" + list.getTotalAmount());
            }
        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }
    }

    @Test
    void getStorageRecordWithSupplierName() {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        try {
            // 创建查询参数
            StorageRecord querySr = new StorageRecord();
            querySr.setGoodsName("奶");
            querySr.setSupplierId(100);

            // 调用Mapper接口方法
            List<StorageRecord> sRList = sqlSession.getMapper(storage_recordMapper.class).getStorageRecordWithSupplierName(querySr);

            // 验证并打印结果
            if (sRList != null && !sRList.isEmpty()) {
                for (StorageRecord list : sRList) {
                    logger.debug("商品信息 - 名称:" + list.getGoodsName()
                            + ", 供货商Id:" + list.getSupplierId()
                            + ", 供货商名称:" + list.getSupplierName());
                }
                logger.debug("查询到 " + sRList.size() + " 条符合条件的商品记录");
            } else {
                logger.debug("未查询到符合条件的商品记录");
            }
        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }
    }
}
