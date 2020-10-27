package pnc.mesadmin.service;

import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllFileInfo.GetAllFileInfoRes;
import pnc.mesadmin.dto.GetAllFileInfo.GetAllFileInfoResD;
import pnc.mesadmin.dto.GetFileTreeInfo.GetFileTreeInfoBD00;
import pnc.mesadmin.dto.GetFileTreeInfo.GetFileTreeInfoRes;
import pnc.mesadmin.dto.GetFileTreeInfo.GetFileTreeInfoResD;
import pnc.mesadmin.dto.GetFileVInfo.GetFileVInfoReqBD00;
import pnc.mesadmin.dto.GetFileVInfo.GetFileVInfoReqBD01;
import pnc.mesadmin.dto.GetFileVInfo.GetFileVInfoRes;
import pnc.mesadmin.dto.GetFileVInfo.GetFileVInfoResD;
import pnc.mesadmin.dto.SaveFileInfo.*;
import pnc.mesadmin.entity.FileInfo;
import pnc.mesadmin.entity.FileVerInfo;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：文件信息Service接口
 * 创建人：刘福志
 * 创建时间：2017-6-3
 * 修改人：
 * 修改时间：
 */
public interface FileIService {
    //查询文件列表信息
    GetAllFileInfoRes QALLselectAllFileInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException;

    //查询文件版本列表信息
    GetFileTreeInfoRes GetselectfileVerRd(GetFileTreeInfoBD00 argGetFileTreeInfoBD00) throws SystemException;

    //查询文件版本信息00
    GetFileVInfoRes GetselectByfileRd(GetFileVInfoReqBD00 argGetFileVInfoReqBD00) throws SystemException;

    //查询文件版本信息01
    GetFileVInfoRes GetselectByfileVerRd(GetFileVInfoReqBD01 argGetFileVInfoReqBD01) throws SystemException;

    //保存文件信息
    SaveFileInfoRes AddinsertFileInfo(HttpServletRequest request, SaveFileInfoReqBD00 busData00, FileInfo fileInfo) throws SystemException,IOException;

    //更新文件信息
    SaveFileInfoRes ModupdateFileInfo(HttpServletRequest request, SaveFileInfoReqBD02 busData02, FileInfo fileInfo) throws SystemException,IOException;

    //复制文件信息
    SaveFileInfoRes copyFileInfo(SaveFileInfoReqBD03 busData03, FileInfo fileInfo) throws SystemException;

    //新增文件版本信息
    SaveFileInfoRes AddinsertFileVerInfo(HttpServletRequest request, SaveFileInfoReqBD04 busData04, FileVerInfo fileVerInfo) throws SystemException,IOException;

    //删除文件版本信息
    SaveFileInfoRes RmdeleteFileVerInfo(SaveFileInfoReqBD05 busData05)throws SystemException;

    //删除文件信息
    SaveFileInfoRes RmdeleteFileInfo(SaveFileInfoReqBD01 busData01) throws SystemException;

    /**
     * 获取文件列表信息(新)
     * @param request
     * @return
     */
    PageResult<GetAllFileInfoResD> QALLFileInfoNew(BaseRequest request);

    /**
     * 获取所有文件版本列表信息(新)
     * @param request
     * @return
     */
    PageResult<GetAllFileInfoResD> QALLFileVerListNew(BaseRequest request);

    /**
     * 获取文件版本列表信息(新)
     * @param request
     * @return
     */
    PageResult<GetFileVInfoResD> QALLFileVerInfoNew(BaseRequest<GetFileVInfoReqBD00> request);

    /**
     * 新增文件信息(新)
     * @param request
     */
    void AddFileInfo(BaseRequest<SaveFileInfoReqBD00> request);

    /**
     * 新增文件版本信息(新)
     * @param request
     */
    void AddFileVerInfo(BaseRequest<SaveFileInfoReqBD04> request);

    /**
     * 更新文件信息(新)
     * @param request
     */
    void ModFileInfo(BaseRequest<SaveFileInfoReqBD02> request);

    /**
     * 删除文件信息(新)
     * @param request
     */
    void RmFileInfo(BaseRequest<SaveFileInfoReqBD01> request);

    /**
     * 删除文件版本信息(新)
     * @param request
     */
    void RmFileVerInfo(BaseRequest<SaveFileInfoReqBD05> request);
}
