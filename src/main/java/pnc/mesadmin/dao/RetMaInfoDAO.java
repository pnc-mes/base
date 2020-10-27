package pnc.mesadmin.dao;

import pnc.mesadmin.entity.RetMaInfo;

import java.util.List;

/**
 * Created by test on 2017/9/21.
 */

public interface RetMaInfoDAO {
    /**
     * 通过ruid查询查询退料信息
     * @param retRd
     * @return
     */
    RetMaInfo SelectRetMaInfoByRuid(int retRd);


    /*通过退料单号查询退料单信息 WHL*/
    RetMaInfo SelectRetMaInfoByRetCode(String retCode);

    /**
     * 查询所有
     */
    List<RetMaInfo> SelectAllRetMaInfo();

    /**
     * 新增退料单
     * @param retMaInfo
     * @return
     */
    int InsertRetMaInfo(RetMaInfo retMaInfo);

    /**
     * 删除退料信息
     * @param retMaInfo
     * @return
     */
    int DeleteRetMaInfo(RetMaInfo retMaInfo);

    /*根据Ruid更新退料任务信息*/
    int UpdateRetMaInfoByRuid(RetMaInfo retMaInfo);

    RetMaInfo SelectRetMaInfoByGuid(String guid);
}
