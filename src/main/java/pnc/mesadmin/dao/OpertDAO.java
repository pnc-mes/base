package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.OpertInfo;
import pnc.mesadmin.entity.WorkCenterInfo;

import java.util.List;

/**
 * Created by PNC on 2017/6/1.
 */
public interface OpertDAO extends BaseMapper<OpertInfo> {
    int insertOpertInfo(OpertInfo opertInfo);
    int CopyOpertInfo(OpertInfo opertInfo);
    int deleteByRuid(int opertRd);
    int editByRuid(OpertInfo opertInfo);
    List<OpertInfo> GetAllOpertInfo();
    OpertInfo GetOpertInfoByRuid(int argRuid);
    OpertInfo GetOpertInfoByGuid(String argGuid);

    //查询操作名称信息--LFZ
    OpertInfo SelectoptName(String optName);
}
