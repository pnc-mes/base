package pnc.mesadmin.dto.GetFrePlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by haozan on 2018/9/7.
 */
public class FileInfo {
    @JsonProperty("FileVerRd")
    private Integer FileVerRd;
    @JsonProperty("FileName")
    private String FileName;

    @JsonIgnore
    public Integer getFileVerRd() {
        return FileVerRd;
    }
    @JsonIgnore
    public void setFileVerRd(Integer fileVerRd) {
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
