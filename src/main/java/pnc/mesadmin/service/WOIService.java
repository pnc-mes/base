package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.GetAllWOBInfo.GetAllWOBInfoReqBD00;
import pnc.mesadmin.dto.GetAllWOBInfo.GetAllWOBInfoReqBD01;
import pnc.mesadmin.dto.GetAllWOBInfo.GetAllWOBInfoReqBD03;
import pnc.mesadmin.dto.GetAllWOBInfo.GetAllWOBInfoRes;
import pnc.mesadmin.dto.GetAllWOInfo.GetAllWOInfoRes;
import pnc.mesadmin.dto.GetWOInfo.GetWOInfoReqBD00;
import pnc.mesadmin.dto.GetWOInfo.GetWOInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.PageResult;
import pnc.mesadmin.dto.SaveWOInfo.*;
import pnc.mesadmin.dto.MBaseDto.MBaseRes;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工单Service
 * 创建人：张亮亮
 * 创建时间：2017-06-08
 * 修改人：
 * 修改时间：
 */
public interface WOIService {

    //dto查询工单信息列表
    PageResult<GetAllWOInfoRes> QALLGetAllNewWOInfoRes(BaseRequest req);

    //dto查询工单信息列表
    GetAllWOInfoRes QALLGetAllWOInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //dto查询工单关联线体列表信息
    BaseRes QALLGetAllWoLineALl(List<InitDataField> argInitDataFields, PageInfo argPageInfo);
    //dto查询批次信息，根据工单id
    GetAllWOBInfoRes GetGetAllWOBInfoRes(GetAllWOBInfoReqBD00 argGetAllWOBInfoReqBD00);
    //dto查询工单信息 根据工单id,自己的id
    GetWOInfoRes GetGetWOInfoRes(GetWOInfoReqBD00 argGetWOInfoReqBD00);
    //dto 新增工单信息
    SaveWOInfoRes AddSaveWOInfoRes(SaveWOInfoReqBD00 argSaveWOInfoReqBD00);
    //dto删除工单信息
    SaveWOInfoRes RmSaveWOInfoRes(SaveWOInfoReqBD01 argSaveWOInfoReqBD01);
    //dto修改工单信息
    SaveWOInfoRes ModSaveWOInfoRes(SaveWOInfoReqBD02 argSaveWOInfoReqBD02);
    //tdo根据批次id查询批次信息
    GetAllWOBInfoRes GetGetAllWOBInfoReqBD01(GetAllWOBInfoReqBD01 argGetAllWOBInfoReqBD01);
    //根据线体查询工单信息
    MBaseRes GetAllWoinfoByLineRd(GetAllWOBInfoReqBD03 request);

    //下达
    SaveWOInfoRes ModSaveWO(SaveWOInfoReqBD03 argBD03);

    //取消
    SaveWOInfoRes ModSaveWO(SaveWOInfoReqBD04 argBD04);

    //冻结
    SaveWOInfoRes ModSaveWO(SaveWOInfoReqBD05 argBD05);

    //解冻
    SaveWOInfoRes ModSaveWO(SaveWOInfoReqBD06 argBD06);

    //终止
    SaveWOInfoRes ModSaveWO(SaveWOInfoReqBD07 argBD07);

    //开始
    SaveWOInfoRes ModSaveWO(SaveWOInfoReqBD08 argBD08);

    //关闭
    SaveWOInfoRes ModSaveWO(SaveWOInfoReqBD09 argBD09);
}
