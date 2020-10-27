package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.DevSMDtDAO;
import pnc.mesadmin.dao.DevSModeDAO;
import pnc.mesadmin.dao.DevStateDAO;
import pnc.mesadmin.dao.SkillGlDAO;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllDevSMInfo.*;
import pnc.mesadmin.dto.GetAllSGInfo.GetAllSGInfoResD;
import pnc.mesadmin.dto.GetDevSMInfo.*;
import pnc.mesadmin.dto.SaveDevSMInfo.*;
import pnc.mesadmin.entity.DevSMDtlnfo;
import pnc.mesadmin.entity.DevSModeInfo;
import pnc.mesadmin.entity.DevStateInfo;
import pnc.mesadmin.entity.SkillGlInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.DevSMIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.MyPage;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by PNC on 2017/8/21.
 */
@Transactional
@Service
public class DevSMService implements DevSMIService {
    @Resource
    private DevSModeDAO devSModeDAO;

    @Resource
    private DevSMDtDAO devSMDtDAO;

    @Resource
    private DevStateDAO devStateDAO;

    @Resource
    private BaseIService baseIService;



    //dto获取设备状态模型信息
    public GetAllDevSMInfoRes QALLGetAllDevSMInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllDevSMInfoRes objEGetAllDevSMInfoRes = new GetAllDevSMInfoRes();

        GetAllDevSMInfoResB body = new GetAllDevSMInfoResB();

        List<GetAllDevSMInfoResD> dataList =  new ArrayList<GetAllDevSMInfoResD>();

        // 获取设备状态模型信息
        List<DevSModeInfo> devSModeInfoList = baseIService.QALLBaseInfo(DevSModeDAO.class, "SelectAllDevSModeInfo",
                argInitDataFields, argPageInfo);

        if(devSModeInfoList!=null || devSModeInfoList.size()>0) {
            GetAllDevSMInfoResD data = null;

            // 循环赋值查询文件组
            for (int i = 0; i < devSModeInfoList.size(); i++) {
                data = new GetAllDevSMInfoResD();
                data.setDSMRd(devSModeInfoList.get(i).getRuid());
                data.setModelName(devSModeInfoList.get(i).getModelName());
                dataList.add(data);
            }
        }

        body.setData(dataList);
        objEGetAllDevSMInfoRes.setBody(body);

        return objEGetAllDevSMInfoRes;
    }

    @Override
    public PageResult<GetAllDevSMInfoResD> QALLGetAllDevSMNew(BaseRequest req) {
        List<GetAllDevSMInfoResD> resDList = new ArrayList<GetAllDevSMInfoResD>();
        GetAllDevSMInfoResD resD = null;

        IPage<DevSModeInfo> iPage = devSModeDAO.selectPage(new MyPage(req), CommonUtils.getQueryWrapper(req.getFields()));
        //赋值dto并返回
        for (DevSModeInfo obj : iPage.getRecords()) {
            resD = new GetAllDevSMInfoResD();
            resD.setDSMRd(obj.getRuid());
            resD.setModelName(obj.getModelName());
            resDList.add(resD);
        }
        return new PageResult<>(iPage, resDList);
    }

    //获取设备状态模型信息
    public GetDevSMInfoRes GetGetDevSMInfoRes(int ruid) throws SystemException {
        GetDevSMInfoRes objEGetDevSMInfoRes = new  GetDevSMInfoRes();

        GetDevSMInfoResB body = new GetDevSMInfoResB();

        GetDevSMInfoResD data = new GetDevSMInfoResD();

        // 获取批次等级信息
        DevSModeInfo devSModeInfo =devSModeDAO.SelectDevSModeInfo(ruid);

        if(devSModeInfo!=null) {
            DevStateInfo DevStateInfo =  devStateDAO.SelectDevStateInfoByGuid(devSModeInfo.getDSGd());
            if(DevStateInfo!=null){
                GetDevSMInfoResDDSInfo getDevSMInfoResDDSInfo = new GetDevSMInfoResDDSInfo();
                getDevSMInfoResDDSInfo.setDevSRd(DevStateInfo.getRuid());
                getDevSMInfoResDDSInfo.setDevSName(DevStateInfo.getDevSName());;
                data.setDSInfo(getDevSMInfoResDDSInfo);
            }

            List<GetDevSMInfoResDDevSMInfo> objEGetDevSMInfoDevSMInfo=new ArrayList<GetDevSMInfoResDDevSMInfo>();

            //查询设备状态模型明细信息
            List<DevSMDtlnfo> objEDevSMDtlnfo=devSMDtDAO.SelectDevSMDtlnfoBydevSMGd(devSModeInfo.getGuid());

            if(objEDevSMDtlnfo!=null && objEDevSMDtlnfo.size()>0) {
                for (DevSMDtlnfo obj : objEDevSMDtlnfo) {
                    GetDevSMInfoResDDevSMInfo objeGetDevSMInfoResDDevSMInfo = new GetDevSMInfoResDDevSMInfo();

                    DevStateInfo objEDevStateInfo = devStateDAO.SelectDevStateInfoByGuid(obj.getSouDSGd());
                    DevStateInfo objEDevStateInfo1 = devStateDAO.SelectDevStateInfoByGuid(obj.getTarDSGd());
                    if (objEDevStateInfo != null) {
                        GetDevSMInfoResDSouDS objEGetDevSMInfoResDSouDS = new GetDevSMInfoResDSouDS();

                        GetDevSMInfoResTarDS objEGetDevSMInfoResTarDS = new GetDevSMInfoResTarDS();

                        objEGetDevSMInfoResDSouDS.setDevSRd(objEDevStateInfo.getRuid());
                        objEGetDevSMInfoResDSouDS.setDevSName(objEDevStateInfo.getDevSName());
                        objeGetDevSMInfoResDDevSMInfo.setSouDSInfo(objEGetDevSMInfoResDSouDS);

                        objEGetDevSMInfoResTarDS.setDevSRd(objEDevStateInfo1.getRuid());
                        objEGetDevSMInfoResTarDS.setDevSName(objEDevStateInfo1.getDevSName());
                        objeGetDevSMInfoResDDevSMInfo.setTarDSInfo(objEGetDevSMInfoResTarDS);

                        objEGetDevSMInfoDevSMInfo.add(objeGetDevSMInfoResDDevSMInfo);
                    }

                }
            }

            // 赋值查询批次等级信息
            data.setDSMRd(devSModeInfo.getRuid());
            data.setModelName(devSModeInfo.getModelName());
            data.setDevSMInfo(objEGetDevSMInfoDevSMInfo);
        }

        body.setData(data);
        objEGetDevSMInfoRes.setBody(body);

        return objEGetDevSMInfoRes;
    }

   //新增设备状态模型信息
    public SaveDevSMInfoRes AddSaveDevSMInfoRes(SaveDevSMInfoReq00 argSaveDevSMInfoReq00) {
        SaveDevSMInfoRes saveDevSMInfoRes = new SaveDevSMInfoRes();

        SaveDevSMInfoResB body = new SaveDevSMInfoResB();

        SaveDevSMInfoResD data = new  SaveDevSMInfoResD();

        DevSModeInfo devSModeInfo=new DevSModeInfo();

        DevSMDtlnfo devSMDtlnfo=new DevSMDtlnfo();

        String guid = CommonUtils.getRandomNumber();
        //查询设备状态所有信息
        List<DevSModeInfo> objEDevSModeInfo=devSModeDAO.SelectAllDevSModeInfo();

        // 赋值新增设备组
        devSModeInfo.setGuid(guid);

        //逻辑校验保存的文件组名称不能相同
        for (DevSModeInfo obj:objEDevSModeInfo){
            if (obj.getModelName().equals(argSaveDevSMInfoReq00.getModelName())){
                throw new SystemException("MG0006F","设备状态模型名称已存在");
            }
        }
        DevStateInfo devStateInfo = devStateDAO.SelectDevStateInfoByRuid(Integer.valueOf(argSaveDevSMInfoReq00.getDevSRd())) ;
        if(devStateInfo!=null){
            devSModeInfo.setDSGd(devStateInfo.getGuid());
        }
        //检验方法
 /*       CmethodInfo cmethodInfo = cmethodDAO.SelectByRuid(req.getCMethodRd());
        if (cmethodInfo != null) {
            checkItemInfo.setCheckMethodGd(cmethodInfo.getGuid());
        }*/
        devSModeInfo.setModelName(argSaveDevSMInfoReq00.getModelName());
        devSModeInfo.setCreator((String) SecurityUtils.getSubject().getPrincipal());
        devSModeInfo.setCreateTime(new Date());

        //新增设备状态模型明细信息
        int i=1;
        for(SaveDevSMInfoReq00DevSMInfo obj:argSaveDevSMInfoReq00.getDevSMInfo()){
            DevStateInfo objEDevStateInfo=devStateDAO.SelectDevStateInfoByRuid(obj.getSouDSRd());
            DevStateInfo objEDevStateInfo1=devStateDAO.SelectDevStateInfoByRuid(obj.getTarDSRd());
            if(objEDevStateInfo==null){
                throw new SystemException("xx","新增失败，第"+i+"行的源状态不存在");
            }
            if(objEDevStateInfo1==null){
                throw new SystemException("xx","新增失败，第"+i+"行的目标状态不存在");
            }
            if(objEDevStateInfo.getGuid().equals(objEDevStateInfo1.getGuid())){
                throw new SystemException("xx","源状态和目标状态不能重复");
            }

            devSMDtlnfo.setGuid(CommonUtils.getRandomNumber());
            devSMDtlnfo.setDevSMGd(guid);
            devSMDtlnfo.setSouDSGd(objEDevStateInfo.getGuid());
            devSMDtlnfo.setTarDSGd(objEDevStateInfo1.getGuid());

            devSMDtlnfo.setCreator(CommonUtils.readUser().getRealName());
            devSMDtlnfo.setCreateTime(new Date());

            devSMDtDAO.InsertDevSMDtlnfo(devSMDtlnfo);
            i++;
        }

        // 保存
        devSModeDAO.InsertDevSModeInfo(devSModeInfo);


        body.setData(data);
        saveDevSMInfoRes.setBody(body);

        return saveDevSMInfoRes;
    }

   //删除设备状态模型信息
    public SaveDevSMInfoRes RmSaveDevSMInfoRes(SaveDevSMInfoReq01 argSaveDevSMInfoReq01) {
        SaveDevSMInfoRes saveDevSMInfoRes = new SaveDevSMInfoRes();

        SaveDevSMInfoResB body = new SaveDevSMInfoResB();

        SaveDevSMInfoResD data = new SaveDevSMInfoResD();

        //查询设备状态模型信息信息
        DevSModeInfo objEDevSModeInfo=devSModeDAO.SelectDevSModeInfo(argSaveDevSMInfoReq01.getDSMRd());

        if (objEDevSModeInfo==null){
            throw new SystemException("MG_MES4001210010001F","查询设备状态模型信息为空！");
        }

        //查询设备状态模型明细信息
        List<DevSMDtlnfo> objEDevSModeInfoss=devSMDtDAO.SelectDevSMDtlnfoBydevSMGd(objEDevSModeInfo.getGuid());

        for(DevSMDtlnfo obj:objEDevSModeInfoss) {
            devSMDtDAO.DeleteDevSMDtlnfodevSMGd(obj.getDevSMGd());
        }

        // 删除设备组信息
        int count = devSModeDAO.DeleteDevSModeInfo(argSaveDevSMInfoReq01.getDSMRd());

        if(count <=0) throw new SystemException("MG_MES4001214020001F","删除设备状态模型信息失败！");

        body.setData(data);
        saveDevSMInfoRes.setBody(body);

        return saveDevSMInfoRes;
    }

  //更新设备状态模型信息
    public SaveDevSMInfoRes ModSaveDevSMInfoRes(SaveDevSMInfoReq02 argSaveDevSMInfoReq02) {
        SaveDevSMInfoRes saveDevSMInfoRes = new SaveDevSMInfoRes();

        SaveDevSMInfoResB body = new SaveDevSMInfoResB();

        SaveDevSMInfoResD data = new SaveDevSMInfoResD();

        DevSModeInfo devSModeInfo=new DevSModeInfo();

        DevSMDtlnfo devSMDtlnfo=new DevSMDtlnfo();

        // 获取设备状态模型信息
        DevSModeInfo objEDevSModeInfo =devSModeDAO.SelectDevSModeInfo(argSaveDevSMInfoReq02.getDSMRd());

        // 判断返回设备组是否为空
        if (objEDevSModeInfo == null){
            throw new SystemException("MG_MES2001010010001F", "查询设备状态模型信息为空！");
        }

        //查询设备组名称信息
        DevSModeInfo objEDevSModeInfos=devSModeDAO.SelectDevSModeInfomodelName(argSaveDevSMInfoReq02.getModelName());

        if(objEDevSModeInfos!=null && !objEDevSModeInfos.getModelName().equals(objEDevSModeInfo.getModelName())){
            throw new SystemException("MG0006F","更新失败，设备状态模型名称已存在");
        }
        DevStateInfo DevStateInfo = devStateDAO.SelectDevStateInfoByRuid(argSaveDevSMInfoReq02.getDevSRd());
        if(DevStateInfo!=null){
            devSModeInfo.setDSGd(DevStateInfo.getGuid());
        }
        // 赋值更新设备组信息
        devSModeInfo.setRuid(argSaveDevSMInfoReq02.getDSMRd());
        devSModeInfo.setModelName(argSaveDevSMInfoReq02.getModelName());
        devSModeInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        devSModeInfo.setLastModifyTime(new Date());

        //查询设备状态模型明细信息
        List<DevSMDtlnfo> objEDevSModeInfoss=devSMDtDAO.SelectDevSMDtlnfoBydevSMGd(objEDevSModeInfo.getGuid());

        for(DevSMDtlnfo obj:objEDevSModeInfoss) {
            devSMDtDAO.DeleteDevSMDtlnfodevSMGd(obj.getDevSMGd());
        }

        if(argSaveDevSMInfoReq02.getDevSMInfo()!=null) {
            //新增设备状态模型明细信息
            int i=1;
            for(SaveDevSMInfoReq02DevSMInfo obj:argSaveDevSMInfoReq02.getDevSMInfo()){
                DevStateInfo objEDevStateInfo=devStateDAO.SelectDevStateInfoByRuid(obj.getSouDSRd());
                DevStateInfo objEDevStateInfo1=devStateDAO.SelectDevStateInfoByRuid(obj.getTarDSRd());
                if(objEDevStateInfo==null){
                    throw new SystemException("xx","新增失败，第"+i+"行的源状态不存在");
                }
                if(objEDevStateInfo1==null){
                    throw new SystemException("xx","新增失败，第"+i+"行的目标状态不存在");
                }
                if(objEDevStateInfo.getGuid().equals(objEDevStateInfo1.getGuid())){
                    throw new SystemException("xx","源状态和目标状态不能重复");
                }

                devSMDtlnfo.setGuid(CommonUtils.getRandomNumber());
                devSMDtlnfo.setDevSMGd(objEDevSModeInfo.getGuid());
                if(objEDevStateInfo!=null && objEDevStateInfo1!=null) {
                    devSMDtlnfo.setSouDSGd(objEDevStateInfo.getGuid());
                    devSMDtlnfo.setTarDSGd(objEDevStateInfo1.getGuid());
                }
                devSMDtlnfo.setCreator(CommonUtils.readUser().getRealName());
                devSMDtlnfo.setCreateTime(new Date());

                devSMDtDAO.InsertDevSMDtlnfo(devSMDtlnfo);
                i++;
            }

        }

        // 更新
        int count = devSModeDAO.UpdateDevSModeInfo(devSModeInfo);

        if(count <=0) throw new SystemException("MG_MES4001214030002F","更新设备状态模型信息失败！");

        body.setData(data);
        saveDevSMInfoRes.setBody(body);
        return saveDevSMInfoRes;
    }

}
