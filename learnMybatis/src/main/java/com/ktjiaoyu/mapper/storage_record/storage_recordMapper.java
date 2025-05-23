package com.ktjiaoyu.mapper.storage_record;

import com.ktjiaoyu.entity.StorageRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface storage_recordMapper  {

    /**
     * 根据商品信息查询商品详情
     * @param goodsName
     * @param supplierId
     * @param payStatus
     * @return 商品详情
     */
    List<StorageRecord> getStorageRecordList(@Param("goodsName") String goodsName,
                                             @Param("supplierId") int supplierId,
                                             @Param("payStatus") int payStatus);

    List<StorageRecord> getStorageRecordWithSupplierName(StorageRecord storageRecord);
}
