package pnc.mesadmin.dto.GetBHChgDtlInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by zhaochao on 11/20 0020.
 */
public class GetBHChgDtlInfoResB {

    @JsonProperty("MsgCode")
    private String MsgCode;
    @JsonProperty("MsgDes")
    private String MsgDes;
    @JsonProperty("Data")
    private List<GetBHChgDtlInfoResBD> Data;

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
    public List<GetBHChgDtlInfoResBD> getData() {
        return Data;
    }
    @JsonIgnore
    public void setData(List<GetBHChgDtlInfoResBD> data) {
        Data = data;
    }
}
