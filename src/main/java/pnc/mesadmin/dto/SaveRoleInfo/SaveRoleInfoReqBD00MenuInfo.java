package pnc.mesadmin.dto.SaveRoleInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/5/18.
 */
public class SaveRoleInfoReqBD00MenuInfo implements Serializable{

    @JsonProperty("ModuleRd")
    private int ModuleRd;

    @JsonProperty("ModuleName")
    private String ModuleName;

    @JsonProperty("ResInfo")
    private List<SaveRoleInfoReqBD00MResInfo> ResInfo;

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
    public List<SaveRoleInfoReqBD00MResInfo> getResInfo() {
        return ResInfo;
    }

    @JsonIgnore
    public void setResInfo(List<SaveRoleInfoReqBD00MResInfo> resInfo) {
        ResInfo = resInfo;
    }
}
