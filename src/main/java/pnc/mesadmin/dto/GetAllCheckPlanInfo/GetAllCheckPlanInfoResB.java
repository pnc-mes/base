package pnc.mesadmin.dto.GetAllCheckPlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetAllCyclePlanInfo.GetAllCyclePlanInfoResD;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/10 09:51
 * @Description:
 */
public class GetAllCheckPlanInfoResB {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private List<GetAllCheckPlanInfoResD> Data;

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
    public List<GetAllCheckPlanInfoResD> getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(List<GetAllCheckPlanInfoResD> data) {
        Data = data;
    }
}
