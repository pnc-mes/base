package pnc.mesadmin.dto.SaveDevSMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.SaveDevSInfo.SaveDevSInfoResD;

import java.io.Serializable;

/**
 * Created by PNC on 2017/8/21.
 */
public class SaveDevSMInfoResB implements Serializable {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;
    @JsonProperty("Data")
    private SaveDevSMInfoResD Data;
    @JsonIgnore
    public SaveDevSMInfoResD getData() {
        return Data;
    }
    @JsonIgnore
    public void setData(SaveDevSMInfoResD data) {
        Data = data;
    }

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
