package pnc.mesadmin.dto.GetDicLanTypeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：字典信息语言类别DTO返回业务数据实体类Data
 * 创建人：赵超
 * 创建时间：2017-5-24
 * 修改人：
 * 修改时间：
 */
public class GetDicLanTypeInfoResD implements Serializable{

    @JsonProperty("LanCode")
    private String LanCode;

    @JsonProperty("LanName")
    private String LanName;

    @JsonProperty("Count")
    private int Count;

    @JsonIgnore
    public String getLanCode() {
        return LanCode;
    }

    @JsonIgnore
    public void setLanCode(String lanCode) {
        LanCode = lanCode;
    }

    @JsonIgnore
    public String getLanName() {
        return LanName;
    }

    @JsonIgnore
    public void setLanName(String lanName) {
        LanName = lanName;
    }

    @JsonIgnore
    public int getCount() {
        return Count;
    }

    @JsonIgnore
    public void setCount(int count) {
        Count = count;
    }
}
