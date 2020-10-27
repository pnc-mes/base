package pnc.mesadmin.dto.SaveDcInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存数据采集信息DTO请求业务数据实体类BD04：新增版本
 * 创建人：赵超
 * 创建时间：2017-5-25
 * 修改人：
 * 修改时间：
 */
public class SaveDcInfoReqBD04 implements Serializable{

    @JsonProperty("DCRd")
    private int DCRd;

    @JsonProperty("DCName")
    private String DCName;

    @JsonProperty("Version")
    private String Version;

    @JsonProperty("IsDef")
    private String IsDef;

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("EmailDistributionRd")
    private int EmailDistributionRd;

    @JsonProperty("EmailMessageRd")
    private int EmailMessageRd;

    @JsonProperty("DCItemsInfo")
    private List<SaveDcInfoReqDCItems> DCItemsInfo;

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
    public List<SaveDcInfoReqDCItems> getDCItemsInfo() {
        return DCItemsInfo;
    }

    @JsonIgnore
    public void setDCItemsInfo(List<SaveDcInfoReqDCItems> DCItemsInfo) {
        this.DCItemsInfo = DCItemsInfo;
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
    public int getEmailDistributionRd() {
        return EmailDistributionRd;
    }

    @JsonIgnore
    public void setEmailDistributionRd(int emailDistributionRd) {
        EmailDistributionRd = emailDistributionRd;
    }

    @JsonIgnore
    public int getEmailMessageRd() {
        return EmailMessageRd;
    }

    @JsonIgnore
    public void setEmailMessageRd(int emailMessageRd) {
        EmailMessageRd = emailMessageRd;
    }
}
