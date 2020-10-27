package pnc.mesadmin.entity;

import java.util.Date;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：物料信息表实体类
 * 创建人：潘俊峰
 * 创建时间：2017-06-02
 * 修改人：
 * 修改时间：
 */
public class MaterialInfo {
    private int ruid;
    private String guid;
    private String companyCode;
    private String materialCode;
    private String mapMaCode;
    private String materialName;
    private String materialDes;
    private String materialType;
    private String verGd;
    private String pdFamilyGd;
    private String creator;
    private Date createTime;
    private String lastModifyMan;
    private Date lastModifyTime;
    private String remark;

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

    public String getMaterialDes() {
        return materialDes;
    }

    public void setMaterialDes(String materialDes) {
        this.materialDes = materialDes;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getVerGd() {
        return verGd;
    }

    public void setVerGd(String verGd) {
        this.verGd = verGd;
    }

    public String getPdFamilyGd() {
        return pdFamilyGd;
    }

    public void setPdFamilyGd(String pdFamilyGd) {
        this.pdFamilyGd = pdFamilyGd;
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
}
