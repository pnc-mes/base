package pnc.mesadmin.dto.GetAllCalendarInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetAllCarrierFamilyInfo.GetAllCarrierFamilyInfoResD;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 20:29
 * @Description:
 */
public class GetAllCalendarInfoResB {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private List<GetAllCalendarInfoResD> Data;

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
    public List<GetAllCalendarInfoResD> getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(List<GetAllCalendarInfoResD> data) {
        Data = data;
    }
}
