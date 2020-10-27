package pnc.mesadmin.dto.SaveDevMalfInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/11/8 18:00
 * @Description:
 */
public class SaveDevMalfInfoReq02 implements Serializable {
    @JsonProperty("DevMalfRd")
    private int DevMalfRd;

    @JsonProperty("DevMalfCode")
    private String DevMalfCode;

    @JsonProperty("DevMalfName")
    private String DevMalfName;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public String getDevMalfCode() {
        return DevMalfCode;
    }
    @JsonIgnore
    public void setDevMalfCode(String devMalfCode) {
        DevMalfCode = devMalfCode;
    }
    @JsonIgnore
    public String getDevMalfName() {
        return DevMalfName;
    }
    @JsonIgnore
    public void setDevMalfName(String devMalfName) {
        DevMalfName = devMalfName;
    }
    @JsonIgnore
    public String getRemark() {
        return Remark;
    }
    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
    @JsonIgnore
    public int getDevMalfRd() {
        return DevMalfRd;
    }
    @JsonIgnore
    public void setDevMalfRd(int devMalfRd) {
        DevMalfRd = devMalfRd;
    }
}
