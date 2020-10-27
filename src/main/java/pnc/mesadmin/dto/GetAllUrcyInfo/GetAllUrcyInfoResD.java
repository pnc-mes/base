package pnc.mesadmin.dto.GetAllUrcyInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：紧急度代码信息列表DTO返回业务实体类data
 * 创建人：刘福志
 * 创建时间：2017/8/17
 * 修改人：
 * 修改时间：
 *
 */
public class GetAllUrcyInfoResD implements Serializable {

    @JsonProperty("UrcyRd")
    private int UrcyRd;

    @JsonProperty("UrcyCode")
    private String UrcyCode;

    @JsonProperty("UrcyDes")
    private String UrcyDes;

    @JsonIgnore
    public int getUrcyRd() {
        return UrcyRd;
    }

    @JsonIgnore
    public void setUrcyRd(int urcyRd) {
        UrcyRd = urcyRd;
    }

    @JsonIgnore
    public String getUrcyDes() {
        return UrcyDes;
    }

    @JsonIgnore
    public void setUrcyDes(String urcyDes) {
        UrcyDes = urcyDes;
    }

    @JsonIgnore
    public String getUrcyCode() {
        return UrcyCode;
    }

    @JsonIgnore
    public void setUrcyCode(String urcyCode) {
        UrcyCode = urcyCode;
    }
}
