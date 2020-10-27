package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.BaseDto.BaseResB;
import pnc.mesadmin.dto.GetAllCsConfigDto.GetAllCsConfigRes;
import pnc.mesadmin.dto.GetAllCsConfigDto.GetCsConfigReq;
import pnc.mesadmin.dto.GetAllCsConfigDto.GetCsConfigRes;
import pnc.mesadmin.dto.GetAllCsConfigDtoV2.GetCsConfigV2Res;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.CSConfigService;
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
public class CSConfigServiceImpl implements CSConfigService {
    @Resource
    private BaseIService baseIService;
    @Resource
    private CSConfigInfoDAO csConfigDAO;
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
    @Resource
    private CSCSearchRelInfoDAO cscSearchRelInfoDAO;

    @Resource
    private CSSearchInfoDAO csSearchInfoDAO;

    @Resource
    private CSSearchFieldsInfoDAO csSearchFieldsInfoDAO;

    @Resource
    private CSSearchDDInfoDAO csSearchDDInfoDAO;

    @Resource
    private CSCTableRelInfoDAO cscTableRelInfoDAO;

    @Resource
    private CSTableDAO csTableDAO;

    @Resource
    private CSTableColumnsDAO csTableColumnsDAO;

    @Resource
    private CSTableSearchDAO csTableSearchDAO;

    @Resource
    private CSTotalDAO csTotalDAO;

    @Resource
    private CSTotalColumnsDAO csTotalColumnsDAO;

    @Resource
    private CSTotalSearchDAO csTotalSearchDAO;


    @Resource
    private CSCTotalRelInfoDAO cscTotalRelInfoDAO;


    @Override
    public BaseRes GetAllCSInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        BaseRes baseRes = new BaseRes();
        BaseResB baseResB = new BaseResB();
        List<GetAllCsConfigRes> respose = new ArrayList<>();
        List<CSConfigInfo> configInfoList = baseIService.QALLBaseInfo(CSConfigInfoDAO.class, "SelectAll",
                argInitDataFields, argPageInfo);
        if (configInfoList.size() > 0) {
            for (CSConfigInfo model : configInfoList) {
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
        GetCsConfigV2Res response = new GetCsConfigV2Res();
        CSConfigInfo csConfigInfo = csConfigDAO.SelectCsConfigByRd(request.getCSConfigRd());
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

        // 模块1-查询设置信息模块
        GetCsConfigV2Res.CSSearch csSearch = new GetCsConfigV2Res.CSSearch();
        List<GetCsConfigV2Res.Fields> fieldsList = new ArrayList<>();
        CSCSearchRelInfo cscSearchRelInfo = cscSearchRelInfoDAO.SelectcsConfigInfo(csConfigInfo.getGuid());
        if (cscSearchRelInfo != null) {
            CSSearchInfo csSearchInfo = csSearchInfoDAO.SelectCSSearchInfo(cscSearchRelInfo.getCSSearchGd());
            List<CSSearchFieldsInfo> csSearchFieldsInfoList = csSearchFieldsInfoDAO.SelectCSSearchFieldsInfo(csSearchInfo.getGuid());
            for (CSSearchFieldsInfo model : csSearchFieldsInfoList) {
                GetCsConfigV2Res.Fields fields = new GetCsConfigV2Res.Fields();
                CSSearchDDInfo csSearchDDInfo = csSearchDDInfoDAO.SelectCSSearchDDInfo(model.getGuid());
                if (csSearchDDInfo != null) {
                    GetCsConfigV2Res.DDs dDs = new GetCsConfigV2Res.DDs();
                    dDs.setDisFieldName(csSearchDDInfo.getDisFieldName());
                    dDs.setFieldRd(model.getRuid());
                    dDs.setSqlConfig(csSearchDDInfo.getSqlConfig());
                    dDs.setValFiledName(csSearchDDInfo.getValFiledName());
                    fields.setDDs(dDs);
                }
                fields.setAliasName(model.getAliasName());
                fields.setCdName(model.getCdName());
                fields.setCdType(model.getCdType());
                fields.setFieldRd(model.getRuid());
                fieldsList.add(fields);
            }
            csSearch.setFields(fieldsList);
            csSearch.setRowNum(csSearchInfo.getRowNum());
            csSearch.setColNum(csSearchInfo.getColNum());
            csSearch.setCSSearchRd(csSearchInfo.getRuid());
        }


        // 模块2-表格设置信息表
        GetCsConfigV2Res.CSTable csTable = new GetCsConfigV2Res.CSTable();
        List<GetCsConfigV2Res.Columns> columnsList = new ArrayList<>();
        List<GetCsConfigV2Res.SearchFields> searchFieldsList = new ArrayList<>();

        CSCTableRelInfo cscTableRelInfo = cscTableRelInfoDAO.SelectcscTableRelInfo(csConfigInfo.getGuid());
        if (cscTableRelInfo != null) {
            CSTableInfo csTableInfo = csTableDAO.SelectCSTableInfo(cscTableRelInfo.getCSTableGd());
            if (csTableInfo != null) {
                List<CSTableColumnsInfo> csTableColumnsInfoList = csTableColumnsDAO.SelectCSTableColumnsInfo(csTableInfo.getGuid());
                for (CSTableColumnsInfo model : csTableColumnsInfoList) {
                    GetCsConfigV2Res.Columns columns = new GetCsConfigV2Res.Columns();
                    columns.setAliasName(model.getAliasName());
                    columns.setColumnName(model.getColumnName());
                    columns.setColumnRd(model.getRuid());
                    columns.setColumnType(model.getColumnType());
                    columns.setColumnWidth(model.getColumnWidth());
                    columns.setIsDisplay(model.getIsDisplay());
                    columns.setTruncated(model.getTruncated());
                    columnsList.add(columns);
                }
                List<CSTableSearchInfo> csTableSearchInfoList = csTableSearchDAO.SelectCSTableSearchInfo(csTableInfo.getGuid());
                for (CSTableSearchInfo model : csTableSearchInfoList) {
                    GetCsConfigV2Res.SearchFields searchFields = new GetCsConfigV2Res.SearchFields();
                    searchFields.setAliasName(model.getAliasName());
                    searchFields.setCdName(model.getCdName());
                    searchFields.setFieldRd(model.getRuid());
                    searchFieldsList.add(searchFields);
                }

                csTable.setCSTableRd(csTableInfo.getRuid());
                csTable.setSqlConfig(csTableInfo.getSqlConfig());
                csTable.setColumns(columnsList);
                csTable.setSearchFields(searchFieldsList);
            }
        }


        //模块3-统计设置信息
        List<GetCsConfigV2Res.CSTotal> csTotalList3 = new ArrayList<>();
        List<CSCTotalRelInfo> cscTotalRelInfoList = cscTotalRelInfoDAO.SelectCSCTotalRelInfo(csConfigInfo.getGuid());

        for (CSCTotalRelInfo cscModel : cscTotalRelInfoList) {
            GetCsConfigV2Res.CSTotal csTotal = new GetCsConfigV2Res.CSTotal();
            List<GetCsConfigV2Res.Columns> columnsList3 = new ArrayList<>();
            List<GetCsConfigV2Res.SearchFields> searchFieldsList3 = new ArrayList<>();
            CSTotalInfo csTotalInfo = csTotalDAO.SelectCSTotalInfo(cscModel.getCSTotalGd());

            List<CSTotalColumnsInfo> csTotalColumnsInfoList = csTotalColumnsDAO.SelectAllByCSTotalGd(csTotalInfo.getGuid());
            for (CSTotalColumnsInfo model : csTotalColumnsInfoList) {
                GetCsConfigV2Res.Columns columns = new GetCsConfigV2Res.Columns();
                columns.setColumnRd(model.getRuid());
                columns.setColumnName(model.getColumnName());
                columns.setOutPutText(model.getOutPutText());
                columnsList3.add(columns);
            }

            List<CSTotalSearchInfo> csTotalSearchInfoList = csTotalSearchDAO.SelectCSTotalSearchInfo(csTotalInfo.getGuid());
            for (CSTotalSearchInfo model : csTotalSearchInfoList) {
                GetCsConfigV2Res.SearchFields searchFields = new GetCsConfigV2Res.SearchFields();
                searchFields.setFieldRd(model.getRuid());
                searchFields.setCdName(model.getCdName());
                searchFields.setAliasName(model.getAliasName());
                searchFieldsList3.add(searchFields);
            }
            csTotal.setCSTotalRd(csTotalInfo.getRuid());
            csTotal.setConfigName(csTotalInfo.getConfigName());
            csTotal.setSqlConfig(csTotalInfo.getSqlConfig());
            csTotal.setColumns(columnsList3);
            csTotal.setSearchFields(searchFieldsList3);
            csTotalList3.add(csTotal);
        }
        response.setCSSearch(csSearch);
        response.setCSTable(csTable);
        response.setCSTotal(csTotalList3);

        baseResB.setMsgCode("0x00000");
        baseResB.setMsgDes("成功");
        baseResB.setData(response);
        baseRes.setBody(baseResB);
        return baseRes;
    }

    @Override
    public BaseRes AddSaveCSInfo00(GetCsConfigV2Res req) {
        BaseRes baseRes = new BaseRes();
        BaseResB baseResB = new BaseResB();
        if (req.getConfigName() == null || req.getConfigName().equals("")) {
            throw new SystemException("EEEE", "SQL配置名称不允许为空！");
        }
        CSConfigInfo csConfigInfoCName = csConfigDAO.SelectCsconfigByName(req.getConfigName());
        if (csConfigInfoCName != null) {
            throw new SystemException("EEEE", "SQL配置名称" + req.getConfigName() + "出现重复！");
        }
        CSConfigInfo csConfigInfo = new CSConfigInfo();
        csConfigInfo.setGuid(CommonUtils.getRandomNumber());
        csConfigInfo.setCreator(CommonUtils.readUser().getRealName());
        csConfigInfo.setCreateTime(new Date());
        csConfigInfo.setRemark(req.getRemark());
        csConfigInfo.setConfigName(req.getConfigName());
        csConfigDAO.InsertCSConfigInfo(csConfigInfo);
        InsertConfigBase(csConfigInfo, req);
        baseResB.setMsgCode("0x00000");
        baseResB.setMsgDes("成功");
        baseRes.setBody(baseResB);
        return baseRes;
    }

    @Override
    public BaseRes AddSaveCSInfo01(GetCsConfigReq req) {
        BaseRes baseRes = new BaseRes();
        BaseResB baseResB = new BaseResB();

        CSConfigInfo csConfigInfo = csConfigDAO.SelectCsConfigByRd(req.getCSConfigRd());
        if (csConfigInfo == null) {
            throw new SystemException("EEEE", "通用查询为空！");
        }
        //删除操作 1 删除查询设置
        cscSearchRelInfoDAO.DeleteAllByConfigGd(csConfigInfo.getGuid());
        //删除操作 2 删除表格设置
        cscTableRelInfoDAO.DeleteAllByConfigGd(csConfigInfo.getGuid());
        //删除操作 3 删除统计设置
        cscTotalRelInfoDAO.DeleteAllByConfigGd(csConfigInfo.getGuid());
        //删除操作 4 删除发布菜单
        if (csConfigInfo.getResGd() != null && !csConfigInfo.getResGd().equals("")) {
            resDAO.DeleteResByGuid(csConfigInfo.getResGd());
            resOptDAO.DeleteResOptByResGd(csConfigInfo.getResGd());
        }
        //删除操作 5 删除主表
        csConfigDAO.DelCsconfigByGuid(csConfigInfo.getGuid());

        baseResB.setMsgCode("0x00000");
        baseResB.setMsgDes("成功");
        baseRes.setBody(baseResB);
        return baseRes;
    }

    @Override
    public BaseRes AddSaveCSInfo02(GetCsConfigV2Res req) {
        BaseRes baseRes = new BaseRes();
        BaseResB baseResB = new BaseResB();
        if (req.getConfigName() == null || req.getConfigName().equals("")) {
            throw new SystemException("EEEE", "SQL配置名称不允许为空！");
        }
        CSConfigInfo csConfigInfoCNameJ = csConfigDAO.SelectCsconfigByName(req.getConfigName());
        if (csConfigInfoCNameJ != null && req.getCSConfigRd() != csConfigInfoCNameJ.getRuid()) {
            throw new SystemException("EEEE", "SQL配置名称" + req.getConfigName() + "出现重复！");
        }
        CSConfigInfo csConfigInfoCName = csConfigDAO.SelectCsConfigByRd(req.getCSConfigRd());
        if (csConfigInfoCName == null) {
            throw new SystemException("EEEE", "SQL配置查询为空！");
        }
        CSConfigInfo csConfigInfo = new CSConfigInfo();
        csConfigInfo.setRuid(req.getCSConfigRd());
        csConfigInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        csConfigInfo.setLastModifyTime(new Date());
        csConfigInfo.setRemark(req.getRemark());
        csConfigInfo.setConfigName(req.getConfigName());
        csConfigDAO.UpdateCsconfig(csConfigInfo);

        //删除操作 1 删除查询设置
        cscSearchRelInfoDAO.DeleteAllByConfigGd(csConfigInfoCName.getGuid());
        //删除操作 2 删除表格设置
        cscTableRelInfoDAO.DeleteAllByConfigGd(csConfigInfoCName.getGuid());
        //删除操作 3 删除
        cscTotalRelInfoDAO.DeleteAllByConfigGd(csConfigInfoCName.getGuid());
        InsertConfigBase(csConfigInfoCName, req);

        baseResB.setMsgCode("0x00000");
        baseResB.setMsgDes("成功");
        baseRes.setBody(baseResB);
        return baseRes;
    }

    public void InsertConfigBase(CSConfigInfo csConfigInfo, GetCsConfigV2Res req) {
        //模块一 查询设置
        if (req.getCSSearch() != null) {
            //查询设置主表
            CSSearchInfo csSearchInfo = new CSSearchInfo();
            csSearchInfo.setColNum(req.getCSSearch().getColNum());
            csSearchInfo.setRowNum(req.getCSSearch().getRowNum());
            csSearchInfo.setCreateTime(new Date());
            csSearchInfo.setCreator(CommonUtils.readUser().getRealName());
            csSearchInfo.setGuid(CommonUtils.getRandomNumber());
            csSearchInfoDAO.InsertCSSearchInfo(csSearchInfo);
            //查询设置与配置主表关联表
            CSCSearchRelInfo cscSearchRelInfo = new CSCSearchRelInfo();
            cscSearchRelInfo.setCreateTime(new Date());
            cscSearchRelInfo.setCreator(CommonUtils.readUser().getRealName());
            cscSearchRelInfo.setCSConfigGd(csConfigInfo.getGuid());
            cscSearchRelInfo.setCSSearchGd(csSearchInfo.getGuid());
            cscSearchRelInfoDAO.InsertCSCSearchRelInfo(cscSearchRelInfo);
            if (req.getCSSearch().getFields() != null) {
                for (GetCsConfigV2Res.Fields model : req.getCSSearch().getFields()) {
                    CSSearchFieldsInfo csSearchFieldsInfo = new CSSearchFieldsInfo();
                    csSearchFieldsInfo.setAliasName(model.getAliasName());
                    csSearchFieldsInfo.setCdName(model.getCdName());
                    csSearchFieldsInfo.setCdType(model.getCdType());
                    csSearchFieldsInfo.setCreateTime(new Date());
                    csSearchFieldsInfo.setCreator(CommonUtils.readUser().getRealName());
                    csSearchFieldsInfo.setGuid(CommonUtils.getRandomNumber());
                    csSearchFieldsInfo.setCSSearchGd(csSearchInfo.getGuid());
                    csSearchFieldsInfoDAO.InsertCSSearchFieldsInfo(csSearchFieldsInfo);
                    if (model.getDDs() != null) {
                        CSSearchDDInfo csSearchDDInfo = new CSSearchDDInfo();
                        csSearchDDInfo.setCSSearchFieldGd(csSearchFieldsInfo.getGuid());
                        csSearchDDInfo.setDisFieldName(model.getDDs().getDisFieldName());
                        csSearchDDInfo.setSqlConfig(model.getDDs().getSqlConfig());
                        csSearchDDInfo.setValFiledName(model.getDDs().getValFiledName());
                        csSearchDDInfo.setCreateTime(new Date());
                        csSearchDDInfo.setCreator(CommonUtils.readUser().getRealName());
                        csSearchDDInfo.setGuid(CommonUtils.getRandomNumber());
                        csSearchDDInfoDAO.InsertCSSearchDDInfo(csSearchDDInfo);
                    }
                }
            }
        }
        //模块二 表格设置
        if (req.getCSTable() != null) {
            CSTableInfo csTableInfo = new CSTableInfo();
            csTableInfo.setGuid(CommonUtils.getRandomNumber());
            csTableInfo.setCreator(CommonUtils.readUser().getRealName());
            csTableInfo.setCreateTime(new Date());
            csTableInfo.setSqlConfig(req.getCSTable().getSqlConfig());
            csTableDAO.InsertCSTableInfo(csTableInfo);
            CSCTableRelInfo cscTableRelInfo = new CSCTableRelInfo();
            cscTableRelInfo.setCreator(CommonUtils.readUser().getRealName());
            cscTableRelInfo.setCreateTime(new Date());
            cscTableRelInfo.setCSConfigGd(csConfigInfo.getGuid());
            cscTableRelInfo.setCSTableGd(csTableInfo.getGuid());
            cscTableRelInfoDAO.InsertCSCTableRelInfo(cscTableRelInfo);
            if (req.getCSTable().getColumns() != null) {
                for (GetCsConfigV2Res.Columns model : req.getCSTable().getColumns()) {
                    CSTableColumnsInfo csTableColumnsInfo = new CSTableColumnsInfo();
                    csTableColumnsInfo.setAliasName(model.getAliasName());
                    csTableColumnsInfo.setColumnName(model.getColumnName());
                    csTableColumnsInfo.setColumnType(model.getColumnType());
                    csTableColumnsInfo.setColumnWidth(model.getColumnWidth());
                    csTableColumnsInfo.setIsDisplay(model.getIsDisplay());
                    csTableColumnsInfo.setTruncated(model.getTruncated());
                    csTableColumnsInfo.setCreator(CommonUtils.readUser().getRealName());
                    csTableColumnsInfo.setCreateTime(new Date());
                    csTableColumnsInfo.setGuid(CommonUtils.getRandomNumber());
                    csTableColumnsInfo.setCSTableGd(csTableInfo.getGuid());
                    csTableColumnsDAO.InsertCSTableColumnsInfo(csTableColumnsInfo);
                }
            }
            if (req.getCSTable().getSearchFields() != null) {
                for (GetCsConfigV2Res.SearchFields model : req.getCSTable().getSearchFields()) {
                    CSTableSearchInfo csTableSearchInfo = new CSTableSearchInfo();
                    csTableSearchInfo.setAliasName(model.getAliasName());
                    csTableSearchInfo.setCdName(model.getCdName());
                    csTableSearchInfo.setCSTableGd(csTableInfo.getGuid());
                    csTableSearchInfo.setCreator(CommonUtils.readUser().getRealName());
                    csTableSearchInfo.setCreateTime(new Date());
                    csTableSearchInfo.setGuid(CommonUtils.getRandomNumber());
                    csTableSearchDAO.InsertCSTableSearchInfo(csTableSearchInfo);
                }
            }
        }

        //模块三——统计设置
        if (req.getCSTotal() != null) {
            for (GetCsConfigV2Res.CSTotal model : req.getCSTotal()) {
                CSTotalInfo csTotalInfo = new CSTotalInfo();
                csTotalInfo.setConfigName(model.getConfigName());
                csTotalInfo.setSqlConfig(model.getSqlConfig());
                csTotalInfo.setCreateTime(new Date());
                csTotalInfo.setCreator(CommonUtils.readUser().getRealName());
                csTotalInfo.setGuid(CommonUtils.getRandomNumber());
                csTotalDAO.InsertCSTotalInfo(csTotalInfo);
                CSCTotalRelInfo cscTotalRelInfo = new CSCTotalRelInfo();
                cscTotalRelInfo.setCSConfigGd(csConfigInfo.getGuid());
                cscTotalRelInfo.setCSTotalGd(csTotalInfo.getGuid());
                cscTotalRelInfo.setCreator(CommonUtils.readUser().getRealName());
                cscTotalRelInfo.setCreateTime(new Date());
                cscTotalRelInfoDAO.InsertCSCTotalRelInfo(cscTotalRelInfo);
                if (model.getColumns() != null) {
                    for (GetCsConfigV2Res.Columns cmodel : model.getColumns()) {
                        CSTotalColumnsInfo csTotalColumnsInfo = new CSTotalColumnsInfo();
                        csTotalColumnsInfo.setColumnName(cmodel.getColumnName());
                        csTotalColumnsInfo.setOutPutText(cmodel.getOutPutText());
                        csTotalColumnsInfo.setCreateTime(new Date());
                        csTotalColumnsInfo.setCreator(CommonUtils.readUser().getRealName());
                        csTotalColumnsInfo.setCSTotalGd(csTotalInfo.getGuid());
                        csTotalColumnsInfo.setGuid(CommonUtils.getRandomNumber());
                        csTotalColumnsDAO.InsertCSTotalColumnsInfo(csTotalColumnsInfo);
                    }
                }
                if (model.getSearchFields() != null) {
                    for (GetCsConfigV2Res.SearchFields searModel : model.getSearchFields()) {
                        CSTotalSearchInfo csTotalSearchInfo = new CSTotalSearchInfo();
                        csTotalSearchInfo.setAliasName(searModel.getAliasName());
                        csTotalSearchInfo.setCdName(searModel.getCdName());
                        csTotalSearchInfo.setCdName(searModel.getCdName());
                        csTotalSearchInfo.setCSTotalGd(csTotalInfo.getGuid());
                        csTotalSearchInfo.setCreateTime(new Date());
                        csTotalSearchInfo.setCreator(CommonUtils.readUser().getRealName());
                        csTotalSearchInfo.setGuid(CommonUtils.getRandomNumber());
                        csTotalSearchDAO.InsertCSTotalSearchInfo(csTotalSearchInfo);
                    }
                }
            }
        }
    }

    @Override
    public BaseRes AddSaveCSInfo03(GetCsConfigRes req) {
        BaseRes baseRes = new BaseRes();
        BaseResB baseResB = new BaseResB();
        CSConfigInfo csConfigInfo = csConfigDAO.SelectCsConfigByRd(req.getCSConfigRd());
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
        csConfigInfo = new CSConfigInfo();
        csConfigInfo.setRuid(req.getCSConfigRd());
        csConfigInfo.setResGd(resInfo.getGuid());
        //csConfigDAO.UpdateCsconfig(csConfigInfo);
        baseResB.setMsgCode("0x00000");
        baseResB.setMsgDes("成功");
        baseRes.setBody(baseResB);
        return baseRes;
    }
}
