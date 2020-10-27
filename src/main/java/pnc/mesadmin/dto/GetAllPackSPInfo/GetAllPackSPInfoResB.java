package pnc.mesadmin.dto.GetAllPackSPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by test on 2017/8/21.
 */
public class GetAllPackSPInfoResB implements Serializable{
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private List<GetAllPackSPInfoResD> Data;

    @JsonIgnore
    public List<GetAllPackSPInfoResD> getData() {
        return Data;
    }
    @JsonIgnore
    public void setData(List<GetAllPackSPInfoResD> data) {
        Data = data;
    }
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
}
