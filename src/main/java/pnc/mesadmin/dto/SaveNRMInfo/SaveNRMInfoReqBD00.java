package pnc.mesadmin.dto.SaveNRMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by test on 2017/9/21.
 */
public class SaveNRMInfoReqBD00 implements Serializable {

    @JsonProperty("RetCode")
    private String RetCode;
    @JsonProperty("StoreRd")
    private int StoreRd;

    @JsonProperty("RetDlInfo")
    private List<SaveNRMInfoReqBD00RetDlInfo> RetDlInfo;
    @JsonIgnore
    public int getStoreRd() {
        return StoreRd;
    }
    @JsonIgnore
    public void setStoreRd(int storeRd) {
        StoreRd = storeRd;
    }

    @JsonIgnore
    public String getRetCode() {
        return RetCode;
    }
    @JsonIgnore
    public void setRetCode(String retCode) {
        RetCode = retCode;
    }

    @JsonIgnore
    public List<SaveNRMInfoReqBD00RetDlInfo> getRetDlInfo() {
        return RetDlInfo;
    }
    @JsonIgnore
    public void setRetDlInfo(List<SaveNRMInfoReqBD00RetDlInfo> retDlInfo) {
        RetDlInfo = retDlInfo;
    }
}
