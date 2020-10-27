package pnc.mesadmin.dto.GetMenuInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/5/19.
 */
public class GetMenuInfoResB implements Serializable{

    @JsonProperty("MesCode")
    private String MesCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private List<GetMenuInfoResD> Data;

    @JsonIgnore
    public String getMesCode() {
        return MesCode;
    }

    @JsonIgnore
    public void setMesCode(String mesCode) {
        MesCode = mesCode;
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
    public List<GetMenuInfoResD> getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(List<GetMenuInfoResD> data) {
        Data = data;
    }
}

