package pnc.mesadmin.service;

import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllWfInfo.GetAllRes;
import pnc.mesadmin.dto.GetAllWfInfo.GetAllWfInfoRes;
import pnc.mesadmin.dto.GetAllWfInfo.GetAllWfInfoResD;
import pnc.mesadmin.dto.GetWfSInfo.GetWfSInfoReqBD00;
import pnc.mesadmin.dto.GetWfSInfo.GetWfSInfoRes;
import pnc.mesadmin.dto.GetWfVInfo.GetWfVInfoReqBD00;
import pnc.mesadmin.dto.GetWfVInfo.GetWfVInfoReqBD01;
import pnc.mesadmin.dto.GetWfVInfo.GetWfVInfoRes;
import pnc.mesadmin.dto.GetWfVTreeInfo.GetWfVTreeInfoReqBD00;
import pnc.mesadmin.dto.GetWfVTreeInfo.GetWfVTreeInfoRes;
import pnc.mesadmin.dto.SaveWfInfo.*;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：流程管理业务信息逻辑
 * 创建人：潘俊峰
 * 创建时间：2017-06-09
 * 修改人：
 * 修改时间：
 */
public interface WFIService {

    /**
     * 获取流程信息
     * @param argInitDataFields
     * @param argPageInfo
     * @return
     * @throws SystemException
     */
    GetAllWfInfoRes GetAllWfInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException;

    /**
     * 获取流程列表信息(新)
     * @param req
     * @return
     */
    PageResult<GetAllWfInfoResD> GetAllWfNew(BaseRequest req);

    /**
     * 获取流程版本列表信息
     * @param argBD00
     * @return
     * @throws SystemException
     */
    GetWfVTreeInfoRes GetWfVTreeInfo(GetWfVTreeInfoReqBD00 argBD00) throws SystemException;

    /**
     * 获取流程版本信息--WfRd
     * @param argBD00
     * @return
     * @throws SystemException
     */
    GetWfVInfoRes GetWfVInfo(GetWfVInfoReqBD00 argBD00) throws SystemException;

    /**
     * 获取流程版本信息--WfVerRd
     * @param argBD01
     * @return
     * @throws SystemException
     */
    GetWfVInfoRes GetWfVInfo(GetWfVInfoReqBD01 argBD01) throws SystemException;

    /**
     * 获取流程工序信息
     * @param argBD00
     * @return
     * @throws SystemException
     */
    GetWfSInfoRes GetWfSInfo(GetWfSInfoReqBD00 argBD00) throws SystemException;

    /**
     * 新增流程信息
     * @param argBD00
     * @return
     * @throws SystemException
     */
    SaveWfInfoRes AddWfInfo(SaveWfInfoReqBD00 argBD00) throws SystemException;

    /**
     * 删除流程信息
     * @param argBD01
     * @return
     * @throws SystemException
     */
    SaveWfInfoRes RmWfInfo(SaveWfInfoReqBD01 argBD01) throws SystemException;

    /**
     * 编辑流程信息
     * @param argBD02
     * @return
     * @throws SystemException
     */
    SaveWfInfoRes ModWfInfo(SaveWfInfoReqBD02 argBD02) throws SystemException;

    /**
     * 复制流程信息
     * @param argBD03
     * @return
     * @throws SystemException
     */
    SaveWfInfoRes AddCopyWfInfo(SaveWfInfoReqBD03 argBD03) throws SystemException;

    /**
     * 新增流程版本信息
     * @param argBD04
     * @return
     * @throws SystemException
     */
    SaveWfInfoRes AddWfVerInfo(SaveWfInfoReqBD04 argBD04) throws SystemException;

    /**
     * 删除流程版本信息
     * @param argBD05
     * @return
     * @throws SystemException
     */
    SaveWfInfoRes RmWfVerInfo(SaveWfInfoReqBD05 argBD05) throws SystemException;

    /**
     * 判断当前工序能不能删除
     * @param argBD06
     * @return
     * @throws SystemException
     */
    SaveWfInfoRes GetWfVerInfo(SaveWfInfoReqBD06 argBD06) throws SystemException;
}
