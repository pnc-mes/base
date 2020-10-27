package pnc.mesadmin.dto.GetAllCarrierFamilyInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：获取载具家族信息DTO返回实体类Data
 * 创建人：郝赞
 * 创建时间：2018-6-19
 * 修改人：
 * 修改时间：
 */
public class GetAllCarrierFamilyInfoResD implements Serializable{

    @JsonProperty("CarrierFamilyRd")
    private int CarrierFamilyRd;

    @JsonProperty("CarrierFamilyName")
    private String CarrierFamilyName;

    @JsonIgnore
    public int getCarrierFamilyRd() {
        return CarrierFamilyRd;
    }

    @JsonIgnore
    public void setCarrierFamilyRd(int carrierFamilyRd) {
        CarrierFamilyRd = carrierFamilyRd;
    }

    @JsonIgnore
    public String getCarrierFamilyName() {
        return CarrierFamilyName;
    }

    @JsonIgnore
    public void setCarrierFamilyName(String carrierFamilyName) {
        CarrierFamilyName = carrierFamilyName;
    }
}
