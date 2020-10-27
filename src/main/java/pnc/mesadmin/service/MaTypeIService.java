package pnc.mesadmin.service;

import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllMTInfo.GetAllMTInfoRes;
import pnc.mesadmin.dto.GetAllMTInfo.GetAllMTInfoResD;
import pnc.mesadmin.dto.GetAllMTInfo.GetAllMTInfoResDCMTInfo;
import pnc.mesadmin.dto.GetMTInfo.GetMTInfoRes;
import pnc.mesadmin.dto.SaveMTInfo.SaveMTInfoReqBD00;
import pnc.mesadmin.dto.SaveMTInfo.SaveMTInfoReqBD02;
import pnc.mesadmin.dto.SaveMTInfo.SaveMTInfoRes;
import pnc.mesadmin.entity.MaTypeInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：物料类别信息Service接口
 * 创建人：刘福志
 * 创建时间：2017-8-21
 * 修改人：
 * 修改时间：
 */
public interface MaTypeIService {
    //查询物料类别列表信息
    GetAllMTInfoRes QALLselectAllMaTypeInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException;

    //查询物料类别信息
    GetMTInfoRes GetselectMaTypeInfo(int mTRd) throws SystemException;

    //保存物料类别信息
    SaveMTInfoRes AddinsertMaTypeInfo(SaveMTInfoReqBD00 busData00, MaTypeInfo maTypeInfo) throws SystemException;

    //更新物料类别信息
    SaveMTInfoRes ModupdateMaTypeInfo(SaveMTInfoReqBD02 busData02, MaTypeInfo maTypeInfo) throws SystemException;

    //删除物料类别信息
    SaveMTInfoRes RmdeleteMaTypeInfo(int ruid) throws SystemException;

    /**
     * 查询物料类别列表信息(新)
     * @param req
     * @return
     */
    PageResult<GetAllMTInfoResDCMTInfo> QALLMaTypeNew(BaseRequest req);
}
