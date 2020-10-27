package pnc.mesadmin.dto.GetAllPickInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetAllPrInfo.GetAllPrInfoResD;

import java.util.List;

/**
 * Created by PNC on 2017/9/13.
 */
public class GetAllPickInfoResB {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private List<GetAllPickInfoResD> Data;

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
    public List<GetAllPickInfoResD> getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(List<GetAllPickInfoResD> data) {
        Data = data;
    }
}
