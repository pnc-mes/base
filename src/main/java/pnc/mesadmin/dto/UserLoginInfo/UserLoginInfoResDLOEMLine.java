package pnc.mesadmin.dto.UserLoginInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserLoginInfoResDLOEMLine {

    @JsonProperty("LineRd")
    private int LineRd;

    @JsonProperty("LineName")
    private String LineName;

    @JsonIgnore
    public int getLineRd() {
        return LineRd;
    }

    @JsonIgnore
    public void setLineRd(int lineRd) {
        LineRd = lineRd;
    }

    @JsonIgnore
    public String getLineName() {
        return LineName;
    }

    @JsonIgnore
    public void setLineName(String lineName) {
        LineName = lineName;
    }
}
