package pnc.mesadmin.entity.common;

import java.util.Date;

//请检单信息表
public class QCheckMaInfo {
    private Integer Ruid;
    private String Guid;
    private String QCheckMaCode;
    private String QCheckMaType;
    private String Status;
    private String FinalResult; //出库确认结果 00#合格 01#不合格
    private String StockMaCode;
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

    public String getQCheckMaCode() {
        return QCheckMaCode;
    }

    public void setQCheckMaCode(String QCheckMaCode) {
        this.QCheckMaCode = QCheckMaCode;
    }

    public String getQCheckMaType() {
        return QCheckMaType;
    }

    public void setQCheckMaType(String QCheckMaType) {
        this.QCheckMaType = QCheckMaType;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getFinalResult() {
        return FinalResult;
    }

    public void setFinalResult(String finalResult) {
        FinalResult = finalResult;
    }

    public String getStockMaCode() {
        return StockMaCode;
    }

    public void setStockMaCode(String stockMaCode) {
        StockMaCode = stockMaCode;
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
