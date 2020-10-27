package pnc.mesadmin.dto.GetBVInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by xfxi on 2017/6/8.
 */
public class GetBVInfoResD implements Serializable{

    @JsonProperty("BomVerRd")
    private int BomVerRd;

    @JsonProperty("BomCode")
    private String BomCode;

    @JsonProperty("BomName")
    private String BomName;

    @JsonProperty("Version")
    private String Version;

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("VersionNo")
    private String VersionNo;

    @JsonProperty("BomNo")
    private String BomNo;

    @JsonProperty("BMInfo")
    private List<GetBVInfoResDBMInfo> BMInfo;

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
    public int getBomVerRd() {
        return BomVerRd;
    }

    @JsonIgnore
    public void setBomVerRd(int bomVerRd) {
        BomVerRd = bomVerRd;
    }

    @JsonIgnore
    public String getBomCode() {
        return BomCode;
    }

    @JsonIgnore
    public void setBomCode(String bomCode) {
        BomCode = bomCode;
    }

    @JsonIgnore
    public String getBomName() {
        return BomName;
    }

    @JsonIgnore
    public void setBomName(String bomName) {
        BomName = bomName;
    }

    @JsonIgnore
    public String getVersion() {
        return Version;
    }

    @JsonIgnore
    public void setVersion(String version) {
        Version = version;
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
    public String getVersionNo() {
        return VersionNo;
    }

    @JsonIgnore
    public void setVersionNo(String versionNo) {
        VersionNo = versionNo;
    }

    @JsonIgnore
    public String getBomNo() {
        return BomNo;
    }

    @JsonIgnore
    public void setBomNo(String bomNo) {
        BomNo = bomNo;
    }

    @JsonIgnore
    public List<GetBVInfoResDBMInfo> getBMInfo() {
        return BMInfo;
    }

    @JsonIgnore
    public void setBMInfo(List<GetBVInfoResDBMInfo> BMInfo) {
        this.BMInfo = BMInfo;
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
