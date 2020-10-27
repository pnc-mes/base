package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.CheckPlanInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/10 09:31
 * @Description:
 */
public interface CheckPlanDAO extends BaseMapper<CheckPlanInfo> {
    //列表
    List<CheckPlanInfo> selectAllCheckPlanInfo();

    //根据名称查询一条信息
    CheckPlanInfo selectCheckPlanInfoByCheckPlanName(String checkPlanName);

    //新增
    void insertCheckPlanInfo(CheckPlanInfo checkPlanInfo);

    //根据guid查询一条信息
    CheckPlanInfo selectCheckPlanInfoByGuid(String guid);
    //删除
    int deleteCheckPlanInfo(int ruid);

    int DeleteCheckPlanInfoByGd(String Guid);

    //根据ruid查询一条信息
    CheckPlanInfo selectCheckPlanInfoByRuid(int ruid);

    //修改
    int updateCheckPlanInfo(CheckPlanInfo checkPlanInfo);

    //熊伟
    List<CheckPlanInfo> selectCheckPlanInfoByGuidd(String guid);
}
