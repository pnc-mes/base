package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.ShiftInfo;
import pnc.mesadmin.entity.UrgencyInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：紧急度代码DAO
 * 创建人：刘福志
 * 创建时间：2017-08-17
 * 修改人：
 * 修改时间：
 */
public interface UrgencyDAO extends BaseMapper<UrgencyInfo> {
    //查询紧急度代码列表信息
    List<UrgencyInfo> SelectAllUrgencyInfo();

    //查询紧急度代码信息
    UrgencyInfo SelectByRuid(int urcyRd);

    //查询紧急代码
    UrgencyInfo SelecturcyCode(String urcyCode);

    //查询紧急度名称
    UrgencyInfo SelecturcyDes(String urcyDes);

    //根据gd查询紧急代码
    UrgencyInfo SelectByguid(String guid);

    //保存紧急度代码信息
    int InsertUrgencyInfo(UrgencyInfo urgencyInfo);

    //删除紧急度代码信息
    int DeleteUrgencyInfo(int ruid);

    //更新紧急度代码信息
    int UpdateUrgencyInfo(UrgencyInfo urgencyInfo);

    //根据名称查询一条信息
    UrgencyInfo SelectAllUrgencyInfoByUrcyCode(String UrcyCode);
}
