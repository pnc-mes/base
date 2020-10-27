package pnc.mesadmin.dto.GetDevSMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by liufuzhi on 2017/9/14.
 */
public class GetDevSMInfoResDDevSMInfo  implements Serializable {
    @JsonProperty("SouDSInfo")
    private GetDevSMInfoResDSouDS SouDSInfo;
    @JsonProperty("TarDSInfo")
    private GetDevSMInfoResTarDS TarDSInfo;

    @JsonIgnore
    public GetDevSMInfoResDSouDS getSouDSInfo() {
        return SouDSInfo;
    }

    @JsonIgnore
    public void setSouDSInfo(GetDevSMInfoResDSouDS souDSInfo) {
        SouDSInfo = souDSInfo;
    }

    @JsonIgnore
    public GetDevSMInfoResTarDS getTarDSInfo() {
        return TarDSInfo;
    }

    @JsonIgnore
    public void setTarDSInfo(GetDevSMInfoResTarDS tarDSInfo) {
        TarDSInfo = tarDSInfo;
    }
}
