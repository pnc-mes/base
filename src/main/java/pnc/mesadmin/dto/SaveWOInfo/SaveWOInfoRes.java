package pnc.mesadmin.dto.SaveWOInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存工单信息DTO返回业务数据实体类Res
 * 创建人：张亮亮
 * 创建时间：2017-06-09
 * 修改人：
 * 修改时间：
 */
public class SaveWOInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveWOInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveWOInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveWOInfoResB body) {
        Body = body;
    }
}
