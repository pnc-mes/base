package pnc.mesadmin.dto.SaveDevSInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2017/8/18.
 */
public class SaveDevSInfoResB implements Serializable {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;
    @JsonProperty("Data")
    private SaveDevSInfoResD Data;
    @JsonIgnore
    public SaveDevSInfoResD getData() {
        return Data;
    }
    @JsonIgnore
    public void setData(SaveDevSInfoResD data) {
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
