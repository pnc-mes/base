package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.PDTkInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：盘点任务单信息DAO
 * 创建人：刘福志
 * 创建时间：2017-6-10
 * 修改人：
 * 修改时间：
 */
public interface PDTkDAO {
    //查询盘点单列表信息
    List<PDTkInfo> SelectAllPDTkInfo();

    //查询盘点单信息
    PDTkInfo SelectByRuid(int PDRd);

    //查询盘点单信息关联仓库的标识信息
    PDTkInfo SelectBystoreGd(String storeGd);

    //查询盘点单信息关联明细
    PDTkInfo  SelectByguid(String guid);

    //查询盘点单仓库标识
    PDTkInfo SelectstoreGdguid(String guid);

    //新增盘点单信息
    int InsertPDTkInfo(PDTkInfo pdTkInfo);

    //删除盘点单信息
    int DeletePDTkInfo(int ruid);

    //更新盘点单信息
    int UpdatePDTkInfo(PDTkInfo pdTkInfo);

    //根据类型，代码，状态查询盘点信息 张亮亮
    List<PDTkInfo> SelectPDTkInfoByOOS(@Param("pDCode") String pDCode, @Param("exStatus") String exStatus);

    //根据单号查询盘点信息 张亮亮
    PDTkInfo SelectPDTkInfoBypDCode(String argpDCode);
}
