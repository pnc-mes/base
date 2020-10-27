package pnc.mesadmin.dto.GetAllBLInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：批次等级列表信息DTO返回业务实体类data
 * 创建人：刘福志
 * 创建时间：2017/5/26
 * 修改人：
 * 修改时间：
 *
 */
public class GetAllBLInfoResD implements Serializable{

    @JsonProperty("BLRd")
    private int BLRd;

    @JsonProperty("BLName")
    private String BLName;

    @JsonIgnore
    public int getBLRd() {
        return BLRd;
    }

    @JsonIgnore
    public void setBLRd(int BLRd) {
        this.BLRd = BLRd;
    }

    @JsonIgnore
    public String getBLName() {
        return BLName;
    }

    @JsonIgnore
    public void setBLName(String BLName) {
        this.BLName = BLName;
    }
}
