package pnc.mesadmin.dto.newmove.GetBarCodeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetBarCodeInfoReqBD00 {
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
