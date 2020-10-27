package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllCaeeierInfo.GetAllCarrierInfoRes;
import pnc.mesadmin.dto.GetAllCaeeierInfo.GetAllCarrierInfoResB;
import pnc.mesadmin.dto.GetAllCaeeierInfo.GetAllCarrierInfoResD;
import pnc.mesadmin.dto.GetAllToolInfo.GetAllToolInfoResD;
import pnc.mesadmin.dto.GetCarrierInfo.*;
import pnc.mesadmin.dto.SaveCarrierInfo.*;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.CarrierIService;
import pnc.mesadmin.service.GConfigIService;
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
 * 子系统名称：载具信息Service层实现层
 * 创建人：郝赞
 * 创建时间：2018-06-15
 * 修改人：
 * 修改时间：
 */
@Transactional
@Service
public class CarrierService implements CarrierIService{
    @Resource
    private CheckPlanDAO checkPlanDAO;
    @Resource
    private CarrierCheckPlanInfoDAO CarrierCheckPlanInfoDAO;

    @Resource
    private FactoryDAO factoryDAO;

    @Resource
    private CodeRuleDAO codeRuleDAO;

    @Resource
    private GConfigIService gConfigIService;

    @Resource
    CarrierDao carrierDao;

    @Resource
    private SupplierDAO supplierDAO;

    @Resource
    private  CarrierFamilyDAO carrierFamilyDAO;

    @Resource
    private  DevSModeDAO devSModeDAO;

    @Resource
    private BaseIService baseIService;

    @Resource
    private CarrierPMDAO carrierPMDAO;


    @Resource
    private CyclePlanDAO cyclePlanDAO;

    @Resource
    private FrePlanDAO frePlanDAO;



    @Resource
    private CarrierBZDao carrierBZDao;

    @Resource
    private CarrierBZDtlDao carrierBZDtlDao;

    @Resource
    private  CarrierCheckPlanInfoDAO carrierCheckPlanInfoDAO;

    @Resource
    private DoCheckInfoDAO doCheckInfoDAO;

    @Resource
    private DoPMInfoDAO doPMInfoDAO;


    //dto  获取载具列表信息
    @Override
    public GetAllCarrierInfoRes QALLGetAllCarrierInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllCarrierInfoRes data=new GetAllCarrierInfoRes();
        GetAllCarrierInfoResB dataB=new GetAllCarrierInfoResB();
        List<GetAllCarrierInfoResD> dataDs=new ArrayList<GetAllCarrierInfoResD>();

        List<CarrierInfo> lists= baseIService.QALLBaseInfo(CarrierDao.class, "SelectAllCarrierInfo",
                argInitDataFields, argPageInfo);

        if(lists!=null||lists.size()>0) {

            for (CarrierInfo obj : lists) {
                GetAllCarrierInfoResD dataD = new GetAllCarrierInfoResD();
                dataD.setCarrierRd(obj.getRuid());
                if(obj.getCarrierName()==null){
                    break;
                }
                dataD.setStatus(obj.getStatus());
                dataD.setCarrierName(obj.getCarrierName());
                dataDs.add(dataD);
            }
        }
        dataB.setData(dataDs);
        data.setBody(dataB);

        return data;
    }

    @Override
    public PageResult<GetAllCarrierInfoResD> QALLGetAllCarriersNewRes(BaseRequest req) {
        List<GetAllCarrierInfoResD> resDList = new ArrayList<>();
        GetAllCarrierInfoResD resD = null;

        IPage<CarrierInfo> iPage = carrierDao.selectPage(new MyPage(req), CommonUtils.getQueryWrapper(req.getFields()));

        //赋值dto并返回
        for (CarrierInfo obj : iPage.getRecords()) {
            resD = new GetAllCarrierInfoResD();
            resD.setCarrierRd(obj.getRuid());
            resD.setCarrierName(obj.getCarrierName());
            resDList.add(resD);
        }
        return new PageResult<>(iPage, resDList);
    }

    //dto查询载具信息 根据传过来的CarrierRd
    @Override
    public GetCarrierInfoRes GetGetCarrierInfoRes(GetCarrierInfoReqBD00 argGetCarrierInfoReqBD00) {
        GetCarrierInfoRes objEGetFaInfoRes=new GetCarrierInfoRes();
        GetCarrierInfoResB objEGetFaInfoResB=new GetCarrierInfoResB();
        //根据页面传过来的载具id 查询载具信息
        CarrierInfo objFactoryInfo=carrierDao.SelectCarrierInfoByruid(argGetCarrierInfoReqBD00.getCarrierRd());

        if(objFactoryInfo!=null) {
            GetCarrierInfoResD objEGetFaInfoResD = new GetCarrierInfoResD();

            //   ================2019-5-23==============
            CarrierBZInfo carrierBZInfo = carrierBZDao.SelectCarrierBZInfo(objFactoryInfo.getGuid());

            if(carrierBZInfo!=null){
                GetCarrierInfoResDCarrierBZInfo getCarrierInfoResDCarrierBZInfo = new GetCarrierInfoResDCarrierBZInfo();
                getCarrierInfoResDCarrierBZInfo.setReference(carrierBZInfo.getReference());
                List<GetCarrierInfoResDBZDtl> GetCarrierInfoResDBZDtl = new ArrayList<>();
                List<CarrierBZDtlInfo>  CarrierBZDtlInfo = carrierBZDtlDao.SelectCarrierBZDtlInfoCarrierBZGd(carrierBZInfo.getGuid());
                if(CarrierBZDtlInfo!=null||CarrierBZDtlInfo.size()>0){
                    for (CarrierBZDtlInfo objCarrierBZDtlInfo:CarrierBZDtlInfo){
                        GetCarrierInfoResDBZDtl GetCarrierInfoResDBZDtl1 = new GetCarrierInfoResDBZDtl();
                        GetCarrierInfoResDBZDtl1.setKeyName(objCarrierBZDtlInfo.getKeyName());
                        GetCarrierInfoResDBZDtl1.setKeyValue(objCarrierBZDtlInfo.getKeyValue());
                        GetCarrierInfoResDBZDtl.add(GetCarrierInfoResDBZDtl1);
                    }
                }
                getCarrierInfoResDCarrierBZInfo.setBZDtl(GetCarrierInfoResDBZDtl);
                objEGetFaInfoResD.setCarrierBZInfo(getCarrierInfoResDCarrierBZInfo);
            }


            objEGetFaInfoResD.setCarrierRd(objFactoryInfo.getRuid());
            objEGetFaInfoResD.setCarrierName(objFactoryInfo.getCarrierName());
            objEGetFaInfoResD.setStatus(objFactoryInfo.getStatus());
            objEGetFaInfoResD.setVenderSN(objFactoryInfo.getVenderSN());


            //保养计划
            List<GetCarrierInfoResDPM>  getCarrierInfoResDPMS=new ArrayList<GetCarrierInfoResDPM>();
            List<CarrierPMInfo> carrierPMInfos=carrierPMDAO.selectCarrierPMInfoByToolFamilyGd(objFactoryInfo.getGuid());
            if(carrierPMInfos!=null&&carrierPMInfos.size()>0){
                for(CarrierPMInfo carrierPMInfo:carrierPMInfos){
                    GetCarrierInfoResDPM getDevInfoResDPM=new GetCarrierInfoResDPM();

                    if(carrierPMInfo.getpMType().equals("00")){
                        CyclePlanInfo cyclePlanInfo =cyclePlanDAO.selectCyclePlanInfoByGuid(carrierPMInfo.getpMGd());
                        if(cyclePlanInfo!=null){
                        getDevInfoResDPM.setPMType(carrierPMInfo.getpMType());
                        getDevInfoResDPM.setPMName(cyclePlanInfo.getCyclePlanName());
                        getDevInfoResDPM.setPMRd(cyclePlanInfo.getRuid());
                    }}
                    if(carrierPMInfo.getpMType().equals("01")){
                        FrePlanInfo frePlanInfo =frePlanDAO.selectFrePlanByGuid(carrierPMInfo.getpMGd());
                        if(frePlanInfo!=null){
                        getDevInfoResDPM.setPMType(carrierPMInfo.getpMType());
                        getDevInfoResDPM.setPMName(frePlanInfo.getFrePlanName());
                        getDevInfoResDPM.setPMRd(frePlanInfo.getRuid());
                    }
                    }
                    getCarrierInfoResDPMS.add(getDevInfoResDPM);
                }
                objEGetFaInfoResD.setPMInfo(getCarrierInfoResDPMS);
            }

            //点检计划
            List<SaveCarrierInfoReqBD00CheckPlanInfo> SaveCarrierInfoReqBD00CheckPlanInfos=new ArrayList<>();
            List<CarrierCheckPlanInfo> CarrierCheckPlanInfos=CarrierCheckPlanInfoDAO.SelectCarrierCheckPlanInfoByGd(objFactoryInfo.getGuid());
            if(CarrierCheckPlanInfos!=null && CarrierCheckPlanInfos.size()>0){
                for(CarrierCheckPlanInfo carrierCheckPlanInfo:CarrierCheckPlanInfos){
                    CheckPlanInfo checkPlanInfo=checkPlanDAO.selectCheckPlanInfoByGuid(carrierCheckPlanInfo.getCheckPlanGd());
                    if(checkPlanInfo!=null) {
                        SaveCarrierInfoReqBD00CheckPlanInfo SaveCarrierInfoReqBD00CheckPlanInfo = new SaveCarrierInfoReqBD00CheckPlanInfo();
                        SaveCarrierInfoReqBD00CheckPlanInfo.setCheckPlanRd(checkPlanInfo.getRuid());
                        SaveCarrierInfoReqBD00CheckPlanInfo.setCheckPlanName(checkPlanInfo.getCheckPlanName());
                        SaveCarrierInfoReqBD00CheckPlanInfos.add(SaveCarrierInfoReqBD00CheckPlanInfo);
                    }
                }
                objEGetFaInfoResD.setCheckPlanInfo(SaveCarrierInfoReqBD00CheckPlanInfos);
            }



            //根据载具家族ID查询载具家族信息
            CarrierFamilyInfo toolFamilyInfo=null;
            if(objFactoryInfo.getCarrierFamilyGd()!=null){
                toolFamilyInfo= carrierFamilyDAO.selectCarrierFamily(objFactoryInfo.getCarrierFamilyGd());
            }
            GetCarrierInfoResD.CarrierFamily carrierFamily=new GetCarrierInfoResD.CarrierFamily();
            if(toolFamilyInfo!=null){
                carrierFamily=new GetCarrierInfoResD.CarrierFamily();
                carrierFamily.setCarrierFamilyRd(toolFamilyInfo.getRuid());
                carrierFamily.setCarrierFamilyName(toolFamilyInfo.getCarrierFamilyName());
            }
            objEGetFaInfoResD.setCarrierFamily(carrierFamily);
            //根据供应商标识查询供应商信息
            SupplierInfo supplierInfo=null;
            if(objFactoryInfo.getSupplierGd()!=null){
                supplierInfo= supplierDAO.SelectByGuid(objFactoryInfo.getSupplierGd());
            }

            if(supplierInfo!=null){
                GetCarrierInfoResD.Suppier supplier=new GetCarrierInfoResD.Suppier();
                supplier.setSupRd(supplierInfo.getRuid());
                supplier.setSupName(supplierInfo.getSupplierName());
                objEGetFaInfoResD.setSuppier(supplier);
            }
            //根据工厂标识 查询工厂信息
            FactoryInfo factoryInfo= factoryDAO.SelectFactoryInfoByguid(objFactoryInfo.getFaGd());
            if(factoryInfo!=null){
                GetCarrierInfoResD.Factory factory=new GetCarrierInfoResD.Factory();
                factory.setFaRd(factoryInfo.getRuid());
                factory.setFaName(factoryInfo.getFactoryName());
                objEGetFaInfoResD.setFactory(factory);
            }

            //根据状态模型ID查询状态模型信息
            DevSModeInfo devSModeInfo=devSModeDAO.SelectDevSModeInfoguid(objFactoryInfo.getDevSMGd());
            if(devSModeInfo!=null){
                GetCarrierInfoResD.Model model=new GetCarrierInfoResD.Model();
                model.setDSMRd(devSModeInfo.getRuid());
                model.setModelName(devSModeInfo.getModelName());
                objEGetFaInfoResD.setModel(model);
            }
            //objEGetFaInfoResD.
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

    //dto. 保存载具
    @Override
    public SaveCarrierInfoRes AddGetCarrierInfoRes(SaveCarrierInfoReqBD00 argSaveCarrierInfoReqBD00) {
        SaveCarrierInfoRes objESaveFaInfoRes=new SaveCarrierInfoRes();
        SaveCarrierInfoResB objESaveFaInfoResB=new SaveCarrierInfoResB();
        SaveCarrierInfoResD objESaveFaInfoResD=new SaveCarrierInfoResD();

        if(argSaveCarrierInfoReqBD00.getCarrierName()==null||"".equals(argSaveCarrierInfoReqBD00.getCarrierName())){
            throw new SystemException("xx","新增失败，载具名称不能为空");
        }


        //创建载具实体类
        CarrierInfo objFactoryInfo=new CarrierInfo();
        objFactoryInfo.setGuid(CommonUtils.getRandomNumber());

        //查询载具信息，判断名称是否存在
        CarrierInfo objEFactoryInfo=carrierDao.SelectCarrierInfoByFactoryName(argSaveCarrierInfoReqBD00.getCarrierName());

        if(objEFactoryInfo!=null){
            throw new SystemException("MG_MES3001112010001F","新增失败，载具名称已存在");
        }

        objFactoryInfo.setCarrierName(argSaveCarrierInfoReqBD00.getCarrierName());
        //根据供应商标识 查询供应商信息
        SupplierInfo supplierInfo=supplierDAO.SelectBySuppId(argSaveCarrierInfoReqBD00.getSupplierRd());
        if(supplierInfo!=null){
            objFactoryInfo.setSupplierGd(supplierInfo.getGuid());
        }

        //根据载具家族标识 查询载具信息
        CarrierFamilyInfo carrierFamilyInfo=carrierFamilyDAO.selectCarrierFamilyID(argSaveCarrierInfoReqBD00.getCarrierFamilyRd());
        if(carrierFamilyInfo!=null){
            objFactoryInfo.setCarrierFamilyGd(carrierFamilyInfo.getGuid());
        }
       /* //查询代码生成
        CodeRuleInfo codeRuleInfo=codeRuleDAO.SelectCodeRuleInfocodeType("13");
        String SN="";



        if(!"".equals(argSaveCarrierInfoReqBD00.getVenderSN())) {
            CarrierInfo carrierInfo1= carrierDao.SelectCarrierSN(argSaveCarrierInfoReqBD00.getVenderSN());
            if(carrierInfo1!=null){
                throw new SystemException("", "新增失败，序列号已存在");
            }
            objFactoryInfo.setVenderSN(argSaveCarrierInfoReqBD00.getVenderSN());
        }else if(codeRuleInfo!=null && "00".equals(codeRuleInfo.getStatus())){
            SN=gConfigIService.GetCreateSC(codeRuleInfo);
            objFactoryInfo.setVenderSN(SN);
        }else{
            throw new SystemException("", "请输入载具家族序号，或请到全局配置进行代码生成配置");
        }
*/
        CarrierInfo carrierInfo1= carrierDao.SelectCarrierSN(argSaveCarrierInfoReqBD00.getVenderSN());
        if(carrierInfo1!=null){
            throw new SystemException("", "新增失败，序列号已存在");
        }
        objFactoryInfo.setVenderSN(argSaveCarrierInfoReqBD00.getVenderSN());


        //根据状态模型ID查询状态模型信息
        DevSModeInfo devSModeInfo=devSModeDAO.SelectDevSModeInfo(argSaveCarrierInfoReqBD00.getDSMRd());
        if(devSModeInfo!=null){
            objFactoryInfo.setDevSMGd(devSModeInfo.getGuid());
            objFactoryInfo.setDevSMGd(devSModeInfo.getDSGd());

        }else{
            objFactoryInfo.setDevSMGd("");
        }



        //   ================2019-5-23==============
        CarrierBZInfo carrierBZInfo = new CarrierBZInfo();
        if(argSaveCarrierInfoReqBD00.getCarrierBZInfo().getReference()!=null||"".equals(argSaveCarrierInfoReqBD00.getCarrierBZInfo().getReference())){
            carrierBZInfo.setGuid(CommonUtils.getRandomNumber());
            carrierBZInfo.setCarrierGd(objFactoryInfo.getGuid());
            carrierBZInfo.setReference(argSaveCarrierInfoReqBD00.getCarrierBZInfo().getReference());
            carrierBZDao.InsertCarrierBZInfo(carrierBZInfo);
        }
        List<SaveCarrierInfoReqBD00BZDtl> SaveCarrierInfoReqBD00BZDtl = new ArrayList<>();
        if(argSaveCarrierInfoReqBD00.getCarrierBZInfo().getBZDtl().size()>0){
            for(SaveCarrierInfoReqBD00BZDtl obj: argSaveCarrierInfoReqBD00.getCarrierBZInfo().getBZDtl()) {
                CarrierBZDtlInfo CarrierBZDtlInfo = new CarrierBZDtlInfo();
                CarrierBZDtlInfo.setKeyName(obj.getKeyName());
                CarrierBZDtlInfo.setKeyValue(obj.getKeyValue());
                CarrierBZDtlInfo.setCarrierBZGd(carrierBZInfo.getGuid());
                carrierBZDtlDao.InsertCarrierBZDtlInfo(CarrierBZDtlInfo);
            }
        }




        //根据工厂标识 查询工厂信息
        FactoryInfo factoryInfo= factoryDAO.SelectFactoryInfoByruid(argSaveCarrierInfoReqBD00.getFaRd());
        if(factoryInfo!=null){
            objFactoryInfo.setFaGd(factoryInfo.getGuid());
        }

        objFactoryInfo.setCreator(CommonUtils.readUser().getRealName());
        objFactoryInfo.setCreateTime(new Date());
        objFactoryInfo.setRemark(argSaveCarrierInfoReqBD00.getRemark());
        carrierDao.InsertCarrierInfo(objFactoryInfo);

        //点检计划
        if(argSaveCarrierInfoReqBD00.getCheckPlanInfo().size()>0){
            for(SaveCarrierInfoReqBD00CheckPlanInfo SaveCarrierInfoReqBD00CheckPlanInfo:argSaveCarrierInfoReqBD00.getCheckPlanInfo()){
                CarrierCheckPlanInfo carrierCheckPlanInfo=new CarrierCheckPlanInfo();
                carrierCheckPlanInfo.setCarrierGd(objFactoryInfo.getGuid());
                carrierCheckPlanInfo.setGuid(CommonUtils.getRandomNumber());
                CheckPlanInfo checkPlanInfo=checkPlanDAO.selectCheckPlanInfoByRuid(SaveCarrierInfoReqBD00CheckPlanInfo.getCheckPlanRd());
                carrierCheckPlanInfo.setCheckPlanGd(checkPlanInfo.getGuid());
                CarrierCheckPlanInfoDAO.AddCarrierCheckPlanInfo(carrierCheckPlanInfo);
            }
        }


        //设备保养
        if(argSaveCarrierInfoReqBD00.getPMInfo().size()>0){
            for(SaveCarrierInfoReqBD00PM saveCarrierInfoReqBD00PM:argSaveCarrierInfoReqBD00.getPMInfo()){
                CarrierPMInfo carrierPMInfo=new CarrierPMInfo();
                carrierPMInfo.setGuid(CommonUtils.getRandomNumber());
                carrierPMInfo.setCarrierGd(objFactoryInfo.getGuid());
                if("00".equals(saveCarrierInfoReqBD00PM.getPMType())){
                    CyclePlanInfo cyclePlanInfo=cyclePlanDAO.selectCyclePlanInfoByRuid(saveCarrierInfoReqBD00PM.getPMRd());
                    if(cyclePlanInfo!=null){
                        carrierPMInfo.setpMGd(cyclePlanInfo.getGuid());
                    }
                    carrierPMInfo.setpMType(saveCarrierInfoReqBD00PM.getPMType());
                }
                if("01".equals(saveCarrierInfoReqBD00PM.getPMType())){
                    FrePlanInfo frePlanInfo=frePlanDAO.selectFrePlan(saveCarrierInfoReqBD00PM.getPMRd());
                    if(frePlanInfo!=null){
                        carrierPMInfo.setpMGd(frePlanInfo.getGuid());
                    }
                    carrierPMInfo.setpMType(saveCarrierInfoReqBD00PM.getPMType());
                }carrierPMDAO.insertCarrierPMInfo(carrierPMInfo);
            }
        }

        objESaveFaInfoResB.setData(objESaveFaInfoResD);
        objESaveFaInfoRes.setBody(objESaveFaInfoResB);

        return objESaveFaInfoRes;
    }

    //dto删除
    @Override
    public SaveCarrierInfoRes RmSaveCarrierInfoRes(SaveCarrierInfoReqBD01 argSaveCarrierInfoReqBD01) {
        SaveCarrierInfoRes objESaveFaInfoRes=new SaveCarrierInfoRes();
        SaveCarrierInfoResB objESaveFaInfoResB=new SaveCarrierInfoResB();
        SaveCarrierInfoResD objESaveFaInfoResD=new SaveCarrierInfoResD();

        CarrierInfo carrierInfo=carrierDao.SelectCarrierInfoByruid(argSaveCarrierInfoReqBD01.getCarrierRd());

        if (carrierInfo == null) {
            throw new SystemException("EEEE", "设备信息不能为空");
        }
/*        List<DoPMInfo> doPMInfos = doPMInfoDAO.SelectDoPMInfoByPMObjTypeandPMObjGdandStatus("02", carrierInfo.getGuid());
        if ( doPMInfos != null && doPMInfos.size()>0) {
            throw  new SystemException("EEE","当前设备不能进行删除，因为有未处理完成的保养任务");
        }
        List<DoCheckInfo> doCheckInfos = doCheckInfoDAO.SelectDoCheckInfosByCheckObjTypeandCheckObjGdandStatus("02", carrierInfo.getGuid());
        if (doCheckInfos != null && doCheckInfos.size()>0) {
            throw  new SystemException("EEE","当前设备不能进行删除，因为有未处理完成的点检任务");
        }*/
        if(carrierInfo!=null){
            if(carrierDao.DeleteToolInfoByruid(carrierInfo.getRuid())<=0){
                throw new SystemException("MG_MES3001112020001F","删除载具信息失败");
            }

            List<CarrierPMInfo> carrierPMInfos = carrierPMDAO.selectCarrierPMInfoByToolFamilyGd(carrierInfo.getGuid());
            if (carrierPMInfos != null && carrierPMInfos.size() > 0) {
                for (CarrierPMInfo carrierPMInfo : carrierPMInfos) {
                    if (carrierPMDAO.deleteCarrierPMInfo(carrierPMInfo.getRuid()) <= 0) {
                        throw new SystemException("xx", "删除失败");
                    }
                }
            }

            List<CarrierCheckPlanInfo> carrierCheckPlanInfos = carrierCheckPlanInfoDAO.SelectCarrierCheckPlanInfoByGd(carrierInfo.getGuid());
            if (carrierCheckPlanInfos != null &&  carrierCheckPlanInfos.size()>0) {
                int i = carrierCheckPlanInfoDAO.deleteByCarrierCheckPlanInfoGd(carrierInfo.getGuid());
                if (i <=0) {
                    throw new SystemException("xx", "删除失败");
                }

            }
        }


        objESaveFaInfoResB.setData(objESaveFaInfoResD);
        objESaveFaInfoRes.setBody(objESaveFaInfoResB);
        return objESaveFaInfoRes;
    }

    //dto更新
    @Override
    public SaveCarrierInfoRes ModSaveCarrierInfoRes(SaveCarrierInfoReqBD02 argSaveCarrierInfoReqBD02) {
        SaveCarrierInfoRes objESaveFaInfoRes=new SaveCarrierInfoRes();
        SaveCarrierInfoResB objESaveFaInfoResB=new SaveCarrierInfoResB();
        SaveCarrierInfoResD objESaveFaInfoResD=new SaveCarrierInfoResD();
        if(argSaveCarrierInfoReqBD02.getCarrierName()==null||"".equals(argSaveCarrierInfoReqBD02.getCarrierName())){
            throw new SystemException("xx","修改失败，载具名称不能为空");
        }

        //根据载具ID查询载具信息
        CarrierInfo objFactoryInfo=carrierDao.SelectCarrierInfoByruid(argSaveCarrierInfoReqBD02.getCarrierRd());
        objFactoryInfo.setRuid(argSaveCarrierInfoReqBD02.getCarrierRd());

        //更新的载具名称不允许重复
        CarrierInfo factoryInfoname=carrierDao.SelectCarrierInfoByFactoryName(argSaveCarrierInfoReqBD02.getCarrierName());
        if(factoryInfoname!=null&&!factoryInfoname.getCarrierName().equals(objFactoryInfo.getCarrierName())){
            throw new SystemException("MG_MES3001013030002F","更新失败，载具名称已存在");
        }
        objFactoryInfo.setCarrierName(argSaveCarrierInfoReqBD02.getCarrierName());
        //根据供应商标识 查询供应商信息
        SupplierInfo supplierInfo=supplierDAO.SelectBySuppId(argSaveCarrierInfoReqBD02.getSupplierRd());
        if(supplierInfo!=null){
            objFactoryInfo.setSupplierGd(supplierInfo.getGuid());
        }else {
            objFactoryInfo.setSupplierGd("");
        }

        List<CarrierPMInfo> carrierPMInfos = carrierPMDAO.selectCarrierPMInfoByToolFamilyGd(objFactoryInfo.getGuid());
        if (carrierPMInfos != null && carrierPMInfos.size() > 0) {
            for (CarrierPMInfo carrierPMInfo : carrierPMInfos) {
                if (carrierPMDAO.deleteCarrierPMInfo(carrierPMInfo.getRuid()) <= 0) {
                    throw new SystemException("xx", "删除失败");
                }
            }
        }

        //
        //  设备标准值  //   ================2019-5-23==============
        CarrierBZInfo CarrierBZInfo = carrierBZDao.SelectCarrierBZInfo(objFactoryInfo.getGuid());
        if(CarrierBZInfo!=null){
            carrierBZDao.DeleteAllCarrierBZInfoRuid(CarrierBZInfo.getCarrierGd());
            carrierBZDtlDao.DeleteAllCarrierBZDtlToolBZGd(CarrierBZInfo.getGuid());
        }
        CarrierBZInfo carrierBZInfo = new CarrierBZInfo();
        if(argSaveCarrierInfoReqBD02.getCarrierBZInfo().getReference()!=null||"".equals(argSaveCarrierInfoReqBD02.getCarrierBZInfo().getReference())){
            carrierBZInfo.setGuid(CommonUtils.getRandomNumber());
            carrierBZInfo.setCarrierGd(objFactoryInfo.getGuid());
            carrierBZInfo.setReference(argSaveCarrierInfoReqBD02.getCarrierBZInfo().getReference());
            carrierBZDao.InsertCarrierBZInfo(carrierBZInfo);
        }
        List<SaveCarrierInfoReqBD02BZDtl> SaveCarrierInfoReqBD02BZDtl = new ArrayList<>();
        if(argSaveCarrierInfoReqBD02.getCarrierBZInfo().getBZDtl().size()>0){
            for(SaveCarrierInfoReqBD02BZDtl obj: argSaveCarrierInfoReqBD02.getCarrierBZInfo().getBZDtl()) {
                CarrierBZDtlInfo CarrierBZDtlInfo = new CarrierBZDtlInfo();
                CarrierBZDtlInfo.setKeyName(obj.getKeyName());
                CarrierBZDtlInfo.setKeyValue(obj.getKeyValue());
                CarrierBZDtlInfo.setCarrierBZGd(carrierBZInfo.getGuid());
                carrierBZDtlDao.InsertCarrierBZDtlInfo(CarrierBZDtlInfo);
            }
        }

        //点检计划
        CarrierInfo CarrierInfo =carrierDao.SelectCarrierInfoByruid(argSaveCarrierInfoReqBD02.getCarrierRd());
        CarrierCheckPlanInfoDAO.deleteByCarrierCheckPlanInfoGd(CarrierInfo.getGuid());
        if(argSaveCarrierInfoReqBD02.getCheckPlanInfo().size()>0){
            for(SaveCarrierInfoReqBD00CheckPlanInfo SaveCarrierInfoReqBD00CheckPlanInfo:argSaveCarrierInfoReqBD02.getCheckPlanInfo()){
                CarrierCheckPlanInfo carrierCheckPlanInfo=new CarrierCheckPlanInfo();
                carrierCheckPlanInfo.setCarrierGd(objFactoryInfo.getGuid());
                carrierCheckPlanInfo.setGuid(CommonUtils.getRandomNumber());
                CheckPlanInfo checkPlanInfo=checkPlanDAO.selectCheckPlanInfoByRuid(SaveCarrierInfoReqBD00CheckPlanInfo.getCheckPlanRd());
                carrierCheckPlanInfo.setCheckPlanGd(checkPlanInfo.getGuid());
                CarrierCheckPlanInfoDAO.AddCarrierCheckPlanInfo(carrierCheckPlanInfo);
            }
        }




        //设备保养
        if(argSaveCarrierInfoReqBD02.getPMInfo().size()>0){
            for(SaveCarrierInfoReqBD02PM saveCarrierInfoReqBD02PM:argSaveCarrierInfoReqBD02.getPMInfo()){
                CarrierPMInfo carrierPMInfo=new CarrierPMInfo();
                carrierPMInfo.setGuid(CommonUtils.getRandomNumber());
                carrierPMInfo.setCarrierGd(objFactoryInfo.getGuid());
                if("00".equals(saveCarrierInfoReqBD02PM.getPMType())){
                    CyclePlanInfo cyclePlanInfo=cyclePlanDAO.selectCyclePlanInfoByRuid(saveCarrierInfoReqBD02PM.getPMRd());
                    if(cyclePlanInfo!=null){
                        carrierPMInfo.setpMGd(cyclePlanInfo.getGuid());
                    }
                    carrierPMInfo.setpMType(saveCarrierInfoReqBD02PM.getPMType());
                }
                if("01".equals(saveCarrierInfoReqBD02PM.getPMType())){
                    FrePlanInfo frePlanInfo=frePlanDAO.selectFrePlan(saveCarrierInfoReqBD02PM.getPMRd());
                    if(frePlanInfo!=null){
                        carrierPMInfo.setpMGd(frePlanInfo.getGuid());
                    }
                    carrierPMInfo.setpMType(saveCarrierInfoReqBD02PM.getPMType());
                }carrierPMDAO.insertCarrierPMInfo(carrierPMInfo);
            }
        }

        //根据载具家族标识 查询载具信息
        CarrierFamilyInfo carrierFamilyInfo=carrierFamilyInfo=carrierFamilyDAO.selectCarrierFamilyID(argSaveCarrierInfoReqBD02.getCarrierFamilyRd());


        if(carrierFamilyInfo!=null){
                objFactoryInfo.setCarrierFamilyGd(carrierFamilyInfo.getGuid());
        }else {
            objFactoryInfo.setCarrierFamilyGd("");
        }
        /*//代码生成
        CodeRuleInfo codeRuleInfo=codeRuleDAO.SelectCodeRuleInfocodeType("13");

        String SN="";

        if(!"".equals(argSaveCarrierInfoReqBD02.getVenderSN())) {
            CarrierInfo carrierInfo1= carrierDao.SelectCarrierSN(argSaveCarrierInfoReqBD02.getVenderSN());
            if(carrierInfo1!=null&&!carrierInfo1.getVenderSN().equals(objFactoryInfo.getVenderSN())){
                throw new SystemException("", "更新失败，序列号已存在");
            }


            objFactoryInfo.setVenderSN(argSaveCarrierInfoReqBD02.getVenderSN());
        }else if(codeRuleInfo!=null && "00".equals(codeRuleInfo.getStatus())){
            SN=gConfigIService.GetCreateSC(codeRuleInfo);
            objFactoryInfo.setVenderSN(SN);
        }else{
            throw new SystemException("", "请输入载具家族序号，或请到全局配置进行代码生成配置");
        }*/

        CarrierInfo carrierInfo1= carrierDao.SelectCarrierSN(argSaveCarrierInfoReqBD02.getVenderSN());
        if(carrierInfo1!=null&&!carrierInfo1.getVenderSN().equals(objFactoryInfo.getVenderSN())){
            throw new SystemException("", "更新失败，序列号已存在");
        }


        objFactoryInfo.setVenderSN(argSaveCarrierInfoReqBD02.getVenderSN());


        //根据状态模型ID查询状态模型信息         //2019-5-23
        DevSModeInfo devSModeInfo=devSModeDAO.SelectDevSModeInfo(argSaveCarrierInfoReqBD02.getDSMRd());
        if(devSModeInfo!=null){

        if(devSModeInfo.getGuid()!=objFactoryInfo.getDevSMGd()){
            objFactoryInfo.setDevSGD(devSModeInfo.getDSGd());
        }
            objFactoryInfo.setDevSMGd(devSModeInfo.getGuid());
        }else {
            objFactoryInfo.setDevSMGd("");
        }


        //根据工厂标识 查询工厂信息
        FactoryInfo factoryInfo= factoryDAO.SelectFactoryInfoByruid(argSaveCarrierInfoReqBD02.getFaRd());
        if(factoryInfo!=null){
            objFactoryInfo.setFaGd(factoryInfo.getGuid());
        }else {
            objFactoryInfo.setFaGd("");
        }

        objFactoryInfo.setRemark(argSaveCarrierInfoReqBD02.getRemark());
        objFactoryInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        objFactoryInfo.setLastModifyTime(new Date());

        if(carrierDao.UpdateCarrierInfoByruid(objFactoryInfo)<=0){
            throw new SystemException("MG_MES3001112030003F","更新载具信息失败");
        }
        objESaveFaInfoResB.setData(objESaveFaInfoResD);
        objESaveFaInfoRes.setBody(objESaveFaInfoResB);

        return objESaveFaInfoRes;
    }

    //dto复制
    @Override
    public SaveCarrierInfoRes AddSaveCarrierInfoRes(SaveCarrierInfoReqBD03 argSaveCarrierInfoReqBD03) {
        SaveCarrierInfoRes objESaveFaInfoRes=new SaveCarrierInfoRes();
        SaveCarrierInfoResB objESaveFaInfoResB=new SaveCarrierInfoResB();
        SaveCarrierInfoResD objESaveFaInfoResD=new SaveCarrierInfoResD();


        //根据页面传过来的载具id 查询载具信息
        CarrierInfo objFactoryInfo=carrierDao.SelectCarrierInfoByruid(argSaveCarrierInfoReqBD03.getCarrierRd());
        if(objFactoryInfo==null){
            throw new SystemException("MG_MES3001112040001F","查询载具信息失败");
        }

        objFactoryInfo.setGuid(CommonUtils.getRandomNumber());
        objFactoryInfo.setCreateTime(new Date());
        objFactoryInfo.setLastModifyTime(new Date());
        objFactoryInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        objFactoryInfo.setCreator(CommonUtils.readUser().getRealName());
        carrierDao.InsertCarrierInfo(objFactoryInfo);

        CarrierInfo objEFactoryInfo=carrierDao.SelectCarrierInfoByguid(objFactoryInfo.getGuid());
        if(objEFactoryInfo==null){
            throw new SystemException("MG_MES3001112040003F","查询载具信息失败");
        }

        objEFactoryInfo.setCarrierName(objEFactoryInfo.getCarrierName()+objEFactoryInfo.getRuid());
        if(carrierDao.UpdateCarrierInfoByruid(objEFactoryInfo)<=0){
            throw new SystemException("MG_MES3001112040004F","更新载具名称信息失败");
        }

        objESaveFaInfoResB.setData(objESaveFaInfoResD);
        objESaveFaInfoRes.setBody(objESaveFaInfoResB);

        return objESaveFaInfoRes;
    }
}
