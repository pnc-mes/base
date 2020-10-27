package pnc.mesadmin.dto.GetRoleInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by xfxi on 2017/8/21.
 */
public class GetRoleInfoResDScanPVInfo {

    @JsonProperty("OpertMFlag")
    private String OpertMFlag;

    @JsonProperty("PVIInfo")
    private List<GetRoleInfoResDSPVIInfo> PVIInfo;

    @JsonIgnore
    public String getOpertMFlag() {
        return OpertMFlag;
    }

    @JsonIgnore
    public void setOpertMFlag(String opertMFlag) {
        OpertMFlag = opertMFlag;
    }

    @JsonIgnore
    public List<GetRoleInfoResDSPVIInfo> getPVIInfo() {
        return PVIInfo;
    }

    @JsonIgnore
    public void setPVIInfo(List<GetRoleInfoResDSPVIInfo> PVIInfo) {
        this.PVIInfo = PVIInfo;
    }
}
