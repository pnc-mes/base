package pnc.mesadmin.dto.SaveGCInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by liufuzhi on 2017/8/25.
 */
public class SaveGCInfoReqBD00ModeC implements Serializable {
    @JsonProperty("ModeName")
    private String ModeName;

    @JsonProperty("ModeValue")
    private String ModeValue;

    @JsonIgnore
    public String getModeName() {
        return ModeName;
    }

    @JsonIgnore
    public void setModeName(String modeName) {
        ModeName = modeName;
    }

    @JsonIgnore
    public String getModeValue() {
        return ModeValue;
    }

    @JsonIgnore
    public void setModeValue(String modeValue) {
        ModeValue = modeValue;
    }
}
