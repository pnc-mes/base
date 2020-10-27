package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllDcInfo.GetAllDcInfoRes;
import pnc.mesadmin.dto.GetAllDcInfo.GetAllDcInfoResB;
import pnc.mesadmin.dto.GetAllDcInfo.GetAllDcInfoResD;
import pnc.mesadmin.dto.GetAllOpertInfo.GetAllOpertInfoResD;
import pnc.mesadmin.dto.GetDVInfo.*;
import pnc.mesadmin.dto.GetDVTreeInfo.GetDVTreeInfoRes;
import pnc.mesadmin.dto.GetDVTreeInfo.GetDVTreeInfoResB;
import pnc.mesadmin.dto.GetDVTreeInfo.GetDVTreeInfoResD;
import pnc.mesadmin.dto.GetDcInfo.*;
import pnc.mesadmin.dto.SaveDcInfo.*;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.DCIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;
import pnc.mesadmin.utils.MyPage;

import javax.annotation.Resource;
import java.util.*;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：数据采集信息Service实现类
 * 创建人：赵超
 * 创建时间：2017-5-24
 * 修改人：
 * 修改时间：
 */
@Transactional
@Service
public class DCService implements DCIService {

    @Resource
    private DCDAO dcDAO;

    @Resource
    private DCVerDAO dcVerDAO;

    @Resource
    private DCItemDAO dcItemDAO;

    @Resource
    private BaseIService baseIService;

    @Resource
    private SpecVerDAO specVerDAO;

    @Resource
    private EmailDistributionDAO emailDistributionDAO;

    @Resource
    private EmailMessageDAO emailMessageDAO;

    @Override
    public PageResult<GetDcInfoRes> QALLGetAllDcInfoRes(BaseRequest req) {
        GetAllDcInfoRes objEGetAllDcInfoRes  = new GetAllDcInfoRes();

        GetAllDcInfoResB objEGetAllDcInfoResB = new GetAllDcInfoResB();

        List<GetAllDcInfoResD> getAllDcInfoResDs = new ArrayList<GetAllDcInfoResD>();

        GetAllDcInfoResD objEGetAllDcInfoResD = null;

        Page<DCInfo> page = new Page<>(req.getCurrent(), req.getSize());

        IPage<DCInfo> woInfoIPage = dcDAO.selectPage(new MyPage(req), CommonUtils.getQueryWrapper(req.getFields()));

        // 调用DAO查询数据采集列表
        List<DCInfo> dcInfos = woInfoIPage.getRecords();

        // 判断返回数据采集信息列表是否为空
        if (dcInfos != null&&dcInfos.size()>=1 ) {
            for (int i = 0 ; i < dcInfos.size() ; i ++){
                objEGetAllDcInfoResD = new GetAllDcInfoResD();
                objEGetAllDcInfoResD.setDcRd(dcInfos.get(i).getRuid());
                objEGetAllDcInfoResD.setDcName(dcInfos.get(i).getDcName());
                DCVerInfo dcVerInfo = dcVerDAO.selectDCVerInfoByGuid(dcInfos.get(i).getVerGd());
                objEGetAllDcInfoResD.setDCVerRd(dcVerInfo.getRuid());
                objEGetAllDcInfoResD.setVersion(dcVerInfo.getVersion());
                getAllDcInfoResDs.add(objEGetAllDcInfoResD);
            }
        }
        objEGetAllDcInfoResB.setData(getAllDcInfoResDs);

        objEGetAllDcInfoRes.setBody(objEGetAllDcInfoResB);

    return    new PageResult(woInfoIPage.getTotal(), woInfoIPage.getPages(), woInfoIPage.getCurrent(), woInfoIPage.getSize(), getAllDcInfoResDs);
    }

    /**
     * 查询数据采集列表
     */
    public GetAllDcInfoRes QALLselectDCInfos(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException {

        GetAllDcInfoRes objEGetAllDcInfoRes  = new GetAllDcInfoRes();

        GetAllDcInfoResB objEGetAllDcInfoResB = new GetAllDcInfoResB();

        List<GetAllDcInfoResD> getAllDcInfoResDs = new ArrayList<GetAllDcInfoResD>();

        GetAllDcInfoResD objEGetAllDcInfoResD = null;

        // 调用DAO查询数据采集列表
        List<DCInfo> dcInfos = baseIService.QALLBaseInfo(DCDAO.class, "selectDCInfos",
                argInitDataFields, argPageInfo);

        // 判断返回数据采集信息列表是否为空
        if (dcInfos != null&&dcInfos.size()>=1 ) {
            for (int i = 0 ; i < dcInfos.size() ; i ++){
                objEGetAllDcInfoResD = new GetAllDcInfoResD();
                objEGetAllDcInfoResD.setDcRd(dcInfos.get(i).getRuid());
                objEGetAllDcInfoResD.setDcName(dcInfos.get(i).getDcName());
                DCVerInfo dcVerInfo = dcVerDAO.selectDCVerInfoByGuid(dcInfos.get(i).getVerGd());
                objEGetAllDcInfoResD.setDCVerRd(dcVerInfo.getRuid());
                objEGetAllDcInfoResD.setVersion(dcVerInfo.getVersion());
                getAllDcInfoResDs.add(objEGetAllDcInfoResD);
            }
        }
        objEGetAllDcInfoResB.setData(getAllDcInfoResDs);

        objEGetAllDcInfoRes.setBody(objEGetAllDcInfoResB);

        return objEGetAllDcInfoRes;
    }
    /**
     * 查询数据采集信息，二级树形结构
     */
    public GetDcInfoRes GetselectDCInfoByDcRd(int dcRd) throws SystemException{

        GetDcInfoRes objEGetDcInfoRes = new GetDcInfoRes();

        GetDcInfoResB objEGetDcInfoResB = new GetDcInfoResB();

        GetDcInfoResD objEGetDcInfoResD = new GetDcInfoResD();

        // 查询默认版本信息
        DCInfo objDcInfo = dcDAO.selectDCInfoByDcRd(dcRd);
        DCVerInfo objDCVerInfo = dcVerDAO.selectDCVerInfoByGuid(objDcInfo.getVerGd());
        //
        List<GetDcInfoResDDCItems> objEGetDcInfoResDDCItemss = new ArrayList<GetDcInfoResDDCItems>();
        GetDcInfoResDDCItems objEGetDcInfoResDDCItems = null;
        // 调用DAO，查询数据采集信息默认版本的信息对应的列表
        List<DCItemInfo> dcItemInfos = dcItemDAO.selectDCItemInfosByDCVerGd(objDCVerInfo.getGuid());
        for (int i = 0; i < dcItemInfos.size(); i++) {
            objEGetDcInfoResDDCItems = new GetDcInfoResDDCItems();
            objEGetDcInfoResDDCItems.setItemRd(dcItemInfos.get(i).getRuid());
            objEGetDcInfoResDDCItems.setItemName(dcItemInfos.get(i).getItemName());
            objEGetDcInfoResDDCItems.setDataAlias(dcItemInfos.get(i).getDataAlias());
            objEGetDcInfoResDDCItems.setSValue(dcItemInfos.get(i).getsValue());
            objEGetDcInfoResDDCItems.setUpLimit(dcItemInfos.get(i).getUpLimit());
            objEGetDcInfoResDDCItems.setAction(dcItemInfos.get(i).getAction());
            objEGetDcInfoResDDCItems.setDwLimit(dcItemInfos.get(i).getDwLimit());
            objEGetDcInfoResDDCItems.setDefValue(dcItemInfos.get(i).getDefValue());
            objEGetDcInfoResDDCItems.setChecked(dcItemInfos.get(i).getChecked());
            // 00#数字类型 01#浮点 02#字符串
            GetDcInfoResDDItemType objEGetDcInfoResDDItemType = new GetDcInfoResDDItemType();
            if("00".equals(dcItemInfos.get(i).getItemType())) {
                objEGetDcInfoResDDItemType.setTypeText("数字类型");
                objEGetDcInfoResDDItemType.setTypeVal("00");
            }else if("01".equals(dcItemInfos.get(i).getItemType())) {
                objEGetDcInfoResDDItemType.setTypeText("浮点类型");
                objEGetDcInfoResDDItemType.setTypeVal("01");
            }else if("02".equals(dcItemInfos.get(i).getItemType())) {
                objEGetDcInfoResDDItemType.setTypeText("字符串类型");
                objEGetDcInfoResDDItemType.setTypeVal("02");
            }
            objEGetDcInfoResDDCItems.setItemType(objEGetDcInfoResDDItemType);
            objEGetDcInfoResDDCItemss.add(objEGetDcInfoResDDCItems);
        }

        //两个邮件
        GetDcInfoResDEmailDistribution getDcInfoResDEmailDistribution=new   GetDcInfoResDEmailDistribution();
        EmailDistributionInfo emailDistributionInfo1=emailDistributionDAO.SelectEmailDistributionInfoByGuid(objDCVerInfo.getPresetEmailDistributionGd());
        if(emailDistributionInfo1!=null){
            getDcInfoResDEmailDistribution.setEmailDistributionRd(emailDistributionInfo1.getRuid());
            getDcInfoResDEmailDistribution.setEmailDistributionName(emailDistributionInfo1.getDistributionName());
            objEGetDcInfoResD.setEmailDistributionInfo(getDcInfoResDEmailDistribution);
        }else {
            objEGetDcInfoResD.setEmailDistributionInfo(null);
        }

        GetDcInfoResDEmailMessage getDcInfoResDEmailMessage=new GetDcInfoResDEmailMessage();
        EmailMessageInfo emailMessageInfo1=emailMessageDAO.SelectEmailMessageInfoByGuid(objDCVerInfo.getPresetEmailMessageGd());
        if(emailMessageInfo1!=null){
            getDcInfoResDEmailMessage.setEmailMessageRd(emailMessageInfo1.getRuid());
            getDcInfoResDEmailMessage.setEmailMessageName(emailMessageInfo1.getEmailName());
            objEGetDcInfoResD.setEmailMessageInfo(getDcInfoResDEmailMessage);
        }else {
            objEGetDcInfoResD.setEmailMessageInfo(null);
        }


        // 把数据封装到DTO里面
        objEGetDcInfoResD.setDcRd(objDcInfo.getRuid());
        objEGetDcInfoResD.setVersion(objDCVerInfo.getVersion());
        objEGetDcInfoResD.setStatus(objDCVerInfo.getStatus());
        objEGetDcInfoResD.setDCName(objDCVerInfo.getDcName());
        objEGetDcInfoResD.setIsDef(objDCVerInfo.getIsDef());
        objEGetDcInfoResD.setDCVerRd(objDCVerInfo.getRuid());
        objEGetDcInfoResD.setCreator(objDCVerInfo.getCreator());
        objEGetDcInfoResD.setCreateTime(DateUtil.format(objDCVerInfo.getCreateTime()));
        objEGetDcInfoResD.setLastModifyMan(objDCVerInfo.getLastModifyMan());
        objEGetDcInfoResD.setLastModifyTime(DateUtil.format(objDCVerInfo.getLastModifyTime()));
        objEGetDcInfoResD.setRemark(objDCVerInfo.getRemark());

//        objEGetDcInfoResD.setADcRd(objDCVerInfo.getRuid());
//        objEGetDcInfoResD.setAfterDcName(objDCVerInfo.get(i).getAfterDcName());

        objEGetDcInfoResD.setDCItemsInfo(objEGetDcInfoResDDCItemss);

        objEGetDcInfoResB.setData(objEGetDcInfoResD);

        objEGetDcInfoRes.setBody(objEGetDcInfoResB);

        return objEGetDcInfoRes;
    }

   //dto新增
    public SaveDcInfoRes AddsaveDCInfo(SaveDcInfoReqBD00 argSaveDcInfoReqBD00) throws SystemException{
        // 创建返回的DTO对象
        SaveDcInfoRes objESaveDcInfoRes = new SaveDcInfoRes();
        SaveDcInfoResB objESaveDcInfoResB = new SaveDcInfoResB();

       //先新增自己信息
        DCInfo objDCInfo = new DCInfo();

        //查询数据采集信息
        List<DCInfo> objEDCInfo=dcDAO.selectDCInfos();

        //逻辑校验保存的数据采集名称不能相同
        for (DCInfo obj:objEDCInfo){
            if (obj.getDcName().equals(argSaveDcInfoReqBD00.getDCName())){
                throw new SystemException("MG0006F","数据采集名称已存在");
            }
        }
        if ((argSaveDcInfoReqBD00.getDCName()).equals("")){
            throw new SystemException("MG0003F","数据采集名称不能为空");
        }

        objDCInfo.setGuid(CommonUtils.getRandomNumber());
        //新增版本信息
        DCVerInfo objEDCVerInfo=new DCVerInfo();

        objEDCVerInfo.setGuid(CommonUtils.getRandomNumber());
        objDCInfo.setDcName(argSaveDcInfoReqBD00.getDCName());
        //设置默认版本标识
        objDCInfo.setVerGd(objEDCVerInfo.getGuid());
        objDCInfo.setCreateTime(new Date());
        objDCInfo.setCreator(CommonUtils.readUser().getRealName());
        objDCInfo.setRemark(argSaveDcInfoReqBD00.getRemark());


        dcDAO.insertDCInfo(objDCInfo);

        EmailDistributionInfo emailDistributionInfo1=emailDistributionDAO.SelectEmailDistributionInfoByRuid(argSaveDcInfoReqBD00.getEmailDistributionRd());
        if(emailDistributionInfo1!=null){
            objEDCVerInfo.setPresetEmailDistributionGd(emailDistributionInfo1.getGuid());
        }

        EmailMessageInfo emailMessageInfo1=emailMessageDAO.SelectEmailMessageByID(argSaveDcInfoReqBD00.getEmailMessageRd());
        if(emailMessageInfo1!=null){
            objEDCVerInfo.setPresetEmailMessageGd(emailMessageInfo1.getGuid());
        }


        objEDCVerInfo.setDcGd(objDCInfo.getGuid());
        objEDCVerInfo.setDcName(argSaveDcInfoReqBD00.getDCName());
        objEDCVerInfo.setVersion(argSaveDcInfoReqBD00.getVersion());
        objEDCVerInfo.setRemark(argSaveDcInfoReqBD00.getRemark());
        //状态 默认00
        if("00".equals(argSaveDcInfoReqBD00.getStatus())){
            objEDCVerInfo.setStatus("00");
        }else if("01".equals(argSaveDcInfoReqBD00.getStatus())){
            objEDCVerInfo.setStatus("01");
        }else {
            objEDCVerInfo.setStatus("00");
        }
        //默认版本 00
        objEDCVerInfo.setIsDef("00");
        objEDCVerInfo.setCreateTime(new Date());
        objEDCVerInfo.setCreator(CommonUtils.readUser().getRealName());
        dcVerDAO.insertDCVerInfo(objEDCVerInfo);

        Set<String> set =new HashSet<String>();

        for(SaveDcInfoReqDCItems objESaveDcInfoReqDCItems:argSaveDcInfoReqBD00.getDCItemsInfo()){
            set.add(objESaveDcInfoReqDCItems.getItemName());
        }
        if(set.size()<argSaveDcInfoReqBD00.getDCItemsInfo().size()){
            throw new SystemException("xxx","新增失败，采集内容已重复");
        }



        // 保存版本下面的items
        List<DCItemInfo> dcItemInfos = new ArrayList<DCItemInfo>();
        DCItemInfo objDCItemInfo = null;
        // 把业务数据遍历存入items中
        for (SaveDcInfoReqDCItems objESaveDcInfoReqDCItems : argSaveDcInfoReqBD00.getDCItemsInfo()){


                /*if("00".equals(objESaveDcInfoReqDCItems.getChecked())&&("00".equals(objESaveDcInfoReqDCItems.getItemType())||"01".equals(objESaveDcInfoReqDCItems.getItemType()))){

                    if("".equals(objESaveDcInfoReqDCItems.getItemName())||objESaveDcInfoReqDCItems.getItemName()==null){
                        throw new SystemException("xxx","采集内容: "+objESaveDcInfoReqDCItems.getItemName()+"采集内容名称不能为空");
                    }
                    if("".equals(objESaveDcInfoReqDCItems.getSValue())||objESaveDcInfoReqDCItems.getSValue()==null){
                            throw new SystemException("xxx","采集内容: "+objESaveDcInfoReqDCItems.getItemName()+"标准值不能为空");
                    }
                    if("".equals(objESaveDcInfoReqDCItems.getDataAlias())||objESaveDcInfoReqDCItems.getDataAlias()==null){
                        throw new SystemException("xxx","采集内容: "+objESaveDcInfoReqDCItems.getItemName()+"数据别名不能为空");
                    }
                    if("".equals(objESaveDcInfoReqDCItems.getUpLimit())||objESaveDcInfoReqDCItems.getUpLimit()==null){
                        throw new SystemException("xxx","采集内容: "+objESaveDcInfoReqDCItems.getItemName()+"上限浮动值不能为空");
                    }
                    if("".equals(objESaveDcInfoReqDCItems.getDwLimit())||objESaveDcInfoReqDCItems.getDwLimit()==null){
                        throw new SystemException("xxx","采集内容: "+objESaveDcInfoReqDCItems.getItemName()+"下限浮动值不能为空");
                    }
                    if("".equals(objESaveDcInfoReqDCItems.getSValue())||objESaveDcInfoReqDCItems.getSValue()==null&&"".equals(objESaveDcInfoReqDCItems.getUpLimit())||objESaveDcInfoReqDCItems.getUpLimit()==null){
                        throw new SystemException("xxx","采集内容: "+objESaveDcInfoReqDCItems.getItemName()+"标准值和上限浮动值不能为空");
                    }
                    if("".equals(objESaveDcInfoReqDCItems.getSValue())||objESaveDcInfoReqDCItems.getSValue()==null&&"".equals(objESaveDcInfoReqDCItems.getDwLimit())||objESaveDcInfoReqDCItems.getDwLimit()==null){
                        throw new SystemException("xxx","采集内容: "+objESaveDcInfoReqDCItems.getItemName()+"标准值和下限浮动值不能为空");
                    }
                    if("".equals(objESaveDcInfoReqDCItems.getUpLimit())||objESaveDcInfoReqDCItems.getUpLimit()==null&&"".equals(objESaveDcInfoReqDCItems.getDwLimit())||objESaveDcInfoReqDCItems.getDwLimit()==null){
                        throw new SystemException("xxx","采集内容: "+objESaveDcInfoReqDCItems.getItemName()+"上限浮动值和下限浮动值不能为空");
                    }

                    try{
                        Float.valueOf(objESaveDcInfoReqDCItems.getSValue());
                    }catch (Exception e){
                        throw new SystemException("x1", "标准值填写不正确");
                    }
                    try{
                        Float.valueOf(objESaveDcInfoReqDCItems.getUpLimit());
                    }catch (Exception e){
                        throw new SystemException("x2", "上限浮动值填写不正确");
                    }
                    try{
                        Float.valueOf(objESaveDcInfoReqDCItems.getDwLimit());
                    }catch (Exception e){
                        throw new SystemException("x3", "下限浮动值填写不正确");
                    }

                }

            if("01".equals(objESaveDcInfoReqDCItems.getChecked())||"00".equals(objESaveDcInfoReqDCItems.getChecked())&&"02".equals(objESaveDcInfoReqDCItems.getItemType())){
               *//* if("".equals(objESaveDcInfoReqDCItems.getSValue())||objESaveDcInfoReqDCItems.getSValue()==null){
                    throw new SystemException("xxx","采集内容: "+objESaveDcInfoReqDCItems.getItemName()+"标准值不能为空");
                }*//*

                if("".equals(objESaveDcInfoReqDCItems.getItemName())||objESaveDcInfoReqDCItems.getItemName()==null){
                    throw new SystemException("xxx","采集内容: "+objESaveDcInfoReqDCItems.getItemName()+"采集内容名称不能为空");
                }
                if("".equals(objESaveDcInfoReqDCItems.getDataAlias())||objESaveDcInfoReqDCItems.getDataAlias()==null){
                    throw new SystemException("xxx","采集内容: "+objESaveDcInfoReqDCItems.getItemName()+"数据别名不能为空");
                }
                if("".equals(objESaveDcInfoReqDCItems.getSValue())||objESaveDcInfoReqDCItems.getSValue()==null){
                    throw new SystemException("xxx","采集内容: "+objESaveDcInfoReqDCItems.getItemName()+"标准值不能为空");
                }
                if("".equals(objESaveDcInfoReqDCItems.getUpLimit())||objESaveDcInfoReqDCItems.getUpLimit()==null){
                    throw new SystemException("xxx","采集内容: "+objESaveDcInfoReqDCItems.getItemName()+"上限浮动值不能为空");
                }
                if("".equals(objESaveDcInfoReqDCItems.getDwLimit())||objESaveDcInfoReqDCItems.getDwLimit()==null){
                    throw new SystemException("xxx","采集内容: "+objESaveDcInfoReqDCItems.getItemName()+"下限浮动值不能为空");
                }
                if("".equals(objESaveDcInfoReqDCItems.getSValue())||objESaveDcInfoReqDCItems.getSValue()==null&&"".equals(objESaveDcInfoReqDCItems.getUpLimit())||objESaveDcInfoReqDCItems.getUpLimit()==null){
                    throw new SystemException("xxx","采集内容: "+objESaveDcInfoReqDCItems.getItemName()+"标准值和上限浮动值不能为空");
                }
                if("".equals(objESaveDcInfoReqDCItems.getSValue())||objESaveDcInfoReqDCItems.getSValue()==null&&"".equals(objESaveDcInfoReqDCItems.getDwLimit())||objESaveDcInfoReqDCItems.getDwLimit()==null){
                    throw new SystemException("xxx","采集内容: "+objESaveDcInfoReqDCItems.getItemName()+"标准值和下限浮动值不能为空");
                }
                if("".equals(objESaveDcInfoReqDCItems.getUpLimit())||objESaveDcInfoReqDCItems.getUpLimit()==null&&"".equals(objESaveDcInfoReqDCItems.getDwLimit())||objESaveDcInfoReqDCItems.getDwLimit()==null){
                    throw new SystemException("xxx","采集内容: "+objESaveDcInfoReqDCItems.getItemName()+"上限浮动值和下限浮动值不能为空");
                }


            }*/







            objDCItemInfo = new DCItemInfo();
            if("".equals(objESaveDcInfoReqDCItems.getItemName())||objESaveDcInfoReqDCItems.getItemName()==null){
                throw new SystemException("xxx","采集内容: "+objESaveDcInfoReqDCItems.getItemName()+"采集内容名称不能为空");
            }
            if("".equals(objESaveDcInfoReqDCItems.getDataAlias())||objESaveDcInfoReqDCItems.getDataAlias()==null){
                throw new SystemException("xxx","采集内容: "+objESaveDcInfoReqDCItems.getItemName()+"数据别名不能为空");
            }
            if("02".equals(objESaveDcInfoReqDCItems.getItemType())){
                objDCItemInfo.setDefValue(objESaveDcInfoReqDCItems.getDefValue());
                objDCItemInfo.setsValue(objESaveDcInfoReqDCItems.getSValue());
                objDCItemInfo.setUpLimit(objESaveDcInfoReqDCItems.getUpLimit());
                objDCItemInfo.setDwLimit(objESaveDcInfoReqDCItems.getDwLimit());
            }else {
                if("".equals(objESaveDcInfoReqDCItems.getSValue())||objESaveDcInfoReqDCItems.getSValue()==null){
                    throw new SystemException("xxx","采集内容: "+objESaveDcInfoReqDCItems.getItemName()+"标准值不能为空");
                }

                if("".equals(objESaveDcInfoReqDCItems.getUpLimit())||objESaveDcInfoReqDCItems.getUpLimit()==null){
                    throw new SystemException("xxx","采集内容: "+objESaveDcInfoReqDCItems.getItemName()+"上限浮动值不能为空");
                }

                if("".equals(objESaveDcInfoReqDCItems.getDwLimit())||objESaveDcInfoReqDCItems.getDwLimit()==null){
                    throw new SystemException("xxx","采集内容: "+objESaveDcInfoReqDCItems.getItemName()+"下限浮动值不能为空");
                }

               /* if("".equals(objESaveDcInfoReqDCItems.getDefValue())||objESaveDcInfoReqDCItems.getDefValue()==null){
                    throw new SystemException("xxx","采集内容: "+objESaveDcInfoReqDCItems.getItemName()+"默认值不能为空");
                }*/

                try{
                    Float.valueOf(objESaveDcInfoReqDCItems.getSValue());
                }catch (Exception e){
                    throw new SystemException("x1", "标准值填写不正确");
                }
                try{
                    Float.valueOf(objESaveDcInfoReqDCItems.getUpLimit());
                }catch (Exception e){
                    throw new SystemException("x2", "上限浮动值填写不正确");
                }
                try{
                    Float.valueOf(objESaveDcInfoReqDCItems.getDwLimit());
                }catch (Exception e){
                    throw new SystemException("x3", "下限浮动值填写不正确");
                }

                objDCItemInfo.setDefValue(objESaveDcInfoReqDCItems.getDefValue());
                objDCItemInfo.setsValue(objESaveDcInfoReqDCItems.getSValue());
                objDCItemInfo.setUpLimit(objESaveDcInfoReqDCItems.getUpLimit());
                objDCItemInfo.setDwLimit(objESaveDcInfoReqDCItems.getDwLimit());

            }



            // 设置业务参数数据
            objDCItemInfo.setGuid(CommonUtils.getRandomNumber());
            objDCItemInfo.setCreateTime(new Date());
            objDCItemInfo.setCreator(CommonUtils.readUser().getRealName());
            objDCItemInfo.setCreator(CommonUtils.readUser().getUserName());

            if("00".equals(objESaveDcInfoReqDCItems.getChecked())){
                objDCItemInfo.setChecked("00");
            }
            else {
                objDCItemInfo.setChecked("01");
            }

            objDCItemInfo.setDefValue(objESaveDcInfoReqDCItems.getDefValue());
            objDCItemInfo.setAction(objESaveDcInfoReqDCItems.getAction());
            objDCItemInfo.setsValue(objESaveDcInfoReqDCItems.getSValue());
            objDCItemInfo.setUpLimit(objESaveDcInfoReqDCItems.getUpLimit());
            objDCItemInfo.setDwLimit(objESaveDcInfoReqDCItems.getDwLimit());
            objDCItemInfo.setDataAlias(objESaveDcInfoReqDCItems.getDataAlias());


            objDCItemInfo.setItemName(objESaveDcInfoReqDCItems.getItemName());
            objDCItemInfo.setItemType(objESaveDcInfoReqDCItems.getItemType());

            objDCItemInfo.setRemark(argSaveDcInfoReqBD00.getRemark());
            objDCItemInfo.setDcVerGd(objEDCVerInfo.getGuid());
            dcItemInfos.add(objDCItemInfo);
        }
        dcItemDAO.insertDCItemInfos(dcItemInfos);

        // 返回DTO信息
        objESaveDcInfoRes.setBody(objESaveDcInfoResB);
        return objESaveDcInfoRes;
    }

   //dto删除
    public SaveDcInfoRes RmdeleteDcInfoByRuid(int dcRd) throws SystemException {
        // 创建返回的DTO对象
        SaveDcInfoRes objESaveDcInfoRes = new SaveDcInfoRes();
        SaveDcInfoResB objESaveDcInfoResB = new SaveDcInfoResB();
        SaveDcInfoResD objESaveDcInfoResD = new SaveDcInfoResD();



        int count = 0;

        DCInfo objDCInfo = dcDAO.selectDCInfoByDcRd(dcRd);
        if(objDCInfo==null){
            throw new SystemException("xxx", "查询数据采集信息失败");
        }

        List<DCVerInfo> dcVerInfos = dcVerDAO.selectDCVerInfosByDcGd(objDCInfo.getGuid());
        if(dcVerInfos==null||dcVerInfos.size()<=0){
            throw new SystemException("xxx", "查询数据采集版本信息失败");
        }

        for (DCVerInfo dcVerInfo: dcVerInfos) {

            if(dcItemDAO.deleteDCItemInfoByDCVerGd(dcVerInfo.getGuid())<=0){
                throw new SystemException("MG_MES4001812015001F","删除详细信息失败");
            }

            List<SpecVerInfo> specVerInfos=specVerDAO.selectList(new QueryWrapper<SpecVerInfo>().eq("dCVerGd", dcVerInfo.getGuid()));
            if(specVerInfos!=null&&specVerInfos.size()>0){
                throw new SystemException("xxx", "删除失败,工序管理已使用该设备采集");
            }
            List<DCItemInfo> dcItemInfos=dcItemDAO.selectDCItemInfosByDCVerGd(dcVerInfo.getGuid());
            if(!CollectionUtils.isEmpty(dcItemInfos)){
                for(DCItemInfo dcItemInfo:dcItemInfos){
                     count=dcItemDAO.deleteById(dcItemInfo.getRuid());
                    if(count <= 0) throw new SystemException("MG000001", "删除数据采集项信息失败！");
                }
                }
            }

        // 4.删除下面所有的版本
        count = dcVerDAO.deleteDCVerInfoByDCGd(objDCInfo.getGuid());
        if(count <= 0) throw new SystemException("MG000001", "删除数据采集版本信息失败！");
        // 5.删除根节点
        count = dcDAO.deleteDCInfoByGuid(objDCInfo.getGuid());
        if(count <= 0) throw new SystemException("MG000001", "删除数据采集信息失败！");

        objESaveDcInfoResB.setData(objESaveDcInfoResD);
        objESaveDcInfoRes.setBody(objESaveDcInfoResB);
        return objESaveDcInfoRes;
    }

    //tdo更新
    public SaveDcInfoRes Addupdate(SaveDcInfoReqBD02 busData02, DCInfo argObjDCInfo) {
        // 创建返回的DTO对象
        SaveDcInfoRes objESaveDcInfoRes = new SaveDcInfoRes();
        SaveDcInfoResB objESaveDcInfoResB = new SaveDcInfoResB();
        SaveDcInfoResD objESaveDcInfoResD = new SaveDcInfoResD();

        //查询数据采集信息
        DCInfo objEDCInfo=dcDAO.selectDCInfoByDcRd(busData02.getDCRd());

        if(objEDCInfo==null){
            throw new SystemException("xxx", "查询数据采集信息失败");
        }

        if ((busData02.getDCName()).equals("")){
            throw new SystemException("MG0003F","数据采集名称不能为空");
        }
        //输入的名字到数据库先查询信息
        DCInfo objeDCInfo=dcDAO.SelectDcName(busData02.getDCName());
        //校验数据采集名称
        if(objeDCInfo!=null && !objeDCInfo.getDcName().equals(objEDCInfo.getDcName())){
            throw new SystemException("MG0006F","更新失败，数据采集名称已存在");
        }

        DCVerInfo dcVerInfo=dcVerDAO.selectDCVerInfoByDcGd(objEDCInfo.getGuid());
        if(dcVerInfo==null){
            throw new SystemException("xxx","查询数据采集版本信息失败");
        }
        //先删除再新增
        List<DCItemInfo> dcItemInfoss=dcItemDAO.selectDCItemInfosByDCVerGd(dcVerInfo.getGuid());
        if(dcItemInfoss!=null&&dcItemInfoss.size()>0){
            for(DCItemInfo obj:dcItemInfoss){
                dcItemDAO.deleteDCItemInfoByRuid(obj.getRuid());
            }
        }

        //dcVerDAO.deleteDCVerInfoByGuid(dcVerInfo.getGuid());


        DCVerInfo dcVerInfo1=new DCVerInfo();
        dcVerInfo1.setDcGd(objEDCInfo.getGuid());
        dcVerInfo1.setVersion(busData02.getVersion());
        dcVerInfo1.setStatus(busData02.getStatus());
        dcVerInfo1.setDcName(objEDCInfo.getDcName());
        EmailDistributionInfo emailDistributionInfo1=emailDistributionDAO.SelectEmailDistributionInfoByRuid(busData02.getEmailDistributionRd());
        if(emailDistributionInfo1!=null){
            dcVerInfo1.setPresetEmailDistributionGd(emailDistributionInfo1.getGuid());
        }else {
            dcVerInfo1.setPresetEmailDistributionGd("");
        }

        EmailMessageInfo emailMessageInfo1=emailMessageDAO.SelectEmailMessageByID(busData02.getEmailMessageRd());
        if(emailMessageInfo1!=null){
            dcVerInfo1.setPresetEmailMessageGd(emailMessageInfo1.getGuid());
        }else {
            dcVerInfo1.setPresetEmailMessageGd("");
        }
        dcVerInfo1.setGuid(dcVerInfo.getGuid());
        dcVerInfo1.setIsDef("00");
        dcVerInfo1.setDcName(busData02.getDCName());
        dcVerInfo1.setLastModifyMan(CommonUtils.readUser().getRealName());
        dcVerInfo1.setLastModifyTime(new Date());
        dcVerInfo1.setDcName(busData02.getDCName());
        dcVerInfo1.setRemark(busData02.getRemark());
        if(dcVerDAO.updateDCVerInfo(dcVerInfo1)<=0){
            throw new SystemException("xxx","更新数据采集版本信息失败");
        }

        objEDCInfo.setDcName(busData02.getDCName());
        objEDCInfo.setRemark(busData02.getRemark());
        objEDCInfo.setVerGd(dcVerInfo1.getGuid());
        if(dcDAO.updateDCInfo(objEDCInfo)<=0){
            throw new SystemException("xxx","更新数据采集信息失败");
        }
        Set<String> set =new HashSet<String>();
        for(SaveDcInfoReqDCItems objESaveDcInfoReqDCItems:busData02.getDCItemsInfo()){
            set.add(objESaveDcInfoReqDCItems.getItemName());
        }
        if(set.size()<busData02.getDCItemsInfo().size()){
            throw new SystemException("xxx","新增失败，采集内容已重复");
        }

        // 保存版本下面的items
        List<DCItemInfo> dcItemInfos = new ArrayList<DCItemInfo>();

        // 把业务数据遍历存入items中
        for (SaveDcInfoReqDCItems objESaveDcInfoReqDCItems : busData02.getDCItemsInfo()){
            DCItemInfo objDCItemInfo = new DCItemInfo();
            if("".equals(objESaveDcInfoReqDCItems.getItemName())||objESaveDcInfoReqDCItems.getItemName()==null){
                throw new SystemException("xxx","采集内容: "+objESaveDcInfoReqDCItems.getItemName()+"采集内容名称不能为空");
            }
            if("".equals(objESaveDcInfoReqDCItems.getDataAlias())||objESaveDcInfoReqDCItems.getDataAlias()==null){
                throw new SystemException("xxx","采集内容: "+objESaveDcInfoReqDCItems.getItemName()+"数据别名不能为空");
            }
            if("02".equals(objESaveDcInfoReqDCItems.getItemType())){
                objDCItemInfo.setDefValue(objESaveDcInfoReqDCItems.getDefValue());
                objDCItemInfo.setsValue(objESaveDcInfoReqDCItems.getSValue());
                objDCItemInfo.setUpLimit(objESaveDcInfoReqDCItems.getUpLimit());
                objDCItemInfo.setDwLimit(objESaveDcInfoReqDCItems.getDwLimit());
            }else {
                if("".equals(objESaveDcInfoReqDCItems.getSValue())||objESaveDcInfoReqDCItems.getSValue()==null){
                    throw new SystemException("xxx","采集内容: "+objESaveDcInfoReqDCItems.getItemName()+"标准值不能为空");
                }

                if("".equals(objESaveDcInfoReqDCItems.getUpLimit())||objESaveDcInfoReqDCItems.getUpLimit()==null){
                    throw new SystemException("xxx","采集内容: "+objESaveDcInfoReqDCItems.getItemName()+"上限浮动值不能为空");
                }

                if("".equals(objESaveDcInfoReqDCItems.getDwLimit())||objESaveDcInfoReqDCItems.getDwLimit()==null){
                    throw new SystemException("xxx","采集内容: "+objESaveDcInfoReqDCItems.getItemName()+"下限浮动值不能为空");
                }

                /*if("".equals(objESaveDcInfoReqDCItems.getDefValue())||objESaveDcInfoReqDCItems.getDefValue()==null){
                    throw new SystemException("xxx","采集内容: "+objESaveDcInfoReqDCItems.getItemName()+"默认值不能为空");
                }*/

                /*if("".equals(objESaveDcInfoReqDCItems.getSValue())||objESaveDcInfoReqDCItems.getSValue()==null&&"".equals(objESaveDcInfoReqDCItems.getUpLimit())||objESaveDcInfoReqDCItems.getUpLimit()==null){
                    throw new SystemException("xxx","采集内容: "+objESaveDcInfoReqDCItems.getItemName()+"标准值和上限浮动值不能为空");
                }
                if("".equals(objESaveDcInfoReqDCItems.getSValue())||objESaveDcInfoReqDCItems.getSValue()==null&&"".equals(objESaveDcInfoReqDCItems.getDwLimit())||objESaveDcInfoReqDCItems.getDwLimit()==null){
                    throw new SystemException("xxx","采集内容: "+objESaveDcInfoReqDCItems.getItemName()+"标准值和下限浮动值不能为空");
                }
                if("".equals(objESaveDcInfoReqDCItems.getUpLimit())||objESaveDcInfoReqDCItems.getUpLimit()==null&&"".equals(objESaveDcInfoReqDCItems.getDwLimit())||objESaveDcInfoReqDCItems.getDwLimit()==null){
                    throw new SystemException("xxx","采集内容: "+objESaveDcInfoReqDCItems.getItemName()+"上限浮动值和下限浮动值不能为空");
                }*/


                try{
                    Float.valueOf(objESaveDcInfoReqDCItems.getSValue());
                }catch (Exception e){
                    throw new SystemException("x1", "标准值填写不正确");
                }
                try{
                    Float.valueOf(objESaveDcInfoReqDCItems.getUpLimit());
                }catch (Exception e){
                    throw new SystemException("x2", "上限浮动值填写不正确");
                }
                try{
                    Float.valueOf(objESaveDcInfoReqDCItems.getDwLimit());
                }catch (Exception e){
                    throw new SystemException("x3", "下限浮动值填写不正确");
                }
                objDCItemInfo.setDefValue(objESaveDcInfoReqDCItems.getDefValue());
                objDCItemInfo.setsValue(objESaveDcInfoReqDCItems.getSValue());
                objDCItemInfo.setUpLimit(objESaveDcInfoReqDCItems.getUpLimit());
                objDCItemInfo.setDwLimit(objESaveDcInfoReqDCItems.getDwLimit());
            }


            // 设置业务参数数据
            objDCItemInfo.setGuid(CommonUtils.getRandomNumber());
            objDCItemInfo.setCreateTime(new Date());
            objDCItemInfo.setCreator(CommonUtils.readUser().getRealName());
            objDCItemInfo.setCreator(CommonUtils.readUser().getUserName());

            if("00".equals(objESaveDcInfoReqDCItems.getChecked())){
                objDCItemInfo.setChecked("00");
            }
            else {
                objDCItemInfo.setChecked("01");
            }


            objDCItemInfo.setAction(objESaveDcInfoReqDCItems.getAction());

            objDCItemInfo.setDataAlias(objESaveDcInfoReqDCItems.getDataAlias());


            objDCItemInfo.setItemName(objESaveDcInfoReqDCItems.getItemName());
            objDCItemInfo.setItemType(objESaveDcInfoReqDCItems.getItemType());

            objDCItemInfo.setRemark(busData02.getRemark());
            objDCItemInfo.setDcVerGd(dcVerInfo.getGuid());
            dcItemDAO.insert(objDCItemInfo);

           // dcItemInfos.add(objDCItemInfo);
        }
      //  dcItemDAO.insertDCItemInfos(dcItemInfos);

        objESaveDcInfoResB.setData(objESaveDcInfoResD);
        objESaveDcInfoRes.setBody(objESaveDcInfoResB);
        return objESaveDcInfoRes;
    }

    /**
     * 获取数据采集所有版本信息
     */
    public GetDVTreeInfoRes GetselectDCVerInfoByDcRd(int dcRd) throws SystemException{

        GetDVTreeInfoRes objEGetDVTreeInfoRes = new GetDVTreeInfoRes();

        GetDVTreeInfoResB objEGetDVTreeInfoResB = new GetDVTreeInfoResB();

        List<GetDVTreeInfoResD> getDVTreeInfoResDs = new ArrayList<GetDVTreeInfoResD>();

        GetDVTreeInfoResD objEGetDVTreeInfoResD = null;

        // 先根据dcRd 查询出dcGd,然后根据dcGd再查询版本列表
        DCInfo dcInfo = dcDAO.selectDCInfoByDcRd(dcRd);
        // 根据dcGd查询下面所有的版本
        List<DCVerInfo> dcVerInfos = dcVerDAO.selectDCVerInfosByDcGd(dcInfo.getGuid());

        // 判断返回数据采集信息列表是否为空
        if (dcVerInfos.size() <= 0 || dcVerInfos == null) { // 为空
            throw new SystemException("MG000001", "数据采集版本信息列表为空！");
        }
        // 不为空，直接把数据写入返回的DTO中
        for (int i = 0 ; i < dcVerInfos.size() ; i++){
            objEGetDVTreeInfoResD = new GetDVTreeInfoResD();
            objEGetDVTreeInfoResD.setDCVerRd(dcVerInfos.get(i).getRuid());
            objEGetDVTreeInfoResD.setVersion(dcVerInfos.get(i).getVersion());
            getDVTreeInfoResDs.add(objEGetDVTreeInfoResD);
        }
        objEGetDVTreeInfoResB.setData(getDVTreeInfoResDs);

        objEGetDVTreeInfoRes.setBody(objEGetDVTreeInfoResB);

        return objEGetDVTreeInfoRes;
    }
    /**
     * 获取数据采集版本详细信息
     */
    public GetDVInfoRes GetselectDCVerInfoByDCVerRd(int dcVerRd) throws SystemException{
        // 创建DTO对象
        GetDVInfoRes objEGetDVInfoRes = new GetDVInfoRes();

        GetDVInfoResB objEGetDVInfoResB = new GetDVInfoResB();

        GetDVInfoResD objEGetDVInfoResD = new GetDVInfoResD();

        List<GetDVInfoResDDCItems> objEGetDVInfoResDDCItemss = new ArrayList<GetDVInfoResDDCItems>();

        GetDVInfoResDDCItems objEGetDVInfoResDDCItems = null;

        // 先获取DCVerInfo对象，通过对象获取dcVerGd
        DCVerInfo objDCVerInfo = dcVerDAO.selectDCVerInfoByRuid(dcVerRd);

        // 获取Item列表
        List<DCItemInfo> dcItemInfos = dcItemDAO.selectDCItemInfosByDCVerGd(objDCVerInfo.getGuid());
        // 不为空，直接把数据写入返回的DTO中
        for (int i = 0 ; i < dcItemInfos.size(); i++){
            objEGetDVInfoResDDCItems = new GetDVInfoResDDCItems();
            objEGetDVInfoResDDCItems.setItemRd(dcItemInfos.get(i).getRuid());
            objEGetDVInfoResDDCItems.setItemName(dcItemInfos.get(i).getItemName());
            objEGetDVInfoResDDCItems.setDataAlias(dcItemInfos.get(i).getDataAlias());
            objEGetDVInfoResDDCItems.setSValue(dcItemInfos.get(i).getsValue());
            objEGetDVInfoResDDCItems.setUpLimit(dcItemInfos.get(i).getUpLimit());
            objEGetDVInfoResDDCItems.setDwLimit(dcItemInfos.get(i).getDwLimit());
            objEGetDVInfoResDDCItems.setDefValue(dcItemInfos.get(i).getDefValue());
            objEGetDVInfoResDDCItems.setChecked(dcItemInfos.get(i).getChecked());
            // 00#数字类型 01#浮点 02#字符串
            GetDVInfoResDDItemType objEGetDVInfoResDDItemType = new GetDVInfoResDDItemType();
            if ("00".equals(dcItemInfos.get(i).getItemType())) {
                objEGetDVInfoResDDItemType.setTypeText("数字类型");
                objEGetDVInfoResDDItemType.setTypeVal("00");
            } else if ("01".equals(dcItemInfos.get(i).getItemType())) {
                objEGetDVInfoResDDItemType.setTypeText("浮点类型");
                objEGetDVInfoResDDItemType.setTypeVal("01");
            } else if ("02".equals(dcItemInfos.get(i).getItemType())) {
                objEGetDVInfoResDDItemType.setTypeText("字符串类型");
                objEGetDVInfoResDDItemType.setTypeVal("02");
            }
            objEGetDVInfoResDDCItems.setItemType(objEGetDVInfoResDDItemType);
            objEGetDVInfoResDDCItemss.add(objEGetDVInfoResDDCItems);
        }


        //两个邮件
        GetDcInfoResDEmailDistribution getDcInfoResDEmailDistribution=new   GetDcInfoResDEmailDistribution();
        EmailDistributionInfo emailDistributionInfo1=emailDistributionDAO.SelectEmailDistributionInfoByGuid(objDCVerInfo.getPresetEmailDistributionGd());
        if(emailDistributionInfo1!=null){
            getDcInfoResDEmailDistribution.setEmailDistributionRd(emailDistributionInfo1.getRuid());
            getDcInfoResDEmailDistribution.setEmailDistributionName(emailDistributionInfo1.getDistributionName());
            objEGetDVInfoResD.setEmailDistributionInfo(getDcInfoResDEmailDistribution);
        }else {
            objEGetDVInfoResD.setEmailDistributionInfo(null);
        }

        GetDcInfoResDEmailMessage getDcInfoResDEmailMessage=new GetDcInfoResDEmailMessage();
        EmailMessageInfo emailMessageInfo1=emailMessageDAO.SelectEmailMessageInfoByGuid(objDCVerInfo.getPresetEmailMessageGd());
        if(emailMessageInfo1!=null){
            getDcInfoResDEmailMessage.setEmailMessageRd(emailMessageInfo1.getRuid());
            getDcInfoResDEmailMessage.setEmailMessageName(emailMessageInfo1.getEmailName());
            objEGetDVInfoResD.setEmailMessageInfo(getDcInfoResDEmailMessage);
        }else {
            objEGetDVInfoResD.setEmailMessageInfo(null);
        }


        // 把数据封装到DTO里面
        objEGetDVInfoResD.setVersion(objDCVerInfo.getVersion());
        objEGetDVInfoResD.setStatus(objDCVerInfo.getStatus());
        objEGetDVInfoResD.setDCRd(dcDAO.selectByGuid(objDCVerInfo.getDcGd()).getRuid());
        objEGetDVInfoResD.setDCVerRd(objDCVerInfo.getRuid());
        objEGetDVInfoResD.setDCName(objDCVerInfo.getDcName());
        objEGetDVInfoResD.setIsDef(objDCVerInfo.getIsDef());
       // objEGetDVInfoResD.setCreator(objDCVerInfo.getCreator());
        objEGetDVInfoResD.setCreator("测试");
        objEGetDVInfoResD.setCreateTime(objDCVerInfo.getCreateTime());
        objEGetDVInfoResD.setLastModifyMan(objDCVerInfo.getLastModifyMan());
        objEGetDVInfoResD.setLastModifyTime(objDCVerInfo.getLastModifyTime());
        objEGetDVInfoResD.setRemark(objDCVerInfo.getRemark());

        objEGetDVInfoResD.setDCItemsInfo(objEGetDVInfoResDDCItemss);

        objEGetDVInfoResB.setData(objEGetDVInfoResD);

        objEGetDVInfoRes.setBody(objEGetDVInfoResB);

        return objEGetDVInfoRes;
    }
    /**
     * 新增版本
     */
    public SaveDcInfoRes AddsaveDCVerInfo(SaveDcInfoReqBD04 busData04, DCVerInfo argObjDCVerInfo) throws SystemException{
        SaveDcInfoRes objESaveDcInfoRes = new SaveDcInfoRes();
        SaveDcInfoResB objESaveDcInfoResB = new SaveDcInfoResB();
        SaveDcInfoResD objESaveDcInfoResD = new SaveDcInfoResD();

        int count = 0;
        /*
         * 新增版本：
         *      1. 判断新增的版本是否是默认版本，
         *         1.1 如果是默认版本，清空之前的默认版本
         *             1.1.1 更新DCInfo，设置当前为默认版本
         *         1.2 如果不是默认版本
         *             1.2.1直接新增当前版本到当前DCInfo下面
         */
        DCVerInfo objDCVerInfo = new DCVerInfo();
        objDCVerInfo.setGuid(CommonUtils.getRandomNumber());

        // 更新DCInfo,先根据DCRd得到DCInfo，再把页面表单封装进去
        DCInfo objDCInfo = dcDAO.selectDCInfoByDcRd(busData04.getDCRd());

        //版本不能重复--LFZ
        List<DCVerInfo> objEDCVerInfo=dcVerDAO.selectDCVerInfosByDcGd(objDCInfo.getGuid());

        for(DCVerInfo obj:objEDCVerInfo){
            if(obj.getVersion().equals(busData04.getVersion())){
                throw new SystemException("MG0006F","版本已存在");
            }
        }

        if ((busData04.getDCName()).equals("")){
            throw new SystemException("MG0003F","数据采集名称不能为空");
        }

        if ((busData04.getVersion()).equals("")){
            throw new SystemException("MG0003F","版本不能为空");
        }

        objDCInfo.setLastModifyMan(argObjDCVerInfo.getLastModifyMan());
        objDCInfo.setLastModifyTime(argObjDCVerInfo.getLastModifyTime());
        // 判断修改的当前DCVerInfo是否是默认版本，
        // 如果是：则修改DCInfo的默认版本细信息；
        if("00".equals(busData04.getIsDef())){
            //更新DCInfo对应的DCVerInfo
            DCVerInfo dv = dcVerDAO.selectDefaultDCVerInfoByDcGd(objDCInfo.getGuid());
            dv.setIsDef("01");
            dv.setDcName(objDCInfo.getDcName());
            count = dcVerDAO.updateDCVerInfo(dv);
            if(count <= 0) throw new SystemException("MG000001", "设置数据采集为非默认版本失败！");
            // 更新DCInfo
            objDCInfo.setDcName(busData04.getDCName());
            objDCInfo.setVerGd(objDCVerInfo.getGuid());
            count = dcDAO.updateDCInfo(objDCInfo);
            if(count <= 0) throw new SystemException("MG000001", "更新数据采集信息失败！");
        }
        // 如果不是，则什么也不做

        objDCVerInfo.setDcGd(objDCInfo.getGuid());
        objDCVerInfo.setCreator(argObjDCVerInfo.getCreator());
        objDCVerInfo.setCreateTime(argObjDCVerInfo.getCreateTime());
        objDCVerInfo.setLastModifyMan(argObjDCVerInfo.getLastModifyMan());
        objDCVerInfo.setLastModifyTime(argObjDCVerInfo.getLastModifyTime());

        objDCVerInfo.setDcName(objDCInfo.getDcName());
        objDCVerInfo.setVersion(busData04.getVersion());
        objDCVerInfo.setStatus(busData04.getStatus());
        objDCVerInfo.setIsDef(busData04.getIsDef());
        objDCVerInfo.setRemark(busData04.getRemark());

        // 保存新增版本信息
        count = dcVerDAO.insertDCVerInfo(objDCVerInfo);
        if(count <= 0) throw new SystemException("MG000001", "新增版本信息失败！");

        // 保存版本下面的items
        List<DCItemInfo> dcItemInfos = new ArrayList<DCItemInfo>();
        DCItemInfo objDCItemInfo = null;
        // 把业务数据遍历存入items中
        for (SaveDcInfoReqDCItems objESaveDcInfoReqDCItems : busData04.getDCItemsInfo()){
            objDCItemInfo = new DCItemInfo();
            // 设置业务参数数据
            objDCItemInfo.setGuid(CommonUtils.getRandomNumber());
            objDCItemInfo.setCreator(objDCVerInfo.getCreator());
            objDCItemInfo.setCreateTime(objDCVerInfo.getCreateTime());
            objDCItemInfo.setLastModifyMan(objDCVerInfo.getLastModifyMan());
            objDCItemInfo.setLastModifyTime(objDCVerInfo.getLastModifyTime());
            objDCItemInfo.setDcVerGd(objDCVerInfo.getGuid());
            objDCItemInfo.setChecked(objESaveDcInfoReqDCItems.getChecked());
            objDCItemInfo.setDefValue(objESaveDcInfoReqDCItems.getDefValue());
            objDCItemInfo.setDwLimit(objESaveDcInfoReqDCItems.getDwLimit());
            objDCItemInfo.setItemName(objESaveDcInfoReqDCItems.getItemName());
            objDCItemInfo.setItemType(objESaveDcInfoReqDCItems.getItemType());
            objDCItemInfo.setsValue(objESaveDcInfoReqDCItems.getSValue());
            objDCItemInfo.setUpLimit(objESaveDcInfoReqDCItems.getUpLimit());
            objDCItemInfo.setAction(objESaveDcInfoReqDCItems.getAction());
            objDCItemInfo.setDataAlias(objESaveDcInfoReqDCItems.getDataAlias());
            dcItemInfos.add(objDCItemInfo);
        }
        count = dcItemDAO.insertDCItemInfos(dcItemInfos);
        if(count <= 0) throw new SystemException("MG000001", "新增数据采集信息内容失败！");
        // 返回DTO信息
        objESaveDcInfoResB.setData(objESaveDcInfoResD);
        objESaveDcInfoRes.setBody(objESaveDcInfoResB);

        return objESaveDcInfoRes;
    }
    /**
     * 删除版本
     */
    public SaveDcInfoRes RmdeleteDcVerInfoByRuid(int dcVerRd) throws SystemException {

        SaveDcInfoRes objESaveDcInfoRes = new SaveDcInfoRes();
        SaveDcInfoResB objESaveDcInfoResB = new SaveDcInfoResB();
        SaveDcInfoResD objESaveDcInfoResD = new SaveDcInfoResD();
        int count = 0;

        // 1.删除的版本是否是默认版本
        DCVerInfo objDCVerInfo = dcVerDAO.selectDCVerInfoByRuid(dcVerRd);

        if("00".equals(objDCVerInfo.getIsDef())){

            if(dcItemDAO.deleteDCItemInfoByDCVerGd(objDCVerInfo.getGuid())<=0){
                throw new SystemException("MG_MES4001812015001F","删除详细信息失败");
            }
            if(dcVerDAO.deleteDCVerInfoByGuid (objDCVerInfo.getGuid())<=0){
                throw new SystemException("MG_MES4001812015001F","删除版本信息失败");
            }

            if(dcDAO.deleteDCInfoByGuid (objDCVerInfo.getDcGd())<=0){
                throw new SystemException("MG_MES4001812015001F","删除自己信息失败");
            }

        }
        else{

            if(dcItemDAO.deleteDCItemInfoByDCVerGd(objDCVerInfo.getGuid())<=0){
                throw new SystemException("MG_MES4001812015001F","删除版本信息失败");
            }
            if(dcVerDAO.deleteDCVerInfoByGuid (objDCVerInfo.getGuid())<=0){
                throw new SystemException("MG_MES4001812015001F","删除版本信息失败");
            }

        }


        // 返回DTO信息
        objESaveDcInfoResB.setData(objESaveDcInfoResD);
        objESaveDcInfoRes.setBody(objESaveDcInfoResB);

        return objESaveDcInfoRes;
    }

    //复制
    public SaveDcInfoRes AddargSaveDcInfoReqBD03(SaveDcInfoReqBD03 argSaveDcInfoReqBD03) throws SystemException {
        SaveDcInfoRes objESaveDcInfoRes = new SaveDcInfoRes();
        SaveDcInfoResB objESaveDcInfoResB = new SaveDcInfoResB();
        SaveDcInfoResD objESaveDcInfoResD = new SaveDcInfoResD();
        String gd = CommonUtils.getRandomNumber();
        DCInfo objEDCInfo=dcDAO.selectDCInfoByDcRd(argSaveDcInfoReqBD03.getDCRd());
        if(objEDCInfo==null){
            throw new SystemException("xxx","查询失败");
        }

        /**   修改复制默认版本---- ZLL 2017年7月21日 17:50:19  **/
        DCVerInfo objEDCVerInfo = dcVerDAO.selectDefaultDCVerInfoByDcGd(objEDCInfo.getGuid());
        List<DCItemInfo> objEDCItemInfos = dcItemDAO.selectDCItemInfosByDCVerGd(objEDCVerInfo.getGuid());

        List<DCItemInfo> objEobjEDCItemInfoList = new ArrayList<DCItemInfo>(Collections.<DCItemInfo>emptyList());
        if(objEDCItemInfos != null && objEDCItemInfos.size()>0){
            objEDCVerInfo.setGuid(CommonUtils.getRandomNumber());
            for(DCItemInfo objEDCItemInfo:objEDCItemInfos){
                objEDCItemInfo.setGuid(CommonUtils.getRandomNumber());
                objEDCItemInfo.setDcVerGd(objEDCVerInfo.getGuid());
                objEDCItemInfo.setLastModifyTime(new Date());
                objEDCItemInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
                objEDCItemInfo.setCreateTime(new Date());
                objEDCItemInfo.setCreator(CommonUtils.readUser().getRealName());
                objEobjEDCItemInfoList.add(objEDCItemInfo);
            }
            dcItemDAO.insertDCItemInfos(objEobjEDCItemInfoList);


            objEDCVerInfo.setLastModifyTime(new Date());
            objEDCVerInfo.setLastModifyMan((String) SecurityUtils.getSubject().getPrincipal());
            objEDCVerInfo.setCreateTime(new Date());
            objEDCVerInfo.setCreator((String) SecurityUtils.getSubject().getPrincipal());
            objEDCVerInfo.setDcGd(gd);
            objEDCInfo.setVerGd(objEDCVerInfo.getGuid());
            dcVerDAO.insertDCVerInfo(objEDCVerInfo);
        }
        /**      **/
/*
        List<DCVerInfo> objEDCVerInfos=dcVerDAO.selectDCVerInfosByDcGd(objEDCInfo.getGuid());
        if(objEDCVerInfos==null||objEDCVerInfos.size()<=0){
            throw new SystemException("xxx","查询失败");
        }

        objEDCInfo.setGuid(CommonUtils.getRandomNumber());
        objEDCInfo.setLastModifyTime(new Date());
        objEDCInfo.setLastModifyMan((String) SecurityUtils.getSubject().getPrincipal());
        objEDCInfo.setCreateTime(new Date());
        objEDCInfo.setCreator((String) SecurityUtils.getSubject().getPrincipal());


        DCItemInfo objEfo=null;
        for(DCVerInfo obj:objEDCVerInfos){


            if("00".equals(obj.getIsDef())){

                List<DCItemInfo> objEDCItemInfos=dcItemDAO.selectDCItemInfosByDCVerGd(obj.getGuid());

                for(DCItemInfo objEDCItemInfo:objEDCItemInfos){
                    objEfo=new DCItemInfo();
                    objEfo.setGuid(CommonUtils.getRandomNumber());
                    objEfo.setDcVerGd(objEDCItemInfo.getGuid());
                    objEDCItemInfos.add(objEfo);
                }
                dcItemDAO.insertDCItemInfos(objEDCItemInfos);

                obj.setGuid(CommonUtils.getRandomNumber());
                obj.setLastModifyTime(new Date());
                obj.setLastModifyMan((String) SecurityUtils.getSubject().getPrincipal());
                obj.setCreateTime(new Date());
                obj.setCreator((String) SecurityUtils.getSubject().getPrincipal());
                obj.setDcGd(objEDCInfo.getGuid());
                objEDCInfo.setVerGd(obj.getGuid());
                dcVerDAO.insertDCVerInfo(obj);
            }
        }*/
        objEDCInfo.setGuid(gd);
        objEDCInfo.setLastModifyTime(new Date());
        objEDCInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        objEDCInfo.setCreateTime(new Date());
        objEDCInfo.setCreator(CommonUtils.readUser().getRealName());
        dcDAO.insertDCInfo(objEDCInfo);

        DCInfo objEDCInfoss=dcDAO.selectDCInfoByDcRd(objEDCInfo.getRuid());
        objEDCInfoss.setDcName(objEDCInfoss.getDcName()+objEDCInfoss.getRuid());
        dcDAO.updateDCInfo(objEDCInfoss);

        // 返回DTO信息
        objESaveDcInfoResB.setData(objESaveDcInfoResD);
        objESaveDcInfoRes.setBody(objESaveDcInfoResB);

        return objESaveDcInfoRes;
    }
}
