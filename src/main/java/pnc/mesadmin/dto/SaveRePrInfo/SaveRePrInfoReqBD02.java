package pnc.mesadmin.dto.SaveRePrInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by xfxi on 2018/8/28.
 */
public class SaveRePrInfoReqBD02 {

    @JsonProperty("RePrInfo")
    private List<SaveRePrInfoReqBD02RePrInfo> RePrInfo;

    @JsonProperty("PrintRd")
    private int PrintRd;

    @JsonProperty("PrintCount")
    private int PrintCount;

    @JsonProperty("PrintCopy")
    private int PrintCopy;

    @JsonProperty("PrintTRd")
    private int PrintTRd;

    @JsonIgnore
    public List<SaveRePrInfoReqBD02RePrInfo> getRePrInfo() {
        return RePrInfo;
    }

    @JsonIgnore
    public void setRePrInfo(List<SaveRePrInfoReqBD02RePrInfo> rePrInfo) {
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
