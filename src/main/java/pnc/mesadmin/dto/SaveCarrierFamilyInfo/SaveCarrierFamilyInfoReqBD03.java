package pnc.mesadmin.dto.SaveCarrierFamilyInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存载具家族信息dto返回实体类Data
 * 创建人：郝赞
 * 创建时间：2018-6-19
 * 修改人：
 * 修改时间：
 */
public class SaveCarrierFamilyInfoReqBD03 implements Serializable{

    @JsonProperty("CarrierFamilyRd")
    private int CarrierFamilyRd;

    @JsonIgnore
    public int getCarrierFamilyRd() {
        return CarrierFamilyRd;
    }

    @JsonIgnore
    public void setCarrierFamilyRd(int carrierFamilyRd) {
        CarrierFamilyRd = carrierFamilyRd;
    }
}
