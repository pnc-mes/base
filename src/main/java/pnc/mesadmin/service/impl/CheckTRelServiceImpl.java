package pnc.mesadmin.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.BaseDto.BaseResB;
import pnc.mesadmin.dto.CheckTRelDTO.CheckTRelBaseDTO;
import pnc.mesadmin.dto.CheckTRelDTO.GetAllCheckTRelInfoDTO;
import pnc.mesadmin.dto.CheckTRelDTO.SaveCheckTRelBaseDTO;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.CheckTRelService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-03-25
 **/
@Service
@Transactional
public class CheckTRelServiceImpl implements CheckTRelService {
    @Resource
    private BaseIService baseIService;
    @Resource
    private CheckTRelDAO checkTRelDAO;
    @Resource
    private CheckTRelDtlDAO checkTRelDtlDAO;
    //供应商
    @Resource
    private SupplierDAO supplierDAO;
    //客户
    @Resource
    private CustomerDAO customerDAO;
    //文档
    @Resource
    private CheckDocDAO checkDocDAO;
    //模板
    @Resource
    private CheckTempDAO checkTempDAO;
    //水平
    @Resource
    private ClevelDAO clevelDAO;
    //AQL
    @Resource
    private AQLRuleDAO aqlRuleDAO;
    //物料类型
    @Resource
    private MaTypeDAO maTypeDAO;
    //物料
    @Resource
    private MaVerDAO maVerDAO;

    @Override
    public BaseRes GetAllCTRelInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        List<GetAllCheckTRelInfoDTO> responses = new ArrayList<>();
        List<CheckTRelInfo> lists = baseIService.QALLBaseInfo(CheckTRelDAO.class, "SelectAll",
                argInitDataFields, argPageInfo);
        for (CheckTRelInfo model : lists) {
            GetAllCheckTRelInfoDTO response = new GetAllCheckTRelInfoDTO();
            response.setCTRelRd(model.getRuid());
            response.setTempRelName(model.getTempRelName());
            responses.add(response);
        }
        baseResBody.setData(responses);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public BaseRes GetCTRelInfo(SaveCheckTRelBaseDTO request) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        CheckTRelBaseDTO responses = new CheckTRelBaseDTO();
        CheckTRelInfo checkTRelInfo = checkTRelDAO.SelectByRuid(request.getCTRelRd());
        responses.setCreateTime(CommonUtils.getFormatTwo(checkTRelInfo.getCreateTime()));
        responses.setCreator(checkTRelInfo.getCreator());
        responses.setLastModifyMan(checkTRelInfo.getLastModifyMan());
        responses.setLastModifyTime(CommonUtils.getFormatTwo(checkTRelInfo.getLastModifyTime()));
        responses.setRemark(checkTRelInfo.getRemark());
        responses.setTempRelName(checkTRelInfo.getTempRelName());
        responses.setTempRelType(checkTRelInfo.getTempRelType());
        responses.setRelType(checkTRelInfo.getRelType());

        //供应商
        CheckTRelBaseDTO.Supplier supplier = new CheckTRelBaseDTO.Supplier();
        SupplierInfo supplierInfo = supplierDAO.SelectByGuid(checkTRelInfo.getSupplierGd());
        if (supplierInfo != null) {
            supplier.setSupplierRd(supplierInfo.getRuid());
            supplier.setSupplierName(supplierInfo.getSupplierName());
        }
        //客户
        CheckTRelBaseDTO.Customer customer = new CheckTRelBaseDTO.Customer();
        CustomerInfo customerInfo = customerDAO.SelectByGuid(checkTRelInfo.getCustomerGd());
        if (customerInfo != null) {
            customer.setCustomerRd(customerInfo.getRuid());
            customer.setCustomerName(customerInfo.getCustomerName());
        }
        //关联类型 00#物料 01#物料类型
        CheckTRelBaseDTO.RelInfo relInfo = new CheckTRelBaseDTO.RelInfo();
        if (checkTRelInfo.getRelType().equals("00")) {
            MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(checkTRelInfo.getRelGd());
            relInfo.setRelRd(maVerInfo.getRuid());
            relInfo.setRelName(maVerInfo.getMaterialName());
        } else if (checkTRelInfo.getRelType().equals("01")) {
            if("00".equals(checkTRelInfo.getRelGd())){
                relInfo.setRelRd(00);
                relInfo.setRelName("产成品");
            }else if("01".equals(checkTRelInfo.getRelGd())){
                relInfo.setRelRd(01);
                relInfo.setRelName("半成品");
            }else if("02".equals(checkTRelInfo.getRelGd())){
                relInfo.setRelRd(02);
                relInfo.setRelName("原料");
            }else if("03".equals(checkTRelInfo.getRelGd())){
                relInfo.setRelRd(03);
                relInfo.setRelName("其他");
            }else {
            MaTypeInfo maTypeInfo = maTypeDAO.SelectGuid(checkTRelInfo.getRelGd());
            relInfo.setRelRd(maTypeInfo.getRuid());
            relInfo.setRelName(maTypeInfo.getMaTName());
            }
        }
        //文档
        CheckTRelBaseDTO.CDOC cdoc = new CheckTRelBaseDTO.CDOC();
        CheckDocInfo checkDocInfo = checkDocDAO.SelectByGuid(checkTRelInfo.getCheckDocGd());
        if (checkDocInfo != null) {
            cdoc.setCDOCRd(checkDocInfo.getRuid());
            cdoc.setCheckDocName(checkDocInfo.getCheckDocName());
        }
        //检验模板详情
        List<CheckTRelBaseDTO.CTRelDtlInfo> ctRelDtlInfoList = new ArrayList<>();
        List<CheckTRelDtlInfo> checkTRelDtlInfoList = checkTRelDtlDAO.SelectAllByRelGd(checkTRelInfo.getGuid());
        if (checkTRelDtlInfoList != null) {
            for (CheckTRelDtlInfo model : checkTRelDtlInfoList) {
                CheckTRelBaseDTO.CTRelDtlInfo ctRelDtlInfo = new CheckTRelBaseDTO.CTRelDtlInfo();
                //检验模板
                CheckTRelBaseDTO.CPT cpt = new CheckTRelBaseDTO.CPT();
                CheckTempInfo checkTempInfo = checkTempDAO.SelectByGuid(model.getCheckTempGd());
                if (checkTempInfo != null) {
                    cpt.setCPTRd(checkTempInfo.getRuid());
                    cpt.setCheckTempName(checkTempInfo.getCheckTempName());
                }
                //水平
                CheckTRelBaseDTO.CLevel cLevel = new CheckTRelBaseDTO.CLevel();
                ClevelInfo clevelInfo = clevelDAO.SelectByGuid(model.getCheckLevelGd());
                if (clevelInfo != null) {
                    cLevel.setCLevelRd(clevelInfo.getRuid());
                    cLevel.setCheckLevelName(clevelInfo.getCheckLevelName());
                }
                //AQL
                CheckTRelBaseDTO.AQLRule aqlRule = new CheckTRelBaseDTO.AQLRule();
                AQLRuleInfo aqlRuleInfo = aqlRuleDAO.SelectByGuid(model.getAQLRuleGd());
                if (aqlRuleInfo != null) {
                    aqlRule.setAQLRuleRd(aqlRuleInfo.getRuid());
                    aqlRule.setAQLRuleName(aqlRuleInfo.getAQLRuleName());
                }
                ctRelDtlInfo.setAQLRule(aqlRule);
                ctRelDtlInfo.setCLevel(cLevel);
                ctRelDtlInfo.setCPT(cpt);
                ctRelDtlInfoList.add(ctRelDtlInfo);
            }
        }
        responses.setCTRelDtlInfo(ctRelDtlInfoList);
        responses.setCDOC(cdoc);
        responses.setSupplier(supplier);
        responses.setRelInfo(relInfo);
        responses.setCustomer(customer);
        baseResBody.setData(responses);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public BaseRes AddCTRelInfo(SaveCheckTRelBaseDTO reqBD00) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        if (StringUtils.isBlank(reqBD00.getTempRelName()))
            throw new SystemException("EEEE", "名称不能为空！");
        if (checkTRelDAO.SelectByName(reqBD00.getTempRelName()) != null)
            throw new SystemException("EEEE", "名称不能重复！");
        CheckTRelInfo checkTRelInfo = new CheckTRelInfo();
        checkTRelInfo.setGuid(CommonUtils.getRandomNumber());
        checkTRelInfo.setCreateTime(new Date());
        checkTRelInfo.setCreator(CommonUtils.readUser().getRealName());
        checkTRelInfo.setRemark(reqBD00.getRemark());
        checkTRelInfo.setTempRelName(reqBD00.getTempRelName());
        checkTRelInfo.setTempRelType(reqBD00.getTempRelType());
        checkTRelInfo.setRelType(reqBD00.getRelType());
        //关联类型 00#物料 01#物料类型
        if (checkTRelInfo.getRelType().equals("00")) {
            MaVerInfo maVerInfo = maVerDAO.SelectByRuid(reqBD00.getRelRd());
            checkTRelInfo.setRelGd(maVerInfo.getGuid());
        } else if (checkTRelInfo.getRelType().equals("01")) {
            if(00==reqBD00.getRelRd()){
                checkTRelInfo.setRelGd("00");
            }else if(01==reqBD00.getRelRd()){
                checkTRelInfo.setRelGd("01");
            }else if(02==reqBD00.getRelRd()){
                checkTRelInfo.setRelGd("02");
            }else if(03==reqBD00.getRelRd()){
                checkTRelInfo.setRelGd("03");
            }else {
            MaTypeInfo maTypeInfo = maTypeDAO.SelectByRuid(reqBD00.getRelRd());
            checkTRelInfo.setRelGd(maTypeInfo.getGuid());
            }
        }
        //供应商
        SupplierInfo supplierInfo = supplierDAO.SelectBySuppId(reqBD00.getSupplierRd());
        if (supplierInfo != null) {
            checkTRelInfo.setSupplierGd(supplierInfo.getGuid());
        }
        //客户
        CustomerInfo customerInfo = customerDAO.SelectByRuid(reqBD00.getCustomerRd());
        if (customerInfo != null) {
            checkTRelInfo.setCustomerGd(customerInfo.getGuid());
        }
        //文档
        CheckDocInfo checkDocInfo = checkDocDAO.SelectByRuid(reqBD00.getCDOCRd());
        if (checkDocInfo != null) {
            checkTRelInfo.setCheckDocGd(checkDocInfo.getGuid());
        }

        if (reqBD00.getCTRelDtlInfo() != null) {
            for (SaveCheckTRelBaseDTO.CTRelDtlInfo model : reqBD00.getCTRelDtlInfo()) {
                CheckTRelDtlInfo checkTRelDtlInfo = new CheckTRelDtlInfo();
                checkTRelDtlInfo.setGuid(CommonUtils.getRandomNumber());
                checkTRelDtlInfo.setCreateTime(new Date());
                checkTRelDtlInfo.setCreator(CommonUtils.readUser().getRealName());
                checkTRelDtlInfo.setCheckTRelGd(checkTRelInfo.getGuid());
                checkTRelDtlInfo.setRemark(reqBD00.getRemark());
                //检验模板
                CheckTempInfo checkTempInfo = checkTempDAO.SelectByRuid(model.getCPTRd());
                if (checkTempInfo != null) {
                    checkTRelDtlInfo.setCheckTempGd(checkTempInfo.getGuid());
                }
                //水平
                ClevelInfo clevelInfo = clevelDAO.SelectByRuid(model.getCLevelRd());
                if (clevelInfo != null) {
                    checkTRelDtlInfo.setCheckLevelGd(clevelInfo.getGuid());
                }
                //AQL
                AQLRuleInfo aqlRuleInfo = aqlRuleDAO.SelectByRuid(model.getAQLRuleRd());
                if (aqlRuleInfo != null) {
                    checkTRelDtlInfo.setAQLRuleGd(aqlRuleInfo.getGuid());
                }
                checkTRelDtlDAO.Insert(checkTRelDtlInfo);
            }
        }
        checkTRelDAO.Insert(checkTRelInfo);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public BaseRes RmDelCTRelInfo(SaveCheckTRelBaseDTO reqBD00) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        CheckTRelInfo checkTRelInfo = checkTRelDAO.SelectByRuid(reqBD00.getCTRelRd());
        if (checkTRelInfo == null)
            throw new SystemException("EEEE", "暂无信息！");
        checkTRelDtlDAO.DeleteByRelGd(checkTRelInfo.getGuid());
        checkTRelDAO.DeleteByGuid(checkTRelInfo.getGuid());
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public BaseRes AddSaveCTRelInfo(SaveCheckTRelBaseDTO reqBD00) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        if (StringUtils.isBlank(reqBD00.getTempRelName()))
            throw new SystemException("EEEE", "名称不能为空！");
        CheckTRelInfo JYcheckTRelInfo = checkTRelDAO.SelectByName(reqBD00.getTempRelName());
        if (JYcheckTRelInfo != null && JYcheckTRelInfo.getRuid() != reqBD00.getCTRelRd())
            throw new SystemException("EEEE", "名称不能重复！");
        CheckTRelInfo checkTRelInfo = checkTRelDAO.SelectByRuid(reqBD00.getCTRelRd());
        checkTRelInfo.setLastModifyTime(new Date());
        checkTRelInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        checkTRelInfo.setRemark(reqBD00.getRemark());
        checkTRelInfo.setTempRelName(reqBD00.getTempRelName());
        checkTRelInfo.setTempRelType(reqBD00.getTempRelType());
        checkTRelInfo.setRelType(reqBD00.getRelType());
        //关联类型 00#物料 01#物料类型
        if (checkTRelInfo.getRelType().equals("00")) {
            MaVerInfo maVerInfo = maVerDAO.SelectByRuid(reqBD00.getRelRd());
            checkTRelInfo.setRelGd(maVerInfo.getGuid());
        } else if (checkTRelInfo.getRelType().equals("01")) {
            if(00==reqBD00.getRelRd()){
                checkTRelInfo.setRelGd("00");
            }else if(01==reqBD00.getRelRd()){
                checkTRelInfo.setRelGd("01");
            }else if(02==reqBD00.getRelRd()){
                checkTRelInfo.setRelGd("02");
            }else if(03==reqBD00.getRelRd()){
                checkTRelInfo.setRelGd("03");
            }else {
            MaTypeInfo maTypeInfo = maTypeDAO.SelectByRuid(reqBD00.getRelRd());
            checkTRelInfo.setRelGd(maTypeInfo.getGuid());
            }
        }
        //供应商
        SupplierInfo supplierInfo = supplierDAO.SelectBySuppId(reqBD00.getSupplierRd());
        if (supplierInfo != null) {
            checkTRelInfo.setSupplierGd(supplierInfo.getGuid());
        }
        //客户
        CustomerInfo customerInfo = customerDAO.SelectByRuid(reqBD00.getCustomerRd());
        if (customerInfo != null) {
            checkTRelInfo.setCustomerGd(customerInfo.getGuid());
        }
        //文档
        CheckDocInfo checkDocInfo = checkDocDAO.SelectByRuid(reqBD00.getCDOCRd());
        if (checkDocInfo != null) {
            checkTRelInfo.setCheckDocGd(checkDocInfo.getGuid());
        }

        checkTRelDtlDAO.DeleteByRelGd(checkTRelInfo.getGuid());
        if (reqBD00.getCTRelDtlInfo() != null) {
            for (SaveCheckTRelBaseDTO.CTRelDtlInfo model : reqBD00.getCTRelDtlInfo()) {
                CheckTRelDtlInfo checkTRelDtlInfo = new CheckTRelDtlInfo();
                checkTRelDtlInfo.setGuid(CommonUtils.getRandomNumber());
                checkTRelDtlInfo.setCreateTime(new Date());
                checkTRelDtlInfo.setCreator(CommonUtils.readUser().getRealName());
                checkTRelDtlInfo.setCheckTRelGd(checkTRelInfo.getGuid());
                checkTRelDtlInfo.setRemark(reqBD00.getRemark());
                //检验模板
                CheckTempInfo checkTempInfo = checkTempDAO.SelectByRuid(model.getCPTRd());
                if (checkTempInfo != null) {
                    checkTRelDtlInfo.setCheckTempGd(checkTempInfo.getGuid());
                }
                //水平
                ClevelInfo clevelInfo = clevelDAO.SelectByRuid(model.getCLevelRd());
                if (clevelInfo != null) {
                    checkTRelDtlInfo.setCheckLevelGd(clevelInfo.getGuid());
                }
                //AQL
                AQLRuleInfo aqlRuleInfo = aqlRuleDAO.SelectByRuid(model.getAQLRuleRd());
                if (aqlRuleInfo != null) {
                    checkTRelDtlInfo.setAQLRuleGd(aqlRuleInfo.getGuid());
                }
                checkTRelDtlDAO.Insert(checkTRelDtlInfo);
            }
        }
        checkTRelDAO.UpdateByGuid(checkTRelInfo);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }
}
