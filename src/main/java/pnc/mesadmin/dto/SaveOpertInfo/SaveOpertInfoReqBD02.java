package pnc.mesadmin.dto.SaveOpertInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存作业信息DTO请求业务数据实体类BD00：编辑
 * 创建人：王怀龙
 * 创建时间：2017-06-02
 * 修改人：
 * 修改时间：
 */
public class SaveOpertInfoReqBD02 {
    @JsonProperty("OpertRd")
    private int OpertRd;
    @JsonProperty("OptName")
    private String OptName;
    @JsonProperty("WCRd")
    private int WCRd;
    @JsonProperty("BLRd")
    private int BLRd;
    @JsonProperty("SpecOption")
    private String SpecOption;
    @JsonProperty("PackInfo")
    private SaveOpertInfoReqBD00Pack PackInfo;
    @JsonProperty("BadOutSpec")
    private String BadOutSpec;
    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("ReaCGRd")
    private int ReaCGRd;

    @JsonIgnore
    public int getReaCGRd() {
        return ReaCGRd;
    }
    @JsonIgnore
    public void setReaCGRd(int reaCGRd) {
        ReaCGRd = reaCGRd;
    }

    @JsonIgnore
    public int getOpertRd() {
        return OpertRd;
    }
    @JsonIgnore
    public void setOpertRd(int opertRd) {
        OpertRd = opertRd;
    }
    @JsonIgnore
    public String getOptName() {
        return OptName;
    }
    @JsonIgnore
    public void setOptName(String optName) {
        OptName = optName;
    }
    @JsonIgnore
    public int getWCRd() {
        return WCRd;
    }
    @JsonIgnore
    public void setWCRd(int WCRd) {
        this.WCRd = WCRd;
    }
    @JsonIgnore
    public int getBLRd() {
        return BLRd;
    }
    @JsonIgnore
    public void setBLRd(int BLRd) {
        this.BLRd = BLRd;
    }
    @JsonIgnore
    public String getSpecOption() {
        return SpecOption;
    }
    @JsonIgnore
    public void setSpecOption(String specOption) {
        SpecOption = specOption;
    }
    @JsonIgnore
    public SaveOpertInfoReqBD00Pack getPackInfo() {
        return PackInfo;
    }
    @JsonIgnore
    public void setPackInfo(SaveOpertInfoReqBD00Pack packInfo) {
        PackInfo = packInfo;
    }
    @JsonIgnore
    public String getBadOutSpec() {
        return BadOutSpec;
    }
    @JsonIgnore
    public void setBadOutSpec(String badOutSpec) {
        BadOutSpec = badOutSpec;
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
