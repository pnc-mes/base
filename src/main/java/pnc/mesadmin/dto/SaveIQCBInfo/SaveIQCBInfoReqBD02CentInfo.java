package pnc.mesadmin.dto.SaveIQCBInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/6/12.
 */
public class SaveIQCBInfoReqBD02CentInfo {
    @JsonProperty("IQCCentRd")
    private int IQCCentRd;
    @JsonProperty("FileName")
    private String FileName;
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
}
