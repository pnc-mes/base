package pnc.mesadmin.dto.SaveDevSInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2017/8/18.
 */
public class SaveDevSInfoReq01  implements Serializable{
    @JsonProperty("DevSRd")
    private int DevSRd;

    @JsonIgnore
    public int getDevSRd() {
        return DevSRd;
    }
    @JsonIgnore
    public void setDevSRd(int devSRd) {
        DevSRd = devSRd;
    }

}
