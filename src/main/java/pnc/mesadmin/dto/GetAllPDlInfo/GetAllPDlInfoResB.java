package pnc.mesadmin.dto.GetAllPDlInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetAllPDInfo.GetAllPDInfoResD;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PNC on 2017/8/24.
 */
public class GetAllPDlInfoResB implements Serializable {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private List<GetAllPDlInfoResD> Data;

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
    public List<GetAllPDlInfoResD> getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(List<GetAllPDlInfoResD> data) {
        Data = data;
    }
}
