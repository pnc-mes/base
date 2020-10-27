package pnc.mesadmin.dto.SaveMaxTimeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/30 16:13
 * @Description:
 */
public class SaveMaxTimeInfoReq01  implements Serializable {
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
