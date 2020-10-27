package pnc.mesadmin.service.impl;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoRes;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoResB;
import pnc.mesadmin.dto.GetAllLInfo.GetAllLInfoRes;
import pnc.mesadmin.dto.GetAllLInfo.GetAllLInfoResB;
import pnc.mesadmin.dto.GetAllLInfo.GetAllLInfoResD;
import pnc.mesadmin.dto.GetAllStoreInfo.GetAllStoreInfoReqBD00;
import pnc.mesadmin.dto.GetAllStoreInfo.GetAllStoreInfoRes;
import pnc.mesadmin.dto.GetAllStoreInfo.GetAllStoreInfoResB;
import pnc.mesadmin.dto.GetAllStoreInfo.GetAllStoreInfoResD;
import pnc.mesadmin.dto.GetLInfo.GetLInfoRes;
import pnc.mesadmin.dto.GetLInfo.GetLInfoResB;
import pnc.mesadmin.dto.GetLInfo.GetLInfoResD;
import pnc.mesadmin.dto.GetStoreInfo.*;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveLInfo.*;
import pnc.mesadmin.dto.SaveStoreInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.GConfigIService;
import pnc.mesadmin.service.StoreIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;
import pnc.mesadmin.utils.MSExcelUtil;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：仓库信息Service实现类
 * 创建人：刘福志
 * 创建时间：2017-6-13
 * 修改人：
 * 修改时间：
 */

@Transactional
@Service
public class StoreService implements StoreIService {

    @Resource
    private StoreDAO storeDAO;

    @Resource
    private LocationDAO locationDAO;

    @Resource
    private UserDAO userDAO;

    @Resource
    private RoleDAO roleDAO;

    @Resource
    private StorePMDAO storePMDAO;

    @Resource
    private BaseIService baseIService;

    @Resource
    private CodeRuleDAO codeRuleDAO;

    @Resource
    private GConfigIService gConfigIService;


    //查询仓库列表信息
    public GetAllStoreInfoRes QALLselectAllStoreInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo, GetAllStoreInfoReqBD00 argBD00) throws SystemException {
        GetAllStoreInfoRes objEGetAllStoreInfoRes = new GetAllStoreInfoRes();

        GetAllStoreInfoResB body = new GetAllStoreInfoResB();

        List<GetAllStoreInfoResD> dataList = new ArrayList<GetAllStoreInfoResD>();

        List<GetAllStoreInfoResD> dataList1=null;

        if(argBD00==null) {
            dataList1 = new ArrayList<GetAllStoreInfoResD>();

            List<StoreInfo> storeInfoList = baseIService.QALLBaseInfo(StoreDAO.class,"SelectAllStoreInfo",argInitDataFields,argPageInfo);

            if (storeInfoList != null || storeInfoList.size() > 0) {
                GetAllStoreInfoResD data = null;

                // 循环赋值
                for (int j = 0; j < storeInfoList.size(); j++) {
                    data = new GetAllStoreInfoResD();
                    data.setStoreRd(storeInfoList.get(j).getRuid());
                    data.setStoreName(storeInfoList.get(j).getStoreName());
                    dataList1.add(data);
                }
            }
        }else if(argBD00.getUserRd()==0) {
            dataList1 = new ArrayList<GetAllStoreInfoResD>();

            List<StoreInfo> storeInfoList = baseIService.QALLBaseInfo(StoreDAO.class,"SelectAllStoreInfo",argInitDataFields,argPageInfo);

            if (storeInfoList != null || storeInfoList.size() > 0) {
                GetAllStoreInfoResD data = null;

                // 循环赋值
                for (int j = 0; j < storeInfoList.size(); j++) {
                    data = new GetAllStoreInfoResD();
                    data.setStoreRd(storeInfoList.get(j).getRuid());
                    data.setStoreName(storeInfoList.get(j).getStoreName());
                    dataList1.add(data);
                }
            }
        }else{
            //查询绑定用户权限的仓库
            UserInfo userInfo = userDAO.SelectUserInfoByruid(argBD00.getUserRd());

            String rolerd = argBD00.getRoleRd();
            String[] split = rolerd.split("\\D");
            for (int i = 0; i < split.length; i++) {
                String rd = split[i];

                RoleInfo roleInfo = roleDAO.SelectRoleInfoByruid(Integer.parseInt(rd));
                if (roleInfo != null) {
                    //查询仓库权限信息
                    List<StorePMInfo> objEStorePMInfo = storePMDAO.SelectStorePMInfopMorGd(roleInfo.getGuid());

                    for (StorePMInfo obj : objEStorePMInfo) {
                        // 获取仓库信息列表信息
                        if ("01".equals(obj.getpMType())) {
                            List<StoreInfo> storeInfoList = storeDAO.SelectBystoreGd(obj.getStoreGd());

                            if (storeInfoList != null || storeInfoList.size() > 0) {
                                GetAllStoreInfoResD data = null;

                                // 循环赋值
                                for (int j = 0; j < storeInfoList.size(); j++) {
                                    data = new GetAllStoreInfoResD();
                                    data.setStoreRd(storeInfoList.get(j).getRuid());
                                    data.setStoreName(storeInfoList.get(j).getStoreName());
                                    dataList.add(data);
                                }
                            }
                        }
                    }
                }
            }


            if (userInfo != null) {
                //查询仓库权限信息
                List<StorePMInfo> objEStorePMInfo = storePMDAO.SelectStorePMInfopMorGd(userInfo.getGuid());

                for (StorePMInfo obj : objEStorePMInfo) {
                    // 获取仓库信息列表信息
                    if ("00".equals(obj.getpMType())) {
                        List<StoreInfo> storeInfoList = storeDAO.SelectBystoreGd(obj.getStoreGd());

                        if (storeInfoList != null || storeInfoList.size() > 0) {
                            GetAllStoreInfoResD data = null;

                            // 循环赋值
                            for (int i = 0; i < storeInfoList.size(); i++) {
                                data = new GetAllStoreInfoResD();
                                data.setStoreRd(storeInfoList.get(i).getRuid());
                                data.setStoreName(storeInfoList.get(i).getStoreName());
                                dataList.add(data);
                            }
                        }
                    }
                }
            }

            dataList1 = new ArrayList<GetAllStoreInfoResD>();

            Map<String, Integer> map = new HashMap<String, Integer>();
            for (int i = 0; i < dataList.size(); i++) {
                GetAllStoreInfoResD data = dataList.get(i);
                map.put(data.getStoreName(), data.getStoreRd());
            }

            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                GetAllStoreInfoResD data = new GetAllStoreInfoResD();
                data.setStoreRd(entry.getValue());
                data.setStoreName(entry.getKey());
                dataList1.add(data);
            }
        }

        body.setData(dataList1);
        objEGetAllStoreInfoRes.setBody(body);

        return objEGetAllStoreInfoRes;
    }


    //筛选接口
    @Override
    public GetAllLInfoRes QALLselectAllLocationInfo1(GetStoreInfoReqBD00 argGetStoreInfoReqBD00) throws SystemException {
        GetAllLInfoRes objEGetAllLInfoRes = new GetAllLInfoRes();

        GetAllLInfoResB body = new GetAllLInfoResB();

        List<GetAllLInfoResD> dataList = new ArrayList<GetAllLInfoResD>();

        StoreInfo storeInfo = storeDAO.SelectByRuid(argGetStoreInfoReqBD00.getStoreRd());
        if (storeInfo != null) {
            List<LocationInfo> locationInfoList = locationDAO.SelectAllLocationInfoBystoreGd(storeInfo.getGuid());
            if (locationInfoList != null && locationInfoList.size() > 0) {
                // 循环赋值
                for (int i = 0; i < locationInfoList.size(); i++) {
                    GetAllLInfoResD data = new GetAllLInfoResD();
                    data.setLRd(locationInfoList.get(i).getRuid());
                    data.setLName(locationInfoList.get(i).getlName());
                    dataList.add(data);
                }
            }
        }

        body.setData(dataList);
        objEGetAllLInfoRes.setBody(body);
        return objEGetAllLInfoRes;
    }

    //查询仓库信息
    public GetStoreInfoRes GetStoreInfo(int StoreRd) throws SystemException {
        GetStoreInfoRes objEGetStoreInfoRes = new GetStoreInfoRes();

        GetStoreInfoResB body = new GetStoreInfoResB();

        GetStoreInfoResD data = new GetStoreInfoResD();
        // 获取仓库信息
        StoreInfo storeInfo = storeDAO.SelectByRuid(StoreRd);

        List<GetStoreInfoResDPMInfo> objEGetStoreInfoResDPMInfo = new ArrayList<GetStoreInfoResDPMInfo>();
        GetStoreInfoResDPMInfo objEPMInfo = null;
        if (storeInfo != null) {

            data.setStoreRd(storeInfo.getRuid());
            data.setStoreName(storeInfo.getStoreName());
            data.setStoreCode(storeInfo.getStoreCode());
            data.setStoreType(storeInfo.getStoreType());
            data.setDSource(storeInfo.getdSource());
            data.setStoreType(storeInfo.getStoreType());
            data.setF1(storeInfo.getF1());
            data.setF2(storeInfo.getF2());
            data.setF3(storeInfo.getF3());
            data.setF4(storeInfo.getF4());
            data.setStatus(storeInfo.getStatus());
            //循环查询权限信息
            List<StorePMInfo> objEStorePMInfo = storePMDAO.SelectStorePMInfostoreGd(storeInfo.getGuid());

            for (StorePMInfo obj : objEStorePMInfo) {
                objEPMInfo = new GetStoreInfoResDPMInfo();
                objEPMInfo.setPMorRd(obj.getRuid());
                if ("00".equals(obj.getpMType())) {
                    UserInfo userInfo = userDAO.SelectUserRd(obj.getpMorGd());
                    if(userInfo!=null) {
                        objEPMInfo.setPMor(userInfo.getRealName());
                    }else{
                        objEPMInfo.setPMor("");
                    }
                }
                if ("01".equals(obj.getpMType())) {
                    RoleInfo roleInfo = roleDAO.selectRoleIdGuid(obj.getpMorGd());
                    if(roleInfo!=null) {
                        objEPMInfo.setPMor(roleInfo.getRoleName());
                    }else{
                        objEPMInfo.setPMor("");
                    }
                }
                objEPMInfo.setPMorType(obj.getpMType());
                objEGetStoreInfoResDPMInfo.add(objEPMInfo);
            }
            data.setPMInfo(objEGetStoreInfoResDPMInfo);
            data.setCreator(storeInfo.getCreator());
            data.setCreateTime(DateUtil.format(storeInfo.getCreateTime()));
            data.setLastModifyMan(storeInfo.getLastModifyMan());
            data.setLastModifyTime(DateUtil.format(storeInfo.getLastModifyTime()));
            data.setRemark(storeInfo.getRemark());
        }

        body.setData(data);
        objEGetStoreInfoRes.setBody(body);

        return objEGetStoreInfoRes;
    }

    //查询库位列表信息
    public GetAllLInfoRes QALLselectAllLocationInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException {
        GetAllLInfoRes objEGetAllLInfoRes = new GetAllLInfoRes();

        GetAllLInfoResB body = new GetAllLInfoResB();

        List<GetAllLInfoResD> dataList = new ArrayList<GetAllLInfoResD>();

        // 获取仓库所有信息
        List<LocationInfo> locationInfoList = baseIService.QALLBaseInfo(LocationDAO.class, "SelectAllLocationInfo", argInitDataFields, argPageInfo);

        if (locationInfoList != null || locationInfoList.size() > 0) {
            // 循环赋值
            for (int i = 0; i < locationInfoList.size(); i++) {
                GetAllLInfoResD data = new GetAllLInfoResD();
                data.setLRd(locationInfoList.get(i).getRuid());
                data.setLName(locationInfoList.get(i).getlName());
                dataList.add(data);
            }
        }

        body.setData(dataList);
        objEGetAllLInfoRes.setBody(body);

        return objEGetAllLInfoRes;
    }

    //查询库位信息
    public GetLInfoRes GetselectLocationInfo(int lRd) throws SystemException {
        GetLInfoRes objEGetLInfoRes = new GetLInfoRes();

        GetLInfoResB body = new GetLInfoResB();

        GetLInfoResD data = new GetLInfoResD();
        // 获取库位信息
        LocationInfo locationInfo = locationDAO.SelectLocationInfo(lRd);

        if (locationInfo != null) {
            //查询仓库信息
            StoreInfo objEStoreInfo = storeDAO.SelectByStoreGd(locationInfo.getStoreGd());

            // 赋值
            if (objEStoreInfo != null) {
                data.setStoreRd(objEStoreInfo.getRuid());
                data.setStoreName(objEStoreInfo.getStoreName());
            }
            data.setLRd(locationInfo.getRuid());
            data.setLCode(locationInfo.getlCode());
            data.setLName(locationInfo.getlName());
            data.setCreator(locationInfo.getCreator());
            data.setCreateTime(DateUtil.format(locationInfo.getCreateTime()));
            data.setLastModifyMan(locationInfo.getLastModifyMan());
            data.setLastModifyTime(DateUtil.format(locationInfo.getLastModifyTime()));
            data.setRemark(locationInfo.getRemark());
        }

        body.setData(data);
        objEGetLInfoRes.setBody(body);

        return objEGetLInfoRes;
    }

    //导出库位信息
    @Override
    public ByteArrayOutputStream ExportGetLInfo(GetStoreInfoReqBD00 argGetStoreInfoReqBD00) {
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();

        try {

            List<GetAllLInfoResD> dataList = new ArrayList<GetAllLInfoResD>();

            StoreInfo storeInfo = storeDAO.SelectByRuid(argGetStoreInfoReqBD00.getStoreRd());
            if (storeInfo != null) {
                List<LocationInfo> locationInfoList = locationDAO.SelectAllLocationInfoBystoreGd(storeInfo.getGuid());
                if (locationInfoList != null && locationInfoList.size() > 0) {
                    // 循环赋值
                    for (int i = 0; i < locationInfoList.size(); i++) {
                        GetAllLInfoResD data = new GetAllLInfoResD();
                        data.setLRd(locationInfoList.get(i).getRuid());
                        data.setLCode(locationInfoList.get(i).getlCode());
                        data.setLName(locationInfoList.get(i).getlName());
                        dataList.add(data);
                    }
                }
            }

            //创建table工作薄
            HSSFSheet sheet1 = wb.createSheet("table");
            sheet1.setPrintGridlines(false);
            sheet1.setGridsPrinted(false);
            Object[][] datas1 = {
                    {"库位代码", "库位名称"}};

            HSSFRow row1;
            HSSFCell cell1;
            //建表
            for (int i = 0; i < 1; i++) {
                row1 = sheet1.createRow(i);//创建表格行

                for (int j = 0; j < datas1[i].length; j++) {

                    cell1 = row1.createCell(j);//根据表格行创建单元格

                    cell1.setCellValue(String.valueOf(datas1[i][j]));

                    HSSFCellStyle cellStyle = wb.createCellStyle();
                    cellStyle.setAlignment(HorizontalAlignment.CENTER);
                    cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                    cellStyle.setBorderTop(BorderStyle.THIN);
                    cellStyle.setBorderBottom(BorderStyle.THIN);
                    cellStyle.setBorderLeft(BorderStyle.THIN);
                    cellStyle.setBorderRight(BorderStyle.THIN);
                    cell1.setCellStyle(cellStyle);
                }
            }

            //设置行高列宽
            sheet1.setPrintGridlines(false);
            for (int i = 0; i < datas1.length; i++) {
                row1 = sheet1.getRow(i);
                row1.setHeightInPoints(20);
                for (int j = 0; j < datas1[i].length; j++) {
                    sheet1.autoSizeColumn(j);
                    sheet1.setColumnWidth(j, MSExcelUtil.pixel2WidthUnits(65));
                }
            }


            /**********************************库存变动数据************************************/
            HSSFSheet sheet3 = wb.getSheet("table");
            HSSFRow row3;
            HSSFCell cell3;
            //建表
            for (int i = 0; i < dataList.size(); i++) {
                row3 = sheet3.createRow(i + datas1.length);//创建表格行

                for (int j = 0; j < datas1[0].length; j++) {

                    cell3 = row3.createCell(j);//根据表格行创建单元格
                    if (j == 0) {
                        cell3.setCellValue(dataList.get(i).getLCode());
                    } else if (j == 1) {
                        cell3.setCellValue(dataList.get(i).getLName());
                    }

                    HSSFCellStyle cellStyle = wb.createCellStyle();
                    cellStyle.setAlignment(HorizontalAlignment.CENTER);
                    cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                    cellStyle.setBorderTop(BorderStyle.THIN);
                    cellStyle.setBorderBottom(BorderStyle.THIN);
                    cellStyle.setBorderLeft(BorderStyle.THIN);
                    cellStyle.setBorderRight(BorderStyle.THIN);

                    cell3.setCellStyle(cellStyle);
                }

            }
            for (int i = 0; i < dataList.size(); i++) {
                row3 = sheet3.getRow(i + datas1.length);
                row3.setHeightInPoints(20);
                for (int j = 0; j < datas1[0].length; j++) {
                    sheet3.setColumnWidth(j, MSExcelUtil.pixel2WidthUnits(65));
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

    //保存库位信息
    public SaveLInfoRes AddinsertLocationInfo(SaveLInfoReqBD00 busData00, LocationInfo locationInfo) throws SystemException {
        SaveLInfoRes saveLInfoRes = new SaveLInfoRes();

        SaveLInfoResB body = new SaveLInfoResB();

        SaveLInfoResD data = new SaveLInfoResD();

        StoreInfo objEStoreInfo = storeDAO.SelectByRuid(busData00.getStoreRd());

        if (objEStoreInfo == null) {
            throw new SystemException("MG_MES8001010010001F", "查询仓库信息为空！");
        }

        //查询库位代码
        List<LocationInfo> objELocationInfos = locationDAO.SelectAllLocationInfo();

        if (objELocationInfos == null) {
            throw new SystemException("MG_MES8001012010001F", "查询库位信息为空！");
        }

        //查询库位名称
        List<LocationInfo> objELocationInfo = locationDAO.SelectAllLocationInfo();

        if (objELocationInfo == null) {
            throw new SystemException("MG_MES8001012010001F", "查询库位信息为空！");
        }

        // 赋值
        locationInfo.setGuid(CommonUtils.getRandomNumber());

        //逻辑校验库位代码不能重复
        if(!"".equals(busData00.getLCode())) {
            for (LocationInfo obj : objELocationInfos) {
                if (obj.getlCode().equals(busData00.getLCode())) {
                    throw new SystemException("MG0006F", "该库位代码已存在");
                }
            }
        }
        //逻辑校验库位名称不能重复
        for (LocationInfo obj : objELocationInfo) {
            if (obj.getlName().equals(busData00.getLName())) {
                throw new SystemException("MG0006F", "库位名称已存在");
            }
        }

        //查询代码生成
        CodeRuleInfo codeRuleInfo=codeRuleDAO.SelectCodeRuleInfocodeType("12");

        String SCode="";

        if(!"".equals(busData00.getLCode())) {
            locationInfo.setlCode(busData00.getLCode());
        }else if(codeRuleInfo!=null && "00".equals(codeRuleInfo.getStatus())){
            SCode=gConfigIService.GetCreateSC(codeRuleInfo);
            locationInfo.setlCode(SCode);
        }else{
            throw new SystemException("", "请输入{库位代码}，或请到全局配置进行代码生成配置");
        }

        locationInfo.setlName(busData00.getLName());
        locationInfo.setNum(0.00f);
        locationInfo.setStoreGd(objEStoreInfo.getGuid());
        locationInfo.setCreator(CommonUtils.readUser().getRealName());
        locationInfo.setCreateTime(new Date());
        locationInfo.setRemark(busData00.getRemark());

        // 保存
        locationDAO.InsertLocationInfo(locationInfo);

        LocationInfo locationInfos=locationDAO.SelectlocationGd(locationInfo.getGuid());

        data.setLRd(locationInfos.getRuid());
        data.setLCode(locationInfos.getlCode());
        body.setData(data);
        saveLInfoRes.setBody(body);

        return saveLInfoRes;
    }

    //更新库位信息
    public SaveLInfoRes ModupdateLocationInfo(SaveLInfoReqBD02 busData02, LocationInfo locationInfo) throws SystemException {
        SaveLInfoRes saveLInfoRes = new SaveLInfoRes();

        SaveLInfoResB body = new SaveLInfoResB();

        SaveLInfoResD data = new SaveLInfoResD();


        // 获取库位信息
        LocationInfo objElocationInfo = locationDAO.SelectLocationInfo(busData02.getLRd());

        // 判断返回库位是否为空
        if (locationInfo == null) {
            throw new SystemException("MG_MES80010120001F", "查询库位信息为空！");
        }

        //查询库位名称
        LocationInfo objELocationInfo = locationDAO.SelectlName(busData02.getLName());


        if (objELocationInfo != null && !objELocationInfo.getlName().equals(objElocationInfo.getlName())) {
            throw new SystemException("MG0006F", "更新失败，库位名称已存在");
        }

        StoreInfo objEStoreInfo = storeDAO.SelectByRuid(busData02.getStoreRd());

        if (objEStoreInfo == null) {
            throw new SystemException("MG_MES8001010010001F", "查询仓库信息为空！");
        }

        // 赋值更新
        locationInfo.setGuid(CommonUtils.getRandomNumber());
        locationInfo.setRuid(busData02.getLRd());
        locationInfo.setlName(busData02.getLName());
        locationInfo.setStoreGd(objEStoreInfo.getGuid());
        locationInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        locationInfo.setLastModifyTime(new Date());
        locationInfo.setRemark(busData02.getRemark());

        // 更新
        int count = locationDAO.UpdateLocationInfo(locationInfo);

        if (count <= 0) throw new SystemException("MG_MES80010130002F", "更新库位信息失败！");

        body.setData(data);
        saveLInfoRes.setBody(body);

        return saveLInfoRes;
    }

    //删除库位信息
    public SaveLInfoRes RmdeleteLocationInfo(int ruid) throws SystemException {
        SaveLInfoRes saveLInfoRes = new SaveLInfoRes();

        SaveLInfoResB body = new SaveLInfoResB();

        SaveLInfoResD data = new SaveLInfoResD();

        // 删除库位信息
        int count = locationDAO.DeleteLocationInfo(ruid);

        if (count <= 0) throw new SystemException("MG_MES80010130001F", "删除库位信息失败！");

        body.setData(data);
        saveLInfoRes.setBody(body);

        return saveLInfoRes;
    }

    //保存仓库信息
    public SaveStoreInfoRes AddinsertStoreInfo(SaveStoreInfoReqBD00 busData00, StoreInfo storeInfo) throws SystemException {
        SaveStoreInfoRes saveStoreInfoRes = new SaveStoreInfoRes();

        SaveStoreInfoResB body = new SaveStoreInfoResB();

        SaveStoreInfoResD data = new SaveStoreInfoResD();

        //查询仓库的名称
        List<StoreInfo> objEStoreInfos = storeDAO.SelectAllStoreInfo();

        String rand = CommonUtils.getRandomNumber();
        // 赋值新增仓库信息
        storeInfo.setGuid(rand);

        //逻辑校验保存的仓库名称不能相同
        for (StoreInfo obj : objEStoreInfos) {
            if (obj.getStoreName().equals(busData00.getStoreName())) {
                throw new SystemException("MG0006F", "仓库名称已存在");
            }
            if(!"".equals(busData00.getStoreCode()) && obj.getStoreCode().equals(busData00.getStoreCode())){
                throw new SystemException("", "仓库代码已存在");
            }
        }
        storeInfo.setStoreName(busData00.getStoreName());
        if (!busData00.getStoreType().equals("")) {
            storeInfo.setStoreType(busData00.getStoreType());
        } else {
            storeInfo.setStoreType("");
        }

        //查询代码生成
        CodeRuleInfo codeRuleInfo=codeRuleDAO.SelectCodeRuleInfocodeType("11");

        String SCode="";

        if(!"".equals(busData00.getStoreCode())) {
            storeInfo.setStoreCode(busData00.getStoreCode());
        }else if(codeRuleInfo!=null && "00".equals(codeRuleInfo.getStatus())){
            SCode=gConfigIService.GetCreateSC(codeRuleInfo);
            storeInfo.setStoreCode(SCode);
        }else{
            throw new SystemException("", "请输入{仓库代码}，或请到全局配置进行代码生成配置");
        }

        storeInfo.setdSource("01");
        storeInfo.setStatus(busData00.getStatus());
        storeInfo.setF1(busData00.getF1());
        storeInfo.setF2(busData00.getF2());
        storeInfo.setF3(busData00.getF3());
        storeInfo.setF4(busData00.getF4());

        //新增权限信息
        for (SaveStoreInfoReqBD00PMInfo PMInfo : busData00.getPMInfo()) {
            StorePMInfo storePMInfo = new StorePMInfo();

            storePMInfo.setGuid(CommonUtils.getRandomNumber());
            storePMInfo.setStoreGd(rand);
            if ("00".equals(PMInfo.getPMorType())) {
                UserInfo userInfo = userDAO.SelectUserInfoByruid(PMInfo.getPMorRd());
                storePMInfo.setpMorGd(userInfo.getGuid());
            }
            if ("01".equals(PMInfo.getPMorType())) {
                RoleInfo roleInfo = roleDAO.SelectRoleInfoByruid(PMInfo.getPMorRd());
                storePMInfo.setpMorGd(roleInfo.getGuid());
            }
            storePMInfo.setpMType(PMInfo.getPMorType());
            storePMInfo.setCreator(CommonUtils.readUser().getRealName());
            storePMInfo.setCreateTime(new Date());
            storePMDAO.InsertStorePMInfo(storePMInfo);
        }

        storeInfo.setCreator(CommonUtils.readUser().getRealName());
        storeInfo.setCreateTime(new Date());
        storeInfo.setRemark(busData00.getRemark());

        // 保存一条仓库信息
        int count = storeDAO.InsertStoreInfo(storeInfo);
        if (count <= 0) throw new SystemException("MG_MES80010140000F", "新增仓库信息失败！");


        StoreInfo storeInfos=storeDAO.SelectByguid(storeInfo.getGuid());
        data.setStoreRd(storeInfos.getRuid());
        data.setStoreCode(storeInfos.getStoreCode());

        body.setData(data);
        saveStoreInfoRes.setBody(body);

        return saveStoreInfoRes;
    }

    //更新仓库信息
    public SaveStoreInfoRes ModupdateStoreInfo(SaveStoreInfoReqBD02 busData02, StoreInfo storeInfo) throws SystemException {
        SaveStoreInfoRes saveStoreInfoRes = new SaveStoreInfoRes();

        SaveStoreInfoResB body = new SaveStoreInfoResB();

        SaveStoreInfoResD data = new SaveStoreInfoResD();

        //查询仓库信息
        StoreInfo objEStoreInfo = storeDAO.SelectByRuid(busData02.getStoreRd());

        if (objEStoreInfo == null) {
            throw new SystemException("MG_MES8001010010001F", "查询仓库列表信息为空！");
        }

        //查询仓库名称
        StoreInfo objeStoreInfo = storeDAO.SelectBystoreName(busData02.getStoreName());

        if (objeStoreInfo != null && !objeStoreInfo.getStoreName().equals(objEStoreInfo.getStoreName())) {
            throw new SystemException("MG0006F", "更新失败，仓库名称已存在");
        }

        if ("00".equals(objEStoreInfo.getdSource())) {
            throw new SystemException("", "外部数据不能更新");
        }
        // 赋值
        storeInfo.setRuid(busData02.getStoreRd());
        storeInfo.setStoreName(busData02.getStoreName());
        if (!busData02.getStoreType().equals("")) {
            storeInfo.setStoreType(busData02.getStoreType());
        } else {
            storeInfo.setStoreType("");
        }
        storeInfo.setStatus(busData02.getStatus());
        storeInfo.setF1(busData02.getF1());
        storeInfo.setF2(busData02.getF2());
        storeInfo.setF3(busData02.getF3());
        storeInfo.setF4(busData02.getF4());

        //更新权限信息
        for (SaveStoreInfoReqBD02PMInfo PMInfo : busData02.getPMInfo()) {
            StorePMInfo storePMInfo = new StorePMInfo();
            StorePMInfo objEStorePMInfos=storePMDAO.SelectStorePMInfoRuid(PMInfo.getPMorRd());
            if(objEStorePMInfos==null) {
                storePMInfo.setGuid(CommonUtils.getRandomNumber());
                storePMInfo.setStoreGd(objEStoreInfo.getGuid());
                if ("00".equals(PMInfo.getPMorType())) {
                    UserInfo userInfo = userDAO.SelectUserInfoByruid(PMInfo.getPMorRd());
                    if (userInfo != null) {
                        storePMInfo.setpMorGd(userInfo.getGuid());
                    }
                }
                if ("01".equals(PMInfo.getPMorType())) {
                    RoleInfo roleInfo = roleDAO.SelectRoleInfoByruid(PMInfo.getPMorRd());
                    if (roleInfo != null) {
                        storePMInfo.setpMorGd(roleInfo.getGuid());
                    }
                }
                storePMInfo.setpMType(PMInfo.getPMorType());
                storePMInfo.setCreator(CommonUtils.readUser().getRealName());
                storePMInfo.setCreateTime(new Date());
                storePMDAO.InsertStorePMInfo(storePMInfo);
            }else{
                storePMInfo.setRuid(PMInfo.getPMorRd());
                storePMInfo.setpMType(PMInfo.getPMorType());
                storePMInfo.setCreator(CommonUtils.readUser().getRealName());
                storePMInfo.setCreateTime(new Date());
                storePMDAO.UpdateStorePMInfo(storePMInfo);
            }

        }

        storeInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        storeInfo.setLastModifyTime(new Date());
        storeInfo.setRemark(busData02.getRemark());

        // 更新
        int count = storeDAO.UpdateStoreInfo(storeInfo);

        if (count <= 0) throw new SystemException("MG_MES80010140002F", "更新仓库信息失败！");

        body.setData(data);
        saveStoreInfoRes.setBody(body);

        return saveStoreInfoRes;
    }

    //删除仓库信息
    public SaveStoreInfoRes RmdeleteStoreInfo(int ruid) throws SystemException {
        SaveStoreInfoRes saveStoreInfoRes = new SaveStoreInfoRes();

        SaveStoreInfoResB body = new SaveStoreInfoResB();

        SaveStoreInfoResD data = new SaveStoreInfoResD();

        //查询仓库信息
        StoreInfo objEStoreInfo = storeDAO.SelectByRuid(ruid);

        if (objEStoreInfo == null) {
            throw new SystemException("MG_MES8001010010001F", "查询仓库信息为空！");
        }

        if ("00".equals(objEStoreInfo.getdSource())) {
            throw new SystemException("", "外部数据不能删除");
        }

        List<StorePMInfo> objEStorePMInfo = storePMDAO.SelectStorePMInfostoreGd(objEStoreInfo.getGuid());

        if (objEStorePMInfo == null) {
            throw new SystemException("MG_MES8001011010001F", "查询仓库权限信息为空！");
        }
        for (StorePMInfo obj1 : objEStorePMInfo) {
            storePMDAO.DeleteStorePMInfo(obj1.getStoreGd());
        }

        //查询库位信息
        List<LocationInfo> objELocationInfo = locationDAO.Selectguid(objEStoreInfo.getGuid());

        if (objELocationInfo == null) {
            throw new SystemException("MG_MES8001011010001F", "查询库位列表信息为空！");
        }

        //循环删除仓库下的库位
        for (LocationInfo obj : objELocationInfo) {
            locationDAO.DeleteStoreGd(obj.getStoreGd());
        }

        // 保存
        int count = storeDAO.DeleteStoreInfo(ruid);

        if (count <= 0) throw new SystemException("MG_MES80010140001F", "删除仓库信息失败！");

        body.setData(data);
        saveStoreInfoRes.setBody(body);

        return saveStoreInfoRes;
    }
}
