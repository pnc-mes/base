package pnc.mesadmin.dto.ExportPDCInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：导出盘点单差异信息DTO返回业务数据实体类Res
 * 创建人：王怀龙
 * 创建时间：2017-9-18
 * 修改人：
 * 修改时间：
 */
public class ExportPDCInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private ExportPDCInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public ExportPDCInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(ExportPDCInfoResB body) {
        Body = body;
    }
}
