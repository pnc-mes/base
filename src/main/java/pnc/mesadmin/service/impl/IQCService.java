package pnc.mesadmin.service.impl;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllIQCBInfo.GetAllIQCBInfoResB;
import pnc.mesadmin.dto.GetAllIQCBInfo.GetAllIQCBInfoRes;
import pnc.mesadmin.dto.GetAllIQCBInfo.GetAllIQCBInfoResD;
import pnc.mesadmin.dto.GetIQCBInfo.*;
import pnc.mesadmin.dto.SaveIQCBInfo.*;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：质检批次Service
 * 创建人：王怀龙
 * 创建时间：2017-06-10
 * 修改人：
 * 修改时间：
 */
@Service
@Transactional
public class IQCService implements pnc.mesadmin.service.IQCIService {
    @Resource
    IQCBInfoDAO iqcDao;
    @Resource
    IQCBadBInfoDAO iqcBadBInfoDAO;
    @Resource
    IQCCentBInfoDAO iqcCentBInfoDAO;
    @Resource
    BDAO batchDAO;
    @Resource
    MaVerDAO maVerDAO;

    @Resource
    private BaseIService baseIService;
    /**
     * 获取批次质检列表信息
     *
     * @param
     * @param
     * @return
     */
    public GetAllIQCBInfoRes QAllIQCBInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {

        ObjectMapper objectMapper = new ObjectMapper();

        List<GetAllIQCBInfoResD> getAllIQCBInfoResDList = new ArrayList<GetAllIQCBInfoResD>();

        List<IQCInfo> iqcInfoList = baseIService.QALLBaseInfo(IQCBInfoDAO.class, "SelectAllIQCBInfo",
                argInitDataFields, argPageInfo);

        for (IQCInfo iqcInfo : iqcInfoList) {
            GetAllIQCBInfoResD getAllIQCBInfoResD = new GetAllIQCBInfoResD();
            getAllIQCBInfoResD.setIQCRd(iqcInfo.getRuid());
            getAllIQCBInfoResD.setBatch(iqcInfo.getBatch());

            getAllIQCBInfoResDList.add(getAllIQCBInfoResD);
        }

        if (null == iqcInfoList || iqcInfoList.size() <= 0) {
            throw new SystemException("", "IQC列表信息为空");
        }

        GetAllIQCBInfoResB getAllIQCBInfoResB = new GetAllIQCBInfoResB();
        getAllIQCBInfoResB.setMsgCode("0x00000");
        getAllIQCBInfoResB.setMsgDes("成功");
        getAllIQCBInfoResB.setData(getAllIQCBInfoResDList);

        GetAllIQCBInfoRes getAllIQCBInfoRes = new GetAllIQCBInfoRes();
        getAllIQCBInfoRes.setStatus("00");
        getAllIQCBInfoRes.setBody(getAllIQCBInfoResB);

        return getAllIQCBInfoRes;
    }

    /**
     * 获取批次质检信息
     *
     * @param request
     * @param argGetAllReq
     * @return
     */
    public GetIQCBInfoRes QIQCBInfo(HttpServletRequest request, GetAllReq argGetAllReq) {

        String result = null;
        if (!"00".equals(argGetAllReq.getExecType())) {
            throw new SystemException("", "执行类型错误");
        }
        GetIQCBInfoReqBD00 objEGetIQCBInfoReqBD00 = CommonUtils.switchClass(GetIQCBInfoReqBD00.class, argGetAllReq.getBusData());
        //    JSONObject jsonObject = JSONObject.fromObject(argGetAllReq.getBusData());
        //   GetIQCBInfoReqBD00 objEGetIQCBInfoReqBD00 = (GetIQCBInfoReqBD00) JSONObject.toBean(jsonObject,GetIQCBInfoReqBD00.class);
      /*  if(null == objEGetIQCBInfoReqBD00){
            throw new SystemException("","IQCid信息为空");
        }*/
        IQCInfo objEIqcInfo = iqcDao.SelectIQCBInfoByRuid(objEGetIQCBInfoReqBD00.getIQCRd());
    /*    if(null == objEIqcInfo){
            throw new SystemException("","IQC信息为空");
        }*/
        GetIQCBInfoResD getIQCBInfoResD = new GetIQCBInfoResD();
        GetIQCBInfoResB getIQCBInfoResB = new GetIQCBInfoResB();
        GetIQCBInfoRes getIQCBInfoRes = new GetIQCBInfoRes();

        List<IQCBadBInfo> objEIqcBadBInfos = iqcBadBInfoDAO.SelectBadBInfoByIQCGd(objEIqcInfo.getGuid());
       /* if(null == objEIqcBadBInfos || objEIqcBadBInfos.size() <= 0){
            throw new SystemException("","不良批次信息为空");
        }*/
        List<GetIQCBInfoResDBad> GetIQCBInfoResDBads = new ArrayList<GetIQCBInfoResDBad>();
        for (int i = 0; i < objEIqcBadBInfos.size(); i++) {
            GetIQCBInfoResDBad objEGetIQCBInfoResDBad = new GetIQCBInfoResDBad();
            objEGetIQCBInfoResDBad.setIQCBadRd(objEIqcBadBInfos.get(i).getRuid());
            objEGetIQCBInfoResDBad.setReaCode(objEIqcBadBInfos.get(i).getReaCode());
            objEGetIQCBInfoResDBad.setReaDes(objEIqcBadBInfos.get(i).getReaDes());
            GetIQCBInfoResDBads.add(objEGetIQCBInfoResDBad);
        }

        List<IQCCentBInfo> objEIqcCentBInfos = iqcCentBInfoDAO.SelectIQCCentBInfoByIQCGd(objEIqcInfo.getGuid());
        /*if(null == objEIqcCentBInfos || objEIqcCentBInfos.size() <= 0){
            throw new SystemException("","质检证书信息为空");
        }*/
        List<GetIQCBInfoResDCent> GetIQCBInfoResDCents = new ArrayList<GetIQCBInfoResDCent>();
        for (int i = 0; i < objEIqcCentBInfos.size(); i++) {
            GetIQCBInfoResDCent objEGetIQCBInfoResDCent = new GetIQCBInfoResDCent();
            objEGetIQCBInfoResDCent.setIQCCentRd(objEIqcCentBInfos.get(i).getRuid());
            objEGetIQCBInfoResDCent.setFileName(objEIqcCentBInfos.get(i).getFileName());
            objEGetIQCBInfoResDCent.setLastUpDate(DateUtil.format(objEIqcCentBInfos.get(i).getLastModifyTime()));
            GetIQCBInfoResDCents.add(objEGetIQCBInfoResDCent);
        }
        getIQCBInfoResD.setIQCRd(objEIqcInfo.getRuid());
        getIQCBInfoResD.setBatch(objEIqcInfo.getBatch());
        getIQCBInfoResD.setMaCode(objEIqcInfo.getMaterialCode());
        getIQCBInfoResD.setMaName(objEIqcInfo.getMaterialName());
        getIQCBInfoResD.setNum(objEIqcInfo.getNum());
        getIQCBInfoResD.setCkResult(objEIqcInfo.getCkResult());
        getIQCBInfoResD.setBadInfo(GetIQCBInfoResDBads);
        getIQCBInfoResD.setCentInfo(GetIQCBInfoResDCents);
        getIQCBInfoResD.setCreator(objEIqcInfo.getCreator());
        getIQCBInfoResD.setCreateTime(DateUtil.format(objEIqcInfo.getCreateTime()));
        getIQCBInfoResD.setLastModifyMan(objEIqcInfo.getLastModifyMan());
        getIQCBInfoResD.setLastModifyTime(DateUtil.format(objEIqcInfo.getLastModifyTime()));
        getIQCBInfoResD.setRemark(objEIqcInfo.getRemark());

        getIQCBInfoResB.setMsgCode("0x00000");
        getIQCBInfoResB.setMsgDes("成功");
        getIQCBInfoResB.setData(getIQCBInfoResD);

        getIQCBInfoRes.setStatus("00");
        getIQCBInfoRes.setBody(getIQCBInfoResB);

        return getIQCBInfoRes;
    }

    public SaveIQCBInfoRes SaveIQCBInfo(HttpServletRequest request, SaveReq saveReq) {

        String reqCode = saveReq.getExecType();
        SaveIQCBInfoRes objESaveIQCBInfoRes = null;

        if ("00".equals(reqCode)) {
            SaveIQCBInfoReqBD00 saveIQCBInfoReqBD00 = CommonUtils.switchClass(SaveIQCBInfoReqBD00.class, saveReq.getBusData());
            objESaveIQCBInfoRes = AddSaveIQCBInfo(saveIQCBInfoReqBD00);
        }

        if ("01".equals(reqCode)) {
            SaveIQCBInfoReqBD01 saveIQCBInfoReqBD01 = CommonUtils.switchClass(SaveIQCBInfoReqBD01.class, saveReq.getBusData());
            objESaveIQCBInfoRes = RmSaveIQCBInfo(saveIQCBInfoReqBD01);
        }

        if ("02".equals(reqCode)) {
            String strSaveReq = saveReq.getBusData().replace("undefined", "");
            SaveIQCBInfoReqBD02 saveIQCBInfoReqBD02 = CommonUtils.switchClass(SaveIQCBInfoReqBD02.class, strSaveReq);
            objESaveIQCBInfoRes = ModSaveIQCBInfo(saveIQCBInfoReqBD02);
        }

        return objESaveIQCBInfoRes;
    }

    /**
     * 新增批次质检信息
     *
     * @param saveIQCBInfoReqBD00
     * @return
     */
    private SaveIQCBInfoRes AddSaveIQCBInfo(SaveIQCBInfoReqBD00 saveIQCBInfoReqBD00) {
        SaveIQCBInfoRes saveIQCBInfoRes = new SaveIQCBInfoRes();
        SaveIQCBInfoResB saveIQCBInfoResB = new SaveIQCBInfoResB();

        //校验批次和物料信息
        BInfo objEBInfo = batchDAO.selectBatchInfoByBatch(saveIQCBInfoReqBD00.getBatch());

        if (objEBInfo == null) {
            throw new SystemException("", "非法的批次号" + objEBInfo.getBatch() + "请检查批次来源");
        }
        if(objEBInfo.getiQCStatus().equals("01")){
            throw new SystemException("", "批次" + objEBInfo.getBatch() + "为免检批次");
        }
        if (objEBInfo.getStatus().equals("02")) {
            throw new SystemException("", "批次" + objEBInfo.getBatch() + "已冻结");
        }
        if (objEBInfo.getStatus().equals("03")) {    //批次报废状态 00作废{
            throw new SystemException("", "批次" + objEBInfo.getBatch() + "已作废!");
        }
        if (objEBInfo.getStatus().equals("04")) {
            throw new SystemException("", "批次" + objEBInfo.getBatch() + "已完工");
        }
        if (objEBInfo.getInStockStatus().equals("00")) {
            throw new SystemException("", "批次" + objEBInfo.getBatch() + "已在库");
        }
        if (objEBInfo.getInStockStatus().equals("01")) {
            throw new SystemException("", "批次" + objEBInfo.getBatch() + "已离库");
        }

        MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(objEBInfo.getMaVerGd());
        if (objEMaVerInfo == null) {
            throw new SystemException("", "批次" + objEBInfo.getBatch() + "物料不存在");
        }
        if (objEMaVerInfo.getStatus().equals("01")) {
            throw new SystemException("", "批次" + objEBInfo.getBatch() + "已禁用");
        }

        if (objEMaVerInfo.getMaterialType().equals("00")) {
            throw new SystemException("", "IQC只能质检原材料信息,请检查批次" + objEBInfo.getBatch() + "的批次类型!");
        }

        //设置默认的批次状态为已完工  不良状态为未设置
        objEBInfo.setBad("01"); //默认不良状态为未设置
        objEBInfo.setiQCStatus("02"); //状态设为已检
        objEBInfo.setCkResult(saveIQCBInfoReqBD00.getCkResult()); //质检结果

        IQCInfo objEIQCBInfo = iqcDao.SelectIQCBInfoByBatch(saveIQCBInfoReqBD00.getBatch());

        String objGuid = CommonUtils.getRandomNumber();
               /*质检合不格时*/
        if (saveIQCBInfoReqBD00.getBadInfo() != null && !saveIQCBInfoReqBD00.getCkResult().equals("00")) {

            for (int i = 0; i < saveIQCBInfoReqBD00.getBadInfo().size(); i++) {
                IQCBadBInfo iqcBadBInfo = new IQCBadBInfo();
                iqcBadBInfo.setGuid(CommonUtils.getRandomNumber());
                iqcBadBInfo.setIqcGd(objGuid);
                iqcBadBInfo.setCreator(CommonUtils.readUser().getRealName());
                iqcBadBInfo.setCreateTime(new Date());
                iqcBadBInfo.setReaCode(saveIQCBInfoReqBD00.getBadInfo().get(i).getReaCode());
                iqcBadBInfo.setReaDes(saveIQCBInfoReqBD00.getBadInfo().get(i).getReaDes());

                int insertBadInfoCode = iqcBadBInfoDAO.InsertBadBInfo(iqcBadBInfo);
                if (insertBadInfoCode <= 0) {
                    throw new SystemException("", "新增批次不良信息失败");
                }
            }
            //修改批次不良状态为不良,状态为处理中
            objEBInfo.setBad("00"); //修改不良状态为不良
        }

        IQCInfo objEIqcInfo = new IQCInfo();
        objEIqcInfo.setGuid(objGuid);
        objEIqcInfo.setBatch(saveIQCBInfoReqBD00.getBatch());
        objEIqcInfo.setMaVerGd(objEBInfo.getMaVerGd());
        objEIqcInfo.setMaterialCode(objEMaVerInfo.getMaterialCode());
        objEIqcInfo.setMaterialName(objEMaVerInfo.getMaterialName());
        objEIqcInfo.setVersion(objEMaVerInfo.getVersion());
        objEIqcInfo.setNum(objEBInfo.getNum());
        objEIqcInfo.setCkResult(saveIQCBInfoReqBD00.getCkResult());
        objEIqcInfo.setCreator(CommonUtils.readUser().getRealName());
        objEIqcInfo.setCreateTime(new Date());

        try {
            if (iqcDao.InsertIQCBInfo(objEIqcInfo) <= 0) {
                throw new SystemException("", "新增批次质检信息失败");
            }
        } catch (Exception e) {
            throw new SystemException("", "新增批次质检信息失败");
        }

        //更新批次不良信息和完工状态
        batchDAO.UpdateBatchInfoByRuid(objEBInfo);

        saveIQCBInfoResB.setMsgCode("0x00000");
        saveIQCBInfoResB.setMsgDes("成功");
        saveIQCBInfoResB.setData(null);

        saveIQCBInfoRes.setStatus("00");
        saveIQCBInfoRes.setBody(saveIQCBInfoResB);

        return saveIQCBInfoRes;
    }

    /**
     * 删除批次质检信息
     *
     * @param saveIQCBInfoReqBD01
     * @return
     */
    private SaveIQCBInfoRes RmSaveIQCBInfo(SaveIQCBInfoReqBD01 saveIQCBInfoReqBD01) {
        SaveIQCBInfoRes objESaveIQCBInfoRes = new SaveIQCBInfoRes();
        SaveIQCBInfoResB objESaveIQCBInfoResB = new SaveIQCBInfoResB();


        IQCInfo objEIQCInfo = iqcDao.SelectIQCBInfoByRuid(saveIQCBInfoReqBD01.getIQCRd());
        if (objEIQCInfo == null) {
            throw new SystemException("", "批次质检信息为空");
        }

        BInfo objEBInfo = batchDAO.selectBatchInfoByBatch(objEIQCInfo.getBatch());
        if (objEBInfo == null) {
            throw new SystemException("", "当前批次不存在");
        }

        MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(objEBInfo.getMaVerGd());
        if (objEMaVerInfo == null) {
            throw new SystemException("", "当前物料不存在");
        }

        if (objEMaVerInfo.getIsExem().equals("00")) {
            iqcBadBInfoDAO.DeleteBadBInfoByIqcGd(objEIQCInfo.getGuid());
            iqcCentBInfoDAO.DeleteIQCCentBInfoByIqcGd(objEIQCInfo.getGuid());
            int code1 = iqcDao.DeleteIQCBInfoByRuid(saveIQCBInfoReqBD01.getIQCRd());

        } else {
            if (objEBInfo.getInStockStatus().equals("00")) {
                //在库
                throw new SystemException("", "该批次已入库,不可删除");
            } else {
                //不在库
                iqcBadBInfoDAO.DeleteBadBInfoByIqcGd(objEIQCInfo.getGuid());
                iqcCentBInfoDAO.DeleteIQCCentBInfoByIqcGd(objEIQCInfo.getGuid());
                int code1 = iqcDao.DeleteIQCBInfoByRuid(saveIQCBInfoReqBD01.getIQCRd());
                if (code1 != 1) {
                    throw new SystemException("", "删除批次质检信息失败");
                }
                objEBInfo.setStatus("01");
                if (batchDAO.UpdateBatchInfoByRuid(objEBInfo) <= 0) {
                    throw new SystemException("", "更新批次状态失败");
                }
            }
        }


        objESaveIQCBInfoResB.setMsgCode("0x00000");
        objESaveIQCBInfoResB.setMsgDes("成功");
        objESaveIQCBInfoResB.setData(null);

        objESaveIQCBInfoRes.setStatus("00");
        objESaveIQCBInfoRes.setBody(objESaveIQCBInfoResB);

        return objESaveIQCBInfoRes;
    }

    /**
     * 编辑批次质检信息
     *
     * @param saveIQCBInfoReqBD02
     * @return
     */
    private SaveIQCBInfoRes ModSaveIQCBInfo(SaveIQCBInfoReqBD02 saveIQCBInfoReqBD02) {
        SaveIQCBInfoRes objESaveIQCBInfoRes = new SaveIQCBInfoRes();
        SaveIQCBInfoResB objESaveIQCBInfoResB = new SaveIQCBInfoResB();

    /*    IQCInfo objIQCInfo = iqcDao.SelectIQCBInfoByRuid(saveIQCBInfoReqBD02.getIQCRd());
        if (objIQCInfo == null) {
            throw new SystemException("", "批次质检信息为空");
        }


        BInfo objEBInfo = batchDAO.selectBatchInfoByBatch(objIQCInfo.getBatch());
        if (objEBInfo == null) {
            throw new SystemException("", "当前批次不存在");
        }
        if (objEBInfo.getInStockStatus().equals("00")) {
            //在库
            throw new SystemException("", "该批次已入库,不可编辑");
        }

        //删除不良批次信息
        //获取全部不良批次信息
        List<IQCBadBInfo> IQCBadBInfos = iqcBadBInfoDAO.SelectBadBInfoByIQCGd(objIQCInfo.getGuid());
        if (saveIQCBInfoReqBD02.getCkResult().equals("00")) {   //该批次提交上来的校验结果为合格
            if (IQCBadBInfos != null && IQCBadBInfos.size() > 0) {
                iqcBadBInfoDAO.DeleteBadBInfoByIqcGd(objIQCInfo.getGuid());
            }
        } else { //该批次提交上来的校验结果为不合格或降级使用

            if (IQCBadBInfos == null || IQCBadBInfos.size() <= 0) {  //如果该批次无不良代码信息
                for (int i = 0; i < saveIQCBInfoReqBD02.getBadInfo().size(); i++) {


                    //如果提交上来的某条代码在数据库中不存在,就新增不良批次信息
                    IQCBadBInfo objIQCBadBInfo = iqcBadBInfoDAO.SelectIQCBadInfoByRuid(saveIQCBInfoReqBD02.getBadInfo().get(i).getIQCBadRd());
                    if (objIQCBadBInfo == null) {
                        IQCBadBInfo objEIQCBadBInfo = new IQCBadBInfo();
                        objEIQCBadBInfo.setGuid(CommonUtils.getRandomNumber());
                        objEIQCBadBInfo.setIqcGd(objIQCInfo.getGuid());
                        objEIQCBadBInfo.setReaCode(saveIQCBInfoReqBD02.getBadInfo().get(i).getReaCode());
                        objEIQCBadBInfo.setReaDes(saveIQCBInfoReqBD02.getBadInfo().get(i).getReaDes());
                        objEIQCBadBInfo.setCreateTime(new Date());
                        objEIQCBadBInfo.setCreator((String) SecurityUtils.getSubject().getPrincipal());

                        if (iqcBadBInfoDAO.InsertBadBInfo(objEIQCBadBInfo) <= 0) {
                            throw new SystemException("", "新增不良批次信息失败");
                        }
                    }
                }
            } else { //如果该批次有不良代码信息

                for (int y = 0; y < IQCBadBInfos.size(); y++) {
                    int checkCode = -1;
                    for (int i = 0; i < saveIQCBInfoReqBD02.getBadInfo().size(); i++) {


                        //如果提交上来的某条代码在数据库中不存在,就新增不良批次信息
                        IQCBadBInfo objIQCBadBInfo = iqcBadBInfoDAO.SelectIQCBadInfoByRuid(saveIQCBInfoReqBD02.getBadInfo().get(i).getIQCBadRd());
                        if (objIQCBadBInfo == null) {
                            IQCBadBInfo objEIQCBadBInfo = new IQCBadBInfo();
                            objEIQCBadBInfo.setGuid(CommonUtils.getRandomNumber());
                            objEIQCBadBInfo.setIqcGd(objIQCInfo.getGuid());
                            objEIQCBadBInfo.setReaCode(saveIQCBInfoReqBD02.getBadInfo().get(i).getReaCode());
                            objEIQCBadBInfo.setReaDes(saveIQCBInfoReqBD02.getBadInfo().get(i).getReaDes());
                            objEIQCBadBInfo.setCreateTime(new Date());
                            objEIQCBadBInfo.setCreator((String) SecurityUtils.getSubject().getPrincipal());

                            if (iqcBadBInfoDAO.InsertBadBInfo(objEIQCBadBInfo) <= 0) {
                                throw new SystemException("", "新增不良批次信息失败");
                            }
                        }
                        if (IQCBadBInfos.get(y).getRuid() == saveIQCBInfoReqBD02.getBadInfo().get(i).getIQCBadRd()) {
                            checkCode = 1;
                        }
                    }

                    //若数据库中的某条信息在提交上来的信息中不存在与之对应的信息  则删除之
                    if (checkCode == -1 && IQCBadBInfos.get(y) != null && IQCBadBInfos.get(y).getGuid() != null) {
                        try {
                            iqcBadBInfoDAO.DeleteBadBInfoByGuid(IQCBadBInfos.get(y).getGuid());
                        } catch (Exception e) {
                            throw new SystemException("", "删除不良批次信息失败");
                        }
                    }

                }

            }
        }


        //删除质检文件信息
        //获取全部质检文件信息
        List<IQCCentBInfo> IQCCentBInfos = iqcCentBInfoDAO.SelectIQCCentBInfoByIQCGd(objIQCInfo.getGuid());
        for (int y = 0; y < IQCCentBInfos.size(); y++) {

            int checkCode = -1;
            for (int i = 0; i < saveIQCBInfoReqBD02.getCentInfo().size(); i++) {


                IQCCentBInfo objIQCCentBInfo = iqcCentBInfoDAO.SelectIQCCentBInfoByRuid(saveIQCBInfoReqBD02.getCentInfo().get(i).getIQCCentRd());
                if (objIQCCentBInfo == null) {
                    IQCCentBInfo iqcCentBInfo = new IQCCentBInfo();
                    iqcCentBInfo.setGuid(CommonUtils.getRandomNumber());
                    iqcCentBInfo.setCreator((String) SecurityUtils.getSubject().getPrincipal());
                    iqcCentBInfo.setCreateTime(new Date());
                    iqcCentBInfo.setIqcGd(objIQCInfo.getGuid());
                    iqcCentBInfo.setFileName(saveIQCBInfoReqBD02.getCentInfo().get(i).getFileName());

                    if (iqcCentBInfoDAO.InsertIQCCentBInfo(iqcCentBInfo) <= 0) {
                        throw new SystemException("", "新增质检文件信息失败");
                    }
                }

                if (objIQCCentBInfo != null) {
                    objIQCCentBInfo.setFileName(saveIQCBInfoReqBD02.getCentInfo().get(i).getFileName());
                    objIQCCentBInfo.setLastModifyTime(new Date());
                    objIQCCentBInfo.setLastModifyMan((String) SecurityUtils.getSubject().getPrincipal());
                    int centCode = iqcCentBInfoDAO.UpdateIQCCentBInfo(objIQCCentBInfo);
                    if (centCode != 1) {
                        throw new SystemException("", "更新批次质检证书信息失败");
                    }
                }

                if (IQCCentBInfos.get(y).getRuid() == saveIQCBInfoReqBD02.getCentInfo().get(i).getIQCCentRd()) {
                    checkCode = 1;
                }
            }

            if (checkCode == -1) {
                if (iqcCentBInfoDAO.DeleteIQCCentBInfoByIqcGd(IQCCentBInfos.get(y).getGuid()) <= 0) {
                    throw new SystemException("", "删除不良批次信息失败");
                }
            }
        }

        if (!saveIQCBInfoReqBD02.getCkResult().equals(objIQCInfo.getCkResult())) {
            objIQCInfo.setCkResult(saveIQCBInfoReqBD02.getCkResult());
            objIQCInfo.setRemark(saveIQCBInfoReqBD02.getRemark());
            objIQCInfo.setLastModifyMan((String) SecurityUtils.getSubject().getPrincipal());
            objIQCInfo.setLastModifyTime(new Date());
            if (iqcDao.UpdateIQCBInfo(objIQCInfo) <= 0) {
                throw new SystemException("", "更新批次质检信息失败");
            }
        }*/

        //校验批次和物料信息
        IQCInfo objIQCInfo = iqcDao.SelectIQCBInfoByRuid(saveIQCBInfoReqBD02.getIQCRd());
        if (objIQCInfo == null) {
            throw new SystemException("", "批次质检信息为空");
        }

        BInfo objEBInfo = batchDAO.selectBatchInfoByBatch(objIQCInfo.getBatch());
        if (objEBInfo == null) {
            throw new SystemException("", "非法的批次号" + objEBInfo.getBatch() + "请检查批次来源");
        }
        if (objEBInfo.getStatus().equals("02")) {
            throw new SystemException("", "批次" + objEBInfo.getBatch() + "已冻结");
        }
        if (objEBInfo.getStatus().equals("03")) {    //批次报废状态 00作废{
            throw new SystemException("", "批次" + objEBInfo.getBatch() + "已作废!");
        }
        if (objEBInfo.getStatus().equals("04")) {
            throw new SystemException("", "批次" + objEBInfo.getBatch() + "已完工");
        }
        if (objEBInfo.getInStockStatus().equals("00")) {
            throw new SystemException("", "批次" + objEBInfo.getBatch() + "已在库");
        }
        if (objEBInfo.getInStockStatus().equals("01")) {
            throw new SystemException("", "批次" + objEBInfo.getBatch() + "已离库");
        }

        MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(objEBInfo.getMaVerGd());
        if (objEMaVerInfo == null) {
            throw new SystemException("", "批次" + objEBInfo.getBatch() + "物料不存在");
        }
        if (objEMaVerInfo.getStatus().equals("01")) {
            throw new SystemException("", "批次" + objEBInfo.getBatch() + "物料已禁用");
        }
        if (objEMaVerInfo.getIsExem().equals("00")) {
            throw new SystemException("", "批次" + objEBInfo.getBatch() + "物料为免检物料");
        }
        if (!objEMaVerInfo.getMaterialType().equals("02")) {
            throw new SystemException("", "IQC只能质检原材料信息,请检查批次" + objEBInfo.getBatch() + "的批次类型!");
        }

        //设置默认的批次状态为已完工  不良状态为未设置
        objEBInfo.setBad("01"); //默认不良状态为未设置
        objEBInfo.setiQCStatus("02"); //状态设为已检
        objEBInfo.setCkResult(saveIQCBInfoReqBD02.getCkResult()); //质检结果


        //删除原有的不良质检信息
        List<IQCBadBInfo> IQCBadBInfos = iqcBadBInfoDAO.SelectBadBInfoByIQCGd(objIQCInfo.getGuid());
        if (IQCBadBInfos != null && IQCBadBInfos.size() > 0) {
            iqcBadBInfoDAO.DeleteBadBInfoByIqcGd(objIQCInfo.getGuid());
        }

        //新的质检信息为不合格
        if (saveIQCBInfoReqBD02.getBadInfo() != null && !saveIQCBInfoReqBD02.getCkResult().equals("00")) {
            //新增新的不良质检信息
            for (int i = 0; i < saveIQCBInfoReqBD02.getBadInfo().size(); i++) {
                IQCBadBInfo iqcBadBInfo = new IQCBadBInfo();
                iqcBadBInfo.setGuid(CommonUtils.getRandomNumber());
                iqcBadBInfo.setIqcGd(objIQCInfo.getGuid());
                iqcBadBInfo.setCreator(CommonUtils.readUser().getRealName());
                iqcBadBInfo.setCreateTime(new Date());
                iqcBadBInfo.setReaCode(saveIQCBInfoReqBD02.getBadInfo().get(i).getReaCode());
                iqcBadBInfo.setReaDes(saveIQCBInfoReqBD02.getBadInfo().get(i).getReaDes());

                int insertBadInfoCode = iqcBadBInfoDAO.InsertBadBInfo(iqcBadBInfo);
                if (insertBadInfoCode <= 0) {
                    throw new SystemException("", "新增批次不良信息失败");
                }
            }

            //更新批次不良信息和完工状态
            objEBInfo.setBad("00");

            //修改批次的质检信息
            objIQCInfo.setCkResult(saveIQCBInfoReqBD02.getCkResult());  //不合格
            objIQCInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
            objIQCInfo.setLastModifyTime(new Date());

        } else { //新的质检信息为合格

            //修改批次的质检信息
            objIQCInfo.setCkResult(saveIQCBInfoReqBD02.getCkResult());  //合格
            objIQCInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
            objIQCInfo.setLastModifyTime(new Date());

        }
        //更新批次的质检信息
        if (iqcDao.UpdateIQCBInfo(objIQCInfo) <= 0) {
            throw new SystemException("", "修改批次质检信息失败");
        }

        //更新批次不良信息和完工状态
        batchDAO.UpdateBatchInfoByRuid(objEBInfo);


        objESaveIQCBInfoResB.setMsgCode("0x00000");
        objESaveIQCBInfoResB.setMsgDes("成功");
        objESaveIQCBInfoResB.setData(null);

        objESaveIQCBInfoRes.setStatus("00");
        objESaveIQCBInfoRes.setBody(objESaveIQCBInfoResB);

        return objESaveIQCBInfoRes;
    }

}
