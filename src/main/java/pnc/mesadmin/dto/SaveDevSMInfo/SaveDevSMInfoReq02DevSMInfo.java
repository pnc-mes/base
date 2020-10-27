package pnc.mesadmin.dto.SaveDevSMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by liufuzhi on 2017/9/14.
 */
public class SaveDevSMInfoReq02DevSMInfo implements Serializable {
    @JsonProperty("SouDSRd")
    private int SouDSRd;
    @JsonProperty("TarDSRd")
    private int TarDSRd ;

    @JsonIgnore
    public int getSouDSRd() {
        return SouDSRd;
    }

    @JsonIgnore
    public void setSouDSRd(int souDSRd) {
        SouDSRd = souDSRd;
    }

    @JsonIgnore
    public int getTarDSRd() {
        return TarDSRd;
    }

    @JsonIgnore
    public void setTarDSRd(int tarDSRd) {
        TarDSRd = tarDSRd;
    }
}
