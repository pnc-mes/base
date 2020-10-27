package pnc.mesadmin.dto.GetMaxTimeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/30 13:29
 * @Description:
 */
public class GetMaxTimeInfoReq00  implements Serializable {
    @JsonProperty("MaxTimeRd")
    private int MaxTimeRd;
    @JsonIgnore
    public int getMaxTimeRd() {
        return MaxTimeRd;
    }
    @JsonIgnore
    public void setMaxTimeRd(int maxTimeRd) {
        MaxTimeRd = maxTimeRd;
    }
}
