package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.GetMODevInfo.*;
import pnc.mesadmin.dto.SaveMODevInfo.SaveMODevInfoReqBD00;
import pnc.mesadmin.dto.SaveMODevInfo.SaveMODevInfoReqBD00EoBaInfo;
import pnc.mesadmin.dto.SaveMODevInfo.SaveMODevInfoRes;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.MODevIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：装料管理逻辑
 * 创建人：潘俊峰
 * 创建时间：2017-08-29
 * 修改人：
 * 修改时间：
 */
@Service
@Transactional
public class MODevService implements MODevIService {

    @Resource
    private MODevDAO moDevDAO;

    @Resource
    private MODevDlDAO moDevDlDAO;

    @Resource
    private BDAO bdao;

    @Resource
    private MaVerDAO maVerDAO;

    @Resource
    private SpecVerDAO specVerDAO;

    @Resource
    private DevDAO devDAO;

    @Resource
    private BomVerDAO bomVerDAO;

    @Resource
    private BomMaterialDAO bomMaterialDAO;

    //获取装料信息
    public GetMODevInfoRes GetMODev(GetMODevInfoReqBD00 argBD00) {

        GetMODevInfoRes objEGetMODevInfoRes = new GetMODevInfoRes();
        GetMODevInfoResB objEGetMODevInfoResB = new GetMODevInfoResB();
        List<GetMODevInfoResD> objEGetMODevInfoResDs = new ArrayList<GetMODevInfoResD>();

        //查询批次
        BInfo objEBInfo = bdao.selectBatchInfoByBatch(argBD00.getBatch());
        if(objEBInfo == null){
            throw new SystemException("", "批次不存在");
        }
        //判断该批次信息的状态
        if("02".equals(objEBInfo.getStatus())){
            throw new SystemException("", "该批次已冻结");
        }
        if("03".equals(objEBInfo.getStatus())){
            throw new SystemException("", "该批次已报废");
        }
        if("04".equals(objEBInfo.getStatus())){
            throw new SystemException("", "该批次已完成");
        }

        //查询物料
        MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(objEBInfo.getMaVerGd());
        if(objEMaVerInfo == null){
            throw new SystemException("", "物料不存在");
        }
        //查询工序
        SpecVerInfo objESpecVerInfo = specVerDAO.SelectSpecVerInfoByruid(argBD00.getSpecVerRd());
        if(objESpecVerInfo == null){
            throw new SystemException("", "工序不存在");
        }
        //查询设备
        DevInfo objEDevInfo = devDAO.SelectBydevRd(argBD00.getDevRd());
        if(objEDevInfo == null){
            throw new SystemException("", "设备不存在");
        }
        if("01".equals(objESpecVerInfo.getStatus())){
            throw new SystemException("", "该工序不可用");
        }

        //查询当前设备存在物料信息
        List<MODevInfo> objEMODevInfos = moDevDAO.SelectByDevGd(objEDevInfo.getGuid());
        //数据转换
        Map<String, MODevInfo> map = new HashMap<String, MODevInfo>();
        for(int i=0, length=objEMODevInfos.size(); i<length; i++){
            map.put(objEMODevInfos.get(i).getMaVerGd(), objEMODevInfos.get(i));
        }

        //物料清单
        //根据物料查Bom清单版本
        BomVerInfo objEBomVerInfo = bomVerDAO.SelectBomVerInfo("");
        if(objEBomVerInfo != null){
            //根据清单版本查用料
            List<BomMaterialInfo> objEBomMaterialInfos = bomMaterialDAO.SelectByBomVerGd(objEBomVerInfo.getGuid());
            if(objEBomMaterialInfos != null && objEBomMaterialInfos.size() > 0){

                GetMODevInfoResD objEGetMODevInfoResD = null;

                List<GetMODevInfoResDEoBaInfo> eoBaInfos = null;
                GetMODevInfoResDEoBaInfo eoBaInfo = null;
                BInfo bInfo = null;

                for(BomMaterialInfo bomMaterialInfo : objEBomMaterialInfos){
                    //过滤当前工序用料
                    if(objESpecVerInfo.getGuid().equals(bomMaterialInfo.getSpecVerGd())){
                        //查询物料
                        MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(bomMaterialInfo.getMaVerGd());
                        if(maVerInfo == null){
                            throw new SystemException("", "Bom所需用料不存在");
                        }

                        objEGetMODevInfoResD = new GetMODevInfoResD();
                        objEGetMODevInfoResD.setMaVerRd(maVerInfo.getRuid());
                        objEGetMODevInfoResD.setMaCode(maVerInfo.getMaterialCode());
                        objEGetMODevInfoResD.setMaName(maVerInfo.getMaterialName());
                        objEGetMODevInfoResD.setDoNum(bomMaterialInfo.getNum() * objEBInfo.getNum());
                        objEGetMODevInfoResD.setUnitName(bomMaterialInfo.getUnitName());
                        if(map.containsKey(maVerInfo.getGuid())){

                            MODevInfo moDevInfo = map.get(maVerInfo.getGuid());
                            //存量
                            objEGetMODevInfoResD.setEoNum(moDevInfo.getNum());

                            List<MODevDlInfo> moDevDlInfos = moDevDlDAO.SelectByMODevGd(moDevInfo.getGuid());
                            if(moDevDlInfos != null){

                                eoBaInfos = new ArrayList<GetMODevInfoResDEoBaInfo>();
                                for(int i=0, length=moDevDlInfos.size(); i<length; i++){
                                    //查询批次
                                    bInfo = bdao.selectBatchInfoByBatch(moDevDlInfos.get(i).getBatch());
                                    if(bInfo != null){
                                        eoBaInfo = new GetMODevInfoResDEoBaInfo();
                                        eoBaInfo.setBatch(bInfo.getBatch());
                                        eoBaInfo.setNum(bInfo.getCanNum());
                                        eoBaInfos.add(eoBaInfo);
                                    }
                                }
                            }
                        }else{
                            objEGetMODevInfoResD.setEoNum(0f);
                        }

                        objEGetMODevInfoResD.setEoBaInfo(eoBaInfos);
                        objEGetMODevInfoResDs.add(objEGetMODevInfoResD);
                    }
                }
            }
        }

        objEGetMODevInfoResB.setData(objEGetMODevInfoResDs);
        objEGetMODevInfoRes.setBody(objEGetMODevInfoResB);

        return objEGetMODevInfoRes;
    }

    //装料操作信息
    public SaveMODevInfoRes AddMODev(SaveMODevInfoReqBD00 argBD00) {

        SaveMODevInfoRes objESaveMODevInfoRes = new SaveMODevInfoRes();

        String userName = CommonUtils.readUser().getRealName();
        Date date = new Date();

        //查询设备
        DevInfo objEDevInfo = devDAO.SelectBydevRd(argBD00.getDevRd());
        if(objEDevInfo == null){
            throw new SystemException("", "设备不存在");
        }
        //物料
        MaVerInfo objEMaVerInfo = null;
        //批次
        BInfo objEBInfo = null;
        //物料上机
        MODevInfo objEMODevInfo = null;

        float allNum = 0f;

        List<SaveMODevInfoReqBD00EoBaInfo> eoBaInfos = argBD00.getEoBaInfo();
        if(eoBaInfos != null){

            SaveMODevInfoReqBD00EoBaInfo objEEoBaInfo = null;

            for(int i=0, length=eoBaInfos.size(); i<length; i++){

                objEEoBaInfo = eoBaInfos.get(i);

                //查询物料
                objEMaVerInfo = maVerDAO.SelectByRuid(objEEoBaInfo.getMaVerRd());
                if(objEMaVerInfo == null){
                    throw new SystemException("", "物料不存在");
                }
                //查询批次
                objEBInfo = bdao.selectBatchInfoByBatch(objEEoBaInfo.getBatch());
                if(objEBInfo == null){
                    throw new SystemException("", objEEoBaInfo.getBatch() + "批次不存在");
                }
                //更改批次状态（处理中）
                if(!"00".equals(objEBInfo.getStatus())){
                    throw new SystemException("", objEEoBaInfo.getBatch() + "批次不可用");
                }
                objEBInfo.setStatus("01");
                if(bdao.UpdateBatchInfoByRuid(objEBInfo) <= 0){
                    throw new SystemException("", "装料出错");
                }

                //查询物料上机
                objEMODevInfo = moDevDAO.SelectByDevMa(objEDevInfo.getGuid(), objEMaVerInfo.getGuid());
                if(objEMODevInfo == null){ //新增
                    objEMODevInfo = new MODevInfo();
                    objEMODevInfo.setGuid(CommonUtils.getRandomNumber());
                    objEMODevInfo.setDevGd(objEDevInfo.getGuid());
                    objEMODevInfo.setMaVerGd(objEMaVerInfo.getGuid());
                    objEMODevInfo.setMaterialCode(objEMaVerInfo.getMaterialCode());
                    objEMODevInfo.setMaterialName(objEMaVerInfo.getMaterialName());
                    objEMODevInfo.setNum(objEBInfo.getCanNum());
                    objEMODevInfo.setUnitName(eoBaInfos.get(i).getUnitName());
                    objEMODevInfo.setCreator(userName);
                    objEMODevInfo.setCreateTime(date);
                    moDevDAO.InsertMODev(objEMODevInfo);

                }else{ //修改
                    //判断该批次是否已经在设备上
                    if(moDevDlDAO.SelectByMB(objEMODevInfo.getGuid(), eoBaInfos.get(i).getBatch()) != null){
                        throw new SystemException("", eoBaInfos.get(i).getBatch() + "批次已经存在设备中");
                    }

                    objEMODevInfo.setNum(objEMODevInfo.getNum() + objEBInfo.getCanNum());
                    objEMODevInfo.setLastModifyMan(userName);
                    objEMODevInfo.setLastModifyTime(date);
                    if(moDevDAO.UpdateMODev(objEMODevInfo) <= 0){
                        throw new SystemException("", "上料失败");
                    }
                }

                //新增物料上机明细
                MODevDlInfo objEMODevDlInfo = new MODevDlInfo();
                objEMODevDlInfo.setGuid(CommonUtils.getRandomNumber());
                objEMODevDlInfo.setmODevGd(objEMODevInfo.getGuid());
                objEMODevDlInfo.setBatch(objEBInfo.getBatch());
                objEMODevDlInfo.setCreator(userName);
                objEMODevDlInfo.setCreateTime(date);
                moDevDlDAO.InsertMODevDl(objEMODevDlInfo);
            }
        }

        return objESaveMODevInfoRes;
    }
}
