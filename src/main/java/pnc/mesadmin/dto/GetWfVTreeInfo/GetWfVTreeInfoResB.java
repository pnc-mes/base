package pnc.mesadmin.dto.GetWfVTreeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by xfxi on 2017/7/14.
 */
public class GetWfVTreeInfoResB {

    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private List<GetWfVTreeInfoResD> Data;

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
    public List<GetWfVTreeInfoResD> getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(List<GetWfVTreeInfoResD> data) {
        Data = data;
    }
}
