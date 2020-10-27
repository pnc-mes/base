package pnc.mesadmin.dto.GetAllMaSDTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by liufuzhi on 2018/3/8.
 */
public class GetAllMaSDTInfoResDBInfo implements Serializable {
    @JsonProperty("Batch")
    private String Batch;

    @JsonProperty("StoreName")
    private String StoreName;

    @JsonProperty("LName")
    private String LName;

    @JsonProperty("Num")
    private Float Num;

    @JsonProperty("CanNum")
    private Float CanNum;

    @JsonProperty("UnitName")
    private String UnitName;

    @JsonProperty("RKOpter")
    private String RKOpter;

    @JsonProperty("RKTime")
    private String RKTime;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public Float getCanNum() {
        return CanNum;
    }

    @JsonIgnore
    public void setCanNum(Float canNum) {
        CanNum = canNum;
    }

    @JsonIgnore
    public Float getNum() {
        return Num;
    }

    @JsonIgnore
    public void setNum(Float num) {
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
    public String getBatch() {
        return Batch;
    }

    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
    }

    @JsonIgnore
    public String getStoreName() {
        return StoreName;
    }

    @JsonIgnore
    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    @JsonIgnore
    public String getLName() {
        return LName;
    }

    @JsonIgnore
    public void setLName(String LName) {
        this.LName = LName;
    }

    @JsonIgnore
    public String getRKOpter() {
        return RKOpter;
    }

    @JsonIgnore
    public void setRKOpter(String RKOpter) {
        this.RKOpter = RKOpter;
    }

    @JsonIgnore
    public String getRKTime() {
        return RKTime;
    }

    @JsonIgnore
    public void setRKTime(String RKTime) {
        this.RKTime = RKTime;
    }

    @JsonIgnore
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
}
