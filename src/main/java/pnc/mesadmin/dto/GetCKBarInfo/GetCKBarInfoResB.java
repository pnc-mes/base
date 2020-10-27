package pnc.mesadmin.dto.GetCKBarInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetBVInfo.GetBVInfoResD;

import java.util.List;

/**
 * Created by PNC on 2017/8/9.
 */
public class GetCKBarInfoResB {

    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private List<GetCKBarInfoResD> Data;

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
    public List<GetCKBarInfoResD> getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(List<GetCKBarInfoResD> data) {
        Data = data;
    }
}
