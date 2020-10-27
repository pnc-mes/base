package pnc.mesadmin.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：查询公共DTO
 * 创建人：赵超
 * 创建时间：2017-06-09
 * 修改人：
 * 修改时间：
 */
public class InitData implements Serializable{

    @JsonProperty("FiledList")
    private List<InitDataField> FiledList;

    @JsonIgnore
    public List<InitDataField> getFiledList() {
        return FiledList;
    }

    @JsonIgnore
    public void setFiledList(List<InitDataField> filedList) {
        this.FiledList = filedList;
    }
}

