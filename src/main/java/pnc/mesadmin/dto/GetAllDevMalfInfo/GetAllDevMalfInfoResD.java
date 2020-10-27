package pnc.mesadmin.dto.GetAllDevMalfInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/11/8 17:47
 * @Description:
 */
public class GetAllDevMalfInfoResD implements Serializable {
    @JsonProperty("DevMalfRd")
    private int DevMalfRd;

    @JsonProperty("DevMalfName")
    private String DevMalfName;
    @JsonIgnore
    public int getDevMalfRd() {
        return DevMalfRd;
    }
    @JsonIgnore
    public void setDevMalfRd(int devMalfRd) {
        DevMalfRd = devMalfRd;
    }
    @JsonIgnore
    public String getDevMalfName() {
        return DevMalfName;
    }
    @JsonIgnore
    public void setDevMalfName(String devMalfName) {
        DevMalfName = devMalfName;
    }
}
