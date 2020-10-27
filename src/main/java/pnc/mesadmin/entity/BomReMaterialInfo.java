package pnc.mesadmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * Created by xfxi on 2017/6/6.
 */
@TableName("tpm_bomrematerialinfo")
public class BomReMaterialInfo {
    @TableId(value = "ruid", type = IdType.AUTO)
    private int ruid;
    private String guid;
    private String bomMaGd;
    private String specVerGd;
    private String bomVerGd;
    private String maVerGd;
    private float aRate;
    private float num;
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

    public String getBomMaGd() {
        return bomMaGd;
    }

    public void setBomMaGd(String bomMaGd) {
        this.bomMaGd = bomMaGd;
    }

    public String getMaVerGd() {
        return maVerGd;
    }

    public void setMaVerGd(String maVerGd) {
        this.maVerGd = maVerGd;
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

    public String getSpecVerGd() {
        return specVerGd;
    }

    public void setSpecVerGd(String specVerGd) {
        this.specVerGd = specVerGd;
    }

    public String getBomVerGd() {
        return bomVerGd;
    }

    public void setBomVerGd(String bomVerGd) {
        this.bomVerGd = bomVerGd;
    }

    public float getNum() {
        return num;
    }

    public void setNum(float num) {
        this.num = num;
    }
}
