package pnc.mesadmin.dto.SavePurchInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by PNC on 2017/9/6.
 */
public class SavePurchInfoReq02 {
    @JsonProperty("Remark")
    private String Remark;
    @JsonProperty("PurChRd")
    private int PurChRd;
    @JsonProperty("Buyer")
    private String Buyer;
    @JsonProperty("SupCode")
    private String SupCode;
    @JsonProperty("SupName")
    private String SupName;
    @JsonProperty("ArrivalTime")
    private String ArrivalTime;
    @JsonProperty("PurMaInfo")
    private List<SavePurchInfoReq02PurMa> PurMaInfo;
    @JsonIgnore
    public int getPurChRd() {
        return PurChRd;
    }
    @JsonIgnore
    public void setPurChRd(int purChRd) {
        PurChRd = purChRd;
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
    public List<SavePurchInfoReq02PurMa> getPurMaInfo() {
        return PurMaInfo;
    }
    @JsonIgnore
    public void setPurMaInfo(List<SavePurchInfoReq02PurMa> purMaInfo) {
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
