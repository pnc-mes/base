package pnc.mesadmin.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllMaInfo.GetAllMaInfoReqBD00;
import pnc.mesadmin.dto.GetAllMaInfo.GetAllMaInfoRes;
import pnc.mesadmin.dto.GetAllMaInfo.GetAllMaInfoResD;
import pnc.mesadmin.dto.GetBOMReMaInfo.GetBOMReMaInfoReqBD00;
import pnc.mesadmin.dto.GetBOMReMaInfo.GetBOMReMaInfoRes;
import pnc.mesadmin.dto.GetMVTreeInfo.GetMVTreeInfoReqBD00;
import pnc.mesadmin.dto.GetMVTreeInfo.GetMVTreeInfoRes;
import pnc.mesadmin.dto.GetMaInfo.GetMaInfoRes;
import pnc.mesadmin.dto.GetMaTypeInfo.GetMaTypeInfoRes;
import pnc.mesadmin.dto.GetPdBOMDIInfo.GetPdBOMDIInfoReqBD00;
import pnc.mesadmin.dto.GetPdBOMDIInfo.GetPdBOMDIInfoRes;
import pnc.mesadmin.dto.GetPdBOMInfo.GetPdBOMInfoReqBD00;
import pnc.mesadmin.dto.GetPdBOMInfo.GetPdBOMInfoRes;
import pnc.mesadmin.dto.GetPdReGpInfo.GetPdReGpInfoReqBD00;
import pnc.mesadmin.dto.GetPdReGpInfo.GetPdReGpInfoRes;
import pnc.mesadmin.dto.SaveImportRes.SaveImportResB;
import pnc.mesadmin.dto.SaveMaInfo.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：物料管理业务信息逻辑
 * 创建人：潘俊峰
 * 创建时间：2017-06-02
 * 修改人：
 * 修改时间：
 */
public interface MaterialIService {

    /**
     * 获取物料分类信息
     * @return
     * @throws SystemException
     */
    GetMaTypeInfoRes QALLMaType() throws SystemException;

    /**
     * 获取物料列表信息
     * @param argBD00
     * @param argInitDataFields
     * @param argPageInfo
     * @return
     * @throws SystemException
     */
    GetAllMaInfoRes QALLByMaType(GetAllMaInfoReqBD00 argBD00, List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException;

    /**
     * 获取物料列表信息(新)
     * @param req
     * @return
     */
    PageResult<GetAllMaInfoResD> QALLAllMaNew(BaseRequest req);

    /**
     * 获取物料信息
     * @param status
     * @param ruid
     * @return
     * @throws SystemException
     */
    GetMaInfoRes GetMaInfo(int status, int ruid) throws SystemException;

    /**
     * 获取物料版本列表信息
     * @param argBD00
     * @return
     * @throws SystemException
     */
    GetMVTreeInfoRes QALLMVTreeInfo(GetMVTreeInfoReqBD00 argBD00) throws SystemException;

    /**
     * 新增物料信息
     * @param argBD00
     * @return
     * @throws SystemException
     */
    SaveMaInfoRes AddMaInfo(SaveMaInfoReqBD00 argBD00) throws SystemException;

    /**
     * 删除物料信息
     * @param argBD01
     * @return
     * @throws SystemException
     */
    SaveMaInfoRes RmMaInfo(SaveMaInfoReqBD01 argBD01) throws SystemException;

    /**
     * 修改物料信息
     * @param argBD02
     * @return
     * @throws SystemException
     */
    SaveMaInfoRes ModMaInfo(SaveMaInfoReqBD02 argBD02) throws SystemException;

    /**
     * 新增物料版本信息
     * @param argBD03
     * @return
     * @throws SystemException
     */
    SaveMaInfoRes AddMaVerInfo(SaveMaInfoReqBD03 argBD03) throws SystemException;

    /**
     * 获取产品关联产品族信息
     * @param argBD00
     * @return
     * @throws SystemException
     */
    GetPdReGpInfoRes GetPdReGpInfo(GetPdReGpInfoReqBD00 argBD00) throws SystemException;

    /**
     * 获取产品物料清单信息
     * @param argBD00
     * @return
     * @throws SystemException
     */
    GetPdBOMInfoRes GetPdBOMInfo(GetPdBOMInfoReqBD00 argBD00) throws SystemException;

    /**
     * 获取产品物料清单明细信息
     * @param argBD00
     * @return
     * @throws SystemException
     */
    GetPdBOMDIInfoRes GetPdBOMDIInfo(GetPdBOMDIInfoReqBD00 argBD00) throws SystemException;

    /**
     * 获取物料清单替代料信息
     * @param argBD00
     * @return
     * @throws SystemException
     */
    GetBOMReMaInfoRes GetBOMReMaInfo(GetBOMReMaInfoReqBD00 argBD00) throws SystemException;

    GetAllMaInfoRes QALLByMaType();



    //导入
    SaveImportResB AddImportMater(CommonsMultipartFile file, String isRadio) throws IOException;

    //导出
    ByteArrayOutputStream exportMaterialsExcel(GetAllMaInfoReqBD00 argBD00, List<InitDataField> argInitDataFields, PageInfo argPageInfo);
}
