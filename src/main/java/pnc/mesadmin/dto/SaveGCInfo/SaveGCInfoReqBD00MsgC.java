package pnc.mesadmin.dto.SaveGCInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by liufuzhi on 2017/8/25.
 */
public class SaveGCInfoReqBD00MsgC implements Serializable {
    @JsonProperty("MsgName")
    private String MsgName  ;

    @JsonProperty("MsgValue")
    private String MsgValue;

    @JsonIgnore
    public String getMsgName() {
        return MsgName;
    }

    @JsonIgnore
    public void setMsgName(String msgName) {
        MsgName = msgName;
    }

    @JsonIgnore
    public String getMsgValue() {
        return MsgValue;
    }

    @JsonIgnore
    public void setMsgValue(String msgValue) {
        MsgValue = msgValue;
    }
}
