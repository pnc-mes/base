package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import pnc.mesadmin.dao.ClevelDAO;
import pnc.mesadmin.dto.GetAllCLevelInfo.GetAllCLevelInfoRes;
import pnc.mesadmin.dto.GetAllCLevelInfo.GetAllCLevelInfoResB;
import pnc.mesadmin.dto.GetAllCLevelInfo.GetAllCLevelInfoResD;
import pnc.mesadmin.dto.GetCLevelInfoRes.GetCLevelInfoRes;
import pnc.mesadmin.dto.GetCLevelInfoRes.GetCLevelInfoResB;
import pnc.mesadmin.dto.GetCLevelInfoRes.GetCLevelInfoResD;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveCLevelInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.ClevelInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.CLeveIService;
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
public class CLeveIServicelmpl implements CLeveIService {
     @Resource
     private ClevelDAO cLevelDAO;

    @Resource
    private BaseIService baseIService;

    @Override
    public GetAllCLevelInfoRes QALLselectAllCLeveIInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException {
        GetAllCLevelInfoRes objGetAllCLevelInfoRes=new GetAllCLevelInfoRes();
        GetAllCLevelInfoResB objGetAllCLevelInfoResB=new GetAllCLevelInfoResB();
        List<GetAllCLevelInfoResD> objGetAllCLevelInfoResD=new ArrayList<GetAllCLevelInfoResD>();

        List<ClevelInfo> objCLevelInfo=  baseIService.QALLBaseInfo(ClevelDAO.class, "SelectAllCLevelInfo",
                argInitDataFields, argPageInfo);
        if(objCLevelInfo!=null||objCLevelInfo.size()>0) {

            //赋值dto并返回
            for (ClevelInfo obj : objCLevelInfo) {
                GetAllCLevelInfoResD objEGetAllUnitInfoResD = new GetAllCLevelInfoResD();
         //       objEGetAllUnitInfoResD.getCLevelRd(obj.getRuid());
                objEGetAllUnitInfoResD.setCLevelRd(obj.getRuid());
                objEGetAllUnitInfoResD.setCheckLevelName(obj.getCheckLevelName());
                objEGetAllUnitInfoResD.setCheckLevelCode(obj.getCheckLevelCode());
                objGetAllCLevelInfoResD.add(objEGetAllUnitInfoResD);
            }
        }
        objGetAllCLevelInfoResB.setData(objGetAllCLevelInfoResD);
        objGetAllCLevelInfoRes.setBody(objGetAllCLevelInfoResB);
        return objGetAllCLevelInfoRes;
    }


     //获取检验水平 信息
    @Override
    public GetCLevelInfoRes GetselectByRuid(int CLevelRd) throws SystemException {
        GetCLevelInfoRes objGetCLevelInfoRes = new  GetCLevelInfoRes();

        GetCLevelInfoResB body = new GetCLevelInfoResB();

        GetCLevelInfoResD data = new GetCLevelInfoResD();

        // 获取紧急代码信息
        ClevelInfo cLevelInfo = cLevelDAO.SelectByRuid(CLevelRd);

        if(cLevelInfo!=null ) {
            // 赋值查询紧急代码信息
            data.setCLevelRd(cLevelInfo.getRuid());
            data.setCheckLevelCode(cLevelInfo.getCheckLevelCode());
            data.setCheckLevelName(cLevelInfo.getCheckLevelName());
            data.setCreator(cLevelInfo.getCreator());
            data.setCreateTime(DateUtil.format(cLevelInfo.getCreateTime()));
            data.setLastModifyMan(cLevelInfo.getLastModifyMan());
            data.setLastModifyTime(DateUtil.format(cLevelInfo.getLastModifyTime()));
            data.setRemark(cLevelInfo.getRemark());
        }

        body.setData(data);
        objGetCLevelInfoRes.setBody(body);
        return objGetCLevelInfoRes;
    }
    //新增紧急代码信息
    @Override
    public SaveCLevelInfoRes AddinsertCLevelInfo(SaveCLevelInfoReqBD00 busData00, ClevelInfo cLevelInfo) throws SystemException {
        SaveCLevelInfoRes saveCLevelInfoRes = new SaveCLevelInfoRes();

        SaveCLevelInfoResB body = new SaveCLevelInfoResB();

        SaveCLevelInfoResD data = new SaveCLevelInfoResD();
       if(busData00.getCheckLevelCode()==null || "".equals(busData00.getCheckLevelCode())){
           throw new SystemException("","检验水平代码不能为空");
       }
        ClevelInfo objCLevelInfo = cLevelDAO.SelectCheckLevelInfo(busData00.getCheckLevelCode());
       if(objCLevelInfo!=null){
           throw new SystemException("","检验水平代码重复");
       }
        ClevelInfo CLevelInfo = new ClevelInfo();
        CLevelInfo.setGuid(CommonUtils.getRandomNumber());
        CLevelInfo.setCheckLevelCode(busData00.getCheckLevelCode());
        CLevelInfo.setCheckLevelName(busData00.getCheckLevelName());
        CLevelInfo.setCreator(CommonUtils.readUser().getRealName());
        CLevelInfo.setCreateTime(new Date());
        CLevelInfo.setRemark(busData00.getRemark());

       if(cLevelDAO.InsertCLevelInfo(CLevelInfo)<=0){
           throw new SystemException("","新增失败");
     }
        body.setData(data);
        saveCLevelInfoRes.setBody(body);
        return saveCLevelInfoRes;
    }

    @Override
    public SaveCLevelInfoRes ModupdateCLevelInfo(SaveCLevelInfoReqBD02 busData02, ClevelInfo cLevelInfo) throws SystemException {
        SaveCLevelInfoRes objsaveCLevelInfoRes = new SaveCLevelInfoRes();

        SaveCLevelInfoResB body = new SaveCLevelInfoResB();

        SaveCLevelInfoResD data = new SaveCLevelInfoResD();
        //查询紧急代码信息
        ClevelInfo objCLevelInfo=cLevelDAO.SelectByRuid(busData02.getCLevelRd());
        if (objCLevelInfo==null){
            throw new SystemException("","检验水平信息为空");
        }
        //查询紧急代码
        ClevelInfo objCLevelInfo1=cLevelDAO.SelectCheckLevelInfo(busData02.getCheckLevelCode());

        if(objCLevelInfo1!=null && !objCLevelInfo1.getCheckLevelCode().equals(objCLevelInfo.getCheckLevelCode())){
            throw new SystemException("MG0006F","检验水平代码已存在");
        }
        objCLevelInfo.setRuid(busData02.getCLevelRd());
        objCLevelInfo.setCheckLevelCode(busData02.getCheckLevelCode());
        objCLevelInfo.setCheckLevelName(busData02.getCheckLevelName());
        objCLevelInfo.setLastModifyTime(new Date());
        objCLevelInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        objCLevelInfo.setRemark(busData02.getRemark());
       if (cLevelDAO.UpdateCheckLevelInfo(objCLevelInfo)<=0){
           throw new SystemException("MG0006F","检验水平清单更新失败");
       }
        body.setData(data);
        objsaveCLevelInfoRes.setBody(body);
        return objsaveCLevelInfoRes;
    }

    @Override
    public SaveCLevelInfoRes RmdeleteCLevelInfo(int ruid) throws SystemException {
        SaveCLevelInfoRes objsaveCLevelInfoRes = new SaveCLevelInfoRes();
        SaveCLevelInfoResB body = new SaveCLevelInfoResB();
        SaveCLevelInfoResD data = new SaveCLevelInfoResD();
      if(cLevelDAO.DeleteCLevelInfo(ruid)<=0){
          throw new SystemException("MG0006F","检验水平清单删除失败");
      }
        body.setData(data);
        objsaveCLevelInfoRes.setBody(body);
        return objsaveCLevelInfoRes;
    }
}
