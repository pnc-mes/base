package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.MVDtlDAO;
import pnc.mesadmin.dao.MVTKDAO;
import pnc.mesadmin.dao.MaterialDAO;
import pnc.mesadmin.dao.StoreDAO;
import pnc.mesadmin.dto.GetAllMVMaInfo.GetAllMVMaInfoRes;
import pnc.mesadmin.dto.GetAllMVMaInfo.GetAllMVMaInfoResB;
import pnc.mesadmin.dto.GetAllMVMaInfo.GetAllMVMaInfoResD;
import pnc.mesadmin.dto.GetMVMaInfo.*;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.entity.MVDtlInfo;
import pnc.mesadmin.entity.MVTKInfo;
import pnc.mesadmin.entity.MaterialInfo;
import pnc.mesadmin.entity.StoreInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.MVIService;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * Created by zhangliangliang on 2017/11/2.
 */
@Service
@Transactional
public class MVService implements MVIService {
    @Resource
    private MVTKDAO mvTkDAO;

    @Resource
    private MVDtlDAO mvDtlDAO;

    @Resource
    private StoreDAO storeDAO;

    @Resource
    private BaseIService baseIService;

    @Resource
    private MaterialDAO materialDAO;

    //调拨列表
    @Override
    public GetAllMVMaInfoRes QALLGetAllMVMaInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllMVMaInfoRes GetGetAllMVMaInfoRes=new GetAllMVMaInfoRes();
        GetAllMVMaInfoResB GetGetAllMVMaInfoResB=new GetAllMVMaInfoResB();;
        List<GetAllMVMaInfoResD> objEGetAllMVMaInfoResDs=new ArrayList<GetAllMVMaInfoResD>();

        List<MVTKInfo> objEMaVerInfo= baseIService.QALLBaseInfo(MVTKDAO.class, "SelectMVTkInfo",
                argInitDataFields, argPageInfo);

        if (objEMaVerInfo!=null&&objEMaVerInfo.size()>0){
            for(MVTKInfo obj:objEMaVerInfo){
                GetAllMVMaInfoResD objGetAllMVMaInfoResD=new GetAllMVMaInfoResD();
                objGetAllMVMaInfoResD.setMVRd(obj.getRuid());
                objGetAllMVMaInfoResD.setMVCode(obj.getmVCode());
                objEGetAllMVMaInfoResDs.add(objGetAllMVMaInfoResD);
            }
        }
        GetGetAllMVMaInfoResB.setData(objEGetAllMVMaInfoResDs);
        GetGetAllMVMaInfoRes.setBody(GetGetAllMVMaInfoResB);
        return GetGetAllMVMaInfoRes;
    }

    //查询调拨信息
    @Override
    public GetMVMaInfoRes GetGetMVMaInfoRes(GetMVMaInfoReq00 argGetMVMaInfoReq00) {
        GetMVMaInfoRes obJEGetMVMaInfoRes=new GetMVMaInfoRes();
        GetMVMaInfoResB objEGetMVMaInfoResB=new GetMVMaInfoResB();
        GetMVMaInfoResD objEGetMVMaInfoResD=new GetMVMaInfoResD();
        List<GetMVMaInfoResDMVDtl> objEGetMVMaInfoResDMVDtls=new ArrayList<GetMVMaInfoResDMVDtl>();


        MVTKInfo MVTKInfo =mvTkDAO.SelectMVTkInfoByRuid(argGetMVMaInfoReq00.getMVRd());
        if(MVTKInfo !=null){
            List<MVDtlInfo> objEMVDtlInfos=mvDtlDAO.SelectMVDtlInfoBymVTkGd(MVTKInfo.getGuid());
            if(objEMVDtlInfos!=null&&objEMVDtlInfos.size()>0){
                for(MVDtlInfo obj:objEMVDtlInfos){
                    GetMVMaInfoResDMVDtl objEGetMVMaInfoResDMVDtl=new GetMVMaInfoResDMVDtl();
                    MaterialInfo objEMaterialInfo=materialDAO.SelectByMaCode(obj.getMaterialCode());
                    objEGetMVMaInfoResDMVDtl.setMaCode(obj.getMaterialCode());
                    objEGetMVMaInfoResDMVDtl.setMaName(obj.getMaterialName());
                    if(objEMaterialInfo.getMaterialDes()!=null && !objEMaterialInfo.getMaterialDes().equals("")) {
                        objEGetMVMaInfoResDMVDtl.setMaDes(objEMaterialInfo.getMaterialDes());
                    }else{
                        objEGetMVMaInfoResDMVDtl.setMaDes("");
                    }
                    if(obj.getBatch()!=null){
                        objEGetMVMaInfoResDMVDtl.setBatch(obj.getBatch());
                    }else {
                        objEGetMVMaInfoResDMVDtl.setBatch("");
                    }

                    StoreInfo storeInfo=storeDAO.SelectByStoreGd(obj.getmOStoreGd());
                    if(storeInfo!=null){
                        objEGetMVMaInfoResDMVDtl.setMOSName(storeInfo.getStoreName());
                    }
                    objEGetMVMaInfoResDMVDtl.setMONum(obj.getmONum());
                    StoreInfo storeInfo1=storeDAO.SelectByStoreGd(obj.getmIStoreGd());
                    if(storeInfo1!=null){
                        objEGetMVMaInfoResDMVDtl.setMISName(storeInfo1.getStoreName());
                    }
                    objEGetMVMaInfoResDMVDtl.setMINum(obj.getmINum());
                    objEGetMVMaInfoResDMVDtls.add(objEGetMVMaInfoResDMVDtl);
                }
            }
            objEGetMVMaInfoResD.setMVDtlInfo(objEGetMVMaInfoResDMVDtls);
            objEGetMVMaInfoResD.setMVRd(MVTKInfo.getRuid());
            objEGetMVMaInfoResD.setMVCode(MVTKInfo.getmVCode());
            objEGetMVMaInfoResD.setExStatus(MVTKInfo.getExStatus());
            objEGetMVMaInfoResD.setCreator(MVTKInfo.getCreator());
            objEGetMVMaInfoResD.setCreateTime(DateUtil.format(MVTKInfo.getCreateTime()));
            objEGetMVMaInfoResD.setExecor(MVTKInfo.getExecor());
            objEGetMVMaInfoResD.setExecTime(DateUtil.format(MVTKInfo.getExecTime()));
            objEGetMVMaInfoResD.setCancelor(MVTKInfo.getCancelor());
            objEGetMVMaInfoResD.setCancelTime(DateUtil.format(MVTKInfo.getCancelTime()));
            objEGetMVMaInfoResD.setFinishor(MVTKInfo.getFinishor());
            objEGetMVMaInfoResD.setFinishTime(DateUtil.format(MVTKInfo.getFinishTime()));
            objEGetMVMaInfoResD.setRemark(MVTKInfo.getRemark());
        }
        objEGetMVMaInfoResB.setData(objEGetMVMaInfoResD);
        obJEGetMVMaInfoRes.setBody(objEGetMVMaInfoResB);
        return obJEGetMVMaInfoRes;
    }
}
