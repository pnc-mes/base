package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.ShiftInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：班别管理Dao
 * 创建人：乔帅阳
 * 创建时间：2018-6-19
 * 修改人：
 * 修改时间：
 */
public interface ShiftDAO extends BaseMapper<ShiftInfo> {
    //查询班别管理名称列表
    List<ShiftInfo> SelectAllShiftInfo();
    //查询班别信息
    ShiftInfo SelectShiftByID(int ShiftRd);
    //保存供应商信息
    int InsertShiftInfo(ShiftInfo shiftInfo);
    //根据Guid查询
    ShiftInfo SelectByGuid(String Guid);
    //更新客户信息
    int UpdateShiftInfo(ShiftInfo shiftInfo);
    //删除客户信息
    int DeleteShiftInfo(int Ruid);

    //根据名称查询一条信息 zll
    ShiftInfo SelectAllShiftInfoByShiftName(String ShiftName);
}
