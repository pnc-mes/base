package pnc.mesadmin.entity.common;

import java.util.Date;

/**
 * @program: mesadmin
 * @description: 备货明细信息表
 * @author: yin.yang
 * @create: 2019-04-08
 **/
public class StockMaDtlInfo {
    private Integer Ruid;
    private String Guid;
    private String OutMaDtlGd;
    private String StockMaGd;
    private String BarType;
    private String BarCode;
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

    public String getOutMaDtlGd() {
        return OutMaDtlGd;
    }

    public void setOutMaDtlGd(String outMaDtlGd) {
        OutMaDtlGd = outMaDtlGd;
    }

    public String getStockMaGd() {
        return StockMaGd;
    }

    public void setStockMaGd(String stockMaGd) {
        StockMaGd = stockMaGd;
    }

    public String getBarType() {
        return BarType;
    }

    public void setBarType(String barType) {
        BarType = barType;
    }

    public String getBarCode() {
        return BarCode;
    }

    public void setBarCode(String barCode) {
        BarCode = barCode;
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
