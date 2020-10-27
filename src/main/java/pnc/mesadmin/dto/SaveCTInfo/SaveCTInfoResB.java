package pnc.mesadmin.dto.SaveCTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.SaveCusInfo.SaveCusInfoResD;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/27 15:16
 * @Description:
 */
public class SaveCTInfoResB {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private SaveCTInfoResD Data;

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
    public SaveCTInfoResD getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(SaveCTInfoResD data) {
        Data = data;
    }
}
