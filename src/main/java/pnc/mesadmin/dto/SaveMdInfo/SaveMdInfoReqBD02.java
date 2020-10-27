package pnc.mesadmin.dto.SaveMdInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SaveMdInfoReqBD02 {

    @JsonProperty("ModuleRd")
    private int ModuleRd;

    @JsonProperty("SysVerRd")
    private Integer SysVerRd;

    @JsonProperty("ModuleName")
    private String ModuleName;

    @JsonProperty("ResUrl")
    private String ResUrl;

    @JsonProperty("LinkType")
    private String LinkType;

    @JsonProperty("Pos")
    private int Pos;

    @JsonProperty("IsClose")
    private String IsClose;

    @JsonProperty("PModuleRd")
    private int PModuleRd;

    @JsonProperty("ResInfo")
    private List<SaveMdInfoReqBD02ResInfo> ResInfo;

    @JsonIgnore
    public int getModuleRd() {
        return ModuleRd;
    }

    @JsonIgnore
    public void setModuleRd(int moduleRd) {
        ModuleRd = moduleRd;
    }

    @JsonIgnore
    public Integer getSysVerRd() {
        return SysVerRd;
    }

    @JsonIgnore
    public void setSysVerRd(Integer sysVerRd) {
        SysVerRd = sysVerRd;
    }

    @JsonIgnore
    public String getModuleName() {
        return ModuleName;
    }

    @JsonIgnore
    public void setModuleName(String moduleName) {
        ModuleName = moduleName;
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
    public int getPos() {
        return Pos;
    }

    @JsonIgnore
    public void setPos(int pos) {
        Pos = pos;
    }

    @JsonIgnore
    public int getPModuleRd() {
        return PModuleRd;
    }

    @JsonIgnore
    public void setPModuleRd(int PModuleRd) {
        this.PModuleRd = PModuleRd;
    }

    @JsonIgnore
    public List<SaveMdInfoReqBD02ResInfo> getResInfo() {
        return ResInfo;
    }

    @JsonIgnore
    public void setResInfo(List<SaveMdInfoReqBD02ResInfo> resInfo) {
        ResInfo = resInfo;
    }

    @JsonIgnore
    public String getIsClose() {
        return IsClose;
    }

    @JsonIgnore
    public void setIsClose(String isClose) {
        IsClose = isClose;
    }
}
