package pnc.mesadmin.service;

import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllSpecInfo.GetAllSpecInfoRes;
import pnc.mesadmin.dto.GetAllSpecInfo.GetAllSpecInfoResD;
import pnc.mesadmin.dto.GetAllUnitInfo.GetAllUnitInfoResD;
import pnc.mesadmin.dto.GetSVInfo.GetSVInfoReq00;
import pnc.mesadmin.dto.GetSVInfo.GetSVInfoReq01;
import pnc.mesadmin.dto.GetSVInfo.GetSVInfoRes;
import pnc.mesadmin.dto.GetSVTreeInfo.GetSVTreeInfoReq00;
import pnc.mesadmin.dto.GetSVTreeInfo.GetSVTreeInfoRes;
import pnc.mesadmin.dto.SaveSpecInfo.*;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工序信息Servcie
 * 创建人：张亮亮
 * 创建时间：2017-06-02
 * 修改人：
 * 修改时间：
 */
public interface SpecIService {
    //dto 获取工序列表信息
    GetAllSpecInfoRes QALLGetAllSpecInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    /**
     * dto 获取工序列表信息(新)
     * @param req
     * @return
     */
    PageResult<GetAllSpecInfoResD> QALLGetAllSpecNewRes(BaseRequest req);

    //dto查询工序信息根据工序ID
    GetSVInfoRes GetGetSVInfoRes(GetSVInfoReq00 argGetSVInfoReq00);

    //dto查询工序信息根据工序版本ID
    GetSVInfoRes GetGetSVInfoRes1(GetSVInfoReq01 argGetSVInfoReq01);

    //dto增加工序信息
    SaveSpecInfoRes AddSaveSpecInfoRes(SaveSpecInfoReqBD00 argSaveSpecInfoReqBD00);

    //dto删除工序信息
    SaveSpecInfoRes RmSaveSpecInfoRes(SaveSpecInfoReqBD01 argSaveSpecInfoReqBD01);

    //dto更新工序信息
    SaveSpecInfoRes ModSaveSpecInfoRes(SaveSpecInfoReqBD02 argSaveSpecInfoReqBD02) throws SystemException;

    //dto复制工序信息
    SaveSpecInfoRes AddSaveSpecInfoRe(SaveSpecInfoReqBD03 argSaveSpecInfoReqBD03);

    //dto新增工序版本信息
    SaveSpecInfoRes AddSaveSpecInfoReq04(SaveSpecInfoReqBD04 argSaveSpecInfoReqBD04);

    //dto删除工序版本信息
    SaveSpecInfoRes RmSaveSpecInfoReq05(SaveSpecInfoReqBD05 argSaveSpecInfoReqBD05);

    //获取工序版本列表信息
    GetSVTreeInfoRes GetGetSVTreeInfoRes(GetSVTreeInfoReq00 argGetSVTreeInfoReq00);
}
