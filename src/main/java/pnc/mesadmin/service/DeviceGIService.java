package pnc.mesadmin.service;

import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllDevGInfo.GetAllDevGInfoRes;
import pnc.mesadmin.dto.GetAllDevGInfo.GetAllDevGInfoResD;
import pnc.mesadmin.dto.GetAllOpertInfo.GetAllOpertInfoResD;
import pnc.mesadmin.dto.GetDevGInfo.GetDevGInfoRes;
import pnc.mesadmin.dto.SaveDevGPInfo.SaveDevGpInfoReqBD00;
import pnc.mesadmin.dto.SaveDevGPInfo.SaveDevGpInfoReqBD02;
import pnc.mesadmin.dto.SaveDevGPInfo.SaveDevGpInfoReqBD03;
import pnc.mesadmin.dto.SaveDevGPInfo.SaveDevGpInfoRes;
import pnc.mesadmin.entity.DevGpInfo;
import pnc.mesadmin.utils.BaseResponse;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：设备组信息Service接口
 * 创建人：刘福志
 * 创建时间：2017-8-7
 * 修改人：
 * 修改时间：
 */
public interface DeviceGIService {
    /**
     * @Description 分页查询设备组信息
     * @Author yin.yang
     * @Date 2020/9/21
     * @Param
     * @Return
     * @Exception
     */
    BaseResponse GetDevGroupList(SaveDevGpInfoReqBD02 request);

    //查询设备组列表信息
    GetAllDevGInfoRes QALLGetAllDevGInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException;
    /**
     * 查询设备组列表信息(新)
     * @param req
     * @return
     */
    PageResult<GetAllDevGInfoResD> QALLGetAllDevGNew(BaseRequest req);

    //查询设备组信息
    GetDevGInfoRes GetGetDevGInfoRes(int devGRd) throws SystemException;

    //新增设备组信息
    SaveDevGpInfoRes AddinsertDevGpInfo(SaveDevGpInfoReqBD00 busData00, DevGpInfo devGpInfo) throws SystemException;

    //更新设备组信息
    SaveDevGpInfoRes ModupdateDevGpInfo(SaveDevGpInfoReqBD02 busData02, DevGpInfo devGpInfo) throws SystemException;

    //复制设备组信息
    SaveDevGpInfoRes AddinsertDevGpInfo(SaveDevGpInfoReqBD03 busData03, DevGpInfo devGpInfo) throws SystemException;

    //删除设备组信息
    SaveDevGpInfoRes RmdeleteDevGpInfo(int ruid) throws SystemException;
}
