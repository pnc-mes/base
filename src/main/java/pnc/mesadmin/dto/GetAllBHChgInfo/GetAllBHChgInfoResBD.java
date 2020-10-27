package pnc.mesadmin.dto.GetAllBHChgInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by zhaochao on 11/20 0020.
 */
public class GetAllBHChgInfoResBD {
    /*
    * "BomChgRd": "@BomChgRd",
"Version": "@Version",
"Remark": "@Remark",
"Creator": "@Creator",
"CreateTime": "@CreateTime"*/


    @JsonProperty("BomChgRd")
    private int BomChgRd;
    @JsonProperty("Version")
    private String Version;
    @JsonProperty("Remark")
    private String Remark;
    @JsonProperty("Creator")
    private String Creator;
    @JsonProperty("CreateTime")
    private String CreateTime;

    @JsonIgnore
    public int getBomChgRd() {
        return BomChgRd;
    }
    @JsonIgnore
    public void setBomChgRd(int bomChgRd) {
        BomChgRd = bomChgRd;
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
    public String getRemark() {
        return Remark;
    }
    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
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
}
