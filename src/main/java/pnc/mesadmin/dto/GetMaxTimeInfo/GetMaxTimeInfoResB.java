package pnc.mesadmin.dto.GetMaxTimeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetMdInfo.GetMdInfoResD;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/30 11:30
 * @Description:
 */
public class GetMaxTimeInfoResB {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private GetMaxTimeInfoResD Data;

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
    public GetMaxTimeInfoResD getData() {
        return Data;
    }
    @JsonIgnore
    public void setData(GetMaxTimeInfoResD data) {
        Data = data;
    }
}
