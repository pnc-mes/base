package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.DevDAO;
import pnc.mesadmin.dao.ToolDao;
import pnc.mesadmin.dao.ToolDevDAO;
import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.BaseDto.BaseResB;
import pnc.mesadmin.dto.GetAllToolDevDto.GetToolDevRes;
import pnc.mesadmin.dto.GetAllToolDevDto.GetToolDevResB;
import pnc.mesadmin.dto.GetAllToolDevDto.SaveToolDevRes00;
import pnc.mesadmin.dto.GetAllToolDevDto.SaveToolDevRes01;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.DevInfo;
import pnc.mesadmin.entity.DevToolRelationInfo;
import pnc.mesadmin.entity.ToolInfo;
import pnc.mesadmin.service.ToolDevService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ToolDevServiceImpl implements ToolDevService {
    @Resource
    private DevDAO devDAO;
    @Resource
    private ToolDao toolDao;
    @Resource
    private ToolDevDAO toolDevDAO;

    @Override
    public BaseRes GetToolDevRelInfo(GetToolDevRes res) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        List<GetToolDevResB> toolDevResB = new ArrayList<>();
        if (res.getDevRd() == null) {
            throw new SystemException("EEE", "设备id不能为空！");
        }
        DevInfo devInfo = devDAO.SelectBydevRd(res.getDevRd());
        List<DevToolRelationInfo> devToolRelationInfoList = toolDevDAO.SelectAllDevToolRelationInfoByDevGD(devInfo.getGuid());
        for (DevToolRelationInfo model : devToolRelationInfoList) {
            GetToolDevResB getToolDevResB = new GetToolDevResB();
            DevInfo devInfoModel = devDAO.SelectByguid(model.getDevGd());
            ToolInfo toolInfo = toolDao.SelectToolInfoByguid(model.getToolGd());
            if (devInfoModel != null) {
                getToolDevResB.setDevRd(devInfoModel.getRuid());
                getToolDevResB.setDevName(devInfoModel.getDevName());
            }

            if (toolInfo != null) {
                getToolDevResB.setToolRd(toolInfo.getRuid());
                getToolDevResB.setToolName(toolInfo.getToolName());
                getToolDevResB.setUsrNum(toolInfo.getUsrNum());
                getToolDevResB.setVenderSN(toolInfo.getVenderSN());
            }
            toolDevResB.add(getToolDevResB);
        }
        baseResBody.setData(toolDevResB);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public BaseRes SaveToolDevRel00(SaveToolDevRes00 res) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        DevInfo devInfo = devDAO.SelectBydevRd(res.getDevRd());
        if (devInfo == null) {
            throw new SystemException("EEE", "无此设备信息！");
        }
        /*if (devInfo.getToolFamilyGd() == null || devInfo.getToolFamilyGd().equals("")) {
            throw new SystemException("EEE", "当前设备无需工具，不能完成当前操作！");
        }
        //只可能查询出一条
        List<ToolInfo> toolInfo = toolDao.SelectAllByVenSn(res.getVenderSN());
        if (toolInfo.size() < 1) {
            throw new SystemException("EEE", "无此" + res.getVenderSN() + "功能根据信息");
        }
        if (toolInfo.get(0).getToolFamilyGd().equals("") || toolInfo.get(0).getToolFamilyGd() == null) {
            throw new SystemException("EEE", "当前工具没有绑定工具家族不能上设备！");
        }
        if (!devInfo.getToolFamilyGd().equals(toolInfo.get(0).getToolFamilyGd())) {
            throw new SystemException("EEE", "当前工具不匹配当前设备！");
        }
        DevToolRelationInfo devToolRelationInfo = new DevToolRelationInfo();
        devToolRelationInfo.setDevGd(devInfo.getGuid());
        devToolRelationInfo.setToolGd(toolInfo.get(0).getGuid());

        DevToolRelationInfo devToolRelationInfoC = toolDevDAO.SelectAllDevToolRelationInfoByModel(devToolRelationInfo.getToolGd());
        if (devToolRelationInfoC != null) {
            throw new SystemException("EEE", "当前工具已经和设备进行过绑定！");
        }

        //当前设备只能有一个工具
        List<DevToolRelationInfo> devToolRelationInfoCList = toolDevDAO.SelectAllDevToolRelationInfoByDevGD(devToolRelationInfo.getDevGd());
        if (devToolRelationInfoCList != null && devToolRelationInfoCList.size() > 0)
            throw new SystemException("EEE", "当前设备上已有工具！");
        toolDevDAO.InsrtDevToolRelationInfo(devToolRelationInfo);*/
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public BaseRes SaveToolDevRel01(List<SaveToolDevRes01> res) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        for (SaveToolDevRes01 model : res) {
            ToolInfo toolInfo = toolDao.SelectToolInfoByruid(model.getToolRd());
            toolDevDAO.DelDevToolRelationInfoByToolGD(toolInfo.getGuid());
        }
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }
}
