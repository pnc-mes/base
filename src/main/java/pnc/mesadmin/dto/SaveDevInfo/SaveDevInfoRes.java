package pnc.mesadmin.dto.SaveDevInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存设备信息DTO返回业务数据实体类res
 * 创建人：刘福志
 * 创建时间：2017-5-23
 * 修改人：
 * 修改时间：
 */
public class SaveDevInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveDevInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveDevInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveDevInfoResB body) {
        Body = body;
    }
}
