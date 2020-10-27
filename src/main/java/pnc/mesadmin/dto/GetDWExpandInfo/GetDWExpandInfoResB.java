package pnc.mesadmin.dto.GetDWExpandInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: 郝赞
 * @Date: 2018/9/5 17:32
 * @Description:
 */
public class GetDWExpandInfoResB implements Serializable {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private List<GetDWExpandInfoResD> Data;

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
    public List<GetDWExpandInfoResD> getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(List<GetDWExpandInfoResD> data) {
        Data = data;
    }
}
