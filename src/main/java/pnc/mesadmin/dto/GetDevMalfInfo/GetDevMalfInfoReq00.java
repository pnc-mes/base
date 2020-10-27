package pnc.mesadmin.dto.GetDevMalfInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/11/8 17:52
 * @Description:
 */
public class GetDevMalfInfoReq00 implements Serializable {
    @JsonProperty("DevMalfRd")
    private int DevMalfRd;
    @JsonIgnore
    public int getDevMalfRd() {
        return DevMalfRd;
    }
    @JsonIgnore
    public void setDevMalfRd(int devMalfRd) {
        DevMalfRd = devMalfRd;
    }
}
