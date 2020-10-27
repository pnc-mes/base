package pnc.mesadmin.dto.SaveSUBInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by xfxi on 2017/7/10.
 */
public class SaveSUBInfoReqBD00 {

    @JsonProperty("Batch")
    private String Batch;

    @JsonProperty("SplitInfo")
    private List<SaveSUBInfoReqBD00SplitInfo> SplitInfo;

    @JsonProperty("PrintTRd")
    private int PrintTRd;

    @JsonProperty("PrintRd")
    private int PrintRd;

    @JsonProperty("IsPrint")
    private String IsPrint;

    @JsonIgnore
    public String getBatch() {
        return Batch;
    }

    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
    }

    @JsonIgnore
    public List<SaveSUBInfoReqBD00SplitInfo> getSplitInfo() {
        return SplitInfo;
    }

    @JsonIgnore
    public void setSplitInfo(List<SaveSUBInfoReqBD00SplitInfo> splitInfo) {
        SplitInfo = splitInfo;
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
