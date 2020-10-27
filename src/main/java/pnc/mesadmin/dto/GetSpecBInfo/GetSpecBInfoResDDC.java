package pnc.mesadmin.dto.GetSpecBInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/13.
 */
public class GetSpecBInfoResDDC implements Serializable{

    @JsonProperty("DCItemRd")
    private int DCItemRd;

    @JsonProperty("ItemName")
    private String ItemName;

    @JsonProperty("ReferVal")
    private String ReferVal;

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
    public String getReferVal() {
        return ReferVal;
    }

    @JsonIgnore
    public void setReferVal(String referVal) {
        ReferVal = referVal;
    }
}
