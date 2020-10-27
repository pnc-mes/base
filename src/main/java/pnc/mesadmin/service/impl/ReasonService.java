package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.ReasonDAO;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllReaInfo.GetAllReaInfoRes;
import pnc.mesadmin.dto.GetAllReaInfo.GetAllReaInfoResB;
import pnc.mesadmin.dto.GetAllReaInfo.GetAllReaInfoResD;
import pnc.mesadmin.dto.GetReaInfo.*;
import pnc.mesadmin.dto.GetReaTypeInfo.GetReaTypeInfoRes;
import pnc.mesadmin.dto.GetReaTypeInfo.GetReaTypeInfoResB;
import pnc.mesadmin.dto.GetReaTypeInfo.GetReaTypeInfoResD;
import pnc.mesadmin.dto.SaveReaInfo.*;
import pnc.mesadmin.entity.ReaCodeInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.ReasonIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;
import pnc.mesadmin.utils.MyPage;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：原因代码Service实现层
 * 创建人：张亮亮
 * 创建时间：2017-05-31
 * 修改人：
 * 修改时间：
 */
@Transactional
@Service
public class ReasonService implements ReasonIService {
    @Resource
    private ReasonDAO reasonDAO;

    @Resource
    private BaseIService baseIService;

    //dto获取原因类别信息
    @Override
    public GetReaTypeInfoRes QALLGetReaTypeInfoRes() {
        GetReaTypeInfoRes objEGetReaTypeInfoRes = new GetReaTypeInfoRes();
        GetReaTypeInfoResB objEGetReaTypeInfoResB = new GetReaTypeInfoResB();
        List<GetReaTypeInfoResD> objEGetReaTypeInfoResDs = new ArrayList<GetReaTypeInfoResD>();

        //查询所有的原因代码类别
        List<String> objEReacodeInfoReaType = new ArrayList<String>();

        objEReacodeInfoReaType.add("01");
        objEReacodeInfoReaType.add("02");
        objEReacodeInfoReaType.add("03");
        objEReacodeInfoReaType.add("04");
        objEReacodeInfoReaType.add("05");
        objEReacodeInfoReaType.add("06");
        objEReacodeInfoReaType.add("07");
        objEReacodeInfoReaType.add("10");
        objEReacodeInfoReaType.add("11");
        objEReacodeInfoReaType.add("12");
        objEReacodeInfoReaType.add("13");
        objEReacodeInfoReaType.add("14");
        objEReacodeInfoReaType.add("15");
        objEReacodeInfoReaType.add("16");
        objEReacodeInfoReaType.add("17");
        for (String obj : objEReacodeInfoReaType) {
            GetReaTypeInfoResD objEGetReaTypeInfoResD = new GetReaTypeInfoResD();
            objEGetReaTypeInfoResD.setReaType(obj);
            if ("01".equals(obj)) {
                objEGetReaTypeInfoResD.setReaTDes("不良");
            } else if ("02".equals(obj)) {
                objEGetReaTypeInfoResD.setReaTDes("降级");
            } else if ("03".equals(obj)) {
                objEGetReaTypeInfoResD.setReaTDes("返工");
            } else if ("04".equals(obj)) {
                objEGetReaTypeInfoResD.setReaTDes("调增");
            } else if ("05".equals(obj)) {
                objEGetReaTypeInfoResD.setReaTDes("调减");
            } else if ("06".equals(obj)) {
                objEGetReaTypeInfoResD.setReaTDes("冻结");
            }
            else if ("07".equals(obj)) {
                objEGetReaTypeInfoResD.setReaTDes("解冻");
            }
            else if ("10".equals(obj)) {
                objEGetReaTypeInfoResD.setReaTDes("报废");
            }
            else if ("11".equals(obj)) {
                objEGetReaTypeInfoResD.setReaTDes("更改数量");
            }
            else if ("12".equals(obj)) {
                objEGetReaTypeInfoResD.setReaTDes("打开");
            }
            else if ("13".equals(obj)) {
                objEGetReaTypeInfoResD.setReaTDes("关闭");
            }
            else if ("14".equals(obj)) {
                objEGetReaTypeInfoResD.setReaTDes("盘点差异");
            }
            else if ("15".equals(obj)) {
                objEGetReaTypeInfoResD.setReaTDes("不合格");
            }
            else if ("16".equals(obj)) {
                objEGetReaTypeInfoResD.setReaTDes("收料差异");
            }
            else if ("17".equals(obj)) {
                objEGetReaTypeInfoResD.setReaTDes("公共");
            }

            objEGetReaTypeInfoResDs.add(objEGetReaTypeInfoResD);

        }

        objEGetReaTypeInfoResB.setData(objEGetReaTypeInfoResDs);
        objEGetReaTypeInfoRes.setBody(objEGetReaTypeInfoResB);
        return objEGetReaTypeInfoRes;
    }

    //dto获取原因代码列表信息
    @Override
    public GetAllReaInfoRes QALLGetAllReaInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllReaInfoRes objEGetAllReaInfoRes = new GetAllReaInfoRes();
        GetAllReaInfoResB objEGetAllReaInfoResB = new GetAllReaInfoResB();
        List<GetAllReaInfoResD> objEGetAllReaInfoResDs = new ArrayList<GetAllReaInfoResD>();
        List<ReaCodeInfo> reaCodeInfos=reasonDAO.SelectReacodeInfo();

        //获取所有的原因代码信息
        List<ReaCodeInfo> objEReaCodeInfos =baseIService.QALLBaseInfo(ReasonDAO.class,"SelectReacodeInfo", argInitDataFields, argPageInfo);

        if (objEReaCodeInfos != null || objEReaCodeInfos.size() > 0) {

            for (ReaCodeInfo obj : objEReaCodeInfos) {
                GetAllReaInfoResD objEGetAllReaInfoResD = new GetAllReaInfoResD();
                objEGetAllReaInfoResD.setReaRd(obj.getRuid());
                objEGetAllReaInfoResD.setReaDes(obj.getReaDes());
                objEGetAllReaInfoResD.setReaCode(obj.getReaCode());
                objEGetAllReaInfoResDs.add(objEGetAllReaInfoResD);
            }
        }

        objEGetAllReaInfoResB.setData(objEGetAllReaInfoResDs);
        objEGetAllReaInfoRes.setBody(objEGetAllReaInfoResB);
        return objEGetAllReaInfoRes;
    }

    //查询代码原因信息
    @Override
    public GetReaInfoRes GetGetReaInfoRes(GetReaInfoReqBD00 argGetReaInfoReqBD00) {
        GetReaInfoRes objEGetReaInfoRes = new GetReaInfoRes();
        GetReaInfoResB objEGetReaInfoResB = new GetReaInfoResB();

        //根据传过来的id查询原因代码信息
        ReaCodeInfo objEReaCodeInfo = reasonDAO.SelectReacodeInfoByruid(argGetReaInfoReqBD00.getReaRd());
        if (objEReaCodeInfo != null) {

            GetReaInfoResD objEGetReaInfoResD = new GetReaInfoResD();
            GetReaInfoResDReaT objEGetReaInfoResDReaT=new GetReaInfoResDReaT();
            if("01".equals(objEReaCodeInfo.getReaType())){
                objEGetReaInfoResDReaT.setReaTID("01");
                objEGetReaInfoResDReaT.setReaTDes("不良");
            }
            if("02".equals(objEReaCodeInfo.getReaType())){
                objEGetReaInfoResDReaT.setReaTID("02");
                objEGetReaInfoResDReaT.setReaTDes("降级");
            }
            if("03".equals(objEReaCodeInfo.getReaType())){
                objEGetReaInfoResDReaT.setReaTID("03");
                objEGetReaInfoResDReaT.setReaTDes("返工");
            }
            if("04".equals(objEReaCodeInfo.getReaType())){
                objEGetReaInfoResDReaT.setReaTID("04");
                objEGetReaInfoResDReaT.setReaTDes("调增");
            }
            if("05".equals(objEReaCodeInfo.getReaType())){
                objEGetReaInfoResDReaT.setReaTID("05");
                objEGetReaInfoResDReaT.setReaTDes("调减");
            }
            if("06".equals(objEReaCodeInfo.getReaType())){
                objEGetReaInfoResDReaT.setReaTID("06");
                objEGetReaInfoResDReaT.setReaTDes("冻结");
            }
            if("07".equals(objEReaCodeInfo.getReaType())){
                objEGetReaInfoResDReaT.setReaTID("07");
                objEGetReaInfoResDReaT.setReaTDes("解冻");
            }
            if("08".equals(objEReaCodeInfo.getReaType())){
                objEGetReaInfoResDReaT.setReaTID("08");
                objEGetReaInfoResDReaT.setReaTDes("设备状态变更");
            }
            if("09".equals(objEReaCodeInfo.getReaType())){
                objEGetReaInfoResDReaT.setReaTID("09");
                objEGetReaInfoResDReaT.setReaTDes("保养原因");
            }
            if("10".equals(objEReaCodeInfo.getReaType())){
                objEGetReaInfoResDReaT.setReaTID("10");
                objEGetReaInfoResDReaT.setReaTDes("报废");
            }
            if("11".equals(objEReaCodeInfo.getReaType())){
                objEGetReaInfoResDReaT.setReaTID("11");
                objEGetReaInfoResDReaT.setReaTDes("更改数量");
            }
            if("12".equals(objEReaCodeInfo.getReaType())){
                objEGetReaInfoResDReaT.setReaTID("12");
                objEGetReaInfoResDReaT.setReaTDes("打开");
            }
            if("13".equals(objEReaCodeInfo.getReaType())){
                objEGetReaInfoResDReaT.setReaTID("13");
                objEGetReaInfoResDReaT.setReaTDes("关闭");
            }
            if("14".equals(objEReaCodeInfo.getReaType())){
                objEGetReaInfoResDReaT.setReaTID("14");
                objEGetReaInfoResDReaT.setReaTDes("盘点差异");
            }
            if("15".equals(objEReaCodeInfo.getReaType())){
                objEGetReaInfoResDReaT.setReaTID("15");
                objEGetReaInfoResDReaT.setReaTDes("不合格");
            }
            if("16".equals(objEReaCodeInfo.getReaType())){
                objEGetReaInfoResDReaT.setReaTID("16");
                objEGetReaInfoResDReaT.setReaTDes("收料差异");
            }
            if("17".equals(objEReaCodeInfo.getReaType())){
                objEGetReaInfoResDReaT.setReaTID("17");
                objEGetReaInfoResDReaT.setReaTDes("公共");
            }

            objEGetReaInfoResD.setReaTInfo(objEGetReaInfoResDReaT);
            objEGetReaInfoResD.setReaRd(objEReaCodeInfo.getRuid());
            objEGetReaInfoResD.setReaType(objEReaCodeInfo.getReaType());
            objEGetReaInfoResD.setReaDes(objEReaCodeInfo.getReaDes());
            objEGetReaInfoResD.setReaCode(objEReaCodeInfo.getReaCode());
            objEGetReaInfoResD.setCreator(objEReaCodeInfo.getCreator());
            objEGetReaInfoResD.setCreateTime(DateUtil.format(objEReaCodeInfo.getCreateTime()));
            objEGetReaInfoResD.setLastModifyMan(objEReaCodeInfo.getLastModifyMan());
            objEGetReaInfoResD.setLastModifyTime(DateUtil.format(objEReaCodeInfo.getLastModifyTime()));
            objEGetReaInfoResD.setRemark(objEReaCodeInfo.getRemark());
            objEGetReaInfoResB.setData(objEGetReaInfoResD);
        }

        objEGetReaInfoRes.setBody(objEGetReaInfoResB);
        return objEGetReaInfoRes;
    }

    //dto新增代码原因
    @Override
    public SaveReaInfoRes AddSaveReaInfoRes(SaveReaInfoReq00 argSaveReaInfoReq00) {
        SaveReaInfoRes objESaveReaInfoRes = new SaveReaInfoRes();
        SaveReaInfoResB objESaveReaInfoResB = new SaveReaInfoResB();

        ReaCodeInfo objEReaCodeInfo = new ReaCodeInfo();
        objEReaCodeInfo.setGuid(CommonUtils.getRandomNumber());
        objEReaCodeInfo.setReaDes(argSaveReaInfoReq00.getReaDes());
        objEReaCodeInfo.setReaType(argSaveReaInfoReq00.getReaTID());
        //查询原因代码,不允许原因代码重复
        List<ReaCodeInfo> objEReaCodeInfos = reasonDAO.SelectReacodeInfo();
        for (ReaCodeInfo obj : objEReaCodeInfos) {
            if (obj.getReaCode().equals(argSaveReaInfoReq00.getReaCode())&&argSaveReaInfoReq00.getReaTID().equals(obj.getReaType())) {
                throw new SystemException("MG_MES3001013010001F", "新增失败，原因代码已存在");
            }
        }
        objEReaCodeInfo.setReaCode(argSaveReaInfoReq00.getReaCode());
        objEReaCodeInfo.setRemark(argSaveReaInfoReq00.getRemark());
        objEReaCodeInfo.setCreateTime(new Date());
        objEReaCodeInfo.setCreator(CommonUtils.readUser().getRealName());
        reasonDAO.InsertReacodeInfo(objEReaCodeInfo);

        objESaveReaInfoRes.setBody(objESaveReaInfoResB);
        return objESaveReaInfoRes;
    }

    //dto删除
    @Override
    public SaveReaInfoRes RmSaveReaInfoRes(SaveReaInfoReq01 argSaveReaInfoReq01) {
        SaveReaInfoRes objESaveReaInfoRes = new SaveReaInfoRes();
        SaveReaInfoResB objESaveReaInfoResB = new SaveReaInfoResB();

        if (reasonDAO.DeleteReacodeInfo(argSaveReaInfoReq01.getReaRd()) <= 0) {
            throw new SystemException("MG_MES3001413020001F", "删除原因代码失败");
        }
        objESaveReaInfoRes.setBody(objESaveReaInfoResB);
        return objESaveReaInfoRes;
    }

    //dto编辑
    @Override
    public SaveReaInfoRes ModSaveReaInfoRes(SaveReaInfoReq02 argSaveReaInfoReq02) {
        SaveReaInfoRes objESaveReaInfoRes = new SaveReaInfoRes();
        SaveReaInfoResB objESaveReaInfoResB = new SaveReaInfoResB();
        ReaCodeInfo objEReaCodeInfo = reasonDAO.SelectReacodeInfoByruid(argSaveReaInfoReq02.getReaRd());
        objEReaCodeInfo.setRuid(argSaveReaInfoReq02.getReaRd());

        ReaCodeInfo reaCodeInfoname = reasonDAO.SelectReacodeInfoByReaCodeAndReaType(argSaveReaInfoReq02.getReaCode(),argSaveReaInfoReq02.getReaTID());
        if (reaCodeInfoname != null && !reaCodeInfoname.getReaCode().equals(objEReaCodeInfo.getReaCode())&&argSaveReaInfoReq02.getReaTID().equals(reaCodeInfoname.getReaType())) {
            throw new SystemException("MG_MES3001413030001F", "更新失败，原因代码已存在");
        }
        objEReaCodeInfo.setReaType(argSaveReaInfoReq02.getReaTID());
        objEReaCodeInfo.setReaCode(argSaveReaInfoReq02.getReaCode());
        objEReaCodeInfo.setReaDes(argSaveReaInfoReq02.getReaDes());
        objEReaCodeInfo.setRemark(argSaveReaInfoReq02.getRemark());
        objEReaCodeInfo.setLastModifyTime(new Date());
        objEReaCodeInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        if (reasonDAO.UpdateReacodeInfo(objEReaCodeInfo) <= 0) {
            throw new SystemException("MG_MES3001413030002F", "更新失败");
        }

        objESaveReaInfoRes.setBody(objESaveReaInfoResB);
        return objESaveReaInfoRes;
    }

    //dto复制
    @Override
    public SaveReaInfoRes AddSaveReaInfoRes(SaveReaInfoReq03 argSaveReaInfoReq03) {
        SaveReaInfoRes objESaveReaInfoRes = new SaveReaInfoRes();
        SaveReaInfoResB objESaveReaInfoResB = new SaveReaInfoResB();

        //根据id先查询原因代码信息
        ReaCodeInfo objEReaCodeInfo = reasonDAO.SelectReacodeInfoByruid(argSaveReaInfoReq03.getReaRd());
        if (objEReaCodeInfo == null) {
            throw new SystemException("MG_MES3001414030001F", "查询原因代码信息失败");
        }
        objEReaCodeInfo.setGuid(CommonUtils.getRandomNumber());
        objEReaCodeInfo.setLastModifyTime(new Date());
        objEReaCodeInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        objEReaCodeInfo.setCreateTime(new Date());
        objEReaCodeInfo.setCreator(CommonUtils.readUser().getRealName());
        reasonDAO.InsertReacodeInfo(objEReaCodeInfo);
        //根据新增进去的guid查询信息
        ReaCodeInfo objEReaCodeInfos = reasonDAO.SelectReacodeInfoByguid(objEReaCodeInfo.getGuid());
        if (objEReaCodeInfos == null) {
            throw new SystemException("MG_MES3001414030002F", "查询原因代码信息失败");
        }
        objEReaCodeInfos.setReaCode(objEReaCodeInfo.getReaCode() + objEReaCodeInfos.getRuid());
        if (reasonDAO.UpdateReacodeInfo(objEReaCodeInfos) <= 0) {
            throw new SystemException("MG_MES3001414030003F", "复制原因代码失败");
        }

        objESaveReaInfoRes.setBody(objESaveReaInfoResB);
        return objESaveReaInfoRes;
    }

    /**
     * 获取原因代码列表(新)
     * @param request
     * @return
     */
    @Override
    public PageResult<GetAllReaInfoResD> QALLReaInfoNew(BaseRequest request) {
        List<GetAllReaInfoResD> resDList = new ArrayList<GetAllReaInfoResD>();
        GetAllReaInfoResD resD = null;

        IPage<ReaCodeInfo> iPage = reasonDAO.selectPage(new MyPage(request), CommonUtils.getQueryWrapper(request.getFields()));

        for (ReaCodeInfo obj : iPage.getRecords()) {
            resD = new GetAllReaInfoResD();
            resD.setReaRd(obj.getRuid());
            resD.setReaDes(obj.getReaDes());
            resD.setReaCode(obj.getReaCode());
            resDList.add(resD);
        }

        return new PageResult<>(iPage, resDList);
    }
}
