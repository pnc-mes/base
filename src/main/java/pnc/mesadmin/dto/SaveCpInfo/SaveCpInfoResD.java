package pnc.mesadmin.dto.SaveCpInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存企业信息DTO返回业务数据实体类Data
 * 创建人：张亮亮
 * 创建时间：2017-5-26
 * 修改人：
 * 修改时间：
 */
public class SaveCpInfoResD {
    @JsonProperty("CpRd")
    private int CpRd;

    @JsonProperty("CpCode")
    private String CpCode;

    @JsonIgnore
    public int getCpRd() {
        return CpRd;
    }

    @JsonIgnore
    public void setCpRd(int cpRd) {
        CpRd = cpRd;
    }

    @JsonIgnore
    public String getCpCode() {
        return CpCode;
    }

    @JsonIgnore
    public void setCpCode(String cpCode) {
        CpCode = cpCode;
    }
}
