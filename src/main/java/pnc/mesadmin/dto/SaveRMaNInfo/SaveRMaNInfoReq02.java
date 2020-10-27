package pnc.mesadmin.dto.SaveRMaNInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by PNC on 2017/9/26.
 */
public class SaveRMaNInfoReq02 {
    @JsonProperty("RMaNRd")
    private int  RMaNRd;
    @JsonProperty("RMaNDlInfo")
    private List<SaveRMaNInfoReq02RMaNDl> RMaNDlInfo;

    @JsonIgnore
    public int getRMaNRd() {
        return RMaNRd;
    }
    @JsonIgnore
    public void setRMaNRd(int RMaNRd) {
        this.RMaNRd = RMaNRd;
    }
    @JsonIgnore
    public List<SaveRMaNInfoReq02RMaNDl> getRMaNDlInfo() {
        return RMaNDlInfo;
    }
    @JsonIgnore
    public void setRMaNDlInfo(List<SaveRMaNInfoReq02RMaNDl> RMaNDlInfo) {
        this.RMaNDlInfo = RMaNDlInfo;
    }
}
