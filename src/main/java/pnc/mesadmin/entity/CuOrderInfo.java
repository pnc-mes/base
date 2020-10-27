package pnc.mesadmin.entity;

import java.util.Date;

public class CuOrderInfo {
    private Integer Ruid;
    private String Guid;
    private String OrderCode;
    private String CoTGd;
    private String MaVerGd;
    private float Num;
    private String CustomerGd;
    private String DSource;
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

    public String getOrderCode() {
        return OrderCode;
    }

    public void setOrderCode(String orderCode) {
        OrderCode = orderCode;
    }

    public String getCoTGd() {
        return CoTGd;
    }

    public void setCoTGd(String coTGd) {
        CoTGd = coTGd;
    }

    public String getMaVerGd() {
        return MaVerGd;
    }

    public void setMaVerGd(String maVerGd) {
        MaVerGd = maVerGd;
    }

    public float getNum() {
        return Num;
    }

    public void setNum(float num) {
        Num = num;
    }

    public String getCustomerGd() {
        return CustomerGd;
    }

    public void setCustomerGd(String customerGd) {
        CustomerGd = customerGd;
    }

    public String getDSource() {
        return DSource;
    }

    public void setDSource(String DSource) {
        this.DSource = DSource;
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
