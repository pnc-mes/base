package pnc.mesadmin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：物料版本信息表实体类
 * 创建人：潘俊峰
 * 创建时间：2017-06-01
 * 修改人：
 * 修改时间：
 */
@TableName(value = "tpm_maverinfo")
public class MaVerInfo {
    private int ruid;
    private String guid;
    private String maGd;
    private String companyCode;
    private String materialCode;
    private String mapMaCode;
    private String materialName;
    private String materialDes;
    private String version;
    private String isDef;
    private String materialType;
    private String maTypeGd;
    private String pdFamilyGd;
    private String bomGd;
    private String serialRuleGd;
    private String unitGd;
    private String suName;
    private String suMaCode;
    private String cusMaCode;
    private String shelflife;
    @TableField(value = "`interval`")
    private int interval;
    private String sUnit;
    private String disRule;
    private float minSNum;
    private float maxSNum;
    private String reMaterial;
    private String isExem;
    private String trayPackGd;
    private String boxPackGd;
    private String dSource;
    private String status;
    private String f1;
    private String f2;
    private String f3;
    private String f4;
    private String f5;
    private String creator;
    private Date createTime;
    private String lastModifyMan;
    private Date lastModifyTime;
    private String remark;
    private String brand;
    private String orderNum;
    private String isBatch;
    private String maBarCode;
    private float minPQ;
    private String printTGd;
    private String OnLineMaPeriodGd;

    public String getOnLineMaPeriodGd() {
        return OnLineMaPeriodGd;
    }

    public void setOnLineMaPeriodGd(String onLineMaPeriodGd) {
        OnLineMaPeriodGd = onLineMaPeriodGd;
    }

    public String  getMaterialDes() {
        return materialDes;
    }

    public void setMaterialDes(String materialDes) {
        this.materialDes = materialDes;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public int getRuid() {
        return ruid;
    }

    public void setRuid(int ruid) {
        this.ruid = ruid;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getMaGd() {
        return maGd;
    }

    public void setMaGd(String maGd) {
        this.maGd = maGd;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIsDef() {
        return isDef;
    }

    public void setIsDef(String isDef) {
        this.isDef = isDef;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getPdFamilyGd() {
        return pdFamilyGd;
    }

    public void setPdFamilyGd(String pdFamilyGd) {
        this.pdFamilyGd = pdFamilyGd;
    }


    public String getBomGd() {
        return bomGd;
    }

    public void setBomGd(String bomGd) {
        this.bomGd = bomGd;
    }

    public String getSerialRuleGd() {
        return serialRuleGd;
    }

    public void setSerialRuleGd(String serialRuleGd) {
        this.serialRuleGd = serialRuleGd;
    }

    public String getUnitGd() {
        return unitGd;
    }

    public void setUnitGd(String unitGd) {
        this.unitGd = unitGd;
    }

    public String getShelflife() {
        return shelflife;
    }

    public void setShelflife(String shelflife) {
        this.shelflife = shelflife;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public String getsUnit() {
        return sUnit;
    }

    public void setsUnit(String sUnit) {
        this.sUnit = sUnit;
    }

    public String getReMaterial() {
        return reMaterial;
    }

    public void setReMaterial(String reMaterial) {
        this.reMaterial = reMaterial;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastModifyMan() {
        return lastModifyMan;
    }

    public void setLastModifyMan(String lastModifyMan) {
        this.lastModifyMan = lastModifyMan;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSuMaCode() {
        return suMaCode;
    }

    public void setSuMaCode(String suMaCode) {
        this.suMaCode = suMaCode;
    }

    public String getIsExem() {
        return isExem;
    }

    public void setIsExem(String isExem) {
        this.isExem = isExem;
    }

    public String getMaTypeGd() {
        return maTypeGd;
    }

    public void setMaTypeGd(String maTypeGd) {
        this.maTypeGd = maTypeGd;
    }

    public String getSuName() {
        return suName;
    }

    public void setSuName(String suName) {
        this.suName = suName;
    }

    public String getCusMaCode() {
        return cusMaCode;
    }

    public void setCusMaCode(String cusMaCode) {
        this.cusMaCode = cusMaCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDisRule() {
        return disRule;
    }

    public void setDisRule(String disRule) {
        this.disRule = disRule;
    }

    public float getMinSNum() {
        return minSNum;
    }

    public void setMinSNum(float minSNum) {
        this.minSNum = minSNum;
    }

    public float getMaxSNum() {
        return maxSNum;
    }

    public void setMaxSNum(float maxSNum) {
        this.maxSNum = maxSNum;
    }

    public String getTrayPackGd() {
        return trayPackGd;
    }

    public void setTrayPackGd(String trayPackGd) {
        this.trayPackGd = trayPackGd;
    }

    public String getBoxPackGd() {
        return boxPackGd;
    }

    public void setBoxPackGd(String boxPackGd) {
        this.boxPackGd = boxPackGd;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getMapMaCode() {
        return mapMaCode;
    }

    public void setMapMaCode(String mapMaCode) {
        this.mapMaCode = mapMaCode;
    }

    public String getdSource() {
        return dSource;
    }

    public void setdSource(String dSource) {
        this.dSource = dSource;
    }

    public String getF1() {
        return f1;
    }

    public void setF1(String f1) {
        this.f1 = f1;
    }

    public String getF2() {
        return f2;
    }

    public void setF2(String f2) {
        this.f2 = f2;
    }

    public String getF3() {
        return f3;
    }

    public void setF3(String f3) {
        this.f3 = f3;
    }

    public String getF4() {
        return f4;
    }

    public void setF4(String f4) {
        this.f4 = f4;
    }

    public String getF5() {
        return f5;
    }

    public void setF5(String f5) {
        this.f5 = f5;
    }

    public String getIsBatch() {
        return isBatch;
    }

    public void setIsBatch(String isBatch) {
        this.isBatch = isBatch;
    }

    public String getMaBarCode() {
        return maBarCode;
    }

    public void setMaBarCode(String maBarCode) {
        this.maBarCode = maBarCode;
    }

    public float getMinPQ() {
        return minPQ;
    }

    public void setMinPQ(float minPQ) {
        this.minPQ = minPQ;
    }

    public String getPrintTGd() {
        return printTGd;
    }

    public void setPrintTGd(String printTGd) {
        this.printTGd = printTGd;
    }
}
