package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.BaseDto.BaseResB;
import pnc.mesadmin.dto.GetAllCsConfigDto.GetAllCsConfigRes;
import pnc.mesadmin.dto.GetAllCsConfigDto.GetCsConfigReq;
import pnc.mesadmin.dto.GetAllCsConfigDto.GetCsConfigRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.CSConfigV1Service;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2018-11-19
 **/
@Transactional
@Service
public class CSConfigV1ServiceImpl implements CSConfigV1Service {
    @Resource
    private BaseIService baseIService;
    @Resource
    private CSConfigDAOV1 csConfigDAO;
    @Resource
    private CSConfigCdDAO csConfigCdDAO;
    @Resource
    private CSConfigColumnDAO csConfigColumnDAO;
    @Resource
    private ResDAO resDAO;
    @Resource
    private ModuleDAO moduleDAO;
    @Resource
    private ResOptDAO resOptDAO;


    @Override
    public BaseRes GetAllCSInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        BaseRes baseRes = new BaseRes();
        BaseResB baseResB = new BaseResB();
        List<GetAllCsConfigRes> respose = new ArrayList<>();
        List<CSConfigInfoV1> configInfoList = baseIService.QALLBaseInfo(CSConfigDAOV1.class, "SelectCSConfigAll",
                argInitDataFields, argPageInfo);
        if (configInfoList.size() > 0) {
            for (CSConfigInfoV1 model : configInfoList) {
                GetAllCsConfigRes getAllCsConfigRes = new GetAllCsConfigRes();
                getAllCsConfigRes.setCSConfigRd(model.getRuid());
                getAllCsConfigRes.setConfigName(model.getConfigName());
                respose.add(getAllCsConfigRes);
            }
        }
        baseResB.setData(respose);
        baseResB.setMsgCode("0x00000");
        baseResB.setMsgDes("成功");
        baseRes.setBody(baseResB);
        return baseRes;

    }

    @Override
    public BaseRes GetCSInfo(GetCsConfigReq request) {
        BaseRes baseRes = new BaseRes();
        BaseResB baseResB = new BaseResB();
        GetCsConfigRes response = new GetCsConfigRes();
        CSConfigInfoV1 csConfigInfo = csConfigDAO.SelectCsConfigByRd(request.getCSConfigRd());
        if (csConfigInfo == null) {
            throw new SystemException("EEEE", "获取通用查询为空！");
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        response.setConfigName(csConfigInfo.getConfigName());
        response.setCreateTime(format.format(csConfigInfo.getCreateTime()));
        response.setCreator(csConfigInfo.getCreator());
        response.setCSConfigRd(csConfigInfo.getRuid());
        response.setLastModifyMan(csConfigInfo.getLastModifyMan());
        if (csConfigInfo.getLastModifyTime() != null) {
            response.setLastModifyTime(format.format(csConfigInfo.getLastModifyTime()));
        }
        response.setRemark(csConfigInfo.getRemark());
        response.setSqlConfig(csConfigInfo.getSqlConfig());

        List<CSConfigColumnInfo> csConfigColumnInfoList = csConfigColumnDAO.SelectAllByConfigGD(csConfigInfo.getGuid());
        List<GetCsConfigRes.ColumnsInfo> columnsInfoList = new ArrayList<>();
        if (csConfigColumnInfoList.size() > 0) {
            for (CSConfigColumnInfo ccModel : csConfigColumnInfoList) {
                GetCsConfigRes.ColumnsInfo columnsInfo = new GetCsConfigRes.ColumnsInfo();
                columnsInfo.setAliasName(ccModel.getAliasName());
                columnsInfo.setColumnName(ccModel.getColumnName());
                columnsInfo.setColumnRd(ccModel.getRuid());
                columnsInfoList.add(columnsInfo);
            }
        }

        List<CSConfigCdInfo> csConfigCdInfoList = csConfigCdDAO.SelectAllByConfigGD(csConfigInfo.getGuid());
        List<GetCsConfigRes.CdInfo> cdInfoList = new ArrayList<>();
        if (csConfigCdInfoList.size() > 0) {
            for (CSConfigCdInfo ccdModel : csConfigCdInfoList) {
                GetCsConfigRes.CdInfo cdInfo = new GetCsConfigRes.CdInfo();
                cdInfo.setAliasName(ccdModel.getAliasName());
                cdInfo.setCdName(ccdModel.getCdName());
                cdInfo.setCdRd(ccdModel.getRuid());
                cdInfoList.add(cdInfo);
            }
        }
        response.setCdInfo(cdInfoList);
        response.setColumnsInfo(columnsInfoList);

        baseResB.setMsgCode("0x00000");
        baseResB.setMsgDes("成功");
        baseResB.setData(response);
        baseRes.setBody(baseResB);
        return baseRes;
    }

    @Override
    public BaseRes AddSaveCSInfo00(GetCsConfigRes req) {
        BaseRes baseRes = new BaseRes();
        BaseResB baseResB = new BaseResB();
        if (req.getConfigName() == null || req.getConfigName().equals("")) {
            throw new SystemException("EEEE", "SQL配置名称不允许为空！");
        }
        CSConfigInfoV1 csConfigInfoCName = csConfigDAO.SelectCsconfigByName(req.getConfigName());
        if (csConfigInfoCName != null) {
            throw new SystemException("EEEE", "SQL配置名称" + req.getConfigName() + "出现重复！");
        }
        CSConfigInfoV1 csConfigInfo = new CSConfigInfoV1();
        csConfigInfo.setGuid(CommonUtils.getRandomNumber());
        csConfigInfo.setCreator(CommonUtils.readUser().getRealName());
        csConfigInfo.setCreateTime(new Date());
        csConfigInfo.setRemark(req.getRemark());
        csConfigInfo.setConfigName(req.getConfigName());
        csConfigInfo.setSqlConfig(req.getSqlConfig());
        csConfigDAO.InsertCsconfig(csConfigInfo);
        if (req.getColumnsInfo() != null && req.getColumnsInfo().size() > 0) {
            for (GetCsConfigRes.ColumnsInfo cModel : req.getColumnsInfo()) {
                CSConfigColumnInfo csConfigColumnInfo = new CSConfigColumnInfo();
                csConfigColumnInfo.setGuid(CommonUtils.getRandomNumber());
                csConfigColumnInfo.setAliasName(cModel.getAliasName());
                csConfigColumnInfo.setColumnName(cModel.getColumnName());
                csConfigColumnInfo.setCSConfigGd(csConfigInfo.getGuid());
                csConfigColumnInfo.setCreateTime(new Date());
                csConfigColumnInfo.setCreator(CommonUtils.readUser().getRealName());
                csConfigColumnInfo.setRemark(req.getRemark());
                csConfigColumnDAO.InsertCSConfigColumnInfo(csConfigColumnInfo);
            }
        }

        if (req.getCdInfo() != null && req.getCdInfo().size() > 0) {
            for (GetCsConfigRes.CdInfo cdModel : req.getCdInfo()) {
                CSConfigCdInfo csConfigCdInfo = new CSConfigCdInfo();
                csConfigCdInfo.setAliasName(cdModel.getAliasName());
                csConfigCdInfo.setCdName(cdModel.getCdName());
                csConfigCdInfo.setCreateTime(new Date());
                csConfigCdInfo.setCreator(CommonUtils.readUser().getRealName());
                csConfigCdInfo.setGuid(CommonUtils.getRandomNumber());
                csConfigCdInfo.setCSConfigGd(csConfigInfo.getGuid());
                csConfigCdInfo.setRemark(req.getRemark());
                csConfigCdDAO.InsertCSConfigCdInfo(csConfigCdInfo);
            }
        }
        baseResB.setMsgCode("0x00000");
        baseResB.setMsgDes("成功");
        baseRes.setBody(baseResB);
        return baseRes;
    }

    @Override
    public BaseRes AddSaveCSInfo01(GetCsConfigReq req) {
        BaseRes baseRes = new BaseRes();
        BaseResB baseResB = new BaseResB();

        CSConfigInfoV1 csConfigInfo = csConfigDAO.SelectCsConfigByRd(req.getCSConfigRd());
        if (csConfigInfo == null) {
            throw new SystemException("EEEE", "通用查询为空！");
        }
        csConfigCdDAO.DelectCsConfigCdInfoByConfigGuid(csConfigInfo.getGuid());
        csConfigColumnDAO.DeleteCsConfigColumnInfoByConfigGuid(csConfigInfo.getGuid());
        if (csConfigInfo.getResGd() != null && !csConfigInfo.getResGd().equals("")) {
            resDAO.DeleteResByGuid(csConfigInfo.getResGd());
            resOptDAO.DeleteResOptByResGd(csConfigInfo.getResGd());
        }
        csConfigDAO.DelCsconfigByGuid(csConfigInfo.getGuid());

        baseResB.setMsgCode("0x00000");
        baseResB.setMsgDes("成功");
        baseRes.setBody(baseResB);
        return baseRes;
    }

    @Override
    public BaseRes AddSaveCSInfo02(GetCsConfigRes req) {
        BaseRes baseRes = new BaseRes();
        BaseResB baseResB = new BaseResB();
        if (req.getConfigName() == null || req.getConfigName().equals("")) {
            throw new SystemException("EEEE", "SQL配置名称不允许为空！");
        }
        CSConfigInfoV1 csConfigInfoCNameJ = csConfigDAO.SelectCsconfigByName(req.getConfigName());
        if (csConfigInfoCNameJ != null && req.getCSConfigRd() != csConfigInfoCNameJ.getRuid()) {
            throw new SystemException("EEEE", "SQL配置名称" + req.getConfigName() + "出现重复！");
        }
        CSConfigInfoV1 csConfigInfoCName = csConfigDAO.SelectCsConfigByRd(req.getCSConfigRd());
        CSConfigInfoV1 csConfigInfo = new CSConfigInfoV1();
        csConfigInfo.setRuid(req.getCSConfigRd());
        csConfigInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        csConfigInfo.setLastModifyTime(new Date());
        csConfigInfo.setRemark(req.getRemark());
        csConfigInfo.setConfigName(req.getConfigName());
        csConfigInfo.setSqlConfig(req.getSqlConfig());
        csConfigDAO.UpdateCsconfig(csConfigInfo);
        csConfigCdDAO.DelectCsConfigCdInfoByConfigGuid(csConfigInfoCName.getGuid());
        csConfigColumnDAO.DeleteCsConfigColumnInfoByConfigGuid(csConfigInfoCName.getGuid());
        if (req.getColumnsInfo() != null && req.getColumnsInfo().size() > 0) {
            for (GetCsConfigRes.ColumnsInfo cModel : req.getColumnsInfo()) {
                CSConfigColumnInfo csConfigColumnInfo = new CSConfigColumnInfo();
                csConfigColumnInfo.setGuid(CommonUtils.getRandomNumber());
                csConfigColumnInfo.setAliasName(cModel.getAliasName());
                csConfigColumnInfo.setColumnName(cModel.getColumnName());
                csConfigColumnInfo.setCSConfigGd(csConfigInfoCName.getGuid());
                csConfigColumnInfo.setCreateTime(new Date());
                csConfigColumnInfo.setCreator(CommonUtils.readUser().getRealName());
                csConfigColumnInfo.setRemark(req.getRemark());
                csConfigColumnDAO.InsertCSConfigColumnInfo(csConfigColumnInfo);
            }
        }

        if (req.getCdInfo() != null && req.getCdInfo().size() > 0) {
            for (GetCsConfigRes.CdInfo cdModel : req.getCdInfo()) {
                CSConfigCdInfo csConfigCdInfo = new CSConfigCdInfo();
                csConfigCdInfo.setAliasName(cdModel.getAliasName());
                csConfigCdInfo.setCdName(cdModel.getCdName());
                csConfigCdInfo.setCreateTime(new Date());
                csConfigCdInfo.setCreator(CommonUtils.readUser().getRealName());
                csConfigCdInfo.setGuid(CommonUtils.getRandomNumber());
                csConfigCdInfo.setCSConfigGd(csConfigInfoCName.getGuid());
                csConfigCdInfo.setRemark(req.getRemark());
                csConfigCdDAO.InsertCSConfigCdInfo(csConfigCdInfo);
            }
        }
        baseResB.setMsgCode("0x00000");
        baseResB.setMsgDes("成功");
        baseRes.setBody(baseResB);
        return baseRes;
    }

    @Override
    public BaseRes AddSaveCSInfo03(GetCsConfigRes req) {
        BaseRes baseRes = new BaseRes();
        BaseResB baseResB = new BaseResB();
        CSConfigInfoV1 csConfigInfo = csConfigDAO.SelectCsConfigByRd(req.getCSConfigRd());
        if (csConfigInfo == null) {
            throw new SystemException("EEEE", "获取通用查询为空！");
        }
        ModuleInfo moduleInfo = moduleDAO.SelectModuleInfoRuid(req.getModuleRd());
        if (moduleInfo == null) {
            throw new SystemException("EEEE", "请勾选菜单配置！");
        }
        if (csConfigInfo.getResGd() != null && !csConfigInfo.getResGd().equals("")) {
            resDAO.DeleteResByGuid(csConfigInfo.getResGd());
            resOptDAO.DeleteResOptByResGd(csConfigInfo.getResGd());
        }

        //新增菜单下级
        ResInfo resInfo = new ResInfo();
        resInfo.setGuid(CommonUtils.getRandomNumber());
        resInfo.setModuleGd(moduleInfo.getGuid());
        resInfo.setModuleName(moduleInfo.getModuleName());
        resInfo.setResName(req.getResName());
        resInfo.setResUrl("/Sys/tycxzxPage?id=" + req.getCSConfigRd());
        resInfo.setLinkType("00");
        List<ModuleInfo> moduleInfoList = moduleDAO.SelectAllChildModuleAdmin(moduleInfo.getParentGd());
        if (moduleInfoList.size() > 0) {
            resInfo.setPos(moduleInfoList.get(moduleInfoList.size() - 1).getPos() + 1);
        } else {
            resInfo.setPos(1);
        }
        resInfo.setIsClose("01");
        resInfo.setCreator("System");
        resInfo.setCreateTime(new Date());
        resDAO.InsertRes(resInfo);

        //新增默认查询
        ResOptInfo resOptInfo = new ResOptInfo();
        resOptInfo.setGuid(CommonUtils.getRandomNumber());
        resOptInfo.setResGd(resInfo.getGuid());
        resOptInfo.setOptGd("55");
        resOptInfo.setOptUrl(resInfo.getResUrl());
        resOptInfo.setOptName("查询");
        resOptInfo.setCreator("System");
        resOptInfo.setCreateTime(new Date());
        resOptDAO.InsertResOpt(resOptInfo);
        //更新通用查询
        csConfigInfo = new CSConfigInfoV1();
        csConfigInfo.setRuid(req.getCSConfigRd());
        csConfigInfo.setResGd(resInfo.getGuid());
        csConfigDAO.UpdateCsconfig(csConfigInfo);
        baseResB.setMsgCode("0x00000");
        baseResB.setMsgDes("成功");
        baseRes.setBody(baseResB);
        return baseRes;
    }
}
