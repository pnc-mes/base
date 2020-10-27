package pnc.mesadmin.dto.GetRoleInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/5/17.
 */
public class GetRoleInfoResDMROptInfo implements Serializable{

    @JsonProperty("OptRd")
    private int OptRd;

    @JsonProperty("OptName")
    private String OptName;

    @JsonProperty("Checked")
    private String Checked;

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

    @JsonIgnore
    public String getChecked() {
        return Checked;
    }

    @JsonIgnore
    public void setChecked(String checked) {
        Checked = checked;
    }
}
