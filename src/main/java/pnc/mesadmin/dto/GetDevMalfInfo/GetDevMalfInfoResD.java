package pnc.mesadmin.dto.GetDevMalfInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/11/8 17:52
 * @Description:
 */
public class GetDevMalfInfoResD implements Serializable {
    @JsonProperty("DevMalfRd")
    private int DevMalfRd;
    @JsonProperty("DevMalfCode")
    private String DevMalfCode;
    @JsonProperty("DevMalfName")
    private String DevMalfName;
    @JsonProperty("Creator")
    private String Creator;
    @JsonProperty("CreateTime")
    private String CreateTime;
    @JsonProperty("LastModifyMan")
    private String LastModifyMan;
    @JsonProperty("LastModifyTime")
    private String LastModifyTime;
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
    public String getCreator() {
        return Creator;
    }
    @JsonIgnore
    public void setCreator(String creator) {
        Creator = creator;
    }
    @JsonIgnore
    public String getCreateTime() {
        return CreateTime;
    }
    @JsonIgnore
    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }
    @JsonIgnore
    public String getLastModifyMan() {
        return LastModifyMan;
    }
    @JsonIgnore
    public void setLastModifyMan(String lastModifyMan) {
        LastModifyMan = lastModifyMan;
    }
    @JsonIgnore
    public String getLastModifyTime() {
        return LastModifyTime;
    }
    @JsonIgnore
    public void setLastModifyTime(String lastModifyTime) {
        LastModifyTime = lastModifyTime;
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
