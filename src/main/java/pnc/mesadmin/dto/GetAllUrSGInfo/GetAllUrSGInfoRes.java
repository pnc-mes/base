package pnc.mesadmin.dto.GetAllUrSGInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：技能培训记录信息列表DTO返回业务实体类Res
 * 创建人：刘福志
 * 创建时间：2017/8/28
 * 修改人：
 * 修改时间：
 *
 */
public class GetAllUrSGInfoRes implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllUrSGInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllUrSGInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllUrSGInfoResB body) {
        Body = body;
    }
}
