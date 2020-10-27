package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.GetAllCarrierFamilyInfo.GetAllCarrierFamilyInfoRes;
import pnc.mesadmin.dto.GetAllSpecInfo.GetAllSpecInfoResD;
import pnc.mesadmin.dto.GetAllToolFamilyInfo.GetAllToolFamilyInfoRes;
import pnc.mesadmin.dto.GetCarrierFamilyInfo.GetCarrierFamilyInfoReqBD00;
import pnc.mesadmin.dto.GetCarrierFamilyInfo.GetCarrierFamilyInfoRes;
import pnc.mesadmin.dto.GetToolFamilyInfo.GetToolFamilyInfoReqBD00;
import pnc.mesadmin.dto.GetToolFamilyInfo.GetToolFamilyInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.PageResult;
import pnc.mesadmin.dto.SaveCarrierFamilyInfo.*;
import pnc.mesadmin.dto.SaveToolFamilyInfo.*;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：载具家族信息Service接口
 * 创建人：郝赞
 * 创建时间：2018-6-15
 * 修改人：
 * 修改时间：
 */
public interface CarrierFamilyIService {
    //dto查询载具家族信息列表
    GetAllCarrierFamilyInfoRes QALLGetAllFaInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    /**
     * dto 获取载具家族列表信息(新)
     * @param req
     * @return
     */
    PageResult<GetAllSpecInfoResD> QALLGetAllCarrierFamilyNewRes(BaseRequest req);

    //dto查询载具家族信息，根据(载具家族)
    GetCarrierFamilyInfoRes GetGetCarrierFamilyInfoRes(GetCarrierFamilyInfoReqBD00 argGetCarrierFamilyInfoReqBD00);


    //dto新增载具家族信息
    SaveCarrierFamilyInfoRes AddGetCarrierFamilyInfoRes(SaveCarrierFamilyInfoReqBD00 argSaveCarrierFamilyInfoReqBD00);

    //dto删除载具家族信息 根据传过来的载具家族id
    SaveCarrierFamilyInfoRes RmSaveCarrierFamilyInfoRes(SaveCarrierFamilyInfoReqBD01 argSaveCarrierFamilyInfoReqBD01);

    //tdo更新载具家族信息
    SaveCarrierFamilyInfoRes ModSaveCarrierFamilyInfoRes(SaveCarrierFamilyInfoReqBD02 argSaveCarrierFamilyInfoReqBD02);

    //复制更新载具家族信息
    SaveCarrierFamilyInfoRes AddSaveCarrierFamilyInfoRes(SaveCarrierFamilyInfoReqBD03 argSaveCarrierFamilyInfoReqBD03);

}
