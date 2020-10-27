package pnc.mesadmin.dto.GetRMaNTotalInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetRMaNInfo.GetRMaNInfoResD;

import java.util.List;

/**
 * Created by PNC on 2017/9/26.
 */
public class GetRMaNTotalInfoResB {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private List<GetRMaNTotalInfoResD> Data;
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
    public List<GetRMaNTotalInfoResD> getData() {
        return Data;
    }
    @JsonIgnore
    public void setData(List<GetRMaNTotalInfoResD> data) {
        Data = data;
    }
}
