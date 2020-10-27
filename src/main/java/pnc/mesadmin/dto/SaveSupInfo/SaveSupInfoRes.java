package pnc.mesadmin.dto.SaveSupInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存供应商信息DTO返回业务数据实体类res
 * 创建人：刘福志
 * 创建时间：2017-5-24
 * 修改人：
 * 修改时间：
 */
public class SaveSupInfoRes implements Serializable {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveSupInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveSupInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveSupInfoResB body) {
        Body = body;
    }
}
