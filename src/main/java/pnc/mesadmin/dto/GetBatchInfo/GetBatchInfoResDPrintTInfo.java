package pnc.mesadmin.dto.GetBatchInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/6/9.
 */
public class GetBatchInfoResDPrintTInfo {
    @JsonProperty("PrintTRd")
    private int PrintTRd;
    @JsonProperty("PrintTName")
    private String PrintTName;

    @JsonIgnore
    public int getPrintTRd() {
        return PrintTRd;
    }
    @JsonIgnore
    public void setPrintTRd(int printTRd) {
        PrintTRd = printTRd;
    }
    @JsonIgnore
    public String getPrintTName() {
        return PrintTName;
    }
    @JsonIgnore
    public void setPrintTName(String printTName) {
        PrintTName = printTName;
    }


}
