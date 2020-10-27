package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.UnitInfo;
import pnc.mesadmin.entity.WoTypeInfo;

import java.util.List;

/**
 * Created by test on 2017/8/22.
 */
public interface WoTypeDAO extends BaseMapper<WoTypeInfo> {
    List<WoTypeInfo> SelectAllWoType();

    WoTypeInfo SelectWoTypeByRuid(int ruid);

    WoTypeInfo SelectWoTypeByGuid(String guid);

    int DeleteWoType(int ruid);

    int SelectWoTypeByName(String wtName);

    int InsertWoType(WoTypeInfo woTypeInfo);

    int SelectWoTypeInfoByName(@Param("wtName") String wtName, @Param("ruid") int ruid);

    int UpdateWoType(WoTypeInfo woTypeInfo);
}
