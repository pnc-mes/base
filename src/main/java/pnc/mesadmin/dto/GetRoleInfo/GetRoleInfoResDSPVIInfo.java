package pnc.mesadmin.dto.GetRoleInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/8/21.
 */
public class GetRoleInfoResDSPVIInfo {

    @JsonProperty("PVFlag")
    private String PVFlag;

    @JsonIgnore
    public String getPVFlag() {
        return PVFlag;
    }

    @JsonIgnore
    public void setPVFlag(String PVFlag) {
        this.PVFlag = PVFlag;
    }
}
