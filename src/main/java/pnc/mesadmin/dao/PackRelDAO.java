package pnc.mesadmin.dao;

import pnc.mesadmin.entity.PackRelInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：包装关系信息表DAO
 * 创建人：潘俊峰
 * 创建时间：2017-09-29
 * 修改人：
 * 修改时间：
 */
public interface PackRelDAO {

    //新增包装关系 (By-pjf)
    int InsertPackRel(PackRelInfo argPackRelInfo);

    PackRelInfo SelectByPackGd(String packGd);

    PackRelInfo SelectByBarCode(String barCode);

    List<PackRelInfo> SelectListByPackGd(String packGd);

    //查询所有组件号
    List<String> SelectBacthByPackGd(String packGd);

    PackRelInfo SelectByRuid(int ruid);

    int Delete(int ruid);

    List<PackRelInfo> selectAllPackRelInfoByPackGd(String packGd);
}
