package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.WoTypeDAO;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllUnitInfo.GetAllUnitInfoResD;
import pnc.mesadmin.dto.GetAllWoTypeInfo.GetAllWoTypeInfoResB;
import pnc.mesadmin.dto.GetAllWoTypeInfo.GetAllWoTypeInfoResD;
import pnc.mesadmin.dto.GetWoTypeInfo.GetWoTypeInfoResB;
import pnc.mesadmin.dto.GetWoTypeInfo.GetWoTypeInfoResD;
import pnc.mesadmin.dto.SaveWoTypeInfo.SaveWoTypeInfoReqBD00;
import pnc.mesadmin.dto.SaveWoTypeInfo.SaveWoTypeInfoReqBD01;
import pnc.mesadmin.dto.SaveWoTypeInfo.SaveWoTypeInfoReqBD02;
import pnc.mesadmin.dto.SaveWoTypeInfo.SaveWoTypeInfoResB;
import pnc.mesadmin.entity.UnitInfo;
import pnc.mesadmin.entity.WoTypeInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.WoTypeIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：BOM管理业务信息逻辑
 * 创建人：ZC
 * 创建时间：2017-06-08
 * 修改人：
 * 修改时间：
 */
@Transactional
@Service
public class WoTypeService implements WoTypeIService {

    @Resource
    private WoTypeDAO woTypeDAO;

    @Resource
    private BaseIService baseIService;


    //获取工单类型列表
    public GetAllWoTypeInfoResB GetAllWTInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllWoTypeInfoResB objGetAllWoTypeInfoResB = new GetAllWoTypeInfoResB();

        GetAllWoTypeInfoResD objGetAllWoTypeInfoResD = null;

        List<GetAllWoTypeInfoResD> objGetAllWoTypeInfoResDs = new ArrayList<GetAllWoTypeInfoResD>(Collections.<GetAllWoTypeInfoResD>emptyList());
        //查询所有的工单类型
        List<WoTypeInfo> woTypeInfoList =  baseIService.QALLBaseInfo(WoTypeDAO.class, "SelectAllWoType",
                argInitDataFields, argPageInfo);
        if(woTypeInfoList!=null && woTypeInfoList.size()>0) {

            //赋值dto并返回
            for (WoTypeInfo wo : woTypeInfoList) {
                objGetAllWoTypeInfoResD = new GetAllWoTypeInfoResD();
                objGetAllWoTypeInfoResD.setWTRd(wo.getRuid());
                objGetAllWoTypeInfoResD.setWTName(wo.getWoTName());
                objGetAllWoTypeInfoResDs.add(objGetAllWoTypeInfoResD);
            }
        }
        objGetAllWoTypeInfoResB.setData(objGetAllWoTypeInfoResDs);
        objGetAllWoTypeInfoResB.setMsgCode("0x00000");
        objGetAllWoTypeInfoResB.setMsgDes("成功");
        return objGetAllWoTypeInfoResB;
    }

    @Override
    public PageResult<GetAllWoTypeInfoResD> QALLGetAllWTNewRes(BaseRequest req) {
        List<GetAllWoTypeInfoResD> objEGetAllWoTypeInfoResDs=new ArrayList<GetAllWoTypeInfoResD>();
        GetAllWoTypeInfoResD resD = null;

        Page<WoTypeInfo> page = new Page<>(req.getCurrent(), req.getSize());

        IPage<WoTypeInfo> iPage = woTypeDAO.selectPage(page, CommonUtils.getQueryWrapper(req.getFields()));

        //赋值dto并返回
        for (WoTypeInfo obj : iPage.getRecords()) {
            resD = new GetAllWoTypeInfoResD();
            resD.setWTRd(obj.getRuid());
            resD.setWTName(obj.getWoTName());
            objEGetAllWoTypeInfoResDs.add(resD);
        }
        return new PageResult(iPage.getTotal(), iPage.getPages(), iPage.getCurrent(), iPage.getSize(), objEGetAllWoTypeInfoResDs);
    }

    //获取工单类型信息
    public GetWoTypeInfoResB GetWTInfo(int wtRd) {
        GetWoTypeInfoResB objGetWoTypeInfoResB = new GetWoTypeInfoResB();
        GetWoTypeInfoResD objGetWoTypeInfoResD = new GetWoTypeInfoResD();

        //查询工单类型信息
        WoTypeInfo woTypeInfo = woTypeDAO.SelectWoTypeByRuid(wtRd);
        if(woTypeInfo!=null) {
            objGetWoTypeInfoResD.setWTRd(wtRd);
            objGetWoTypeInfoResD.setWTName(woTypeInfo.getWoTName());
            objGetWoTypeInfoResD.setCreator(woTypeInfo.getCreator());
            objGetWoTypeInfoResD.setCreateTime(DateUtil.format(woTypeInfo.getCreateTime()));
            objGetWoTypeInfoResD.setLastModifyMan(woTypeInfo.getLastModifyMan());
            objGetWoTypeInfoResD.setLastModifyTime(DateUtil.format(woTypeInfo.getLastModifyTime()));
            objGetWoTypeInfoResD.setRemark(woTypeInfo.getRemark());
        }

        objGetWoTypeInfoResB.setData(objGetWoTypeInfoResD);
        objGetWoTypeInfoResB.setMsgCode("0x00000");
        objGetWoTypeInfoResB.setMsgDes("成功");
        return objGetWoTypeInfoResB;
    }

    // 删除
    public SaveWoTypeInfoResB RmWoType(SaveWoTypeInfoReqBD01 busData01) {
        SaveWoTypeInfoResB objSaveWoTypeInfoResB = new SaveWoTypeInfoResB();
        if(woTypeDAO.DeleteWoType(busData01.getWTRd()) < 1){
            throw new SystemException("0x00001","删除失败");
        }
        objSaveWoTypeInfoResB.setMsgCode("0x00000");
        objSaveWoTypeInfoResB.setMsgDes("成功");
        return objSaveWoTypeInfoResB;
    }

    // 新增
    public SaveWoTypeInfoResB AddWoType(SaveWoTypeInfoReqBD00 busData00) {
        SaveWoTypeInfoResB objSaveWoTypeInfoResB = new SaveWoTypeInfoResB();
        // 名称是否重复？
        if(woTypeDAO.SelectWoTypeByName(busData00.getWTName()) > 0){
            throw new SystemException("0x00001","该名称已存在");
        }

        WoTypeInfo woTypeInfo = new WoTypeInfo();
        woTypeInfo.setGuid(CommonUtils.getRandomNumber());
        woTypeInfo.setWoTName(busData00.getWTName());
        woTypeInfo.setCreator(CommonUtils.readUser().getRealName());
        woTypeInfo.setCreateTime(new Date());
        woTypeInfo.setRemark(busData00.getRemark());
        if(woTypeDAO.InsertWoType(woTypeInfo) < 1){
            throw new SystemException("0x00001","新增失败");
        }
        objSaveWoTypeInfoResB.setMsgCode("0x00000");
        objSaveWoTypeInfoResB.setMsgDes("成功");
        return objSaveWoTypeInfoResB;
    }

    // 修改
    public SaveWoTypeInfoResB ModWoType(SaveWoTypeInfoReqBD02 busData02) {
        SaveWoTypeInfoResB objSaveWoTypeInfoResB = new SaveWoTypeInfoResB();
        // 名称是否重复？
        if(woTypeDAO.SelectWoTypeInfoByName(busData02.getWTName(),busData02.getWTRd()) > 0){
            throw new SystemException("0x00001","该名称已存在");
        }

        WoTypeInfo woTypeInfo = woTypeDAO.SelectWoTypeByRuid(busData02.getWTRd());
        woTypeInfo.setWoTName(busData02.getWTName());
        woTypeInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        woTypeInfo.setLastModifyTime(new Date());
        woTypeInfo.setRemark(busData02.getRemark());
        if(woTypeDAO.UpdateWoType(woTypeInfo) < 1){
            throw new SystemException("0x00001","修改失败");
        }
        objSaveWoTypeInfoResB.setMsgCode("0x00000");
        objSaveWoTypeInfoResB.setMsgDes("成功");
        return objSaveWoTypeInfoResB;
    }
}
