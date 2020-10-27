package pnc.mesadmin.dto.GetNRMTotalInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by zhaochao on 2017/10/25.
 */
public class GetNRMTotalInfoResB implements Serializable{
    @JsonProperty("MsgCode")
    private String MsgCode;
    @JsonProperty("MsgDes")
    private String MsgDes;
    @JsonProperty("Data")
    private GetNRMTotalInfoResD Data;

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
    public GetNRMTotalInfoResD getData() {
        return Data;
    }
    @JsonIgnore
    public void setData(GetNRMTotalInfoResD data) {
        Data = data;
    }
}
