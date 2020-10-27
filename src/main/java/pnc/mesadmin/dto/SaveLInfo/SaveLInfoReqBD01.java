package pnc.mesadmin.dto.SaveLInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存库位信息DTO请求业务数据实体类BD01：删除
 * 创建人：刘福志
 * 创建时间：2017-6-12
 * 修改人：
 * 修改时间：
 */
public class SaveLInfoReqBD01 implements Serializable{

    @JsonProperty("LRd")
    private int LRd;

    @JsonIgnore
    public int getLRd() {
        return LRd;
    }

    @JsonIgnore
    public void setLRd(int LRd) {
        this.LRd = LRd;
    }
}
