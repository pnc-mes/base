package pnc.mesadmin.dto.SaveBatchInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/6/10.
 */
public class SaveBatchInfoReqBD02 {
    @JsonProperty("BRd")
    private int BRd;
    @JsonProperty("SpecVerRd")
    private int SpecVerRd;
    @JsonProperty("PrintTRd")
    private int PrintTRd;
    @JsonProperty("PrintRd")
    private int PrintRd;
    @JsonProperty("BCount")
    private int BCount;
    @JsonProperty("PrintCopy")
    private int PrintCopy;
    @JsonProperty("JStartDate")
    private String JStartDate;
    @JsonProperty("JFinishDate")
    private  String  JFinishDate;
    @JsonProperty("Remark")
    private String  Remark;
    @JsonIgnore
    public int getBRd() {
        return BRd;
    }
    @JsonIgnore
    public void setBRd(int BRd) {
        this.BRd = BRd;
    }
    @JsonIgnore
    public int getSpecVerRd() {
        return SpecVerRd;
    }
    @JsonIgnore
    public void setSpecVerRd(int specVerRd) {
        SpecVerRd = specVerRd;
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
    public int getBCount() {
        return BCount;
    }
    @JsonIgnore
    public void setBCount(int BCount) {
        this.BCount = BCount;
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
    public String getJStartDate() {
        return JStartDate;
    }
    @JsonIgnore
    public void setJStartDate(String JStartDate) {
        this.JStartDate = JStartDate;
    }
    @JsonIgnore
    public String getJFinishDate() {
        return JFinishDate;
    }
    @JsonIgnore
    public void setJFinishDate(String JFinishDate) {
        this.JFinishDate = JFinishDate;
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
