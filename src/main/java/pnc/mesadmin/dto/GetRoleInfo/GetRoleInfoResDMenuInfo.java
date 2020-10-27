package pnc.mesadmin.dto.GetRoleInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/5/17.
 */
public class GetRoleInfoResDMenuInfo implements Serializable{

    @JsonProperty("ModuleRd")
    private int ModuleRd;

    @JsonProperty("ModuleName")
    private String ModuleName;

    @JsonProperty("ResInfo")
    private List<GetRoleInfoResDMResInfo> ResInfo;

    @JsonProperty("MenuInfo")
    private List<GetRoleInfoResDMenuInfo> MenuInfo;

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
    public List<GetRoleInfoResDMResInfo> getResInfo() {
        return ResInfo;
    }

    @JsonIgnore
    public void setResInfo(List<GetRoleInfoResDMResInfo> resInfo) {
        ResInfo = resInfo;
    }

    @JsonIgnore
    public List<GetRoleInfoResDMenuInfo> getMenuInfo() {
        return MenuInfo;
    }

    @JsonIgnore
    public void setMenuInfo(List<GetRoleInfoResDMenuInfo> menuInfo) {
        MenuInfo = menuInfo;
    }
}
