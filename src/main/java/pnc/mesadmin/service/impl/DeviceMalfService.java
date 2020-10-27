package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.DevMalfInfoDAO;
import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.BaseDto.BaseResB;
import pnc.mesadmin.dto.GetAllDevMalfInfo.GetAllDevMalfInfoResD;
import pnc.mesadmin.dto.GetDevMalfInfo.GetDevMalfInfoReq00;
import pnc.mesadmin.dto.GetDevMalfInfo.GetDevMalfInfoResD;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveDevMalfInfo.SaveDevMalfInfoReq00;
import pnc.mesadmin.dto.SaveDevMalfInfo.SaveDevMalfInfoReq01;
import pnc.mesadmin.dto.SaveDevMalfInfo.SaveDevMalfInfoReq02;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.DevMalfInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.DeviceMalfIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/11/8 17:37
 * @Description:
 */
@Transactional
@Service
public class DeviceMalfService implements DeviceMalfIService {
    @Resource
    private DevMalfInfoDAO devMalfInfoDAO;

    @Resource
    private BaseIService baseIService;

    //列表
    @Override
    public BaseRes QALLBaseRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        BaseRes baseRes=new BaseRes();
        BaseResB baseResB=new BaseResB();

        List<GetAllDevMalfInfoResD> getAllDevMalfInfoResDS=new ArrayList<>();

        List<DevMalfInfo> devMalfInfos=baseIService.QALLBaseInfo(DevMalfInfoDAO.class, "SelectAllDevMalfInfo",
                argInitDataFields, argPageInfo);

        if(devMalfInfos!=null&&devMalfInfos.size()>0) {

            for (DevMalfInfo obj : devMalfInfos) {
                GetAllDevMalfInfoResD getAllDevMalfInfoResD = new GetAllDevMalfInfoResD();
                getAllDevMalfInfoResD.setDevMalfRd(obj.getRuid());
                getAllDevMalfInfoResD.setDevMalfName(obj.getDevMalfName());
                getAllDevMalfInfoResDS.add(getAllDevMalfInfoResD);
            }
        }

        baseResB.setData(getAllDevMalfInfoResDS);
        baseRes.setBody(baseResB);
        return baseRes;
    }

    //单个
    @Override
    public BaseRes GetBaseRes(GetDevMalfInfoReq00 getDevMalfInfoReq00) {
        BaseRes baseRes=new BaseRes();
        BaseResB baseResB=new BaseResB();
        if("".equals(getDevMalfInfoReq00.getDevMalfRd())||getDevMalfInfoReq00.getDevMalfRd()<=0){
            throw new SystemException("xxxx","查询失败，设备故障不存在");
        }

        GetDevMalfInfoResD getDevMalfInfoResD=new GetDevMalfInfoResD();

        DevMalfInfo devMalfInfo=devMalfInfoDAO.SelectDevMalfInfoByruid(getDevMalfInfoReq00.getDevMalfRd());
        if(devMalfInfo!=null){
            getDevMalfInfoResD.setDevMalfRd(devMalfInfo.getRuid());
            getDevMalfInfoResD.setDevMalfCode(devMalfInfo.getDevMalfCode());
            getDevMalfInfoResD.setDevMalfName(devMalfInfo.getDevMalfName());
            getDevMalfInfoResD.setCreator(devMalfInfo.getCreator());
            getDevMalfInfoResD.setCreateTime(DateUtil.format(devMalfInfo.getCreateTime()));
            getDevMalfInfoResD.setLastModifyMan(devMalfInfo.getLastModifyMan());
            getDevMalfInfoResD.setLastModifyTime(DateUtil.format(devMalfInfo.getLastModifyTime()));
            getDevMalfInfoResD.setRemark(devMalfInfo.getRemark());
        }
        baseResB.setData(getDevMalfInfoResD);
        baseRes.setBody(baseResB);
        return baseRes;
    }

    //保存
    @Override
    public BaseRes AddBaseRes(SaveDevMalfInfoReq00 saveDevMalfInfoReq00) {
        BaseRes baseRes=new BaseRes();
        BaseResB baseResB=new BaseResB();
        if("".equals(saveDevMalfInfoReq00.getDevMalfCode())||saveDevMalfInfoReq00.getDevMalfCode()==null){
            throw new SystemException("xxx","设备故障类型代码不能为空");
        }
        if("".equals(saveDevMalfInfoReq00.getDevMalfName())||saveDevMalfInfoReq00.getDevMalfName()==null){
            throw new SystemException("xxx","设备故障类型名称不能为空");
        }
        DevMalfInfo devMalfInfo=devMalfInfoDAO.SelectDevMalfInfoByDevMalfCode(saveDevMalfInfoReq00.getDevMalfCode());
        if(devMalfInfo!=null){
            throw new SystemException("xxx","设备故障类型代码已存在");
        }
        DevMalfInfo devMalfInfo1=new DevMalfInfo();
        devMalfInfo1.setGuid(CommonUtils.getRandomNumber());
        devMalfInfo1.setDevMalfCode(saveDevMalfInfoReq00.getDevMalfCode());
        devMalfInfo1.setDevMalfName(saveDevMalfInfoReq00.getDevMalfName());
        devMalfInfo1.setCreator(CommonUtils.readUser().getRealName());
        devMalfInfo1.setCreateTime(new Date());
        devMalfInfo1.setRemark(saveDevMalfInfoReq00.getRemark());
        devMalfInfoDAO.InsertDevMalfInfo(devMalfInfo1);

        baseRes.setBody(baseResB);
        return baseRes;
    }

    //删除
    @Override
    public BaseRes RmBaseRes(SaveDevMalfInfoReq01 saveDevMalfInfoReq01) {
        BaseRes baseRes=new BaseRes();
        BaseResB baseResB=new BaseResB();
        if("".equals(saveDevMalfInfoReq01.getDevMalfRd())||saveDevMalfInfoReq01.getDevMalfRd()<=0){
            throw new SystemException("xxxx","删除失败，设备故障不存在");
        }

        if(devMalfInfoDAO.DeleteDevMalfInfoByruid(saveDevMalfInfoReq01.getDevMalfRd())<=0){
            throw new SystemException("xxxx","删除失败");
        }

        baseRes.setBody(baseResB);
        return baseRes;
    }

    //修改
    @Override
    public BaseRes ModBaseRes(SaveDevMalfInfoReq02 saveDevMalfInfoReq02) {
        BaseRes baseRes=new BaseRes();
        BaseResB baseResB=new BaseResB();
        if("".equals(saveDevMalfInfoReq02.getDevMalfRd())||saveDevMalfInfoReq02.getDevMalfRd()<=0){
            throw new SystemException("xxxx","修改失败，设备故障不存在");
        }
        if("".equals(saveDevMalfInfoReq02.getDevMalfCode())||saveDevMalfInfoReq02.getDevMalfCode()==null){
            throw new SystemException("xxx","设备故障类型代码不能为空");
        }
        if("".equals(saveDevMalfInfoReq02.getDevMalfName())||saveDevMalfInfoReq02.getDevMalfName()==null){
            throw new SystemException("xxx","设备故障类型名称不能为空");
        }
        DevMalfInfo devMalfInfo=devMalfInfoDAO.SelectDevMalfInfoByruid(saveDevMalfInfoReq02.getDevMalfRd());
        DevMalfInfo devMalfInfo1=devMalfInfoDAO.SelectDevMalfInfoByDevMalfCode(saveDevMalfInfoReq02.getDevMalfCode());
        if(devMalfInfo!=null&&!devMalfInfo1.getDevMalfCode().equals(devMalfInfo.getDevMalfCode())){
            throw new SystemException("xxx","设备故障类型代码已存在");
        }
        devMalfInfo.setDevMalfName(saveDevMalfInfoReq02.getDevMalfName());
        devMalfInfo.setDevMalfCode(saveDevMalfInfoReq02.getDevMalfCode());
        devMalfInfo.setRemark(saveDevMalfInfoReq02.getRemark());
        devMalfInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        devMalfInfo.setLastModifyTime(new Date());
        if(devMalfInfoDAO.UpdateDevMalfInfo(devMalfInfo)<=0){
            throw new SystemException("xxx","更新失败");
        }

        baseRes.setBody(baseResB);
        return baseRes;
    }
}
