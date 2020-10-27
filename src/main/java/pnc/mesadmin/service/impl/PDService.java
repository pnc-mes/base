package pnc.mesadmin.service.impl;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.ExportPDCInfo.ExportPDCInfoReqBD;
import pnc.mesadmin.dto.ExportPDCInfo.ExportPDCInfoRes;
import pnc.mesadmin.dto.ExportPDCInfo.ExportPDCInfoResB;
import pnc.mesadmin.dto.GetAllPDInfo.GetAllPDInfoRes;
import pnc.mesadmin.dto.GetAllPDInfo.GetAllPDInfoResB;
import pnc.mesadmin.dto.GetAllPDInfo.GetAllPDInfoResD;
import pnc.mesadmin.dto.GetPDCInfo.*;
import pnc.mesadmin.dto.GetPDInfo.GetPDInfoRes;
import pnc.mesadmin.dto.GetPDInfo.GetPDInfoResB;
import pnc.mesadmin.dto.GetPDInfo.GetPDInfoResD;
import pnc.mesadmin.dto.GetPDInfo.GetPDInfoResDStoreInfo;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SavePDInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.GConfigIService;
import pnc.mesadmin.service.PDIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;
import pnc.mesadmin.utils.MSExcelUtil;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：盘点单信息Service实现类
 * 创建人：刘福志
 * 创建时间：2017-6-10
 * 修改人：
 * 修改时间：
 */
@Transactional
@Service
public class PDService implements PDIService {
    @Resource
    private PDTkDAO pdTkDAO;//盘点任务单DAO

    @Resource
    private StoreDAO storeDAO;//仓库DAO

    @Resource
    private PDDtlDAO pdDtlDAO;//盘点明细DAO

    @Resource
    private InstockDtlDAO instockDtlDAO;//库存明细DAO

    @Resource
    private LocationDAO locationDAO;//库位信息DAO

    @Resource
    private InstockDAO instockDAO;

    @Resource
    private MaVerDAO maVerDAO;

    @Resource
    private  MaterialDAO materialDAO;

    @Resource
    private BDAO bdao;//批次信息DAO

    @Resource
    private BaseIService baseIService;

    @Resource
    private CodeRuleDAO codeRuleDAO;

    @Resource
    private GConfigIService gConfigIService;

    //查询盘点单列表信息
    public GetAllPDInfoRes QALLselectAllPDTkInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException {
        GetAllPDInfoRes objEGetAllPDInfoRes = new GetAllPDInfoRes();

        GetAllPDInfoResB body = new GetAllPDInfoResB();

        // 获取盘点单列表信息
        List<GetAllPDInfoResD> dataList = new ArrayList<GetAllPDInfoResD>();

        //查询盘点单所有信息
        List<PDTkInfo> pdTkInfoList =  baseIService.QALLBaseInfo(PDTkDAO.class, "SelectAllPDTkInfo",
                argInitDataFields, argPageInfo);

        if (pdTkInfoList != null && pdTkInfoList.size() > 0) {
            GetAllPDInfoResD data = null;

            // 循环赋值
            for (int i = 0; i < pdTkInfoList.size(); i++) {

                data = new GetAllPDInfoResD();

                //通过盘点单表中的仓库标识查询仓库信息
                List<StoreInfo> storeInfoList = storeDAO.SelectBystoreGd(pdTkInfoList.get(i).getStoreGd());

                if (storeInfoList != null || storeInfoList.size() > 0) {
                    for (StoreInfo storeInfo : storeInfoList) {
                        data.setStoreName(storeInfo.getStoreName());
                    }
                }
                //循环赋值查询
                data.setPDRd(pdTkInfoList.get(i).getRuid());
                data.setPDCode(pdTkInfoList.get(i).getpDCode());
                data.setExecor(pdTkInfoList.get(i).getExecor());
                if (pdTkInfoList.get(i).getExStatus().equals("00")) {
                    data.setExStatus("待执行");
                }
                if (pdTkInfoList.get(i).getExStatus().equals("01")) {
                    data.setExStatus("进行中");
                }
                if (pdTkInfoList.get(i).getExStatus().equals("02")) {
                    data.setExStatus("已完成");
                }
                if (pdTkInfoList.get(i).getExStatus().equals("03")) {
                    data.setExStatus("已取消");
                }
                data.setExecTime(DateUtil.format(pdTkInfoList.get(i).getExecTime()));
                data.setCancelor(pdTkInfoList.get(i).getCancelor());
                data.setCancelTime(DateUtil.format(pdTkInfoList.get(i).getCancelTime()));
                data.setFinishor(pdTkInfoList.get(i).getFinishor());
                data.setFinishTime(DateUtil.format(pdTkInfoList.get(i).getFinishTime()));

                dataList.add(data);
            }
        }

        body.setData(dataList);
        objEGetAllPDInfoRes.setBody(body);

        return objEGetAllPDInfoRes;
    }

    //查询盘点单信息
    public GetPDInfoRes GetselectByRuid(int PDRd) throws SystemException {
        GetPDInfoRes objEGetPDInfoRes = new GetPDInfoRes();

        GetPDInfoResB body = new GetPDInfoResB();

        GetPDInfoResD data = new GetPDInfoResD();

        // 获取盘点信息
        PDTkInfo pdTkInfo = pdTkDAO.SelectByRuid(PDRd);

        if (pdTkInfo != null) {
            List<GetPDInfoResDStoreInfo> objEStoreInfos = new ArrayList<GetPDInfoResDStoreInfo>();

            //通过盘点单中的仓库标识查询仓库信息
            List<StoreInfo> storeInfoList = storeDAO.SelectBystoreGd(pdTkInfo.getStoreGd());
            if (storeInfoList != null || storeInfoList.size() > 0) {
                //循环遍历获取仓库信息
                for (StoreInfo storeInfo : storeInfoList) {

                    GetPDInfoResDStoreInfo objEGetPDInfoResDStoreInfo = new GetPDInfoResDStoreInfo();

                    objEGetPDInfoResDStoreInfo.setStoreRd(storeInfo.getRuid());
                    objEGetPDInfoResDStoreInfo.setStoreName(storeInfo.getStoreName());
                    objEStoreInfos.add(objEGetPDInfoResDStoreInfo);
                }
            }
            data.setPDRd(pdTkInfo.getRuid());
            data.setPDCode(pdTkInfo.getpDCode());
            data.setStoreInfo(objEStoreInfos);
            data.setRemark(pdTkInfo.getRemark());
        }

        body.setData(data);
        objEGetPDInfoRes.setBody(body);

        return objEGetPDInfoRes;
    }

    //查询盘点差异信息
    public GetPDCInfoRes GetselectByPDRd(GetPDCInfoReqBD00 argGetPDCInfoReqBD00) throws SystemException {
        GetPDCInfoRes objEGetPDCInfoRes = new GetPDCInfoRes();

        GetPDCInfoResB body = new GetPDCInfoResB();

        GetPDCInfoResD objEGetPDCInfoResD = new GetPDCInfoResD();

        // 获取盘点信息
        PDTkInfo pdTkInfo = pdTkDAO.SelectByRuid(argGetPDCInfoReqBD00.getPDRd());

        //通过库存中的仓库标识查询仓库信息
        StoreInfo objEStoreInfos = storeDAO.SelectByStoreGd(pdTkInfo.getStoreGd());

        if (pdTkInfo != null && pdTkInfo.getExStatus().equals("02")) {

            // 获取盘点结果
            List<GetPDCInfoResDPDInfo> objEPDInfos = new ArrayList<GetPDCInfoResDPDInfo>();

            List<PDDtlInfo> pdDtlInfoList = pdDtlDAO.SelectByGuid(pdTkInfo.getGuid());

            if (pdDtlInfoList != null || pdDtlInfoList.size() > 0) {
                //获取库存结果
                List<GetPDCInfoResDInstockInfo> InstockInfos = new ArrayList<GetPDCInfoResDInstockInfo>();

                //循环遍历查询盘点明细数据
                for (PDDtlInfo pdDtlInfo : pdDtlInfoList) {

                    GetPDCInfoResDPDInfo objEGetPDCInfoResDPDInfo = new GetPDCInfoResDPDInfo();

                    //通过盘点明细批次字段查询库存明细数据
                    InstockDtlInfo objEInstockDtlInfos = instockDtlDAO.SelectBybatch(pdDtlInfo.getBatch());

                    MaterialInfo objeMaterialInfo=materialDAO.SelectByMaCode(pdDtlInfo.getMaterialCode()==null?"":pdDtlInfo.getMaterialCode());
                    //该批次盘点中有，库存中没有
                    if(objEInstockDtlInfos==null){
                        //赋值查询盘点明细数据
                        objEGetPDCInfoResDPDInfo.setMaCode(pdDtlInfo.getMaterialCode()==null?"":pdDtlInfo.getMaterialCode());
                        objEGetPDCInfoResDPDInfo.setMaName(pdDtlInfo.getMaterialName()==null?"":pdDtlInfo.getMaterialName());
                        objEGetPDCInfoResDPDInfo.setMaDes(objeMaterialInfo.getMaterialDes()==null?"":objeMaterialInfo.getMaterialDes());
                        if(objEStoreInfos!=null){
                            objEGetPDCInfoResDPDInfo.setStoreName(objEStoreInfos.getStoreName());
                        }else{
                            objEGetPDCInfoResDPDInfo.setStoreName("");
                        }

                        objEGetPDCInfoResDPDInfo.setLName(pdDtlInfo.getlName()==null?"":pdDtlInfo.getlName());
                        objEGetPDCInfoResDPDInfo.setNum(pdDtlInfo.getNum());
                        objEGetPDCInfoResDPDInfo.setUnitName(pdDtlInfo.getUnitName()==null?"":pdDtlInfo.getUnitName());
                        objEGetPDCInfoResDPDInfo.setChgReason(pdDtlInfo.getChgReason()==null?"":pdDtlInfo.getChgReason());
                        objEPDInfos.add(objEGetPDCInfoResDPDInfo);


                        GetPDCInfoResDInstockInfo objEGetPDCInfoResDInstockInfo = new GetPDCInfoResDInstockInfo();
                        objEGetPDCInfoResDInstockInfo.setMaCode("-");
                        objEGetPDCInfoResDInstockInfo.setMaName("-");
                        objEGetPDCInfoResDInstockInfo.setMaDes("-");
                        objEGetPDCInfoResDInstockInfo.setStoreName("-");
                        objEGetPDCInfoResDInstockInfo.setLName("-");
                        objEGetPDCInfoResDInstockInfo.setNum(0.0f);
                        InstockInfos.add(objEGetPDCInfoResDInstockInfo);
                    }

                    //该批次盘点和库存中都有
                    if(objEInstockDtlInfos!=null) {
                        //通过盘点明细的批次关联查询批次信息
                        BInfo objEBInfo = bdao.selectBatchInfoByBatch(objEInstockDtlInfos.getBatch());

                        //当数量或者仓库不同加载盘点结果表格
                        if (objEBInfo.getCanNum() != pdDtlInfo.getNum() || !pdDtlInfo.getStoreGd().equals(objEInstockDtlInfos.getStoreGd())) {
                            //赋值查询盘点明细数据
                            objEGetPDCInfoResDPDInfo.setMaCode(pdDtlInfo.getMaterialCode()==null?"":pdDtlInfo.getMaterialCode());
                            objEGetPDCInfoResDPDInfo.setMaName(pdDtlInfo.getMaterialName()==null?"":pdDtlInfo.getMaterialName());
                            objEGetPDCInfoResDPDInfo.setMaDes(objeMaterialInfo.getMaterialDes()==null?"":objeMaterialInfo.getMaterialDes());
                            if(objEStoreInfos!=null){
                                objEGetPDCInfoResDPDInfo.setStoreName(objEStoreInfos.getStoreName());
                            }else{
                                objEGetPDCInfoResDPDInfo.setStoreName("");
                            }

                            objEGetPDCInfoResDPDInfo.setLName(pdDtlInfo.getlName()==null?"":pdDtlInfo.getlName());
                            objEGetPDCInfoResDPDInfo.setNum(pdDtlInfo.getNum());
                            objEGetPDCInfoResDPDInfo.setUnitName(pdDtlInfo.getUnitName()==null?"":pdDtlInfo.getUnitName());
                            objEGetPDCInfoResDPDInfo.setChgReason(pdDtlInfo.getChgReason()==null?"":pdDtlInfo.getChgReason());
                            objEPDInfos.add(objEGetPDCInfoResDPDInfo);
                        }

                        //当数量或者仓库不同加载库存表格
                        if(objEBInfo.getCanNum() != pdDtlInfo.getNum() || !pdDtlInfo.getStoreGd().equals(objEInstockDtlInfos.getStoreGd())){
                            GetPDCInfoResDInstockInfo objEGetPDCInfoResDInstockInfo = new GetPDCInfoResDInstockInfo();

                            //通过库存中的库位标识查询库位信息
                            LocationInfo objELocationInfo = locationDAO.SelectlocationGd(objEInstockDtlInfos.getLocationGd());

                            //查询库存明细中得仓库信息
                            StoreInfo objEStoreInfo=storeDAO.SelectByguid(objEInstockDtlInfos.getStoreGd());

                            //赋值查询库存明细数据
                            if(objEStoreInfo!=null){
                                objEGetPDCInfoResDInstockInfo.setStoreName(objEStoreInfo.getStoreName());
                            }else{
                                objEGetPDCInfoResDInstockInfo.setStoreName("");
                            }

                            if (objELocationInfo != null) {
                                objEGetPDCInfoResDInstockInfo.setLName(objELocationInfo.getlName());
                            }else{
                                objEGetPDCInfoResDInstockInfo.setLName("");
                            }
                            objEGetPDCInfoResDInstockInfo.setNum(objEBInfo.getCanNum());
                            objEGetPDCInfoResDInstockInfo.setMaCode(objEInstockDtlInfos.getMaterialCode()==null?"":objEInstockDtlInfos.getMaterialCode());
                            objEGetPDCInfoResDInstockInfo.setMaName(objEInstockDtlInfos.getMaterialName()==null?"":objEInstockDtlInfos.getMaterialName());
                            objEGetPDCInfoResDInstockInfo.setMaDes(objeMaterialInfo.getMaterialDes()==null?"":objeMaterialInfo.getMaterialDes());
                            InstockInfos.add(objEGetPDCInfoResDInstockInfo);
                        }

                        //当仓库相同，库位不同得时候加载盘点结果表格
                        if(pdDtlInfo.getStoreGd().equals(objEInstockDtlInfos.getStoreGd()) && (!"".equals(pdDtlInfo.getLocationGd()) && pdDtlInfo.getLocationGd()!=null)  && (!"".equals(objEInstockDtlInfos.getLocationGd()) && objEInstockDtlInfos.getLocationGd()!=null)){
                           if(!pdDtlInfo.getLocationGd().equals(objEInstockDtlInfos.getLocationGd())) {
                               //赋值查询盘点明细数据
                               objEGetPDCInfoResDPDInfo.setMaCode(pdDtlInfo.getMaterialCode() == null ? "" : pdDtlInfo.getMaterialCode());
                               objEGetPDCInfoResDPDInfo.setMaName(pdDtlInfo.getMaterialName() == null ? "" : pdDtlInfo.getMaterialName());
                               objEGetPDCInfoResDPDInfo.setMaDes(objeMaterialInfo.getMaterialDes()==null?"":objeMaterialInfo.getMaterialDes());

                               if (objEStoreInfos != null) {
                                   objEGetPDCInfoResDPDInfo.setStoreName(objEStoreInfos.getStoreName());
                               } else {
                                   objEGetPDCInfoResDPDInfo.setStoreName("");
                               }

                               objEGetPDCInfoResDPDInfo.setLName(pdDtlInfo.getlName() == null ? "" : pdDtlInfo.getlName());
                               objEGetPDCInfoResDPDInfo.setNum(pdDtlInfo.getNum());
                               objEGetPDCInfoResDPDInfo.setUnitName(pdDtlInfo.getUnitName() == null ? "" : pdDtlInfo.getUnitName());
                               objEGetPDCInfoResDPDInfo.setChgReason(pdDtlInfo.getChgReason() == null ? "" : pdDtlInfo.getChgReason());
                               objEPDInfos.add(objEGetPDCInfoResDPDInfo);
                           }
                        }

                        //当仓库相同，库位不同得时候加载库存表格
                        if(pdDtlInfo.getStoreGd().equals(objEInstockDtlInfos.getStoreGd()) && (!"".equals(pdDtlInfo.getLocationGd()) && pdDtlInfo.getLocationGd()!=null) && (!"".equals(objEInstockDtlInfos.getLocationGd()) && objEInstockDtlInfos.getLocationGd()!=null)){
                            if(!pdDtlInfo.getLocationGd().equals(objEInstockDtlInfos.getLocationGd())) {
                                GetPDCInfoResDInstockInfo objEGetPDCInfoResDInstockInfo = new GetPDCInfoResDInstockInfo();

                                //通过库存中的库位标识查询库位信息
                                LocationInfo objELocationInfo = locationDAO.SelectlocationGd(objEInstockDtlInfos.getLocationGd());

                                //查询库存明细中得仓库信息
                                StoreInfo objEStoreInfo = storeDAO.SelectByguid(objEInstockDtlInfos.getStoreGd());

                                //赋值查询库存明细数据
                                if (objEStoreInfo != null) {
                                    objEGetPDCInfoResDInstockInfo.setStoreName(objEStoreInfo.getStoreName());
                                } else {
                                    objEGetPDCInfoResDInstockInfo.setStoreName("");
                                }

                                if (objELocationInfo != null) {
                                    objEGetPDCInfoResDInstockInfo.setLName(objELocationInfo.getlName());
                                } else {
                                    objEGetPDCInfoResDInstockInfo.setLName("");
                                }
                                objEGetPDCInfoResDInstockInfo.setNum(objEBInfo.getCanNum());
                                objEGetPDCInfoResDInstockInfo.setMaCode(objEInstockDtlInfos.getMaterialCode() == null ? "" : objEInstockDtlInfos.getMaterialCode());
                                objEGetPDCInfoResDInstockInfo.setMaName(objEInstockDtlInfos.getMaterialName() == null ? "" : objEInstockDtlInfos.getMaterialName());
                                objEGetPDCInfoResDInstockInfo.setMaDes(objeMaterialInfo.getMaterialDes()==null?"":objeMaterialInfo.getMaterialDes());

                                InstockInfos.add(objEGetPDCInfoResDInstockInfo);
                            }
                        }

                    }
                    if(InstockInfos.size()==0){
                        GetPDCInfoResDInstockInfo objEGetPDCInfoResDInstockInfo = new GetPDCInfoResDInstockInfo();
                        objEGetPDCInfoResDInstockInfo.setStoreName("-");
                        objEGetPDCInfoResDInstockInfo.setLName("-");
                        objEGetPDCInfoResDInstockInfo.setNum(0.0f);
                        InstockInfos.add(objEGetPDCInfoResDInstockInfo);
                    }
                }
                objEGetPDCInfoResD.setPDCode(pdTkInfo.getpDCode());
                if (objEStoreInfos != null) {
                    objEGetPDCInfoResD.setStoreName(objEStoreInfos.getStoreName());
                }else{
                    objEGetPDCInfoResD.setStoreName("");
                }

                objEGetPDCInfoResD.setExecor(pdTkInfo.getExecor());
                objEGetPDCInfoResD.setExecTime(DateUtil.formatPattern2(pdTkInfo.getExecTime()));
                objEGetPDCInfoResD.setCancelor(pdTkInfo.getCancelor());
                objEGetPDCInfoResD.setCancelTime(DateUtil.formatPattern2(pdTkInfo.getCancelTime()));
                objEGetPDCInfoResD.setFinishor(pdTkInfo.getFinishor());
                objEGetPDCInfoResD.setFinishTime(DateUtil.formatPattern2(pdTkInfo.getFinishTime()));
                objEGetPDCInfoResD.setCheckor(pdTkInfo.getCheckor());
                objEGetPDCInfoResD.setCheckTime(DateUtil.formatPattern2(pdTkInfo.getCheckTime()));

                objEGetPDCInfoResD.setInstockInfo(InstockInfos);
                objEGetPDCInfoResD.setPDInfo(objEPDInfos);
            }
        }

        body.setData(objEGetPDCInfoResD);
        objEGetPDCInfoRes.setBody(body);

        return objEGetPDCInfoRes;
    }

    //新增盘点单信息
    public SavePDInfoRes AddinsertPDTkInfo(SavePDInfoReqBD00 busData00, PDTkInfo pdTkInfo) throws SystemException {
        SavePDInfoRes savePDInfoRes = new SavePDInfoRes();

        SavePDInfoResB body = new SavePDInfoResB();

        SavePDInfoResD data = new SavePDInfoResD();

        //查询仓库信息
        StoreInfo objEstoreInfo = storeDAO.SelectByRuid(busData00.getStoreRd());

        if (objEstoreInfo == null) {
            throw new SystemException("MG_MES8001010010001F", "查询仓库信息为空！");
        }

       /* //获取当前时间
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        String time = format.format(date);*/

        // 赋值新增盘点数据
        pdTkInfo.setGuid(CommonUtils.getRandomNumber());
        pdTkInfo.setStoreGd(objEstoreInfo.getGuid());
        //查询代码生成
        CodeRuleInfo codeRuleInfo=codeRuleDAO.SelectCodeRuleInfocodeType("09");
        String SCode="";
        if(codeRuleInfo!=null && "00".equals(codeRuleInfo.getStatus())){
            SCode=gConfigIService.GetCreateSC(codeRuleInfo);
            pdTkInfo.setpDCode(SCode);
        }else{
            throw new SystemException("", "请到全局配置进行代码生成配置");
        }

        pdTkInfo.setExStatus("00");//执行状态00，01，02，03
        pdTkInfo.setCreator(CommonUtils.readUser().getRealName());
        pdTkInfo.setCreateTime(new Date());
        pdTkInfo.setRemark(busData00.getRemark());
        // 保存
        pdTkDAO.InsertPDTkInfo(pdTkInfo);

      /*  //更新盘点单号
        PDTkInfo pdTkInfo1 = pdTkDAO.SelectBystoreGd(pdTkInfo.getGuid());

        if (pdTkInfo1 == null) {
            throw new SystemException("MG_MES8001111010001F", "查询盘点单信息为空！");
        }

        String str = String.format("%010d", pdTkInfo1.getRuid());
        pdTkInfo1.setpDCode("PD" + time + str);

        //更新盘点单号
        if (pdTkDAO.UpdatePDTkInfo(pdTkInfo1) <= 0) {
            throw new SystemException("MG_MES8001113030002F", "新增盘点单号失败！");
        }*/

        PDTkInfo pdTkInfos=pdTkDAO.SelectByguid(pdTkInfo.getGuid());

        data.setPDCode(pdTkInfos.getpDCode());
        data.setPDRd(pdTkInfos.getRuid());
        body.setData(data);
        savePDInfoRes.setBody(body);

        return savePDInfoRes;


    }

    //更新盘点单信息
    public SavePDInfoRes ModupdatePDTkInfo(SavePDInfoReqBD02 busData02, PDTkInfo pdTkInfo) throws SystemException {
        SavePDInfoRes savePDInfoRes = new SavePDInfoRes();

        SavePDInfoResB body = new SavePDInfoResB();

        SavePDInfoResD data = new SavePDInfoResD();


        //查询盘点任务单信息
        PDTkInfo objEpdTkInfo = pdTkDAO.SelectByRuid(busData02.getPDRd());

        if (objEpdTkInfo == null) {
            throw new SystemException("MG_MES8001111010001F", "查询盘点任务单信息为空！");
        }

        //查询仓库信息
        StoreInfo objEstoreInfo = storeDAO.SelectByRuid(busData02.getStoreRd());

        if (objEstoreInfo == null) {
            throw new SystemException("MG_MES8001010010001F", "查询仓库信息为空！");
        }

        // 赋值更新盘点单信息
        if ("00".equals(objEpdTkInfo.getExStatus()) || "01".equals(objEpdTkInfo.getExStatus())) {
            pdTkInfo.setRuid(busData02.getPDRd());
            pdTkInfo.setStoreGd(objEstoreInfo.getGuid());
            pdTkInfo.setExecor(CommonUtils.readUser().getRealName());
            pdTkInfo.setExecTime(new Date());
            pdTkInfo.setCancelor(CommonUtils.readUser().getRealName());
            pdTkInfo.setCancelTime(new Date());
            pdTkInfo.setFinishor(CommonUtils.readUser().getRealName());
            pdTkInfo.setFinishTime(new Date());
            pdTkInfo.setRemark(busData02.getRemark());
        } else {
            throw new SystemException("MG_MES8001113030002F", "更新盘点单失败！");
        }

        // 更新
        int count = pdTkDAO.UpdatePDTkInfo(pdTkInfo);

        if (count <= 0) throw new SystemException("MG_MES8001113030002F", "更新盘点单失败！");

        body.setData(data);
        savePDInfoRes.setBody(body);

        return savePDInfoRes;
    }

    //盘点差异确认信息
    public SavePDInfoRes confirmPDTkInfo(SavePDInfoReqBD04 busData04) throws SystemException {
        SavePDInfoRes savePDInfoRes = new SavePDInfoRes();

        SavePDInfoResB body = new SavePDInfoResB();

        SavePDInfoResD data = new SavePDInfoResD();

        LocationInfo locationInfo = new LocationInfo();

        BInfo bInfo = new BInfo();

        InstockDtlInfo instockDtlInfo = new InstockDtlInfo();

        InstockInfo instockInfo = new InstockInfo();

        //查询盘点任务单信息
        PDTkInfo objEpdTkInfo = pdTkDAO.SelectByRuid(busData04.getPDRd());

        if (objEpdTkInfo == null) {
            throw new SystemException("MG_MES8001111010001F", "查询盘点单信息为空！");
        }

        //查询盘点明细所有信息
        List<PDDtlInfo> pdDtlInfoList = pdDtlDAO.SelectByGuid(objEpdTkInfo.getGuid());

        if (pdDtlInfoList == null) {
            throw new SystemException("MG_MES8001112010001F", "查询盘点明细信息为空！");
        }


        //查询盘点数据差异，根据批次号判断是否有差异
        Map<String, PDDtlInfo> map = new HashMap<String, PDDtlInfo>();

        //计算变化量
        Map<String, Float> map1 = new HashMap<String, Float>();

        for (PDDtlInfo obj1 : pdDtlInfoList) {

            //数量变化值
            float s_num = 0f;

            //根据盘点明细批次查询库存明细信息
            InstockDtlInfo objEInstockDtlInfos = instockDtlDAO.SelectBybatch(obj1.getBatch());
            //查询库存信息
            InstockInfo objeInstockInfo = instockDAO.SelectInstockInfoListByMaVerGdAndStoreGd(obj1.getMaVerGd(),obj1.getStoreGd());

            if (objEInstockDtlInfos != null) {
                //根据盘点明细批次查询批次信息
                BInfo objEBInfo = bdao.selectBatchInfoByBatch(obj1.getBatch());

                if (objEBInfo == null) {
                    throw new SystemException("", "查询批次信息为空！");
                }

                //更新批次相同的库存明细数据
                instockDtlInfo.setRuid(objEInstockDtlInfos.getRuid());
                instockDtlInfo.setStoreGd(obj1.getStoreGd());
                instockDtlInfo.setLocationGd(obj1.getLocationGd());
                if (!"04".equals(objEBInfo.getStatus())) {
                    throw new SystemException("MG0007F", "此"+objEBInfo.getBatch()+"批次还没有完工！");
                }
                bInfo.setRuid(objEBInfo.getRuid());

                s_num += obj1.getNum() - objEBInfo.getCanNum();

                bInfo.setCanNum(obj1.getNum());

                // 更新库存
                if (instockDtlDAO.UpdateInstockDtlInfo(instockDtlInfo) <= 0) {
                    throw new SystemException("", "确认失败(盘点缺少数据)！");
                }
                //更新批次表中的可用数量
                if (bdao.UpdateBatchInfoByRuid(bInfo) <= 0) {
                    throw new SystemException("", "确认失败(该批次不存在)！");
                }

            } else if(objeInstockInfo!=null){

                if("03".equals(obj1.getBarType())){
                    List<InstockDtlInfo> objeInstockDtlInfo=instockDtlDAO.SelectInstockDtlInfoByMaVerGdAndStoreGd(obj1.getMaVerGd(),obj1.getStoreGd());

                    for(InstockDtlInfo obje:objeInstockDtlInfo){
                        if( "".equals(obje.getBatch()) && "".equals(obje.getLocationGd())){
                            instockDtlInfo.setRuid(obje.getRuid());
                            instockDtlInfo.setNum(obj1.getNum());

                            instockDtlDAO.UpdateInstockDtlInfo(instockDtlInfo);
                        }else {
                            instockDtlInfo.setGuid(CommonUtils.getRandomNumber());
                            if (objeInstockInfo != null) {
                                instockDtlInfo.setInsGd(objeInstockInfo.getGuid());
                            }

                            instockDtlInfo.setMaVerGd(obj1.getMaVerGd());
                            instockDtlInfo.setMaterialCode(obj1.getMaterialCode());
                            instockDtlInfo.setMaterialName(obj1.getMaterialName());
                            instockDtlInfo.setNum(obj1.getNum());
                            instockDtlInfo.setBatch(obj1.getBatch());
                            instockDtlInfo.setStoreGd(obj1.getStoreGd());
                            instockDtlInfo.setLocationGd(obj1.getLocationGd());
                            instockDtlInfo.setProductDate(obj1.getProductDate());
                            instockDtlInfo.setExpireDate(obj1.getExpireDate());
                            instockDtlInfo.setUnitName(obj1.getUnitName());//单位名称
                            instockDtlInfo.setCreator(CommonUtils.readUser().getRealName());
                            instockDtlInfo.setCreateTime(new Date());
                            instockDtlInfo.setRemark(obj1.getRemark());
                            instockDtlDAO.InsertInstockDtlInfo(instockDtlInfo);
                        }
                    }

                }else {
                    //通过批次关联查询批次信息表
                    BInfo objEBInfo = bdao.selectBatchInfoByBatch(obj1.getBatch());

                    if (objEBInfo == null) {
                        throw new SystemException("", "查询批次信息为空！");
                    }

                    //判断当前新增批次是否已完工
                    if (!"04".equals(objEBInfo.getStatus())) {
                        throw new SystemException("MG0007F", "此"+objEBInfo.getBatch()+"批次还没有完工！");
                    }

                    //给新增数据赋值
                    instockDtlInfo.setGuid(CommonUtils.getRandomNumber());
                    if (objeInstockInfo != null) {
                        instockDtlInfo.setInsGd(objeInstockInfo.getGuid());
                    }

                    instockDtlInfo.setMaVerGd(obj1.getMaVerGd());
                    instockDtlInfo.setMaterialCode(obj1.getMaterialCode());
                    instockDtlInfo.setMaterialName(obj1.getMaterialName());
                    instockDtlInfo.setBatch(obj1.getBatch());
                    instockDtlInfo.setNum(0f);
                    instockDtlInfo.setStoreGd(obj1.getStoreGd());
                    instockDtlInfo.setLocationGd(obj1.getLocationGd());
                    instockDtlInfo.setProductDate(obj1.getProductDate());
                    instockDtlInfo.setExpireDate(obj1.getExpireDate());
                    instockDtlInfo.setUnitName(obj1.getUnitName());//单位名称
                    instockDtlInfo.setCreator(CommonUtils.readUser().getRealName());
                    instockDtlInfo.setCreateTime(new Date());
                    instockDtlInfo.setRemark(obj1.getRemark());

                    if (!"04".equals(objEBInfo.getStatus())) {
                        throw new SystemException("MG0007F", "此"+objEBInfo.getBatch()+"批次还没有完工！");
                    }

                    //更新批次数量
                    bInfo.setRuid(objEBInfo.getRuid());
                    s_num += obj1.getNum();

                    bInfo.setCanNum(obj1.getNum());

                    //新增库存明细数据
                    instockDtlDAO.InsertInstockDtlInfo(instockDtlInfo);

                    //更新批次表中的可用数量
                    if (bdao.UpdateBatchInfoByRuid(bInfo) <= 0) {
                        throw new SystemException("", "确认失败(该批次"+objEBInfo.getBatch()+"不存在)！");
                    }
                }
            }

            if (map.containsKey(obj1.getMaVerGd())) {
                PDDtlInfo pdDtlInfo = map.get(obj1.getMaVerGd());
                BigDecimal bigNum1=new BigDecimal(String.valueOf(obj1.getNum()));
                BigDecimal bigNum2=new BigDecimal(String.valueOf(pdDtlInfo.getNum()));
                BigDecimal bigNum3=bigNum1.add(bigNum2);
               // obj1.setNum(obj1.getNum() + pdDtlInfo.getNum());
                obj1.setNum(bigNum3.floatValue());
            }
            map.put(obj1.getMaVerGd(), obj1);

            if (map1.containsKey(obj1.getMaVerGd())) {
                float num = map1.get(obj1.getMaVerGd());
                map1.put(obj1.getMaVerGd(), num + s_num);
            } else {
                map1.put(obj1.getMaVerGd(), s_num);
            }
        }


        for (Map.Entry<String, PDDtlInfo> entry : map.entrySet()) { //更新

            PDDtlInfo obj1 = entry.getValue();

            //根据盘点明细批次查询库存明细信息
            InstockDtlInfo objEInstockDtlInfos = instockDtlDAO.SelectBybatch(obj1.getBatch());

            //查询库存信息
            InstockInfo objeInstockInfo = instockDAO.SelectInstockInfoListByMaVerGdAndStoreGd(obj1.getMaVerGd(),obj1.getStoreGd());

            //查询库位信息关联盘点明细表
            LocationInfo objELocationInfo = locationDAO.SelectlocationGd(obj1.getLocationGd());

            if (objEInstockDtlInfos != null) {
                //更新库存数量
                objeInstockInfo.setRuid(objeInstockInfo.getRuid());
                BigDecimal bigNum1=new BigDecimal(String.valueOf(objeInstockInfo.getNum()));
                BigDecimal bigNum2=new BigDecimal(String.valueOf( map1.get(entry.getKey())));
                BigDecimal bigNum3=bigNum1.add(bigNum2);
               // objeInstockInfo.setNum(objeInstockInfo.getNum() + map1.get(entry.getKey()));
                objeInstockInfo.setNum(bigNum3.floatValue());
                objeInstockInfo.setStoreGd(obj1.getStoreGd());
                if (instockDAO.UpdateInStockInfo(objeInstockInfo) <= 0) {
                    throw new SystemException("MG_MES8001013030002F", "确认失败(该库存信息不存在)！");
                }

                if (objELocationInfo != null) {
                    //更新库位数量
                    locationInfo.setRuid(objELocationInfo.getRuid());
                    locationInfo.setNum(objELocationInfo.getNum() + map1.get(entry.getKey()));

                    if (locationDAO.UpdateLocationInfo(locationInfo) <= 0) {
                        throw new SystemException("MG_MES8001013030002F", "确认失败(该库位不存在)！");
                    }
                }


            }else if(objeInstockInfo!=null){
                //更新库存数量
                objeInstockInfo.setRuid(objeInstockInfo.getRuid());
                BigDecimal bigNum1=new BigDecimal(objeInstockInfo.getNum());
                BigDecimal bigNum2=new BigDecimal(map1.get(entry.getKey()));
                BigDecimal bigNum3=bigNum1.add(bigNum2);
                //objeInstockInfo.setNum(objeInstockInfo.getNum() + map1.get(entry.getKey()));
                objeInstockInfo.setNum(bigNum3.floatValue());
                objeInstockInfo.setStoreGd(obj1.getStoreGd());
                if (instockDAO.UpdateInStockInfo(objeInstockInfo) <= 0) {
                    throw new SystemException("MG_MES8001013030002F", "确认失败(该库存信息不存在)！");
                }

                if (objELocationInfo != null) {
                    //更新库位数量
                    locationInfo.setRuid(objELocationInfo.getRuid());
                    locationInfo.setNum(objELocationInfo.getNum() + map1.get(entry.getKey()));

                    if (locationDAO.UpdateLocationInfo(locationInfo) <= 0) {
                        throw new SystemException("MG_MES8001013030002F", "确认失败(该库位不存在)！");
                    }
                }

            }

            //查询物料版本信息
            MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(obj1.getMaVerGd());

            String guids = CommonUtils.getRandomNumber();
            //盘点出来的物料在库存主表中没有的情况下
            if (objeInstockInfo == null) {
                //新增库存信息
                instockInfo.setGuid(guids);
                instockInfo.setVersion(objEMaVerInfo.getVersion());
                instockInfo.setMaterialCode(obj1.getMaterialCode());
                instockInfo.setMaterialName(obj1.getMaterialName());
                instockInfo.setMaVerGd(obj1.getMaVerGd());
                instockInfo.setMaterialType(objEMaVerInfo.getMaterialType());
                instockInfo.setStoreGd(obj1.getStoreGd());
                instockInfo.setNum(obj1.getNum());
                instockInfo.setCreator(CommonUtils.readUser().getRealName());
                instockInfo.setCreateTime(new Date());

                instockDAO.InsertInStock(instockInfo);

                if("01".equals(objEMaVerInfo.getIsBatch())){
                    instockDtlInfo.setGuid(CommonUtils.getRandomNumber());
                    instockDtlInfo.setInsGd(guids);
                    instockDtlInfo.setMaVerGd(obj1.getMaVerGd());
                    instockDtlInfo.setMaterialCode(obj1.getMaterialCode());
                    instockDtlInfo.setMaterialName(obj1.getMaterialName());
                    instockDtlInfo.setBatch(obj1.getBatch());
                    instockDtlInfo.setNum(obj1.getNum());
                    instockDtlInfo.setStoreGd(obj1.getStoreGd());
                    instockDtlInfo.setLocationGd(obj1.getLocationGd());
                    instockDtlInfo.setProductDate(obj1.getProductDate());
                    instockDtlInfo.setExpireDate(obj1.getExpireDate());
                    instockDtlInfo.setUnitName(obj1.getUnitName());
                    instockDtlInfo.setCreator(CommonUtils.readUser().getRealName());
                    instockDtlInfo.setCreateTime(new Date());

                    instockDtlDAO.InsertInstockDtlInfo(instockDtlInfo);
                }else {
                    //新增库存明细信息
                    instockDtlInfo.setGuid(CommonUtils.getRandomNumber());
                    instockDtlInfo.setInsGd(guids);
                    instockDtlInfo.setMaVerGd(obj1.getMaVerGd());
                    instockDtlInfo.setMaterialCode(obj1.getMaterialCode());
                    instockDtlInfo.setMaterialName(obj1.getMaterialName());
                    instockDtlInfo.setBatch(obj1.getBatch());
                    instockDtlInfo.setStoreGd(obj1.getStoreGd());
                    instockDtlInfo.setLocationGd(obj1.getLocationGd());
                    instockDtlInfo.setProductDate(obj1.getProductDate());
                    instockDtlInfo.setExpireDate(obj1.getExpireDate());
                    instockDtlInfo.setUnitName(obj1.getUnitName());
                    instockDtlInfo.setCreator(CommonUtils.readUser().getRealName());
                    instockDtlInfo.setCreateTime(new Date());

                    instockDtlDAO.InsertInstockDtlInfo(instockDtlInfo);
                }

                if (objELocationInfo != null) {
                    //更新库位数量
                    locationInfo.setRuid(objELocationInfo.getRuid());
                    locationInfo.setNum(objELocationInfo.getNum() + map1.get(entry.getKey()));

                    //更新库位的数量
                    if (locationDAO.UpdateLocationInfo(locationInfo) <= 0) {
                        throw new SystemException("MG_MES8001013030002F", "更新库位数量失败！");
                    }
                }

            }

        }

        objEpdTkInfo.setCheckor((String) SecurityUtils.getSubject().getPrincipal());
        objEpdTkInfo.setCheckTime(new Date());
        if(pdTkDAO.UpdatePDTkInfo(objEpdTkInfo)!=1){
            throw new SystemException("MG_MES8001013030002F", "更新盘点信息失败！");
        }


        body.setData(data);
        savePDInfoRes.setBody(body);

        return savePDInfoRes;
    }

    //删除盘点单信息
    public SavePDInfoRes RmdeletePDTkInfo(int ruid) throws SystemException {
        SavePDInfoRes savePDInfoRes = new SavePDInfoRes();

        SavePDInfoResB body = new SavePDInfoResB();

        SavePDInfoResD data = new SavePDInfoResD();

        //调用盘点sql，删除盘点信息
        int count = pdTkDAO.DeletePDTkInfo(ruid);

        if (count <= 0) throw new SystemException("MG_MES8001113020001F", "删除盘点信息失败！");

        body.setData(data);
        savePDInfoRes.setBody(body);

        return savePDInfoRes;
    }

    //导出盘点差异信息
    public ByteArrayOutputStream ExportPDCInfo(ExportPDCInfoReqBD busData) {

        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();


        try {

            GetPDCInfoResD objEGetPDCInfoResD = new GetPDCInfoResD();
            // 获取盘点信息
            PDTkInfo pdTkInfo = pdTkDAO.SelectByRuid(busData.getPDRd());

            //通过库存中的仓库标识查询仓库信息
            StoreInfo objEStoreInfos = storeDAO.SelectByStoreGd(pdTkInfo.getStoreGd());

            if (pdTkInfo != null && pdTkInfo.getExStatus().equals("02")) {

                // 获取盘点结果
                List<GetPDCInfoResDPDInfo> objEPDInfos = new ArrayList<GetPDCInfoResDPDInfo>();

                List<PDDtlInfo> pdDtlInfoList = pdDtlDAO.SelectByGuid(pdTkInfo.getGuid());

                if (pdDtlInfoList != null || pdDtlInfoList.size() > 0) {
                    //获取库存结果
                    List<GetPDCInfoResDInstockInfo> InstockInfos = new ArrayList<GetPDCInfoResDInstockInfo>();

                    //循环遍历查询盘点明细数据
                    for (PDDtlInfo pdDtlInfo : pdDtlInfoList) {

                        GetPDCInfoResDPDInfo objEGetPDCInfoResDPDInfo = new GetPDCInfoResDPDInfo();

                        //通过盘点明细批次字段查询库存明细数据
                        InstockDtlInfo objEInstockDtlInfos = instockDtlDAO.SelectBybatch(pdDtlInfo.getBatch());

                        MaterialInfo objeMaterialInfo=materialDAO.SelectByMaCode(pdDtlInfo.getMaterialCode()==null?"":pdDtlInfo.getMaterialCode());
                        //该批次盘点中有，库存中没有
                        if(objEInstockDtlInfos==null){
                            //赋值查询盘点明细数据
                            objEGetPDCInfoResDPDInfo.setMaCode(pdDtlInfo.getMaterialCode()==null?"":pdDtlInfo.getMaterialCode());
                            objEGetPDCInfoResDPDInfo.setMaName(pdDtlInfo.getMaterialName()==null?"":pdDtlInfo.getMaterialName());
                            objEGetPDCInfoResDPDInfo.setMaDes(objeMaterialInfo.getMaterialDes()==null?"":objeMaterialInfo.getMaterialDes());
                            if(objEStoreInfos!=null){
                                objEGetPDCInfoResDPDInfo.setStoreName(objEStoreInfos.getStoreName());
                            }else{
                                objEGetPDCInfoResDPDInfo.setStoreName("");
                            }

                            objEGetPDCInfoResDPDInfo.setLName(pdDtlInfo.getlName()==null?"":pdDtlInfo.getlName());
                            objEGetPDCInfoResDPDInfo.setNum(pdDtlInfo.getNum());
                            objEGetPDCInfoResDPDInfo.setUnitName(pdDtlInfo.getUnitName()==null?"":pdDtlInfo.getUnitName());
                            objEGetPDCInfoResDPDInfo.setChgReason(pdDtlInfo.getChgReason()==null?"":pdDtlInfo.getChgReason());
                            objEPDInfos.add(objEGetPDCInfoResDPDInfo);

                            GetPDCInfoResDInstockInfo objEGetPDCInfoResDInstockInfo = new GetPDCInfoResDInstockInfo();
                            objEGetPDCInfoResDInstockInfo.setStoreName("");
                            objEGetPDCInfoResDInstockInfo.setLName("");
                            objEGetPDCInfoResDInstockInfo.setNum(0.0f);
                            InstockInfos.add(objEGetPDCInfoResDInstockInfo);
                        }

                        //该批次盘点和库存中都有
                        if(objEInstockDtlInfos!=null) {
                            //通过盘点明细的批次关联查询批次信息
                            BInfo objEBInfo = bdao.selectBatchInfoByBatch(objEInstockDtlInfos.getBatch());

                            //当数量或者仓库不同加载盘点结果表格
                            if (objEBInfo.getCanNum() != pdDtlInfo.getNum() || !pdDtlInfo.getStoreGd().equals(objEInstockDtlInfos.getStoreGd())) {
                                //赋值查询盘点明细数据
                                objEGetPDCInfoResDPDInfo.setMaCode(pdDtlInfo.getMaterialCode()==null?"":pdDtlInfo.getMaterialCode());
                                objEGetPDCInfoResDPDInfo.setMaName(pdDtlInfo.getMaterialName()==null?"":pdDtlInfo.getMaterialName());
                                objEGetPDCInfoResDPDInfo.setMaDes(objeMaterialInfo.getMaterialDes()==null?"":objeMaterialInfo.getMaterialDes());
                                if(objEStoreInfos!=null){
                                    objEGetPDCInfoResDPDInfo.setStoreName(objEStoreInfos.getStoreName());
                                }else{
                                    objEGetPDCInfoResDPDInfo.setStoreName("");
                                }

                                objEGetPDCInfoResDPDInfo.setLName(pdDtlInfo.getlName()==null?"":pdDtlInfo.getlName());
                                objEGetPDCInfoResDPDInfo.setNum(pdDtlInfo.getNum());
                                objEGetPDCInfoResDPDInfo.setUnitName(pdDtlInfo.getUnitName()==null?"":pdDtlInfo.getUnitName());
                                objEGetPDCInfoResDPDInfo.setChgReason(pdDtlInfo.getChgReason()==null?"":pdDtlInfo.getChgReason());
                                objEPDInfos.add(objEGetPDCInfoResDPDInfo);
                            }

                            //当数量或者仓库不同加载库存表格
                            if(objEBInfo.getCanNum() != pdDtlInfo.getNum() || !pdDtlInfo.getStoreGd().equals(objEInstockDtlInfos.getStoreGd())){
                                GetPDCInfoResDInstockInfo objEGetPDCInfoResDInstockInfo = new GetPDCInfoResDInstockInfo();

                                //通过库存中的库位标识查询库位信息
                                LocationInfo objELocationInfo = locationDAO.SelectlocationGd(objEInstockDtlInfos.getLocationGd());

                                //查询库存明细中得仓库信息
                                StoreInfo objEStoreInfo=storeDAO.SelectByguid(objEInstockDtlInfos.getStoreGd());

                                //赋值查询库存明细数据
                                if(objEStoreInfo!=null){
                                    objEGetPDCInfoResDInstockInfo.setStoreName(objEStoreInfo.getStoreName());
                                }else{
                                    objEGetPDCInfoResDInstockInfo.setStoreName("");
                                }

                                if (objELocationInfo != null) {
                                    objEGetPDCInfoResDInstockInfo.setLName(objELocationInfo.getlName());
                                }else{
                                    objEGetPDCInfoResDInstockInfo.setLName("");
                                }
                                objEGetPDCInfoResDInstockInfo.setNum(objEBInfo.getCanNum());
                                objEGetPDCInfoResDInstockInfo.setMaCode(objEInstockDtlInfos.getMaterialCode()==null?"":objEInstockDtlInfos.getMaterialCode());
                                objEGetPDCInfoResDInstockInfo.setMaName(objEInstockDtlInfos.getMaterialName()==null?"":objEInstockDtlInfos.getMaterialName());
                                objEGetPDCInfoResDInstockInfo.setMaDes(objeMaterialInfo.getMaterialDes()==null?"":objeMaterialInfo.getMaterialDes());
                                InstockInfos.add(objEGetPDCInfoResDInstockInfo);
                            }

                            //当仓库相同，库位不同得时候加载盘点结果表格
                            if(pdDtlInfo.getStoreGd().equals(objEInstockDtlInfos.getStoreGd()) && (!"".equals(pdDtlInfo.getLocationGd()) && pdDtlInfo.getLocationGd()!=null)  && (!"".equals(objEInstockDtlInfos.getLocationGd()) && objEInstockDtlInfos.getLocationGd()!=null)){
                                if(!pdDtlInfo.getLocationGd().equals(objEInstockDtlInfos.getLocationGd())) {
                                    //赋值查询盘点明细数据
                                    objEGetPDCInfoResDPDInfo.setMaCode(pdDtlInfo.getMaterialCode() == null ? "" : pdDtlInfo.getMaterialCode());
                                    objEGetPDCInfoResDPDInfo.setMaName(pdDtlInfo.getMaterialName() == null ? "" : pdDtlInfo.getMaterialName());
                                    objEGetPDCInfoResDPDInfo.setMaDes(objeMaterialInfo.getMaterialDes()==null?"":objeMaterialInfo.getMaterialDes());

                                    if (objEStoreInfos != null) {
                                        objEGetPDCInfoResDPDInfo.setStoreName(objEStoreInfos.getStoreName());
                                    } else {
                                        objEGetPDCInfoResDPDInfo.setStoreName("");
                                    }

                                    objEGetPDCInfoResDPDInfo.setLName(pdDtlInfo.getlName() == null ? "" : pdDtlInfo.getlName());
                                    objEGetPDCInfoResDPDInfo.setNum(pdDtlInfo.getNum());
                                    objEGetPDCInfoResDPDInfo.setUnitName(pdDtlInfo.getUnitName() == null ? "" : pdDtlInfo.getUnitName());
                                    objEGetPDCInfoResDPDInfo.setChgReason(pdDtlInfo.getChgReason() == null ? "" : pdDtlInfo.getChgReason());
                                    objEPDInfos.add(objEGetPDCInfoResDPDInfo);
                                }
                            }

                            //当仓库相同，库位不同得时候加载库存表格
                            if(pdDtlInfo.getStoreGd().equals(objEInstockDtlInfos.getStoreGd()) && (!"".equals(pdDtlInfo.getLocationGd()) && pdDtlInfo.getLocationGd()!=null) && (!"".equals(objEInstockDtlInfos.getLocationGd()) && objEInstockDtlInfos.getLocationGd()!=null)){
                                if(!pdDtlInfo.getLocationGd().equals(objEInstockDtlInfos.getLocationGd())) {
                                    GetPDCInfoResDInstockInfo objEGetPDCInfoResDInstockInfo = new GetPDCInfoResDInstockInfo();

                                    //通过库存中的库位标识查询库位信息
                                    LocationInfo objELocationInfo = locationDAO.SelectlocationGd(objEInstockDtlInfos.getLocationGd());

                                    //查询库存明细中得仓库信息
                                    StoreInfo objEStoreInfo = storeDAO.SelectByguid(objEInstockDtlInfos.getStoreGd());

                                    //赋值查询库存明细数据
                                    if (objEStoreInfo != null) {
                                        objEGetPDCInfoResDInstockInfo.setStoreName(objEStoreInfo.getStoreName());
                                    } else {
                                        objEGetPDCInfoResDInstockInfo.setStoreName("");
                                    }

                                    if (objELocationInfo != null) {
                                        objEGetPDCInfoResDInstockInfo.setLName(objELocationInfo.getlName());
                                    } else {
                                        objEGetPDCInfoResDInstockInfo.setLName("");
                                    }
                                    objEGetPDCInfoResDInstockInfo.setNum(objEBInfo.getCanNum());
                                    objEGetPDCInfoResDInstockInfo.setMaCode(objEInstockDtlInfos.getMaterialCode() == null ? "" : objEInstockDtlInfos.getMaterialCode());
                                    objEGetPDCInfoResDInstockInfo.setMaName(objEInstockDtlInfos.getMaterialName() == null ? "" : objEInstockDtlInfos.getMaterialName());
                                    objEGetPDCInfoResDInstockInfo.setMaDes(objeMaterialInfo.getMaterialDes()==null?"":objeMaterialInfo.getMaterialDes());
                                    InstockInfos.add(objEGetPDCInfoResDInstockInfo);
                                }
                            }

                        }
                        if(InstockInfos.size()==0){
                            GetPDCInfoResDInstockInfo objEGetPDCInfoResDInstockInfo = new GetPDCInfoResDInstockInfo();
                            objEGetPDCInfoResDInstockInfo.setStoreName("");
                            objEGetPDCInfoResDInstockInfo.setLName("");
                            objEGetPDCInfoResDInstockInfo.setNum(0.0f);
                            InstockInfos.add(objEGetPDCInfoResDInstockInfo);
                        }
                    }
                    objEGetPDCInfoResD.setPDCode(pdTkInfo.getpDCode());
                    if (objEStoreInfos != null) {
                        objEGetPDCInfoResD.setStoreName(objEStoreInfos.getStoreName());
                    }else{
                        objEGetPDCInfoResD.setStoreName("");
                    }

                    objEGetPDCInfoResD.setExecor(pdTkInfo.getExecor());
                    objEGetPDCInfoResD.setExecTime(DateUtil.formatPattern2(pdTkInfo.getExecTime()));
                    objEGetPDCInfoResD.setCancelor(pdTkInfo.getCancelor());
                    objEGetPDCInfoResD.setCancelTime(DateUtil.formatPattern2(pdTkInfo.getCancelTime()));
                    objEGetPDCInfoResD.setFinishor(pdTkInfo.getFinishor());
                    objEGetPDCInfoResD.setFinishTime(DateUtil.formatPattern2(pdTkInfo.getFinishTime()));
                    objEGetPDCInfoResD.setCheckor(pdTkInfo.getCheckor());
                    objEGetPDCInfoResD.setCheckTime(DateUtil.formatPattern2(pdTkInfo.getCheckTime()));

                    objEGetPDCInfoResD.setInstockInfo(InstockInfos);
                    objEGetPDCInfoResD.setPDInfo(objEPDInfos);
                }
            }

            //开始导出数据
            //表头

            //创建table工作薄
            HSSFSheet sheet1 = wb.createSheet("table");
            sheet1.setPrintGridlines(false);
            sheet1.setGridsPrinted(false);
            Object[][] datas1 = {
                    {"盘点差异表", "", ""},
                    {"盘点单号", pdTkInfo.getpDCode(),
                            "盘点仓库", objEStoreInfos.getStoreName(),
                            "盘点开始日期", DateUtil.formatPattern2(pdTkInfo.getCreateTime()),
                            "盘点结束日期", DateUtil.formatPattern2(pdTkInfo.getFinishTime())
                    },
                    {"盘点执行人", pdTkInfo.getExecor(), "盘点差异复核人", "xf"}};


            HSSFRow row1;
            HSSFCell cell1;
            //建表
            for (int i = 0; i < datas1.length; i++) {
                row1 = sheet1.createRow(i);//创建表格行

                for (int j = 0; j < datas1[i].length; j++) {

                    cell1 = row1.createCell(j);//根据表格行创建单元格

                    cell1.setCellValue(String.valueOf(datas1[i][j]));

                    HSSFCellStyle cellStyle = wb.createCellStyle();
                    cellStyle.setAlignment(HorizontalAlignment.CENTER);
                    cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                    if (i == 0 && j == 0) {
                        HSSFFont hssfFont = wb.createFont();
                        hssfFont.setBold(true);
                        // hssfFont.setFontHeightInPoints((short) 30);
                        cellStyle.setFont(hssfFont);
                    }
                    cell1.setCellStyle(cellStyle);
                }
            }


            HSSFSheet sheet2 = wb.getSheet("table");
            Object[][] datas2 = {{"物料代码", "物料名称", "物料描述", "变更前", "", "", "变更后", "", "", "单位", "变更原因"},
                    {"","", "", "仓库", "库位", "数量", "仓库", "库位", "数量", "", ""}
            };
            HSSFRow row2;
            HSSFCell cell2;
            //建表
            for (int i = 0; i < datas2.length; i++) {
                row2 = sheet2.createRow(i + datas1.length);//创建表格行

                for (int j = 0; j < datas2[i].length; j++) {

                    cell2 = row2.createCell(j);//根据表格行创建单元格
                    cell2.setCellValue(String.valueOf(datas2[i][j]));

                    HSSFCellStyle cellStyle = wb.createCellStyle();
                    cellStyle.setAlignment(HorizontalAlignment.CENTER);
                    cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                    cellStyle.setBorderTop(BorderStyle.THIN);
                    cellStyle.setBorderBottom(BorderStyle.THIN);
                    cellStyle.setBorderLeft(BorderStyle.THIN);
                    cellStyle.setBorderRight(BorderStyle.THIN);
                    cell2.setCellStyle(cellStyle);
                }

            }


            //合并单元格
            CellRangeAddress region = new CellRangeAddress(0, // first row
                    0, // last row
                    0, // first column
                    9 // last column
            );
            //合并单元格
            CellRangeAddress region1 = new CellRangeAddress(3,
                    4,
                    0,
                    0
            );
            //合并单元格
            CellRangeAddress region2 = new CellRangeAddress(3,
                    4,
                    1,
                    1
            );
            //合并单元格
            CellRangeAddress region3 = new CellRangeAddress(3,
                    4,
                    2,
                    2
            );
            //合并单元格
            CellRangeAddress region4 = new CellRangeAddress(3,
                    3,
                    3,
                    5
            );
            //合并单元格
            CellRangeAddress region5 = new CellRangeAddress(3,
                    3,
                    6,
                    8
            );
            //合并单元格
            CellRangeAddress region6 = new CellRangeAddress(3,
                    4,
                    9,
                    9
            );
            //合并单元格
            CellRangeAddress region7 = new CellRangeAddress(3,
                    4,
                    10,
                    10
            );

            sheet1.addMergedRegion(region);
            sheet2.addMergedRegion(region1);
            sheet2.addMergedRegion(region2);
            sheet2.addMergedRegion(region3);
            sheet2.addMergedRegion(region4);
            sheet2.addMergedRegion(region5);
            sheet2.addMergedRegion(region6);
            sheet2.addMergedRegion(region7);
            //设置行高列宽
            sheet1.setPrintGridlines(false);
            for (int i = 0; i < datas1.length; i++) {
                row1 = sheet1.getRow(i);
                row1.setHeightInPoints(20);
                for (int j = 0; j < datas1[i].length; j++) {
                    sheet1.autoSizeColumn(j);
                    sheet1.setColumnWidth(j, MSExcelUtil.pixel2WidthUnits(65));
                }
            }

            for (int i = 0; i < datas2.length; i++) {
                row2 = sheet2.getRow(i + datas1.length);
                row2.setHeightInPoints(20);
                for (int j = 0; j < datas2[i].length; j++) {
                    sheet2.setColumnWidth(j, MSExcelUtil.pixel2WidthUnits(65));
                }
            }

            /**********************************差异数据************************************/
            HSSFSheet sheet3 = wb.getSheet("table");
            HSSFRow row3;
            HSSFCell cell3;
            //建表
            for (int i = 0; i < objEGetPDCInfoResD.getPDInfo().size(); i++) {
                row3 = sheet3.createRow(i + datas1.length + datas2.length);//创建表格行

                for (int j = 0; j < datas2[1].length; j++) {

                    cell3 = row3.createCell(j);//根据表格行创建单元格
                    if (j == 0) {
                        cell3.setCellValue(objEGetPDCInfoResD.getPDInfo().get(i).getMaCode());
                    } else if (j == 1) {
                        cell3.setCellValue(objEGetPDCInfoResD.getPDInfo().get(i).getMaName());
                    } else if (j == 2) {
                        cell3.setCellValue(objEGetPDCInfoResD.getPDInfo().get(i).getMaDes());
                    }else if (j == 3) {
                        cell3.setCellValue(objEGetPDCInfoResD.getInstockInfo().get(i).getStoreName());
                    } else if (j == 4) {
                        cell3.setCellValue(objEGetPDCInfoResD.getInstockInfo().get(i).getLName());
                    } else if (j == 5) {
                        cell3.setCellValue(objEGetPDCInfoResD.getInstockInfo().get(i).getNum());
                    } else if (j == 6) {
                        cell3.setCellValue(objEGetPDCInfoResD.getPDInfo().get(i).getStoreName());
                    } else if (j == 7) {
                        cell3.setCellValue(objEGetPDCInfoResD.getPDInfo().get(i).getLName());
                    } else if (j == 8) {
                        cell3.setCellValue(objEGetPDCInfoResD.getPDInfo().get(i).getNum());
                    } else if (j == 9) {
                        cell3.setCellValue(objEGetPDCInfoResD.getPDInfo().get(i).getUnitName());
                    } else if (j == 10) {
                        cell3.setCellValue(objEGetPDCInfoResD.getPDInfo().get(i).getChgReason());
                    }


                    HSSFCellStyle cellStyle = wb.createCellStyle();
                    cellStyle.setAlignment(HorizontalAlignment.CENTER);
                    cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                    cellStyle.setBorderTop(BorderStyle.THIN);
                    cellStyle.setBorderBottom(BorderStyle.THIN);
                    cellStyle.setBorderLeft(BorderStyle.THIN);
                    cellStyle.setBorderRight(BorderStyle.THIN);

                    cell3.setCellStyle(cellStyle);
                }

            }
            for (int i = 0; i < objEGetPDCInfoResD.getPDInfo().size(); i++) {
                row3 = sheet3.getRow(i + datas1.length + datas2.length);
                row3.setHeightInPoints(20);
                for (int j = 0; j < datas2[1].length; j++) {
                    sheet3.setColumnWidth(j, MSExcelUtil.pixel2WidthUnits(65));
                }
            }

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            wb.write(os);

            return os;

        } catch (Exception e) {
            if (e != null && e.getMessage() != null) {
                throw new SystemException("0x000000", e.getMessage());
            }
        } finally {
            if (wb != null) try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ExportPDCInfoResB exportPDCInfoResB = new ExportPDCInfoResB();
        exportPDCInfoResB.setMsgCode("");
        exportPDCInfoResB.setMsgDes("成功!");

        ExportPDCInfoRes exportPDCInfoRes = new ExportPDCInfoRes();
        exportPDCInfoRes.setBody(exportPDCInfoResB);


        return null;
    }

}
