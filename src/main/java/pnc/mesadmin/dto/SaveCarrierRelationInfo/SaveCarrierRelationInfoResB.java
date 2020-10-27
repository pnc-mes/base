package pnc.mesadmin.dto.SaveCarrierRelationInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.SaveCKInfo.SaveCKInfoResD;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/25 19:46
 * @Description:
 */
public class SaveCarrierRelationInfoResB {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private SaveCarrierRelationInfoResD Data;
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
    public SaveCarrierRelationInfoResD getData() {
        return Data;
    }
    @JsonIgnore
    public void setData(SaveCarrierRelationInfoResD data) {
        Data = data;
    }
}
