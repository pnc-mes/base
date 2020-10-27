package pnc.mesadmin.dto.GetMVInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/3.
 */
public class GetMVInfoResDBOMInfo implements Serializable{

    @JsonProperty("BOMVerRd")
    private int BOMVerRd;

    @JsonProperty("BOMName")
    private String BOMName;

    @JsonIgnore
    public int getBOMVerRd() {
        return BOMVerRd;
    }

    @JsonIgnore
    public void setBOMVerRd(int BOMVerRd) {
        this.BOMVerRd = BOMVerRd;
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
