package pnc.mesadmin.dto.SaveUrcyInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存紧急度代码信息DTO请求业务数据实体类BD00：新增
 * 创建人：刘福志
 * 创建时间：2017-8-17
 * 修改人：
 * 修改时间：
 */
public class SaveUrcyInfoReqBD00 implements Serializable {

    @JsonProperty("UrcyCode")
    private String UrcyCode;

    @JsonProperty("UrcyDes")
    private String UrcyDes;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public String getUrcyCode() {
        return UrcyCode;
    }

    @JsonIgnore
    public void setUrcyCode(String urcyCode) {
        UrcyCode = urcyCode;
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
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
}
