package pnc.mesadmin.entity;

import java.util.Date;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/11/8 15:56
 * @Description:
 */
public class SparePartInfo {
    private int ruid;
    private String guid;
    private String partName;
    private String supplierGd;
    private String venderSN;
    private String faGd;
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

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getVenderSN() {
        return venderSN;
    }

    public void setVenderSN(String venderSN) {
        this.venderSN = venderSN;
    }

    public String getFaGd() {
        return faGd;
    }

    public void setFaGd(String faGd) {
        this.faGd = faGd;
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

    public String getSupplierGd() {
        return supplierGd;
    }

    public void setSupplierGd(String supplierGd) {
        this.supplierGd = supplierGd;
    }
}
