package pnc.mesadmin.dto.GetBOMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by xfxi on 2017/6/8.
 */
public class GetBOMInfoResD implements Serializable{

    @JsonProperty("BMRd")
    private int BMRd;

    @JsonProperty("BomVerRd")
    private int BomVerRd;

    @JsonProperty("BomCode")
    private String BomCode;

    @JsonProperty("BomName")
    private String BomName;

    @JsonProperty("Version")
    private String Version;

    @JsonProperty("IsDef")
    private String IsDef;

    @JsonProperty("BMInfo")
    private List<GetBOMInfoResDBMInfo> BMInfo;

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
    public String getIsDef() {
        return IsDef;
    }

    @JsonIgnore
    public void setIsDef(String isDef) {
        IsDef = isDef;
    }

    @JsonIgnore
    public List<GetBOMInfoResDBMInfo> getBMInfo() {
        return BMInfo;
    }

    @JsonIgnore
    public void setBMInfo(List<GetBOMInfoResDBMInfo> BMInfo) {
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

    @JsonIgnore
    public int getBMRd() {
        return BMRd;
    }

    @JsonIgnore
    public void setBMRd(int BMRd) {
        this.BMRd = BMRd;
    }
}
