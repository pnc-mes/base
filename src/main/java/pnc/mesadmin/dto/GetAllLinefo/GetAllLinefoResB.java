package pnc.mesadmin.dto.GetAllLinefo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetAllLInfo.GetAllLInfoResD;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @ClassName: GetAllLInfoResB  
 * @Description: TODO 返回消息的body信息
 * @author: haozan
 * @date: 2018-6-7
 */
public class GetAllLinefoResB implements Serializable{

    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private List<GetAllLinefoResD> Data; //data list

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
    public List<GetAllLinefoResD> getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(List<GetAllLinefoResD> data) {
        Data = data;
    }
}
