package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.GetAllMdInfo.GetAllMdInfoRes;
import pnc.mesadmin.dto.GetMdInfo.GetMdInfoReqBD00;
import pnc.mesadmin.dto.GetMdInfo.GetMdInfoRes;
import pnc.mesadmin.dto.SaveMdInfo.SaveMdInfoReqBD00;
import pnc.mesadmin.dto.SaveMdInfo.SaveMdInfoReqBD01;
import pnc.mesadmin.dto.SaveMdInfo.SaveMdInfoReqBD02;
import pnc.mesadmin.dto.SaveMdInfo.SaveMdInfoRes;
import pnc.mesadmin.dto.SysVerInfoDto.SysVerInfoSaveDto;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：菜单配置业务信息逻辑
 * 创建人：潘俊峰
 * 创建时间：2017-12-14
 * 修改人：
 * 修改时间：
 */
public interface ModuleIService {

    /**
     * 获取模块列表信息
     *
     * @return
     */
    GetAllMdInfoRes GetAllMdInfo(GetMdInfoReqBD00 reqBD00);

    /**
     * 获取模块信息
     *
     * @param argBD00
     * @return
     */
    GetMdInfoRes GetMdInfo(GetMdInfoReqBD00 argBD00);

    /**
     * 新增模块信息
     *
     * @param argBD00
     * @return
     */
    SaveMdInfoRes AddMdInfo(SaveMdInfoReqBD00 argBD00);

    /**
     * 删除模块信息
     *
     * @param argBD01
     * @return
     */
    SaveMdInfoRes RmMdInfo(SaveMdInfoReqBD01 argBD01);

    /**
     * 编辑模块信息
     *
     * @param argBD02
     * @return
     */
    SaveMdInfoRes ModMdInfo(SaveMdInfoReqBD02 argBD02);

    /**
     * 新增系统版本信息
     *
     * @param req
     * @return
     */
    BaseRes AddSysVerInfo00(SysVerInfoSaveDto req);

    /**
     * 删除系统版本信息
     *
     * @param req
     * @return
     */
    BaseRes AddSysVerInfo01(SysVerInfoSaveDto req);

    /**
     * 激活系统版本信息
     *
     * @param req
     * @return
     */
    BaseRes AddSysVerInfo03(SysVerInfoSaveDto req);

    /**
     * 获取系统版本信息
     *
     * @param
     * @return
     */
    BaseRes GetAllSysVerInfo(SysVerInfoSaveDto req);

    /**
     * 获取所有菜单信息
     *
     * @param
     * @return
     */
    BaseRes GetAllResInfo();
}
