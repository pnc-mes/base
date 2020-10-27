package pnc.mesadmin.dto.GetSVInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by liufuzhi on 2017/9/19.
 */
public class GetSVInfoResDFileGrInfo implements Serializable {
    @JsonProperty("FileGRd")
    private int FileGRd;

    @JsonProperty("FileGpName")
    private String FileGpName;

    @JsonIgnore
    public int getFileGRd() {
        return FileGRd;
    }

    @JsonIgnore
    public void setFileGRd(int fileGRd) {
        FileGRd = fileGRd;
    }

    @JsonIgnore
    public String getFileGpName() {
        return FileGpName;
    }

    @JsonIgnore
    public void setFileGpName(String fileGpName) {
        FileGpName = fileGpName;
    }
}
