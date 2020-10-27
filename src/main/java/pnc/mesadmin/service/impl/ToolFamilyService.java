package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllSGInfo.GetAllSGInfoResD;
import pnc.mesadmin.dto.GetAllToolFamilyInfo.GetAllToolFamilyInfoRes;
import pnc.mesadmin.dto.GetAllToolFamilyInfo.GetAllToolFamilyInfoResB;
import pnc.mesadmin.dto.GetAllToolFamilyInfo.GetAllToolFamilyInfoResD;
import pnc.mesadmin.dto.GetToolFamilyInfo.*;
import pnc.mesadmin.dto.SaveToolFamilyInfo.*;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.ToolFamilyIService;
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
 * 子系统名称：工具家族信息Service层
 * 创建人：郝赞
 * 创建时间：2018-6-15
 * 修改人：
 * 修改时间：
 */
@Service
@Transactional
public class ToolFamilyService implements ToolFamilyIService {

    @Resource
    private FactoryDAO factoryDAO;

    @Resource
    private ToolDao toolDao;
    @Resource
    private ToolFamilyDAO toolFamilyDAO;

    @Resource
    private DevSModeDAO devSModeDAO;

    @Resource
    private BaseIService baseIService;

    @Resource
    private ToolFamilyPMDAO toolFamilyPMDAO;

    @Resource
    private CyclePlanDAO cyclePlanDAO;

    @Resource
    private FrePlanDAO frePlanDAO;

    //dto  获取工具列表信息
    @Override
    public GetAllToolFamilyInfoRes QALLGetAllFaInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {

        GetAllToolFamilyInfoRes data=new GetAllToolFamilyInfoRes();
        GetAllToolFamilyInfoResB dataB=new GetAllToolFamilyInfoResB();
        List<GetAllToolFamilyInfoResD> dataDs=new ArrayList<GetAllToolFamilyInfoResD>();

        List<ToolFamilyInfo> lists= baseIService.QALLBaseInfo(ToolFamilyDAO.class, "selectToolFamilyAll",
                argInitDataFields, argPageInfo);

        if(lists!=null||lists.size()>0) {

            for (ToolFamilyInfo obj : lists) {
                GetAllToolFamilyInfoResD dataD = new GetAllToolFamilyInfoResD();
                dataD.setToolFamilyRd(obj.getRuid());
                if(obj.getToolFamilyName()==null){
                    break;
                }
                dataD.setToolFamilyName(obj.getToolFamilyName());
                dataDs.add(dataD);
            }

        }
        dataB.setData(dataDs);
        data.setBody(dataB);

        return data;
    }

    @Override
    public PageResult<GetAllToolFamilyInfoResD> QALLGetAllToolFamilyNewRes(BaseRequest req) {
        List<GetAllToolFamilyInfoResD> resDList = new ArrayList<GetAllToolFamilyInfoResD>();
        GetAllToolFamilyInfoResD resD = null;

        IPage<ToolFamilyInfo> iPage = toolFamilyDAO.selectPage(new MyPage(req), CommonUtils.getQueryWrapper(req.getFields()));
        //赋值dto并返回
        for (ToolFamilyInfo obj : iPage.getRecords()) {
            resD = new GetAllToolFamilyInfoResD();
            resD.setToolFamilyRd(obj.getRuid());
            resD.setToolFamilyName(obj.getToolFamilyName());
            resDList.add(resD);
        }
        return new PageResult<>(iPage, resDList);
    }

    //dto查询工具家族信息 根据传过来的ToolRd
    @Override
    public GetToolFamilyInfoRes GetGetToolFamilyInfoRes(GetToolFamilyInfoReqBD00 argGetToolFamilyInfoReqBD00) {
        GetToolFamilyInfoRes objEGetFaInfoRes=new GetToolFamilyInfoRes();
        GetToolFamilyInfoResB objEGetFaInfoResB=new GetToolFamilyInfoResB();
        //根据页面传过来的工具id 查询工具信息
        ToolFamilyInfo objFactoryInfo=toolFamilyDAO.selectToolFamilyID(argGetToolFamilyInfoReqBD00.getToolFamilyRd());

        if(objFactoryInfo!=null) {
            GetToolFamilyInfoResD objEGetFaInfoResD = new GetToolFamilyInfoResD();
            objEGetFaInfoResD.setToolFamilyRd(objFactoryInfo.getRuid());
            objEGetFaInfoResD.setToolFamilyName(objFactoryInfo.getToolFamilyName());
            objEGetFaInfoResD.setDescription(objFactoryInfo.getDescription());


            //保养计划
            List<GetToolFamilyInfoResDPM>  getToolFamilyInfoResDPMS=new ArrayList<GetToolFamilyInfoResDPM>();
            List<ToolFamilyPMInfo> toolFamilyPMInfos=toolFamilyPMDAO.selectToolFamilyPMInfoByToolFamilyGd(objFactoryInfo.getGuid());
            if(toolFamilyPMInfos!=null&&toolFamilyPMInfos.size()>0){
                for(ToolFamilyPMInfo toolFamilyPMInfo:toolFamilyPMInfos){
                    GetToolFamilyInfoResDPM getToolFamilyInfoResDPM =new GetToolFamilyInfoResDPM();

                    if(toolFamilyPMInfo.getpMType().equals("00")){
                        CyclePlanInfo cyclePlanInfo =cyclePlanDAO.selectCyclePlanInfoByGuid(toolFamilyPMInfo.getpMGd());
                        if(cyclePlanInfo != null) {
                            getToolFamilyInfoResDPM.setPMType(toolFamilyPMInfo.getpMType());
                            getToolFamilyInfoResDPM.setPMName(cyclePlanInfo.getCyclePlanName());
                            getToolFamilyInfoResDPM.setPMRd(cyclePlanInfo.getRuid());
                        }
                    }
                    if(toolFamilyPMInfo.getpMType().equals("01")){
                        FrePlanInfo frePlanInfo =frePlanDAO.selectFrePlanByGuid(toolFamilyPMInfo.getpMGd());
                        if(frePlanInfo != null) {
                            getToolFamilyInfoResDPM.setPMType(toolFamilyPMInfo.getpMType());
                            getToolFamilyInfoResDPM.setPMName(frePlanInfo.getFrePlanName());
                            getToolFamilyInfoResDPM.setPMRd(frePlanInfo.getRuid());
                        }
                    }

                    getToolFamilyInfoResDPMS.add(getToolFamilyInfoResDPM);
                }
                objEGetFaInfoResD.setPMInfo(getToolFamilyInfoResDPMS);
            }


            //根据工厂标识 查询工厂信息
            FactoryInfo factoryInfo= factoryDAO.SelectFactoryInfoByguid(objFactoryInfo.getFaGd());
            if(factoryInfo!=null){
                GetToolFamilyInfoResD.Factory factory=new GetToolFamilyInfoResD.Factory();
                factory.setFaRd(factoryInfo.getRuid());
                factory.setFaName(factoryInfo.getFactoryName());
                objEGetFaInfoResD.setFactory(factory);
            }

            //根据状态模型ID查询状态模型信息

            DevSModeInfo devSModeInfo=devSModeDAO.SelectDevSModeInfoguid(objFactoryInfo.getDevSMGd());
            if(devSModeInfo!=null){
                GetToolFamilyInfoResD.Modle modle=new GetToolFamilyInfoResD.Modle();
                modle.setDSMRd(devSModeInfo.getRuid());
                modle.setModelName(devSModeInfo.getModelName());
                objEGetFaInfoResD.setModle(modle);
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
    @Override
    public SaveToolFamilyInfoRes AddGetToolFamilyInfoRes(SaveToolFamilyInfoReqBD00 argSaveToolFamilyInfoReqBD00) {
        SaveToolFamilyInfoRes objESaveFaInfoRes=new SaveToolFamilyInfoRes();
        SaveToolFamilyInfoResB objESaveFaInfoResB=new SaveToolFamilyInfoResB();
        SaveToolFamilyInfoResD objESaveFaInfoResD=new SaveToolFamilyInfoResD();

        //创建工具家族实体类
        ToolFamilyInfo objFactoryInfo=new ToolFamilyInfo();
        objFactoryInfo.setGuid(CommonUtils.getRandomNumber());
        if(argSaveToolFamilyInfoReqBD00.getToolFamilyName()==null||"".equals(argSaveToolFamilyInfoReqBD00.getToolFamilyName())){
            throw new SystemException("MG_MES3001112010001F","新增失败，工具家族名称不能为空");
        }
        //查询所有，判断名称是否存在
        ToolFamilyInfo objEFactoryInfo=toolFamilyDAO.SelectToolInfoByToolFaName(argSaveToolFamilyInfoReqBD00.getToolFamilyName());

        if(objEFactoryInfo!=null){
            throw new SystemException("MG_MES3001112010001F","新增失败，工具家族名称已存在");
        }

        objFactoryInfo.setToolFamilyName(argSaveToolFamilyInfoReqBD00.getToolFamilyName());

        objFactoryInfo.setDescription(argSaveToolFamilyInfoReqBD00.getDescription());

        //根据工厂标识 查询工厂信息   乔帅阳修改
        if(argSaveToolFamilyInfoReqBD00.getFaRd()!=null) {
            FactoryInfo factoryInfo = factoryDAO.SelectFactoryInfoByruid(argSaveToolFamilyInfoReqBD00.getFaRd());
            if (factoryInfo!= null) {
                objFactoryInfo.setFaGd(factoryInfo.getGuid());
            }

        }
        //根据状态模型ID查询状态模型信息       乔帅阳修改
        if(argSaveToolFamilyInfoReqBD00.getDSMRd()!=null){
        DevSModeInfo devSModeInfo=devSModeDAO.SelectDevSModeInfo(argSaveToolFamilyInfoReqBD00.getDSMRd());
        if(devSModeInfo!=null){
            objFactoryInfo.setDevSMGd(devSModeInfo.getGuid());
         }
        }
        objFactoryInfo.setCreator(CommonUtils.readUser().getRealName());
        objFactoryInfo.setCreateTime(new Date());
        objFactoryInfo.setRemark(argSaveToolFamilyInfoReqBD00.getRemark());
        toolFamilyDAO.InsertToolFamilyInfo(objFactoryInfo);





        //设备保养
        if(argSaveToolFamilyInfoReqBD00.getPMInfo().size()>0){
            for(SaveToolFamilyInfoReqBD00PM saveToolFamilyInfoReqBD00PM:argSaveToolFamilyInfoReqBD00.getPMInfo()){
                ToolFamilyPMInfo toolFamilyPMInfo=new ToolFamilyPMInfo();
                toolFamilyPMInfo.setGuid(CommonUtils.getRandomNumber());
                toolFamilyPMInfo.setToolFamilyGd(objFactoryInfo.getGuid());
                if("00".equals(saveToolFamilyInfoReqBD00PM.getPMType())){
                    CyclePlanInfo cyclePlanInfo=cyclePlanDAO.selectCyclePlanInfoByRuid(saveToolFamilyInfoReqBD00PM.getPMRd());
                    if(cyclePlanInfo!=null){
                        toolFamilyPMInfo.setpMGd(cyclePlanInfo.getGuid());
                    }
                    toolFamilyPMInfo.setpMType(saveToolFamilyInfoReqBD00PM.getPMType());
                }
                if("01".equals(saveToolFamilyInfoReqBD00PM.getPMType())){
                    FrePlanInfo frePlanInfo=frePlanDAO.selectFrePlan(saveToolFamilyInfoReqBD00PM.getPMRd());
                    if(frePlanInfo!=null){
                        toolFamilyPMInfo.setpMGd(frePlanInfo.getGuid());
                    }
                    toolFamilyPMInfo.setpMType(saveToolFamilyInfoReqBD00PM.getPMType());
                }
              toolFamilyPMDAO.insertToolFamilyPMInfo(toolFamilyPMInfo);
            }
        }

        objESaveFaInfoResB.setData(objESaveFaInfoResD);
        objESaveFaInfoRes.setBody(objESaveFaInfoResB);

        return objESaveFaInfoRes;
    }

    //dto删除
    @Override
    public SaveToolFamilyInfoRes RmSaveToolFamilyInfoRes(SaveToolFamilyInfoReqBD01 argSaveToolFamilyInfoReqBD01) {
        SaveToolFamilyInfoRes objESaveFaInfoRes=new SaveToolFamilyInfoRes();
        SaveToolFamilyInfoResB objESaveFaInfoResB=new SaveToolFamilyInfoResB();
        SaveToolFamilyInfoResD objESaveFaInfoResD=new SaveToolFamilyInfoResD();

        ToolFamilyInfo toolFamilyInfo=toolFamilyDAO.selectToolFamilyID(argSaveToolFamilyInfoReqBD01.getToolFamilyRd());
        if(toolFamilyInfo!=null){
            if(toolFamilyDAO.DeleteToolFamilyInfoByruid(toolFamilyInfo.getRuid())<=0){
                throw new SystemException("MG_MES3001112020001F","删除工具家族信息失败");
            }

            List<ToolFamilyPMInfo> toolFamilyPMInfos = toolFamilyPMDAO.selectToolFamilyPMInfoByToolFamilyGd(toolFamilyInfo.getGuid());
            if (toolFamilyPMInfos != null && toolFamilyPMInfos.size() > 0) {
                for (ToolFamilyPMInfo toolFamilyPMInfo : toolFamilyPMInfos) {
                    if (toolFamilyPMDAO.deleteToolFamilyPMInfo(toolFamilyPMInfo.getRuid()) <= 0) {
                        throw new SystemException("xx", "删除失败");
                    }
                }
            }
        }



        objESaveFaInfoResB.setData(objESaveFaInfoResD);
        objESaveFaInfoRes.setBody(objESaveFaInfoResB);
        return objESaveFaInfoRes;
    }

    //dto更新
    @Override
    public SaveToolFamilyInfoRes ModSaveToolFamilyInfoRes(SaveToolFamilyInfoReqBD02 argSaveToolFamilyInfoReqBD02) {
        SaveToolFamilyInfoRes objESaveFaInfoRes=new SaveToolFamilyInfoRes();
        SaveToolFamilyInfoResB objESaveFaInfoResB=new SaveToolFamilyInfoResB();
        SaveToolFamilyInfoResD objESaveFaInfoResD=new SaveToolFamilyInfoResD();
        if(argSaveToolFamilyInfoReqBD02.getToolFamilyName()==null||"".equals(argSaveToolFamilyInfoReqBD02.getToolFamilyName())){
            throw new SystemException("MG_MES3001112010001F","修改失败，工具家族名称不能为空");
        }

        //根据页面传过来的工具家族id 查询工具家族信息
        ToolFamilyInfo objFactoryInfo=toolFamilyDAO.selectToolFamilyID(argSaveToolFamilyInfoReqBD02.getToolFamilyRd());
        objFactoryInfo.setRuid(argSaveToolFamilyInfoReqBD02.getToolFamilyRd());

        //更新的工具家族名称不允许重复
        ToolFamilyInfo factoryInfoname=toolFamilyDAO.SelectToolInfoByToolFaName(argSaveToolFamilyInfoReqBD02.getToolFamilyName());
        if(factoryInfoname!=null&&!factoryInfoname.getToolFamilyName().equals(objFactoryInfo.getToolFamilyName())){
            throw new SystemException("MG_MES3001013030002F","更新失败，工具家族名称已存在");
        }
        objFactoryInfo.setToolFamilyName(argSaveToolFamilyInfoReqBD02.getToolFamilyName());
        objFactoryInfo.setDescription(argSaveToolFamilyInfoReqBD02.getDescription());
        //根据工厂标识 查询工厂信息
        if(argSaveToolFamilyInfoReqBD02.getFaRd()!=null&&!"".equals(argSaveToolFamilyInfoReqBD02.getFaRd())) {
            FactoryInfo factoryInfo = factoryDAO.SelectFactoryInfoByruid(argSaveToolFamilyInfoReqBD02.getFaRd());
            objFactoryInfo.setFaGd(factoryInfo.getGuid());
        }else {
            objFactoryInfo.setFaGd("");
        }
        //模型修改乔帅阳
        DevSModeInfo devSModeInfo=null;
        if(argSaveToolFamilyInfoReqBD02.getDSMRd()!=null&&!"".equals(argSaveToolFamilyInfoReqBD02.getDSMRd())){
            //根据状态模型ID查询状态模型信息
            devSModeInfo=devSModeDAO.SelectDevSModeInfo(argSaveToolFamilyInfoReqBD02.getDSMRd());
            if(devSModeInfo!=null){
                objFactoryInfo.setDevSMGd(devSModeInfo.getGuid());
            }
        }else {
            objFactoryInfo.setDevSMGd("");
        }

        objFactoryInfo.setRemark(argSaveToolFamilyInfoReqBD02.getRemark());
        objFactoryInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        objFactoryInfo.setLastModifyTime(new Date());

        List<ToolFamilyPMInfo> toolFamilyPMInfos = toolFamilyPMDAO.selectToolFamilyPMInfoByToolFamilyGd(objFactoryInfo.getGuid());
        if (toolFamilyPMInfos != null && toolFamilyPMInfos.size() > 0) {
            for (ToolFamilyPMInfo toolFamilyPMInfo : toolFamilyPMInfos) {
                if (toolFamilyPMDAO.deleteToolFamilyPMInfo(toolFamilyPMInfo.getRuid()) <= 0) {
                    throw new SystemException("xx", "删除失败");
                }
            }
        }


        //设备保养
        if(argSaveToolFamilyInfoReqBD02.getPMInfo().size()>0){
            for(SaveToolFamilyInfoReqBD02PM saveToolFamilyInfoReqBD00PM:argSaveToolFamilyInfoReqBD02.getPMInfo()){
                ToolFamilyPMInfo toolFamilyPMInfo=new ToolFamilyPMInfo();
                toolFamilyPMInfo.setGuid(CommonUtils.getRandomNumber());
                toolFamilyPMInfo.setToolFamilyGd(objFactoryInfo.getGuid());
                if("00".equals(saveToolFamilyInfoReqBD00PM.getPMType())){
                    CyclePlanInfo cyclePlanInfo=cyclePlanDAO.selectCyclePlanInfoByRuid(saveToolFamilyInfoReqBD00PM.getPMRd());
                    if(cyclePlanInfo!=null){
                        toolFamilyPMInfo.setpMGd(cyclePlanInfo.getGuid());
                    }
                    toolFamilyPMInfo.setpMType(saveToolFamilyInfoReqBD00PM.getPMType());
                }
                if("01".equals(saveToolFamilyInfoReqBD00PM.getPMType())){
                    FrePlanInfo frePlanInfo=frePlanDAO.selectFrePlan(saveToolFamilyInfoReqBD00PM.getPMRd());
                    if(frePlanInfo!=null){
                        toolFamilyPMInfo.setpMGd(frePlanInfo.getGuid());
                    }
                    toolFamilyPMInfo.setpMType(saveToolFamilyInfoReqBD00PM.getPMType());
                }
                toolFamilyPMDAO.insertToolFamilyPMInfo(toolFamilyPMInfo);
            }
        }

        if(toolFamilyDAO.UpdateToolFamilyInfoByruid(objFactoryInfo)<=0){
            throw new SystemException("MG_MES3001112030003F","更新工具信息失败");
        }
        objESaveFaInfoResB.setData(objESaveFaInfoResD);
        objESaveFaInfoRes.setBody(objESaveFaInfoResB);

        return objESaveFaInfoRes;
    }

    //dto复制
    @Override
    public SaveToolFamilyInfoRes AddSaveToolFamilyInfoRes(SaveToolFamilyInfoReqBD03 argSaveToolFamilyInfoReqBD03) {
        SaveToolFamilyInfoRes objESaveFaInfoRes=new SaveToolFamilyInfoRes();
        SaveToolFamilyInfoResB objESaveFaInfoResB=new SaveToolFamilyInfoResB();
        SaveToolFamilyInfoResD objESaveFaInfoResD=new SaveToolFamilyInfoResD();

        //根据页面传过来的工具家族id 查询工具家族信息
        ToolFamilyInfo objFactoryInfo=toolFamilyDAO.selectToolFamilyID(argSaveToolFamilyInfoReqBD03.getToolFamilyRd());
        if(objFactoryInfo==null){
            throw new SystemException("MG_MES3001112040001F","查询工具信息失败");
        }

        objFactoryInfo.setGuid(CommonUtils.getRandomNumber());
        objFactoryInfo.setCreateTime(new Date());
        objFactoryInfo.setLastModifyTime(new Date());
        objFactoryInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        objFactoryInfo.setCreator(CommonUtils.readUser().getRealName());
        toolFamilyDAO.InsertToolFamilyInfo(objFactoryInfo);

        ToolFamilyInfo objEFactoryInfo=toolFamilyDAO.selectToolFamily(objFactoryInfo.getGuid());
        if(objEFactoryInfo==null){
            throw new SystemException("MG_MES3001112040003F","查询工具家族信息失败");
        }

        objEFactoryInfo.setToolFamilyName(objEFactoryInfo.getToolFamilyName()+objEFactoryInfo.getRuid());
        if(toolFamilyDAO.UpdateToolFamilyInfoByruid(objEFactoryInfo)<=0){
            throw new SystemException("MG_MES3001112040004F","更新工具家族名称信息失败");
        }

        objESaveFaInfoResB.setData(objESaveFaInfoResD);
        objESaveFaInfoRes.setBody(objESaveFaInfoResB);

        return objESaveFaInfoRes;
    }
}
