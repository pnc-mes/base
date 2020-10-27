package pnc.mesadmin.dto.SavePdfInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/5/26.
 */
public class SavePdfInfoReqBD02 implements Serializable{

    @JsonProperty("PdfRd")
    private int PdfRd;

    @JsonProperty("PdfName")
    private String PdfName;

    @JsonProperty("SRRd")
    private int SRRd;

    @JsonProperty("PtRd")
    private int PtRd;

    @JsonProperty("Remark")
    private String Remark;

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
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
    @JsonIgnore
    public int getPtRd() {
        return PtRd;
    }
    @JsonIgnore
    public void setPtRd(int ptRd) {
        PtRd = ptRd;
    }
}
