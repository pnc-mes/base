package pnc.mesadmin.dto.GetCarrierFamilyInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：获取载具家族信息DTO返回实体类Res
 * 创建人：郝赞
 * 创建时间：2018-6-19
 * 修改人：
 * 修改时间：
 */
public class GetCarrierFamilyInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetCarrierFamilyInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetCarrierFamilyInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetCarrierFamilyInfoResB body) {
        Body = body;
    }
}
