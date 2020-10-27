package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.CoTypeDAO;
import pnc.mesadmin.dto.GetAllCTInfo.GetAllCTInfoRes;
import pnc.mesadmin.dto.GetAllCTInfo.GetAllCTInfoResB;
import pnc.mesadmin.dto.GetAllCTInfo.GetAllCTInfoResD;
import pnc.mesadmin.dto.GetCTInfo.GetCTInfoReq00;
import pnc.mesadmin.dto.GetCTInfo.GetCTInfoRes;
import pnc.mesadmin.dto.GetCTInfo.GetCTInfoResB;
import pnc.mesadmin.dto.GetCTInfo.GetCTInfoResD;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveCTInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.CoTypeInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.CoTypeIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/27 14:42
 * @Description:
 */
@Transactional
@Service
public class CoTypeService implements CoTypeIService {
    @Resource
    private CoTypeDAO coTypeDAO;

    @Resource
    private BaseIService baseIService;

    //查询所有
    @Override
    public GetAllCTInfoRes QALLGetAllCTInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllCTInfoRes getAllCTInfoRes=new GetAllCTInfoRes();
        GetAllCTInfoResB getAllCusInfoResB=new GetAllCTInfoResB();
        List<GetAllCTInfoResD> getAllCusInfoResDS=new ArrayList<GetAllCTInfoResD>();
        List<CoTypeInfo> coTypeInfos=baseIService.QALLBaseInfo(CoTypeDAO.class,"SelectAllCoTypeInfo",argInitDataFields,argPageInfo);
        if(coTypeInfos!=null&&coTypeInfos.size()>0){
                for(CoTypeInfo  i : coTypeInfos){
                    GetAllCTInfoResD getAllCusInfoResD=new  GetAllCTInfoResD();
                    getAllCusInfoResD.setCTRd(i.getRuid());
                    getAllCusInfoResD.setCTName(i.getCoTName());
                    getAllCusInfoResDS.add(getAllCusInfoResD);
                }
        }
        getAllCusInfoResB.setData(getAllCusInfoResDS);
        getAllCTInfoRes.setBody(getAllCusInfoResB);
        return getAllCTInfoRes;
    }

    //查询单个
    @Override
    public GetCTInfoRes GetGetCTInfoRes(GetCTInfoReq00 getCTInfoReq00) {
        GetCTInfoRes getCTInfoRes=new GetCTInfoRes();
        GetCTInfoResB getCTInfoResB=new GetCTInfoResB();
        GetCTInfoResD getCTInfoResD=new GetCTInfoResD();

        if(getCTInfoReq00.getCTRd()<=0){
            throw new SystemException("xxx","查询失败，订单类型标识不能为空");
        }

        CoTypeInfo coTypeInfo=coTypeDAO.SelectCoTypeInfoByRuid(getCTInfoReq00.getCTRd());
        if(coTypeInfo==null){
            throw new SystemException("xxx","查询失败，该信息不存在");
        }
        getCTInfoResD.setCTRd(coTypeInfo.getRuid());
        getCTInfoResD.setCTName(coTypeInfo.getCoTName());
        getCTInfoResD.setCreator(coTypeInfo.getCreator());
        getCTInfoResD.setCreateTime(DateUtil.format(coTypeInfo.getCreateTime()));
        getCTInfoResD.setLastModifyMan(coTypeInfo.getLastModifyMan());
        getCTInfoResD.setLastModifyTime(DateUtil.format(coTypeInfo.getLastModifyTime()));
        getCTInfoResD.setRemark(coTypeInfo.getRemark());

        getCTInfoRes.setBody(getCTInfoResB);
        getCTInfoResB.setData(getCTInfoResD);
        return getCTInfoRes;
    }

    //增加
    @Override
    public SaveCTInfoRes AddSaveCTInfoRes(SaveCTInfoReq00 saveCTInfoReq00) {
        SaveCTInfoRes saveCTInfoRes=new SaveCTInfoRes();
        SaveCTInfoResB saveCTInfoResB=new SaveCTInfoResB();
        if(saveCTInfoReq00.getCTName()==null||"".equals(saveCTInfoReq00.getCTName())){
            throw new SystemException("xx","新增失败，名称不能为空");
        }
        CoTypeInfo coTypeInfo=coTypeDAO.SelectCoTypeInfoByCoTName(saveCTInfoReq00.getCTName());
        if(coTypeInfo!=null){
            throw new SystemException("xx","新增失败，名称已存在");
        }

        CoTypeInfo coTypeInfo1=new CoTypeInfo();
        coTypeInfo1.setGuid(CommonUtils.getRandomNumber());
        coTypeInfo1.setCoTName(saveCTInfoReq00.getCTName());
        coTypeInfo1.setCreateTime(new Date());
        coTypeInfo1.setCreator(CommonUtils.readUser().getRealName());
        coTypeInfo1.setRemark(saveCTInfoReq00.getRemark());
        coTypeDAO.InsertCoTypeInfo(coTypeInfo1);

        saveCTInfoRes.setBody(saveCTInfoResB);
        return saveCTInfoRes;
    }

    //删除
    @Override
    public SaveCTInfoRes RmSaveCTInfoRes(SaveCTInfoReq01 saveCTInfoReq01) {
        SaveCTInfoRes saveCTInfoRes=new SaveCTInfoRes();
        SaveCTInfoResB saveCTInfoResB=new SaveCTInfoResB();
        if(saveCTInfoReq01.getCTRd()<=0){
            throw new SystemException("xxx","删除失败，订单类型标识不能为空");
        }

        CoTypeInfo coTypeInfo=coTypeDAO.SelectCoTypeInfoByRuid(saveCTInfoReq01.getCTRd());
        if(coTypeInfo==null){
            throw new SystemException("xxx","删除失败，该信息不存在");
        }
        if(coTypeDAO.DeleteCoTypeInfoByRuid(coTypeInfo.getRuid())<=0){
            throw new SystemException("xxx","删除失败，该信息不存在");
        }
        saveCTInfoRes.setBody(saveCTInfoResB);
        return saveCTInfoRes;
    }

    //修改
    @Override
    public SaveCTInfoRes ModSaveCTInfoRes(SaveCTInfoReq02 saveCTInfoReq02) {
        SaveCTInfoRes saveCTInfoRes=new SaveCTInfoRes();
        SaveCTInfoResB saveCTInfoResB=new SaveCTInfoResB();
        if(saveCTInfoReq02.getCTRd()<=0){
            throw new SystemException("xxx","修改失败，订单类型标识不能为空");
        }

        CoTypeInfo coTypeInfo=coTypeDAO.SelectCoTypeInfoByRuid(saveCTInfoReq02.getCTRd());
        if(coTypeInfo==null){
            throw new SystemException("xxx","修改失败，该信息不存在");
        }
        if(saveCTInfoReq02.getCTName()==null||"".equals(saveCTInfoReq02.getCTName())){
            throw new SystemException("xx","修改失败，名称不能为空");
        }

        CoTypeInfo coTypeInfo1=coTypeDAO.SelectCoTypeInfoByCoTName(saveCTInfoReq02.getCTName());
        if(coTypeInfo1!=null&&!coTypeInfo.getCoTName().equals(coTypeInfo1.getCoTName())){
            throw new SystemException("xx","修改失败，名称已存在");
        }
        coTypeInfo.setRemark(saveCTInfoReq02.getRemark());
        coTypeInfo.setCoTName(saveCTInfoReq02.getCTName());
        coTypeInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        coTypeInfo.setLastModifyTime(new Date());
        if(coTypeDAO.UpdateCoTypeInfo(coTypeInfo)<=0){
            throw new SystemException("xx","修改信息失败");
        }

        saveCTInfoRes.setBody(saveCTInfoResB);
        return saveCTInfoRes;
    }
}
