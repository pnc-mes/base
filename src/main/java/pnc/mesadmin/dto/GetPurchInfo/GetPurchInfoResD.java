package pnc.mesadmin.dto.GetPurchInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by PNC on 2017/9/6.
 */
public class GetPurchInfoResD {
    @JsonProperty("PurChRd")
    private int PurChRd;
    @JsonProperty("DSource")
    private String DSource;
    @JsonProperty("Status")
    private String Status;
    @JsonProperty("SStatus")
    private String SStatus;
    @JsonProperty("PurChCode")
    private String PurChCode;
    @JsonProperty("Buyer")
    private String Buyer;
    @JsonProperty("SupCode")
    private String SupCode;
    @JsonProperty("SupName")
    private String SupName;
    @JsonProperty("ArrivalTime")
    private String ArrivalTime;
    @JsonProperty("Creator")
    private String Creator;
    @JsonProperty("CreateTime")
    private String CreateTime;
    @JsonProperty("LastModifyMan")
    private String LastModifyMan;
    @JsonProperty("LastModifyTime")
    private String LastModifyTime;
    @JsonProperty("Remark")
    private String Remark;
    @JsonProperty("PurChDlInfo")
    private List<GetPurchInfoResDPurChDl> PurChDlInfo;
    @JsonIgnore
    public String getDSource() {
        return DSource;
    }
    @JsonIgnore
    public void setDSource(String DSource) {
        this.DSource = DSource;
    }

    @JsonIgnore
    public String getSStatus() {
        return SStatus;
    }
    @JsonIgnore
    public void setSStatus(String SStatus) {
        this.SStatus = SStatus;
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
    public int getPurChRd() {
        return PurChRd;
    }
    @JsonIgnore
    public void setPurChRd(int purChRd) {
        PurChRd = purChRd;
    }
    @JsonIgnore
    public String getPurChCode() {
        return PurChCode;
    }
    @JsonIgnore
    public void setPurChCode(String purChCode) {
        PurChCode = purChCode;
    }
    @JsonIgnore
    public String getBuyer() {
        return Buyer;
    }
    @JsonIgnore
    public void setBuyer(String buyer) {
        Buyer = buyer;
    }
    @JsonIgnore
    public String getSupCode() {
        return SupCode;
    }
    @JsonIgnore
    public void setSupCode(String supCode) {
        SupCode = supCode;
    }
    @JsonIgnore
    public String getSupName() {
        return SupName;
    }
    @JsonIgnore
    public void setSupName(String supName) {
        SupName = supName;
    }
    @JsonIgnore
    public String getArrivalTime() {
        return ArrivalTime;
    }
    @JsonIgnore
    public void setArrivalTime(String arrivalTime) {
        ArrivalTime = arrivalTime;
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
    public String getLastModifyMan() {
        return LastModifyMan;
    }
    @JsonIgnore
    public void setLastModifyMan(String lastModifyMan) {
        LastModifyMan = lastModifyMan;
    }
    @JsonIgnore
    public String getLastModifyTime() {
        return LastModifyTime;
    }
    @JsonIgnore
    public void setLastModifyTime(String lastModifyTime) {
        LastModifyTime = lastModifyTime;
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
    public List<GetPurchInfoResDPurChDl> getPurChDlInfo() {
        return PurChDlInfo;
    }
    @JsonIgnore
    public void setPurChDlInfo(List<GetPurchInfoResDPurChDl> purChDlInfo) {
        PurChDlInfo = purChDlInfo;
    }
}
