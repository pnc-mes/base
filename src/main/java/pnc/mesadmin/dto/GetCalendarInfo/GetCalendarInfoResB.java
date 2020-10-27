package pnc.mesadmin.dto.GetCalendarInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetBVInfo.GetBVInfoResD;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 20:31
 * @Description:
 */
public class GetCalendarInfoResB {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private GetCalendarInfoResD Data;

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
    public GetCalendarInfoResD getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(GetCalendarInfoResD data) {
        Data = data;
    }
}
