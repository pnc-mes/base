package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllSGInfo.GetAllSGInfoResD;
import pnc.mesadmin.dto.GetAllSpecInfo.GetAllSpecInfoResD;
import pnc.mesadmin.dto.GetAllToolInfo.GetAllToolInfoRes;
import pnc.mesadmin.dto.GetAllToolInfo.GetAllToolInfoResB;
import pnc.mesadmin.dto.GetAllToolInfo.GetAllToolInfoResD;
import pnc.mesadmin.dto.GetToolInfo.*;
import pnc.mesadmin.dto.SaveToolInfo.*;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.entity.ToolInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.ToolIService;
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
 * 子系统名称：工具信息Service层实现层
 * 创建人：郝赞
 * 创建时间：2018-06-12
 * 修改人：
 * 修改时间：
 */
@Transactional
@Service
public class ToolService implements ToolIService {
    @Resource
    private ToolCheckPlanInfoDAO toolCheckPlanInfoDAO;

    @Resource
    private FactoryDAO factoryDAO;

    @Resource
    private ToolDao toolDao;

    @Resource
    ToolFamilyDAO toolFamilyDAO;

    @Resource
    private SupplierDAO supplierDAO;

    @Resource
    private  DevSModeDAO devSModeDAO;

    @Resource
    private BaseIService baseIService;

    @Resource
    private CyclePlanDAO cyclePlanDAO;

    @Resource
    private FrePlanDAO frePlanDAO;

    @Resource
    private CheckPlanDAO checkPlanDAO;

    @Resource
    private DevFamilyCheckPlanDAO devFamilyCheckPlanDAO;

    @Resource
    private DevFamilyPMDAO devFamilyPMDAO;

    @Resource
    private ToolPMDAO toolPMDAO;


    @Resource
    private ToolBZDAO toolBZDAO; //工具 标准值
    @Resource
    private ToolBZDtllDao toolBZDtllDao;//工具 标准值明细

    @Resource
    private DoCheckInfoDAO doCheckInfoDAO;

    @Resource
    private DoPMInfoDAO doPMInfoDAO;


    //dto  获取工具列表信息
    public GetAllToolInfoRes QALLGetAllFaInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllToolInfoRes data=new GetAllToolInfoRes();
        GetAllToolInfoResB dataB=new GetAllToolInfoResB();
        List<GetAllToolInfoResD> dataDs=new ArrayList<GetAllToolInfoResD>();

        List<ToolInfo> lists= baseIService.QALLBaseInfo(ToolDao.class, "SelectAllToolInfo",
                argInitDataFields, argPageInfo);

        if(lists!=null||lists.size()>0) {

            for (ToolInfo obj : lists) {
                GetAllToolInfoResD dataD = new GetAllToolInfoResD();
                dataD.setToolRd(obj.getRuid());
                if(obj.getToolName()==null){
                    break;
                }
                dataD.setToolName(obj.getToolName());
                dataDs.add(dataD);
            }

        }
        dataB.setData(dataDs);
        data.setBody(dataB);

        return data;
    }

    @Override
    public PageResult<GetAllToolInfoResD> QALLGetAllToolsNewRes(BaseRequest req) {
        List<GetAllToolInfoResD> resDList = new ArrayList<GetAllToolInfoResD>();
        GetAllToolInfoResD resD = null;

        IPage<ToolInfo> iPage = toolDao.selectPage(new MyPage(req), CommonUtils.getQueryWrapper(req.getFields()));

        //赋值dto并返回
        for (ToolInfo obj : iPage.getRecords()) {
            resD = new GetAllToolInfoResD();
            resD.setToolRd(obj.getRuid());
            resD.setToolName(obj.getToolName());
            resDList.add(resD);
        }
        return new PageResult<>(iPage, resDList);
    }

    //dto查询工具信息 根据传过来的ToolRd
    public GetToolInfoRes GetGetToolInfoRes(GetToolInfoReqBD00 argGetToolInfoReqBD00){
        GetToolInfoRes objEGetFaInfoRes=new GetToolInfoRes();
        GetToolInfoResB objEGetFaInfoResB=new GetToolInfoResB();
        //根据页面传过来的工具id 查询工具信息
        ToolInfo objFactoryInfo=toolDao.SelectToolInfoByruid(argGetToolInfoReqBD00.getToolRd());

        if(objFactoryInfo!=null) {
            GetToolInfoResD objEGetFaInfoResD = new GetToolInfoResD();


            //工具标准值
            ToolBZInfo toolBZInfo = toolBZDAO.SelectToolBZInfo(objFactoryInfo.getGuid());
            if(toolBZInfo!=null){
                GetToolInfoResDToolBZInfo getToolInfoResDToolBZInfo = new GetToolInfoResDToolBZInfo();
                getToolInfoResDToolBZInfo.setReference(toolBZInfo.getReference());
                List<GetToolInfoResDToolBZDtl> getToolInfoResDToolBZDtl = new ArrayList<>();

                List<ToolBZDtlInfo>  toolBZDtlInfo = toolBZDtllDao.SelectToolBZDtlInfoToolBZGd(toolBZInfo.getGuid());
                if(toolBZDtlInfo!=null||toolBZDtlInfo.size()>0){
                    for (ToolBZDtlInfo objtoolBZDtlInfo:toolBZDtlInfo){
                        GetToolInfoResDToolBZDtl getToolInfoResDToolBZDtl1 = new GetToolInfoResDToolBZDtl();
                        getToolInfoResDToolBZDtl1.setKeyName(objtoolBZDtlInfo.getKeyName());
                        getToolInfoResDToolBZDtl1.setKeyValue(objtoolBZDtlInfo.getKeyValue());
                        getToolInfoResDToolBZDtl.add(getToolInfoResDToolBZDtl1);
                    }
                }
                getToolInfoResDToolBZInfo.setBZDtl(getToolInfoResDToolBZDtl);
                objEGetFaInfoResD.setToolBZInfo(getToolInfoResDToolBZInfo);
            }



            objEGetFaInfoResD.setToolRd(objFactoryInfo.getRuid());
            objEGetFaInfoResD.setToolName(objFactoryInfo.getToolName());
            objEGetFaInfoResD.setVenderSN(objFactoryInfo.getVenderSN());
            //根据工具家族ID查询工具家族信息
            ToolFamilyInfo toolFamilyInfo=null;
            if(objFactoryInfo.getToolFamilyGd()!=null){
                toolFamilyInfo= toolFamilyDAO.selectToolFamily(objFactoryInfo.getToolFamilyGd());
            }

            if(toolFamilyInfo!=null){
                GetToolInfoResDToolFamily getToolInfoResDToolFamily=new GetToolInfoResDToolFamily();

                getToolInfoResDToolFamily.setToolFamilyRd(toolFamilyInfo.getRuid());
                getToolInfoResDToolFamily.setToolFamilyName(toolFamilyInfo.getToolFamilyName());

                objEGetFaInfoResD.setToolFamily(getToolInfoResDToolFamily);

            }
            //根据供应商标识查询供应商信息
            SupplierInfo supplierInfo=null;
            if(objFactoryInfo.getSupplierGd()!=null){
                supplierInfo= supplierDAO.SelectByGuid(objFactoryInfo.getSupplierGd());
            }

            if(supplierInfo!=null){
                GetToolInfoResDSuppier getToolInfoResDSuppier=new GetToolInfoResDSuppier();
                getToolInfoResDSuppier.setSupRd(supplierInfo.getRuid());
                getToolInfoResDSuppier.setSupName(supplierInfo.getSupplierName());
                objEGetFaInfoResD.setSuppier(getToolInfoResDSuppier);
            }

            //根据工厂标识 查询工厂信息
            FactoryInfo factoryInfo= factoryDAO.SelectFactoryInfoByguid(objFactoryInfo.getFaGd());
            if(factoryInfo!=null){
                GetToolInfoResDFactory getToolInfoResDFactory=new GetToolInfoResDFactory();
                getToolInfoResDFactory.setFaRd(factoryInfo.getRuid());
                getToolInfoResDFactory.setFaName(factoryInfo.getFactoryName());
                objEGetFaInfoResD.setFactory(getToolInfoResDFactory);
            }

            //根据状态模型ID查询状态模型信息

                DevSModeInfo devSModeInfo=devSModeDAO.SelectDevSModeInfoguid(objFactoryInfo.getDevSMGd());
            if(devSModeInfo!=null){
                GetToolInfoResDModel getToolInfoResDModel=new GetToolInfoResDModel();
                getToolInfoResDModel.setDSMRd(devSModeInfo.getRuid());
                getToolInfoResDModel.setModelName(devSModeInfo.getModelName());
                objEGetFaInfoResD.setModel(getToolInfoResDModel);
            }

            //点检计划
            List<GetCheckPlanInfoDto>  getCheckPlanInfoDtos=new ArrayList<GetCheckPlanInfoDto>();
            List<ToolCheckPlanInfo> toolCheckPlanInfos=toolCheckPlanInfoDAO.SelectToolCheckPlanInfoByGd(objFactoryInfo.getGuid());
            if(toolCheckPlanInfos!=null&&toolCheckPlanInfos.size()>0){
                for(ToolCheckPlanInfo toolCheckPlanInfo:toolCheckPlanInfos){
                    CheckPlanInfo checkPlanInfo=checkPlanDAO.selectCheckPlanInfoByGuid(toolCheckPlanInfo.getCheckPlanGd());
                    if(checkPlanInfo!=null){
                    GetCheckPlanInfoDto getCheckPlanInfoDto=new GetCheckPlanInfoDto();
                    getCheckPlanInfoDto.setCheckPlanRd(checkPlanInfo.getRuid());
                    getCheckPlanInfoDto.setCheckPlanName(checkPlanInfo.getCheckPlanName());
                    getCheckPlanInfoDtos.add(getCheckPlanInfoDto);
                    }
                }
                objEGetFaInfoResD.setCheckPlanInfo(getCheckPlanInfoDtos);
            }

            //保养计划
            List<GetToolInfoResDPM>  getToolInfoResDPMS=new ArrayList<GetToolInfoResDPM>();
            List<ToolPMInfo> toolPMInfos=toolPMDAO.selectToolPMInfoByToolGd(objFactoryInfo.getGuid());
            if(toolPMInfos!=null&&toolPMInfos.size()>0){
                for(ToolPMInfo toolPMInfo:toolPMInfos){
                    GetToolInfoResDPM getToolInfoResDPM=new GetToolInfoResDPM();

                    if(toolPMInfo.getpMType().equals("00")){
                        CyclePlanInfo cyclePlanInfo =cyclePlanDAO.selectCyclePlanInfoByGuid(toolPMInfo.getpMGd());
                        if(cyclePlanInfo!=null){
                        getToolInfoResDPM.setPMType(toolPMInfo.getpMType());
                        getToolInfoResDPM.setPMName(cyclePlanInfo.getCyclePlanName());
                        getToolInfoResDPM.setPMRd(cyclePlanInfo.getRuid());
                        }
                    }
                    if(toolPMInfo.getpMType().equals("01")){
                        FrePlanInfo frePlanInfo =frePlanDAO.selectFrePlanByGuid(toolPMInfo.getpMGd());
                        if(frePlanInfo!=null){
                        getToolInfoResDPM.setPMType(toolPMInfo.getpMType());
                        getToolInfoResDPM.setPMName(frePlanInfo.getFrePlanName());
                        getToolInfoResDPM.setPMRd(frePlanInfo.getRuid());
                        }
                    }

                    getToolInfoResDPMS.add(getToolInfoResDPM);
                }
                objEGetFaInfoResD.setPMInfo(getToolInfoResDPMS);
            }

            objEGetFaInfoResD.setCreator(objFactoryInfo.getCreator());
            objEGetFaInfoResD.setCreateTime(DateUtil.format(objFactoryInfo.getCreateTime()));
            objEGetFaInfoResD.setLastModifyMan(objFactoryInfo.getLastModifyMan());
            objEGetFaInfoResD.setLastModifyTime(DateUtil.format(objFactoryInfo.getLastModifyTime()));
            objEGetFaInfoResD.setRemark(objFactoryInfo.getRemark());
            objEGetFaInfoResB.setData(objEGetFaInfoResD);
        }
        objEGetFaInfoRes.setBody(objEGetFaInfoResB);

        return objEGetFaInfoRes;
    }

    //dto. 保存工具
    public SaveToolInfoRes AddGetToolInfoRes(SaveToolInfoReqBD00 argSaveToolInfoReqBD00){

        SaveToolInfoRes objESaveFaInfoRes=new SaveToolInfoRes();
        SaveToolInfoResB objESaveFaInfoResB=new SaveToolInfoResB();
        SaveToolInfoResD objESaveFaInfoResD=new SaveToolInfoResD();

        //创建工具实体类
        ToolInfo objFactoryInfo=new ToolInfo();
        objFactoryInfo.setGuid(CommonUtils.getRandomNumber());

        if(argSaveToolInfoReqBD00.getToolName()==null||"".equals(argSaveToolInfoReqBD00.getToolName())){
            throw new SystemException("xxx","新增失败，名字不能为空");
        }


        //查询所有，判断名称是否存在
        ToolInfo objEFactoryInfo=toolDao.SelectToolInfoByFactoryName(argSaveToolInfoReqBD00.getToolName());

        if(objEFactoryInfo!=null){
            throw new SystemException("MG_MES3001112010001F","新增失败，工具名称已存在");
        }

        objFactoryInfo.setToolName(argSaveToolInfoReqBD00.getToolName());
        //根据供应商标识 查询供应商信息
        SupplierInfo supplierInfo=supplierDAO.SelectBySuppId(argSaveToolInfoReqBD00.getSupplierRd());

        if(supplierInfo!=null){
            objFactoryInfo.setSupplierGd(supplierInfo.getGuid());
        }

        //根据工具家族标识 查询工具信息
        ToolFamilyInfo toolFamilyInfo=toolFamilyDAO.selectToolFamilyID(argSaveToolInfoReqBD00.getToolFamilyRd());

        if(toolFamilyInfo!=null){
            objFactoryInfo.setToolFamilyGd(toolFamilyInfo.getGuid());
        }

        //设备标准值
        ToolBZInfo toolBZInfo = new ToolBZInfo();
        if(argSaveToolInfoReqBD00.getToolBZInfo().getReference()!=null||"".equals(argSaveToolInfoReqBD00.getToolBZInfo().getReference())){
            toolBZInfo.setGuid(CommonUtils.getRandomNumber());
            toolBZInfo.setToolGd(objFactoryInfo.getGuid());
            toolBZInfo.setReference(argSaveToolInfoReqBD00.getToolBZInfo().getReference());
            toolBZDAO.InsertToolBZInfo(toolBZInfo);
        }
        List<SaveToolInfoReqToolBZDtl> SaveToolInfoReqToolBZDtl = new ArrayList<>();
        if(argSaveToolInfoReqBD00.getToolBZInfo().getBZDtl().size()>0){
            for(SaveToolInfoReqToolBZDtl obj: argSaveToolInfoReqBD00.getToolBZInfo().getBZDtl()) {
                ToolBZDtlInfo ToolBZDtlInfo = new ToolBZDtlInfo();
                ToolBZDtlInfo.setKeyName(obj.getKeyName());
                ToolBZDtlInfo.setKeyValue(obj.getKeyValue());
                ToolBZDtlInfo.setToolBZGd(toolBZInfo.getGuid());
                toolBZDtllDao.InsertToolBZDtlInfo(ToolBZDtlInfo);
            }
        }

        //点检计划
        if(argSaveToolInfoReqBD00.getCheckPlanInfo().size()>0){
            for(GetCheckPlanInfoDto getCheckPlanInfoDto:argSaveToolInfoReqBD00.getCheckPlanInfo()){
                ToolCheckPlanInfo toolCheckPlanInfo=new ToolCheckPlanInfo();
                toolCheckPlanInfo.setToolGd(objFactoryInfo.getGuid());
                toolCheckPlanInfo.setGuid(CommonUtils.getRandomNumber());
                CheckPlanInfo checkPlanInfo=checkPlanDAO.selectCheckPlanInfoByRuid(getCheckPlanInfoDto.getCheckPlanRd());
                toolCheckPlanInfo.setCheckPlanGd(checkPlanInfo.getGuid());
                toolCheckPlanInfoDAO.AddToolCheckPlanInfo(toolCheckPlanInfo);
            }
        }


        //根据状态模型ID查询状态模型信息
        DevSModeInfo devSModeInfo=devSModeDAO.SelectDevSModeInfo(argSaveToolInfoReqBD00.getDSMRd());
        if(devSModeInfo!=null){

            objFactoryInfo.setDevSMGd(devSModeInfo.getGuid());
        }
        if(argSaveToolInfoReqBD00.getVenderSN()!=null && !argSaveToolInfoReqBD00.getVenderSN().equals("")){
            //判断序列号唯一性质
            List<ToolInfo> list=toolDao.SelectAllByVenSn(argSaveToolInfoReqBD00.getVenderSN());
            if (list.size()>0){
                throw new SystemException("MG_MES3001112010001F","新增失败，序列号出现重复！");
            }
            objFactoryInfo.setVenderSN(argSaveToolInfoReqBD00.getVenderSN());
        }

        //根据工厂标识 查询工厂信息
        FactoryInfo factoryInfo= factoryDAO.SelectFactoryInfoByruid(argSaveToolInfoReqBD00.getFaRd());
        if(factoryInfo!=null){
            objFactoryInfo.setFaGd(factoryInfo.getGuid());
        }

        objFactoryInfo.setCreator(CommonUtils.readUser().getRealName());
        objFactoryInfo.setCreateTime(new Date());
        objFactoryInfo.setRemark(argSaveToolInfoReqBD00.getRemark());

       toolDao.InsertToolInfo(objFactoryInfo);


        //设备保养
        if(argSaveToolInfoReqBD00.getPMInfo().size()>0){
            for(SaveToolInfoReqBD00PM saveToolInfoReqBD00PM:argSaveToolInfoReqBD00.getPMInfo()){
                ToolPMInfo toolPMInfo=new ToolPMInfo();
                toolPMInfo.setGuid(CommonUtils.getRandomNumber());
                toolPMInfo.setToolGd(objFactoryInfo.getGuid());
                if("00".equals(saveToolInfoReqBD00PM.getPMType())){
                    CyclePlanInfo cyclePlanInfo=cyclePlanDAO.selectCyclePlanInfoByRuid(saveToolInfoReqBD00PM.getPMRd());
                    if(cyclePlanInfo!=null){
                        toolPMInfo.setpMGd(cyclePlanInfo.getGuid());
                    }
                    toolPMInfo.setpMType(saveToolInfoReqBD00PM.getPMType());
                }
                if("01".equals(saveToolInfoReqBD00PM.getPMType())){
                    FrePlanInfo frePlanInfo=frePlanDAO.selectFrePlan(saveToolInfoReqBD00PM.getPMRd());
                    if(frePlanInfo!=null){
                        toolPMInfo.setpMGd(frePlanInfo.getGuid());
                    }
                    toolPMInfo.setpMType(saveToolInfoReqBD00PM.getPMType());
                }
                toolPMDAO.insertToolPMInfo(toolPMInfo);
            }
        }




        objESaveFaInfoResB.setData(objESaveFaInfoResD);
        objESaveFaInfoRes.setBody(objESaveFaInfoResB);

        return objESaveFaInfoRes;
    }

    //dto复制
    public SaveToolInfoRes AddSaveToolInfoRes(SaveToolInfoReqBD03 argSaveToolInfoReqBD03){
        SaveToolInfoRes objESaveFaInfoRes=new SaveToolInfoRes();
        SaveToolInfoResB objESaveFaInfoResB=new SaveToolInfoResB();
        SaveToolInfoResD objESaveFaInfoResD=new SaveToolInfoResD();

        //根据页面传过来的工具id 查询工具信息
        ToolInfo objFactoryInfo=toolDao.SelectToolInfoByruid(argSaveToolInfoReqBD03.getToolRd());
        if(objFactoryInfo==null){
            throw new SystemException("MG_MES3001112040001F","查询工具信息失败");
        }

        objFactoryInfo.setGuid(CommonUtils.getRandomNumber());
        objFactoryInfo.setCreateTime(new Date());
        objFactoryInfo.setLastModifyTime(new Date());
        objFactoryInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        objFactoryInfo.setCreator(CommonUtils.readUser().getRealName());
        toolDao.InsertToolInfo(objFactoryInfo);


        String  i=objFactoryInfo.getGuid();

        ToolInfo objEFactoryInfo=toolDao.SelectToolInfoByguid(objFactoryInfo.getGuid());
        if(objEFactoryInfo==null){
            throw new SystemException("MG_MES3001112040003F","查询工具信息失败");
        }

        objEFactoryInfo.setToolName(objEFactoryInfo.getToolName()+objEFactoryInfo.getRuid());
        if(toolDao.UpdateToolInfoByruid(objEFactoryInfo)<=0){
            throw new SystemException("MG_MES3001112040004F","更新工具名称信息失败");
        }

        objESaveFaInfoResB.setData(objESaveFaInfoResD);
        objESaveFaInfoRes.setBody(objESaveFaInfoResB);

        return objESaveFaInfoRes;
    }

    //dto更新
    public SaveToolInfoRes ModSaveToolInfoRes(SaveToolInfoReqBD02 argSaveToolInfoReqBD02) {
        SaveToolInfoRes objESaveFaInfoRes=new SaveToolInfoRes();
        SaveToolInfoResB objESaveFaInfoResB=new SaveToolInfoResB();
        SaveToolInfoResD objESaveFaInfoResD=new SaveToolInfoResD();

        //根据工具ID查询工具信息
        ToolInfo objFactoryInfo=toolDao.SelectToolInfoByruid(argSaveToolInfoReqBD02.getToolRd());
        objFactoryInfo.setRuid(argSaveToolInfoReqBD02.getToolRd());
        if(argSaveToolInfoReqBD02.getToolName()==null||"".equals(argSaveToolInfoReqBD02.getToolName())){
            throw new SystemException("xxx","更新失败，名字不能为空");
        }


        //更新的工具名称不允许重复
        ToolInfo factoryInfoname=toolDao.SelectToolInfoByFactoryName(argSaveToolInfoReqBD02.getToolName());
        if(factoryInfoname!=null&&!factoryInfoname.getToolName().equals(objFactoryInfo.getToolName())){
            throw new SystemException("MG_MES3001013030002F","更新失败，工具名称已存在");
        }
        objFactoryInfo.setToolName(argSaveToolInfoReqBD02.getToolName());
        //根据供应商标识 查询供应商信息
        SupplierInfo supplierInfo= supplierInfo=supplierDAO.SelectBySuppId(argSaveToolInfoReqBD02.getSupplierRd());


        if(supplierInfo!=null){
            objFactoryInfo.setSupplierGd(supplierInfo.getGuid());
        }else {
            objFactoryInfo.setSupplierGd("");
        }

        //根据工具家族标识 查询工具信息
        ToolFamilyInfo toolFamilyInfo=toolFamilyInfo=toolFamilyDAO.selectToolFamilyID(argSaveToolInfoReqBD02.getToolFamilyRd());

        if(toolFamilyInfo!=null){
            objFactoryInfo.setToolFamilyGd(toolFamilyInfo.getGuid());
        }else {
            objFactoryInfo.setToolFamilyGd("");
        }

        //保养计划
        List<ToolPMInfo> toolPMInfos = toolPMDAO.selectToolPMInfoByToolGd(objFactoryInfo.getGuid());
        if (toolPMInfos != null && toolPMInfos.size() > 0) {
            for (ToolPMInfo toolPMInfo : toolPMInfos) {
                if (toolPMDAO.deleteToolPMInfo(toolPMInfo.getRuid()) <= 0) {
                    throw new SystemException("xx", "删除失败");
                }
            }
        }
        if(argSaveToolInfoReqBD02.getPMInfo().size()>0){
            for(SaveToolInfoReqBD02PM SaveToolInfoReqBD02PM:argSaveToolInfoReqBD02.getPMInfo()){
                ToolPMInfo toolPMInfo=new ToolPMInfo();
                toolPMInfo.setGuid(CommonUtils.getRandomNumber());
                toolPMInfo.setToolGd(objFactoryInfo.getGuid());
                if("00".equals(SaveToolInfoReqBD02PM.getPMType())){
                    CyclePlanInfo cyclePlanInfo=cyclePlanDAO.selectCyclePlanInfoByRuid(SaveToolInfoReqBD02PM.getPMRd());
                    if(cyclePlanInfo!=null){
                        toolPMInfo.setpMGd(cyclePlanInfo.getGuid());
                    }
                    toolPMInfo.setpMType(SaveToolInfoReqBD02PM.getPMType());
                }
                if("01".equals(SaveToolInfoReqBD02PM.getPMType())){
                    FrePlanInfo frePlanInfo=frePlanDAO.selectFrePlan(SaveToolInfoReqBD02PM.getPMRd());
                    if(frePlanInfo!=null){
                        toolPMInfo.setpMGd(frePlanInfo.getGuid());
                    }
                    toolPMInfo.setpMType(SaveToolInfoReqBD02PM.getPMType());
                }
                toolPMDAO.insertToolPMInfo(toolPMInfo);
            }
        }




        //点检计划
        ToolInfo toolInfo = toolDao.SelectToolInfoByruid( argSaveToolInfoReqBD02.getToolRd());
            toolCheckPlanInfoDAO.deleteByToolCheckPlanInfoToolGd(toolInfo.getGuid());


        if(argSaveToolInfoReqBD02.getCheckPlanInfo().size()>0){
            for(GetCheckPlanInfoDto getCheckPlanInfoDto:argSaveToolInfoReqBD02.getCheckPlanInfo()){
                ToolCheckPlanInfo toolCheckPlanInfo=new ToolCheckPlanInfo();
                toolCheckPlanInfo.setToolGd(objFactoryInfo.getGuid());
                toolCheckPlanInfo.setGuid(CommonUtils.getRandomNumber());
                CheckPlanInfo checkPlanInfo=checkPlanDAO.selectCheckPlanInfoByRuid(getCheckPlanInfoDto.getCheckPlanRd());
                toolCheckPlanInfo.setCheckPlanGd(checkPlanInfo.getGuid());
                toolCheckPlanInfoDAO.AddToolCheckPlanInfo(toolCheckPlanInfo);
            }
        }


        //  设备标准值
        ToolBZInfo toolBZInfo1 = toolBZDAO.SelectToolBZInfo(objFactoryInfo.getGuid());
        if(toolBZInfo1!=null){
            toolBZDAO.DeleteAllToolBZInfoRuid(toolBZInfo1.getGuid());
            toolBZDtllDao.DeleteAllToolBZDtlInfoToolBZGd(toolBZInfo1.getGuid());
        }
        ToolBZInfo toolBZInfo = new ToolBZInfo();
        if(argSaveToolInfoReqBD02.getToolBZInfo().getReference()!=null||"".equals(argSaveToolInfoReqBD02.getToolBZInfo().getReference())){
            toolBZInfo.setGuid(CommonUtils.getRandomNumber());
            toolBZInfo.setToolGd(objFactoryInfo.getGuid());
            toolBZInfo.setReference(argSaveToolInfoReqBD02.getToolBZInfo().getReference());
            toolBZDAO.InsertToolBZInfo(toolBZInfo);
        }
        List<SaveToolInfoReqBD02ToolBZDtl> SaveToolInfoReqBD02ToolBZDtl = new ArrayList<>();
        if(argSaveToolInfoReqBD02.getToolBZInfo().getBZDtl().size()>0){
            for(SaveToolInfoReqBD02ToolBZDtl obj: argSaveToolInfoReqBD02.getToolBZInfo().getBZDtl()) {
                ToolBZDtlInfo ToolBZDtlInfo = new ToolBZDtlInfo();
                ToolBZDtlInfo.setKeyName(obj.getKeyName());
                ToolBZDtlInfo.setKeyValue(obj.getKeyValue());
                ToolBZDtlInfo.setToolBZGd(toolBZInfo.getGuid());
                toolBZDtllDao.InsertToolBZDtlInfo(ToolBZDtlInfo);
            }
        }





        //根据状态模型ID查询状态模型信息
        DevSModeInfo devSModeInfo=devSModeDAO.SelectDevSModeInfo(argSaveToolInfoReqBD02.getDSMRd());
        if(devSModeInfo!=null){
            devSModeInfo.getGuid();
            if(devSModeInfo.getGuid()!=objFactoryInfo.getDevSMGd()){
                objFactoryInfo.setDevSGd(devSModeInfo.getDSGd());
            }
            objFactoryInfo.setDevSMGd(devSModeInfo.getGuid());
        } else {
            objFactoryInfo.setDevSMGd("");
        }
        //判断序列号唯一性质
        if (argSaveToolInfoReqBD02.getVenderSN()!=null&&argSaveToolInfoReqBD02.getVenderSN()!=""){
            List<ToolInfo> list=toolDao.SelectAllByVenSn(argSaveToolInfoReqBD02.getVenderSN());
            if (list.size()>0){
                for (ToolInfo tool:list) {
                    if (tool.getRuid()!=argSaveToolInfoReqBD02.getToolRd()){
                        throw new SystemException("MG_MES3001112010001F","新增失败，序列号出现重复！");
                    }
                }
            }
        }
        objFactoryInfo.setVenderSN(argSaveToolInfoReqBD02.getVenderSN());
        //根据工厂标识 查询工厂信息
        FactoryInfo factoryInfo= factoryDAO.SelectFactoryInfoByruid(argSaveToolInfoReqBD02.getFaRd());
        if(factoryInfo!=null){
            objFactoryInfo.setFaGd(factoryInfo.getGuid());
        }else {
            objFactoryInfo.setFaGd("");
        }

        objFactoryInfo.setRemark(argSaveToolInfoReqBD02.getRemark());
        objFactoryInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        objFactoryInfo.setLastModifyTime(new Date());


        if(toolDao.UpdateToolInfoByruid(objFactoryInfo)<=0){
            throw new SystemException("MG_MES3001112030003F","更新工具信息失败");
        }
        objESaveFaInfoResB.setData(objESaveFaInfoResD);
        objESaveFaInfoRes.setBody(objESaveFaInfoResB);

        return objESaveFaInfoRes;
    }

    //dto删除
    public SaveToolInfoRes RmSaveToolInfoRes(SaveToolInfoReqBD01 argSaveToolInfoReqBD01) {
        SaveToolInfoRes objESaveFaInfoRes=new SaveToolInfoRes();
        SaveToolInfoResB objESaveFaInfoResB=new SaveToolInfoResB();
        SaveToolInfoResD objESaveFaInfoResD=new SaveToolInfoResD();

        ToolInfo toolInfo=toolDao.SelectToolInfoByruid(argSaveToolInfoReqBD01.getToolRd());
        if (toolInfo == null) {
            throw new SystemException("EEEE", "设备信息不能为空");
        }
        /*List<DoPMInfo> doPMInfos = doPMInfoDAO.SelectDoPMInfoByPMObjTypeandPMObjGdandStatus("01", toolInfo.getGuid());
        if ( doPMInfos != null && doPMInfos.size()>0) {
            throw  new SystemException("EEE","当前设备不能进行删除，因为有未处理完成的保养任务");
        }*/
        /*List<DoCheckInfo> doCheckInfos = doCheckInfoDAO.SelectDoCheckInfosByCheckObjTypeandCheckObjGdandStatus("01", toolInfo.getGuid());
        if (doCheckInfos != null && doCheckInfos.size()>0) {
            throw  new SystemException("EEE","当前设备不能进行删除，因为有未处理完成的点检任务");
        }*/

        if(toolInfo!=null){
            if(toolDao.DeleteToolInfoByruid(toolInfo.getRuid())<=0){
                throw new SystemException("MG_MES3001112020001F","删除工具信息失败");
            }
            List<ToolPMInfo> toolPMInfos = toolPMDAO.selectToolPMInfoByToolGd(toolInfo.getGuid());
            if (toolPMInfos != null && toolPMInfos.size() > 0) {
                for (ToolPMInfo toolPMInfo : toolPMInfos) {
                    if (toolPMDAO.deleteToolPMInfo(toolPMInfo.getRuid()) <= 0) {
                        throw new SystemException("xx", "删除失败");
                    }
                }
            }
            List<ToolCheckPlanInfo> toolCheckPlanInfos = toolCheckPlanInfoDAO.SelectToolCheckPlanInfoByGd(toolInfo.getGuid());
            if (toolCheckPlanInfos != null && toolCheckPlanInfos.size()>0) {
                int i =toolCheckPlanInfoDAO.deleteByToolCheckPlanInfoToolGd(toolInfo.getGuid());
                if (i <=0) {
                    throw new SystemException("xx", "删除点检计划失败");
                }
            }
        }

        objESaveFaInfoResB.setData(objESaveFaInfoResD);
        objESaveFaInfoRes.setBody(objESaveFaInfoResB);
        return objESaveFaInfoRes;
    }
}
