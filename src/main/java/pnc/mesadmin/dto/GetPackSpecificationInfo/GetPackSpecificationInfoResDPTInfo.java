package pnc.mesadmin.dto.GetPackSpecificationInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/7.
 */
public class GetPackSpecificationInfoResDPTInfo implements Serializable{

    @JsonProperty("PTRd")
    private int PTRd;

    @JsonProperty("TempName")
    private String TempName;

    @JsonProperty("FileName")
    private String FileName;

    @JsonIgnore
    public int getPTRd() {
        return PTRd;
    }
    @JsonIgnore
    public void setPTRd(int PTRd) {
        this.PTRd = PTRd;
    }
    @JsonIgnore
    public String getTempName() {
        return TempName;
    }
    @JsonIgnore
    public void setTempName(String tempName) {
        TempName = tempName;
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
