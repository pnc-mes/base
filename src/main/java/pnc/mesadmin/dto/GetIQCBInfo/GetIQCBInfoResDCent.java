package pnc.mesadmin.dto.GetIQCBInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/6/10.
 */
public class GetIQCBInfoResDCent {
    @JsonProperty("IQCCentRd")
    private int IQCCentRd;
    @JsonProperty("FileName")
    private String FileName;
    @JsonProperty("LastUpDate")
    private String LastUpDate;

    @JsonIgnore
    public int getIQCCentRd() {
        return IQCCentRd;
    }
    @JsonIgnore
    public void setIQCCentRd(int IQCCentRd) {
        this.IQCCentRd = IQCCentRd;
    }
    @JsonIgnore
    public String getFileName() {
        return FileName;
    }
    @JsonIgnore
    public void setFileName(String fileName) {
        FileName = fileName;
    }
    @JsonIgnore
    public String getLastUpDate() {
        return LastUpDate;
    }
    @JsonIgnore
    public void setLastUpDate(String lastUpDate) {
        LastUpDate = lastUpDate;
    }

}
