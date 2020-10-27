package pnc.mesadmin.dto.SaveLInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @ClassName: SaveLInfoResB 
 * @Description: TODO 保存
 * @author: linyichun
 * @date: 2017-6-9 上午12:27:11
 */
public class SaveLInfoResB implements Serializable{

    @JsonProperty("MsgCode")
    private String MsgCode; //消息代码

    @JsonProperty("MsgDes")
    private String MsgDes;  //消息描述

    @JsonProperty("Data")
    private SaveLInfoResD Data; //data list

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
    public SaveLInfoResD getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(SaveLInfoResD data) {
        Data = data;
    }
}
