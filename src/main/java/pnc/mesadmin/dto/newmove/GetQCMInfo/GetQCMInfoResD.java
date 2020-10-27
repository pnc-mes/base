package pnc.mesadmin.dto.newmove.GetQCMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PNC on 2019/4/3.
 */
public class GetQCMInfoResD implements Serializable {

    @JsonProperty("QCheckMaRd")
    private int QCheckMaRd;
    @JsonProperty("QCheckMaCode")
    private String QCheckMaCode;
    @JsonProperty("QCheckMaType")
    private String QCheckMaType;
    @JsonProperty("Status")
    private String Status;
    @JsonProperty("StockMaCode")
    private String StockMaCode;
    @JsonProperty("FinalResult")
    private String FinalResult; //出库确认结果 00#合格 01#不合格
    @JsonProperty("QCMaInfo")
    private List<GetQCMInfoResQCMaInfo> QCMaInfo;
    @JsonProperty("Creator")
    private String Creator;
    @JsonProperty("CreateTime")
    private String CreateTime;
    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public int getQCheckMaRd() {
        return QCheckMaRd;
    }

    @JsonIgnore
    public void setQCheckMaRd(int QCheckMaRd) {
        this.QCheckMaRd = QCheckMaRd;
    }

    @JsonIgnore
    public String getQCheckMaCode() {
        return QCheckMaCode;
    }

    @JsonIgnore
    public void setQCheckMaCode(String QCheckMaCode) {
        this.QCheckMaCode = QCheckMaCode;
    }

    @JsonIgnore
    public String getQCheckMaType() {
        return QCheckMaType;
    }

    @JsonIgnore
    public void setQCheckMaType(String QCheckMaType) {
        this.QCheckMaType = QCheckMaType;
    }

    @JsonIgnore
    public String getStockMaCode() {
        return StockMaCode;
    }

    @JsonIgnore
    public void setStockMaCode(String stockMaCode) {
        StockMaCode = stockMaCode;
    }

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public List<GetQCMInfoResQCMaInfo> getQCMaInfo() {
        return QCMaInfo;
    }

    @JsonIgnore
    public void setQCMaInfo(List<GetQCMInfoResQCMaInfo> QCMaInfo) {
        this.QCMaInfo = QCMaInfo;
    }

    @JsonIgnore
    public String getCreator() {
        return Creator;
    }

    @JsonIgnore
    public void setCreator(String creator) {
        Creator = creator;
    }

    @JsonIgnore

    public String getCreateTime() {
        return CreateTime;
    }

    @JsonIgnore

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    @JsonIgnore
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }

    @JsonIgnore
    public String getFinalResult() {
        return FinalResult;
    }

    @JsonIgnore
    public void setFinalResult(String finalResult) {
        FinalResult = finalResult;
    }
}
