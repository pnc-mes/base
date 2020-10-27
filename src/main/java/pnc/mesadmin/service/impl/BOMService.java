package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoRes;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoResB;
import pnc.mesadmin.dto.GetAllBHChgInfo.GetAllBHChgInfoResB;
import pnc.mesadmin.dto.GetAllBHChgInfo.GetAllBHChgInfoResBD;
import pnc.mesadmin.dto.GetAllBOMInfo.GetAllBOMInfoRes;
import pnc.mesadmin.dto.GetAllBOMInfo.GetAllBOMInfoResB;
import pnc.mesadmin.dto.GetAllBOMInfo.GetAllBOMInfoResD;
import pnc.mesadmin.dto.GetAllBOMInfo.GetAllBomRes;
import pnc.mesadmin.dto.GetBHChgDtlInfo.GetBHChgDtlInfoResB;
import pnc.mesadmin.dto.GetBHChgDtlInfo.GetBHChgDtlInfoResBD;
import pnc.mesadmin.dto.GetBHChgDtlInfo.GetBHChgDtlInfoResBDHChg;
import pnc.mesadmin.dto.GetBHChgDtlInfo.GetBHChgDtlInfoResBDQChg;
import pnc.mesadmin.dto.GetBOMInfo.*;
import pnc.mesadmin.dto.GetBOMTreeInfo.GetBOMTreeInfoReqBD00;
import pnc.mesadmin.dto.GetBOMTreeInfo.GetBOMTreeInfoRes;
import pnc.mesadmin.dto.GetBOMTreeInfo.GetBOMTreeInfoResB;
import pnc.mesadmin.dto.GetBOMTreeInfo.GetBOMTreeInfoResD;
import pnc.mesadmin.dto.GetBVInfo.*;
import pnc.mesadmin.dto.SaveBomInfo.*;
import pnc.mesadmin.dto.SaveImportRes.SaveImportResB;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BOMIService;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.GConfigIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;
import pnc.mesadmin.utils.MyPage;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：BOM管理业务信息逻辑
 * 创建人：潘俊峰
 * 创建时间：2017-06-08
 * 修改人：
 * 修改时间：
 */
@Service
@Transactional
public class BOMService implements BOMIService {

    @Resource
    private BomDAO bomDAO;

    @Resource
    private BomVerDAO bomVerDAO;

    @Resource
    private SpecDAO specDAO;

    @Resource
    private BomMaterialDAO bomMaterialDAO;

    @Resource
    private BomReMaterialDAO bomReMaterialDAO;

    @Resource
    private MaterialDAO materialDAO;

    @Resource
    private MaVerDAO maVerDAO;

    @Resource
    private UnitDAO unitDAO;

    @Resource
    private SpecVerDAO specVerDAO;

    @Resource
    private BomChgDAO bomChgDAO;

    @Resource
    private BomChgDlDAO bomChgDlDAO;

    @Resource
    private BaseIService baseIService;

    @Resource
    private CodeRuleDAO codeRuleDAO;

    @Resource
    private GConfigIService gConfigIService;

    /**
     * 获取BOM列表信息
     *
     * @param argInitDataFields
     * @param argPageInfo
     * @return
     * @throws SystemException
     */
    @Override
    public GetAllBOMInfoRes QALLBOMInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException {
        GetAllBOMInfoRes objEGetAllBOMInfoRes = new GetAllBOMInfoRes();
        GetAllBOMInfoResB objEGetAllBOMInfoResB = new GetAllBOMInfoResB();
        List<GetAllBOMInfoResD> objEGetAllBOMInfoResBs = new ArrayList<GetAllBOMInfoResD>();

        List<BomInfo> objEBomInfos = baseIService.QALLBaseInfo(BomDAO.class, "SelectAllBomInfo",
                argInitDataFields, argPageInfo);

        List<BomVerInfo> objEBomVerInfos = bomVerDAO.SelectAllBomVerInfo();
        Map<String, BomVerInfo> map = new HashMap<String, BomVerInfo>();
        if (objEBomVerInfos != null && objEBomVerInfos.size() > 0) {
            for (int i = 0, length = objEBomVerInfos.size(); i < length; i++) {
                map.put(objEBomVerInfos.get(i).getGuid(), objEBomVerInfos.get(i));
            }
        }

        if (objEBomInfos != null && objEBomInfos.size() > 0) {
            for (BomInfo bomInfo : objEBomInfos) {
                if (map.containsKey(bomInfo.getVerGd())) {
                    GetAllBOMInfoResD objEGetAllBOMInfoResD = new GetAllBOMInfoResD();
                    objEGetAllBOMInfoResD.setBomRd(bomInfo.getRuid());
                    objEGetAllBOMInfoResD.setBomCode(bomInfo.getBomCode());
                    objEGetAllBOMInfoResD.setBomName(bomInfo.getBomName());

                    //查询默认
                    objEGetAllBOMInfoResD.setBomVerRd(map.get(bomInfo.getVerGd()).getRuid());
                    objEGetAllBOMInfoResD.setVersion(map.get(bomInfo.getVerGd()).getVersion());
                    objEGetAllBOMInfoResBs.add(objEGetAllBOMInfoResD);
                }
            }
        }

        objEGetAllBOMInfoResB.setData(objEGetAllBOMInfoResBs);
        objEGetAllBOMInfoRes.setBody(objEGetAllBOMInfoResB);

        return objEGetAllBOMInfoRes;
    }

    /**
     * @Description 获取BOM列表信息_V2
     * @Author yin.yang
     * @Date 2020/9/16
     * @Param
     * @Return
     * @Exception
     */
    @Override
    public BaseResponse GetAllBOMInfo_V2(GetAllBomRes request) {
        Page<BomInfo> page = new Page<>(request.getPage(), request.getLimit());
        IPage<BomInfo> response = bomDAO.selectPage(page, new QueryWrapper<BomInfo>()
                .like(!StringUtils.isBlank(request.getBomCode()), "bomCode", request.getBomCode())
                .like(!StringUtils.isBlank(request.getBomName()), "bomName", request.getBomName())
                .eq(!StringUtils.isBlank(request.getStatus()), "status", request.getStatus()));
        return BaseResponse.success(response);
    }

    /**
     * 获取BOM列表信息(新)
     *
     * @param req
     * @return
     */
    @Override
    public PageResult<GetAllBOMInfoResD> QALLBOMNew(BaseRequest req) {
        List<GetAllBOMInfoResD> resDList = new ArrayList<GetAllBOMInfoResD>();
        GetAllBOMInfoResD resD = null;

        IPage<BomInfo> iPage = bomDAO.selectPage(new MyPage(req), CommonUtils.getQueryWrapper(req.getFields()));

        List<BomVerInfo> objEBomVerInfos = bomVerDAO.SelectAllBomVerInfo();
        Map<String, BomVerInfo> map = new HashMap<String, BomVerInfo>();
        for (int i = 0, length = objEBomVerInfos.size(); i < length; i++) {
            map.put(objEBomVerInfos.get(i).getGuid(), objEBomVerInfos.get(i));
        }

        for (BomInfo bomInfo : iPage.getRecords()) {
            if (map.containsKey(bomInfo.getVerGd())) {
                resD = new GetAllBOMInfoResD();
                resD.setBomRd(bomInfo.getRuid());
                resD.setBomCode(bomInfo.getBomCode());
                resD.setBomName(bomInfo.getBomName());

                //查询默认
                resD.setBomVerRd(map.get(bomInfo.getVerGd()).getRuid());
                resD.setVersion(map.get(bomInfo.getVerGd()).getVersion());
                resDList.add(resD);
            }
        }

        return new PageResult<>(iPage, resDList);
    }

    /**
     * 获取BOM信息
     *
     * @param argBD00
     * @return
     * @throws SystemException
     */
    @Override
    public GetBOMInfoRes GetBOMInfo(GetBOMInfoReqBD00 argBD00) throws SystemException {
        GetBOMInfoRes objEGetBOMInfoRes = new GetBOMInfoRes();
        GetBOMInfoResB objEGetBOMInfoResB = new GetBOMInfoResB();
        GetBOMInfoResD objEGetBOMInfoResD = new GetBOMInfoResD();

        //查询BOM
        BomInfo objEBomInfo = bomDAO.SelectBomInfo(argBD00.getBomRd());
        if (objEBomInfo == null) {
            throw new SystemException("", "BOM清单为空");
        }
        //查询BOM清单默认版本
        BomVerInfo objEBomVerInfo = bomVerDAO.SelectBomVerInfo(objEBomInfo.getVerGd());
        if (objEBomVerInfo == null) {
            throw new SystemException("", "BOM清单默认版本为空");
        }
        objEGetBOMInfoResD.setBMRd(objEBomInfo.getRuid());
        objEGetBOMInfoResD.setBomVerRd(objEBomVerInfo.getRuid());
        objEGetBOMInfoResD.setBomCode(objEBomVerInfo.getBomCode());
        objEGetBOMInfoResD.setBomName(objEBomVerInfo.getBomName());
        objEGetBOMInfoResD.setVersion(objEBomVerInfo.getVersion());
        objEGetBOMInfoResD.setIsDef(objEBomVerInfo.getIsDef());
        objEGetBOMInfoResD.setCreator(objEBomVerInfo.getCreator());
        objEGetBOMInfoResD.setCreateTime(DateUtil.format(objEBomVerInfo.getCreateTime()));
        objEGetBOMInfoResD.setLastModifyMan(objEBomVerInfo.getLastModifyMan());
        objEGetBOMInfoResD.setLastModifyTime(DateUtil.format(objEBomVerInfo.getLastModifyTime()));
        objEGetBOMInfoResD.setRemark(objEBomVerInfo.getRemark());

        //下边BMInfo
        List<GetBOMInfoResDBMInfo> objEGetBOMInfoResDBMInfos = new ArrayList<GetBOMInfoResDBMInfo>();
        List<BomMaterialInfo> objEBomMaterialInfos = bomMaterialDAO.SelectByBomVerGd(objEBomVerInfo.getGuid());

        for (BomMaterialInfo bomMaterialInfo : objEBomMaterialInfos) {
            GetBOMInfoResDBMInfo objEGetBOMInfoResDBMInfo = new GetBOMInfoResDBMInfo();

            objEGetBOMInfoResDBMInfo.setBMRd(bomMaterialInfo.getRuid());
            //查询物料版本信息
            MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(bomMaterialInfo.getMaVerGd());
            if (objEMaVerInfo != null) {

                objEGetBOMInfoResDBMInfo.setMaVerRd(objEMaVerInfo.getRuid());
                objEGetBOMInfoResDBMInfo.setMaCode(objEMaVerInfo.getMaterialCode());
                objEGetBOMInfoResDBMInfo.setMaName(objEMaVerInfo.getMaterialName());
                //查询工序版本信息
                SpecVerInfo objESpecVerInfo = specVerDAO.SelectSpecVerInfoByguid(bomMaterialInfo.getSpecVerGd());
                if (objESpecVerInfo != null) {
                    objEGetBOMInfoResDBMInfo.setSpecVerRd(objESpecVerInfo.getRuid());
                    objEGetBOMInfoResDBMInfo.setSpecName(objESpecVerInfo.getSpecName());
                } else {
                    objEGetBOMInfoResDBMInfo.setSpecVerRd(0);
                    objEGetBOMInfoResDBMInfo.setSpecName("");
                }

                //查询替代料
                List<GetBOMInfoResDBReMaInfo> objEGetBOMInfoResDBReMaInfos = new ArrayList<GetBOMInfoResDBReMaInfo>();

                List<BomReMaterialInfo> objEBomReMaterialInfos = bomReMaterialDAO.SelectByBomMaGd(bomMaterialInfo.getGuid());
                for (BomReMaterialInfo bomReMaterialInfo : objEBomReMaterialInfos) {

                    GetBOMInfoResDBReMaInfo objEGetBOMInfoResDBReMaInfo = new GetBOMInfoResDBReMaInfo();
                    //查询该替代料信息
                    MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(bomReMaterialInfo.getMaVerGd());
                    if (maVerInfo != null) {
                        objEGetBOMInfoResDBReMaInfo.setMaVerRd(maVerInfo.getRuid());
                        objEGetBOMInfoResDBReMaInfo.setMaCode(maVerInfo.getMaterialCode());
                        objEGetBOMInfoResDBReMaInfo.setMaName(maVerInfo.getMaterialName());
                        objEGetBOMInfoResDBReMaInfos.add(objEGetBOMInfoResDBReMaInfo);
                    }
                }

                objEGetBOMInfoResDBMInfo.setNum(bomMaterialInfo.getNum());
                objEGetBOMInfoResDBMInfo.setUnitName(bomMaterialInfo.getUnitName());
                objEGetBOMInfoResDBMInfo.setChecked(bomMaterialInfo.getChecked());
                objEGetBOMInfoResDBMInfo.setReMaInfo(objEGetBOMInfoResDBReMaInfos);
                objEGetBOMInfoResDBMInfos.add(objEGetBOMInfoResDBMInfo);
            }
        }

        objEGetBOMInfoResD.setBMInfo(objEGetBOMInfoResDBMInfos);

        objEGetBOMInfoResB.setData(objEGetBOMInfoResD);
        objEGetBOMInfoRes.setBody(objEGetBOMInfoResB);

        return objEGetBOMInfoRes;
    }

    /**
     * 获取BOM版本列表信息
     *
     * @param argBD00
     * @return
     * @throws SystemException
     */
    @Override
    public GetBOMTreeInfoRes GetBOMTreeInfo(GetBOMTreeInfoReqBD00 argBD00) throws SystemException {
        GetBOMTreeInfoRes objEGetBOMTreeInfoRes = new GetBOMTreeInfoRes();
        GetBOMTreeInfoResB objEGetBOMTreeInfoResB = new GetBOMTreeInfoResB();
        List<GetBOMTreeInfoResD> objEGetBOMTreeInfoResDs = new ArrayList<GetBOMTreeInfoResD>();

        //查询Bom
        BomInfo objEBomInfo = bomDAO.SelectBomInfo(argBD00.getBomRd());
        //查询Bom版本
        List<BomVerInfo> objEBomVerInfos = bomVerDAO.SelectByBomGd(objEBomInfo.getGuid());
        for (BomVerInfo bomVerInfo : objEBomVerInfos) {
            GetBOMTreeInfoResD objEGetBOMTreeInfoResD = new GetBOMTreeInfoResD();
            objEGetBOMTreeInfoResD.setBomVerRd(bomVerInfo.getRuid());
            objEGetBOMTreeInfoResD.setVersion(bomVerInfo.getVersion());
            objEGetBOMTreeInfoResDs.add(objEGetBOMTreeInfoResD);
        }

        objEGetBOMTreeInfoResB.setData(objEGetBOMTreeInfoResDs);
        objEGetBOMTreeInfoRes.setBody(objEGetBOMTreeInfoResB);

        return objEGetBOMTreeInfoRes;
    }

    /**
     * 获取Bom清单信息
     *
     * @param argBD00
     * @return
     * @throws SystemException
     */
    @Override
    public GetBVInfoRes GetBVInfo(GetBVInfoReqBD00 argBD00) throws SystemException {
        GetBVInfoRes objEGetBVInfoRes = new GetBVInfoRes();
        GetBVInfoResB objEGetBVInfoResB = new GetBVInfoResB();
        GetBVInfoResD objEGetBVInfoResD = new GetBVInfoResD();

        //查询BOM清单
        BomInfo objEBomInfo = bomDAO.SelectBomInfo(argBD00.getBomRd());
        if (objEBomInfo == null) {
            throw new SystemException("", "BOM清单不存在!");
        }
        //查询Bom清单版本信息
        BomVerInfo objEBomVerInfo = bomVerDAO.SelectBomVerInfo(objEBomInfo.getVerGd());
        if (objEBomVerInfo == null) {
            throw new SystemException("", "Bom清单版本不存在!");
        }

        objEGetBVInfoResD = getBomVer(objEBomVerInfo);
        objEGetBVInfoResD.setStatus(objEBomInfo.getStatus());
        objEGetBVInfoResD.setVersionNo(objEBomInfo.getVersionNo());
        objEGetBVInfoResB.setData(objEGetBVInfoResD);
        objEGetBVInfoRes.setBody(objEGetBVInfoResB);

        return objEGetBVInfoRes;
    }

    /**
     * 获取Bom清单版本信息
     *
     * @param argBD01
     * @return
     * @throws SystemException
     */
    @Override
    public GetBVInfoRes GetBVInfo(GetBVInfoReqBD01 argBD01) {
        GetBVInfoRes objEGetBVInfoRes = new GetBVInfoRes();
        GetBVInfoResB objEGetBVInfoResB = new GetBVInfoResB();
        GetBVInfoResD objEGetBVInfoResD = new GetBVInfoResD();

        //查询Bom清单版本信息
        BomVerInfo objEBomVerInfo = bomVerDAO.SelectByRuid(argBD01.getBomVerRd());
        if (objEBomVerInfo == null) {
            throw new SystemException("", "Bom清单版本不存在!");
        }

        objEGetBVInfoResD = getBomVer(objEBomVerInfo);

        //查询BOM清单
        BomInfo objEBomInfo = bomDAO.SelectByGuid(objEBomVerInfo.getBomGd());
        if (objEBomInfo != null) {
            objEGetBVInfoResD.setVersionNo(objEBomInfo.getVersionNo());
        }

        objEGetBVInfoResB.setData(objEGetBVInfoResD);
        objEGetBVInfoRes.setBody(objEGetBVInfoResB);

        return objEGetBVInfoRes;
    }

    /**
     * 新增Bom清单信息
     *
     * @param argBD00
     * @return
     * @throws SystemException
     */
    @Override
    public SaveBomInfoRes AddBomInfo(SaveBomInfoReqBD00 argBD00) throws SystemException {
        SaveBomInfoRes objESaveBomInfoRes = new SaveBomInfoRes();
        SaveBomInfoResB objESaveBomInfoResB = new SaveBomInfoResB();
        SaveBomInfoResD objESaveBomInfoResD = new SaveBomInfoResD();

        //当前用户名
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();
        //Bom清单Guid
        String bomGuid = CommonUtils.getRandomNumber();
        //Bom清单版本Guid
        String bomVerGuid = CommonUtils.getRandomNumber();

        //判断物料名称是否重复
        BomInfo bomInfo = bomDAO.SelectByName(argBD00.getBomName());
        if (bomInfo != null) {
            throw new SystemException("", "Bom清单名称已存在");
        }

        //判断物料代码是否重复
        bomInfo = bomDAO.SelectByCode(argBD00.getBomCode());
        if (bomInfo != null && !"".equals(argBD00.getBomCode())) {
            throw new SystemException("", "Bom清单代码已存在");
        }

        //新增Bom清单
        BomInfo objEBomInfo = new BomInfo();
        objEBomInfo.setGuid(bomGuid);
        objEBomInfo.setBomName(argBD00.getBomName());
        //查询代码生成
        CodeRuleInfo codeRuleInfo = codeRuleDAO.SelectCodeRuleInfocodeType("01");

        String SCode = "";

        if (!"".equals(argBD00.getBomCode())) {
            objEBomInfo.setBomCode(argBD00.getBomCode());
        } else if (codeRuleInfo != null && "00".equals(codeRuleInfo.getStatus())) {
            SCode = gConfigIService.GetCreateSC(codeRuleInfo);
            objEBomInfo.setBomCode(SCode);
        } else {
            throw new SystemException("", "请输入{BOM代码}，或请到全局配置进行代码生成配置");
        }

        objEBomInfo.setVerGd(bomVerGuid);
        objEBomInfo.setVersionNo(argBD00.getVersionNo());
        objEBomInfo.setStatus(argBD00.getStatus());
        objEBomInfo.setCreator(userName);
        objEBomInfo.setCreateTime(date);
        objEBomInfo.setRemark(argBD00.getRemark());
        bomDAO.InsertBomInfo(objEBomInfo);

        //新增Bom清单版本信息
        BomVerInfo objEBomVerInfo = new BomVerInfo();
        objEBomVerInfo.setGuid(bomVerGuid);
        objEBomVerInfo.setBomGd(bomGuid);
        if (!"".equals(argBD00.getBomCode())) {
            objEBomVerInfo.setBomCode(argBD00.getBomCode());
        } else {
            objEBomVerInfo.setBomCode(SCode);
        }
        objEBomVerInfo.setBomName(argBD00.getBomName());
        objEBomVerInfo.setVersion(argBD00.getVersionNo());
        objEBomVerInfo.setIsDef("00");
        objEBomVerInfo.setCreator(userName);
        objEBomVerInfo.setCreateTime(date);
        objEBomVerInfo.setRemark(argBD00.getRemark());
        bomVerDAO.InsertBomVerInfo(objEBomVerInfo);

        //BMInfo用料清单
        for (SaveBomInfoReqBD00BMInfo bmInfo : argBD00.getBMInfo()) {
            BomMaterialInfo objEBomMaterialInfo = new BomMaterialInfo();
            String bomMaGd = CommonUtils.getRandomNumber();
            objEBomMaterialInfo.setGuid(bomMaGd);
            objEBomMaterialInfo.setBomVerGd(bomVerGuid);
            objEBomMaterialInfo.setaRate(bmInfo.getARate());
            //查询物料版本
            MaVerInfo maVerInfo = maVerDAO.selectOne(new QueryWrapper<MaVerInfo>().eq("materialCode", bmInfo.getMaCode()));
            if (maVerInfo == null) {
                throw new SystemException("", "物料版本不存在");
            }
            objEBomMaterialInfo.setMaVerGd(maVerInfo.getGuid());
            /*objEBomMaterialInfo.setMaterialCode(maVerInfo.getMaterialCode());
            objEBomMaterialInfo.setMaterialName(maVerInfo.getMaterialName());*/
            //查询工序版本
            SpecVerInfo specVerInfo = specVerDAO.SelectSpecVerInfoByruid(bmInfo.getSpecVerRd());
            if (specVerInfo == null) {
                throw new SystemException("", bmInfo.getMaName() + "物料所对应的工序版本不存在!");
            }

            objEBomMaterialInfo.setSpecVerGd(specVerInfo.getGuid());
            objEBomMaterialInfo.setNum(bmInfo.getNum());
            objEBomMaterialInfo.setUnitName(bmInfo.getUnitName());
            /*if(bmInfo.getConSMode().equals("仅显示")){
                objEBomMaterialInfo.setConSMode("00");
            }else if(bmInfo.getConSMode().equals("批次")){
                objEBomMaterialInfo.setConSMode("01");
            }else if(bmInfo.getConSMode().equals("序号")){
                objEBomMaterialInfo.setConSMode("02");
            }else {
                throw new SystemException("","请核对消耗方式是否匹配");
            }*/
            objEBomMaterialInfo.setConSMode(bmInfo.getConSMode());
            objEBomMaterialInfo.setChecked(StringUtils.isBlank(bmInfo.getChecked()) ? "00" : bmInfo.getChecked());
            objEBomMaterialInfo.setCreator(userName);
            objEBomMaterialInfo.setCreateTime(date);
            objEBomMaterialInfo.setRemark(argBD00.getRemark());
            bomMaterialDAO.InsertBomMaterialInfo(objEBomMaterialInfo);

            //新增替代料
            if (bmInfo.getReMaInfo() != null) {
                BomReMaterialInfo objEBomReMaterialInfo = null;

                for (SaveBomInfoReqBD00BReMaInfo reMaInfo : bmInfo.getReMaInfo()) {
                    //查询物料
                    MaVerInfo maVerInfo1 = maVerDAO.selectOne(new QueryWrapper<MaVerInfo>().eq("materialCode", reMaInfo.getMaCode()));
                    if (maVerInfo1 != null) {
                        objEBomReMaterialInfo = new BomReMaterialInfo();
                        objEBomReMaterialInfo.setGuid(CommonUtils.getRandomNumber());
                        objEBomReMaterialInfo.setBomMaGd(bomMaGd);
                        objEBomReMaterialInfo.setBomVerGd(objEBomVerInfo.getGuid());
                        objEBomReMaterialInfo.setSpecVerGd(specVerInfo.getGuid());
                        objEBomReMaterialInfo.setMaVerGd(maVerInfo1.getGuid());
                        objEBomReMaterialInfo.setCreator(userName);
                        objEBomReMaterialInfo.setCreateTime(date);
                        objEBomReMaterialInfo.setaRate(reMaInfo.getARate());
                        objEBomReMaterialInfo.setNum(reMaInfo.getNum());
                        bomReMaterialDAO.InsertBomReMaterialInfo(objEBomReMaterialInfo);
                    }
                }
            }
        }

        BomInfo objEBomInfos = bomDAO.SelectByGuid(objEBomInfo.getGuid());
        objESaveBomInfoResD.setBomRd(objEBomInfos.getRuid());
        objESaveBomInfoResD.setBomCode(objEBomInfos.getBomCode());
        objESaveBomInfoResB.setData(objESaveBomInfoResD);
        objESaveBomInfoRes.setBody(objESaveBomInfoResB);
        return objESaveBomInfoRes;
    }

    /**
     * 编辑Bom清单信息
     *
     * @param argBD03
     * @return
     * @throws SystemException
     */
    @Override
    public SaveBomInfoRes ModBomInfo(SaveBomInfoReqBD03 argBD03) throws SystemException {
        SaveBomInfoRes objESaveBomInfoRes = new SaveBomInfoRes();

        //当前用户名
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();

        //查询Bom版本
        BomVerInfo objEBomVerInfo = bomVerDAO.SelectByRuid(argBD03.getBomVerRd());
        if (objEBomVerInfo == null) {
            throw new SystemException("", "BOM版本为空!");
        }

        //判断版本是否重复
        List<BomVerInfo> bomVerInfo = bomVerDAO.SelectByBomGdVersion(objEBomVerInfo.getBomGd(), argBD03.getVersionNo());
        if (bomVerInfo.size() > 0) {
            throw new SystemException("", "工程变更单号已存在!");
        }

        //修改原来Bom版本信息
        objEBomVerInfo.setIsDef("01");
        if (bomVerDAO.UpdateByBomGd(objEBomVerInfo) <= 0) {
            throw new SystemException("", "BOM变更失败!");
        }

        //新增Bom清单版本信息
        BomVerInfo objEBomVerInfoNew = new BomVerInfo();
        objEBomVerInfoNew.setGuid(CommonUtils.getRandomNumber());
        objEBomVerInfoNew.setBomGd(objEBomVerInfo.getBomGd());
        objEBomVerInfoNew.setBomCode(objEBomVerInfo.getBomCode());
        objEBomVerInfoNew.setBomName(objEBomVerInfo.getBomName());
        objEBomVerInfoNew.setVersion(argBD03.getVersionNo());
        objEBomVerInfoNew.setIsDef("00");
        objEBomVerInfoNew.setCreator(userName);
        objEBomVerInfoNew.setCreateTime(date);
        objEBomVerInfoNew.setRemark(argBD03.getRemark());
        bomVerDAO.InsertBomVerInfo(objEBomVerInfoNew);

        //修改Bom信息
        BomInfo objEBomInfo = new BomInfo();
        objEBomInfo.setGuid(objEBomVerInfo.getBomGd());
        objEBomInfo.setVerGd(objEBomVerInfoNew.getGuid());
        objEBomInfo.setVersionNo(argBD03.getVersionNo());
        objEBomInfo.setStatus(argBD03.getStatus());
        if (bomDAO.UpdateByGuid(objEBomInfo) <= 0) {
            throw new SystemException("", "BOM变更失败!");
        }

        //变更记录信息
        BomChgInfo objEBomChgInfo = new BomChgInfo();
        objEBomChgInfo.setGuid(CommonUtils.getRandomNumber());
        objEBomChgInfo.setBomGd(objEBomInfo.getGuid());
        objEBomChgInfo.setVersion(objEBomVerInfoNew.getVersion());
        objEBomChgInfo.setCreator(userName);
        objEBomChgInfo.setCreateTime(date);
        objEBomChgInfo.setRemark(argBD03.getRemark());
        bomChgDAO.InsertBomChg(objEBomChgInfo);

        //变更记录明细信息
        BomChgDlInfo objEBomChgDlInfo = null;
        //变更前后工序
        SpecVerInfo objESpecVerInfoOld = null, objESpecVerInfoNew = null;

        //查询Bom用料信息->Old
        List<BomMaterialInfo> objEBomMaterialInfos = bomMaterialDAO.SelectByBomVerGd(objEBomVerInfo.getGuid());
        //逻辑判断分离删除与添加
        boolean flag = true;
        for (SaveBomInfoReqBD03BMInfo bmInfo : argBD03.getBMInfo()) {
            flag = true;

            for (int i = 0; i < objEBomMaterialInfos.size(); i++) {
                if (bmInfo.getBMRd() == objEBomMaterialInfos.get(i).getRuid()) {
                    BomMaterialInfo bomMaterialInfo = objEBomMaterialInfos.get(i);
                    //编辑
                    if (checkUpdate(bmInfo, bomMaterialInfo, userName, date)) {
                        //获取工序版本-变更前
                        objESpecVerInfoOld = specVerDAO.SelectSpecVerInfoByguid(bomMaterialInfo.getSpecVerGd());
                        if (objESpecVerInfoOld == null) {
                            //throw new SystemException("", bomMaterialInfo.getMaterialName() + "物料变更前所对应的工序版本不存在!");
                            objESpecVerInfoOld = new SpecVerInfo();
                            objESpecVerInfoOld.setSpecName("");
                        }
                        objESpecVerInfoNew = specVerDAO.SelectSpecVerInfoByruid(bmInfo.getSpecVerRd());
                        if (objESpecVerInfoNew == null) {
                            throw new SystemException("", bmInfo.getMaName() + "物料变更后所对应的工序版本不存在!");
                        }

                        //查询物料信息
                        MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(bomMaterialInfo.getMaVerGd());
                        if (maVerInfo == null) {
                            throw new SystemException("", "Bom中ID为" + bomMaterialInfo.getMaVerGd() + "物料已经不存在");
                        }

                        objEBomChgDlInfo = new BomChgDlInfo();
                        objEBomChgDlInfo.setqARate(bomMaterialInfo.getaRate());
                        objEBomChgDlInfo.setGuid(CommonUtils.getRandomNumber());
                        objEBomChgDlInfo.setBomChgGd(objEBomChgInfo.getGuid());
                        objEBomChgDlInfo.setqMaterialCode(maVerInfo.getMaterialCode());
                        objEBomChgDlInfo.setqMaterialName(maVerInfo.getMaterialName());
                        objEBomChgDlInfo.setqSpecName(objESpecVerInfoOld.getSpecName());
                        objEBomChgDlInfo.setqNum(bomMaterialInfo.getNum());
                        objEBomChgDlInfo.setqConSMode(bomMaterialInfo.getConSMode());
                        objEBomChgDlInfo.setqChecked(bomMaterialInfo.getChecked());
                        //变更后
                        objEBomChgDlInfo.sethMaterialCode(bmInfo.getMaCode());
                        objEBomChgDlInfo.sethMaterialName(bmInfo.getMaName());
                        objEBomChgDlInfo.sethSpecName(objESpecVerInfoNew.getSpecName());
                        objEBomChgDlInfo.sethNum(bmInfo.getNum());
                        /*if(bmInfo.getConSMode().equals("仅显示")){
                            objEBomChgDlInfo.sethConSMode("00");
                        }else if(bmInfo.getConSMode().equals("批次")){
                            objEBomChgDlInfo.sethConSMode("01");
                        }else if(bmInfo.getConSMode().equals("序号")){
                            objEBomChgDlInfo.sethConSMode("02");
                        }else {
                            throw new SystemException("","请核对消耗方式是否匹配");
                        }*/
                        objEBomChgDlInfo.sethConSMode(bmInfo.getConSMode());
                        objEBomChgDlInfo.sethChecked(bmInfo.getChecked());
                        objEBomChgDlInfo.setOptType("02");
                        objEBomChgDlInfo.setCreator(userName);
                        objEBomChgDlInfo.setCreateTime(date);
                        bomChgDlDAO.InsertBomChgDl(objEBomChgDlInfo);
                    }

                    objEBomMaterialInfos.remove(i);
                    flag = false;
                    break;
                }
            }

            if (flag) {
                //变更后
                objESpecVerInfoNew = specVerDAO.SelectSpecVerInfoByruid(bmInfo.getSpecVerRd());
                if (objESpecVerInfoNew == null) {
                    throw new SystemException("", bmInfo.getMaName() + "物料变更后所对应的工序版本不存在!");
                }

                objEBomChgDlInfo = new BomChgDlInfo();
                objEBomChgDlInfo.setGuid(CommonUtils.getRandomNumber());
                objEBomChgDlInfo.setBomChgGd(objEBomChgInfo.getGuid());
                objEBomChgDlInfo.setqMaterialCode("");
                objEBomChgDlInfo.setqMaterialName("");
                objEBomChgDlInfo.setqSpecName("");
                objEBomChgDlInfo.setqNum(0f);
                objEBomChgDlInfo.setqConSMode("");
                objEBomChgDlInfo.setqChecked("");
                objEBomChgDlInfo.setqARate(bmInfo.getARate());
                //变更后
                objEBomChgDlInfo.sethMaterialCode(bmInfo.getMaCode());
                objEBomChgDlInfo.sethMaterialName(bmInfo.getMaName());
                objEBomChgDlInfo.sethSpecName(objESpecVerInfoNew.getSpecName());
                objEBomChgDlInfo.sethNum(bmInfo.getNum());
               /* if(bmInfo.getConSMode().equals("仅显示")){
                    objEBomChgDlInfo.sethConSMode("00");
                }else if(bmInfo.getConSMode().equals("批次")){
                    objEBomChgDlInfo.sethConSMode("01");
                }else if(bmInfo.getConSMode().equals("序号")){
                    objEBomChgDlInfo.sethConSMode("02");
                }else {
                    throw new SystemException("","请核对消耗方式是否匹配");
                }*/
                objEBomChgDlInfo.sethConSMode(bmInfo.getConSMode());
                objEBomChgDlInfo.sethChecked(bmInfo.getChecked());
                objEBomChgDlInfo.setOptType("00");
                objEBomChgDlInfo.setCreator(userName);
                objEBomChgDlInfo.setCreateTime(date);
                objEBomChgDlInfo.setqARate(bmInfo.getARate());
                bomChgDlDAO.InsertBomChgDl(objEBomChgDlInfo);
            }

            //新增版本
            BomMaterialInfo objEBomMaterialInfo = new BomMaterialInfo();
            String bomMaGd = CommonUtils.getRandomNumber();

            objEBomMaterialInfo.setGuid(bomMaGd);
            objEBomMaterialInfo.setBomVerGd(objEBomVerInfoNew.getGuid());

            //查询物料版本
            MaVerInfo maVerInfo = maVerDAO.selectOne(new QueryWrapper<MaVerInfo>().eq("materialCode", bmInfo.getMaCode()));
            if (maVerInfo == null) {
                throw new SystemException("", "物料版本不存在");
            }

            objEBomMaterialInfo.setMaVerGd(maVerInfo.getGuid());

            //查询工序版本
            SpecVerInfo specVerInfo = specVerDAO.SelectSpecVerInfoByruid(bmInfo.getSpecVerRd());
            if (specVerInfo == null) {
                throw new SystemException("", maVerInfo.getMaterialName() + "物料所对应的工序版本不存在!");
            }
            objEBomMaterialInfo.setaRate(bmInfo.getARate());
            objEBomMaterialInfo.setSpecVerGd(specVerInfo.getGuid());
            objEBomMaterialInfo.setNum(bmInfo.getNum());
            objEBomMaterialInfo.setUnitName(bmInfo.getUnitName());
            /*if(bmInfo.getConSMode().equals("仅显示")){
                objEBomMaterialInfo.setConSMode("00");
            }else if(bmInfo.getConSMode().equals("批次")){
                objEBomMaterialInfo.setConSMode("01");
            }else if(bmInfo.getConSMode().equals("序号")){
                objEBomMaterialInfo.setConSMode("02");
            }else {
                throw new SystemException("","请核对消耗方式是否匹配");
            }*/
            objEBomMaterialInfo.setConSMode(bmInfo.getConSMode());
            objEBomMaterialInfo.setChecked("00");
            objEBomMaterialInfo.setCreator(userName);
            objEBomMaterialInfo.setCreateTime(date);

            objEBomMaterialInfo.setRemark(argBD03.getRemark());
            bomMaterialDAO.InsertBomMaterialInfo(objEBomMaterialInfo);

            //新增替代料
            if (bmInfo.getReMaInfo() != null) {
                BomReMaterialInfo objEBomReMaterialInfo = null;

                for (SaveBomInfoReqBD03BMReMaInfo reMaInfo : bmInfo.getReMaInfo()) {
                    //查询物料
                    MaVerInfo maVerInfo1 = maVerDAO.selectOne(new QueryWrapper<MaVerInfo>().eq("materialCode", reMaInfo.getMaCode()));
                    if (maVerInfo1 != null) {
                        objEBomReMaterialInfo = new BomReMaterialInfo();
                        objEBomReMaterialInfo.setGuid(CommonUtils.getRandomNumber());
                        objEBomReMaterialInfo.setBomMaGd(bomMaGd);
                        objEBomReMaterialInfo.setBomVerGd(objEBomVerInfoNew.getGuid());
                        objEBomReMaterialInfo.setSpecVerGd(specVerInfo.getGuid());
                        objEBomReMaterialInfo.setMaVerGd(maVerInfo1.getGuid());
                        objEBomReMaterialInfo.setCreator(userName);
                        objEBomReMaterialInfo.setCreateTime(date);
                        objEBomReMaterialInfo.setaRate(reMaInfo.getARate());
                        objEBomReMaterialInfo.setNum(reMaInfo.getNum());
                        bomReMaterialDAO.InsertBomReMaterialInfo(objEBomReMaterialInfo);
                    }
                }
            }
        }

        for (BomMaterialInfo bomMaterialInfo : objEBomMaterialInfos) {
            //获取工序版本-变更前
            objESpecVerInfoOld = specVerDAO.SelectSpecVerInfoByguid(bomMaterialInfo.getSpecVerGd());
            if (objESpecVerInfoOld == null) {
                //throw new SystemException("", bomMaterialInfo.getMaterialName() + "物料变更前所对应的工序版本不存在!");
                objESpecVerInfoOld = new SpecVerInfo();
                objESpecVerInfoOld.setSpecName("");
            }

            //查询物料信息
            MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(bomMaterialInfo.getMaVerGd());
            if (maVerInfo == null) {
                throw new SystemException("", "Bom中ID为" + bomMaterialInfo.getMaVerGd() + "物料已经不存在");
            }

            objEBomChgDlInfo = new BomChgDlInfo();
            objEBomChgDlInfo.setqARate(bomMaterialInfo.getaRate());
            objEBomChgDlInfo.setGuid(CommonUtils.getRandomNumber());
            objEBomChgDlInfo.setBomChgGd(objEBomChgInfo.getGuid());
            objEBomChgDlInfo.setqMaterialCode(maVerInfo.getMaterialCode());
            objEBomChgDlInfo.setqMaterialName(maVerInfo.getMaterialName());
            objEBomChgDlInfo.setqSpecName(objESpecVerInfoOld.getSpecName());
            objEBomChgDlInfo.setqNum(bomMaterialInfo.getNum());
            objEBomChgDlInfo.setqConSMode(bomMaterialInfo.getConSMode());
            objEBomChgDlInfo.setqChecked(bomMaterialInfo.getChecked());
            //变更后
            objEBomChgDlInfo.sethMaterialCode("");
            objEBomChgDlInfo.sethMaterialName("");
            objEBomChgDlInfo.sethSpecName("");
            objEBomChgDlInfo.sethNum(0f);
            objEBomChgDlInfo.sethConSMode("");
            objEBomChgDlInfo.sethChecked("");
            objEBomChgDlInfo.setOptType("01");
            objEBomChgDlInfo.setCreator(userName);
            objEBomChgDlInfo.setCreateTime(date);
            bomChgDlDAO.InsertBomChgDl(objEBomChgDlInfo);
        }

        return objESaveBomInfoRes;
    }

    /**
     * 获取Bom版本信息
     *
     * @param objEBomVerInfo
     * @return
     */
    private GetBVInfoResD getBomVer(BomVerInfo objEBomVerInfo) {
        GetBVInfoResD objEGetBVInfoResD = new GetBVInfoResD();

        objEGetBVInfoResD.setBomVerRd(objEBomVerInfo.getRuid());
        objEGetBVInfoResD.setBomCode(objEBomVerInfo.getBomCode());
        objEGetBVInfoResD.setBomName(objEBomVerInfo.getBomName());
        objEGetBVInfoResD.setVersion(objEBomVerInfo.getVersion());
        objEGetBVInfoResD.setCreator(objEBomVerInfo.getCreator());
        objEGetBVInfoResD.setCreateTime(DateUtil.format(objEBomVerInfo.getCreateTime()));
        objEGetBVInfoResD.setLastModifyMan(objEBomVerInfo.getLastModifyMan());
        objEGetBVInfoResD.setLastModifyTime(DateUtil.format(objEBomVerInfo.getLastModifyTime()));
        objEGetBVInfoResD.setRemark(objEBomVerInfo.getRemark());

        //下边BMInfo
        List<GetBVInfoResDBMInfo> objEGetBVInfoResDBMInfos = new ArrayList<GetBVInfoResDBMInfo>();
        List<BomMaterialInfo> objEBomMaterialInfos = bomMaterialDAO.SelectByBomVerGd(objEBomVerInfo.getGuid());
        for (BomMaterialInfo bomMaterialInfo : objEBomMaterialInfos) {
            GetBVInfoResDBMInfo objEGetBVInfoResDBMInfo = new GetBVInfoResDBMInfo();

            objEGetBVInfoResDBMInfo.setBMRd(bomMaterialInfo.getRuid());
            //查询物料版本信息
            MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(bomMaterialInfo.getMaVerGd());
            if (objEMaVerInfo != null) {

                objEGetBVInfoResDBMInfo.setMaVerRd(objEMaVerInfo.getRuid());
                objEGetBVInfoResDBMInfo.setMaCode(objEMaVerInfo.getMaterialCode());
                objEGetBVInfoResDBMInfo.setMaName(objEMaVerInfo.getMaterialName());
                if ("".equals(objEMaVerInfo.getMaterialDes()) || objEMaVerInfo.getMaterialDes() == null) {
                    objEGetBVInfoResDBMInfo.setMaDes("");
                } else {
                    objEGetBVInfoResDBMInfo.setMaDes(objEMaVerInfo.getMaterialDes());
                }


                //查询工序版本信息
                SpecVerInfo objESpecVerInfo = specVerDAO.SelectSpecVerInfoByguid(bomMaterialInfo.getSpecVerGd());
                if (objESpecVerInfo != null) {
                    objEGetBVInfoResDBMInfo.setSpecVerRd(objESpecVerInfo.getRuid());
                    objEGetBVInfoResDBMInfo.setSpecName(objESpecVerInfo.getSpecName());
                } else {
                    objEGetBVInfoResDBMInfo.setSpecVerRd(0);
                    objEGetBVInfoResDBMInfo.setSpecName("");
                }

                //查询替代料
                List<GetBVInfoResDBReMaInfo> objEGetBVInfoResDBReMaInfos = new ArrayList<GetBVInfoResDBReMaInfo>();

                List<BomReMaterialInfo> objEBomReMaterialInfos = bomReMaterialDAO.SelectByBomMaGd(bomMaterialInfo.getGuid());
                for (BomReMaterialInfo bomReMaterialInfo : objEBomReMaterialInfos) {

                    GetBVInfoResDBReMaInfo objEGetBVInfoResDBReMaInfo = new GetBVInfoResDBReMaInfo();
                    //查询该替代料信息
                    MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(bomReMaterialInfo.getMaVerGd());
                    if (maVerInfo != null) {
                        objEGetBVInfoResDBReMaInfo.setMaVerRd(maVerInfo.getRuid());
                        objEGetBVInfoResDBReMaInfo.setMaCode(maVerInfo.getMaterialCode());
                        objEGetBVInfoResDBReMaInfo.setMaName(maVerInfo.getMaterialName());
                        if ("".equals(maVerInfo.getMaterialDes()) || maVerInfo.getMaterialDes() == null) {
                            objEGetBVInfoResDBReMaInfo.setMaDes("");
                        } else {
                            objEGetBVInfoResDBReMaInfo.setMaDes(maVerInfo.getMaterialDes());
                        }


                        objEGetBVInfoResDBReMaInfo.setARate(bomReMaterialInfo.getaRate());
                        objEGetBVInfoResDBReMaInfo.setNum(bomReMaterialInfo.getNum());
                        objEGetBVInfoResDBReMaInfos.add(objEGetBVInfoResDBReMaInfo);
                    }
                }
                objEGetBVInfoResDBMInfo.setARate(bomMaterialInfo.getaRate());
                objEGetBVInfoResDBMInfo.setNum(bomMaterialInfo.getNum());
                //查询单位
                UnitInfo unitInfo = unitDAO.SelectByGuid(objEMaVerInfo.getUnitGd());
                if (unitInfo == null) {
                    objEGetBVInfoResDBMInfo.setUnitName(bomMaterialInfo.getUnitName());
                } else {
                    objEGetBVInfoResDBMInfo.setUnitName(unitInfo.getUnitName());
                }
                objEGetBVInfoResDBMInfo.setConSMode(bomMaterialInfo.getConSMode());
                objEGetBVInfoResDBMInfo.setChecked(bomMaterialInfo.getChecked());
                objEGetBVInfoResDBMInfo.setReMaInfo(objEGetBVInfoResDBReMaInfos);

                objEGetBVInfoResDBMInfos.add(objEGetBVInfoResDBMInfo);
            }
        }
        objEGetBVInfoResD.setBMInfo(objEGetBVInfoResDBMInfos);

        return objEGetBVInfoResD;
    }

    /**
     * 判断BOM物料是否有变更
     *
     * @param argBMInfo
     * @param argBomMaterialInfo
     * @param userName
     * @param date
     * @return
     */
    private boolean checkUpdate(SaveBomInfoReqBD03BMInfo argBMInfo, BomMaterialInfo argBomMaterialInfo, String userName, Date date) {
        boolean flag = false;

        //查询物料
        MaVerInfo objEMaVerInfo = maVerDAO.SelectByRuid(argBMInfo.getMaVerRd());
        if (objEMaVerInfo == null) {
            throw new SystemException("", argBMInfo.getMaName() + "物料不存在!");
        }
        //查询工序
        SpecVerInfo objESpecVerInfo = specVerDAO.SelectSpecVerInfoByruid(argBMInfo.getSpecVerRd());
        if (objESpecVerInfo == null) {
            throw new SystemException("", argBMInfo.getMaName() + "物料所对应的工序不存在!");
        }
        //判断是否修改
        if (!objEMaVerInfo.getGuid().equals(argBomMaterialInfo.getMaVerGd())) {
            flag = true;
        } else if (!objESpecVerInfo.getGuid().equals(argBomMaterialInfo.getSpecVerGd())) {
            flag = true;
        } else if (!argBMInfo.getConSMode().equals(argBomMaterialInfo.getConSMode())) {
            flag = true;
        } else if (!(argBMInfo.getNum() == argBomMaterialInfo.getNum())) {
            flag = true;
        } else if (!argBMInfo.getChecked().equals(argBomMaterialInfo.getChecked())) {
            flag = true;
        } else if (!argBMInfo.getUnitName().equals(argBomMaterialInfo.getUnitName())) {
            flag = true;
        }

        if (flag) {
            //删除用料替代料信息->Old
            bomReMaterialDAO.DeleteByMaVerGd(argBomMaterialInfo.getGuid());

            //逻辑判断分离删除与添加2
            for (SaveBomInfoReqBD03BMReMaInfo reMaInfo : argBMInfo.getReMaInfo()) {
                //查询物料
                MaVerInfo maVerInfo1 = maVerDAO.SelectByRuid(reMaInfo.getMaVerRd());
                if (maVerInfo1 != null) {

                    BomReMaterialInfo objEBomReMaterialInfo = new BomReMaterialInfo();
                    objEBomReMaterialInfo.setGuid(CommonUtils.getRandomNumber());
                    objEBomReMaterialInfo.setBomMaGd(argBomMaterialInfo.getGuid());
                    objEBomReMaterialInfo.setMaVerGd(maVerInfo1.getGuid());
                    objEBomReMaterialInfo.setCreator(userName);
                    objEBomReMaterialInfo.setaRate(reMaInfo.getARate());
                    objEBomReMaterialInfo.setNum(reMaInfo.getNum());
                    objEBomReMaterialInfo.setCreateTime(date);
                    bomReMaterialDAO.InsertBomReMaterialInfo(objEBomReMaterialInfo);
                }
            }

            //修改
            //获取物料版本信息
            /*MaVerInfo maVerInfo = maVerDAO.SelectByRuid(argBMInfo.getMaVerRd());
            if (maVerInfo == null) {
                throw new SystemException("", "物料不存在");
            }
            argBomMaterialInfo.setMaVerGd(maVerInfo.getGuid());
            argBomMaterialInfo.setMaterialCode(maVerInfo.getMaterialCode());
            argBomMaterialInfo.setMaterialName(maVerInfo.getMaterialName());
            //获取工序版本
            SpecVerInfo specVerInfo = specVerDAO.SelectSpecVerInfoByruid(argBMInfo.getSpecVerRd());
            if (maVerInfo == null) {
                throw new SystemException("", "工序版本不存在");
            }
            argBomMaterialInfo.setSpecVerGd(specVerInfo.getGuid());
            argBomMaterialInfo.setNum(argBMInfo.getNum());
            argBomMaterialInfo.setUnitName(argBMInfo.getUnitName());
            argBomMaterialInfo.setChecked(argBMInfo.getChecked());
            argBomMaterialInfo.setLastModifyMan(userName);
            argBomMaterialInfo.setLastModifyTime(date);
            if (bomMaterialDAO.UpdateByRuid(argBomMaterialInfo) < 0) {
                throw new SystemException("", "BOM用料修改失败");
            }*/
        }
        return flag;
    }

    //变更记录列表
    @Override
    public GetAllBHChgInfoResB QALLBOMChgInfo(int bomRd) {
        GetAllBHChgInfoResB objGetAllBHChgInfoResB = new GetAllBHChgInfoResB();
        List<GetAllBHChgInfoResBD> objGetAllBHChgInfoResBDList = new ArrayList<GetAllBHChgInfoResBD>();
        //根据BOM的rd查询出BOM
        if (bomRd == 0) {
            throw new SystemException("11", "BOM的ID不能为空");
        }
        BomInfo bomInfo = bomDAO.SelectBomInfo(bomRd);
        if (bomInfo == null) {
            throw new SystemException("12", "BOM不存在");
        }
        //根据BOM的Guid查询变更列表
        List<BomChgInfo> bomChgInfoList = new ArrayList<BomChgInfo>();
        bomChgInfoList = bomChgDAO.SelectBomChgInfoByBomGd(bomInfo.getGuid());

        if (bomChgInfoList != null && bomChgInfoList.size() > 0) {
            for (BomChgInfo bomChgInfo : bomChgInfoList) {
                GetAllBHChgInfoResBD objGetAllBHChgInfoResBD = new GetAllBHChgInfoResBD();
                objGetAllBHChgInfoResBD.setBomChgRd(bomChgInfo.getRuid());
                objGetAllBHChgInfoResBD.setVersion(bomChgInfo.getVersion());
                objGetAllBHChgInfoResBD.setCreator(bomChgInfo.getCreator());
                objGetAllBHChgInfoResBD.setCreateTime(CommonUtils.getFormat(bomChgInfo.getCreateTime()));
                objGetAllBHChgInfoResBD.setRemark(bomChgInfo.getRemark());
                objGetAllBHChgInfoResBDList.add(objGetAllBHChgInfoResBD);
            }
        }
        objGetAllBHChgInfoResB.setData(objGetAllBHChgInfoResBDList);
        objGetAllBHChgInfoResB.setMsgCode("0x00000");
        objGetAllBHChgInfoResB.setMsgDes("成功");
        return objGetAllBHChgInfoResB;
    }

    //变更记录明细
    @Override
    public GetBHChgDtlInfoResB QALLBOMChgInfoDetail(int bomChgRd) {
        GetBHChgDtlInfoResB objGetBHChgDtlInfoResB = new GetBHChgDtlInfoResB();
        List<GetBHChgDtlInfoResBD> objGetBHChgDtlInfoResBDList = new ArrayList<GetBHChgDtlInfoResBD>();
        BomChgInfo bomChgInfo = bomChgDAO.SelectByRuid(bomChgRd);
        if (bomChgInfo == null)
            throw new SystemException("11", "BOM工程变更不存在");
        //根据bomChgGd查询明细
        List<BomChgDlInfo> bomChgDlInfoList = new ArrayList<BomChgDlInfo>();
        bomChgDlInfoList = bomChgDlDAO.SelectByBomChgGd(bomChgInfo.getGuid());
        for (BomChgDlInfo bomChgDlInfo : bomChgDlInfoList) {
            GetBHChgDtlInfoResBD getBHChgDtlInfoResBD = new GetBHChgDtlInfoResBD();

            //变更后
            GetBHChgDtlInfoResBDHChg getBHChgDtlInfoResBDHChg = new GetBHChgDtlInfoResBDHChg();
            getBHChgDtlInfoResBDHChg.setMaCode(bomChgDlInfo.gethMaterialCode());
            getBHChgDtlInfoResBDHChg.setMaName(bomChgDlInfo.gethMaterialName());
            getBHChgDtlInfoResBDHChg.setConSMode(bomChgDlInfo.gethConSMode());
            getBHChgDtlInfoResBDHChg.setNum(bomChgDlInfo.gethNum());
            getBHChgDtlInfoResBDHChg.setARate(bomChgDlInfo.getqARate());
            getBHChgDtlInfoResBDHChg.setSpecName(bomChgDlInfo.gethSpecName());
            getBHChgDtlInfoResBDHChg.setChecked(bomChgDlInfo.gethChecked());
            getBHChgDtlInfoResBD.setHChg(getBHChgDtlInfoResBDHChg);
            //变更前
            GetBHChgDtlInfoResBDQChg getBHChgDtlInfoResBDQChg = new GetBHChgDtlInfoResBDQChg();
            getBHChgDtlInfoResBDQChg.setMaCode(bomChgDlInfo.getqMaterialCode());
            getBHChgDtlInfoResBDQChg.setMaName(bomChgDlInfo.getqMaterialName());
            getBHChgDtlInfoResBDQChg.setConSMode(bomChgDlInfo.getqConSMode());
            getBHChgDtlInfoResBDQChg.setNum(bomChgDlInfo.getqNum());
            getBHChgDtlInfoResBDQChg.setARate(bomChgDlInfo.getqARate());
            getBHChgDtlInfoResBDQChg.setSpecName(bomChgDlInfo.getqSpecName());
            getBHChgDtlInfoResBDQChg.setChecked(bomChgDlInfo.getqChecked());
            getBHChgDtlInfoResBD.setQChg(getBHChgDtlInfoResBDQChg);

            getBHChgDtlInfoResBD.setOptType(bomChgDlInfo.getOptType());
            objGetBHChgDtlInfoResBDList.add(getBHChgDtlInfoResBD);
        }
        objGetBHChgDtlInfoResB.setMsgCode("0x00000");
        objGetBHChgDtlInfoResB.setMsgDes("成功");
        objGetBHChgDtlInfoResB.setData(objGetBHChgDtlInfoResBDList);
        return objGetBHChgDtlInfoResB;
    }

    //导入BOM
    @Override
    public SaveImportResB AddImportBOM(CommonsMultipartFile file) throws IOException {
        SaveImportResB objSaveImportResB = new SaveImportResB();
        HSSFWorkbook wb = new HSSFWorkbook(file.getInputStream());
        BomInfo bomInfo = null;
        BomVerInfo bomVerInfo = null;
        for (int n = 0; n < wb.getNumberOfSheets(); n++) {
            Sheet sheet = wb.getSheetAt(n);
            int m = 0;
            Iterator<Row> rows = sheet.rowIterator();
            a:
            while (rows.hasNext()) {
                Row row = (Row) rows.next();
                if (m == 2) {
                    MaterialInfo materialInfo = null;
                    List<String> strList = new ArrayList<String>();
                    for (int i = 0; i < 2; i++) {
                        Cell cell = (Cell) row.getCell(i);
                        if (cell != null && !"".equals(cell.toString().trim())) {
                            cell.setCellType(CellType.STRING);
                            cell.setCellType(1);
                            if (i == 0) {
                                bomInfo = bomDAO.SelectByCode(cell.getStringCellValue().trim());
                                if (bomInfo != null) {
                                    throw new SystemException("11", bomInfo.getBomName() + "BOM代码已存在,请修改BOM代码信息");
                                } else {
                                    //新增
                                    strList.add(cell.getStringCellValue().trim());
                                }
                            } else {
                                //新增
                                strList.add(cell.getStringCellValue().trim());
                            }
                        } else {
                            throw new SystemException("12", "第" + (n + 1) + "Sheet，第3行第" + (i + 1) + "列不能为空");
                        }
                    }
                    bomInfo = new BomInfo();
                    String bomVerGd = CommonUtils.getRandomNumber();
                    bomInfo.setGuid(CommonUtils.getRandomNumber());
                    bomInfo.setVerGd(bomVerGd);
                    bomInfo.setBomCode(strList.get(0));
                    bomInfo.setBomName(strList.get(1));
                    bomInfo.setCreateTime(new Date());
                    bomInfo.setCreator(CommonUtils.readUser().getRealName());
                    BomInfo bomInfo1 = bomDAO.SelectByName(bomInfo.getBomName());
                    if (bomInfo1 != null) {
                        throw new SystemException("", "此清单名称已存在,请不要重复输入!");
                    }
                    bomDAO.InsertBomInfo(bomInfo);
                    bomVerInfo = new BomVerInfo();
                    bomVerInfo.setGuid(bomVerGd);
                    bomVerInfo.setBomCode(strList.get(0));
                    bomVerInfo.setBomName(strList.get(1));
                    bomVerInfo.setBomGd(bomInfo.getGuid());
                    bomVerInfo.setVersion("1");
                    bomVerInfo.setIsDef("00");
                    bomVerInfo.setCreator(bomInfo.getCreator());
                    bomVerInfo.setCreateTime(bomInfo.getCreateTime());
                    bomVerDAO.InsertBomVerInfo(bomVerInfo);

                    UnitInfo unitInfo = null;
                    for (int k = 2; k < 6; k++) {
                        Cell cell = (Cell) row.getCell(k);

                        if (k == 4) {
                            strList.add("");
                            continue;
                        }
                        if (cell != null && !"".equals(cell.toString().trim())) {
                            cell.setCellType(CellType.STRING);
                            cell.setCellType(1);
                            if (k == 5) {
                                unitInfo = unitDAO.SelectByUnitName(cell.getStringCellValue().trim());
                                if (unitInfo == null) {
                                    throw new SystemException("13", "第" + (n + 1) + "Sheet，第3行第" + (k + 1) + "列，单位不存在");
                                }
                            }
                            strList.add(cell.getStringCellValue().trim());
                        } else {
                            throw new SystemException("14", "第" + (n + 1) + "Sheet，第3行第" + (k + 1) + "列不能为空");
                        }
                    }
                    //同时通过物料代码查询产品是或否存在，如果存在更新产品的默认版本的BOM
                    materialInfo = materialDAO.SelectByMaCode(row.getCell(2).getStringCellValue());
                    if (materialInfo != null) {
                        //更新
                        MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(materialInfo.getVerGd());
                        if (maVerInfo != null) {
                            maVerInfo.setBomGd(bomVerInfo.getBomGd());
                            maVerInfo.setRuid(maVerInfo.getRuid());
                            if (maVerDAO.UpdateMaVerInfo(maVerInfo) <= 0) {
                                throw new SystemException("x", "更新物料版本信息失败");
                            }

                        } else {
                            throw new SystemException("15", "物料代码：" + row.getCell(2).getStringCellValue() + "，对应的版本不存在");
                        }
                    }
                    //如果产品不存在，则新增产品
                    else {
                        //新增
                        String maVerGd = CommonUtils.getRandomNumber();
                        materialInfo = new MaterialInfo();
                        materialInfo.setGuid(CommonUtils.getRandomNumber());
                        materialInfo.setMaterialCode(strList.get(2));
                        materialInfo.setMaterialName(strList.get(3));
                        materialInfo.setMaterialType("00");
                        materialInfo.setVerGd(maVerGd);
                        materialInfo.setCreator(bomInfo.getCreator());
                        materialInfo.setCreateTime(bomInfo.getCreateTime());
                        materialDAO.InsertMaterialInfo(materialInfo);
                        MaVerInfo maVerInfo = new MaVerInfo();
                        maVerInfo.setGuid(maVerGd);
                        maVerInfo.setMaterialCode(strList.get(2));
                        maVerInfo.setMaterialName(strList.get(3));
                        maVerInfo.setMaterialType("00");
                        maVerInfo.setIsDef("00");
                        maVerInfo.setVersion("1");
                        maVerInfo.setBomGd(bomInfo.getGuid());
                        maVerInfo.setMaGd(materialInfo.getGuid());
                        maVerInfo.setUnitGd(unitInfo.getGuid());
                        maVerInfo.setDisRule("00");
                        maVerInfo.setShelflife("01");
                        maVerInfo.setsUnit("03");
                        maVerInfo.setInterval(0);
                        maVerInfo.setMinSNum(0);
                        maVerInfo.setMaxSNum(0);
                        maVerInfo.setReMaterial("01");
                        maVerInfo.setIsExem("00");
                        maVerInfo.setStatus("00");
                        maVerInfo.setCreator(materialInfo.getCreator());
                        maVerInfo.setCreateTime(materialInfo.getCreateTime());
                        maVerDAO.InsertMaverInfo(maVerInfo);
                    }
                }
                if (m > 4) {
                    MaterialInfo materialInfo = null;
                    List<String> strList = new ArrayList<String>();
                    for (int i = 0; i < 5; i++) {
                        Cell cell = (Cell) row.getCell(i);
                        if ((cell == null || cell.toString().equals("")) && (row.getCell(1) == null || row.getCell(1).toString().equals(""))
                                && (row.getCell(2) == null || row.getCell(2).toString().equals("")) && (row.getCell(3) == null ||
                                row.getCell(3).toString().equals("")) && (row.getCell(4) == null || row.getCell(4).toString().equals(""))) {
                            break a;

                        }
                       /* if(i == 2) {
                            strList.add("");
                            continue;
                        }*/
                        if (cell != null && !"".equals(cell.toString().trim())) {
                            cell.setCellType(1);
                            if (i == 0) {
                                materialInfo = materialDAO.SelectByMaCode(cell.getStringCellValue().trim());
                                if (materialInfo == null) {
                                    throw new SystemException("16", "第" + (n + 1) + "Sheet，第" + (m + 1) + "行，物料代码:" + cell.getStringCellValue().trim() + "不存在");
                                }
                                strList.add(cell.getStringCellValue().trim());
                            } else if (i == 3) {
                                UnitInfo unitInfo = unitDAO.SelectByUnitName(cell.getStringCellValue().trim());
                                if (unitInfo == null) {
                                    throw new SystemException("17", "第" + (n + 1) + "Sheet，第" + (m + 1) + "行，单位:" + cell.getStringCellValue().trim() + "不存在");
                                }
                                strList.add(cell.getStringCellValue().trim());
                            } else if (i == 2) {
                                SpecInfo specInfo = specDAO.SelectSpecName(cell.getStringCellValue().trim());
                                if (specInfo == null) {
                                    throw new SystemException("18", "第" + (n + 1) + "Sheet，第" + (m + 1) + "行，工序:" + cell.getStringCellValue().trim() + "不存在");
                                }
                                strList.add(specInfo.getVerGd());
                            } else {
                                strList.add(cell.getStringCellValue().trim());
                            }
                        } else {
                            throw new SystemException("19", "第" + (i + 1) + "列，有空值的");
                        }
                    }
                    BomMaterialInfo bomMaterialInfo = new BomMaterialInfo();
                    bomMaterialInfo.setGuid(CommonUtils.getRandomNumber());
                    bomMaterialInfo.setBomVerGd(bomVerInfo.getGuid());
                    bomMaterialInfo.setMaVerGd(materialInfo.getVerGd());
                    /*bomMaterialInfo.setMaterialCode(strList.get(0));
                    bomMaterialInfo.setMaterialName(strList.get(1));*/
                    bomMaterialInfo.setUnitName(strList.get(3));
                    //bomMaterialInfo.setNum(Float.parseFloat(strList.get(4)));
                    bomMaterialInfo.setNum(Float.parseFloat(strList.get(1)));
                    // bomMaterialInfo.setSpecVerGd(strList.get(5));
                    bomMaterialInfo.setSpecVerGd(strList.get(2));
                    if (strList.get(4).equals("仅显示")) {
                        bomMaterialInfo.setConSMode("00");
                    } else if (strList.get(4).equals("批次")) {
                        bomMaterialInfo.setConSMode("01");
                    } else if (strList.get(4).equals("序号")) {
                        bomMaterialInfo.setConSMode("02");
                    }

                    bomMaterialInfo.setChecked("00");
                    bomMaterialInfo.setCreator(CommonUtils.readUser().getRealName());
                    bomMaterialInfo.setCreateTime(new Date());
                    // BomMaterialInfo bomMaterialInfo1=bomMaterialDAO.SelectBomMatName(bomMaterialInfo);
                    bomMaterialDAO.InsertBomMaterialInfo(bomMaterialInfo);
                }
                m++;
            }
        }
        objSaveImportResB.setMsgCode("0x00000");
        objSaveImportResB.setMsgDes("成功");
        return objSaveImportResB;
    }

    @Override
    public ByteArrayOutputStream exportBOMExcel(int busData) {
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            //查询Bom清单版本信息
            GetBVInfoResD objEGetBVInfoResD = new GetBVInfoResD();

            //查询Bom清单版本信息
            BomVerInfo objEBomVerInfo = bomVerDAO.SelectByRuid(busData);
            if (objEBomVerInfo == null) {
                throw new SystemException("", "Bom清单版本不存在!");
            }

            //查询用料清单
            List<BomMaterialInfo> bomMaterialInfoList = new ArrayList<BomMaterialInfo>();
            bomMaterialInfoList = bomMaterialDAO.SelectByBomVerGd(objEBomVerInfo.getGuid());

            //创建table工作薄
            HSSFSheet sheet1 = wb.createSheet("table");
            sheet1.setPrintGridlines(false);
            sheet1.setGridsPrinted(false);
            /**/
            Object[][] datas1 = {{"[P]产品", "", "", "", "", ""}, {"BOM代码", "BOM名称", "物料代码", "物料名称", "规格型号", "单位"}};
            HSSFRow row1;
            HSSFCell cell1;
            //建表
            for (int i = 0; i < 2; i++) {
                row1 = sheet1.createRow(i);//创建表格行
                for (int j = 0; j < 6; j++) {
                    cell1 = row1.createCell(j);//根据表格行创建单元格
                    cell1.setCellValue(String.valueOf(datas1[i][j]));
                }
            }

            HSSFSheet sheet2 = wb.getSheet("table");
            sheet2.setPrintGridlines(false);
            sheet2.setGridsPrinted(false);
            Object[][] datas2 = {{"[D]材料", "", "", "", "", "", "", "", "", ""}, {"物料代码", "物料名称", "规格型号", "单位", "数量", "工序", "消耗方式", "物料描述", "替代料物料代码", "替代料物料名称"}};
            HSSFRow row2;
            HSSFCell cell2;
            //建表
            for (int i = 3; i < 5; i++) {
                row2 = sheet2.createRow(i);//创建表格行
                for (int j = 0; j < 10; j++) {
                    cell2 = row2.createCell(j);//根据表格行创建单元格
                    cell2.setCellValue(String.valueOf(datas2[i - 3][j]));
                }
            }

            /**********************************写入数据************************************/
            HSSFSheet sheet3 = wb.getSheet("table");
            HSSFRow row3;
            HSSFCell cell3;
            //建表
            row3 = sheet3.createRow(2);//创建表格行
            for (int j = 0; j < 6; j++) {
                cell3 = row3.createCell(j);//根据表格行创建单元格
                if (j == 0) {
                    cell3.setCellValue(objEBomVerInfo.getBomCode());
                } else if (j == 1) {
                    cell3.setCellValue(objEBomVerInfo.getBomName());
                } else if (j == 2) {
                    cell3.setCellValue("");
                } else if (j == 3) {
                    cell3.setCellValue("");
                } else if (j == 4) {
                    cell3.setCellValue("");
                } else if (j == 5) {
                    cell3.setCellValue("");
                }
            }

            HSSFSheet sheet4 = wb.getSheet("table");
            HSSFRow row4;
            HSSFCell cell4;
            //建表
            int i = 5;
            int num = 0;
            GetBVInfoResD data = getBomVer(objEBomVerInfo);
            List<GetBVInfoResDBMInfo> datas = data.getBMInfo();
            for (BomMaterialInfo bomMaterialInfo : bomMaterialInfoList) {
                //查询物料信息
                MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(bomMaterialInfo.getMaVerGd());
                if (maVerInfo == null) {
                    throw new SystemException("", "Bom中ID为" + bomMaterialInfo.getMaVerGd() + "物料已经不存在");
                }
                row4 = sheet4.createRow(i++);//创建表格行
                for (int j = 0; j < 10; j++) {
                    cell4 = row4.createCell(j);//根据表格行创建单元格
                    if (j == 0) {
                        cell4.setCellValue(maVerInfo.getMaterialCode());
                    } else if (j == 1) {
                        cell4.setCellValue(maVerInfo.getMaterialName());
                    } else if (j == 2) {
                        cell4.setCellValue("");
                    } else if (j == 3) {
                        cell4.setCellValue(bomMaterialInfo.getUnitName());
                    } else if (j == 4) {
                        cell4.setCellValue(bomMaterialInfo.getNum());
                    } else if (j == 5) {
                        SpecVerInfo specVerInfo = specVerDAO.SelectSpecVerInfoByguid(bomMaterialInfo.getSpecVerGd());
                        cell4.setCellValue(specVerInfo != null ? specVerInfo.getSpecName() : "");
                    } else if (j == 6) {
                        String str = "";
                        if (bomMaterialInfo.getConSMode().equals("00")) {
                            str = "仅显示";
                        } else if (bomMaterialInfo.getConSMode().equals("01")) {
                            str = "批次";
                        } else if (bomMaterialInfo.getConSMode().equals("02")) {
                            str = "序号";
                        }
                        cell4.setCellValue(str);
                    } else if (j == 7) {
                        if (maVerInfo.getMaterialDes() == null) {
                            cell4.setCellValue("");
                        } else {
                            cell4.setCellValue(maVerInfo.getMaterialDes());
                        }
                    } else if (j == 8) {
                        GetBVInfoResDBMInfo g = datas.get(num);
                        List<GetBVInfoResDBReMaInfo> remainfo = g.getReMaInfo();
                        if (remainfo.size() > 0) {
                            cell4.setCellValue(remainfo.get(0).getMaCode());
                        } else {
                            cell4.setCellValue("");
                        }
                    } else if (j == 9) {
                        GetBVInfoResDBMInfo g = datas.get(num);
                        List<GetBVInfoResDBReMaInfo> remainfo = g.getReMaInfo();
                        if (remainfo.size() > 0) {
                            cell4.setCellValue(remainfo.get(0).getMaName());
                        } else {
                            cell4.setCellValue("");
                        }
                    }
                }
                num++;
            }

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            wb.write(os);

            return os;

        } catch (Exception e) {
            if (e != null && e.getMessage() != null) {
                throw new SystemException("0x00000", "Export False");
            }
        } finally {
            if (wb != null) {
                try {
                    wb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        ExportSWareInfoResB exportSWareInfoResB = new ExportSWareInfoResB();
        exportSWareInfoResB.setMsgCode("");
        exportSWareInfoResB.setMsgDes("成功!");

        ExportSWareInfoRes exportSWareInfoRes = new ExportSWareInfoRes();
        exportSWareInfoRes.setBody(exportSWareInfoResB);

        return null;
    }
}
