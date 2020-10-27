package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.GetAllRoleInfo.GetAllRoleInfoRes;
import pnc.mesadmin.dto.GetAllRoleInfo.GetAllRoleInfoResB;
import pnc.mesadmin.dto.GetAllRoleInfo.GetAllRoleInfoResD;
import pnc.mesadmin.dto.GetRoleInfo.*;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveRoleInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.RoleIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by test on 2017/5/9.
 */
@Transactional
@Service
public class RoleService implements RoleIService {

    @Resource
    private RoleDAO roleDAO;

    @Resource
    private RolePmDAO rolePmDAO;

    @Resource
    private ModuleDAO moduleDAO;

    @Resource
    private ResDAO resDAO;

    @Resource
    private ResOptDAO resOptDAO;

    @Resource
    private OptDAO optDAO;

    @Resource
    private ScanPVDAO scanPVDAO;

    @Resource
    private BaseIService baseIService;

    @Resource
    private ExecPVDAO execPVDAO;

    @Resource
    private SysVerDao sysVerDao;

    @Override
    public RoleInfo selectRoleIdList(String ruid) {
        return roleDAO.selectRoleIdList(ruid);
    }

    @Override
    public GetAllRoleInfoRes getRoleInfoList(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException {

        GetAllRoleInfoRes objEGetAllRoleInfoRes = new GetAllRoleInfoRes();
        GetAllRoleInfoResB objEGetAllRoleInfoRB = new GetAllRoleInfoResB();
        List<GetAllRoleInfoResD> objEGetAllRoleInfoRBDTList = new ArrayList<GetAllRoleInfoResD>();

        List<RoleInfo> roleInfoList = baseIService.QALLBaseInfo(RoleDAO.class, "SelectRoleInfoList",
                argInitDataFields, argPageInfo);


        if (roleInfoList != null && roleInfoList.size() >= 1) {
            for (RoleInfo roleInfo : roleInfoList) {
                GetAllRoleInfoResD getAllRoleInfoResData = new GetAllRoleInfoResD();
                getAllRoleInfoResData.setRoleRd(roleInfo.getRuid());
                getAllRoleInfoResData.setRoleName(roleInfo.getRoleName());
                objEGetAllRoleInfoRBDTList.add(getAllRoleInfoResData);
            }
        }

        objEGetAllRoleInfoRB.setData(objEGetAllRoleInfoRBDTList);

        objEGetAllRoleInfoRes.setBody(objEGetAllRoleInfoRB);

        return objEGetAllRoleInfoRes;
    }

    /**
     * 查询角色-权限信息
     *
     * @param busData00
     * @return
     * @throws SystemException
     */
    @Override
    public GetRoleInfoRes getRoleInfo(GetRoleInfoBusData00 busData00) throws SystemException {
        GetRoleInfoRes objEGetRoleInfoRes = new GetRoleInfoRes();
        GetRoleInfoResB objEGetRoleInfoRB = new GetRoleInfoResB();
        GetRoleInfoResD objEGetRoleInfoRBDT = new GetRoleInfoResD();
        List<GetRoleInfoResDMenuInfo> getRoleInfoRBDTMList = new ArrayList<GetRoleInfoResDMenuInfo>();
        List<GetRoleInfoResDScanPVInfo> objEGetRoleInfoResDScanPVInfos = new ArrayList<GetRoleInfoResDScanPVInfo>();
        List<GetRoleInfoResDScanPVInfo> objExecPVInfoResp = new ArrayList<GetRoleInfoResDScanPVInfo>();
        GetRoleInfoResDScanPVInfo objEGetRoleInfoResDScanPVInfo = null;
        GetRoleInfoResDScanPVInfo objGetRoleInfoRespExecPVInfo = null;

        boolean flag = true;
        String roleGd = "";
        if (0 == busData00.getRoleRd()) {
            flag = false;
        }

        if (flag) {
            //查询该角色信息
            RoleInfo roleInfo = roleDAO.SelectRoleInfoByruid(busData00.getRoleRd());

            if (roleInfo == null) {
                throw new SystemException("MG000001", "该角色不存在");
            }
            roleGd = roleInfo.getGuid();
            objEGetRoleInfoRBDT.setRoleRd(roleInfo.getRuid());
            objEGetRoleInfoRBDT.setRoleName(roleInfo.getRoleName());
            objEGetRoleInfoRBDT.setCreator(roleInfo.getCreator());
            objEGetRoleInfoRBDT.setCreateTime(DateUtil.format(roleInfo.getCreateTime()));
            objEGetRoleInfoRBDT.setLastModifyTime(DateUtil.format(roleInfo.getLastModifyTime()));
            objEGetRoleInfoRBDT.setLastModifyMan(roleInfo.getLastModifyMan());
            objEGetRoleInfoRBDT.setRemark(roleInfo.getRemark());

            //查询ScanPVInfo
            objEGetRoleInfoResDScanPVInfo = getScanPVInfo(scanPVDAO.SelectByOpertMFlag(roleInfo.getGuid(), "00"), "00");
            if (objEGetRoleInfoResDScanPVInfo != null) {
                objEGetRoleInfoResDScanPVInfos.add(objEGetRoleInfoResDScanPVInfo);
            }

            objEGetRoleInfoResDScanPVInfo = getScanPVInfo(scanPVDAO.SelectByOpertMFlag(roleInfo.getGuid(), "01"), "01");
            if (objEGetRoleInfoResDScanPVInfo != null) {
                objEGetRoleInfoResDScanPVInfos.add(objEGetRoleInfoResDScanPVInfo);
            }

            objEGetRoleInfoResDScanPVInfo = getScanPVInfo(scanPVDAO.SelectByOpertMFlag(roleInfo.getGuid(), "02"), "02");
            if (objEGetRoleInfoResDScanPVInfo != null) {
                objEGetRoleInfoResDScanPVInfos.add(objEGetRoleInfoResDScanPVInfo);
            }

            objEGetRoleInfoResDScanPVInfo = getScanPVInfo(scanPVDAO.SelectByOpertMFlag(roleInfo.getGuid(), "03"), "03");
            if (objEGetRoleInfoResDScanPVInfo != null) {
                objEGetRoleInfoResDScanPVInfos.add(objEGetRoleInfoResDScanPVInfo);
            }

            //查询车间现场执行系统权限信息
            objGetRoleInfoRespExecPVInfo = getexecPVInfos(execPVDAO.SelectByOpertMFlag(roleInfo.getGuid(), "00"), "00");
            if (objGetRoleInfoRespExecPVInfo != null) {
                objExecPVInfoResp.add(objGetRoleInfoRespExecPVInfo);
            }

        }
        //获取当前系统激活版本
        List<SysVerInfo> sysVerInfos = sysVerDao.SelectAllByStatus("00");
        if (sysVerInfos.size() != 1) {
            throw new SystemException("EEEE", "请检查当前系统版本激活状态！");
        }
        List<ModuleInfo> moduleInfos = new ArrayList<>();
        //查询所有的板块
        if ("system".equals(CommonUtils.readUser().getUserName())) {
            moduleInfos = moduleDAO.SelectAllChildModuleAdminBySysVerGd("/", sysVerInfos.get(0).getGuid());
        } else {
            moduleInfos = moduleDAO.SelectAllChildModule("/", "01", sysVerInfos.get(0).getGuid());
        }

        if (moduleInfos == null || moduleInfos.size() <= 0) {
            throw new SystemException("MG000001", "模块信息不存在");
        }

        getRoleInfoRBDTMList = getMenuList(moduleInfos, roleGd);

        objEGetRoleInfoRBDT.setMenuInfo(getRoleInfoRBDTMList);
        objEGetRoleInfoRBDT.setScanPVInfo(objEGetRoleInfoResDScanPVInfos);
        objEGetRoleInfoRBDT.setExecPVInfo(objExecPVInfoResp);

        objEGetRoleInfoRB.setData(objEGetRoleInfoRBDT);
        objEGetRoleInfoRes.setBody(objEGetRoleInfoRB);

        return objEGetRoleInfoRes;
    }

    /**
     * 新增角色-权限信息
     *
     * @param busData00
     * @return
     * @throws SystemException
     */
    @Override
    public SaveRoleInfoRes insert(SaveRoleInfoReqBD00 busData00) throws SystemException {

        SaveRoleInfoRes objESaveRoleInfoRes = new SaveRoleInfoRes();
        SaveRoleInfoResB objESaveRoleInfoRB = new SaveRoleInfoResB();
        List<SaveRoleInfoResD> objESaveRoleInfoRBDTList = new ArrayList<SaveRoleInfoResD>();

        //填充Role对象，等待增加-->roleinfo
        RoleInfo roleInfo = new RoleInfo();
        //role随机id
        String roleInfoId = CommonUtils.getRandomNumber();
        //当前用户名
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();
        if ("".equals(busData00.getRoleName())) {
            throw new SystemException("", "角色名称不能为空");
        }
        //根据名称查询角色
        RoleInfo objERoleInfo = roleDAO.SelectByRoleName(busData00.getRoleName());
        if (objERoleInfo != null) {
            throw new SystemException("", "角色名称已经存在");
        }

        roleInfo.setGuid(roleInfoId);
        roleInfo.setRoleName(busData00.getRoleName());
        roleInfo.setCreator(userName);
        roleInfo.setCreateTime(date);
        roleInfo.setLastModifyTime(date);
        roleInfo.setLastModifyMan(userName);
        roleInfo.setRemark(busData00.getRemark());

        if (roleDAO.InsertRoleInfo(roleInfo) <= 0) {
            throw new SystemException("MG000001", "新增用户失败");
        }

        //-->rolepminfo
        for (SaveRoleInfoReqBD00MenuInfo menuInfo : busData00.getMenuInfo()) {

            for (SaveRoleInfoReqBD00MResInfo resInfo : menuInfo.getResInfo()) {

                if (resInfo.getOptInfo() != null && resInfo.getOptInfo().size() > 0) {

                    RolePmInfo rolePmInfo = new RolePmInfo();

                    //查询资源表
                    ResInfo objEResInfo = resDAO.SelectResByRuid(resInfo.getResRd());
                    if (objEResInfo == null) {
                        throw new SystemException("", "资源不存在");
                    }

                    ModuleInfo moduleInfo = moduleDAO.SelectModuleInfoRuid(menuInfo.getModuleRd());
                    if (moduleInfo == null) {
                        throw new SystemException("MG000001", "查询模块失败");
                    }

                    rolePmInfo.setGuid(CommonUtils.getRandomNumber());
                    rolePmInfo.setRoleGd(roleInfoId);

                    rolePmInfo.setResGd(objEResInfo.getGuid());

                    rolePmInfo.setModuleGd(moduleInfo.getGuid());

                    rolePmInfo.setCreator(userName);
                    rolePmInfo.setCreateTime(date);
                    rolePmInfo.setLastModifyTime(date);
                    rolePmInfo.setLastModifyMan(userName);
                    rolePmInfo.setRemark(busData00.getRemark());

                    for (SaveRoleInfoReqBD00MROptInfo optInfo : resInfo.getOptInfo()) {

                        rolePmInfo.setGuid(CommonUtils.getRandomNumber());

                        ResOptInfo resOptInfo = resOptDAO.SelectResOptInfoRuid(optInfo.getOptRd());
                        if (resOptInfo == null) {
                            throw new SystemException("MG000001", "查询角色操作失败");
                        }
                        rolePmInfo.setResOptGd(resOptInfo.getGuid());

                        if (rolePmDAO.InsertRolePmInfo(rolePmInfo) <= 0) {
                            throw new SystemException("MG000001", "新增用户失败");
                        }
                    }
                }
            }

        }

        //新增Scanner权限信息
        List<SaveRoleInfoReqBD00ScanPVInfo> objESaveRoleInfoReqBD00ScanPVInfos = busData00.getScanPVInfo();
        List<SaveRoleInfoReqBD00SPVIInfo> objESaveRoleInfoReqBD00SPVIInfos = null;
        ScanPVInfo scanPVInfo = null;
        if (objESaveRoleInfoReqBD00ScanPVInfos != null) {
            for (int i = 0, length = objESaveRoleInfoReqBD00ScanPVInfos.size(); i < length; i++) {

                objESaveRoleInfoReqBD00SPVIInfos = objESaveRoleInfoReqBD00ScanPVInfos.get(i).getPVIInfo();
                if (objESaveRoleInfoReqBD00SPVIInfos != null) {

                    for (SaveRoleInfoReqBD00SPVIInfo spviInfo : objESaveRoleInfoReqBD00SPVIInfos) {
                        scanPVInfo = new ScanPVInfo();
                        scanPVInfo.setGuid(CommonUtils.getRandomNumber());
                        scanPVInfo.setRoleGd(roleInfoId);
                        scanPVInfo.setOpertMFlag(objESaveRoleInfoReqBD00ScanPVInfos.get(i).getOpertMFlag());
                        scanPVInfo.setpVFlag(spviInfo.getPVFlag());
                        scanPVInfo.setCreator(userName);
                        scanPVInfo.setCreateTime(date);
                        scanPVDAO.InsertScanPV(scanPVInfo);
                    }
                }
            }
        }

        //新增车间现场执行系统权限信息
        List<SaveRoleInfoReqBD00ScanPVInfo> ExecPVInfoList = busData00.getExecPVInfo();
        if (ExecPVInfoList.size() > 0) {
            for (SaveRoleInfoReqBD00ScanPVInfo ExModel : ExecPVInfoList) {
                if (ExModel.getPVIInfo().size() > 0) {
                    for (SaveRoleInfoReqBD00SPVIInfo PVModel : ExModel.getPVIInfo()) {
                        ExecPVInfo execPVInfo = new ExecPVInfo();
                        execPVInfo.setGuid(CommonUtils.getRandomNumber());
                        execPVInfo.setRoleGd(roleInfoId);
                        execPVInfo.setOpertMFlag(ExModel.getOpertMFlag());
                        execPVInfo.setPVFlag(PVModel.getPVFlag());
                        execPVInfo.setCreator(userName);
                        execPVInfo.setCreateTime(date);
                        execPVDAO.InsertExecpvinfo(execPVInfo);
                    }

                }
            }
        }

        objESaveRoleInfoRB.setData(objESaveRoleInfoRBDTList);
        objESaveRoleInfoRes.setBody(objESaveRoleInfoRB);

        return objESaveRoleInfoRes;
    }

    /**
     * 删除角色
     *
     * @param busData01
     * @return
     * @throws SystemException
     */
    @Override
    public SaveRoleInfoRes remove(SaveRoleInfoReqBD01 busData01) throws SystemException {

        SaveRoleInfoRes objESaveRoleInfoRes = new SaveRoleInfoRes();
        SaveRoleInfoResB objESaveRoleInfoRB = new SaveRoleInfoResB();

        //查询该角色信息
        RoleInfo roleInfo = roleDAO.SelectRoleInfoByruid(busData01.getRoleRd());

        if (roleInfo == null) {
            throw new SystemException("MG000001", "该角色不存在");
        }

        List<RolePmInfo> objERolePmInfos = rolePmDAO.SelectRolePmInfo(roleInfo.getGuid());
        if (objERolePmInfos != null && objERolePmInfos.size() > 0) {
            if (rolePmDAO.DeleteRolePmInfo(roleInfo.getGuid()) <= 0) {
                throw new SystemException("MG000002", "删除角色失败");
            }
        }

        scanPVDAO.DeleteByRoleGd(roleInfo.getGuid());

        if (roleDAO.DeleteRoleInfo(busData01.getRoleRd()) <= 0) {
            throw new SystemException("MG000003", "删除角色失败");
        }

        objESaveRoleInfoRes.setBody(objESaveRoleInfoRB);

        return objESaveRoleInfoRes;
    }

    /**
     * 保存角色-权限信息
     *
     * @param busData02
     * @return
     * @throws SystemException
     */
    @Override
    public SaveRoleInfoRes save(SaveRoleInfoReqBD02 busData02) throws SystemException {

        SaveRoleInfoRes objESaveRoleInfoRes = new SaveRoleInfoRes();
        SaveRoleInfoResB objESaveRoleInfoRB = new SaveRoleInfoResB();

        //填充Role对象，等待修改-->roleinfo
        RoleInfo roleInfo = new RoleInfo();
        //当前用户名
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();

        //待修改角色名
        String roleName = busData02.getRoleName();

        if ("".equals(roleName)) {
            throw new SystemException("", "角色名称不能为空");
        }

        RoleInfo roleInfo1 = roleDAO.SelectRoleInfoByruid(busData02.getRoleRd());
        if (roleInfo1 == null) {
            throw new SystemException("", "该角色信息不存在");
        }

        //根据名称查询角色
        RoleInfo objERoleInfo = roleDAO.SelectByRoleName(roleName);
        if (objERoleInfo != null && !roleName.equals(roleInfo1.getRoleName())) {
            throw new SystemException("", "角色名称已经存在");
        }

        roleInfo.setGuid(roleInfo1.getGuid());
        roleInfo.setRoleName(busData02.getRoleName());
        roleInfo.setLastModifyTime(date);
        roleInfo.setLastModifyMan(userName);
        roleInfo.setRemark(busData02.getRemark());

        if (roleDAO.UpdateRoleInfo(roleInfo) <= 0) {
            throw new SystemException("MG000001", "修改角色信息失败");
        }

        //获取当前角色所有操作
        List<RolePmInfo> rolePmInfos = rolePmDAO.SelectRolePmInfo(roleInfo1.getGuid());

        for (SaveRoleInfoReqBD02MenuInfo menuInfo : busData02.getMenuInfo()) {
            for (SaveRoleInfoReqBD02MResInfo resInfo : menuInfo.getResInfo()) {

                for (SaveRoleInfoReqBD02MROptInfo optInfo : resInfo.getOptInfo()) {

                    //根据Rd查Gd
                    ResOptInfo resOptInfo = resOptDAO.SelectResOptInfoRuid(optInfo.getOptRd());
                    if (resOptInfo == null) {
                        throw new SystemException("MG000001", "查询角色操作失败");
                    }

                    boolean flag = true;

                    //判断该操作现在所属的状态
                    if (rolePmInfos != null) {
                        for (int i = 0; i < rolePmInfos.size(); i++) {
                            if (rolePmInfos.get(i).getResOptGd().equals(resOptInfo.getGuid())) {
                                //去除操作
                                rolePmInfos.remove(i);
                                flag = false;
                                break;
                            }
                        }
                    }

                    if (flag) {
                        //新增
                        RolePmInfo rolePmInfo = new RolePmInfo();
                        rolePmInfo.setGuid(CommonUtils.getRandomNumber());
                        rolePmInfo.setRoleGd(roleInfo1.getGuid());
                        ModuleInfo moduleInfo = moduleDAO.SelectModuleInfoRuid(menuInfo.getModuleRd());
                        if (moduleInfo == null) {
                            throw new SystemException("MG000001", "查询模块失败");
                        }
                        rolePmInfo.setModuleGd(moduleInfo.getGuid());

                        rolePmInfo.setResGd(resOptInfo.getResGd());

                        rolePmInfo.setResOptGd(resOptInfo.getGuid());
                        rolePmInfo.setCreator(userName);
                        rolePmInfo.setCreateTime(date);
                        rolePmInfo.setLastModifyTime(date);
                        rolePmInfo.setLastModifyMan(userName);
                        rolePmInfo.setRemark(busData02.getRemark());
                        if (rolePmDAO.InsertRolePmInfo(rolePmInfo) <= 0) {
                            throw new SystemException("MG000001", "新增角色操作失败");
                        }
                    }

                }
            }
        }

        for (int i = 0; i < rolePmInfos.size(); i++) {
            rolePmDAO.DeleteByGuid(rolePmInfos.get(i).getGuid());
        }

        //删除Scanner信息
        scanPVDAO.DeleteByRoleGd(roleInfo.getGuid());/* <= 0){
            throw new SystemException("MG000003", "修改失败");
        }*/

        //新增Scanner权限信息
        List<SaveRoleInfoReqBD02ScanPVInfo> objESaveRoleInfoReqBD02ScanPVInfos = busData02.getScanPVInfo();
        List<SaveRoleInfoReqBD02SPVIInfo> objESaveRoleInfoReqBD02SPVIInfos = null;
        ScanPVInfo scanPVInfo = null;
        if (objESaveRoleInfoReqBD02ScanPVInfos != null) {
            for (int i = 0, length = objESaveRoleInfoReqBD02ScanPVInfos.size(); i < length; i++) {

                objESaveRoleInfoReqBD02SPVIInfos = objESaveRoleInfoReqBD02ScanPVInfos.get(i).getPVIInfo();
                if (objESaveRoleInfoReqBD02SPVIInfos != null) {

                    for (SaveRoleInfoReqBD02SPVIInfo spviInfo : objESaveRoleInfoReqBD02SPVIInfos) {
                        scanPVInfo = new ScanPVInfo();
                        scanPVInfo.setGuid(CommonUtils.getRandomNumber());
                        scanPVInfo.setRoleGd(roleInfo.getGuid());
                        scanPVInfo.setOpertMFlag(objESaveRoleInfoReqBD02ScanPVInfos.get(i).getOpertMFlag());
                        scanPVInfo.setpVFlag(spviInfo.getPVFlag());
                        scanPVInfo.setCreator(userName);
                        scanPVInfo.setCreateTime(date);
                        scanPVDAO.InsertScanPV(scanPVInfo);
                    }
                }
            }
        }

        //删除车间
        execPVDAO.DeleteByRoleGd(roleInfo.getGuid());
        //新增车间现场执行系统权限信息
        List<SaveRoleInfoReqBD00ScanPVInfo> ExecPVInfoList = busData02.getExecPVInfo();
        if (ExecPVInfoList.size() > 0) {
            for (SaveRoleInfoReqBD00ScanPVInfo ExModel : ExecPVInfoList) {
                if (ExModel.getPVIInfo().size() > 0) {
                    for (SaveRoleInfoReqBD00SPVIInfo PVModel : ExModel.getPVIInfo()) {
                        ExecPVInfo execPVInfo = new ExecPVInfo();
                        execPVInfo.setGuid(CommonUtils.getRandomNumber());
                        execPVInfo.setRoleGd(roleInfo.getGuid());
                        execPVInfo.setOpertMFlag(ExModel.getOpertMFlag());
                        execPVInfo.setPVFlag(PVModel.getPVFlag());
                        execPVInfo.setCreator(userName);
                        execPVInfo.setCreateTime(date);
                        execPVDAO.InsertExecpvinfo(execPVInfo);
                    }

                }
            }
        }

        objESaveRoleInfoRes.setBody(objESaveRoleInfoRB);

        return objESaveRoleInfoRes;
    }

    /**
     * 获取ScanPVInfo
     *
     * @param scanPVInfos
     * @param opertMFlag
     * @return
     */
    public GetRoleInfoResDScanPVInfo getScanPVInfo(List<ScanPVInfo> scanPVInfos, String opertMFlag) {

        GetRoleInfoResDScanPVInfo objEGetRoleInfoResDScanPVInfo = null;
        List<GetRoleInfoResDSPVIInfo> objEGetRoleInfoResDSPVIInfos = null;
        GetRoleInfoResDSPVIInfo objEGetRoleInfoResDSPVIInfo = null;

        if (scanPVInfos != null && scanPVInfos.size() > 0) {
            objEGetRoleInfoResDScanPVInfo = new GetRoleInfoResDScanPVInfo();
            objEGetRoleInfoResDSPVIInfos = new ArrayList<GetRoleInfoResDSPVIInfo>();
            objEGetRoleInfoResDScanPVInfo.setOpertMFlag(opertMFlag);

            for (int i = 0, length = scanPVInfos.size(); i < length; i++) {
                objEGetRoleInfoResDSPVIInfo = new GetRoleInfoResDSPVIInfo();
                objEGetRoleInfoResDSPVIInfo.setPVFlag(scanPVInfos.get(i).getpVFlag());
                objEGetRoleInfoResDSPVIInfos.add(objEGetRoleInfoResDSPVIInfo);
            }

            objEGetRoleInfoResDScanPVInfo.setPVIInfo(objEGetRoleInfoResDSPVIInfos);
        }

        return objEGetRoleInfoResDScanPVInfo;
    }

    /**
     * 获取ExecPVInfo
     *
     * @param execPVInfos
     * @param opertMFlag
     * @return
     */
    public GetRoleInfoResDScanPVInfo getexecPVInfos(List<ExecPVInfo> execPVInfos, String opertMFlag) {

        GetRoleInfoResDScanPVInfo objEGetRoleInfoResDScanPVInfo = null;
        List<GetRoleInfoResDSPVIInfo> objEGetRoleInfoResDSPVIInfos = null;
        GetRoleInfoResDSPVIInfo objEGetRoleInfoResDSPVIInfo = null;

        if (execPVInfos != null && execPVInfos.size() > 0) {
            objEGetRoleInfoResDScanPVInfo = new GetRoleInfoResDScanPVInfo();
            objEGetRoleInfoResDSPVIInfos = new ArrayList<GetRoleInfoResDSPVIInfo>();
            objEGetRoleInfoResDScanPVInfo.setOpertMFlag(opertMFlag);

            for (int i = 0, length = execPVInfos.size(); i < length; i++) {
                objEGetRoleInfoResDSPVIInfo = new GetRoleInfoResDSPVIInfo();
                objEGetRoleInfoResDSPVIInfo.setPVFlag(execPVInfos.get(i).getPVFlag());
                objEGetRoleInfoResDSPVIInfos.add(objEGetRoleInfoResDSPVIInfo);
            }

            objEGetRoleInfoResDScanPVInfo.setPVIInfo(objEGetRoleInfoResDSPVIInfos);
        }

        return objEGetRoleInfoResDScanPVInfo;
    }

    /**
     * 获取模块下面的资源
     *
     * @param moduleInfos
     * @param roleGd
     * @return
     */
    public List<GetRoleInfoResDMenuInfo> getMenuList(List<ModuleInfo> moduleInfos, String roleGd) {

        List<GetRoleInfoResDMenuInfo> objEGetRoleInfoResDMenuInfos = new ArrayList<GetRoleInfoResDMenuInfo>();
        GetRoleInfoResDMenuInfo objEGetRoleInfoResDMenuInfo = null;

        for (ModuleInfo moduleInfo : moduleInfos) {
            List<GetRoleInfoResDMResInfo> getRoleInfoResResInfoList = new ArrayList<GetRoleInfoResDMResInfo>();

            objEGetRoleInfoResDMenuInfo = new GetRoleInfoResDMenuInfo();

            objEGetRoleInfoResDMenuInfo.setModuleRd(moduleInfo.getRuid());
            objEGetRoleInfoResDMenuInfo.setModuleName(moduleInfo.getModuleName());

            List<ResInfo> resInfos = new ArrayList<>();
            //查询模块下面的资源
            if ("system".equals(CommonUtils.readUser().getUserName())) {
                resInfos = resDAO.SelectAllModuleAdmin(moduleInfo.getGuid());
            } else {
                resInfos = resDAO.SelectAllModule(moduleInfo.getGuid(), "01");
            }
            if (resInfos != null && resInfos.size() > 0) {
                for (ResInfo resInfo : resInfos) {
                    GetRoleInfoResDMResInfo ri = new GetRoleInfoResDMResInfo();
                    ri.setResRd(resInfo.getRuid());
                    ri.setResName(resInfo.getResName());

                    List<GetRoleInfoResDMROptInfo> getRoleInfoResOptInfoList = new ArrayList<GetRoleInfoResDMROptInfo>();

                    //查询资源的操作
                    List<ResOptInfo> resOptInfos = resOptDAO.SelectResOptInfo(resInfo.getGuid());

                    if (resOptInfos != null && resOptInfos.size() > 0) {

                        for (ResOptInfo resOptInfo : resOptInfos) {
                            GetRoleInfoResDMROptInfo getRoleInfoResOptInfo = new GetRoleInfoResDMROptInfo();

                            //查询操作
                            OptInfo optInfo = optDAO.SelectOptInfo(resOptInfo.getOptGd());
                            if (optInfo == null) {
                                throw new SystemException("MG000001", "操作查询失败");
                            }
                            getRoleInfoResOptInfo.setOptRd(resOptInfo.getRuid());
                            //查询并设置对应操作的名称
                            getRoleInfoResOptInfo.setOptName(optInfo.getOptName());

                            //判断该资源操作是否拥有

                            //查角色权限操作表中有没有该角色的权限操作信息
                            if (!"".equals(roleGd)) {
                                if (rolePmDAO.SelectOptGd(resOptInfo.getGuid(), roleGd) != null) {
                                    getRoleInfoResOptInfo.setChecked("00");
                                } else {
                                    getRoleInfoResOptInfo.setChecked("01");
                                }
                            } else {
                                getRoleInfoResOptInfo.setChecked("01");
                            }

                            getRoleInfoResOptInfoList.add(getRoleInfoResOptInfo);
                        }

                        ri.setOptInfo(getRoleInfoResOptInfoList);
                    }

                    getRoleInfoResResInfoList.add(ri);
                }
            }

            //获取当前系统激活版本
            List<SysVerInfo> sysVerInfos = sysVerDao.SelectAllByStatus("00");
            if (sysVerInfos.size() != 1) {
                throw new SystemException("EEEE", "请检查当前系统版本激活状态！");
            }
            //判断该模块下面是否还存在子模块
            List<ModuleInfo> objEChildModules = new ArrayList<>();
            if ("system".equals(CommonUtils.readUser().getUserName())) {
                objEChildModules = moduleDAO.SelectAllChildModuleAdmin(moduleInfo.getGuid());
            } else {
                objEChildModules = moduleDAO.SelectAllChildModule(moduleInfo.getGuid(), "01", sysVerInfos.get(0).getGuid());
            }
            if (objEChildModules != null || objEChildModules.size() > 0) {
                //存在子模块
                objEGetRoleInfoResDMenuInfo.setMenuInfo(getMenuList(objEChildModules, roleGd));
            }

            objEGetRoleInfoResDMenuInfo.setResInfo(getRoleInfoResResInfoList);

            objEGetRoleInfoResDMenuInfos.add(objEGetRoleInfoResDMenuInfo);
        }

        return objEGetRoleInfoResDMenuInfos;
    }
}
