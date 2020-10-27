package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllPackSPInfo.GetAllPackSPInfoResB;
import pnc.mesadmin.dto.GetAllPackSPInfo.GetAllPackSPInfoResD;
import pnc.mesadmin.dto.GetPackSpecificationInfo.GetPackSpecificationInfoResB;
import pnc.mesadmin.dto.GetPackSpecificationInfo.GetPackSpecificationInfoResD;
import pnc.mesadmin.dto.GetPackSpecificationInfo.GetPackSpecificationInfoResDPTInfo;
import pnc.mesadmin.dto.GetPackSpecificationInfo.GetPackSpecificationInfoResDSNInfo;
import pnc.mesadmin.dto.SavePackSpecificationInfo.SavePackSpecificationInfoReq00;
import pnc.mesadmin.dto.SavePackSpecificationInfo.SavePackSpecificationInfoReq02;
import pnc.mesadmin.dto.SavePackSpecificationInfo.SavePackSpecificationInfoResB;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.PackSpecificationIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;
import pnc.mesadmin.utils.FastfdsUtils;
import pnc.mesadmin.entity.common.ColorGearInfo;
import pnc.mesadmin.entity.common.CurrentGearInfo;
import pnc.mesadmin.entity.common.GradeInfo;
import pnc.mesadmin.entity.common.PowerGearInfo;
import pnc.mesadmin.utils.MyPage;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by test on 2017/8/21.
 */
@Service
@Transactional
public class PackSpecificationService implements PackSpecificationIService {

    @Resource
    private PackSpecificationDAO packSpecificationDAO;

    @Resource
    private PrintTDAO printTDAO;

    @Resource
    private SerialRuleDAO serialRuleDAO;

    @Resource
    private BaseIService baseIService;

    @Resource
    private PowerGearDAO powerGearDAO;

    @Resource
    private CurrentGearDAO currentGearDAO;

    @Resource
    private ColorGearDAO colorGearDAO;

    @Resource
    private GradeDAO gradeDAO;

    @Resource
    private PackSpPropertyDAO packSpPropertyDAO;

    @Resource
    private MaVerDAO maVerDAO;

    public GetAllPackSPInfoResB GetAllMPKInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllPackSPInfoResB objGetAllPackSPInfoResB = new GetAllPackSPInfoResB();

        List<PackSpecificationInfo> packSpecificationInfoList = baseIService.QALLBaseInfo(PackSpecificationDAO.class, "SelectAllPackSpecification",
                argInitDataFields, argPageInfo);
        List<GetAllPackSPInfoResD> getAllPackSPInfoResDList = new ArrayList<GetAllPackSPInfoResD>(Collections.<GetAllPackSPInfoResD>emptyList());
        if(packSpecificationInfoList!=null && packSpecificationInfoList.size()>0) {
            GetAllPackSPInfoResD objGetAllPackSPInfoResD = null;
            for (PackSpecificationInfo ps : packSpecificationInfoList) {
                objGetAllPackSPInfoResD = new GetAllPackSPInfoResD();
                objGetAllPackSPInfoResD.setMPRd(ps.getRuid());
                objGetAllPackSPInfoResD.setMPName(ps.getPackName());
                getAllPackSPInfoResDList.add(objGetAllPackSPInfoResD);
            }
        }

        objGetAllPackSPInfoResB.setData(getAllPackSPInfoResDList);
        objGetAllPackSPInfoResB.setMsgCode("0x00000");
        objGetAllPackSPInfoResB.setMsgDes("成功");
        return objGetAllPackSPInfoResB;
    }

    /**
     * 获取包装规则列表信息(新)
     * @param req
     * @return
     */
    @Override
    public PageResult<GetAllPackSPInfoResD> GetAllMPKNew(BaseRequest req) {
        List<GetAllPackSPInfoResD> resDList = new ArrayList<GetAllPackSPInfoResD>();
        GetAllPackSPInfoResD resD = null;

        IPage<PackSpecificationInfo> iPage = packSpecificationDAO.selectPage(new MyPage(req), CommonUtils.getQueryWrapper(req.getFields()));

        for (PackSpecificationInfo ps : iPage.getRecords()) {
            resD = new GetAllPackSPInfoResD();
            resD.setMPRd(ps.getRuid());
            resD.setMPName(ps.getPackName());
            resDList.add(resD);
        }

        return new PageResult<>(iPage, resDList);
    }

    // 查询包装规格信息
    public GetPackSpecificationInfoResB GetMPKInfoByRuid(int mpRd) {
        GetPackSpecificationInfoResB objGetPackSpecificationInfoResB = new GetPackSpecificationInfoResB();
        GetPackSpecificationInfoResD objGetPackSpecificationInfoResD = new GetPackSpecificationInfoResD();
        GetPackSpecificationInfoResDPTInfo objGetPackSpecificationInfoResDPTInfo = new GetPackSpecificationInfoResDPTInfo();
        GetPackSpecificationInfoResDSNInfo objGetPackSpecificationInfoResDSNInfo = new GetPackSpecificationInfoResDSNInfo();

        // 查询包装规格信息
        PackSpecificationInfo packSpecificationInfo = packSpecificationDAO.SelectPackSpecificationByRuid(mpRd);

        if(packSpecificationInfo !=null) {
            PrintTInfo printTInfo = printTDAO.SelectByGuid(packSpecificationInfo.getPTGd());

            if(printTInfo!=null) {
                objGetPackSpecificationInfoResDPTInfo.setPTRd(printTInfo.getRuid());
                objGetPackSpecificationInfoResDPTInfo.setTempName(printTInfo.getTempName());
                objGetPackSpecificationInfoResDPTInfo.setFileName(FastfdsUtils.readProps() + printTInfo.getFileName());
                objGetPackSpecificationInfoResD.setPTInfo(objGetPackSpecificationInfoResDPTInfo);
            }

            SerialRuleInfo serialRuleInfo = serialRuleDAO.SelectSerialRuleInfoByguid(packSpecificationInfo.getSerialRuleGd());

            if(serialRuleInfo!=null) {
                objGetPackSpecificationInfoResDSNInfo.setSNRd(serialRuleInfo.getRuid());
                objGetPackSpecificationInfoResDSNInfo.setSNName(serialRuleInfo.getRuleName());
                objGetPackSpecificationInfoResD.setSNInfo(objGetPackSpecificationInfoResDSNInfo);
            }
            objGetPackSpecificationInfoResD.setMPRd(packSpecificationInfo.getRuid());
            objGetPackSpecificationInfoResD.setMPName(packSpecificationInfo.getPackName());
            objGetPackSpecificationInfoResD.setPackType(packSpecificationInfo.getPackType());
            objGetPackSpecificationInfoResD.setNum(packSpecificationInfo.getNum());
            objGetPackSpecificationInfoResD.setWeight(packSpecificationInfo.getWeight());
            objGetPackSpecificationInfoResD.setUpLimit(packSpecificationInfo.getUpLimit());
            objGetPackSpecificationInfoResD.setDownLimit(packSpecificationInfo.getDownLimit());
            objGetPackSpecificationInfoResD.setUnitName(packSpecificationInfo.getUnitName());
            objGetPackSpecificationInfoResD.setCreator(packSpecificationInfo.getCreator());
            objGetPackSpecificationInfoResD.setCreateTime(DateUtil.format(packSpecificationInfo.getCreateTime()));
            objGetPackSpecificationInfoResD.setLastModifyMan(packSpecificationInfo.getLastModifyMan());
            objGetPackSpecificationInfoResD.setLastModifyTime(DateUtil.format(packSpecificationInfo.getLastModifyTime()));
            objGetPackSpecificationInfoResD.setRemark(packSpecificationInfo.getRemark());
            objGetPackSpecificationInfoResD.setIsProperty(packSpecificationInfo.getIsProperty());
            objGetPackSpecificationInfoResD.setStartPower(packSpecificationInfo.getStartPower());
            objGetPackSpecificationInfoResD.setEndPower(packSpecificationInfo.getEndPower());
            PowerGearInfo powerGearInfo = powerGearDAO.selectPowerGearInfoByGuid(packSpecificationInfo.getPowerGd());
            if(powerGearInfo != null) {
                objGetPackSpecificationInfoResD.setPowerRd(powerGearInfo.getRuid());
                objGetPackSpecificationInfoResD.setPowerName(powerGearInfo.getPowerName());
            }
            CurrentGearInfo currentGearInfo = currentGearDAO.selectCurrentGearInfoByGuid(packSpecificationInfo.getCurrentGd());
            if(currentGearInfo != null){
                objGetPackSpecificationInfoResD.setCurrentRd(currentGearInfo.getRuid());
                objGetPackSpecificationInfoResD.setCurrentName(currentGearInfo.getCurrentName());
            }
            ColorGearInfo colorGearInfo = colorGearDAO.selectColorGearInfoByGuid(packSpecificationInfo.getColorGd());
            if(colorGearInfo != null){
                objGetPackSpecificationInfoResD.setColorRd(colorGearInfo.getRuid());
                objGetPackSpecificationInfoResD.setColorName(colorGearInfo.getColorName());
            }
            GradeInfo gradeInfo = gradeDAO.selecGradeInfoByGuid(packSpecificationInfo.getGradeGd());
            if(gradeInfo != null){
                objGetPackSpecificationInfoResD.setGradeRd(gradeInfo.getRuid());
                objGetPackSpecificationInfoResD.setGradeName(gradeInfo.getGradeName());
            }
            MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(packSpecificationInfo.getMaVerGd());
            if(maVerInfo != null){
                objGetPackSpecificationInfoResD.setMaRd(maVerInfo.getRuid());
                objGetPackSpecificationInfoResD.setMaName(maVerInfo.getMaterialName());
                objGetPackSpecificationInfoResD.setMaCode(maVerInfo.getMaterialCode());
            }

            //List<PackSpPropertyInfo> powers = packSpPropertyDAO.SelectByPackSpGdAndType(packSpecificationInfo.getGuid(), "00");
            List<PackSpPropertyInfo> currents = packSpPropertyDAO.SelectByPackSpGdAndType(packSpecificationInfo.getGuid(), "01");
            List<PackSpPropertyInfo> colors = packSpPropertyDAO.SelectByPackSpGdAndType(packSpecificationInfo.getGuid(), "02");
            List<PackSpPropertyInfo> grades = packSpPropertyDAO.SelectByPackSpGdAndType(packSpecificationInfo.getGuid(), "03");
            List<PackSpPropertyInfo> mas = packSpPropertyDAO.SelectByPackSpGdAndType(packSpecificationInfo.getGuid(), "04");
            List<GetPackSpecificationInfoResD.Property> powerps = new ArrayList<>();
            List<GetPackSpecificationInfoResD.Property> currentps = new ArrayList<>();
            List<GetPackSpecificationInfoResD.Property> colorps = new ArrayList<>();
            List<GetPackSpecificationInfoResD.Property> gradeps = new ArrayList<>();
            List<GetPackSpecificationInfoResD.Property> maps = new ArrayList<>();
            GetPackSpecificationInfoResD.Property property = null;
            /*for(PackSpPropertyInfo p : powers){
                property = new GetPackSpecificationInfoResD.Property();
                powerGearInfo = powerGearDAO.selectPowerGearInfoByGuid(p.getPropertyGd());
                if(powerGearInfo == null){
                    continue;
                }
                property.setId(powerGearInfo.getRuid());
                property.setName(powerGearInfo.getPowerName());
                powerps.add(property);
            }*/
            for(PackSpPropertyInfo p : currents){
                property = new GetPackSpecificationInfoResD.Property();
                currentGearInfo = currentGearDAO.selectCurrentGearInfoByGuid(p.getPropertyGd());
                if(currentGearInfo == null){
                    continue;
                }
                property.setId(currentGearInfo.getRuid());
                property.setName(currentGearInfo.getCurrentName());
                currentps.add(property);
            }
            for(PackSpPropertyInfo p : colors){
                property = new GetPackSpecificationInfoResD.Property();
                colorGearInfo = colorGearDAO.selectColorGearInfoByGuid(p.getPropertyGd());
                if(colorGearInfo == null){
                    continue;
                }
                property.setId(colorGearInfo.getRuid());
                property.setName(colorGearInfo.getColorName());
                colorps.add(property);
            }
            for(PackSpPropertyInfo p : grades){
                property = new GetPackSpecificationInfoResD.Property();
                gradeInfo = gradeDAO.selecGradeInfoByGuid(p.getPropertyGd());
                if(gradeInfo == null){
                    continue;
                }
                property.setId(gradeInfo.getRuid());
                property.setName(gradeInfo.getGradeName());
                gradeps.add(property);
            }
            for(PackSpPropertyInfo p : mas){
                property = new GetPackSpecificationInfoResD.Property();
                maVerInfo = maVerDAO.SelectMaverInfo(p.getPropertyGd());
                if(maVerInfo == null){
                    continue;
                }
                property.setId(maVerInfo.getRuid());
                property.setName(maVerInfo.getMaterialName());
                maps.add(property);
            }
            objGetPackSpecificationInfoResD.setPowerProperty(powerps);
            objGetPackSpecificationInfoResD.setCurrentProperty(currentps);
            objGetPackSpecificationInfoResD.setColorProperty(colorps);
            objGetPackSpecificationInfoResD.setGradeProperty(gradeps);
            objGetPackSpecificationInfoResD.setMaProperty(maps);
        }

        objGetPackSpecificationInfoResB.setData(objGetPackSpecificationInfoResD);
        objGetPackSpecificationInfoResB.setMsgCode("0x00000");
        objGetPackSpecificationInfoResB.setMsgDes("成功");
        return objGetPackSpecificationInfoResB;
    }
    //新增
    public SavePackSpecificationInfoResB AddPackSpecification(SavePackSpecificationInfoReq00 busData00) {
        SavePackSpecificationInfoResB objSavePackSpecificationInfoResB = new SavePackSpecificationInfoResB();
        PackSpecificationInfo packSpecificationInfo = new PackSpecificationInfo();
        if("".equals(busData00.getMPName())){
            throw new SystemException("0x00001","名称不能为空");
        }
        // 判断是否有重名的
        int count = packSpecificationDAO.SelectPackSpecificationByName(busData00.getMPName());
        if(count > 0){
            throw new SystemException("0x00001","该名称已存在");
        }
        // 新增
        packSpecificationInfo.setGuid(CommonUtils.getRandomNumber());
        packSpecificationInfo.setPackName(busData00.getMPName());
        packSpecificationInfo.setPackType(busData00.getPackType());
        packSpecificationInfo.setPTGd(printTDAO.SelectPrintTInfo(busData00.getPTRd()).getGuid());
        packSpecificationInfo.setSerialRuleGd(serialRuleDAO.SelectSerialRuleInfoByruid(busData00.getSNRd()).getGuid());
        packSpecificationInfo.setNum(busData00.getNum());
        packSpecificationInfo.setWeight(busData00.getWeight());
        packSpecificationInfo.setUpLimit(busData00.getUpLimit());
        packSpecificationInfo.setDownLimit(busData00.getDownLimit());
        packSpecificationInfo.setUnitName(busData00.getUnitName());
        packSpecificationInfo.setCreator(CommonUtils.readUser().getRealName());
        packSpecificationInfo.setCreateTime(new Date());
        packSpecificationInfo.setRemark(busData00.getRemark());

        if("00".equals(busData00.getIsProperty())) {
            PackSpPropertyInfo packSpPropertyInfo = null;
            for (SavePackSpecificationInfoReq00.Property property : busData00.getProperty()) {
                packSpPropertyInfo = new PackSpPropertyInfo();
                packSpPropertyInfo.setGuid(CommonUtils.getRandomNumber());
                packSpPropertyInfo.setPackSpGd(packSpecificationInfo.getGuid());
                if ("00".equals(property.getType())) {
                    //功率
                    PowerGearInfo powerGearInfo = powerGearDAO.selectPowerGearInfoByRuid(property.getId());
                    if (powerGearInfo == null) {
                        continue;
                    }
                    packSpPropertyInfo.setPropertyGd(powerGearInfo.getGuid());
                } else if ("01".equals(property.getType())) {
                    //电流
                    CurrentGearInfo currentGearInfo = currentGearDAO.selectCurrentGearInfoByRuid(property.getId());
                    if (currentGearInfo == null) {
                        continue;
                    }
                    packSpPropertyInfo.setPropertyGd(currentGearInfo.getGuid());
                } else if ("02".equals(property.getType())) {
                    //颜色
                    ColorGearInfo colorGearInfo = colorGearDAO.selectColorGearInfoByRuid(property.getId());
                    if (colorGearInfo == null) {
                        continue;
                    }
                    packSpPropertyInfo.setPropertyGd(colorGearInfo.getGuid());
                } else if ("03".equals(property.getType())) {
                    //等级
                    GradeInfo gradeInfo = gradeDAO.selecGradeInfoByRuid(property.getId());
                    if (gradeInfo == null) {
                        continue;
                    }
                    packSpPropertyInfo.setPropertyGd(gradeInfo.getGuid());
                } else if ("04".equals(property.getType())) {
                    //组件
                    MaVerInfo maVerInfo = maVerDAO.SelectByRuid(property.getId());
                    if (maVerInfo == null) {
                        continue;
                    }
                    packSpPropertyInfo.setPropertyGd(maVerInfo.getGuid());
                } else {
                    continue;
                }
                packSpPropertyInfo.setPropertyType(property.getType());
                packSpPropertyDAO.InsertPackSpProperty(packSpPropertyInfo);
            }

            PowerGearInfo powerGearInfo = powerGearDAO.selectPowerGearInfoByRuid(busData00.getPowerRd());
            if (powerGearInfo != null) {
                packSpecificationInfo.setPowerGd(powerGearInfo.getGuid());
            }
            CurrentGearInfo currentGearInfo = currentGearDAO.selectCurrentGearInfoByRuid(busData00.getCurrentRd());
            if (currentGearInfo != null) {
                packSpecificationInfo.setCurrentGd(currentGearInfo.getGuid());
            }
            ColorGearInfo colorGearInfo = colorGearDAO.selectColorGearInfoByRuid(busData00.getColorRd());
            if (colorGearInfo != null) {
                packSpecificationInfo.setColorGd(colorGearInfo.getGuid());
            }
            GradeInfo gradeInfo = gradeDAO.selecGradeInfoByRuid(busData00.getGradeRd());
            if (gradeInfo != null) {
                packSpecificationInfo.setGradeGd(gradeInfo.getGuid());
            }
            MaVerInfo maVerInfo = maVerDAO.SelectByRuid(busData00.getMaRd());
            if(maVerInfo != null){
                packSpecificationInfo.setMaVerGd(maVerInfo.getGuid());
            }
            packSpecificationInfo.setIsProperty("00");
            packSpecificationInfo.setStartPower(busData00.getStartPower());
            packSpecificationInfo.setEndPower(busData00.getEndPower());
        }else{
            packSpecificationInfo.setIsProperty("01");
        }

        if(packSpecificationDAO.InsertPackSpecification(packSpecificationInfo) <= 0){
            throw new SystemException("0x00001","新增失败");
        }

        objSavePackSpecificationInfoResB.setMsgCode("0x00000");
        objSavePackSpecificationInfoResB.setMsgDes("成功");
        return objSavePackSpecificationInfoResB;
    }
    // 删除
    public SavePackSpecificationInfoResB RmPackSpecification(String mpRd) {
        SavePackSpecificationInfoResB objSavePackSpecificationInfoResB = new SavePackSpecificationInfoResB();

        if(packSpecificationDAO.DeletePackSpecificationByRuid(mpRd) <= 0){
            throw new SystemException("0x00001","删除失败");
        }
        objSavePackSpecificationInfoResB.setMsgCode("0x00000");
        objSavePackSpecificationInfoResB.setMsgDes("成功");
        return objSavePackSpecificationInfoResB;
    }
    //修改
    public SavePackSpecificationInfoResB ModPackSpecification(SavePackSpecificationInfoReq02 busData02) {
        SavePackSpecificationInfoResB objSavePackSpecificationInfoResB = new SavePackSpecificationInfoResB();
        if("".equals(busData02.getMPName())){
            throw new SystemException("0x00001","名称不能为空");
        }
        //判断名称是否存在
        PackSpecificationInfo ps = new PackSpecificationInfo();
        ps.setRuid(busData02.getMPRd());
        ps.setPackName(busData02.getMPName());
        if(packSpecificationDAO.SelectPackSPByName(ps) > 0){
            throw new SystemException("0x00001","该名称已存在");
        }

        PackSpecificationInfo packSpecificationInfo = packSpecificationDAO.SelectPackSpecificationByRuid(busData02.getMPRd());
        packSpecificationInfo.setPackName(busData02.getMPName());
        packSpecificationInfo.setPackType(busData02.getPackType());
        packSpecificationInfo.setPTGd(printTDAO.SelectPrintTInfo(busData02.getPTRd()).getGuid());
        packSpecificationInfo.setSerialRuleGd(serialRuleDAO.SelectSerialRuleInfoByruid(busData02.getSNRd()).getGuid());
        packSpecificationInfo.setNum(busData02.getNum());
        packSpecificationInfo.setWeight(busData02.getWeight());
        packSpecificationInfo.setUpLimit(busData02.getUpLimit());
        packSpecificationInfo.setDownLimit(busData02.getDownLimit());
        packSpecificationInfo.setUnitName(busData02.getUnitName());
        packSpecificationInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        packSpecificationInfo.setLastModifyTime(new Date());
        packSpecificationInfo.setRemark(busData02.getRemark());

        packSpPropertyDAO.DeleteByPackSpGd(packSpecificationInfo.getGuid());
        if("00".equals(busData02.getIsProperty())) {
            PackSpPropertyInfo packSpPropertyInfo = null;
            for (SavePackSpecificationInfoReq02.Property property : busData02.getProperty()) {
                packSpPropertyInfo = new PackSpPropertyInfo();
                packSpPropertyInfo.setGuid(CommonUtils.getRandomNumber());
                packSpPropertyInfo.setPackSpGd(packSpecificationInfo.getGuid());
                if ("00".equals(property.getType())) {
                    //功率
                    PowerGearInfo powerGearInfo = powerGearDAO.selectPowerGearInfoByRuid(property.getId());
                    if (powerGearInfo == null) {
                        continue;
                    }
                    packSpPropertyInfo.setPropertyGd(powerGearInfo.getGuid());
                } else if ("01".equals(property.getType())) {
                    //电流
                    CurrentGearInfo currentGearInfo = currentGearDAO.selectCurrentGearInfoByRuid(property.getId());
                    if (currentGearInfo == null) {
                        continue;
                    }
                    packSpPropertyInfo.setPropertyGd(currentGearInfo.getGuid());
                } else if ("02".equals(property.getType())) {
                    //颜色
                    ColorGearInfo colorGearInfo = colorGearDAO.selectColorGearInfoByRuid(property.getId());
                    if (colorGearInfo == null) {
                        continue;
                    }
                    packSpPropertyInfo.setPropertyGd(colorGearInfo.getGuid());
                } else if ("03".equals(property.getType())) {
                    //等级
                    GradeInfo gradeInfo = gradeDAO.selecGradeInfoByRuid(property.getId());
                    if (gradeInfo == null) {
                        continue;
                    }
                    packSpPropertyInfo.setPropertyGd(gradeInfo.getGuid());
                } else if ("04".equals(property.getType())) {
                    //组件
                    MaVerInfo maVerInfo = maVerDAO.SelectByRuid(property.getId());
                    if (maVerInfo == null) {
                        continue;
                    }
                    packSpPropertyInfo.setPropertyGd(maVerInfo.getGuid());
                } else {
                    continue;
                }
                packSpPropertyInfo.setPropertyType(property.getType());
                packSpPropertyDAO.InsertPackSpProperty(packSpPropertyInfo);
            }

            PowerGearInfo powerGearInfo = powerGearDAO.selectPowerGearInfoByRuid(busData02.getPowerRd());
            if (powerGearInfo != null) {
                packSpecificationInfo.setPowerGd(powerGearInfo.getGuid());
            }else{
                packSpecificationInfo.setPowerGd(null);
            }
            CurrentGearInfo currentGearInfo = currentGearDAO.selectCurrentGearInfoByRuid(busData02.getCurrentRd());
            if (currentGearInfo != null) {
                packSpecificationInfo.setCurrentGd(currentGearInfo.getGuid());
            }else{
                packSpecificationInfo.setCurrentGd(null);
            }
            ColorGearInfo colorGearInfo = colorGearDAO.selectColorGearInfoByRuid(busData02.getColorRd());
            if (colorGearInfo != null) {
                packSpecificationInfo.setColorGd(colorGearInfo.getGuid());
            }else{
                packSpecificationInfo.setColorGd(null);
            }
            GradeInfo gradeInfo = gradeDAO.selecGradeInfoByRuid(busData02.getGradeRd());
            if (gradeInfo != null) {
                packSpecificationInfo.setGradeGd(gradeInfo.getGuid());
            }else{
                packSpecificationInfo.setGradeGd(null);
            }
            MaVerInfo maVerInfo = maVerDAO.SelectByRuid(busData02.getMaRd());
            if(maVerInfo != null){
                packSpecificationInfo.setMaVerGd(maVerInfo.getGuid());
            }else{
                packSpecificationInfo.setMaVerGd(null);
            }
            packSpecificationInfo.setIsProperty("00");
            packSpecificationInfo.setStartPower(busData02.getStartPower());
            packSpecificationInfo.setEndPower(busData02.getEndPower());
        }else {
            packSpecificationInfo.setPowerGd(null);
            packSpecificationInfo.setCurrentGd(null);
            packSpecificationInfo.setColorGd(null);
            packSpecificationInfo.setGradeGd(null);
            packSpecificationInfo.setIsProperty("01");
            packSpecificationInfo.setStartPower(0);
            packSpecificationInfo.setEndPower(0);
        }

        if(packSpecificationDAO.UpdatePackSpecification(packSpecificationInfo) <= 0){
            throw new SystemException("0x00001","修改失败");
        }
        objSavePackSpecificationInfoResB.setMsgCode("0x00000");
        objSavePackSpecificationInfoResB.setMsgDes("成功");
        return objSavePackSpecificationInfoResB;
    }

}
