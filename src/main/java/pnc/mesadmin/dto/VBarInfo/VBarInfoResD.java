package pnc.mesadmin.dto.VBarInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/6/21.
 */
public class VBarInfoResD implements Serializable{

    @JsonProperty("Batch")
    private String Batch;

    @JsonProperty("BNum")
    private float BNum;

    @JsonProperty("WoRd")
    private int WoRd;

    @JsonProperty("WoCode")
    private String WoCode;

    @JsonProperty("MaVerRd")
    private int MaVerRd;

    @JsonProperty("MaName")
    private String MaName;

    @JsonProperty("ScanTime")
    private String ScanTime;

    @JsonProperty("BoxPackInfo")
    private VBarInfoResDBoxPackInfo BoxPackInfo;

    @JsonIgnore
    public String getScanTime() {
        return ScanTime;
    }

    @JsonIgnore
    public void setScanTime(String scanTime) {
        ScanTime = scanTime;
    }

    @JsonIgnore
    public String getWoCode() {
        return WoCode;
    }

    @JsonIgnore
    public void setWoCode(String woCode) {
        WoCode = woCode;
    }

    @JsonIgnore
    public int getMaVerRd() {
        return MaVerRd;
    }

    @JsonIgnore
    public void setMaVerRd(int maVerRd) {
        MaVerRd = maVerRd;
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
    public String getBatch() {
        return Batch;
    }

    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
    }

    @JsonIgnore
    public float getBNum() {
        return BNum;
    }

    @JsonIgnore
    public void setBNum(float BNum) {
        this.BNum = BNum;
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
    public VBarInfoResDBoxPackInfo getBoxPackInfo() {
        return BoxPackInfo;
    }

    @JsonIgnore
    public void setBoxPackInfo(VBarInfoResDBoxPackInfo boxPackInfo) {
        BoxPackInfo = boxPackInfo;
    }
}
