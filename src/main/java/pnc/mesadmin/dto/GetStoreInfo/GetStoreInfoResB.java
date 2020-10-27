package pnc.mesadmin.dto.GetStoreInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：仓库信息DTO返回业务数据实体类body
 * 创建人：刘福志
 * 创建时间：2017-8-15
 * 修改人：
 * 修改时间：
 */
public class GetStoreInfoResB implements Serializable {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private GetStoreInfoResD Data;

    @JsonIgnore
    public String getMsgCode() {
        return MsgCode;
    }

    @JsonIgnore
    public void setMsgCode(String msgCode) {
        MsgCode = msgCode;
    }

    @JsonIgnore
    public String getMsgDes() {
        return MsgDes;
    }

    @JsonIgnore
    public void setMsgDes(String msgDes) {
        MsgDes = msgDes;
    }

    @JsonIgnore
    public GetStoreInfoResD getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(GetStoreInfoResD data) {
        Data = data;
    }
}
