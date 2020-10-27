package pnc.mesadmin.dto.SaveOpertInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/6/3.
 */
public class SaveOpertInfoResB {
    @JsonProperty("MsgCode")
    private String MsgCode;
    @JsonProperty("MsgDes")
    private String MsgDes;
    @JsonProperty("Data")
    private SaveOpertInfoResD Data;

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
    public SaveOpertInfoResD getData() {
        return Data;
    }
    @JsonIgnore
    public void setData(SaveOpertInfoResD data) {
        Data = data;
    }


}
