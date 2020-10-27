package pnc.mesadmin.dto.SaveStartInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SaveStartInfoReq00 {
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
