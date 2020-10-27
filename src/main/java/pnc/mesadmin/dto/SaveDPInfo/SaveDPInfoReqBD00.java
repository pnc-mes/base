package pnc.mesadmin.dto.SaveDPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * Created by panjunfeng on 2017/11/3.
 */
public class SaveDPInfoReqBD00 {

    @JsonProperty("DPType")
    private String DPType;

    @JsonProperty("MPRd")
    private Integer MPRd;

    @JsonProperty("BarInfo")
    private List<SaveDPInfoReqBD00BarInfo> BarInfo;

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

    @JsonProperty("IsRework")
    private String IsRework;

    @JsonIgnore
    public String getDPType() {
        return DPType;
    }

    @JsonIgnore
    public void setDPType(String DPType) {
        this.DPType = DPType;
    }

    @JsonIgnore
    public Integer getMPRd() {
        return MPRd;
    }

    @JsonIgnore
    public void setMPRd(Integer MPRd) {
        this.MPRd = MPRd;
    }

    @JsonIgnore
    public List<SaveDPInfoReqBD00BarInfo> getBarInfo() {
        return BarInfo;
    }

    @JsonIgnore
    public void setBarInfo(List<SaveDPInfoReqBD00BarInfo> barInfo) {
        BarInfo = barInfo;
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

    @JsonIgnore
    public String getIsRework() {
        return IsRework;
    }

    @JsonIgnore
    public void setIsRework(String isRework) {
        IsRework = isRework;
    }
}
