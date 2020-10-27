package pnc.mesadmin.dto.GetMVInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/7/29.
 */
public class GetMVInfoResDPTPTInfo implements Serializable {

    @JsonProperty("PTRd")
    private int PTRd;

    @JsonProperty("TempName")
    private String TempName;

    @JsonIgnore
    public int getPTRd() {
        return PTRd;
    }

    @JsonIgnore
    public void setPTRd(int PTRd) {
        this.PTRd = PTRd;
    }

    @JsonIgnore
    public String getTempName() {
        return TempName;
    }

    @JsonIgnore
    public void setTempName(String tempName) {
        TempName = tempName;
    }
}
