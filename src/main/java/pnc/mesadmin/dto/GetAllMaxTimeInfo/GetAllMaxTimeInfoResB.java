package pnc.mesadmin.dto.GetAllMaxTimeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetAllMdInfo.GetAllMdInfoResD;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/30 10:37
 * @Description:
 */
public class GetAllMaxTimeInfoResB {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private List<GetAllMaxTimeInfoResD> Data;
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
    public List<GetAllMaxTimeInfoResD> getData() {
        return Data;
    }
    @JsonIgnore
    public void setData(List<GetAllMaxTimeInfoResD> data) {
        Data = data;
    }
}
