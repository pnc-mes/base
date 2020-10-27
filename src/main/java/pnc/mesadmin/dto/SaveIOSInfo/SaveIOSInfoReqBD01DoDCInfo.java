package pnc.mesadmin.dto.SaveIOSInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
/**
 * Created by xfxi on 2017/6/14.
 */
public class SaveIOSInfoReqBD01DoDCInfo implements Serializable{

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
