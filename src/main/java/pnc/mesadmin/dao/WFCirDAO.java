package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.WFCirInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：非正常工序流转信息DAO
 * 创建人：潘俊峰
 * 创建时间：2017-06-10
 * 修改人：
 * 修改时间：
 */
public interface WFCirDAO {

    //新增非正常工序流转信息 (By-pjf)
    int InsertWFCir(WFCirInfo argWFCirInfo);

    //删除非正常工序流转信息 (By-pjf)
    int DeleteWFCir(int argRuid);

    //根据WFSpecGd删除
    int DeleteByWFSpecGd(String argWFSpecGd);

    //根据WFSpecGd查询非正常工序流转信息 (By-pjf)
    List<WFCirInfo> SelectByWFSpecGd(String argWFSpecGd);

    //根据WFSpecGd+线路类型查询非正常工序流转信息 (By-pjf)
    WFCirInfo SelectByWFGdRT(@Param("argWFSpecGd") String argWFSpecGd, @Param("argRouteType") String argRouteType);
}
