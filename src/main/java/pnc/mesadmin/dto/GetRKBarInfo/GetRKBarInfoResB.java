package pnc.mesadmin.dto.GetRKBarInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

import pnc.mesadmin.dto.GetRKDlInfo.GetRKDlInfoResD;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：获取入库单条码信息DTO返回实体类Body
 * 创建人：张亮亮
 * 创建时间：2017-06-12
 * 修改人：
 * 修改时间：
 */
public class GetRKBarInfoResB implements Serializable {

    @JsonProperty("MsgCode")
    private String MsgCode;

    @JsonProperty("MsgDes")
    private String MsgDes;

    @JsonProperty("Data")
    private List<GetRKBarInfoResD> Data;

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
    public List<GetRKBarInfoResD> getData() {
        return Data;
    }

    @JsonIgnore
    public void setData(List<GetRKBarInfoResD> data) {
        Data = data;
    }
}
