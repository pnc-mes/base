package pnc.mesadmin.dto.SaveToolFamilyInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存工具家族信息dto返回实体类Data
 * 创建人：郝赞
 * 创建时间：2018-6-15
 * 修改人：
 * 修改时间：
 */
public class SaveToolFamilyInfoResB implements Serializable{

    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private SaveToolFamilyInfoResD Data;

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
    public SaveToolFamilyInfoResD getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(SaveToolFamilyInfoResD data) {
        Data = data;
    }
}
