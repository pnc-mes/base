package pnc.mesadmin.dto.GetAllBatchInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetAllOpertInfo.GetAllOpertInfoResB;

import java.util.List;

/**
 * Created by PNC on 2017/6/9.
 */
public class GetAllBatchInfoResB {
    @JsonProperty("MsgCode")
    private String MsgCode;
    @JsonProperty("MsgDes")
    private String MsgDes;
    @JsonProperty("Data")
    private List<GetAllBatchInfoResD> Data;

    @JsonIgnore
    public List<GetAllBatchInfoResD> getData() {
        return Data;
    }
    @JsonIgnore
    public void setData(List<GetAllBatchInfoResD> data) {
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
