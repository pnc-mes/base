package pnc.mesadmin.dto.GetHoldBatchDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Description: mesadmin
 * Created By LENOVO on 2019/5/10
 */
public class GetHoldBatchReq {
    @JsonProperty("Batch")
    private String Batch;
    @JsonProperty("LineRd")
    private Integer LineRd;
    @JsonProperty("StartDate")
    private String StartDate;
    @JsonProperty("EndDate")
    private String EndDate;

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
    public String getStartDate() {
        return StartDate;
    }

    @JsonIgnore
    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    @JsonIgnore
    public String getEndDate() {
        return EndDate;
    }

    @JsonIgnore
    public void setEndDate(String endDate) {
        EndDate = endDate;
    }
}
