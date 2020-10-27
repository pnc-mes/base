package pnc.mesadmin.dto.SaveToolInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class SaveToolInfoReqToolBZDtl implements Serializable {
    @JsonProperty("KeyName")
    private String KeyName;
    @JsonProperty("KeyValue")
    private String KeyValue;
    @JsonIgnore
    public String getKeyName() {
        return KeyName;
    }
    @JsonIgnore
    public void setKeyName(String keyName) {
        KeyName = keyName;
    }
    @JsonIgnore
    public String getKeyValue() {
        return KeyValue;
    }
    @JsonIgnore
    public void setKeyValue(String keyValue) {
        KeyValue = keyValue;
    }
}
