package pnc.mesadmin.dto.SaveRePrInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by xfxi on 2017/7/14.
 */
public class SaveRePrInfoReqBD00 {

    @JsonProperty("RePrInfo")
    private List<SaveRePrInfoReqBD00RePrInfo> RePrInfo;

    @JsonProperty("PrintRd")
    private int PrintRd;

    @JsonProperty("PrintCount")
    private int PrintCount;

    @JsonProperty("PrintCopy")
    private int PrintCopy;

    @JsonProperty("PrintTRd")
    private int PrintTRd;

    @JsonIgnore
    public List<SaveRePrInfoReqBD00RePrInfo> getRePrInfo() {
        return RePrInfo;
    }

    @JsonIgnore
    public void setRePrInfo(List<SaveRePrInfoReqBD00RePrInfo> rePrInfo) {
        RePrInfo = rePrInfo;
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
    public int getPrintRd() {
        return PrintRd;
    }

    @JsonIgnore
    public void setPrintRd(int printRd) {
        PrintRd = printRd;
    }

    @JsonIgnore
    public int getPrintTRd() {
        return PrintTRd;
    }

    @JsonIgnore
    public void setPrintTRd(int printTRd) {
        PrintTRd = printTRd;
    }
}
