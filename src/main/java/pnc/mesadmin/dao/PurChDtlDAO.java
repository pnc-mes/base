package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.PurChDtlInfo;

import java.util.List;

/**
 * Created by PNC on 2017/8/23.
 */
public interface PurChDtlDAO {
    //新增采购单明细信息
    int  InsertPurChDtlInfo(PurChDtlInfo argPurChDtlInfo);

    //查询所有的采购单明细
    List<PurChDtlInfo> SelectPurChDtlInfo();

    List<PurChDtlInfo> SelectPurChDtlInfoBypurChGd(String argpurChGd);

    //根据单号查询明细信息
    List<PurChDtlInfo> SelectPurChDtlInfoByPurChCode(String argPurChCode);

    //根据guid查询明细信息
    PurChDtlInfo SelectPurChDtlInfoByGuid(String argguid);

    //根据ruid查询明细信息
    PurChDtlInfo SelectPurChDtlInfoByRuid(int argRuid);

    //更新采购单明细信息
    int UpdatePurChDtlInfo(PurChDtlInfo argPurChDtlInfo);

    //删除采购单明细
    int deletePurChDtlInfo(int argRuid);

    //根据采购单号和物料Guid查询唯一的任务单明细
    PurChDtlInfo SelectPurChDtlInfoByPurChCodeAndMaVerGd(@Param("argPurChCode") String argPurChCode, @Param("argMaVerGd") String argMaVerGd);

}
