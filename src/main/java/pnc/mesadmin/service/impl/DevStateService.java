package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.DevStateDAO;
import pnc.mesadmin.dto.GetAllDevSInfo.GetAllDevSInfoRes;
import pnc.mesadmin.dto.GetAllDevSInfo.GetAllDevSInfoResB;
import pnc.mesadmin.dto.GetAllDevSInfo.GetAllDevSInfoResD;
import pnc.mesadmin.dto.GetDevSInfo.GetDevSInfoReq00;
import pnc.mesadmin.dto.GetDevSInfo.GetDevSInfoRes;
import pnc.mesadmin.dto.GetDevSInfo.GetDevSInfoResB;
import pnc.mesadmin.dto.GetDevSInfo.GetDevSInfoResD;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveDevSInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.DevStateInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.DevStateIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by PNC on 2017/8/18.
 */
@Transactional
@Service
public class DevStateService implements DevStateIService {
    @Resource
    private DevStateDAO devStateDAO;

    @Resource
    private BaseIService baseIService;

    //查询设备状态列表
    public GetAllDevSInfoRes QALLGetAllDevSInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllDevSInfoRes objEGetAllDevSInfoRes=new GetAllDevSInfoRes();
        GetAllDevSInfoResB objEGetAllDevSInfoResB=new GetAllDevSInfoResB();
        List<GetAllDevSInfoResD> objEGetAllDevSInfoResDs=new ArrayList<GetAllDevSInfoResD>();

        List<DevStateInfo> objEDevStateInfos=baseIService.QALLBaseInfo(DevStateDAO.class, "SelectDevStateInfo",
                argInitDataFields, argPageInfo);

        if(objEDevStateInfos!=null||objEDevStateInfos.size()!=0){
            for(DevStateInfo obj:objEDevStateInfos){
               GetAllDevSInfoResD objEGetAllDevSInfoResD=new GetAllDevSInfoResD();
                objEGetAllDevSInfoResD.setDevSRd(obj.getRuid());
                objEGetAllDevSInfoResD.setDevSName(obj.getDevSName());
                objEGetAllDevSInfoResDs.add(objEGetAllDevSInfoResD);
            }
        }
        objEGetAllDevSInfoResB.setData(objEGetAllDevSInfoResDs);
        objEGetAllDevSInfoRes.setBody(objEGetAllDevSInfoResB);
        return objEGetAllDevSInfoRes;
    }

    //查询设备状态信息
    public GetDevSInfoRes GetGetDevSInfoRes(GetDevSInfoReq00 argGetDevSInfoReq00) {
        GetDevSInfoRes objEGetDevSInfoRes=new GetDevSInfoRes();
        GetDevSInfoResB objEGetDevSInfoResB=new GetDevSInfoResB();
        GetDevSInfoResD objEGetDevSInfoResD=new GetDevSInfoResD();
        DevStateInfo objEDevStateInfo=devStateDAO.SelectDevStateInfoByRuid(argGetDevSInfoReq00.getDevSRd());
        if(objEDevStateInfo!=null){
            objEGetDevSInfoResD.setDevSRd(objEDevStateInfo.getRuid());
            objEGetDevSInfoResD.setStatus(objEDevStateInfo.getStatus());
            objEGetDevSInfoResD.setDevSName(objEDevStateInfo.getDevSName());
            objEGetDevSInfoResD.setCreator(objEDevStateInfo.getCreator());
            objEGetDevSInfoResD.setCreateTime(DateUtil.format(objEDevStateInfo.getCreateTime()));
            objEGetDevSInfoResD.setLastModifyMan(objEDevStateInfo.getLastModifyMan());
            objEGetDevSInfoResD.setLastModifyTime(DateUtil.format(objEDevStateInfo.getLastModifyTime()));
            objEGetDevSInfoResD.setRemark(objEDevStateInfo.getRemark());
        }
        objEGetDevSInfoResB.setData(objEGetDevSInfoResD);
        objEGetDevSInfoRes.setBody(objEGetDevSInfoResB);
        return objEGetDevSInfoRes;
    }

    //新增设备状态信息
    public SaveDevSInfoRes AddSaveDevSInfoRes(SaveDevSInfoReq00 argSaveDevSInfoRes) {
        SaveDevSInfoRes objESaveDevSInfoRes=new SaveDevSInfoRes();
        SaveDevSInfoResB objESaveDevSInfoResB=new SaveDevSInfoResB();
        if(argSaveDevSInfoRes.getDevSName()==null||argSaveDevSInfoRes.getDevSName()==""){
            throw new SystemException("xxx","新增失败，设备状态名称不能为空");
        }
        if(argSaveDevSInfoRes.getStatus()==null||argSaveDevSInfoRes.getStatus()==""){
            throw new SystemException("xxx","新增失败，设备状态不能为空");
        }

        List<DevStateInfo> objEDevStateInfos=devStateDAO.SelectDevStateInfo();
        if(objEDevStateInfos!=null||objEDevStateInfos.size()!=0){
            for(DevStateInfo objE:objEDevStateInfos){
                    if(argSaveDevSInfoRes.getDevSName().equals(objE.getDevSName())){
                        throw new SystemException("xxx","新增失败，设备状态名称已存在");
                    }
            }
        }
        DevStateInfo objEDevStateInfo=new DevStateInfo();
        objEDevStateInfo.setGuid(CommonUtils.getRandomNumber());
        objEDevStateInfo.setRemark(argSaveDevSInfoRes.getRemark());
        objEDevStateInfo.setStatus(argSaveDevSInfoRes.getStatus());
        objEDevStateInfo.setCreator(CommonUtils.readUser().getRealName());
        objEDevStateInfo.setCreateTime(new Date());
        objEDevStateInfo.setDevSName(argSaveDevSInfoRes.getDevSName());
        devStateDAO.InsertDevStateInfo(objEDevStateInfo);
        objESaveDevSInfoRes.setBody(objESaveDevSInfoResB);
        return objESaveDevSInfoRes;
    }

    //删除设备状态信息
    public SaveDevSInfoRes RmSaveDevSInfoRes(SaveDevSInfoReq01 argSaveSaveDevSInfoReq01) {
        SaveDevSInfoRes objESaveDevSInfoRes=new SaveDevSInfoRes();
        SaveDevSInfoResB objESaveDevSInfoResB=new SaveDevSInfoResB();

        DevStateInfo objEDevStateInfo=devStateDAO.SelectDevStateInfoByRuid(argSaveSaveDevSInfoReq01.getDevSRd());

        if("1".equals(objEDevStateInfo.getGuid()) || "2".equals(objEDevStateInfo.getGuid()) || "3".equals(objEDevStateInfo.getGuid()) ||
                "4".equals(objEDevStateInfo.getGuid()) || "5".equals(objEDevStateInfo.getGuid()) || "6".equals(objEDevStateInfo.getGuid()) ||
                "7".equals(objEDevStateInfo.getGuid())){
            throw new SystemException("xxx","改数据不能删除");
        }
        if(objEDevStateInfo!=null){
            if(devStateDAO.DeleteDevStateInfoByRuid(objEDevStateInfo.getRuid())<=0){
                    throw new SystemException("xxx","删除设备状态信息失败");
            }
        }

        objESaveDevSInfoRes.setBody(objESaveDevSInfoResB);
        return objESaveDevSInfoRes;
    }

    //更新设备状态信息
    public SaveDevSInfoRes ModSaveDevSInfoRes(SaveDevSInfoReq02 argSaveSaveDevSInfoReq02) {
        SaveDevSInfoRes objESaveDevSInfoRes=new SaveDevSInfoRes();
        SaveDevSInfoResB objESaveDevSInfoResB=new SaveDevSInfoResB();
        if(argSaveSaveDevSInfoReq02.getDevSName()==null||argSaveSaveDevSInfoReq02.getDevSName()==""){
            throw new SystemException("xxx","更新失败，设备状态名称不能为空");
        }
        if(argSaveSaveDevSInfoReq02.getStatus()==null||argSaveSaveDevSInfoReq02.getStatus()==""){
            throw new SystemException("xxx","新增失败，设备状态不能为空");
        }
        DevStateInfo objEDevStateInfo=devStateDAO.SelectDevStateInfoByRuid(argSaveSaveDevSInfoReq02.getDevSRd());
        if(objEDevStateInfo!=null){
            DevStateInfo objName=devStateDAO.SelectDevStateInfoByDevSName(argSaveSaveDevSInfoReq02.getDevSName());
            if(objName!=null&&!argSaveSaveDevSInfoReq02.getDevSName().equals(objEDevStateInfo.getDevSName())){
                throw new SystemException("xxx","更新失败，设备状态名称已存在");
            }
            objEDevStateInfo.setStatus(argSaveSaveDevSInfoReq02.getStatus());
            objEDevStateInfo.setDevSName(argSaveSaveDevSInfoReq02.getDevSName());
            objEDevStateInfo.setRemark(argSaveSaveDevSInfoReq02.getRemark());
            objEDevStateInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
            objEDevStateInfo.setLastModifyTime(new Date());
            if(devStateDAO.UpdateDevStateInfo(objEDevStateInfo)<=0){
                throw new SystemException("xxx","更新设备状态信息失败");
            }
        }
        objESaveDevSInfoRes.setBody(objESaveDevSInfoResB);
        return objESaveDevSInfoRes;
    }
}
