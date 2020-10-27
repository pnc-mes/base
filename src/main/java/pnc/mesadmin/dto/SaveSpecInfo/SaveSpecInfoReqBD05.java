package pnc.mesadmin.dto.SaveSpecInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：删除工序版本
 * 创建人：张亮亮
 * 创建时间：2017-06-03
 * 修改人：
 * 修改时间：
 */
public class SaveSpecInfoReqBD05 implements Serializable{

    @JsonProperty("SpecVerRd")
    private int SpecVerRd;

    @JsonIgnore
    public int getSpecVerRd() {
        return SpecVerRd;
    }

    @JsonIgnore
    public void setSpecVerRd(int specVerRd) {
        SpecVerRd = specVerRd;
    }
}
