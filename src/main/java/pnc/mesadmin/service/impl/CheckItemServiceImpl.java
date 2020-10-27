package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.CheckItemDAO;
import pnc.mesadmin.dao.CmethodDAO;
import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.BaseDto.BaseResB;
import pnc.mesadmin.dto.GetAllCheckItemDTO.GetAllCheckItemResponse;
import pnc.mesadmin.dto.GetAllCheckItemDTO.GetCheckItemInfoRequest;
import pnc.mesadmin.dto.GetAllCheckItemDTO.GetCheckItemInfoResponse;
import pnc.mesadmin.dto.GetAllCheckItemDTO.MethodInfo;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.CheckItemInfo;
import pnc.mesadmin.entity.CmethodInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.CheckItemService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-03-19
 **/
@Service
@Transactional
public class CheckItemServiceImpl implements CheckItemService {

    @Resource
    private CheckItemDAO checkItemDAO;
    @Resource
    private CmethodDAO cmethodDAO;
    @Resource
    private BaseIService baseIService;

    @Override
    public BaseRes GetAllCIInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        List<GetAllCheckItemResponse> responses = new ArrayList<>();
        List<CheckItemInfo> lists = baseIService.QALLBaseInfo(CheckItemDAO.class, "SelectAll",
                argInitDataFields, argPageInfo);
        for (CheckItemInfo model : lists) {
            GetAllCheckItemResponse response = new GetAllCheckItemResponse();
            response.setCIRd(model.getRuid());
            response.setCheckItemCode(model.getCheckItemCode());
            response.setCheckItemName(model.getCheckItemName());
            responses.add(response);
        }
        baseResBody.setData(responses);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public BaseRes GetCIInfo(GetCheckItemInfoRequest request) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        GetCheckItemInfoResponse response = new GetCheckItemInfoResponse();
        CheckItemInfo checkItemInfo = checkItemDAO.SelectByRuid(request.getCIRd());
    //检验方法
        CmethodInfo cmethodInfo = cmethodDAO.SelectCmethodGd(checkItemInfo.getCheckMethodGd());
        MethodInfo methodInfo = new MethodInfo();
        if(cmethodInfo!=null){
            methodInfo.setCMethodRd(cmethodInfo.getRuid());
            methodInfo.setCheckMethodName(cmethodInfo.getCheckMethodName());
            response.setMethodInfo(methodInfo);
        }else {
            response.setMethodInfo(methodInfo);
        }
        response.setCreateTime(CommonUtils.getFormatTwo(checkItemInfo.getCreateTime()));
        response.setCheckItemC(checkItemInfo.getCheckItemC());
        response.setCheckItemCode(checkItemInfo.getCheckItemCode());
        response.setCheckItemName(checkItemInfo.getCheckItemName());
        response.setCreator(checkItemInfo.getCreator());
        response.setLastModifyMan(checkItemInfo.getLastModifyMan());
        response.setLastModifyTime(CommonUtils.getFormatTwo(checkItemInfo.getLastModifyTime()));
        response.setRemark(checkItemInfo.getRemark());
        response.setSureType(checkItemInfo.getSureType());
        baseResBody.setData(response);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public BaseRes AddCIInfo(GetCheckItemInfoResponse req) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        if (req.getCheckItemName() == null || req.getCheckItemName().equals("")) {
            throw new SystemException("EEEE", "请输入名称！");
        }
        if (req.getCheckItemCode() == null || req.getCheckItemCode().equals("")) {
            throw new SystemException("EEEE", "请输入代码！");
        }
        List<CheckItemInfo> checkItemInfoList = checkItemDAO.SelectByCode(req.getCheckItemCode());
        if (checkItemInfoList.size() > 0) {
            throw new SystemException("EEEE", "代码重复！");
        }

        CheckItemInfo checkItemInfo = new CheckItemInfo();
        //检验方法
        CmethodInfo cmethodInfo = cmethodDAO.SelectByRuid(req.getCMethodRd());
        if (cmethodInfo != null) {
            checkItemInfo.setCheckMethodGd(cmethodInfo.getGuid());
        }
        checkItemInfo.setGuid(CommonUtils.getRandomNumber());
        checkItemInfo.setCheckItemC(req.getCheckItemC());
        checkItemInfo.setCheckItemCode(req.getCheckItemCode());
        checkItemInfo.setCheckItemName(req.getCheckItemName());
        checkItemInfo.setSureType(req.getSureType());
        checkItemInfo.setCreateTime(new Date());
        checkItemInfo.setCreator(CommonUtils.readUser().getRealName());
        checkItemInfo.setRemark(req.getRemark());
        checkItemDAO.Insert(checkItemInfo);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public BaseRes RmDelCIInfo(GetCheckItemInfoResponse reqBD00) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        CheckItemInfo checkItemInfo = checkItemDAO.SelectByRuid(reqBD00.getCIRd());
        if (checkItemInfo == null) {
            throw new SystemException("EEEE", "暂无信息！");
        }
        checkItemDAO.DeleteByGuid(checkItemInfo.getGuid());
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public BaseRes AddSaveCIInfo(GetCheckItemInfoResponse reqBD00) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        if (reqBD00.getCheckItemName() == null || reqBD00.getCheckItemName().equals("")) {
            throw new SystemException("EEEE", "请输入名称！");
        }
        if (reqBD00.getCheckItemCode() == null || reqBD00.getCheckItemCode().equals("")) {
            throw new SystemException("EEEE", "请输入代码！");
        }
        List<CheckItemInfo> checkItemInfoList = checkItemDAO.SelectByCode(reqBD00.getCheckItemCode());
        if (checkItemInfoList.size() > 0) {
            if (checkItemInfoList.get(0).getRuid() != reqBD00.getCIRd()) {
                throw new SystemException("EEEE", "代码重复！");
            }
        }
        CheckItemInfo JYcheckItemInfo = checkItemDAO.SelectByRuid(reqBD00.getCIRd());
        CheckItemInfo checkItemInfo = new CheckItemInfo();
        //检验方法
        CmethodInfo cmethodInfo = cmethodDAO.SelectByRuid(reqBD00.getCMethodRd());
        if (cmethodInfo != null) {
            checkItemInfo.setCheckMethodGd(cmethodInfo.getGuid());
        }
        checkItemInfo.setGuid(JYcheckItemInfo.getGuid());
        checkItemInfo.setCheckItemC(reqBD00.getCheckItemC());
        checkItemInfo.setCheckItemCode(reqBD00.getCheckItemCode());
        checkItemInfo.setCheckItemName(reqBD00.getCheckItemName());
        checkItemInfo.setSureType(reqBD00.getSureType());
        checkItemInfo.setLastModifyTime(new Date());
        checkItemInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        checkItemInfo.setRemark(reqBD00.getRemark());
        checkItemDAO.UpdateByGuid(checkItemInfo);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }
}
