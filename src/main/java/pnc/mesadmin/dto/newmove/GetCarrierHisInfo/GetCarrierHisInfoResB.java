package pnc.mesadmin.dto.newmove.GetCarrierHisInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.newmove.GetBarCodeInfo.GetBarCodeInfoResD;

import java.util.List;

public class GetCarrierHisInfoResB {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private List<GetCarrierHisInfoResD> Data;

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
    public List<GetCarrierHisInfoResD> getData() {
        return Data;
    }
    @JsonIgnore
    public void setData(List<GetCarrierHisInfoResD> data) {
        Data = data;
    }
}
