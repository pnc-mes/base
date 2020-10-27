package pnc.mesadmin.dto.GetRKBarInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import pnc.mesadmin.dto.GetRKDlInfo.GetRKDlInfoResB;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：获取入库单条码信息DTO返回实体类Res
 * 创建人：张亮亮
 * 创建时间：2017-06-12
 * 修改人：
 * 修改时间：
 */
public class GetRKBarInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetRKBarInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetRKBarInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetRKBarInfoResB body) {
        Body = body;
    }
}
