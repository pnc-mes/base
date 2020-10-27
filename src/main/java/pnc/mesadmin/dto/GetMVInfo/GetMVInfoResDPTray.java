package pnc.mesadmin.dto.GetMVInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/3.
 */
public class GetMVInfoResDPTray implements Serializable{

    @JsonProperty("MPRd")
    private int MPRd;

    @JsonProperty("PTInfo")
    private GetMVInfoResDPTPTInfo PTInfo;

    @JsonProperty("SRInfo")
    private GetMVInfoResDPTSRInfo SRInfo;

    @JsonProperty("Num")
    private int Num;

    @JsonProperty("Weight")
    private float Weight;

    @JsonProperty("UpLimit")
    private float UpLimit;

    @JsonProperty("DownLimit")
    private float DownLimit;

    @JsonProperty("IsDef")
    private String IsDef;

    @JsonIgnore
    public int getMPRd() {
        return MPRd;
    }

    @JsonIgnore
    public void setMPRd(int MPRd) {
        this.MPRd = MPRd;
    }

    @JsonIgnore
    public GetMVInfoResDPTPTInfo getPTInfo() {
        return PTInfo;
    }

    @JsonIgnore
    public void setPTInfo(GetMVInfoResDPTPTInfo PTInfo) {
        this.PTInfo = PTInfo;
    }

    @JsonIgnore
    public GetMVInfoResDPTSRInfo getSRInfo() {
        return SRInfo;
    }

    @JsonIgnore
    public void setSRInfo(GetMVInfoResDPTSRInfo SRInfo) {
        this.SRInfo = SRInfo;
    }

    @JsonIgnore
    public int getNum() {
        return Num;
    }

    @JsonIgnore
    public void setNum(int num) {
        Num = num;
    }

    @JsonIgnore
    public float getWeight() {
        return Weight;
    }

    @JsonIgnore
    public void setWeight(float weight) {
        Weight = weight;
    }

    @JsonIgnore
    public float getUpLimit() {
        return UpLimit;
    }

    @JsonIgnore
    public void setUpLimit(float upLimit) {
        UpLimit = upLimit;
    }

    @JsonIgnore
    public float getDownLimit() {
        return DownLimit;
    }

    @JsonIgnore
    public void setDownLimit(float downLimit) {
        DownLimit = downLimit;
    }

    @JsonIgnore
    public String getIsDef() {
        return IsDef;
    }

    @JsonIgnore
    public void setIsDef(String isDef) {
        IsDef = isDef;
    }
}
