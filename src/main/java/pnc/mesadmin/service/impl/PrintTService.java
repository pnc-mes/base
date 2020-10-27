package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllPtInfo.GetAllPtInfoRes;
import pnc.mesadmin.dto.GetAllPtInfo.GetAllPtInfoResB;
import pnc.mesadmin.dto.GetAllPtInfo.GetAllPtInfoResD;
import pnc.mesadmin.dto.GetPtBFInfo.GetPtBFInfoRes;
import pnc.mesadmin.dto.GetPtBFInfo.GetPtBFInfoResB;
import pnc.mesadmin.dto.GetPtBFInfo.GetPtBFInfoResD;
import pnc.mesadmin.dto.GetPtInfo.*;
import pnc.mesadmin.dto.SavePtInfo.*;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.PrintTIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;
import pnc.mesadmin.utils.FastfdsUtils;
import pnc.mesadmin.utils.MyPage;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：模板管理信息业务信息逻辑
 * 创建人：潘俊峰
 * 创建时间：2017-05-27
 * 修改人：
 * 修改时间：
 */
@Service
@Transactional
public class PrintTService implements PrintTIService {
    @Resource
    private PrintTDAO printTDAO;

    @Resource
    private PrintTBFDAO printTBFDAO;

    @Resource
    private PrintTFDAO printTFDAO;

    @Resource
    private BaseIService baseIService;

    @Resource
    private PrintScriptInDAO printScriptInDAO;

    @Resource
    private PrintScriptOutDAO printScriptOutDAO;

    //获取全部打印模板信息
    public GetAllPtInfoRes QALLPrintTInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException {
        GetAllPtInfoRes objEGetAllPtInfoRes = new GetAllPtInfoRes();
        GetAllPtInfoResB objEGetAllPtInfoResB = new GetAllPtInfoResB();
        List<GetAllPtInfoResD> objEGetAllPtInfoResDs = new ArrayList<GetAllPtInfoResD>();

        List<PrintTInfo> objEPrintTInfo = baseIService.QALLBaseInfo(PrintTDAO.class, "SelectAllPrintTInfo",
                argInitDataFields, argPageInfo);

        if (objEGetAllPtInfoRes != null) {
            for (PrintTInfo printTInfo : objEPrintTInfo) {
                GetAllPtInfoResD objEGetAllPtInfoResD = new GetAllPtInfoResD();
                objEGetAllPtInfoResD.setPtRd(printTInfo.getRuid());
                objEGetAllPtInfoResD.setPtName(printTInfo.getTempName());
                objEGetAllPtInfoResDs.add(objEGetAllPtInfoResD);
            }
            objEGetAllPtInfoResB.setData(objEGetAllPtInfoResDs);
        }

        objEGetAllPtInfoRes.setBody(objEGetAllPtInfoResB);

        return objEGetAllPtInfoRes;
    }

    /**
     * 获取打印模板列表信息(新)
     * @param req
     * @return
     */
    @Override
    public PageResult<GetAllPtInfoResD> QALLPrintTNew(BaseRequest req) {
        List<GetAllPtInfoResD> resDList = new ArrayList<GetAllPtInfoResD>();
        GetAllPtInfoResD resD = null;

        IPage<PrintTInfo> iPage = printTDAO.selectPage(new MyPage(req), CommonUtils.getQueryWrapper(req.getFields()));

        for (PrintTInfo printTInfo : iPage.getRecords()) {
            resD = new GetAllPtInfoResD();
            resD.setPtRd(printTInfo.getRuid());
            resD.setPtName(printTInfo.getTempName());
            resDList.add(resD);
        }

        return new PageResult<>(iPage, resDList);
    }

    //获取打印模板信息
    public GetPtInfoRes GetPrintTInfo(GetPtInfoReqBD00 argBD00) throws SystemException {
        GetPtInfoRes objEGetPtInfoRes = new GetPtInfoRes();
        GetPtInfoResB objEGetPtInfoResB = new GetPtInfoResB();
        GetPtInfoResD objEGetPtInfoResD = new GetPtInfoResD();
        List<GetPtInfoResDBFInfo> objEGetPtInfoResDBFInfos = new ArrayList<GetPtInfoResDBFInfo>();

        // 查询模板信息
        PrintTInfo objEPrintTInfo = printTDAO.SelectPrintTInfo(argBD00.getPtRd());
        if (objEPrintTInfo == null) {
            throw new SystemException("MG000001", "模板信息为空");
        }
        objEGetPtInfoResD.setPtRd(objEPrintTInfo.getRuid());
        objEGetPtInfoResD.setPtName(objEPrintTInfo.getTempName());
        objEGetPtInfoResD.setFileName(objEPrintTInfo.getFileName());
        objEGetPtInfoResD.setCreator(objEPrintTInfo.getCreator());
        objEGetPtInfoResD.setCreateTime(DateUtil.format(objEPrintTInfo.getCreateTime()));
        objEGetPtInfoResD.setLastModifyMan(objEPrintTInfo.getLastModifyMan());
        objEGetPtInfoResD.setLastModifyTime(DateUtil.format(objEPrintTInfo.getLastModifyTime()));
        objEGetPtInfoResD.setRemark(objEPrintTInfo.getRemark());

        // 查询该模板绑定的所有字段信息
        List<PrintTBFInfo> objEPrintTBFInfos = printTBFDAO.SelectAllPrintTBFInfo(objEPrintTInfo.getGuid());
        for (PrintTBFInfo printTBFInfo : objEPrintTBFInfos) {
            GetPtInfoResDBFInfo objEGetPtInfoResDBFInfo = new GetPtInfoResDBFInfo();
            objEGetPtInfoResDBFInfo.setBFRd(printTBFInfo.getRuid());
            objEGetPtInfoResDBFInfo.setBFCode(printTBFInfo.getFieldCode());
            objEGetPtInfoResDBFInfo.setBFName(printTBFInfo.getFieldName());
            objEGetPtInfoResDBFInfo.setBFSource(printTBFInfo.getFSouce());
            objEGetPtInfoResDBFInfos.add(objEGetPtInfoResDBFInfo);
        }
        objEGetPtInfoResD.setBFInfo(objEGetPtInfoResDBFInfos);

        //获取打印模板输入输出信息
        List<PrintScriptInInfo> printScriptInInfoList = printScriptInDAO.selectByPrintTGd(objEPrintTInfo.getGuid());
        List<PrintScriptOutInfo> printScriptOutInfoList = printScriptOutDAO.selectByPrintTGd(objEPrintTInfo.getGuid());
        GetPtInfoResD.Customer customer = new GetPtInfoResD.Customer();
        List<GetPtInfoResD.InParams> inParamsList = new ArrayList<>();
        List<GetPtInfoResD.OutParams> outParamsList = new ArrayList<>();

        for (PrintScriptInInfo model : printScriptInInfoList) {
            GetPtInfoResD.InParams inParams = new GetPtInfoResD.InParams();
            inParams.setFieldCode(model.getFieldCode());
            inParams.setFieldSource(model.getFieldSource());
            inParams.setVal(model.getVal());
            inParamsList.add(inParams);
        }
        for (PrintScriptOutInfo model : printScriptOutInfoList) {
            GetPtInfoResD.OutParams outParams = new GetPtInfoResD.OutParams();
            outParams.setFieldCode(model.getFieldCode());
            outParams.setFieldName(model.getFieldName());
            outParamsList.add(outParams);
        }
        customer.setIsScript(objEPrintTInfo.getIsScript());
        customer.setScriptName(objEPrintTInfo.getScriptName());
        customer.setInParams(inParamsList);
        customer.setOutParams(outParamsList);
        objEGetPtInfoResD.setCustomer(customer);
        objEGetPtInfoResB.setData(objEGetPtInfoResD);
        objEGetPtInfoRes.setBody(objEGetPtInfoResB);
        return objEGetPtInfoRes;
    }

    //新增打印模板信息
    public SavePtInfoRes AddPrintTInfo(HttpServletRequest request, SavePtInfoReqBD00 argBD00) throws SystemException, IOException {
        SavePtInfoRes objESavePtInfoRes = new SavePtInfoRes();

        //Map<String, String> path = FastfdsUtils.upload(request, "btw,xlsx");

        //模板信息Guid
        String guid = CommonUtils.getRandomNumber();
        //当前时间
        Date date = new Date();
        //当前创建人
        String creator = CommonUtils.readUser().getRealName();

        //判断模板名称是否重复
        PrintTInfo printTInfo = printTDAO.SelectByTempName(argBD00.getPtName());
        if (printTInfo != null) {
            throw new SystemException("xxx", "模板名称已存在");
        }

        if (argBD00.getCustomer() == null || argBD00.getCustomer().getIsScript() == null) {
            throw new SystemException("xxx", "请选择脚本实现！");
        }

        //往打印模板信息表里插值
        PrintTInfo objEPrintTInfo = new PrintTInfo();
        objEPrintTInfo.setGuid(guid);
        objEPrintTInfo.setTempName(argBD00.getPtName());
        objEPrintTInfo.setFileName(/*path.get(argBD00.getFileName())*/argBD00.getFileName());
        objEPrintTInfo.setCreator(creator);
        objEPrintTInfo.setCreateTime(date);
        objEPrintTInfo.setRemark(argBD00.getRemark());
        objEPrintTInfo.setIsScript(argBD00.getCustomer().getIsScript());
        objEPrintTInfo.setScriptName(argBD00.getCustomer().getScriptName());
        if (printTDAO.InsertPrintTInfo(objEPrintTInfo) <= 0) {
            throw new SystemException("MG000001", "模板信息新增失败");
        }

        //往打印模板绑定字段信息表里插值
        for (SavePtInfoReqBD00BFInfo bfInfo : argBD00.getBFInfo()) {
            PrintTBFInfo objEPrintTBFInfo = new PrintTBFInfo();
            objEPrintTBFInfo.setGuid(CommonUtils.getRandomNumber());
            objEPrintTBFInfo.setPrintTGd(guid);
            objEPrintTBFInfo.setFieldCode(bfInfo.getBFCode());
            objEPrintTBFInfo.setFieldName(bfInfo.getBFName());
            objEPrintTBFInfo.setFSouce(bfInfo.getBFSource());
            objEPrintTBFInfo.setCreator(creator);
            objEPrintTBFInfo.setCreateTime(date);
            objEPrintTBFInfo.setRemark(argBD00.getRemark());
            if (printTBFDAO.InsertPrintTBFInfo(objEPrintTBFInfo) <= 0) {
                throw new SystemException("MG000001", "模板字段信息新增失败");
            }
        }

        //新增脚本输入参数信息表
        for (SavePtInfoReqBD00.InParams inParams : argBD00.getCustomer().getInParams()) {
            PrintScriptInInfo printScriptInInfo = new PrintScriptInInfo();
            printScriptInInfo.setGuid(CommonUtils.getRandomNumber());
            printScriptInInfo.setCreateTime(new Date());
            printScriptInInfo.setCreator(CommonUtils.readUser().getRealName());
            printScriptInInfo.setPrintTGd(guid);
            printScriptInInfo.setRemark(argBD00.getRemark());
            printScriptInInfo.setFieldCode(inParams.getFieldCode());
            printScriptInInfo.setFieldSource(inParams.getFieldSource());
            printScriptInInfo.setVal(inParams.getVal());
            printScriptInDAO.InsertPrintScriptInInfo(printScriptInInfo);
        }

        for (SavePtInfoReqBD00.OutParams outParams : argBD00.getCustomer().getOutParams()) {
            //新增脚本输出参数信息表
            PrintScriptOutInfo printScriptOutInfo = new PrintScriptOutInfo();
            printScriptOutInfo.setGuid(CommonUtils.getRandomNumber());
            printScriptOutInfo.setCreateTime(new Date());
            printScriptOutInfo.setCreator(CommonUtils.readUser().getRealName());
            printScriptOutInfo.setPrintTGd(guid);
            printScriptOutInfo.setRemark(argBD00.getRemark());
            printScriptOutInfo.setFieldCode(outParams.getFieldCode());
            printScriptOutInfo.setFieldName(outParams.getFieldName());
            printScriptOutDAO.InsertPrintScriptOutInfo(printScriptOutInfo);
            //新增打印模板绑定字段信息表
            PrintTBFInfo printTBFInfo = new PrintTBFInfo();
            printTBFInfo.setGuid(CommonUtils.getRandomNumber());
            printTBFInfo.setCreateTime(new Date());
            printTBFInfo.setCreator(CommonUtils.readUser().getRealName());
            printTBFInfo.setPrintTGd(guid);
            printTBFInfo.setFSouce("01");
            printTBFInfo.setRemark(argBD00.getRemark());
            printTBFInfo.setFieldCode(outParams.getFieldCode());
            printTBFInfo.setFieldName(outParams.getFieldName());
            printTBFDAO.InsertPrintTBFInfo(printTBFInfo);
        }
        return objESavePtInfoRes;
    }

    //删除打印模板信息
    public SavePtInfoRes RmPrintTInfo(SavePtInfoReqBD01 argBD01) throws SystemException {
        SavePtInfoRes objESavePtInfoRes = new SavePtInfoRes();

        //根据ruid查打印模板信息
        PrintTInfo objEPrintTInfo = printTDAO.SelectPrintTInfo(argBD01.getPtRd());

        List<PrintTBFInfo> objEPrintTBFInfos = printTBFDAO.SelectAllPrintTBFInfo(objEPrintTInfo.getGuid());

        if (objEPrintTBFInfos != null && objEPrintTBFInfos.size() > 0) {
            if (printTBFDAO.DeletePrintTBFInfo(objEPrintTInfo.getGuid()) <= 0) {
                throw new SystemException("MG000001", "删除打印模板信息失败");
            }
        }

        if (printTDAO.DeletePrintTInfo(objEPrintTInfo.getGuid()) <= 0) {
            throw new SystemException("MG000001", "删除打印模板信息失败");
        }
        //删除脚本输入
        printScriptInDAO.DelPrintScriptInInfoByPrintTGd(objEPrintTInfo.getGuid());
        //删除脚本输出
        printScriptOutDAO.DelPrintScriptOutInfoBYPGD(objEPrintTInfo.getGuid());

        //删除模板文件
        //FastfdsUtils.delete(objEPrintTInfo.getFileName());

        return objESavePtInfoRes;
    }

    //编辑打印模板信息
    public SavePtInfoRes ModPrintTInfo(HttpServletRequest request, SavePtInfoReqBD02 argBD02) throws SystemException, IOException {
        SavePtInfoRes objESavePtInfoRes = new SavePtInfoRes();

        //根据ruid查打印模板信息
        PrintTInfo objEPrintTInfo = printTDAO.SelectPrintTInfo(argBD02.getPtRd());
        if (objEPrintTInfo == null) {
            throw new SystemException("MG000001", "打印模板信息为空");
        }

        //判断模板名称是否重复
        PrintTInfo printTInfo = printTDAO.SelectByTempName(argBD02.getPtName());
        if (printTInfo != null && !objEPrintTInfo.getTempName().equals(printTInfo.getTempName())) {
            throw new SystemException("xxx", "模板名称已存在");
        }

        if (argBD02.getCustomer() == null || argBD02.getCustomer().getIsScript() == null) {
            throw new SystemException("xxx", "请选择脚本实现！");
        }

        //添加文件
        Set<String> oldName = new HashSet<String>();
        oldName.add(objEPrintTInfo.getFileName());
        //Map<String, String> path = FastfdsUtils.upload(request, "btw,xlsx");

        //获取当前用户
        String userName = CommonUtils.readUser().getRealName();
        //获取当前时间
        Date date = new Date();

        //修改打印模板信息
        objEPrintTInfo.setTempName(argBD02.getPtName());
        /*if (path.containsKey(argBD02.getFileName())) {
            FastfdsUtils.delete(objEPrintTInfo.getFileName());
            objEPrintTInfo.setFileName(path.get(argBD02.getFileName()));
        }*/
        objEPrintTInfo.setFileName(argBD02.getFileName());
        objEPrintTInfo.setLastModifyMan(userName);
        objEPrintTInfo.setLastModifyTime(date);
        objEPrintTInfo.setRemark(argBD02.getRemark());
        objEPrintTInfo.setIsScript(argBD02.getCustomer().getIsScript());
        objEPrintTInfo.setScriptName(argBD02.getCustomer().getScriptName());
        if (printTDAO.UpdatePrintTInfo(objEPrintTInfo) <= 0) {
            throw new SystemException("MG000001", "修改打印模板信息失败");
        }

        //根据PrintTGd查该打印模板下的所有绑定字段
        List<PrintTBFInfo> objEPrintTBFInfos = printTBFDAO.SelectAllPrintTBFInfo(objEPrintTInfo.getGuid());

        if (null != argBD02.getBFInfo()) {
            for (SavePtInfoReqBD02BFInfo bfInfo : argBD02.getBFInfo()) {

                if (bfInfo.getBFRd() != 0) {
                    for (int i = 0; i < objEPrintTBFInfos.size(); i++) {
                        if (bfInfo.getBFRd() == objEPrintTBFInfos.get(i).getRuid()) {
                            PrintTBFInfo printTBFInfo = objEPrintTBFInfos.get(i);
                            printTBFInfo.setFieldCode(bfInfo.getBFCode());
                            printTBFInfo.setFieldName(bfInfo.getBFName());
                            printTBFInfo.setFSouce(bfInfo.getBFSource());
                            printTBFInfo.setLastModifyMan(userName);
                            printTBFInfo.setLastModifyTime(date);
                            if (printTBFDAO.UpdateByRuid(printTBFInfo) <= 0) {
                                throw new SystemException("", "打印模板字段修改失败");
                            }
                            objEPrintTBFInfos.remove(i);
                            break;
                        }
                    }
                } else {
                    //往PrintTBFInfo中插入
                    PrintTBFInfo printTBFInfo = new PrintTBFInfo();
                    printTBFInfo.setGuid(CommonUtils.getRandomNumber());
                    printTBFInfo.setPrintTGd(objEPrintTInfo.getGuid());
                    printTBFInfo.setFieldCode(bfInfo.getBFCode());
                    printTBFInfo.setFieldName(bfInfo.getBFName());
                    printTBFInfo.setFSouce(bfInfo.getBFSource());
                    printTBFInfo.setCreator(userName);
                    printTBFInfo.setCreateTime(date);
                    printTBFInfo.setRemark(argBD02.getRemark());
                    if (printTBFDAO.InsertPrintTBFInfo(printTBFInfo) <= 0) {
                        throw new SystemException("MG000001", "修改打印模板信息失败");
                    }
                }
            }
        }

        for (int i = 0; i < objEPrintTBFInfos.size(); i++) {
            if (printTBFDAO.DeleteByGuid(objEPrintTBFInfos.get(i).getGuid()) <= 0) {
                throw new SystemException("MG000001", "修改打印模板信息失败");
            }
        }

        printScriptInDAO.DelPrintScriptInInfoByPrintTGd(objEPrintTInfo.getGuid());
        printScriptOutDAO.DelPrintScriptOutInfoBYPGD(objEPrintTInfo.getGuid());
        //新增脚本输入参数信息表
        for (SavePtInfoReqBD02.InParams inParams : argBD02.getCustomer().getInParams()) {
            PrintScriptInInfo printScriptInInfo = new PrintScriptInInfo();
            printScriptInInfo.setGuid(CommonUtils.getRandomNumber());
            printScriptInInfo.setCreateTime(new Date());
            printScriptInInfo.setCreator(CommonUtils.readUser().getRealName());
            printScriptInInfo.setPrintTGd(objEPrintTInfo.getGuid());
            printScriptInInfo.setRemark(argBD02.getRemark());
            printScriptInInfo.setFieldCode(inParams.getFieldCode());
            printScriptInInfo.setFieldSource(inParams.getFieldSource());
            printScriptInInfo.setVal(inParams.getVal());
            printScriptInDAO.InsertPrintScriptInInfo(printScriptInInfo);
        }

        for (SavePtInfoReqBD02.OutParams outParams : argBD02.getCustomer().getOutParams()) {
            //新增脚本输出参数信息表
            PrintScriptOutInfo printScriptOutInfo = new PrintScriptOutInfo();
            printScriptOutInfo.setGuid(CommonUtils.getRandomNumber());
            printScriptOutInfo.setCreateTime(new Date());
            printScriptOutInfo.setCreator(CommonUtils.readUser().getRealName());
            printScriptOutInfo.setPrintTGd(objEPrintTInfo.getGuid());
            printScriptOutInfo.setRemark(argBD02.getRemark());
            printScriptOutInfo.setFieldCode(outParams.getFieldCode());
            printScriptOutInfo.setFieldName(outParams.getFieldName());
            printScriptOutDAO.InsertPrintScriptOutInfo(printScriptOutInfo);
        }

        return objESavePtInfoRes;
    }

    public GetPtBFInfoRes QALLPrintTFInfo() throws SystemException {
        GetPtBFInfoRes objEGetPtBFInfoRes = new GetPtBFInfoRes();
        GetPtBFInfoResB objEGetPtBFInfoResB = new GetPtBFInfoResB();
        List<GetPtBFInfoResD> objEGetPtBFInfoResDs = new ArrayList<GetPtBFInfoResD>();

        //查询模板业务字段信息（1）
        List<PrintTFInfo> objEPrintTFInfos = printTFDAO.SelectAllPrintTFInfo();
        //TODO 查询模板业务字段信息（2）

        if (objEPrintTFInfos != null && objEPrintTFInfos.size() > 0) {
            for (PrintTFInfo printTFInfo : objEPrintTFInfos) {
                GetPtBFInfoResD objEGetPtBFInfoResD = new GetPtBFInfoResD();
                objEGetPtBFInfoResD.setBFCode(printTFInfo.getFieldCode());
                objEGetPtBFInfoResD.setBFName(printTFInfo.getFieldName());
                objEGetPtBFInfoResDs.add(objEGetPtBFInfoResD);
            }
        }

        objEGetPtBFInfoResB.setData(objEGetPtBFInfoResDs);
        objEGetPtBFInfoRes.setBody(objEGetPtBFInfoResB);

        return objEGetPtBFInfoRes;
    }
}
