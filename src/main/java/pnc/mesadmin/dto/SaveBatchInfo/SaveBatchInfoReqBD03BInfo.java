package pnc.mesadmin.dto.SaveBatchInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/7/22.
 */
public class SaveBatchInfoReqBD03BInfo {

    @JsonProperty("WoDlRd")
    private int WoDlRd;

    @JsonProperty("MaVerRd")
    private int MaVerRd;

    @JsonProperty("SplitBCount")
    private int SplitBCount;

    @JsonProperty("BCount")
    private float BCount;

    @JsonProperty("IsPrintPack")
    private String IsPrintPack;

    @JsonProperty("IsPrintWP")
    private String IsPrintWP;

    @JsonProperty("F1")
    private String F1;

    @JsonProperty("F2")
    private String F2;

    @JsonProperty("F3")
    private String F3;

    @JsonIgnore
    public int getMaVerRd() {
        return MaVerRd;
    }

    @JsonIgnore
    public void setMaVerRd(int maVerRd) {
        MaVerRd = maVerRd;
    }

    @JsonIgnore
    public int getWoDlRd() {
        return WoDlRd;
    }

    @JsonIgnore
    public void setWoDlRd(int woDlRd) {
        WoDlRd = woDlRd;
    }

    @JsonIgnore
    public int getSplitBCount() {
        return SplitBCount;
    }

    @JsonIgnore
    public void setSplitBCount(int splitBCount) {
        SplitBCount = splitBCount;
    }

    @JsonIgnore
    public float getBCount() {
        return BCount;
    }

    @JsonIgnore
    public void setBCount(float BCount) {
        this.BCount = BCount;
    }

    @JsonIgnore
    public String getIsPrintPack() {
        return IsPrintPack;
    }

    @JsonIgnore
    public void setIsPrintPack(String isPrintPack) {
        IsPrintPack = isPrintPack;
    }

    @JsonIgnore
    public String getF1() {
        return F1;
    }

    @JsonIgnore
    public void setF1(String f1) {
        F1 = f1;
    }

    @JsonIgnore
    public String getF2() {
        return F2;
    }

    @JsonIgnore
    public void setF2(String f2) {
        F2 = f2;
    }

    @JsonIgnore
    public String getF3() {
        return F3;
    }

    @JsonIgnore
    public void setF3(String f3) {
        F3 = f3;
    }

    @JsonIgnore
    public String getIsPrintWP() {
        return IsPrintWP;
    }

    @JsonIgnore
    public void setIsPrintWP(String isPrintWP) {
        IsPrintWP = isPrintWP;
    }
}
