package pnc.mesadmin.dto.GetAllIQCBInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2017/6/10.
 */
public class GetAllIQCBInfoResD implements Serializable{

    @JsonProperty("IQCRd")
    private int IQCRd;

    @JsonProperty("Batch")
    private String Batch;

    @JsonIgnore
    public int getIQCRd() {
        return IQCRd;
    }

    @JsonIgnore
    public void setIQCRd(int IQCRd) {
        this.IQCRd = IQCRd;
    }

    @JsonIgnore
    public String getBatch() {
        return Batch;
    }

    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
    }

}
