package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.GetAllCuOrderInfo.*;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.CuOrderService;
import pnc.mesadmin.service.GConfigIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CuOrderServiceImpl implements CuOrderService {

    @Resource
    private CuOrderDao cuOrderDao;

    @Resource
    private MaVerDAO maVerDAO;

    @Resource
    private MaterialDAO materialDAO;

    @Resource
    private UnitDAO unitDAO;

    @Resource
    private BaseIService baseIService;

    @Resource
    private WODAO wODAO;
    @Resource
    private CoTypeDAO coTypeDAO;
    @Resource
    private CustomerDAO customerDAO;
    @Resource
    CodeRuleDAO codeRuleDAO;

    @Resource
    private GConfigIService gConfigIService;

    //获取所有订单列表
    @Override
    public GetAllCuOrderInfoRes GetAllCOInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllCuOrderInfoRes getAllCuOrderInfoRes = new GetAllCuOrderInfoRes();
        GetAllCuOrderInfoResB getAllCuOrderInfoResB = new GetAllCuOrderInfoResB();
        List<GetAllCuOrderInfoResD> datas = new ArrayList<>();
        List<CuOrderInfo> objEWorkCenterInfos = baseIService.QALLBaseInfo(CuOrderDao.class, "SelectAllCuOrderInfo",
                argInitDataFields, argPageInfo);
        for (CuOrderInfo model : objEWorkCenterInfos) {
            GetAllCuOrderInfoResD getAllCuOrderInfoResD = new GetAllCuOrderInfoResD();
            getAllCuOrderInfoResD.setCoRd(model.getRuid());
            getAllCuOrderInfoResD.setOrderCode(model.getOrderCode());
            datas.add(getAllCuOrderInfoResD);
        }
        getAllCuOrderInfoResB.setData(datas);
        getAllCuOrderInfoRes.setBody(getAllCuOrderInfoResB);
        getAllCuOrderInfoRes.setStatus("00");
        return getAllCuOrderInfoRes;
    }

    //获取订单关联工单信息
    @Override
    public GetAllCuOrderInfoRes GetAllCOBInfo(GetCuOrderReqBD00 getCuOrderReqBD00) {
        GetAllCuOrderInfoRes getAllCuOrderInfoRes = new GetAllCuOrderInfoRes();
        GetAllCuOrderInfoResB getAllCuOrderInfoResB = new GetAllCuOrderInfoResB();
        GetAllCuOrderInfoResD getAllCuOrderInfoResD = new GetAllCuOrderInfoResD();
        CuOrderInfo cuOrderInfo = cuOrderDao.SelectByRuId(getCuOrderReqBD00.getCoRd());
        List<GetAllWoInfoRes> woInfos = new ArrayList<>();
        if (cuOrderInfo != null) {
            List<WoInfo> woInfoList = wODAO.SelectWoInfoByOrderCode(cuOrderInfo.getOrderCode());

            for (WoInfo model : woInfoList) {
                GetAllWoInfoRes woInfo = new GetAllWoInfoRes();
                //订单信息
                woInfo.setWoRd(model.getRuid());
                woInfo.setWoCode(model.getWoCode());
                //物料信息
                MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(model.getMaVerGd());
                if (objEMaVerInfo != null) {
                    MaterialInfo objEMaterialInfo = materialDAO.SelectByGuid(objEMaVerInfo.getMaGd());
                    if (objEMaterialInfo != null) {
                        woInfo.setMaVerRd(objEMaVerInfo.getRuid());
                        woInfo.setMaName(objEMaterialInfo.getMaterialName());
                        woInfo.setMaterialDes(objEMaterialInfo.getMaterialDes());
                    }
                }
                woInfo.setNum(model.getNum());
                if (model.getUnitGd() != null) {
                    //单位信息
                    UnitInfo unitInfo = unitDAO.SelectByGuid(model.getUnitGd());
                    woInfo.setUnitName((unitInfo != null) ? unitInfo.getUnitName() : "");
                }
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                woInfo.setJFDate((model.getjFinishDate() != null) ? format.format(model.getjFinishDate()) : "");
                woInfo.setSFDate((model.getsFinishDate() != null) ? format.format(model.getsFinishDate()) : "");
                String str = "";
                if ("00".equals(model.getStatus())) {
                    str = "待处理";
                } else if ("01".equals(model.getStatus())) {
                    str = "下达";
                } else if ("02".equals(model.getStatus())) {
                    str = "取消";
                } else if ("03".equals(model.getStatus())) {
                    str = "处理中";
                } else if ("04".equals(model.getStatus())) {
                    str = "冻结";
                } else if ("05".equals(model.getStatus())) {
                    str = "终止";
                } else if ("06".equals(model.getStatus())) {
                    str = "完成";
                } else if ("07".equals(model.getStatus())) {
                    str = "关闭";
                }
                woInfo.setStatus(str);
                woInfos.add(woInfo);
            }
        }
        getAllCuOrderInfoResB.setData(woInfos);
        getAllCuOrderInfoRes.setBody(getAllCuOrderInfoResB);
        return getAllCuOrderInfoRes;
    }

    //获取订单详情
    @Override
    public GetAllCuOrderInfoRes GetCOInfo(GetCuOrderReqBD00 getCuOrderReqBD00) {
        GetAllCuOrderInfoRes getAllCuOrderInfoRes = new GetAllCuOrderInfoRes();
        GetAllCuOrderInfoResB getAllCuOrderInfoResB = new GetAllCuOrderInfoResB();
        GetAllCuOrderInfoResD getAllCuOrderInfoResD = new GetAllCuOrderInfoResD();
        CuOrderInfo cuOrderInfo = cuOrderDao.SelectByRuId(getCuOrderReqBD00.getCoRd());

        if (cuOrderInfo != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            getAllCuOrderInfoResD.setCoRd(cuOrderInfo.getRuid());
            getAllCuOrderInfoResD.setOrderCode(cuOrderInfo.getOrderCode());
            getAllCuOrderInfoResD.setNum(cuOrderInfo.getNum());
            getAllCuOrderInfoResD.setDSource(cuOrderInfo.getDSource());
            getAllCuOrderInfoResD.setCreator(cuOrderInfo.getCreator());
            getAllCuOrderInfoResD.setCreateTime(format.format(cuOrderInfo.getCreateTime()));
            getAllCuOrderInfoResD.setLastModifyMan(cuOrderInfo.getLastModifyMan());
            getAllCuOrderInfoResD.setLastModifyTime(format.format(cuOrderInfo.getLastModifyTime()));
            getAllCuOrderInfoResD.setRemark(cuOrderInfo.getRemark());

            //订单类型
            if (cuOrderInfo.getCoTGd() != null) {
                CoTypeInfo coTypeInfo = coTypeDAO.SelectCoTypeInfoByGuid(cuOrderInfo.getCoTGd());
                if (coTypeInfo != null) {
                    GetAllCuOrderInfoResD.COTInfo cotInfo = new GetAllCuOrderInfoResD.COTInfo();
                    cotInfo.setCTRd(coTypeInfo.getRuid());
                    cotInfo.setCTName(coTypeInfo.getCoTName());
                    getAllCuOrderInfoResD.setCOTInfo(cotInfo);
                }
            }
            //客户信息
            if (cuOrderInfo.getCustomerGd() != null) {
                CustomerInfo customerInfo = customerDAO.SelectByGuid(cuOrderInfo.getCustomerGd());
                if (customerInfo != null) {
                    GetAllCuOrderInfoResD.CustomerInfo customerInfoResponse = new GetAllCuOrderInfoResD.CustomerInfo();
                    customerInfoResponse.setCustomerRd(customerInfo.getRuid());
                    customerInfoResponse.setCustomerName(customerInfo.getCustomerName());
                    getAllCuOrderInfoResD.setCustomerInfo(customerInfoResponse);
                }
            }

            //物料版本
            if (cuOrderInfo.getMaVerGd() != null) {
                GetAllCuOrderInfoResD.MaInfo maInfo = new GetAllCuOrderInfoResD.MaInfo();
                MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(cuOrderInfo.getMaVerGd());
                if (objEMaVerInfo != null) {
                    MaterialInfo objEMaterialInfo = materialDAO.SelectByGuid(objEMaVerInfo.getMaGd());
                    if (objEMaterialInfo != null) {
                        maInfo.setMaVerRd(objEMaVerInfo.getRuid());
                        maInfo.setMaCode(objEMaterialInfo.getMaterialCode());
                        maInfo.setMaName(objEMaterialInfo.getMaterialName());
                        maInfo.setMaDes(objEMaterialInfo.getMaterialDes());
                    }
                }
                getAllCuOrderInfoResD.setMaInfo(maInfo);
            }
        }
        getAllCuOrderInfoResB.setData(getAllCuOrderInfoResD);
        getAllCuOrderInfoRes.setBody(getAllCuOrderInfoResB);
        return getAllCuOrderInfoRes;
    }

    @Override
    public GetAllCuOrderInfoRes AddSaveCOInfo(SaveCuOrderReqBD00 reqBD00) {
        GetAllCuOrderInfoRes getAllCuOrderInfoRes = new GetAllCuOrderInfoRes();
        CuOrderInfo cuOrderInfo = new CuOrderInfo();
        if (reqBD00.getCoRd() == null) {
            throw new SystemException("EEEE", "保存失败，参数不能为空");
        }
        cuOrderInfo.setRuid(reqBD00.getCoRd());
        if (reqBD00.getCTRd() != null) {
            //订单类型
            CoTypeInfo coTypeInfo = coTypeDAO.SelectCoTypeInfoByRuid(reqBD00.getCTRd());
            if (coTypeInfo != null) {
                cuOrderInfo.setCoTGd(coTypeInfo.getGuid());
            }
        }

        if (reqBD00.getCustomerRd() != null) {
            //客户信息
            CustomerInfo customerInfo = customerDAO.SelectByRuid(reqBD00.getCustomerRd());
            if (customerInfo != null) {
                cuOrderInfo.setCustomerGd(customerInfo.getGuid());
            }
        }

        if (reqBD00.getMaVerRd() != null) {
            //物料版本
            MaVerInfo objEMaVerInfo = maVerDAO.SelectByRuid(reqBD00.getMaVerRd());
            if (objEMaVerInfo != null) {
                cuOrderInfo.setMaVerGd(objEMaVerInfo.getGuid());
            }
        }

        cuOrderInfo.setLastModifyTime(new Date());
        cuOrderInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        cuOrderInfo.setNum(reqBD00.getNum());
        cuOrderInfo.setRemark(reqBD00.getRemark());
        cuOrderDao.SaveOrderInfo(cuOrderInfo);
        return getAllCuOrderInfoRes;
    }

    @Override
    public GetAllCuOrderInfoRes AddCOInfo(SaveCuOrderReqBD00 reqBD00) {
        GetAllCuOrderInfoRes getAllCuOrderInfoRes = new GetAllCuOrderInfoRes();
        GetAllCuOrderInfoResB getAllCuOrderInfoResB = new GetAllCuOrderInfoResB();
        CuOrderInfo cuOrderInfo = new CuOrderInfo();
        if (reqBD00.getOrderCode() != null && reqBD00.getOrderCode() != "") {
            cuOrderInfo.setOrderCode(reqBD00.getOrderCode());
        } else {
            CodeRuleInfo codeRuleInfo = codeRuleDAO.SelectCodeRuleInfocodeType("14");
            if (codeRuleInfo != null && "00".equals(codeRuleInfo.getStatus())) {
                cuOrderInfo.setOrderCode(gConfigIService.GetCreateSC(codeRuleInfo));
            }
        }
        cuOrderInfo.setGuid(CommonUtils.getRandomNumber());
        //订单类型
        CoTypeInfo coTypeInfo = coTypeDAO.SelectCoTypeInfoByRuid(reqBD00.getCTRd());
        if (coTypeInfo != null) {
            cuOrderInfo.setCoTGd(coTypeInfo.getGuid());
        }
        //客户信息
        CustomerInfo customerInfo = customerDAO.SelectByRuid(reqBD00.getCustomerRd());
        if (customerInfo != null) {
            cuOrderInfo.setCustomerGd(customerInfo.getGuid());
        }

        //物料版本
        MaVerInfo objEMaVerInfo = maVerDAO.SelectByRuid(reqBD00.getMaVerRd());
        if (objEMaVerInfo != null) {
            cuOrderInfo.setMaVerGd(objEMaVerInfo.getGuid());
        }
        cuOrderInfo.setLastModifyTime(new Date());
        cuOrderInfo.setNum(reqBD00.getNum());
        cuOrderInfo.setRemark(reqBD00.getRemark());
        cuOrderInfo.setCreator(CommonUtils.readUser().getRealName());
        cuOrderInfo.setCreateTime(new Date());
        cuOrderInfo.setLastModifyTime(new Date());
        cuOrderInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        List<CuOrderInfo> list=cuOrderDao.SelectAllByOrderCode(cuOrderInfo.getOrderCode());
        if (list.size()>0){
            throw new SystemException("EEEE", "新增失败，订单号出现重复！");
        }
        cuOrderDao.AddOrderInfo(cuOrderInfo);
        cuOrderInfo = cuOrderDao.SelectByGuId(cuOrderInfo.getGuid());
        getAllCuOrderInfoResB.setData(cuOrderInfo);
        getAllCuOrderInfoResB.setMsgCode("0x00000");
        getAllCuOrderInfoResB.setMsgDes("新增成功");
        getAllCuOrderInfoRes.setBody(getAllCuOrderInfoResB);
        return getAllCuOrderInfoRes;
    }

    @Override
    public GetAllCuOrderInfoRes RmDelCOInfo(SaveCuOrderReqBD00 reqBD00) {
        GetAllCuOrderInfoRes getAllCuOrderInfoRes = new GetAllCuOrderInfoRes();
        cuOrderDao.delOrderInfo(reqBD00.getCoRd());
        return getAllCuOrderInfoRes;
    }
}
