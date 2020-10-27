package pnc.mesadmin.dto.GetAllUnitInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称:计量单位列表返回的Data
 * 创建人：张亮亮
 * 创建时间：2017-05-31
 * 修改人：
 * 修改时间：
 */
public class GetAllUnitInfoResD implements Serializable{

    @JsonProperty("UnitRd")
    private int UnitRd;

    @JsonProperty("UnitName")
    private String UnitName;

    @JsonIgnore
    public int getUnitRd() {
        return UnitRd;
    }

    @JsonIgnore
    public void setUnitRd(int unitRd) {
        UnitRd = unitRd;
    }

    @JsonIgnore
    public String getUnitName() {
        return UnitName;
    }

    @JsonIgnore
    public void setUnitName(String unitName) {
        UnitName = unitName;
    }
}
