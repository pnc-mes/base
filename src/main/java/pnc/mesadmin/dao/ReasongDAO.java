package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.ReaCodeGdlInfo;
import pnc.mesadmin.entity.ReasongInfo;

import java.util.List;

public interface ReasongDAO extends BaseMapper<ReasongInfo> {

    List<ReasongInfo> selectAllReasongInfo();

    ReasongInfo SelectReasongInfoByGuid(String argGuid);

    //根据主键查询原因代码组信息 张亮亮
    ReasongInfo SelectReasongInfoByRuid(int argRuid);
    // 根据原因代码组gd查询明细表
    List<ReaCodeGdlInfo> SelectReaCodeGdlInfoByRCGGd(String argGuid);
    // 插入原因代码组
    int InsertReasongInfo(ReasongInfo reasongInfo);
    // 插入原因代码组明细
    int InsertReaCodeDlInfos(List<ReaCodeGdlInfo> reaCodeGdlInfoList);

    ReasongInfo SelectReasongGByName(String rcGpName);
    // 删除明细
    int DeleteRCGpDlInfoByGuid(String guid);
    // 删除代码组
    int DeleteRCGpInfoByGuid(String guid);
    // 修改原因代码组
    int UpDateRCGpInfoByRuid(ReasongInfo reasongInfo);

    int SelectCountByName(ReasongInfo reasong);
}