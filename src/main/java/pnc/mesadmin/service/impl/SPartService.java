package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.FactoryDAO;
import pnc.mesadmin.dao.SparePartDAO;
import pnc.mesadmin.dao.SupplierDAO;
import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.BaseDto.BaseResB;
import pnc.mesadmin.dto.GetAllSPartInfo.GetAllSPartInfoResD;
import pnc.mesadmin.dto.GetSPartInfo.GetSPartInfoReq00;
import pnc.mesadmin.dto.GetSPartInfo.GetSPartInfoResD;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveSPartInfo.SaveSPartInfoReq00;
import pnc.mesadmin.dto.SaveSPartInfo.SaveSPartInfoReq01;
import pnc.mesadmin.dto.SaveSPartInfo.SaveSPartInfoReq02;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.FactoryInfo;
import pnc.mesadmin.entity.SparePartInfo;
import pnc.mesadmin.entity.SupplierInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.SPartIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/11/8 16:12
 * @Description:
 */
@Transactional
@Service
public class SPartService implements SPartIService {
    @Resource
    private SparePartDAO sparePartDAO;

    @Resource
    private SupplierDAO supplierDAO;

    @Resource
    private FactoryDAO factoryDAO;

    @Resource
    private BaseIService baseIService;

    //列表
    @Override
    public BaseRes QALLBaseRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        BaseRes baseRes=new BaseRes();
        BaseResB baseResB=new BaseResB();

        List<GetAllSPartInfoResD> getAllSPartInfoResDS=new ArrayList<GetAllSPartInfoResD>();

        List<SparePartInfo> sparePartInfos=baseIService.QALLBaseInfo(SparePartDAO.class, "SelectSparePartInfo",
                argInitDataFields, argPageInfo);

        if(sparePartInfos!=null&&sparePartInfos.size()>0) {

            for (SparePartInfo obj : sparePartInfos) {
                GetAllSPartInfoResD getAllDevInfoResD = new GetAllSPartInfoResD();
                getAllDevInfoResD.setSPartRd(obj.getRuid());
                getAllDevInfoResD.setSPartName(obj.getPartName());
                getAllSPartInfoResDS.add(getAllDevInfoResD);
            }
        }

        baseResB.setData(getAllSPartInfoResDS);
        baseRes.setBody(baseResB);
        return baseRes;
    }

    //单个
    @Override
    public BaseRes GetBaseRes(GetSPartInfoReq00 getSPartInfoReq00) {
        BaseRes baseRes=new BaseRes();
        BaseResB baseResB=new BaseResB();

        if("".equals(getSPartInfoReq00.getSPartRd())||getSPartInfoReq00.getSPartRd()<=0){
              throw new SystemException("xxxx","查询失败，标识信息不存在");
        }

        GetSPartInfoResD  getSPartInfoResD=new GetSPartInfoResD();

        SparePartInfo sparePartInfo=sparePartDAO.SelectSparePartInfoByruid(getSPartInfoReq00.getSPartRd());
        if(sparePartInfo!=null){
            getSPartInfoResD.setSPartRd(sparePartInfo.getRuid());
            getSPartInfoResD.setSPartName(sparePartInfo.getPartName());
            getSPartInfoResD.setVenderSN(sparePartInfo.getVenderSN());

            GetSPartInfoResD.Factory factory=new GetSPartInfoResD.Factory();
            FactoryInfo factoryInfo=factoryDAO.SelectFactoryInfoByguid(sparePartInfo.getFaGd());
            if(factoryInfo!=null){
                factory.setFaRd(factoryInfo.getRuid());
                factory.setFaName(factoryInfo.getFactoryName());
            }
            getSPartInfoResD.setFactory(factory);

            GetSPartInfoResD.Suppier suppier=new GetSPartInfoResD.Suppier();
            SupplierInfo supplierInfo=supplierDAO.SelectByGuid(sparePartInfo.getSupplierGd());
            if(supplierInfo!=null){
                suppier.setSupRd(supplierInfo.getRuid());
                suppier.setSupName(supplierInfo.getSupplierName());
                getSPartInfoResD.setSuppier(suppier);
            }else {
                getSPartInfoResD.setSuppier(null);
            }


            getSPartInfoResD.setCreator(sparePartInfo.getCreator());
            getSPartInfoResD.setCreateTime(DateUtil.format(sparePartInfo.getCreateTime()));
            getSPartInfoResD.setLastModifyMan(sparePartInfo.getLastModifyMan());
            getSPartInfoResD.setLastModifyTime(DateUtil.format(sparePartInfo.getLastModifyTime()));
            getSPartInfoResD.setRemark(sparePartInfo.getRemark());

        }


        baseResB.setData(getSPartInfoResD);
        baseRes.setBody(baseResB);
        return baseRes;
    }

    //新增
    @Override
    public BaseRes AddBaseRes(SaveSPartInfoReq00 saveSPartInfoReq00) {
        BaseRes baseRes=new BaseRes();
        BaseResB baseResB=new BaseResB();
        if("".equals(saveSPartInfoReq00.getSPartName())||saveSPartInfoReq00.getSPartName()==null){
            throw new SystemException("xxx","备品备件名称不能为空");
        }
        if (saveSPartInfoReq00.getSupRd()<=0){
            throw new SystemException("xxx","供应商信息不能为空");
        }
        SparePartInfo sparePartInfo1=sparePartDAO.SelectSparePartInfoByPartName(saveSPartInfoReq00.getSPartName());
        if(sparePartInfo1!=null){
            throw new SystemException("xxx","备品备件名称已存在");
        }
        SparePartInfo sparePartInfo=new SparePartInfo();
        sparePartInfo.setGuid(CommonUtils.getRandomNumber());
        sparePartInfo.setPartName(saveSPartInfoReq00.getSPartName());
        FactoryInfo factoryInfo=factoryDAO.SelectFactoryInfoByruid(saveSPartInfoReq00.getFaRd());
        if(factoryInfo!=null){
            sparePartInfo.setFaGd(factoryInfo.getGuid());
        }

        SupplierInfo supplierInfo=supplierDAO.SelectBySuppId(saveSPartInfoReq00.getSupRd());
        if(supplierInfo!=null){
            sparePartInfo.setSupplierGd(supplierInfo.getGuid());
        }
        sparePartInfo.setVenderSN(saveSPartInfoReq00.getVenderSN());

        sparePartInfo.setCreator(CommonUtils.readUser().getRealName());
        sparePartInfo.setCreateTime(new Date());
        sparePartInfo.setRemark(saveSPartInfoReq00.getRemark());
        sparePartDAO.InsertSparePartInfo(sparePartInfo);
        baseRes.setBody(baseResB);
        return baseRes;
    }

    //删除
    @Override
    public BaseRes RmBaseRes(SaveSPartInfoReq01 saveSPartInfoReq01) {
        BaseRes baseRes=new BaseRes();
        BaseResB baseResB=new BaseResB();

        if("".equals(saveSPartInfoReq01.getSPartRd())||saveSPartInfoReq01.getSPartRd()<=0){
            throw new SystemException("xxxx","删除失败，标识信息不存在");
        }

        if(sparePartDAO.DeleteSparePartInfoByruid(saveSPartInfoReq01.getSPartRd())<=0){
            throw new SystemException("xxxx","删除失败");
        }


        baseRes.setBody(baseResB);
        return baseRes;
    }

    //修改
    @Override
    public BaseRes ModBaseRes(SaveSPartInfoReq02 saveSPartInfoReq02) {
        BaseRes baseRes=new BaseRes();
        BaseResB baseResB=new BaseResB();

        if("".equals(saveSPartInfoReq02.getSPartRd())||saveSPartInfoReq02.getSPartRd()<=0){
            throw new SystemException("xxxx","修改失败，标识信息不存在");
        }
        if("".equals(saveSPartInfoReq02.getSPartName())||saveSPartInfoReq02.getSPartName()==null){
            throw new SystemException("xxx","备品备件名称不能为空");
        }
        if (saveSPartInfoReq02.getSupRd()<=0){
            throw new SystemException("xxx","供应商信息不能为空");
        }

        SparePartInfo sparePartInfo=sparePartDAO.SelectSparePartInfoByruid(saveSPartInfoReq02.getSPartRd());
        if(sparePartInfo!=null){


            SparePartInfo sparePartInfo1=sparePartDAO.SelectSparePartInfoByPartName(saveSPartInfoReq02.getSPartName());
            if(sparePartInfo1!=null&&!sparePartInfo1.getPartName().equals(sparePartInfo.getPartName())){
                throw new SystemException("xxx","备品备件名称已存在");
            }
            FactoryInfo factoryInfo=factoryDAO.SelectFactoryInfoByruid(saveSPartInfoReq02.getFaRd());
            if(factoryInfo!=null){
                sparePartInfo.setFaGd(factoryInfo.getGuid());
            }else{
                sparePartInfo.setFaGd("");
            }

            SupplierInfo supplierInfo=supplierDAO.SelectBySuppId(saveSPartInfoReq02.getSupRd());
            if(supplierInfo!=null){
                sparePartInfo.setSupplierGd(supplierInfo.getGuid());
            }
            sparePartInfo.setVenderSN(saveSPartInfoReq02.getVenderSN());
            sparePartInfo.setPartName(saveSPartInfoReq02.getSPartName());
            sparePartInfo.setRemark(saveSPartInfoReq02.getRemark());
            sparePartInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
            sparePartInfo.setLastModifyTime(new Date());
            if(sparePartDAO.UpdateSparePartInfo(sparePartInfo)<=0){
                throw new SystemException("xxx","更新失败");
            }

        }
        baseRes.setBody(baseResB);
        return baseRes;
    }
}
