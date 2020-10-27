package pnc.mesadmin.dto.SaveIOSInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/6/14.
 */
public class SaveIOSInfoReqBD01DoMaInfo  implements Serializable{

    @JsonProperty("MaVerRd")
    private int MaVerRd;

    @JsonProperty("ConSMode")
    private String ConSMode;

    @JsonProperty("DoNum")
    private float DoNum;

    @JsonProperty("DoBaInfo")
    private List<SaveIOSInfoReqBD01DDoBaInfo> DoBaInfo;

    @JsonIgnore
    public int getMaVerRd() {
        return MaVerRd;
    }

    @JsonIgnore
    public void setMaVerRd(int maVerRd) {
        MaVerRd = maVerRd;
    }

    @JsonIgnore
    public String getConSMode() {
        return ConSMode;
    }

    @JsonIgnore
    public void setConSMode(String conSMode) {
        ConSMode = conSMode;
    }

    @JsonIgnore
    public float getDoNum() {
        return DoNum;
    }

    @JsonIgnore
    public void setDoNum(float doNum) {
        DoNum = doNum;
    }

    @JsonIgnore
    public List<SaveIOSInfoReqBD01DDoBaInfo> getDoBaInfo() {
        return DoBaInfo;
    }

    @JsonIgnore
    public void setDoBaInfo(List<SaveIOSInfoReqBD01DDoBaInfo> doBaInfo) {
        DoBaInfo = doBaInfo;
    }
}
