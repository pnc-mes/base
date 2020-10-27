package pnc.mesadmin.dto.SaveRoleInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by xfxi on 2017/8/21.
 */
public class SaveRoleInfoReqBD02ScanPVInfo {

    @JsonProperty("OpertMFlag")
    private String OpertMFlag;

    @JsonProperty("PVIInfo")
    private List<SaveRoleInfoReqBD02SPVIInfo> PVIInfo;

    @JsonIgnore
    public String getOpertMFlag() {
        return OpertMFlag;
    }

    @JsonIgnore
    public void setOpertMFlag(String opertMFlag) {
        OpertMFlag = opertMFlag;
    }

    @JsonIgnore
    public List<SaveRoleInfoReqBD02SPVIInfo> getPVIInfo() {
        return PVIInfo;
    }

    @JsonIgnore
    public void setPVIInfo(List<SaveRoleInfoReqBD02SPVIInfo> PVIInfo) {
        this.PVIInfo = PVIInfo;
    }
}
