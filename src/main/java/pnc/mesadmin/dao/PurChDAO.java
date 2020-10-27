package pnc.mesadmin.dao;

import pnc.mesadmin.entity.PurChInfo;

import java.util.List;

/**
 * Created by PNC on 2017/8/23.
 */
public interface PurChDAO {

    //查询采购单信息
    List<PurChInfo> SelectPurChInfo();

    //新增采购单
    int InsertPurChInfo(PurChInfo argPurChInfo);

    //根据ID修改采购单信息
    int  UpdatePurChInfoByRuid(PurChInfo argPurChInfo);

    //不允许重复
    int SelectPurChInfoBypurChCode(String argpurChCode);

     //根据采购单单号获取采购单信息  王怀龙
    PurChInfo SelectPurChInfoByPurChCode(String purChCode);


    //根据Ruid查询采购单信息  (pjf)
    PurChInfo SelectByRuid(int argRuid);

    /**
     * 根据Guid查询采购单信息 (pjf)
     * @param argGuid
     * @return
     */
    PurChInfo SelectByGuid(String argGuid);

    //删除采购单信息
    int DeletePurChInfo(int argRuid);

}
