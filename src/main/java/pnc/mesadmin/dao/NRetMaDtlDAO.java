package pnc.mesadmin.dao;

import pnc.mesadmin.entity.NRetMaDtlInfo;

import java.util.List;

/**
 * Created by zhaochao on 2017/10/25.
 */
public interface NRetMaDtlDAO {
    /**
     * 通过guid查询关联的所有退料明细信息
     * @param guid
     * @return
     */
    List<NRetMaDtlInfo> SelectRetMaDtlInfoByRetGd(String guid);

    /*通过退料明细ID查询退料信息 WHL*/
    NRetMaDtlInfo SelectRetMaDtlInfoByRuid(int ruid);

    /*通过退料单号查询退料单明细信息 WHL*/
    List<NRetMaDtlInfo> SelectRetMaDtlInfoByRetCode(String retCode);

    /**
     * 新增退料单明细
     * @param retMaDtlInfoList
     * @return
     */
    int InsertRetMaDtlInfo(List<NRetMaDtlInfo> retMaDtlInfoList);


    void Insert(NRetMaDtlInfo nRetMaDtlInfo);
}
