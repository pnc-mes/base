package pnc.mesadmin.dto.VBarInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/21.
 */
public class VBarInfoReqBD00 implements Serializable{

    @JsonProperty("Batch")
    private String Batch;

    @JsonProperty("WoRd")
    private int WoRd;

    @JsonProperty("ReNum")
    private float ReNum;

    @JsonProperty("ScanTime")
    private String ScanTime;

    @JsonIgnore
    public String getBatch() {
        return Batch;
    }

    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
    }

    @JsonIgnore
    public int getWoRd() {
        return WoRd;
    }

    @JsonIgnore
    public void setWoRd(int woRd) {
        WoRd = woRd;
    }

    @JsonIgnore
    public float getReNum() {
        return ReNum;
    }

    @JsonIgnore
    public void setReNum(float reNum) {
        ReNum = reNum;
    }

    @JsonIgnore
    public String getScanTime() {
        return ScanTime;
    }

    @JsonIgnore
    public void setScanTime(String scanTime) {
        ScanTime = scanTime;
    }
}
