package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.PdFamilyDAO;
import pnc.mesadmin.dao.PrintTDAO;
import pnc.mesadmin.dao.SerialRuleDAO;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllMTInfo.GetAllMTInfoResDCMTInfo;
import pnc.mesadmin.dto.GetAllPDInfo.GetAllPDInfoResD;
import pnc.mesadmin.dto.GetAllPdfInfo.GetAllPdfInfoRes;
import pnc.mesadmin.dto.GetAllPdfInfo.GetAllPdfInfoResB;
import pnc.mesadmin.dto.GetAllPdfInfo.GetAllPdfInfoResD;
import pnc.mesadmin.dto.GetPdfInfo.GetPdfInfoReqBD00;
import pnc.mesadmin.dto.GetPdfInfo.GetPdfInfoRes;
import pnc.mesadmin.dto.GetPdfInfo.GetPdfInfoResB;
import pnc.mesadmin.dto.GetPdfInfo.GetPdfInfoResD;
import pnc.mesadmin.dto.SavePdfInfo.*;
import pnc.mesadmin.entity.MaTypeInfo;
import pnc.mesadmin.entity.PdFamilyInfo;
import pnc.mesadmin.entity.PrintTInfo;
import pnc.mesadmin.entity.SerialRuleInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.PFIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;
import pnc.mesadmin.utils.MyPage;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：产品家族信息业务信息逻辑
 * 创建人：潘俊峰
 * 创建时间：2017-05-26
 * 修改人：
 * 修改时间：
 */
@Service
@Transactional
public class PFService implements PFIService {

    @Resource
    private PdFamilyDAO pdFamilyDAO;

    @Resource
    private SerialRuleDAO serialRuleDAO;

    @Resource
    private BaseIService baseIService;

    @Resource
    private PrintTDAO printTDAO;

    //获取产品族列表信息
    public GetAllPdfInfoRes QALLPdFamilyInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException{

        GetAllPdfInfoRes objEGetAllPdfInfoRes = new GetAllPdfInfoRes();
        GetAllPdfInfoResB objEGetAllPdfInfoResB = new GetAllPdfInfoResB();
        List<GetAllPdfInfoResD> objEGetAllPdfInfoResDs = new ArrayList<GetAllPdfInfoResD>();

        List<PdFamilyInfo> objEPdFamilyInfos = baseIService.QALLBaseInfo(PdFamilyDAO.class, "SelectAllPdFamilyInfo",
                argInitDataFields, argPageInfo);

        if(objEPdFamilyInfos!=null || objEPdFamilyInfos.size()>0) {

            for (PdFamilyInfo pdFamilyInfo : objEPdFamilyInfos) {
                GetAllPdfInfoResD objEGetAllPdfInfoResD = new GetAllPdfInfoResD();
                objEGetAllPdfInfoResD.setPdfRd(pdFamilyInfo.getRuid());
                objEGetAllPdfInfoResD.setPdfName(pdFamilyInfo.getFamilyName());
                objEGetAllPdfInfoResDs.add(objEGetAllPdfInfoResD);
            }
        }

        objEGetAllPdfInfoResB.setData(objEGetAllPdfInfoResDs);
        objEGetAllPdfInfoRes.setBody(objEGetAllPdfInfoResB);

        return objEGetAllPdfInfoRes;
    }

    /**
     * 获取产品族列表信息(新)
     * @param req
     * @return
     */
    @Override
    public PageResult<GetAllPdfInfoResD> QALLPdFamilyNew(BaseRequest req) {
        IPage<PdFamilyInfo> iPage = pdFamilyDAO.selectPage(new MyPage(req), CommonUtils.getQueryWrapper(req.getFields()));

        List<PdFamilyInfo> list = iPage.getRecords().stream().filter(o -> "".equals(o.getpId())).collect(Collectors.toList());

        return new PageResult<GetAllPdfInfoResD>(iPage, SelectChild(list));
    }

    //获取产品族信息
    public GetPdfInfoRes GetPdFamilyInfo(GetPdfInfoReqBD00 argBD00) throws SystemException {

        GetPdfInfoRes objEGetPdfInfoRes = new GetPdfInfoRes();
        GetPdfInfoResB objEGetPdfInfoResB = new GetPdfInfoResB();
        GetPdfInfoResD objEGetPdfInfoResD = new GetPdfInfoResD();

        PdFamilyInfo objEpdFamilyInfo = pdFamilyDAO.SelectPdFamilyInfo(argBD00.getPdfRd());

        if(objEpdFamilyInfo!=null) {

            //往输出DTO中填值
            objEGetPdfInfoResD.setPdfRd(objEpdFamilyInfo.getRuid());
            objEGetPdfInfoResD.setPdfName(objEpdFamilyInfo.getFamilyName());
            //获取序号
            SerialRuleInfo objESerialRuleInfo = serialRuleDAO.SelectSerialRuleInfoByguid(objEpdFamilyInfo.getSerialRuleGd());

            if (objESerialRuleInfo != null) {
                objEGetPdfInfoResD.setSRRd(objESerialRuleInfo.getRuid());
                objEGetPdfInfoResD.setSRName(objESerialRuleInfo.getRuleName());
            }

            //获取打印模板
            PrintTInfo printTInfo=printTDAO.SelectByGuid(objEpdFamilyInfo.getPrintTGd());
            if(printTInfo!=null){
                objEGetPdfInfoResD.setPtRd(printTInfo.getRuid());
                objEGetPdfInfoResD.setPtName(printTInfo.getTempName());
            }

            objEGetPdfInfoResD.setCreator(objEpdFamilyInfo.getCreator());
            objEGetPdfInfoResD.setCreateTime(DateUtil.format(objEpdFamilyInfo.getCreateTime()));
            objEGetPdfInfoResD.setLastModifyMan(objEpdFamilyInfo.getLastModifyMan());
            objEGetPdfInfoResD.setLastModifyTime(DateUtil.format(objEpdFamilyInfo.getLastModifyTime()));
            objEGetPdfInfoResD.setRemark(objEpdFamilyInfo.getRemark());
        }
        objEGetPdfInfoResB.setData(objEGetPdfInfoResD);
        objEGetPdfInfoRes.setBody(objEGetPdfInfoResB);

        return objEGetPdfInfoRes;
    }

    //新增产品族信息
    public SavePdfInfoRes AddPdFamilyInfo(SavePdfInfoReqBD00 argBD00) throws SystemException {

        SavePdfInfoRes objESavePdfInfoRes = new SavePdfInfoRes();

        PdFamilyInfo objEPdFamilyInfo = new PdFamilyInfo();

        //校验家族名称不能出现重复，不能为空--LFZ
        List<PdFamilyInfo> objEPdFamilyInfos=pdFamilyDAO.SelectAllPdFamilyInfo();

        //逻辑校验保存的家族名称不能相同--LFZ
        for (PdFamilyInfo obj:objEPdFamilyInfos){
            if (obj.getFamilyName().equals(argBD00.getPdfName())){
                throw new SystemException("MG0006F","家族名称已存在");
            }
        }

        objEPdFamilyInfo.setGuid(CommonUtils.getRandomNumber());
        objEPdFamilyInfo.setFamilyName(argBD00.getPdfName());
        //获取序号
        SerialRuleInfo objESerialRuleInfo = serialRuleDAO.SelectSerialRuleInfoByruid(argBD00.getSRRd());

        if(objESerialRuleInfo!=null){
            objEPdFamilyInfo.setSerialRuleGd(objESerialRuleInfo.getGuid());
        }else {
            objEPdFamilyInfo.setSerialRuleGd("");
        }

        PrintTInfo printTInfo=printTDAO.SelectPrintTInfo(argBD00.getPtRd());
        if(printTInfo!=null){
            objEPdFamilyInfo.setPrintTGd(printTInfo.getGuid());
        }else {
            objEPdFamilyInfo.setPrintTGd("");
        }

        objEPdFamilyInfo.setCreator(CommonUtils.readUser().getRealName());
        objEPdFamilyInfo.setCreateTime(new Date());
        objEPdFamilyInfo.setRemark(argBD00.getRemark());

        if(pdFamilyDAO.InsertPdFamilyInfo(objEPdFamilyInfo) <= 0){
            throw new SystemException("MG000001", "新增产品家族失败");
        }

        return objESavePdfInfoRes;
    }

    //删除产品族信息
    public SavePdfInfoRes RmPdFamilyInfo(SavePdfInfoReqBD01 argBD01) throws SystemException {

        SavePdfInfoRes objESavePdfInfoRes = new SavePdfInfoRes();

        //ToDo 删除逻辑
        if(pdFamilyDAO.DeletePdFamilyInfo(argBD01.getPdfRd()) <= 0){
            throw new SystemException("MG000001", "产品家族删除失败");
        }

        return objESavePdfInfoRes;
    }

    //修改产品族信息
    public SavePdfInfoRes ModPdFamilyInfo(SavePdfInfoReqBD02 argBD02) throws SystemException {

        SavePdfInfoRes objESavePdfInfoRes = new SavePdfInfoRes();
        PdFamilyInfo objEPdFamilyInfo = new PdFamilyInfo();

        //查询产品家族信息--LFZ
        PdFamilyInfo objEpdFamilyInfo=pdFamilyDAO.SelectPdFamilyInfo(argBD02.getPdfRd());

        if(objEpdFamilyInfo==null){
            throw new SystemException("MG000001", "产品家族信息为空");
        }

        //查询产品家族名称--LFZ
        PdFamilyInfo objePdFamilyInfo=pdFamilyDAO.SelectfamilyName(argBD02.getPdfName());

        if(objePdFamilyInfo!=null && !objePdFamilyInfo.getFamilyName().equals(objEpdFamilyInfo.getFamilyName())){
            throw new SystemException("MG0006F","更新失败，产品家族名称已存在");
        }

        objEPdFamilyInfo.setRuid(argBD02.getPdfRd());
        objEPdFamilyInfo.setFamilyName(argBD02.getPdfName());
        //获取序号
        SerialRuleInfo objESerialRuleInfo = serialRuleDAO.SelectSerialRuleInfoByruid(argBD02.getSRRd());

        if(objESerialRuleInfo!=null) {
            objEPdFamilyInfo.setSerialRuleGd(objESerialRuleInfo.getGuid());
        }else{
            objEPdFamilyInfo.setSerialRuleGd("");
        }

        PrintTInfo printTInfo=printTDAO.SelectPrintTInfo(argBD02.getPtRd());
        if(printTInfo!=null){
            objEPdFamilyInfo.setPrintTGd(printTInfo.getGuid());
        }else {
            objEPdFamilyInfo.setPrintTGd("");
        }

        objEPdFamilyInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        objEPdFamilyInfo.setLastModifyTime(new Date());
        objEPdFamilyInfo.setRemark(argBD02.getRemark());

        if(pdFamilyDAO.UpdatePdFamilyInfo(objEPdFamilyInfo) <= 0){
            throw new SystemException("MG000001", "修改产品家族失败");
        }

        return objESavePdfInfoRes;
    }

    //复制
    public SavePdfInfoRes AddCopyPdFamilyInfo(SavePdfInfoReqBD03 argBD03) throws SystemException {

        SavePdfInfoRes objESavePdfInfoRes = new SavePdfInfoRes();

        PdFamilyInfo objEPdFamilyInfo = pdFamilyDAO.SelectPdFamilyInfo(argBD03.getPdfRd());

        if(objEPdFamilyInfo == null){
            throw new SystemException("MG000001", "没有找到产品家族信息");
        }

        objEPdFamilyInfo.setGuid(CommonUtils.getRandomNumber());
        objEPdFamilyInfo.setFamilyName(objEPdFamilyInfo.getFamilyName());
        objEPdFamilyInfo.setSerialRuleGd(objEPdFamilyInfo.getSerialRuleGd());
        objEPdFamilyInfo.setCreator(objEPdFamilyInfo.getCreator());
        objEPdFamilyInfo.setCreateTime(objEPdFamilyInfo.getCreateTime());
        objEPdFamilyInfo.setLastModifyMan(objEPdFamilyInfo.getLastModifyMan());
        objEPdFamilyInfo.setLastModifyTime(objEPdFamilyInfo.getLastModifyTime());
        objEPdFamilyInfo.setRemark(objEPdFamilyInfo.getRemark());

        if(pdFamilyDAO.InsertPdFamilyInfo(objEPdFamilyInfo) <= 0){
            throw new SystemException("MG000001", "复制产品家族信息失败");
        }

        //查询产品家族信息
        PdFamilyInfo objePdFamilyInfo=pdFamilyDAO.SelectByGuid(objEPdFamilyInfo.getGuid());

        objePdFamilyInfo.setFamilyName(objePdFamilyInfo.getFamilyName()+objePdFamilyInfo.getRuid());

        if(pdFamilyDAO.UpdatePdFamilyInfo(objePdFamilyInfo)<=0){
            throw new SystemException("MG000001", "修改产品家族失败");
        }

        return objESavePdfInfoRes;
    }

    //递归查询子节点
    public List<GetAllPdfInfoResD> SelectChild(List<PdFamilyInfo> pdFamilyInfos){
        List<GetAllPdfInfoResD> dataList =  new ArrayList<GetAllPdfInfoResD>();
        GetAllPdfInfoResD pdfInfo = null;

        for(PdFamilyInfo obj : pdFamilyInfos) {

            pdfInfo = new GetAllPdfInfoResD();

            //通过gd查询物料类别
            List<PdFamilyInfo> pdFamilyInfoList = pdFamilyDAO.selectList(new LambdaQueryWrapper<PdFamilyInfo>().eq(PdFamilyInfo::getpId, obj.getGuid()));

            if (pdFamilyInfoList.size() > 0) {
                pdfInfo.setChildren(SelectChild(pdFamilyInfoList));
            }else{
                pdfInfo.setChildren(new ArrayList<GetAllPdfInfoResD>());
            }

            pdfInfo.setPdfRd(obj.getRuid());
            pdfInfo.setPdfName(obj.getFamilyName());
            dataList.add(pdfInfo);
        }

        return dataList;
    }
}
