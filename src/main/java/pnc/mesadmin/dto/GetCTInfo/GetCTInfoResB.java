package pnc.mesadmin.dto.GetCTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetCusInfo.GetCusInfoResD;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/27 14:58
 * @Description:
 */
public class GetCTInfoResB {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private GetCTInfoResD Data;

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
    public GetCTInfoResD getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(GetCTInfoResD data) {
        Data = data;
    }
}
