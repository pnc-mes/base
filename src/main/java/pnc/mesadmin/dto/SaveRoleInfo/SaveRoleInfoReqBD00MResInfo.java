package pnc.mesadmin.dto.SaveRoleInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/5/18.
 */
public class SaveRoleInfoReqBD00MResInfo implements Serializable{

    @JsonProperty("ResRd")
    private int ResRd;

    @JsonProperty("ResName")
    private String ResName;

    @JsonProperty("OptInfo")
    private List<SaveRoleInfoReqBD00MROptInfo> OptInfo;

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
    public List<SaveRoleInfoReqBD00MROptInfo> getOptInfo() {
        return OptInfo;
    }

    @JsonIgnore
    public void setOptInfo(List<SaveRoleInfoReqBD00MROptInfo> optInfo) {
        OptInfo = optInfo;
    }
}
