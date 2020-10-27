package pnc.mesadmin.dto.GetRoleInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/5/17.
 */
public class GetRoleInfoResDMResInfo implements Serializable{

    @JsonProperty("ResRd")
    private int ResRd;

    @JsonProperty("ResName")
    private String ResName;

    @JsonProperty("OptInfo")
    private List<GetRoleInfoResDMROptInfo> OptInfo;

    @JsonIgnore
    public int getResRd() {
        return ResRd;
    }

    @JsonIgnore
    public void setResRd(int resRd) {
        ResRd = resRd;
    }

    @JsonIgnore
    public String getResName() {
        return ResName;
    }

    @JsonIgnore
    public void setResName(String resName) {
        ResName = resName;
    }

    @JsonIgnore
    public List<GetRoleInfoResDMROptInfo> getOptInfo() {
        return OptInfo;
    }

    @JsonIgnore
    public void setOptInfo(List<GetRoleInfoResDMROptInfo> optInfo) {
        OptInfo = optInfo;
    }
}
