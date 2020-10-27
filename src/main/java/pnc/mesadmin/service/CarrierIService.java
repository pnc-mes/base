package pnc.mesadmin.service;


import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.GetAllCaeeierInfo.GetAllCarrierInfoRes;
import pnc.mesadmin.dto.GetAllCaeeierInfo.GetAllCarrierInfoResD;
import pnc.mesadmin.dto.GetCarrierInfo.GetCarrierInfoReqBD00;
import pnc.mesadmin.dto.GetCarrierInfo.GetCarrierInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.PageResult;
import pnc.mesadmin.dto.SaveCarrierInfo.*;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：载具信息Service层接口
 * 创建人：郝赞
 * 创建时间：20178-6-13
 * 修改人：
 * 修改时间：
 */
public interface CarrierIService {

    //dto查询载具信息列表
    GetAllCarrierInfoRes QALLGetAllCarrierInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    /**
     * dto 查询载具信息列表(新)
     * @param req
     * @return
     */
    PageResult<GetAllCarrierInfoResD> QALLGetAllCarriersNewRes(BaseRequest req);

    //dto查询载具信息，根据ToolRd(工具ID)
    GetCarrierInfoRes GetGetCarrierInfoRes(GetCarrierInfoReqBD00 argGetCarrierInfoReqBD00);


    //dto新增载具信息
    SaveCarrierInfoRes AddGetCarrierInfoRes(SaveCarrierInfoReqBD00 argSaveCarrierInfoReqBD00);

    //dto删除载具信息 根据传过来的载具id
    SaveCarrierInfoRes RmSaveCarrierInfoRes(SaveCarrierInfoReqBD01 argSaveCarrierInfoReqBD01);

    //tdo更新载具信息
    SaveCarrierInfoRes ModSaveCarrierInfoRes(SaveCarrierInfoReqBD02 argSaveCarrierInfoReqBD02);

    //复制更新载具信息
    SaveCarrierInfoRes AddSaveCarrierInfoRes(SaveCarrierInfoReqBD03 argSaveCarrierInfoReqBD03);

}
