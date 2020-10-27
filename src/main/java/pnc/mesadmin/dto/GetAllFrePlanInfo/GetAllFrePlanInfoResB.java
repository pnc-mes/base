package pnc.mesadmin.dto.GetAllFrePlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @Auther: haozan
 * @Date: 2018/9/7
 * @Description:
 */
public class GetAllFrePlanInfoResB {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private List<GetAllFrePlanInfoResD> Data;
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
    public List<GetAllFrePlanInfoResD> getData() {
        return Data;
    }
    @JsonIgnore
    public void setData(List<GetAllFrePlanInfoResD> data) {
        Data = data;
    }
}
