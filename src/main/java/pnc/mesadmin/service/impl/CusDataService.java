package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.CusDataCDAO;
import pnc.mesadmin.dao.CusDataDtlCDAO;
import pnc.mesadmin.dto.GetAllCDataInfo.GetAllCDataInfoRes;
import pnc.mesadmin.dto.GetAllCDataInfo.GetAllCDataInfoResB;
import pnc.mesadmin.dto.GetAllCDataInfo.GetAllCDataInfoResD;
import pnc.mesadmin.dto.GetCDataInfo.*;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveCDataInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.CusDataCInfo;
import pnc.mesadmin.entity.CusDataDtlCInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.CusDataIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/3 10:08
 * @Description:
 */
@Transactional
@Service
public class CusDataService implements CusDataIService {

    @Resource
    private CusDataCDAO cusDataCDAO;

    @Resource
    private BaseIService baseIService;

    @Resource
    private CusDataDtlCDAO cusDataDtlCDAO;


    //查询列表
    @Override
    public GetAllCDataInfoRes QALLGetAllCDataInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllCDataInfoRes getAllCDataInfoRes=new GetAllCDataInfoRes();
        GetAllCDataInfoResB getAllCDataInfoResB=new GetAllCDataInfoResB();
        List<GetAllCDataInfoResD> getAllCDataInfoResDS=new ArrayList<GetAllCDataInfoResD>();

        List<CusDataCInfo> cusDataCInfos=baseIService.QALLBaseInfo(CusDataCDAO.class, "SelectAllCusDataCInfo",
                argInitDataFields, argPageInfo);

        if(cusDataCInfos!=null&&cusDataCInfos.size()>0){
            for(CusDataCInfo cusDataCInfo:cusDataCInfos){
                GetAllCDataInfoResD getAllCDataInfoResD=new GetAllCDataInfoResD();
                getAllCDataInfoResD.setCusDataRd(cusDataCInfo.getRuid());
                getAllCDataInfoResD.setCusDataName(cusDataCInfo.getCusDataName());
                getAllCDataInfoResDS.add(getAllCDataInfoResD);
            }
        }
        getAllCDataInfoResB.setData(getAllCDataInfoResDS);
        getAllCDataInfoRes.setBody(getAllCDataInfoResB);
        return getAllCDataInfoRes;
    }

    //查询单个
    @Override
    public GetCDataInfoRes GetGetCDataInfoRes(GetCDataInfoReq00 getCDataInfoReq00) {
        GetCDataInfoRes getCDataInfoRes=new GetCDataInfoRes();
        GetCDataInfoResB getCDataInfoResB=new GetCDataInfoResB();
        GetCDataInfoResD getCDataInfoResD=new GetCDataInfoResD();
        List<GetCDataInfoResDCusDataDtl> getCDataInfoResDCusDataDtls=new ArrayList<GetCDataInfoResDCusDataDtl>();
        if(getCDataInfoReq00.getCusDataRd()<=0){
            throw new SystemException("xxx","查询失败，该标识不存在");
        }
        CusDataCInfo cusDataCInfo=cusDataCDAO.SelectCusDataCInfo(getCDataInfoReq00.getCusDataRd());
        if(cusDataCInfo==null){
            throw new SystemException("xxx","查询失败，该信息为空");
        }

        List<CusDataDtlCInfo> cusDataDtlCInfos=cusDataDtlCDAO.SelectCusDataDtlCInfoByCusDataGd(cusDataCInfo.getGuid());
        if(cusDataDtlCInfos!=null&&cusDataDtlCInfos.size()>0){
            for(CusDataDtlCInfo cusDataDtlCInfo:cusDataDtlCInfos){
                GetCDataInfoResDCusDataDtl getCDataInfoResDCusDataDtl=new GetCDataInfoResDCusDataDtl();
                getCDataInfoResDCusDataDtl.setCusDataDtlRd(cusDataDtlCInfo.getRuid());
                getCDataInfoResDCusDataDtl.setDisplayName(cusDataDtlCInfo.getDisplayName());
                getCDataInfoResDCusDataDtl.setVal(cusDataDtlCInfo.getVal());
                getCDataInfoResDCusDataDtls.add(getCDataInfoResDCusDataDtl);
            }
        }
        getCDataInfoResD.setCusDataDtl(getCDataInfoResDCusDataDtls);

        getCDataInfoResD.setCusDataRd(cusDataCInfo.getRuid());
        getCDataInfoResD.setCusDataName(cusDataCInfo.getCusDataName());

        getCDataInfoResD.setCreator(cusDataCInfo.getCreator());
        getCDataInfoResD.setCreateTime(DateUtil.format(cusDataCInfo.getCreateTime()));
        getCDataInfoResD.setLastModifyMan(cusDataCInfo.getLastModifyMan());
        getCDataInfoResD.setLastModifyTime(DateUtil.format(cusDataCInfo.getLastModifyTime()));
        getCDataInfoResD.setRemark(cusDataCInfo.getRemark());


        getCDataInfoResB.setData(getCDataInfoResD);
        getCDataInfoRes.setBody(getCDataInfoResB);
        return getCDataInfoRes;
    }

    //保存
    @Override
    public SaveCDataInfoRes AddSaveCDataInfoRes(SaveCDataInfoReq00 saveCDataInfoReq00) {
        SaveCDataInfoRes saveCDataInfoRes=new SaveCDataInfoRes();
        SaveCDataInfoResB saveCDataInfoResB=new SaveCDataInfoResB();

        if(saveCDataInfoReq00.getCusDataName()==null||"".equals(saveCDataInfoReq00.getCusDataName())){
            throw new SystemException("xx","新增失败，自定义名称不能为空");
        }

        CusDataCInfo cusDataCInfo=cusDataCDAO.SelectCusDataCInfoByCusDataName(saveCDataInfoReq00.getCusDataName());
        if(cusDataCInfo!=null){
            throw new SystemException("xx","新增失败，自定义名称已存在");
        }

        if(saveCDataInfoReq00.getCusDataDtl().size()<=0){
            throw new SystemException("xx","新增失败，明细不能为空");
        }

        CusDataCInfo cusDataCInfo1=new CusDataCInfo();
        cusDataCInfo1.setCusDataName(saveCDataInfoReq00.getCusDataName());
        cusDataCInfo1.setRemark(saveCDataInfoReq00.getRemark());
        cusDataCInfo1.setGuid(CommonUtils.getRandomNumber());
        cusDataCInfo1.setCreateTime(new Date());
        cusDataCInfo1.setCreator(CommonUtils.readUser().getRealName());
        cusDataCDAO.InsertCusDataCInfo(cusDataCInfo1);

        Set<String> set=new HashSet<>();
        for(SaveCDataInfoReq00CusDataDtl saveCDataInfoReq00CusDataDtls:saveCDataInfoReq00.getCusDataDtl()){
            set.add(saveCDataInfoReq00CusDataDtls.getVal());
        }

        if(set.size()!=saveCDataInfoReq00.getCusDataDtl().size()){
            throw new SystemException("xx","新增失败，明细信息中的值有重复");
        }


        for(SaveCDataInfoReq00CusDataDtl saveCDataInfoReq00CusDataDtl:saveCDataInfoReq00.getCusDataDtl()){
            CusDataDtlCInfo cusDataDtlCInfo=new CusDataDtlCInfo();
            cusDataDtlCInfo.setGuid(CommonUtils.getRandomNumber());
            cusDataDtlCInfo.setCusDataGd(cusDataCInfo1.getGuid());
            cusDataDtlCInfo.setVal(saveCDataInfoReq00CusDataDtl.getVal());
            cusDataDtlCInfo.setDisplayName(saveCDataInfoReq00CusDataDtl.getDisplayName());
            cusDataDtlCInfo.setCreateTime(new Date());
            cusDataDtlCInfo.setCreator(CommonUtils.readUser().getRealName());
            cusDataDtlCDAO.InsertCusDataDtlCInfo(cusDataDtlCInfo);
        }

        saveCDataInfoRes.setBody(saveCDataInfoResB);
        return saveCDataInfoRes;
    }

    //删除
    @Override
    public SaveCDataInfoRes RmSaveCDataInfoRes(SaveCDataInfoReq01 saveCDataInfoReq01) {
        SaveCDataInfoRes saveCDataInfoRes=new SaveCDataInfoRes();
        SaveCDataInfoResB saveCDataInfoResB=new SaveCDataInfoResB();
        if(saveCDataInfoReq01.getCusDataRd()<=0){
            throw new SystemException("xxx","删除失败，该标识不存在");
        }
        CusDataCInfo cusDataCInfo=cusDataCDAO.SelectCusDataCInfo(saveCDataInfoReq01.getCusDataRd());
        if(cusDataCInfo==null){
            throw new SystemException("xxx","删除失败，该信息为空");
        }

        List<CusDataDtlCInfo> cusDataDtlCInfos=cusDataDtlCDAO.SelectCusDataDtlCInfoByCusDataGd(cusDataCInfo.getGuid());
        if(cusDataDtlCInfos!=null&&cusDataDtlCInfos.size()>0){
            for(CusDataDtlCInfo cusDataDtlCInfo:cusDataDtlCInfos){
                if(cusDataDtlCDAO.DeleteCusDataDtlCInfo(cusDataDtlCInfo.getRuid())<=0){
                    throw new SystemException("xxx","删除明细信息失败");
                }
            }
            if(cusDataCDAO.DeleteCusDataCInfo(cusDataCInfo.getRuid())<=0){
                throw new SystemException("xxx","删除主表信息失败");
            }
        }
        saveCDataInfoRes.setBody(saveCDataInfoResB);
        return saveCDataInfoRes;
    }

    //修改
    @Override
    public SaveCDataInfoRes ModSaveCDataInfoRes(SaveCDataInfoReq02 saveCDataInfoReq02) {
        SaveCDataInfoRes saveCDataInfoRes=new SaveCDataInfoRes();
        SaveCDataInfoResB saveCDataInfoResB=new SaveCDataInfoResB();
        if(saveCDataInfoReq02.getCusDataRd()<=0){
            throw new SystemException("xxx","修改失败，该标识不存在");
        }
        CusDataCInfo cusDataCInfo=cusDataCDAO.SelectCusDataCInfo(saveCDataInfoReq02.getCusDataRd());
        if(cusDataCInfo==null){
            throw new SystemException("xxx","修改失败，该信息为空");
        }

        if(saveCDataInfoReq02.getCusDataName()==null||"".equals(saveCDataInfoReq02.getCusDataName())){
            throw new SystemException("xxx","修改失败，自定义名称不能为空");
        }

        if(saveCDataInfoReq02.getCusDataDtl().size()<=0){
            throw new SystemException("xx","修改失败，明细不能为空");
        }
        CusDataCInfo cusDataCInfo1=cusDataCDAO.SelectCusDataCInfoByCusDataName(saveCDataInfoReq02.getCusDataName());
        if(cusDataCInfo1!=null&&!cusDataCInfo1.getCusDataName().equals(cusDataCInfo.getCusDataName())){
            throw new SystemException("xx","修改失败，自定义名称已存在");
        }

        Set<String> set=new HashSet<>();
        for(SaveCDataInfoReq02CusDataDtl saveCDataInfoReq00CusDataDtls:saveCDataInfoReq02.getCusDataDtl()){
            set.add(saveCDataInfoReq00CusDataDtls.getVal());
        }

        if(set.size()!=saveCDataInfoReq02.getCusDataDtl().size()){
            throw new SystemException("xx","修改失败，明细信息中的值有重复");
        }

        //先删除明细再新增
        List<CusDataDtlCInfo> cusDataDtlCInfos=cusDataDtlCDAO.SelectCusDataDtlCInfoByCusDataGd(cusDataCInfo.getGuid());
        if(cusDataDtlCInfos!=null&&cusDataDtlCInfos.size()>0){
            for(CusDataDtlCInfo cusDataDtlCInfo:cusDataDtlCInfos){
                if(cusDataDtlCDAO.DeleteCusDataDtlCInfo(cusDataDtlCInfo.getRuid())<=0){
                    throw new SystemException("xxx","删除明细信息失败");
                }
            }
            for(SaveCDataInfoReq02CusDataDtl saveCDataInfoReq02CusDataDtl:saveCDataInfoReq02.getCusDataDtl()){
                CusDataDtlCInfo cusDataDtlCInfo=new CusDataDtlCInfo();
                cusDataDtlCInfo.setGuid(CommonUtils.getRandomNumber());
                cusDataDtlCInfo.setCusDataGd(cusDataCInfo.getGuid());
                cusDataDtlCInfo.setVal(saveCDataInfoReq02CusDataDtl.getVal());
                cusDataDtlCInfo.setDisplayName(saveCDataInfoReq02CusDataDtl.getDisplayName());
                cusDataDtlCInfo.setCreateTime(new Date());
                cusDataDtlCInfo.setCreator(CommonUtils.readUser().getRealName());
                cusDataDtlCDAO.InsertCusDataDtlCInfo(cusDataDtlCInfo);
            }

        }

        cusDataCInfo.setRemark(saveCDataInfoReq02.getRemark());
        cusDataCInfo.setCusDataName(saveCDataInfoReq02.getCusDataName());
        cusDataCInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        cusDataCInfo.setLastModifyTime(new Date());
        if(cusDataCDAO.UpdateCusDataCInfo(cusDataCInfo)<=0){
            throw new SystemException("xxx","修改信息失败");
        }

        saveCDataInfoRes.setBody(saveCDataInfoResB);
        return saveCDataInfoRes;
    }
}
