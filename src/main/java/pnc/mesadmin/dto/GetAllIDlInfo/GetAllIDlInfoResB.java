package pnc.mesadmin.dto.GetAllIDlInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetAllIChInfo.GetAllIChInfoResD;

import java.util.List;

/**
 * Created by PNC on 2017/8/24.
 */
public class GetAllIDlInfoResB {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private List<GetAllIDlInfoResD> Data;

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
    public List<GetAllIDlInfoResD> getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(List<GetAllIDlInfoResD> data) {
        Data = data;
    }
}
