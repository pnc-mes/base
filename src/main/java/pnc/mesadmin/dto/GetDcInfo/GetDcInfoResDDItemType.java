package pnc.mesadmin.dto.GetDcInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：数据采集信息DTO返回业务实体类DItemType
 * 创建人：赵超
 * 创建时间：2017-5-24
 * 修改人：
 * 修改时间：
 */
public class GetDcInfoResDDItemType implements Serializable{

    @JsonProperty("TypeText")
    private String TypeText;

    @JsonProperty("TypeVal")
    private String TypeVal;

    @JsonIgnore
    public String getTypeText() {
        return TypeText;
    }

    @JsonIgnore
    public void setTypeText(String typeText) {
        TypeText = typeText;
    }

    @JsonIgnore
    public String getTypeVal() {
        return TypeVal;
    }

    @JsonIgnore
    public void setTypeVal(String typeVal) {
        TypeVal = typeVal;
    }

}
