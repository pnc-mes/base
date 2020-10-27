package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import pnc.mesadmin.dao.CmethodDAO;
import pnc.mesadmin.dto.GetAllCMethodInfo.GetAllCMethodInfoRes;
import pnc.mesadmin.dto.GetAllCMethodInfo.GetAllCMethodInfoResB;
import pnc.mesadmin.dto.GetAllCMethodInfo.GetAllCMethodInfoResD;
import pnc.mesadmin.dto.GetCMethodInfoRes.GetCMethodInfoRes;
import pnc.mesadmin.dto.GetCMethodInfoRes.GetCMethodInfoResB;
import pnc.mesadmin.dto.GetCMethodInfoRes.GetCMethodInfoResD;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveCMethodInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.CmethodInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.CheckMethodInfoService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by PNC on 2019/3/22.
 */
@Service
public class CheckMethodServicelmpl implements CheckMethodInfoService {
    @Resource
    private CmethodDAO cmethodDAO;
    @Resource
    private BaseIService baseIService;

    @Override
    public GetAllCMethodInfoRes QALLselectAllCMethodInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException {
        GetAllCMethodInfoRes objGetAllCMethodInfoRes=new GetAllCMethodInfoRes();
        GetAllCMethodInfoResB objGetAllCMethodInfoResB=new GetAllCMethodInfoResB();
        List<GetAllCMethodInfoResD> objGetAllCMethodInfoResD=new ArrayList<GetAllCMethodInfoResD>();

        List<CmethodInfo> objCmethodInfo=  baseIService.QALLBaseInfo(CmethodDAO.class, "SelectAllCmethodInfo",
                argInitDataFields, argPageInfo);
        if(objCmethodInfo!=null||objCmethodInfo.size()>0) {

            //赋值dto并返回
            for (CmethodInfo obj : objCmethodInfo) {
                GetAllCMethodInfoResD getAllCMethodInfoResD = new GetAllCMethodInfoResD();
         //       objEGetAllUnitInfoResD.getCLevelRd(obj.getRuid());
                getAllCMethodInfoResD.setCMethodRd(obj.getRuid());
                getAllCMethodInfoResD.setCheckMethodCode(obj.getCheckMethodCode());
                getAllCMethodInfoResD.setCheckMethodName(obj.getCheckMethodName());
                objGetAllCMethodInfoResD.add(getAllCMethodInfoResD);
            }
        }
        objGetAllCMethodInfoResB.setData(objGetAllCMethodInfoResD);
        objGetAllCMethodInfoRes.setBody(objGetAllCMethodInfoResB);
        return objGetAllCMethodInfoRes;
    }


     //获取检验水平 信息
    @Override
    public GetCMethodInfoRes GetselectByRuid(int CMethodRd) throws SystemException {
        GetCMethodInfoRes objGetCMethodInfoRes = new  GetCMethodInfoRes();

        GetCMethodInfoResB body = new GetCMethodInfoResB();

        GetCMethodInfoResD data = new GetCMethodInfoResD();

        // 获取紧急代码信息
        CmethodInfo cmethodInfo = cmethodDAO.SelectByRuid(CMethodRd);

        if(cmethodInfo!=null ) {
            // 赋值查询紧急代码信息
            data.setCMethodRd(cmethodInfo.getRuid());
            data.setCheckMethodCode(cmethodInfo.getCheckMethodCode());
            data.setCheckMethodName(cmethodInfo.getCheckMethodName());
            data.setCreator(cmethodInfo.getCreator());
            data.setCreateTime(DateUtil.format(cmethodInfo.getCreateTime()));
            data.setLastModifyMan(cmethodInfo.getLastModifyMan());
            data.setLastModifyTime(DateUtil.format(cmethodInfo.getLastModifyTime()));
            data.setRemark(cmethodInfo.getRemark());
        }
        body.setData(data);
        objGetCMethodInfoRes.setBody(body);
        return objGetCMethodInfoRes;
    }

    //新增检验方法信息
    @Override
    public SaveCMethodInfoRes AddinsertCMethodInfo(SaveCMethodInfoReqBD00 busData00,CmethodInfo cmethodInfo) throws SystemException {
        SaveCMethodInfoRes objSaveCMethodInfoRes = new SaveCMethodInfoRes();

        SaveCMethodInfoResB body = new SaveCMethodInfoResB();

        SaveCMethodInfoResD data = new SaveCMethodInfoResD();
       if(busData00.getCheckMethodCode()==null || "".equals(busData00.getCheckMethodCode())){
           throw new SystemException("","检验方法代码不能为空");
       }
        CmethodInfo CmethodInfo1 = cmethodDAO.SelectCheckCmethodInfo(busData00.getCheckMethodCode());
       if(CmethodInfo1!=null){
           throw new SystemException("","检验方法代码重复");
       }
        CmethodInfo CmethodInfo2 = new CmethodInfo();
        CmethodInfo2.setGuid(CommonUtils.getRandomNumber());
        CmethodInfo2.setCheckMethodCode(busData00.getCheckMethodCode());
        CmethodInfo2.setCheckMethodName(busData00.getCheckMethodName());
        CmethodInfo2.setCreator(CommonUtils.readUser().getRealName());
        CmethodInfo2.setCreateTime(new Date());
        CmethodInfo2.setRemark(busData00.getRemark());

       if(cmethodDAO.InsertCmethodInfo(CmethodInfo2)<=0){
           throw new SystemException("","新增失败");
     }
        body.setData(data);
        objSaveCMethodInfoRes.setBody(body);
        return objSaveCMethodInfoRes;
    }

    @Override
    public SaveCMethodInfoRes ModupdateCMethodInfo(SaveCMethodInfoReqBD02 busData02, CmethodInfo cmethodInfo) throws SystemException {
        SaveCMethodInfoRes objSaveCMethodInfoRes = new SaveCMethodInfoRes();

        SaveCMethodInfoResB body = new SaveCMethodInfoResB();

        SaveCMethodInfoResD data = new SaveCMethodInfoResD();
        CmethodInfo objCmethodInfo=cmethodDAO.SelectByRuid(busData02.getCMethodRd());
        if (objCmethodInfo==null){
            throw new SystemException("","检验方法信息为空");
        }
        //查询紧急代码
        CmethodInfo objCmethodInfo1=cmethodDAO.SelectCheckCmethodInfo(busData02.getCheckMethodCode());

        if(objCmethodInfo1!=null && !objCmethodInfo1.getCheckMethodCode().equals(objCmethodInfo.getCheckMethodCode())){
            throw new SystemException("MG0006F","检验方法代码已存在");
        }
        objCmethodInfo.setRuid(busData02.getCMethodRd());
        objCmethodInfo.setCheckMethodCode(busData02.getCheckMethodCode());
        objCmethodInfo.setCheckMethodName(busData02.getCheckMethodName());
        objCmethodInfo.setLastModifyTime(new Date());
        objCmethodInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        objCmethodInfo.setRemark(busData02.getRemark());
       if (cmethodDAO.UpdateCheckCmethodInfo(objCmethodInfo)<=0){
           throw new SystemException("MG0006F","检验方法更新失败");
       }
        body.setData(data);
        objSaveCMethodInfoRes.setBody(body);
        return objSaveCMethodInfoRes;
    }

    @Override
    public SaveCMethodInfoRes RmdeleteCMethodInfo(int ruid) throws SystemException {
        SaveCMethodInfoRes objSaveCMethodInfoRes = new SaveCMethodInfoRes();

        SaveCMethodInfoResB body = new SaveCMethodInfoResB();

        SaveCMethodInfoResD data = new SaveCMethodInfoResD();
      if(cmethodDAO.DeleteCmethodInfo(ruid)<=0){
          throw new SystemException("MG0006F","检验水平清单删除失败");
      }
        body.setData(data);
        objSaveCMethodInfoRes.setBody(body);
        return objSaveCMethodInfoRes;
    }
}
