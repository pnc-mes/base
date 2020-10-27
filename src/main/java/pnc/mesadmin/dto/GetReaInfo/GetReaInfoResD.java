package pnc.mesadmin.dto.GetReaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：获取资源信息DTO返回业务数据实体类Data
 * 创建人：张亮亮
 * 创建时间：2017-5-31
 * 修改人：
 * 修改时间：
 */
public class GetReaInfoResD implements Serializable{

    @JsonProperty("ReaRd")
    private int ReaRd;

    @JsonProperty("ReaDes")
    private String ReaDes;

    @JsonProperty("ReaCode")
    private String ReaCode;

    @JsonProperty("ReaType")
    private String ReaType;

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
    @JsonProperty("ReaTInfo")
    private GetReaInfoResDReaT ReaTInfo;

    @JsonIgnore
    public String getReaType() {
        return ReaType;
    }

    @JsonIgnore
    public void setReaType(String reaType) {
        ReaType = reaType;
    }

    @JsonIgnore
    public String getReaCode() {
        return ReaCode;
    }

    @JsonIgnore
    public void setReaCode(String reaCode) {
        ReaCode = reaCode;
    }

    @JsonIgnore
    public int getReaRd() {
        return ReaRd;
    }

    @JsonIgnore
    public void setReaRd(int reaRd) {
        ReaRd = reaRd;
    }

    @JsonIgnore
    public String getReaDes() {
        return ReaDes;
    }

    @JsonIgnore
    public void setReaDes(String reaDes) {
        ReaDes = reaDes;
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
    public GetReaInfoResDReaT getReaTInfo() {
        return ReaTInfo;
    }
    @JsonIgnore
    public void setReaTInfo(GetReaInfoResDReaT reaTInfo) {
        ReaTInfo = reaTInfo;
    }
}
