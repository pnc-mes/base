package pnc.mesadmin.dto.SaveSUBInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/9/22.
 */
public class SaveSUBInfoReqBD01SplitInfo {

    @JsonProperty("SplitBatch")
    private String SplitBatch;

    @JsonProperty("Num")
    private float Num;

    @JsonIgnore
    public String getSplitBatch() {
        return SplitBatch;
    }

    @JsonIgnore
    public void setSplitBatch(String splitBatch) {
        SplitBatch = splitBatch;
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
