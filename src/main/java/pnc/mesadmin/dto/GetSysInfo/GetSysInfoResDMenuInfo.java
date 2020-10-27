package pnc.mesadmin.dto.GetSysInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/5/19.
 */
public class GetSysInfoResDMenuInfo implements Serializable{

    @JsonProperty("ModuleRd")
    private int ModuleRd;

    @JsonProperty("ModuleName")
    private String ModuleName;

    @JsonProperty("ModuleIcon")
    private String ModuleIcon;

    @JsonProperty("ResUrl")
    private String ResUrl;

    @JsonProperty("OpenType")
    private String OpenType;

    @JsonProperty("IsOpen")
    private String IsOpen;

    @JsonProperty("Pos")
    private Integer Pos;

    @JsonProperty("ResInfo")
    private List<GetSysInfoResDMResInfo> ResInfo;

    @JsonProperty("MenuInfo")
    private List<GetSysInfoResDMenuInfo> MenuInfo;

    @JsonIgnore
    public Integer getPos() {
        return Pos;
    }

    @JsonIgnore
    public void setPos(Integer pos) {
        Pos = pos;
    }

    @JsonIgnore
    public int getModuleRd() {
        return ModuleRd;
    }

    @JsonIgnore
    public void setModuleRd(int moduleRd) {
        ModuleRd = moduleRd;
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
    public String getModuleIcon() {
        return ModuleIcon;
    }

    @JsonIgnore
    public void setModuleIcon(String moduleIcon) {
        ModuleIcon = moduleIcon;
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
    public String getOpenType() {
        return OpenType;
    }

    @JsonIgnore
    public void setOpenType(String openType) {
        OpenType = openType;
    }

    @JsonIgnore
    public String getIsOpen() {
        return IsOpen;
    }

    @JsonIgnore
    public void setIsOpen(String isOpen) {
        IsOpen = isOpen;
    }

    @JsonIgnore
    public List<GetSysInfoResDMResInfo> getResInfo() {
        return ResInfo;
    }

    @JsonIgnore
    public void setResInfo(List<GetSysInfoResDMResInfo> resInfo) {
        ResInfo = resInfo;
    }

    @JsonIgnore
    public List<GetSysInfoResDMenuInfo> getMenuInfo() {
        return MenuInfo;
    }

    @JsonIgnore
    public void setMenuInfo(List<GetSysInfoResDMenuInfo> menuInfo) {
        MenuInfo = menuInfo;
    }
}
