package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.shiro.SecurityUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoRes;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoResB;
import pnc.mesadmin.dto.GetAllMaInfo.GetAllMaInfoReqBD00;
import pnc.mesadmin.dto.GetAllMaInfo.GetAllMaInfoRes;
import pnc.mesadmin.dto.GetAllMaInfo.GetAllMaInfoResB;
import pnc.mesadmin.dto.GetAllMaInfo.GetAllMaInfoResD;
import pnc.mesadmin.dto.GetBOMReMaInfo.GetBOMReMaInfoReqBD00;
import pnc.mesadmin.dto.GetBOMReMaInfo.GetBOMReMaInfoRes;
import pnc.mesadmin.dto.GetBOMReMaInfo.GetBOMReMaInfoResB;
import pnc.mesadmin.dto.GetBOMReMaInfo.GetBOMReMaInfoResD;
import pnc.mesadmin.dto.GetMVTreeInfo.GetMVTreeInfoReqBD00;
import pnc.mesadmin.dto.GetMVTreeInfo.GetMVTreeInfoRes;
import pnc.mesadmin.dto.GetMVTreeInfo.GetMVTreeInfoResB;
import pnc.mesadmin.dto.GetMVTreeInfo.GetMVTreeInfoResD;
import pnc.mesadmin.dto.GetMaInfo.*;
import pnc.mesadmin.dto.GetMaTypeInfo.GetMaTypeInfoRes;
import pnc.mesadmin.dto.GetMaTypeInfo.GetMaTypeInfoResB;
import pnc.mesadmin.dto.GetMaTypeInfo.GetMaTypeInfoResD;
import pnc.mesadmin.dto.GetPdBOMDIInfo.*;
import pnc.mesadmin.dto.GetPdBOMInfo.GetPdBOMInfoReqBD00;
import pnc.mesadmin.dto.GetPdBOMInfo.GetPdBOMInfoRes;
import pnc.mesadmin.dto.GetPdBOMInfo.GetPdBOMInfoResB;
import pnc.mesadmin.dto.GetPdBOMInfo.GetPdBOMInfoResD;
import pnc.mesadmin.dto.GetPdReGpInfo.*;
import pnc.mesadmin.dto.SaveImportRes.SaveImportResB;
import pnc.mesadmin.dto.SaveMaInfo.*;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.GConfigIService;
import pnc.mesadmin.service.MaterialIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：物料管理业务信息逻辑
 * 创建人：潘俊峰
 * 创建时间：2017-06-02
 * 修改人：
 * 修改时间：
 */
@Service
@Transactional
public class MaterialService implements MaterialIService {

    @Resource
    private MaterialDAO materialDAO;

    @Resource
    private MaVerDAO maVerDAO;

    @Resource
    private PdFamilyDAO pdFamilyDAO;

    @Resource
    private ReMaDAO reMaDAO;

    @Resource
    private WFVerDAO wfVerDAO;

    @Resource
    private BomVerDAO bomVerDAO;

    @Resource
    private UnitDAO unitDAO;

    @Resource
    private CompanyDAO companyDAO;

    @Resource
    private BomMaterialDAO bomMaterialDAO;

    @Resource
    private SpecVerDAO specVerDAO;

    @Resource
    private BomReMaterialDAO bomReMaterialDAO;

    @Resource
    private SerialRuleDAO serialRuleDAO;

    @Resource
    private MaTypeDAO maTypeDAO;

    @Resource
    private PackSpecificationDAO packSpecificationDAO;

    @Resource
    private WFDAO wfdao;

    @Resource
    private BomDAO bomDAO;

    @Resource
    private CodeRuleDAO codeRuleDAO;

    @Resource
    private MaPropertyDAO maPropertyDAO;

    @Resource
    private GConfigIService gConfigIService;

    @Resource
    private BaseIService baseIService;

    @Resource
    private PrintTDAO printTDAO;

    @Resource
    private ExpandDAO expandDAO;

    @Resource
    private ExpandDtlDAO expandDtlDAO;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private CusDataCDAO cusDataCDAO;

    @Resource
    private  MaWFDAO maWFDAO;

    @Resource
    private CusDataDtlCDAO cusDataDtlCDAO;

    @Resource
    private OnLineMaPeriodDAO onLineMaPeriodDAO;

    private static final String[] MATYPE = {"00", "01", "02", "03"};

    private static final String[] TNAME = {"产成品", "半成品", "原料", "其他"};

    //获取物料分类信息
    @Override
    public GetMaTypeInfoRes QALLMaType() throws SystemException {
        GetMaTypeInfoRes objEGetMaTypeInfoRes = new GetMaTypeInfoRes();
        GetMaTypeInfoResB objEGetMaTypeInfoResB = new GetMaTypeInfoResB();
        List<GetMaTypeInfoResD> objEGetMaTypeInfoResDs = new ArrayList<GetMaTypeInfoResD>();

        for (int i = 0; i < 4; i++) {
            GetMaTypeInfoResD objEGetMaTypeInfoResD = new GetMaTypeInfoResD();
            objEGetMaTypeInfoResD.setMaType(MATYPE[i]);
            objEGetMaTypeInfoResD.setTName(TNAME[i]);
            objEGetMaTypeInfoResDs.add(objEGetMaTypeInfoResD);
        }

        objEGetMaTypeInfoResB.setData(objEGetMaTypeInfoResDs);
        objEGetMaTypeInfoRes.setBody(objEGetMaTypeInfoResB);

        return objEGetMaTypeInfoRes;
    }

    //根据条件获取物料版本信息
    @Override
    public GetAllMaInfoRes QALLByMaType(GetAllMaInfoReqBD00 argBD00, List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException {
        GetAllMaInfoRes objEGetAllMaInfoRes = new GetAllMaInfoRes();
        GetAllMaInfoResB objEGetAllMaInfoResB = new GetAllMaInfoResB();
        List<GetAllMaInfoResD> objEGetAllMaInfoResDs = new ArrayList<GetAllMaInfoResD>();

        if (argInitDataFields != null) {
            for (int i = 0; i < argInitDataFields.size(); i++) {
                if ("MaTypeRd".equals(argInitDataFields.get(i).getFieldName())) {
                    if ("".equals(argInitDataFields.get(i).getFieldVal())) {
                        argInitDataFields.remove(i);
                        break;
                    }
                    //查询物料类别标识
                    MaTypeInfo objEMaTypeInfo = maTypeDAO.SelectByRuid(Integer.valueOf(argInitDataFields.get(i).getFieldVal()));
                    if (objEMaTypeInfo == null) {
                        throw new SystemException("", "物料类型不存在!");
                    }
                    argInitDataFields.get(i).setFieldName("MaTypeGd");
                    argInitDataFields.get(i).setFieldVal(objEMaTypeInfo.getGuid());
                    break;
                }
            }
        }

        List<MaVerInfo> objEMaVerInfos = baseIService.QALLBaseInfo(MaVerDAO.class, "SelectAllMaVerInfo",
                argInitDataFields, argPageInfo);

        GetAllMaInfoResD objEGetAllMaInfoResD = null;

        if (objEMaVerInfos != null && objEMaVerInfos.size() > 0) {

            HashOperations<String, String, Map<String, String>> ho = redisTemplate.opsForHash();

            //缓存的key，value写入
            //Map<String, Map<String, String>> mapMap = ho.entries("Material");

            //缓存value
            //Map<String, String> map = null;

            Map<String, MaterialInfo> mapMa = new HashMap<String, MaterialInfo>();
            List<MaterialInfo> objEMaterialInfos = materialDAO.SelectAllMaterialInfoguidmaterialDes();
            if (objEMaterialInfos != null) {
                for (MaterialInfo materialInfo : objEMaterialInfos) {
                    mapMa.put(materialInfo.getGuid(), materialInfo);
                }
            }

            MaterialInfo objEMaterialInfo = null;

            for (MaVerInfo maVerInfo : objEMaVerInfos) {
                /*map = mapMap.get(maVerInfo.getMaGd());

                if(map == null){
                    map = new HashMap<String, String>();
                    objEMaterialInfo = materialDAO.SelectByGuid(maVerInfo.getMaGd());*/

                objEMaterialInfo = mapMa.get(maVerInfo.getMaGd());
                if (objEMaterialInfo != null) {
                    objEGetAllMaInfoResD = new GetAllMaInfoResD();
                    objEGetAllMaInfoResD.setMaRd(objEMaterialInfo.getRuid());
                    objEGetAllMaInfoResD.setMaCode(maVerInfo.getMaterialCode());
                    objEGetAllMaInfoResD.setMaName(maVerInfo.getMaterialName());
                    if(objEMaterialInfo.getMaterialDes()==null) {
                        objEGetAllMaInfoResD.setMaDes("");
                    }else {
                        objEGetAllMaInfoResD.setMaDes(objEMaterialInfo.getMaterialDes());
                    }
                    objEGetAllMaInfoResD.setMaVerRd(maVerInfo.getRuid());
                    objEGetAllMaInfoResD.setVersion(maVerInfo.getVersion());

                        /*map.put("Ruid", String.valueOf(objEMaterialInfo.getRuid()));
                        map.put("MaterialCode", objEMaterialInfo.getMaterialCode());
                        map.put("MaterialName", objEMaterialInfo.getMaterialName());
                        map.put("MaterialDes", objEMaterialInfo.getMaterialDes());
                        ho.put("Material", maVerInfo.getMaGd(), map);*/

                    objEGetAllMaInfoResDs.add(objEGetAllMaInfoResD);
                }
                /*}else{
                    objEGetAllMaInfoResD = new GetAllMaInfoResD();
                    objEGetAllMaInfoResD.setMaRd(Integer.valueOf(map.get("Ruid")));
                    objEGetAllMaInfoResD.setMaCode(map.get("MaterialCode"));
                    objEGetAllMaInfoResD.setMaName(map.get("MaterialName"));
                    objEGetAllMaInfoResD.setMaDes(map.get("MaterialDes"));
                    objEGetAllMaInfoResD.setMaVerRd(maVerInfo.getRuid());
                    objEGetAllMaInfoResD.setVersion(maVerInfo.getVersion());

                    objEGetAllMaInfoResDs.add(objEGetAllMaInfoResD);
                }*/
            }
        }


        objEGetAllMaInfoResB.setData(objEGetAllMaInfoResDs);
        objEGetAllMaInfoRes.setBody(objEGetAllMaInfoResB);

        return objEGetAllMaInfoRes;
    }

    /**
     * 获取物料列表信息(新)
     * @param req
     * @return
     */
    @Override
    public PageResult<GetAllMaInfoResD> QALLAllMaNew(BaseRequest req) {
        List<GetAllMaInfoResD> resDList = new ArrayList<GetAllMaInfoResD>();
        GetAllMaInfoResD resD = null;

        Page<MaVerInfo> page = new Page<>(req.getCurrent(), req.getSize() <= 0 ? 1000 : req.getSize());

        IPage<MaVerInfo> iPage = maVerDAO.selectPage(page, CommonUtils.getQueryWrapper(req.getFields()));

        Map<String, MaterialInfo> mapMa = new HashMap<String, MaterialInfo>();
        List<MaterialInfo> objEMaterialInfos = materialDAO.SelectAllMaterialInfoguidmaterialDes();
        if (objEMaterialInfos != null) {
            for (MaterialInfo materialInfo : objEMaterialInfos) {
                mapMa.put(materialInfo.getGuid(), materialInfo);
            }
        }

        MaterialInfo objEMaterialInfo = null;

        for (MaVerInfo maVerInfo : iPage.getRecords()) {
            objEMaterialInfo = mapMa.get(maVerInfo.getMaGd());
            if (objEMaterialInfo != null) {
                resD = new GetAllMaInfoResD();
                resD.setMaRd(objEMaterialInfo.getRuid());
                resD.setMaCode(maVerInfo.getMaterialCode());
                resD.setMaName(maVerInfo.getMaterialName());
                if(objEMaterialInfo.getMaterialDes() == null) {
                    resD.setMaDes("");
                }else {
                    resD.setMaDes(objEMaterialInfo.getMaterialDes());
                }
                resD.setMaVerRd(maVerInfo.getRuid());
                resD.setVersion(maVerInfo.getVersion());
                resD.setMaVerGd(maVerInfo.getGuid());

                resDList.add(resD);
            }
        }

        return new PageResult<GetAllMaInfoResD>(iPage, resDList);
    }

    //获取物料列表信息  无条件获取全部
    @Override
    public GetAllMaInfoRes QALLByMaType() {
        GetAllMaInfoRes objEGetAllMaInfoRes = new GetAllMaInfoRes();
        GetAllMaInfoResB objEGetAllMaInfoResB = new GetAllMaInfoResB();
        List<GetAllMaInfoResD> objEGetAllMaInfoResDs = new ArrayList<GetAllMaInfoResD>();

        List<MaterialInfo> objEMaterialInfos = materialDAO.SelectAllMaterialInfo();
        if (objEMaterialInfos != null || objEMaterialInfos.size() > 0) {

            for (MaterialInfo materialInfo : objEMaterialInfos) {
                GetAllMaInfoResD objEGetAllMaInfoResD = new GetAllMaInfoResD();
                objEGetAllMaInfoResD.setMaRd(materialInfo.getRuid());
                objEGetAllMaInfoResD.setMaCode(materialInfo.getMaterialCode());
                objEGetAllMaInfoResD.setMaName(materialInfo.getMaterialName());

                //查找默认版本
                MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(materialInfo.getVerGd());
                if (objEMaVerInfo != null) {
                    objEGetAllMaInfoResD.setMaVerRd(objEMaVerInfo.getRuid());
                    objEGetAllMaInfoResD.setVersion(objEMaVerInfo.getVersion());
                }

                objEGetAllMaInfoResDs.add(objEGetAllMaInfoResD);
            }
        }

        objEGetAllMaInfoResB.setData(objEGetAllMaInfoResDs);
        objEGetAllMaInfoRes.setBody(objEGetAllMaInfoResB);

        return objEGetAllMaInfoRes;
    }

    //获取物料信息
    @Override
    public GetMaInfoRes GetMaInfo(int status, int ruid) throws SystemException {
        GetMaInfoRes objEGetMaInfoRes = new GetMaInfoRes();
        GetMaInfoResB objEGetMaInfoResB = new GetMaInfoResB();
        GetMaInfoResD objEGetMaInfoResD = new GetMaInfoResD();
        //MaType
        GetMaInfoResDMaType objEGetMaInfoResDMaType = new GetMaInfoResDMaType();
        //WFInfo
        List<GetMaInfoResDWFInfo> objEGetMaInfoResDWFInfo = new ArrayList<GetMaInfoResDWFInfo>();
        //BOMInfo
        GetMaInfoResDBOMInfo objEGetMaInfoResDBOMInfo = new GetMaInfoResDBOMInfo();
        //SRInfo
        GetMaInfoResDSRInfo objEGetMaInfoResDSRInfo = new GetMaInfoResDSRInfo();
        //UnitInfo
        GetMaInfoResDUnitInfo objEGetMaInfoResDUnitInfo = new GetMaInfoResDUnitInfo();
        //CPInfo
        GetMaInfoResDCPInfo objEGetMaInfoResDCPInfo = new GetMaInfoResDCPInfo();
        //SPInfo
        GetMaInfoResDSPInfo objEGetMaInfoResDSPInfo = new GetMaInfoResDSPInfo();
        //PDFInfo
        GetMaInfoResDPDFInfo objEGetMaInfoResDPDFInfo = new GetMaInfoResDPDFInfo();
        //PtInfo
        GetMaInfoResDPtInfo getMaInfoResDPtInfo=new GetMaInfoResDPtInfo();
        //ReMaInfo
        List<GetMaInfoResDReMaInfo> objEGetMaInfoResDReMaInfos = new ArrayList<GetMaInfoResDReMaInfo>();
        GetMaInfoResDTrayPackInfo objEGetMaInfoResDTrayPackInfo = new GetMaInfoResDTrayPackInfo();
        GetMaInfoResDBoxPackInfo objEGetMaInfoResDBoxPackInfo = new GetMaInfoResDBoxPackInfo();

        MaterialInfo objEMaterialInfo = null;
        MaVerInfo objEMaverInfo = null;

        //物料ruid
        if (status == 1) {
            objEMaterialInfo = materialDAO.SelectMaterialInfo(ruid);
            if (objEMaterialInfo == null) {
                throw new SystemException("", "物料信息为空");
            }
            objEMaverInfo = maVerDAO.SelectMaverInfo(objEMaterialInfo.getVerGd());
            if (objEMaverInfo == null) {
                throw new SystemException("", "物料版本信息为空");
            }
        } else if (status == 2) {
            //物料版本ruid
            objEMaverInfo = maVerDAO.SelectByRuid(ruid);
            if (objEMaverInfo == null) {
                throw new SystemException("", "物料版本信息为空");
            }
            objEMaterialInfo = materialDAO.SelectByGuid(objEMaverInfo.getMaGd());
            if (objEMaterialInfo == null) {
                throw new SystemException("", "物料信息为空");
            }
        }

        //往dto中填充数据
        objEGetMaInfoResD.setMaVerRd(objEMaverInfo.getRuid());
        objEGetMaInfoResD.setMaCode(objEMaverInfo.getMaterialCode());
        objEGetMaInfoResD.setMaName(objEMaverInfo.getMaterialName());
        objEGetMaInfoResD.setMaterialType(objEMaverInfo.getMaterialType());

        objEGetMaInfoResD.setMaDes(objEMaterialInfo.getMaterialDes());
        objEGetMaInfoResD.setVersion(objEMaverInfo.getVersion());
        objEGetMaInfoResD.setIsDef(objEMaverInfo.getIsDef());
        objEGetMaInfoResD.setSuName(objEMaverInfo.getSuName());
        objEGetMaInfoResD.setSuMaCode(objEMaverInfo.getSuMaCode());
        objEGetMaInfoResD.setCusMaCode(objEMaverInfo.getCusMaCode());
        objEGetMaInfoResD.setReMaterial(objEMaverInfo.getReMaterial());
        objEGetMaInfoResD.setShelflife(objEMaverInfo.getShelflife());
        objEGetMaInfoResD.setInterval(objEMaverInfo.getInterval());
        objEGetMaInfoResD.setSUnit(objEMaverInfo.getsUnit());
        objEGetMaInfoResD.setIsExem(objEMaverInfo.getIsExem());
        objEGetMaInfoResD.setMinPQ(objEMaverInfo.getMinPQ());
        if (objEMaverInfo.getMaBarCode() != null && !"".equals(objEMaverInfo.getMaBarCode())) {
            objEGetMaInfoResD.setMaBarCode(objEMaverInfo.getMaBarCode());
        }

        objEGetMaInfoResD.setIsBatch(objEMaverInfo.getIsBatch());
        List<GetMaInfoResDMaPropertyInfo> getMaInfoResDMaPropertyInfos = new ArrayList<GetMaInfoResDMaPropertyInfo>();

        //查询物料特性
        List <ExpandInfo> expandCInfs=expandDAO.SelectAllMaRuid(ruid);
        if(expandCInfs==null||expandCInfs.size()<=0){
            //通过物料版本标识查询物料类别信息 objEMaverInfo
            MaTypeInfo maTypeInfo=maTypeDAO.SelectGuid(objEMaverInfo.getMaTypeGd());
            if(maTypeInfo!=null){
                expandCInfs=expandDAO.SelectAllMaRuid(maTypeInfo.getRuid());
            }
            if(expandCInfs==null||expandCInfs.size()<=0){
                expandCInfs= expandDAO.SelectAllExpandCInfoByExpandType("00","00");
            }
        }

        for(ExpandInfo expandCInf0:expandCInfs) {
            List<ExpandDtlInfo> expandDtlInfos = expandDtlDAO.SelectExpandDtlCInfoByExpandGd(expandCInf0.getGuid());
            if (expandDtlInfos.size() > 0 && expandDtlInfos != null) {
                for (ExpandDtlInfo expandDtlInfo : expandDtlInfos) {
                    GetMaInfoResDMaPropertyInfo getMaInfoResDMaPropertyInfo = new GetMaInfoResDMaPropertyInfo();

                    //添加字段名称
                    getMaInfoResDMaPropertyInfo.setFieldName(expandDtlInfo.getFieldName());
                    //添加定义类型
                    getMaInfoResDMaPropertyInfo.setFiledType(expandDtlInfo.getFiledType());
                    //添加字段显示名
                    getMaInfoResDMaPropertyInfo.setDisplayName(expandDtlInfo.getDisplayName());
                    //通过MaVerGd 查询对应的值
                    MaPropertyInfo maPropertyInfos = new MaPropertyInfo();
                    maPropertyInfos = maPropertyDAO.SelectByMaVerF(objEMaverInfo.getGuid());
                    if (expandDtlInfo.getFiledType().equals("00")) {
                        if (maPropertyInfos != null) {
                            String s = "";
                            Field f = null;
                            try {
                                //通过反射传入字段名查出对应的值
                                f = maPropertyInfos.getClass().getDeclaredField(expandDtlInfo.getFieldName());
                                f.setAccessible(true);
                                s = (String) f.get(maPropertyInfos);
                                //添加字段值val
                                getMaInfoResDMaPropertyInfo.setVal(s);
                            } catch (NoSuchFieldException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        } else {
                            //添加字段值val
                            getMaInfoResDMaPropertyInfo.setVal(expandDtlInfo.getCusData());
                        }

                    }
                    if (expandDtlInfo.getFiledType().equals("01")) {
                        if (maPropertyInfos == null) {
                            maPropertyInfos = new MaPropertyInfo();
                        }
                        List<GetMaInfoResDMaPropertyInfo.Option> options = new ArrayList<GetMaInfoResDMaPropertyInfo.Option>();
                        String s = "";
                        Field f = null;
                        try {
                            //通过反射传入字段名查出对应的值
                            f = maPropertyInfos.getClass().getDeclaredField(expandDtlInfo.getFieldName());
                            f.setAccessible(true);
                            s = (String) f.get(maPropertyInfos);
                            //添加字段值val
                            getMaInfoResDMaPropertyInfo.setVal(s);
                        } catch (NoSuchFieldException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        CusDataCInfo cusDataCInfo = cusDataCDAO.SelectCusDataCInfoByGuid(expandDtlInfo.getCusData());
                        List<CusDataDtlCInfo> cusDataDtlCInfos = cusDataDtlCDAO.selectCusData(cusDataCInfo.getGuid());
                        for (CusDataDtlCInfo cusDataDtlCInfo : cusDataDtlCInfos) {
                            GetMaInfoResDMaPropertyInfo.Option option = new GetMaInfoResDMaPropertyInfo.Option();
                            option.setVal(cusDataDtlCInfo.getVal());
                            option.setDisplayName(cusDataDtlCInfo.getDisplayName());
                            options.add(option);
                        }
                        getMaInfoResDMaPropertyInfo.setOption(options);
                    }
                    getMaInfoResDMaPropertyInfos.add(getMaInfoResDMaPropertyInfo);
                }

            }
        }
        objEGetMaInfoResD.setMaPropertyInfo(getMaInfoResDMaPropertyInfos);
        objEGetMaInfoResD.setBrand(objEMaverInfo.getBrand());
        objEGetMaInfoResD.setOrderNum(objEMaverInfo.getOrderNum());

        objEGetMaInfoResD.setDisRule(objEMaverInfo.getDisRule());
        objEGetMaInfoResD.setMinSNum(objEMaverInfo.getMinSNum());
        objEGetMaInfoResD.setMaxSNum(objEMaverInfo.getMaxSNum());

        objEGetMaInfoResD.setStatus(objEMaverInfo.getStatus());
        objEGetMaInfoResD.setCreator(objEMaverInfo.getCreator());
        objEGetMaInfoResD.setCreateTime(DateUtil.format(objEMaverInfo.getCreateTime()));
        objEGetMaInfoResD.setLastModifyMan(objEMaverInfo.getLastModifyMan());
        objEGetMaInfoResD.setLastModifyTime(DateUtil.format(objEMaverInfo.getLastModifyTime()));
        objEGetMaInfoResD.setRemark(objEMaverInfo.getRemark());

        objEGetMaInfoResD.setSuMaCode(objEMaverInfo.getSuMaCode());
        objEGetMaInfoResD.setIsExem(objEMaverInfo.getIsExem());

        //设置MaType物料类型
        MaTypeInfo objEMaTypeInfo = null;
        if ("00".equals(objEMaverInfo.getMaTypeGd())) {
            objEGetMaInfoResDMaType.setMaTRd("00");
            objEGetMaInfoResDMaType.setMaTName("产成品");
        } else if ("01".equals(objEMaverInfo.getMaTypeGd())) {
            objEGetMaInfoResDMaType.setMaTRd("01");
            objEGetMaInfoResDMaType.setMaTName("半成品");
        } else if ("02".equals(objEMaverInfo.getMaTypeGd())) {
            objEGetMaInfoResDMaType.setMaTRd("02");
            objEGetMaInfoResDMaType.setMaTName("原料");
        } else if ("03".equals(objEMaverInfo.getMaTypeGd())) {
            objEGetMaInfoResDMaType.setMaTRd("03");
            objEGetMaInfoResDMaType.setMaTName("其他");
        } else {
            objEMaTypeInfo = maTypeDAO.SelectOneByGuid(objEMaverInfo.getMaTypeGd());
            if (objEMaTypeInfo != null) {
                objEGetMaInfoResDMaType.setMaTRd(String.valueOf(objEMaTypeInfo.getRuid()));
                objEGetMaInfoResDMaType.setMaTName(objEMaTypeInfo.getMaTName());
                ExpandInfo expandInfo=expandDAO.selectExpandInfoByExpandByGuid(objEMaTypeInfo.getExpandGd());
                if(expandInfo!=null){
                    objEGetMaInfoResDMaType.setExpandRd(expandInfo.getRuid());
                }
            } else {
                objEGetMaInfoResDMaType.setMaTRd("");
                objEGetMaInfoResDMaType.setMaTName("");
                objEGetMaInfoResDMaType.setExpandRd(0);
            }
        }
        objEGetMaInfoResD.setMaType(objEGetMaInfoResDMaType);

        //设置WFInfo工艺流程
        List<MaWFInfo> maWFInfos=maWFDAO.SelectByMaVerGd(String.valueOf(objEMaverInfo.getGuid()));
       // WFInfo objEWFInfo = wfdao.SelectByGuid(objEMaverInfo.getwFGd());
        for(MaWFInfo maWFInfo:maWFInfos){
            GetMaInfoResDWFInfo  wFInfo=new GetMaInfoResDWFInfo();
            WFVerInfo wfVerInfo=wfVerDAO.SelectByGuid(maWFInfo.getwFVerGd());
            if(wfVerInfo!=null){
                wFInfo.setWfVerRd(wfVerInfo.getRuid());
                wFInfo.setWFName(wfVerInfo.getwFName());
            }
            objEGetMaInfoResDWFInfo.add(wFInfo);
        }
        objEGetMaInfoResD.setWFInfo(objEGetMaInfoResDWFInfo);

        //设置BomInfoBOM清单
        BomInfo objEBomInfo = bomDAO.SelectByGuid(objEMaverInfo.getBomGd());
        if (objEBomInfo != null) {
            objEGetMaInfoResDBOMInfo.setBOMRd(objEBomInfo.getRuid());
            objEGetMaInfoResDBOMInfo.setBOMName(objEBomInfo.getBomName());
        }
        objEGetMaInfoResD.setBOMInfo(objEGetMaInfoResDBOMInfo);

        //物料管控
        OnLineMaPeriodInfo onLineMaPeriodInfo=onLineMaPeriodDAO.selectMaPeriodByGuid(objEMaverInfo.getOnLineMaPeriodGd());
        MaOnLineMaPeriodInfo maPeriodInfo=new MaOnLineMaPeriodInfo();
        if(onLineMaPeriodInfo!=null){
            maPeriodInfo.setMaPeriodName(onLineMaPeriodInfo.getMaPeriodName());
            maPeriodInfo.setMaPerionRd(onLineMaPeriodInfo.getRuid());
        }
        objEGetMaInfoResD.setOnLineMaPeriodInfo(maPeriodInfo);

        //SRInfo  SRRd 序号
        SerialRuleInfo objESerialRuleInfo = serialRuleDAO.SelectSerialRuleInfoByguid(objEMaverInfo.getSerialRuleGd());
        if (objESerialRuleInfo != null) {
            objEGetMaInfoResDSRInfo.setSRRd(objESerialRuleInfo.getRuid());
            objEGetMaInfoResDSRInfo.setSRName(objESerialRuleInfo.getRuleName());
        }
        objEGetMaInfoResD.setSRInfo(objEGetMaInfoResDSRInfo);

        PrintTInfo printTInfo=printTDAO.SelectByGuid(objEMaverInfo.getPrintTGd());
        if(printTInfo!=null){
            getMaInfoResDPtInfo.setPtRd(printTInfo.getRuid());
            getMaInfoResDPtInfo.setPtName(printTInfo.getTempName());
        }
        objEGetMaInfoResD.setPtInfo(getMaInfoResDPtInfo);

        //UnitInfo unitRd 单位
        UnitInfo objEUnitInfo = unitDAO.SelectByGuid(objEMaverInfo.getUnitGd());
        if (objEUnitInfo != null) {
            objEGetMaInfoResDUnitInfo.setUnitRd(objEUnitInfo.getRuid());
            objEGetMaInfoResDUnitInfo.setUnitName(objEUnitInfo.getUnitName());
        }
        objEGetMaInfoResD.setUnitInfo(objEGetMaInfoResDUnitInfo);

        //CPInfo 企业
        if(!"".equals(objEMaverInfo.getCompanyCode())) {
            CompanyInfo objECompanyInfo = companyDAO.SelectCompanyInfoBycompanyCode(objEMaverInfo.getCompanyCode());
            if (objECompanyInfo != null) {
                objEGetMaInfoResDCPInfo.setCpRd(objECompanyInfo.getRuid());
                objEGetMaInfoResDCPInfo.setCpName(objECompanyInfo.getCompanyName());
            }
        }
        objEGetMaInfoResD.setCPInfo(objEGetMaInfoResDCPInfo);

        //设置PDFInfo产品家族
        PdFamilyInfo objEPdFamilyInfo = pdFamilyDAO.SelectByGuid(objEMaverInfo.getPdFamilyGd());
        if (objEPdFamilyInfo != null) {
            objEGetMaInfoResDPDFInfo.setPDFRd(objEPdFamilyInfo.getRuid());
            objEGetMaInfoResDPDFInfo.setPDFName(objEPdFamilyInfo.getFamilyName());
        }
        objEGetMaInfoResD.setPDFInfo(objEGetMaInfoResDPDFInfo);

        //数据来源
        objEGetMaInfoResD.setDSource(objEMaverInfo.getdSource());

        //栈板包装规格
        PackSpecificationInfo objEPackSpecificationInfo = packSpecificationDAO.SelectPackSpecificationByGuid(objEMaverInfo.getTrayPackGd());
        if (objEPackSpecificationInfo == null) {
            objEGetMaInfoResDTrayPackInfo.setPackSpRd(0);
            objEGetMaInfoResDTrayPackInfo.setPackName("");
        } else {
            objEGetMaInfoResDTrayPackInfo.setPackSpRd(objEPackSpecificationInfo.getRuid());
            objEGetMaInfoResDTrayPackInfo.setPackName(objEPackSpecificationInfo.getPackName());
        }
        objEGetMaInfoResD.setTrayPackInfo(objEGetMaInfoResDTrayPackInfo);

        //箱包包装规格
        objEPackSpecificationInfo = packSpecificationDAO.SelectPackSpecificationByGuid(objEMaverInfo.getBoxPackGd());
        if (objEPackSpecificationInfo == null) {
            objEGetMaInfoResDBoxPackInfo.setPackSpRd(0);
            objEGetMaInfoResDBoxPackInfo.setPackName("");
        } else {
            objEGetMaInfoResDBoxPackInfo.setPackSpRd(objEPackSpecificationInfo.getRuid());
            objEGetMaInfoResDBoxPackInfo.setPackName(objEPackSpecificationInfo.getPackName());
        }
        objEGetMaInfoResD.setBoxPackInfo(objEGetMaInfoResDBoxPackInfo);

        //ReMaInfo
        List<ReMaInfo> objEReMaInfos = reMaDAO.SelectByMaVerGd(objEMaverInfo.getGuid());
        for (ReMaInfo reMaInfo : objEReMaInfos) {
            GetMaInfoResDReMaInfo objEGetMaInfoResDReMaInfo = new GetMaInfoResDReMaInfo();
            objEGetMaInfoResDReMaInfo.setReMaRd(reMaInfo.getRuid());
            //查询物料
            MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(reMaInfo.getReMaVerGd());
            if (maVerInfo != null) {
                objEGetMaInfoResDReMaInfo.setMaVerRd(maVerInfo.getRuid());
            }
            objEGetMaInfoResDReMaInfo.setMaCode(reMaInfo.getMaterialCode());
            objEGetMaInfoResDReMaInfo.setMaName(reMaInfo.getMaterialName());
            MaterialInfo objeMaterialInfo = materialDAO.SelectByMaCode(reMaInfo.getMaterialCode());
            if (objeMaterialInfo.getMaterialDes() != null && !objeMaterialInfo.getMaterialDes().equals("")) {
                objEGetMaInfoResDReMaInfo.setMaDes(objeMaterialInfo.getMaterialDes());
            } else {
                objEGetMaInfoResDReMaInfo.setMaDes("");
            }
            objEGetMaInfoResDReMaInfo.setVersion(reMaInfo.getVersion());
            objEGetMaInfoResDReMaInfos.add(objEGetMaInfoResDReMaInfo);
        }
        objEGetMaInfoResD.setReMaInfo(objEGetMaInfoResDReMaInfos);

        objEGetMaInfoResB.setData(objEGetMaInfoResD);
        objEGetMaInfoRes.setBody(objEGetMaInfoResB);

        return objEGetMaInfoRes;
    }

    //获取物料版本列表信息
    @Override
    public GetMVTreeInfoRes QALLMVTreeInfo(GetMVTreeInfoReqBD00 argBD00) throws SystemException {
        GetMVTreeInfoRes objEGetMVTreeInfoRes = new GetMVTreeInfoRes();
        GetMVTreeInfoResB objEGetMVTreeInfoResB = new GetMVTreeInfoResB();
        List<GetMVTreeInfoResD> objEGetMVTreeInfoResDs = new ArrayList<GetMVTreeInfoResD>();

        //通过ruid获取物料
        MaterialInfo objEMaterialInfo = materialDAO.SelectMaterialInfo(argBD00.getMaRd());
        if (objEMaterialInfo == null) {
            throw new SystemException("", "物料信息为空");
        }

        List<MaVerInfo> objEMaVerInfos = maVerDAO.SelectByMaGd(objEMaterialInfo.getGuid());
        if (objEMaVerInfos == null || objEMaVerInfos.size() <= 0) {
            throw new SystemException("", "物料版本信息为空");
        }

        for (MaVerInfo maVerInfo : objEMaVerInfos) {
            GetMVTreeInfoResD objEGetMVTreeInfoResD = new GetMVTreeInfoResD();
            objEGetMVTreeInfoResD.setMaVerRd(maVerInfo.getRuid());
            objEGetMVTreeInfoResD.setVersion(maVerInfo.getVersion());
            objEGetMVTreeInfoResDs.add(objEGetMVTreeInfoResD);
        }

        objEGetMVTreeInfoResB.setData(objEGetMVTreeInfoResDs);
        objEGetMVTreeInfoRes.setBody(objEGetMVTreeInfoResB);

        return objEGetMVTreeInfoRes;
    }

    //新增物料信息
    @Override
    public SaveMaInfoRes AddMaInfo(SaveMaInfoReqBD00 argBD00) throws SystemException {
        SaveMaInfoRes objESaveMaInfoRes = new SaveMaInfoRes();

        SaveMaInfoResB objESaveMaInfoResB = new SaveMaInfoResB();

        SaveMaInfoResD objESaveMaInfoResD = new SaveMaInfoResD();
        //当前用户
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();
        //物料Guid
        String MaGuid = CommonUtils.getRandomNumber();
        //物料版本Guid
        String MaVerGuid = CommonUtils.getRandomNumber();

        //判断物料代码是否已经存在
        if (!"".equals(argBD00.getMaCode())) {
            MaterialInfo materialInfoCode = materialDAO.SelectByMaCode(argBD00.getMaCode());
            if (materialInfoCode != null) {
                throw new SystemException("", "物料代码已存在!");
            }
        }
        if ("".equals(argBD00.getVersion())) {
            throw new SystemException("", "物料版本不能为空!");
        }

        //查询代码生成
        CodeRuleInfo codeRuleInfo = codeRuleDAO.SelectCodeRuleInfocodeType("00");

        if(argBD00.getMaCode() == null || "".equals(argBD00.getMaCode().trim())){
            throw new SystemException("", "物料代码不能为空");
        }

        String SCode = "";

        SCode = argBD00.getMaCode();

        /*if (!"".equals(argBD00.getMaCode())) {
            SCode = argBD00.getMaCode();
        } else if (codeRuleInfo != null && "00".equals(codeRuleInfo.getStatus())) {
            SCode = gConfigIService.GetCreateSC(codeRuleInfo);
        } else {
            throw new SystemException("", "请输入{物料代码}，或请到全局配置进行代码生成配置");
        }*/

        //查询物料类型
        MaTypeInfo objEMaTypeInfo = null;
        switch (argBD00.getMaTRd()) {
            case "00":
                objEMaTypeInfo = new MaTypeInfo();
                objEMaTypeInfo.setMaterialType("00");
                objEMaTypeInfo.setGuid("00");
                break;
            case "01":
                objEMaTypeInfo = new MaTypeInfo();
                objEMaTypeInfo.setGuid("01");
                objEMaTypeInfo.setMaterialType("01");
                break;
            case "02":
                objEMaTypeInfo = new MaTypeInfo();
                objEMaTypeInfo.setGuid("02");
                objEMaTypeInfo.setMaterialType("02");
                break;
            case "03":
                objEMaTypeInfo = new MaTypeInfo();
                objEMaTypeInfo.setGuid("03");
                objEMaTypeInfo.setMaterialType("03");
                break;
            default:
                if ("".equals(argBD00.getMaTRd())) {
                    throw new SystemException("", "物料类型不能为空!");
                }
                objEMaTypeInfo = maTypeDAO.SelectByRuid(Integer.valueOf(argBD00.getMaTRd()));
                break;
        }
        //物料类型
        if (objEMaTypeInfo == null) {
            throw new SystemException("", "物料类型不能为空!");
        }

        MaterialInfo objEMaterialInfo = new MaterialInfo();
        MaVerInfo objEMaVerInfo = new MaVerInfo();

        //objEMaVerInfo.setIsBatch("00");
        objEMaVerInfo.setMaBarCode(argBD00.getMaBarCode());

        /*if ("02".equals(objEMaTypeInfo.getMaterialType()) || "03".equals(objEMaTypeInfo.getMaterialType())) {
            if (!"00".equals(argBD00.getIsBatch())) {
                if (argBD00.getMaBarCode() == null || "".equals(argBD00.getMaBarCode())) {
                    argBD00.setMaBarCode(SCode);
                }
                // 在物料类型=原材料 || 物料类型=其他时,物料条码必须保证唯一性
                MaVerInfo a = maVerDAO.SelectMaterialInfoBymaBarCode(argBD00.getMaBarCode());
                if (a != null) {
                    throw new SystemException("", "保存失败，物料条码已存在");
                }
                objEMaVerInfo.setIsBatch("01");
                objEMaVerInfo.setMaBarCode(argBD00.getMaBarCode());
            }
        }*/

        //企业
        CompanyInfo companyInfo = companyDAO.SelectCompanyInfoByRuid(argBD00.getCpRd());
        if (companyInfo != null) {
            objEMaterialInfo.setCompanyCode(companyInfo.getCompanyCode());
            objEMaVerInfo.setCompanyCode(companyInfo.getCompanyCode());
        }

        objEMaterialInfo.setGuid(MaGuid);

        objEMaterialInfo.setMaterialCode(SCode);
        objEMaterialInfo.setMaterialName(argBD00.getMaName());

        objEMaterialInfo.setMaterialType(objEMaTypeInfo.getMaterialType());
        objEMaterialInfo.setVerGd(MaVerGuid);
        objEMaterialInfo.setCreator(userName);
        objEMaterialInfo.setCreateTime(date);
        objEMaterialInfo.setRemark(argBD00.getRemark());

        //查询产品族
        PdFamilyInfo pdFamilyInfo = pdFamilyDAO.SelectPdFamilyInfo(argBD00.getPDFRd());
        if (pdFamilyInfo != null) {
            objEMaterialInfo.setPdFamilyGd(pdFamilyInfo.getGuid());
            objEMaVerInfo.setPdFamilyGd(pdFamilyInfo.getGuid());
        }
        //物料描述
        if(argBD00.getMaDes()!=null&&!argBD00.getMaDes().equals("")) {
            objEMaterialInfo.setMaterialDes(argBD00.getMaDes());
        }
        materialDAO.InsertMaterialInfo(objEMaterialInfo);

        objEMaVerInfo.setGuid(MaVerGuid);
        //新增物料特性
        List<SaveMaInfoReqBD00MaPropertyInfo> saveMaInfoReqBD00MaPropertyInfos = argBD00.getMaPropertyInfo();
        MaPropertyInfo maPropertyInfo = new MaPropertyInfo();
        try {
            for(SaveMaInfoReqBD00MaPropertyInfo saveMaInfoReqBD00MaPropertyInfo:saveMaInfoReqBD00MaPropertyInfos){
                    Field f=MaPropertyInfo.class.getDeclaredField(saveMaInfoReqBD00MaPropertyInfo.getFieldName());
                    f.setAccessible(true);
                    f.set(maPropertyInfo,saveMaInfoReqBD00MaPropertyInfo.getVal());
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        maPropertyInfo.setGuid(CommonUtils.getRandomNumber());
        maPropertyInfo.setMaVerGd(objEMaVerInfo.getGuid());
        maPropertyInfo.setCreateTime(new Date());
        maPropertyInfo.setCreator(userName);
        maPropertyDAO.InsertMaPropertyInfo(maPropertyInfo);

        objEMaVerInfo.setMaGd(MaGuid);

        if (!"".equals(argBD00.getMaCode())) {
            objEMaVerInfo.setMaterialCode(argBD00.getMaCode());
        } else {
            objEMaVerInfo.setMaterialCode(SCode);
        }

        objEMaVerInfo.setMaterialName(argBD00.getMaName());
        objEMaVerInfo.setVersion(argBD00.getVersion());
        objEMaVerInfo.setIsDef("00");
        objEMaVerInfo.setMaTypeGd(objEMaTypeInfo.getGuid());
        objEMaVerInfo.setMaterialType(objEMaTypeInfo.getMaterialType());

        objEMaVerInfo.setSuName(argBD00.getSuName());
        objEMaVerInfo.setSuMaCode(argBD00.getSuMaCode());
        objEMaVerInfo.setCusMaCode(argBD00.getCusMaCode());
        objEMaVerInfo.setIsExem(argBD00.getIsExem());

        objEMaVerInfo.setStatus(argBD00.getStatus());

        //工艺流程版本标识 WFRd
        List<SaveMaInfoReqBD00.WFInfo> wfInfos=argBD00.getWFInfo();
        WFVerInfo wfVerInfo = null;
        for(SaveMaInfoReqBD00.WFInfo wfInfo : wfInfos){
            wfVerInfo = wfVerDAO.SelectByRuid(wfInfo.getWfVerRd());
            if(wfVerInfo != null) {
                MaWFInfo maWFInfo = new MaWFInfo();
                maWFInfo.setGuid(CommonUtils.getRandomNumber());
                maWFInfo.setMaVerGd(MaVerGuid);
                maWFInfo.setwFVerGd(wfVerInfo.getGuid());
                maWFInfo.setCreator(userName);
                maWFInfo.setCreateTime(new Date());
                maWFDAO.inseetAll(maWFInfo);
            }
        }

        //BOM清单版本标识 BOMRd
        BomInfo objEBomInfo = bomDAO.SelectBomInfo(argBD00.getBOMRd());
        if (objEBomInfo != null) {
            objEMaVerInfo.setBomGd(objEBomInfo.getGuid());
        }

        // 在线物料有效期管控
        if(argBD00.getMaPerionRd()!=null){
            OnLineMaPeriodInfo onLineMaPeriodInfo=onLineMaPeriodDAO.selectMaPeriodByRuid(argBD00.getMaPerionRd());
            if(onLineMaPeriodInfo != null){
                objEMaVerInfo.setOnLineMaPeriodGd(onLineMaPeriodInfo.getGuid());
            }
        }

        //序号规则标识 SRRd
        SerialRuleInfo objESerialRuleInfo = serialRuleDAO.SelectSerialRuleInfoByruid(argBD00.getSRRd());
        if (objESerialRuleInfo != null) {
            objEMaVerInfo.setSerialRuleGd(objESerialRuleInfo.getGuid());
        }

        //打印模板
        PrintTInfo printTInfo=printTDAO.SelectPrintTInfo(argBD00.getPtRd());
        if(printTInfo!=null){
            objEMaVerInfo.setPrintTGd(printTInfo.getGuid());
        }
        //单位
        UnitInfo objEUnitInfo = unitDAO.SelectUnitInfoByruid(argBD00.getUnitRd());
        if (objEUnitInfo == null) {
            throw new SystemException("", "单位不能为空");
        }
        objEMaVerInfo.setUnitGd(objEUnitInfo.getGuid());

        //栈板包装规格
        PackSpecificationInfo objEPackSpecificationInfo = packSpecificationDAO.SelectPackSpecificationByRuid(argBD00.getTrayPSpRd());
        if (objEPackSpecificationInfo != null) {
            objEMaVerInfo.setTrayPackGd(objEPackSpecificationInfo.getGuid());
        }
        //箱包包装规格
        objEPackSpecificationInfo = packSpecificationDAO.SelectPackSpecificationByRuid(argBD00.getBoxPSpRd());
        if (objEPackSpecificationInfo != null) {
            objEMaVerInfo.setBoxPackGd(objEPackSpecificationInfo.getGuid());
        }

        objEMaVerInfo.setShelflife(argBD00.getShelflife());

        if ("00".equals(argBD00.getShelflife())) {
            objEMaVerInfo.setInterval(argBD00.getInterval());
        } else {
            objEMaVerInfo.setInterval(0);
        }
        objEMaVerInfo.setsUnit(argBD00.getSUnit());

        objEMaVerInfo.setdSource("01");

        //物料描述
        if(argBD00.getMaDes()!=null&&!argBD00.getMaDes().equals("")){
            objEMaVerInfo.setMaterialDes(argBD00.getMaDes());
        }
        objEMaVerInfo.setDisRule(argBD00.getDisRule());
        objEMaVerInfo.setMinSNum(argBD00.getMinSNum());
        objEMaVerInfo.setMaxSNum(argBD00.getMaxSNum());
        objEMaVerInfo.setBrand(argBD00.getBrand());
        objEMaVerInfo.setOrderNum(argBD00.getOrderNum());

        objEMaVerInfo.setReMaterial(argBD00.getReMaterial());
        objEMaVerInfo.setCreator(userName);
        objEMaVerInfo.setCreateTime(date);
        objEMaVerInfo.setRemark(argBD00.getRemark());
        objEMaVerInfo.setMinPQ(argBD00.getMinPQ());
        maVerDAO.InsertMaverInfo(objEMaVerInfo);

        //往ReMaInfo表中插入数据
        for (SaveMaInfoReqBD00ReMaInfo reMaInfo : argBD00.getReMaInfo()) {
            //根据MaVerRd查询MaVerGd
            MaVerInfo maVerInfo = maVerDAO.SelectByRuid(reMaInfo.getMaVerRd());
            ReMaInfo objEReMaInfo = new ReMaInfo();
            objEReMaInfo.setGuid(CommonUtils.getRandomNumber());
            objEReMaInfo.setMaVerGd(MaVerGuid);
            objEReMaInfo.setReMaVerGd(maVerInfo.getGuid());
            objEReMaInfo.setMaterialCode(reMaInfo.getMaCode());
            objEReMaInfo.setMaterialName(reMaInfo.getMaName());
            objEReMaInfo.setVersion(reMaInfo.getVersion());
            objEReMaInfo.setCreator(userName);
            objEReMaInfo.setCreateTime(date);
            reMaDAO.InsertReMaInfo(objEReMaInfo);
        }

        MaterialInfo objEMaterialInfos = materialDAO.SelectByGuid(objEMaterialInfo.getGuid());
        MaVerInfo objEMaVerInfoS = maVerDAO.SelectMaverInfo(objEMaVerInfo.getGuid());
        objESaveMaInfoResD.setMaRd(objEMaterialInfos.getRuid());
        objESaveMaInfoResD.setMaVerRd(objEMaVerInfoS.getRuid());
        objESaveMaInfoResD.setMaCode(objEMaVerInfoS.getMaterialCode());
        objESaveMaInfoResB.setData(objESaveMaInfoResD);
        objESaveMaInfoRes.setBody(objESaveMaInfoResB);
        return objESaveMaInfoRes;
    }

    //删除物料信息
    @Override
    public SaveMaInfoRes RmMaInfo(SaveMaInfoReqBD01 argBD01) throws SystemException {
        SaveMaInfoRes objESaveMaInfoRes = new SaveMaInfoRes();

        //查询物料版本信息
        MaVerInfo objEMaVerInfo = maVerDAO.SelectByRuid(argBD01.getMaVerRd());
        if (objEMaVerInfo == null) {
            throw new SystemException("", "物料版本为空");
        }
        if ("00".equals(objEMaVerInfo.getdSource())) {
            throw new SystemException("", "外部数据无法删除");
        }

        MaPropertyInfo maPropertyInfo = maPropertyDAO.SelectByMaVerGd(objEMaVerInfo.getGuid());
        //查询物料信息
        MaterialInfo objEMaterialInfo = materialDAO.SelectByGuid(objEMaVerInfo.getMaGd());
        if (objEMaterialInfo == null) {
            throw new SystemException("", "物料为空");
        }
        //判断当前版本是不是默认版本
        if (objEMaterialInfo.getVerGd().equals(objEMaVerInfo.getGuid())) {
            //查询该物料信息下面的所有版本信息
            List<MaVerInfo> objEMaVerInfos = maVerDAO.SelectByMaGd(objEMaterialInfo.getGuid());
            if (objEMaVerInfos == null && objEMaVerInfos.size() <= 0) {
                throw new SystemException("", "物料版本信息为空");
            }

            for (MaVerInfo maVerInfo : objEMaVerInfos) {
                //删除替代料信息
                reMaDAO.DeleteByMaVerGd(maVerInfo.getGuid());
            }

            //删除物料版本
            if (maVerDAO.DeleteByMaGd(objEMaterialInfo.getGuid()) <= 0) {
                throw new SystemException("", "物料版本删除失败");
            }
            //删除工艺流程
            maWFDAO.deleteAll(String.valueOf(argBD01.getMaVerRd()));

            //删除物料
            if (materialDAO.DeleteMaterialInfo(objEMaterialInfo.getRuid()) <= 0) {
                throw new SystemException("", "物料删除失败");
            }
            if (maPropertyDAO.DeleteMaPropertyInfo(maPropertyInfo.getRuid()) <= 0) {
                throw new SystemException("", "物料特性删除失败");
            }
        } else {
            //删除替代料信息
            reMaDAO.DeleteByMaVerGd(objEMaVerInfo.getGuid());

            //删除物料版本
            if (maVerDAO.DeleteByGuid(objEMaVerInfo.getGuid()) <= 0) {
                throw new SystemException("", "物料版本删除失败");
            }
            //删除工艺流程
            maWFDAO.deleteAll(String.valueOf(argBD01.getMaVerRd()));

            if (maPropertyDAO.DeleteMaPropertyInfo(maPropertyInfo.getRuid()) <= 0) {
                throw new SystemException("", "物料特性删除失败");
            }
        }

      List<BomMaterialInfo> bomMaterialInfos=bomMaterialDAO.selectBomMaterialInfoBymaVerGd(objEMaVerInfo.getGuid());

      if(bomMaterialInfos!=null&&bomMaterialInfos.size()>0){
            throw new SystemException("", "删除失败该料已被用料");
      }

        List<BomReMaterialInfo> bomReMaterialInfos=bomReMaterialDAO.SelectByMaVerGd(objEMaVerInfo.getGuid());

        if(bomReMaterialInfos!=null&&bomReMaterialInfos.size()>0){
            throw new SystemException("", "删除失败该料已被替代");
        }



        return objESaveMaInfoRes;
    }

    //修改物料信息
    @Override
    public SaveMaInfoRes ModMaInfo(SaveMaInfoReqBD02 argBD02) throws SystemException {
        SaveMaInfoRes objESaveMaInfoRes = new SaveMaInfoRes();

        //当前用户
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();

        //查询物料版本信息
        MaVerInfo objEMaVerInfo = maVerDAO.SelectByRuid(argBD02.getMaVerRd());
        if (objEMaVerInfo == null) {
            throw new SystemException("", "物料版本不存在");
        }
        //企业
        CompanyInfo companyInfo = companyDAO.SelectCompanyInfoByRuid(argBD02.getCpRd());
        if (companyInfo != null) {
            objEMaVerInfo.setCompanyCode(companyInfo.getCompanyCode());
        }
        if ("00".equals(objEMaVerInfo.getdSource())) {
            throw new SystemException("", "外部数据无法修改");
        }

        //查询物料信息
        MaterialInfo objEMaterialInfo = materialDAO.SelectByGuid(objEMaVerInfo.getMaGd());
        if (objEMaterialInfo == null) {
            throw new SystemException("", "物料不存在");
        }

        //查询物料类型
        MaTypeInfo objEMaTypeInfo = null;
        switch (argBD02.getMaTRd()) {
            case "00":
                objEMaTypeInfo = new MaTypeInfo();
                objEMaTypeInfo.setGuid("00");
                objEMaTypeInfo.setMaterialType("00");
                break;
            case "01":
                objEMaTypeInfo = new MaTypeInfo();
                objEMaTypeInfo.setGuid("01");
                objEMaTypeInfo.setMaterialType("01");
                break;
            case "02":
                objEMaTypeInfo = new MaTypeInfo();
                objEMaTypeInfo.setGuid("02");
                objEMaTypeInfo.setMaterialType("02");
                break;
            case "03":
                objEMaTypeInfo = new MaTypeInfo();
                objEMaTypeInfo.setGuid("03");
                objEMaTypeInfo.setMaterialType("03");
                break;
            default:
                if ("".equals(argBD02.getMaTRd())) {
                    throw new SystemException("", "物料类型不能为空!");
                }
                objEMaTypeInfo = maTypeDAO.SelectByRuid(Integer.valueOf(argBD02.getMaTRd()));
                break;
        }
        if (objEMaTypeInfo == null) {
            throw new SystemException("", "物料类型不能为空!");
        }

        //当前版本标识-（默认true、非默认false）
        boolean v = false;

        if ("00".equals(objEMaVerInfo.getIsDef())) {
            v = true;
        }

        //查询产品族Guid
        PdFamilyInfo pdFamilyInfo = null;

        //修改物料版本信息
        MaVerInfo maVerInfo = new MaVerInfo();
        maVerInfo.setRuid(objEMaVerInfo.getRuid());
        maVerInfo.setIsBatch(argBD02.getIsBatch());
        maVerInfo.setMinPQ(argBD02.getMinPQ());

        //maVerInfo.setIsBatch("00");
        maVerInfo.setMaBarCode(argBD02.getMaBarCode());

        /*if ("02".equals(objEMaTypeInfo.getMaterialType()) || "03".equals(objEMaTypeInfo.getMaterialType())) {
            if (!"00".equals(argBD02.getIsBatch())) {
                if (argBD02.getMaBarCode() == null || "".equals(argBD02.getMaBarCode())) {
                    argBD02.setMaBarCode(argBD02.getMaCode());
                }
                // 在物料类型=原材料 || 物料类型=其他时,物料条码必须保证唯一性
                MaVerInfo a = maVerDAO.SelectMaterialInfoBymaBarCode(argBD02.getMaBarCode());
                if (a != null && !objEMaVerInfo.getMaBarCode().equals(a.getMaBarCode())) {
                    throw new SystemException("", "保存失败，物料条码已存在");
                }
                maVerInfo.setIsBatch("01");
                maVerInfo.setMaBarCode(argBD02.getMaBarCode());
            }
        }*/

        String SCode = "";
        if (v) {
            /*//判断物料代码是否已经存在
            if (!"".equals(argBD02.getMaCode())) {
                MaterialInfo materialInfoCode = materialDAO.SelectByMaCode(argBD02.getMaCode());
                if (materialInfoCode != null && !objEMaterialInfo.getMaterialCode().equals(materialInfoCode.getMaterialCode())) {
                    throw new SystemException("", "物料代码已存在");
                }
            }

            //查询代码生成
            CodeRuleInfo codeRuleInfo = codeRuleDAO.SelectCodeRuleInfocodeType("00");


            if (!"".equals(argBD02.getMaCode())) {
                maVerInfo.setMaterialCode(argBD02.getMaCode());
            } else if (codeRuleInfo != null && "00".equals(codeRuleInfo.getStatus())) {
                SCode = gConfigIService.GetCreateSC(codeRuleInfo);
                maVerInfo.setMaterialCode(SCode);
            } else {
                throw new SystemException("", "请输入{物料代码}，或请到全局配置进行代码生成配置");
            }*/

            maVerInfo.setMaterialName(argBD02.getMaName());

            //修改所有非默认版本信息
            List<MaVerInfo> maVerInfos = maVerDAO.SelectByMaGd(objEMaVerInfo.getMaGd());
            for (MaVerInfo m : maVerInfos) {
                m.setMaterialName(argBD02.getMaName());
                m.setMaterialType(objEMaTypeInfo.getMaterialType());
                m.setMaTypeGd(objEMaTypeInfo.getGuid());
                m.setLastModifyMan(userName);
                m.setLastModifyTime(date);
                if (maVerDAO.UpdateMaVerInfo(m) <= 0) {
                    throw new SystemException("", "修改版本失败");
                }
            }

            pdFamilyInfo = pdFamilyDAO.SelectPdFamilyInfo(argBD02.getPDFRd());
            if (pdFamilyInfo != null) {
                maVerInfo.setPdFamilyGd(pdFamilyInfo.getGuid());
            } else {
                maVerInfo.setPdFamilyGd("");
            }
            //打印模板
            PrintTInfo  printTInfo=printTDAO.SelectPrintTInfo(argBD02.getPtRd());
            if(printTInfo!=null){
                maVerInfo.setPrintTGd(printTInfo.getGuid());
            }else {
                maVerInfo.setPrintTGd("");
            }

            //单位
            UnitInfo objEUnitInfo = unitDAO.SelectUnitInfoByruid(argBD02.getUnitRd());
            if (objEUnitInfo == null) {
                throw new SystemException("", "单位不能为空");
            }
            maVerInfo.setUnitGd(objEUnitInfo.getGuid());
        }
        //跟新物料描述
        if(argBD02.getMaDes()!=null&&!argBD02.getMaDes().equals("")){
            maVerInfo.setMaterialDes(argBD02.getMaDes());
        }

        //更新物料特性
        List<SaveMaInfoReqBD02MaPropertyInfo> saveMaInfoReqBD02MaPropertyInfos = argBD02.getMaPropertyInfo();
        MaPropertyInfo maPropertyInfo = maPropertyDAO.SelectByMaVerGd(objEMaVerInfo.getGuid());
        if (maPropertyInfo != null) {
            try {
                for(SaveMaInfoReqBD02MaPropertyInfo saveMaInfoReqBD02MaPropertyInfo:saveMaInfoReqBD02MaPropertyInfos){

                          Field f= MaPropertyInfo.class.getDeclaredField(saveMaInfoReqBD02MaPropertyInfo.getFieldName());
                          f.setAccessible(true);
                          //通过反射对备用字段名赋值
                    if(!saveMaInfoReqBD02MaPropertyInfo.getVal().equals("")){
                          f.set(maPropertyInfo,saveMaInfoReqBD02MaPropertyInfo.getVal());
                      }else {
                        f.set(maPropertyInfo,null);
                      }
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            maPropertyInfo.setLastModifyMan(userName);
            maPropertyInfo.setLastModifyTime(date);
            if (maPropertyDAO.UpdateMaPropertyInfo(maPropertyInfo) <= 0) {
                throw new SystemException("", "更新物料特性失败");
            }
        }else {
            maPropertyInfo=new MaPropertyInfo();
            maPropertyInfo.setGuid(CommonUtils.getRandomNumber());
            maPropertyInfo.setMaVerGd(objEMaVerInfo.getGuid());
            maPropertyInfo.setCreator(userName);
            maPropertyInfo.setCreateTime(date);
            try {
                for(SaveMaInfoReqBD02MaPropertyInfo saveMaInfoReqBD02MaPropertyInfo:saveMaInfoReqBD02MaPropertyInfos){
                    Field f= MaPropertyInfo.class.getDeclaredField(saveMaInfoReqBD02MaPropertyInfo.getFieldName());
                    f.setAccessible(true);
                    //通过反射对备用字段名赋值
                    f.set(maPropertyInfo,saveMaInfoReqBD02MaPropertyInfo.getVal());

                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            maPropertyDAO.InsertMaPropertyInfo(maPropertyInfo);

        }

        //工艺流程版本标识 WFRd
        List<SaveMaInfoReqBD02.WFInfo> wfInfos=argBD02.getWFInfo();
        //删除物料版本ID对应的工艺流程
        maWFDAO.deleteAll(String.valueOf(objEMaVerInfo.getGuid()));
        MaWFInfo maWFInfo=new MaWFInfo();
        maWFInfo.setGuid(CommonUtils.getRandomNumber());
        maWFInfo.setCreator(userName);
        maWFInfo.setCreateTime(date);
        maWFInfo.setMaVerGd(String.valueOf(objEMaVerInfo.getGuid()));
        for(SaveMaInfoReqBD02.WFInfo wfInfo:wfInfos){
            WFVerInfo wfVerInfo=wfVerDAO.SelectByRuid(Integer.parseInt(wfInfo.getWfVerRd()));
            maWFInfo.setwFVerGd(wfVerInfo.getGuid());
            maWFDAO.inseetAll(maWFInfo);
        }

        //BOM清单版本标识 BOMRd
        BomInfo objEBomInfo = bomDAO.SelectBomInfo(argBD02.getBOMRd());
        //BomVerInfo objEBomVerInfo = bomVerDAO.SelectByRuid(argBD02.getBOMVerRd());
        if (objEBomInfo != null) {
            maVerInfo.setBomGd(objEBomInfo.getGuid());
        } else {
            maVerInfo.setBomGd("");
        }

        //序号规则标识 SRRd
        SerialRuleInfo objESerialRuleInfo = serialRuleDAO.SelectSerialRuleInfoByruid(argBD02.getSRRd());
        if (objESerialRuleInfo != null) {
            maVerInfo.setSerialRuleGd(objESerialRuleInfo.getGuid());
        } else {
            maVerInfo.setSerialRuleGd("");
        }

        //在线物料有效期管控
        if(argBD02.getMaPerionRd()!=null){
            OnLineMaPeriodInfo onLineMaPeriodInfo= onLineMaPeriodDAO.selectMaPeriodByRuid(argBD02.getMaPerionRd());
            maVerInfo.setOnLineMaPeriodGd(onLineMaPeriodInfo.getGuid());
        }else {
            maVerInfo.setOnLineMaPeriodGd("");
        }


        maVerInfo.setSuName(argBD02.getSuName());
        maVerInfo.setCusMaCode(argBD02.getCusMaCode());

        //栈板包装规格
        PackSpecificationInfo objEPackSpecificationInfo = packSpecificationDAO.SelectPackSpecificationByRuid(argBD02.getTrayPSpRd());
        if (objEPackSpecificationInfo != null) {
            maVerInfo.setTrayPackGd(objEPackSpecificationInfo.getGuid());
        } else {
            maVerInfo.setTrayPackGd("");
        }
        //箱包包装规格
        objEPackSpecificationInfo = packSpecificationDAO.SelectPackSpecificationByRuid(argBD02.getBoxPSpRd());
        if (objEPackSpecificationInfo != null) {
            maVerInfo.setBoxPackGd(objEPackSpecificationInfo.getGuid());
        } else {
            maVerInfo.setBoxPackGd("");
        }

        maVerInfo.setShelflife(argBD02.getShelflife());

        if ("00".equals(argBD02.getShelflife())) {
            maVerInfo.setInterval(argBD02.getInterval());
            maVerInfo.setsUnit(argBD02.getSUnit());
        } else {
            maVerInfo.setInterval(0);
        }

        maVerInfo.setDisRule(argBD02.getDisRule());
        maVerInfo.setMinSNum(argBD02.getMinSNum());
        maVerInfo.setMaxSNum(argBD02.getMaxSNum());
        maVerInfo.setReMaterial(argBD02.getReMaterial());
        maVerInfo.setLastModifyMan(userName);
        maVerInfo.setLastModifyTime(date);
        maVerInfo.setRemark(argBD02.getRemark());

        maVerInfo.setSuMaCode(argBD02.getSuMaCode());
        maVerInfo.setIsExem(argBD02.getIsExem());
        maVerInfo.setStatus(argBD02.getStatus());

        maVerInfo.setOrderNum(argBD02.getOrderNum());
        maVerInfo.setBrand(argBD02.getBrand());

        if (maVerDAO.UpdateMaVerInfo(maVerInfo) <= 0) {
            throw new SystemException("", "修改物料版本信息失败");
        }

        //往ReMaInfo表中插入数据
        List<ReMaInfo> objEReMaInfos = reMaDAO.SelectByMaVerGd(objEMaVerInfo.getGuid());
        for (SaveMaInfoReqBD02ReMaInfo reMaInfo : argBD02.getReMaInfo()) {
            boolean flag = true;
            for (int i = 0; i < objEReMaInfos.size(); i++) {
                if (reMaInfo.getReMaRd() == objEReMaInfos.get(i).getRuid()) {
                    //修改替代料信息
                    ReMaInfo reMaInfo1 = objEReMaInfos.get(i);
                    //查询物料
                    MaVerInfo maVerInfo1 = maVerDAO.SelectByRuid(reMaInfo.getMaVerRd());
                    reMaInfo1.setReMaVerGd(maVerInfo1.getGuid());
                    reMaInfo1.setMaterialCode(maVerInfo1.getMaterialCode());
                    reMaInfo1.setMaterialName(maVerInfo1.getMaterialName());
                    reMaInfo1.setVersion(maVerInfo1.getVersion());
                    reMaInfo1.setLastModifyMan(userName);
                    reMaInfo1.setLastModifyTime(date);
                    if (reMaDAO.UpdateByRuid(reMaInfo1) <= 0) {
                        throw new SystemException("", "物料替代料修改失败");
                    }

                    objEReMaInfos.remove(i);
                    flag = false;
                    break;
                }
            }
            if (flag) {
                //根据MaVerRd查询MaVerGd
                MaVerInfo maVerInfo1 = maVerDAO.SelectByRuid(reMaInfo.getMaVerRd());
                ReMaInfo objEReMaInfo = new ReMaInfo();
                objEReMaInfo.setGuid(CommonUtils.getRandomNumber());
                objEReMaInfo.setMaVerGd(objEMaVerInfo.getGuid());
                objEReMaInfo.setReMaVerGd(maVerInfo1.getGuid());
                objEReMaInfo.setMaterialCode(reMaInfo.getMaCode());
                objEReMaInfo.setMaterialName(reMaInfo.getMaName());
                objEReMaInfo.setVersion(reMaInfo.getVersion());
                objEReMaInfo.setCreator(userName);
                objEReMaInfo.setCreateTime(date);
                reMaDAO.InsertReMaInfo(objEReMaInfo);
            }
        }
        for (ReMaInfo r : objEReMaInfos) {
            if (reMaDAO.DeleteReMaInfo(r.getGuid()) <= 0) {
                throw new SystemException("", "物料替代料修改失败");
            }
        }

        //修改物料信息
        //判断当前物料版本是不是默认版本
        if (v) {
            MaterialInfo materialInfo = new MaterialInfo();
            materialInfo.setRuid(objEMaterialInfo.getRuid());
            //跟新物料描述
            if(argBD02.getMaDes()!=null&&!argBD02.getMaDes().equals("")){
                materialInfo.setMaterialDes(argBD02.getMaDes());
            }
            materialInfo.setMaterialName(objEMaterialInfo.getMaterialName());

            //设置默认版本
            materialInfo.setVerGd(objEMaVerInfo.getGuid());

            materialInfo.setMaterialName(argBD02.getMaName());

            materialInfo.setMaterialType(objEMaTypeInfo.getMaterialType());
            if (pdFamilyInfo != null) {
                materialInfo.setPdFamilyGd(pdFamilyInfo.getGuid());
            } else {
                materialInfo.setPdFamilyGd("");
            }
            materialInfo.setLastModifyMan(userName);
            materialInfo.setLastModifyTime(date);
            materialDAO.UpdateMaterialInfo(materialInfo);
        }

        return objESaveMaInfoRes;
    }

    //新增物料版本信息
    @Override
    public SaveMaInfoRes AddMaVerInfo(SaveMaInfoReqBD03 argBD03) throws SystemException {
        SaveMaInfoRes objESaveMaInfoRes = new SaveMaInfoRes();

        //当前用户
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        //当前时间
        Date date = new Date();
        //查询物料信息
        MaterialInfo objEMaterialInfo = materialDAO.SelectMaterialInfo(argBD03.getMaRd());
        if (objEMaterialInfo == null) {
            throw new SystemException("", "默认物料版本信息不存在!");
        }
        MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(objEMaterialInfo.getVerGd());
        if (objEMaVerInfo == null) {
            throw new SystemException("", "默认物料版本信息不存在!");
        }

        //物料Guid
        String MaGuid = objEMaterialInfo.getGuid();
        //物料版本Guid
        String MaVerGuid = CommonUtils.getRandomNumber();

        if ("".equals(argBD03.getVersion())) {
            throw new SystemException("", "物料版本不能为空!");
        }

        //判断当前版本有没有存在重复
        MaVerInfo maVerInfoByVersion = maVerDAO.SelectByMaGdVersion(objEMaterialInfo.getGuid(), argBD03.getVersion());
        if (maVerInfoByVersion != null) {
            throw new SystemException("", "该版本已存在");
        }

        //新增物料版本信息
        MaVerInfo objEMaVerInfoNew = new MaVerInfo();

        if (!"00".equals(argBD03.getIsBatch())) {
            argBD03.setIsExem("00");
        }

        objEMaVerInfoNew.setIsBatch("00");
        objEMaVerInfoNew.setMaBarCode("");

        if ("02".equals(objEMaVerInfo.getMaterialType()) || "03".equals(objEMaVerInfo.getMaterialType())) {
            if (!"00".equals(argBD03.getIsBatch())) {
                if (argBD03.getMaBarCode() == null || "".equals(argBD03.getMaBarCode())) {
                    argBD03.setMaBarCode(objEMaVerInfo.getMaterialCode());
                }
                // 在物料类型=原材料 || 物料类型=其他时,物料条码必须保证唯一性
                MaVerInfo a = maVerDAO.SelectMaterialInfoBymaBarCode(argBD03.getMaBarCode());
                if (a != null) {
                    throw new SystemException("", "保存失败，物料条码已存在");
                }
                objEMaVerInfoNew.setIsBatch("01");
                objEMaVerInfoNew.setMaBarCode(argBD03.getMaBarCode());
            }
        }

        //查询产品族Guid
        objEMaVerInfoNew.setPdFamilyGd(objEMaVerInfo.getPdFamilyGd());

        objEMaVerInfoNew.setGuid(MaVerGuid);
        objEMaVerInfoNew.setMaGd(MaGuid);
        objEMaVerInfoNew.setMaterialCode(objEMaterialInfo.getMaterialCode());
        objEMaVerInfoNew.setMaterialName(objEMaterialInfo.getMaterialName());
        objEMaVerInfoNew.setVersion(argBD03.getVersion());
        objEMaVerInfoNew.setIsDef("01");
        objEMaVerInfoNew.setMaterialType(objEMaVerInfo.getMaterialType());
        objEMaVerInfoNew.setMaTypeGd(objEMaVerInfo.getMaTypeGd());
        objEMaVerInfoNew.setMaBarCode(argBD03.getMaBarCode());
        objEMaVerInfoNew.setIsBatch(argBD03.getIsBatch());

        List<SaveMaInfoReqBD03MaPropertyInfo> saveMaInfoReqBD03MaPropertyInfos = argBD03.getMaPropertyInfo();
        MaPropertyInfo maPropertyInfo = new MaPropertyInfo();
        maPropertyInfo.setGuid(CommonUtils.getRandomNumber());
        maPropertyInfo.setMaVerGd(objEMaVerInfoNew.getGuid());

        try {
            for(SaveMaInfoReqBD03MaPropertyInfo saveMaInfoReqBD03MaPropertyInfo:saveMaInfoReqBD03MaPropertyInfos){
                Field f = MaPropertyInfo.class.getDeclaredField(saveMaInfoReqBD03MaPropertyInfo.getFieldName());
                f.setAccessible(true);
                //通过反射对备用字段名赋值
                f.set(maPropertyInfo, saveMaInfoReqBD03MaPropertyInfo.getVal());

            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        maPropertyInfo.setCreateTime(new Date());
        maPropertyInfo.setCreator(userName);
        maPropertyDAO.InsertMaPropertyInfo(maPropertyInfo);

        //工艺流程版本标识 WFRd
       // WFInfo objEWFINfo = wfdao.SelectWFInfo(argBD03.getWfRd());
        //WFVerInfo objEWFVerInfo = wfVerDAO.SelectByRuid(argBD03.getWfVerRd());
        /*if (objEWFINfo != null) {
            objEMaVerInfoNew.setwFGd(objEWFINfo.getGuid());
        }*/

        //模板
        PrintTInfo printTInfo=printTDAO.SelectPrintTInfo(argBD03.getPtRd());
        if(printTInfo!=null){
            objEMaVerInfoNew.setPrintTGd(printTInfo.getGuid());
        }

        //BOM清单版本标识 BOMRd
        BomInfo objEBomInfo = bomDAO.SelectBomInfo(argBD03.getBOMRd());
        //BomVerInfo objEBomVerInfo = bomVerDAO.SelectByRuid(argBD03.getBOMVerRd());
        if (objEBomInfo != null) {
            objEMaVerInfoNew.setBomGd(objEBomInfo.getGuid());
        }

        //序号规则标识 SRRd
        SerialRuleInfo objESerialRuleInfo = serialRuleDAO.SelectSerialRuleInfoByruid(argBD03.getSRRd());
        if (objESerialRuleInfo != null) {
            objEMaVerInfoNew.setSerialRuleGd(objESerialRuleInfo.getGuid());
        }

        objEMaVerInfoNew.setUnitGd(objEMaVerInfo.getUnitGd());

        objEMaVerInfoNew.setSuName(argBD03.getSuName());
        objEMaVerInfoNew.setSuMaCode(argBD03.getSuMaCode());
        objEMaVerInfoNew.setCusMaCode(argBD03.getCusMaCode());
        objEMaVerInfoNew.setBrand(argBD03.getBrand());
        objEMaVerInfoNew.setOrderNum(argBD03.getOrderNum());
        objEMaVerInfoNew.setMinPQ(argBD03.getMinPQ());
        objEMaVerInfoNew.setShelflife(argBD03.getShelflife());
        //物料描述

        if ("00".equals(argBD03.getShelflife())) {
            objEMaVerInfoNew.setInterval(argBD03.getInterval());
        } else {
            objEMaVerInfoNew.setInterval(0);
        }
        objEMaVerInfoNew.setsUnit(argBD03.getSUnit());
        objEMaVerInfoNew.setIsExem(argBD03.getIsExem());

        //栈板包装规格
        PackSpecificationInfo objEPackSpecificationInfo = packSpecificationDAO.SelectPackSpecificationByRuid(argBD03.getTrayPSpRd());
        if (objEPackSpecificationInfo != null) {
            objEMaVerInfoNew.setTrayPackGd(objEPackSpecificationInfo.getGuid());
        }
        //箱包包装规格
        objEPackSpecificationInfo = packSpecificationDAO.SelectPackSpecificationByRuid(argBD03.getBoxPSpRd());
        if (objEPackSpecificationInfo != null) {
            objEMaVerInfoNew.setBoxPackGd(objEPackSpecificationInfo.getGuid());
        }

        objEMaVerInfoNew.setDisRule(argBD03.getDisRule());
        objEMaVerInfoNew.setMinSNum(argBD03.getMinSNum());
        objEMaVerInfoNew.setMaxSNum(argBD03.getMaxSNum());

        objEMaVerInfoNew.setdSource("01");
        /*objEMaVerInfoNew.setMaterialDes(saveMaInfoReqBD03MaPropertyInfo.getSize()+"  "+
                saveMaInfoReqBD03MaPropertyInfo.getMaterial()+"  "+
                saveMaInfoReqBD03MaPropertyInfo.getNorm()+"  "+
                saveMaInfoReqBD03MaPropertyInfo.getModel());*/
        objEMaVerInfoNew.setReMaterial(argBD03.getReMaterial());
        objEMaVerInfoNew.setStatus(argBD03.getStatus());
        objEMaVerInfoNew.setRemark(argBD03.getRemark());
        objEMaVerInfoNew.setCreator(userName);
        objEMaVerInfoNew.setCreateTime(date);
        maVerDAO.InsertMaverInfo(objEMaVerInfoNew);

        //往ReMaInfo表中插入数据
        for (SaveMaInfoReqBD03ReMaInfo reMaInfo : argBD03.getReMaInfo()) {
            //根据MaVerRd查询MaVerGd
            MaVerInfo maVerInfo = maVerDAO.SelectByRuid(reMaInfo.getMaVerRd());
            ReMaInfo objEReMaInfo = new ReMaInfo();
            objEReMaInfo.setGuid(CommonUtils.getRandomNumber());
            objEReMaInfo.setMaVerGd(MaVerGuid);
            objEReMaInfo.setReMaVerGd(maVerInfo.getGuid());
            objEReMaInfo.setMaterialCode(reMaInfo.getMaCode());
            objEReMaInfo.setMaterialName(reMaInfo.getMaName());
            objEReMaInfo.setVersion(reMaInfo.getVersion());
            objEReMaInfo.setCreator(userName);
            objEReMaInfo.setCreateTime(date);
            reMaDAO.InsertReMaInfo(objEReMaInfo);
        }

        return objESaveMaInfoRes;
    }

    //获取产品关联产品族信息
    @Override
    public GetPdReGpInfoRes GetPdReGpInfo(GetPdReGpInfoReqBD00 argBD00) throws SystemException {
        GetPdReGpInfoRes objEGetPdReGpInfoRes = new GetPdReGpInfoRes();
        GetPdReGpInfoResB objEGetPdReGpInfoResB = new GetPdReGpInfoResB();
        GetPdReGpInfoResD objEGetPdReGpInfoResD = new GetPdReGpInfoResD();
        List<GetPdReGpInfoResDPDInfo> objEGetPdReGpInfoResDPDInfos = new ArrayList<GetPdReGpInfoResDPDInfo>();

        //查询物料信息
        MaterialInfo objEMaterialInfo = materialDAO.SelectMaterialInfo(argBD00.getMaRd());
        if (objEMaterialInfo == null) {
            throw new SystemException("", "物料信息为空");
        }

        //根据PdGd查询产品家族
        PdFamilyInfo objEPdFamilyInfo = pdFamilyDAO.SelectByGuid(objEMaterialInfo.getPdFamilyGd());
        if (objEPdFamilyInfo == null) {
            throw new SystemException("", "产品家族为空");
        }

        //根据产品家族Gd查询所有物料信息
        List<MaterialInfo> objEMaterialInfos = materialDAO.SelectByPdFamilyGd(objEPdFamilyInfo.getGuid());

        for (MaterialInfo materialInfo : objEMaterialInfos) {
            GetPdReGpInfoResDPDInfo objEGetPdReGpInfoResDPDInfo = new GetPdReGpInfoResDPDInfo();

            //通过产品家族Gd查询物料版本
            List<MaVerInfo> objEMaVerInfos = maVerDAO.SelectByMaGd(materialInfo.getGuid());
            if (objEMaVerInfos != null && objEMaVerInfos.size() > 0) {
                //循环物料版本并设置
                List<GetPdReGpInfoResDPVer> objEGetPdReGpInfoResDPVers = new ArrayList<GetPdReGpInfoResDPVer>();
                for (MaVerInfo maVerInfo : objEMaVerInfos) {
                    GetPdReGpInfoResDPVer objEGetPdReGpInfoResDPVer = new GetPdReGpInfoResDPVer();
                    objEGetPdReGpInfoResDPVer.setMaVerRd(maVerInfo.getRuid());
                    objEGetPdReGpInfoResDPVer.setVersion(maVerInfo.getVersion());
                    objEGetPdReGpInfoResDPVers.add(objEGetPdReGpInfoResDPVer);
                }

                objEGetPdReGpInfoResDPDInfo.setMaRd(materialInfo.getRuid());
                objEGetPdReGpInfoResDPDInfo.setMaName(materialInfo.getMaterialName());
                objEGetPdReGpInfoResDPDInfo.setVer(objEGetPdReGpInfoResDPVers);
            }

            objEGetPdReGpInfoResDPDInfos.add(objEGetPdReGpInfoResDPDInfo);
        }

        objEGetPdReGpInfoResD.setPDFRd(objEPdFamilyInfo.getRuid());
        objEGetPdReGpInfoResD.setPDFName(objEPdFamilyInfo.getFamilyName());
        objEGetPdReGpInfoResD.setPDInfo(objEGetPdReGpInfoResDPDInfos);

        objEGetPdReGpInfoResB.setData(objEGetPdReGpInfoResD);
        objEGetPdReGpInfoRes.setBody(objEGetPdReGpInfoResB);

        return objEGetPdReGpInfoRes;
    }

    //获取产品物料清单信息
    @Override
    public GetPdBOMInfoRes GetPdBOMInfo(GetPdBOMInfoReqBD00 argBD00) throws SystemException {
        GetPdBOMInfoRes objEGetPdBOMInfoRes = new GetPdBOMInfoRes();
        GetPdBOMInfoResB objEGetPdBOMInfoResB = new GetPdBOMInfoResB();
        List<GetPdBOMInfoResD> objEGetPdBOMInfoResDs = new ArrayList<GetPdBOMInfoResD>();

        //根据物料版本Rd查物料版本信息
        MaVerInfo objEMaVerInfo = maVerDAO.SelectByRuid(argBD00.getMaVerRd());
        if (objEMaVerInfo == null) {
            throw new SystemException("", "物料版本为空");
        }

        //根据BomVerGd查Bom清单版本
        BomInfo objEBomInfo = bomDAO.SelectByGuid(objEMaVerInfo.getBomGd());
        if (objEBomInfo == null) {
            throw new SystemException("", "Bom清单版本为空");
        }
        BomVerInfo objEBomVerInfo = bomVerDAO.SelectBomVerInfo(objEBomInfo.getVerGd());
        if (objEBomVerInfo == null) {
            throw new SystemException("", "Bom清单版本为空");
        }

        //查询Bom用料表
        List<BomMaterialInfo> objEBomMaterialInfos = bomMaterialDAO.SelectByBomVerGd(objEBomVerInfo.getGuid());
        if (objEBomMaterialInfos == null || objEBomMaterialInfos.size() <= 0) {
            throw new SystemException("0x000001", "无");
        }
        for (BomMaterialInfo bomMaterialInfo : objEBomMaterialInfos) {
            GetPdBOMInfoResD objEGetPdBOMInfoResD = new GetPdBOMInfoResD();
            //根据MaVerGd查询Rd
            MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(bomMaterialInfo.getMaVerGd());
            if (maVerInfo != null) {
                objEGetPdBOMInfoResD.setMaVerRd(maVerInfo.getRuid());
                objEGetPdBOMInfoResD.setMaName(maVerInfo.getMaterialName());
                objEGetPdBOMInfoResDs.add(objEGetPdBOMInfoResD);
            }
        }

        objEGetPdBOMInfoResB.setData(objEGetPdBOMInfoResDs);
        objEGetPdBOMInfoRes.setBody(objEGetPdBOMInfoResB);

        return objEGetPdBOMInfoRes;
    }

    //获取产品物料清单明细信息
    @Override
    public GetPdBOMDIInfoRes GetPdBOMDIInfo(GetPdBOMDIInfoReqBD00 argBD00) throws SystemException {
        GetPdBOMDIInfoRes objEGetPdBOMDIInfoRes = new GetPdBOMDIInfoRes();
        GetPdBOMDIInfoResB objEGetPdBOMDIInfoResB = new GetPdBOMDIInfoResB();
        GetPdBOMDIInfoResD objEGetPdBOMDIInfoResD = new GetPdBOMDIInfoResD();
        List<GetPdBOMDIInfoResDList> objEGetPdBOMDIInfoResDLists = new ArrayList<GetPdBOMDIInfoResDList>();
        List<GetPdBOMDIInfoResDDetails> objEGetPdBOMDIInfoResDDetailss = new ArrayList<GetPdBOMDIInfoResDDetails>();

        //根据物料版本Rd查物料版本信息
        MaVerInfo objEMaVerInfo = maVerDAO.SelectByRuid(argBD00.getMaVerRd());
        if (objEMaVerInfo == null) {
            throw new SystemException("", "物料版本为空");
        }

        //根据BomVerGd查Bom清单版本
        BomInfo objEBomInfo = bomDAO.SelectByGuid(objEMaVerInfo.getBomGd());
        if (objEBomInfo == null) {
            throw new SystemException("", "Bom清单版本为空");
        }
        BomVerInfo objEBomVerInfo = bomVerDAO.SelectBomVerInfo(objEBomInfo.getVerGd());
        if (objEBomVerInfo == null) {
            throw new SystemException("", "Bom清单版本为空");
        }

        //查询Bom用料表
        List<BomMaterialInfo> objEBomMaterialInfos = bomMaterialDAO.SelectByBomVerGd(objEBomVerInfo.getGuid());
        if (objEBomMaterialInfos == null || objEBomMaterialInfos.size() <= 0) {
            throw new SystemException("0x000001", "无");
        }
        for (BomMaterialInfo bomMaterialInfo : objEBomMaterialInfos) {
            GetPdBOMDIInfoResDList objEGetPdBOMDIInfoResDList = new GetPdBOMDIInfoResDList();
            //根据MaVerGd查询Rd
            MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(bomMaterialInfo.getMaVerGd());
            objEGetPdBOMDIInfoResDList.setMaVerRd(maVerInfo.getRuid());
            objEGetPdBOMDIInfoResDList.setMaName(maVerInfo.getMaterialName());
            objEGetPdBOMDIInfoResDLists.add(objEGetPdBOMDIInfoResDList);

            GetPdBOMDIInfoResDDetails objEGetPdBOMDIInfoResDDetails = new GetPdBOMDIInfoResDDetails();
            objEGetPdBOMDIInfoResDDetails.setMaVerRd(maVerInfo.getRuid());
            objEGetPdBOMDIInfoResDDetails.setMaCode(maVerInfo.getMaterialCode());
            objEGetPdBOMDIInfoResDDetails.setMaName(maVerInfo.getMaterialName());
            //查询工序
            SpecVerInfo objESpecVerInfo = specVerDAO.SelectSpecVerInfoByguid(bomMaterialInfo.getSpecVerGd());
            objEGetPdBOMDIInfoResDDetails.setSpecName(objESpecVerInfo.getSpecName());
            objEGetPdBOMDIInfoResDDetails.setNum(bomMaterialInfo.getNum());
            objEGetPdBOMDIInfoResDDetails.setUnitName(bomMaterialInfo.getUnitName());
            objEGetPdBOMDIInfoResDDetailss.add(objEGetPdBOMDIInfoResDDetails);
        }

        objEGetPdBOMDIInfoResD.setMaVerRd(objEBomVerInfo.getRuid());
        objEGetPdBOMDIInfoResD.setBomMaRd(objEBomVerInfo.getRuid());
        objEGetPdBOMDIInfoResD.setList(objEGetPdBOMDIInfoResDLists);
        objEGetPdBOMDIInfoResD.setDetails(objEGetPdBOMDIInfoResDDetailss);
        objEGetPdBOMDIInfoResB.setData(objEGetPdBOMDIInfoResD);
        objEGetPdBOMDIInfoRes.setBody(objEGetPdBOMDIInfoResB);

        return objEGetPdBOMDIInfoRes;
    }

    //获取物料清单替代料信息
    @Override
    public GetBOMReMaInfoRes GetBOMReMaInfo(GetBOMReMaInfoReqBD00 argBD00) throws SystemException {
        GetBOMReMaInfoRes objEGetBOMReMaInfoRes = new GetBOMReMaInfoRes();
        GetBOMReMaInfoResB objEGetBOMReMaInfoResB = new GetBOMReMaInfoResB();
        List<GetBOMReMaInfoResD> objEGetBOMReMaInfoResDs = new ArrayList<GetBOMReMaInfoResD>();

        BomMaterialInfo objEBomMaterialInfo = bomMaterialDAO.SelectBomMaterialInfo(argBD00.getBomMaRd());
        if (objEBomMaterialInfo != null) {
            //查询替代料
            List<BomReMaterialInfo> objEBomReMaterialInfos = bomReMaterialDAO.SelectByBomMaGd(objEBomMaterialInfo.getGuid());
            for (BomReMaterialInfo bomReMaterialInfo : objEBomReMaterialInfos) {
                GetBOMReMaInfoResD objEGetBOMReMaInfoResD = new GetBOMReMaInfoResD();
                //查询物料版本信息
                MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(bomReMaterialInfo.getMaVerGd());
                objEGetBOMReMaInfoResD.setMaVerRd(objEMaVerInfo.getRuid());
                objEGetBOMReMaInfoResD.setMaName(objEMaVerInfo.getMaterialName());
                objEGetBOMReMaInfoResDs.add(objEGetBOMReMaInfoResD);
            }
        }

        objEGetBOMReMaInfoResB.setData(objEGetBOMReMaInfoResDs);
        objEGetBOMReMaInfoRes.setBody(objEGetBOMReMaInfoResB);

        return objEGetBOMReMaInfoRes;
    }

    //导入
    @Override
    public SaveImportResB AddImportMater(CommonsMultipartFile file, String isRadio) throws IOException {
        SaveImportResB saveImportResB = new SaveImportResB();

        HSSFWorkbook wb = new HSSFWorkbook(file.getInputStream());

        Sheet sheet = wb.getSheetAt(0);
        int m = 0;
        Iterator<Row> rows = sheet.rowIterator();
        int count = 2;
        a:
        while (rows.hasNext()) {

            Row row = rows.next();
            if (sheet.getRow(1) == null) {
                throw new SystemException("xxx", "导入失败，导入的数据为空!");
            }
            if (m == 0) {
                m++;
                continue;
            }
            Boolean flag = true;

            Boolean aa = true;
            MaterialInfo materialInfo = new MaterialInfo();
            MaVerInfo maVerInfo = new MaVerInfo();
            MaPropertyInfo maPropertyInfo = new MaPropertyInfo();
            //定义变量 来存储物料条码 目的是在物料类型的时候 保证唯一性
            String materialmaPropertyInfo = "";
            String wltm = "";
            MaterialInfo materialInfo1=null;
            for (int i = 0; i <= 18; i++) {
                Cell cell = row.getCell(i);
                if (cell != null && !"".equals(cell.toString().trim())) {
                  cell.setCellType(CellType.STRING);
                    if (i == 0) {
                        //物料代码当Excel不填时，需要检测物料代码是否启用了自动生成功能，如果启用则按照物料代码生成规则处理;如果没有启用则提示用户填写物料代码
                        if (cell.toString().trim() == null || "".equals(cell.toString().trim())) {
                            CodeRuleInfo codeRuleInfo = codeRuleDAO.SelectCodeRuleInfocodeType("00");
                            String SCode = "";
                            if (codeRuleInfo != null && "00".equals(codeRuleInfo.getStatus())) {
                                SCode = gConfigIService.GetCreateSC(codeRuleInfo);
                                materialInfo.setMaterialCode(SCode);
                                //导入的时候，如果excel中的物料代码为空的时候，者物料代码和物料条码一样
                                maVerInfo.setMaBarCode(SCode);
                            } else {
                                throw new SystemException("", "请输入{物料代码}，或请到全局配置进行代码生成配置");
                            }
                        }
                        String s=cell.getStringCellValue().trim();
                       materialInfo1 = materialDAO.SelectByMaCode(cell.getStringCellValue().trim());
                        if (materialInfo1 != null) {
                            if ("true".equals(isRadio)) {
                                MaVerInfo maVerInfo1=maVerDAO.SelectMaverInfo(materialInfo1.getVerGd());
                                materialInfo1.setRuid(materialInfo1.getRuid());
                                materialInfo1.setMaterialCode(materialInfo1.getMaterialCode());
                                maVerInfo1.setMaBarCode(materialInfo1.getMaterialCode());
                                materialInfo1.setLastModifyMan((String) SecurityUtils.getSubject().getPrincipal());
                                materialInfo1.setLastModifyTime(new Date());
                                Cell cell3 = row.getCell(3);
                                Cell cell4 = row.getCell(4);
                                Cell cell5 = row.getCell(5);
                                Cell cell6 = row.getCell(6);
                                //materialmaPropertyInfo+=cell5.getStringCellValue();
                                if(cell3!=null){
                                    materialmaPropertyInfo+=cell3.getStringCellValue();
                                }
                                if(cell4!=null){
                                    materialmaPropertyInfo+=cell4.getStringCellValue();
                                }
                                if(cell5!=null){
                                    materialmaPropertyInfo+=cell5.getStringCellValue();
                                }
                                if(cell6!=null){
                                    materialmaPropertyInfo+=cell6.getStringCellValue();
                                }
                                //materialInfo1.setMaterialDes(materialmaPropertyInfo);
                               // maVerInfo1.setMaterialDes(materialmaPropertyInfo);
                                maVerInfo1.setLastModifyTime(new Date());
                                maVerInfo1.setLastModifyMan((String) SecurityUtils.getSubject().getPrincipal());
                                aa = false;
                                if (materialDAO.UpdateMaterialInfo(materialInfo1) <= 0) {
                                    throw new SystemException("02", "第" + count + "行，物料代码更新失败");
                                }
                                if (maVerDAO.UpdateMaVerInfo(maVerInfo1) <= 0) {
                                    throw new SystemException("02", "第" + count + "行，物料代码更新失败");
                                }

                            } else {
                                throw new SystemException("02", "第" + count + "行，物料代码：" + cell.getStringCellValue().trim() + "，已存在");
                            }
                        }else {
                            maVerInfo.setMaBarCode(cell.getStringCellValue().trim());
                        }

                        materialInfo.setGuid(CommonUtils.getRandomNumber());
                        materialInfo.setMaterialCode(cell.getStringCellValue().trim());
                        maVerInfo.setGuid(CommonUtils.getRandomNumber());
                        maVerInfo.setMaterialCode(cell.getStringCellValue().trim());
                        materialInfo.setVerGd(maVerInfo.getGuid());
                        maVerInfo.setCreator((String) SecurityUtils.getSubject().getPrincipal());
                        maVerInfo.setCreateTime(new Date());
                        maVerInfo.setMaGd(materialInfo.getGuid());
                        materialInfo.setCreator((String) SecurityUtils.getSubject().getPrincipal());
                        materialInfo.setCreateTime(new Date());
                        maVerInfo.setSerialRuleGd("1");
                        maVerInfo.setReMaterial("01");
                        maVerInfo.setIsExem("00");
                        maVerInfo.setStatus("00");
                        maVerInfo.setVersion("1");
                        maVerInfo.setIsDef("00");
                        maVerInfo.setDisRule("00");
                        maVerInfo.setShelflife("01");
                        maVerInfo.setsUnit("03");
                        maVerInfo.setdSource("01");
                    } else if (i == 1) {
                        maVerInfo.setMaterialName(cell.getStringCellValue().trim());
                        materialInfo.setMaterialName(cell.getStringCellValue().trim());
                    } else if (i == 2) {
                        materialInfo.setMaterialDes(cell.getStringCellValue().trim());
                        maVerInfo.setMaterialDes(cell.getStringCellValue().trim());
                    }else if (i == 3) {
                        //materialInfo.setMaterialDes(materialmaPropertyInfo);
                        //maVerInfo.setMaterialDes(materialmaPropertyInfo);
                        // cell1.setV
                        //物料类型
                        if (!"产成品".equals(cell.getStringCellValue().trim()) && !"半成品".equals(cell.getStringCellValue().trim()) && !"原料".equals(cell.getStringCellValue().trim()) && !"其他".equals(cell.getStringCellValue().trim())) {
                            throw new SystemException("02", "第" + count + "行，物料类型：" + cell.getStringCellValue().trim() + "，不存在");
                        }
                        if (cell.getStringCellValue().trim() != null && "产成品".equals(cell.getStringCellValue().trim())) {
                            maVerInfo.setMaterialType("00");
                            maVerInfo.setMaTypeGd("00");
                            materialInfo.setMaterialType("00");
                        } else if (cell.getStringCellValue().trim() != null && "半成品".equals(cell.getStringCellValue().trim())) {
                            maVerInfo.setMaterialType("01");
                            maVerInfo.setMaTypeGd("01");
                            materialInfo.setMaterialType("01");
                        } else if (cell.getStringCellValue().trim() != null && "原料".equals(cell.getStringCellValue().trim())) {
                            maVerInfo.setMaterialType("02");
                            materialInfo.setMaterialType("02");
                            maVerInfo.setMaTypeGd("02");
                        } else {
                            maVerInfo.setMaterialType("03");
                            materialInfo.setMaterialType("03");
                            maVerInfo.setMaTypeGd("03");
                        }
                    } else if (i == 4) {
                        maVerInfo.setOrderNum(cell.getStringCellValue().trim());
                    } else if (i == 5) {
                        maVerInfo.setBrand(cell.getStringCellValue().trim());
                    } else if (i == 6) {
                        UnitInfo unitInfo = unitDAO.SelectByUnitName(cell.getStringCellValue().trim());
                        if (unitInfo == null) {
                            //如果直接用上面的对象会空指针异常， 所以重新用了一个
                            UnitInfo unitInfos=new UnitInfo();
                            unitInfos.setGuid(CommonUtils.getRandomNumber());
                            unitInfos.setCreator((String) SecurityUtils.getSubject().getPrincipal());
                            unitInfos.setCreateTime(new Date());
                            unitInfos.setUnitName(cell.getStringCellValue().trim());
                            unitInfos.setdSource("01");
                            unitDAO.InsertUnitInfo(unitInfos);
                            maVerInfo.setUnitGd(unitInfos.getGuid());
                        }else {
                            maVerInfo.setUnitGd(unitInfo.getGuid());
                        }
                    } else if(i==7) {
                        CompanyInfo companyInfo = companyDAO.SelectCompanyInfoByCompanyName(cell.getStringCellValue().trim());
                        if (companyInfo != null) {
                            maVerInfo.setCompanyCode(companyInfo.getCompanyCode());
                          //  materialInfo.setCompanyCode(companyInfo.getCompanyCode());
                        }
                    }else if (i == 8) {
                        maVerInfo.setIsExem(cell.getStringCellValue().trim());
                    }else if (i == 9) {
                        maVerInfo.setIsExem(cell.getStringCellValue().trim());
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        maPropertyInfo.setMaVerGd(maVerInfo.getGuid());
                        maPropertyInfo.setGuid(CommonUtils.getRandomNumber());
                        maPropertyInfo.setCreateTime(new Date());
                        maPropertyInfo.setCreator((String) SecurityUtils.getSubject().getPrincipal());
                        maPropertyInfo.setF1(cell.getStringCellValue().trim());
                        materialmaPropertyInfo=cell.getStringCellValue().trim();
                    } else if (i == 10) {
                        maPropertyInfo.setF2(cell.getStringCellValue().trim());
                        materialmaPropertyInfo+=" "+cell.getStringCellValue().trim();
                    } else if (i == 11) {
                        maPropertyInfo.setF3(cell.getStringCellValue().trim());
                        materialmaPropertyInfo+=" "+cell.getStringCellValue().trim();
                    } else if (i == 12) {
                        maPropertyInfo.setF4(cell.getStringCellValue().trim());
                        materialmaPropertyInfo+=" "+cell.getStringCellValue().trim();
                    }else if (i == 13) {
                        maPropertyInfo.setF5(cell.getStringCellValue().trim());
                        materialmaPropertyInfo+=" "+cell.getStringCellValue().trim();
                    }else if (i == 14) {
                        maPropertyInfo.setF6(cell.getStringCellValue().trim());
                        materialmaPropertyInfo+=" "+cell.getStringCellValue().trim();
                    }else if (i == 15) {
                        maPropertyInfo.setF7(cell.getStringCellValue().trim());
                        materialmaPropertyInfo+=" "+cell.getStringCellValue().trim();
                    }else if (i == 16) {
                        maPropertyInfo.setF8(cell.getStringCellValue().trim());
                        materialmaPropertyInfo+=" "+cell.getStringCellValue().trim();
                    }else if (i == 17) {
                        maPropertyInfo.setF9(cell.getStringCellValue().trim());
                        materialmaPropertyInfo+=" "+cell.getStringCellValue().trim();
                    }else if (i == 18) {
                        maPropertyInfo.setF10(cell.getStringCellValue().trim());
                        materialmaPropertyInfo+=" "+cell.getStringCellValue().trim();
                    }
                } else {
                    /*Cell cell2 = row.getCell(8);
                    if(cell2.getStringCellValue()=="原料"||"原料".equals(cell2.getStringCellValue())||cell2.getStringCellValue()=="其他"||"其他".equals(cell2.getStringCellValue())){
                        Cell cell3 = row.getCell(1);
                        //原料或者其他时，保证唯一性
                        MaVerInfo a=maVerDAO.SelectMaterialInfoBymaBarCode(cell3.getStringCellValue().trim());
                        if(a!=null){
                            throw new SystemException("02", "第" + count + "行，物料类型：" + cell.getStringCellValue().trim() + "，的物料条码已存在");
                        }
                    }else{
                        continue;
                    }*/
                    if (i == 0) {
                        Cell cell2 = row.getCell(i);
                        if((cell2 == null || "".equals(cell2.toString()))&& (row.getCell(1)==null||row.getCell(1).equals(""))){
                            break a;
                        }
                        if (cell2 == null || "".equals(cell2.toString())) {
                            CodeRuleInfo codeRuleInfo = codeRuleDAO.SelectCodeRuleInfocodeType("00");
                            String SCode = "";
                            if (codeRuleInfo != null && "00".equals(codeRuleInfo.getStatus())) {
                                SCode = gConfigIService.GetCreateSC(codeRuleInfo);
                                materialInfo.setMaterialCode(SCode);
                                //导入的时候，如果excel中的物料代码为空的时候，者物料代码和物料条码一样
                                maVerInfo.setMaBarCode(SCode);
                                maVerInfo.setMaterialCode(SCode);
                            } else {
                                throw new SystemException("", "请输入{物料代码}，或请到全局配置进行代码生成配置");
                            }
                        }


                        materialInfo.setGuid(CommonUtils.getRandomNumber());

                        maVerInfo.setGuid(CommonUtils.getRandomNumber());
                        materialInfo.setVerGd(maVerInfo.getGuid());
                        maVerInfo.setCreator((String) SecurityUtils.getSubject().getPrincipal());
                        maVerInfo.setCreateTime(new Date());
                        maVerInfo.setMaGd(materialInfo.getGuid());
                        materialInfo.setCreator((String) SecurityUtils.getSubject().getPrincipal());
                        materialInfo.setCreateTime(new Date());
                        maVerInfo.setSerialRuleGd("1");
                        maVerInfo.setReMaterial("01");
                        maVerInfo.setIsExem("00");
                        maVerInfo.setStatus("00");
                        maVerInfo.setVersion("1");
                        maVerInfo.setIsDef("00");
                        maVerInfo.setDisRule("00");
                        maVerInfo.setShelflife("01");
                        maVerInfo.setsUnit("03");
                        maVerInfo.setdSource("01");
                        continue;
                    }
                    if (i == 2) {
                        continue;
                    }
                    if (i == 3) {

                        continue;
                    }
                    if (i == 4) {
                        continue;
                    }
                    if (i == 5) {
                        continue;
                    }
                    if (i == 6) {
                        continue;
                    }
                    if (i == 8) {

                        continue;
                    }
                    if (i == 9) {
                        maPropertyInfo.setMaVerGd(maVerInfo.getGuid());
                        maPropertyInfo.setGuid(CommonUtils.getRandomNumber());
                        maPropertyInfo.setCreateTime(new Date());
                        maPropertyInfo.setCreator((String) SecurityUtils.getSubject().getPrincipal());
                        continue;
                    }
                    if (i == 10) {
                        continue;
                    }
                    if (i == 11) {
                        continue;
                    }
                    if (i == 12) {
                        continue;
                    }
                    if (i == 13) {
                        continue;
                    }
                    if (i == 14) {
                        continue;
                    }
                    if (i == 15) {
                        continue;
                    }
                    if (i == 16) {
                        continue;
                    }
                    if (i == 17) {
                        continue;
                    }
                    if (i == 18) {
                        continue;
                    }


                    throw new SystemException("07", "第" + count + "行，第" + (i + 1) + "列不能为空");
                }
            }

            if (!flag) {
                m++;
                continue;
            }
            if (aa) {
                materialDAO.InsertMaterialInfo(materialInfo);
                maVerDAO.InsertMaverInfo(maVerInfo);
                maPropertyDAO.InsertMaPropertyInfo(maPropertyInfo);
            }else {
                materialInfo.setRuid(materialInfo1.getRuid());
                materialInfo.setGuid(materialInfo1.getGuid());
                materialInfo.setVerGd(materialInfo1.getVerGd());
                int i=materialDAO.UpdateMaterialInfo(materialInfo);
                MaVerInfo maVerInfo1=maVerDAO.SelectMaverInfo(materialInfo1.getVerGd());
                maVerInfo.setRuid(maVerInfo1.getRuid());
                maVerInfo.setGuid(maVerInfo1.getGuid());
                int r=maVerDAO.UpdateMaVerInfo(maVerInfo);
                MaPropertyInfo maPropertyInfo1=maPropertyDAO.SelectByMaVerF(maVerInfo1.getGuid());
                if(maPropertyInfo1!=null){
                    maPropertyInfo.setRuid(maPropertyInfo1.getRuid());
                    maPropertyInfo.setGuid(maPropertyInfo1.getGuid());
                    maPropertyInfo.setMaVerGd(maVerInfo.getGuid());
                    maPropertyInfo.setCreator(null);
                    maPropertyInfo.setCreateTime(null);
                    maPropertyInfo.setLastModifyMan(CommonUtils.readUser().getUserName());
                    maPropertyInfo.setLastModifyTime(new Date());
                    int s=maPropertyDAO.UpdateMaPropertyInfo(maPropertyInfo);
                }else {
                    maPropertyInfo.setMaVerGd(maVerInfo.getGuid());
                    int s=maPropertyDAO.InsertMaPropertyInfo(maPropertyInfo);
                }

            }

            count++;
            m++;
        }
        saveImportResB.setMsgCode("0x00000");
        saveImportResB.setMsgDes("成功");
        return saveImportResB;
    }

    //导出物料
    @Override
    public ByteArrayOutputStream exportMaterialsExcel(GetAllMaInfoReqBD00 argBD00, List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        try {

            List<GetAllMaInfoResD> objEGetAllMaInfoResDs = new ArrayList<GetAllMaInfoResD>();

            if (argInitDataFields != null) {
                for (int i = 0; i < argInitDataFields.size(); i++) {
                    if ("MaTypeRd".equals(argInitDataFields.get(i).getFieldName())) {
                        if ("".equals(argInitDataFields.get(i).getFieldVal())) {
                            argInitDataFields.remove(i);
                            break;
                        }
                        //查询物料类别标识
                        MaTypeInfo objEMaTypeInfo = maTypeDAO.SelectByRuid(Integer.valueOf(argInitDataFields.get(i).getFieldVal()));
                        if (objEMaTypeInfo == null) {
                            throw new SystemException("", "物料类型不存在!");
                        }
                        argInitDataFields.get(i).setFieldName("MaTypeGd");
                        argInitDataFields.get(i).setFieldVal(objEMaTypeInfo.getGuid());
                        break;
                    }
                }
            }

            List<MaVerInfo> objEMaVerInfos = baseIService.QALLBaseInfo(MaVerDAO.class, "SelectAllMaVerInfo",
                    argInitDataFields, argPageInfo);

            GetAllMaInfoResD objEGetAllMaInfoResD = null;

            if (objEMaVerInfos != null && objEMaVerInfos.size() > 0) {

                HashOperations<String, String, Map<String, String>> ho = redisTemplate.opsForHash();

                Map<String, MaterialInfo> mapMa = new HashMap<String, MaterialInfo>();
                List<MaterialInfo> objEMaterialInfos = materialDAO.SelectAllMaterialInfo();
                if (objEMaterialInfos != null) {
                    for (MaterialInfo materialInfo : objEMaterialInfos) {
                        mapMa.put(materialInfo.getGuid(), materialInfo);
                    }
                }

                MaterialInfo objEMaterialInfo = null;
                int i=0;
                for (MaVerInfo maVerInfo : objEMaVerInfos) {
                    i++;
                    if(i==2511){
                      System.out.print("");
                    }

                    try {
                        objEMaterialInfo = mapMa.get(maVerInfo.getMaGd());
                    }catch (Exception e){
                        throw new SystemException("", "就是这报错!");
                    }

                    if (objEMaterialInfo != null) {
                        objEGetAllMaInfoResD = new GetAllMaInfoResD();
                        objEGetAllMaInfoResD.setMaRd(objEMaterialInfo.getRuid());
                        objEGetAllMaInfoResD.setMaCode(objEMaterialInfo.getMaterialCode());
                        objEGetAllMaInfoResD.setMaName(objEMaterialInfo.getMaterialName());
                        if(objEMaterialInfo.getMaterialDes()!=null){
                            objEGetAllMaInfoResD.setMaDes(objEMaterialInfo.getMaterialDes());
                        }else {
                            objEGetAllMaInfoResD.setMaDes("");
                        }

                        objEGetAllMaInfoResD.setMaVerRd(maVerInfo.getRuid());
                        objEGetAllMaInfoResD.setVersion(maVerInfo.getVersion());
                        objEGetAllMaInfoResD.setMaType(maVerInfo.getMaterialType());
                        objEGetAllMaInfoResD.setOrderNum(maVerInfo.getOrderNum());
                        objEGetAllMaInfoResD.setBrand(maVerInfo.getBrand());
                        UnitInfo unitInfo = unitDAO.SelectByGuid(maVerInfo.getUnitGd());
                        if (unitInfo != null) {
                            objEGetAllMaInfoResD.setUnitName(unitInfo.getUnitName());
                        } else {
                            objEGetAllMaInfoResD.setUnitName("");
                        }
                        //企业名称(用版本字段代替企业名称)
                        CompanyInfo companyInfo=null;
                        if(!(maVerInfo.getCompanyCode()==null||maVerInfo.getCompanyCode().equals(""))){
                            companyInfo = companyDAO.SelectCompanyInfoBycompanyCode(maVerInfo.getCompanyCode());
                        }

                        if (companyInfo != null) {
                            objEGetAllMaInfoResD.setVersion(companyInfo.getCompanyName());
                        } else {
                            objEGetAllMaInfoResD.setVersion("");
                        }

                        objEGetAllMaInfoResDs.add(objEGetAllMaInfoResD);
                    }
                }
            }


            //创建table工作薄
            HSSFSheet sheet1 = wb.createSheet("table");
            Object[][] datas1 = {
                    {"物料代码", "物料名称", "物料描述", "物料类型", "订货号", "品牌", "单位", "企业名称"}};

            HSSFRow row1;
            HSSFCell cell1;
            //建表
            for (int i = 0; i < 1; i++) {
                row1 = sheet1.createRow(i);//创建表格行

                for (int j = 0; j < datas1[i].length; j++) {

                    cell1 = row1.createCell(j);//根据表格行创建单元格

                    cell1.setCellValue(String.valueOf(datas1[i][j]));
                }
            }


            /**********************************数据************************************/
            HSSFSheet sheet3 = wb.getSheet("table");
            HSSFRow row3;
            HSSFCell cell3;
            //建表
            for (int i = 0; i < objEGetAllMaInfoResDs.size(); i++) {
                row3 = sheet3.createRow(i + datas1.length);//创建表格行

                for (int j = 0; j < datas1[0].length; j++) {

                    cell3 = row3.createCell(j);//根据表格行创建单元格
                    if (j == 0) {
                        cell3.setCellValue(objEGetAllMaInfoResDs.get(i).getMaCode());
                    } else if (j == 1) {
                        cell3.setCellValue(objEGetAllMaInfoResDs.get(i).getMaName());
                    } else if (j == 2) {
                        cell3.setCellValue(objEGetAllMaInfoResDs.get(i).getMaDes());
                    } else if (j == 3) {
                        if ("00".equals(objEGetAllMaInfoResDs.get(i).getMaType())) {
                            cell3.setCellValue("产成品");
                        } else if ("01".equals(objEGetAllMaInfoResDs.get(i).getMaType())) {
                            cell3.setCellValue("半成品");
                        } else if ("02".equals(objEGetAllMaInfoResDs.get(i).getMaType())) {
                            cell3.setCellValue("原料");
                        } else {
                            cell3.setCellValue("其他");
                        }
                    } else if (j == 4) {
                        cell3.setCellValue(objEGetAllMaInfoResDs.get(i).getOrderNum());
                    } else if (j == 5) {
                        cell3.setCellValue(objEGetAllMaInfoResDs.get(i).getBrand());
                    } else if (j == 6) {
                        cell3.setCellValue(objEGetAllMaInfoResDs.get(i).getUnitName());
                    } else if (j == 7) {
                        cell3.setCellValue(objEGetAllMaInfoResDs.get(i).getVersion());
                    }
                }

            }

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            wb.write(os);

            return os;

        } catch (Exception e) {
            if (e != null && e.getMessage() != null) {
                throw new SystemException("0x000000", e.getMessage());
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
