package pnc.mesadmin.entity;

import java.util.Date;

//检验模板关联信息表
public class CheckTRelInfo {
    private Integer Ruid;
    private String Guid;
    private String TempRelName;
    private String TempRelType;
    private String SupplierGd;
    private String CustomerGd;
    private String RelType;
    private String RelGd;
    private String CheckDocGd;
    private String Creator;
    private Date CreateTime;
    private String LastModifyMan;
    private Date LastModifyTime;
    private String Remark;

    public Integer getRuid() {
        return Ruid;
    }

    public void setRuid(Integer ruid) {
        Ruid = ruid;
    }

    public String getGuid() {
        return Guid;
    }

    public void setGuid(String guid) {
        Guid = guid;
    }

    public String getTempRelName() {
        return TempRelName;
    }

    public void setTempRelName(String tempRelName) {
        TempRelName = tempRelName;
    }

    public String getTempRelType() {
        return TempRelType;
    }

    public void setTempRelType(String tempRelType) {
        TempRelType = tempRelType;
    }

    public String getSupplierGd() {
        return SupplierGd;
    }

    public void setSupplierGd(String supplierGd) {
        SupplierGd = supplierGd;
    }

    public String getCustomerGd() {
        return CustomerGd;
    }

    public void setCustomerGd(String customerGd) {
        CustomerGd = customerGd;
    }

    public String getRelType() {
        return RelType;
    }

    public void setRelType(String relType) {
        RelType = relType;
    }

    public String getRelGd() {
        return RelGd;
    }

    public void setRelGd(String relGd) {
        RelGd = relGd;
    }

    public String getCheckDocGd() {
        return CheckDocGd;
    }

    public void setCheckDocGd(String checkDocGd) {
        CheckDocGd = checkDocGd;
    }

    public String getCreator() {
        return Creator;
    }

    public void setCreator(String creator) {
        Creator = creator;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public String getLastModifyMan() {
        return LastModifyMan;
    }

    public void setLastModifyMan(String lastModifyMan) {
        LastModifyMan = lastModifyMan;
    }

    public Date getLastModifyTime() {
        return LastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        LastModifyTime = lastModifyTime;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
