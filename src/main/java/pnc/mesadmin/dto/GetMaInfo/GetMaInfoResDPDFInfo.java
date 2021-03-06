package pnc.mesadmin.dto.GetMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/1.
 */
public class GetMaInfoResDPDFInfo implements Serializable{

    @JsonProperty("PDFRd")
    private int PDFRd;

    @JsonProperty("PDFName")
    private String PDFName;

    @JsonIgnore
    public int getPDFRd() {
        return PDFRd;
    }

    @JsonIgnore
    public void setPDFRd(int PDFRd) {
        this.PDFRd = PDFRd;
    }

    @JsonIgnore
    public String getPDFName() {
        return PDFName;
    }

    @JsonIgnore
    public void setPDFName(String PDFName) {
        this.PDFName = PDFName;
    }
}
