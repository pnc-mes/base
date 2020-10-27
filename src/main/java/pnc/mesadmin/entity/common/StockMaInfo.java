package pnc.mesadmin.entity.common;

import java.util.Date;

/**
 * @program: mesadmin
 * @description: 备货单信息表
 * @author: yin.yang
 * @create: 2019-04-08
 **/
public class StockMaInfo {
    private Integer Ruid;
    private String Guid;
    private String StockMaCode;
    private String OutCode;
    private String CKStatus; //00#待出库01#已出库02#已调拨出库03#已取消
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

    public String getStockMaCode() {
        return StockMaCode;
    }

    public void setStockMaCode(String stockMaCode) {
        StockMaCode = stockMaCode;
    }

    public String getOutCode() {
        return OutCode;
    }

    public void setOutCode(String outCode) {
        OutCode = outCode;
    }

    public String getCKStatus() {
        return CKStatus;
    }

    public void setCKStatus(String CKStatus) {
        this.CKStatus = CKStatus;
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
