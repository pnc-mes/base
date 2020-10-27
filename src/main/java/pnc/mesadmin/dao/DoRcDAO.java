package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.DoRcInfo;

import java.util.List;

/**
 * Created by PNC on 2017/8/15.
 */
public interface DoRcDAO {

    //新增批次查询信息  (pjf)
    int InsertDoRC(DoRcInfo argDoRcInfo);

    //根据批次查询信息
    List<DoRcInfo> SelectDoRcInfoBybatch(String argbatch);

    //根据批次信息查询数据采集信息返回的ItemName汇总的其他值 张亮亮
    DoRcInfo SelectDoRCInfoItemNameBybatchspecName(@Param("batch") String batch, @Param("specName") String specName);

    //ReaCode
    List<DoRcInfo>  SelectDoRCInfoItemNameBybatchReaCode(@Param("batch") String batch, @Param("specName") String specName);
}
