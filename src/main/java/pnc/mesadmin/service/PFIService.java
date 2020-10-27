package pnc.mesadmin.service;

import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllPdfInfo.GetAllPdfInfoRes;
import pnc.mesadmin.dto.GetAllPdfInfo.GetAllPdfInfoResD;
import pnc.mesadmin.dto.GetPdfInfo.GetPdfInfoReqBD00;
import pnc.mesadmin.dto.GetPdfInfo.GetPdfInfoRes;
import pnc.mesadmin.dto.SavePdfInfo.*;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：产品家族信息业务信息逻辑
 * 创建人：潘俊峰
 * 创建时间：2017-05-26
 * 修改人：
 * 修改时间：
 */
public interface PFIService {

    //获取产品族列表信息
    GetAllPdfInfoRes QALLPdFamilyInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException;

    //获取产品族信息
    GetPdfInfoRes GetPdFamilyInfo(GetPdfInfoReqBD00 argBD00) throws SystemException;

    //新增产品族信息
    SavePdfInfoRes AddPdFamilyInfo(SavePdfInfoReqBD00 argBD00) throws SystemException;

    //删除产品族信息
    SavePdfInfoRes RmPdFamilyInfo(SavePdfInfoReqBD01 argBD01) throws SystemException;

    //编辑产品族信息
    SavePdfInfoRes ModPdFamilyInfo(SavePdfInfoReqBD02 argBD02) throws SystemException;

    SavePdfInfoRes AddCopyPdFamilyInfo(SavePdfInfoReqBD03 argBD03) throws SystemException;

    /**
     * 获取产品族列表信息(新)
     * @param req
     * @return
     */
    PageResult<GetAllPdfInfoResD> QALLPdFamilyNew(BaseRequest req);
}
