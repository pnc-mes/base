package pnc.mesadmin.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllBHChgInfo.GetAllBHChgInfoResB;
import pnc.mesadmin.dto.GetAllBOMInfo.GetAllBOMInfoRes;
import pnc.mesadmin.dto.GetAllBOMInfo.GetAllBOMInfoResD;
import pnc.mesadmin.dto.GetAllBOMInfo.GetAllBomRes;
import pnc.mesadmin.dto.GetBHChgDtlInfo.GetBHChgDtlInfoResB;
import pnc.mesadmin.dto.GetBOMInfo.GetBOMInfoReqBD00;
import pnc.mesadmin.dto.GetBOMInfo.GetBOMInfoRes;
import pnc.mesadmin.dto.GetBOMTreeInfo.GetBOMTreeInfoReqBD00;
import pnc.mesadmin.dto.GetBOMTreeInfo.GetBOMTreeInfoRes;
import pnc.mesadmin.dto.GetBVInfo.GetBVInfoReqBD00;
import pnc.mesadmin.dto.GetBVInfo.GetBVInfoReqBD01;
import pnc.mesadmin.dto.GetBVInfo.GetBVInfoRes;
import pnc.mesadmin.dto.SaveBomInfo.SaveBomInfoReqBD00;
import pnc.mesadmin.dto.SaveBomInfo.SaveBomInfoReqBD03;
import pnc.mesadmin.dto.SaveBomInfo.SaveBomInfoRes;
import pnc.mesadmin.dto.SaveImportRes.SaveImportResB;
import pnc.mesadmin.utils.BaseResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：BOM管理业务信息逻辑
 * 创建人：潘俊峰
 * 创建时间：2017-06-08
 * 修改人：
 * 修改时间：
 */
public interface BOMIService {

    /**
     * 获取BOM列表信息
     * @param argInitDataFields
     * @param argPageInfo
     * @return
     * @throws SystemException
     */
    GetAllBOMInfoRes QALLBOMInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException;

    /**
     * 获取BOM列表信息_V2
     * @param
     * @return
     * @throws
     */
    BaseResponse GetAllBOMInfo_V2(GetAllBomRes request);

    /**
     * 获取BOM列表信息(新)
     * @param req
     * @return
     */
    PageResult<GetAllBOMInfoResD> QALLBOMNew(BaseRequest req);

    /**
     * 获取BOM信息
     * @param argBD00
     * @return
     * @throws SystemException
     */
    GetBOMInfoRes GetBOMInfo(GetBOMInfoReqBD00 argBD00) throws SystemException;

    /**
     * 获取BOM版本列表信息
     * @param argBD00
     * @return
     * @throws SystemException
     */
    GetBOMTreeInfoRes GetBOMTreeInfo(GetBOMTreeInfoReqBD00 argBD00) throws SystemException;

    /**
     * 获取Bom清单版本信息
     * @param argBD00
     * @return
     * @throws SystemException
     */
    GetBVInfoRes GetBVInfo(GetBVInfoReqBD00 argBD00) throws SystemException;

    /**
     * 获取Bom清单版本信息
     * @param argBD01
     * @return
     * @throws SystemException
     */
    GetBVInfoRes GetBVInfo(GetBVInfoReqBD01 argBD01);

    /**
     * 新增Bom清单信息
     * @param argBD00
     * @return
     * @throws SystemException
     */
    SaveBomInfoRes AddBomInfo(SaveBomInfoReqBD00 argBD00) throws SystemException;

    /**
     * 编辑Bom清单信息
     * @param argBD03
     * @return
     * @throws SystemException
     */
    SaveBomInfoRes ModBomInfo(SaveBomInfoReqBD03 argBD03) throws SystemException;

    //查询变更记录列表
    GetAllBHChgInfoResB QALLBOMChgInfo(int bomRd);

    //查询变更记录明细
    GetBHChgDtlInfoResB QALLBOMChgInfoDetail(int bomChgRd);

    SaveImportResB AddImportBOM(CommonsMultipartFile file) throws IOException;

    ByteArrayOutputStream exportBOMExcel(int busData);
}
