package pnc.mesadmin.dto.GetMWFInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by liufuzhi on 2017/7/25.
 */
public class GetMWFInfoResDFileGrInfo implements Serializable {
    @JsonProperty("FileGrRd")
    private int FileGrRd;

    @JsonProperty("FileGrName")
    private String FileGrName;

    @JsonIgnore
    public int getFileGrRd() {
        return FileGrRd;
    }

    @JsonIgnore
    public void setFileGrRd(int fileGrRd) {
        FileGrRd = fileGrRd;
    }

    @JsonIgnore
    public String getFileGrName() {
        return FileGrName;
    }

    @JsonIgnore
    public void setFileGrName(String fileGrName) {
        FileGrName = fileGrName;
    }
}
