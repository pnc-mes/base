package pnc.mesadmin.dto.GetCKDlInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetBVInfo.GetBVInfoResD;

import java.util.List;

/**
 * Created by PNC on 2017/8/9.
 */
public class GetCKDlInfoResB {

    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    protected List<GetCKDlInfoResD> Data;

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
    public List<GetCKDlInfoResD> getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(List<GetCKDlInfoResD> data) {
        Data = data;
    }
}
