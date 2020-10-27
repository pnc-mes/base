package pnc.mesadmin.dto.GetBatchInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：获取批次信息请求实体
 * 创建人：王怀龙
 * 创建时间：2017-06-08
 * 修改人：
 * 修改时间：
 */
public class GetBatchInfoReqBD00 implements Serializable{
     @JsonProperty("BRd")
    private String BRd;
    @JsonIgnore
    public String getBRd() {
        return BRd;
    }
    @JsonIgnore
    public void setBRd(String BRd) {
        this.BRd = BRd;
    }
}
