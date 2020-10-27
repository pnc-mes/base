package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.SaveDExLogInfo.SaveDExLogInfoReq00;
import pnc.mesadmin.dto.SaveDExLogInfo.SaveDExLogInfoReq01;
import pnc.mesadmin.dto.SaveDExLogInfo.SaveDExLogInfoRes;
import pnc.mesadmin.dto.SaveDExLogInfo.SaveDExLogInfoResB;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.DevMonitorIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/10/22 17:50
 * @Description:
 */
@Transactional
@Service
public class DevMonitorService implements DevMonitorIService {

    @Resource
    private DevExpectLogDAO devExpectLogDAO;

    @Resource
    private LineDao lineDao;

    @Resource
    private SpecVerDAO specVerDAO;

    @Resource
    private StationDao stationDao;

    @Resource
    private DevDAO devDAO;

    @Resource
    private DevMapInfoDAO devMapInfoDAO;

    @Override
    public SaveDExLogInfoRes AddSaveDExLogInfoRes(SaveDExLogInfoReq00 saveDExLogInfoReq00) {
        SaveDExLogInfoRes saveDExLogInfoRes=new SaveDExLogInfoRes();
        SaveDExLogInfoResB saveDExLogInfoResB=new SaveDExLogInfoResB();

        if(saveDExLogInfoReq00.getLineGd()==null||"".equals(saveDExLogInfoReq00.getLineGd())){
            throw new SystemException("xxx","线体标识不能为空");
        }
        if(saveDExLogInfoReq00.getSpecVerGd()==null||"".equals(saveDExLogInfoReq00.getSpecVerGd())){
            throw new SystemException("xxx","工序版本标识不能为空");
        }
        if(saveDExLogInfoReq00.getStationGd()==null||"".equals(saveDExLogInfoReq00.getStationGd())){
            throw new SystemException("xxx","工位标识不能为空");
        }
        if(saveDExLogInfoReq00.getDevGd()==null||"".equals(saveDExLogInfoReq00.getDevGd())){
            throw new SystemException("xxx","设备标识不能为空");
        }
        if(saveDExLogInfoReq00.getDevMapGd()==null||"".equals(saveDExLogInfoReq00.getDevMapGd())){
            throw new SystemException("xxx","设备映射标识不能为空");
        }
        if(saveDExLogInfoReq00.getMsg()==null||"".equals(saveDExLogInfoReq00.getMsg())){
            throw new SystemException("xxx","设备消息不能为空");
        }

        DevExpectLogInfo devExpectLogInfo=new DevExpectLogInfo();
        devExpectLogInfo.setGuid(CommonUtils.getRandomNumber());

        Lineinfo lineinfo=lineDao.SelectLineInfoByguid(saveDExLogInfoReq00.getLineGd());
        if(lineinfo!=null){
            devExpectLogInfo.setLineName(lineinfo.getLineName());
        }else {
            throw new SystemException("xxx","线体不存在");
        }

        SpecVerInfo specVerInfo=specVerDAO.SelectSpecVerInfoByguid(saveDExLogInfoReq00.getSpecVerGd());
        if(specVerInfo!=null){
            devExpectLogInfo.setSpecName(specVerInfo.getSpecName());
        }else {
            throw new SystemException("xxx","工序不存在");
        }

        StationInfo stationInfo=stationDao.SelectByGuId(saveDExLogInfoReq00.getStationGd());
        if(stationInfo!=null){
            devExpectLogInfo.setStationName(stationInfo.getStationName());
        }else {
            throw new SystemException("xxx","工位不存在");
        }

        DevInfo devInfo=devDAO.SelectByguid(saveDExLogInfoReq00.getDevGd());
        if(devInfo!=null){
            devExpectLogInfo.setDevCode(devInfo.getDevCode());
            devExpectLogInfo.setDevName(devInfo.getDevName());
        }else {
            throw new SystemException("xxx","设备不存在");
        }

        DevMapInfo devMapInfo=devMapInfoDAO.SelectGuidDevMap(saveDExLogInfoReq00.getDevMapGd());
        if(devMapInfo!=null){
            devExpectLogInfo.setDevMapCode(devMapInfo.getDevMapCode());
            devExpectLogInfo.setDevMapName(devMapInfo.getDevMapName());
        }else {
            throw new SystemException("xxx","设备映射不存在");
        }

        devExpectLogInfo.setMsg(saveDExLogInfoReq00.getMsg());
        devExpectLogInfo.setCreator(CommonUtils.readUser().getRealName());
        devExpectLogInfo.setCreateTime(new Date());
        devExpectLogDAO.InsertDevExpectLogInfo(devExpectLogInfo);
        saveDExLogInfoRes.setBody(saveDExLogInfoResB);
        return saveDExLogInfoRes;
    }

    @Override
    public SaveDExLogInfoRes AddSaveDExLogInfoReq01(SaveDExLogInfoReq01 saveDExLogInfoReq01) {
        SaveDExLogInfoRes saveDExLogInfoRes=new SaveDExLogInfoRes();
        SaveDExLogInfoResB saveDExLogInfoResB=new SaveDExLogInfoResB();

        BCirExcpInfo bCirExcpInfo=new BCirExcpInfo();
        if(saveDExLogInfoReq01.getBatch()==null||"".equals(saveDExLogInfoReq01.getBatch())){
            bCirExcpInfo.setBatch(saveDExLogInfoReq01.getBatch());
            bCirExcpInfo.setMsg(saveDExLogInfoReq01.getMsg());
            bCirExcpInfo.setRemark(saveDExLogInfoReq01.getMsg());
        }else {
            bCirExcpInfo.setBatch(saveDExLogInfoReq01.getBatch());
            bCirExcpInfo.setMsg(saveDExLogInfoReq01.getMsg());
        }

        bCirExcpInfo.setGuid(CommonUtils.getRandomNumber());

        Lineinfo lineinfo=lineDao.SelectLineInfoByguid(saveDExLogInfoReq01.getLineGd());
        if(lineinfo!=null){
            bCirExcpInfo.setLineGd(lineinfo.getGuid());
            bCirExcpInfo.setLineName(lineinfo.getLineName());
        }else {
            bCirExcpInfo.setLineGd("");
            bCirExcpInfo.setLineName("");
        }


        SpecVerInfo specVerInfo=specVerDAO.SelectSpecVerInfoByguid(saveDExLogInfoReq01.getSpecVerGd());
        if(specVerInfo!=null){
            bCirExcpInfo.setSpecVerGd(specVerInfo.getGuid());
            bCirExcpInfo.setSpecName(specVerInfo.getSpecName());
        }else {
            bCirExcpInfo.setSpecVerGd("");
            bCirExcpInfo.setSpecName("");
        }

        StationInfo stationInfo=stationDao.SelectByGuId(saveDExLogInfoReq01.getStationGd());
        if(stationInfo!=null){
            bCirExcpInfo.setStationGd(stationInfo.getGuid());
            bCirExcpInfo.setStationName(stationInfo.getStationName());
        }else {
            bCirExcpInfo.setStationGd("");
            bCirExcpInfo.setStationName("");
        }
        bCirExcpInfo.setExecor(saveDExLogInfoReq01.getExecor());
        bCirExcpInfo.setCreator("system");
        bCirExcpInfo.setCreateTime(new Date());
        devExpectLogDAO.InsertBCirExcpInfo(bCirExcpInfo);
        saveDExLogInfoRes.setBody(saveDExLogInfoResB);
        return saveDExLogInfoRes;
    }
}
