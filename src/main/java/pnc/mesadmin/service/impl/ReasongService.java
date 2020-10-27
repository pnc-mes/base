package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.ReasonDAO;
import pnc.mesadmin.dao.ReasongDAO;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllRCGInfo.GetAllRCGInfoResB;
import pnc.mesadmin.dto.GetAllRCGInfo.GetAllRCGInfoResD;
import pnc.mesadmin.dto.GetRCGInfo.GetRCGInfoResB;
import pnc.mesadmin.dto.GetRCGInfo.GetRCGInfoResD;
import pnc.mesadmin.dto.GetRCGInfo.GetRCGInfoResDRCInfo;
import pnc.mesadmin.dto.SaveRCGpInfo.*;
import pnc.mesadmin.entity.ReaCodeGdlInfo;
import pnc.mesadmin.entity.ReaCodeInfo;
import pnc.mesadmin.entity.ReasongInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.ReasongIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;
import pnc.mesadmin.utils.MyPage;

import javax.annotation.Resource;
import java.util.*;

@Transactional
@Service
public class ReasongService implements ReasongIService {
    @Resource
    private ReasongDAO reasongDAO;

    @Resource
    private ReasonDAO reasonDAO;

    @Resource
    private BaseIService baseIService;

    //查询所有的原因代码组列表
    public GetAllRCGInfoResB GetAllRCGInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllRCGInfoResB objEGetAllRCGInfoResB = new GetAllRCGInfoResB();
        List<GetAllRCGInfoResD> lists = new ArrayList<GetAllRCGInfoResD>(Collections.EMPTY_LIST);
        GetAllRCGInfoResD objEGetAllRCGInfoResD = null;
        List<ReasongInfo> list =  baseIService.QALLBaseInfo(ReasongDAO.class, "selectAllReasongInfo",
                argInitDataFields, argPageInfo);

        if(list.size() > 0 && list != null) {
            for (ReasongInfo reasonInfo : list) {
                objEGetAllRCGInfoResD = new GetAllRCGInfoResD();
                objEGetAllRCGInfoResD.setRCGRd(reasonInfo.getRuid());
                objEGetAllRCGInfoResD.setRCGpName(reasonInfo.getReaCGName());
                lists.add(objEGetAllRCGInfoResD);
            }
        }
        objEGetAllRCGInfoResB.setMsgCode("0x00000");
        objEGetAllRCGInfoResB.setMsgDes("成功");
        objEGetAllRCGInfoResB.setData(lists);
        return objEGetAllRCGInfoResB;

    }

    @Override
    public PageResult<GetAllRCGInfoResD> QALLReaInfoNew(BaseRequest request) {
        List<GetAllRCGInfoResD> resDList = new ArrayList<GetAllRCGInfoResD>();
        GetAllRCGInfoResD resD = null;

        IPage<ReasongInfo> iPage = reasongDAO.selectPage(new MyPage(request), CommonUtils.getQueryWrapper(request.getFields()));

        for (ReasongInfo obj : iPage.getRecords()) {
            resD = new GetAllRCGInfoResD();
            resD.setRCGRd(obj.getRuid());
            resD.setRCGpName(obj.getReaCGName());
            resDList.add(resD);
        }

        return new PageResult<>(iPage, resDList);
    }

    public GetRCGInfoResB GetRCGInfoByRuid(int argRuid) {
        GetRCGInfoResB objEGetRCGInfoResB = new GetRCGInfoResB();
        GetRCGInfoResD objEGetRCGInfoResD = new GetRCGInfoResD();
        List<GetRCGInfoResDRCInfo> objGetRCGInfoResDRCInfos = new ArrayList<GetRCGInfoResDRCInfo>(Collections.EMPTY_LIST);
        GetRCGInfoResDRCInfo objEGetRCGInfoResDRCInfo = null;

        // 根据ruid查询原因代码信息
        ReasongInfo reasongInfo = reasongDAO.SelectReasongInfoByRuid(argRuid);
        // 根据 原因代码组的Guid 查询原因代码明细列表
        List<ReaCodeGdlInfo> reaCodeGdlInfoList = reasongDAO.SelectReaCodeGdlInfoByRCGGd(reasongInfo.getGuid());

        if(reaCodeGdlInfoList.size() > 0 && reaCodeGdlInfoList != null) {
            for (ReaCodeGdlInfo reaCodeGdlInfo : reaCodeGdlInfoList) {
                objEGetRCGInfoResDRCInfo = new GetRCGInfoResDRCInfo();
                // 根据明细列表里面的原因代码gd查询原因代码信息
                ReaCodeInfo reaCodeInfo = reasonDAO.SelectReacodeInfoByguid(reaCodeGdlInfo.getReaCGd());
                objEGetRCGInfoResDRCInfo.setReaRd(reaCodeInfo.getRuid());
                objEGetRCGInfoResDRCInfo.setReaCode(reaCodeInfo.getReaCode());
                objEGetRCGInfoResDRCInfo.setReaDes(reaCodeInfo.getReaDes());
                objGetRCGInfoResDRCInfos.add(objEGetRCGInfoResDRCInfo);
            }
            objEGetRCGInfoResD.setRCInfo(objGetRCGInfoResDRCInfos);
        }
        objEGetRCGInfoResD.setRCGRd(reasongInfo.getRuid());
        objEGetRCGInfoResD.setRCGpName(reasongInfo.getReaCGName());
        objEGetRCGInfoResD.setCreator(reasongInfo.getCreator());
        objEGetRCGInfoResD.setCreateTime(DateUtil.format(reasongInfo.getCreateTime()));
        objEGetRCGInfoResD.setLastModifyMan(reasongInfo.getLastModifyMan());
        objEGetRCGInfoResD.setLastModifyTime(DateUtil.format(reasongInfo.getLastModifyTime()));
        objEGetRCGInfoResD.setRemark(reasongInfo.getRemark());
        objEGetRCGInfoResB.setData(objEGetRCGInfoResD);
        objEGetRCGInfoResB.setMsgCode("0x00000");
        objEGetRCGInfoResB.setMsgDes("成功");

        return objEGetRCGInfoResB;
    }

    // 新增
    public SaveRCGpInfoResB AddRCGpInfo(SaveRCGpInfoReq00 busData00) {
        SaveRCGpInfoResB objESaveRCGpInfoResB = new SaveRCGpInfoResB();
        if("".equals(busData00.getRCGpName())){
            throw new SystemException("0x00001","原因代码组名称不能为空");
        }
        //判断名称是否已经存在
        if(reasongDAO.SelectReasongGByName(busData00.getRCGpName()) != null){
            throw new SystemException("0x00001","原因代码组名称已存在");
        }

        if(!(busData00.getRCInfo().size() > 0 && busData00 != null)){
            throw new SystemException("0x00001","原因代码不能为空");
        }
        // 转换成原因代码组po对象进行插入数据
        ReasongInfo reasongInfo = new ReasongInfo();
        // 原因代码组Guid
        String reasongGGuid = CommonUtils.getRandomNumber();
        reasongInfo.setGuid(reasongGGuid);
        reasongInfo.setReaCGName(busData00.getRCGpName());
        reasongInfo.setRemark(busData00.getRemark());
        reasongInfo.setCreator(CommonUtils.readUser().getRealName());
        reasongInfo.setCreateTime(new Date());
        // 插入原因代码组
        if(reasongDAO.InsertReasongInfo(reasongInfo) <= 0){
            throw new SystemException("0x00001","新增失败");
        }

        Set<Integer> set = new HashSet<Integer>();

        for(int i=0;i<busData00.getRCInfo().size();i++){
            set.add(busData00.getRCInfo().get(i).getReaRd());
        }

        if(set.size() < busData00.getRCInfo().size()){
            throw new SystemException("MG_MES2001012010001F","原因代码信息重复,不能保存");
        }

        List<ReaCodeGdlInfo> reaCodeGdlInfoList = new ArrayList<ReaCodeGdlInfo>(Collections.EMPTY_LIST);
        ReaCodeGdlInfo reaCodeGdlInfo = null;
        int i=1;
        for(SaveRCGpInfoReq00RCInfo reaCodeInfo : busData00.getRCInfo()){

            ReaCodeInfo reaCodeInfo1 = reasonDAO.SelectReacodeInfoByruid(reaCodeInfo.getReaRd());
            if(reaCodeInfo1==null){
                throw new SystemException("xxx","新增失败，第:"+i+"行原因代码信息不存在");
            }
            reaCodeGdlInfo = new ReaCodeGdlInfo();
            reaCodeGdlInfo.setGuid(CommonUtils.getRandomNumber());
            reaCodeGdlInfo.setReaCGd(reaCodeInfo1.getGuid());
            reaCodeGdlInfo.setReaCGGd(reasongGGuid);
            reaCodeGdlInfo.setCreator(CommonUtils.readUser().getRealName());
            reaCodeGdlInfo.setCreateTime(new Date());
            reaCodeGdlInfoList.add(reaCodeGdlInfo);
            i++;

        }
        //插入明细表
        if(reasongDAO.InsertReaCodeDlInfos(reaCodeGdlInfoList) < reaCodeGdlInfoList.size()){
            throw new SystemException("0x00001","新增失败");
        }
        objESaveRCGpInfoResB.setMsgCode("0x00000");
        objESaveRCGpInfoResB.setMsgDes("成功");
        return objESaveRCGpInfoResB;
    }
    // 删除操作
    public SaveRCGpInfoResB RmRCGpInfo(SaveRCGpInfoReq01 busData01) {
        SaveRCGpInfoResB objESaveRCGpInfoResB = new SaveRCGpInfoResB();
        //1.0根据ruid查询代码组对象
        ReasongInfo reasongInfo = reasongDAO.SelectReasongInfoByRuid(busData01.getRCGRd());
        //1.1先删除明细,根据代码组的Guid
        if(reasongDAO.DeleteRCGpDlInfoByGuid(reasongInfo.getGuid()) <= 0) {
            throw new SystemException("0x00001","删除原因代码组明细失败");
        }
        //1.2再删除代码组
        if(reasongDAO.DeleteRCGpInfoByGuid(reasongInfo.getGuid()) <= 0) {
            throw new SystemException("0x00001","删除原因代码组失败");
        }
        objESaveRCGpInfoResB.setMsgCode("0x00000");
        objESaveRCGpInfoResB.setMsgDes("成功");
        return objESaveRCGpInfoResB;
    }

    // 修改操作
    public SaveRCGpInfoResB ModRCGpInfo(SaveRCGpInfoReq02 busData02) {
        SaveRCGpInfoResB objESaveRCGpInfoResB = new SaveRCGpInfoResB();
        ReasongInfo reasong = new ReasongInfo();
        reasong.setRuid(busData02.getRCGRd());
        reasong.setReaCGName(busData02.getRCGpName());
        int count = reasongDAO.SelectCountByName(reasong);
        if(count > 0){
            throw new SystemException("0x00001","名称不能重复");
        }

        ReasongInfo reasongInfo = reasongDAO.SelectReasongInfoByRuid(busData02.getRCGRd());
        reasongInfo.setReaCGName(busData02.getRCGpName());
        reasongInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        reasongInfo.setLastModifyTime(new Date());
        reasongInfo.setRemark(busData02.getRemark());
        //1.修改原因代码组信息
        if(reasongDAO.UpDateRCGpInfoByRuid(reasongInfo) <= 0){
            throw new SystemException("0x00001","修改原因代码组失败");
        }
        if(busData02.getRCInfo().size()<=0){
            throw new SystemException("xxxx","更新失败，明细不能为空");
        }
        //2.修改明细表
        //2.1 先删除关联的明细表
        if(reasongDAO.DeleteRCGpDlInfoByGuid(reasongInfo.getGuid()) <= 0) {
            throw new SystemException("0x00001","修改原因代码组明细失败");
        }

        Set<Integer> set = new HashSet<Integer>();

        for(int i=0;i<busData02.getRCInfo().size();i++){
            set.add(busData02.getRCInfo().get(i).getReaRd());
        }

        if(set.size() < busData02.getRCInfo().size()){
            throw new SystemException("MG_MES2001012010001F","原因代码信息重复,不能保存");
        }

        //2.2 再插入明细表
        List<ReaCodeGdlInfo> reaCodeGdlInfoList = new ArrayList<ReaCodeGdlInfo>(Collections.EMPTY_LIST);
        ReaCodeGdlInfo reaCodeGdlInfo = null;
        int i=1;
        for(SaveRCGpInfoReq00RCInfo rcInfo:busData02.getRCInfo()){
            ReaCodeInfo reaCodeInfo = reasonDAO.SelectReacodeInfoByruid(rcInfo.getReaRd());
            if(reaCodeInfo==null){
                throw new SystemException("xxx","修改失败，第:"+i+"行原因代码信息不存在");
            }
            reaCodeGdlInfo = new ReaCodeGdlInfo();
            reaCodeGdlInfo.setGuid(CommonUtils.getRandomNumber());
            reaCodeGdlInfo.setReaCGGd(reasongInfo.getGuid());
            reaCodeGdlInfo.setReaCGd(reaCodeInfo.getGuid());
            reaCodeGdlInfo.setCreator(CommonUtils.readUser().getRealName());
            reaCodeGdlInfo.setCreateTime(new Date());
            reaCodeGdlInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
            reaCodeGdlInfo.setLastModifyTime(new Date());
            reaCodeGdlInfoList.add(reaCodeGdlInfo);
            i++;
        }
        if(reasongDAO.InsertReaCodeDlInfos(reaCodeGdlInfoList) < reaCodeGdlInfoList.size()){
            throw new SystemException("0x00001","修改原因代码组明细失败");
        }
        objESaveRCGpInfoResB.setMsgCode("0x00000");
        objESaveRCGpInfoResB.setMsgDes("成功");
        return objESaveRCGpInfoResB;
    }
}
