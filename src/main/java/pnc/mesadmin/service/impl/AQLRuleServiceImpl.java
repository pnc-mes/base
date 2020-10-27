package pnc.mesadmin.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.AQLRuleDAO;
import pnc.mesadmin.dao.AQLRuleDtlDAO;
import pnc.mesadmin.dao.ClevelDAO;
import pnc.mesadmin.dto.AQLRuleDTO.AQLRuleBaseDTO;
import pnc.mesadmin.dto.AQLRuleDTO.GetAllAQLRuleResponse;
import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.BaseDto.BaseResB;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.AQLRuleDtlInfo;
import pnc.mesadmin.entity.AQLRuleInfo;
import pnc.mesadmin.entity.ClevelInfo;
import pnc.mesadmin.service.AQLRuleService;
import pnc.mesadmin.service.BaseIService;
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
public class AQLRuleServiceImpl implements AQLRuleService {
    @Resource
    private BaseIService baseIService;
    @Resource
    private AQLRuleDAO aqlRuleDAO;
    @Resource
    private AQLRuleDtlDAO aqlRuleDtlDAO;

    @Resource
    private ClevelDAO clevelDAO;


    @Override
    public BaseRes GetAllAQLInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        List<GetAllAQLRuleResponse> responses = new ArrayList<>();
        List<AQLRuleInfo> lists = baseIService.QALLBaseInfo(AQLRuleDAO.class, "SelectAll",
                argInitDataFields, argPageInfo);
        for (AQLRuleInfo model : lists) {
            GetAllAQLRuleResponse response = new GetAllAQLRuleResponse();
            response.setAQLRuleRd(model.getRuid());
            response.setAQLRuleName(model.getAQLRuleName());
            responses.add(response);
        }
        baseResBody.setData(responses);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public BaseRes GetAQLInfo(AQLRuleBaseDTO request) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        AQLRuleBaseDTO response = new AQLRuleBaseDTO();
        AQLRuleInfo aqlRuleInfo = aqlRuleDAO.SelectByRuid(request.getAQLRuleRd());
        response.setAQLRuleRd(aqlRuleInfo.getRuid());
        response.setAQLRuleName(aqlRuleInfo.getAQLRuleName());
        response.setCreateTime(CommonUtils.getFormatTwo(aqlRuleInfo.getCreateTime()));
        response.setCreator(aqlRuleInfo.getCreator());
        response.setLastModifyMan(aqlRuleInfo.getLastModifyMan());
        response.setLastModifyTime(CommonUtils.getFormatTwo(aqlRuleInfo.getLastModifyTime()));
        response.setRemark(aqlRuleInfo.getRemark());

        ClevelInfo clevelInfo = clevelDAO.SelectByGuid(aqlRuleInfo.getCheckLevelGd());
        AQLRuleBaseDTO.CheckLevel checkLevel = new AQLRuleBaseDTO.CheckLevel();
        checkLevel.setCLevelRd(clevelInfo.getRuid());
        checkLevel.setCheckLevelName(clevelInfo.getCheckLevelName());
        response.setCheckLevel(checkLevel);
        List<AQLRuleDtlInfo> aqlRuleDtlInfo = aqlRuleDtlDAO.SelectAllByAQLGd(aqlRuleInfo.getGuid());
        List<AQLRuleBaseDTO.AQLDtlInfo> aqlDtlInfoList = new ArrayList<>();
        for (AQLRuleDtlInfo model : aqlRuleDtlInfo) {
            AQLRuleBaseDTO.AQLDtlInfo aqlDtlInfo = new AQLRuleBaseDTO.AQLDtlInfo();
            aqlDtlInfo.setAcceptNum(model.getAcceptNum());
            aqlDtlInfo.setMaxNum(model.getMaxNum());
            aqlDtlInfo.setMinNum(model.getMinNum());
            aqlDtlInfo.setRejectNum(model.getRejectNum());
            aqlDtlInfo.setSamplingNum(model.getSamplingNum());
            aqlDtlInfoList.add(aqlDtlInfo);
        }
        response.setAQLDtlInfo(aqlDtlInfoList);
        baseResBody.setData(response);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public BaseRes AddAQLInfo(AQLRuleBaseDTO reqBD00) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        if (StringUtils.isBlank(reqBD00.getAQLRuleName()))
            throw new SystemException("EEEE", "AQL名称不能为空！");
        if (aqlRuleDAO.SelectByName(reqBD00.getAQLRuleName()) != null)
            throw new SystemException("EEEE", "AQL名称不能重复！");
        AQLRuleInfo aqlRuleInfo = new AQLRuleInfo();
        aqlRuleInfo.setGuid(CommonUtils.getRandomNumber());
        aqlRuleInfo.setAQLRuleName(reqBD00.getAQLRuleName());
        aqlRuleInfo.setCreateTime(new Date());
        aqlRuleInfo.setCreator(CommonUtils.readUser().getRealName());
        aqlRuleInfo.setRemark(reqBD00.getRemark());
        ClevelInfo clevelInfo = clevelDAO.SelectByRuid(reqBD00.getCLevelRd());
        aqlRuleInfo.setCheckLevelGd(clevelInfo.getGuid());
        aqlRuleDAO.Insert(aqlRuleInfo);

        for (AQLRuleBaseDTO.AQLDtlInfo model : reqBD00.getAQLDtlInfo()) {
            AQLRuleDtlInfo aqlDtlInfo = new AQLRuleDtlInfo();
            aqlDtlInfo.setGuid(CommonUtils.getRandomNumber());
            aqlDtlInfo.setAQLRuleGd(aqlRuleInfo.getGuid());
            aqlDtlInfo.setAcceptNum(model.getAcceptNum());
            aqlDtlInfo.setMaxNum(model.getMaxNum());
            aqlDtlInfo.setMinNum(model.getMinNum());
            aqlDtlInfo.setRejectNum(model.getRejectNum());
            aqlDtlInfo.setSamplingNum(model.getSamplingNum());
            aqlDtlInfo.setCreateTime(new Date());
            aqlDtlInfo.setCreator(CommonUtils.readUser().getRealName());
            aqlRuleDtlDAO.Insert(aqlDtlInfo);
        }
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public BaseRes RmDelAQLInfo(AQLRuleBaseDTO reqBD00) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        AQLRuleInfo aqlRuleInfo = aqlRuleDAO.SelectByRuid(reqBD00.getAQLRuleRd());
        if (aqlRuleInfo == null)
            throw new SystemException("EEEE", "暂无信息！");
        aqlRuleDtlDAO.DeleteByAQLGd(aqlRuleInfo.getGuid());
        aqlRuleDAO.DeleteByGuid(aqlRuleInfo.getGuid());
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public BaseRes AddSaveAQLInfo(AQLRuleBaseDTO reqBD00) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        if (StringUtils.isBlank(reqBD00.getAQLRuleName()))
            throw new SystemException("EEEE", "AQL名称不能为空！");
        AQLRuleInfo JYAqlRuleInfo = aqlRuleDAO.SelectByName(reqBD00.getAQLRuleName());
        if (JYAqlRuleInfo != null && JYAqlRuleInfo.getRuid() != reqBD00.getAQLRuleRd())
            throw new SystemException("EEEE", "AQL名称不能重复！");
        AQLRuleInfo aqlRuleInfo = aqlRuleDAO.SelectByRuid(reqBD00.getAQLRuleRd());
        aqlRuleInfo.setAQLRuleName(reqBD00.getAQLRuleName());
        aqlRuleInfo.setLastModifyTime(new Date());
        aqlRuleInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        aqlRuleInfo.setRemark(reqBD00.getRemark());
        ClevelInfo clevelInfo = clevelDAO.SelectByRuid(reqBD00.getCLevelRd());
        aqlRuleInfo.setCheckLevelGd(clevelInfo.getGuid());
        aqlRuleDAO.UpdateByGuid(aqlRuleInfo);
        aqlRuleDtlDAO.DeleteByAQLGd(aqlRuleInfo.getGuid());
        for (AQLRuleBaseDTO.AQLDtlInfo model : reqBD00.getAQLDtlInfo()) {
            AQLRuleDtlInfo aqlDtlInfo = new AQLRuleDtlInfo();
            aqlDtlInfo.setGuid(CommonUtils.getRandomNumber());
            aqlDtlInfo.setAQLRuleGd(aqlRuleInfo.getGuid());
            aqlDtlInfo.setAcceptNum(model.getAcceptNum());
            aqlDtlInfo.setMaxNum(model.getMaxNum());
            aqlDtlInfo.setMinNum(model.getMinNum());
            aqlDtlInfo.setRejectNum(model.getRejectNum());
            aqlDtlInfo.setSamplingNum(model.getSamplingNum());
            aqlDtlInfo.setCreateTime(new Date());
            aqlDtlInfo.setCreator(CommonUtils.readUser().getRealName());
            aqlRuleDtlDAO.Insert(aqlDtlInfo);
        }
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }
}
