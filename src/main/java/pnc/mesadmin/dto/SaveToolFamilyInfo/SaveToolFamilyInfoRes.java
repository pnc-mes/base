package pnc.mesadmin.dto.SaveToolFamilyInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存工具家族信息dto返回实体类Data
 * 创建人：郝赞
 * 创建时间：2018-6-15
 * 修改人：
 * 修改时间：
 */
public class SaveToolFamilyInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveToolFamilyInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveToolFamilyInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveToolFamilyInfoResB body) {
        Body = body;
    }
}
