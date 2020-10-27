package pnc.mesadmin.dto.SaveGCInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.SaveSMTPInfo.SaveSMTPInfoReqBD00;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liufuzhi on 2017/8/25.
 */
public class SaveGCInfoReqBD00 implements Serializable {
    @JsonProperty("ModeC")
    private List<SaveGCInfoReqBD00ModeC> ModeC;

    @JsonProperty("MsgC")
    private List<SaveGCInfoReqBD00MsgC> MsgC;

    @JsonProperty("CodeRule")
    private List<SaveGCInfoReqBD00CodeRule> CodeRule;

    @JsonProperty("SynchC")
    private List<SaveGCInfoReqBD00SynchC> SynchC;

    //熊伟 保存邮箱
    @JsonProperty("SMTPInfo")
    private SaveSMTPInfoReqBD00 SMTPInfo;

    @JsonIgnore
    public SaveSMTPInfoReqBD00 getSMTPInfo() {
        return SMTPInfo;
    }
    @JsonIgnore
    public void setSMTPInfo(SaveSMTPInfoReqBD00 SMTPInfo) {
        this.SMTPInfo = SMTPInfo;
    }

    @JsonIgnore
    public List<SaveGCInfoReqBD00SynchC> getSynchC() {
        return SynchC;
    }

    @JsonIgnore
    public void setSynchC(List<SaveGCInfoReqBD00SynchC> synchC) {
        SynchC = synchC;
    }

    @JsonIgnore
    public List<SaveGCInfoReqBD00CodeRule> getCodeRule() {
        return CodeRule;
    }

    @JsonIgnore
    public void setCodeRule(List<SaveGCInfoReqBD00CodeRule> codeRule) {
        CodeRule = codeRule;
    }

    @JsonIgnore
    public List<SaveGCInfoReqBD00ModeC> getModeC() {
        return ModeC;
    }

    @JsonIgnore
    public void setModeC(List<SaveGCInfoReqBD00ModeC> modeC) {
        ModeC = modeC;
    }

    @JsonIgnore
    public List<SaveGCInfoReqBD00MsgC> getMsgC() {
        return MsgC;
    }

    @JsonIgnore
    public void setMsgC(List<SaveGCInfoReqBD00MsgC> msgC) {
        MsgC = msgC;
    }

}
