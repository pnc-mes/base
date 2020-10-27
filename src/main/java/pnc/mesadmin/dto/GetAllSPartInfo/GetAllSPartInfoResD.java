package pnc.mesadmin.dto.GetAllSPartInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/11/8 15:53
 * @Description:
 */
public class GetAllSPartInfoResD implements Serializable {
    @JsonProperty("SPartRd")
    private int SPartRd;

    @JsonProperty("SPartName")
    private String SPartName;
    @JsonIgnore
    public int getSPartRd() {
        return SPartRd;
    }
    @JsonIgnore
    public void setSPartRd(int SPartRd) {
        this.SPartRd = SPartRd;
    }
    @JsonIgnore
    public String getSPartName() {
        return SPartName;
    }
    @JsonIgnore
    public void setSPartName(String SPartName) {
        this.SPartName = SPartName;
    }
}
