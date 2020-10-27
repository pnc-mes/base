package pnc.mesadmin.dto.GetRMaNInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by PNC on 2017/9/26.
 */
public class GetRMaNInfoResD {
    @JsonProperty("RMaNRd")
    private int RMaNRd;

    @JsonProperty("RMaNCode")
    private String RMaNCode;

    @JsonProperty("AssCode")
    private String AssCode;

    @JsonProperty("AssSource")
    private String AssSource;

    @JsonProperty("ExStatus")
    private String ExStatus;

    @JsonProperty("SStatus")
    private String SStatus;

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

    @JsonProperty("RMaNDlInfo")
    private List<GetRMaNInfoResDRMaNDl> RMaNDlInfo;
    @JsonIgnore
    public String getSStatus() {
        return SStatus;
    }
    @JsonIgnore
    public void setSStatus(String SStatus) {
        this.SStatus = SStatus;
    }

    @JsonIgnore
    public int getRMaNRd() {
        return RMaNRd;
    }
    @JsonIgnore
    public void setRMaNRd(int RMaNRd) {
        this.RMaNRd = RMaNRd;
    }
    @JsonIgnore
    public String getRMaNCode() {
        return RMaNCode;
    }
    @JsonIgnore
    public void setRMaNCode(String RMaNCode) {
        this.RMaNCode = RMaNCode;
    }
    @JsonIgnore
    public String getAssCode() {
        return AssCode;
    }
    @JsonIgnore
    public void setAssCode(String assCode) {
        AssCode = assCode;
    }
    @JsonIgnore
    public String getAssSource() {
        return AssSource;
    }
    @JsonIgnore
    public void setAssSource(String assSource) {
        AssSource = assSource;
    }
    @JsonIgnore
    public String getExStatus() {
        return ExStatus;
    }
    @JsonIgnore
    public void setExStatus(String exStatus) {
        ExStatus = exStatus;
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
    public List<GetRMaNInfoResDRMaNDl> getRMaNDlInfo() {
        return RMaNDlInfo;
    }
    @JsonIgnore
    public void setRMaNDlInfo(List<GetRMaNInfoResDRMaNDl> RMaNDlInfo) {
        this.RMaNDlInfo = RMaNDlInfo;
    }
}
