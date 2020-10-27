package pnc.mesadmin.dto.GetAllPurchInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetAllRCGInfo.GetAllRCGInfoResD;

import java.util.List;

/**
 * Created by PNC on 2017/8/23.
 */
public class GetAllPurchInfoResB {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private List<GetAllPurchInfoResD> Data;
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
    public List<GetAllPurchInfoResD> getData() {
        return Data;
    }
    @JsonIgnore
    public void setData(List<GetAllPurchInfoResD> data) {
        Data = data;
    }
}
