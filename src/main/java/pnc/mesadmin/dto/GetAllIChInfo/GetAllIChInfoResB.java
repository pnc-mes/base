package pnc.mesadmin.dto.GetAllIChInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetAllHPLInfo.GetAllHPLInfoResD;

import java.util.List;

/**
 * Created by PNC on 2017/8/24.
 */
public class GetAllIChInfoResB {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private List<GetAllIChInfoResD> Data;

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
    public List<GetAllIChInfoResD> getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(List<GetAllIChInfoResD> data) {
        Data = data;
    }
}
