package pnc.mesadmin.dto.newmove.GetAllQCMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PNC on 2019/4/3.
 */
public class GetAllQCMInfoResB implements Serializable {

    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private List<GetAllQCMInfoResD> Data;
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
    public List<GetAllQCMInfoResD> getData() {
        return Data;
    }
    @JsonIgnore
    public void setData(List<GetAllQCMInfoResD> data) {
        Data = data;
    }
}
