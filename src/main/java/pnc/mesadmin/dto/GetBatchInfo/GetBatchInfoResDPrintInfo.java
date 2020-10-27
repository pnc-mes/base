package pnc.mesadmin.dto.GetBatchInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/6/9.
 */
public class GetBatchInfoResDPrintInfo {

    @JsonProperty("PrintRd")
    private int PrintRd;
    @JsonProperty("PrintName")
    private String PrintName;

    @JsonIgnore
    public int getPrintRd() {
        return PrintRd;
    }
    @JsonIgnore
    public void setPrintRd(int printRd) {
        PrintRd = printRd;
    }
    @JsonIgnore
    public String getPrintName() {
        return PrintName;
    }
    @JsonIgnore
    public void setPrintName(String printName) {
        PrintName = printName;
    }




}
