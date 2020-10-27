package pnc.mesadmin.dao;

import pnc.mesadmin.entity.AppUpInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：应用更新DAO
 * 创建人：张亮亮
 * 创建时间：2017-06-21
 * 修改人：
 * 修改时间：
 */
public interface AppUpDAO {
    //根据应用标识查询应用更新 张亮亮
    AppUpInfo SelectAppUpInfoGByappID(String argappID);

    //获取所有发布App信息 (pjf)
    List<AppUpInfo> SeectAllAppUp();

    //新增发布App信息 (pjf)
    int InsertAppUp(AppUpInfo argAppUpInfo);

    //根据Guid删除发布App信息 (pjf)
    int DeleteByGuid(String argGuid);

    //修改发布App信息 (pjf)
    int UpdateAppUp(AppUpInfo argAppUpInfo);
}
