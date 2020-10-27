package pnc.mesadmin.service.impl;

import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoRes;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoResB;
import pnc.mesadmin.dto.GetAllMaSDTInfo.*;
import pnc.mesadmin.dto.GetAllMaSTInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.InStoreIService;
import pnc.mesadmin.utils.DateUtil;
import pnc.mesadmin.entity.common.GetAllXiangAndBatch;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：库存信息Service实现类
 * 创建人：刘福志
 * 创建时间：2017-6-16
 * 修改人：
 * 修改时间：
 */
@Service
@Transactional
public class InStoreService implements InStoreIService {

    @Resource
    private InstockDAO instockDAO;

    @Resource
    private InstockDtlDAO instockDtlDAO;

    @Resource
    private LocationDAO locationDAO;

    @Resource
    private StoreDAO storeDAO;

    @Resource
    private BDAO bdao;

    @Resource
    private MaterialDAO materialDAO;

    @Resource
    private MaVerDAO maVerDAO;

    @Resource
    private UnitDAO unitDAO;

    @Resource
    private OutMaInfoDAO outMaInfoDAO;

    @Resource
    private SPackDAO sPackDAO;

    @Resource
    private PackDAO packDAO;

    @Resource
    private PackRelDAO packRelDAO;

    @Resource
    private WODAO wodao;

    @Resource
    private WoPropertyDao woPropertyDao;

    //查询物料库存信息00
    @Override
    public GetAllMaSTInfoRes GetInstockInfo(GetAllMaSTInfoReqBD00 argGetAllMaSTInfoReqBD00) throws SystemException {
        GetAllMaSTInfoRes objEGetAllMaSTInfoRes = new GetAllMaSTInfoRes();

        GetAllMaSTInfoResB body = new GetAllMaSTInfoResB();

        List<GetAllMaSTInfoResD> dataList = new ArrayList<GetAllMaSTInfoResD>();

        GetAllMaSTInfoResD data = null;

        //查询库存物料类型
        List<InstockInfo> instockInfoList = instockDAO.SelectmaterialType(argGetAllMaSTInfoReqBD00.getMaType());

        if (instockInfoList != null || instockInfoList.size() > 0) {
            // 循环赋值查询库存信息
            for (int i = 0; i < instockInfoList.size(); i++) {
                data = new GetAllMaSTInfoResD();
                data.setInsRd(instockInfoList.get(i).getRuid());
                data.setMaCode(instockInfoList.get(i).getMaterialCode());
                data.setMaName(instockInfoList.get(i).getMaterialName());
                if (instockInfoList.get(i).getNum() != null) {
                    data.setNum(instockInfoList.get(i).getNum());
                } else {
                    data.setNum(0f);
                }
                dataList.add(data);
            }
        }

        body.setData(dataList);
        objEGetAllMaSTInfoRes.setBody(body);

        return objEGetAllMaSTInfoRes;
    }

    //查询物料库存信息01
    @Override
    public GetAllMaSTInfoRes GetselectInstockInfo(GetAllMaSTInfoReqBD01 argGetAllMaSTInfoReqBD01) throws SystemException {
        GetAllMaSTInfoRes objEGetAllMaSTInfoRes = new GetAllMaSTInfoRes();

        GetAllMaSTInfoResB body = new GetAllMaSTInfoResB();

        List<GetAllMaSTInfoResD> dataList = new ArrayList<GetAllMaSTInfoResD>();

        GetAllMaSTInfoResD data = null;

        //查询仓库信息
        StoreInfo storeInfo = storeDAO.SelectByRuid(argGetAllMaSTInfoReqBD01.getStoreRd());

        if (storeInfo != null) {
            List<InstockInfo> objEInstockInfo = instockDAO.SelectBystoreGdShaixuan(storeInfo.getGuid(), argGetAllMaSTInfoReqBD01.getMaCode(), argGetAllMaSTInfoReqBD01.getMaName());
            if (objEInstockInfo != null && objEInstockInfo.size() > 0) {
                for (InstockInfo obj : objEInstockInfo) {
                    data = new GetAllMaSTInfoResD();
                    MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                    if (objEMaVerInfo != null) {
                        MaterialInfo objEMaterialInfo = materialDAO.SelectByMaCode(objEMaVerInfo.getMaterialCode());
                        if(objEMaterialInfo!=null){
                            data.setInsRd(obj.getRuid());
                            data.setMaCode(objEMaVerInfo.getMaterialCode());
                            data.setMaName(objEMaVerInfo.getMaterialName());
                            if (objEMaterialInfo.getMaterialDes() != null && !"".equals(objEMaterialInfo.getMaterialDes())) {
                                data.setMaDes(objEMaterialInfo.getMaterialDes());
                            } else {
                                data.setMaDes("");
                            }
                            data.setStoreName(storeInfo.getStoreName());
                            data.setMaVerRd(objEMaVerInfo.getRuid());
                            if (!objEMaVerInfo.getUnitGd().equals("") && objEMaVerInfo.getUnitGd() != null) {
                                UnitInfo obJEUnitInfo = unitDAO.SelectByGuid(objEMaVerInfo.getUnitGd());

                                if (obJEUnitInfo != null) {
                                    data.setUnitName(obJEUnitInfo.getUnitName());
                                } else {
                                    data.setUnitName("");
                                }
                            }

                            data.setMaxSNum(objEMaVerInfo.getMaxSNum());
                            data.setMinSNum(objEMaVerInfo.getMinSNum());
                        }

                    }

                    data.setNum(obj.getNum());

                    dataList.add(data);
                }
            }
        }

        body.setData(dataList);
        objEGetAllMaSTInfoRes.setBody(body);

        return objEGetAllMaSTInfoRes;
    }

    //查询物料库存信息02 InitData查询
    @Override
    public GetAllMaSTInfoRes GetInstockInfo1(GetAllMaSTInfoReqBD01 argGetAllMaSTInfoReqBD01) throws SystemException {
        GetAllMaSTInfoRes objEGetAllMaSTInfoRes = new GetAllMaSTInfoRes();

        GetAllMaSTInfoResB body = new GetAllMaSTInfoResB();

        List<GetAllMaSTInfoResD> dataList = new ArrayList<GetAllMaSTInfoResD>();

        GetAllMaSTInfoResD data = null;
        //查询仓库信息
        StoreInfo storeInfo = storeDAO.SelectByRuid(argGetAllMaSTInfoReqBD01.getStoreRd());

        if (storeInfo != null) {
            List<InstockInfo> objEInstockInfo = instockDAO.SelectBystoreGdShaixuan00(storeInfo.getGuid(), argGetAllMaSTInfoReqBD01.getMaCode(), argGetAllMaSTInfoReqBD01.getMaName());
            if (objEInstockInfo != null && objEInstockInfo.size() > 0) {
                for (InstockInfo obj : objEInstockInfo) {
                    data = new GetAllMaSTInfoResD();
                    MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                    if (objEMaVerInfo != null) {
                        MaterialInfo objEMaterialInfo = materialDAO.SelectByMaCode(objEMaVerInfo.getMaterialCode());
                        data.setInsRd(obj.getRuid());
                        data.setMaCode(objEMaVerInfo.getMaterialCode());
                        data.setMaName(objEMaVerInfo.getMaterialName());
                        if (objEMaterialInfo.getMaterialDes() != null && !"".equals(objEMaterialInfo.getMaterialDes())) {
                            data.setMaDes(objEMaterialInfo.getMaterialDes());
                        } else {
                            data.setMaDes("");
                        }
                        data.setMaVerRd(objEMaVerInfo.getRuid());
                        if (!objEMaVerInfo.getUnitGd().equals("") && objEMaVerInfo.getUnitGd() != null) {
                            UnitInfo obJEUnitInfo = unitDAO.SelectByGuid(objEMaVerInfo.getUnitGd());

                            if (obJEUnitInfo != null) {
                                data.setUnitName(obJEUnitInfo.getUnitName());
                            } else {
                                data.setUnitName("");
                            }
                        }
                    }

                    data.setNum(obj.getNum());

                    dataList.add(data);
                }
            }
        }

        body.setData(dataList);
        objEGetAllMaSTInfoRes.setBody(body);

        return objEGetAllMaSTInfoRes;
    }

    //查询物料库存明细列表信息
    @Override
    public GetAllMaSDTInfoRes GetselectInstockDtlInfo(GetAllMaSDTInfoReqBD00 argGetAllMaSDTInfoReqBD00) throws SystemException {
        GetAllMaSDTInfoRes objEGetAllMaSDTInfoRes = new GetAllMaSDTInfoRes();

        GetAllMaSDTInfoResB body = new GetAllMaSDTInfoResB();

        // 获取库存明细信息列表
        GetAllMaSDTInfoResD data = new GetAllMaSDTInfoResD();;

        List<GetAllMaSDTInfoResDMaBInfo> MaBInfoList = new ArrayList<GetAllMaSDTInfoResDMaBInfo>();

        List<GetAllMaSDTInfoResDBInfo> BInfoList = new ArrayList<GetAllMaSDTInfoResDBInfo>();
        //查询库存信息
        InstockInfo objEInstockInfo = instockDAO.SelectByInsRd(argGetAllMaSDTInfoReqBD00.getInsRd());

        if (objEInstockInfo != null) {

            //查询库存里列表信息
            List<InstockDtlInfo> instockDtlInfoList = instockDtlDAO.SelectByinsGd(objEInstockInfo.getGuid());

            if (instockDtlInfoList != null || instockDtlInfoList.size() > 0) {
                GetAllMaSDTInfoResDMaBInfo objMaBInfo = null;
                GetAllMaSDTInfoResDBInfo objBInfo = null;
                // 循环赋值查询
                for (InstockDtlInfo obj : instockDtlInfoList) {
                    //关联查询库存信息
                    objMaBInfo = new GetAllMaSDTInfoResDMaBInfo();

                    objBInfo = new GetAllMaSDTInfoResDBInfo();
                    //查询库位信息
                    LocationInfo objELocationInfo = locationDAO.SelectlocationGd(obj.getLocationGd());

                    //查询仓库信息
                    StoreInfo objEStoreInfos = storeDAO.SelectByguid(obj.getStoreGd());

                    //查询批次的可用数量
                    BInfo objeBInfo = bdao.selectBatchInfoByBatch(obj.getBatch());
                    if (objeBInfo != null) {
                        if(!"00".equals(objeBInfo.getInStockStatus())){
                            continue;
                        }
                        //赋值查询库存信息
                        objBInfo.setBatch(obj.getBatch());
                        if (objELocationInfo != null) {
                            objBInfo.setLName(objELocationInfo.getlName());
                        } else {
                            objBInfo.setLName("");
                        }
                        objBInfo.setNum(objeBInfo.getNum());
                        objBInfo.setCanNum(objeBInfo.getCanNum());
                        if (obj.getUnitName() != null) {
                            objBInfo.setUnitName(obj.getUnitName());
                        } else {
                            objBInfo.setUnitName("");
                        }
                        objBInfo.setRKOpter(obj.getCreator());
                        objBInfo.setRKTime(DateUtil.format(obj.getCreateTime()));
                        if (objEStoreInfos != null) {
                            objBInfo.setStoreName(objEStoreInfos.getStoreName());
                        } else {
                            objBInfo.setStoreName("");
                        }
                        if ("02".equals(objeBInfo.getStatus())) {
                            objBInfo.setRemark("冻结");
                        } else if ("03".equals(objeBInfo.getStatus())) {
                            objBInfo.setRemark("报废");
                        } else if (obj.getRemark() != null) {
                            objBInfo.setRemark(obj.getRemark());
                        } else {
                            objBInfo.setRemark("");
                        }
                        BInfoList.add(objBInfo);
                    } else {
                        MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                        if(objEMaVerInfo!=null) {
                            MaterialInfo objEMaterialInfo = materialDAO.SelectByMaCode(objEMaVerInfo.getMaterialCode());
                            //库存明细
                            objMaBInfo.setMaCode(objEMaVerInfo.getMaterialCode());
                            objMaBInfo.setMaName(objEMaterialInfo.getMaterialName());
                            objMaBInfo.setMaDes(objEMaterialInfo.getMaterialDes());
                            if (objEStoreInfos != null) {
                                objMaBInfo.setStoreName(objEStoreInfos.getStoreName());
                            } else {
                                objBInfo.setStoreName("");
                            }

                            if (objELocationInfo != null) {
                                objMaBInfo.setLName(objELocationInfo.getlName());
                            } else {
                                objMaBInfo.setLName("");
                            }

                            objMaBInfo.setNum(obj.getNum());
                            if (obj.getUnitName() != null) {
                                objMaBInfo.setUnitName(obj.getUnitName());
                            } else {
                                objMaBInfo.setUnitName("");
                            }

                            MaBInfoList.add(objMaBInfo);
                        }
                    }
                }
            }
        }

        data.setBInfo(BInfoList);
        data.setMaBInfo(MaBInfoList);
        body.setData(data);
        objEGetAllMaSDTInfoRes.setBody(body);

        return objEGetAllMaSDTInfoRes;
    }

    //筛选
    @Override
    public GetAllMaSTInfoRes GetselectInstockInfo1(GetAllMaSTInfoReqBD01 argGetAllMaSTInfoReqBD01) throws SystemException {
        GetAllMaSTInfoRes objEGetAllMaSTInfoRes = new GetAllMaSTInfoRes();

        GetAllMaSTInfoResB body = new GetAllMaSTInfoResB();

        List<GetAllMaSTInfoResD> dataList = new ArrayList<GetAllMaSTInfoResD>();

        GetAllMaSTInfoResD data = null;

        List<InstockInfo> objEInstockInfo = instockDAO.SelectBystoreGdShaixuanMaterial_Storeinfo_Maverinfo1(
                argGetAllMaSTInfoReqBD01.getStoreRd(), argGetAllMaSTInfoReqBD01.getMaterialDes(),
                argGetAllMaSTInfoReqBD01.getBrand(), argGetAllMaSTInfoReqBD01.getOrderNum(),
                argGetAllMaSTInfoReqBD01.getMaCode(), argGetAllMaSTInfoReqBD01.getMaName(),
                argGetAllMaSTInfoReqBD01.getSize(), argGetAllMaSTInfoReqBD01.getMaterial(),
                argGetAllMaSTInfoReqBD01.getNorm(), argGetAllMaSTInfoReqBD01.getModel()
        );

        if (objEInstockInfo != null && objEInstockInfo.size() > 0) {
            for (InstockInfo obj : objEInstockInfo) {
                data = new GetAllMaSTInfoResD();
                MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                if (objEMaVerInfo != null) {
                MaterialInfo objEMaterialInfo = materialDAO.SelectByMaCode(objEMaVerInfo.getMaterialCode());
                data.setInsRd(obj.getRuid());
                data.setMaCode(objEMaVerInfo.getMaterialCode());
                data.setMaName(objEMaVerInfo.getMaterialName());
                if (objEMaterialInfo.getMaterialDes() != null && !"".equals(objEMaterialInfo.getMaterialDes())) {
                    data.setMaDes(objEMaterialInfo.getMaterialDes());
                } else {
                    data.setMaDes("");
                }
                StoreInfo objEStoreInfos = storeDAO.SelectByguid(obj.getStoreGd());
                if (objEStoreInfos != null) {
                    data.setStoreName(objEStoreInfos.getStoreName());
                } else {
                    data.setStoreName("");
                }

                    data.setMaVerRd(objEMaVerInfo.getRuid());
                    if (!objEMaVerInfo.getUnitGd().equals("") && objEMaVerInfo.getUnitGd() != null) {
                        UnitInfo obJEUnitInfo = unitDAO.SelectByGuid(objEMaVerInfo.getUnitGd());

                        if (obJEUnitInfo != null) {
                            data.setUnitName(obJEUnitInfo.getUnitName());
                        } else {
                            data.setUnitName("");
                        }
                    }
                    data.setMaxSNum(objEMaVerInfo.getMaxSNum());
                    data.setMinSNum(objEMaVerInfo.getMinSNum());
                }

                data.setNum(obj.getNum());

                dataList.add(data);
            }
        }

        body.setData(dataList);
        objEGetAllMaSTInfoRes.setBody(body);

        return objEGetAllMaSTInfoRes;
    }

    //导出库存主表信息
    @Override
    public ByteArrayOutputStream ExportInStore(GetAllMaSTInfoReqBD01 argGetAllMaSTInfoReqBD01) {
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();

        try {

            List<GetAllMaSTInfoResD> dataList = new ArrayList<GetAllMaSTInfoResD>();

            GetAllMaSTInfoResD data = null;

            if (argGetAllMaSTInfoReqBD01.getStoreRd() != 0 && argGetAllMaSTInfoReqBD01.getMaName() == null && argGetAllMaSTInfoReqBD01.getMaCode() == null) {
                //查询仓库信息
                StoreInfo storeInfo = storeDAO.SelectByRuid(argGetAllMaSTInfoReqBD01.getStoreRd());

                if (storeInfo != null) {
                    List<InstockInfo> objEInstockInfo = instockDAO.SelectBystoreGdShaixuan(storeInfo.getGuid(), argGetAllMaSTInfoReqBD01.getMaCode(), argGetAllMaSTInfoReqBD01.getMaName());
                    if (objEInstockInfo != null && objEInstockInfo.size() > 0) {
                        for (InstockInfo obj : objEInstockInfo) {
                            data = new GetAllMaSTInfoResD();
                            MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                            if(objEMaVerInfo!=null) {
                                MaterialInfo objEMaterialInfo = materialDAO.SelectByMaCode(objEMaVerInfo.getMaterialCode());
                                data.setInsRd(obj.getRuid());
                                data.setMaCode(objEMaVerInfo.getMaterialCode());
                                data.setMaName(objEMaVerInfo.getMaterialName());
                                if (objEMaterialInfo.getMaterialDes() != null && !"".equals(objEMaterialInfo.getMaterialDes())) {
                                    data.setMaDes(objEMaterialInfo.getMaterialDes());
                                } else {
                                    data.setMaDes("");
                                }
                                StoreInfo objEStoreInfos = storeDAO.SelectByguid(obj.getStoreGd());
                                if (objEStoreInfos != null) {
                                    data.setStoreName(objEStoreInfos.getStoreName());
                                } else {
                                    data.setStoreName("");
                                }
                                data.setNum(obj.getNum());

                                dataList.add(data);
                            }
                        }
                    }
                }
            } else {
                List<InstockInfo> objEInstockInfo = instockDAO.SelectBystoreGdShaixuanMaterial_Storeinfo_Maverinfo1(
                        argGetAllMaSTInfoReqBD01.getStoreRd(), argGetAllMaSTInfoReqBD01.getMaterialDes(),
                        argGetAllMaSTInfoReqBD01.getBrand(), argGetAllMaSTInfoReqBD01.getOrderNum(),
                        argGetAllMaSTInfoReqBD01.getMaCode(), argGetAllMaSTInfoReqBD01.getMaName(),
                        argGetAllMaSTInfoReqBD01.getSize(), argGetAllMaSTInfoReqBD01.getMaterial(),
                        argGetAllMaSTInfoReqBD01.getNorm(), argGetAllMaSTInfoReqBD01.getModel()
                );

                if (objEInstockInfo != null && objEInstockInfo.size() > 0) {
                    for (InstockInfo obj : objEInstockInfo) {
                        data = new GetAllMaSTInfoResD();
                        MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                        if(objEMaVerInfo!=null) {
                            MaterialInfo objEMaterialInfo = materialDAO.SelectByMaCode(objEMaVerInfo.getMaterialCode());
                            data.setInsRd(obj.getRuid());
                            data.setMaCode(objEMaVerInfo.getMaterialCode());
                            data.setMaName(objEMaVerInfo.getMaterialName());
                            if (objEMaterialInfo.getMaterialDes() != null && !"".equals(objEMaterialInfo.getMaterialDes())) {
                                data.setMaDes(objEMaterialInfo.getMaterialDes());
                            } else {
                                data.setMaDes("");
                            }
                            StoreInfo objEStoreInfos = storeDAO.SelectByguid(obj.getStoreGd());
                            if (objEStoreInfos != null) {
                                data.setStoreName(objEStoreInfos.getStoreName());
                            } else {
                                data.setStoreName("");
                            }
                            data.setNum(obj.getNum());

                            dataList.add(data);
                        }
                    }
                }
            }

            //创建table工作薄
            HSSFSheet sheet1 = wb.createSheet("table");
            Object[][] datas1 = {
                    {"物料代码", "物料名称", "物料描述", "数量", "仓库"}};

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

            /**********************************库存数据************************************/
            HSSFSheet sheet3 = wb.getSheet("table");
            HSSFRow row3;
            HSSFCell cell3;
            //建表
            for (int i = 0; i < dataList.size(); i++) {
                row3 = sheet3.createRow(i + datas1.length);//创建表格行

                for (int j = 0; j < datas1[0].length; j++) {

                    cell3 = row3.createCell(j);//根据表格行创建单元格
                    if (j == 0) {
                        cell3.setCellValue(dataList.get(i).getMaCode());
                    } else if (j == 1) {
                        cell3.setCellValue(dataList.get(i).getMaName());
                    } else if (j == 2) {
                        cell3.setCellValue(dataList.get(i).getMaDes());
                    } else if (j == 3) {
                        cell3.setCellValue(dataList.get(i).getNum());
                    } else if (j == 4) {
                        cell3.setCellValue(dataList.get(i).getStoreName());
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

    //导出成品库存
    @Override
    public ByteArrayOutputStream CPExport(String MaVerRd, String StoreRd, String LRd, String F1, String F2, String F3, String F4, String CreateTime1, String CreateTime2, String Batch) {
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();

        try {

            List<GetAllXiangAndBatch> getAllXiangAndBatches=outMaInfoDAO.selectAllXiangAndBatch1( MaVerRd   ,StoreRd ,LRd,F1,F2,F3,F4,CreateTime1,CreateTime2,Batch);


            if(getAllXiangAndBatches!=null&&getAllXiangAndBatches.size()>0){
                for(GetAllXiangAndBatch obj:getAllXiangAndBatches){
                    if("01".equals(obj.getBarType())){
                      PackInfo packInfo=sPackDAO.SelectByPackCode(obj.getBatch());
                        Set<String> set=new HashSet<String>();
                        if(packInfo!=null){
                            if(packInfo.getRemark()!=null){
                                obj.setRemark(packInfo.getRemark());
                            }else {
                                obj.setRemark("");
                            }

                            List<String> str = outMaInfoDAO.selectUniqueCode(obj.getBatch());
                            String text = "";
                            if (str != null && str.size() > 0) {
                                for (String string : str) {
                                    text += string + "/";
                                }
                                obj.setUniqueText(text.substring(0, text.length() - 1));
                            } else {
                                obj.setUniqueText("");
                            }


                            /*pnc.mesadmin.entity.PackInfo packInfomain=packDAO.SelectByBarCode(obj.getBatch());
                            if(packInfomain!=null){
                                List<PackRelInfo> packRelInfos=packRelDAO.SelectListByPackGd(packInfomain.getGuid());
                                if(packRelInfos!=null&&packRelInfos.size()>0){
                                    for(PackRelInfo packRelInfo:packRelInfos){
                                        BInfo bInfo = bdao.selectBatchInfoByBatch(packRelInfo.getBarCode());
                                        if (bInfo != null) {
                                            WoInfo woInfo = wodao.SelectWoInfoByGuid(bInfo.getWoGd());
                                            if (woInfo != null) {
                                                WoPropertyInfo woPropertyInfo = woPropertyDao.SelectWoPropertyInfoByWoGd(woInfo.getGuid());
                                                if (woPropertyInfo != null) {
                                                    String f2 = woPropertyInfo.getF2() == null ? "0" : woPropertyInfo.getF2();
                                                    String f3 = woPropertyInfo.getF3() == null ? "0" : woPropertyInfo.getF3();
                                                    String f4 = woPropertyInfo.getF4() == null ? "0" : woPropertyInfo.getF4();
                                                    String f5 = woPropertyInfo.getF5() == null ? "0" : woPropertyInfo.getF5();
                                                    String f6 = woPropertyInfo.getF6() == null ? "0" : woPropertyInfo.getF6();
                                                    String f7 = woPropertyInfo.getF7() == null ? "0" : woPropertyInfo.getF7();
                                                    String map = f2 + f3 + f4 + f5 + f6 + f7;
                                                    set.add(map);
                                                }

                                            }
                                        }
                                    }
                                    String uniquetext="";
                                    for (String str : set) {
                                        uniquetext+=str+"-";
                                    }
                                    obj.setUniqueText(   uniquetext.substring(0,uniquetext.length()-1));
                                }
                            }*/
                        }else {
                            obj.setRemark("");
                        }
                    }
                }
            }





            //创建table工作薄
            HSSFSheet sheet1 = wb.createSheet("table");
            Object[][] datas1 = {
                    {"箱号", "库存数量", "产品代码", "产品名称", "产品描述", "功率", "电流", "颜色", "综合等级","识别码", "仓库", "库位", "备注", "单位", "创建时间"}};

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

            /**********************************库存数据************************************/
            HSSFSheet sheet3 = wb.getSheet("table");
            HSSFRow row3;
            HSSFCell cell3;
            //建表
            for (int i = 0; i < getAllXiangAndBatches.size(); i++) {
                row3 = sheet3.createRow(i + datas1.length);//创建表格行

                for (int j = 0; j < datas1[0].length; j++) {

                    cell3 = row3.createCell(j);//根据表格行创建单元格
                    if (j == 0) {
                        cell3.setCellValue(getAllXiangAndBatches.get(i).getBatch());
                    } else if (j == 1) {
                        cell3.setCellValue(getAllXiangAndBatches.get(i).getNum());
                    } else if (j == 2) {
                        cell3.setCellValue(getAllXiangAndBatches.get(i).getMaterialCode());
                    } else if (j == 3) {
                        cell3.setCellValue(getAllXiangAndBatches.get(i).getMaterialName());
                    } else if (j == 4) {
                        cell3.setCellValue(getAllXiangAndBatches.get(i).getMaterialDes());
                    }else if (j == 5) {
                        cell3.setCellValue(getAllXiangAndBatches.get(i).getPowerGear());
                    } else if (j == 6) {
                        cell3.setCellValue(getAllXiangAndBatches.get(i).getELGear());
                    } else if (j == 7) {
                        cell3.setCellValue(getAllXiangAndBatches.get(i).getColorGear());
                    } else if (j == 8) {
                        cell3.setCellValue(getAllXiangAndBatches.get(i).getGradeName());
                    }else if (j==9){
                        cell3.setCellValue(getAllXiangAndBatches.get(i).getUniqueText());
                    }
                    else if (j == 10) {
                        cell3.setCellValue(getAllXiangAndBatches.get(i).getStoreName());
                    }else if (j == 11) {
                        cell3.setCellValue(getAllXiangAndBatches.get(i).getLName());
                    } else if (j == 12) {
                        cell3.setCellValue(getAllXiangAndBatches.get(i).getRemark());
                    } else if (j == 13) {
                        cell3.setCellValue(getAllXiangAndBatches.get(i).getUnitName());
                    } else if (j == 14) {
                        cell3.setCellValue(getAllXiangAndBatches.get(i).getCreateTime());
                    }

                }

            }

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            wb.write(os);

            return os;

        } catch (Exception e) {
           e.printStackTrace();
        } finally {
            if (wb != null) {
                try {
                    wb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return null;
    }
}
