package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetAllRKInfo.GetAllRKInfoReqBD00;
import pnc.mesadmin.dto.GetAllRKInfo.GetAllRKInfoRes;
import pnc.mesadmin.dto.GetRKBarInfo.GetRKBarInfoReqBD00;
import pnc.mesadmin.dto.GetRKBarInfo.GetRKBarInfoRes;
import pnc.mesadmin.dto.GetRKDlInfo.GetRKDlInfoRes;
import pnc.mesadmin.dto.GetRKDlInfo.GetRKDlInfoReqBD00;
import pnc.mesadmin.dto.GetRKInfo.GetRKInfoReqBD00;
import pnc.mesadmin.dto.GetRKInfo.GetRKInfoRes;
import pnc.mesadmin.dto.GetRKTInfo.GetRKTInfoRes;
import pnc.mesadmin.dto.SaveRKInfo.*;

import java.io.ByteArrayOutputStream;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：入库单Service
 * 创建人：张亮亮
 * 创建时间：2017-06-10
 * 修改人：
 * 修改时间：
 */
public interface RkIService {
    //dto获取入库单类型
    GetRKTInfoRes QALLGetRKTInfoRes();
    //dto获取入库单信息 根据入库类型
    GetAllRKInfoRes GetGetAllRKInfoRes(GetAllRKInfoReqBD00 argGetAllRKInfoReqBD00);
    //dto获取入库单信息
    GetRKInfoRes GetGetRKInfoRes(GetRKInfoReqBD00 argGetRKInfoReqBD00);
    //dto 获取入库单明细信息
    GetRKDlInfoRes GetGetRKDlInfoRes(GetRKDlInfoReqBD00 argGetRKDlInfoReqBD00);

    //导出入库单明细数据
    ByteArrayOutputStream ExportGetRKInfo(GetRKDlInfoReqBD00[] argGetRKDlInfoReqBD00);

    //dto 获取入库单条码信息
    GetRKBarInfoRes GetGetRKBarInfoRes(GetRKBarInfoReqBD00 argGetRKBarInfoReqBD00);
    //dto新增入库单信息
  //  SaveRKInfoRes AddSaveRKInfoRes(SaveRKInfoReqBD00 argSaveRKInfoReqBD00);
    //dto删除入库信息
    SaveRKInfoRes RmSaveRKInfoRes(SaveRKInfoReqBD01 argSaveRKInfoReqBD01);
    //dto取消入库信息
    SaveRKInfoRes MoSaveRKInfoRes(SaveRKInfoReqBD03 argSaveRKInfoReqBD03);
    //dto更新入库单信息
    SaveRKInfoRes MoSaveRKInfoRess(SaveRKInfoReqBD02 argSaveRKInfoReqBD02);

}
