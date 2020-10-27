package pnc.mesadmin.dto.GetCMethodInfoRes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2019/3/22.
 */
public class GetCMethodInfoResD implements Serializable {
    @JsonProperty("CMethodRd")
    private int CMethodRd;

    @JsonProperty("CheckMethodCode")
    private String CheckMethodCode;

    @JsonProperty("CheckMethodName")
    private String CheckMethodName;

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

    public int getCMethodRd() {
        return CMethodRd;
    }
    @JsonIgnore
    public void setCMethodRd(int CMethodRd) {
        this.CMethodRd = CMethodRd;
    }
    @JsonIgnore
    public String getCheckMethodCode() {
        return CheckMethodCode;
    }
    @JsonIgnore
    public void setCheckMethodCode(String checkMethodCode) {
        CheckMethodCode = checkMethodCode;
    }
    @JsonIgnore
    public String getCheckMethodName() {
        return CheckMethodName;
    }
    @JsonIgnore
    public void setCheckMethodName(String checkMethodName) {
        CheckMethodName = checkMethodName;
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
}
