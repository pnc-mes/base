package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.DoDCInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：  批次数据采集信息表DAO
 * 创建人：张亮亮
 * 创建时间：2017-06-14
 * 修改人：
 * 修改时间：
 */
public interface DoDCDAO {

    //新增批次数据采集信息  (By-pjf)
    int InsertDoDcInfo(DoDCInfo argDoDCInfo);

    //根据批次信息批次数据采集信息 张亮亮
    List<DoDCInfo> SelectDoDCInfoBybatch(String strbatch);

     //根据批次信息批次数据采集信息查询数据采集信息去掉去重 张亮亮
    List<DoDCInfo> SelectDoDCInfoDISTINCTBybatch(String strbatch);

    //根据批次信息查询数据采集信息返回的ItemName汇总 张亮亮
    List<DoDCInfo> SelectDoDCInfoItemNameBybatch(String strbatch);

    //根据批次信息查询数据采集信息返回的ItemName汇总的其他值 张亮亮
    DoDCInfo SelectDoDCInfoItemNameBybatchspecName(@Param("batch") String batch, @Param("specName") String specName);

    //CValue
    List<DoDCInfo>  SelectDoDCInfoItemNameBybatchCValue(@Param("batch") String batch, @Param("specName") String specName, @Param("type") String type);
}
