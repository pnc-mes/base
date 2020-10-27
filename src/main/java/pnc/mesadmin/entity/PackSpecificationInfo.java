package pnc.mesadmin.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * Created by test on 2017/8/21.
 */
@TableName(value = "tpm_packspinfo")
public class PackSpecificationInfo {
    private int Ruid;
    private String Guid;
    private String PackName;
    private String PackType;
    private String PTGd;
    private String SerialRuleGd;
    private float Num;
    private float Weight;
    private float UpLimit;
    private float DownLimit;
    private String UnitName;
    private String creator;
    private Date createTime;
    private String lastModifyMan;
    private Date lastModifyTime;
    private String Remark;
    private String isProperty;//是否混包 00混包 01不混
    private String maVerGd;//组件类型
    private String powerGd;//唛头功率
    private String currentGd;//唛头电流
    private String colorGd;//唛头颜色
    private String gradeGd;//唛头等级
    private float startPower;//功率区间开始
    private float endPower;//功率区间结束

    public int getRuid() {
        return Ruid;
    }

    public void setRuid(int ruid) {
        Ruid = ruid;
    }

    public String getGuid() {
        return Guid;
    }

    public void setGuid(String guid) {
        Guid = guid;
    }

    public String getPackName() {
        return PackName;
    }

    public void setPackName(String packName) {
        PackName = packName;
    }

    public String getPackType() {
        return PackType;
    }

    public void setPackType(String packType) {
        PackType = packType;
    }

    public String getPTGd() {
        return PTGd;
    }

    public void setPTGd(String PTGd) {
        this.PTGd = PTGd;
    }

    public String getSerialRuleGd() {
        return SerialRuleGd;
    }

    public void setSerialRuleGd(String serialRuleGd) {
        SerialRuleGd = serialRuleGd;
    }

    public float getNum() {
        return Num;
    }

    public void setNum(float num) {
        Num = num;
    }

    public float getWeight() {
        return Weight;
    }

    public void setWeight(float weight) {
        Weight = weight;
    }

    public float getUpLimit() {
        return UpLimit;
    }

    public void setUpLimit(float upLimit) {
        UpLimit = upLimit;
    }

    public float getDownLimit() {
        return DownLimit;
    }

    public void setDownLimit(float downLimit) {
        DownLimit = downLimit;
    }

    public String getUnitName() {
        return UnitName;
    }

    public void setUnitName(String unitName) {
        UnitName = unitName;
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
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getIsProperty() {
        return isProperty;
    }

    public void setIsProperty(String isProperty) {
        this.isProperty = isProperty;
    }

    public String getPowerGd() {
        return powerGd;
    }

    public void setPowerGd(String powerGd) {
        this.powerGd = powerGd;
    }

    public String getCurrentGd() {
        return currentGd;
    }

    public void setCurrentGd(String currentGd) {
        this.currentGd = currentGd;
    }

    public String getColorGd() {
        return colorGd;
    }

    public void setColorGd(String colorGd) {
        this.colorGd = colorGd;
    }

    public String getGradeGd() {
        return gradeGd;
    }

    public void setGradeGd(String gradeGd) {
        this.gradeGd = gradeGd;
    }

    public float getStartPower() {
        return startPower;
    }

    public void setStartPower(float startPower) {
        this.startPower = startPower;
    }

    public float getEndPower() {
        return endPower;
    }

    public void setEndPower(float endPower) {
        this.endPower = endPower;
    }

    public String getMaVerGd() {
        return maVerGd;
    }

    public void setMaVerGd(String maVerGd) {
        this.maVerGd = maVerGd;
    }
}
