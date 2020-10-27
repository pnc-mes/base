package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetSpecBInfo.GetSpecBInfoResD;
import pnc.mesadmin.dto.SaveIOSInfo.*;
import pnc.mesadmin.dto.SystemException;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：车间管理业务信息逻辑
 * 创建人：潘俊峰
 * 创建时间：2017-06-13
 * 修改人：
 * 修改时间：
 */
public interface SpecOperateIService {

    //获取工序批次信息
    GetSpecBInfoResD GetSpecBInfo(String batch) throws SystemException;

    //进站操作
    SaveIOSInfoRes AddInput(SaveIOSInfoReqBD00 argBD00) throws SystemException;

    //出站操作
    SaveIOSInfoRes AddOutput(SaveIOSInfoReqBD01 argBD01) throws SystemException;

    //上机操作
    SaveIOSInfoRes AddUp(SaveIOSInfoReqBD02 argBD02) throws SystemException;

    //上机操作
    SaveIOSInfoRes AddDown(SaveIOSInfoReqBD03 argBD03) throws SystemException;

    //非标准移动操作
    SaveIOSInfoRes AddMove(SaveIOSInfoReqBD04[] argBD04) throws SystemException;

    //在线重工
    SaveIOSInfoRes AddRe(SaveIOSInfoReqBD05 argBD05) throws SystemException;
}
