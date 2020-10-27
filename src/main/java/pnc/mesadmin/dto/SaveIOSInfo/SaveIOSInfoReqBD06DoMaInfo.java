package pnc.mesadmin.dto.SaveIOSInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SaveIOSInfoReqBD06DoMaInfo {

    @JsonProperty("MaVerRd")
    private int MaVerRd;

    @JsonProperty("DoNum")
    private float DoNum;

    @JsonProperty("Lock")
    private String Lock;

    @JsonProperty("DoBaInfo")
    private List<SaveIOSInfoReqBD06DDoBaInfo> DoBaInfo;

    @JsonIgnore
    public int getMaVerRd() {
        return MaVerRd;
    }

    @JsonIgnore
    public void setMaVerRd(int maVerRd) {
        MaVerRd = maVerRd;
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
    public String getLock() {
        return Lock;
    }

    @JsonIgnore
    public void setLock(String lock) {
        Lock = lock;
    }

    @JsonIgnore
    public List<SaveIOSInfoReqBD06DDoBaInfo> getDoBaInfo() {
        return DoBaInfo;
    }

    @JsonIgnore
    public void setDoBaInfo(List<SaveIOSInfoReqBD06DDoBaInfo> doBaInfo) {
        DoBaInfo = doBaInfo;
    }
}
