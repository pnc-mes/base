package pnc.mesadmin.dao;

import pnc.mesadmin.entity.DevToolRelationInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 修改人：
 * 修改时间：
 */
public interface ToolDevDAO {
    List<DevToolRelationInfo> SelectAllDevToolRelationInfoByDevGD(String devGd);

    DevToolRelationInfo SelectAllDevToolRelationInfoByModel(String toolGd);

    int DelDevToolRelationInfoByToolGD(String toolGd);

    int InsrtDevToolRelationInfo(DevToolRelationInfo model);
}
