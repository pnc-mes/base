package pnc.mesadmin.dto.GetMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/1.
 */
public class GetMaInfoResDBOMInfo implements Serializable{

    @JsonProperty("BOMRd")
    private int BOMRd;

    @JsonProperty("BOMName")
    private String BOMName;

    @JsonIgnore
    public int getBOMRd() {
        return BOMRd;
    }

    @JsonIgnore
    public void setBOMRd(int BOMRd) {
        this.BOMRd = BOMRd;
    }

    @JsonIgnore
    public String getBOMName() {
        return BOMName;
    }

    @JsonIgnore
    public void setBOMName(String BOMName) {
        this.BOMName = BOMName;
    }
}
