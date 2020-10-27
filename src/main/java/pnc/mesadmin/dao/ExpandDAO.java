package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.ExpandInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/3 15:26
 * @Description:
 */
public interface ExpandDAO extends BaseMapper<ExpandInfo> {
    //查询列表
    List<ExpandInfo> SelectAllExpandCInfo();

    //新增
    void InsertExpandCInfo(ExpandInfo expandInfo);

    //查询根据类型
    List <ExpandInfo> SelectAllExpandCInfoByExpandType(@Param("expandType") String expandType, @Param("IsSettleObj") String IsSettleObj);

    ExpandInfo selectExpandInfoByExpandName(String expandName);

    //删除
    int DeleteExpandCInfo(int ruid);

    //查询
    ExpandInfo SelectExpandCInfoByRuid(int ruid);

    //更新
    int UpdateExpandCInfo(ExpandInfo expandInfo);

    //有且只有一个
    ExpandInfo selectExpandInfoByExpandTypeAndIsSettleObj(String expandType);

    ExpandInfo selectExpandInfoByExpandByGuid(String guid);
    //与物料特性表关联查询扩展字段
    List <ExpandInfo> SelectAllMaRuid(Integer maRuid);

    List<ExpandInfo>  selectExpandInfoByExpandByGuids(String guid);
}
