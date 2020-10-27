package pnc.mesadmin.dao;

import pnc.mesadmin.entity.ToolBZInfo;

public interface ToolBZDAO {



    //工具标识查询
    ToolBZInfo SelectToolBZInfo(String ToolGd);
    //标识查询
    ToolBZInfo SelectToolBZInfoGuid(String argguid);
    //工具Ruid查询
    ToolBZInfo SelectToolBZInfoRuid(String argRuid);

    int InsertToolBZInfo(ToolBZInfo toolBZInfo);

    int UpdateToolBZInfo(ToolBZInfo toolBZInfo);
    //工具标识删除
    int DeleteAllToolBZInfoRuid(String ToolGd);
}
