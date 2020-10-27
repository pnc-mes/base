package pnc.mesadmin.dto.GetLInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @ClassName: GetAllLInfoResB  
 * @Description: TODO 返回消息的body信息
 * @author: linyichun
 * @date: 2017-6-8 下午6:12:16
 */
public class GetLInfoResB implements Serializable{

    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private GetLInfoResD Data;

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
    public GetLInfoResD getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(GetLInfoResD data) {
        Data = data;
    }
}
