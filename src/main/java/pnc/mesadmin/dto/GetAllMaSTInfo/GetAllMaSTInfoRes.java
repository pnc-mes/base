package pnc.mesadmin.dto.GetAllMaSTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：物料库存信息DTO返回业务数据实体类Res
 * 创建人：刘福志
 * 创建时间：2017-6-16
 * 修改人：
 * 修改时间：
 */
public class GetAllMaSTInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllMaSTInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllMaSTInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllMaSTInfoResB body) {
        Body = body;
    }
}
