package pnc.mesadmin.dto.GetAllFaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：获取工厂信息DTO返回实体类Res
 * 创建人：张亮亮
 * 创建时间：2017-5-27
 * 修改人：
 * 修改时间：
 */
public class GetAllFaInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllFaInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllFaInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllFaInfoResB body) {
        Body = body;
    }
}
