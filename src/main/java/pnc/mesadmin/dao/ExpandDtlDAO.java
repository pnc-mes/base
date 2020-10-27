package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.ExpandDtlInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/3 15:34
 * @Description:
 */
public interface ExpandDtlDAO {
    //新增
    void InsertExpandDtlCInfo(ExpandDtlInfo expandDtlInfo);

    //查询根据主表标识
    List<ExpandDtlInfo> SelectExpandDtlCInfoByExpandGd(String expandGd);

    //删除
    int DeleteExpandDtlCInfo(int ruid);

    List<ExpandDtlInfo> selectEE(String ExpandType);

    //查询根据主表标识
    ExpandDtlInfo SelectExpandDtlCInfoByExpandGdAndDisplayName(@Param("expandGd") String expandGd, @Param("DisplayName") String DisplayName);
}
