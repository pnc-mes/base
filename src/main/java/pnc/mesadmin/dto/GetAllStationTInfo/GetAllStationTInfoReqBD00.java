package pnc.mesadmin.dto.GetAllStationTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by 郝赞 on 2018/11/12.
 */
public class GetAllStationTInfoReqBD00 implements Serializable{

    @JsonProperty("LineRd")
    private Integer LineRd;

    @JsonProperty("UserRd")
    private Integer UserRd;

    @JsonProperty("DevRd")
    private Integer DevRd;

    @JsonIgnore
    public Integer getLineRd() {
        return LineRd;
    }
    @JsonIgnore
    public void setLineRd(Integer lineRd) {
        LineRd = lineRd;
    }
    @JsonIgnore
    public Integer getUserRd() {
        return UserRd;
    }
    @JsonIgnore
    public void setUserRd(Integer userRd) {
        UserRd = userRd;
    }
    @JsonIgnore
    public Integer getDevRd() {
        return DevRd;
    }
    @JsonIgnore
    public void setDevRd(Integer devRd) {
        DevRd = devRd;
    }
}
