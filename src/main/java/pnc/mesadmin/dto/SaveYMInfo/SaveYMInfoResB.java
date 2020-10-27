package pnc.mesadmin.dto.SaveYMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zhaochao on 11/6 0006.
 */
public class SaveYMInfoResB {

    @JsonProperty("MsgCode")
    private String MsgCode;
    @JsonProperty("MsgDes")
    private String MsgDes;

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
}
