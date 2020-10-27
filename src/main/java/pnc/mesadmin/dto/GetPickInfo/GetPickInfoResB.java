package pnc.mesadmin.dto.GetPickInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetPickInfoResDExcel.GetPickInfoResDExcel;

import java.util.List;

/**
 * Created by PNC on 2017/9/13.
 */
public class GetPickInfoResB {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private GetPickInfoResD Data;

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
    public GetPickInfoResD getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(GetPickInfoResD data) {
        Data = data;
    }
}
