package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllExpandInfo.GetAllExpandInfoRes;
import pnc.mesadmin.dto.GetAllExpandInfo.GetAllExpandInfoResB;
import pnc.mesadmin.dto.GetAllExpandInfo.GetAllExpandInfoResD;
import pnc.mesadmin.dto.GetDWExpandInfo.GetDWExpandInfoReq00;
import pnc.mesadmin.dto.GetDWExpandInfo.GetDWExpandInfoRes;
import pnc.mesadmin.dto.GetDWExpandInfo.GetDWExpandInfoResB;
import pnc.mesadmin.dto.GetDWExpandInfo.GetDWExpandInfoResD;
import pnc.mesadmin.dto.GetExpandFieldInfo.GetExpandFieldInfoReq00;
import pnc.mesadmin.dto.GetExpandFieldInfo.GetExpandFieldInfoRes;
import pnc.mesadmin.dto.GetExpandFieldInfo.GetExpandFieldInfoResB;
import pnc.mesadmin.dto.GetExpandFieldInfo.GetExpandFieldInfoResD;
import pnc.mesadmin.dto.GetExpandInfo.*;
import pnc.mesadmin.dto.SaveExpandInfo.*;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.ExpandIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/3 15:39
 * @Description:
 */
@Transactional
@Service
public class ExpandService implements ExpandIService {
    @Resource
    private ExpandDAO expandDAO;

    @Resource
    private BaseIService baseIService;

    @Resource
    private ExpandFieldDAO expandFieldDAO;

    @Resource
    private CusDataCDAO cusDataCDAO;

    @Resource
    private ExpandDtlDAO expandDtlDAO;

    @Resource
    private CusDataDtlCDAO cusDataDtlCDAO;

    //查询列表
    @Override
    public GetAllExpandInfoRes QALLGetAllExpandInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllExpandInfoRes getAllExpandInfoRes=new GetAllExpandInfoRes();
        GetAllExpandInfoResB getAllExpandInfoResB=new GetAllExpandInfoResB();
        List<GetAllExpandInfoResD> getAllExpandInfoResDS=new ArrayList<GetAllExpandInfoResD>();

        List<ExpandInfo> expandInfos =baseIService.QALLBaseInfo(ExpandDAO.class,"SelectAllExpandCInfo",argInitDataFields,argPageInfo);
        if(expandInfos !=null&& expandInfos.size()>0){
            for(ExpandInfo expandInfo : expandInfos){
                GetAllExpandInfoResD getAllExpandInfoResD=new GetAllExpandInfoResD();
                getAllExpandInfoResD.setExpandRd(expandInfo.getRuid());
                getAllExpandInfoResD.setExpandType(expandInfo.getExpandType());
                getAllExpandInfoResD.setExpandName(expandInfo.getExpandName());
                getAllExpandInfoResDS.add(getAllExpandInfoResD);
            }
        }

        getAllExpandInfoResB.setData(getAllExpandInfoResDS);
        getAllExpandInfoRes.setBody(getAllExpandInfoResB);
        return getAllExpandInfoRes;
    }

    /**
     * 获取物料扩展字段列表(新)
     * @param req
     * @return
     */
    @Override
    public PageResult<GetAllExpandInfoResD> QALLExpandNewRes(BaseRequest req) {
        List<GetAllExpandInfoResD> resDList = new ArrayList<GetAllExpandInfoResD>();
        GetAllExpandInfoResD resD = null;

        Page<ExpandInfo> page = new Page<>(req.getCurrent(), req.getSize() <= 0 ? 1000 : req.getSize());

        IPage<ExpandInfo> iPage = expandDAO.selectPage(page, CommonUtils.getQueryWrapper(req.getFields()));

        for(ExpandInfo expandInfo : iPage.getRecords()){
            resD = new GetAllExpandInfoResD();
            resD.setExpandRd(expandInfo.getRuid());
            resD.setExpandType(expandInfo.getExpandType());
            resD.setExpandName(expandInfo.getExpandName());
            resDList.add(resD);
        }

        return new PageResult<>(iPage, resDList);
    }

    //保存
    @Override
    public SaveExpandInfoRes AddSaveExpandInfoRes(SaveExpandInfoReq00 saveExpandInfoReq00) {
        SaveExpandInfoRes saveExpandInfoRes=new SaveExpandInfoRes();
        SaveExpandInfoResB saveExpandInfoResB=new SaveExpandInfoResB();
        if(saveExpandInfoReq00.getExpandType()==null||"".equals(saveExpandInfoReq00.getExpandType())){
            throw new SystemException("xxx","新增失败，扩展字段类型不能为空");
        }
        if(saveExpandInfoReq00.getExpandName()==null||saveExpandInfoReq00.getExpandName().equals("")){
            throw new SystemException("xxx","新增失败，扩展字段名称不能为空");
        }
        if(saveExpandInfoReq00.getIsSettleObj()==null||saveExpandInfoReq00.getIsSettleObj().equals("")){
            throw new SystemException("xxx","新增失败，直接应用到对象不能为空");
        }

        if(saveExpandInfoReq00.getExpandDtl().size()<=0){
            throw new SystemException("xxx","新增失败，扩展明细不能为空");
        }
        ExpandInfo expandInfo1=expandDAO.selectExpandInfoByExpandName(saveExpandInfoReq00.getExpandName());
        if(expandInfo1!=null){
            throw new SystemException("xxx","新增失败，名称已存在");
        }
        if("00".equals(saveExpandInfoReq00.getExpandType())){
            if("00".equals(saveExpandInfoReq00.getIsSettleObj())){
                ExpandInfo expandInfo2=expandDAO.selectExpandInfoByExpandTypeAndIsSettleObj(saveExpandInfoReq00.getExpandType());
                if(expandInfo2!=null){
                    throw new SystemException("xxx","扩展对象为物料信息时，最多只能有一个直接应用对象");
                }
            }

        }else {
            if("00".equals(saveExpandInfoReq00.getIsSettleObj())){
                ExpandInfo expand=expandDAO.selectExpandInfoByExpandTypeAndIsSettleObj(saveExpandInfoReq00.getExpandType());
                if(expand!=null){
                    throw new SystemException("xxx","扩展对象为设备信息时，最多只能有一个直接应用对象");
                }
            }

        }

        ExpandInfo expandInfo =new ExpandInfo();
        expandInfo.setGuid(CommonUtils.getRandomNumber());
        expandInfo.setExpandType(saveExpandInfoReq00.getExpandType());
        expandInfo.setRemark(saveExpandInfoReq00.getRemark());
        expandInfo.setCreateTime(new Date());
        expandInfo.setCreator((String) SecurityUtils.getSubject().getPrincipal());
        expandInfo.setExpandName(saveExpandInfoReq00.getExpandName());
        expandInfo.setIsSettleObj(saveExpandInfoReq00.getIsSettleObj());
        expandDAO.InsertExpandCInfo(expandInfo);

        for(SaveExpandInfoReq00ExpandDtl saveExpandInfoReq00ExpandDtl: saveExpandInfoReq00.getExpandDtl()){
            ExpandDtlInfo expandDtlInfo =new ExpandDtlInfo();
            expandDtlInfo.setGuid(CommonUtils.getRandomNumber());
            expandDtlInfo.setExpandGd(expandInfo.getGuid());
            expandDtlInfo.setFieldName(saveExpandInfoReq00ExpandDtl.getFieldName());
            expandDtlInfo.setFiledType(saveExpandInfoReq00ExpandDtl.getFiledType());
            expandDtlInfo.setDisplayName(saveExpandInfoReq00ExpandDtl.getDisplayName());
            expandDtlInfo.setCusData(saveExpandInfoReq00ExpandDtl.getCusData());
            if("01".equals(saveExpandInfoReq00ExpandDtl.getFiledType())){
                CusDataCInfo cusDataCInfo=cusDataCDAO.SelectCusDataCInfo(Integer.valueOf(saveExpandInfoReq00ExpandDtl.getCusData()));
                if(cusDataCInfo!=null){
                    expandDtlInfo.setCusData(cusDataCInfo.getGuid());
                }
            }else {
                expandDtlInfo.setCusData(saveExpandInfoReq00ExpandDtl.getCusData());
            }
            expandDtlInfo.setCreateTime(new Date());
            expandDtlInfo.setCreator((String) SecurityUtils.getSubject().getPrincipal());
            expandDtlDAO.InsertExpandDtlCInfo(expandDtlInfo);
        }

        saveExpandInfoRes.setBody(saveExpandInfoResB);
        return saveExpandInfoRes;
    }

    //删除
    @Override
    public SaveExpandInfoRes RmSaveExpandInfoRes(SaveExpandInfoReq01 saveExpandInfoReq01) {
        SaveExpandInfoRes saveExpandInfoRes=new SaveExpandInfoRes();
        SaveExpandInfoResB saveExpandInfoResB=new SaveExpandInfoResB();

        if(saveExpandInfoReq01.getExpandRd()<=0){
            throw new SystemException("xx","删除失败，该信息不能为空");
        }

        ExpandInfo expandCInf0= expandDAO.SelectExpandCInfoByRuid(saveExpandInfoReq01.getExpandRd());
        if(expandCInf0==null){
            throw new SystemException("xx","删除失败，该信息不存在");
        }

        List<ExpandDtlInfo> expandDtlInfos = expandDtlDAO.SelectExpandDtlCInfoByExpandGd(expandCInf0.getGuid());
        if(expandDtlInfos !=null&& expandDtlInfos.size()>0){
            for(ExpandDtlInfo expandDtlInfo : expandDtlInfos){
                if(expandDtlDAO.DeleteExpandDtlCInfo(expandDtlInfo.getRuid())<=0){
                    throw new SystemException("xx","删除细表信息失败");
                }
            }
            if(expandDAO.DeleteExpandCInfo(saveExpandInfoReq01.getExpandRd())<=0){
                throw new SystemException("xx","删除主表信息失败");
            }
        }


        saveExpandInfoRes.setBody(saveExpandInfoResB);
        return saveExpandInfoRes;
    }

    //修改
    @Override
    public SaveExpandInfoRes ModSaveExpandInfoRes(SaveExpandInfoReq02 saveExpandInfoReq02) {
        boolean flag=false;
        SaveExpandInfoRes saveExpandInfoRes=new SaveExpandInfoRes();
        SaveExpandInfoResB saveExpandInfoResB=new SaveExpandInfoResB();

        if(saveExpandInfoReq02.getExpandRd()<=0){
            throw new SystemException("xx","修改失败，该信息不能为空");
        }
        if(saveExpandInfoReq02.getExpandName()==null||saveExpandInfoReq02.getExpandName().equals("")){
            throw new SystemException("xxx","修改失败，扩展字段名称不能为空");
        }
        if(saveExpandInfoReq02.getIsSettleObj()==null||saveExpandInfoReq02.getIsSettleObj().equals("")){
            throw new SystemException("xxx","修改失败，直接应用到对象不能为空");
        }

        ExpandInfo expandCInf0= expandDAO.SelectExpandCInfoByRuid(saveExpandInfoReq02.getExpandRd());
        if(expandCInf0==null){
            throw new SystemException("xx","修改失败，该信息不存在");
        }

        ExpandInfo expandInfo2=expandDAO.selectExpandInfoByExpandName(saveExpandInfoReq02.getExpandName());
        if(expandInfo2!=null&&!expandCInf0.getExpandName().equals(expandInfo2.getExpandName())){
            throw new SystemException("xxx","修改失败，名称已存在");
        }

        if(saveExpandInfoReq02.getExpandDtl().size()<=0){
            throw new SystemException("xxx","新增失败，扩展明细不能为空");
        }
        if("00".equals(saveExpandInfoReq02.getExpandType())){
            if("00".equals(saveExpandInfoReq02.getIsSettleObj())){
                ExpandInfo expandInfo3=expandDAO.selectExpandInfoByExpandTypeAndIsSettleObj(saveExpandInfoReq02.getExpandType());
                if(expandInfo3!=null&&!expandInfo3.getExpandName().equals(expandCInf0.getExpandName())){
                    throw new SystemException("xxx","扩展对象为物料信息时，最多只能有一个直接应用对象");
                }
            }

        }else {
            if("00".equals(saveExpandInfoReq02.getIsSettleObj())){
                ExpandInfo expand=expandDAO.selectExpandInfoByExpandTypeAndIsSettleObj(saveExpandInfoReq02.getExpandType());
                if(expand!=null&&!expand.getExpandName().equals(expandCInf0.getExpandName())){
                    throw new SystemException("xxx","扩展对象为设备信息时，最多只能有一个直接应用对象");
                }
            }

        }

        List<ExpandDtlInfo> expandDtlInfos = expandDtlDAO.SelectExpandDtlCInfoByExpandGd(expandCInf0.getGuid());
        if(expandDtlInfos !=null&& expandDtlInfos.size()>0){
            for(ExpandDtlInfo expandDtlInfo : expandDtlInfos){
                if(expandDtlDAO.DeleteExpandDtlCInfo(expandDtlInfo.getRuid())<=0){
                    throw new SystemException("xx","删除细表信息失败");
                }
            }

            for(SaveExpandInfoReq02ExpandDtl saveExpandInfoReq02ExpandDtl: saveExpandInfoReq02.getExpandDtl()){
                ExpandDtlInfo expandDtlInfo =new ExpandDtlInfo();
                expandDtlInfo.setGuid(CommonUtils.getRandomNumber());
                expandDtlInfo.setExpandGd(expandCInf0.getGuid());
                expandDtlInfo.setFieldName(saveExpandInfoReq02ExpandDtl.getFieldName());
                expandDtlInfo.setFiledType(saveExpandInfoReq02ExpandDtl.getFiledType());
                expandDtlInfo.setDisplayName(saveExpandInfoReq02ExpandDtl.getDisplayName());
                expandDtlInfo.setCusData(saveExpandInfoReq02ExpandDtl.getCusData());
                if("01".equals(saveExpandInfoReq02ExpandDtl.getFiledType())){
                    CusDataCInfo cusDataCInfo=cusDataCDAO.SelectCusDataCInfo(Integer.valueOf(saveExpandInfoReq02ExpandDtl.getCusData()));
                    if(cusDataCInfo!=null){
                        expandDtlInfo.setCusData(cusDataCInfo.getGuid());
                    }
                }else {
                    expandDtlInfo.setCusData(saveExpandInfoReq02ExpandDtl.getCusData());
                }
                expandDtlInfo.setCreateTime(new Date());
                expandDtlInfo.setCreator((String) SecurityUtils.getSubject().getPrincipal());
                expandDtlDAO.InsertExpandDtlCInfo(expandDtlInfo);
            }
        }
        expandCInf0.setExpandType(saveExpandInfoReq02.getExpandType());
        expandCInf0.setLastModifyMan(CommonUtils.readUser().getRealName());
        expandCInf0.setExpandName(saveExpandInfoReq02.getExpandName());
        expandCInf0.setIsSettleObj(saveExpandInfoReq02.getIsSettleObj());
        expandCInf0.setLastModifyTime(new Date());
        expandCInf0.setRemark(saveExpandInfoReq02.getRemark());
       if(expandDAO.UpdateExpandCInfo(expandCInf0)<=0){
           throw new SystemException("xx","更新主表信息失败");
       }
        saveExpandInfoRes.setBody(saveExpandInfoResB);
        return saveExpandInfoRes;
    }

    //单个
    @Override
    public GetExpandInfoRes GetGetExpandInfoRes(GetExpandInfoReq00 getExpandInfoReq00) {
        GetExpandInfoRes getExpandInfoRes=new GetExpandInfoRes();
        GetExpandInfoResB getExpandInfoResB=new GetExpandInfoResB();
        GetExpandInfoResD getExpandInfoResD=new GetExpandInfoResD();
        if(getExpandInfoReq00.getExpandRd()<=0||"".equals(getExpandInfoReq00.getExpandRd())){
            throw new SystemException("xx","查询失败，该信息不能为空");
        }
        List<GetExpandInfoResDExpandDtl> getExpandInfoResDExpandDtls=new ArrayList<GetExpandInfoResDExpandDtl>();

        ExpandInfo expandCInf0= expandDAO.SelectExpandCInfoByRuid(getExpandInfoReq00.getExpandRd());
        if(expandCInf0!=null){
            List<ExpandDtlInfo> expandDtlInfos = expandDtlDAO.SelectExpandDtlCInfoByExpandGd(expandCInf0.getGuid());
            if(expandDtlInfos !=null&& expandDtlInfos.size()>0){
                for(ExpandDtlInfo expandDtlInfo : expandDtlInfos){
                    GetExpandInfoResDExpandDtl getExpandInfoResDExpandDtl=new GetExpandInfoResDExpandDtl();
                    getExpandInfoResDExpandDtl.setExpandDtlRd(expandDtlInfo.getRuid());
                    if("01".equals(expandDtlInfo.getFiledType())){
                        getExpandInfoResDExpandDtl.setFiledType(expandDtlInfo.getFiledType());
                        CusDataCInfo cusDataCInfo=cusDataCDAO.SelectCusDataCInfoByGuid(expandDtlInfo.getCusData());
                        if(cusDataCInfo!=null){
                            getExpandInfoResDExpandDtl.setCusData(cusDataCInfo.getCusDataName());
                        }
                    }else {
                        getExpandInfoResDExpandDtl.setFiledType(expandDtlInfo.getFiledType());
                        getExpandInfoResDExpandDtl.setCusData(expandDtlInfo.getCusData());
                    }
                    getExpandInfoResDExpandDtl.setFieldName(expandDtlInfo.getFieldName());
                    getExpandInfoResDExpandDtl.setDisplayName(expandDtlInfo.getDisplayName());
                    getExpandInfoResDExpandDtls.add(getExpandInfoResDExpandDtl);
                }
            }
            getExpandInfoResD.setCreator(expandCInf0.getCreator());
            getExpandInfoResD.setCreateTime(DateUtil.format(expandCInf0.getCreateTime()));
            getExpandInfoResD.setLastModifyMan(expandCInf0.getLastModifyMan());
            getExpandInfoResD.setLastModifyTime(DateUtil.format(expandCInf0.getLastModifyTime()));
            getExpandInfoResD.setRemark(expandCInf0.getRemark());
            getExpandInfoResD.setExpandRd(expandCInf0.getRuid());
            getExpandInfoResD.setExpandType(expandCInf0.getExpandType());
            getExpandInfoResD.setIsSettleObj(expandCInf0.getIsSettleObj());
            getExpandInfoResD.setExpandName(expandCInf0.getExpandName());
        }


        getExpandInfoResD.setExpandDtl(getExpandInfoResDExpandDtls);
        getExpandInfoResB.setData(getExpandInfoResD);
        getExpandInfoRes.setBody(getExpandInfoResB);
        return getExpandInfoRes;
    }

    //获取物料扩展字段对象信息
    @Override
    public GetDWExpandInfoRes GetDWExpandInfo(GetDWExpandInfoReq00 getDWExpandInfoReq00) {
        GetDWExpandInfoRes getDWExpandInfoRes = new GetDWExpandInfoRes();
        GetDWExpandInfoResB getDWExpandInfoResB=new GetDWExpandInfoResB();
        List<GetDWExpandInfoResD> getDWExpandInfoResD=new ArrayList<GetDWExpandInfoResD>();
        if(getDWExpandInfoReq00.getMTRd()==null||"".equals(getDWExpandInfoReq00.getMTRd())){
            throw new SystemException("xx","查询失败，该信息不能为空");
        }
        List <ExpandInfo> expandCInfs=new ArrayList<>();
        if(getDWExpandInfoReq00.getMTRd().equals("00")||getDWExpandInfoReq00.getMTRd().equals("01")
                ||getDWExpandInfoReq00.getMTRd().equals("02")||getDWExpandInfoReq00.getMTRd().equals("03")){
            expandCInfs= expandDAO.SelectAllExpandCInfoByExpandType("00","00");
        }else {
            expandCInfs=expandDAO.SelectAllMaRuid(Integer.parseInt(getDWExpandInfoReq00.getMTRd()));
            if(expandCInfs==null||expandCInfs.size()<=0){
                expandCInfs= expandDAO.SelectAllExpandCInfoByExpandType("00","00");
            }

        }

        for(ExpandInfo expandCInf0:expandCInfs){
            List<ExpandDtlInfo> expandDtlInfos = expandDtlDAO.SelectExpandDtlCInfoByExpandGd(expandCInf0.getGuid());
            for(ExpandDtlInfo expandDtlInfo : expandDtlInfos){
                GetDWExpandInfoResD data=new GetDWExpandInfoResD();
                data.setFieldName(expandDtlInfo.getFieldName());
                data.setFiledType(expandDtlInfo.getFiledType());
                data.setDisplayName(expandDtlInfo.getDisplayName());
                if(expandDtlInfo.getFiledType().equals("00")){
                    data.setVal(expandDtlInfo.getCusData());
                }
                if(expandDtlInfo.getFiledType().equals("01")){
                    CusDataCInfo cusDataCInfo=cusDataCDAO.SelectCusDataCInfoByGuid(expandDtlInfo.getCusData());
                    List<CusDataDtlCInfo> cusDataDtlCInfos=cusDataDtlCDAO.selectCusData(cusDataCInfo.getGuid());
                    List<GetDWExpandInfoResD.Option> options=new ArrayList<GetDWExpandInfoResD.Option>();
                    for (CusDataDtlCInfo cusDataDtlCInfo:cusDataDtlCInfos){
                        GetDWExpandInfoResD.Option option=new GetDWExpandInfoResD.Option();
                        option.setDisplayName(cusDataDtlCInfo.getDisplayName());
                        option.setVal(cusDataDtlCInfo.getVal());
                        options.add(option);
                    }
                    data.setOption(options);
                }

                getDWExpandInfoResD.add(data);
            }

        }



        getDWExpandInfoResB.setData(getDWExpandInfoResD);
        getDWExpandInfoRes.setBody(getDWExpandInfoResB);
        return getDWExpandInfoRes;
    }
    //获取设备扩展字段对象信息
    @Override
    public GetDWExpandInfoRes GetDWExpandInfo01(GetDWExpandInfoReq00 getDWExpandInfoReq00) {
        GetDWExpandInfoRes getDWExpandInfoRes = new GetDWExpandInfoRes();
        GetDWExpandInfoResB getDWExpandInfoResB=new GetDWExpandInfoResB();
        List<GetDWExpandInfoResD> getDWExpandInfoResD=new ArrayList<GetDWExpandInfoResD>();

        List <ExpandInfo> expandCInfs=new ArrayList<>();

            expandCInfs= expandDAO.SelectAllExpandCInfoByExpandType("01","00");


        for(ExpandInfo expandCInf0:expandCInfs){
            List<ExpandDtlInfo> expandDtlInfos = expandDtlDAO.SelectExpandDtlCInfoByExpandGd(expandCInf0.getGuid());
            for(ExpandDtlInfo expandDtlInfo : expandDtlInfos){
                GetDWExpandInfoResD data=new GetDWExpandInfoResD();
                data.setFieldName(expandDtlInfo.getFieldName());
                data.setFiledType(expandDtlInfo.getFiledType());
                data.setDisplayName(expandDtlInfo.getDisplayName());
                if(expandDtlInfo.getFiledType().equals("00")){
                    data.setVal(expandDtlInfo.getCusData());
                }
                if(expandDtlInfo.getFiledType().equals("01")){
                    CusDataCInfo cusDataCInfo=cusDataCDAO.SelectCusDataCInfoByGuid(expandDtlInfo.getCusData());
                    List<CusDataDtlCInfo> cusDataDtlCInfos=cusDataDtlCDAO.selectCusData(cusDataCInfo.getGuid());
                    List<GetDWExpandInfoResD.Option> options=new ArrayList<GetDWExpandInfoResD.Option>();
                    for (CusDataDtlCInfo cusDataDtlCInfo:cusDataDtlCInfos){
                        GetDWExpandInfoResD.Option option=new GetDWExpandInfoResD.Option();
                        option.setDisplayName(cusDataDtlCInfo.getDisplayName());
                        option.setVal(cusDataDtlCInfo.getVal());
                        options.add(option);
                    }
                    data.setOption(options);
                }

                getDWExpandInfoResD.add(data);
            }

        }



        getDWExpandInfoResB.setData(getDWExpandInfoResD);
        getDWExpandInfoRes.setBody(getDWExpandInfoResB);
        return getDWExpandInfoRes;
    }


    //获取扩展字段 获取扩展字段 对象 字段 信息
    @Override
    public GetExpandFieldInfoRes GetGetExpandFieldInfoRes(GetExpandFieldInfoReq00 getExpandFieldInfoReq00) {
        GetExpandFieldInfoRes getExpandFieldInfoRes=new GetExpandFieldInfoRes();
        GetExpandFieldInfoResB getExpandFieldInfoResB=new GetExpandFieldInfoResB();

        List<GetExpandFieldInfoResD> getExpandFieldInfoResBS=new ArrayList<GetExpandFieldInfoResD>();

        List<ExpandFieldInfo> expandCInf0= expandFieldDAO.SelectExpandFieldInfoByExpandType(getExpandFieldInfoReq00.getExpandType());
        if(expandCInf0!=null&&expandCInf0.size()>0){
            for(ExpandFieldInfo expandFieldInfo:expandCInf0){
                GetExpandFieldInfoResD getExpandFieldInfoResD=new GetExpandFieldInfoResD();
                getExpandFieldInfoResD.setFieldName(expandFieldInfo.getFieldName());
                getExpandFieldInfoResBS.add(getExpandFieldInfoResD);
            }
        }
        getExpandFieldInfoResB.setData(getExpandFieldInfoResBS);
        getExpandFieldInfoRes.setBody(getExpandFieldInfoResB);
        return getExpandFieldInfoRes;
    }
}
