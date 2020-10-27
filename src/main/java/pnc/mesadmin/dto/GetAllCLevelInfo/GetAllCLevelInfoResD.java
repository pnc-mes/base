package pnc.mesadmin.dto.GetAllCLevelInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：紧急度代码信息列表DTO返回业务实体类data
 * 创建人：刘福志
 * 创建时间：2017/8/17
 * 修改人：
 * 修改时间：
 *
 */
public class GetAllCLevelInfoResD implements Serializable {

    @JsonProperty("CLevelRd")
    private int CLevelRd;

    @JsonProperty("CheckLevelCode")
    private String CheckLevelCode;

    @JsonProperty("CheckLevelName")
    private String CheckLevelName;
    @JsonIgnore
    public int getCLevelRd() {
        return CLevelRd;
    }
    @JsonIgnore
    public void setCLevelRd(int CLevelRd) {
        this.CLevelRd = CLevelRd;
    }
    @JsonIgnore
    public String getCheckLevelCode() {
        return CheckLevelCode;
    }
    @JsonIgnore
    public void setCheckLevelCode(String checkLevelCode) {
        CheckLevelCode = checkLevelCode;
    }
    @JsonIgnore
    public String getCheckLevelName() {
        return CheckLevelName;
    }
    @JsonIgnore
    public void setCheckLevelName(String checkLevelName) {
        CheckLevelName = checkLevelName;
    }
}
