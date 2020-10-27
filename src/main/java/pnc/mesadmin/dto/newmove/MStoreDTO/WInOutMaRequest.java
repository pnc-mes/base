package pnc.mesadmin.dto.newmove.MStoreDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-03-27
 **/
public class WInOutMaRequest {
    //操作类型 00#生产入库 01#出库 02返工入库，03移动库存-出库 ，04移动库存-入库
    @JsonProperty("DirType")
    private String DirType;
    //仓库ID
    @JsonProperty("StoreRd")
    private Integer StoreRd;
    //库位ID
    @JsonProperty("LocationRd")
    private Integer LocationRd;
    //备注
    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("BarInfo")
    private List<BarInfo> BarInfo;

    public static class BarInfo {
        //条码类型 00#箱 01#组件
        @JsonProperty("BarType")
        private String BarType;
        //条码
        @JsonProperty("BarCode")
        private String BarCode;

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

    @JsonIgnore
    public String getDirType() {
        return DirType;
    }

    @JsonIgnore
    public void setDirType(String dirType) {
        DirType = dirType;
    }

    @JsonIgnore
    public Integer getStoreRd() {
        return StoreRd;
    }

    @JsonIgnore
    public void setStoreRd(Integer storeRd) {
        StoreRd = storeRd;
    }

    @JsonIgnore
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }

    @JsonIgnore
    public Integer getLocationRd() {
        return LocationRd;
    }

    @JsonIgnore
    public void setLocationRd(Integer locationRd) {
        LocationRd = locationRd;
    }

    @JsonIgnore
    public List<WInOutMaRequest.BarInfo> getBarInfo() {
        return BarInfo;
    }

    @JsonIgnore
    public void setBarInfo(List<WInOutMaRequest.BarInfo> barInfo) {
        BarInfo = barInfo;
    }
}
