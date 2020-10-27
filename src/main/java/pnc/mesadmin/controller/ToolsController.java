package pnc.mesadmin.controller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import pnc.mesadmin.dto.SaveImportInfo.SaveImportInfo;
import pnc.mesadmin.dto.SaveImportInfo.SaveImportInfoReqBD00;
import pnc.mesadmin.dto.SaveImportInfo.SaveImportInfoRes;
import pnc.mesadmin.dto.SaveImportInfo.SaveImportInfoResB;
import pnc.mesadmin.dto.SaveReq;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.service.ToolsIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：导入库存信息Controller
 * 创建人：刘福志
 * 创建时间：2017-9-2
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/Tools")
public class ToolsController {
    @Resource
    private ToolsIService toolsIService;

    //获取导入库存页面
    @RequestMapping(value = "/ImportPG")
    public String getCustomerPGPage() {

        return "currency/import";
    }

    //保存导入库存信息
    @RequestMapping(value = "/SaveImportInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveImportInfoRes SaveImportInfo(HttpServletRequest request, SaveReq saveReq) throws IOException {

        // 创建实体对象
        SaveImportInfoRes saveImportInfoRes = new SaveImportInfoRes();

        // 转换成JsonObject实体类
        String rowData = saveReq.getBusData();

        // 新增
        if ("00".equals(saveReq.getExecType())) {
            // JsonObject转换成实体类
            SaveImportInfoReqBD00[] busData00 = CommonUtils.switchClass(SaveImportInfoReqBD00[].class, rowData);

            // 直接可以获取的表单数据
            try {
                saveImportInfoRes = toolsIService.AddSaveImportInfoRes(busData00);
                saveImportInfoRes.getBody().setMsgCode("0x00000");
                saveImportInfoRes.getBody().setMsgDes("成功！");
            } catch (SystemException e) {
                SaveImportInfoResB saveImportInfoResB = new SaveImportInfoResB();
                saveImportInfoResB.setMsgCode(e.getMsgCode());
                saveImportInfoResB.setMsgDes(e.getMsgDes());
                saveImportInfoRes.setBody(saveImportInfoResB);
            }
        } else {
            SaveImportInfoResB saveImportInfoResB = new SaveImportInfoResB();
            saveImportInfoResB.setMsgCode("MG0006F");
            saveImportInfoResB.setMsgDes("参数名" + saveReq.getExecType() + "中值应该等于00");
            saveImportInfoRes.setBody(saveImportInfoResB);
        }

        saveImportInfoRes.setStatus("00");
        return saveImportInfoRes;
    }

    //文件解析
    @RequestMapping(value = "/readExecl", method = RequestMethod.POST)
    @ResponseBody
    public List<SaveImportInfo> readExecl(HttpServletRequest request, @RequestParam("upload") CommonsMultipartFile file) throws IOException {

        XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = wb.getSheetAt(0);

        List<SaveImportInfo> bd00s = new ArrayList<SaveImportInfo>();
        SaveImportInfo bd00 = null;
        List<String> str = null;

        Iterator<Row> rows = sheet.rowIterator();
        while (rows.hasNext()) {
            Row row = (Row) rows.next();
            if (row.getRowNum() == 0) {
                continue;
            }

            str = new ArrayList<String>();
            bd00 = new SaveImportInfo();

            Iterator<Cell> cells = row.cellIterator();
            for (int i = 0; i < 6; i++) {

                Cell cell = (Cell) row.getCell(i);

                if (cell != null) {
                    if(i != 5){
                        cell.setCellType(1);
                    }

                    if (cell.getCellType() == 0) {
                        str.add(String.valueOf(cell.getNumericCellValue()));
                    } else if (cell.getCellType() == 1) {
                        str.add(cell.getStringCellValue());
                    }
                } else {
                    str.add("");
                }

            }

            bd00.setMaCode(str.get(0));
            bd00.setMaName(str.get(1));
            bd00.setStoreName(str.get(2));
            bd00.setLCode(str.get(3));
            bd00.setBatch(str.get(4));
            bd00.setNum(str.get(5));

            bd00s.add(bd00);
        }

        return bd00s;
    }

}
