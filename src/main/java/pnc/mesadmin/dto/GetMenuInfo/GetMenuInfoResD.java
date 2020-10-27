package pnc.mesadmin.dto.GetMenuInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/5/19.
 */
public class GetMenuInfoResD implements Serializable {

    @JsonProperty("ResRd")
    private String ResRd;

    @JsonProperty("ResName")
    private String ResName;

    @JsonIgnore
    public String getResRd() {
        return ResRd;
    }

    @JsonIgnore
    public void setResRd(String resRd) {
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
}
