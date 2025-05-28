package com.ktjiaoyu.mapper.supplier;

import com.ktjiaoyu.entity.StorageRecord;
import com.ktjiaoyu.entity.Supplier;
import com.ktjiaoyu.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Test
    void testAddSupplier() throws Exception {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        try {
            Supplier supplier = new Supplier();
            supplier.setSupCode("1");
            supplier.setSupName("皮炎");
            supplier.setSupDesc("卖皮炎的");
            supplier.setSupContact("雷丰璇");
            supplier.setSupPhone("13800022222");
            supplier.setSupAddress("湖南省娄底市娄星区");
            supplier.setSupFax("0739-88888887");
            supplier.setBizPicPath(null);
            supplier.setCreatedUserId(1);
            supplier.setCreatedTime(new Date());
            int result = sqlSession.getMapper(SupplierMapper.class).addSupplier(supplier);
            sqlSession.commit();
            if (result > 0) {
                logger.debug("添加成功！");
            } else {
                logger.debug("添加失败");
            }
        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }
    }

    @Test
    void testUpdateSupplierById() throws Exception {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        try {
            Supplier supplier = new Supplier();
            supplier.setId(116);
            supplier.setSupCode("1");
            supplier.setSupName("皮炎");
            supplier.setSupDesc("卖皮炎的");
            supplier.setSupContact("雷丰璇");
            supplier.setSupPhone("13800022222");
            supplier.setSupAddress("湖南省郴州市苏仙区北大青鸟");
            supplier.setSupFax("0739-88888887");
            supplier.setBizPicPath(null);
            supplier.setUpdatedUserId(1);
            supplier.setUpdatedTime(new Date());
            int result = sqlSession.getMapper(SupplierMapper.class).updateSupplierById(supplier);
            sqlSession.commit();
            if (result > 0) {
                logger.debug("更新成功！");
            } else {
                logger.debug("更新失败");
            }
        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }
    }

    @Test
    void testDeleteSupplierById() throws Exception {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        try {
            int result = sqlSession.getMapper(SupplierMapper.class).deleteSupplierById(116);
            sqlSession.commit();
            if (result > 0) {
                logger.debug("删除成功！");
            } else {
                logger.debug("删除失败");
            }
        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }
    }

    @Test
    void testSupplierListByChoose() throws Exception {
        List<Supplier> supplierList = new ArrayList<Supplier>();
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        try {
            String supName = "";
            String supCode = "";
            String supContact = "";
            Date createdTime = new SimpleDateFormat("yyyy-MM-dd").parse("2024-11-11");
            supplierList = sqlSession.getMapper(SupplierMapper.class).listSupplierByChoose(supName, supCode, supContact, createdTime);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }
        logger.info("查询到的供应商数量:" + supplierList.size());
        for (Supplier supplier : supplierList) {
            logger.info("查询到的供应商信息:" + supplier);
        }
    }

    @Test
    void testSupplierUpdate() throws Exception {
        SqlSession sqlSession = null;
        Integer id = 100;
        int count = 0;
        Supplier supplier = new Supplier();
        supplier.setId(id);
        supplier.setSupDesc("我爱洗澡皮肤好好");
        supplier.setSupPhone("13800022222");
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            count = sqlSession.getMapper(SupplierMapper.class).updateSupplierById(supplier);
            sqlSession.commit();
        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }
        logger.info("修改Id =" + id + "的用户修改" + (count == 0 ? "失败" : "成功"));
    }

    @Test
    void testSelectPageList() {
        SqlSession sqlSession = null;
        List<Supplier> supplierList = new ArrayList<>();
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            String supName = "湖南";  // 名称中包含"北"的供应商
            String supCode = "";    // 不限制编码
            String supContact = ""; // 不限制联系人
            Integer pageIndex = 1;  // 第一页
            Integer pageSize = 5;   // 每页5条
            Integer pageBegin = (pageIndex - 1) * pageSize;

            supplierList = sqlSession.getMapper(SupplierMapper.class)
                    .selectPageList(supName, supCode, supContact, pageBegin, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }

        logger.info("分页查询到的供应商数量:" + supplierList.size());
        for (Supplier supplier : supplierList) {
            logger.info("供应商信息:" + supplier);
        }
    }
}