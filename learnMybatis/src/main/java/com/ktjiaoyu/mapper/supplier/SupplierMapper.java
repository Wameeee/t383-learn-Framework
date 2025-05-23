package com.ktjiaoyu.mapper.supplier;

import com.ktjiaoyu.entity.Supplier;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupplierMapper {

    /**
     * 查询供应商总数
     *
     * @return 供应商总数
     */
    int count();

    /**
     * 查询供货商列表
     *
     * @return 供货商列表
     */
    List<Supplier> getSupplierList();

    /**
     * 根据供货商id查询供货商信息和入库记录
     *
     * @param SupId
     * @return 供货商信息, 入库记录
     */
    List<Supplier> getSupplierListAndStorageRecordListBySupId(@Param("id") Integer id);
}
