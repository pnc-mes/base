package pnc.mesadmin.dto.GetMdInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class GetMdInfoResD {

    @JsonProperty("MInfo")
    private GetMdInfoResDMInfo MInfo;

    @JsonProperty("PMInfo")
    private GetMdInfoResDPMInfo PMInfo;

    @JsonProperty("ResInfo")
    private List<GetMdInfoResDResInfo> ResInfo;

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
    public GetMdInfoResDMInfo getMInfo() {
        return MInfo;
    }

    @JsonIgnore
    public void setMInfo(GetMdInfoResDMInfo MInfo) {
        this.MInfo = MInfo;
    }

    @JsonIgnore
    public GetMdInfoResDPMInfo getPMInfo() {
        return PMInfo;
    }

    @JsonIgnore
    public void setPMInfo(GetMdInfoResDPMInfo PMInfo) {
        this.PMInfo = PMInfo;
    }

    @JsonIgnore
    public List<GetMdInfoResDResInfo> getResInfo() {
        return ResInfo;
    }

    @JsonIgnore
    public void setResInfo(List<GetMdInfoResDResInfo> resInfo) {
        ResInfo = resInfo;
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
