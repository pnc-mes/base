package pnc.mesadmin.dto.GetPdfInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xfxi on 2017/5/26.
 */
public class GetPdfInfoResD implements Serializable{

    @JsonProperty("PdfRd")
    private int PdfRd;

    @JsonProperty("PdfName")
    private String PdfName;

    @JsonProperty("SRRd")
    private int SRRd;

    @JsonProperty("SRName")
    private String SRName;

    @JsonProperty("PtRd")
    private int PtRd;

    @JsonProperty("PtName")
    private String PtName;

    @JsonProperty("Creator")
    private String Creator;

    @JsonProperty("CreateTime")
    private String CreateTime;

    @JsonProperty("LastModifyMan")
    private String LastModifyMan;

    @JsonProperty("LastModifyTime")
    private String LastModifyTime;

    @JsonProperty("Remark")
    private String Remark;
    @JsonIgnore
    public int getPtRd() {
        return PtRd;
    }
    @JsonIgnore
    public void setPtRd(int ptRd) {
        PtRd = ptRd;
    }
    @JsonIgnore
    public String getPtName() {
        return PtName;
    }
    @JsonIgnore
    public void setPtName(String ptName) {
        PtName = ptName;
    }

    @JsonIgnore
    public int getPdfRd() {
        return PdfRd;
    }

    @JsonIgnore
    public void setPdfRd(int pdfRd) {
        PdfRd = pdfRd;
    }

    @JsonIgnore
    public String getPdfName() {
        return PdfName;
    }

    @JsonIgnore
    public void setPdfName(String pdfName) {
        PdfName = pdfName;
    }

    @JsonIgnore
    public int getSRRd() {
        return SRRd;
    }

    @JsonIgnore
    public void setSRRd(int SRRd) {
        this.SRRd = SRRd;
    }

    @JsonIgnore
    public String getSRName() {
        return SRName;
    }

    @JsonIgnore
    public void setSRName(String SRName) {
        this.SRName = SRName;
    }

    @JsonIgnore
    public String getCreator() {
        return Creator;
    }

    @JsonIgnore
    public void setCreator(String creator) {
        Creator = creator;
    }

    @JsonIgnore
    public String getCreateTime() {
        return CreateTime;
    }

    @JsonIgnore
    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    @JsonIgnore
    public String getLastModifyMan() {
        return LastModifyMan;
    }

    @JsonIgnore
    public void setLastModifyMan(String lastModifyMan) {
        LastModifyMan = lastModifyMan;
    }

    @JsonIgnore
    public String getLastModifyTime() {
        return LastModifyTime;
    }

    @JsonIgnore
    public void setLastModifyTime(String lastModifyTime) {
        LastModifyTime = lastModifyTime;
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
