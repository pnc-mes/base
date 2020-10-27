package pnc.mesadmin.dto.GetAllMinTimeWindowInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PNC on 2018/7/30.
 */
public class GetAllMinTimeWindowInfoResB implements Serializable {

    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private List<GetAllMinTimeWindowInfoResD> Data;
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
    public List<GetAllMinTimeWindowInfoResD> getData() {
        return Data;
    }
    @JsonIgnore
    public void setData(List<GetAllMinTimeWindowInfoResD> data) {
        Data = data;
    }
}
