package pnc.mesadmin.dto.SaveMaxTimeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.SaveMaInfo.SaveMaInfoResD;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/30 16:12
 * @Description:
 */
public class SaveMaxTimeInfoResB  implements Serializable {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private SaveMaxTimeInfoResD Data;

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
    public SaveMaxTimeInfoResD getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(SaveMaxTimeInfoResD data) {
        Data = data;
    }
}
