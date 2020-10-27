package pnc.mesadmin.dto.SaveCalendarInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.SaveCarrierFamilyInfo.SaveCarrierFamilyInfoResD;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 20:37
 * @Description:
 */
public class SaveCalendarInfoResB {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private SaveCalendarInfoResD Data;

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
    public SaveCalendarInfoResD getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(SaveCalendarInfoResD data) {
        Data = data;
    }
}
