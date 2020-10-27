package pnc.mesadmin.dto.GetEmailInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2018/7/4.
 */
public class GetEmailInfoResB implements Serializable {


    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private GetEmailInfoResD Data;
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
    public GetEmailInfoResD getData() {
        return Data;
    }
    @JsonIgnore
    public void setData(GetEmailInfoResD data) {
        Data = data;
    }
}
