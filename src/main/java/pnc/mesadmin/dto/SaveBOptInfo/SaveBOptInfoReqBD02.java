package pnc.mesadmin.dto.SaveBOptInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/8/31.
 */
public class SaveBOptInfoReqBD02 {

    @JsonProperty("Batch")
    private String Batch;

    @JsonProperty("BeNum")
    private float BeNum;

    @JsonProperty("AfNum")
    private float AfNum;

    @JsonProperty("ReaDes")
    private String ReaDes;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public String getBatch() {
        return Batch;
    }

    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
    }

    @JsonIgnore
    public float getBeNum() {
        return BeNum;
    }

    @JsonIgnore
    public void setBeNum(float beNum) {
        BeNum = beNum;
    }

    @JsonIgnore
    public float getAfNum() {
        return AfNum;
    }

    @JsonIgnore
    public void setAfNum(float afNum) {
        AfNum = afNum;
    }

    @JsonIgnore
    public String getReaDes() {
        return ReaDes;
    }

    @JsonIgnore
    public void setReaDes(String reaDes) {
        ReaDes = reaDes;
    }

    @JsonIgnore
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
}
