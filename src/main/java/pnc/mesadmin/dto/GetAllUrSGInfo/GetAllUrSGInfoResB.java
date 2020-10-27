package pnc.mesadmin.dto.GetAllUrSGInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：技能培训记录信息列表DTO返回业务实体类body
 * 创建人：刘福志
 * 创建时间：2017/8/28
 * 修改人：
 * 修改时间：
 *
 */
public class GetAllUrSGInfoResB implements Serializable {
    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private List<GetAllUrSGInfoResD> Data;

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
    public List<GetAllUrSGInfoResD> getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(List<GetAllUrSGInfoResD> data) {
        Data = data;
    }
}
