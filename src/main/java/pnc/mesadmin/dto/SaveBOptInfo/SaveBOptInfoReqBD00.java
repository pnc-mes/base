package pnc.mesadmin.dto.SaveBOptInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by xfxi on 2017/8/31.
 */
public class SaveBOptInfoReqBD00 {

    @JsonProperty("ReaDes")
    private String ReaDes;

    @JsonProperty("BatchInfo")
    private List<SaveBOptInfoReqBD00BatchInfo> BatchInfo;

    @JsonIgnore
    public String getReaDes() {
        return ReaDes;
    }

    @JsonIgnore
    public void setReaDes(String reaDes) {
        ReaDes = reaDes;
    }

    @JsonIgnore
    public List<SaveBOptInfoReqBD00BatchInfo> getBatchInfo() {
        return BatchInfo;
    }

    @JsonIgnore
    public void setBatchInfo(List<SaveBOptInfoReqBD00BatchInfo> batchInfo) {
        BatchInfo = batchInfo;
    }
}
