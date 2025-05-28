package com.ktjiaoyu.mapper.supplier;

import com.ktjiaoyu.entity.Supplier;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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

    /**
     * 条件查询供货商列表
     *
     * @param supName
     * @param supCode
     * @param supContact
     * @param createdTime
     * @return
     */
    List<Supplier> listSupplierByChoose(@Param("supName") String supName, @Param("supCode") String supCode, @Param("supContact") String supContact, @Param("createdTime") Date createdTime);

    /**
     * 分页查询供货商列表
     *
     * @param supName    供货商名称
     * @param supCode    供货商编码
     * @param supContact 联系人
     * @param pageBegin  起始位置
     * @param pageSize   每页大小
     * @return 供货商列表
     */
    List<Supplier> selectPageList(@Param("supName") String supName,
                                  @Param("supCode") String supCode,
                                  @Param("supContact") String supContact,
                                  @Param("pageBegin") Integer pageBegin,
                                  @Param("pageSize") Integer pageSize);
}
