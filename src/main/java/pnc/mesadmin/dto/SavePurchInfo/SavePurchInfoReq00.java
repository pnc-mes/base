package pnc.mesadmin.dto.SavePurchInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by PNC on 2017/8/23.
 */
public class SavePurchInfoReq00 {
    @JsonProperty("PurChCode")
    private String PurChCode;
    @JsonProperty("Remark")
    private String Remark;
    @JsonProperty("Buyer")
    private String Buyer;
    @JsonProperty("SupCode")
    private String SupCode;
    @JsonProperty("SupName")
    private String SupName;
    @JsonProperty("ArrivalTime")
    private String ArrivalTime;
    @JsonProperty("PurMaInfo")
    private List<SavePurchInfoReq00PurMa> PurMaInfo;
    @JsonIgnore
    public String getPurChCode() {
        return PurChCode;
    }
    @JsonIgnore
    public void setPurChCode(String purChCode) {
        PurChCode = purChCode;
    }
    @JsonIgnore
    public String getBuyer() {
        return Buyer;
    }
    @JsonIgnore
    public void setBuyer(String buyer) {
        Buyer = buyer;
    }
    @JsonIgnore
    public String getArrivalTime() {
        return ArrivalTime;
    }
    @JsonIgnore
    public void setArrivalTime(String arrivalTime) {
        ArrivalTime = arrivalTime;
    }
    @JsonIgnore
    public List<SavePurchInfoReq00PurMa> getPurMaInfo() {
        return PurMaInfo;
    }
    @JsonIgnore
    public void setPurMaInfo(List<SavePurchInfoReq00PurMa> purMaInfo) {
        PurMaInfo = purMaInfo;
    }
    @JsonIgnore
    public String getSupCode() {
        return SupCode;
    }
    @JsonIgnore
    public void setSupCode(String supCode) {
        SupCode = supCode;
    }
    @JsonIgnore
    public String getSupName() {
        return SupName;
    }
    @JsonIgnore
    public void setSupName(String supName) {
        SupName = supName;
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
