package pnc.mesadmin.dto.GetPdReGpInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/6/6.
 */
public class GetPdReGpInfoResDPDInfo implements Serializable{

    @JsonProperty("MaRd")
    private int MaRd;

    @JsonProperty("MaName")
    private String MaName;

    @JsonProperty("Ver")
    private List<GetPdReGpInfoResDPVer> Ver;

    @JsonIgnore
    public int getMaRd() {
        return MaRd;
    }

    @JsonIgnore
    public void setMaRd(int maRd) {
        MaRd = maRd;
    }

    @JsonIgnore
    public String getMaName() {
        return MaName;
    }

    @JsonIgnore
    public void setMaName(String maName) {
        MaName = maName;
    }

    @JsonIgnore
    public List<GetPdReGpInfoResDPVer> getVer() {
        return Ver;
    }

    @JsonIgnore
    public void setVer(List<GetPdReGpInfoResDPVer> ver) {
        Ver = ver;
    }
}
