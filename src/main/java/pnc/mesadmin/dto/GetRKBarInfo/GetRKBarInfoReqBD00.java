package pnc.mesadmin.dto.GetRKBarInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：获取入库单条码信息
 * 创建人：张亮亮
 * 创建时间：2017-06-12
 * 修改人：
 * 修改时间：
 */
public class GetRKBarInfoReqBD00 implements Serializable{

    @JsonProperty("RkDtlRd")
    private int RkDtlRd;
    @JsonProperty("AssSource")
    private String AssSource;
    @JsonIgnore
    public String getAssSource() {
        return AssSource;
    }
    @JsonIgnore
    public void setAssSource(String assSource) {
        AssSource = assSource;
    }

    @JsonIgnore
    public int getRkDtlRd() {
        return RkDtlRd;
    }

    @JsonIgnore
    public void setRkDtlRd(int rkDtlRd) {
        RkDtlRd = rkDtlRd;
    }
}
