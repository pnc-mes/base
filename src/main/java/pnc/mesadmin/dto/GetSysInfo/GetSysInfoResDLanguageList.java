package pnc.mesadmin.dto.GetSysInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/5/19.
 */
public class GetSysInfoResDLanguageList implements Serializable{

    @JsonProperty("LanCode")
    private String LanCode;

    @JsonProperty("LanName")
    private String LanName;

    @JsonProperty("Checked")
    private String Checked;

    @JsonIgnore
    public String getLanCode() {
        return LanCode;
    }

    @JsonIgnore
    public void setLanCode(String lanCode) {
        LanCode = lanCode;
    }

    @JsonIgnore
    public String getLanName() {
        return LanName;
    }

    @JsonIgnore
    public void setLanName(String lanName) {
        LanName = lanName;
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
