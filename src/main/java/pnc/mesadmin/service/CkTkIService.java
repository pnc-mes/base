package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetAllCKInfo.GetAllCKInfoReq00;
import pnc.mesadmin.dto.GetAllCKInfo.GetAllCKInfoRes;
import pnc.mesadmin.dto.GetCKBarInfo.GetCKBarInfoReq00;
import pnc.mesadmin.dto.GetCKBarInfo.GetCKBarInfoRes;
import pnc.mesadmin.dto.GetCKDlInfo.GetCKDlInfoReq00;
import pnc.mesadmin.dto.GetCKDlInfo.GetCKDlInfoRes;
import pnc.mesadmin.dto.GetCKTInfo.GetCKTInfoRes;
import pnc.mesadmin.dto.GetMaTotalInfo.GetMaTotalInfoReq00;
import pnc.mesadmin.dto.GetMaTotalInfo.GetMaTotalInfoRes;
import pnc.mesadmin.dto.SaveCKInfo.SaveCKInfoReq01;
import pnc.mesadmin.dto.SaveCKInfo.SaveCKInfoReq03;
import pnc.mesadmin.dto.SaveCKInfo.SaveCKInfoRes;
import pnc.mesadmin.dto.SavePickInfo.SavePickInfoReq00;
import pnc.mesadmin.dto.SavePickInfo.SavePickInfoRes;

import java.io.ByteArrayOutputStream;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：出库信息Service层类
 * 创建人：张亮亮
 * 创建时间：2017-8-7
 * 修改人：
 * 修改时间：
 */
public interface CkTkIService {
    //获取用料汇总信息
    GetMaTotalInfoRes GetGetMaTotalInfoRes(GetMaTotalInfoReq00 argGetMaTotalInfoReq00);

    //保存领料单信息
    SavePickInfoRes AddSavePickInfoRes(SavePickInfoReq00 argSavePickInfoReq00);

    //获取出库的类型
    GetCKTInfoRes GetGetCKTInfoRes();

    //获取出库单列表信息
    GetAllCKInfoRes GetGetAllCKInfoRes(GetAllCKInfoReq00 argGetAllCKInfoReq00);

    //获取出库单明细信息
    GetCKDlInfoRes GetGetCKDlInfoRes(GetCKDlInfoReq00 argGetCKDlInfoRes);

    //导出一条出库数据
    ByteArrayOutputStream ExportGetCKDlInfo(GetCKDlInfoReq00[] argGetCKDlInfoRes);

    //导出所有出库数据
    ByteArrayOutputStream  ExportGetCKDlInfo1(GetAllCKInfoReq00 argGetAllCKInfoReq00);

    //获取扫描出库信息
    GetCKBarInfoRes GetGetCKBarInfoRes(GetCKBarInfoReq00 argGetCKBarInfoRes);

    //取消出库信息
    SaveCKInfoRes GetSaveCKInfoRes(SaveCKInfoReq03 argSaveCKInfoReq03);

    //删除出库单信息
    SaveCKInfoRes RmSaveCKInfoRes(SaveCKInfoReq01 argSaveCKInfoReq01);
}
