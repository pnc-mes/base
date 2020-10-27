package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllCarrierFamilyInfo.GetAllCarrierFamilyInfoRes;
import pnc.mesadmin.dto.GetAllCarrierFamilyInfo.GetAllCarrierFamilyInfoResB;
import pnc.mesadmin.dto.GetAllCarrierFamilyInfo.GetAllCarrierFamilyInfoResD;
import pnc.mesadmin.dto.GetAllSpecInfo.GetAllSpecInfoResD;
import pnc.mesadmin.dto.GetCarrierFamilyInfo.*;
import pnc.mesadmin.dto.SaveCarrierFamilyInfo.*;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.CarrierFamilyIService;
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
 * 子系统名称：载具家族信息Service层
 * 创建人：郝赞
 * 创建时间：2018-6-19
 * 修改人：
 * 修改时间：
 */
@Service
@Transactional
public class CarrierFamilyService implements CarrierFamilyIService {

    @Resource
    private FactoryDAO factoryDAO;

    @Resource
    private ToolFamilyDAO toolFamilyDAO;
    @Resource
    private CarrierFamilyDAO carrierFamilyDAO;
    @Resource
    private DevSModeDAO devSModeDAO;

    @Resource
    private CarrierFamilyPMDAO carrierFamilyPMDAO;

    @Resource
    private BaseIService baseIService;

    @Resource
    private CarrierPMDAO carrierPMDAO;

    @Resource
    private CyclePlanDAO cyclePlanDAO;

    @Resource
    private FrePlanDAO frePlanDAO;

    //dto  获取载具家族列表信息
    @Override
    public GetAllCarrierFamilyInfoRes QALLGetAllFaInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllCarrierFamilyInfoRes data=new GetAllCarrierFamilyInfoRes();
        GetAllCarrierFamilyInfoResB dataB=new GetAllCarrierFamilyInfoResB();
        List<GetAllCarrierFamilyInfoResD> dataDs=new ArrayList<GetAllCarrierFamilyInfoResD>();

        List<CarrierFamilyInfo> lists= baseIService.QALLBaseInfo(CarrierFamilyDAO.class, "selectCarrierFamilyAll",
                argInitDataFields, argPageInfo);

        if(lists!=null||lists.size()>0) {

            for (CarrierFamilyInfo obj : lists) {
                GetAllCarrierFamilyInfoResD dataD = new GetAllCarrierFamilyInfoResD();
                dataD.setCarrierFamilyRd(obj.getRuid());
                if(obj.getCarrierFamilyName()==null){
                    break;
                }
                dataD.setCarrierFamilyName(obj.getCarrierFamilyName());
                dataDs.add(dataD);
            }

        }
        dataB.setData(dataDs);
        data.setBody(dataB);

        return data;
    }

    @Override
    public PageResult<GetAllSpecInfoResD> QALLGetAllCarrierFamilyNewRes(BaseRequest req) {
        List<GetAllCarrierFamilyInfoResD> dataList = new ArrayList<>();
        GetAllCarrierFamilyInfoResD resD = null;

        if(req.getSize() <= 0){
            req.setSize(1000);
        }

        Page<CarrierFamilyInfo> page = new Page<>(req.getCurrent(), req.getSize());

        IPage<CarrierFamilyInfo> carrierFamilyInfoIPage = carrierFamilyDAO.selectPage(new MyPage(req), CommonUtils.getQueryWrapper(req.getFields()));

        //获取载具家族列表信息
        List<CarrierFamilyInfo> carrierFamilyInfos = carrierFamilyInfoIPage.getRecords();
        for (CarrierFamilyInfo obj : carrierFamilyInfos){
            resD = new GetAllCarrierFamilyInfoResD();
            resD.setCarrierFamilyRd(obj.getRuid());
            resD.setCarrierFamilyName(obj.getCarrierFamilyName());
            dataList.add(resD);
        }

        return new PageResult(carrierFamilyInfoIPage.getTotal(), carrierFamilyInfoIPage.getPages(), carrierFamilyInfoIPage.getCurrent(), carrierFamilyInfoIPage.getSize(), dataList);
    }

    //dto查询载具家族信息 根据传过来的CarrierRd
    @Override
    public GetCarrierFamilyInfoRes GetGetCarrierFamilyInfoRes(GetCarrierFamilyInfoReqBD00 argGetCarrierFamilyInfoReqBD00) {
        GetCarrierFamilyInfoRes objEGetFaInfoRes=new GetCarrierFamilyInfoRes();
        GetCarrierFamilyInfoResB objEGetFaInfoResB=new GetCarrierFamilyInfoResB();
        //根据页面传过来的载具id 查询载具信息
        CarrierFamilyInfo objFactoryInfo=carrierFamilyDAO.selectCarrierFamilyID(argGetCarrierFamilyInfoReqBD00.getCarrierFamilyRd());

        if(objFactoryInfo!=null) {
            GetCarrierFamilyInfoResD objEGetFaInfoResD = new GetCarrierFamilyInfoResD();
            objEGetFaInfoResD.setCarrierFamilyRd(objFactoryInfo.getRuid());
            objEGetFaInfoResD.setCarrierFamilyName(objFactoryInfo.getCarrierFamilyName());
            objEGetFaInfoResD.setDescription(objFactoryInfo.getDescription());

            //根据工厂标识 查询工厂信息
            FactoryInfo factoryInfo= factoryDAO.SelectFactoryInfoByguid(objFactoryInfo.getFaGd());
            if(factoryInfo!=null){
                GetCarrierFamilyInfoResD.Factory factory=new GetCarrierFamilyInfoResD.Factory();
                factory.setFaRd(factoryInfo.getRuid());
                factory.setFaName(factoryInfo.getFactoryName());
                objEGetFaInfoResD.setFactory(factory);
            }

            //根据状态模型ID查询状态模型信息

            DevSModeInfo devSModeInfo=devSModeDAO.SelectDevSModeInfoguid(objFactoryInfo.getDevSMGd());
            if(devSModeInfo!=null){
                GetCarrierFamilyInfoResD.Modle modle=new GetCarrierFamilyInfoResD.Modle();
                modle.setDSMRd(devSModeInfo.getRuid());
                modle.setModelName(devSModeInfo.getModelName());
                objEGetFaInfoResD.setModle(modle);
            }

            //保养计划
            List<GetCarrierFamilyInfoResDPM>  getCarrierInfoResDPMS=new ArrayList<GetCarrierFamilyInfoResDPM>();
            List<CarrierFamilyPMInfo> carrierFamilyPMInfos=carrierFamilyPMDAO.selectCarrierFamilyPMInfoByCarrierFamilyGd(objFactoryInfo.getGuid());
            if(carrierFamilyPMInfos!=null&&carrierFamilyPMInfos.size()>0){
                for(CarrierFamilyPMInfo carrierFamilyPMInfo:carrierFamilyPMInfos){
                    GetCarrierFamilyInfoResDPM getCarrierFamilyInfoResDPM=new GetCarrierFamilyInfoResDPM();

                    if(carrierFamilyPMInfo.getpMType().equals("00")){
                        CyclePlanInfo cyclePlanInfo =cyclePlanDAO.selectCyclePlanInfoByGuid(carrierFamilyPMInfo.getpMGd());
                        if(cyclePlanInfo == null){
                            continue;
                        }
                        getCarrierFamilyInfoResDPM.setPMType(carrierFamilyPMInfo.getpMType());
                        getCarrierFamilyInfoResDPM.setPMName(cyclePlanInfo.getCyclePlanName());
                        getCarrierFamilyInfoResDPM.setPMRd(cyclePlanInfo.getRuid());
                    }
                    if(carrierFamilyPMInfo.getpMType().equals("01")){
                        FrePlanInfo frePlanInfo =frePlanDAO.selectFrePlanByGuid(carrierFamilyPMInfo.getpMGd());
                        if(frePlanInfo == null){
                            continue;
                        }
                        getCarrierFamilyInfoResDPM.setPMType(carrierFamilyPMInfo.getpMType());
                        getCarrierFamilyInfoResDPM.setPMName(frePlanInfo.getFrePlanName());
                        getCarrierFamilyInfoResDPM.setPMRd(frePlanInfo.getRuid());
                    }

                    getCarrierInfoResDPMS.add(getCarrierFamilyInfoResDPM);
                }
                objEGetFaInfoResD.setPMInfo(getCarrierInfoResDPMS);
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

    //dto. 保存载具家族
    @Override
    public SaveCarrierFamilyInfoRes AddGetCarrierFamilyInfoRes(SaveCarrierFamilyInfoReqBD00 argSaveCarrierFamilyInfoReqBD00) {
        SaveCarrierFamilyInfoRes objESaveFaInfoRes=new SaveCarrierFamilyInfoRes();
        SaveCarrierFamilyInfoResB objESaveFaInfoResB=new SaveCarrierFamilyInfoResB();
        SaveCarrierFamilyInfoResD objESaveFaInfoResD=new SaveCarrierFamilyInfoResD();

        //创建载具家族实体类
        CarrierFamilyInfo objFactoryInfo=new CarrierFamilyInfo();
        objFactoryInfo.setGuid(CommonUtils.getRandomNumber());
        if(argSaveCarrierFamilyInfoReqBD00.getCarrierFamilyName()==null||"".equals(argSaveCarrierFamilyInfoReqBD00.getCarrierFamilyName())){
            throw new SystemException("MG_MES3001112010001F","新增失败，载具家族名称不能为空");
        }


        //查询所有，判断名称是否存在
        CarrierFamilyInfo objEFactoryInfo=carrierFamilyDAO.SelectCarrierInfoByToolFaName(argSaveCarrierFamilyInfoReqBD00.getCarrierFamilyName());

        if(objEFactoryInfo!=null){
            throw new SystemException("MG_MES3001112010001F","新增失败，载具家族名称已存在");
        }


        //设备保养
        if(argSaveCarrierFamilyInfoReqBD00.getPMInfo().size()>0){
            for(SaveCarrierFamilyInfoReqBD00PM saveCarrierFamilyInfoReqBD00PM:argSaveCarrierFamilyInfoReqBD00.getPMInfo()){
                CarrierFamilyPMInfo carrierFamilyPMInfo=new CarrierFamilyPMInfo();
                carrierFamilyPMInfo.setGuid(CommonUtils.getRandomNumber());
                carrierFamilyPMInfo.setCarrierFamilyGd(objFactoryInfo.getGuid());
                if("00".equals(saveCarrierFamilyInfoReqBD00PM.getPMType())){
                    CyclePlanInfo cyclePlanInfo=cyclePlanDAO.selectCyclePlanInfoByRuid(saveCarrierFamilyInfoReqBD00PM.getPMRd());
                    if(cyclePlanInfo!=null){
                        carrierFamilyPMInfo.setpMGd(cyclePlanInfo.getGuid());
                    }
                    carrierFamilyPMInfo.setpMType(saveCarrierFamilyInfoReqBD00PM.getPMType());
                }
                if("01".equals(saveCarrierFamilyInfoReqBD00PM.getPMType())){
                    FrePlanInfo frePlanInfo=frePlanDAO.selectFrePlan(saveCarrierFamilyInfoReqBD00PM.getPMRd());
                    if(frePlanInfo!=null){
                        carrierFamilyPMInfo.setpMGd(frePlanInfo.getGuid());
                    }
                    carrierFamilyPMInfo.setpMType(saveCarrierFamilyInfoReqBD00PM.getPMType());
                }
                carrierFamilyPMDAO.insertCarrierFamilyPMInfo(carrierFamilyPMInfo);
            }
        }


        objFactoryInfo.setCarrierFamilyName(argSaveCarrierFamilyInfoReqBD00.getCarrierFamilyName());

        objFactoryInfo.setDescription(argSaveCarrierFamilyInfoReqBD00.getDescription());

        //根据工厂标识 查询工厂信息     乔帅阳修改
        FactoryInfo factoryInfo= factoryDAO.SelectFactoryInfoByruid(argSaveCarrierFamilyInfoReqBD00.getFaRd());
            if(factoryInfo!=null){
                objFactoryInfo.setFaGd(factoryInfo.getGuid());
            }
        //根据状态模型ID查询状态模型信息   乔帅阳修改

        DevSModeInfo devSModeInfo=devSModeDAO.SelectDevSModeInfo(argSaveCarrierFamilyInfoReqBD00.getDSMRd());
        if(devSModeInfo!=null){
            objFactoryInfo.setDevSMGd(devSModeInfo.getGuid());
        }

        objFactoryInfo.setCreator(CommonUtils.readUser().getRealName());
        objFactoryInfo.setCreateTime(new Date());
        objFactoryInfo.setRemark(argSaveCarrierFamilyInfoReqBD00.getRemark());
        carrierFamilyDAO.InsertCarrierFamilyInfo(objFactoryInfo);
        objESaveFaInfoResB.setData(objESaveFaInfoResD);
        objESaveFaInfoRes.setBody(objESaveFaInfoResB);

        return objESaveFaInfoRes;
    }


    //dto删除
    @Override
    public SaveCarrierFamilyInfoRes RmSaveCarrierFamilyInfoRes(SaveCarrierFamilyInfoReqBD01 argSaveCarrierFamilyInfoReqBD01) {
        SaveCarrierFamilyInfoRes objESaveFaInfoRes=new SaveCarrierFamilyInfoRes();
        SaveCarrierFamilyInfoResB objESaveFaInfoResB=new SaveCarrierFamilyInfoResB();
        SaveCarrierFamilyInfoResD objESaveFaInfoResD=new SaveCarrierFamilyInfoResD();
        CarrierFamilyInfo carrierFamilyInfo=carrierFamilyDAO.selectCarrierFamilyID(argSaveCarrierFamilyInfoReqBD01.getCarrierFamilyRd());

        if(carrierFamilyInfo!=null){
            if(carrierFamilyDAO.DeleteCarrierFamilyInfoByruid(argSaveCarrierFamilyInfoReqBD01.getCarrierFamilyRd())<=0){
                throw new SystemException("MG_MES3001112020001F","删除载具家族信息失败");
            }
            List<CarrierFamilyPMInfo> carrierFamilyPMInfos=carrierFamilyPMDAO.selectCarrierFamilyPMInfoByCarrierFamilyGd(carrierFamilyInfo.getGuid());
            if(carrierFamilyPMInfos!=null&&carrierFamilyPMInfos.size()>0){
                for(CarrierFamilyPMInfo carrierFamilyPMInfo:carrierFamilyPMInfos){
                    if(carrierFamilyPMDAO.deleteCarrierFamilyPMInfo(carrierFamilyPMInfo.getRuid())<0){
                        throw new SystemException("xxx","删除失败");
                    }
                }
            }

        }





        objESaveFaInfoResB.setData(objESaveFaInfoResD);
        objESaveFaInfoRes.setBody(objESaveFaInfoResB);
        return objESaveFaInfoRes;
    }

    //dto更新   修改乔帅阳
    @Override
    public SaveCarrierFamilyInfoRes ModSaveCarrierFamilyInfoRes(SaveCarrierFamilyInfoReqBD02 argSaveCarrierFamilyInfoReqBD02) {
        SaveCarrierFamilyInfoRes objESaveFaInfoRes=new SaveCarrierFamilyInfoRes();
        SaveCarrierFamilyInfoResB objESaveFaInfoResB=new SaveCarrierFamilyInfoResB();
        SaveCarrierFamilyInfoResD objESaveFaInfoResD=new SaveCarrierFamilyInfoResD();


        if(argSaveCarrierFamilyInfoReqBD02.getCarrierFamilyName()==null||"".equals(argSaveCarrierFamilyInfoReqBD02.getCarrierFamilyName())){
            throw new SystemException("MG_MES3001112010001F","修改失败，载具家族名称不能为空");
        }

        //根据页面传过来的载具家族id 查询载具家族信息
        CarrierFamilyInfo objFactoryInfo=carrierFamilyDAO.selectCarrierFamilyID(argSaveCarrierFamilyInfoReqBD02.getCarrierFamilyRd());

        CarrierFamilyInfo objEFactoryInfo1=carrierFamilyDAO.SelectCarrierInfoByToolFaName(argSaveCarrierFamilyInfoReqBD02.getCarrierFamilyName());
        if(objEFactoryInfo1!=null&&!objFactoryInfo.getCarrierFamilyName().equals(argSaveCarrierFamilyInfoReqBD02.getCarrierFamilyName())){
            throw new SystemException("xxx","修改失败，载具家族名称已存在");
        }


        List<CarrierFamilyPMInfo> carrierFamilyPMInfos=carrierFamilyPMDAO.selectCarrierFamilyPMInfoByCarrierFamilyGd(objFactoryInfo.getGuid());
        if(carrierFamilyPMInfos!=null&&carrierFamilyPMInfos.size()>0){
            for(CarrierFamilyPMInfo carrierFamilyPMInfo:carrierFamilyPMInfos){
                if(carrierFamilyPMDAO.deleteCarrierFamilyPMInfo(carrierFamilyPMInfo.getRuid())<0){
                    throw new SystemException("xxx","删除失败");
                }
            }
        }

        //设备保养
        if(argSaveCarrierFamilyInfoReqBD02.getPMInfo().size()>0){
            for(SaveCarrierFamilyInfoReqBD02PM saveCarrierFamilyInfoReqBD02PM:argSaveCarrierFamilyInfoReqBD02.getPMInfo()){
                CarrierFamilyPMInfo carrierFamilyPMInfo=new CarrierFamilyPMInfo();
                carrierFamilyPMInfo.setGuid(CommonUtils.getRandomNumber());
                carrierFamilyPMInfo.setCarrierFamilyGd(objFactoryInfo.getGuid());
                if("00".equals(saveCarrierFamilyInfoReqBD02PM.getPMType())){
                    CyclePlanInfo cyclePlanInfo=cyclePlanDAO.selectCyclePlanInfoByRuid(saveCarrierFamilyInfoReqBD02PM.getPMRd());
                    if(cyclePlanInfo!=null){
                        carrierFamilyPMInfo.setpMGd(cyclePlanInfo.getGuid());
                    }
                    carrierFamilyPMInfo.setpMType(saveCarrierFamilyInfoReqBD02PM.getPMType());
                }
                if("01".equals(saveCarrierFamilyInfoReqBD02PM.getPMType())){
                    FrePlanInfo frePlanInfo=frePlanDAO.selectFrePlan(saveCarrierFamilyInfoReqBD02PM.getPMRd());
                    if(frePlanInfo!=null){
                        carrierFamilyPMInfo.setpMGd(frePlanInfo.getGuid());
                    }
                    carrierFamilyPMInfo.setpMType(saveCarrierFamilyInfoReqBD02PM.getPMType());
                }
                carrierFamilyPMDAO.insertCarrierFamilyPMInfo(carrierFamilyPMInfo);
            }
        }

        objFactoryInfo.setRuid(argSaveCarrierFamilyInfoReqBD02.getCarrierFamilyRd());
        //更新的载具家族名称不允许重复
        objFactoryInfo.setCarrierFamilyName(argSaveCarrierFamilyInfoReqBD02.getCarrierFamilyName());
        objFactoryInfo.setDescription(argSaveCarrierFamilyInfoReqBD02.getDescription());
        //根据工厂标识 查询工厂信息

            FactoryInfo factoryInfo = factoryDAO.SelectFactoryInfoByruid(argSaveCarrierFamilyInfoReqBD02.getFaRd());
            if(factoryInfo!=null) {
                objFactoryInfo.setFaGd(factoryInfo.getGuid());
            }else {
                objFactoryInfo.setFaGd("");
            }
       //乔帅阳 修改
        DevSModeInfo devSModeInfo=null;
        if(argSaveCarrierFamilyInfoReqBD02.getDSMRd()!=null){
            devSModeInfo=devSModeDAO.SelectDevSModeInfo(argSaveCarrierFamilyInfoReqBD02.getDSMRd());
            if(devSModeInfo!=null){
                objFactoryInfo.setDevSMGd(devSModeInfo.getGuid());
            }
        }else {
            objFactoryInfo.setDevSMGd("");
        }

        objFactoryInfo.setRemark(argSaveCarrierFamilyInfoReqBD02.getRemark());
        objFactoryInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        objFactoryInfo.setLastModifyTime(new Date());

        if(carrierFamilyDAO.UpdateCarrierFamilyInfoByruid(objFactoryInfo)<=0){
            throw new SystemException("MG_MES3001112030003F","更新载具信息失败");
        }
        objESaveFaInfoResB.setData(objESaveFaInfoResD);
        objESaveFaInfoRes.setBody(objESaveFaInfoResB);

        return objESaveFaInfoRes;
    }

    //dto复制
    @Override
    public SaveCarrierFamilyInfoRes AddSaveCarrierFamilyInfoRes(SaveCarrierFamilyInfoReqBD03 argSaveCarrierFamilyInfoReqBD03) {
        SaveCarrierFamilyInfoRes objESaveFaInfoRes=new SaveCarrierFamilyInfoRes();
        SaveCarrierFamilyInfoResB objESaveFaInfoResB=new SaveCarrierFamilyInfoResB();
        SaveCarrierFamilyInfoResD objESaveFaInfoResD=new SaveCarrierFamilyInfoResD();

        //根据页面传过来的载具家族id 查询载具家族信息
        CarrierFamilyInfo objFactoryInfo=carrierFamilyDAO.selectCarrierFamilyID(argSaveCarrierFamilyInfoReqBD03.getCarrierFamilyRd());
        if(objFactoryInfo==null){
            throw new SystemException("MG_MES3001112040001F","查询载具信息失败");
        }

        objFactoryInfo.setGuid(CommonUtils.getRandomNumber());
        objFactoryInfo.setCreateTime(new Date());
        objFactoryInfo.setLastModifyTime(new Date());
        objFactoryInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        objFactoryInfo.setCreator(CommonUtils.readUser().getRealName());
        carrierFamilyDAO.InsertCarrierFamilyInfo(objFactoryInfo);

        CarrierFamilyInfo objEFactoryInfo=carrierFamilyDAO.selectCarrierFamily(objFactoryInfo.getGuid());
        if(objEFactoryInfo==null){
            throw new SystemException("MG_MES3001112040003F","查询载具家族信息失败");
        }

        objEFactoryInfo.setCarrierFamilyName(objEFactoryInfo.getCarrierFamilyName()+objEFactoryInfo.getRuid());
        if(carrierFamilyDAO.UpdateCarrierFamilyInfoByruid(objEFactoryInfo)<=0){
            throw new SystemException("MG_MES3001112040004F","更新载具载具名称信息失败");
        }

        objESaveFaInfoResB.setData(objESaveFaInfoResD);
        objESaveFaInfoRes.setBody(objESaveFaInfoResB);

        return objESaveFaInfoRes;
    }
}
