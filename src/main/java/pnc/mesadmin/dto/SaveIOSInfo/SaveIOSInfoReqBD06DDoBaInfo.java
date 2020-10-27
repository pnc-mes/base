package pnc.mesadmin.dto.SaveIOSInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SaveIOSInfoReqBD06DDoBaInfo {

    @JsonProperty("DoBatch")
    private String DoBatch;

    @JsonProperty("Num")
    private float Num;

    @JsonIgnore
    public String getDoBatch() {
        return DoBatch;
    }

    @JsonIgnore
    public void setDoBatch(String doBatch) {
        DoBatch = doBatch;
    }

    @JsonIgnore
    public float getNum() {
        return Num;
    }

    @JsonIgnore
    public void setNum(float num) {
        Num = num;
    }
}
