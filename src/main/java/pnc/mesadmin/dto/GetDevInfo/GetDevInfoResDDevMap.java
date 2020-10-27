package pnc.mesadmin.dto.GetDevInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/13 11:15
 * @Description:
 */
public class GetDevInfoResDDevMap {
    @JsonProperty("DevMapRd")
    private int DevMapRd;

    @JsonProperty("DevMapCode")
    private String DevMapCode;

    @JsonProperty("DevMapName")
    private String DevMapName;
    @JsonIgnore
    public int getDevMapRd() {
        return DevMapRd;
    }
    @JsonIgnore
    public void setDevMapRd(int devMapRd) {
        DevMapRd = devMapRd;
    }
    @JsonIgnore
    public String getDevMapCode() {
        return DevMapCode;
    }
    @JsonIgnore
    public void setDevMapCode(String devMapCode) {
        DevMapCode = devMapCode;
    }
    @JsonIgnore
    public String getDevMapName() {
        return DevMapName;
    }
    @JsonIgnore
    public void setDevMapName(String devMapName) {
        DevMapName = devMapName;
    }
}
