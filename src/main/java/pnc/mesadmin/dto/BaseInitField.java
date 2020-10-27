package pnc.mesadmin.dto;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：查询通用实体类
 * 创建人：pjf
 * 创建时间：2020-09-08
 * 修改人：
 * 修改时间：
 */
public class BaseInitField implements Serializable{

    private String fieldName;

    private String fieldOpt;

    private String fieldVal;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldOpt() {
        return fieldOpt;
    }

    public void setFieldOpt(String fieldOpt) {
        this.fieldOpt = fieldOpt;
    }

    public String getFieldVal() {
        return fieldVal;
    }

    public void setFieldVal(String fieldVal) {
        this.fieldVal = fieldVal;
    }
}

