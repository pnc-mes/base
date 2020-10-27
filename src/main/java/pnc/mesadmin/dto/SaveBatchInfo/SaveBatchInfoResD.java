package pnc.mesadmin.dto.SaveBatchInfo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by PNC on 2017/6/9.
 */
public class SaveBatchInfoResD {
    @JsonProperty("BInfo")
    private List<SaveBatchInfoResDBInfo> BInfo;

    @JsonIgnore
    public List<SaveBatchInfoResDBInfo> getBInfo() {
        return BInfo;
    }

    @JsonIgnore
    public void setBInfo(List<SaveBatchInfoResDBInfo> BInfo) {
        this.BInfo = BInfo;
    }
}
