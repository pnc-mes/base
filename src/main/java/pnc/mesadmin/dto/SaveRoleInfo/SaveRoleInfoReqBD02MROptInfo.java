package pnc.mesadmin.dto.SaveRoleInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/5/18.
 */
public class SaveRoleInfoReqBD02MROptInfo implements Serializable{

    @JsonProperty("OptRd")
    private int OptRd;

    @JsonProperty("OptName")
    private String OptName;

    @JsonIgnore
    public int getOptRd() {
        return OptRd;
    }

    @JsonIgnore
    public void setOptRd(int optRd) {
        OptRd = optRd;
    }

    @JsonIgnore
    public String getOptName() {
        return OptName;
    }

    @JsonIgnore
    public void setOptName(String optName) {
        OptName = optName;
    }
}
