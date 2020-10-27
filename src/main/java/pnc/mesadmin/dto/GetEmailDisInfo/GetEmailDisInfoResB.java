package pnc.mesadmin.dto.GetEmailDisInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetFaInfo.GetFaInfoResD;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/5 18:39
 * @Description:
 */
public class GetEmailDisInfoResB {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private GetEmailDisInfoResD Data;

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
    public GetEmailDisInfoResD getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(GetEmailDisInfoResD data) {
        Data = data;
    }
}
