package pnc.mesadmin.dto.GetAllMaSDTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：物料库存明细信息DTO返回业务数据实体类data
 * 创建人：刘福志
 * 创建时间：2017-6-16
 * 修改人：
 * 修改时间：
 */
public class GetAllMaSDTInfoResD implements Serializable{

    @JsonProperty("MaBInfo")
    private List<GetAllMaSDTInfoResDMaBInfo> MaBInfo;

    @JsonProperty("BInfo")
    private List<GetAllMaSDTInfoResDBInfo> BInfo;

    @JsonIgnore
    public List<GetAllMaSDTInfoResDMaBInfo> getMaBInfo() {
        return MaBInfo;
    }

    @JsonIgnore
    public void setMaBInfo(List<GetAllMaSDTInfoResDMaBInfo> maBInfo) {
        MaBInfo = maBInfo;
    }

    @JsonIgnore
    public List<GetAllMaSDTInfoResDBInfo> getBInfo() {
        return BInfo;
    }

    @JsonIgnore
    public void setBInfo(List<GetAllMaSDTInfoResDBInfo> BInfo) {
        this.BInfo = BInfo;
    }
}
