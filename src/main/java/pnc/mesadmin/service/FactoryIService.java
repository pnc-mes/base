package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.GetAllFaInfo.GetAllFaInfoRes;
import pnc.mesadmin.dto.GetAllFaInfo.GetAllFaInfoResD;
import pnc.mesadmin.dto.GetFaInfo.GetFaInfoReqBD00;
import pnc.mesadmin.dto.GetFaInfo.GetFaInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.PageResult;
import pnc.mesadmin.dto.SaveFaInfo.*;
import pnc.mesadmin.utils.BaseResponse;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工厂信息Service层接口
 * 创建人：张亮亮
 * 创建时间：2017-5-27
 * 修改人：
 * 修改时间：
 */
public interface FactoryIService {
    //dto查询工厂信息列表
    GetAllFaInfoRes QALLGetAllFaInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    /**
     * 查询工厂信息列表(新)
     * @param res
     * @return
     */
    PageResult<GetAllFaInfoResD> QALLGetAllFaNewRes(BaseRequest res);

    BaseResponse GetAllFaInfoV2(SaveFaInfoReqBD00 reqBD00);

    //dto查询工厂信息，根据FaRd(工厂ID)
    GetFaInfoRes GetGetFaInfoRes(GetFaInfoReqBD00 argGetFaInfoReqBD00);

    //dto新增工程信息
    SaveFaInfoRes AddGetFaInfoRes(SaveFaInfoReqBD00 argSaveFaInfoReqBD00);

    //dto删除工厂信息 根据传过来的工厂id
    SaveFaInfoRes RmSaveFaInfoRes(SaveFaInfoReqBD01 argSaveFaInfoReqBD01);

    //tdo更新工厂信息
    SaveFaInfoRes ModSaveFaInfoRes(SaveFaInfoReqBD02 argSaveFaInfoReqBD02);

    //复制更新工厂信息
    SaveFaInfoRes AddSaveFaInfoRes(SaveFaInfoReqBD03 argSaveFaInfoReqBD03);
}
