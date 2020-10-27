package pnc.mesadmin.dto.GetGCInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：全局配置信息DTO返回业务数据实体类data
 * 创建人：刘福志
 * 创建时间：2017-8-25
 * 修改人：
 * 修改时间：
 */
public class GetGCInfoResD implements Serializable {
    @JsonProperty("ModeC")
    private List<GetGCInfoResDModeC> ModeC;

    @JsonProperty("MsgC")
    private List<GetGCInfoResDMsgC> MsgC;

    @JsonProperty("CodeRule")
    private List<GetGCInfoResDCodeRule> CodeRule;

    @JsonProperty("SynchC")
    private List<GetGCInfoResDSynchC> SynchC;

    @JsonIgnore
    public List<GetGCInfoResDSynchC> getSynchC() {
        return SynchC;
    }

    @JsonIgnore
    public void setSynchC(List<GetGCInfoResDSynchC> synchC) {
        SynchC = synchC;
    }

    @JsonIgnore
    public List<GetGCInfoResDCodeRule> getCodeRule() {
        return CodeRule;
    }

    @JsonIgnore
    public void setCodeRule(List<GetGCInfoResDCodeRule> codeRule) {
        CodeRule = codeRule;
    }

    @JsonIgnore
    public List<GetGCInfoResDModeC> getModeC() {
        return ModeC;
    }

    @JsonIgnore
    public void setModeC(List<GetGCInfoResDModeC> modeC) {
        ModeC = modeC;
    }

    @JsonIgnore
    public List<GetGCInfoResDMsgC> getMsgC() {
        return MsgC;
    }

    @JsonIgnore
    public void setMsgC(List<GetGCInfoResDMsgC> msgC) {
        MsgC = msgC;
    }
}
