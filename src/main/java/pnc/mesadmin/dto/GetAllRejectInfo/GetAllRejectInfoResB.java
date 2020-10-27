package pnc.mesadmin.dto.GetAllRejectInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：原因代码DTO返回业务数据实体类resB
 * 创建人：郝赞
 * 创建时间：2018-8-6
 * 修改人：
 * 修改时间：
 */
public class GetAllRejectInfoResB {

    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    protected List<GetAllRejectInfoResD> Data;

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
    public List<GetAllRejectInfoResD> getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(List<GetAllRejectInfoResD> data) {
        Data = data;
    }
}
