package pnc.mesadmin.dto.SaveCDataInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import pnc.mesadmin.dto.SaveCKInfo.SaveCKInfoResD;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/3 11:15
 * @Description:
 */
public class SaveCDataInfoResB implements Serializable {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    @JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
    private SaveCDataInfoResD Data;

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
    public SaveCDataInfoResD getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(SaveCDataInfoResD data) {
        Data = data;
    }
}
