package pnc.mesadmin.dto.SaveLineInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2018/7/26.
 */
public class SaveOEMLineInfoReq00OEMLineList implements Serializable {

    @JsonProperty("LineRd")
    private int LineRd ;

    @JsonIgnore
    public int getLineRd() {
        return LineRd;
    }
    @JsonIgnore
    public void setLineRd(int lineRd) {
        LineRd = lineRd;
    }
}
