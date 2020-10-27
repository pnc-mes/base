package pnc.mesadmin.dao;

import pnc.mesadmin.entity.ToolCheckPlanInfo;

import java.util.List;
//点检计划接口
public interface ToolCheckPlanInfoDAO {

    int AddToolCheckPlanInfo(ToolCheckPlanInfo argToolCheckPlanInfo);

    List<ToolCheckPlanInfo> SelectToolCheckPlanInfoByGd(String Guid);

    List<ToolCheckPlanInfo> SelectToolCheckPlanInfoByToolGd(String ToolGd);

    int deleteByToolCheckPlanInfoToolGd(String ToolGd);

    List<ToolCheckPlanInfo>  SelectToolByCheckPlanGd(String CheckPlanGd);

    int deleteByCheckPlanGd(String CheckPlanGd);


}
