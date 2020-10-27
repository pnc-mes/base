package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.CyclePlanInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/6 14:18
 * @Description:
 */
public interface CyclePlanDAO extends BaseMapper<CyclePlanInfo> {
    //查询列表
    List<CyclePlanInfo> selectAllCyclePlanInfo();

    //根据名字查询
    CyclePlanInfo selectCyclePlanInfoByCyclePlanName(String cyclePlanName);

    //根据guid查询
    CyclePlanInfo selectCyclePlanInfoByGuid(String guid);

    //新增
    void insertCyclePlanInfo(CyclePlanInfo cyclePlanInfo);

    //删除
    int deleteCyclePlanInfo(int ruid);

    //查询根据id
    CyclePlanInfo selectCyclePlanInfoByRuid(int ruid);

    //修改
    int updateCyclePlanInfo(CyclePlanInfo cyclePlanInfo);
}
