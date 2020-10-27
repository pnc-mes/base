package pnc.mesadmin.dto.SavePickInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存领料单信息DTO返回业务数据实体类Res
 * 创建人：张亮亮
 * 创建时间：2017-8-9
 * 修改人：
 * 修改时间：
 */
public class SavePickInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SavePickInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SavePickInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SavePickInfoResB body) {
        Body = body;
    }
}
