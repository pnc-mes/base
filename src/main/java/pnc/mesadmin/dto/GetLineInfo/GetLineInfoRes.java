package pnc.mesadmin.dto.GetLineInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：获取线体信息DTO返回实体类Res
 * 创建人：郝赞
 * 创建时间：2018-6-7
 * 修改人：
 * 修改时间：
 */
public class GetLineInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetLineInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetLineInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetLineInfoResB body) {
        Body = body;
    }
}
