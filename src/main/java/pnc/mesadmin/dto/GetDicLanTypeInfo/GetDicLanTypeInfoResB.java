package pnc.mesadmin.dto.GetDicLanTypeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：字典信息语言类别DTO返回业务数据实体类Body
 * 创建人：赵超
 * 创建时间：2017-5-24
 * 修改人：
 * 修改时间：
 */
public class GetDicLanTypeInfoResB implements Serializable{

    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private List<GetDicLanTypeInfoResD> Data;

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
    public List<GetDicLanTypeInfoResD> getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(List<GetDicLanTypeInfoResD> data) {
        Data = data;
    }
}
