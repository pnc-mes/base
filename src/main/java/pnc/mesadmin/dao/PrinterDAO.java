package pnc.mesadmin.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.PrinterInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：打印机信息DAO
 * 创建人：刘福志
 * 创建时间：2017-5-27
 * 修改人：
 * 修改时间：
 */
public interface PrinterDAO extends BaseMapper<PrinterInfo> {
    //查询打印机列表信息
    List<PrinterInfo> SelectAllPrinterInfo();

    //查询打印机信息
    PrinterInfo SelectByRuid(int prRd);

    //保存打印机信息
    int InsertPrinterInfo(PrinterInfo printerInfo);

    //删除打印机信息
    int DeletePrinterInfo(int ruid);

    //更新打印机信息
    int UpdatePrinterInfo(PrinterInfo printerInfo);

    //根据Guid查询打印机信息---王怀龙
    PrinterInfo SelectByGuid(String guid);

    //查询打印机名称信息
    List<PrinterInfo> SelectprinterNames(String printerName);

    //擦汗寻打印机名称信息
    PrinterInfo   SelectprinterName(String printerName);

    //查询打印机站信息
    List<PrinterInfo>  SelectprinterSer(String printerSer);
}
