package pnc.mesadmin.dao;

import pnc.mesadmin.entity.NRetMaInfo;

import java.util.List;

/**
 * Created by zhaochao on 2017/10/25.
 */
public interface NRetMaInfoDAO {
    /**
     * 通过ruid查询查询退料信息
     * @param retRd
     * @return
     */
    NRetMaInfo SelectRetMaInfoByRuid(int retRd);

    NRetMaInfo SelectRetMaInfoByGuid(String guid);

    /*通过退料单号查询退料单信息 WHL*/
    NRetMaInfo SelectRetMaInfoByRetCode(String retCode);

    /**
     * 查询所有
     */
    List<NRetMaInfo> SelectAllRetMaInfo();

    /**
     * 新增退料单
     * @param retMaInfo
     * @return
     */
    int InsertRetMaInfo(NRetMaInfo retMaInfo);

}
