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
     * @param id
     * @return 供货商信息, 入库记录
     */
    List<Supplier> getSupplierListAndStorageRecordListBySupId(@Param("id") Integer id);

    /**
     * 添加供货商
     *
     * @param supplier
     * @return
     */
    int addSupplier(Supplier supplier);

    /**
     * 根据供货商id添加供货商
     *
     * @param supplier
     * @return result
     */
    int updateSupplierById(Supplier supplier);

    /**
     * 根据供货商id删除供货商信息
     *
     * @param id
     * @return result
     */
    int deleteSupplierById(Integer id);
}
