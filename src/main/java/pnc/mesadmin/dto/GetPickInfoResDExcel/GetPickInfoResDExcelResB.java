package pnc.mesadmin.dto.GetPickInfoResDExcel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by liufuzhi on 2017/12/18.
 */
public class GetPickInfoResDExcelResB {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private List<GetPickInfoResDExcel> Data;
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
    public List<GetPickInfoResDExcel> getData() {
        return Data;
    }
    @JsonIgnore
    public void setData(List<GetPickInfoResDExcel> data) {
        Data = data;
    }
}
