package pnc.mesadmin.dto.SaveBOptInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/8/31.
 */
public class SaveBOptInfoReqBD04BatchInfo {

    @JsonProperty("Batch")
    private String Batch;

    @JsonIgnore
    public String getBatch() {
        return Batch;
    }

    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
    }
}
