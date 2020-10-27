package pnc.mesadmin.dto.GetCheckPlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class GetCheckPlanInfoResFileInfo implements Serializable {


    @JsonProperty("FileVerRd")
    private String FileVerRd;
    @JsonProperty("FileName")
    private String FileName;
    @JsonIgnore
    public String getFileVerRd() {
        return FileVerRd;
    }
    @JsonIgnore
    public void setFileVerRd(String fileVerRd) {
        FileVerRd = fileVerRd;
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
