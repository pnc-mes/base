package pnc.mesadmin.dto.GetGCInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：消息配置信息DTO返回业务数据实体类data
 * 创建人：刘福志
 * 创建时间：2017-8-25
 * 修改人：
 * 修改时间：
 */
public class GetGCInfoResDMsgC implements Serializable {

    @JsonProperty("MsgName")
    private String MsgName;

    @JsonProperty("MsgValue")
    private String MsgValue;

    @JsonIgnore
    public String getMsgName() {
        return MsgName;
    }

    @JsonIgnore
    public void setMsgName(String msgName) {
        MsgName = msgName;
    }

    @JsonIgnore
    public String getMsgValue() {
        return MsgValue;
    }

    @JsonIgnore
    public void setMsgValue(String msgValue) {
        MsgValue = msgValue;
    }
}
