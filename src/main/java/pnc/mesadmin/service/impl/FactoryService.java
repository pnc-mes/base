package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.CompanyDAO;
import pnc.mesadmin.dao.FactoryDAO;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllFaInfo.GetAllFaInfoRes;
import pnc.mesadmin.dto.GetAllFaInfo.GetAllFaInfoResB;
import pnc.mesadmin.dto.GetAllFaInfo.GetAllFaInfoResD;
import pnc.mesadmin.dto.GetFaInfo.GetFaInfoReqBD00;
import pnc.mesadmin.dto.GetFaInfo.GetFaInfoRes;
import pnc.mesadmin.dto.GetFaInfo.GetFaInfoResB;
import pnc.mesadmin.dto.GetFaInfo.GetFaInfoResD;
import pnc.mesadmin.dto.SaveFaInfo.*;
import pnc.mesadmin.entity.CompanyInfo;
import pnc.mesadmin.entity.FactoryInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.FactoryIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工厂信息Service层实现层
 * 创建人：张亮亮
 * 创建时间：2017-5-27
 * 修改人：
 * 修改时间：
 */
@Transactional
@Service
public class FactoryService implements FactoryIService {
    @Resource
    private FactoryDAO factoryDAO;

    @Resource
    private CompanyDAO companyDAO;

    @Resource
    private BaseIService baseIService;

    //dto获取工厂信息列表
    public GetAllFaInfoRes QALLGetAllFaInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllFaInfoRes objEGetAllFaInfoRes=new GetAllFaInfoRes();
        GetAllFaInfoResB objEGetAllFaInfoResB=new GetAllFaInfoResB();
        List<GetAllFaInfoResD> objEGetAllFaInfoResDs=new ArrayList<GetAllFaInfoResD>();

        List<FactoryInfo> objFactoryInfos= baseIService.QALLBaseInfo(FactoryDAO.class, "SelectAllFactoryInfo",
                argInitDataFields, argPageInfo);

        if(objFactoryInfos!=null||objFactoryInfos.size()>0) {

            for (FactoryInfo obj : objFactoryInfos) {
                GetAllFaInfoResD objGetAllFaInfoResD = new GetAllFaInfoResD();
                objGetAllFaInfoResD.setFaRd(obj.getRuid());
                objGetAllFaInfoResD.setFaName(obj.getFactoryName());
                objGetAllFaInfoResD.setContactor(obj.getContactor());
                objGetAllFaInfoResD.setAddress(obj.getAddress());
                //获取工厂里面所属企业companyGd
                String companyGd = obj.getCompanyGd();
                //根据companyGd 到企业里查询，所属企业信息
                CompanyInfo objCompanyInfo = companyDAO.SelectCompanyInfoByGuid(companyGd);
                if (objCompanyInfo != null) {
                    objGetAllFaInfoResD.setCpName(objCompanyInfo.getCompanyName());
                }

                objEGetAllFaInfoResDs.add(objGetAllFaInfoResD);
            }

        }
        objEGetAllFaInfoResB.setData(objEGetAllFaInfoResDs);
        objEGetAllFaInfoRes.setBody(objEGetAllFaInfoResB);

        return objEGetAllFaInfoRes;
    }

    /**
     * 查询工厂信息列表(新)
     * @param res
     * @return
     */
    @Override
    public PageResult<GetAllFaInfoResD> QALLGetAllFaNewRes(BaseRequest res) {
        List<GetAllFaInfoResD> resDList = new ArrayList<GetAllFaInfoResD>();
        GetAllFaInfoResD resD = null;

        Page<FactoryInfo> page = new Page<>(res.getCurrent(), res.getSize() <= 0 ? 1000 : res.getSize());

        IPage<FactoryInfo> iPage = factoryDAO.selectPage(page, CommonUtils.getQueryWrapper(res.getFields()));

        for (FactoryInfo obj : iPage.getRecords()) {
            resD = new GetAllFaInfoResD();
            resD.setFaRd(obj.getRuid());
            resD.setFaName(obj.getFactoryName());
            resD.setContactor(obj.getContactor());
            resD.setAddress(obj.getAddress());
            //根据companyGd 到企业里查询，所属企业信息
            CompanyInfo objCompanyInfo = companyDAO.SelectCompanyInfoByGuid(obj.getCompanyGd());
            if (objCompanyInfo != null) {
                resD.setCpName(objCompanyInfo.getCompanyName());
            }

            resDList.add(resD);
        }

        return new PageResult<GetAllFaInfoResD>(iPage, resDList);
    }

    @Override
    public BaseResponse GetAllFaInfoV2(SaveFaInfoReqBD00 request) {
        Page<FactoryInfo> page = new Page<>(request.getPage(), request.getLimit());
        IPage<FactoryInfo> response = factoryDAO.selectPage(page, new QueryWrapper<FactoryInfo>()
                .like(!StringUtils.isBlank(request.getFaName()), "factoryName", request.getFaName()));
        return BaseResponse.success(response);
    }

    //dto查询工厂信息 根据传过来的FaRd
    public GetFaInfoRes GetGetFaInfoRes(GetFaInfoReqBD00 argGetFaInfoReqBD00) {
        GetFaInfoRes objEGetFaInfoRes=new GetFaInfoRes();
        GetFaInfoResB objEGetFaInfoResB=new GetFaInfoResB();
        //根据页面传过来的工厂id 查询工厂信息
        FactoryInfo objFactoryInfo= factoryDAO.SelectFactoryInfoByruid(argGetFaInfoReqBD00.getFaRd());

        if(objFactoryInfo!=null) {

            GetFaInfoResD objEGetFaInfoResD = new GetFaInfoResD();
            objEGetFaInfoResD.setFaRd(objFactoryInfo.getRuid());
            objEGetFaInfoResD.setFaName(objFactoryInfo.getFactoryName());
            objEGetFaInfoResD.setContactor(objFactoryInfo.getContactor());
            objEGetFaInfoResD.setAddress(objFactoryInfo.getAddress());
            objEGetFaInfoResD.setCreator(objFactoryInfo.getCreator());
            objEGetFaInfoResD.setCreateTime(DateUtil.format(objFactoryInfo.getCreateTime()));
            objEGetFaInfoResD.setLastModifyMan(objFactoryInfo.getLastModifyMan());
            objEGetFaInfoResD.setLastModifyTime(DateUtil.format(objFactoryInfo.getLastModifyTime()));
            objEGetFaInfoResD.setRemark(objFactoryInfo.getRemark());
            //获取工厂里面的CompanyGd
            String objCpRd = objFactoryInfo.getCompanyGd();
            //根据objCpRd 到企业表查询企业信息
            CompanyInfo objECompanyInfo = companyDAO.SelectCompanyInfoByGuid(objCpRd);
            if (objECompanyInfo != null) {
                objEGetFaInfoResD.setCpRd(objECompanyInfo.getRuid());
                objEGetFaInfoResD.setCpName(objECompanyInfo.getCompanyName());
            }

            objEGetFaInfoResB.setData(objEGetFaInfoResD);
        }
        objEGetFaInfoRes.setBody(objEGetFaInfoResB);

        return objEGetFaInfoRes;
    }

    //dto新增工厂信息
    public SaveFaInfoRes AddGetFaInfoRes(SaveFaInfoReqBD00 argSaveFaInfoReqBD00) {
        SaveFaInfoRes objESaveFaInfoRes=new SaveFaInfoRes();
        SaveFaInfoResB objESaveFaInfoResB=new SaveFaInfoResB();
        SaveFaInfoResD objESaveFaInfoResD=new SaveFaInfoResD();
        if(argSaveFaInfoReqBD00.getFaName()==null||"".equals(argSaveFaInfoReqBD00.getFaName())){
            throw new SystemException("xxxx","新增失败，名称不能为空");
        }
        if(argSaveFaInfoReqBD00.getContactor()==null||"".equals(argSaveFaInfoReqBD00.getContactor())){
            throw new SystemException("xxxx","新增失败，联系人不能为空");
        }
        if(argSaveFaInfoReqBD00.getAddress()==null||"".equals(argSaveFaInfoReqBD00.getAddress())){
            throw new SystemException("xxxx","新增失败，地址不能为空");
        }
        FactoryInfo objFactoryInfo=new FactoryInfo();
        objFactoryInfo.setGuid(CommonUtils.getRandomNumber());

        //查询所有，判断名称是否存在
        List<FactoryInfo> objEFactoryInfo=factoryDAO.SelectAllFactoryInfo();
        for(FactoryInfo obj:objEFactoryInfo){
            if(obj.getFactoryName().equals(argSaveFaInfoReqBD00.getFaName())){
                throw new SystemException("MG_MES3001112010001F","新增失败，工厂名称已存在");
            }
        }

        objFactoryInfo.setFactoryName(argSaveFaInfoReqBD00.getFaName());
        objFactoryInfo.setContactor(argSaveFaInfoReqBD00.getContactor());
        objFactoryInfo.setAddress(argSaveFaInfoReqBD00.getAddress());
        objFactoryInfo.setRemark(argSaveFaInfoReqBD00.getRemark());
        objFactoryInfo.setCreator(CommonUtils.readUser().getRealName());
        objFactoryInfo.setCreateTime(new Date());
        //企业信息
        CompanyInfo objCompanyInfo= companyDAO.SelectCompanyInfoByRuid(argSaveFaInfoReqBD00.getCpRd());
        if(objCompanyInfo==null){
            throw new SystemException("MG_MES3001112010002F","查询企业信息失败");
        }
        objFactoryInfo.setCompanyGd(objCompanyInfo.getGuid());

       factoryDAO.InsertFactoryInfo(objFactoryInfo);



        objESaveFaInfoResB.setData(objESaveFaInfoResD);
        objESaveFaInfoRes.setBody(objESaveFaInfoResB);

        return objESaveFaInfoRes;
    }

    //dto复制
    public SaveFaInfoRes AddSaveFaInfoRes(SaveFaInfoReqBD03 argSaveFaInfoReqBD03) {
        SaveFaInfoRes objESaveFaInfoRes=new SaveFaInfoRes();
        SaveFaInfoResB objESaveFaInfoResB=new SaveFaInfoResB();
        SaveFaInfoResD objESaveFaInfoResD=new SaveFaInfoResD();

        FactoryInfo objFactoryInfo= factoryDAO.SelectFactoryInfoByruid(argSaveFaInfoReqBD03.getFaRd());
        if(objFactoryInfo==null){
            throw new SystemException("MG_MES3001112040001F","查询工厂信息失败");
        }

        objFactoryInfo.setGuid(CommonUtils.getRandomNumber());
        objFactoryInfo.setCreateTime(new Date());
        objFactoryInfo.setLastModifyTime(new Date());
        objFactoryInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        objFactoryInfo.setCreator(CommonUtils.readUser().getRealName());
        factoryDAO.InsertFactoryInfo(objFactoryInfo);

        FactoryInfo objEFactoryInfo=factoryDAO.SelectFactoryInfoByguid(objFactoryInfo.getGuid());
        if(objEFactoryInfo==null){
            throw new SystemException("MG_MES3001112040003F","查询工厂信息失败");
        }

        objEFactoryInfo.setFactoryName(objEFactoryInfo.getFactoryName()+objEFactoryInfo.getRuid());
        if(factoryDAO.UpdateFactoryInfoByruid(objEFactoryInfo)<=0){
            throw new SystemException("MG_MES3001112040004F","更新工厂名称信息失败");
        }

        objESaveFaInfoResB.setData(objESaveFaInfoResD);
        objESaveFaInfoRes.setBody(objESaveFaInfoResB);

        return objESaveFaInfoRes;
    }

    //dto更新
    public SaveFaInfoRes ModSaveFaInfoRes(SaveFaInfoReqBD02 argSaveFaInfoReqBD02) {
        SaveFaInfoRes objESaveFaInfoRes=new SaveFaInfoRes();
        SaveFaInfoResB objESaveFaInfoResB=new SaveFaInfoResB();
        SaveFaInfoResD objESaveFaInfoResD=new SaveFaInfoResD();
        if(argSaveFaInfoReqBD02.getFaName()==null||"".equals(argSaveFaInfoReqBD02.getFaName())){
            throw new SystemException("xxxx","更新失败，名称不能为空");
        }
        if(argSaveFaInfoReqBD02.getContactor()==null||"".equals(argSaveFaInfoReqBD02.getContactor())){
            throw new SystemException("xxxx","更新失败，联系人不能为空");
        }
        if(argSaveFaInfoReqBD02.getAddress()==null||"".equals(argSaveFaInfoReqBD02.getAddress())){
            throw new SystemException("xxxx","更新失败，地址不能为空");
        }
        FactoryInfo objEFactoryInfo=factoryDAO.SelectFactoryInfoByruid(argSaveFaInfoReqBD02.getFaRd());

        objEFactoryInfo.setRuid(argSaveFaInfoReqBD02.getFaRd());
        //更新的工厂名称不允许重复
        FactoryInfo factoryInfoname=factoryDAO.SelectFactoryInfoByFactoryName(argSaveFaInfoReqBD02.getFaName());

        if(factoryInfoname!=null&&!factoryInfoname.getFactoryName().equals(objEFactoryInfo.getFactoryName())){
            throw new SystemException("MG_MES3001013030002F","更新失败，工厂名称已存在");
        }
        objEFactoryInfo.setFactoryName(argSaveFaInfoReqBD02.getFaName());
        objEFactoryInfo.setContactor(argSaveFaInfoReqBD02.getContactor());
        objEFactoryInfo.setAddress(argSaveFaInfoReqBD02.getAddress());
        objEFactoryInfo.setRemark(argSaveFaInfoReqBD02.getRemark());
        objEFactoryInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        objEFactoryInfo.setLastModifyTime(new Date());

        //根据传过来的企业ID 来获取企业的guid
        CompanyInfo objCompanyInfo= companyDAO.SelectCompanyInfoByRuid(argSaveFaInfoReqBD02.getCpRd());
        if(objCompanyInfo==null){
            throw new SystemException("MG_MES3001112030002F","查询企业信息失败");
        }
        objEFactoryInfo.setCompanyGd(objCompanyInfo.getGuid());

        if(factoryDAO.UpdateFactoryInfoByruid(objEFactoryInfo)<=0){
            throw new SystemException("MG_MES3001112030003F","更新工厂信息失败");
        }
        objESaveFaInfoResB.setData(objESaveFaInfoResD);
        objESaveFaInfoRes.setBody(objESaveFaInfoResB);

        return objESaveFaInfoRes;
    }

    //dto删除
    public SaveFaInfoRes RmSaveFaInfoRes(SaveFaInfoReqBD01 argSaveFaInfoReqBD01) {
        SaveFaInfoRes objESaveFaInfoRes=new SaveFaInfoRes();
        SaveFaInfoResB objESaveFaInfoResB=new SaveFaInfoResB();
        SaveFaInfoResD objESaveFaInfoResD=new SaveFaInfoResD();

        if(factoryDAO.DeleteFactoryInfoByruid(argSaveFaInfoReqBD01.getFaRd())<=0){
            throw new SystemException("MG_MES3001112020001F","删除工厂信息失败");
        }
        objESaveFaInfoResB.setData(objESaveFaInfoResD);
        objESaveFaInfoRes.setBody(objESaveFaInfoResB);
        return objESaveFaInfoRes;
    }
}
