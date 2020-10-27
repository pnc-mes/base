package pnc.mesadmin.dto.SaveBatchInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by PNC on 2017/6/10.
 */
public class SaveBatchInfoReqBD00 {
    @JsonProperty("WoSource")
    private String WoSource;
    @JsonProperty("WoRd")
    private int WoRd;
    @JsonProperty("UnitRd")
    private int UnitRd;
    @JsonProperty("MaVerRd")
    private int MaVerRd;
    @JsonProperty("WFVerRd")
    private int WFVerRd;
    @JsonProperty("SpecVerRd")
    private int SpecVerRd;
    @JsonProperty("PrintTRd")
    private int PrintTRd;
    @JsonProperty("PrintRd")
    private int PrintRd;
    @JsonProperty("SplitBCount")
    private int SplitBCount;
    @JsonProperty("BCount")
    private float BCount;
    @JsonProperty("IsABatch")
    private String IsABatch;
    @JsonProperty("BInfo")
    private List<SaveBatchInfoReqBD00BInfo> BInfo;
    @JsonProperty("IsPrint")
    private String IsPrint;
    @JsonProperty("PrintCopy")
    private int PrintCopy;
    @JsonProperty("IsPrintPack")
    private String IsPrintPack;
    @JsonProperty("JStartDate")
    private String JStartDate;
    @JsonProperty("JFinishDate")
    private String JFinishDate;
    @JsonProperty("Remark")
    private String Remark;
    @JsonProperty("LineGd")
    private String LineGd;
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
    public int getUnitRd() {
        return UnitRd;
    }
    @JsonIgnore
    public void setUnitRd(int unitRd) {
        UnitRd = unitRd;
    }
    @JsonIgnore
    public int getMaVerRd() {
        return MaVerRd;
    }
    @JsonIgnore
    public void setMaVerRd(int maVerRd) {
        MaVerRd = maVerRd;
    }
    @JsonIgnore
    public int getWFVerRd() {
        return WFVerRd;
    }
    @JsonIgnore
    public void setWFVerRd(int WFVerRd) {
        this.WFVerRd = WFVerRd;
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
    public int getSplitBCount() {
        return SplitBCount;
    }
    @JsonIgnore
    public void setSplitBCount(int splitBCount) {
        SplitBCount = splitBCount;
    }
    @JsonIgnore
    public float getBCount() {
        return BCount;
    }
    @JsonIgnore
    public void setBCount(float BCount) {
        this.BCount = BCount;
    }
    @JsonIgnore
    public String getIsABatch() {
        return IsABatch;
    }
    @JsonIgnore
    public void setIsABatch(String isABatch) {
        IsABatch = isABatch;
    }
    @JsonIgnore
    public List<SaveBatchInfoReqBD00BInfo> getBInfo() {
        return BInfo;
    }
    @JsonIgnore
    public void setBInfo(List<SaveBatchInfoReqBD00BInfo> BInfo) {
        this.BInfo = BInfo;
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
    public String getIsPrintPack() {
        return IsPrintPack;
    }
    @JsonIgnore
    public void setIsPrintPack(String isPrintPack) {
        IsPrintPack = isPrintPack;
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
    @JsonIgnore
    public String getLineGd() {
        return LineGd;
    }
    @JsonIgnore
    public void setLineGd(String lineGd) {
        LineGd = lineGd;
    }
}
