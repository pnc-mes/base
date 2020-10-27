package pnc.mesadmin.dto.SaveBOptInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by xfxi on 2017/8/31.
 */
public class SaveBOptInfoReqBD04 {

    @JsonProperty("ReaDes")
    private String ReaDes;

    @JsonProperty("BatchInfo")
    private List<SaveBOptInfoReqBD04BatchInfo> BatchInfo;

    @JsonIgnore
    public String getReaDes() {
        return ReaDes;
    }

    @JsonIgnore
    public void setReaDes(String reaDes) {
        ReaDes = reaDes;
    }

    @JsonIgnore
    public List<SaveBOptInfoReqBD04BatchInfo> getBatchInfo() {
        return BatchInfo;
    }

    @JsonIgnore
    public void setBatchInfo(List<SaveBOptInfoReqBD04BatchInfo> batchInfo) {
        BatchInfo = batchInfo;
    }
}
