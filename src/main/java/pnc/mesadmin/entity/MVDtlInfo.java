package pnc.mesadmin.entity;

import java.util.Date;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * Created by zhangliangliang on 2017/11/2.
 */
public class MVDtlInfo {
    private int ruid;
    private String guid;
    private String mVTkGd;
    private String materialCode;
    private String materialName;
    private String BarType;
    private String batch;
    private String mOStoreGd;
    private float mONum;
    private String mIStoreGd;
    private float mINum;
    private String creator;
    private Date createTime;
    private String lastModifyMan;
    private Date lastModifyTime;
    private String remark;
    private String mOLGd;

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

    public String getmVTkGd() {
        return mVTkGd;
    }

    public void setmVTkGd(String mVTkGd) {
        this.mVTkGd = mVTkGd;
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

    public String getBarType() {
        return BarType;
    }

    public void setBarType(String barType) {
        BarType = barType;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getmOStoreGd() {
        return mOStoreGd;
    }

    public void setmOStoreGd(String mOStoreGd) {
        this.mOStoreGd = mOStoreGd;
    }

    public float getmONum() {
        return mONum;
    }

    public void setmONum(float mONum) {
        this.mONum = mONum;
    }

    public String getmIStoreGd() {
        return mIStoreGd;
    }

    public void setmIStoreGd(String mIStoreGd) {
        this.mIStoreGd = mIStoreGd;
    }

    public float getmINum() {
        return mINum;
    }

    public void setmINum(float mINum) {
        this.mINum = mINum;
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

    public String getmOLGd() {
        return mOLGd;
    }

    public void setmOLGd(String mOLGd) {
        this.mOLGd = mOLGd;
    }
}
