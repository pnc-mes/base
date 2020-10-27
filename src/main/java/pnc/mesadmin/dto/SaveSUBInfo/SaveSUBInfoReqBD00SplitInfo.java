package pnc.mesadmin.dto.SaveSUBInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/7/10.
 */
public class SaveSUBInfoReqBD00SplitInfo {

    @JsonProperty("SplitBatch")
    private String SplitBatch;

    @JsonProperty("BadFlag")
    private String BadFlag;

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
    public String getBadFlag() {
        return BadFlag;
    }

    @JsonIgnore
    public void setBadFlag(String badFlag) {
        BadFlag = badFlag;
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
