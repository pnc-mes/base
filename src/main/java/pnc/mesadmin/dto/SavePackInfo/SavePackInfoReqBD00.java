package pnc.mesadmin.dto.SavePackInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/6/21.
 */
public class SavePackInfoReqBD00 implements Serializable{

    @JsonProperty("WoCode")
    private String WoCode;

    @JsonProperty("MaVerRd")
    private int MaVerRd;

    @JsonProperty("Num")
    private float Num;

    @JsonProperty("PTRd")
    private int PTRd;

    @JsonProperty("IsPrint")
    private String IsPrint;

    @JsonProperty("PrintRd")
    private int PrintRd;

    @JsonProperty("PrintCount")
    private int PrintCount;

    @JsonProperty("PrintCopy")
    private int PrintCopy;

    @JsonProperty("BarInfo")
    private List<SavePackInfoReqBD00BarInfo> BarInfo;

    @JsonIgnore
    public String getWoCode() {
        return WoCode;
    }

    @JsonIgnore
    public void setWoCode(String woCode) {
        WoCode = woCode;
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
    public float getNum() {
        return Num;
    }

    @JsonIgnore
    public void setNum(float num) {
        Num = num;
    }

    @JsonIgnore
    public int getPTRd() {
        return PTRd;
    }

    @JsonIgnore
    public void setPTRd(int PTRd) {
        this.PTRd = PTRd;
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

    @JsonIgnore
    public List<SavePackInfoReqBD00BarInfo> getBarInfo() {
        return BarInfo;
    }

    @JsonIgnore
    public void setBarInfo(List<SavePackInfoReqBD00BarInfo> barInfo) {
        BarInfo = barInfo;
    }
}
