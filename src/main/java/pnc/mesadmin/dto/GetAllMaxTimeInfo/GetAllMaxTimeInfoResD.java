package pnc.mesadmin.dto.GetAllMaxTimeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/30 10:37
 * @Description:
 */
public class GetAllMaxTimeInfoResD {
    @JsonProperty("MaxTimeRd")
    private int MaxTimeRd;

    @JsonProperty("MaxTimeWindowName")
    private String MaxTimeWindowName;

    @JsonIgnore
    public int getMaxTimeRd() {
        return MaxTimeRd;
    }
    @JsonIgnore
    public void setMaxTimeRd(int maxTimeRd) {
        MaxTimeRd = maxTimeRd;
    }
    @JsonIgnore
    public String getMaxTimeWindowName() {
        return MaxTimeWindowName;
    }
    @JsonIgnore
    public void setMaxTimeWindowName(String maxTimeWindowName) {
        MaxTimeWindowName = maxTimeWindowName;
    }
}
