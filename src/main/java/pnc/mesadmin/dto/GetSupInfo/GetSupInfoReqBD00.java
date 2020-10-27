package pnc.mesadmin.dto.GetSupInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：供应商信息DTO返回业务数据实体类BD00
 * 创建人：赵超
 * 创建时间：2017-5-23
 * 修改人：
 * 修改时间：
 */

public class GetSupInfoReqBD00 implements Serializable{

    @JsonProperty("SupRd")
    private int SupRd;

    @JsonIgnore
    public int getSupRd() {
        return SupRd;
    }

    @JsonIgnore
    public void setSupRd(int supRd) {
        SupRd = supRd;
    }
}
