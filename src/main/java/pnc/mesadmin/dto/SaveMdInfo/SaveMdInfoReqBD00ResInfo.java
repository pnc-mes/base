package pnc.mesadmin.dto.SaveMdInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SaveMdInfoReqBD00ResInfo {

    @JsonProperty("ResName")
    private String ResName;

    @JsonProperty("ResUrl")
    private String ResUrl;

    @JsonProperty("LinkType")
    private String LinkType;

    @JsonProperty("Pos")
    private Integer Pos;

    @JsonProperty("IsClose")
    private String IsClose;

    @JsonProperty("OptInfo")
    private List<SaveMdInfoReqBD00ROptInfo> OptInfo;
    @JsonIgnore
    public String getIsClose() {
        return IsClose;
    }
    @JsonIgnore
    public void setIsClose(String isClose) {
        IsClose = isClose;
    }

    @JsonIgnore
    public Integer getPos() {
        return Pos;
    }

    @JsonIgnore
    public void setPos(Integer pos) {
        Pos = pos;
    }

    @JsonIgnore
    public String getResName() {
        return ResName;
    }

    @JsonIgnore
    public void setResName(String resName) {
        ResName = resName;
    }

    @JsonIgnore
    public String getResUrl() {
        return ResUrl;
    }

    @JsonIgnore
    public void setResUrl(String resUrl) {
        ResUrl = resUrl;
    }

    @JsonIgnore
    public String getLinkType() {
        return LinkType;
    }

    @JsonIgnore
    public void setLinkType(String linkType) {
        LinkType = linkType;
    }

    @JsonIgnore
    public List<SaveMdInfoReqBD00ROptInfo> getOptInfo() {
        return OptInfo;
    }

    @JsonIgnore
    public void setOptInfo(List<SaveMdInfoReqBD00ROptInfo> optInfo) {
        OptInfo = optInfo;
    }
}
