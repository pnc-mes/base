package pnc.mesadmin.dto.GetAllHPLInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/7/14.
 */
public class GetAllHPLInfoResD {

    @JsonProperty("PTCode")
    private String PTCode;

    @JsonProperty("ReCode")
    private String ReCode;

    @JsonProperty("OrderType")
    private String OrderType;

    @JsonProperty("BarCode")
    private String BarCode;

    @JsonProperty("BarType")
    private String BarType;

    @JsonProperty("Num")
    private Float Num;

    @JsonProperty("MaCode")
    private String MaCode;

    @JsonProperty("MaName")
    private String MaName;

    @JsonProperty("LastPTime")
    private String LastPTime;

    @JsonProperty("PrintCount")
    private int PrintCount;

    @JsonProperty("PrintCopy")
    private int PrintCopy;

    @JsonIgnore
    public String getPTCode() {
        return PTCode;
    }

    @JsonIgnore
    public String getReCode() {
        return ReCode;
    }

    @JsonIgnore
    public void setReCode(String reCode) {
        ReCode = reCode;
    }

    @JsonIgnore
    public String getOrderType() {
        return OrderType;
    }

    @JsonIgnore
    public void setOrderType(String orderType) {
        OrderType = orderType;
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
    public void setPTCode(String PTCode) {
        this.PTCode = PTCode;
    }

    @JsonIgnore
    public String getBarCode() {
        return BarCode;
    }

    @JsonIgnore
    public void setBarCode(String barCode) {
        BarCode = barCode;
    }

    @JsonIgnore
    public String getBarType() {
        return BarType;
    }

    @JsonIgnore
    public void setBarType(String barType) {
        BarType = barType;
    }

    @JsonIgnore
    public int getPrintCount() {
        return PrintCount;
    }

    @JsonIgnore
    public void setPrintCount(int printCount) {
        PrintCount = printCount;
    }

    @JsonIgnore
    public int getPrintCopy() {
        return PrintCopy;
    }

    @JsonIgnore
    public void setPrintCopy(int printCopy) {
        PrintCopy = printCopy;
    }

    @JsonIgnore
    public String getMaCode() {
        return MaCode;
    }

    @JsonIgnore
    public void setMaCode(String maCode) {
        MaCode = maCode;
    }

    @JsonIgnore
    public String getMaName() {
        return MaName;
    }

    @JsonIgnore
    public void setMaName(String maName) {
        MaName = maName;
    }

    @JsonIgnore
    public String getLastPTime() {
        return LastPTime;
    }

    @JsonIgnore
    public void setLastPTime(String lastPTime) {
        LastPTime = lastPTime;
    }
}
