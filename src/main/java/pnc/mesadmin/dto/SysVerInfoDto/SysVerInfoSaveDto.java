package pnc.mesadmin.dto.SysVerInfoDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-03-11
 **/
public class SysVerInfoSaveDto {
    @JsonProperty("SysVerName")
    private String SysVerName;
    @JsonProperty("SourceSysVRd")
    private Integer SourceSysVRd;
    @JsonProperty("SysVerRd")
    private Integer SysVerRd;
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
    public String getSysVerName() {
        return SysVerName;
    }

    @JsonIgnore
    public void setSysVerName(String sysVerName) {
        SysVerName = sysVerName;
    }

    @JsonIgnore
    public Integer getSourceSysVRd() {
        return SourceSysVRd;
    }

    @JsonIgnore
    public void setSourceSysVRd(Integer sourceSysVRd) {
        SourceSysVRd = sourceSysVRd;
    }

    @JsonIgnore
    public Integer getSysVerRd() {
        return SysVerRd;
    }

    @JsonIgnore
    public void setSysVerRd(Integer sysVerRd) {
        SysVerRd = sysVerRd;
    }
}
