package pnc.mesadmin.dto.SavePdfInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/5/26.
 */
public class SavePdfInfoReqBD01 implements Serializable{

    @JsonProperty("PdfRd")
    private int PdfRd;

    @JsonIgnore
    public int getPdfRd() {
        return PdfRd;
    }

    @JsonIgnore
    public void setPdfRd(int pdfRd) {
        PdfRd = pdfRd;
    }
}
