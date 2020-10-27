package pnc.mesadmin.dto.SaveSOPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存工艺文件信息DTO请求业务数据实体类BD01：删除
 * 创建人：刘福志
 * 创建时间：2017-6-1
 * 修改人：
 * 修改时间：
 */
public class SaveSOPInfoReqBD01 implements Serializable {

    @JsonProperty("MaVerRd")
    private int MaVerRd;
    @JsonProperty("WFVerRd")
    private int WFVerRd;

    @JsonIgnore
    public int getWFVerRd() {
        return WFVerRd;
    }

    @JsonIgnore
    public void setWFVerRd(int WFVerRd) {
        this.WFVerRd = WFVerRd;
    }

    @JsonIgnore
    public int getMaVerRd() {
        return MaVerRd;
    }

    @JsonIgnore
    public void setMaVerRd(int MaVerRd) {
        this.MaVerRd = MaVerRd;
    }
}
