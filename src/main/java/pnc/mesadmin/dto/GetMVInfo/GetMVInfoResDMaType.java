package pnc.mesadmin.dto.GetMVInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/3.
 */
public class GetMVInfoResDMaType implements Serializable{

    @JsonProperty("MaTCode")
    private String MaTCode;

    @JsonProperty("MaTName")
    private String MaTName;

    @JsonProperty("Checked")
    private String Checked;

    @JsonIgnore
    public String getMaTCode() {
        return MaTCode;
    }

    @JsonIgnore
    public void setMaTCode(String maTCode) {
        MaTCode = maTCode;
    }

    @JsonIgnore
    public String getMaTName() {
        return MaTName;
    }

    @JsonIgnore
    public void setMaTName(String maTName) {
        MaTName = maTName;
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
