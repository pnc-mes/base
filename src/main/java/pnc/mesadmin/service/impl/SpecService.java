package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllSpecInfo.GetAllSpecInfoRes;
import pnc.mesadmin.dto.GetAllSpecInfo.GetAllSpecInfoResB;
import pnc.mesadmin.dto.GetAllSpecInfo.GetAllSpecInfoResD;
import pnc.mesadmin.dto.GetAllUnitInfo.GetAllUnitInfoResD;
import pnc.mesadmin.dto.GetSVInfo.*;
import pnc.mesadmin.dto.GetSVTreeInfo.GetSVTreeInfoReq00;
import pnc.mesadmin.dto.GetSVTreeInfo.GetSVTreeInfoRes;
import pnc.mesadmin.dto.GetSVTreeInfo.GetSVTreeInfoResB;
import pnc.mesadmin.dto.GetSVTreeInfo.GetSVTreeInfoResD;
import pnc.mesadmin.dto.SaveSpecInfo.*;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.SpecIService;
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
 * 子系统名称：工序信息实现层
 * 创建人：张亮亮
 * 创建时间：2017-06-02
 * 修改人：
 * 修改时间：
 */
@Transactional
@Service
public class SpecService implements SpecIService {

    @Resource
    private SpecDAO specDAO;

    @Resource
    private SpecVerDAO specVerDAO;

    @Resource
    private DevGpDAO devGpInfoMapper;

    @Resource
    private OpertDAO opertInfoMapper;

    @Resource
    private DCVerDAO dcVerDAO;

    @Resource
    private SkillGlDAO skillGlDAO;

    @Resource
    private FileGpDAO fileGpDAO;

    @Resource
    private PrinterDAO printerDAO;

    @Resource
    private PrintTDAO printTDAO;

    @Resource
    private WFVerDAO wfVerDAO;

    @Resource
    private BaseIService baseIService;

    //获取工序信息列表
    public GetAllSpecInfoRes QALLGetAllSpecInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllSpecInfoRes objEGetAllSpecInfoRes = new GetAllSpecInfoRes();
        GetAllSpecInfoResB objEGetAllSpecInfoResB = new GetAllSpecInfoResB();
        List<GetAllSpecInfoResD> objEGetAllSpecInfoResDs = new ArrayList<GetAllSpecInfoResD>();

        //查询所有的工序信息
        List<SpecInfo> objESpecInfos = baseIService.QALLBaseInfo(SpecDAO.class, "SelectSpecInfo",
                argInitDataFields, argPageInfo);

        if (objESpecInfos != null && objESpecInfos.size() >= 1) {
            for (int i = 0; i < objESpecInfos.size(); i++) {
                GetAllSpecInfoResD objEGetAllSpecInfoResD = new GetAllSpecInfoResD();
                objEGetAllSpecInfoResD.setSpecRd(objESpecInfos.get(i).getRuid());
                objEGetAllSpecInfoResD.setSpecName(objESpecInfos.get(i).getSpecName());
                SpecVerInfo objESpecVerInfo = specVerDAO.SelectSpecVerInfoByspecGd00(objESpecInfos.get(i).getGuid());
                if (objESpecVerInfo != null) {
                    objEGetAllSpecInfoResD.setSpecVerRd(objESpecVerInfo.getRuid());
                    objEGetAllSpecInfoResD.setVersion(objESpecVerInfo.getVersion());
                }

                objEGetAllSpecInfoResDs.add(objEGetAllSpecInfoResD);
            }
        }
        objEGetAllSpecInfoResB.setData(objEGetAllSpecInfoResDs);
        objEGetAllSpecInfoRes.setBody(objEGetAllSpecInfoResB);
        return objEGetAllSpecInfoRes;
    }

    @Override
    public PageResult<GetAllSpecInfoResD> QALLGetAllSpecNewRes(BaseRequest req) {
        List<GetAllSpecInfoResD> objEGetAllSpecInfoResDs = new ArrayList<GetAllSpecInfoResD>();
        GetAllSpecInfoResD resD = null;
        SpecVerInfo objESpecVerInfo = null;

        IPage<SpecInfo> iPage = specDAO.selectPage(new MyPage(req), CommonUtils.getQueryWrapper(req.getFields()));

        //赋值dto并返回
        for (SpecInfo obj : iPage.getRecords()) {
            resD = new GetAllSpecInfoResD();
            resD.setSpecRd(obj.getRuid());
            resD.setSpecName(obj.getSpecName());
            objESpecVerInfo = specVerDAO.SelectSpecVerInfoByspecGd00(obj.getGuid());
            if (objESpecVerInfo != null) {
                resD.setSpecVerRd(objESpecVerInfo.getRuid());
                resD.setVersion(objESpecVerInfo.getVersion());
            }

            objEGetAllSpecInfoResDs.add(resD);
        }

        return new PageResult<>(iPage, objEGetAllSpecInfoResDs);
    }

    //获取工序信息根据工序ID
    public GetSVInfoRes GetGetSVInfoRes(GetSVInfoReq00 argGetSVInfoReq00) {
        GetSVInfoRes objEGetSVInfoRes = new GetSVInfoRes();
        GetSVInfoResB objEGetSVInfoResB = new GetSVInfoResB();
        //根据传过来的 id 查询工序信息
        SpecInfo objESpecInfo = specDAO.SelectSpecInfoByruid(argGetSVInfoReq00.getSpecRd());

        //获取工序信息的工序版本标识
        List<SpecVerInfo> objESpecVerInfos = specVerDAO.SelectSpecVerInfoByspecGd(objESpecInfo.getGuid());

        for (SpecVerInfo obj : objESpecVerInfos) {
            //专门查询属于该下面的默认版本
            if (!"01".equals(obj.getIsDef())) {
                GetSVInfoResD objEGetSVInfoResD = new GetSVInfoResD();

                /*GetSVInfoResDPTInfo getSVInfoResDPTInfo=new GetSVInfoResDPTInfo();
                //获取打印模板信息
                PrintTInfo objEPrintTInfo = printTDAO.SelectByGuid(obj.getPrintTGd());
                if(objEPrintTInfo != null){
                    getSVInfoResDPTInfo.setPrintTRd(objEPrintTInfo.getRuid());
                    getSVInfoResDPTInfo.setPtName(objEPrintTInfo.getTempName());
                    objEGetSVInfoResD.setPTInfo(getSVInfoResDPTInfo);
                }
                GetSVInfoResDPrinterInfo getSVInfoResDPrinterInfo=new GetSVInfoResDPrinterInfo();
                //获取打印机信息
                PrinterInfo objEPrinterInfo = printerDAO.SelectByGuid(obj.getPinterGd());
                if(objEPrinterInfo!= null){
                    getSVInfoResDPrinterInfo.setPrRd(objEPrinterInfo.getRuid());
                    getSVInfoResDPrinterInfo.setPrName(objEPrinterInfo.getPrinterName());
                    objEGetSVInfoResD.setPrinterInfo(getSVInfoResDPrinterInfo);
                }*/

                //获取工序版本ID
                int strSpecVerRuid = obj.getRuid();
                //获取工序名称
                //   String strSpecName=obj.getSpecName();
                String strVersion = obj.getVersion();
                String strIsDef = obj.getIsDef();
                if ("00".equals(strIsDef)) {
                    strIsDef = "00";
                } else {
                    strIsDef = "01";
                }
                String strStatus = obj.getStatus();
                if ("00".equals(strStatus)) {
                    strStatus = "00";
                }
                if ("01".equals(strStatus)) {
                    strStatus = "01";
                }
                objEGetSVInfoResD.setSpecRd(objESpecInfo.getRuid());
                objEGetSVInfoResD.setSpecVerRd(strSpecVerRuid);
                //这个是版本工序名字
                objEGetSVInfoResD.setSpecName(objESpecInfo.getSpecName());
                objEGetSVInfoResD.setVersion(strVersion);
                objEGetSVInfoResD.setIsDef(strIsDef);
                objEGetSVInfoResD.setStatus(strStatus);
                objEGetSVInfoResD.setCreator(objESpecInfo.getCreator());
                objEGetSVInfoResD.setCreateTime(DateUtil.format(objESpecInfo.getCreateTime()));
                objEGetSVInfoResD.setLastModifyMan(objESpecInfo.getLastModifyMan());
                objEGetSVInfoResD.setLastModifyTime(DateUtil.format(objESpecInfo.getLastModifyTime()));
                objEGetSVInfoResD.setRemark(objESpecInfo.getRemark());
                //作业ID
                String strOpertGd = obj.getOpertGd();
                OpertInfo objEOpertInfo = opertInfoMapper.GetOpertInfoByGuid(strOpertGd);

                GetSVInfoResDOpert objEGetSVInfoResDOpert = new GetSVInfoResDOpert();
                if (objEOpertInfo != null) {
                    objEGetSVInfoResDOpert.setOptRd(objEOpertInfo.getRuid());
                    objEGetSVInfoResDOpert.setOptName(objEOpertInfo.getOptname());
                }

                //设备组ID
                String strDevGrGd = obj.getDevGrGd();
                DevGpInfo objEDevGpInfo = devGpInfoMapper.SelectById(strDevGrGd);

                GetSVInfoResDDC getSVInfoResDDC = new GetSVInfoResDDC();
                GetSVInfoResDDC getSVInfoResDDC2 = new GetSVInfoResDDC();

                //上机前数据采集版本
                DCVerInfo objEDCInfo = dcVerDAO.selectDCVerInfoByGuid(obj.getdCVerGd());
                if (objEDCInfo != null) {
                    getSVInfoResDDC.setDCVerRd(objEDCInfo.getRuid());
                    getSVInfoResDDC.setDCName(objEDCInfo.getDcName());
                }
                //上机后数据采集版本
                objEDCInfo = dcVerDAO.selectDCVerInfoByGuid(obj.getAfDCVerGd());
                if (objEDCInfo != null) {
                    getSVInfoResDDC2.setDCVerRd(objEDCInfo.getRuid());
                    getSVInfoResDDC2.setDCName(objEDCInfo.getDcName());
                }

                GetSVInfoResDSkillG getSVInfoResDSkillG = new GetSVInfoResDSkillG();
                //技能组
                SkillGlInfo objESkillGlInfo = skillGlDAO.SelectSkillGlInfoByGuid(obj.getSkillGGd());

                if (objESkillGlInfo != null) {
                    getSVInfoResDSkillG.setSkillGRd(objESkillGlInfo.getRuid());
                    getSVInfoResDSkillG.setSkillGName(objESkillGlInfo.getSkillGName());
                }

                GetSVInfoResDDevice objEGetSVInfoResDDevice = new GetSVInfoResDDevice();
                if (objEDevGpInfo != null) {
                    objEGetSVInfoResDDevice.setDevGpRd(objEDevGpInfo.getRuid());
                    objEGetSVInfoResDDevice.setDevGpName(objEDevGpInfo.getDevGpName());
                }

                //文件组
                GetSVInfoResDFileGrInfo objEGetSVInfoResDFileGrInfo = new GetSVInfoResDFileGrInfo();
                FileGpInfo objEFileGpInfo = fileGpDAO.SelectByguid(obj.getFileGrGd());

                if (objEFileGpInfo != null) {
                    objEGetSVInfoResDFileGrInfo.setFileGRd(objEFileGpInfo.getRuid());
                    objEGetSVInfoResDFileGrInfo.setFileGpName(objEFileGpInfo.getFileGpName());
                }

                objEGetSVInfoResD.setSkillG(getSVInfoResDSkillG);
                objEGetSVInfoResD.setDC(getSVInfoResDDC);
                objEGetSVInfoResD.setAfterDC(getSVInfoResDDC2);
                objEGetSVInfoResD.setOpert(objEGetSVInfoResDOpert);
                objEGetSVInfoResD.setDevice(objEGetSVInfoResDDevice);
                objEGetSVInfoResD.setFileGrInfo(objEGetSVInfoResDFileGrInfo);
                objEGetSVInfoResB.setData(objEGetSVInfoResD);
            }
        }
        objEGetSVInfoRes.setBody(objEGetSVInfoResB);

        return objEGetSVInfoRes;
    }

    //获取工序信息根据工序版本ID
    public GetSVInfoRes GetGetSVInfoRes1(GetSVInfoReq01 argGetSVInfoReq01) {
        GetSVInfoRes objEGetSVInfoRes = new GetSVInfoRes();
        GetSVInfoResB objEGetSVInfoResB = new GetSVInfoResB();
        GetSVInfoResD objEGetSVInfoResD = new GetSVInfoResD();
        SpecVerInfo objESpecVerInfo = specVerDAO.SelectSpecVerInfoByruid(argGetSVInfoReq01.getSpecVerRd());
        if (objESpecVerInfo != null) {
            // GetSVInfoResDPTInfo getSVInfoResDPTInfo=new GetSVInfoResDPTInfo();
            /*//获取打印模板信息
            PrintTInfo objEPrintTInfo = printTDAO.SelectByGuid(objESpecVerInfo.getPrintTGd());
            if(objEPrintTInfo != null){
                getSVInfoResDPTInfo.setPrintTRd(objEPrintTInfo.getRuid());
                getSVInfoResDPTInfo.setPtName(objEPrintTInfo.getTempName());
                objEGetSVInfoResD.setPTInfo(getSVInfoResDPTInfo);
            }
            GetSVInfoResDPrinterInfo getSVInfoResDPrinterInfo=new GetSVInfoResDPrinterInfo();
            //获取打印机信息
            PrinterInfo objEPrinterInfo = printerDAO.SelectByGuid(objESpecVerInfo.getPinterGd());
            if(objEPrinterInfo != null){
                getSVInfoResDPrinterInfo.setPrRd(objEPrinterInfo.getRuid());
                getSVInfoResDPrinterInfo.setPrName(objEPrinterInfo.getPrinterName());
                objEGetSVInfoResD.setPrinterInfo(getSVInfoResDPrinterInfo);
            }*/
            //根据工序版本的工序标识获取工序标识
            SpecInfo objSpecInfo = specDAO.SelectSpecInfoByguid(objESpecVerInfo.getSpecGd());


            //获取工序版本ID
            int strSpecVerRuid = objESpecVerInfo.getRuid();
            //获取工序名称
            //   String strSpecName=obj.getSpecName();
            String strIsDef = objESpecVerInfo.getIsDef();
            if ("00".equals(strIsDef)) {
                strIsDef = "00";
            } else {
                strIsDef = "01";
            }
            String strStatus = objESpecVerInfo.getStatus();
            if ("00".equals(strStatus)) {
                strStatus = "00";
            } else {
                strStatus = "01";
            }
            if (objSpecInfo != null) {
                objEGetSVInfoResD.setSpecRd(objSpecInfo.getRuid());
            }

            objEGetSVInfoResD.setSpecVerRd(strSpecVerRuid);
            //这个是版本工序名字
            objEGetSVInfoResD.setSpecName(objESpecVerInfo.getSpecName());
            objEGetSVInfoResD.setVersion(objESpecVerInfo.getVersion());
            objEGetSVInfoResD.setIsDef(strIsDef);
            objEGetSVInfoResD.setStatus(strStatus);
            objEGetSVInfoResD.setCreator(objESpecVerInfo.getCreator());
            objEGetSVInfoResD.setCreateTime(DateUtil.format(objESpecVerInfo.getCreateTime()));
            objEGetSVInfoResD.setLastModifyMan(objESpecVerInfo.getLastModifyMan());
            objEGetSVInfoResD.setLastModifyTime(DateUtil.format(objESpecVerInfo.getLastModifyTime()));
            objEGetSVInfoResD.setRemark(objESpecVerInfo.getRemark());
            //作业ID
            String strOpertGd = objESpecVerInfo.getOpertGd();
            OpertInfo objEOpertInfo = opertInfoMapper.GetOpertInfoByGuid(strOpertGd);
            GetSVInfoResDOpert objEGetSVInfoResDOpert = new GetSVInfoResDOpert();
            if (objEOpertInfo != null) {
                objEGetSVInfoResDOpert.setOptRd(objEOpertInfo.getRuid());
                objEGetSVInfoResDOpert.setOptName(objEOpertInfo.getOptname());
            }

            //设备组ID
            String strDevGrGd = objESpecVerInfo.getDevGrGd();
            DevGpInfo objEDevGpInfo = devGpInfoMapper.SelectById(strDevGrGd);

            GetSVInfoResDDevice objEGetSVInfoResDDevice = new GetSVInfoResDDevice();
            if (objEDevGpInfo != null) {
                objEGetSVInfoResDDevice.setDevGpRd(objEDevGpInfo.getRuid());
                objEGetSVInfoResDDevice.setDevGpName(objEDevGpInfo.getDevGpName());
                objEGetSVInfoResD.setDevice(objEGetSVInfoResDDevice);
            } else {
                objEGetSVInfoResD.setDevice(null);
            }


            GetSVInfoResDDC getSVInfoResDDC = new GetSVInfoResDDC();
            GetSVInfoResDDC getSVInfoResDDC2 = new GetSVInfoResDDC();

            //上机前数据采集版本
            DCVerInfo objEDCInfo = dcVerDAO.selectDCVerInfoByGuid(objESpecVerInfo.getdCVerGd());
            if (objEDCInfo != null) {
                getSVInfoResDDC.setDCVerRd(objEDCInfo.getRuid());
                getSVInfoResDDC.setDCName(objEDCInfo.getDcName());
            }
            //上机后数据采集版本
            objEDCInfo = dcVerDAO.selectDCVerInfoByGuid(objESpecVerInfo.getAfDCVerGd());
            if (objEDCInfo != null) {
                getSVInfoResDDC2.setDCVerRd(objEDCInfo.getRuid());
                getSVInfoResDDC2.setDCName(objEDCInfo.getDcName());
            }


            //技能组
            GetSVInfoResDSkillG getSVInfoResDSkillG = new GetSVInfoResDSkillG();
            SkillGlInfo objESkillGlInfo = skillGlDAO.SelectSkillGlInfoByGuid(objESpecVerInfo.getSkillGGd());

            if (objESkillGlInfo != null) {
                getSVInfoResDSkillG.setSkillGRd(objESkillGlInfo.getRuid());
                getSVInfoResDSkillG.setSkillGName(objESkillGlInfo.getSkillGName());
            }

            //文件组
            GetSVInfoResDFileGrInfo objEGetSVInfoResDFileGrInfo = new GetSVInfoResDFileGrInfo();
            FileGpInfo objEFileGpInfo = fileGpDAO.SelectByguid(objESpecVerInfo.getFileGrGd());

            if (objEFileGpInfo != null) {
                objEGetSVInfoResDFileGrInfo.setFileGRd(objEFileGpInfo.getRuid());
                objEGetSVInfoResDFileGrInfo.setFileGpName(objEFileGpInfo.getFileGpName());
            }

            objEGetSVInfoResD.setSkillG(getSVInfoResDSkillG);
            objEGetSVInfoResD.setDC(getSVInfoResDDC);
            objEGetSVInfoResD.setAfterDC(getSVInfoResDDC2);
            objEGetSVInfoResD.setOpert(objEGetSVInfoResDOpert);

            objEGetSVInfoResD.setFileGrInfo(objEGetSVInfoResDFileGrInfo);
            objEGetSVInfoResB.setData(objEGetSVInfoResD);

        }

        objEGetSVInfoRes.setBody(objEGetSVInfoResB);

        return objEGetSVInfoRes;
    }

    //dto新增工序信息
    public SaveSpecInfoRes AddSaveSpecInfoRes(SaveSpecInfoReqBD00 argSaveSpecInfoReqBD00) {
        SaveSpecInfoRes objESaveSpecInfoRes = new SaveSpecInfoRes();
        SaveSpecInfoResB objESaveSpecInfoResB = new SaveSpecInfoResB();

        //工序版本信息
        SpecVerInfo objESpecVerInfo = new SpecVerInfo();

        if ((argSaveSpecInfoReqBD00.getVersion()).equals("")) {
            throw new SystemException("MG0003F", "版本不能为空");
        }

        objESpecVerInfo.setGuid(CommonUtils.getRandomNumber());

        //工序信息
        SpecInfo objESpecInfo = new SpecInfo();

        //校验工序名称
        List<SpecInfo> objeSpecInfo = specDAO.SelectSpecInfo();

        //逻辑校验保存的工序版本不能相同--LFZ
        for (SpecInfo obj : objeSpecInfo) {
            if (obj.getSpecName().equals(argSaveSpecInfoReqBD00.getSpecName())) {
                throw new SystemException("MG0006F", "工序名称已存在");
            }
        }

        objESpecInfo.setGuid(CommonUtils.getRandomNumber());

        //默认版本标识
        objESpecInfo.setVerGd(objESpecVerInfo.getGuid());

        objESpecInfo.setSpecName(argSaveSpecInfoReqBD00.getSpecName());
        objESpecInfo.setRemark(argSaveSpecInfoReqBD00.getRemark());
        objESpecInfo.setCreateTime(new Date());
        objESpecInfo.setCreator(CommonUtils.readUser().getRealName());


        if (specDAO.InsertSpecInfo(objESpecInfo) <= 0) {
            throw new SystemException("MG_MES4001812010001F", "新增工序信息失败");
        }


        objESpecVerInfo.setSpecGd(objESpecInfo.getGuid());
        objESpecVerInfo.setSpecName(argSaveSpecInfoReqBD00.getSpecName());
        objESpecVerInfo.setVersion(argSaveSpecInfoReqBD00.getVersion());
        //只要是新增，则该地方就是新增默认版本
        objESpecVerInfo.setIsDef("00");//可用
        objESpecVerInfo.setStatus(argSaveSpecInfoReqBD00.getStatus());
        //作业标识
        OpertInfo objEOpertInfo = opertInfoMapper.GetOpertInfoByRuid(argSaveSpecInfoReqBD00.getOptRd());

        if (objEOpertInfo != null) {
            objESpecVerInfo.setOpertGd(objEOpertInfo.getGuid());
        }
        //上机前数据采集
        DCVerInfo objEDCInfo = dcVerDAO.selectDCVerInfoByRuid(argSaveSpecInfoReqBD00.getDCVerRd());//dcdao.selectDCInfoByDcRd();
        if (objEDCInfo != null) {
            objESpecVerInfo.setdCVerGd(objEDCInfo.getGuid());
        }
        //上机后数据采集
        objEDCInfo = dcVerDAO.selectDCVerInfoByRuid(argSaveSpecInfoReqBD00.getAfDCVerRd());//dcdao.selectDCInfoByDcRd();
        if (objEDCInfo != null) {
            objESpecVerInfo.setAfDCVerGd(objEDCInfo.getGuid());
        }
        //技能组
        SkillGlInfo objESkillGlInfo = skillGlDAO.SelectSkillGlInfoByRuid(argSaveSpecInfoReqBD00.getSkillGRd());
        if (objESkillGlInfo != null) {
            objESpecVerInfo.setSkillGGd(objESkillGlInfo.getGuid());
        }

        //设备组标识标识
        DevGpInfo objEDevGpInfo = devGpInfoMapper.SelectDevGpById(argSaveSpecInfoReqBD00.getDevGpRd());
        if (objEDevGpInfo != null) {
            objESpecVerInfo.setDevGrGd(objEDevGpInfo.getGuid());
        }

        //文件组
        FileGpInfo objEFileGpInfo = fileGpDAO.SelectByruid(argSaveSpecInfoReqBD00.getFileGRd());
        if (objEFileGpInfo != null) {
            objESpecVerInfo.setFileGrGd(objEFileGpInfo.getGuid());
        }


        objESpecVerInfo.setCreateTime(new Date());
        objESpecVerInfo.setCreator(CommonUtils.readUser().getRealName());
        objESpecVerInfo.setRemark(argSaveSpecInfoReqBD00.getRemark());
        if (specVerDAO.InsertSpecVerInfo(objESpecVerInfo) <= 0) {
            throw new SystemException("MG_MES4001812010001F", "新增工序版本信息失败");
        }


        objESaveSpecInfoRes.setBody(objESaveSpecInfoResB);
        return objESaveSpecInfoRes;
    }

    //dto删除工序信息
    public SaveSpecInfoRes RmSaveSpecInfoRes(SaveSpecInfoReqBD01 argSaveSpecInfoReqBD01) {
        SaveSpecInfoRes objESaveSpecInfoRes = new SaveSpecInfoRes();
        SaveSpecInfoResB objESaveSpecInfoResB = new SaveSpecInfoResB();

        //根据传过来的工序id查询工序信息并获取工序版本标识
        SpecInfo objESpecInfo = specDAO.SelectSpecInfoByruid(argSaveSpecInfoReqBD01.getSpecRd());
        String strGuid = objESpecInfo.getGuid();

        //根据标识查询工序版本信息
        List<SpecVerInfo> objESpecVerInfos = specVerDAO.SelectSpecVerInfoByspecGd(strGuid);
        for (SpecVerInfo obj : objESpecVerInfos) {

            if (specVerDAO.DeleteSpecVerInfoByspecGd(obj.getSpecGd(), obj.getGuid()) <= 0) {
                throw new SystemException("MG_MES4001812012001F", "删除工序信息所有的版本失败");
            }
        }

        if (specDAO.DeleteSpecInfoByruid(argSaveSpecInfoReqBD01.getSpecRd()) <= 0) {
            throw new SystemException("MG_MES4001812011001F", "删除工序信息失败");
        }
        //查询是否被工艺流程引用
        Integer count = wfVerDAO.selectCount(new QueryWrapper<WFVerInfo>().like("wFJson", objESpecInfo.getSpecName()));
        if (count > 0) {
            throw new SystemException("", "该工序已被使用,不允许删除");
        }

        //查询是否被BOM引用

        objESaveSpecInfoRes.setBody(objESaveSpecInfoResB);
        return objESaveSpecInfoRes;
    }

    //dto更新工序信息
    public SaveSpecInfoRes ModSaveSpecInfoRes(SaveSpecInfoReqBD02 argSaveSpecInfoReqBD02) {


        SaveSpecInfoRes objESaveSpecInfoRes = new SaveSpecInfoRes();
        SaveSpecInfoResB objESaveSpecInfoResB = new SaveSpecInfoResB();

        //根据工序id查询工序信息
        SpecInfo objESpecInfo = specDAO.SelectSpecInfoByruid(argSaveSpecInfoReqBD02.getSpecRd());

        if (objESpecInfo == null) {
            throw new SystemException("MG_MES4001810010001F", "查询工序信息为空");
        }

        //查询工序名称--LFZ
        SpecInfo objeSpecInfo = specDAO.SelectSpecName(argSaveSpecInfoReqBD02.getSpecName());

        if (objeSpecInfo != null && !objeSpecInfo.getSpecName().equals(objESpecInfo.getSpecName())) {
            throw new SystemException("MG0006F", "更新失败，工序名称已存在");
        }

        if (StringUtils.isBlank(argSaveSpecInfoReqBD02.getSpecName())) {
            throw new SystemException("MG0003F", "工序名称不能为空");
        }

        objESpecInfo.setLastModifyTime(new Date());
        objESpecInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        objESpecInfo.setRemark(argSaveSpecInfoReqBD02.getRemark());

        //根据传过来的工序版本id查询工序版本信息
        SpecVerInfo objESpecVerInfos = specVerDAO.SelectSpecVerInfoByruid(argSaveSpecInfoReqBD02.getSpecVerRd());

        if (objESpecVerInfos == null) {
            throw new SystemException("MG_MES4001811020001F", "查询工序版本信息失败");
        }

        if ((argSaveSpecInfoReqBD02.getVersion()).equals("")) {
            throw new SystemException("MG0003F", "版本不能为空");
        }

        SpecVerInfo objVersion = specVerDAO.SelectSpecVerInfoByVersion(argSaveSpecInfoReqBD02.getVersion(), objESpecVerInfos.getSpecGd());
        if (objVersion != null && !objVersion.getVersion().equals(objESpecVerInfos.getVersion())) {
            throw new SystemException("MG_MES4001811020007F", "更新失败，该版本已存在");
        }


        if ("00".equals(objESpecVerInfos.getIsDef())) {

            objESpecInfo.setSpecName(argSaveSpecInfoReqBD02.getSpecName());

            //获取该版本下所有的版本
            List<SpecVerInfo> objESpecVerInfoss = specVerDAO.SelectSpecVerInfoByspecGd(objESpecVerInfos.getSpecGd());
            for (SpecVerInfo obj : objESpecVerInfoss) {

                if ("00".equals(argSaveSpecInfoReqBD02.getIsDef())) {
                    //由于更新的是默认版本，因此设置该下面的所有版本名字都更新成一个
                    obj.setSpecName(argSaveSpecInfoReqBD02.getSpecName());
                    if (specVerDAO.UpdateSpecVerInfo(obj) <= 0) {
                        throw new SystemException("xxx", "更新所有的工序版本名称信息失败");
                    }
                    //更新默认版本
                    if ("00".equals(obj.getIsDef())) {
                        //更新单条的数据，由于默认因此属于该下面的所有名称都是一样的
                        SpecVerInfo objESpecVerInfo = new SpecVerInfo();
                        objESpecVerInfo.setRuid(obj.getRuid());
                        objESpecVerInfo.setIsDef("00");
                        objESpecVerInfo.setSpecName(argSaveSpecInfoReqBD02.getSpecName());

                        objESpecVerInfo.setVersion(argSaveSpecInfoReqBD02.getVersion());
                        objESpecVerInfo.setStatus(argSaveSpecInfoReqBD02.getStatus());
                        //作业 ID
                        OpertInfo objEOpertInfo = opertInfoMapper.GetOpertInfoByRuid(argSaveSpecInfoReqBD02.getOptRd());
                        if (objEOpertInfo != null) {
                            objESpecVerInfo.setOpertGd(objEOpertInfo.getGuid());
                        } else {
                            objESpecVerInfo.setOpertGd("");
                        }
                        //设备组 ID
                        DevGpInfo objEDevGpInfo = devGpInfoMapper.SelectDevGpById(argSaveSpecInfoReqBD02.getDevGpRd());
                        if (objEDevGpInfo != null) {
                            objESpecVerInfo.setDevGrGd(objEDevGpInfo.getGuid());
                        } else {
                            objESpecVerInfo.setDevGrGd("");
                        }

                        //上机前数据采集
                        DCVerInfo objEDCInfo = dcVerDAO.selectDCVerInfoByRuid(argSaveSpecInfoReqBD02.getDCVerRd());
                        if (objEDCInfo != null) {
                            objESpecVerInfo.setdCVerGd(objEDCInfo.getGuid());
                        } else {
                            objESpecVerInfo.setdCVerGd("");
                        }
                        //上机后数据采集
                        objEDCInfo = dcVerDAO.selectDCVerInfoByRuid(argSaveSpecInfoReqBD02.getAfDCVerRd());
                        if (objEDCInfo != null) {
                            objESpecVerInfo.setAfDCVerGd(objEDCInfo.getGuid());
                        } else {
                            objESpecVerInfo.setAfDCVerGd("");
                        }

                        //技能组
                        SkillGlInfo objESkillGlInfo = skillGlDAO.SelectSkillGlInfoByRuid(argSaveSpecInfoReqBD02.getSkillGRd());
                        if (objESkillGlInfo != null) {
                            objESpecVerInfo.setSkillGGd(objESkillGlInfo.getGuid());
                        } else {
                            objESpecVerInfo.setSkillGGd("");
                        }

                        //文件组
                        FileGpInfo objEFileGpInfo = fileGpDAO.SelectByruid(argSaveSpecInfoReqBD02.getFileGRd());
                        if (objEFileGpInfo != null) {
                            objESpecVerInfo.setFileGrGd(objEFileGpInfo.getGuid());
                        } else {
                            objESpecVerInfo.setFileGrGd("");
                        }

                        objESpecVerInfo.setRemark(argSaveSpecInfoReqBD02.getRemark());
                        objESpecVerInfo.setLastModifyTime(new Date());
                        objESpecVerInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
                        //更新自己默认的版本
                        if (specVerDAO.UpdateSpecVerInfo(objESpecVerInfo) <= 0) {
                            throw new SystemException("xxx", "更新工序版本信息失败");
                        }
                        objESpecInfo.setVerGd(objESpecVerInfo.getGuid());
                        objESpecInfo.setSpecName(argSaveSpecInfoReqBD02.getSpecName());
                        objESpecInfo.setRemark(argSaveSpecInfoReqBD02.getRemark());
                        objESpecInfo.setLastModifyTime(new Date());
                        objESpecInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
                        //更新工序信息
                        if (specDAO.UpdateSpecInfo(objESpecInfo) <= 0) {
                            throw new SystemException("xxx", "更新工序信息失败");
                        }
                    }
                }

            }
        }
        //更新的是该版本下面的子版本，但不是默认版本，则只更新自己信息，否则也可以把自己更新成默认版本
        if ("01".equals(objESpecVerInfos.getIsDef())) {

            objESpecVerInfos.setVersion(argSaveSpecInfoReqBD02.getVersion());
            objESpecVerInfos.setStatus(argSaveSpecInfoReqBD02.getStatus());
            //作业 ID
            OpertInfo objEOpertInfo = opertInfoMapper.GetOpertInfoByRuid(argSaveSpecInfoReqBD02.getOptRd());
            if (objEOpertInfo != null) {
                objESpecVerInfos.setOpertGd(objEOpertInfo.getGuid());
            } else {
                objESpecVerInfos.setOpertGd("");
            }
            //设备组 ID
            DevGpInfo objEDevGpInfo = devGpInfoMapper.SelectDevGpById(argSaveSpecInfoReqBD02.getDevGpRd());

            if (objEDevGpInfo != null) {
                objESpecVerInfos.setDevGrGd(objEDevGpInfo.getGuid());
            } else {
                objESpecVerInfos.setDevGrGd("");
            }
            //上机前数据采集
            DCVerInfo objEDCInfo = dcVerDAO.selectDCVerInfoByRuid(argSaveSpecInfoReqBD02.getDCVerRd());
            if (objEDCInfo != null) {
                objESpecVerInfos.setdCVerGd(objEDCInfo.getGuid());
            } else {
                objESpecVerInfos.setdCVerGd("");
            }
            //上机后数据采集
            objEDCInfo = dcVerDAO.selectDCVerInfoByRuid(argSaveSpecInfoReqBD02.getAfDCVerRd());
            if (objEDCInfo != null) {
                objESpecVerInfos.setAfDCVerGd(objEDCInfo.getGuid());
            } else {
                objESpecVerInfos.setAfDCVerGd("");
            }
            //技能组
            SkillGlInfo objESkillGlInfo = skillGlDAO.SelectSkillGlInfoByRuid(argSaveSpecInfoReqBD02.getSkillGRd());
            if (objESkillGlInfo != null) {
                objESpecVerInfos.setSkillGGd(objESkillGlInfo.getGuid());
            } else {
                objESpecVerInfos.setSkillGGd("");
            }

            //文件组
            FileGpInfo objEFileGpInfo = fileGpDAO.SelectByruid(argSaveSpecInfoReqBD02.getFileGRd());
            if (objEFileGpInfo != null) {
                objESpecVerInfos.setFileGrGd(objEFileGpInfo.getGuid());
            } else {
                objESpecVerInfos.setFileGrGd("");
            }

            objESpecVerInfos.setRemark(argSaveSpecInfoReqBD02.getRemark());
            objESpecVerInfos.setLastModifyTime(new Date());
            objESpecVerInfos.setLastModifyMan((String) SecurityUtils.getSubject().getPrincipal());
            //如果自己不是默认版本，把自己更新成默认，只想改程序
            if ("00".equals(argSaveSpecInfoReqBD02.getIsDef())) {
                //把自己设置成默认
                objESpecVerInfos.setIsDef("00");
                //获取该版本下所有的版本
                List<SpecVerInfo> objESpecVerInfoss = specVerDAO.SelectSpecVerInfoByspecGd(objESpecVerInfos.getSpecGd());
                for (SpecVerInfo obj : objESpecVerInfoss) {
                    //循环遍历设置其他为不是默认版本     即01
                    obj.setIsDef("01");
                    // obj.setSpecName(argSaveSpecInfoReqBD02.getSpecName());

                    if (specVerDAO.UpdateSpecVerInfo(obj) <= 0) {
                        throw new SystemException("MG_MES4001812012005F", "更新工序版本的默认版本信息失败");
                    }
                    //更新自己为默认 即00
                    if (specVerDAO.UpdateSpecVerInfo(objESpecVerInfos) <= 0) {
                        throw new SystemException("MG_MES4001812012005F", "更新工序版本的默认版本信息失败");
                    }

                }
                //为默认的时候，要设置根
                objESpecInfo.setSpecName(argSaveSpecInfoReqBD02.getSpecName());
                objESpecInfo.setVerGd(objESpecVerInfos.getGuid());
                objESpecInfo.setRemark(argSaveSpecInfoReqBD02.getRemark());
                //获取该版本下所有的版本
                List<SpecVerInfo> objESpecVerInfosss = specVerDAO.SelectSpecVerInfoByspecGd(objESpecVerInfos.getSpecGd());
                for (SpecVerInfo obj : objESpecVerInfosss) {
                    obj.setSpecName(argSaveSpecInfoReqBD02.getSpecName());
                    obj.setLastModifyTime(new Date());
                    obj.setLastModifyMan(CommonUtils.readUser().getRealName());
                    //因为更新的是默认版本，所以所有的子版本都更新为传过来的名字
                    if (specVerDAO.UpdateSpecVerInfo(obj) <= 0) {
                        throw new SystemException("MG_MES4001812012003F", "更新工序版本信息失败");
                    }
                }
                if (specDAO.UpdateSpecInfo(objESpecInfo) <= 0) {
                    throw new SystemException("MG_MES4001812012005F", "更新工序版本信息失败");
                }
            }
            //从页面传过来的是否设置默认版本，下面是设置默认版本
            if ("01".equals(argSaveSpecInfoReqBD02.getIsDef())) {
                objESpecVerInfos.setSpecName(argSaveSpecInfoReqBD02.getSpecName());
                if (specVerDAO.UpdateSpecVerInfo(objESpecVerInfos) <= 0) {
                    throw new SystemException("MG_MES4001812012003F", "更新工序版本信息失败");
                }
            }

        }
        objESaveSpecInfoRes.setBody(objESaveSpecInfoResB);
        return objESaveSpecInfoRes;
    }

    //dto复制工序信息
    public SaveSpecInfoRes AddSaveSpecInfoRe(SaveSpecInfoReqBD03 argSaveSpecInfoReqBD03) {
        SaveSpecInfoRes objESaveSpecInfoRes = new SaveSpecInfoRes();
        SaveSpecInfoResB objESaveSpecInfoResB = new SaveSpecInfoResB();
        //根据传过来的id 查询工序信息
        SpecInfo objESpecInfo = specDAO.SelectSpecInfoByruid(argSaveSpecInfoReqBD03.getSpecRd());
        objESpecInfo.setCreateTime(new Date());
        objESpecInfo.setCreator(CommonUtils.readUser().getRealName());
        //根据版本guid查询版本里面的信息
        SpecVerInfo objESpecVerInfo = specVerDAO.SelectSpecVerInfoByspecGd00(objESpecInfo.getGuid());
        objESpecInfo.setGuid(CommonUtils.getRandomNumber());
        if (objESpecVerInfo == null) {
            throw new SystemException("xxx", "查询工序信息失败");
        }
        objESpecVerInfo.setGuid(CommonUtils.getRandomNumber());
        objESpecVerInfo.setCreateTime(new Date());
        objESpecVerInfo.setCreator(CommonUtils.readUser().getRealName());
        objESpecVerInfo.setSpecGd(objESpecInfo.getGuid());
        specVerDAO.InsertSpecVerInfo(objESpecVerInfo);
        objESpecInfo.setSpecName(objESpecInfo.getSpecName() + objESpecInfo.getRuid());
        specDAO.InsertSpecInfo(objESpecInfo);
        SpecInfo oo = specDAO.SelectSpecInfoByguid(objESpecInfo.getGuid());
        oo.setSpecName(oo.getSpecName() + oo.getRuid());
        oo.setVerGd(objESpecVerInfo.getGuid());
        if (specDAO.UpdateSpecInfo(oo) <= 0) {
            throw new SystemException("xxx", "复制失败");
        }
        SpecVerInfo OOOSpecVerInfo = specVerDAO.SelectSpecVerInfoByguid(objESpecVerInfo.getGuid());
        OOOSpecVerInfo.setSpecName(oo.getSpecName());
        if (specVerDAO.UpdateSpecVerInfo(OOOSpecVerInfo) <= 0) {
            throw new SystemException("xxx", "复制失败");
        }

        objESaveSpecInfoRes.setBody(objESaveSpecInfoResB);
        return objESaveSpecInfoRes;
    }

    //dto新增工序版本信息
    public SaveSpecInfoRes AddSaveSpecInfoReq04(SaveSpecInfoReqBD04 argSaveSpecInfoReqBD04) {
        SaveSpecInfoRes objESaveSpecInfoRes = new SaveSpecInfoRes();
        SaveSpecInfoResB objESaveSpecInfoResB = new SaveSpecInfoResB();
        //新增根据工序id查询工序信息
        SpecInfo objESpecInfo = specDAO.SelectSpecInfoByruid(argSaveSpecInfoReqBD04.getSpecRd());

        //新增工序版本信息
        SpecVerInfo objESpecVerInfo = new SpecVerInfo();

        objESpecVerInfo.setGuid(CommonUtils.getRandomNumber());
        objESpecVerInfo.setSpecGd(objESpecInfo.getGuid());

        objESpecVerInfo.setSpecName(objESpecInfo.getSpecName());

        objESpecVerInfo.setStatus(argSaveSpecInfoReqBD04.getStatus());
        if ("00".equals(argSaveSpecInfoReqBD04.getIsDef())) {
            //如果新增版本是默认版本的话把自己设置成默认版本，并且把属于该版本其他设置成不是默认版本，工序的名字改成现在新增的名字，因为是默认版本
            objESpecVerInfo.setIsDef("00");//默认版
            //查询所有工序版本的工序标识属于工序
            List<SpecVerInfo> objESpecVerInfos = specVerDAO.SelectSpecVerInfoByspecGd(objESpecInfo.getGuid());
            for (SpecVerInfo obj : objESpecVerInfos) {


                SpecVerInfo objVersion = specVerDAO.SelectSpecVerInfoByVersion(argSaveSpecInfoReqBD04.getVersion(), obj.getSpecGd());
                if (objVersion != null && !objVersion.getVersion().equals(obj.getVersion())) {
                    throw new SystemException("MG_MES4001811020007F", "新增失败，该版本已存在");
                }
                objESpecVerInfo.setVersion(argSaveSpecInfoReqBD04.getVersion());
                obj.setIsDef("01");


                if (specVerDAO.UpdateSpecVerInfo(obj) <= 0) {
                    throw new SystemException("MG_MES4001812014001F", "更新工序版本的默认版本信息失败");
                }

                SpecInfo objSpecInfo = specDAO.SelectSpecInfoByguid(obj.getSpecGd());
                objSpecInfo.setSpecName(argSaveSpecInfoReqBD04.getSpecName());
                objSpecInfo.setRemark(argSaveSpecInfoReqBD04.getRemark());
                if (specDAO.UpdateSpecInfo(objSpecInfo) <= 0) {
                    throw new SystemException("MG_MES4001812014002F", "更新工序的默认名称信息失败");
                }
            }

        } else {
            List<SpecVerInfo> objESpecVerInfos = specVerDAO.SelectSpecVerInfoByspecGd(objESpecInfo.getGuid());
            for (SpecVerInfo obj : objESpecVerInfos) {
                SpecVerInfo objVersion = specVerDAO.SelectSpecVerInfoByVersion(argSaveSpecInfoReqBD04.getVersion(), obj.getSpecGd());
                if (objVersion != null && !objVersion.getVersion().equals(obj.getVersion())) {
                    throw new SystemException("MG_MES4001811020007F", "新增失败，该版本已存在");
                }
                objESpecVerInfo.setVersion(argSaveSpecInfoReqBD04.getVersion());
            }
        }


        objESpecVerInfo.setIsDef(argSaveSpecInfoReqBD04.getIsDef());//未设置


        //作业
        OpertInfo objEOpertInfo = opertInfoMapper.GetOpertInfoByRuid(argSaveSpecInfoReqBD04.getOptRd());
        if (objEOpertInfo != null) {
            objESpecVerInfo.setOpertGd(objEOpertInfo.getGuid());
        }
        //设备组
        DevGpInfo objEDevGpInfo = devGpInfoMapper.SelectDevGpById(argSaveSpecInfoReqBD04.getDevGpRd());
        if (objEDevGpInfo != null) {
            objESpecVerInfo.setDevGrGd(objEDevGpInfo.getGuid());
        }

        DCVerInfo objEDCInfo = dcVerDAO.selectDCVerInfoByRuid(argSaveSpecInfoReqBD04.getDCVerRd());
        if (objEDCInfo != null) {
            objESpecVerInfo.setdCVerGd(objEDCInfo.getGuid());
        }

        SkillGlInfo objESkillGlInfo = skillGlDAO.SelectSkillGlInfoByRuid(argSaveSpecInfoReqBD04.getSkillGRd());
        if (objESkillGlInfo != null) {
            objESpecVerInfo.setSkillGGd(objESkillGlInfo.getGuid());
        }

        //文件组
        FileGpInfo objEFileGpInfo = fileGpDAO.SelectByruid(argSaveSpecInfoReqBD04.getFileGRd());
        if (objEFileGpInfo != null) {
            objESpecVerInfo.setFileGrGd(objEFileGpInfo.getGuid());
        }

        objESpecVerInfo.setLastModifyTime(new Date());
        objESpecVerInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        objESpecVerInfo.setCreateTime(new Date());
        objESpecVerInfo.setCreator(CommonUtils.readUser().getRealName());
        objESpecVerInfo.setRemark(argSaveSpecInfoReqBD04.getRemark());
        if (specVerDAO.InsertSpecVerInfo(objESpecVerInfo) <= 0) {
            throw new SystemException("MG_MES4001812014003F", "新增工序版本信息失败");
        }

        objESaveSpecInfoRes.setBody(objESaveSpecInfoResB);
        return objESaveSpecInfoRes;
    }

    //dto删除工序版本信息
    public SaveSpecInfoRes RmSaveSpecInfoReq05(SaveSpecInfoReqBD05 argSaveSpecInfoReqBD05) {
        SaveSpecInfoRes objESaveSpecInfoRes = new SaveSpecInfoRes();
        SaveSpecInfoResB objESaveSpecInfoResB = new SaveSpecInfoResB();
        SpecVerInfo objESpecVerInfo = specVerDAO.SelectSpecVerInfoByruid(argSaveSpecInfoReqBD05.getSpecVerRd());
        if ("00".equals(objESpecVerInfo.getIsDef())) {

            List<SpecVerInfo> objESpecVerInfos = specVerDAO.SelectSpecVerInfoByspecGd(objESpecVerInfo.getSpecGd());
            for (SpecVerInfo obj : objESpecVerInfos) {
                if (specVerDAO.DeleteSpecVerInfoByspecGd(obj.getSpecGd(), obj.getGuid()) <= 0) {
                    throw new SystemException("MG_MES4001812012001F", "删除工序信息所有的版本失败");
                }
            }

            SpecInfo objESpecInfo = specDAO.SelectSpecInfoByguid(objESpecVerInfo.getSpecGd());
            if (specDAO.DeleteSpecInfoByruid(objESpecInfo.getRuid()) <= 0) {
                throw new SystemException("MG_MES4001812011001F", "删除工序信息失败");
            }
        } else {
            if (specVerDAO.DeleteSpecVerInfo(argSaveSpecInfoReqBD05.getSpecVerRd()) <= 0) {
                throw new SystemException("MG_MES4001812015001F", "删除工序版本信息失败");
            }
        }

        objESaveSpecInfoRes.setBody(objESaveSpecInfoResB);
        return objESaveSpecInfoRes;
    }

    //获取工序版本列表信息
    public GetSVTreeInfoRes GetGetSVTreeInfoRes(GetSVTreeInfoReq00 argGetSVTreeInfoReq00) {
        GetSVTreeInfoRes objEGetSVTreeInfoRes = new GetSVTreeInfoRes();
        GetSVTreeInfoResB objEGetSVTreeInfoResB = new GetSVTreeInfoResB();
        List<GetSVTreeInfoResD> objEGetSVTreeInfoResDs = new ArrayList<GetSVTreeInfoResD>();

        SpecInfo objESpecInfo = specDAO.SelectSpecInfoByruid(argGetSVTreeInfoReq00.getSpecRd());
        if (objESpecInfo == null) {
            throw new SystemException("xxx", "查询工序信息失败");
        }
        List<SpecVerInfo> objESpecVerInfos = specVerDAO.SelectSpecVerInfoByspecGd(objESpecInfo.getGuid());
        if (objESpecVerInfos == null || objESpecVerInfos.size() <= 0) {
            throw new SystemException("xxx", "查询工序版本信息失败");
        }
        for (SpecVerInfo obj : objESpecVerInfos) {
            GetSVTreeInfoResD objEGetSVTreeInfoResD = new GetSVTreeInfoResD();
            objEGetSVTreeInfoResD.setSpecVerRd(obj.getRuid());
            objEGetSVTreeInfoResD.setVersion(obj.getVersion());
            objEGetSVTreeInfoResDs.add(objEGetSVTreeInfoResD);
        }
        objEGetSVTreeInfoResB.setData(objEGetSVTreeInfoResDs);
        objEGetSVTreeInfoRes.setBody(objEGetSVTreeInfoResB);
        return objEGetSVTreeInfoRes;
    }

}
