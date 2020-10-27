package pnc.mesadmin.dto.GetAllPurchInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/8/23.
 */
public class GetAllPurchInfoResD {
    @JsonProperty("PurChRd")
    private int PurChRd;
    @JsonProperty("PurChCode")
    private String PurChCode;
    @JsonProperty("Buyer")
    private String Buyer;
    @JsonProperty("SupCode")
    private String SupCode;
    @JsonProperty("SupName")
    private String SupName;
    @JsonProperty("ArrivalTime")
    private String ArrivalTime;
    @JsonProperty("Status")
    private String Status;
    @JsonProperty("CreateTime")
    private String CreateTime;
    @JsonProperty("Remark")
    private String Remark;
    @JsonIgnore
    public String getCreateTime() {
        return CreateTime;
    }
    @JsonIgnore
    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }
    @JsonIgnore
    public int getPurChRd() {
        return PurChRd;
    }
    @JsonIgnore
    public void setPurChRd(int purChRd) {
        PurChRd = purChRd;
    }
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
    public String getArrivalTime() {
        return ArrivalTime;
    }
    @JsonIgnore
    public void setArrivalTime(String arrivalTime) {
        ArrivalTime = arrivalTime;
    }
    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
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
