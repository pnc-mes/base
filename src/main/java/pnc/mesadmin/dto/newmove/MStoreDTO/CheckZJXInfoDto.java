package pnc.mesadmin.dto.newmove.MStoreDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-03-26
 **/
public class CheckZJXInfoDto {
    //操作类型 00#入库 01#出库
    @JsonProperty("DirType")
    private String DirType;
    //条码类型 00#箱 01#组件
    @JsonProperty("BarType")
    private String BarType;
    //条码
    @JsonProperty("BarCode")
    private String BarCode;

    @JsonIgnore
    public String getDirType() {
        return DirType;
    }

    @JsonIgnore
    public void setDirType(String dirType) {
        DirType = dirType;
    }

    @JsonIgnore
    public String getBarType() {
        return BarType;
    }

    @JsonIgnore
    public void setBarType(String barType) {
        BarType = barType;
    }

    @JsonIgnore
    public String getBarCode() {
        return BarCode;
    }

    @JsonIgnore
    public void setBarCode(String barCode) {
        BarCode = barCode;
    }
}
