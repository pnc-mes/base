package pnc.mesadmin.dto.GetToolInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/18 11:19
 * @Description:
 */
public class GetToolInfoResDSuppier implements Serializable {
    @JsonProperty("SupRd")
    private int SupRd;

    @JsonProperty("SupName")
    private String SupName;

    @JsonIgnore
    public int getSupRd() {
        return SupRd;
    }
    @JsonIgnore
    public void setSupRd(int supRd) {
        SupRd = supRd;
    }
    @JsonIgnore
    public String getSupName() {
        return SupName;
    }
    @JsonIgnore
    public void setSupName(String supName) {
        SupName = supName;
    }
}
