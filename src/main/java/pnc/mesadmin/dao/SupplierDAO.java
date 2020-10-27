package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.SupplierInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：供应商信息DAO
 * 创建人：刘福志
 * 创建时间：2017-5-26
 * 修改人：
 * 修改时间：
 */
public interface SupplierDAO extends BaseMapper<SupplierInfo> {
    //查询供应商列表信息
    List<SupplierInfo> SelectAllSupplierInfo();

    //查询关联资源表
    List<SupplierInfo> SelectsupplierGd(String supplierGd);

    //查询供应商信息
    SupplierInfo SelectBySuppId(int supRd);

    //保存供应商信息
    int InsertSupplierInfo(SupplierInfo supplierInfo);

    //删除供应商信息
    int DeleteSupplierInfo(int ruid);

    //更新供应商信息
    int UpdateSupplierInfo(SupplierInfo supplierInfo);

    //查询供应商名字
    SupplierInfo SelectBysupplierName(String supplierName);

    //根据Guid查询供应商信息  (By-pjf)
    SupplierInfo SelectByGuid(String argGuid);

    //根据代码查询供应商信息 (By-pjf)
    SupplierInfo SelectBySupplierCode(String argSupplierCode);
}
