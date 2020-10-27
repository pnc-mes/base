package pnc.mesadmin.dto.GetAllAppSetInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称:获取工单信息，根据工单ID
 * 创建人：张亮亮
 * 创建时间：2017-06-08
 * 修改人：
 * 修改时间：
 */
public class GetAllAppSetReq implements Serializable {
    @JsonProperty("AppSetID")
    private Integer AppSetID;
    @JsonProperty("AppName")
    private String AppName;
    @JsonProperty("AppLogo")
    private String AppLogo;

    @JsonIgnore
    public Integer getAppSetID() {
        return AppSetID;
    }

    @JsonIgnore
    public void setAppSetID(Integer appSetID) {
        AppSetID = appSetID;
    }

    @JsonIgnore
    public String getAppName() {
        return AppName;
    }

    @JsonIgnore
    public void setAppName(String appName) {
        AppName = appName;
    }

    @JsonIgnore
    public String getAppLogo() {
        return AppLogo;
    }

    @JsonIgnore
    public void setAppLogo(String appLogo) {
        AppLogo = appLogo;
    }
}
