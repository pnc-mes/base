package pnc.mesadmin.dto.SaveRoleInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by xfxi on 2017/8/21.
 */
public class SaveRoleInfoReqBD00ScanPVInfo {

    @JsonProperty("OpertMFlag")
    private String OpertMFlag;

    @JsonProperty("PVIInfo")
    private List<SaveRoleInfoReqBD00SPVIInfo> PVIInfo;

    @JsonIgnore
    public String getOpertMFlag() {
        return OpertMFlag;
    }

    @JsonIgnore
    public void setOpertMFlag(String opertMFlag) {
        OpertMFlag = opertMFlag;
    }

    @JsonIgnore
    public List<SaveRoleInfoReqBD00SPVIInfo> getPVIInfo() {
        return PVIInfo;
    }

    @JsonIgnore
    public void setPVIInfo(List<SaveRoleInfoReqBD00SPVIInfo> PVIInfo) {
        this.PVIInfo = PVIInfo;
    }
}
