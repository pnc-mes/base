package pnc.mesadmin.dto.GetCMWInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/10/18.
 */
public class GetCMWInfoResDBInfo {

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
