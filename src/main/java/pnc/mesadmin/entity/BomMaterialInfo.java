package pnc.mesadmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

/**
 * Created by xfxi on 2017/6/6.
 */
@TableName("tpm_bommaterialinfo")
public class BomMaterialInfo {

    @TableId(value = "ruid", type = IdType.AUTO)
    private int ruid;
    private String guid;
    private String bomVerGd;
    private String maVerGd;
    private String specVerGd;
    private float num;
    private float aRate;
    private String unitName;
    private String conSMode;
    private String checked;
    private String creator;
    private Date createTime;
    private String lastModifyMan;
    private Date lastModifyTime;
    private String remark;

    public float getaRate() {
        return aRate;
    }

    public void setaRate(float aRate) {
        this.aRate = aRate;
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

    public String getBomVerGd() {
        return bomVerGd;
    }

    public void setBomVerGd(String bomVerGd) {
        this.bomVerGd = bomVerGd;
    }

    public String getMaVerGd() {
        return maVerGd;
    }

    public void setMaVerGd(String maVerGd) {
        this.maVerGd = maVerGd;
    }

    public String getSpecVerGd() {
        return specVerGd;
    }

    public void setSpecVerGd(String specVerGd) {
        this.specVerGd = specVerGd;
    }

    public float getNum() {
        return num;
    }

    public void setNum(float num) {
        this.num = num;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getConSMode() {
        return conSMode;
    }

    public void setConSMode(String conSMode) {
        this.conSMode = conSMode;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BomMaterialInfo that = (BomMaterialInfo) o;

        if (ruid != that.ruid) return false;
        if (Float.compare(that.num, num) != 0) return false;
        if (!guid.equals(that.guid)) return false;
        if (!bomVerGd.equals(that.bomVerGd)) return false;
        if (!maVerGd.equals(that.maVerGd)) return false;
        if (!specVerGd.equals(that.specVerGd)) return false;
        if (!unitName.equals(that.unitName)) return false;
        if (!checked.equals(that.checked)) return false;
        if (!creator.equals(that.creator)) return false;
        if (!createTime.equals(that.createTime)) return false;
        if (!lastModifyMan.equals(that.lastModifyMan)) return false;
        if (!lastModifyTime.equals(that.lastModifyTime)) return false;
        return remark.equals(that.remark);
    }

    @Override
    public int hashCode() {
        int result = ruid;
        result = 31 * result + guid.hashCode();
        result = 31 * result + bomVerGd.hashCode();
        result = 31 * result + maVerGd.hashCode();
        result = 31 * result + specVerGd.hashCode();
        result = 31 * result + (num != +0.0f ? Float.floatToIntBits(num) : 0);
        result = 31 * result + unitName.hashCode();
        result = 31 * result + checked.hashCode();
        result = 31 * result + creator.hashCode();
        result = 31 * result + createTime.hashCode();
        result = 31 * result + lastModifyMan.hashCode();
        result = 31 * result + lastModifyTime.hashCode();
        result = 31 * result + remark.hashCode();
        return result;
    }
}
