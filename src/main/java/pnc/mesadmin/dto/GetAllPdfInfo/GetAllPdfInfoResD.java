package pnc.mesadmin.dto.GetAllPdfInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/5/26.
 */
public class GetAllPdfInfoResD implements Serializable{

    @JsonProperty("PdfRd")
    private int PdfRd;

    @JsonProperty("PdfName")
    private String PdfName;

    private List<GetAllPdfInfoResD> children;

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

    public List<GetAllPdfInfoResD> getChildren() {
        return children;
    }

    public void setChildren(List<GetAllPdfInfoResD> children) {
        this.children = children;
    }
}
