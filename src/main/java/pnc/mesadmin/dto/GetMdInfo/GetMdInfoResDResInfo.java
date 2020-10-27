package pnc.mesadmin.dto.GetMdInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GetMdInfoResDResInfo {

    @JsonProperty("ResRd")
    private int ResRd;

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
    private List<GetMdInfoResDROptInfo> OptInfo;
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
    public int getResRd() {
        return ResRd;
    }

    @JsonIgnore
    public void setResRd(int resRd) {
        ResRd = resRd;
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
    public List<GetMdInfoResDROptInfo> getOptInfo() {
        return OptInfo;
    }

    @JsonIgnore
    public void setOptInfo(List<GetMdInfoResDROptInfo> optInfo) {
        OptInfo = optInfo;
    }


}
