package pnc.mesadmin.dto.SaveIOSInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/14.
 */
public class SaveIOSInfoReqBD01DDoBaInfo implements Serializable{

    @JsonProperty("DoBatch")
    private String DoBatch;

    @JsonIgnore
    public String getDoBatch() {
        return DoBatch;
    }

    @JsonIgnore
    public void setDoBatch(String doBatch) {
        DoBatch = doBatch;
    }
}
