package com.ktjiaoyu.mapper.storage_record;

import com.ktjiaoyu.entity.StorageRecord;
import com.ktjiaoyu.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    @Test
    public void testGetStorageRecordListAndSupplierList() throws Exception {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        try {
            List<StorageRecord> storageRecordList = sqlSession.getMapper(storage_recordMapper.class).getStorageRecordListAndSupplierList(100);
            for (StorageRecord storageRecord : storageRecordList) {
                logger.debug("Storage_record ===> 入库记录编码:" + storageRecord.getSrCode() +
                        ",商品名称:" + storageRecord.getGoodsName() +
                        ",供货商编码:" + storageRecord.getSupplier().getSupCode() +
                        ",供货商名称:" + storageRecord.getSupplier().getSupName() +
                        ",联系人:" + storageRecord.getSupplier().getSupContact() +
                        ",联系电话:" + storageRecord.getSupplier().getSupPhone() +
                        ",商品总额:" + storageRecord.getTotalAmount() +
                        ",支付状态:" + storageRecord.getPayStatus()
                );
            }
        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }
    }

    @Test
    void testStorageRecordList() throws Exception {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        try {
            Integer supplierId = 100;
            Integer payStatus = 1;
            String goodsName = "水";
            List<StorageRecord> storageRecordList = sqlSession.getMapper(storage_recordMapper.class).storageRecordList(supplierId, payStatus, goodsName);
            logger.debug("获取到的入库记录数量:" + storageRecordList.size());
            for (StorageRecord storageRecord : storageRecordList) {
                logger.debug("获取到的入库记录信息:" + storageRecord.toString());
            }
        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }
    }

    @Test
    void testSelectSrBySupplierIdArray() {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        List<StorageRecord> storageRecordList = null;
        Integer[] supplierIds = {100, 101};
        try {
            storageRecordList = sqlSession.getMapper(storage_recordMapper.class).selectSrBySupplierIdArray(supplierIds);
        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }
        logger.info("查询到的商品数量:" + storageRecordList.size());
        for (StorageRecord storageRecord : storageRecordList) {
            logger.debug("查询到的商品信息:" + storageRecord.toString());
        }
    }

    @Test
    void testSelectSrBySupplierIdArrayList() {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        List<StorageRecord> storageRecordList = null;
        List<Integer> sRList = new ArrayList<Integer>();
        sRList.add(100);
        sRList.add(101);
        try {
            storageRecordList = sqlSession.getMapper(storage_recordMapper.class).selectBySupplierIdArrayList(sRList);
        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }
        logger.info("查询到的商品数量:" + storageRecordList.size());
        for (StorageRecord storageRecord : storageRecordList) {
            logger.debug("查询到的商品信息:" + storageRecord);
        }
    }

    @Test
    void testSelectSrBySupplierIdMap() {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        List<StorageRecord> storageRecordList = null;
        List<Integer> sRList = new ArrayList<Integer>();
        sRList.add(100);
        sRList.add(101);
        Map<String, Object> sRMap = new HashMap<String, Object>();
        sRMap.put("list", sRList);
        try {
            storageRecordList = sqlSession.getMapper(storage_recordMapper.class).getSrBySupplierIdMap(sRMap);
        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }
        logger.info("查询到的商品数量:" + storageRecordList.size());
        for (StorageRecord storageRecord : storageRecordList) {
            logger.debug("查询到的商品信息:" + storageRecord);
        }
    }

    @Test
    void testSelectPageList() {
        SqlSession sqlSession = null;
        List<StorageRecord> storageRecordList = new ArrayList<>();
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            String goodsName = "水";
            Integer supplierId = null; // 不限供应商
            Integer payStatus = 1;  // 已支付
            Integer pageIndex = 1;  // 第一页
            Integer pageSize = 5;   // 每页5条
            Integer pageBegin = (pageIndex - 1) * pageSize;

            storageRecordList = sqlSession.getMapper(storage_recordMapper.class)
                    .selectPageList(goodsName, supplierId, payStatus, pageBegin, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }

        logger.info("分页查询到的入库单数量:" + storageRecordList.size());
        for (StorageRecord storageRecord : storageRecordList) {
            logger.info("入库单信息:" + storageRecord);
        }
    }
}
