package pnc.mesadmin.dto.GetMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/1.
 */
public class GetMaInfoResDMaType implements Serializable{

    @JsonProperty("MaTRd")
    private String MaTRd;

    @JsonProperty("MaTName")
    private String MaTName;

    @JsonProperty("ExpandRd")
    private int ExpandRd;

    @JsonIgnore
    public String getMaTRd() {
        return MaTRd;
    }

    @JsonIgnore
    public void setMaTRd(String maTRd) {
        MaTRd = maTRd;
    }

    @JsonIgnore
    public String getMaTName() {
        return MaTName;
    }

    @JsonIgnore
    public void setMaTName(String maTName) {
        MaTName = maTName;
    }

    @JsonIgnore
    public int getExpandRd() {
        return ExpandRd;
    }

    @JsonIgnore
    public void setExpandRd(int expandRd) {
        ExpandRd = expandRd;
    }
}
