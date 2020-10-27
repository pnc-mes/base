package pnc.mesadmin.dto.GetAllStoreInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @ClassName: GetAllStoreInfoResB  
 * @Description: TODO 返回消息的body信息
 * @author: linyichun
 * @date: 2017-6-8 下午6:12:16
 */
public class GetAllStoreInfoResB implements Serializable{

    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private List<GetAllStoreInfoResD> Data;

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
    public List<GetAllStoreInfoResD> getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(List<GetAllStoreInfoResD> data) {
        Data = data;
    }
}
