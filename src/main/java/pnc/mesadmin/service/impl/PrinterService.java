package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.PrinterDAO;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllPrInfo.GetAllPrInfoRes;
import pnc.mesadmin.dto.GetAllPrInfo.GetAllPrInfoResB;
import pnc.mesadmin.dto.GetAllPrInfo.GetAllPrInfoResD;
import pnc.mesadmin.dto.GetPrInfo.GetPrInfoRes;
import pnc.mesadmin.dto.GetPrInfo.GetPrInfoResB;
import pnc.mesadmin.dto.GetPrInfo.GetPrInfoResD;
import pnc.mesadmin.dto.SavePrInfo.*;
import pnc.mesadmin.entity.PrinterInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.PrinterIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;
import pnc.mesadmin.utils.MyPage;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：打印机信息Service实现类
 * 创建人：刘福志
 * 创建时间：2017-5-27
 * 修改人：
 * 修改时间：
 */
@Transactional
@Service
public class PrinterService implements PrinterIService {

    @Resource
    private PrinterDAO printerDAO;

    @Resource
    private BaseIService baseIService;

    //查询打印机列表信息
    public GetAllPrInfoRes QALLselectAllPrinterInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException {
        GetAllPrInfoRes objEGetAllPrInfoRes = new GetAllPrInfoRes();

        GetAllPrInfoResB body = new GetAllPrInfoResB();

        List<GetAllPrInfoResD> dataList =  new ArrayList<GetAllPrInfoResD>();

        // 获取打印机信息列表信息
        List<PrinterInfo> printerInfoList = baseIService.QALLBaseInfo(PrinterDAO.class, "SelectAllPrinterInfo",
                argInitDataFields, argPageInfo);

        if(printerInfoList.size()>0 || printerInfoList!=null) {
            GetAllPrInfoResD data = null;

            // 循环赋值查询
            for (int i = 0; i < printerInfoList.size(); i++) {
                data = new GetAllPrInfoResD();
                data.setPrRd(printerInfoList.get(i).getRuid());
                data.setPrName(printerInfoList.get(i).getPrinterName());
                data.setPrSer(printerInfoList.get(i).getPrinterSer());
                dataList.add(data);
            }
        }

        body.setData(dataList);
        objEGetAllPrInfoRes.setBody(body);

        return objEGetAllPrInfoRes;
    }

    /**
     * 查询打印机列表信息(新)
     * @param req
     * @return
     */
    @Override
    public PageResult<GetAllPrInfoResD> QALLPrinterNew(BaseRequest req) {
        List<GetAllPrInfoResD> resDList =  new ArrayList<GetAllPrInfoResD>();
        GetAllPrInfoResD resD = null;

        IPage<PrinterInfo> iPage = printerDAO.selectPage(new MyPage(req), CommonUtils.getQueryWrapper(req.getFields()));

        // 循环赋值查询
        for (PrinterInfo obj : iPage.getRecords()) {
            resD = new GetAllPrInfoResD();
            resD.setPrRd(obj.getRuid());
            resD.setPrName(obj.getPrinterName());
            resD.setPrSer(obj.getPrinterSer());
            resDList.add(resD);
        }

        return new PageResult<>(iPage, resDList);
    }

    //查询打印机信息
    public GetPrInfoRes GetselectByRuid(int prRd) throws SystemException {
        GetPrInfoRes objEGetPrInfoRes = new  GetPrInfoRes();

        GetPrInfoResB body = new GetPrInfoResB();

        GetPrInfoResD data = new GetPrInfoResD();

        // 获取打印机信息
        PrinterInfo printerInfo = printerDAO.SelectByRuid(prRd);

        if(printerInfo!=null ) {
            // 赋值查询打印机信息
            data.setPrRd(printerInfo.getRuid());
            data.setPrName(printerInfo.getPrinterName());
            data.setPrSer(printerInfo.getPrinterSer());
            data.setCreator(printerInfo.getCreator());
            data.setCreateTime(DateUtil.format(printerInfo.getCreateTime()));
            data.setLastModifyMan(printerInfo.getLastModifyMan());
            data.setLastModifyTime(DateUtil.format(printerInfo.getLastModifyTime()));
            data.setRemark(printerInfo.getRemark());
        }

        body.setData(data);
        objEGetPrInfoRes.setBody(body);

        return objEGetPrInfoRes;
    }

    //保存打印机信息
    public SavePrInfoRes AddinsertPrinterInfo(SavePrInfoReqBD00 busData00,PrinterInfo printerInfo) throws SystemException {
        SavePrInfoRes savePrInfoRes = new SavePrInfoRes();

        SavePrInfoResB body = new SavePrInfoResB();

        SavePrInfoResD data = new SavePrInfoResD();

        //查询打印机名称
        List<PrinterInfo> objEPrinterInfos=printerDAO.SelectAllPrinterInfo();

        // 赋值新增打印机信息
        printerInfo.setGuid(CommonUtils.getRandomNumber());

        //逻辑校验保存的打印机名称不能相同
        for (PrinterInfo obj:objEPrinterInfos){
            if (obj.getPrinterName().equals(busData00.getPrName()) && obj.getPrinterSer().equals(busData00.getPrSer())){
                throw new SystemException("MG0006F","打印机名称和打印工作站已存在");
            }
        }

        printerInfo.setPrinterName(busData00.getPrName());
        printerInfo.setPrinterSer(busData00.getPrSer());
        printerInfo.setCreator(CommonUtils.readUser().getRealName());
        printerInfo.setCreateTime(new Date());
        printerInfo.setRemark(busData00.getRemark());

        // 保存
        printerDAO.InsertPrinterInfo(printerInfo);

        body.setData(data);
        savePrInfoRes.setBody(body);

        return savePrInfoRes;
    }

    //更新打印机信息
    public SavePrInfoRes ModupdatePrinterInfo(SavePrInfoReqBD02 busData02,PrinterInfo printerInfo) throws SystemException {
        SavePrInfoRes saveCusInfoRes = new SavePrInfoRes();

        SavePrInfoResB body = new SavePrInfoResB();

        SavePrInfoResD data = new SavePrInfoResD();

       //查询打印机名称
        PrinterInfo objEPrinterInfos=printerDAO.SelectByRuid(busData02.getPrRd());

        //查询打印机名称
        List<PrinterInfo> objEPrinterInfo=printerDAO.SelectprinterNames(busData02.getPrName());

        List<PrinterInfo> objEPrinterInf = printerDAO.SelectprinterSer(busData02.getPrSer());

        if(objEPrinterInfo!=null && objEPrinterInfo.size()>0 && objEPrinterInf!=null && objEPrinterInf.size()>0) {

            for (PrinterInfo obj : objEPrinterInfo) {
                if(obj.getPrinterName().equals(objEPrinterInfos.getPrinterName())) {
                    for (PrinterInfo obj1 : objEPrinterInf) {
                        if (!obj.getPrinterName().equals(objEPrinterInfos.getPrinterName()) || !obj1.getPrinterSer().equals(objEPrinterInfos.getPrinterSer())) {
                            throw new SystemException("MG0006F", "打印机名称和打印工作站已存在");
                        }
                    }
                }
            }
        }

        // 赋值更新打印机信息
        printerInfo.setRuid(busData02.getPrRd());
        printerInfo.setPrinterName(busData02.getPrName());
        printerInfo.setPrinterSer(busData02.getPrSer());
        printerInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        printerInfo.setLastModifyTime(new Date());
        printerInfo.setRemark(busData02.getRemark());

        // 更新
        int count = printerDAO.UpdatePrinterInfo(printerInfo);

        if(count <=0) throw new SystemException("MG_MES3001812030002F","更新打印机信息失败！");

        body.setData(data);
        saveCusInfoRes.setBody(body);

        return saveCusInfoRes;
    }

    //复制打印机信息
    public SavePrInfoRes copyPrinterInfo(SavePrInfoReqBD03 busData03,PrinterInfo printerInfo) throws SystemException {
        SavePrInfoRes savePrInfoRes = new SavePrInfoRes();

        SavePrInfoResB body = new SavePrInfoResB();

        SavePrInfoResD data = new SavePrInfoResD();
        printerInfo= printerDAO.SelectByRuid(busData03.getPrRd());

        if (printerInfo==null){
            throw new SystemException("MG_MES3001811010001F","查询打印机信息为空！");
        }

        // 赋值复制一条打印机信息
        printerInfo.setGuid(CommonUtils.getRandomNumber());
        printerInfo.setPrinterName(printerInfo.getPrinterName());
        printerInfo.setPrinterSer(printerInfo.getPrinterSer());
        printerInfo.setCreator(printerInfo.getCreator());
        printerInfo.setCreateTime(printerInfo.getCreateTime());
        printerInfo.setLastModifyMan(printerInfo.getLastModifyMan());
        printerInfo.setLastModifyTime(printerInfo.getLastModifyTime());
        printerInfo.setRemark(printerInfo.getRemark());

        // 保存
        int count = printerDAO.InsertPrinterInfo(printerInfo);
        if(count <=0) throw new SystemException("MG_MES3001812040003F","复制打印机信息失败！");

        //查询打印机信息
        PrinterInfo objEprinterInfo =printerDAO.SelectByGuid(printerInfo.getGuid());

        objEprinterInfo.setPrinterName(objEprinterInfo.getPrinterName()+objEprinterInfo.getRuid());

        if (printerDAO.UpdatePrinterInfo(objEprinterInfo)<=0){
            throw new SystemException("MG_MES3001812030002F","复制打印机信息失败！");
        }


        body.setData(data);
        savePrInfoRes.setBody(body);

        return savePrInfoRes;
    }

    //删除打印机信息
    public SavePrInfoRes RmdeletePrinterInfo(int ruid) throws SystemException {
        SavePrInfoRes savePrInfoRes = new SavePrInfoRes();

        SavePrInfoResB body = new SavePrInfoResB();

        SavePrInfoResD data = new SavePrInfoResD();

        // 保存
        int count = printerDAO.DeletePrinterInfo(ruid);

        if(count <=0) throw new SystemException("MG_MES3001812020001F","删除打印机信息失败！");

        body.setData(data);
        savePrInfoRes.setBody(body);

        return savePrInfoRes;
    }
}
