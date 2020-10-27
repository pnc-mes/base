package pnc.mesadmin.dto.GetDicInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：字典信息DTO返回业务数据实体类Res
 * 创建人：赵超
 * 创建时间：2017-5-24
 * 修改人：
 * 修改时间：
 */
public class GetDicInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetDicInfoResB Body;

    @JsonIgnore
    public GetDicInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetDicInfoResB body) {
        Body = body;
    }

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
}
