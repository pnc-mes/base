package pnc.mesadmin.dto.GetMaTypeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/2.
 */
public class GetMaTypeInfoResD implements Serializable{

    @JsonProperty("MaType")
    private String MaType;

    @JsonProperty("TName")
    private String TName;

    @JsonIgnore
    public String getMaType() {
        return MaType;
    }

    @JsonIgnore
    public void setMaType(String maType) {
        MaType = maType;
    }

    @JsonIgnore
    public String getTName() {
        return TName;
    }

    @JsonIgnore
    public void setTName(String TName) {
        this.TName = TName;
    }
}
