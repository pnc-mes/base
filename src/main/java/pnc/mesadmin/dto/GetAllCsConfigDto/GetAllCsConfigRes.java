package pnc.mesadmin.dto.GetAllCsConfigDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @program: mesadmin
 * @description: 通用查询DTo
 * @author: yin.yang
 * @create: 2018-11-19
 **/
public class GetAllCsConfigRes {
    @JsonProperty("CSConfigRd")
    private Integer CSConfigRd;
    @JsonProperty("ConfigName")
    private String ConfigName;

    @JsonIgnore
    public Integer getCSConfigRd() {
        return CSConfigRd;
    }

    @JsonIgnore
    public void setCSConfigRd(Integer CSConfigRd) {
        this.CSConfigRd = CSConfigRd;
    }

    @JsonIgnore
    public String getConfigName() {
        return ConfigName;
    }

    @JsonIgnore
    public void setConfigName(String configName) {
        ConfigName = configName;
    }
}
