package pnc.mesadmin.dto.GetCarrierFamilyInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：获取载具家族信息DTO返回实体类Res
 * 创建人：郝赞
 * 创建时间：2018-6-19
 * 修改人：
 * 修改时间：
 */
public class GetCarrierFamilyInfoResB implements Serializable{

    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private GetCarrierFamilyInfoResD Data;

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
    public GetCarrierFamilyInfoResD getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(GetCarrierFamilyInfoResD data) {
        Data = data;
    }
}
