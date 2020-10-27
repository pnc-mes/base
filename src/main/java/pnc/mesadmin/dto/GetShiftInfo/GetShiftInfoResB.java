package pnc.mesadmin.dto.GetShiftInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2018/6/19.
 */
public class GetShiftInfoResB implements Serializable {

    @JsonProperty("MsgCode")
    private String MsgCode;
    @JsonProperty("MsgDes")
    private String MsgDes;
    @JsonProperty("Data")
    private GetShiftInfoResD Data;
    @JsonIgnore
    public String getMsgCode() {
        return MsgCode;
    }
    @JsonIgnore
    public void setMsgCode(String msgCode) {
        MsgCode = msgCode;
    }
    @JsonIgnore
    public String getMsgDes() {
        return MsgDes;
    }
    @JsonIgnore
    public void setMsgDes(String msgDes) {
        MsgDes = msgDes;
    }
    @JsonIgnore
    public GetShiftInfoResD getData() {
        return Data;
    }
    @JsonIgnore
    public void setData(GetShiftInfoResD data) {
        Data = data;
    }
}
