package pnc.mesadmin.dto.GetExpandFieldInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetEmailInfo.GetEmailInfoResD;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/11 18:22
 * @Description:
 */
public class GetExpandFieldInfoResB {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private List<GetExpandFieldInfoResD> Data;
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
    public List<GetExpandFieldInfoResD>  getData() {
        return Data;
    }
    @JsonIgnore
    public void setData(List<GetExpandFieldInfoResD>  data) {
        Data = data;
    }

}
