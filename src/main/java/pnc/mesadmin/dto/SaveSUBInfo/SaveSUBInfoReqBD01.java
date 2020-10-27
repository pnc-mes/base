package pnc.mesadmin.dto.SaveSUBInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by xfxi on 2017/9/22.
 */
public class SaveSUBInfoReqBD01 {

    @JsonProperty("BatchInfo")
    private List<SaveSUBInfoReqBD01BatchInfo> BatchInfo;

    @JsonProperty("PrintTRd")
    private int PrintTRd;

    @JsonProperty("PrintRd")
    private int PrintRd;

    @JsonProperty("IsPrint")
    private String IsPrint;

    @JsonIgnore
    public List<SaveSUBInfoReqBD01BatchInfo> getBatchInfo() {
        return BatchInfo;
    }

    @JsonIgnore
    public void setBatchInfo(List<SaveSUBInfoReqBD01BatchInfo> batchInfo) {
        BatchInfo = batchInfo;
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
    public String getIsPrint() {
        return IsPrint;
    }

    @JsonIgnore
    public void setIsPrint(String isPrint) {
        IsPrint = isPrint;
    }
}
