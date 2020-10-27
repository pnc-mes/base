package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.UnitInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：计量单位DAO
 * 创建人：张亮亮
 * 创建时间：2017-05-27
 * 修改人：
 * 修改时间：
 */
public interface UnitDAO extends BaseMapper<UnitInfo> {
    //查询计量单位
    List<UnitInfo> SelectUnitInfo();
    //根据ruid查询计量单位
    UnitInfo SelectUnitInfoByruid(int argruid);
    //新增计量单位
    int InsertUnitInfo(UnitInfo argUnitInfo);
    //删除计量单位
    int DeleteUnitInfo(int argruid);
    //更新计量单位
    int UpdateUnitInfo(UnitInfo argUnitInfo);
    //根据Guid查询计量单位  (By-pjf)
    UnitInfo SelectByGuid(String argGuid);
    UnitInfo SelectByUnitName(String argUnitName);

}
