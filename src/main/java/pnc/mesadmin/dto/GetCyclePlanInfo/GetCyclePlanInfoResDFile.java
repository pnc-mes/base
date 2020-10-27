package pnc.mesadmin.dto.GetCyclePlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/7 10:31
 * @Description:
 */
public class GetCyclePlanInfoResDFile implements Serializable {
    @JsonProperty("FileVerRd")
    private int FileVerRd;
    @JsonProperty("FileName")
    private String FileName;
    @JsonIgnore
    public int getFileVerRd() {
        return FileVerRd;
    }
    @JsonIgnore
    public void setFileVerRd(int fileVerRd) {
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
