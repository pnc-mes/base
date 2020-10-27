package pnc.mesadmin.dto.SavePackInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/21.
 */
public class SavePackInfoReqBD00BarInfo implements Serializable{

    @JsonProperty("Batch")
    private String Batch;

    @JsonProperty("ScanTime")
    private String ScanTime;

    @JsonIgnore
    public String getBatch() {
        return Batch;
    }

    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
    }

    @JsonIgnore
    public String getScanTime() {
        return ScanTime;
    }

    @JsonIgnore
    public void setScanTime(String scanTime) {
        ScanTime = scanTime;
    }
}
