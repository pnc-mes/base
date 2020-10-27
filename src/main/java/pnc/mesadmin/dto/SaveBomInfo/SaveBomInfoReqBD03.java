package pnc.mesadmin.dto.SaveBomInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/6/9.
 */
public class SaveBomInfoReqBD03 implements Serializable{

    @JsonProperty("BomVerRd")
    private int BomVerRd;

    @JsonProperty("VersionNo")
    private String VersionNo;

    @JsonProperty("BomNo")
    private String BomNo;

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("BMInfo")
    private List<SaveBomInfoReqBD03BMInfo> BMInfo;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public int getBomVerRd() {
        return BomVerRd;
    }

    @JsonIgnore
    public void setBomVerRd(int bomVerRd) {
        BomVerRd = bomVerRd;
    }

    @JsonIgnore
    public String getVersionNo() {
        return VersionNo;
    }

    @JsonIgnore
    public void setVersionNo(String versionNo) {
        VersionNo = versionNo;
    }

    @JsonIgnore
    public String getBomNo() {
        return BomNo;
    }

    @JsonIgnore
    public void setBomNo(String bomNo) {
        BomNo = bomNo;
    }

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }


    @JsonIgnore
    public List<SaveBomInfoReqBD03BMInfo> getBMInfo() {
        return BMInfo;
    }

    @JsonIgnore
    public void setBMInfo(List<SaveBomInfoReqBD03BMInfo> BMInfo) {
        this.BMInfo = BMInfo;
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
