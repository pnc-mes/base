package pnc.mesadmin.dto.GetLineInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2018/7/26.
 */
public class GetLineInfoResDB implements Serializable {
    @JsonProperty("LineName ")
    private String LineName ;
    @JsonProperty("LineRd")
    private int LineRd;

    @JsonIgnore
    public String getLineName() {
        return LineName;
    }
    @JsonIgnore
    public void setLineName(String lineName) {
        LineName = lineName;
    }
    @JsonIgnore
    public int getLineRd() {
        return LineRd;
    }
    @JsonIgnore
    public void setLineRd(int lineRd) {
        LineRd = lineRd;
    }

}
