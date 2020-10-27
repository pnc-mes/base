package pnc.mesadmin.dto.GetCDataInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetCKBarInfo.GetCKBarInfoResD;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/3 10:28
 * @Description:
 */
public class GetCDataInfoResB implements Serializable {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private GetCDataInfoResD Data;

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
    public GetCDataInfoResD getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(GetCDataInfoResD data) {
        Data = data;
    }
}
