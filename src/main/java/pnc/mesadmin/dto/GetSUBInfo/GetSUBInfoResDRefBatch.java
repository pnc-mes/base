package pnc.mesadmin.dto.GetSUBInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/7/10.
 */
public class GetSUBInfoResDRefBatch implements Serializable{

    @JsonProperty("Batch")
    private String Batch;

    @JsonProperty("Num")
    private float Num;

    @JsonProperty("UnitName")
    private String UnitName;

    @JsonProperty("Splitor")
    private String Splitor;

    @JsonProperty("SplitTime")
    private String SplitTime;

    @JsonIgnore
    public String getBatch() {
        return Batch;
    }

    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
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
    public String getUnitName() {
        return UnitName;
    }

    @JsonIgnore
    public void setUnitName(String unitName) {
        UnitName = unitName;
    }

    @JsonIgnore
    public String getSplitor() {
        return Splitor;
    }

    @JsonIgnore
    public void setSplitor(String splitor) {
        Splitor = splitor;
    }

    @JsonIgnore
    public String getSplitTime() {
        return SplitTime;
    }

    @JsonIgnore
    public void setSplitTime(String splitTime) {
        SplitTime = splitTime;
    }
}
