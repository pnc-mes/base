package pnc.mesadmin.dto.SaveNRMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.SaveRMInfoRes.SaveRMInfoReqBD02RetDlInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by test on 2017/9/22.
 */
public class SaveNRMInfoReqBD02 implements Serializable{
    @JsonProperty("RetRd")
    private int RetRd;
    @JsonProperty("RetDlInfo")
    private List<SaveRMInfoReqBD02RetDlInfo> RetDlInfo;
    @JsonIgnore
    public int getRetRd() {
        return RetRd;
    }
    @JsonIgnore
    public void setRetRd(int retRd) {
        RetRd = retRd;
    }
    @JsonIgnore
    public List<SaveRMInfoReqBD02RetDlInfo> getRetDlInfo() {
        return RetDlInfo;
    }
    @JsonIgnore
    public void setRetDlInfo(List<SaveRMInfoReqBD02RetDlInfo> retDlInfo) {
        RetDlInfo = retDlInfo;
    }
}
