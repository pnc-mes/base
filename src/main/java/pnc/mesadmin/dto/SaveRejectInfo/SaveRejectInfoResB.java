package pnc.mesadmin.dto.SaveRejectInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.SaveBatchInfo.SaveBatchInfoResD;

/**
 * Created by HAOZAN on 2018/8/6.
 */
public class SaveRejectInfoResB {
    @JsonProperty("MsgCode")
    private String MsgCode;
    @JsonProperty("MsgDes")
    private String MsgDes;
    @JsonProperty("Data")
    private SaveRejectInfoResD Data;
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
    public SaveRejectInfoResD getData() {
        return Data;
    }
    @JsonIgnore
    public void setData(SaveRejectInfoResD data) {
        Data = data;
    }
}
