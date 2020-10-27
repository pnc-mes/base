package pnc.mesadmin.dto.SaveSNInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存工序管理DTO返回业务数据实体类Res
 * 创建人：张亮亮
 * 创建时间：2017-06-07
 * 修改人：
 * 修改时间：
 */
public class SaveSNInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveSNInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveSNInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveSNInfoResB body) {
        Body = body;
    }
}
