package pnc.mesadmin.dto.SaveBatchInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/6/9.
 */
public class SaveBatchInfoResB {
    @JsonProperty("MsgCode")
    private String MsgCode;
    @JsonProperty("MsgDes")
    private String MsgDes;
    @JsonProperty("Data")
    private SaveBatchInfoResD Data;
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
    public SaveBatchInfoResD getData() {
        return Data;
    }
    @JsonIgnore
    public void setData(SaveBatchInfoResD data) {
        Data = data;
    }
}
