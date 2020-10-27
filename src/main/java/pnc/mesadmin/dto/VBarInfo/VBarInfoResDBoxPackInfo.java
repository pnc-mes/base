package pnc.mesadmin.dto.VBarInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/21.
 */
public class VBarInfoResDBoxPackInfo implements Serializable {

    @JsonProperty("PTRd")
    private int PTRd;

    @JsonProperty("PTName")
    private String PTName;

    @JsonProperty("Num")
    private float Num;

    @JsonProperty("Weight")
    private float Weight;

    @JsonProperty("UpLimit")
    private float UpLimit;

    @JsonProperty("DownLimit")
    private float DownLimit;

    @JsonProperty("UnitName")
    private String UnitName;

    @JsonIgnore
    public int getPTRd() {
        return PTRd;
    }

    @JsonIgnore
    public void setPTRd(int PTRd) {
        this.PTRd = PTRd;
    }

    @JsonIgnore
    public String getPTName() {
        return PTName;
    }

    @JsonIgnore
    public void setPTName(String PTName) {
        this.PTName = PTName;
    }

    @JsonIgnore
    public float getNum() {
        return Num;
    }

    @JsonIgnore
    public void setNum(float num) {
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
    public String getUnitName() {
        return UnitName;
    }

    @JsonIgnore
    public void setUnitName(String unitName) {
        UnitName = unitName;
    }
}
