package pnc.mesadmin.dao;


import pnc.mesadmin.entity.ReMaInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：物料包装规格信息DAO
 * 创建人：潘俊峰
 * 创建时间：2017-06-03
 * 修改人：
 * 修改时间：
 */
public interface ReMaDAO {

    //新增物料替代料信息  (By-pjf)
    int InsertReMaInfo(ReMaInfo argReMaInfo);

    //删除物料替代料信息  (By-pjf)
    int DeleteReMaInfo(String argGuid);

    //根据物料版本Guid删除物料替代料信息  (By-pjf)
    int DeleteByMaVerGd(String argMaVerGd);

    //修改物料替代料信息 (By-pjf)
    int UpdateByRuid(ReMaInfo argReMaInfo);

    //根据物料版本标识查询物料替代料  (By-pjf)
    List<ReMaInfo> SelectByMaVerGd(String argMaVerGd);

    //根据ruid查询物料替代料 (By-pjf)
    ReMaInfo SelectByRuid(int argRuid);
}
