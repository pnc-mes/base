package pnc.mesadmin.dto.GetFileVInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：文件版本信息DTO返回业务数据实体类data Status
 * 创建人：刘福志
 * 创建时间：2017-6-3
 * 修改人：
 * 修改时间：
 */
public class GetFileVInfoResDStatus implements Serializable{

    @JsonProperty("Code")
    private String Code;

    @JsonProperty("Des")
    private String Des;

    @JsonProperty("Checked")
    private String Checked;

    @JsonIgnore
    public String getCode() {
        return Code;
    }

    @JsonIgnore
    public void setCode(String code) {
        Code = code;
    }

    @JsonIgnore
    public String getDes() {
        return Des;
    }

    @JsonIgnore
    public void setDes(String des) {
        Des = des;
    }

    @JsonIgnore
    public String getChecked() {
        return Checked;
    }

    @JsonIgnore
    public void setChecked(String checked) {
        Checked = checked;
    }
}
