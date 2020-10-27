package pnc.mesadmin.dto.SaveFileInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存文件信息DTO返回业务数据实体类res
 * 创建人：刘福志
 * 创建时间：2017-6-3
 * 修改人：
 * 修改时间：
 */
public class SaveFileInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveFileInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveFileInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveFileInfoResB body) {
        Body = body;
    }
}
