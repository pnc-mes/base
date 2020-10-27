package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.WFVerInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工艺版本信息DAO
 * 创建人：刘福志
 * 创建时间：2017-06-08
 * 修改人：
 * 修改时间：
 */
public interface WFVerDAO extends BaseMapper<WFVerInfo> {

    //新增工艺版本信息  (By-pjf)
    int InsertWFVerInfo(WFVerInfo argWFVerInfo);

    //删除工艺版本信息 (By-pjf)
    int DeleteWFVerInfo(int argRuid);

    //根据WFGd删除  (By-pjf)
    int DeleteByWFGd(String argWFGd);

    //修改工艺版本信息  (By-pjf)
    int UpdateWFVerInfo(WFVerInfo argWFVerInfo);

    //根据工艺信息修改工艺版本信息  (By-pjf)
    int UpdateWFVerInfoByWFGd(WFVerInfo argWFVerInfo);

    //根据WFGd查询工艺版本信息  (By-pjf)
    List<WFVerInfo> SelectByWFGd(String argWFGd);

    //根据Guid查询工艺版本信息  (By-pjf)
    WFVerInfo SelectByGuid(String argGuid);

    //根据Ruid查询工艺版本信息  (By-pjf)
    WFVerInfo SelectByRuid(int argRuid);

    //根据Version+WFGd查询工艺版本信息  (By-pjf)
    WFVerInfo SelectByVersion(@Param("argWFGd") String argWFGd, @Param("argVersion") String argVersion);

}
