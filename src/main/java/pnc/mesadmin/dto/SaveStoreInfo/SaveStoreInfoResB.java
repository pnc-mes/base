package pnc.mesadmin.dto.SaveStoreInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @ClassName: SaveStoreInfoResB 
 * @Description: TODO 保存
 * @author: linyichun
 * @date: 2017-6-9 上午12:27:11
 */
public class SaveStoreInfoResB implements Serializable{

    @JsonProperty("MsgCode")
    private String MsgCode; //消息代码

    @JsonProperty("MsgDes")
    private String MsgDes;  //消息描述

    @JsonProperty("Data")
    private SaveStoreInfoResD Data; //data list

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
    public SaveStoreInfoResD getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(SaveStoreInfoResD data) {
        Data = data;
    }
}
