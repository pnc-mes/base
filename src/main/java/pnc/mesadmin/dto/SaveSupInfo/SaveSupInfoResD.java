package pnc.mesadmin.dto.SaveSupInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存供应商信息DTO返回业务数据实体类data
 * 创建人：刘福志
 * 创建时间：2017-5-23
 * 修改人：
 * 修改时间：
 */
public class SaveSupInfoResD {
    @JsonProperty("SupRd")
    private int SupRd;

    @JsonProperty("SupCode")
    private String SupCode;

    @JsonIgnore
    public int getSupRd() {
        return SupRd;
    }

    @JsonIgnore
    public void setSupRd(int supRd) {
        SupRd = supRd;
    }

    @JsonIgnore
    public String getSupCode() {
        return SupCode;
    }

    @JsonIgnore
    public void setSupCode(String supCode) {
        SupCode = supCode;
    }
}
