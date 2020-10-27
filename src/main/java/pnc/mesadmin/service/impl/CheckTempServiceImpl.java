package pnc.mesadmin.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.CheckItemDAO;
import pnc.mesadmin.dao.CheckTempDAO;
import pnc.mesadmin.dao.CheckTempDtlDAO;
import pnc.mesadmin.dao.CheckTypeDAO;
import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.BaseDto.BaseResB;
import pnc.mesadmin.dto.CheckTempDTO.CheckTempBaseDto;
import pnc.mesadmin.dto.CheckTempDTO.GetAllCheckTempResponse;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.CheckItemInfo;
import pnc.mesadmin.entity.CheckTempDtlInfo;
import pnc.mesadmin.entity.CheckTempInfo;
import pnc.mesadmin.entity.CheckTypeInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.CheckTempService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-03-21
 **/
@Service
@Transactional
public class CheckTempServiceImpl implements CheckTempService {
    @Resource
    private BaseIService baseIService;
    @Resource
    private CheckTempDAO checkTempDAO;
    @Resource
    private CheckTempDtlDAO checkTempDtlDAO;
    @Resource
    private CheckItemDAO checkItemDAO;
    @Resource
    private CheckTypeDAO checkTypeDAO;

    @Override
    public BaseRes GetAllCTPInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        List<GetAllCheckTempResponse> responses = new ArrayList<>();
        List<CheckTempInfo> lists = baseIService.QALLBaseInfo(CheckTempDAO.class, "SelectAll",
                argInitDataFields, argPageInfo);
        for (CheckTempInfo model : lists) {
            GetAllCheckTempResponse response = new GetAllCheckTempResponse();
            response.setCPTRd(model.getRuid());
            response.setCheckTempName(model.getCheckTempName());
            responses.add(response);
        }
        baseResBody.setData(responses);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public BaseRes GetCTPInfo(CheckTempBaseDto request) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        CheckTempBaseDto response = new CheckTempBaseDto();
        CheckTempInfo checkTempInfo = checkTempDAO.SelectByRuid(request.getCPTRd());
        if (checkTempInfo == null)
            throw new SystemException("EEEE", "暂无信息!");
        List<CheckTempDtlInfo> checkTempDtlInfoList = checkTempDtlDAO.SelectAllByTempGuid(checkTempInfo.getGuid());
        List<CheckTempBaseDto.CPTInfo> checkTempBaseDtos = new ArrayList<>();
        for (CheckTempDtlInfo model : checkTempDtlInfoList) {
            CheckTempBaseDto.CPTInfo cptInfo = new CheckTempBaseDto.CPTInfo();
            if (model.getRelType().equals("00")) {
                CheckItemInfo checkItemInfo = checkItemDAO.SelectByGuid(model.getRelGd());
                cptInfo.setRelRd(checkItemInfo.getRuid());
                cptInfo.setRelName(checkItemInfo.getCheckItemName());
                cptInfo.setRelType("00");
            } else if (model.getRelType().equals("01")) {
                CheckTypeInfo checkTypeInfo = checkTypeDAO.SelectByGuid(model.getRelGd());
                cptInfo.setRelRd(checkTypeInfo.getRuid());
                cptInfo.setRelName(checkTypeInfo.getCheckTypeName());
                cptInfo.setRelType("01");
            }
            checkTempBaseDtos.add(cptInfo);
        }
        response.setCheckTempName(checkTempInfo.getCheckTempName());
        response.setCPTRd(checkTempInfo.getRuid());
        response.setStatus(checkTempInfo.getStatus());
        response.setCreateTime(CommonUtils.getFormatTwo(checkTempInfo.getCreateTime()));
        response.setCreator(checkTempInfo.getCreator());
        response.setLastModifyMan(checkTempInfo.getLastModifyMan());
        response.setLastModifyTime(CommonUtils.getFormatTwo(checkTempInfo.getLastModifyTime()));
        response.setRemark(checkTempInfo.getRemark());
        response.setCPTInfo(checkTempBaseDtos);
        baseResBody.setData(response);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public BaseRes AddCTPInfo(CheckTempBaseDto reqBD00) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        if (StringUtils.isBlank(reqBD00.getCheckTempName()))
            throw new SystemException("EEEE", "请填写名称！");
        if (checkTempDAO.SelectByName(reqBD00.getCheckTempName()) != null)
            throw new SystemException("EEEE", "名称重复！");
        CheckTempInfo checkTempInfo = new CheckTempInfo();
        checkTempInfo.setGuid(CommonUtils.getRandomNumber());
        checkTempInfo.setCheckTempName(reqBD00.getCheckTempName());
        checkTempInfo.setStatus(reqBD00.getStatus());
        checkTempInfo.setCreateTime(new Date());
        checkTempInfo.setCreator(CommonUtils.readUser().getRealName());
        checkTempInfo.setRemark(reqBD00.getRemark());
        for (CheckTempBaseDto.CPTInfo model : reqBD00.getCPTInfo()) {
            CheckTempDtlInfo checkTempDtlInfo = new CheckTempDtlInfo();
            checkTempDtlInfo.setGuid(CommonUtils.getRandomNumber());
            checkTempDtlInfo.setCheckTempGd(checkTempInfo.getGuid());
            checkTempDtlInfo.setCreator(CommonUtils.readUser().getRealName());
            checkTempDtlInfo.setCreateTime(new Date());
            if (model.getRelType().equals("00")) {
                CheckItemInfo checkItemInfo = checkItemDAO.SelectByRuid(model.getRelRd());
                if (checkItemInfo != null) {
                    checkTempDtlInfo.setRelGd(checkItemInfo.getGuid());
                    checkTempDtlInfo.setRelType(model.getRelType());
                }
            } else if (model.getRelType().equals("01")) {
                CheckTypeInfo checkTypeInfo = checkTypeDAO.SelectByRuid(model.getRelRd());
                if (checkTypeInfo != null) {
                    checkTempDtlInfo.setRelGd(checkTypeInfo.getGuid());
                    checkTempDtlInfo.setRelType(model.getRelType());
                }
            }
            checkTempDtlDAO.Insert(checkTempDtlInfo);
        }
        checkTempDAO.Insert(checkTempInfo);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public BaseRes RmDelCTPInfo(CheckTempBaseDto reqBD00) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        if (reqBD00.getCPTRd() == null) {
            throw new SystemException("EEEE", "暂无信息！");
        }
        CheckTempInfo checkTempInfo = checkTempDAO.SelectByRuid(reqBD00.getCPTRd());
        checkTempDtlDAO.DeleteByTempGuid(checkTempInfo.getGuid());
        checkTempDAO.DeleteByGuid(checkTempInfo.getGuid());
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public BaseRes AddSaveCTPInfo(CheckTempBaseDto reqBD00) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        if (StringUtils.isBlank(reqBD00.getCheckTempName()))
            throw new SystemException("EEEE", "请填写名称！");
        CheckTempInfo JYcheckTempInfo = checkTempDAO.SelectByName(reqBD00.getCheckTempName());
        if (JYcheckTempInfo != null && JYcheckTempInfo.getRuid() != reqBD00.getCPTRd())
            throw new SystemException("EEEE", "名称重复！");
        CheckTempInfo checkTempInfo = checkTempDAO.SelectByRuid(reqBD00.getCPTRd());
        checkTempInfo.setCheckTempName(reqBD00.getCheckTempName());
        checkTempInfo.setStatus(reqBD00.getStatus());
        checkTempInfo.setLastModifyTime(new Date());
        checkTempInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        checkTempInfo.setRemark(reqBD00.getRemark());
        checkTempDtlDAO.DeleteByTempGuid(checkTempInfo.getGuid());
        for (CheckTempBaseDto.CPTInfo model : reqBD00.getCPTInfo()) {
            CheckTempDtlInfo checkTempDtlInfo = new CheckTempDtlInfo();
            checkTempDtlInfo.setGuid(CommonUtils.getRandomNumber());
            checkTempDtlInfo.setCheckTempGd(checkTempInfo.getGuid());
            checkTempDtlInfo.setCreator(CommonUtils.readUser().getRealName());
            checkTempDtlInfo.setCreateTime(new Date());
            if (model.getRelType().equals("00")) {
                CheckItemInfo checkItemInfo = checkItemDAO.SelectByRuid(model.getRelRd());
                if (checkItemInfo != null) {
                    checkTempDtlInfo.setRelGd(checkItemInfo.getGuid());
                    checkTempDtlInfo.setRelType(model.getRelType());
                }
            } else if (model.getRelType().equals("01")) {
                CheckTypeInfo checkTypeInfo = checkTypeDAO.SelectByRuid(model.getRelRd());
                if (checkTypeInfo != null) {
                    checkTempDtlInfo.setRelGd(checkTypeInfo.getGuid());
                    checkTempDtlInfo.setRelType(model.getRelType());
                }
            }
            checkTempDtlDAO.Insert(checkTempDtlInfo);
        }
        checkTempDAO.UpdateByGuid(checkTempInfo);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public BaseRes AddCopyCTPInfo(CheckTempBaseDto reqBD00) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        if (StringUtils.isBlank(reqBD00.getCheckTempName()))
            throw new SystemException("EEEE", "请填写名称！");
        if (checkTempDAO.SelectByName(reqBD00.getCheckTempName())!=null)
            throw new SystemException("EEEE", "名称重复！");
        CheckTempInfo checkTempInfo = checkTempDAO.SelectByRuid(reqBD00.getCPTRd());
        List<CheckTempDtlInfo> checkTempDtlInfoList = checkTempDtlDAO.SelectAllByTempGuid(checkTempInfo.getGuid());
        checkTempInfo.setGuid(CommonUtils.getRandomNumber());
        checkTempInfo.setCreateTime(new Date());
        checkTempInfo.setCreator(CommonUtils.readUser().getRealName());
        checkTempInfo.setCheckTempName(reqBD00.getCheckTempName());
        for (CheckTempDtlInfo model : checkTempDtlInfoList) {
            model.setGuid(CommonUtils.getRandomNumber());
            model.setCheckTempGd(checkTempInfo.getGuid());
            model.setCreator(CommonUtils.readUser().getRealName());
            model.setCreateTime(new Date());
            checkTempDtlDAO.Insert(model);
        }
        checkTempDAO.Insert(checkTempInfo);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }
}
