package pnc.mesadmin.dto.GetPdReGpInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/6/6.
 */
public class GetPdReGpInfoResD implements Serializable{

    @JsonProperty("PDFRd")
    private int PDFRd;

    @JsonProperty("PDFName")
    private String PDFName;

    @JsonProperty("PDInfo")
    private List<GetPdReGpInfoResDPDInfo> PDInfo;

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

    @JsonIgnore
    public List<GetPdReGpInfoResDPDInfo> getPDInfo() {
        return PDInfo;
    }

    @JsonIgnore
    public void setPDInfo(List<GetPdReGpInfoResDPDInfo> PDInfo) {
        this.PDInfo = PDInfo;
    }
}
