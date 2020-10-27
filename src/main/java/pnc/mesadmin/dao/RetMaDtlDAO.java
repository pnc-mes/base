package pnc.mesadmin.dao;

import pnc.mesadmin.entity.RetMaDtlInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by test on 2017/9/21.
 */

public interface RetMaDtlDAO {

    /**
     * 通过guid查询关联的所有退料明细信息
     * @param guid
     * @return
     */
    List<RetMaDtlInfo> SelectRetMaDtlInfoByRetGd(String guid);

    /*通过退料明细ID查询退料信息 WHL*/
    RetMaDtlInfo SelectRetMaDtlInfoByRuid(int ruid);

    /*通过退料单号查询退料单明细信息 WHL*/
    List<RetMaDtlInfo> SelectRetMaDtlInfoByRetCode(String retCode);

    /**
     * 新增退料单明细
     * @param retMaDtlInfoList
     * @return
     */
    int InsertRetMaDtlInfo(List<RetMaDtlInfo> retMaDtlInfoList);

    /**
     * 删除退料明细
     * @param retMaDtlInfoList
     * @return
     */
    int DeleteRetMaDtlInfo(List<RetMaDtlInfo> retMaDtlInfoList);

    /**
     * 查询物料汇总的应退数量信息
     * @param retMaGdList
     * @return
     */
    List<Map<String,Object>> SelectAllMaVerInfo(List<String> retMaGdList);

    /**
     * 更新细表多条
     * @param retMaDtlInfos
     * @return
     */
    int UpdateRetMaDtlInfo(List<RetMaDtlInfo> retMaDtlInfos);

    /**
     * 更新一条信息
     */
    int UpdateOneRetMadtlInfo(RetMaDtlInfo retMaDtlInfo);
}
