package pnc.mesadmin.dto.GetCMGCInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：公共接口 请求获取全局配置信息
 * 创建人：刘福志
 * 创建时间：2017-08-25
 * 修改人：
 * 修改时间：
 */
public class GetCMGCInfoReqBD01 implements Serializable {
    @JsonProperty("MsgName")
    private String MsgName;

    @JsonIgnore
    public String getMsgName() {
        return MsgName;
    }

    @JsonIgnore
    public void setMsgName(String msgName) {
        MsgName = msgName;
    }
}
