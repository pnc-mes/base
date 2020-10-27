package pnc.mesadmin.dto.GetRCGInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/5/27.
 */
public class GetRCGInfoResD implements Serializable{

    @JsonProperty("RCGRd")
    private int RCGRd;

    @JsonProperty("RCGpName")
    private String RCGpName;

    @JsonProperty("RCInfo")
    private List<GetRCGInfoResDRCInfo> RCInfo;

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
    public int getRCGRd() {
        return RCGRd;
    }
    @JsonIgnore
    public void setRCGRd(int RCGRd) {
        this.RCGRd = RCGRd;
    }
    @JsonIgnore
    public String getRCGpName() {
        return RCGpName;
    }
    @JsonIgnore
    public void setRCGpName(String RCGpName) {
        this.RCGpName = RCGpName;
    }
    @JsonIgnore
    public List<GetRCGInfoResDRCInfo> getRCInfo() {
        return RCInfo;
    }
    @JsonIgnore
    public void setRCInfo(List<GetRCGInfoResDRCInfo> RCInfo) {
        this.RCInfo = RCInfo;
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
