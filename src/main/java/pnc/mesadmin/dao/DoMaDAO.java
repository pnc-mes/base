package pnc.mesadmin.dao;

import pnc.mesadmin.entity.DoMaInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：  批次用料信息表DAO
 * 创建人：张亮亮
 * 创建时间：2017-06-14
 * 修改人：
 * 修改时间：
 */
public interface DoMaDAO {

    //新增  (By-pjf)
    int InsertDoMaInfo(DoMaInfo argDoMaInfo);

    //根据批次信息查询批次用料信息表 张亮亮
    List<DoMaInfo> SelectDoMaInfoBybatch(String strbatch);
}
