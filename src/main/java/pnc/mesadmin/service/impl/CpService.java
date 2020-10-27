package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.CodeRuleDAO;
import pnc.mesadmin.dao.CompanyDAO;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllCpInfo.GetAllCpInfoRes;
import pnc.mesadmin.dto.GetAllCpInfo.GetAllCpInfoResB;
import pnc.mesadmin.dto.GetAllCpInfo.GetAllCpInfoResD;
import pnc.mesadmin.dto.GetCpInfo.GetCpInfoReqBD00;
import pnc.mesadmin.dto.GetCpInfo.GetCpInfoRes;
import pnc.mesadmin.dto.GetCpInfo.GetCpInfoResB;
import pnc.mesadmin.dto.GetCpInfo.GetCpInfoResD;
import pnc.mesadmin.dto.SaveCpInfo.*;
import pnc.mesadmin.entity.CodeRuleInfo;
import pnc.mesadmin.entity.CompanyInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.CpIService;
import pnc.mesadmin.service.GConfigIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;
import pnc.mesadmin.utils.MyPage;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：企业信息Service层实现层类
 * 创建人：张亮亮
 * 创建时间：2017-5-26
 * 修改人：
 * 修改时间：
 */
@Transactional
@Service
public class CpService implements CpIService {

    @Resource
    private CompanyDAO companyDAO;

    @Resource
    private BaseIService baseIService;

    @Resource
    private CodeRuleDAO codeRuleDAO;

    @Resource
    private GConfigIService gConfigIService;

    //dto获取企业列表信息
    public GetAllCpInfoRes QALLCompanyInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllCpInfoRes objEGetAllCpInfoRes=new GetAllCpInfoRes();
        GetAllCpInfoResB objEGetAllCpInfoResB=new GetAllCpInfoResB();
        List<GetAllCpInfoResD> objEGetAllCpInfoResDs=new ArrayList<GetAllCpInfoResD>();

        //获取所有的企业信息
        List<CompanyInfo> objCompanyInfo= baseIService.QALLBaseInfo(CompanyDAO.class, "SelectAllCompanyInfo",
                argInitDataFields, argPageInfo);

        if(objCompanyInfo!=null||objCompanyInfo.size()>0) {

            for (CompanyInfo ogjCompanyInfo : objCompanyInfo) {
                GetAllCpInfoResD objGetAllCpInfoResD = new GetAllCpInfoResD();
                objGetAllCpInfoResD.setCpRd(ogjCompanyInfo.getRuid());
                objGetAllCpInfoResD.setCpName(ogjCompanyInfo.getCompanyName());
                objGetAllCpInfoResD.setAddress(ogjCompanyInfo.getAddress());
                objEGetAllCpInfoResDs.add(objGetAllCpInfoResD);
            }
        }

        objEGetAllCpInfoResB.setData(objEGetAllCpInfoResDs);
        objEGetAllCpInfoRes.setBody(objEGetAllCpInfoResB);
        return objEGetAllCpInfoRes;
    }

    /**
     * 查询企业信息列表(新)
     * @param req
     * @return
     */
    @Override
    public PageResult<GetAllCpInfoResD> QALLCompanyNew(BaseRequest req) {
        List<GetAllCpInfoResD> resDList = new ArrayList<GetAllCpInfoResD>();
        GetAllCpInfoResD resD = null;

        IPage<CompanyInfo> iPage = companyDAO.selectPage(new MyPage(req), CommonUtils.getQueryWrapper(req.getFields()));

        for (CompanyInfo ogjCompanyInfo : iPage.getRecords()) {
            resD = new GetAllCpInfoResD();
            resD.setCpRd(ogjCompanyInfo.getRuid());
            resD.setCpName(ogjCompanyInfo.getCompanyName());
            resD.setCpCode(ogjCompanyInfo.getCompanyCode());
            resD.setContactor(ogjCompanyInfo.getContactor());
            resD.setAddress(ogjCompanyInfo.getAddress());
            resD.setStatus(ogjCompanyInfo.getStatus());
            resDList.add(resD);
        }

        return new PageResult<>(iPage, resDList);
    }

    //dto获取企业信息
    public GetCpInfoRes GetGetCpInfoRes(GetCpInfoReqBD00 argGetCpInfoRes00) {
        GetCpInfoRes objEGetCpInfoRes=new GetCpInfoRes();
        GetCpInfoResB objEGetCpInfoResB=new GetCpInfoResB();


        //根据传过来的企业ID(CpRd)来查询企业信息
        CompanyInfo objCompanyInfo= companyDAO.SelectCompanyInfoByRuid(argGetCpInfoRes00.getCpRd());
        if(objCompanyInfo!=null) {

            GetCpInfoResD objEGetCpInfoResD = new GetCpInfoResD();
            objEGetCpInfoResD.setCpRd(objCompanyInfo.getRuid());
            objEGetCpInfoResD.setCpCode(objCompanyInfo.getCompanyCode());
            objEGetCpInfoResD.setCpName(objCompanyInfo.getCompanyName());
            objEGetCpInfoResD.setContactor(objCompanyInfo.getContactor());
            objEGetCpInfoResD.setAddress(objCompanyInfo.getAddress());
            objEGetCpInfoResD.setDSource(objCompanyInfo.getdSource());
            objEGetCpInfoResD.setStatus(objCompanyInfo.getStatus());
            objEGetCpInfoResD.setCreator(objCompanyInfo.getCreator());
            objEGetCpInfoResD.setCreateTime(DateUtil.format(objCompanyInfo.getCreateTime()));
            objEGetCpInfoResD.setLastModifyMan(objCompanyInfo.getLastModifyMan());
            objEGetCpInfoResD.setLastModifyTime(DateUtil.format(objCompanyInfo.getLastModifyTime()));
            objEGetCpInfoResD.setRemark(objCompanyInfo.getRemark());

            objEGetCpInfoResB.setData(objEGetCpInfoResD);
        }
        objEGetCpInfoRes.setBody(objEGetCpInfoResB);

        return objEGetCpInfoRes;
    }
    //dto新增
    public SaveCpInfoRes ADDSaveCpInfoRes(SaveCpInfoReqBD00 argSaveCpInfoRes00) {
        SaveCpInfoRes objESaveCpInfoRes=new SaveCpInfoRes();
        SaveCpInfoResB objESaveCpInfoResB=new SaveCpInfoResB();
        SaveCpInfoResD objESaveCpInfoResD=new SaveCpInfoResD();

        CompanyInfo objCompanyInfo=new CompanyInfo();
        objCompanyInfo.setGuid(CommonUtils.getRandomNumber());
        List<CompanyInfo> objECompanyInfos=companyDAO.SelectAllCompanyInfo();

        if(argSaveCpInfoRes00.getCpName()==null||"".equals(argSaveCpInfoRes00.getCpName())){
            throw new SystemException("xxxx","新增失败，名称不能为空");
        }
        if(argSaveCpInfoRes00.getContactor()==null||"".equals(argSaveCpInfoRes00.getContactor())){
            throw new SystemException("xxxx","新增失败，联系人不能为空");
        }
        if(argSaveCpInfoRes00.getAddress()==null||"".equals(argSaveCpInfoRes00.getAddress())){
            throw new SystemException("xxxx","新增失败，地址不能为空");
        }



        for(CompanyInfo obj:objECompanyInfos){
            if(obj.getCompanyName().equals(argSaveCpInfoRes00.getCpName())){
                throw new SystemException("MG_MES3001012010001F","新增失败，企业名称已存在");
            }
            if (!"".equals(argSaveCpInfoRes00.getCpCode()) && obj.getCompanyCode().equals(argSaveCpInfoRes00.getCpCode())){
                throw new SystemException("MG0006F","新增失败,企业代码已存在");
            }
        }

        //查询代码生成
        CodeRuleInfo codeRuleInfo=codeRuleDAO.SelectCodeRuleInfocodeType("10");

        String SCode="";

        if(!"".equals(argSaveCpInfoRes00.getCpCode())) {
            objCompanyInfo.setCompanyCode(argSaveCpInfoRes00.getCpCode());
        }else if(codeRuleInfo!=null && "00".equals(codeRuleInfo.getStatus())){
            SCode=gConfigIService.GetCreateSC(codeRuleInfo);
            objCompanyInfo.setCompanyCode(SCode);
        }else{
            throw new SystemException("", "请输入{企业代码}，或请到全局配置进行代码生成配置");
        }

        objCompanyInfo.setCompanyName(argSaveCpInfoRes00.getCpName());
        objCompanyInfo.setContactor(argSaveCpInfoRes00.getContactor());
        objCompanyInfo.setCreator(CommonUtils.readUser().getRealName());
        objCompanyInfo.setAddress(argSaveCpInfoRes00.getAddress());
        objCompanyInfo.setStatus(argSaveCpInfoRes00.getStatus());
        objCompanyInfo.setdSource("01");
        objCompanyInfo.setRemark(argSaveCpInfoRes00.getRemark());
        objCompanyInfo.setCreateTime(new Date());
        companyDAO.InsertCompanyInfo(objCompanyInfo);

        //返回保存企业信息
        CompanyInfo companyInfo=companyDAO.SelectCompanyInfoByGuid(objCompanyInfo.getGuid());
        objESaveCpInfoResD.setCpRd(companyInfo.getRuid());
        objESaveCpInfoResD.setCpCode(companyInfo.getCompanyCode());

        objESaveCpInfoResB.setData(objESaveCpInfoResD);
        objESaveCpInfoRes.setBody(objESaveCpInfoResB);
        return objESaveCpInfoRes;
    }
    //dto删除
    public SaveCpInfoRes RmSaveCpInfoRes(SaveCpInfoReqBD01 argSaveCpInfoRes01) {
        SaveCpInfoRes objESaveCpInfoRes=new SaveCpInfoRes();
        SaveCpInfoResB objESaveCpInfoResB=new SaveCpInfoResB();
        SaveCpInfoResD objESaveCpInfoResD=new SaveCpInfoResD();

        CompanyInfo objCompanyInfo= companyDAO.SelectCompanyInfoByRuid(argSaveCpInfoRes01.getCpRd());

        if("00".equals(objCompanyInfo.getdSource())){
            throw new SystemException("","为外部数据不能更新");
        }

        if(companyDAO.DeleteCompanyInfoByruid(argSaveCpInfoRes01.getCpRd())<=0){
           throw new SystemException("MG_MES3001012020001F", "删除企业失败");
       }

        objESaveCpInfoResB.setData(objESaveCpInfoResD);
        objESaveCpInfoRes.setBody(objESaveCpInfoResB);
        return objESaveCpInfoRes;
    }
    //dto更新
    public SaveCpInfoRes ModSaveCpInfoRes(SaveCpInfoReqBD02 argSaveCpInfoRes02) {
        SaveCpInfoRes objESaveCpInfoRes=new SaveCpInfoRes();
        SaveCpInfoResB objESaveCpInfoResB=new SaveCpInfoResB();
        SaveCpInfoResD objESaveCpInfoResD=new SaveCpInfoResD();
        if(argSaveCpInfoRes02.getCpName()==null||"".equals(argSaveCpInfoRes02.getCpName())){
            throw new SystemException("xxxx","更新失败，名称不能为空");
        }
        if(argSaveCpInfoRes02.getContactor()==null||"".equals(argSaveCpInfoRes02.getContactor())){
            throw new SystemException("xxxx","更新失败，联系人不能为空");
        }
        if(argSaveCpInfoRes02.getAddress()==null||"".equals(argSaveCpInfoRes02.getAddress())){
            throw new SystemException("xxxx","更新失败，地址不能为空");
        }

        CompanyInfo objECompanyInfo=companyDAO.SelectCompanyInfoByRuid(argSaveCpInfoRes02.getCpRd());
        if(objECompanyInfo==null){
            throw new SystemException("MG_MES3001013030001F","该信息不存在");
        }
        CompanyInfo companyInfoname=companyDAO.SelectCompanyInfoByCompanyName(argSaveCpInfoRes02.getCpName());
        if(companyInfoname!=null&&!companyInfoname.getCompanyName().equals(objECompanyInfo.getCompanyName())){
            throw new SystemException("MG_MES3001013030002F","更新失败，企业名称已存在");
        }

        if("00".equals(objECompanyInfo.getdSource())){
            throw new SystemException("","为外部数据不能更新");
        }

        objECompanyInfo.setRuid(argSaveCpInfoRes02.getCpRd());
        objECompanyInfo.setCompanyName(argSaveCpInfoRes02.getCpName());
        objECompanyInfo.setContactor(argSaveCpInfoRes02.getContactor());
        objECompanyInfo.setAddress(argSaveCpInfoRes02.getAddress());
        objECompanyInfo.setStatus(argSaveCpInfoRes02.getStatus());
        objECompanyInfo.setRemark(argSaveCpInfoRes02.getRemark());
        objECompanyInfo.setLastModifyTime(new Date());
        objECompanyInfo.setLastModifyMan(CommonUtils.readUser().getRealName());

          if(companyDAO.UpdateConmpanyInfoByruid(objECompanyInfo)<=0){
              throw new SystemException("MG_MES3001013030003F", "更新企业信息失败");
          }
        objESaveCpInfoResB.setData(objESaveCpInfoResD);
        objESaveCpInfoRes.setBody(objESaveCpInfoResB);

        return objESaveCpInfoRes;
    }
    //dto复制
    public SaveCpInfoRes ModSaveCpInfoRes(SaveCpInfoReqBD03 argSaveCpInfoRes03) {
        SaveCpInfoRes objESaveCpInfoRes=new SaveCpInfoRes();
        SaveCpInfoResB objESaveCpInfoResB=new SaveCpInfoResB();
        SaveCpInfoResD objESaveCpInfoResD=new SaveCpInfoResD();

        CompanyInfo objCompanyInfo= companyDAO.SelectCompanyInfoByRuid(argSaveCpInfoRes03.getCpRd());
        if(objCompanyInfo==null){
            throw new SystemException("MG_MES3001013040001F","复制失败，该信息不存在");
        }
        CompanyInfo objCompanyInfos=new CompanyInfo();
        objCompanyInfos.setGuid(CommonUtils.getRandomNumber());
        objCompanyInfos.setCompanyCode(objCompanyInfo.getCompanyCode());
        objCompanyInfos.setCompanyName(objCompanyInfo.getCompanyName());
        objCompanyInfos.setContactor(objCompanyInfo.getContactor());
        objCompanyInfos.setAddress(objCompanyInfo.getAddress());
        objCompanyInfos.setStatus(objCompanyInfo.getStatus());
        objCompanyInfos.setdSource(objCompanyInfo.getdSource());
        objCompanyInfos.setCreator(objCompanyInfo.getCreator());
        objCompanyInfos.setCreateTime(new Date());
        objCompanyInfos.setLastModifyTime(new Date());
        objCompanyInfos.setLastModifyMan(CommonUtils.readUser().getRealName());
        objCompanyInfos.setRemark(objCompanyInfo.getRemark());

        companyDAO.InsertCompanyInfo(objCompanyInfos);

        CompanyInfo objECompanyInfo=companyDAO.SelectCompanyInfoByGuid(objCompanyInfos.getGuid());
        if(objECompanyInfo==null){
            throw new SystemException("MG_MES3001013040003F","查询企业信息失败");
        }
        objECompanyInfo.setCompanyName(objCompanyInfo.getCompanyName()+objECompanyInfo.getRuid());
        if(companyDAO.UpdateConmpanyInfoByruid(objECompanyInfo)<=0){
            throw new SystemException("MG_MES3001013040004F","更新企业信息失败");
        }

        objESaveCpInfoResB.setData(objESaveCpInfoResD);
        objESaveCpInfoRes.setBody(objESaveCpInfoResB);
        return objESaveCpInfoRes;
    }


}
