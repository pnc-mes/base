package pnc.mesadmin.dto.GetCLevelInfoRes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2019/3/22.
 */
public class GetCLevelInfoResD implements Serializable {


    @JsonProperty("CLevelRd")
    private int CLevelRd;

    @JsonProperty("CheckLevelCode")
    private String CheckLevelCode;

    @JsonProperty("CheckLevelName")
    private String CheckLevelName;

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
    public int getCLevelRd() {
        return CLevelRd;
    }
    @JsonIgnore
    public void setCLevelRd(int CLevelRd) {
        this.CLevelRd = CLevelRd;
    }
    @JsonIgnore
    public String getCheckLevelCode() {
        return CheckLevelCode;
    }
    @JsonIgnore
    public void setCheckLevelCode(String checkLevelCode) {
        CheckLevelCode = checkLevelCode;
    }
    @JsonIgnore
    public String getCheckLevelName() {
        return CheckLevelName;
    }
    @JsonIgnore
    public void setCheckLevelName(String checkLevelName) {
        CheckLevelName = checkLevelName;
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
