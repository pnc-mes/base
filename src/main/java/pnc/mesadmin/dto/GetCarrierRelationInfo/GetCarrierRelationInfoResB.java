package pnc.mesadmin.dto.GetCarrierRelationInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetCKBarInfo.GetCKBarInfoResD;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/27 09:10
 * @Description:
 */
public class GetCarrierRelationInfoResB {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private List<GetCarrierRelationInfoResD> Data;

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
    public List<GetCarrierRelationInfoResD> getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(List<GetCarrierRelationInfoResD> data) {
        Data = data;
    }
}
