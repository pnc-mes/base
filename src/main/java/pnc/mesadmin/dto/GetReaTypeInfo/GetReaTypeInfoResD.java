package pnc.mesadmin.dto.GetReaTypeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：原因代码DTO返回业务数据实体类data
 * 创建人：张亮亮
 * 创建时间：2017-5-31
 * 修改人：
 * 修改时间：
 */
public class GetReaTypeInfoResD implements Serializable{

    @JsonProperty("ReaType")
    private String ReaType;

    @JsonProperty("ReaTDes")
    private String ReaTDes;

    @JsonIgnore
    public String getReaType() {
        return ReaType;
    }

    @JsonIgnore
    public void setReaType(String reaType) {
        ReaType = reaType;
    }

    @JsonIgnore
    public String getReaTDes() {
        return ReaTDes;
    }

    @JsonIgnore
    public void setReaTDes(String reaTDes) {
        ReaTDes = reaTDes;
    }

}
