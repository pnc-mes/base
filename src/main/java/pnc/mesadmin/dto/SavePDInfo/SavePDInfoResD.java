package pnc.mesadmin.dto.SavePDInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存盘点单信息DTO返回业务数据实体类data
 * 创建人：刘福志
 * 创建时间：2017-6-10
 * 修改人：
 * 修改时间：
 */
public class SavePDInfoResD {
    @JsonProperty("PDRd")
    private int PDRd;

    @JsonProperty("PDCode")
    private String PDCode;

    @JsonIgnore
    public int getPDRd() {
        return PDRd;
    }

    @JsonIgnore
    public void setPDRd(int PDRd) {
        this.PDRd = PDRd;
    }

    @JsonIgnore
    public String getPDCode() {
        return PDCode;
    }

    @JsonIgnore
    public void setPDCode(String PDCode) {
        this.PDCode = PDCode;
    }
}
