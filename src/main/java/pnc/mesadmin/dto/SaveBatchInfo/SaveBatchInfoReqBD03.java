package pnc.mesadmin.dto.SaveBatchInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by xfxi on 2017/7/22.
 */
public class SaveBatchInfoReqBD03 {

    @JsonProperty("WoSource")
    private String WoSource;

    @JsonProperty("WoRd")
    private int WoRd;

    @JsonProperty("BInfo")
    private List<SaveBatchInfoReqBD03BInfo> BInfo;

    @JsonProperty("PrintTRd")
    private int PrintTRd;

    @JsonProperty("PrintRd")
    private int PrintRd;

    @JsonProperty("IsPrint")
    private String IsPrint;

    @JsonProperty("PrintCopy")
    private int PrintCopy;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public String getWoSource() {
        return WoSource;
    }

    @JsonIgnore
    public void setWoSource(String woSource) {
        WoSource = woSource;
    }

    @JsonIgnore
    public int getWoRd() {
        return WoRd;
    }

    @JsonIgnore
    public void setWoRd(int woRd) {
        WoRd = woRd;
    }

    @JsonIgnore
    public List<SaveBatchInfoReqBD03BInfo> getBInfo() {
        return BInfo;
    }

    @JsonIgnore
    public void setBInfo(List<SaveBatchInfoReqBD03BInfo> BInfo) {
        this.BInfo = BInfo;
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

    @JsonIgnore
    public int getPrintCopy() {
        return PrintCopy;
    }

    @JsonIgnore
    public void setPrintCopy(int printCopy) {
        PrintCopy = printCopy;
    }

    @JsonIgnore
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
}
