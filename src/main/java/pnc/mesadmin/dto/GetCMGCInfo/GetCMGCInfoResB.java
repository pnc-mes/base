package pnc.mesadmin.dto.GetCMGCInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：公共接口 获取全局配置信息返回的body
 * 创建人：刘福志
 * 创建时间：2017-08-25
 * 修改人：
 * 修改时间：
 */
public class GetCMGCInfoResB implements Serializable {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private List<GetCMGCInfoResD> Data;

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
    public List<GetCMGCInfoResD> getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(List<GetCMGCInfoResD> data) {
        Data = data;
    }
}
