package pnc.mesadmin.dto.newmove.GetAllRVInfoDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class SaveRVInfoRes implements Serializable {
    @JsonProperty("RVID")
    private Integer RVID;
    //生产意见
    @JsonProperty("ProRvMsg")
    private String ProRvMsg;
    //工艺意见
    @JsonProperty("ProcessMsg")
    private String ProcessMsg;
    //质量意见
    @JsonProperty("QCRvMsg")
    private String QCRvMsg;
    @JsonProperty("ZGradeName")
    private String ZGradeName;
    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("BadInfo")
    private List<BadInfo> BadInfo;

    public static class BadInfo {
        @JsonProperty("BadType")
        private String BadType;
        @JsonProperty("BadCode")
        private String BadCode;
        @JsonProperty("BadName")
        private String BadName;
        @JsonProperty("LocationCode")
        private String LocationCode;
        @JsonProperty("LocationName")
        private String LocationName;

        @JsonIgnore
        public String getBadType() {
            return BadType;
        }

        @JsonIgnore
        public void setBadType(String badType) {
            BadType = badType;
        }

        @JsonIgnore
        public String getBadCode() {
            return BadCode;
        }

        @JsonIgnore
        public void setBadCode(String badCode) {
            BadCode = badCode;
        }

        @JsonIgnore
        public String getBadName() {
            return BadName;
        }

        @JsonIgnore
        public void setBadName(String badName) {
            BadName = badName;
        }

        @JsonIgnore
        public String getLocationCode() {
            return LocationCode;
        }

        @JsonIgnore
        public void setLocationCode(String locationCode) {
            LocationCode = locationCode;
        }

        @JsonIgnore
        public String getLocationName() {
            return LocationName;
        }

        @JsonIgnore
        public void setLocationName(String locationName) {
            LocationName = locationName;
        }
    }

    @JsonIgnore
    public Integer getRVID() {
        return RVID;
    }

    @JsonIgnore
    public void setRVID(Integer RVID) {
        this.RVID = RVID;
    }

    @JsonIgnore
    public String getProRvMsg() {
        return ProRvMsg;
    }

    @JsonIgnore
    public void setProRvMsg(String proRvMsg) {
        ProRvMsg = proRvMsg;
    }

    @JsonIgnore
    public String getProcessMsg() {
        return ProcessMsg;
    }

    @JsonIgnore
    public void setProcessMsg(String processMsg) {
        ProcessMsg = processMsg;
    }

    @JsonIgnore
    public String getQCRvMsg() {
        return QCRvMsg;
    }

    @JsonIgnore
    public void setQCRvMsg(String QCRvMsg) {
        this.QCRvMsg = QCRvMsg;
    }

    @JsonIgnore
    public String getZGradeName() {
        return ZGradeName;
    }

    @JsonIgnore
    public void setZGradeName(String ZGradeName) {
        this.ZGradeName = ZGradeName;
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
    public List<SaveRVInfoRes.BadInfo> getBadInfo() {
        return BadInfo;
    }

    @JsonIgnore
    public void setBadInfo(List<SaveRVInfoRes.BadInfo> badInfo) {
        BadInfo = badInfo;
    }
}
