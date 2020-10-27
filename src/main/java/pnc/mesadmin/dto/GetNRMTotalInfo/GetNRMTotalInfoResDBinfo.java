package pnc.mesadmin.dto.GetNRMTotalInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by zhaochao on 2017/10/25.
 */
public class GetNRMTotalInfoResDBinfo implements Serializable{
    @JsonProperty("Batch")
    private String Batch;
    @JsonProperty("InSStatus")
    private String InSStatus;
    @JsonProperty("Num")
    private Float Num;
    @JsonProperty("StoreInfo")
    private GetNRMTotalInfoResDBStoreInfo StoreInfo;
    @JsonProperty("LInfo")
    private GetNRMTotalInfoResDBLInfo LInfo;
    @JsonProperty("UnitName")
    private String UnitName;

    @JsonIgnore
    public String getBatch() {
        return Batch;
    }
    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
    }
    @JsonIgnore
    public String getInSStatus() {
        return InSStatus;
    }
    @JsonIgnore
    public void setInSStatus(String inSStatus) {
        InSStatus = inSStatus;
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
    public GetNRMTotalInfoResDBStoreInfo getStoreInfo() {
        return StoreInfo;
    }
    @JsonIgnore
    public void setStoreInfo(GetNRMTotalInfoResDBStoreInfo storeInfo) {
        StoreInfo = storeInfo;
    }
    @JsonIgnore
    public GetNRMTotalInfoResDBLInfo getLInfo() {
        return LInfo;
    }
    @JsonIgnore
    public void setLInfo(GetNRMTotalInfoResDBLInfo LInfo) {
        this.LInfo = LInfo;
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
