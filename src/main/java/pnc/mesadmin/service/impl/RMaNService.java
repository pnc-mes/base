package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.GetAllRMaNInfo.GetAllRMaNInfoRes;
import pnc.mesadmin.dto.GetAllRMaNInfo.GetAllRMaNInfoResB;
import pnc.mesadmin.dto.GetAllRMaNInfo.GetAllRMaNInfoResD;
import pnc.mesadmin.dto.GetRMaNInfo.*;
import pnc.mesadmin.dto.GetRMaNTotalInfo.GetRMaNTotalInfoReq00;
import pnc.mesadmin.dto.GetRMaNTotalInfo.GetRMaNTotalInfoRes;
import pnc.mesadmin.dto.GetRMaNTotalInfo.GetRMaNTotalInfoResB;
import pnc.mesadmin.dto.GetRMaNTotalInfo.GetRMaNTotalInfoResD;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveRMaNInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.GConfigIService;
import pnc.mesadmin.service.RMaNIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by PNC on 2017/9/26.
 */
@Transactional
@Service
public class RMaNService implements RMaNIService {

    @Resource
    private RMaNDAO rMaNDAO;

    @Resource
    private RMaNDtlDAO rMaNDtlDAO;

    @Resource
    private BaseIService baseIService;

    @Resource
    private MaVerDAO maVerDAO;

    @Resource
    private WODAO wodao;

    @Resource
    private BDAO bdao;

    @Resource
    private UnitDAO unitDAO;

    @Resource
    private RkTkDAO rkTkDAO;

    @Resource
    private CodeRuleDAO codeRuleDAO;

    @Resource
    private GConfigIService gConfigIService;

    //查询产成品通知单列表信息
    @Override
    public GetAllRMaNInfoRes QALLGetAllRMaNInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllRMaNInfoRes objEGetAllRMaNInfoRes=new GetAllRMaNInfoRes();
        GetAllRMaNInfoResB objEGetAllRMaNInfoResB=new GetAllRMaNInfoResB();
        List<GetAllRMaNInfoResD> objEGetAllRMaNInfoResDs=new ArrayList<GetAllRMaNInfoResD>();
        List<RMaNInfo> objERMaNInfo=
                baseIService.QALLBaseInfo(RMaNDAO.class, "SelectRMaNInfo",
                        argInitDataFields, argPageInfo);
        if(objERMaNInfo!=null&&objERMaNInfo.size()>0){
            for(RMaNInfo obj:objERMaNInfo){
                GetAllRMaNInfoResD objEGetAllRMaNInfoResD=new GetAllRMaNInfoResD();
                objEGetAllRMaNInfoResD.setRMaNRd(obj.getRuid());
                objEGetAllRMaNInfoResD.setRMaNCode(obj.getrMaNCode());
                objEGetAllRMaNInfoResDs.add(objEGetAllRMaNInfoResD);
            }
        }
        objEGetAllRMaNInfoResB.setData(objEGetAllRMaNInfoResDs);
        objEGetAllRMaNInfoRes.setBody(objEGetAllRMaNInfoResB);
        return objEGetAllRMaNInfoRes;
    }

    //查询产成品通知单信息
    @Override
    public GetRMaNInfoRes GetGetRMaNInfoRes(GetRMaNInfoReq00 argGetRMaNInfoReq00) {
        GetRMaNInfoRes objEGetRMaNInfoRes=new GetRMaNInfoRes();
        GetRMaNInfoResB objEGetRMaNInfoResB=new GetRMaNInfoResB();
        GetRMaNInfoResD objEGetRMaNInfoResD=new GetRMaNInfoResD();
        List<GetRMaNInfoResDRMaNDl> objEGetRMaNInfoResDRMaNDls=new ArrayList<GetRMaNInfoResDRMaNDl>();

        RMaNInfo rMaNInfo=rMaNDAO.SelectRMaNInfoByRuid(argGetRMaNInfoReq00.getRMaNRd());
        if(rMaNInfo!=null){
            objEGetRMaNInfoResD.setRMaNRd(rMaNInfo.getRuid());
            objEGetRMaNInfoResD.setRMaNCode(rMaNInfo.getrMaNCode());
            objEGetRMaNInfoResD.setAssCode(rMaNInfo.getAssCode());
            objEGetRMaNInfoResD.setAssSource(rMaNInfo.getAssSource());
            objEGetRMaNInfoResD.setExStatus(rMaNInfo.getExStatus());
            objEGetRMaNInfoResD.setSStatus(rMaNInfo.getsStatus());
            objEGetRMaNInfoResD.setCreator(rMaNInfo.getCreator());
            objEGetRMaNInfoResD.setCreateTime(DateUtil.formatPattern2(rMaNInfo.getCreateTime()));
            objEGetRMaNInfoResD.setLastModifyMan(rMaNInfo.getLastModifyMan());

            objEGetRMaNInfoResD.setLastModifyTime(DateUtil.formatPattern2(rMaNInfo.getLastModifyTime()));
            objEGetRMaNInfoResD.setRemark(rMaNInfo.getRemark());
            WoInfo woInfo=wodao.SelectWoInfoBywoCode(rMaNInfo.getAssCode());
            float sumnum=0.0f;
            if(woInfo!=null){
                List<BInfo> bInfos=bdao.SelectByWoGdANDStatusANDWFStatus(woInfo.getGuid());
                if(bInfos!=null&&bInfos.size()>0){
                    for(BInfo bInfo:bInfos){
                        sumnum+=bInfo.getCanNum();
                    }
                }
            }
            List<RMaNDtlInfo> rMaNDtlInfos=rMaNDtlDAO.SelectRMaNDtlInfoByRMaNGd(rMaNInfo.getGuid());
            if(rMaNDtlInfos!=null&&rMaNDtlInfos.size()>0){
                    for(RMaNDtlInfo obj:rMaNDtlInfos){
                        GetRMaNInfoResDRMaNDl objEGetRMaNInfoResDRMaNDl=new GetRMaNInfoResDRMaNDl();
                        objEGetRMaNInfoResDRMaNDl.setRMaNDtlRd(obj.getRuid());

                        MaVerInfo maVerInfo=maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                        if(maVerInfo!=null){
                            objEGetRMaNInfoResDRMaNDl.setMaVerRd(maVerInfo.getRuid());
                            objEGetRMaNInfoResDRMaNDl.setMaName(maVerInfo.getMaterialName());
                            objEGetRMaNInfoResDRMaNDl.setMaCode(maVerInfo.getMaterialCode());
                            //查询明细的物料描述
                            MaterialInfo materialInfo=maVerDAO.SelectMaverAndMater(maVerInfo.getMaGd());
                            if(materialInfo==null||"".equals(materialInfo)){
                                objEGetRMaNInfoResDRMaNDl.setMaDes("");
                            }else {
                                if(materialInfo.getMaterialDes()==null||"".equals(materialInfo.getMaterialDes())){
                                    objEGetRMaNInfoResDRMaNDl.setMaDes("");
                                }else {
                                    objEGetRMaNInfoResDRMaNDl.setMaDes(materialInfo.getMaterialDes());
                                }
                            }
                        }

                        //实入数量
                        objEGetRMaNInfoResDRMaNDl.setSRMaNNum(obj.getNum());

                        //应入数量
                        objEGetRMaNInfoResDRMaNDl.setRMaNNum(sumnum);
                        objEGetRMaNInfoResDRMaNDl.setUnitName(obj.getUnitName());
                        objEGetRMaNInfoResDRMaNDls.add(objEGetRMaNInfoResDRMaNDl);
                    }
            }

            objEGetRMaNInfoResD.setRMaNDlInfo(objEGetRMaNInfoResDRMaNDls);
        }
        objEGetRMaNInfoResB.setData(objEGetRMaNInfoResD);
        objEGetRMaNInfoRes.setBody(objEGetRMaNInfoResB);

        return objEGetRMaNInfoRes;
    }

    //新增产成品入库信息
    @Override
    public SaveRMaNInfoRes AddSaveRMaNInfoRes(SaveRMaNInfoReq00 argSaveRMaNInfoReq00) {
        SaveRMaNInfoRes objESaveRMaNInfoRes=new SaveRMaNInfoRes();
        SaveRMaNInfoResB objESaveRMaNInfoResB=new SaveRMaNInfoResB();
        SaveRMaNInfoResD objESaveRMaNInfoResD=new SaveRMaNInfoResD();

        if(argSaveRMaNInfoReq00.getAssCode()==null||"".equals(argSaveRMaNInfoReq00.getAssCode())){
            throw new SystemException("xx","新增失败，关联单号不能为空");
        }
        if(argSaveRMaNInfoReq00.getAssSource()==null||"".equals(argSaveRMaNInfoReq00.getAssSource())){
            throw new SystemException("xx","新增失败，关联单号来源不能为空");
        }
        if(argSaveRMaNInfoReq00.getRMaNDlInfo()==null||argSaveRMaNInfoReq00.getRMaNDlInfo().size()<=0){
            throw new SystemException("xx","新增失败，新增明细信息不能为空");
        }
        List<RMaNInfo> rMaNInfo1=rMaNDAO.SelectRMaNInfo();
        for(RMaNInfo oo:rMaNInfo1){
                    if(argSaveRMaNInfoReq00.getRetCode().equals(oo.getrMaNCode())){
                        throw new SystemException("xx","新增失败,该通知单号已存在");
                    }
        }
        RMaNInfo rMaNInfo=new RMaNInfo();
        rMaNInfo.setGuid(CommonUtils.getRandomNumber());
        //查询代码生成
        CodeRuleInfo codeRuleInfo=codeRuleDAO.SelectCodeRuleInfocodeType("08");
        String SCode="";
        if(!"".equals(argSaveRMaNInfoReq00.getRetCode())) {
            rMaNInfo.setrMaNCode(argSaveRMaNInfoReq00.getRetCode());
        }else if(codeRuleInfo!=null && "00".equals(codeRuleInfo.getStatus())){
            SCode=gConfigIService.GetCreateSC(codeRuleInfo);
            rMaNInfo.setrMaNCode(SCode);
        }else{
            throw new SystemException("", "请输入{入库通知单号}，或请到全局配置进行代码生成配置");
        }

        rMaNInfo.setAssCode(argSaveRMaNInfoReq00.getAssCode());
        rMaNInfo.setAssSource(argSaveRMaNInfoReq00.getAssSource());
        rMaNInfo.setExStatus("00");
        rMaNInfo.setsStatus("00");
        rMaNInfo.setdSource("01");
        rMaNInfo.setCreator(CommonUtils.readUser().getRealName());
        rMaNInfo.setCreateTime(new Date());
        rMaNDAO.InsertRMaNInfo(rMaNInfo);

        for(SaveRMaNInfoReq00RMaNDl obj:argSaveRMaNInfoReq00.getRMaNDlInfo()){
            if("".equals(obj.getMaVerRd())){
                throw new SystemException("xx","新增失败，物料版本不能为空");
            }
            if("".equals(obj.getSRMaNNum())){
                throw new SystemException("xx","新增失败，实入数量不能为空");
            }
            if(obj.getUnitName()==null||"".equals(obj.getUnitName())){
                throw new SystemException("xx","新增失败，单位信息不能为空");
            }
            RMaNDtlInfo rMaNDtlInfo=new RMaNDtlInfo();
            rMaNDtlInfo.setGuid(CommonUtils.getRandomNumber());
            rMaNDtlInfo.setrMaNGd(rMaNInfo.getGuid());
            if(!"".equals(argSaveRMaNInfoReq00.getRetCode())) {
                rMaNDtlInfo.setrMaNCode(argSaveRMaNInfoReq00.getRetCode());
            }else{
                rMaNDtlInfo.setrMaNCode(SCode);
            }
            MaVerInfo maVerInfo=maVerDAO.SelectByRuid(obj.getMaVerRd());
            if(maVerInfo!=null){
                rMaNDtlInfo.setMaVerGd(maVerInfo.getGuid());
            }
            rMaNDtlInfo.setNum(obj.getSRMaNNum());
            rMaNDtlInfo.setScanNum(0.0f);
            rMaNDtlInfo.setUnitName(obj.getUnitName());
            rMaNDtlInfo.setCreator(CommonUtils.readUser().getRealName());
            rMaNDtlInfo.setCreateTime(new Date());
            rMaNDtlDAO.InsertRMaNDtlInfo(rMaNDtlInfo);
        }

       /* RkTkInfo rkTkInfo=new RkTkInfo();
        rkTkInfo.setGuid(CommonUtils.getRandomNumber());
        rkTkInfo.setRkType("00");
        rkTkInfo.setRkCode("");
        rkTkInfo.setAssCode(argSaveRMaNInfoReq00.getRetCode());
        rkTkInfo.setAssSource("03");
        rkTkInfo.setExStatus("00");
        rkTkInfo.setCreator(CommonUtils.readUser().getRealName());
        rkTkInfo.setCreateTime(new Date());
        rkTkDAO.InsertRKTkInfo(rkTkInfo);
        RkTkInfo objERkTkInfos=rkTkDAO.SelectRKTkInfoByguid(rkTkInfo.getGuid());
        //获取左边设置为0
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        //设置最大整数位数
        nf.setMaximumIntegerDigits(10);
        //设置最小整数位数
        nf.setMinimumIntegerDigits(10);
        //获取当前的年月日字符串
        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] str= format.format(new Date()).substring(0, 10).split("-");
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < str.length; i++){
            sb. append(str[i]);
        }
        objERkTkInfos.setRkCode(("RK"+sb.toString()+nf.format(objERkTkInfos.getRuid())));
        if(rkTkDAO.UpdateRKTkInfo(objERkTkInfos)<=0){
            throw new SystemException("xxx","更细入库单位号失败");
        }*/

        RMaNInfo rMaNInfos=rMaNDAO.SelectRMaNInfoGuid(rMaNInfo.getGuid());

        objESaveRMaNInfoResD.setRMaNRd(rMaNInfos.getRuid());
        objESaveRMaNInfoResD.setRMaNCode(rMaNInfos.getrMaNCode());
        objESaveRMaNInfoResB.setData(objESaveRMaNInfoResD);
        objESaveRMaNInfoRes.setBody(objESaveRMaNInfoResB);
        return objESaveRMaNInfoRes;
    }

    //查询入库通知单汇总信息
    @Override
    public GetRMaNTotalInfoRes QALLGetRMaNTotalInfoRes(GetRMaNTotalInfoReq00 argGetRMaNTotalInfoReq00) {
        GetRMaNTotalInfoRes objEGetRMaNTotalInfoRes=new GetRMaNTotalInfoRes();
        GetRMaNTotalInfoResB objEGetRMaNTotalInfoResB=new GetRMaNTotalInfoResB();
        List<GetRMaNTotalInfoResD> objEGetRMaNTotalInfoResDs=new ArrayList<GetRMaNTotalInfoResD>();

        if(argGetRMaNTotalInfoReq00.getAssCode()==null||"".equals(argGetRMaNTotalInfoReq00.getAssCode())){
            throw new SystemException("xx","查询失败，关联单号不能为空");
        }
        if(argGetRMaNTotalInfoReq00.getAssSource()==null||"".equals(argGetRMaNTotalInfoReq00.getAssSource())){
            throw new SystemException("xx","查询失败，关联单号来源不能为空");
        }
        WoInfo woInfo=wodao.SelectWoInfoBywoCode(argGetRMaNTotalInfoReq00.getAssCode());
        float sumnum=0.0f;
        if(woInfo!=null){
            List<BInfo> bInfos=bdao.SelectByWoGdANDStatusANDWFStatus(woInfo.getGuid());
            if(bInfos!=null&&bInfos.size()>0){
                for(BInfo bInfo:bInfos){
                    sumnum+=bInfo.getCanNum();
                }
            }else {
                throw new SystemException("xx","汇总失败，统计数量失败");
            }
            GetRMaNTotalInfoResD objEGetRMaNTotalInfoResD=new GetRMaNTotalInfoResD();
            MaVerInfo maVerInfo=maVerDAO.SelectMaverInfo(woInfo.getMaVerGd());
            if(maVerInfo!=null){
                objEGetRMaNTotalInfoResD.setMaVerRd(maVerInfo.getRuid());
                objEGetRMaNTotalInfoResD.setMaCode(maVerInfo.getMaterialCode());
                objEGetRMaNTotalInfoResD.setMaName(maVerInfo.getMaterialName());
                //查询明细的物料描述
                MaterialInfo materialInfo=maVerDAO.SelectMaverAndMater(maVerInfo.getMaGd());
                if(materialInfo==null||"".equals(materialInfo)){
                    objEGetRMaNTotalInfoResD.setMaDes("");
                }else {
                    if(materialInfo.getMaterialDes()==null||"".equals(materialInfo.getMaterialDes())){
                        objEGetRMaNTotalInfoResD.setMaDes("");
                    }else {
                        objEGetRMaNTotalInfoResD.setMaDes(materialInfo.getMaterialDes());
                    }
                }

                objEGetRMaNTotalInfoResD.setRMaNNum(sumnum);
            }
            UnitInfo unitInfo=unitDAO.SelectByGuid(woInfo.getUnitGd());
            if(unitInfo!=null){
                objEGetRMaNTotalInfoResD.setUnitName(unitInfo.getUnitName());
            }
            objEGetRMaNTotalInfoResDs.add(objEGetRMaNTotalInfoResD);
        }
        objEGetRMaNTotalInfoResB.setData(objEGetRMaNTotalInfoResDs);
        objEGetRMaNTotalInfoRes.setBody(objEGetRMaNTotalInfoResB);
        return objEGetRMaNTotalInfoRes;
    }

    //删除入库通知单信息
    @Override
    public SaveRMaNInfoRes RmSaveRMaNInfoRes(SaveRMaNInfoReq01 argSaveRMaNInfoReq01) {
        SaveRMaNInfoRes objESaveRMaNInfoRes=new SaveRMaNInfoRes();
        SaveRMaNInfoResB objESaveRMaNInfoResB=new SaveRMaNInfoResB();

        RMaNInfo rMaNInfo=rMaNDAO.SelectRMaNInfoByRuid(argSaveRMaNInfoReq01.getRMaNRd());
        if("00".equals(rMaNInfo.getdSource())){
            throw new SystemException("","外部数据不能删除");
        }
        if(rMaNInfo!=null){
            if(!"00".equals(rMaNInfo.getExStatus()))
                throw new SystemException("","删除失败，此单子处于进行中、已完成、已取消中");
            List<RMaNDtlInfo> rMaNDtlInfos=rMaNDtlDAO.SelectRMaNDtlInfoByRMaNGd(rMaNInfo.getGuid());
            if(rMaNDtlInfos!=null&&rMaNDtlInfos.size()>0){
                for(RMaNDtlInfo obj:rMaNDtlInfos){
                    if(rMaNDtlDAO.DeleteRMaNDtlInfo(obj.getRuid())<=0){
                            throw new SystemException("xx","删除明细信息失败");
                    }
                }
            }
            if(rMaNDAO.DeleteRMaNInfo(rMaNInfo.getRuid())<=0){
                throw new SystemException("xx","删除信息失败");
            }
            RkTkInfo rkTkInfo=rkTkDAO.SelectByAssCode(rMaNInfo.getAssCode());
            if(rkTkInfo != null){
                if(rkTkDAO.DeleteRKTkInfo(rkTkInfo.getRuid())<=0){
                    throw new SystemException("xx","删除信息失败");
                }
            }


        }else {
            throw new SystemException("xx","删除失败，该信息不存在");
        }
        objESaveRMaNInfoRes.setBody(objESaveRMaNInfoResB);
        return objESaveRMaNInfoRes;
    }

    //更新入库通知单信息
    @Override
    public SaveRMaNInfoRes ModSaveRMaNInfoRes(SaveRMaNInfoReq02 argSaveRMaNInfoReq02) {
        SaveRMaNInfoRes objESaveRMaNInfoRes=new SaveRMaNInfoRes();
        SaveRMaNInfoResB objESaveRMaNInfoResB=new SaveRMaNInfoResB();

        RMaNInfo rMaNInfo=rMaNDAO.SelectRMaNInfoByRuid(argSaveRMaNInfoReq02.getRMaNRd());
        if("00".equals(rMaNInfo.getdSource())){
            throw new SystemException("","外部数据不能更新");
        }
        if(rMaNInfo!=null){
            if("02".equals(rMaNInfo.getExStatus()) || "03".equals(rMaNInfo.getExStatus()) || "01".equals(rMaNInfo.getsStatus()) || "02".equals(rMaNInfo.getsStatus()))
                throw new  SystemException("","修改失败，此单子处于已完成或者已下达、已取消状态");
            rMaNInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
            rMaNInfo.setLastModifyTime(new Date());
          //  List<RMaNDtlInfo> rMaNDtlInfos=rMaNDtlDAO.SelectRMaNDtlInfoByRMaNGd(rMaNInfo.getGuid());

            for(SaveRMaNInfoReq02RMaNDl obj:argSaveRMaNInfoReq02.getRMaNDlInfo()){
                if("".equals(obj.getMaVerRd())){
                    throw new SystemException("xx","更新失败，物料版本不能为空");
                }
                if("".equals(obj.getSRMaNNum())){
                    throw new SystemException("xx","更新失败，实入数量不能为空");
                }
                if(obj.getUnitName()==null||"".equals(obj.getUnitName())){
                    throw new SystemException("xx","更新失败，单位信息不能为空");
                }
                if("".equals(obj.getRMaNDtlRd())){
                    throw new SystemException("xx","更新失败，通知单明细不能为空");
                }
                RMaNDtlInfo objERMaNDtlInfo=new RMaNDtlInfo();
                objERMaNDtlInfo.setRuid(obj.getRMaNDtlRd());
                MaVerInfo maVerInfo=maVerDAO.SelectByRuid(obj.getMaVerRd());
                if(maVerInfo!=null){
                    objERMaNDtlInfo.setMaVerGd(maVerInfo.getGuid());
                }
                objERMaNDtlInfo.setUnitName(obj.getUnitName());
                objERMaNDtlInfo.setNum(obj.getSRMaNNum());
                if(rMaNDtlDAO.UpdateRMaNInfo(objERMaNDtlInfo)<=0){
                    throw new SystemException("xx","更新明细信息失败");
                }
            }
            if(rMaNDAO.UpdateRMaNInfo(rMaNInfo)<=0){
                throw new SystemException("xx","更新失败，该信息不存在");
            }
        }
        objESaveRMaNInfoRes.setBody(objESaveRMaNInfoResB);
        return objESaveRMaNInfoRes;
    }

    //下达
    @Override
    public SaveRMaNInfoRes ModSaveRMaNInfoRes03(SaveRMaNInfoReq03 argSaveRMaNInfoReq03) {
        SaveRMaNInfoRes objESaveRMaNInfoRes=new SaveRMaNInfoRes();
        SaveRMaNInfoResB objESaveRMaNInfoResB=new SaveRMaNInfoResB();

        RMaNInfo rMaNInfo=rMaNDAO.SelectRMaNInfoByRuid(argSaveRMaNInfoReq03.getRMaNRd());
        if("01".equals(rMaNInfo.getsStatus())){
            throw new SystemException("","产成品入库单已下达,不能重复下达");
        }
        if("02".equals(rMaNInfo.getsStatus())){
            throw new SystemException("","产成品入库单已取消,不能进行下达");
        }

        if(rMaNInfo!=null){
            RMaNInfo rMaNInfo1=new RMaNInfo();
            rMaNInfo1.setRuid(rMaNInfo.getRuid());
            rMaNInfo1.setsStatus("01");
            rMaNInfo1.setLastModifyTime(new Date());
            rMaNInfo1.setLastModifyMan(CommonUtils.readUser().getRealName());
            rMaNDAO.UpdateRMaNInfo(rMaNInfo1);

            //给入库任务表中的AssCodeGd赋值(修改新增入库任务单)
            RkTkInfo rkTkInfo=rkTkDAO.SelectByAssCode(rMaNInfo.getrMaNCode());
            if(rkTkInfo == null){
                rkTkInfo = new RkTkInfo();
                rkTkInfo.setGuid(CommonUtils.getRandomNumber());
                rkTkInfo.setRkType("00");
                rkTkInfo.setRkCode("RK");
                rkTkInfo.setAssCode(rMaNInfo.getrMaNCode());
                rkTkInfo.setAssCodeGd(rMaNInfo.getGuid());
                rkTkInfo.setAssSource("03");
                rkTkInfo.setExStatus("00");
                rkTkInfo.setSubmitStore("00");
                rkTkInfo.setCreator(CommonUtils.readUser().getRealName());
                rkTkInfo.setCreateTime(new Date());
                rkTkDAO.InsertRKTkInfo(rkTkInfo);
                RkTkInfo objERkTkInfos=rkTkDAO.SelectRKTkInfoByguid(rkTkInfo.getGuid());
                //获取左边设置为0
                NumberFormat nf = NumberFormat.getInstance();
                nf.setGroupingUsed(false);
                //设置最大整数位数
                nf.setMaximumIntegerDigits(10);
                //设置最小整数位数
                nf.setMinimumIntegerDigits(10);
                //获取当前的年月日字符串
                SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String[] str= format.format(new Date()).substring(0, 10).split("-");
                StringBuffer sb = new StringBuffer();
                for(int i = 0; i < str.length; i++){
                    sb. append(str[i]);
                }
                objERkTkInfos.setRkCode(("RK"+sb.toString()+nf.format(objERkTkInfos.getRuid())));
                if(rkTkDAO.UpdateRKTkInfo(objERkTkInfos)<=0){
                    throw new SystemException("xxx","更细入库单位号失败");
                }
            }else{
                rkTkInfo.setRuid(rkTkInfo.getRuid());
                rkTkInfo.setAssCodeGd(rMaNInfo.getGuid());
                rkTkDAO.UpdateRKTkInfo(rkTkInfo);
            }
        }

        objESaveRMaNInfoRes.setBody(objESaveRMaNInfoResB);
        return objESaveRMaNInfoRes;
    }

    //取消
    @Override
    public SaveRMaNInfoRes ModSaveRMaNInfoRes04(SaveRMaNInfoReq04 argSaveRMaNInfoReq04) {
        SaveRMaNInfoRes objESaveRMaNInfoRes=new SaveRMaNInfoRes();
        SaveRMaNInfoResB objESaveRMaNInfoResB=new SaveRMaNInfoResB();

        RMaNInfo rMaNInfo=rMaNDAO.SelectRMaNInfoByRuid(argSaveRMaNInfoReq04.getRMaNRd());
        if("00".equals(rMaNInfo.getsStatus())){
            throw new SystemException("","产成品入库单未下达，不能取消");
        }
        if("02".equals(rMaNInfo.getsStatus())){
            throw new SystemException("","产成品入库单已取消，不能重复取消");
        }

        if(rMaNInfo!=null && "01".equals(rMaNInfo.getsStatus())){
            RMaNInfo rMaNInfo1=new RMaNInfo();
            rMaNInfo1.setRuid(rMaNInfo.getRuid());
            rMaNInfo1.setsStatus("02");
            rMaNDAO.UpdateRMaNInfo(rMaNInfo1);

            if("00".equals(rMaNInfo.getExStatus())){
                List<RkTkInfo> objERkTkInfo=rkTkDAO.SelectByAssCodes(rMaNInfo.getrMaNCode());

                for(RkTkInfo obj:objERkTkInfo){
                    if(rMaNInfo.getrMaNCode().equals(obj.getAssCode()) && "03".equals(obj.getAssSource())){
                        RkTkInfo rkTkInfo=new RkTkInfo();

                        rkTkInfo.setRuid(obj.getRuid());
                        rkTkInfo.setExStatus("03");

                        rkTkDAO.UpdateRKTkInfo(rkTkInfo);
                    }
                }
            }
        }

        if(rMaNInfo!=null && "01".equals(rMaNInfo.getExStatus()) && "01".equals(rMaNInfo.getsStatus())){
            RMaNInfo rMaNInfo1=new RMaNInfo();
            rMaNInfo1.setRuid(rMaNInfo.getRuid());
            rMaNInfo1.setsStatus("02");
            rMaNInfo1.setExStatus("03");

            rMaNDAO.UpdateRMaNInfo(rMaNInfo1);

            List<RkTkInfo> objERkTkInfo=rkTkDAO.SelectByAssCodes(rMaNInfo.getrMaNCode());

            for(RkTkInfo obj:objERkTkInfo){
                if(rMaNInfo.getrMaNCode().equals(obj.getAssCode()) && "03".equals(obj.getAssSource())){
                    RkTkInfo rkTkInfo=new RkTkInfo();

                    rkTkInfo.setRuid(obj.getRuid());
                    rkTkInfo.setExStatus("03");

                    rkTkDAO.UpdateRKTkInfo(rkTkInfo);
                }
            }
        }

        objESaveRMaNInfoRes.setBody(objESaveRMaNInfoResB);
        return objESaveRMaNInfoRes;
    }
}
