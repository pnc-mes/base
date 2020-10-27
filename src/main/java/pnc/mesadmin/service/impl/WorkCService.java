package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.FactoryDAO;
import pnc.mesadmin.dao.WorkCenterDAO;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllSpecInfo.GetAllSpecInfoResD;
import pnc.mesadmin.dto.GetAllWCInfo.GetAllWCInfoRes;
import pnc.mesadmin.dto.GetAllWCInfo.GetAllWCInfoResB;
import pnc.mesadmin.dto.GetAllWCInfo.GetAllWCInfoResD;
import pnc.mesadmin.dto.GetWCInfo.GetWCInfoReqBD00;
import pnc.mesadmin.dto.GetWCInfo.GetWCInfoRes;
import pnc.mesadmin.dto.GetWCInfo.GetWCInfoResB;
import pnc.mesadmin.dto.GetWCInfo.GetWCInfoResD;
import pnc.mesadmin.dto.SaveWCInfo.*;
import pnc.mesadmin.entity.FactoryInfo;
import pnc.mesadmin.entity.SpecInfo;
import pnc.mesadmin.entity.SpecVerInfo;
import pnc.mesadmin.entity.WorkCenterInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.WorkCIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;
import pnc.mesadmin.utils.MyPage;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工作中心Service实现层
 * 创建人：张亮亮
 * 创建时间：2017-05-27
 * 修改人：
 * 修改时间：
 */
@Transactional
@Service
public class WorkCService implements WorkCIService {

    @Resource
    private WorkCenterDAO workCenterDAO;

    @Resource
    private FactoryDAO factoryDAO;

    @Resource
    private BaseIService baseIService;

    //dto查询工作中心列表
    public GetAllWCInfoRes QALLGetAllWCInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllWCInfoRes objEGetAllWCInfoRes=new GetAllWCInfoRes();
        GetAllWCInfoResB objEGetAllWCInfoResB=new GetAllWCInfoResB();
        List<GetAllWCInfoResD> objEGetAllWCInfoResDs=new ArrayList<GetAllWCInfoResD>();

        List<WorkCenterInfo> objEWorkCenterInfos= baseIService.QALLBaseInfo(WorkCenterDAO.class, "SelectWorkCenterInfo",
                argInitDataFields, argPageInfo);

        if(objEWorkCenterInfos!=null||objEWorkCenterInfos.size()>0) {

            for (WorkCenterInfo obj : objEWorkCenterInfos) {
                GetAllWCInfoResD objGetAllWCInfoResD = new GetAllWCInfoResD();
                objGetAllWCInfoResD.setWCRd(obj.getRuid());
                objGetAllWCInfoResD.setWCName(obj.getCenterName());
                //根据工作中心里的FaGd 到工厂表中查询工作信息
                FactoryInfo objFactoryInfo = factoryDAO.SelectFactoryInfoByguid(obj.getFaGd());
                if(objFactoryInfo!=null){
                    objGetAllWCInfoResD.setFaName(objFactoryInfo.getFactoryName());
                }

                objEGetAllWCInfoResDs.add(objGetAllWCInfoResD);
            }
        }
        objEGetAllWCInfoResB.setData(objEGetAllWCInfoResDs);
        objEGetAllWCInfoRes.setBody(objEGetAllWCInfoResB);

        return objEGetAllWCInfoRes;
    }

    @Override
    public PageResult<GetAllWCInfoResD> QALLGetAllWCInfoRes(BaseRequest req) {
        List<GetAllWCInfoResD> objEGetAllWCInfoResDs=new ArrayList<GetAllWCInfoResD>();
        GetAllWCInfoResD resD = null;
        FactoryInfo objEFactoryInfo = null;

        IPage<WorkCenterInfo> iPage = workCenterDAO.selectPage(new MyPage(req), CommonUtils.getQueryWrapper(req.getFields()));

        //赋值dto并返回
        for (WorkCenterInfo obj : iPage.getRecords()) {
            resD = new GetAllWCInfoResD();
            resD.setWCRd(obj.getRuid());
            resD.setWCName(obj.getCenterName());
            objEFactoryInfo = factoryDAO.SelectFactoryInfoByguid(obj.getGuid());
            if(objEFactoryInfo != null){
                resD.setFaName(objEFactoryInfo.getFactoryName());
            }

            objEGetAllWCInfoResDs.add(resD);
        }

        return new PageResult<>(iPage, objEGetAllWCInfoResDs);
    }

    //dto根据穿过来的WCRd查询工作中心
    public GetWCInfoRes GetGetWCInfoRes(GetWCInfoReqBD00 argGetWCInfoReqBD00) {
        GetWCInfoRes objEGetWCInfoRes=new GetWCInfoRes();
        GetWCInfoResB objEGetWCInfoResB=new GetWCInfoResB();

        WorkCenterInfo objWorkCenterInfo= workCenterDAO.SelectWorkCenterByruid(argGetWCInfoReqBD00.getWCRd());
        if(objWorkCenterInfo==null){
            throw  new SystemException("MG_MES3001211010001F","查询工作中心信息为空");
        }

        GetWCInfoResD objEGetWCInfoResD=new GetWCInfoResD();
        objEGetWCInfoResD.setWCRd(objWorkCenterInfo.getRuid());
        objEGetWCInfoResD.setWCName(objWorkCenterInfo.getCenterName());
        objEGetWCInfoResD.setCreator(objWorkCenterInfo.getCreator());
        objEGetWCInfoResD.setCreateTime(DateUtil.format(objWorkCenterInfo.getCreateTime()));
        objEGetWCInfoResD.setLastModifyMan(objWorkCenterInfo.getLastModifyMan());
        objEGetWCInfoResD.setLastModifyTime(DateUtil.format(objWorkCenterInfo.getLastModifyTime()));
        objEGetWCInfoResD.setRemark(objWorkCenterInfo.getRemark());
        //获取工作中心的工厂标识 FaRd
        String objgetFaGd=objWorkCenterInfo.getFaGd();
        //根据FaRd 查询工厂信息
        FactoryInfo objFactoryInfo= factoryDAO.SelectFactoryInfoByguid(objgetFaGd);
        if(objFactoryInfo != null) {
            objEGetWCInfoResD.setFaRd(objFactoryInfo.getRuid());
            objEGetWCInfoResD.setFaName(objFactoryInfo.getFactoryName());
        }
        objEGetWCInfoResB.setData(objEGetWCInfoResD);
        objEGetWCInfoRes.setBody(objEGetWCInfoResB);
        return objEGetWCInfoRes;
    }

    //dto新增工作中心
    public SaveWCInfoRes AddSaveWCInfoRes(SaveWCInfoReqBD00 argSaveWCInfoReqBD00) {
        SaveWCInfoRes objESaveWCInfoRes=new SaveWCInfoRes();
        SaveWCInfoResB objESaveWCInfoResB=new SaveWCInfoResB();

        WorkCenterInfo objEWorkCenterInfo=new WorkCenterInfo();
        objEWorkCenterInfo.setGuid(CommonUtils.getRandomNumber());
        List<WorkCenterInfo> objEWorkCenterInfos=workCenterDAO.SelectWorkCenterInfo();
        for(WorkCenterInfo obj:objEWorkCenterInfos){
            if(argSaveWCInfoReqBD00.getWCName().equals(obj.getCenterName())){
                throw  new SystemException("MG_MES3001212010001F","工作中心名称已存在");
            }
        }

        objEWorkCenterInfo.setCenterName(argSaveWCInfoReqBD00.getWCName());
        objEWorkCenterInfo.setRemark(argSaveWCInfoReqBD00.getRemark());
        objEWorkCenterInfo.setCreateTime(new Date());
        objEWorkCenterInfo.setCreator(CommonUtils.readUser().getRealName());
        //根据传过来的工厂标识查询工厂信息
        FactoryInfo objEFactoryInfo= factoryDAO.SelectFactoryInfoByruid(argSaveWCInfoReqBD00.getFaRd());
        if(objEFactoryInfo==null){
            throw  new SystemException("MG_MES3001212010002F","查询工厂信息失败");
        }
        objEWorkCenterInfo.setFaGd(objEFactoryInfo.getGuid());

        workCenterDAO.InsertWorkCenterInfo(objEWorkCenterInfo);
        objESaveWCInfoRes.setBody(objESaveWCInfoResB);
        return objESaveWCInfoRes;
    }
    //dto复制工作中心
    public SaveWCInfoRes AddSaveWCInfoRes(SaveWCInfoReqBD03 argSaveWCInfoReqBD03) {
        SaveWCInfoRes objESaveWCInfoRes=new SaveWCInfoRes();
        SaveWCInfoResB objESaveWCInfoResB=new SaveWCInfoResB();
        WorkCenterInfo objEWorkCenterInfo= workCenterDAO.SelectWorkCenterByruid(argSaveWCInfoReqBD03.getWCRd());
        if(objEWorkCenterInfo==null){
            throw  new SystemException("MG_MES3001212040001F","查询工作中心失败");
        }
        objEWorkCenterInfo.setGuid(CommonUtils.getRandomNumber());
        objEWorkCenterInfo.setLastModifyTime(new Date());
        objEWorkCenterInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        objEWorkCenterInfo.setCreateTime(new Date());
        objEWorkCenterInfo.setCreator(CommonUtils.readUser().getRealName());
        workCenterDAO.InsertWorkCenterInfo(objEWorkCenterInfo);
        WorkCenterInfo objEWorkCenterInfos=workCenterDAO.SelectWorkCenterByguid(objEWorkCenterInfo.getGuid());
        if(objEWorkCenterInfos==null){
            throw  new SystemException("MG_MES3001212040002F","查询工作中心失败");
        }
        objEWorkCenterInfos.setCenterName(objEWorkCenterInfos.getCenterName()+objEWorkCenterInfos.getRuid());
       if(workCenterDAO.UpdateWorkCenterInfo(objEWorkCenterInfos)<=0){
           throw  new SystemException("MG_MES3001212040003F","复制工作中心失败");
       }

        objESaveWCInfoRes.setBody(objESaveWCInfoResB);
        return objESaveWCInfoRes;
    }

    //dto更新工作中心
    public SaveWCInfoRes ModSaveWCInfoRes(SaveWCInfoReqBD02 argSaveWCInfoReqBD02) {
        SaveWCInfoRes objESaveWCInfoRes=new SaveWCInfoRes();
        SaveWCInfoResB objESaveWCInfoResB=new SaveWCInfoResB();

        WorkCenterInfo objEWorkCenterInfo=workCenterDAO.SelectWorkCenterByruid(argSaveWCInfoReqBD02.getWCRd());
        objEWorkCenterInfo.setRuid(argSaveWCInfoReqBD02.getWCRd());

        //名称不允许重复
        WorkCenterInfo workCenterInfoname=workCenterDAO.SelectWorkCenterByCenterName(argSaveWCInfoReqBD02.getWCName());

        if(workCenterInfoname!=null&&!workCenterInfoname.getCenterName().equals(objEWorkCenterInfo.getCenterName())){
            throw new SystemException("MG_MES3001013030002F","更新失败，工作中心名称已存在");
        }

        objEWorkCenterInfo.setCenterName(argSaveWCInfoReqBD02.getWCName());
        objEWorkCenterInfo.setRemark(argSaveWCInfoReqBD02.getRemark());
        objEWorkCenterInfo.setLastModifyTime(new Date());
        objEWorkCenterInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        FactoryInfo objEFactoryInfo= factoryDAO.SelectFactoryInfoByruid(argSaveWCInfoReqBD02.getFaRd());
        if(objEFactoryInfo==null){
            throw  new SystemException("MG_MES3001212030002F","查询工厂信息失败");
        }
        objEWorkCenterInfo.setFaGd(objEFactoryInfo.getGuid());

        if(workCenterDAO.UpdateWorkCenterInfo(objEWorkCenterInfo)<=0){
            throw  new SystemException("MG_MES3001212030003F","更新工作中心失败");
        }
        objESaveWCInfoRes.setBody(objESaveWCInfoResB);
        return objESaveWCInfoRes;
    }

    //dto删除工作中心
    public SaveWCInfoRes RmSaveWCInfoRes(SaveWCInfoReqBD01 argSaveWCInfoReqBD01) {
        SaveWCInfoRes objESaveWCInfoRes=new SaveWCInfoRes();
        SaveWCInfoResB objESaveWCInfoResB=new SaveWCInfoResB();

        if(workCenterDAO.DeleteWorkCenterInfo(argSaveWCInfoReqBD01.getWCRd())<=0){
            throw  new SystemException("MG_MES3001212020001F","删除工作中心失败");
        }
        objESaveWCInfoRes.setBody(objESaveWCInfoResB);
        return objESaveWCInfoRes;
    }
}
