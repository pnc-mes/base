package pnc.mesadmin.dto.GetRetMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by test on 2017/9/21.
 */
public class GetRetMaInfoResD implements Serializable{

    @JsonProperty("RetRd")
    private int RetRd;

    @JsonProperty("RetCode")
    private String RetCode;

    @JsonProperty("AssCode")
    private String AssCode;

    @JsonProperty("AssSource")
    private String AssSource;

    @JsonProperty("ExStatus")
    private String ExStatus;

    @JsonProperty("SStatus")
    private String SStatus;

    @JsonProperty("RetDlInfo")
    private List<GetRetMaInfoResDRetDlInfo> RetDlInfo;

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
    public String getSStatus() {
        return SStatus;
    }

    @JsonIgnore
    public void setSStatus(String SStatus) {
        this.SStatus = SStatus;
    }

    @JsonIgnore
    public int getRetRd() {
        return RetRd;
    }
    @JsonIgnore
    public void setRetRd(int retRd) {
        RetRd = retRd;
    }
    @JsonIgnore
    public String getRetCode() {
        return RetCode;
    }
    @JsonIgnore
    public void setRetCode(String retCode) {
        RetCode = retCode;
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
    public List<GetRetMaInfoResDRetDlInfo> getRetDlInfo() {
        return RetDlInfo;
    }
    @JsonIgnore
    public void setRetDlInfo(List<GetRetMaInfoResDRetDlInfo> retDlInfo) {
        RetDlInfo = retDlInfo;
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
}
