package pnc.mesadmin.dto.SaveSpecInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：编辑工序管理
 * 创建人：张亮亮
 * 创建时间：2017-06-03
 * 修改人：
 * 修改时间：
 */
public class SaveSpecInfoReqBD02 implements Serializable{

    @JsonProperty("SpecRd")
    private int SpecRd;

    @JsonProperty("SpecVerRd")
    private int SpecVerRd;

    @JsonProperty("SpecName")
    private String SpecName;

    @JsonProperty("Version")
    private String Version;

    @JsonProperty("IsDef")
    private String IsDef;

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("OptRd")
    private int OptRd;

    @JsonProperty("DevGpRd")
    private int DevGpRd;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("DCVerRd")
    private int DCVerRd;

    @JsonProperty("AfDCVerRd")
    private int AfDCVerRd;

    @JsonProperty("SkillGRd")
    private int SkillGRd;

    @JsonProperty("FileGRd")
    private int FileGRd;
    /*@JsonProperty("PrintTRd")
    private int PrintTRd;

    @JsonProperty("PrintRd")
    private int PrintRd;*/
    @JsonIgnore
    public int getFileGRd() {
        return FileGRd;
    }

    @JsonIgnore
    public void setFileGRd(int fileGRd) {
        FileGRd = fileGRd;
    }

    @JsonIgnore
    public int getDCVerRd() {
        return DCVerRd;
    }

    @JsonIgnore
    public void setDCVerRd(int DCVerRd) {
        this.DCVerRd = DCVerRd;
    }

    @JsonIgnore
    public int getSkillGRd() {
        return SkillGRd;
    }

    @JsonIgnore
    public void setSkillGRd(int skillGRd) {
        SkillGRd = skillGRd;
    }
    @JsonIgnore
    public int getSpecRd() {
        return SpecRd;
    }

    @JsonIgnore
    public void setSpecRd(int specRd) {
        SpecRd = specRd;
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
    public String getSpecName() {
        return SpecName;
    }

    @JsonIgnore
    public void setSpecName(String specName) {
        SpecName = specName;
    }

    @JsonIgnore
    public String getVersion() {
        return Version;
    }

    @JsonIgnore
    public void setVersion(String version) {
        Version = version;
    }

    @JsonIgnore
    public String getIsDef() {
        return IsDef;
    }

    @JsonIgnore
    public void setIsDef(String isDef) {
        IsDef = isDef;
    }

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public int getOptRd() {
        return OptRd;
    }

    @JsonIgnore
    public void setOptRd(int optRd) {
        OptRd = optRd;
    }

    @JsonIgnore
    public int getDevGpRd() {
        return DevGpRd;
    }

    @JsonIgnore
    public void setDevGpRd(int devGpRd) {
        DevGpRd = devGpRd;
    }

    @JsonIgnore
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }

    public int getAfDCVerRd() {
        return AfDCVerRd;
    }

    public void setAfDCVerRd(int afDCVerRd) {
        AfDCVerRd = afDCVerRd;
    }

    /*@JsonIgnore
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
    }*/
}
