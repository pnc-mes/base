package pnc.mesadmin.dto.GetAllBcirExcp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2018-12-12
 **/
public class GetAllBcirExcpReq {
    @JsonProperty("Batch")
    private String Batch;
    @JsonProperty("CreateTimeStart")
    private String CreateTimeStart;
    @JsonProperty("CreateTimeEnd")
    private String CreateTimeEnd;
    @JsonProperty("Status")
    private String Status;

    @JsonIgnore
    public String getBatch() {
        return Batch;
    }

    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
    }

    @JsonIgnore
    public String getCreateTimeStart() {
        return CreateTimeStart;
    }

    @JsonIgnore
    public void setCreateTimeStart(String createTimeStart) {
        CreateTimeStart = createTimeStart;
    }

    @JsonIgnore
    public String getCreateTimeEnd() {
        return CreateTimeEnd;
    }

    @JsonIgnore
    public void setCreateTimeEnd(String createTimeEnd) {
        CreateTimeEnd = createTimeEnd;
    }

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
}
