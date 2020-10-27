package pnc.mesadmin.dto.GetAllPrInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：打印机信息列表DTO返回业务实体类Res
 * 创建人：刘福志
 * 创建时间：2017/5/27
 * 修改人：
 * 修改时间：
 *
 */
public class GetAllPrInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllPrInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllPrInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllPrInfoResB body) {
        Body = body;
    }
}
