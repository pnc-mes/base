package pnc.mesadmin.dto.GetSgyInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by liufuzhi on 2018/1/30.
 */
public class GetSgyInfoResD implements Serializable {
    @JsonProperty("SgyRd")
    private int SgyRd;

    @JsonProperty("SgyName")
    private String SgyName;

    @JsonProperty("IsDef")
    private String IsDef;

    @JsonProperty("SInCome")
    private String SInCome;

    @JsonProperty("PartRev")
    private String PartRev;

    @JsonProperty("ActOn")
    private String ActOn;

    @JsonProperty("Status")
    private String Status;

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
    public int getSgyRd() {
        return SgyRd;
    }

    @JsonIgnore
    public void setSgyRd(int sgyRd) {
        SgyRd = sgyRd;
    }

    @JsonIgnore
    public String getSgyName() {
        return SgyName;
    }

    @JsonIgnore
    public void setSgyName(String sgyName) {
        SgyName = sgyName;
    }

    @JsonIgnore
    public String getIsDef() {
        return IsDef;
    }

    @JsonIgnore
    public void setIsDef(String isDef) {
        IsDef = isDef;
    }

    @JsonIgnore
    public String getSInCome() {
        return SInCome;
    }

    @JsonIgnore
    public void setSInCome(String SInCome) {
        this.SInCome = SInCome;
    }

    @JsonIgnore
    public String getPartRev() {
        return PartRev;
    }

    @JsonIgnore
    public void setPartRev(String partRev) {
        PartRev = partRev;
    }

    @JsonIgnore
    public String getActOn() {
        return ActOn;
    }

    @JsonIgnore
    public void setActOn(String actOn) {
        ActOn = actOn;
    }

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
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
