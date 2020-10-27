package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.GetAllWOInfo.GetAllWOInfoRes;
import pnc.mesadmin.dto.GetAllWorkFoolControllerInfo.GetAllWorkFoolControllerResD;
import pnc.mesadmin.dto.GetMWFInfo.GetMWFInfoReqBD00;
import pnc.mesadmin.dto.GetMWFInfo.GetMWFInfoReqBD01;
import pnc.mesadmin.dto.GetMWFInfo.GetMWFInfoRes;
import pnc.mesadmin.dto.PageResult;
import pnc.mesadmin.dto.SaveSOPInfo.SaveSOPInfoReqBD00;
import pnc.mesadmin.dto.SaveSOPInfo.SaveSOPInfoReqBD01;
import pnc.mesadmin.dto.SaveSOPInfo.SaveSOPInfoReqBD02;
import pnc.mesadmin.dto.SaveSOPInfo.SaveSOPInfoRes;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.SOPInfo;


/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工艺文件信息Service接口
 * 创建人：刘福志
 * 创建时间：2017-6-1
 * 修改人：
 * 修改时间：
 */
public interface SpecfileIService {

    //获取所有的工艺流程控制
    PageResult<GetAllWorkFoolControllerResD> GetAllWorkFoolControllerResD(BaseRequest req);


    //查询物料工艺流程工序信息00状态下
    GetMWFInfoRes GetSelectByRuid(GetMWFInfoReqBD00 busData00) throws SystemException;

    //查询物料工艺流程工序信息01状态下
    GetMWFInfoRes GetSelectByruid(GetMWFInfoReqBD01 busData01) throws SystemException;

    //保存工艺文件信息
    SaveSOPInfoRes AddinsertSOPInfo(SaveSOPInfoReqBD00 busData00, SOPInfo sopInfo) throws SystemException;

    //更新工艺文件信息
    SaveSOPInfoRes ModupdateSOPInfo(SaveSOPInfoReqBD02 busData02, SOPInfo sopInfo) throws SystemException;

    //删除工艺文件信息
    SaveSOPInfoRes RmdeleteSOPInfo(SaveSOPInfoReqBD01 busData01) throws SystemException;
}
