package pnc.mesadmin.dto.SaveIOSInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2018/11/27.
 */
public class SaveIOSInfoReqBD07 implements Serializable{

    @JsonProperty("Batch")
    private String Batch;

    @JsonProperty("LineRd")
    private Integer LineRd;

    @JsonProperty("Direction")
    private String Direction;

    @JsonProperty("SpecVerRd")
    private Integer SpecVerRd;

    @JsonProperty("ActionType")
    private String ActionType;

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
    public Integer getLineRd() {
        return LineRd;
    }

    @JsonIgnore
    public void setLineRd(Integer lineRd) {
        LineRd = lineRd;
    }

    @JsonIgnore
    public String getDirection() {
        return Direction;
    }

    @JsonIgnore
    public void setDirection(String direction) {
        Direction = direction;
    }

    @JsonIgnore
    public Integer getSpecVerRd() {
        return SpecVerRd;
    }

    @JsonIgnore
    public void setSpecVerRd(Integer specVerRd) {
        SpecVerRd = specVerRd;
    }

    @JsonIgnore
    public String getActionType() {
        return ActionType;
    }

    @JsonIgnore
    public void setActionType(String actionType) {
        ActionType = actionType;
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
