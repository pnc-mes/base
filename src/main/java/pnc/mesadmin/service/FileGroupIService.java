package pnc.mesadmin.service;

import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllFileGInfo.GetAllFileGInfoRes;
import pnc.mesadmin.dto.GetAllFileGInfo.GetAllFileGInfoResD;
import pnc.mesadmin.dto.GetAllSGInfo.GetAllSGInfoResD;
import pnc.mesadmin.dto.GetFileGInfo.GetFileGInfoRes;
import pnc.mesadmin.dto.SaveDevGPInfo.SaveDevGpInfoReqBD02;
import pnc.mesadmin.dto.SaveFileGPInfo.SaveFileGPInfoReqBD00;
import pnc.mesadmin.dto.SaveFileGPInfo.SaveFileGPInfoReqBD02;
import pnc.mesadmin.dto.SaveFileGPInfo.SaveFileGPInfoRes;
import pnc.mesadmin.entity.FileGpInfo;
import pnc.mesadmin.utils.BaseResponse;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：文件组信息Service接口
 * 创建人：刘福志
 * 创建时间：2017-6-6
 * 修改人：
 * 修改时间：
 */
public interface FileGroupIService {
    /**
     * @Description 分页查询文件组信息
     * @Author jgy
     * @Date 2020/10/13
     * @Param
     * @Return
     * @Exception
     */
    BaseResponse GetFileGroupList(SaveFileGPInfoReqBD02 request);

    //查询文件组列表信息
    GetAllFileGInfoRes QALLselectAllFileGpInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException;

    /**
     * 查询文件组列表信息(新)
     * @param req
     * @return
     */
    PageResult<GetAllFileGInfoResD> QALLGetAllFileGNew(BaseRequest req);

    //查询文件组信息
    GetFileGInfoRes GetselectByruid(int fileGRd) throws SystemException;

    //新增文件组信息
    SaveFileGPInfoRes AddinsertFileGpInfo(SaveFileGPInfoReqBD00 busData00, FileGpInfo fileGpInfo) throws SystemException;

    //更新文件组信息
    SaveFileGPInfoRes ModupdateFileGpInfo(SaveFileGPInfoReqBD02 busData02, FileGpInfo fileGpInfo) throws SystemException;

    //删除文件组信息
    SaveFileGPInfoRes RmdeleteFileGpInfo(int ruid) throws SystemException;
}
