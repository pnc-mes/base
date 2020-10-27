package pnc.mesadmin.dto.SaveOpertInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存作业信息DTO请求业务数据实体类BD00：删除
 * 创建人：王怀龙
 * 创建时间：2017-06-02
 * 修改人：
 * 修改时间：
 */
public class SaveOpertInfoReqBD01 {
    @JsonProperty("OpertRd")
    private int OpertRd;

    @JsonIgnore
    public int getOpertRd() {
        return OpertRd;
    }
    @JsonIgnore
    public void setOpertRd(int opertRd) {
        OpertRd = opertRd;
    }
}
