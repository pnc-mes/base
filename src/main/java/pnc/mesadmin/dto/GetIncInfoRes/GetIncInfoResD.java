package pnc.mesadmin.dto.GetIncInfoRes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by PNC on 2017/9/7.
 */
public class GetIncInfoResD {
    @JsonProperty("InCRd")
    private int InCRd;

    @JsonProperty("InCCode")
    private String InCCode;

    @JsonProperty("PurChCode")
    private String PurChCode;

    @JsonProperty("SourceType")
    private String SourceType;

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("SStatus")
    private String SStatus;

    @JsonProperty("DSource")
    private String DSource;

    @JsonProperty("IncDlInfo")
    private List<GetIncInfoResDIncDl> IncDlInfo;

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

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public int getInCRd() {
        return InCRd;
    }

    @JsonIgnore
    public void setInCRd(int inCRd) {
        InCRd = inCRd;
    }

    @JsonIgnore
    public String getInCCode() {
        return InCCode;
    }

    @JsonIgnore
    public void setInCCode(String inCCode) {
        InCCode = inCCode;
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
    public List<GetIncInfoResDIncDl> getIncDlInfo() {
        return IncDlInfo;
    }

    @JsonIgnore
    public void setIncDlInfo(List<GetIncInfoResDIncDl> incDlInfo) {
        IncDlInfo = incDlInfo;
    }

    @JsonIgnore
    public String getDSource() {
        return DSource;
    }

    @JsonIgnore
    public void setDSource(String DSource) {
        this.DSource = DSource;
    }

    @JsonIgnore
    public String getSourceType() {
        return SourceType;
    }

    @JsonIgnore
    public void setSourceType(String sourceType) {
        SourceType = sourceType;
    }

    @JsonIgnore
    public String getSStatus() {
        return SStatus;
    }

    @JsonIgnore
    public void setSStatus(String SStatus) {
        this.SStatus = SStatus;
    }
}
