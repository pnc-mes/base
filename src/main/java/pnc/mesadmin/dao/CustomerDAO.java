package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.CustomerInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：客户信息DAO
 * 创建人：刘福志
 * 创建时间：2017-5-26
 * 修改人：
 * 修改时间：
 */
public interface CustomerDAO extends BaseMapper<CustomerInfo> {
    //查询客户列表信息
    List<CustomerInfo> SelectAllCustomerInfo();

    //查询客户信息
    CustomerInfo SelectByRuid(int cusRd);

    //通过guid查询客户信息
    CustomerInfo SelectByGuid(String guid);

    //保存客户信息
    int InsertCustomerInfo(CustomerInfo customerInfo);

    //删除客户信息
    int DeleteCustomerInfo(int ruid);

    //更新客户信息
    int UpdateCustomerInfo(CustomerInfo customerInfo);

    //查询客户名称
    CustomerInfo SelectBycustomerName(String customerName);

    //查询客户信息
    CustomerInfo SelectByOrderCode(String orderCode);
}
