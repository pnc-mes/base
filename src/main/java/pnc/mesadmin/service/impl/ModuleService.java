package pnc.mesadmin.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.BaseDto.BaseResB;
import pnc.mesadmin.dto.GetAllMdInfo.GetAllMdInfoRes;
import pnc.mesadmin.dto.GetAllMdInfo.GetAllMdInfoResB;
import pnc.mesadmin.dto.GetAllMdInfo.GetAllMdInfoResD;
import pnc.mesadmin.dto.GetAllMdInfo.GetAllMdInfoResDMenuInfo;
import pnc.mesadmin.dto.GetMdInfo.*;
import pnc.mesadmin.dto.SaveMdInfo.*;
import pnc.mesadmin.dto.SysVerInfoDto.SysVerInfoSaveDto;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.ModuleIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.util.*;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：菜单配置业务信息逻辑
 * 创建人：潘俊峰
 * 创建时间：2017-12-14
 * 修改人：
 * 修改时间：
 */
@Service
@Transactional
public class ModuleService implements ModuleIService {

    @Resource
    private ModuleDAO moduleDAO;

    @Resource
    private ResDAO resDAO;

    @Resource
    private ResOptDAO resOptDAO;

    @Resource
    private OptDAO optDAO;

    @Resource
    private RolePmDAO rolePmDAO;
    @Resource
    private SysVerDao sysVerDao;

    //获取模块列表信息
    @Override
    public GetAllMdInfoRes GetAllMdInfo(GetMdInfoReqBD00 reqBD00) {
        GetAllMdInfoRes objEGetAllMdInfoRes = new GetAllMdInfoRes();
        GetAllMdInfoResB objEGetAllMdInfoResB = new GetAllMdInfoResB();
        List<GetAllMdInfoResD> objEGetAllMdInfoResDs = new ArrayList<>();
        if (reqBD00.getSysVerRd() == null) {
            throw new SystemException("EEEE", "请选择版本信息！");
        }
        SysVerInfo sysVerInfo = sysVerDao.SelectSysVerInfoByRuid(reqBD00.getSysVerRd());
        GetAllMdInfoResD objEGetAllMdInfoResD = null;
        //查询所有一级模块
        List<ModuleInfo> objEModuleInfos = moduleDAO.SelectAllChildModuleAdminBySysVerGd("/", sysVerInfo.getGuid());
        for (ModuleInfo moduleInfo : objEModuleInfos) {
            objEGetAllMdInfoResD = new GetAllMdInfoResD();
            objEGetAllMdInfoResD.setModuleRd(moduleInfo.getRuid());
            objEGetAllMdInfoResD.setModuleName(moduleInfo.getModuleName());
            objEGetAllMdInfoResD.setMenuInfo(getMenuInfo(moduleInfo.getGuid()));

            objEGetAllMdInfoResDs.add(objEGetAllMdInfoResD);
        }

        objEGetAllMdInfoResB.setData(objEGetAllMdInfoResDs);
        objEGetAllMdInfoRes.setBody(objEGetAllMdInfoResB);

        return objEGetAllMdInfoRes;
    }

    //获取模块信息
    @Override
    public GetMdInfoRes GetMdInfo(GetMdInfoReqBD00 argBD00) {
        GetMdInfoRes objEGetMdInfoRes = new GetMdInfoRes();
        GetMdInfoResB objEGetMdInfoResB = new GetMdInfoResB();
        GetMdInfoResD objEGetMdInfoResD = new GetMdInfoResD();
        GetMdInfoResDMInfo objEGetMdInfoResDMInfo = new GetMdInfoResDMInfo();
        GetMdInfoResDPMInfo objEGetMdInfoResDPMInfo = new GetMdInfoResDPMInfo();
        List<GetMdInfoResDResInfo> objEGetMdInfoResDResInfos = new ArrayList<>();
        GetMdInfoResDResInfo objEGetMdInfoResDResInfo = null;
        List<GetMdInfoResDROptInfo> objEGetMdInfoResDROptInfos = null;
        GetMdInfoResDROptInfo objEGetMdInfoResDROptInfo = null;
        ResInfo resInfo = null;
        List<ResOptInfo> resOptInfos = null;

        //查询模块
        ModuleInfo objEModuleInfo = moduleDAO.SelectModuleInfoRuid(argBD00.getModuleRd());
        if (objEModuleInfo == null) {
            throw new SystemException("", "模块不存在");
        }
        objEGetMdInfoResDMInfo.setModuleRd(objEModuleInfo.getRuid());
        objEGetMdInfoResDMInfo.setModuleName(objEModuleInfo.getModuleName());
        objEGetMdInfoResDMInfo.setResUrl(objEModuleInfo.getResUrl());
        objEGetMdInfoResDMInfo.setLinkType(objEModuleInfo.getLinkType());
        objEGetMdInfoResDMInfo.setPos(objEModuleInfo.getPos());
        objEGetMdInfoResDMInfo.setIsClose(objEModuleInfo.getIsClose());
        objEGetMdInfoResD.setMInfo(objEGetMdInfoResDMInfo);

        //查询上级模块
        ModuleInfo objEModuleInfoP = moduleDAO.SelectModuleInfo(objEModuleInfo.getParentGd());
        if (objEModuleInfoP != null) {
            objEGetMdInfoResDPMInfo.setPModuleRd(objEModuleInfoP.getRuid());
            objEGetMdInfoResDPMInfo.setPModuleName(objEModuleInfoP.getModuleName());
        }
        objEGetMdInfoResD.setPMInfo(objEGetMdInfoResDPMInfo);
        //查询操作
        List<OptInfo> optInfoList = optDAO.SelectAllOptInfo();
        Set<String> set = null;

        //查询该模块下的资源
        List<ResInfo> objEResInfos = null;//resDAO.SelectAllModuleAdmin(objEModuleInfo.getGuid());
        if ("system".equals(CommonUtils.readUser().getUserName())) {
            objEResInfos = resDAO.SelectAllModuleAdmin(objEModuleInfo.getGuid());
        } else {
            objEResInfos = resDAO.SelectAllModule(objEModuleInfo.getGuid(), "01");
        }
        for (int i = 0, length = objEResInfos.size(); i < length; i++) {
            //遍历资源
            resInfo = objEResInfos.get(i);
            objEGetMdInfoResDResInfo = new GetMdInfoResDResInfo();
            objEGetMdInfoResDResInfo.setResRd(resInfo.getRuid());
            objEGetMdInfoResDResInfo.setResName(resInfo.getResName());
            objEGetMdInfoResDResInfo.setResUrl(resInfo.getResUrl());
            objEGetMdInfoResDResInfo.setLinkType(resInfo.getLinkType());
            objEGetMdInfoResDResInfo.setPos(resInfo.getPos());
            objEGetMdInfoResDResInfo.setIsClose(resInfo.getIsClose());
            objEGetMdInfoResDROptInfos = new ArrayList<>();
            //查询资源的操作
            resOptInfos = resOptDAO.SelectResOptInfo(resInfo.getGuid());
            set = new HashSet<>();
            for (ResOptInfo resOptInfo : resOptInfos) {
                set.add(resOptInfo.getOptGd());
            }

            for (OptInfo optInfo : optInfoList) {
                objEGetMdInfoResDROptInfo = new GetMdInfoResDROptInfo();
                objEGetMdInfoResDROptInfo.setOptRd(optInfo.getRuid());
                objEGetMdInfoResDROptInfo.setOptName(optInfo.getOptName());

                if (set.contains(optInfo.getGuid())) {
                    objEGetMdInfoResDROptInfo.setChecked("00");
                } else {
                    objEGetMdInfoResDROptInfo.setChecked("01");
                }

                objEGetMdInfoResDROptInfos.add(objEGetMdInfoResDROptInfo);
            }

            objEGetMdInfoResDResInfo.setOptInfo(objEGetMdInfoResDROptInfos);

            objEGetMdInfoResDResInfos.add(objEGetMdInfoResDResInfo);
        }

        objEGetMdInfoResD.setCreator(objEModuleInfo.getCreator());
        objEGetMdInfoResD.setCreateTime(DateUtil.format(objEModuleInfo.getCreateTime()));
        objEGetMdInfoResD.setLastModifyMan(objEModuleInfo.getLastModifyMan());
        objEGetMdInfoResD.setLastModifyTime(DateUtil.format(objEModuleInfo.getLastModifyTime()));
        objEGetMdInfoResD.setRemark(objEModuleInfo.getRemark());
        objEGetMdInfoResD.setResInfo(objEGetMdInfoResDResInfos);
        objEGetMdInfoResB.setData(objEGetMdInfoResD);
        objEGetMdInfoRes.setBody(objEGetMdInfoResB);

        return objEGetMdInfoRes;
    }

    //新增模块信息
    @Override
    public SaveMdInfoRes AddMdInfo(SaveMdInfoReqBD00 argBD00) {
        SaveMdInfoRes objESaveMdInfoRes = new SaveMdInfoRes();

        //当前用户
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();

        if (argBD00.getSysVerRd() == null) {
            throw new SystemException("EEEE", "请选择版本信息！");
        }
        SysVerInfo sysVerInfo = sysVerDao.SelectSysVerInfoByRuid(argBD00.getSysVerRd());
        ModuleInfo objEModuleInfo = new ModuleInfo();
        objEModuleInfo.setGuid(CommonUtils.getRandomNumber());
        objEModuleInfo.setModuleName(argBD00.getModuleName());
        //查询模块
        ModuleInfo moduleInfo = moduleDAO.SelectModuleInfoRuid(argBD00.getPModuleRd());
        if (moduleInfo == null) {
            if (moduleDAO.SelectModuleByName(argBD00.getModuleName(), "/", sysVerInfo.getGuid()) != null) {
                throw new SystemException("", "模块名称已存在!");
            }
            objEModuleInfo.setParentGd("/");
        } else {
            if (moduleDAO.SelectModuleByName(argBD00.getModuleName(), moduleInfo.getGuid(), sysVerInfo.getGuid()) != null) {
                throw new SystemException("", "模块名称已存在!");
            }
            objEModuleInfo.setParentGd(moduleInfo.getGuid());
        }
        objEModuleInfo.setResUrl(argBD00.getResUrl());
        objEModuleInfo.setLinkType(argBD00.getLinkType());
        objEModuleInfo.setPos(argBD00.getPos());
        objEModuleInfo.setCreator(userName);
        objEModuleInfo.setCreateTime(date);
        objEModuleInfo.setIsClose(argBD00.getIsClose());
        objEModuleInfo.setSysVerGd(sysVerInfo.getGuid());
        moduleDAO.InsertModuleInfo(objEModuleInfo);

        if (argBD00.getResInfo() != null) {
            ResInfo objEResInfo = null;
            ResOptInfo objEResOptInfo = null;
            OptInfo objEOptInfo = null;
            int i = 1;

            for (SaveMdInfoReqBD00ResInfo resInfo : argBD00.getResInfo()) {
                objEResInfo = new ResInfo();
                objEResInfo.setGuid(CommonUtils.getRandomNumber());
                objEResInfo.setModuleGd(objEModuleInfo.getGuid());
                objEResInfo.setModuleName(objEModuleInfo.getModuleName());
                objEResInfo.setResName(resInfo.getResName());
                objEResInfo.setResUrl(resInfo.getResUrl());
                objEResInfo.setLinkType(resInfo.getLinkType());
                objEResInfo.setPos(resInfo.getPos());
                objEResInfo.setIsClose(resInfo.getIsClose());
                objEResInfo.setCreator(userName);
                objEResInfo.setCreateTime(date);
                resDAO.InsertRes(objEResInfo);
                for (SaveMdInfoReqBD00ROptInfo optInfo : resInfo.getOptInfo()) {
                    objEResOptInfo = new ResOptInfo();
                    objEOptInfo = optDAO.SelectOptInfoRuid(optInfo.getOptRd());
                    if (objEOptInfo == null) {
                        throw new SystemException("", "操作信息不存在!");
                    }
                    objEResOptInfo.setGuid(CommonUtils.getRandomNumber());
                    objEResOptInfo.setResGd(objEResInfo.getGuid());
                    objEResOptInfo.setOptGd(objEOptInfo.getGuid());
                    objEResOptInfo.setOptUrl(objEResInfo.getResUrl());
                    objEResOptInfo.setOptName(objEOptInfo.getOptName());
                    objEResOptInfo.setCreator(userName);
                    objEResOptInfo.setCreateTime(date);
                    resOptDAO.InsertResOpt(objEResOptInfo);
                }
            }
        }

        return objESaveMdInfoRes;
    }

    //删除模块信息
    @Override
    public SaveMdInfoRes RmMdInfo(SaveMdInfoReqBD01 argBD01) {
        //查询模块信息
        ModuleInfo objEModuleInfo = moduleDAO.SelectModuleInfoRuid(argBD01.getModuleRd());
        if (objEModuleInfo == null) {
            throw new SystemException("", "模块信息不存在!");
        }
        //查询模块下资源
        List<ResInfo> objEResInfos = resDAO.SelectAllModuleAdmin(objEModuleInfo.getGuid());
        for (ResInfo resInfo : objEResInfos) {
            //删除资源下操作
            resOptDAO.DeleteResOptByResGd(resInfo.getGuid());
        }
        //删除模块下资源
        resDAO.DeleteResInfoByModuleGd(objEModuleInfo.getGuid());

        //删除模块下角色权限
        rolePmDAO.DeleteRolePmInfoByModuleGd(objEModuleInfo.getGuid());

        //删除模块信息
        moduleDAO.DeleteModuleByRuid(objEModuleInfo.getRuid());

        return new SaveMdInfoRes();
    }

    //编辑模块信息
    @Override
    public SaveMdInfoRes ModMdInfo(SaveMdInfoReqBD02 argBD02) {
        //用户名
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();

        ModuleInfo objEModuleInfo = moduleDAO.SelectModuleInfoRuid(argBD02.getModuleRd());
        if (objEModuleInfo == null) {
            throw new SystemException("", "模块信息不存在!");
        }
        SysVerInfo sysVerInfo = sysVerDao.SelectSysVerInfoByRuid(argBD02.getSysVerRd());
        if (sysVerInfo == null) {
            throw new SystemException("", "系统版本不存在!");
        }
        ModuleInfo moduleInfo = moduleDAO.SelectModuleByName(argBD02.getModuleName(), objEModuleInfo.getParentGd(), sysVerInfo.getGuid());
        if (moduleInfo != null && !objEModuleInfo.getModuleName().equals(moduleInfo.getModuleName())) {
            throw new SystemException("", "模块名称已存在!");
        }
        objEModuleInfo.setModuleName(argBD02.getModuleName());
        //查询模块
        /*ModuleInfo pModuleInfo = moduleDAO.SelectModuleInfoRuid(argBD02.getPModuleRd());
        if(pModuleInfo == null){
            objEModuleInfo.setParentGd("/");
        }else{
            objEModuleInfo.setParentGd(pModuleInfo.getGuid());
        }*/
        objEModuleInfo.setResUrl(argBD02.getResUrl());
        objEModuleInfo.setLinkType(argBD02.getLinkType());
        objEModuleInfo.setPos(argBD02.getPos());
        objEModuleInfo.setLastModifyMan(userName);
        objEModuleInfo.setIsClose(argBD02.getIsClose());
        objEModuleInfo.setLastModifyTime(new Date());
        if (moduleDAO.UpdateModule(objEModuleInfo) <= 0) {
            throw new SystemException("", "更新失败");
        }


        //查询该模块下的资源
        List<ResOptInfo> objEResOptInfos = null;
        ResOptInfo objEResOptInfo = null;
        ResInfo objEResInfo = null;
        Map<Integer, ResOptInfo> mapResOpt = null;
        OptInfo objEOptInfo = null;
        List<ResInfo> objEResInfos = null;//resDAO.SelectAllModuleAdmin(objEModuleInfo.getGuid());
        if ("system".equals(CommonUtils.readUser().getUserName())) {
            objEResInfos = resDAO.SelectAllModuleAdmin(objEModuleInfo.getGuid());
        } else {
            objEResInfos = resDAO.SelectAllModule(objEModuleInfo.getGuid(), "01");
        }
        Map<Integer, ResInfo> mapRes = new HashMap<>();
        for (ResInfo resInfo : objEResInfos) {
            mapRes.put(resInfo.getRuid(), resInfo);
        }

        for (SaveMdInfoReqBD02ResInfo resInfo : argBD02.getResInfo()) {
            if (mapRes.containsKey(resInfo.getResRd())) {
                objEResInfo = mapRes.get(resInfo.getResRd());
                //更新
                if (checkRes(resInfo, objEResInfo)) {
                    objEResInfo.setResName(resInfo.getResName());
                    objEResInfo.setResUrl(resInfo.getResUrl());
                    objEResInfo.setPos(resInfo.getPos());
                    objEResInfo.setLinkType(resInfo.getLinkType());
                    objEResInfo.setIsClose(resInfo.getIsClose());
                    //修改
                    resDAO.UpdateResInfo(objEResInfo);
                }
                //Opt
                objEResOptInfos = resOptDAO.SelectResOptInfo(objEResInfo.getGuid());
                mapResOpt = new HashMap<>();
                for (ResOptInfo resOptInfo : objEResOptInfos) {
                    objEOptInfo = optDAO.SelectOptInfo(resOptInfo.getOptGd());
                    if (objEOptInfo == null) {
                        throw new SystemException("", "操作信息不存在!");
                    }
                    mapResOpt.put(objEOptInfo.getRuid(), resOptInfo);
                }
                for (SaveMdInfoReqBD02ROptInfo optInfo : resInfo.getOptInfo()) {
                    if (mapResOpt.containsKey(optInfo.getOptRd())) {
                        //修改
                        mapResOpt.remove(optInfo.getOptRd());
                    } else {
                        //新增
                        objEResOptInfo = new ResOptInfo();
                        objEOptInfo = optDAO.SelectOptInfoRuid(optInfo.getOptRd());
                        if (objEOptInfo == null) {
                            throw new SystemException("", "操作信息不存在!");
                        }
                        objEResOptInfo.setGuid(CommonUtils.getRandomNumber());
                        objEResOptInfo.setResGd(objEResInfo.getGuid());
                        objEResOptInfo.setOptGd(objEOptInfo.getGuid());
                        objEResOptInfo.setOptUrl(objEResInfo.getResUrl());
                        objEResOptInfo.setOptName(objEOptInfo.getOptName());
                        objEResOptInfo.setCreator(userName);
                        objEResOptInfo.setCreateTime(date);

                        resOptDAO.InsertResOpt(objEResOptInfo);
                    }
                }
                //资源操作删除
                for (Map.Entry<Integer, ResOptInfo> resOptInfoEntry : mapResOpt.entrySet()) {
                    //删除角色资源操作
                    rolePmDAO.DeleteRPByResGdResOptGd(resOptInfoEntry.getValue().getGuid());

                    //删除资源操作
                    resOptDAO.DeleteResOptByGuid(resOptInfoEntry.getValue().getGuid());
                }

                mapRes.remove(resInfo.getResRd());
            } else {
                //新增
                objEResInfo = new ResInfo();
                objEResInfo.setGuid(CommonUtils.getRandomNumber());
                objEResInfo.setModuleGd(moduleInfo.getGuid());
                objEResInfo.setModuleName(moduleInfo.getModuleName());
                objEResInfo.setResName(resInfo.getResName());
                objEResInfo.setResUrl(resInfo.getResUrl());
                /*Integer pos = resDAO.SelectResPosByModuleGd(moduleInfo.getGuid());
                objEResInfo.setPos(pos==null?1:pos+1);*/
                objEResInfo.setLinkType(resInfo.getLinkType());
                objEResInfo.setPos(resInfo.getPos());
                objEResInfo.setIsClose(resInfo.getIsClose());
                objEResInfo.setCreator(userName);
                objEResInfo.setCreateTime(date);
                resDAO.InsertRes(objEResInfo);
                for (SaveMdInfoReqBD02ROptInfo optInfo : resInfo.getOptInfo()) {
                    objEResOptInfo = new ResOptInfo();
                    objEOptInfo = optDAO.SelectOptInfoRuid(optInfo.getOptRd());
                    if (objEOptInfo == null) {
                        throw new SystemException("", "操作信息不存在!");
                    }
                    objEResOptInfo.setGuid(CommonUtils.getRandomNumber());
                    objEResOptInfo.setResGd(objEResInfo.getGuid());
                    objEResOptInfo.setOptGd(objEOptInfo.getGuid());
                    objEResOptInfo.setOptUrl(objEResInfo.getResUrl());
                    objEResOptInfo.setOptName(objEOptInfo.getOptName());
                    objEResOptInfo.setCreator(userName);
                    objEResOptInfo.setCreateTime(date);
                    resOptDAO.InsertResOpt(objEResOptInfo);
                }
            }
        }

        for (Map.Entry<Integer, ResInfo> resInfoEntry : mapRes.entrySet()) {
            String guid = resInfoEntry.getValue().getGuid();

            //删除角色资源操作
            rolePmDAO.DeleteRPByResGd(guid);

            //删除资源操作
            resOptDAO.DeleteResOptByResGd(guid);

            //删除资源
            resDAO.DeleteResByGuid(guid);
        }

        return new SaveMdInfoRes();
    }

    /**
     * 获取子模块
     *
     * @param moduleGd
     * @return
     */
    public List<GetAllMdInfoResDMenuInfo> getMenuInfo(String moduleGd) {
        List<GetAllMdInfoResDMenuInfo> objEGetAllMdInfoResDMenuInfos = new ArrayList<GetAllMdInfoResDMenuInfo>();
        GetAllMdInfoResDMenuInfo objEGetAllMdInfoResDMenuInfo = null;

        List<ModuleInfo> objEModuleInfos = moduleDAO.SelectAllChildModuleAdmin(moduleGd);
        for (ModuleInfo moduleInfo : objEModuleInfos) {
            objEGetAllMdInfoResDMenuInfo = new GetAllMdInfoResDMenuInfo();
            objEGetAllMdInfoResDMenuInfo.setModuleRd(moduleInfo.getRuid());
            objEGetAllMdInfoResDMenuInfo.setModuleName(moduleInfo.getModuleName());
            objEGetAllMdInfoResDMenuInfo.setMenuInfo(getMenuInfo(moduleInfo.getGuid()));

            objEGetAllMdInfoResDMenuInfos.add(objEGetAllMdInfoResDMenuInfo);
        }

        return objEGetAllMdInfoResDMenuInfos;
    }

    /**
     * 检验是否变化
     *
     * @param resInfo
     * @param oldResInfo
     * @return
     */
    public Boolean checkRes(SaveMdInfoReqBD02ResInfo resInfo, ResInfo oldResInfo) {
        if (resInfo.getResName().equals(oldResInfo.getResName()) &&
                resInfo.getResUrl().equals(oldResInfo.getResUrl()) &&
                resInfo.getPos() == oldResInfo.getPos() &&
                resInfo.getLinkType().equals(oldResInfo.getLinkType()) &&
                resInfo.getIsClose().equals(oldResInfo.getIsClose())) {
            return false;
        }

        return true;
    }

    @Override
    public BaseRes AddSysVerInfo00(SysVerInfoSaveDto req) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        SysVerInfo sysVerInfoJY = sysVerDao.SelectSysVerInfoBySysVerName(req.getSysVerName());
        if (sysVerInfoJY != null) {
            throw new SystemException("EEEE", "系统版本名称重复！");
        }
        SysVerInfo sysVerInfo = new SysVerInfo();
        sysVerInfo.setGuid(CommonUtils.getRandomNumber());
        sysVerInfo.setCreator(CommonUtils.readUser().getRealName());
        sysVerInfo.setCreateTime(new Date());
        sysVerInfo.setSysVerName(req.getSysVerName());
        if (req.getSourceSysVRd() != null) {
            SysVerInfo sysVerInfo1 = sysVerDao.SelectSysVerInfoByRuid(req.getSourceSysVRd());
            if (sysVerInfo1 != null) {
                sysVerInfo.setSourceSysVGd(sysVerInfo1.getGuid());
                List<ModuleInfo> moduleInfos = moduleDAO.SelectAllChildModuleAdminBySysVerGd("/", sysVerInfo1.getGuid());
                for (ModuleInfo model : moduleInfos) {
                    ModuleInfo insertModule = new ModuleInfo();
                    BeanUtils.copyProperties(model, insertModule);

                    insertModule.setGuid(CommonUtils.getRandomNumber());
                    insertModule.setSysVerGd(sysVerInfo.getGuid());
                    insertModule.setCreator(CommonUtils.readUser().getRealName());
                    insertModule.setCreateTime(new Date());
                    moduleDAO.InsertModuleInfo(insertModule);
                    getModule(model.getGuid(), insertModule.getGuid(), sysVerInfo.getGuid());
                }
            }
        }

        sysVerInfo.setStatus("01"); //不可用
        sysVerDao.InsertSysverInfo(sysVerInfo);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    //复制继承版本的菜单信息
    public void getModule(String parentGd, String InsertGd, String sysVerGd) {
        List<ModuleInfo> moduleInfos = moduleDAO.SelectAllChildModuleAdmin(parentGd);
        List<ResInfo> resInfoList = resDAO.SelectAllModuleAdmin(parentGd);
        if (resInfoList != null && resInfoList.size() > 0) {
            for (ResInfo model : resInfoList) {
                ResInfo resInfo = new ResInfo();
                BeanUtils.copyProperties(model, resInfo);
                resInfo.setGuid(CommonUtils.getRandomNumber());
                resInfo.setModuleGd(InsertGd);
                resInfo.setCreator(CommonUtils.readUser().getRealName());
                resInfo.setCreateTime(new Date());
                resDAO.InsertRes(resInfo);
                List<ResOptInfo> resOptInfoList = resOptDAO.SelectResOptInfo(model.getGuid());
                if (resOptInfoList != null) {
                    for (ResOptInfo optModel : resOptInfoList) {
                        ResOptInfo resOptInfo = new ResOptInfo();
                        BeanUtils.copyProperties(optModel, resOptInfo);
                        resOptInfo.setGuid(CommonUtils.getRandomNumber());
                        resOptInfo.setResGd(resInfo.getGuid());
                        resOptInfo.setCreator(CommonUtils.readUser().getRealName());
                        resOptInfo.setCreateTime(new Date());
                        resOptDAO.InsertResOpt(resOptInfo);
                    }
                }
            }
        }
        if (moduleInfos != null) {
            for (ModuleInfo model : moduleInfos) {
                ModuleInfo insertModule = new ModuleInfo();
                BeanUtils.copyProperties(model, insertModule);
                insertModule.setGuid(CommonUtils.getRandomNumber());
                insertModule.setSysVerGd(sysVerGd);
                insertModule.setParentGd(InsertGd);
                insertModule.setCreator(CommonUtils.readUser().getRealName());
                insertModule.setCreateTime(new Date());
                moduleDAO.InsertModuleInfo(insertModule);
                getModule(model.getGuid(), insertModule.getGuid(), sysVerGd);
            }
        }
    }

    @Override
    public BaseRes AddSysVerInfo01(SysVerInfoSaveDto req) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        if (req.getSysVerRd() == null) {
            throw new SystemException("EEEE", "请传输必需字段！");
        }
        SysVerInfo sysVerInfo = sysVerDao.SelectSysVerInfoByRuid(req.getSysVerRd());
        if (sysVerInfo.getStatus().equals("00")) {
            throw new SystemException("EEEE", "当前版本处于激活状态不予删除！");
        }
        List<ModuleInfo> moduleInfos = moduleDAO.SelectAllChildModuleAdminBySysVerGd("/", sysVerInfo.getGuid());
        for (ModuleInfo model : moduleInfos) {
            List<ResInfo> resInfoList = resDAO.SelectAllModuleAdmin(model.getGuid());
            for (ResInfo resModel : resInfoList) {
                resDAO.DeleteResInfoByModuleGd(model.getGuid());
                resOptDAO.DeleteResOptByResGd(resModel.getGuid());
            }
            delModule(model.getGuid());
        }
        moduleDAO.DeleteModuleBySysVerGd(sysVerInfo.getGuid());
        sysVerDao.DelSysverInfo(req.getSysVerRd());
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    //删除对应版本下的菜单信息
    public void delModule(String parentGd) {
        List<ModuleInfo> moduleInfos = moduleDAO.SelectAllChildModuleAdmin(parentGd);
        if (moduleInfos != null) {
            for (ModuleInfo model : moduleInfos) {
                List<ResInfo> resInfoList = resDAO.SelectAllModuleAdmin(model.getGuid());
                if (resInfoList != null && resInfoList.size() > 0) {
                    for (ResInfo resModel : resInfoList) {
                        resOptDAO.DeleteResOptByResGd(resModel.getGuid());
                    }
                }
                resDAO.DeleteResInfoByModuleGd(model.getGuid());
                delModule(model.getGuid());
            }
        }
    }

    @Override
    public BaseRes AddSysVerInfo03(SysVerInfoSaveDto req) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        SysVerInfo sysVerInfo = sysVerDao.SelectSysVerInfoByRuid(req.getSysVerRd());
        if (sysVerInfo == null) {
            throw new SystemException("EEEE", "暂无版本信息！");
        }
        List<ModuleInfo> moduleInfos = moduleDAO.SelectAllBySysVerGd(sysVerInfo.getGuid());
        if (moduleInfos == null || moduleInfos.size() < 1) {
            throw new SystemException("EEEE", "此版本下无菜单信息，不允许激活版本！");
        }
        List<SysVerInfo> sysVerInfoList = sysVerDao.SelectAllByStatus("00");
        for (SysVerInfo model : sysVerInfoList) {
            sysVerDao.UpSysverInfo(model.getRuid(), "01");
        }
        sysVerDao.UpSysverInfo(req.getSysVerRd(), "00");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public BaseRes GetAllSysVerInfo(SysVerInfoSaveDto req) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        if (req != null) {
            if (req.getStatus() != null && !req.getStatus().equals("")) {
                List<SysVerInfo> sysVerInfoList = sysVerDao.SelectAllByStatus(req.getStatus());
                baseResBody.setData(sysVerInfoList);
                baseResBody.setMsgCode("0x00000");
                baseResBody.setMsgDes("成功");
                baseResponse.setBody(baseResBody);
                return baseResponse;
            }
            if (req.getSysVerRd() != null) {
                SysVerInfo sysVerInfo = sysVerDao.SelectSysVerInfoByRuid(req.getSysVerRd());
                baseResBody.setData(sysVerInfo);
                baseResBody.setMsgCode("0x00000");
                baseResBody.setMsgDes("成功");
                baseResponse.setBody(baseResBody);
                return baseResponse;
            }
        } else {
            List<SysVerInfo> sysVerInfoList = sysVerDao.SelectSysVerInfoAll();
            List<SysVerInfoSaveDto> response = new ArrayList<>();
            for (SysVerInfo model : sysVerInfoList) {
                SysVerInfoSaveDto sysVerInfoSaveDto = new SysVerInfoSaveDto();
                sysVerInfoSaveDto.setStatus(model.getStatus());
                sysVerInfoSaveDto.setSysVerRd(model.getRuid());
                sysVerInfoSaveDto.setSysVerName(model.getSysVerName());
                response.add(sysVerInfoSaveDto);
            }
            baseResBody.setData(response);
            baseResBody.setMsgCode("0x00000");
            baseResBody.setMsgDes("成功");
            baseResponse.setBody(baseResBody);
            return baseResponse;
        }
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }


    @Override
    public BaseRes GetAllResInfo() {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        List<ResInfo> resInfoList = resDAO.SelectAllResInfoByGW();
        baseResBody.setData(resInfoList);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }
}
