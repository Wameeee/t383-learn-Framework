package com.ktjiaoyu.mapper.supplier;

import com.ktjiaoyu.entity.Supplier;

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
}
