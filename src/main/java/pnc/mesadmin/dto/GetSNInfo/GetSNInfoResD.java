package pnc.mesadmin.dto.GetSNInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：获取序号管理信息DTO返回业务数据实体类Data
 * 创建人：张亮亮
 * 创建时间：2017-06-07
 * 修改人：
 * 修改时间：
 */
public class GetSNInfoResD implements Serializable{

    @JsonProperty("SNRd")
    private int SNRd;

    @JsonProperty("SNName")
    private String SNName;

    @JsonProperty("IsScript")
    private String IsScript;

    @JsonProperty("ScriptName")
    private String ScriptName;

    @JsonProperty("BFInfo")
    private List<BFInfo> BFInfo;


    @JsonProperty("Prefix")
    private String Prefix;

    @JsonProperty("Suffix")
    private String Suffix;

    @JsonProperty("SNLength")
    private int SNLength;

    @JsonProperty("Start")
    private int Start;

    @JsonProperty("Step")
    private int Step;

    @JsonProperty("LastLevel")
    private int LastLevel;

    @JsonProperty("Reset")
    private String Reset;

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
    public String getIsScript() {
        return IsScript;
    }

    @JsonIgnore
    public void setIsScript(String isScript) {
        IsScript = isScript;
    }

    @JsonIgnore
    public String getScriptName() {
        return ScriptName;
    }

    @JsonIgnore
    public void setScriptName(String scriptName) {
        ScriptName = scriptName;
    }

    @JsonIgnore
    public List<GetSNInfoResD.BFInfo> getBFInfo() {
        return BFInfo;
    }

    @JsonIgnore
    public void setBFInfo(List<GetSNInfoResD.BFInfo> BFInfo) {
        this.BFInfo = BFInfo;
    }

    public static class BFInfo{

        @JsonProperty("BFCode")
        private String BFCode;

        @JsonProperty("BFName")
        private String BFName;

        @JsonIgnore
        public String getBFCode() {
            return BFCode;
        }

        @JsonIgnore
        public void setBFCode(String BFCode) {
            this.BFCode = BFCode;
        }

        @JsonIgnore
        public String getBFName() {
            return BFName;
        }

        @JsonIgnore
        public void setBFName(String BFName) {
            this.BFName = BFName;
        }
    }

    @JsonIgnore
    public int getSNRd() {
        return SNRd;
    }

    @JsonIgnore
    public void setSNRd(int SNRd) {
        this.SNRd = SNRd;
    }

    @JsonIgnore
    public String getSNName() {
        return SNName;
    }

    @JsonIgnore
    public void setSNName(String SNName) {
        this.SNName = SNName;
    }

    @JsonIgnore
    public String getPrefix() {
        return Prefix;
    }

    @JsonIgnore
    public void setPrefix(String prefix) {
        Prefix = prefix;
    }

    @JsonIgnore
    public String getSuffix() {
        return Suffix;
    }

    @JsonIgnore
    public void setSuffix(String suffix) {
        Suffix = suffix;
    }

    @JsonIgnore
    public int getSNLength() {
        return SNLength;
    }

    @JsonIgnore
    public void setSNLength(int SNLength) {
        this.SNLength = SNLength;
    }

    @JsonIgnore
    public int getStart() {
        return Start;
    }

    @JsonIgnore
    public void setStart(int start) {
        Start = start;
    }

    @JsonIgnore
    public int getStep() {
        return Step;
    }

    @JsonIgnore
    public void setStep(int step) {
        Step = step;
    }

    @JsonIgnore
    public int getLastLevel() {
        return LastLevel;
    }

    @JsonIgnore
    public void setLastLevel(int lastLevel) {
        LastLevel = lastLevel;
    }

    @JsonIgnore
    public String getReset() {
        return Reset;
    }

    @JsonIgnore
    public void setReset(String reset) {
        Reset = reset;
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
