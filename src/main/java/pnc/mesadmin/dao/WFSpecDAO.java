package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.WFSpecInfo;

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
public interface WFSpecDAO {

    //新增工艺工序版本信息  (By-pjf)
    int InsertWFSpec(WFSpecInfo argWFSpecInfo);

    //删除工艺工序版本信息  (By-pjf)
    int DeleteWFSpec(int argRuid);

    //根据WFVerGd删除  (By-pjf)
    int DeleteByWFVerGd(String argWFVerGd);

    //查询工艺工序版本信息
    WFSpecInfo Selectguid(String guid);

    //根据WFVerGd与SpecVerGd查询工艺工序版本信息  (By-pjf)
    WFSpecInfo SelectByWSVerGd(@Param("argWFVerGd") String argWFVerGd, @Param("argSpecVerGd") String argSpecVerGd);

    //根据WFVerGd查询工艺版本信息  (By-pjf)
    List<WFSpecInfo> SelectByWFVerGd(String argWFVerGd);

    //根据WFVerGd与Pos查询工艺工序版本信息  (By-pjf)
    WFSpecInfo SelectByWPVerGd(@Param("argWFVerGd") String argWFVerGd, @Param("argPos") int argPos);

}
