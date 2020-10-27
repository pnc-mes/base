package pnc.mesadmin.dto.SaveDevSInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2017/8/18.
 */
public class SaveDevSInfoReq02 implements Serializable {
    @JsonProperty("DevSRd")
    private int DevSRd;
    @JsonProperty("DevSName")
    private String DevSName;
    @JsonProperty("Remark")
    private String Remark;
    @JsonProperty("Status")
    private String Status;
    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public String getDevSName() {
        return DevSName;
    }
    @JsonIgnore
    public void setDevSName(String devSName) {
        DevSName = devSName;
    }
    @JsonIgnore
    public String getRemark() {
        return Remark;
    }
    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
    @JsonIgnore
    public int getDevSRd() {
        return DevSRd;
    }
    @JsonIgnore
    public void setDevSRd(int devSRd) {
        DevSRd = devSRd;
    }

}
