package pnc.mesadmin.dto.SaveSGInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.SaveSNInfo.SaveSNInfoResD;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PNC on 2017/8/16.
 */
public class SaveSGInfoResB  implements Serializable {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private SaveSGInfoResD Data;



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
    public SaveSGInfoResD getData() {
        return Data;
    }
    @JsonIgnore
    public void setData(SaveSGInfoResD data) {
        Data = data;
    }
}
