package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.GetSpecBInfo.*;
import pnc.mesadmin.dto.SaveIOSInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.SpecOperateIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.FastfdsUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：车间管理业务信息逻辑
 * 创建人：潘俊峰
 * 创建时间：2020-09-16
 * 修改人：
 * 修改时间：
 */
@Service
@Transactional
public class SpecOperateService implements SpecOperateIService {

    @Resource
    private BDAO bdao;

    @Resource
    private MaVerDAO maVerDAO;

    @Resource
    private WFVerDAO wfVerDAO;

    @Resource
    private SpecVerDAO specVerDAO;

    @Resource
    private BomMaterialDAO bomMaterialDAO;

    @Resource
    private BomVerDAO bomVerDAO;

    @Resource
    private WODAO wodao;

    @Resource
    private BCirDAO bCirDAO;

    @Resource
    private BCirDlDAO bCirDlDAO;

    @Resource
    private BCirCDAO bCirCDAO;

    @Resource
    private UnitDAO unitDAO;

    @Resource
    private OpertDAO opertDAO;

    @Resource
    private DevDAO devDAO;

    @Resource
    private DevGpDtlDAO devGpDtlDAO;

    @Resource
    private SOPDlDAO sopDlDAO;

    @Resource
    private FileVerDAO fileVerDAO;

    @Resource
    private FileGpDtlDAO fileGpDtlDAO;

    @Resource
    private DCItemDAO dcItemDAO;

    @Resource
    private ReasonDAO reasonDAO;

    @Resource
    private ReasongDAO reasongDAO;

    @Resource
    private AffairDAO affairDAO;

    @Resource
    private WFSpecDAO wfSpecDAO;

    @Resource
    private WFCirDAO wfCirDAO;

    @Resource
    private DoDCDAO doDCDAO;

    @Resource
    private DoMaDAO doMaDAO;

    @Resource
    private DoRcDAO doRcDAO;

    @Resource
    private ULineMaterialInfoDao uLineMaterialInfoDao;

    @Resource
    private ULineMaterialDetailInfoDao uLineMaterialDetailInfoDao;

    @Resource
    private WmsMaterialsBDAO wmsMaterialsBDAO;

    @Resource
    private BomReMaterialDAO bomReMaterialDAO;

    @Resource
    private SOPDAO sopdao;

    /**
     * 获取工序批次信息
     * @param batch
     * @return
     * @throws SystemException
     */
    @Override
    public GetSpecBInfoResD GetSpecBInfo(String batch) throws SystemException {
        GetSpecBInfoResD objEGetSpecBInfoResD = new GetSpecBInfoResD();
        List<GetSpecBInfoResDDevG> objEGetSpecBInfoResDDevGs = new ArrayList<GetSpecBInfoResDDevG>();
        List<GetSpecBInfoResDSOP> objEGetSpecBInfoResDSOPs = new ArrayList<GetSpecBInfoResDSOP>();
        List<GetSpecBInfoResDWJSpec> objEGetSpecBInfoResDWJSpecs = new ArrayList<GetSpecBInfoResDWJSpec>();
        List<GetSpecBInfoResDWCSpec> objEGetSpecBInfoResDWCSpecs = new ArrayList<GetSpecBInfoResDWCSpec>();
        List<GetSpecBInfoResDBOM> objEGetSpecBInfoResDBOMs = new ArrayList<GetSpecBInfoResDBOM>();
        List<GetSpecBInfoResDDC> objEGetSpecBInfoResDDCs = new ArrayList<GetSpecBInfoResDDC>();
        List<GetSpecBInfoResDRCG> objEGetSpecBInfoResDRCGs = new ArrayList<GetSpecBInfoResDRCG>();

        //查询批次信息
        BInfo objEBInfo = bdao.selectBatchInfoByBatch(batch);
        if(objEBInfo == null){
            throw new SystemException("", "该批次不存在");
        }
        if("04".equals(objEBInfo.getStatus()) || "02".equals(objEBInfo.getwFStatus())){
            throw new SystemException("", "该批次已完成");
        }

        //查询工单信息
        WoInfo woInfo = wodao.SelectWoInfoByGuid(objEBInfo.getWoGd());
        if(woInfo == null){
            throw new SystemException("", "批次对应的工单信息不存在");
        }

        //查询物料版本
        MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(objEBInfo.getMaVerGd());
        if(objEMaVerInfo == null){
            throw new SystemException("", "该批次对应对的物料信息不存在");
        }

        //查询工艺流程版本
        WFVerInfo objEWFVerInfo = wfVerDAO.SelectByGuid(objEBInfo.getwFVerGd());
        if(objEWFVerInfo == null){
            throw new SystemException("", "该批次对应的工艺流程版本不存在");
        }

        //查询工序版本
        SpecVerInfo objESpecVerInfo = null;

        //查询生产流转信息
        BCirInfo objEBCirInfo = bCirDAO.SelectByBatch(objEBInfo.getBatch());
        if(objEBCirInfo == null){
            objESpecVerInfo = specVerDAO.SelectSpecVerInfoByguid(objEBInfo.getSpecVerGd());
            objEGetSpecBInfoResD.setStatus("00");
        }else{
            objESpecVerInfo = specVerDAO.SelectSpecVerInfoByguid(objEBCirInfo.getSpecVerGd());
            objEGetSpecBInfoResD.setStatus(objEBCirInfo.getStatus());
        }
        if("02".equals(objEBInfo.getwFStatus())){ //已完成
            objEGetSpecBInfoResD.setStatus("04");
        }
        if(objESpecVerInfo == null){
            throw new SystemException("", "该工序版本为空");
        }

        //查询单位
        UnitInfo objEUnitInfo = unitDAO.SelectByGuid(objEBInfo.getUnitGd());
        if(objEUnitInfo == null){
            throw new SystemException("", "该批次对应的单位为空");
        }

        //设备组ID
        String devGrGd = objESpecVerInfo.getDevGrGd();
        //数据采集ID
        String dcVerGd = objESpecVerInfo.getdCVerGd();
        String dcVerGdAfter = objESpecVerInfo.getAfDCVerGd();
        //文件组ID
        String fileGrGd = objESpecVerInfo.getFileGrGd();

        //查询生产工艺控制
        SOPInfo sopInfo = sopdao.selectOne(new LambdaQueryWrapper<SOPInfo>()
                .eq(SOPInfo::getMaVerGd, objEMaVerInfo.getGuid())
                .eq(SOPInfo::getWFVerGd, objEWFVerInfo.getGuid()));
        if(sopInfo != null){
            SOPDlInfo sopDlInfo = sopDlDAO.selectOne(new LambdaQueryWrapper<SOPDlInfo>()
                    .eq(SOPDlInfo::getSOPGd, sopInfo.getGuid())
                    .eq(SOPDlInfo::getSpecVerGd, objESpecVerInfo.getGuid()));
            //查询工序工艺文件信息
            if(sopDlInfo != null){
                if(sopDlInfo.getFileGrGd() != null){
                    fileGrGd = sopDlInfo.getFileGrGd();
                }
                if(sopDlInfo.getDevGrGd() != null){
                    devGrGd = sopDlInfo.getDevGrGd();
                }
                if(sopDlInfo.getDCVerGd() != null){
                    dcVerGd = sopDlInfo.getDCVerGd();
                }
            }
        }

        //查询作业
        OpertInfo objEOpertInfo = opertDAO.GetOpertInfoByGuid(objESpecVerInfo.getOpertGd());
        if(objEOpertInfo == null){
            throw new SystemException("", "该作业为空");
        }

        //判断该批次是否在包装作业
        if("00".equals(objEOpertInfo.getPackopt())){
            throw new SystemException("", "该批次在包装操作中，请去另一个页面操作");
        }

        //查询文件组明细
        List<FileGpDtlInfo> objEFileGpDtlInfos = fileGpDtlDAO.SelectByguid(fileGrGd);
        //查询文件版本信息
        FileVerInfo fileVerInfo = null;
        GetSpecBInfoResDSOP resDSOP = null;
        for(FileGpDtlInfo fileGpDtlInfo : objEFileGpDtlInfos){
            fileVerInfo = fileVerDAO.SelectByFileVerGd(fileGpDtlInfo.getFileVerGd());
            if(fileVerInfo == null){
                continue;
            }
            resDSOP = new GetSpecBInfoResDSOP();
            resDSOP.setFileName(fileVerInfo.getFileName());
            resDSOP.setFilePath(fileVerInfo.getFilePath());
            objEGetSpecBInfoResDSOPs.add(resDSOP);
        }

        //查询设备组明细
        List<DevGpDtlInfo> objEDevGpDtInfos = devGpDtlDAO.SelectByguid(devGrGd);
        DevInfo devInfo = null;
        GetSpecBInfoResDDevG resDDevG = null;
        for(DevGpDtlInfo devGpDtlInfo : objEDevGpDtInfos) {
            //查询设备
            devInfo = devDAO.SelectByguid(devGpDtlInfo.getDevGd());
            if(devInfo != null){
                resDDevG = new GetSpecBInfoResDDevG();
                resDDevG.setDevRd(devInfo.getRuid());
                resDDevG.setDevName(devInfo.getDevName());

                if(objEBCirInfo != null) {
                    if (devInfo.getGuid().equals(objEBCirInfo.getDevGd())) {
                        resDDevG.setSelected(true);
                    }
                }

                objEGetSpecBInfoResDDevGs.add(resDDevG);
            }
        }

        //TODO 查询该工单下所有批次
        List<BInfo> objEBInfos = bdao.SelectByWoGd(objEBInfo.getWoGd());
        if(objEBInfos == null){
            throw new SystemException("", "工单批次列表为空");
        }
        GetSpecBInfoResDWJSpec resDWJSpec = null;
        GetSpecBInfoResDWCSpec resDWCSpec = null;

        for(BInfo bInfo : objEBInfos) {
            //查询流转信息
            BCirInfo bCirInfo = bCirDAO.SelectByBatch(bInfo.getBatch());
            if(bCirInfo == null){
                if(bInfo.getSpecVerGd().equals(objESpecVerInfo.getGuid())){
                    //等待进站批次
                    resDWJSpec = new GetSpecBInfoResDWJSpec();
                    resDWJSpec.setBatch(bInfo.getBatch());
                    //查询该批次生产的物料
                    MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(bInfo.getMaVerGd());
                    if(maVerInfo == null){
                        throw new SystemException("", "该物料不存在");
                    }
                    resDWJSpec.setMaCode(maVerInfo.getMaterialCode());
                    resDWJSpec.setMaName(maVerInfo.getMaterialName());
                    resDWJSpec.setMaDes(maVerInfo.getMaterialDes());
                    resDWJSpec.setSpecName(objESpecVerInfo.getSpecName());
                    resDWJSpec.setNum(bInfo.getNum());
                    //查询单位
                    UnitInfo unitInfo = unitDAO.SelectByGuid(objEBInfo.getUnitGd());
                    if(unitInfo == null){
                        throw new SystemException("", "该单位为空");
                    }

                    resDWJSpec.setUnitName(unitInfo.getUnitName());
                    resDWJSpec.setOptName(objEOpertInfo.getOptname());
                    objEGetSpecBInfoResDWJSpecs.add(resDWJSpec);
                }
            }else{
                if(bCirInfo.getSpecVerGd().equals(objESpecVerInfo.getGuid())){
                    if("00".equals(bCirInfo.getStatus())){
                        //等待进站批次
                        resDWJSpec = new GetSpecBInfoResDWJSpec();
                        resDWJSpec.setBatch(bInfo.getBatch());
                        //查询该批次生产的物料
                        MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(bInfo.getMaVerGd());
                        if(maVerInfo == null){
                            throw new SystemException("", "该物料不存在");
                        }
                        resDWJSpec.setMaCode(maVerInfo.getMaterialCode());
                        resDWJSpec.setMaName(maVerInfo.getMaterialName());
                        resDWJSpec.setMaDes(maVerInfo.getMaterialDes());
                        resDWJSpec.setSpecName(objESpecVerInfo.getSpecName());
                        resDWJSpec.setNum(bInfo.getNum());
                        //查询单位
                        UnitInfo unitInfo = unitDAO.SelectByGuid(objEBInfo.getUnitGd());
                        if(unitInfo == null){
                            throw new SystemException("1", "该单位为空");
                        }
                        resDWJSpec.setUnitName(unitInfo.getUnitName());
                        resDWJSpec.setOptName(objEOpertInfo.getOptname());
                        objEGetSpecBInfoResDWJSpecs.add(resDWJSpec);
                    }else if("03".equals(bCirInfo.getStatus())){
                        if(!"02".equals(bInfo.getwFStatus())){
                            //等待出站批次
                            resDWCSpec = new GetSpecBInfoResDWCSpec();
                            resDWCSpec.setBatch(bInfo.getBatch());
                            //查询该批次生产的物料
                            MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(bInfo.getMaVerGd());
                            if (maVerInfo == null) {
                                throw new SystemException("", "该物料不存在");
                            }
                            resDWCSpec.setMaCode(maVerInfo.getMaterialCode());
                            resDWCSpec.setMaName(maVerInfo.getMaterialName());
                            resDWCSpec.setMaDes(maVerInfo.getMaterialDes());
                            resDWCSpec.setSpecName(objESpecVerInfo.getSpecName());
                            resDWCSpec.setNum(bInfo.getNum());
                            //查询单位
                            UnitInfo unitInfo = unitDAO.SelectByGuid(objEBInfo.getUnitGd());
                            if (unitInfo == null) {
                                throw new SystemException("1", "该单位为空");
                            }
                            resDWCSpec.setUnitName(unitInfo.getUnitName());
                            resDWCSpec.setOptName(objEOpertInfo.getOptname());
                            objEGetSpecBInfoResDWCSpecs.add(resDWCSpec);
                        }
                    }
                }
            }
        }

        //物料清单
        //根据物料查Bom清单版本
        BomVerInfo objEBomVerInfo = bomVerDAO.SelectBomVerInfo(woInfo.getBomVerGd());
        if(objEBomVerInfo != null){
            //根据清单版本查用料
            List<BomMaterialInfo> objEBomMaterialInfos = bomMaterialDAO.SelectByBomVerGd(objEBomVerInfo.getGuid());
            for(BomMaterialInfo bomMaterialInfo : objEBomMaterialInfos){
                //过滤当前工序用料
                if(objESpecVerInfo.getGuid().equals(bomMaterialInfo.getSpecVerGd())){
                    GetSpecBInfoResDBOM objEGetSpecBInfoResDBOM = new GetSpecBInfoResDBOM();
                    //查询物料
                    MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(bomMaterialInfo.getMaVerGd());
                    if(maVerInfo == null){
                        throw new SystemException("", "Bom所需用料不存在");
                    }
                    objEGetSpecBInfoResDBOM.setMaVerRd(maVerInfo.getRuid());
                    objEGetSpecBInfoResDBOM.setMaCode(maVerInfo.getMaterialCode());
                    objEGetSpecBInfoResDBOM.setMaName(maVerInfo.getMaterialName());
                    objEGetSpecBInfoResDBOM.setMaDes(maVerInfo.getMaterialDes());
                    objEGetSpecBInfoResDBOM.setDoNum(bomMaterialInfo.getNum() * objEBInfo.getNum());
                    objEGetSpecBInfoResDBOM.setUnitName(bomMaterialInfo.getUnitName());

                    objEGetSpecBInfoResDBOMs.add(objEGetSpecBInfoResDBOM);
                }
            }
        }

        if(objEBCirInfo != null) {
            //只有上机和下机显示数据采集
            //查询数据采集项信息表
            List<DCItemInfo> objEDCItemInfos = new ArrayList<>();
            if("01".equals(objEBCirInfo.getStatus())) {
                objEDCItemInfos = dcItemDAO.selectDCItemInfosByDCVerGd(dcVerGd);
            }else if("02".equals(objEBCirInfo.getStatus())){
                objEDCItemInfos = dcItemDAO.selectDCItemInfosByDCVerGd(dcVerGdAfter);
            }
            GetSpecBInfoResDDC resDDC = null;
            for (DCItemInfo dcItemInfo : objEDCItemInfos) {
                resDDC = new GetSpecBInfoResDDC();
                resDDC.setDCItemRd(dcItemInfo.getRuid());
                resDDC.setItemName(dcItemInfo.getItemName());
                resDDC.setReferVal(dcItemInfo.getsValue());
                objEGetSpecBInfoResDDCs.add(resDDC);
            }
        }

        //标记不良 查询原因代码组明细
        List<ReaCodeGdlInfo> objEReaCodeGdlInfos = reasongDAO.SelectReaCodeGdlInfoByRCGGd(objEOpertInfo.getReaCGGd());
        GetSpecBInfoResDRCG resDRCG = null;
        ReaCodeInfo reaCodeInfo = null;
        for (ReaCodeGdlInfo reaCodeGdlInfo : objEReaCodeGdlInfos) {
            //查询原因代码
            reaCodeInfo = reasonDAO.SelectReacodeInfoByguid(reaCodeGdlInfo.getReaCGd());
            if (reaCodeInfo != null) {
                resDRCG = new GetSpecBInfoResDRCG();
                resDRCG.setReaCode(reaCodeInfo.getReaCode());
                resDRCG.setReaDes(reaCodeInfo.getReaDes());
                objEGetSpecBInfoResDRCGs.add(resDRCG);
            }
        }

        //写入DTO
        objEGetSpecBInfoResD.setDevGInfo(objEGetSpecBInfoResDDevGs);
        objEGetSpecBInfoResD.setSOPInfo(objEGetSpecBInfoResDSOPs);
        objEGetSpecBInfoResD.setWJSpecInfo(objEGetSpecBInfoResDWJSpecs);
        objEGetSpecBInfoResD.setWCSpecInfo(objEGetSpecBInfoResDWCSpecs);
        objEGetSpecBInfoResD.setBOMInfo(objEGetSpecBInfoResDBOMs);
        objEGetSpecBInfoResD.setDCInfo(objEGetSpecBInfoResDDCs);
        objEGetSpecBInfoResD.setRCGInfo(objEGetSpecBInfoResDRCGs);

        objEGetSpecBInfoResD.setMaCode(objEMaVerInfo.getMaterialCode());
        objEGetSpecBInfoResD.setMaName(objEMaVerInfo.getMaterialName());
        objEGetSpecBInfoResD.setMaDes(objEMaVerInfo.getMaterialDes());
        objEGetSpecBInfoResD.setWFName(objEWFVerInfo.getwFName());
        objEGetSpecBInfoResD.setSpecVerRd(objESpecVerInfo.getRuid());
        objEGetSpecBInfoResD.setSpecName(objESpecVerInfo.getSpecName());
        objEGetSpecBInfoResD.setNum(objEBInfo.getNum());
        objEGetSpecBInfoResD.setUnitName(objEUnitInfo.getUnitName());
        objEGetSpecBInfoResD.setOptName(objEOpertInfo.getOptname());

        return objEGetSpecBInfoResD;
    }

    /**
     * 工序入站
     * @param argBD00
     * @return
     * @throws SystemException
     */
    @Override
    public SaveIOSInfoRes AddInput(SaveIOSInfoReqBD00 argBD00) throws SystemException {
        SaveIOSInfoRes objESaveIOSInfoRes = new SaveIOSInfoRes();

        //当前用户名
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();

        //查询批次信息表
        BInfo objEBInfo = bdao.selectBatchInfoByBatch(argBD00.getBatch());
        if(objEBInfo == null){
            throw new SystemException("", "该批次信息为空");
        }
        //判断该批次信息的状态
        if("02".equals(objEBInfo.getStatus())){
            throw new SystemException("", "该批次已冻结");
        }
        if("03".equals(objEBInfo.getStatus())){
            throw new SystemException("", "该批次已报废");
        }
        if("04".equals(objEBInfo.getStatus()) || "02".equals(objEBInfo.getwFStatus())){
            throw new SystemException("", "该批次已完成");
        }

        //查询工序
        SpecVerInfo objESpecVerInfo = null;

        //判断是否为起始工序
        BCirInfo objEBCirInfo = bCirDAO.SelectByBatch(argBD00.getBatch());
        if(objEBCirInfo == null){

            objEBCirInfo  = new BCirInfo();
            //成产流转信息表Guid
            String bCirGuid = CommonUtils.getRandomNumber();
            objEBCirInfo.setGuid(bCirGuid);
            objEBCirInfo.setWoBGd(objEBInfo.getGuid());
            objEBCirInfo.setBatch(objEBInfo.getBatch());
            //查询物料版本
            MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(objEBInfo.getMaVerGd());
            if(objEMaVerInfo == null){
                throw new SystemException("", "该物料版本为空");
            }
            objEBCirInfo.setMaVerGd(objEMaVerInfo.getGuid());
            objEBCirInfo.setMaterialCode(objEMaVerInfo.getMaterialCode());
            objEBCirInfo.setMaterialName(objEMaVerInfo.getMaterialName());
            objEBCirInfo.setVersion(objEMaVerInfo.getVersion());
            objEBCirInfo.setNum(objEBInfo.getNum());
            //查询单位
            UnitInfo objEUnitInfo = unitDAO.SelectByGuid(objEBInfo.getUnitGd());
            if(objEUnitInfo == null){
                throw new SystemException("", "该单位为空");
            }
            objEBCirInfo.setUnitName(objEUnitInfo.getUnitName());
            objEBCirInfo.setwFVerGd(objEBInfo.getwFVerGd());

            //查询工序版本
            objESpecVerInfo = specVerDAO.SelectSpecVerInfoByguid(objEBInfo.getSpecVerGd());
            if(objESpecVerInfo == null){
                throw new SystemException("", "该工序版本为空");
            }
            if("01".equals(objESpecVerInfo.getStatus())){
                throw new SystemException("", "该工序不可用");
            }

            //判断是否拥有该工序操作权限
            /*if(!isSpecOpert(objESpecVerInfo.getSkillGGd())){
                throw new SystemException("", "您不拥有操作改工序的权限");
            }*/

            objEBCirInfo.setlSpecVerGd(objESpecVerInfo.getGuid());
            objEBCirInfo.setlSpecName(objESpecVerInfo.getSpecName());
            objEBCirInfo.setSpecVerGd(objESpecVerInfo.getGuid());
            objEBCirInfo.setSpecName(objESpecVerInfo.getSpecName());

            //查询作业
            OpertInfo objEOpertInfo = opertDAO.GetOpertInfoByGuid(objESpecVerInfo.getOpertGd());
            if(objEOpertInfo == null){
                throw new SystemException("", "该作业为空");
            }
            objEBCirInfo.setlOpertGd(objEOpertInfo.getGuid());
            objEBCirInfo.setlOptName(objEOpertInfo.getOptname());
            objEBCirInfo.setOpertGd(objEOpertInfo.getGuid());
            objEBCirInfo.setOptName(objEOpertInfo.getOptname());

            //查询设备组明细
            List<DevGpDtlInfo> objEDevGpDtInfos = devGpDtlDAO.SelectByguid(objESpecVerInfo.getDevGrGd());
            if(objEDevGpDtInfos != null && objEDevGpDtInfos.size() > 0){
                objEBCirInfo.setStatus("01");
            }else{
                objEBCirInfo.setStatus("03");
            }

            //objEBCirInfo.setStatus("01");
            objEBCirInfo.setCreator(userName);
            objEBCirInfo.setCreateTime(date);
            bCirDAO.InsertBCir(objEBCirInfo);

            //新增生产流转信息日志
            BCirDlInfo objEBCirDlInfo = new BCirDlInfo();
            objEBCirDlInfo.setGuid(CommonUtils.getRandomNumber());
            objEBCirDlInfo.setWoBWGd(objEBCirInfo.getWoBGd());
            objEBCirDlInfo.setBatch(objEBCirInfo.getBatch());
            objEBCirDlInfo.setsOpertGd(objEBCirInfo.getOpertGd());
            objEBCirDlInfo.setsOptName(objEBCirInfo.getOptName());
            objEBCirDlInfo.settSpecVerGd(objESpecVerInfo.getGuid());
            objEBCirDlInfo.settSpecName(objESpecVerInfo.getSpecName());
            objEBCirDlInfo.settOpertGd(objEOpertInfo.getGuid());
            objEBCirDlInfo.settOptName(objEOpertInfo.getOptname());
            objEBCirDlInfo.setFlowFlag("00");
            objEBCirDlInfo.setCreator(userName);
            objEBCirDlInfo.setCreateTime(date);
            bCirDlDAO.InsertBCirDl(objEBCirDlInfo);

            //修改批次状态
            objEBInfo.setStatus("01");
            objEBInfo.setwFStatus("01");
            objEBInfo.setLastModifyMan(userName);
            objEBInfo.setLastModifyTime(date);
            if(bdao.UpdateBatchInfoByRuid(objEBInfo) <= 0){
                throw new SystemException("", "修改批次状态失败");
            }

            //修改工单状态
            WoInfo objEWoInfo = wodao.SelectWoInfoByGuid(objEBInfo.getWoGd());
            if(objEWoInfo == null){
                throw new SystemException("", "工单不存在");
            }
            objEWoInfo.setStatus("01");
            objEWoInfo.setLastModifyMan(userName);
            objEWoInfo.setLastModifyTime(date);
            if(wodao.UpdateWoInfo(objEWoInfo) <= 0){
                throw new SystemException("", "修改工单状态失败");
            }

        }else{
            if(!"00".equals(objEBCirInfo.getStatus())){
                throw new SystemException("", "已经入站");
            }

            //查询工序版本
            objESpecVerInfo = specVerDAO.SelectSpecVerInfoByguid(objEBCirInfo.getSpecVerGd());
            if(objESpecVerInfo == null){
                throw new SystemException("", "该工序版本为空");
            }
            if("01".equals(objESpecVerInfo.getStatus())){
                throw new SystemException("", "该工序不可用");
            }

            //判断是否拥有该工序操作权限
            /*if(!isSpecOpert(objESpecVerInfo.getSkillGGd())){
                throw new SystemException("", "您不拥有操作改工序的权限");
            }*/

            //查询作业
            OpertInfo objEOpertInfo = opertDAO.GetOpertInfoByGuid(objESpecVerInfo.getOpertGd());
            if(objEOpertInfo == null){
                throw new SystemException("", "该作业为空");
            }

            //查询设备组明细
            List<DevGpDtlInfo> objEDevGpDtInfos = devGpDtlDAO.SelectByguid(objESpecVerInfo.getDevGrGd());
            if(objEDevGpDtInfos != null && objEDevGpDtInfos.size() > 0){

                objEBCirInfo.setStatus("01");
            }else{
                objEBCirInfo.setStatus("03");
            }

            objEBCirInfo.setLastModifyMan(userName);
            objEBCirInfo.setLastModifyTime(date);
            if(bCirDAO.UpdateBCir(objEBCirInfo) <= 0){
                throw new SystemException("", "入站失败");
            }
        }

        //查询当前工序有没有设备
        if(StringUtils.isBlank(objESpecVerInfo.getDevGrGd())) {
            //查询工单
            WoInfo woInfo = wodao.SelectWoInfoByGuid(objEBInfo.getWoGd());
            if (woInfo == null) {
                throw new SystemException("", "该批次工单信息不存在");
            }
            //物料清单
            //根据物料查Bom清单版本
            BomVerInfo objEBomVerInfo = bomVerDAO.SelectBomVerInfo(woInfo.getBomVerGd());
            if (objEBomVerInfo != null) {
                //根据清单版本查用料
                List<BomMaterialInfo> objEBomMaterialInfos = bomMaterialDAO.selectList(new LambdaQueryWrapper<BomMaterialInfo>()
                        .eq(BomMaterialInfo::getBomVerGd, objEBomVerInfo.getGuid())
                        .eq(BomMaterialInfo::getConSMode, "01")
                        .eq(BomMaterialInfo::getSpecVerGd, objESpecVerInfo.getGuid()));
                if (objEBomMaterialInfos.size() > 0) {
                    //查询当前工序存在物料信息
                    ULineMaterialInfo uLineMaterialInfo = uLineMaterialInfoDao.selectOne(
                            new LambdaQueryWrapper<ULineMaterialInfo>()
                                    .eq(ULineMaterialInfo::getSpecGuid, objESpecVerInfo.getGuid())
                                    .eq(ULineMaterialInfo::getWoCode, woInfo.getWoCode()));
                    if (uLineMaterialInfo == null) {
                        throw new SystemException("", "该工序还未上料");
                    }
                    List<ULineMaterialDetailInfo> uLineMaterialDetailInfos = uLineMaterialDetailInfoDao.selectList(
                            new LambdaQueryWrapper<ULineMaterialDetailInfo>()
                                    .eq(ULineMaterialDetailInfo::getUlmGuid, uLineMaterialInfo.getGuid())
                                    .eq(ULineMaterialDetailInfo::getStatus, "00")
                                    .eq(ULineMaterialDetailInfo::getLuType, "00"));
                    //数据转换
                    Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
                    BigDecimal num = BigDecimal.ZERO;
                    for (ULineMaterialDetailInfo obj : uLineMaterialDetailInfos) {
                        if (map.containsKey(obj.getMaterialCode())) {
                            num = map.get(obj.getMaterialCode()).add(obj.getCanNum());
                        } else {
                            num = obj.getCanNum();
                        }
                        map.put(obj.getMaterialCode(), num);
                    }

                    //BOM清单替代料
                    Map<String, Set<BomReMaterialInfo>> reMap = new HashMap<>();
                    Set<BomReMaterialInfo> oSet = null;
                    List<BomReMaterialInfo> bomReMaterialInfos = bomReMaterialDAO.SelectBomReMaterialInfo(woInfo.getGuid(), objESpecVerInfo.getGuid());
                    for (BomReMaterialInfo bomRe : bomReMaterialInfos) {
                        if (reMap.containsKey(bomRe.getBomMaGd())) {
                            oSet = reMap.get(bomRe.getBomMaGd());
                        } else {
                            oSet = new HashSet<>();
                        }
                        oSet.add(bomRe);
                        reMap.put(bomRe.getBomMaGd(), oSet);
                    }

                    MaVerInfo reMaverInfo = null;
                    BigDecimal allNum = null;

                    for (BomMaterialInfo bomMaterialInfo : objEBomMaterialInfos) {
                        //查询物料
                        MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(bomMaterialInfo.getMaVerGd());
                        if (maVerInfo == null) {
                            throw new SystemException("", "Bom所需用料不存在");
                        }

                        allNum = map.get(maVerInfo.getMaterialCode());
                        //查询替代料
                        if (reMap.containsKey(bomMaterialInfo.getGuid())) {
                            oSet = reMap.get((bomMaterialInfo.getGuid()));
                            for (BomReMaterialInfo re : oSet) {
                                //判断是否上料
                                reMaverInfo = maVerDAO.SelectMaverInfo(re.getMaVerGd());
                                if (reMaverInfo == null) {
                                    continue;
                                }
                                if (map.containsKey(reMaverInfo.getMaterialCode())) {
                                    if (allNum == null) {
                                        allNum = BigDecimal.ZERO;
                                    }
                                    allNum = allNum.add(map.get(reMaverInfo.getMaterialCode()));
                                }
                            }
                        }
                        if (allNum == null || allNum.compareTo(new BigDecimal(String.valueOf(bomMaterialInfo.getNum())).multiply(new BigDecimal(String.valueOf(objEBInfo.getNum())))) == -1) {
                            throw new SystemException("", "工序料不足，请上料");
                        }
                    }
                }
            }
        }

        //批次标记信息->记录不良代码
        if(argBD00.getDoRCInfo() != null){
            for(SaveIOSInfoReqBD01DoRCInfo doRCInfo : argBD00.getDoRCInfo()){
                DoRcInfo objEdoRcInfo = new DoRcInfo();
                objEdoRcInfo.setGuid(CommonUtils.getRandomNumber());
                objEdoRcInfo.setBatch(argBD00.getBatch());
                objEdoRcInfo.setSpecVerGd(objESpecVerInfo.getGuid());
                objEdoRcInfo.setSpecName(objESpecVerInfo.getSpecName());
                objEdoRcInfo.setDevGd("");
                objEdoRcInfo.setDevName("");
                objEdoRcInfo.setReaCode(doRCInfo.getReaCode());
                objEdoRcInfo.setReaDes(doRCInfo.getReaDes());
                objEdoRcInfo.setNum(doRCInfo.getNum());
                objEdoRcInfo.setCreator(userName);
                objEdoRcInfo.setCreateTime(date);
                doRcDAO.InsertDoRC(objEdoRcInfo);
            }
        }

        //批次事物操作
        AffairInfo objEAffairInfo = new AffairInfo();
        objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
        objEAffairInfo.setBatch(objEBCirInfo.getBatch());
        objEAffairInfo.setSpecVerGd(objEBCirInfo.getSpecVerGd());
        objEAffairInfo.setSpecName(objEBCirInfo.getSpecName());
        objEAffairInfo.setOptType("00");
        objEAffairInfo.setCreator(userName);
        objEAffairInfo.setCreateTime(date);
        affairDAO.InsertAffairInfo(objEAffairInfo);

        return objESaveIOSInfoRes;
    }

    /**
     * 工序出站
     * @param argBD01
     * @return
     * @throws SystemException
     */
    @Override
    public SaveIOSInfoRes AddOutput(SaveIOSInfoReqBD01 argBD01) throws SystemException {
        SaveIOSInfoRes objESaveIOSInfoRes = new SaveIOSInfoRes();
        SaveIOSInfoResB objESaveIOSInfoResB = new SaveIOSInfoResB();

        //当前用户名
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();
        //是否是最后一道工序 true （是）
        boolean specLast = false;

        //查询当前产品批次信息表
        BInfo objEBInfo = bdao.selectBatchInfoByBatch(argBD01.getBatch());

        if(objEBInfo == null){
            throw new SystemException("", "该批次信息为空");
        }

        //判断该批次信息的状态
        if("02".equals(objEBInfo.getStatus())){
            throw new SystemException("", "该批次已冻结");
        }
        if("03".equals(objEBInfo.getStatus())){
            throw new SystemException("", "该批次已报废");
        }
        if("04".equals(objEBInfo.getStatus()) || "02".equals(objEBInfo.getwFStatus())){
            throw new SystemException("", "该批次已完成");
        }
        if("00".equals(objEBInfo.getwFStatus())){
            throw new SystemException("", "还未进入工艺流程");
        }

        //查询工单
        WoInfo woInfo = wodao.SelectWoInfoByGuid(objEBInfo.getWoGd());
        if(woInfo == null){
            throw new SystemException("", "该批次工单信息不存在");
        }

        //查询工艺流程版本
        WFVerInfo objEWFVerInfo = wfVerDAO.SelectByGuid(objEBInfo.getwFVerGd());
        if(objEWFVerInfo == null){
            throw new SystemException("", "该工艺流程版本为空");
        }
        //查询生产流转表
        BCirInfo objEBCirInfo = bCirDAO.SelectByBatch(argBD01.getBatch());
        if(objEBCirInfo == null){
            throw new SystemException("", "还未入站");
        }
        if("00".equals(objEBCirInfo.getStatus())){
            throw new SystemException("", "还未入站");
        }

        //检查不良（作业）
        //查询作业
        OpertInfo objEOpertInfo = opertDAO.GetOpertInfoByGuid(objEBCirInfo.getOpertGd());
        if(objEOpertInfo == null){
            throw new SystemException("", "该作业为空");
        }

        //查询当前工序版本
        SpecVerInfo objESpecVerInfo = specVerDAO.SelectSpecVerInfoByguid(objEBCirInfo.getSpecVerGd());
        if(objESpecVerInfo == null){
            throw new SystemException("", "当前工序版本为空");
        }
        if("01".equals(objESpecVerInfo.getStatus())){
            throw new SystemException("", "该工序不可用");
        }
        //判断是否拥有该工序操作权限
        /*if(!isSpecOpert(objESpecVerInfo.getSkillGGd())){
            throw new SystemException("", "您不拥有操作改工序的权限");
        }*/

        //物料清单处理
        //物料清单
        MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(objEBInfo.getMaVerGd());
        if(objEMaVerInfo == null){
            throw new SystemException("", "物料版本为空");
        }

        //根据物料查Bom清单
        BomVerInfo objEBomVerInfo = bomVerDAO.SelectBomVerInfo(woInfo.getBomVerGd());
        if(objEBomVerInfo != null){
            //根据Bom清单+工序查询Bom用料
            List<BomMaterialInfo> objEBomMaterialInfos = bomMaterialDAO.SelectByBSGd(objEBomVerInfo.getGuid(), objESpecVerInfo.getGuid());
            if(objEBomMaterialInfos.size() > 0){
                //查询当前工序存在物料信息
                ULineMaterialInfo uLineMaterialInfo = uLineMaterialInfoDao.selectOne(
                        new LambdaQueryWrapper<ULineMaterialInfo>()
                                .eq(ULineMaterialInfo::getSpecGuid, objESpecVerInfo.getGuid())
                                .eq(ULineMaterialInfo::getWoCode, woInfo.getWoCode()));

                List<ULineMaterialDetailInfo> uLineMaterialDetailInfos = new ArrayList<>();

                if(uLineMaterialInfo != null){
                    uLineMaterialDetailInfos = uLineMaterialDetailInfoDao.selectList(
                            new LambdaQueryWrapper<ULineMaterialDetailInfo>()
                                    .eq(ULineMaterialDetailInfo::getUlmGuid, uLineMaterialInfo.getGuid())
                                    .eq(ULineMaterialDetailInfo::getStatus, "00")
                                    .eq(ULineMaterialDetailInfo::getLuType, "00"));
                }

                //数据转换
                Map<String, List<ULineMaterialDetailInfo>> map = new HashMap<String, List<ULineMaterialDetailInfo>>();
                List<ULineMaterialDetailInfo> detailInfoList = null;
                for(ULineMaterialDetailInfo obj : uLineMaterialDetailInfos){
                    if(map.containsKey(obj.getMaterialCode())){
                        detailInfoList = map.get(obj.getMaterialCode());
                    }else{
                        detailInfoList = new ArrayList<>();
                    }
                    detailInfoList.add(obj);
                    map.put(obj.getMaterialCode(), detailInfoList);
                }
                WmsMaterialsBInfo bInfo = null;
                MaVerInfo reMaverInfo = null;
                DoMaInfo objEDoMaInfo = null;

                //BOM清单替代料
                Map<String, Set<BomReMaterialInfo>> reMap = new HashMap<>();
                Set<BomReMaterialInfo> oSet = null;
                List<BomReMaterialInfo> bomReMaterialInfos = bomReMaterialDAO.SelectBomReMaterialInfo(woInfo.getGuid(), objESpecVerInfo.getGuid());
                for(BomReMaterialInfo bomRe : bomReMaterialInfos){
                    if(reMap.containsKey(bomRe.getBomMaGd())){
                        oSet = reMap.get(bomRe.getBomMaGd());
                    }else{
                        oSet = new HashSet<>();
                    }
                    oSet.add(bomRe);
                    reMap.put(bomRe.getBomMaGd(), oSet);
                }

                for (BomMaterialInfo bomMaterialInfo : objEBomMaterialInfos) {
                    //查询物料
                    MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(bomMaterialInfo.getMaVerGd());
                    if (maVerInfo == null) {
                        throw new SystemException("", "Bom所需用料不存在");
                    }

                    boolean isBreak = false;

                    //消耗方式(仅显示)
                    if("00".equals(bomMaterialInfo.getConSMode())){
                        continue;
                    }else if("01".equals(bomMaterialInfo.getConSMode())){
                        //消耗方式(批次)
                        detailInfoList = map.get(maVerInfo.getMaterialCode());
                        //查询替代料
                        if(reMap.containsKey(bomMaterialInfo.getGuid())){
                            oSet = reMap.get((bomMaterialInfo.getGuid()));
                            for(BomReMaterialInfo re : oSet){
                                //判断是否上料
                                reMaverInfo = maVerDAO.SelectMaverInfo(re.getMaVerGd());
                                if(reMaverInfo == null){
                                    continue;
                                }
                                if (map.containsKey(reMaverInfo.getMaterialCode())) {
                                    if(detailInfoList == null){
                                        detailInfoList = new ArrayList<>();
                                    }
                                    detailInfoList.addAll(map.get(reMaverInfo.getMaterialCode()));
                                }
                            }
                        }
                        if (detailInfoList == null || detailInfoList.size() <= 0) {
                            throw new SystemException("", "工序" + maVerInfo.getMaterialCode() + "料不足，请装料");
                        }

                        //当前工序可用数量
                        BigDecimal specCNum = BigDecimal.ZERO;
                        for (ULineMaterialDetailInfo o : detailInfoList) {
                            specCNum = specCNum.add(o.getCanNum());
                        }

                        //总消耗
                        BigDecimal allNum = new BigDecimal(String.valueOf(bomMaterialInfo.getNum())).multiply(new BigDecimal(String.valueOf(objEBInfo.getNum())));
                        BigDecimal num = specCNum.subtract(allNum); //差值
                        if (num.compareTo(BigDecimal.ZERO) == -1) {
                            throw new SystemException("", "工序" + maVerInfo.getMaterialCode() + "料不足，请装料");
                        }

                        for (ULineMaterialDetailInfo detailInfo : detailInfoList) {
                            //新增批次用料信息
                            objEDoMaInfo = new DoMaInfo();

                            //查询准备消耗批次
                            bInfo = wmsMaterialsBDAO.selectOne(new LambdaQueryWrapper<WmsMaterialsBInfo>()
                                    .eq(WmsMaterialsBInfo::getBatch, detailInfo.getLotNo()));
                            if (bInfo == null) {
                                throw new SystemException("", detailInfo.getLotNo() + "批次信息已不存在");
                            }
                            if (detailInfo.getCanNum().compareTo(bInfo.getCanNum()) != 0) {
                                throw new SystemException("", bInfo.getBatch() + "批次可用数量和上料数量不同,请检查");
                            }
                            allNum = allNum.subtract(detailInfo.getCanNum());
                            if (allNum.compareTo(BigDecimal.ZERO) > -1) {
                                //销毁批次
                                num = bInfo.getCanNum();
                                bInfo.setCanNum(BigDecimal.ZERO);
                                bInfo.setConSuNum(bInfo.getNum());
                                bInfo.setStatus("04");

                                detailInfo.setCanNum(BigDecimal.ZERO);
                                detailInfo.setStatus("01");

                                if (wmsMaterialsBDAO.updateById(bInfo) <= 0
                                        || uLineMaterialDetailInfoDao.updateById(detailInfo) <= 0) {
                                    throw new SystemException("", "出站消耗批次失败,请重试");
                                }

                                objEDoMaInfo.setDoNum(num.floatValue());
                            } else {
                                //更改批次
                                num = bInfo.getCanNum().add(allNum);
                                bInfo.setCanNum(allNum.negate());
                                bInfo.setConSuNum(bInfo.getConSuNum().add(num));

                                detailInfo.setCanNum(allNum.negate());

                                if (wmsMaterialsBDAO.updateById(bInfo) <= 0 || uLineMaterialDetailInfoDao.updateById(detailInfo) <= 0) {
                                    throw new SystemException("", "出站消耗批次失败,请重试");
                                }

                                objEDoMaInfo.setDoNum(num.floatValue());
                            }

                            objEDoMaInfo.setGuid(CommonUtils.getRandomNumber());
                            objEDoMaInfo.setBatch(objEBInfo.getBatch());
                            objEDoMaInfo.setSpecVerGd(objESpecVerInfo.getGuid());
                            objEDoMaInfo.setSpecName(objESpecVerInfo.getSpecName());
                            objEDoMaInfo.setConSMode("01");
                            objEDoMaInfo.setDevGd("");
                            objEDoMaInfo.setDevName("");
                            objEDoMaInfo.setDoBatch(bInfo.getBatch());
                            objEDoMaInfo.setMaVerGd(bInfo.getMaVerGd());
                            objEDoMaInfo.setMaterialCode(detailInfo.getMaterialCode());
                            objEDoMaInfo.setMaterialName(detailInfo.getMaterialName());
                            objEDoMaInfo.setUnitName(detailInfo.getUnitName());
                            objEDoMaInfo.setCreator(userName);
                            objEDoMaInfo.setCreateTime(date);
                            doMaDAO.InsertDoMaInfo(objEDoMaInfo);

                            if (allNum.compareTo(BigDecimal.ZERO) < 1) {
                                break;
                            }
                        }
                    }else if("02".equals(bomMaterialInfo.getConSMode())){
                        //消耗方式(序号)
                        continue;
                    }
                }
            }
        }

        //查询数据采集项信息表
        List<SaveIOSInfoReqBD01DoDCInfo> objESaveIOSInfoReqBD01DoDCInfos = argBD01.getDoDCInfo();
        if (objESaveIOSInfoReqBD01DoDCInfos != null) {
            for (SaveIOSInfoReqBD01DoDCInfo doDCInfo : objESaveIOSInfoReqBD01DoDCInfos) {
                //查询采集项信息
                DCItemInfo objEDCItemInfo = dcItemDAO.selectDCItemInfoByItemRd(doDCInfo.getDCItemRd());
                if (objEDCItemInfo == null) {
                    throw new SystemException("", "数据采集项信息为空");
                }

                String sCValue = doDCInfo.getCValue();

                AffairInfo objEAffairInfo = new AffairInfo();

                //判断是否合格
                if("00".equals(objEDCItemInfo.getItemType()) || "01".equals(objEDCItemInfo.getItemType())){
                    BigDecimal svalue = new BigDecimal(objEDCItemInfo.getsValue()), uplimit = new BigDecimal(objEDCItemInfo.getUpLimit())
                            , dwlimit = new BigDecimal(objEDCItemInfo.getDwLimit());

                    //最大上限值
                    BigDecimal nMax = svalue.add(uplimit);
                    //最大下限值
                    BigDecimal nMin = svalue.subtract(dwlimit);
                    //采集数据值
                    if("".equals(sCValue)){
                        throw new SystemException("", objEDCItemInfo.getItemName() + "--数据采集不能为空");
                    }

                    if("00".equals(objEDCItemInfo.getChecked())) {
                        BigDecimal nNew = new BigDecimal(sCValue);

                        if (nNew.compareTo(nMax) == 1 || nNew.compareTo(nMin) == -1) {
                            objEBInfo.setBad("00");
                            objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
                            objEAffairInfo.setBatch(objEBInfo.getBatch());
                            objEAffairInfo.setSpecVerGd(objESpecVerInfo.getGuid());
                            objEAffairInfo.setSpecName(objESpecVerInfo.getSpecName());
                            objEAffairInfo.setOptType("07");
                            objEAffairInfo.setCreator(userName);
                            objEAffairInfo.setCreateTime(date);
                            affairDAO.InsertAffairInfo(objEAffairInfo);
                        }
                    }

                }else if("02".equals(objEDCItemInfo.getItemType())){
                    if("00".equals(objEDCItemInfo.getChecked())) {
                        if (!sCValue.equals(objEDCItemInfo.getsValue())) {
                            objEBInfo.setBad("00");

                            objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
                            objEAffairInfo.setBatch(objEBInfo.getBatch());
                            objEAffairInfo.setSpecVerGd("");
                            objEAffairInfo.setSpecName("");
                            objEAffairInfo.setOptType("07");
                            objEAffairInfo.setCreator(userName);
                            objEAffairInfo.setCreateTime(date);
                            affairDAO.InsertAffairInfo(objEAffairInfo);
                        }
                    }
                }

                //如果不良则不允许出站
                if("00".equals(objEDCItemInfo.getChecked()) && "00".equals(objEDCItemInfo.getAction())
                        && "00".equals(objEBInfo.getBad())){
                    objEBInfo.setStatus("02");

                    objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
                    objEAffairInfo.setBatch(objEBInfo.getBatch());
                    objEAffairInfo.setSpecVerGd("");
                    objEAffairInfo.setSpecName("");
                    objEAffairInfo.setOptType("09");
                    objEAffairInfo.setCreator(userName);
                    objEAffairInfo.setCreateTime(date);
                    affairDAO.InsertAffairInfo(objEAffairInfo);
                }

                //新增批次数据采集信息表
                DoDCInfo objEDoDCInfo = new DoDCInfo();
                objEDoDCInfo.setGuid(CommonUtils.getRandomNumber());
                objEDoDCInfo.setBatch(objEBInfo.getBatch());
                objEDoDCInfo.setSpecVerGd(objESpecVerInfo.getGuid());
                objEDoDCInfo.setSpecName(objESpecVerInfo.getSpecName());
                objEDoDCInfo.setDevGd("");
                objEDoDCInfo.setDevName("");
                objEDoDCInfo.setdCItemGd(objEDCItemInfo.getDcVerGd());
                objEDoDCInfo.setItemName(objEDCItemInfo.getItemName());
                objEDoDCInfo.setcValue(doDCInfo.getCValue());
                objEDoDCInfo.setCreator(userName);
                objEDoDCInfo.setCreateTime(date);
                doDCDAO.InsertDoDcInfo(objEDoDCInfo);
            }
        }

        //批次标记信息->记录不良代码
        if(argBD01.getDoRCInfo() != null){
            for(SaveIOSInfoReqBD01DoRCInfo doRCInfo : argBD01.getDoRCInfo()){
                DoRcInfo objEdoRcInfo = new DoRcInfo();
                objEdoRcInfo.setGuid(CommonUtils.getRandomNumber());
                objEdoRcInfo.setBatch(argBD01.getBatch());
                objEdoRcInfo.setSpecVerGd(objESpecVerInfo.getGuid());
                objEdoRcInfo.setSpecName(objESpecVerInfo.getSpecName());
                objEdoRcInfo.setDevGd("");
                objEdoRcInfo.setDevName("");
                objEdoRcInfo.setReaCode(doRCInfo.getReaCode());
                objEdoRcInfo.setReaDes(doRCInfo.getReaDes());
                objEdoRcInfo.setNum(doRCInfo.getNum());
                objEdoRcInfo.setCreator(userName);
                objEdoRcInfo.setCreateTime(date);
                doRcDAO.InsertDoRC(objEdoRcInfo);
            }
        }

        String data = outStation(objEBInfo, objEBCirInfo, objESpecVerInfo, objEOpertInfo, userName, date);

        objESaveIOSInfoResB.setMsgDes(data);

        objESaveIOSInfoRes.setBody(objESaveIOSInfoResB);

        return objESaveIOSInfoRes;
    }

    /**
     * 工序上机
     * @param argBD02
     * @return
     * @throws SystemException
     */
    @Override
    public SaveIOSInfoRes AddUp(SaveIOSInfoReqBD02 argBD02) throws SystemException {
        SaveIOSInfoRes objESaveIOSInfoRes = new SaveIOSInfoRes();
        SaveIOSInfoResB saveIOSInfoResB = new SaveIOSInfoResB();

        String userName = CommonUtils.readUser().getRealName();
        Date date = new Date();

        //查询批次
        BInfo objEBInfo = bdao.selectBatchInfoByBatch(argBD02.getBatch());
        if(objEBInfo == null){
            throw new SystemException("", "批次不存在");
        }
        //判断该批次信息的状态
        if("02".equals(objEBInfo.getStatus())){
            throw new SystemException("", "该批次已冻结");
        }
        if("03".equals(objEBInfo.getStatus())){
            throw new SystemException("", "该批次已报废");
        }
        if("04".equals(objEBInfo.getStatus()) || "02".equals(objEBInfo.getwFStatus())){
            throw new SystemException("", "该批次已完成");
        }
        if("00".equals(objEBInfo.getwFStatus())){
            throw new SystemException("", "还未进入工艺流程");
        }

        //查询工单
        WoInfo woInfo = wodao.SelectWoInfoByGuid(objEBInfo.getWoGd());
        if(woInfo == null){
            throw new SystemException("", "该批次工单信息不存在");
        }

        //查询物料
        MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(objEBInfo.getMaVerGd());
        if(objEMaVerInfo == null){
            throw new SystemException("", "物料不存在");
        }
        //查询工序
        SpecVerInfo objESpecVerInfo = specVerDAO.SelectSpecVerInfoByruid(argBD02.getSpecVerRd());
        if(objESpecVerInfo == null){
            throw new SystemException("", "工序不存在");
        }
        //查询设备
        DevInfo objEDevInfo = devDAO.SelectBydevRd(argBD02.getDevRd());
        if(objEDevInfo == null){
            throw new SystemException("", "设备不存在");
        }
        if("01".equals(objESpecVerInfo.getStatus())){
            throw new SystemException("", "该工序不可用");
        }

        //物料清单
        //根据物料查Bom清单版本
        BomVerInfo objEBomVerInfo = bomVerDAO.SelectBomVerInfo(woInfo.getBomVerGd());
        if(objEBomVerInfo != null){
            //根据清单版本查用料
            List<BomMaterialInfo> objEBomMaterialInfos = bomMaterialDAO.selectList(new LambdaQueryWrapper<BomMaterialInfo>()
                    .eq(BomMaterialInfo::getBomVerGd, objEBomVerInfo.getGuid())
                    .eq(BomMaterialInfo::getConSMode, "01")
                    .eq(BomMaterialInfo::getSpecVerGd, objESpecVerInfo.getGuid()));
            if(objEBomMaterialInfos.size() > 0){
                //查询当前工序存在物料信息
                ULineMaterialInfo uLineMaterialInfo = uLineMaterialInfoDao.selectOne(
                        new LambdaQueryWrapper<ULineMaterialInfo>()
                                .eq(ULineMaterialInfo::getSpecGuid, objESpecVerInfo.getGuid())
                                .eq(ULineMaterialInfo::getWoCode, woInfo.getWoCode()));
                if(uLineMaterialInfo == null){
                    throw new SystemException("", "该工序还未上料");
                }
                List<ULineMaterialDetailInfo> uLineMaterialDetailInfos = uLineMaterialDetailInfoDao.selectList(
                        new LambdaQueryWrapper<ULineMaterialDetailInfo>()
                                .eq(ULineMaterialDetailInfo::getUlmGuid, uLineMaterialInfo.getGuid())
                                .eq(ULineMaterialDetailInfo::getStatus, "00")
                                .eq(ULineMaterialDetailInfo::getLuType, "01")
                                .eq(ULineMaterialDetailInfo::getDevGd, objEDevInfo.getGuid()));
                //数据转换
                Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
                BigDecimal num = BigDecimal.ZERO;
                for(ULineMaterialDetailInfo obj : uLineMaterialDetailInfos){
                    if(map.containsKey(obj.getMaterialCode())){
                        num = map.get(obj.getMaterialCode()).add(obj.getCanNum());
                    }else{
                        num = obj.getCanNum();
                    }
                    map.put(obj.getMaterialCode(), num);
                }

                //BOM清单替代料
                Map<String, Set<BomReMaterialInfo>> reMap = new HashMap<>();
                Set<BomReMaterialInfo> oSet = null;
                List<BomReMaterialInfo> bomReMaterialInfos = bomReMaterialDAO.SelectBomReMaterialInfo(woInfo.getGuid(), objESpecVerInfo.getGuid());
                for(BomReMaterialInfo bomRe : bomReMaterialInfos){
                    if(reMap.containsKey(bomRe.getBomMaGd())){
                        oSet = reMap.get(bomRe.getBomMaGd());
                    }else{
                        oSet = new HashSet<>();
                    }
                    oSet.add(bomRe);
                    reMap.put(bomRe.getBomMaGd(), oSet);
                }

                MaVerInfo reMaverInfo = null;
                BigDecimal allNum = null;

                for(BomMaterialInfo bomMaterialInfo : objEBomMaterialInfos){
                    //查询物料
                    MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(bomMaterialInfo.getMaVerGd());
                    if(maVerInfo == null){
                        throw new SystemException("", "Bom所需用料不存在");
                    }

                    allNum  = map.get(maVerInfo.getMaterialCode());
                    //查询替代料
                    if(reMap.containsKey(bomMaterialInfo.getGuid())){
                        oSet = reMap.get((bomMaterialInfo.getGuid()));
                        for(BomReMaterialInfo re : oSet){
                            //判断是否上料
                            reMaverInfo = maVerDAO.SelectMaverInfo(re.getMaVerGd());
                            if(reMaverInfo == null){
                                continue;
                            }
                            if (map.containsKey(reMaverInfo.getMaterialCode())) {
                                if(allNum == null){
                                    allNum = BigDecimal.ZERO;
                                }
                                allNum = allNum.add(map.get(reMaverInfo.getMaterialCode()));
                            }
                        }
                    }
                    if (allNum == null || allNum.compareTo(new BigDecimal(String.valueOf(bomMaterialInfo.getNum())).multiply(new BigDecimal(String.valueOf(objEBInfo.getNum())))) == -1) {
                        throw new SystemException("", "设备料不足，请装料");
                    }
                }
            }
        }

        boolean flag = false;

        //数据采集，不良代码处理
        //查询数据采集项信息表
        List<SaveIOSInfoReqBD03DoDCInfo> objESaveIOSInfoReqBD03DoDCInfos = argBD02.getDoDCInfo();
        if (objESaveIOSInfoReqBD03DoDCInfos != null) {

            for (SaveIOSInfoReqBD03DoDCInfo doDCInfo : objESaveIOSInfoReqBD03DoDCInfos) {
                //查询采集项信息
                DCItemInfo objEDCItemInfo = dcItemDAO.selectDCItemInfoByItemRd(doDCInfo.getDCItemRd());
                if (objEDCItemInfo == null) {
                    throw new SystemException("", "数据采集项信息为空");
                }

                //批次事物操作
                AffairInfo objEAffairInfo = new AffairInfo();

                String sCValue = doDCInfo.getCValue();

                //判断是否合格
                if("00".equals(objEDCItemInfo.getItemType()) || "01".equals(objEDCItemInfo.getItemType())){
                    BigDecimal svalue = new BigDecimal(objEDCItemInfo.getsValue()), uplimit = new BigDecimal(objEDCItemInfo.getUpLimit())
                            , dwlimit = new BigDecimal(objEDCItemInfo.getDwLimit());

                    //最大上限值
                    BigDecimal nMax = svalue.add(uplimit);
                    //最大下限值
                    BigDecimal nMin = svalue.subtract(dwlimit);
                    //采集数据值
                    if("".equals(sCValue)){
                        throw new SystemException("", objEDCItemInfo.getItemName() + "--数据采集不能为空");
                    }

                    if("00".equals(objEDCItemInfo.getChecked())) {
                        BigDecimal nNew = new BigDecimal(sCValue);

                        if (nNew.compareTo(nMax) == 1 || nNew.compareTo(nMin) == -1) {
                            objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
                            objEAffairInfo.setBatch(objEBInfo.getBatch());
                            objEAffairInfo.setSpecVerGd(objESpecVerInfo.getGuid());
                            objEAffairInfo.setSpecName(objESpecVerInfo.getSpecName());
                            objEAffairInfo.setOptType("07");
                            objEAffairInfo.setCreator(userName);
                            objEAffairInfo.setCreateTime(date);
                            affairDAO.InsertAffairInfo(objEAffairInfo);

                            objEBInfo.setBad("00");
                            flag = true;
                        }
                    }

                }else if("02".equals(objEDCItemInfo.getItemType())){

                    if("00".equals(objEDCItemInfo.getChecked())) {
                        if (!sCValue.equals(objEDCItemInfo.getsValue())) {
                            objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
                            objEAffairInfo.setBatch(objEBInfo.getBatch());
                            objEAffairInfo.setSpecVerGd(objESpecVerInfo.getGuid());
                            objEAffairInfo.setSpecName(objESpecVerInfo.getSpecName());
                            objEAffairInfo.setOptType("07");
                            objEAffairInfo.setCreator(userName);
                            objEAffairInfo.setCreateTime(date);
                            affairDAO.InsertAffairInfo(objEAffairInfo);

                            objEBInfo.setBad("00");
                            flag = true;
                        }
                    }
                }

                //如果不良则不允许出站
                if("00".equals(objEDCItemInfo.getChecked()) && "00".equals(objEDCItemInfo.getAction()) && flag){
                    objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
                    objEAffairInfo.setBatch(objEBInfo.getBatch());
                    objEAffairInfo.setSpecVerGd(objESpecVerInfo.getGuid());
                    objEAffairInfo.setSpecName(objESpecVerInfo.getSpecName());
                    objEAffairInfo.setOptType("09");
                    objEAffairInfo.setCreator(userName);
                    objEAffairInfo.setCreateTime(date);
                    affairDAO.InsertAffairInfo(objEAffairInfo);

                    //不良冻结批次
                    objEBInfo.setStatus("02");
                }

                //新增批次数据采集信息表
                DoDCInfo objEDoDCInfo = new DoDCInfo();
                objEDoDCInfo.setGuid(CommonUtils.getRandomNumber());
                objEDoDCInfo.setBatch(objEBInfo.getBatch());
                objEDoDCInfo.setType("00");
                objEDoDCInfo.setSpecVerGd(objESpecVerInfo.getGuid());
                objEDoDCInfo.setSpecName(objESpecVerInfo.getSpecName());
                objEDoDCInfo.setDevGd(objEDevInfo.getGuid());
                objEDoDCInfo.setDevName(objEDevInfo.getDevName());
                objEDoDCInfo.setdCItemGd(objEDCItemInfo.getDcVerGd());
                objEDoDCInfo.setItemName(objEDCItemInfo.getItemName());
                objEDoDCInfo.setcValue(doDCInfo.getCValue());
                objEDoDCInfo.setCreator(userName);
                objEDoDCInfo.setCreateTime(date);
                doDCDAO.InsertDoDcInfo(objEDoDCInfo);
            }
        }

        if(flag) {
            saveIOSInfoResB.setMsgDes("上机失败，不良批次已冻结");

            //修改批次信息
            objEBInfo.setLastModifyMan(userName);
            objEBInfo.setLastModifyTime(date);
            if (bdao.UpdateBatchInfoByRuid(objEBInfo) <= 0) {
                throw new SystemException("", "修改批次失败");
            }
        }else{
            //更改流转状态
            BCirInfo objEBCirInfo = bCirDAO.SelectByBatch(argBD02.getBatch());
            objEBCirInfo.setStatus("02");
            objEBCirInfo.setDevGd(objEDevInfo.getGuid());
            if (bCirDAO.UpdateBCir(objEBCirInfo) <= 0) {
                throw new SystemException("", "上机失败");
            }

            //批次标记信息->记录不良代码
            if(argBD02.getDoRCInfo() != null){
                for(SaveIOSInfoReqBD01DoRCInfo doRCInfo : argBD02.getDoRCInfo()){
                    DoRcInfo objEdoRcInfo = new DoRcInfo();
                    objEdoRcInfo.setGuid(CommonUtils.getRandomNumber());
                    objEdoRcInfo.setBatch(argBD02.getBatch());
                    objEdoRcInfo.setSpecVerGd(objESpecVerInfo.getGuid());
                    objEdoRcInfo.setSpecName(objESpecVerInfo.getSpecName());
                    objEdoRcInfo.setDevGd("");
                    objEdoRcInfo.setDevName("");
                    objEdoRcInfo.setReaCode(doRCInfo.getReaCode());
                    objEdoRcInfo.setReaDes(doRCInfo.getReaDes());
                    objEdoRcInfo.setNum(doRCInfo.getNum());
                    objEdoRcInfo.setCreator(userName);
                    objEdoRcInfo.setCreateTime(date);
                    doRcDAO.InsertDoRC(objEdoRcInfo);
                }
            }

            //批次事物操作
            AffairInfo objEAffairInfo = new AffairInfo();
            objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
            objEAffairInfo.setBatch(objEBInfo.getBatch());
            objEAffairInfo.setSpecVerGd(objESpecVerInfo.getGuid());
            objEAffairInfo.setSpecName(objESpecVerInfo.getSpecName());
            objEAffairInfo.setOptType("02");
            objEAffairInfo.setCreator(userName);
            objEAffairInfo.setCreateTime(date);
            affairDAO.InsertAffairInfo(objEAffairInfo);
        }

        objESaveIOSInfoRes.setBody(saveIOSInfoResB);
        return objESaveIOSInfoRes;
    }

    /**
     * 工序下机
     * @param argBD03
     * @return
     * @throws SystemException
     */
    @Override
    public SaveIOSInfoRes AddDown(SaveIOSInfoReqBD03 argBD03) throws SystemException {
        SaveIOSInfoRes objESaveIOSInfoRes = new SaveIOSInfoRes();
        SaveIOSInfoResB objESaveIOSInfoResB = new SaveIOSInfoResB();

        String userName = CommonUtils.readUser().getRealName();
        Date date = new Date();

        //查询批次
        BInfo objEBInfo = bdao.selectBatchInfoByBatch(argBD03.getBatch());
        if(objEBInfo == null){
            throw new SystemException("", "批次不存在");
        }
        //判断该批次信息的状态
        if("02".equals(objEBInfo.getStatus())){
            throw new SystemException("", "该批次已冻结");
        }
        if("03".equals(objEBInfo.getStatus())){
            throw new SystemException("", "该批次已报废");
        }
        if("04".equals(objEBInfo.getStatus()) || "02".equals(objEBInfo.getwFStatus())){
            throw new SystemException("", "该批次已完成");
        }
        if("00".equals(objEBInfo.getwFStatus())){
            throw new SystemException("", "还未进入工艺流程");
        }

        //查询工单
        WoInfo woInfo = wodao.SelectWoInfoByGuid(objEBInfo.getWoGd());
        if(woInfo == null){
            throw new SystemException("", "该批次工单信息不存在");
        }

        //查询物料
        MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(objEBInfo.getMaVerGd());
        if(objEMaVerInfo == null){
            throw new SystemException("", "物料不存在");
        }
        //查询工序
        SpecVerInfo objESpecVerInfo = specVerDAO.SelectSpecVerInfoByruid(argBD03.getSpecVerRd());
        if(objESpecVerInfo == null){
            throw new SystemException("", "工序不存在");
        }

        //查询生产流转表
        BCirInfo objEBCirInfo = bCirDAO.SelectByBatch(objEBInfo.getBatch());
        if(objEBCirInfo == null){
            throw new SystemException("", "还未入站");
        }
        if(!"02".equals(objEBCirInfo.getStatus())){
            throw new SystemException("", "还未到下机操作");
        }
        objEBCirInfo.setDevGd("");

        //查询作业
        OpertInfo objEOpertInfo = opertDAO.GetOpertInfoByGuid(objEBCirInfo.getOpertGd());
        if(objEOpertInfo == null){
            throw new SystemException("", "该作业为空");
        }

        //查询设备
        DevInfo objEDevInfo = devDAO.SelectBydevRd(argBD03.getDevRd());
        if(objEDevInfo == null){
            throw new SystemException("", "设备不存在");
        }
        if("01".equals(objESpecVerInfo.getStatus())){
            throw new SystemException("", "该工序不可用");
        }

        //物料清单
        //根据物料查Bom清单版本
        BomVerInfo objEBomVerInfo = bomVerDAO.SelectBomVerInfo(woInfo.getBomVerGd());
        if(objEBomVerInfo != null){
            //根据清单版本查用料
            List<BomMaterialInfo> objEBomMaterialInfos = bomMaterialDAO.selectList(new LambdaQueryWrapper<BomMaterialInfo>()
                    .eq(BomMaterialInfo::getBomVerGd, objEBomVerInfo.getGuid())
                    .eq(BomMaterialInfo::getSpecVerGd, objESpecVerInfo.getGuid()));
            if(objEBomMaterialInfos.size() > 0) {
                //查询当前工序存在物料信息
                ULineMaterialInfo uLineMaterialInfo = uLineMaterialInfoDao.selectOne(
                        new LambdaQueryWrapper<ULineMaterialInfo>()
                                .eq(ULineMaterialInfo::getSpecGuid, objESpecVerInfo.getGuid())
                                .eq(ULineMaterialInfo::getWoCode, woInfo.getWoCode()));
                List<ULineMaterialDetailInfo> uLineMaterialDetailInfos = new ArrayList<>();
                if (uLineMaterialInfo != null) {
                    uLineMaterialDetailInfos = uLineMaterialDetailInfoDao.selectList(
                            new LambdaQueryWrapper<ULineMaterialDetailInfo>()
                                    .eq(ULineMaterialDetailInfo::getUlmGuid, uLineMaterialInfo.getGuid())
                                    .eq(ULineMaterialDetailInfo::getLuType, "01")
                                    .eq(ULineMaterialDetailInfo::getStatus, "00")
                                    .eq(ULineMaterialDetailInfo::getDevGd, objEDevInfo.getGuid()));
                }

                //数据转换
                Map<String, List<ULineMaterialDetailInfo>> map = new HashMap<String, List<ULineMaterialDetailInfo>>();
                List<ULineMaterialDetailInfo> detailInfoList = null;
                for(ULineMaterialDetailInfo obj : uLineMaterialDetailInfos){
                    if(map.containsKey(obj.getMaterialCode())){
                        detailInfoList = map.get(obj.getMaterialCode());
                    }else{
                        detailInfoList = new ArrayList<>();
                    }
                    detailInfoList.add(obj);
                    map.put(obj.getMaterialCode(), detailInfoList);
                }
                WmsMaterialsBInfo bInfo = null;
                MaVerInfo reMaverInfo = null;
                DoMaInfo objEDoMaInfo = null;

                //BOM清单替代料
                Map<String, Set<BomReMaterialInfo>> reMap = new HashMap<>();
                Set<BomReMaterialInfo> oSet = null;
                List<BomReMaterialInfo> bomReMaterialInfos = bomReMaterialDAO.SelectBomReMaterialInfo(woInfo.getGuid(), objESpecVerInfo.getGuid());
                for(BomReMaterialInfo bomRe : bomReMaterialInfos){
                    if(reMap.containsKey(bomRe.getBomMaGd())){
                        oSet = reMap.get(bomRe.getBomMaGd());
                    }else{
                        oSet = new HashSet<>();
                    }
                    oSet.add(bomRe);
                    reMap.put(bomRe.getBomMaGd(), oSet);
                }

                for (BomMaterialInfo bomMaterialInfo : objEBomMaterialInfos) {
                    //查询物料
                    MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(bomMaterialInfo.getMaVerGd());
                    if (maVerInfo == null) {
                        throw new SystemException("", "Bom所需用料不存在");
                    }

                    boolean isBreak = false;

                    //消耗方式(仅显示)
                    if("00".equals(bomMaterialInfo.getConSMode())){
                        continue;
                    }else if("01".equals(bomMaterialInfo.getConSMode())){
                        //消耗方式(批次)
                        detailInfoList = map.get(maVerInfo.getMaterialCode());
                        //查询替代料
                        if(reMap.containsKey(bomMaterialInfo.getGuid())){
                            oSet = reMap.get((bomMaterialInfo.getGuid()));
                            for(BomReMaterialInfo re : oSet){
                                //判断是否上料
                                reMaverInfo = maVerDAO.SelectMaverInfo(re.getMaVerGd());
                                if(reMaverInfo == null){
                                    continue;
                                }
                                if (map.containsKey(reMaverInfo.getMaterialCode())) {
                                    if(detailInfoList == null){
                                        detailInfoList = new ArrayList<>();
                                    }
                                    detailInfoList.addAll(map.get(reMaverInfo.getMaterialCode()));
                                }
                            }
                        }
                        if (detailInfoList == null || detailInfoList.size() <= 0) {
                            throw new SystemException("", "设备" + maVerInfo.getMaterialCode() + "料不足，请装料");
                        }

                        //当前工序可用数量
                        BigDecimal specCNum = BigDecimal.ZERO;
                        for (ULineMaterialDetailInfo o : detailInfoList) {
                            specCNum = specCNum.add(o.getCanNum());
                        }

                        //总消耗
                        BigDecimal allNum = new BigDecimal(String.valueOf(bomMaterialInfo.getNum())).multiply(new BigDecimal(String.valueOf(objEBInfo.getNum())));
                        BigDecimal num = specCNum.subtract(allNum); //差值
                        if (num.compareTo(BigDecimal.ZERO) == -1) {
                            throw new SystemException("", "工序" + maVerInfo.getMaterialCode() + "料不足，请装料");
                        }

                        for (ULineMaterialDetailInfo detailInfo : detailInfoList) {
                            //新增批次用料信息
                            objEDoMaInfo = new DoMaInfo();

                            //查询准备消耗批次
                            bInfo = wmsMaterialsBDAO.selectOne(new LambdaQueryWrapper<WmsMaterialsBInfo>()
                                    .eq(WmsMaterialsBInfo::getBatch, detailInfo.getLotNo()));
                            if (bInfo == null) {
                                throw new SystemException("", detailInfo.getLotNo() + "批次信息已不存在");
                            }
                            if (detailInfo.getCanNum().compareTo(bInfo.getCanNum()) != 0) {
                                throw new SystemException("", bInfo.getBatch() + "批次可用数量和上料数量不同,请检查");
                            }
                            allNum = allNum.subtract(detailInfo.getCanNum());
                            if (allNum.compareTo(BigDecimal.ZERO) > -1) {
                                //销毁批次
                                num = bInfo.getCanNum();
                                bInfo.setCanNum(BigDecimal.ZERO);
                                bInfo.setConSuNum(bInfo.getNum());
                                bInfo.setStatus("04");

                                detailInfo.setCanNum(BigDecimal.ZERO);
                                detailInfo.setStatus("01");

                                if (wmsMaterialsBDAO.updateById(bInfo) <= 0
                                        || uLineMaterialDetailInfoDao.updateById(detailInfo) <= 0) {
                                    throw new SystemException("", "下机消耗批次失败,请重试");
                                }

                                objEDoMaInfo.setDoNum(num.floatValue());
                            } else {
                                //更改批次
                                num = bInfo.getCanNum().add(allNum);
                                bInfo.setCanNum(allNum.negate());
                                bInfo.setConSuNum(bInfo.getConSuNum().add(num));

                                detailInfo.setCanNum(allNum.negate());

                                if (wmsMaterialsBDAO.updateById(bInfo) <= 0 || uLineMaterialDetailInfoDao.updateById(detailInfo) <= 0) {
                                    throw new SystemException("", "下机消耗批次失败,请重试");
                                }

                                objEDoMaInfo.setDoNum(num.floatValue());
                            }

                            objEDoMaInfo.setGuid(CommonUtils.getRandomNumber());
                            objEDoMaInfo.setBatch(objEBInfo.getBatch());
                            objEDoMaInfo.setConSMode("01");
                            objEDoMaInfo.setSpecVerGd(objESpecVerInfo.getGuid());
                            objEDoMaInfo.setSpecName(objESpecVerInfo.getSpecName());
                            objEDoMaInfo.setDevGd(objEDevInfo.getGuid());
                            objEDoMaInfo.setDevName(objEDevInfo.getDevName());
                            objEDoMaInfo.setDoBatch(bInfo.getBatch());
                            objEDoMaInfo.setMaVerGd(bInfo.getMaVerGd());
                            objEDoMaInfo.setMaterialCode(detailInfo.getMaterialCode());
                            objEDoMaInfo.setMaterialName(detailInfo.getMaterialName());
                            objEDoMaInfo.setUnitName(detailInfo.getUnitName());
                            objEDoMaInfo.setCreator(userName);
                            objEDoMaInfo.setCreateTime(date);
                            doMaDAO.InsertDoMaInfo(objEDoMaInfo);

                            if (allNum.compareTo(BigDecimal.ZERO) < 1) {
                                break;
                            }
                        }
                    }else if("02".equals(bomMaterialInfo.getConSMode())){
                        //消耗方式(序号)
                        continue;
                    }
                }
            }
        }

        //数据采集，不良代码处理
        //查询数据采集项信息表
        List<SaveIOSInfoReqBD03DoDCInfo> objESaveIOSInfoReqBD03DoDCInfos = argBD03.getDoDCInfo();
        if (objESaveIOSInfoReqBD03DoDCInfos != null) {

            for (SaveIOSInfoReqBD03DoDCInfo doDCInfo : objESaveIOSInfoReqBD03DoDCInfos) {
                //查询采集项信息
                DCItemInfo objEDCItemInfo = dcItemDAO.selectDCItemInfoByItemRd(doDCInfo.getDCItemRd());
                if (objEDCItemInfo == null) {
                    throw new SystemException("", "数据采集项信息为空");
                }

                //批次事物操作
                AffairInfo objEAffairInfo = new AffairInfo();

                String sCValue = doDCInfo.getCValue();

                boolean flag = false;

                //判断是否合格
                if("00".equals(objEDCItemInfo.getItemType()) || "01".equals(objEDCItemInfo.getItemType())){
                    BigDecimal svalue = new BigDecimal(objEDCItemInfo.getsValue()), uplimit = new BigDecimal(objEDCItemInfo.getUpLimit())
                            , dwlimit = new BigDecimal(objEDCItemInfo.getDwLimit());

                    //最大上限值
                    BigDecimal nMax = svalue.add(uplimit);
                    //最大下限值
                    BigDecimal nMin = svalue.subtract(dwlimit);
                    //采集数据值
                    if("".equals(sCValue)){
                        throw new SystemException("", objEDCItemInfo.getItemName() + "--数据采集不能为空");
                    }

                    if("00".equals(objEDCItemInfo.getChecked())) {
                        BigDecimal nNew = new BigDecimal(sCValue);

                        if (nNew.compareTo(nMax) == 1 || nNew.compareTo(nMin) == -1) {
                            objEBInfo.setBad("00");
                            objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
                            objEAffairInfo.setBatch(objEBInfo.getBatch());
                            objEAffairInfo.setSpecVerGd(objESpecVerInfo.getGuid());
                            objEAffairInfo.setSpecName(objESpecVerInfo.getSpecName());
                            objEAffairInfo.setOptType("07");
                            objEAffairInfo.setCreator(userName);
                            objEAffairInfo.setCreateTime(date);
                            affairDAO.InsertAffairInfo(objEAffairInfo);

                            flag = true;
                        }
                    }

                }else if("02".equals(objEDCItemInfo.getItemType())){

                    if("00".equals(objEDCItemInfo.getChecked())) {
                        if (!sCValue.equals(objEDCItemInfo.getsValue())) {
                            objEBInfo.setBad("00");
                            objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
                            objEAffairInfo.setBatch(objEBInfo.getBatch());
                            objEAffairInfo.setSpecVerGd(objESpecVerInfo.getGuid());
                            objEAffairInfo.setSpecName(objESpecVerInfo.getSpecName());
                            objEAffairInfo.setOptType("07");
                            objEAffairInfo.setCreator(userName);
                            objEAffairInfo.setCreateTime(date);
                            affairDAO.InsertAffairInfo(objEAffairInfo);

                            flag = true;
                        }
                    }
                }

                //如果不良则不允许出站
                if("00".equals(objEDCItemInfo.getChecked()) && "00".equals(objEDCItemInfo.getAction()) && flag){
                    objEBInfo.setStatus("02"); //不良冻结批次

                    objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
                    objEAffairInfo.setBatch(objEBInfo.getBatch());
                    objEAffairInfo.setSpecVerGd(objESpecVerInfo.getGuid());
                    objEAffairInfo.setSpecName(objESpecVerInfo.getSpecName());
                    objEAffairInfo.setOptType("09");
                    objEAffairInfo.setCreator(userName);
                    objEAffairInfo.setCreateTime(date);
                    affairDAO.InsertAffairInfo(objEAffairInfo);
                }

                //新增批次数据采集信息表
                DoDCInfo objEDoDCInfo = new DoDCInfo();
                objEDoDCInfo.setGuid(CommonUtils.getRandomNumber());
                objEDoDCInfo.setBatch(objEBInfo.getBatch());
                objEDoDCInfo.setType("01");
                objEDoDCInfo.setSpecVerGd(objESpecVerInfo.getGuid());
                objEDoDCInfo.setSpecName(objESpecVerInfo.getSpecName());
                objEDoDCInfo.setDevGd(objEDevInfo.getGuid());
                objEDoDCInfo.setDevName(objEDevInfo.getDevName());
                objEDoDCInfo.setdCItemGd(objEDCItemInfo.getDcVerGd());
                objEDoDCInfo.setItemName(objEDCItemInfo.getItemName());
                objEDoDCInfo.setcValue(doDCInfo.getCValue());
                objEDoDCInfo.setCreator(userName);
                objEDoDCInfo.setCreateTime(date);
                doDCDAO.InsertDoDcInfo(objEDoDCInfo);
            }
        }

        //批次标记信息->记录不良代码
        if(argBD03.getDoRCInfo() != null){
            DoRcInfo doRcInfo = null;
            for(SaveIOSInfoReqBD03DoRCInfo doRCInfo : argBD03.getDoRCInfo()){
                doRcInfo = new DoRcInfo();
                doRcInfo.setGuid(CommonUtils.getRandomNumber());
                doRcInfo.setBatch(objEBInfo.getBatch());
                doRcInfo.setSpecVerGd(objESpecVerInfo.getGuid());
                doRcInfo.setSpecName(objESpecVerInfo.getSpecName());
                doRcInfo.setDevGd(objEDevInfo.getGuid());
                doRcInfo.setDevName(objEDevInfo.getDevName());
                doRcInfo.setReaCode(doRCInfo.getReaCode());
                doRcInfo.setReaDes(doRCInfo.getReaDes());
                doRcInfo.setNum(doRCInfo.getNum());
                doRcInfo.setCreator(userName);
                doRcInfo.setCreateTime(date);
                doRcDAO.InsertDoRC(doRcInfo);
            }
        }

        //批次事物操作
        AffairInfo objEAffairInfo = new AffairInfo();
        objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
        objEAffairInfo.setBatch(objEBInfo.getBatch());
        objEAffairInfo.setSpecVerGd(objESpecVerInfo.getGuid());
        objEAffairInfo.setSpecName(objESpecVerInfo.getSpecName());
        objEAffairInfo.setOptType("03");
        objEAffairInfo.setCreator(userName);
        objEAffairInfo.setCreateTime(date);
        affairDAO.InsertAffairInfo(objEAffairInfo);

        //出站
        String data = outStation(objEBInfo, objEBCirInfo, objESpecVerInfo, objEOpertInfo, userName, date);

        objESaveIOSInfoResB.setMsgDes(data);

        objESaveIOSInfoRes.setBody(objESaveIOSInfoResB);

        return objESaveIOSInfoRes;
    }

    @Override
    public SaveIOSInfoRes AddMove(SaveIOSInfoReqBD04[] argBD04) throws SystemException {
        return null;
    }

    @Override
    public SaveIOSInfoRes AddRe(SaveIOSInfoReqBD05 argBD05) throws SystemException {
        return null;
    }

    //出站
    public String outStation(BInfo objEBInfo, BCirInfo objEBCirInfo,
                             SpecVerInfo objESpecVerInfo, OpertInfo objEOpertInfo,
                             String userName, Date date){

        //提示信息
        String data = "";

        //是否是最后一道工序 true （是）
        boolean specLast = false;

        //生产流转信息日志
        BCirDlInfo objEBCirDlInfo = new BCirDlInfo();
        //生产流转信息日志Guid
        String bcirDlInfoGd = CommonUtils.getRandomNumber();

        //根据工艺版本+工序版本查找工艺工序信息
        WFSpecInfo objEWFSpecInfo = wfSpecDAO.SelectByWSVerGd(objEBCirInfo.getwFVerGd(), objEBCirInfo.getSpecVerGd());
        if (objEWFSpecInfo == null) {
            throw new SystemException("", "该工艺工序信息为空");
        }
        if (null != objEWFSpecInfo.getnSpecVerGd() && !"".equals(objEWFSpecInfo.getnSpecVerGd())) { //非最后一道工序
            //查询下一道工艺工序
            WFSpecInfo objEWFSpecInfoLast = null;
            //判断是否存在可选或返工工序
            if ("00".equals(objEWFSpecInfo.geteOSpec()) || "00".equals(objEWFSpecInfo.geteRSpec())) {
                //查询非正常工序流转信息
                List<WFCirInfo> objEWFCirInfos = wfCirDAO.SelectByWFSpecGd(objEWFSpecInfo.getGuid());
                for (WFCirInfo wfCirInfo : objEWFCirInfos) {
                    if ("x".equals(wfCirInfo.getExpression())) {
                        BCirCInfo bCirCInfo = new BCirCInfo();
                        bCirCInfo.setGuid(CommonUtils.getRandomNumber());
                        bCirCInfo.setbCirDlGd(bcirDlInfoGd);
                        bCirCInfo.setBatch(objEBInfo.getBatch());
                        bCirCInfo.setCondtion(wfCirInfo.getExpression());
                        bCirCInfo.setCreator(userName);
                        bCirCInfo.setCreateTime(date);
                        //新增生产流转条件日志信息
                        bCirCDAO.InsertBCirCInfo(bCirCInfo);

                        objEWFSpecInfoLast = wfSpecDAO.SelectByWSVerGd(objEBInfo.getwFVerGd(), wfCirInfo.getSpecVerGd());
                        if ("01".equals(wfCirInfo.getRouteType())) {
                            objEBCirDlInfo.setFlowFlag("01");
                        } else {
                            objEBCirDlInfo.setFlowFlag("02");
                        }
                        break;
                    }
                }
            } else {
                objEWFSpecInfoLast = wfSpecDAO.SelectByWSVerGd(objEBInfo.getwFVerGd(), objEWFSpecInfo.getnSpecVerGd());
                objEBCirDlInfo.setFlowFlag("00");
            }
            if (objEWFSpecInfoLast == null) {
                throw new SystemException("", "该工艺工序信息为空");
            }
            //查询下一道工序版本
            SpecVerInfo objESpecVerInfoLast = specVerDAO.SelectSpecVerInfoByguid(objEWFSpecInfoLast.getSpecVerGd());
            if (objESpecVerInfoLast == null) {
                throw new SystemException("", "下一道工序版本为空");
            }
            //查询下一道工序作业
            OpertInfo objEOpertInfoLast = opertDAO.GetOpertInfoByGuid(objESpecVerInfoLast.getOpertGd());
            if (objEOpertInfoLast == null) {
                throw new SystemException("", "下一道作业为空");
            }

            //新增生产流转信息日志
            objEBCirDlInfo.setGuid(CommonUtils.getRandomNumber());
            objEBCirDlInfo.setWoBWGd(objEBCirInfo.getWoBGd());
            objEBCirDlInfo.setBatch(objEBCirInfo.getBatch());
            objEBCirDlInfo.setsSpecVerGd(objEBCirInfo.getSpecVerGd());
            objEBCirDlInfo.setsSpecVerName(objEBCirInfo.getSpecName());
            objEBCirDlInfo.setsOpertGd(objEBCirInfo.getOpertGd());
            objEBCirDlInfo.setsOptName(objEBCirInfo.getOptName());

            objEBCirDlInfo.settSpecVerGd(objESpecVerInfoLast.getGuid());
            objEBCirDlInfo.settSpecName(objESpecVerInfoLast.getSpecName());
            objEBCirDlInfo.settOpertGd(objEOpertInfoLast.getGuid());
            objEBCirDlInfo.settOptName(objEOpertInfoLast.getOptname());
            objEBCirDlInfo.setCreator(userName);
            objEBCirDlInfo.setCreateTime(date);
            bCirDlDAO.InsertBCirDl(objEBCirDlInfo);

            //批次事物操作
            AffairInfo objEAffairInfo = new AffairInfo();
            objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
            objEAffairInfo.setBatch(objEBCirInfo.getBatch());
            objEAffairInfo.setSpecVerGd(objEBCirInfo.getSpecVerGd());
            objEAffairInfo.setSpecName(objEBCirInfo.getSpecName());
            objEAffairInfo.setOptType("01");
            objEAffairInfo.setCreator(userName);
            objEAffairInfo.setCreateTime(date);
            affairDAO.InsertAffairInfo(objEAffairInfo);

            if ("00".equals(objESpecVerInfoLast.getStatus()) && !"02".equals(objEBInfo.getStatus())) {  //如果下一道工序不可用，则阻止入站
                if ("02".equals(objEOpertInfoLast.getSpecoption())) {  //如果02自动入站
                    objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
                    objEAffairInfo.setBatch(objEBCirInfo.getBatch());
                    objEAffairInfo.setSpecVerGd(objESpecVerInfoLast.getGuid());
                    objEAffairInfo.setSpecName(objESpecVerInfoLast.getSpecName());
                    objEAffairInfo.setOptType("00");
                    objEAffairInfo.setCreator(userName);
                    objEAffairInfo.setCreateTime(date);
                    affairDAO.InsertAffairInfo(objEAffairInfo);

                    //出站还是上机问题
                    if(isDev(objESpecVerInfoLast.getDevGrGd())){
                        objEBCirInfo.setStatus("01");
                    }else {
                        objEBCirInfo.setStatus("03");
                    }
                } else if ("01".equals(objEOpertInfoLast.getSpecoption())) { //如果01自动出入站
                    //标识下一道工序是自动出站 true（是）
                    boolean flag = true;
                    while (true) {
                        //查询当前工序
                        objESpecVerInfo = objESpecVerInfoLast;
                        if ("01".equals(objESpecVerInfo.getStatus())) {
                            objEBCirInfo.setStatus("00");
                            break;
                        }

                        //查询当前工序作业
                        objEOpertInfo = opertDAO.GetOpertInfoByGuid(objESpecVerInfo.getOpertGd());
                        if (objEOpertInfo == null) {
                            break;
                        }

                        //查询当前工艺
                        objEWFSpecInfo = wfSpecDAO.SelectByWSVerGd(objEBCirInfo.getwFVerGd(), objESpecVerInfo.getGuid());
                        if (objEWFSpecInfo == null) {
                            break;
                        }

                        //记录批次事物操作信息表  (进站)
                        objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
                        objEAffairInfo.setBatch(objEBCirInfo.getBatch());
                        objEAffairInfo.setSpecVerGd(objESpecVerInfoLast.getGuid());
                        objEAffairInfo.setSpecName(objESpecVerInfoLast.getSpecName());
                        objEAffairInfo.setOptType("00");
                        objEAffairInfo.setCreator(userName);
                        objEAffairInfo.setCreateTime(date);
                        affairDAO.InsertAffairInfo(objEAffairInfo);

                        //出站还是上机问题
                        if(isDev(objESpecVerInfo.getDevGrGd())){
                            objEBCirInfo.setStatus("01");
                            break;
                        }

                        if (!flag) {
                            break;
                        }

                        if (null != objEWFSpecInfo.getnSpecVerGd() && !"".equals(objEWFSpecInfo.getnSpecVerGd())) { //非最后一道工序

                            //查询下一道工序
                            objESpecVerInfoLast = specVerDAO.SelectSpecVerInfoByguid(objEWFSpecInfo.getnSpecVerGd());
                            if (objESpecVerInfoLast == null) {
                                objESpecVerInfoLast = objESpecVerInfo;
                                break;
                            }

                            //查询下一道工序作业
                            objEOpertInfoLast = opertDAO.GetOpertInfoByGuid(objESpecVerInfoLast.getOpertGd());
                            if (objEOpertInfoLast == null) {
                                objESpecVerInfoLast = objESpecVerInfo;
                                break;
                            }

                            //记录生产流转信息日志表
                            objEBCirDlInfo.setGuid(CommonUtils.getRandomNumber());
                            objEBCirDlInfo.setWoBWGd(objEBCirInfo.getWoBGd());
                            objEBCirDlInfo.setBatch(objEBCirInfo.getBatch());

                            objEBCirDlInfo.setsSpecVerGd(objESpecVerInfo.getGuid());
                            objEBCirDlInfo.setsSpecVerName(objESpecVerInfo.getSpecName());
                            objEBCirDlInfo.setsOpertGd(objEOpertInfo.getGuid());
                            objEBCirDlInfo.setsOptName(objEOpertInfo.getOptname());

                            objEBCirDlInfo.settSpecVerGd(objESpecVerInfoLast.getGuid());
                            objEBCirDlInfo.settSpecName(objESpecVerInfoLast.getSpecName());
                            objEBCirDlInfo.settOpertGd(objEOpertInfoLast.getGuid());
                            objEBCirDlInfo.settOptName(objEOpertInfoLast.getOptname());
                            objEBCirDlInfo.setCreator(userName);
                            objEBCirDlInfo.setCreateTime(date);
                            bCirDlDAO.InsertBCirDl(objEBCirDlInfo);

                            //判断下一道工序作业是否还是自动出站
                            if ("00".equals(objEOpertInfoLast.getPackopt())) { //包装作业，停止

                                break;

                            } else if ("00".equals(objEOpertInfoLast.getSpecoption())) {  //不自动入站，停止

                                break;

                            } else if ("02".equals(objEOpertInfoLast.getSpecoption())) {  //自动入站，停止一半

                                flag = false;

                            }

                        } else {
                            //关闭批次、（工单）
                            specLast = true;
                            break;
                        }

                        //记录批次事物操作信息表 （出站）
                        objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
                        objEAffairInfo.setOptType("01");
                        affairDAO.InsertAffairInfo(objEAffairInfo);
                    }

                } else {
                    objEBCirInfo.setStatus("00");
                }
            } else {
                objEBCirInfo.setStatus("00");
            }

            //修改生产流转信息
            objEBCirInfo.setlSpecVerGd(objESpecVerInfo.getGuid());
            objEBCirInfo.setlSpecName(objESpecVerInfo.getSpecName());
            objEBCirInfo.setlOpertGd(objEOpertInfo.getGuid());
            objEBCirInfo.setlOptName(objEOpertInfo.getOptname());

            objEBCirInfo.setSpecVerGd(objESpecVerInfoLast.getGuid());
            objEBCirInfo.setSpecName(objESpecVerInfoLast.getSpecName());
            objEBCirInfo.setOpertGd(objEOpertInfoLast.getGuid());
            objEBCirInfo.setOptName(objEOpertInfoLast.getOptname());
            objEBCirInfo.setLastModifyMan(userName);
            objEBCirInfo.setLastModifyTime(date);
            if (bCirDAO.UpdateBCir(objEBCirInfo) <= 0) {
                throw new SystemException("", "出站失败");
            }

            data = "正在进入工序-" + objESpecVerInfoLast.getSpecName();
        } else {
            specLast = true;
        }

        if("02".equals(objEBInfo.getStatus())) {
            data = "出站成功，不良批次已冻结";

            //修改批次信息
            objEBInfo.setLastModifyMan(userName);
            objEBInfo.setLastModifyTime(date);
            if (bdao.UpdateBatchInfoByRuid(objEBInfo) <= 0) {
                throw new SystemException("", "修改批次失败");
            }
        }

        //如果是最后一道工序，则关闭批次、（工单）
        if(specLast){
            //记录批次事物操作信息表
            AffairInfo affairInfo = new AffairInfo();
            affairInfo.setGuid(CommonUtils.getRandomNumber());
            affairInfo.setBatch(objEBCirInfo.getBatch());
            affairInfo.setSpecVerGd(objESpecVerInfo.getGuid());
            affairInfo.setSpecName(objESpecVerInfo.getSpecName());
            affairInfo.setOptType("01");
            affairInfo.setCreator(userName);
            affairInfo.setCreateTime(date);
            affairDAO.InsertAffairInfo(affairInfo);

            //修改批次信息
            objEBInfo.setStatus("00");
            objEBInfo.setwFStatus("02");
            objEBInfo.setLastModifyMan(userName);
            objEBInfo.setLastModifyTime(date);
            if(bdao.UpdateBatchInfoByRuid(objEBInfo) <= 0){
                throw new SystemException("", "修改批次失败");
            }

            data = "批次完成";
        }

        return data;
    }

    //判断当前工序是否有设备
    public boolean isDev(String devGrGd){
        //查询设备组明细
        List<DevGpDtlInfo> objEDevGpDtInfos = devGpDtlDAO.SelectByguid(devGrGd);
        if(objEDevGpDtInfos != null && objEDevGpDtInfos.size() > 0){
            return true;
        }

        return false;
    }
}
