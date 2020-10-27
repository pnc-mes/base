package pnc.mesadmin.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：物料类别信息表实体类
 * 创建人：刘福志
 * 创建时间：2017-08-21
 * 修改人：
 * 修改时间：
 */
@TableName(value = "tpm_matypeinfo")
public class MaTypeInfo {
    private int ruid;
    private String guid;
    //父类Gd
    private String pMaTGd;
    //物料类别代码
    private String maTCode;
    //物料类别名称
    private String maTName;
    //扩展字段Gd
    private String expandGd;
    //物料类型 00#产成品 01#半成品 02#原料 #03其它
    private String materialType;
    //是否外部引用 00#外部 01#未设置
    private String dSource;
    private String creator;
    private Date createTime;
    private String lastModifyMan;
    private Date lastModifyTime;
    private String remark;

    public String getdSource() {
        return dSource;
    }

    public void setdSource(String dSource) {
        this.dSource = dSource;
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

    public String getpMaTGd() {
        return pMaTGd;
    }

    public void setpMaTGd(String pMaTGd) {
        this.pMaTGd = pMaTGd;
    }

    public String getMaTCode() {
        return maTCode;
    }

    public void setMaTCode(String maTCode) {
        this.maTCode = maTCode;
    }

    public String getMaTName() {
        return maTName;
    }

    public void setMaTName(String maTName) {
        this.maTName = maTName;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
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

    public String getExpandGd() {
        return expandGd;
    }

    public void setExpandGd(String expandGd) {
        this.expandGd = expandGd;
    }
}
