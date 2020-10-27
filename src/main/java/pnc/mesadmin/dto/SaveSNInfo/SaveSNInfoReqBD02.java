package pnc.mesadmin.dto.SaveSNInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：编辑工序管理DTO返回业务数据实体类Res
 * 创建人：张亮亮
 * 创建时间：2017-06-08
 * 修改人：
 * 修改时间：
 */
public class SaveSNInfoReqBD02 implements Serializable{

    @JsonProperty("SNRd")
    private int SNRd;

    @JsonProperty("SNName")
    private String SNName;

    @JsonProperty("ScriptName")
    private String ScriptName;

    @JsonProperty("IsScript")
    private String IsScript;

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

    @JsonProperty("Reset")
    private String Reset;

    @JsonProperty("Remark")
    private String Remark;

    public static class BFInfo{

        @JsonProperty("BFCode")
        private String BFCode;

        @JsonProperty("BFName")
        private String BFName;

        @JsonIgnore
        public void setBFCode(String BFCode) {
            this.BFCode = BFCode;
        }
        @JsonIgnore
        public String getBFCode() {
            return BFCode;
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
    public String getScriptName() {
        return ScriptName;
    }

    @JsonIgnore
    public void setScriptName(String scriptName) {
        ScriptName = scriptName;
    }

    @JsonIgnore
    public String getIsScript() {
        return IsScript;
    }

    @JsonIgnore
    public void setIsScript(String isScript) {
        IsScript = isScript;
    }

    @JsonIgnore
    public List<SaveSNInfoReqBD02.BFInfo> getBFInfo() {
        return BFInfo;
    }

    @JsonIgnore
    public void setBFInfo(List<SaveSNInfoReqBD02.BFInfo> BFInfo) {
        this.BFInfo = BFInfo;
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
    public String getReset() {
        return Reset;
    }

    @JsonIgnore
    public void setReset(String reset) {
        Reset = reset;
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
