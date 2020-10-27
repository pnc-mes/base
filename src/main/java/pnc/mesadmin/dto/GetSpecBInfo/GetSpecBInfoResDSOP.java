package pnc.mesadmin.dto.GetSpecBInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/13.
 */
public class GetSpecBInfoResDSOP implements Serializable{

    @JsonProperty("FileName")
    private String FileName;

    @JsonProperty("FilePath")
    private String FilePath;

    @JsonIgnore
    public String getFileName() {
        return FileName;
    }

    @JsonIgnore
    public void setFileName(String fileName) {
        FileName = fileName;
    }

    @JsonIgnore
    public String getFilePath() {
        return FilePath;
    }

    @JsonIgnore
    public void setFilePath(String filePath) {
        FilePath = filePath;
    }
}
