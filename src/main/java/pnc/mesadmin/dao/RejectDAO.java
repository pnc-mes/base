package pnc.mesadmin.dao;

import pnc.mesadmin.dto.GetAllRejectInfo.GetAllRejectInfoResD;
import pnc.mesadmin.entity.RejectInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：不良日志DAO
 * 创建人：潘俊峰
 * 创建时间：2017-08-31
 * 修改人：
 * 修改时间：
 */
public interface RejectDAO {

    //新增 (By-pjf)
    int InsertReject(RejectInfo argRejectInfo);
    //  获取不良标记列表信息
    List<GetAllRejectInfoResD> SelectAllRejectInfo(String batch);

    //新增标记不良
    int addReject(RejectInfo rejectInfo);
}
