package pnc.mesadmin.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.BaseDto.BaseResB;
import pnc.mesadmin.dto.CheckDocDTO.CheckDocBaseDTO;
import pnc.mesadmin.dto.CheckDocDTO.GetAllCheckDocResponse;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.CheckDocService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-03-22
 **/
@Service
@Transactional
public class CheckDocServiceImpl implements CheckDocService {

    @Resource
    private BaseIService baseIService;

    @Resource
    private CheckDocDAO checkDocDAO;

    @Resource
    private CheckDocDtlDAO checkDocDtlDAO;

    @Resource
    private MaTypeDAO maTypeDAO;

    @Resource
    private MaVerDAO maVerDAO;

    @Override
    public BaseRes GetAllCDOCInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        List<GetAllCheckDocResponse> responses = new ArrayList<>();
        List<CheckDocInfo> lists = baseIService.QALLBaseInfo(CheckDocDAO.class, "SelectAll",
                argInitDataFields, argPageInfo);
        for (CheckDocInfo model : lists) {
            GetAllCheckDocResponse response = new GetAllCheckDocResponse();
            response.setCDOCRd(model.getRuid());
            response.setCheckDocName(model.getCheckDocName());
            responses.add(response);
        }
        baseResBody.setData(responses);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public BaseRes GetCDOCInfo(CheckDocBaseDTO request) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        CheckDocBaseDTO response = new CheckDocBaseDTO();
        CheckDocInfo checkDocInfo = checkDocDAO.SelectByRuid(request.getCDOCRd());
        if (checkDocInfo == null)
            throw new SystemException("EEEE", "暂无文档信息！");
        List<CheckDocDtlInfo> checkDocDtlInfos = checkDocDtlDAO.SelectAllByDocGuid(checkDocInfo.getGuid());
        List<CheckDocBaseDTO.CDocInfo> cDocInfos = new ArrayList<>();
        for (CheckDocDtlInfo model : checkDocDtlInfos) {
            CheckDocBaseDTO.CDocInfo cDocInfo = new CheckDocBaseDTO.CDocInfo();
            cDocInfo.setFileName(model.getFileName());
            cDocInfo.setFileUrl(model.getFileUrl());
            cDocInfos.add(cDocInfo);
        }
        response.setCDOCRd(checkDocInfo.getRuid());
        response.setCheckDocName(checkDocInfo.getCheckDocName());
        if (checkDocInfo.getRelGd() != null) {
            if (checkDocInfo.getRelType().equals("00")) {
                MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(checkDocInfo.getRelGd());
                response.setRelRd(maVerInfo.getRuid());
                response.setRelName(maVerInfo.getMaterialName());

            } else if (checkDocInfo.getRelType().equals("01")) {
                if ("00".equals(checkDocInfo.getRelGd())) {

                    response.setRelName("产成品");
                } else if ("01".equals(checkDocInfo.getRelGd())) {

                    response.setRelName("半成品");
                } else if ("02".equals(checkDocInfo.getRelGd())) {

                    response.setRelName("原料");
                } else if ("03".equals(checkDocInfo.getRelGd())) {

                    response.setRelName("其他");
                } else {
                    MaTypeInfo maTypeInfo = maTypeDAO.SelectGuid(checkDocInfo.getRelGd());
                    response.setRelRd(maTypeInfo.getRuid());
                    response.setRelName(maTypeInfo.getMaTName());
                }
            }
        }
        response.setCDocInfo(cDocInfos);
        response.setRelType(checkDocInfo.getRelType());
        response.setCreateTime(CommonUtils.getFormatTwo(checkDocInfo.getCreateTime()));
        response.setCreator(checkDocInfo.getCreator());
        response.setLastModifyMan(checkDocInfo.getLastModifyMan());
        response.setLastModifyTime(CommonUtils.getFormatTwo(checkDocInfo.getLastModifyTime()));
        response.setRemark(checkDocInfo.getRemark());
        baseResBody.setData(response);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public BaseRes AddCDOCInfo(CheckDocBaseDTO reqBD00) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        if (StringUtils.isBlank(reqBD00.getCheckDocName()))
            throw new SystemException("EEEE", "请输入名称！");
        if (checkDocDAO.SelectByName(reqBD00.getCheckDocName()) != null)
            throw new SystemException("EEEE", "名称重复！");
        CheckDocInfo checkDocInfo = new CheckDocInfo();
        checkDocInfo.setGuid(CommonUtils.getRandomNumber());
        checkDocInfo.setCreateTime(new Date());
        checkDocInfo.setCreator(CommonUtils.readUser().getRealName());
        checkDocInfo.setCheckDocName(reqBD00.getCheckDocName());
        checkDocInfo.setRemark(reqBD00.getRemark());
        if (reqBD00.getRelRd() != null) {
            checkDocInfo.setRelType(reqBD00.getRelType());
            if (checkDocInfo.getRelType().equals("00")) {
                MaVerInfo maVerInfo = maVerDAO.SelectByRuid(reqBD00.getRelRd());
                checkDocInfo.setRelGd(maVerInfo.getGuid());
            } else if (checkDocInfo.getRelType().equals("01")) {
                if (00 == reqBD00.getRelRd()) {
                    checkDocInfo.setRelGd("00");
                } else if (01 == reqBD00.getRelRd()) {
                    checkDocInfo.setRelGd("01");
                } else if (02 == reqBD00.getRelRd()) {
                    checkDocInfo.setRelGd("02");
                } else if (03 == reqBD00.getRelRd()) {
                    checkDocInfo.setRelGd("03");
                } else {
                    MaTypeInfo maTypeInfo = maTypeDAO.SelectByRuid(reqBD00.getRelRd());
                    checkDocInfo.setRelGd(maTypeInfo.getGuid());
                }
            }
        }
        if (reqBD00.getCDocInfo() != null) {
            for (CheckDocBaseDTO.CDocInfo model : reqBD00.getCDocInfo()) {
                CheckDocDtlInfo checkDocDtlInfo = new CheckDocDtlInfo();
                checkDocDtlInfo.setGuid(CommonUtils.getRandomNumber());
                checkDocDtlInfo.setCheckDocGd(checkDocInfo.getGuid());
                checkDocDtlInfo.setCreateTime(new Date());
                checkDocDtlInfo.setCreator(CommonUtils.readUser().getRealName());
                checkDocDtlInfo.setFileName(model.getFileName());
                checkDocDtlInfo.setFileUrl(model.getFileUrl());
                checkDocDtlDAO.Insert(checkDocDtlInfo);
            }
        }
        checkDocDAO.Insert(checkDocInfo);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public BaseRes RmDelCDOCInfo(CheckDocBaseDTO reqBD00) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        CheckDocInfo checkDocInfo = checkDocDAO.SelectByRuid(reqBD00.getCDOCRd());
        if (checkDocInfo == null)
            throw new SystemException("EEEE", "暂无信息！");
        checkDocDtlDAO.DeleteByDocGuid(checkDocInfo.getGuid());
        checkDocDAO.DeleteByGuid(checkDocInfo.getGuid());
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public BaseRes AddSaveCDOCInfo(CheckDocBaseDTO reqBD00) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        if (StringUtils.isBlank(reqBD00.getCheckDocName()))
            throw new SystemException("EEEE", "请输入名称！");
        CheckDocInfo JYCh = checkDocDAO.SelectByName(reqBD00.getCheckDocName());
        if (JYCh != null && JYCh.getRuid() != reqBD00.getCDOCRd())
            throw new SystemException("EEEE", "名称重复！");
        CheckDocInfo checkDocInfo = checkDocDAO.SelectByRuid(reqBD00.getCDOCRd());
        checkDocInfo.setLastModifyTime(new Date());
        checkDocInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        checkDocInfo.setCheckDocName(reqBD00.getCheckDocName());
        checkDocInfo.setRemark(reqBD00.getRemark());
        if (reqBD00.getRelRd() != null) {
            checkDocInfo.setRelType(reqBD00.getRelType());
            if (checkDocInfo.getRelType().equals("00")) {
                MaVerInfo maVerInfo = maVerDAO.SelectByRuid(reqBD00.getRelRd());
                checkDocInfo.setRelGd(maVerInfo.getGuid());
            } else if (checkDocInfo.getRelType().equals("01")) {
                if (0 == reqBD00.getRelRd()) {
                    checkDocInfo.setRelGd("00");
                } else if (1 == reqBD00.getRelRd()) {
                    checkDocInfo.setRelGd("01");
                } else if (2 == reqBD00.getRelRd()) {
                    checkDocInfo.setRelGd("02");
                } else if (3 == reqBD00.getRelRd()) {
                    checkDocInfo.setRelGd("03");
                } else {
                    MaTypeInfo maTypeInfo = maTypeDAO.SelectByRuid(reqBD00.getRelRd());
                    checkDocInfo.setRelGd(maTypeInfo.getGuid());
                }
            }
        }
        checkDocDtlDAO.DeleteByDocGuid(checkDocInfo.getGuid());
        if (reqBD00.getCDocInfo() != null) {
            for (CheckDocBaseDTO.CDocInfo model : reqBD00.getCDocInfo()) {
                CheckDocDtlInfo checkDocDtlInfo = new CheckDocDtlInfo();
                checkDocDtlInfo.setGuid(CommonUtils.getRandomNumber());
                checkDocDtlInfo.setCheckDocGd(checkDocInfo.getGuid());
                checkDocDtlInfo.setCreateTime(new Date());
                checkDocDtlInfo.setCreator(CommonUtils.readUser().getRealName());
                checkDocDtlInfo.setFileName(model.getFileName());
                checkDocDtlInfo.setFileUrl(model.getFileUrl());
                checkDocDtlDAO.Insert(checkDocDtlInfo);
            }
        }
        checkDocDAO.UpdateByGuid(checkDocInfo);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public BaseRes AddCopyCDOCInfo(CheckDocBaseDTO reqBD00) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        if (StringUtils.isBlank(reqBD00.getCheckDocName()))
            throw new SystemException("EEEE", "请输入名称！");
        if (checkDocDAO.SelectByName(reqBD00.getCheckDocName()) != null)
            throw new SystemException("EEEE", "名称重复！");
        CheckDocInfo checkDocInfo = checkDocDAO.SelectByRuid(reqBD00.getCDOCRd());
        List<CheckDocDtlInfo> checkDocDtlInfos = checkDocDtlDAO.SelectAllByDocGuid(checkDocInfo.getGuid());
        checkDocInfo.setGuid(CommonUtils.getRandomNumber());
        checkDocInfo.setCreateTime(new Date());
        checkDocInfo.setCreator(CommonUtils.readUser().getRealName());
        checkDocInfo.setCheckDocName(reqBD00.getCheckDocName());
        if (checkDocDtlInfos != null) {
            for (CheckDocDtlInfo model : checkDocDtlInfos) {
                model.setGuid(CommonUtils.getRandomNumber());
                model.setCheckDocGd(checkDocInfo.getGuid());
                model.setCreateTime(new Date());
                model.setCreator(CommonUtils.readUser().getRealName());
                checkDocDtlDAO.Insert(model);
            }
        }
        checkDocDAO.Insert(checkDocInfo);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }
}
