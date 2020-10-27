package pnc.mesadmin.dto.SaveIOSInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SaveIOSInfoReqBD06DoDCInfo {

    @JsonProperty("DCItemRd")
    private int DCItemRd;

    @JsonProperty("ItemName")
    private String ItemName;

    @JsonProperty("CValue")
    private String CValue;

    @JsonIgnore
    public int getDCItemRd() {
        return DCItemRd;
    }

    @JsonIgnore
    public void setDCItemRd(int DCItemRd) {
        this.DCItemRd = DCItemRd;
    }

    @JsonIgnore
    public String getItemName() {
        return ItemName;
    }

    @JsonIgnore
    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    @JsonIgnore
    public String getCValue() {
        return CValue;
    }

    @JsonIgnore
    public void setCValue(String CValue) {
        this.CValue = CValue;
    }
}
