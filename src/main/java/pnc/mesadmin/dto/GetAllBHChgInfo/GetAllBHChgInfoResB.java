package pnc.mesadmin.dto.GetAllBHChgInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhaochao on 11/20 0020.
 */
public class GetAllBHChgInfoResB implements Serializable{

    @JsonProperty("MsgCode")
    private String MsgCode;
    @JsonProperty("MsgDes")
    private String MsgDes;
    @JsonProperty("Data")
    private List<GetAllBHChgInfoResBD> Data;

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
    public List<GetAllBHChgInfoResBD> getData() {
        return Data;
    }
    @JsonIgnore
    public void setData(List<GetAllBHChgInfoResBD> data) {
        Data = data;
    }
}
