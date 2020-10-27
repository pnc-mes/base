package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.WFInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工艺信息DAO
 * 创建人：潘俊峰
 * 创建时间：2017-06-05
 * 修改人：
 * 修改时间：
 */
public interface WFDAO extends BaseMapper<WFInfo> {

    //新增工艺信息  (By-pjf)
    int InsertWFInfo(WFInfo argWFInfo);

    //删除工艺信息  (By-pjf)
    int DeleteWFInfo(int argRuid);

    //修改工艺信息  (By-pjf)
    int UpdateWFInfo(WFInfo argWFInfo);

    //查询所有工艺信息  (By-pjf)
    List<WFInfo> SelectAllWFInfo();

    //查询工艺信息  (By-pjf)
    WFInfo SelectWFInfo(int argRuid);

    //根据Guid查询工艺信息  (By-pjf)
    WFInfo SelectByGuid(String argGuid);

    //根据WFName查询工艺信息  (By-pjf)
    WFInfo SelectByName(String argWFName);
}
