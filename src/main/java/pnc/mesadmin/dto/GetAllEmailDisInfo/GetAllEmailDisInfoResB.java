package pnc.mesadmin.dto.GetAllEmailDisInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetAllFaInfo.GetAllFaInfoResD;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/5 18:17
 * @Description:
 */
public class GetAllEmailDisInfoResB {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private List<GetAllEmailDisInfoResD> Data;

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
    public List<GetAllEmailDisInfoResD> getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(List<GetAllEmailDisInfoResD> data) {
        Data = data;
    }
}
