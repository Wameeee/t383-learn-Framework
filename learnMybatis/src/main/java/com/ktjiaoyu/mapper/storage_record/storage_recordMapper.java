package com.ktjiaoyu.mapper.storage_record;

import com.ktjiaoyu.entity.StorageRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface storage_recordMapper {

    /**
     * 根据商品信息查询商品详情
     *
     * @param goodsName
     * @param supplierId
     * @param payStatus
     * @return 商品详情
     */
    List<StorageRecord> getStorageRecordList(@Param("goodsName") String goodsName,
                                             @Param("supplierId") int supplierId,
                                             @Param("payStatus") int payStatus);

    /**
     * 根据供货商姓名来查询商品详情
     *
     * @param storageRecord
     * @return 商品详情
     */
    List<StorageRecord> getStorageRecordWithSupplierName(StorageRecord storageRecord);

    /**
     * 根据供货商id查询入库表信息，供货商名称信息
     *
     * @param supplierId
     * @return 入库表信息，供货商名称信息
     */
    List<StorageRecord> getStorageRecordListAndSupplierList(@Param("supplierId") Integer supplierId);

    /**
     * 根据供货商id 支付状态 商品名称条件查询
     *
     * @param supplierId
     * @param payStatus
     * @param goodsName
     * @return
     */
    List<StorageRecord> storageRecordList(@Param("supplierId") Integer supplierId, @Param("payStatus") Integer payStatus, @Param("goodsName") String goodsName);

    /**
     * 根据供货商Id数组 查询入库记录数据
     *
     * @param supplierIds
     * @return
     */
    List<StorageRecord> selectSrBySupplierIdArray(Integer[] supplierIds);

    /**
     * 根据供货商Id 数组集合 查询入库记录数据
     *
     * @param supplierIdList
     * @return
     */
    List<StorageRecord> selectBySupplierIdArrayList(List<Integer> supplierIdList);

    /**
     * 根据供货商Id Map集合 查询入库记录数据
     *
     * @param supplierIdMap
     * @return
     */
    List<StorageRecord> getSrBySupplierIdMap(Map<String, Object> supplierIdMap);

    /**
     * 分页查询入库单列表
     *
     * @param goodsName  商品名称
     * @param supplierId 供应商ID
     * @param payStatus  支付状态
     * @param pageBegin  起始位置
     * @param pageSize   每页大小
     * @return 入库单列表
     */
    List<StorageRecord> selectPageList(@Param("goodsName") String goodsName,
                                       @Param("supplierId") Integer supplierId,
                                       @Param("payStatus") Integer payStatus,
                                       @Param("pageBegin") Integer pageBegin,
                                       @Param("pageSize") Integer pageSize);
}
