package pnc.mesadmin.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
import pnc.mesadmin.dto.GetAllNPickInfo.GetAllNPickInfoRes;
import pnc.mesadmin.dto.GetAllNPickInfo.GetAllNPickInfoResD;
import pnc.mesadmin.dto.GetAllPickInfo.GetAllPickInfoRes;
import pnc.mesadmin.dto.GetNMaTotalInfo.GetNMaTotalInfoReq00;
import pnc.mesadmin.dto.GetNMaTotalInfo.GetNMaTotalInfoRes;
import pnc.mesadmin.dto.GetNPickInfo.GetNPickInfoReq00;
import pnc.mesadmin.dto.GetNPickInfo.GetNPickInfoRes;
import pnc.mesadmin.dto.GetPickInfo.GetPickInfoReq00;
import pnc.mesadmin.dto.GetPickInfo.GetPickInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveImportRes.SaveImportResB;
import pnc.mesadmin.dto.SaveNPickInfo.*;
import pnc.mesadmin.dto.SavePickInfo.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by PNC on 2017/9/13.
 */
public interface PickIService {
    //获取领料单列表信息
    GetAllPickInfoRes QALLGetAllPickInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //获取领料单信息
    GetPickInfoRes GetGetPickInfoRes(GetPickInfoReq00 argGetPickInfoReq00);

    //删除领料出库
    SavePickInfoRes RmSavePickInfoRes(SavePickInfoReq01 argSavePickInfoReq01);

    //更新领料单信息
    SavePickInfoRes ModSavePickInfoRes(SavePickInfoReq02 argSavePickInfoReq02);

    //获取领料单列表信息 N
    GetAllNPickInfoRes QALLGetAllNPickInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //筛选无工单领料
    GetAllNPickInfoRes QALLGetAllNPickInfoRes1(GetAllNPickInfoResD getAllNPickInfoResD, PageInfo argPageInfo);

    //获取领料单信息 N
    GetNPickInfoRes GetGetNPickInfoRes(GetNPickInfoReq00 argGetNPickInfoReq00);

    //汇总信息 N
    GetNMaTotalInfoRes QALLGetNMaTotalInfoRes(GetNMaTotalInfoReq00 argGetNMaTotalInfoReq00);

    //新增领料单信息 N
    SaveNPickInfoRes AddSaveNPickInfoRes(SaveNPickInfoReq00 argSaveNPickInfoReq00);

    //删除 N
    SaveNPickInfoRes RmSaveNPickInfoRes(SaveNPickInfoReq01 argSaveNPickInfoReq01);

    //修改
    SaveNPickInfoRes ModSaveNPickInfoRes(SaveNPickInfoReq02 argSaveNPickInfoReq02);

    SaveImportResB AddImportPick(CommonsMultipartFile file) throws IOException;

    SaveImportResB AddImportnPick(CommonsMultipartFile file) throws IOException;

    //领料单导出
    ByteArrayOutputStream exportPickExcel(Integer busData);

    //导出下载推荐库位单
    ByteArrayOutputStream ExportPickExcel(Integer busData, HttpServletResponse response);

    //导出备货清单
    ByteArrayOutputStream exportnPickExcel(Integer busData);

    //导出领料确认单
    ByteArrayOutputStream exportnPickExcel1(Integer busData) throws IOException;

    //导出下载推荐库位单 N
    ByteArrayOutputStream ExportnPickExcel(Integer busData);

    //下达 N
    SaveNPickInfoRes GetSaveNPickInfoReq03(SaveNPickInfoReq03 argSaveNPickInfoReq03);

    //取消 N  这边用同一个实体类
    SaveNPickInfoRes GetCancelSaveNPickInfoReq03(SaveNPickInfoReq03 argSaveNPickInfoReq03);

    //下达
    SavePickInfoRes ModPickInfoOn(SavePickInfoReq03 argBD03);

    //取消
    SavePickInfoRes ModPickInfoOff(SavePickInfoReq04 argBD04);
}
