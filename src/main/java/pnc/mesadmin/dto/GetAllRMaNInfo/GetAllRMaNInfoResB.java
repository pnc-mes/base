package pnc.mesadmin.dto.GetAllRMaNInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetAllRoleInfo.GetAllRoleInfoResD;

import java.util.List;

/**
 * Created by PNC on 2017/9/26.
 */
public class GetAllRMaNInfoResB {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private List<GetAllRMaNInfoResD> Data;

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
    public List<GetAllRMaNInfoResD> getData() {
        return Data;
    }
    @JsonIgnore
    public void setData(List<GetAllRMaNInfoResD> data) {
        Data = data;
    }
}
