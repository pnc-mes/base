package pnc.mesadmin.dto.UserLoginInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserLoginInfoResDLine {

    @JsonProperty("LineRd")
    private int LineRd;

    @JsonProperty("LineName")
    private String LineName;

    @JsonProperty("OEMLine")
    private List<UserLoginInfoResDLOEMLine> OEMLine;

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

    @JsonIgnore
    public List<UserLoginInfoResDLOEMLine> getOEMLine() {
        return OEMLine;
    }

    @JsonIgnore
    public void setOEMLine(List<UserLoginInfoResDLOEMLine> OEMLine) {
        this.OEMLine = OEMLine;
    }
}
