package pnc.mesadmin.dto.GetRKTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：获取入库单类型信息DTO返回实体类Res
 * 创建人：张亮亮
 * 创建时间：2017-06-10
 * 修改人：
 * 修改时间：
 */
public class GetRKTInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetRKTInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetRKTInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetRKTInfoResB body) {
        Body = body;
    }
}
