package pnc.mesadmin.dto.GetAllMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/2.
 */
public class GetAllMaInfoReqBD00 implements Serializable{

    @JsonProperty("MaType")
    private String MaType;

    @JsonIgnore
    public String getMaType() {
        return MaType;
    }

    @JsonIgnore
    public void setMaType(String maType) {
        MaType = maType;
    }
}
