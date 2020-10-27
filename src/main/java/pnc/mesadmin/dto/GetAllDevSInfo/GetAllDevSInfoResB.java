package pnc.mesadmin.dto.GetAllDevSInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetAllDevInfo.GetAllDevInfoResD;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PNC on 2017/8/18.
 */
public class GetAllDevSInfoResB implements Serializable {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private List<GetAllDevSInfoResD> Data;
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
    public List<GetAllDevSInfoResD> getData() {
        return Data;
    }
    @JsonIgnore
    public void setData(List<GetAllDevSInfoResD> data) {
        Data = data;
    }
}
