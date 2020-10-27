package pnc.mesadmin.dto.GetDVInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetDcInfo.GetDcInfoResDEmailDistribution;
import pnc.mesadmin.dto.GetDcInfo.GetDcInfoResDEmailMessage;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：数据采集版本详细信息DTO，返回版本列表的业务实体类Data
 * 创建人：赵超
 * 创建时间：2017-5-25
 * 修改人：
 * 修改时间：
 */
public class GetDVInfoResD implements Serializable{

    @JsonProperty("DCRd")
    private int DCRd;

    @JsonProperty("DCVerRd")
    private int DCVerRd;

    @JsonProperty("DCName")
    private String DCName;

    @JsonProperty("Version")
    private String Version;

    @JsonProperty("IsDef")
    private String IsDef;

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("DCItemsInfo")
    private List<GetDVInfoResDDCItems> DCItemsInfo;

    @JsonProperty("EmailDistributionInfo")
    private GetDcInfoResDEmailDistribution EmailDistributionInfo;

    @JsonProperty("EmailMessageInfo")
    private GetDcInfoResDEmailMessage EmailMessageInfo;

    @JsonProperty("Creator")
    private String Creator;

    @JsonProperty("CreateTime")
    private Date CreateTime;

    @JsonProperty("LastModifyMan")
    private String LastModifyMan;

    @JsonProperty("LastModifyTime")
    private Date LastModifyTime;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public int getDCRd() {
        return DCRd;
    }

    @JsonIgnore
    public void setDCRd(int DCRd) {
        this.DCRd = DCRd;
    }

    @JsonIgnore
    public int getDCVerRd() {
        return DCVerRd;
    }

    @JsonIgnore
    public void setDCVerRd(int DCVerRd) {
        this.DCVerRd = DCVerRd;
    }

    @JsonIgnore
    public String getDCName() {
        return DCName;
    }

    @JsonIgnore
    public void setDCName(String DCName) {
        this.DCName = DCName;
    }

    @JsonIgnore
    public String getVersion() {
        return Version;
    }

    @JsonIgnore
    public void setVersion(String version) {
        Version = version;
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
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public List<GetDVInfoResDDCItems> getDCItemsInfo() {
        return DCItemsInfo;
    }

    @JsonIgnore
    public void setDCItemsInfo(List<GetDVInfoResDDCItems> DCItemsInfo) {
        this.DCItemsInfo = DCItemsInfo;
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
    public Date getCreateTime() {
        return CreateTime;
    }

    @JsonIgnore
    public void setCreateTime(Date createTime) {
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
    public Date getLastModifyTime() {
        return LastModifyTime;
    }

    @JsonIgnore
    public void setLastModifyTime(Date lastModifyTime) {
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
    public GetDcInfoResDEmailDistribution getEmailDistributionInfo() {
        return EmailDistributionInfo;
    }
    @JsonIgnore
    public void setEmailDistributionInfo(GetDcInfoResDEmailDistribution emailDistributionInfo) {
        EmailDistributionInfo = emailDistributionInfo;
    }
    @JsonIgnore
    public GetDcInfoResDEmailMessage getEmailMessageInfo() {
        return EmailMessageInfo;
    }
    @JsonIgnore
    public void setEmailMessageInfo(GetDcInfoResDEmailMessage emailMessageInfo) {
        EmailMessageInfo = emailMessageInfo;
    }
}
