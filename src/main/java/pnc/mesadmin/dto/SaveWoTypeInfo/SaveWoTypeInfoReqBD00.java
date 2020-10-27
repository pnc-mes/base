package pnc.mesadmin.dto.SaveWoTypeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：
 * 创建人：ZC
 * 创建时间：2017-06-09
 * 修改人：
 * 修改时间：
 */
public class SaveWoTypeInfoReqBD00 implements Serializable{

    @JsonProperty("WTName")
    private String  WTName;

    @JsonProperty("Remark")
    private String  Remark;

    @JsonIgnore
    public String getWTName() {
        return WTName;
    }
    @JsonIgnore
    public void setWTName(String WTName) {
        this.WTName = WTName;
    }
    @JsonIgnore
    public String getRemark() {
        return Remark;
    }
    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
}
