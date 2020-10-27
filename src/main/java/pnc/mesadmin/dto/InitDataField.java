package pnc.mesadmin.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：查询公共DTO
 * 创建人：赵超
 * 创建时间：2017-06-09
 * 修改人：
 * 修改时间：
 */
public class InitDataField implements Serializable{

    @JsonProperty("FieldName")
    private String FieldName;

    @JsonProperty("FieldOpt")
    private String FieldOpt;

    @JsonProperty("FieldVal")
    private String FieldVal;

    @JsonIgnore
    public String getFieldName() {
        return FieldName;
    }

    @JsonIgnore
    public void setFieldName(String fieldName) {
        this.FieldName = fieldName;
    }

    @JsonIgnore
    public String getFieldOpt() {
        return FieldOpt;
    }

    @JsonIgnore
    public void setFieldOpt(String fieldOpt) {
        this.FieldOpt = fieldOpt;
    }

    @JsonIgnore
    public String getFieldVal() {
        return FieldVal;
    }

    @JsonIgnore
    public void setFieldVal(String fieldVal) {
        this.FieldVal = fieldVal;
    }
}

