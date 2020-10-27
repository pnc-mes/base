package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.ExpandDAO;
import pnc.mesadmin.dao.MaTypeDAO;
import pnc.mesadmin.dao.MaVerDAO;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllMTInfo.*;
import pnc.mesadmin.dto.GetMTInfo.GetMTInfoRes;
import pnc.mesadmin.dto.GetMTInfo.GetMTInfoResB;
import pnc.mesadmin.dto.GetMTInfo.GetMTInfoResD;
import pnc.mesadmin.dto.GetMTInfo.GetMTInfoResDExpand;
import pnc.mesadmin.dto.SaveMTInfo.*;
import pnc.mesadmin.entity.ExpandInfo;
import pnc.mesadmin.entity.MaTypeInfo;
import pnc.mesadmin.entity.MaVerInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.MaTypeIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：物料类别信息Service实现类
 * 创建人：刘福志
 * 创建时间：2017-8-21
 * 修改人：pjf
 * 修改时间：2020-09-10
 */
@Transactional
@Service
public class MaTypeService implements MaTypeIService{

    @Resource
    private MaTypeDAO maTypeDAO;

    @Resource
    private BaseIService baseIService;

    @Resource
    private ExpandDAO expandDAO;

    @Resource
    private MaVerDAO maVerDAO;

    //查询物料类别列表信息
    public GetAllMTInfoRes QALLselectAllMaTypeInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException {
        GetAllMTInfoRes objEGetAllMTInfoRes = new GetAllMTInfoRes();

        GetAllMTInfoResB body = new GetAllMTInfoResB();

        GetAllMTInfoResD data = new GetAllMTInfoResD();

        //查询物料类别所有信息
        List<MaTypeInfo> maTypeInfoList =  baseIService.QALLBaseInfo(MaTypeDAO.class, "SelectBypMaTGd",
                argInitDataFields, argPageInfo);
        if(maTypeInfoList!=null || maTypeInfoList.size()>0) {
            List<MaTypeInfo> list = new ArrayList<MaTypeInfo>();
            for(MaTypeInfo maTypeInfo : maTypeInfoList){
                if("".equals(maTypeInfo.getpMaTGd()))
                    list.add(maTypeInfo);
            }
            data.setCMTInfo(Selectchild(list));
        }
        body.setData(data);
        objEGetAllMTInfoRes.setBody(body);

        return objEGetAllMTInfoRes;
    }

    /**
     * 查询物料类别列表信息(新)
     * @param req
     * @return
     */
    @Override
    public PageResult<GetAllMTInfoResDCMTInfo> QALLMaTypeNew(BaseRequest req) {

        Page<MaTypeInfo> page = new Page<>(req.getCurrent(), req.getSize() <= 0 ? 1000 : req.getSize());

        //根据条件查询物料类别所有信息
        IPage<MaTypeInfo> iPage = maTypeDAO.selectPage(page, CommonUtils.getQueryWrapper(req.getFields()));

        List<MaTypeInfo> list = iPage.getRecords().stream().filter(o -> "".equals(o.getpMaTGd())).collect(Collectors.toList());

        return new PageResult<GetAllMTInfoResDCMTInfo>(iPage, Selectchild(list));
    }

    //递归查询子节点
    public  List<GetAllMTInfoResDCMTInfo> Selectchild(List<MaTypeInfo> maTypeInfos){
        List<GetAllMTInfoResDCMTInfo> dataList1 =  new ArrayList<GetAllMTInfoResDCMTInfo>();
        GetAllMTInfoResDCMTInfo objEGetAllMTInfoResDCMTInfo = null;

        for(int i = 0; i < maTypeInfos.size(); i++) {

            objEGetAllMTInfoResDCMTInfo = new GetAllMTInfoResDCMTInfo();

            //通过gd查询物料类别
            List<MaTypeInfo> objEMaTypeInfo = maTypeDAO.SelectByGuid(maTypeInfos.get(i).getGuid());

            if (objEMaTypeInfo.size() > 0) {
                objEGetAllMTInfoResDCMTInfo.setCMTInfo(Selectchild(objEMaTypeInfo));
            }else{
                objEGetAllMTInfoResDCMTInfo.setCMTInfo(new ArrayList<GetAllMTInfoResDCMTInfo>());
            }

            objEGetAllMTInfoResDCMTInfo.setMTRd(maTypeInfos.get(i).getRuid());
            objEGetAllMTInfoResDCMTInfo.setMTGd(maTypeInfos.get(i).getGuid());
            objEGetAllMTInfoResDCMTInfo.setMTName(maTypeInfos.get(i).getMaTName());
            objEGetAllMTInfoResDCMTInfo.setMTCode(maTypeInfos.get(i).getMaTCode());
            objEGetAllMTInfoResDCMTInfo.setMaType(maTypeInfos.get(i).getMaterialType());
            dataList1.add(objEGetAllMTInfoResDCMTInfo);
        }

        return dataList1;
    }

    //查询物料类别信息
    public GetMTInfoRes GetselectMaTypeInfo(int mTRd) throws SystemException {
        GetMTInfoRes objEGetMTInfoRes = new  GetMTInfoRes();

        GetMTInfoResB body = new GetMTInfoResB();

        GetMTInfoResD data = new GetMTInfoResD();

        // 获取物料类别信息
        MaTypeInfo maTypeInfo = maTypeDAO.SelectByRuid(mTRd);

        if(maTypeInfo != null) {
            // 赋值查询物料类别信息
            data.setMTRd(maTypeInfo.getRuid());
            data.setMTCode(maTypeInfo.getMaTCode());
            data.setMTName(maTypeInfo.getMaTName());
            GetMTInfoResDExpand getMTInfoResDExpand = new GetMTInfoResDExpand();
            ExpandInfo expandInfo = expandDAO.selectExpandInfoByExpandByGuid(maTypeInfo.getExpandGd());
            if(expandInfo != null){
                getMTInfoResDExpand.setExpandRd(expandInfo.getRuid());
                getMTInfoResDExpand.setExpandName(expandInfo.getExpandName());
            }
            MaTypeInfo maType = maTypeDAO.SelectGuid(maTypeInfo.getpMaTGd());
            if(maType != null){
                data.setPMTRd(maType.getRuid());
            }
            data.setExpandInfo(getMTInfoResDExpand);
            data.setMaterialType(maTypeInfo.getMaterialType());
            data.setDSource(maTypeInfo.getdSource());
            data.setCreator(maTypeInfo.getCreator());
            data.setCreateTime(DateUtil.format(maTypeInfo.getCreateTime()));
            data.setLastModifyMan(maTypeInfo.getLastModifyMan());
            data.setLastModifyTime(DateUtil.format(maTypeInfo.getLastModifyTime()));
            data.setRemark(maTypeInfo.getRemark());
        }

        body.setData(data);
        objEGetMTInfoRes.setBody(body);

        return objEGetMTInfoRes;
    }

    //新增物料类别信息
    public SaveMTInfoRes AddinsertMaTypeInfo(SaveMTInfoReqBD00 busData00, MaTypeInfo maTypeInfo) throws SystemException {
        SaveMTInfoRes saveMTInfoRes = new SaveMTInfoRes();

        SaveMTInfoResB body = new SaveMTInfoResB();

        SaveMTInfoResD data = new SaveMTInfoResD();

        if(busData00.getMTCode() == null || "".equals(busData00.getMTCode())){
            throw new SystemException("MG0003F","物料类别代码不能为空");
        }
        if (busData00.getMTCode() == null || "".equals(busData00.getMTCode())){
            throw new SystemException("xxxxxx","物料类别代码不能为空");
        }
        if ("".equals(busData00.getMTName())){
            throw new SystemException("MG0003F","物料类别名称不能为空");
        }

        //查询物料类别信息
        MaTypeInfo objeMaTypeInfos = maTypeDAO.SelectByRuid(busData00.getPMTRd());

        //赋值新增物料类别信息
        maTypeInfo.setGuid(CommonUtils.getRandomNumber());

        //逻辑校验保存的物料类别名称不能相同
        MaTypeInfo maTypeInf = maTypeDAO.SelectMaTypeInfoByMaTCode(busData00.getMTCode());
        if(maTypeInf != null){
            throw new SystemException("MG0006F", "物料类别代码已存在");
        }

        if(objeMaTypeInfos != null) {
            maTypeInfo.setpMaTGd(objeMaTypeInfos.getGuid());
            maTypeInfo.setMaterialType(objeMaTypeInfos.getMaterialType());
        }else{
            if(busData00.getMaType() == null || "".equals(busData00.getMaType())){
                throw new SystemException("","物料类型不能为空");
            }
            maTypeInfo.setpMaTGd("");
            maTypeInfo.setMaterialType(busData00.getMaType());
        }
        maTypeInfo.setMaTCode(busData00.getMTCode());
        maTypeInfo.setMaTName(busData00.getMTName());
        ExpandInfo expandInfo = expandDAO.SelectExpandCInfoByRuid(busData00.getExpandRd());
        if(expandInfo != null){
            maTypeInfo.setExpandGd(expandInfo.getGuid());
        }
        maTypeInfo.setdSource("01");
        maTypeInfo.setCreator(CommonUtils.readUser().getRealName());
        maTypeInfo.setCreateTime(new Date());
        maTypeInfo.setRemark(busData00.getRemark());

        // 调用物料类别sql保存
        maTypeDAO.InsertMaTypeInfo(maTypeInfo);

        body.setData(data);
        saveMTInfoRes.setBody(body);

        return saveMTInfoRes;
    }

    //更新物料类别信息
    public SaveMTInfoRes ModupdateMaTypeInfo(SaveMTInfoReqBD02 busData02, MaTypeInfo maTypeInfo) throws SystemException {
        SaveMTInfoRes saveMTInfoRes = new SaveMTInfoRes();

        SaveMTInfoResB body = new SaveMTInfoResB();

        SaveMTInfoResD data = new SaveMTInfoResD();

        if (busData02.getMTName() == null || "".equals(busData02.getMTName())){
            throw new SystemException("","物料类别名称不能为空");
        }
        if (busData02.getMTCode() == null || "".equals(busData02.getMTCode())){
            throw new SystemException("","物料类别代码不能为空");
        }

        MaTypeInfo objeMaTypeInfo = maTypeDAO.SelectByRuid(busData02.getMTRd());

        // 判断物料类别是否为空
        if (objeMaTypeInfo == null){
            throw new SystemException("MG_MES3001711010001F", "查询物料类别信息为空！");
        }
        if(objeMaTypeInfo.getMaterialType() == null || "".equals(objeMaTypeInfo.getMaterialType()) ){
            throw new SystemException("","查询物料类型不能为空");
        }
        MaTypeInfo maTypeInf = maTypeDAO.SelectMaTypeInfoByMaTCode(busData02.getMTCode());
        if(maTypeInf !=null && !maTypeInf.getMaTCode().equals(objeMaTypeInfo.getMaTCode())){
            throw new SystemException("MG0006F", "物料类别代码已存在");
        }

        if("00".equals(objeMaTypeInfo.getdSource())){
            throw new SystemException("","外部数据不能更新");
        }

        // 赋值更新物料类别信息
        maTypeInfo.setRuid(busData02.getMTRd());

        ExpandInfo expandInfo = expandDAO.SelectExpandCInfoByRuid(busData02.getExpandRd());
        if(expandInfo != null){
            maTypeInfo.setExpandGd(expandInfo.getGuid());
        }else {
            maTypeInfo.setExpandGd("");
        }

        //查询物料类别信息
        MaTypeInfo maType = maTypeDAO.SelectByRuid(busData02.getPMTRd());
        if(maType == null){
            maTypeInfo.setpMaTGd("");
            if(StringUtils.isNotBlank(busData02.getMaType())){
                maTypeInfo.setMaterialType(busData02.getMaType());
            }
        }else{
            maTypeInfo.setpMaTGd(maType.getGuid());
        }

        maTypeInfo.setMaTCode(busData02.getMTCode());
        maTypeInfo.setMaTName(busData02.getMTName());
        maTypeInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        maTypeInfo.setLastModifyTime(new Date());
        maTypeInfo.setRemark(busData02.getRemark());

        // 调用批次等级sql更新
        int count = maTypeDAO.UpdateMaTypeInfo(maTypeInfo);

        if(count <=0) throw new SystemException("MG_MES3001712030002F","更新物料类别信息失败！");

        body.setData(data);
        saveMTInfoRes.setBody(body);

        return saveMTInfoRes;
    }

    //删除物料类别信息
    public SaveMTInfoRes RmdeleteMaTypeInfo(int ruid) throws SystemException {
        SaveMTInfoRes saveMTInfoRes = new SaveMTInfoRes();

        SaveMTInfoResB body = new SaveMTInfoResB();

        SaveMTInfoResD data = new SaveMTInfoResD();

        MaTypeInfo maTypeInfo = maTypeDAO.SelectByRuid(ruid);

        //查询是否有子类
        List<MaTypeInfo> maTypeInfos = maTypeDAO.SelectByGuid(maTypeInfo.getGuid());
        if(maTypeInfos.size() > 0){
            throw new SystemException("", "该类型下面有子类,不允许删除");
        }

        //查询时候被物料引用
        if(maVerDAO.selectCount(new LambdaQueryWrapper<MaVerInfo>().eq(MaVerInfo::getMaTypeGd,  maTypeInfo.getGuid())) > 0){
            throw new SystemException("", "该类型已被使用,不允许删除");
        }

        //调用物料类别sql删除信息
        if(maTypeDAO.DeleteMaTypeInfo(ruid) <= 0) throw new SystemException("MG_MES3001712020001F","删除物料类别信息失败！");

        body.setData(data);
        saveMTInfoRes.setBody(body);

        return saveMTInfoRes;
    }

    public void re(int ruid){
        MaTypeInfo maTypeInfo =maTypeDAO.SelectByRuid(ruid);

        //通过gd查询物料类别
        List<MaTypeInfo> objEMaTypeInfo=maTypeDAO.SelectByGuid(maTypeInfo.getGuid());

        if(objEMaTypeInfo!=null && objEMaTypeInfo.size()>0) {
            for (int i = 0; i < objEMaTypeInfo.size(); i++) {
                re(objEMaTypeInfo.get(i).getRuid());
            }
        }

        //调用物料类别sql删除信息
        int count = maTypeDAO.DeleteMaTypeInfo(ruid);

        if(count <=0) throw new SystemException("MG_MES3001712020001F","删除物料类别信息失败！");
    }
}
