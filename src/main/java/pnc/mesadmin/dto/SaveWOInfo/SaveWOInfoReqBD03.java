package pnc.mesadmin.dto.SaveWOInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/10/22.
 */
public class SaveWOInfoReqBD03 {

    @JsonProperty("WoRd")
    private int WoRd;

    @JsonProperty("IsPrint")
    private String IsPrint;

    @JsonProperty("PrintTRd")
    private int PrintTRd;

    @JsonProperty("PrintRd")
    private int PrintRd;

    @JsonProperty("PrintCount")
    private int PrintCount;

    @JsonProperty("PrintCopy")
    private int PrintCopy;

    @JsonIgnore
    public int getWoRd() {
        return WoRd;
    }

    @JsonIgnore
    public void setWoRd(int woRd) {
        WoRd = woRd;
    }

    @JsonIgnore
    public String getIsPrint() {
        return IsPrint;
    }

    @JsonIgnore
    public void setIsPrint(String isPrint) {
        IsPrint = isPrint;
    }

    @JsonIgnore
    public int getPrintTRd() {
        return PrintTRd;
    }

    @JsonIgnore
    public void setPrintTRd(int printTRd) {
        PrintTRd = printTRd;
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
}
