package pnc.mesadmin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.GetAllReq;
import pnc.mesadmin.dto.GetCMBBInfo.GetCMBBInfoReq00;
import pnc.mesadmin.dto.GetCMBBInfo.GetCMBBInfoResD;
import pnc.mesadmin.dto.GetCMBBInfo.GetCMBBInfoResDUnitInfo;
import pnc.mesadmin.dto.GetWOInfo.GetWOInfoReqBD00;
import pnc.mesadmin.dto.SaveReq;
import pnc.mesadmin.dto.SaveUDMaterailDto.SaveUdMateriaLallotRequest;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.MaVerInfo;
import pnc.mesadmin.entity.UnitInfo;
import pnc.mesadmin.entity.WmsMaterialsBInfo;
import pnc.mesadmin.entity.WoInfo;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;
import pnc.mesadmin.dto.SaveUDMaterailDto.GetAllUDMaterialsRespons;
import pnc.mesadmin.dto.SaveUDMaterailDto.GetAllUdMaterialLogsPageRequest;
import pnc.mesadmin.dto.SaveUDMaterailDto.SaveUDMaterailRequest;
import pnc.mesadmin.entity.UDMaterialLogInfo;
import pnc.mesadmin.service.UDMaterialPlusService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称 装卸物料Controller
 * 创建时间：2019-06-15
 * 修改人：yin.yang
 * 修改时间：
 */
@Controller
@RequestMapping("/UDMaterialFPPlus")
public class UDMaterialPlusController {

    @Resource
    private UDMaterialPlusService service;

    @Resource
    private MaVerDAO maVerDAO;

    @Resource
    private WmsMaterialsBDAO materialsBDAO;
    @Resource
    private UnitDAO unitDAO;

    @Resource
    private UDMaterialLogDao udMaterialLogDao;
    @Resource
    private WODAO wodao;
    @Resource
    private SpecVerDAO specVerDAO;
    @Resource
    private ULineMaterialDetailInfoDao uLineMaterialDetailInfoDao;

    @RequestMapping(value = "SetupMPG")
    public String setupmView() {
        return "mprocess/setupm";
    }

    //上下料日志查询
    @RequestMapping(value = "GetUDMaterialLogList")
    public String GetUDMaterialLogList() {
        return "layui/GetUDMaterialLogList";
    }

    //上下料（余料）查询
    @RequestMapping(value = "GetUDMaterialList")
    public String GetUDMaterialList() {
        return "layui/GetUDMaterialList";
    }

    /**
     * @Description 保存上下料接口
     * @Author yin.yang
     * @Date 2019/12/19
     * @Param
     * @Return
     * @Exception
     */
    @RequestMapping(value = "/SaveUDMaterial", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse SaveUDMaterial(SaveReq saveReq) {
        SaveUDMaterailRequest res = CommonUtils.switchClass(SaveUDMaterailRequest.class, saveReq.getBusData());
        try {
            if ("00".equals(saveReq.getExecType())) {
                service.AddSaveUDMaterial(res);
            }
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
        return BaseResponse.SUCCESS;
    }

    /**
     * @Description 根据工单号查询所有上下物料记录料
     * @Author yin.yang
     * @Date 2019/12/19
     * @Param
     * @Return
     * @Exception
     */
    @RequestMapping(value = "/GetAllByWoAndSpec", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllByWoCode(@RequestBody SaveUDMaterailRequest request) {
        try {
            return service.GetAllByWoCode(request);
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    //dto查询工单信息
    @RequestMapping(value = "/GetWOInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetWOInfo(GetAllReq getAllReq) {
        GetWOInfoReqBD00 objEGetWOInfoReqBD00 = CommonUtils.switchClass(GetWOInfoReqBD00.class, getAllReq.getBusData());
        try {
            return service.GetGetWOInfoRes(objEGetWOInfoReqBD00);
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    //根据物料批次查询详细信息
    @RequestMapping(value = "/GetMaterialBatch", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetMaterialBatch(GetAllReq getAllReq) {
        GetCMBBInfoReq00 request = CommonUtils.switchClass(GetCMBBInfoReq00.class, getAllReq.getBusData());
        try {
            if (request == null || StringUtils.isBlank(request.getBatch()))
                throw new SystemException("EEEE", "请求参数不能为空");
            WmsMaterialsBInfo wmsMaterialsBInfo = materialsBDAO.selectOne(new QueryWrapper<WmsMaterialsBInfo>().eq("batch", request.getBatch()));
            if (wmsMaterialsBInfo == null)
                throw new SystemException("EEEE", request.getBatch() + "未找到当前物料批次的信息");
            GetCMBBInfoResD objEGetCMBBInfoResD = new GetCMBBInfoResD();
            GetCMBBInfoResDUnitInfo getCMBBInfoResDUnitInfo = new GetCMBBInfoResDUnitInfo();
            MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(wmsMaterialsBInfo.getMaVerGd());
            if (objEMaVerInfo == null)
                throw new SystemException("EEEE", request.getBatch() + "未找到当前物料批次对应物料信息");
            objEGetCMBBInfoResD.setBatch(request.getBatch());
            objEGetCMBBInfoResD.setMaVerRd(objEMaVerInfo.getRuid());
            objEGetCMBBInfoResD.setMaCode(objEMaVerInfo.getMaterialCode());
            objEGetCMBBInfoResD.setMaName(objEMaVerInfo.getMaterialName());
            objEGetCMBBInfoResD.setMaDes(objEMaVerInfo.getMaterialDes());
            objEGetCMBBInfoResD.setMaType(objEMaVerInfo.getMaterialType());
            objEGetCMBBInfoResD.setVersion(objEMaVerInfo.getVersion());
            objEGetCMBBInfoResD.setMaStatus(objEMaVerInfo.getStatus());
            objEGetCMBBInfoResD.setNum(wmsMaterialsBInfo.getNum().floatValue());
            objEGetCMBBInfoResD.setCanNum(wmsMaterialsBInfo.getCanNum().floatValue());
            objEGetCMBBInfoResD.setIQCStatus(wmsMaterialsBInfo.getiQCStatus());
            //查询单位
            UnitInfo unitInfo = unitDAO.SelectByGuid(objEMaVerInfo.getUnitGd());
            if (unitInfo != null) {
                getCMBBInfoResDUnitInfo.setUnitRd(unitInfo.getRuid());
                getCMBBInfoResDUnitInfo.setUnitName(unitInfo.getUnitName());
            }
            objEGetCMBBInfoResD.setUnitInfo(getCMBBInfoResDUnitInfo);
            objEGetCMBBInfoResD.setStatus(wmsMaterialsBInfo.getStatus());
            return BaseResponse.success(objEGetCMBBInfoResD);
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    /**
     * @Description 分页查询上料日志
     * @Author yin.yang
     * @Date 2020/10/18
     * @Param
     * @Return
     * @Exception
     */
    @RequestMapping(value = "/GetAllUDMaterialLogs", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllUDMaterialLogs(GetAllUdMaterialLogsPageRequest pageRequest) {
        return service.GetAllUDMaterialLogs(pageRequest);
    }

    //查询所有正常工单
    @RequestMapping(value = "/GetAllWoInfo", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse GetAllWoInfo() {
        List<WoInfo> woInfoList = wodao.SeleAllWoCodeByWoTGd("00"); //00标准工单
        return BaseResponse.success(woInfoList);
    }

    /**
     * @Description 查询workflow中的所有工序
     * @Author yin.yang
     * @Date 2020/7/13
     * @Param
     * @Return
     * @Exception
     */
    @RequestMapping(value = "/GetAllSepcVers", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse GetAllSepcVers() {
        return BaseResponse.success(specVerDAO.SelectSpecVerInfo());
    }

    /**
     * @Description 分页查询产线余料信息
     * @Author yin.yang
     * @Date 2020/7/13
     * @Param
     * @Return
     * @Exception
     */
    @RequestMapping(value = "/GetAllUDMaterials", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllUDMaterials(GetAllUdMaterialLogsPageRequest pageRequest) {
        return service.GetAllUDMaterials(pageRequest);
    }


    /**
     * @Description 产线余料调拨
     * @Author yin.yang
     * @Date 2020/7/13
     * @Param
     * @Return
     * @Exception
     */
    @RequestMapping(value = "/SaveUdMateriaLallot", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse SaveUdMateriaLallot(@RequestBody SaveUdMateriaLallotRequest request) {
        try {
            return service.SaveUdMateriaLallot(request);
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

}
