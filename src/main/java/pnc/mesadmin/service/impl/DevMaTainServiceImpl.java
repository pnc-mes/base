package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.BaseDto.BaseResB;
import pnc.mesadmin.dto.SaveDevMaTainDto.SaveDevMaTainRes00;
import pnc.mesadmin.dto.SaveDevMaTainDto.SaveDevMaTainRes01;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.DevMaTainService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @program: mesadmin
 * @description: 设备维修、设备更换界面点击保存时需要调用此接口
 * @author: yin.yang
 * @create: 2018-11-08
 **/
@Service
@Transactional
public class DevMaTainServiceImpl implements DevMaTainService {
    @Resource
    private DevDAO devDAO;
    @Resource
    private SparePartDAO sparePartDAO;
    @Resource
    private DevStateDAO devStateDAO;
    @Resource
    private DevReplaceLogDAO devReplaceLogDAO;
    @Resource
    private DevRepairLogDAO devRepairLogDAO;
    @Resource
    private DevMalfInfoDAO devMalfInfoDAO;

    @Override
    public BaseRes AddSaveDevOpt00(SaveDevMaTainRes00 res) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        DevRepairLogInfo devRepairLogInfo = new DevRepairLogInfo();
        devRepairLogInfo.setGuid(CommonUtils.getRandomNumber());
        DevInfo devInfo = devDAO.SelectBydevRd(res.getDevRd());
        DevStateInfo devStateInfo = devStateDAO.SelectDevStateInfoByRuid(res.getDevSRd());
        DevMalfInfo devMalfInfo = devMalfInfoDAO.SelectDevMalfInfoByruid(res.getDevMalfRd());
        devRepairLogInfo.setDevGd(devInfo.getGuid());
        devRepairLogInfo.setDevSGd(devStateInfo.getGuid());
        devRepairLogInfo.setDevMalfGd(devMalfInfo.getGuid());
        devRepairLogInfo.setCreator(CommonUtils.readUser().getRealName());
        devRepairLogInfo.setCreateTime(new Date());
        devRepairLogInfo.setRemark(res.getRemark());
        devRepairLogDAO.InsertDevRepairLog(devRepairLogInfo);
      /*  devInfo = new DevInfo();
        devInfo.setRuid(res.getDevRd());
        devInfo.setDevSGd(devStateInfo.getGuid());
        devInfo.setTotalUsrNum(devInfo.getTotalUsrNum()+devInfo.getUsrNum());
        devInfo.setUsrNum(0);
        devDAO.UpdateDevInfo(devInfo);*/

        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public BaseRes AddSaveDevOpt01(List<SaveDevMaTainRes01> res01) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        for (SaveDevMaTainRes01 model : res01) {
            DevReplaceLogInfo devReplaceLogInfo = new DevReplaceLogInfo();
            DevInfo devInfo = devDAO.SelectBydevRd(model.getDevRd());
            SparePartInfo sparePartInfo = sparePartDAO.SelectSparePartInfoByruid(model.getSPartRd());
            devReplaceLogInfo.setGuid(CommonUtils.getRandomNumber());
            devReplaceLogInfo.setCreator(CommonUtils.readUser().getRealName());
            devReplaceLogInfo.setCreateTime(new Date());
            devReplaceLogInfo.setDevGd(devInfo.getGuid());
            devReplaceLogInfo.setNum(model.getNum());
            devReplaceLogInfo.setSPartGd(sparePartInfo.getGuid());
            devReplaceLogDAO.InsertDevReplaceLog(devReplaceLogInfo);
        }
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }
}
