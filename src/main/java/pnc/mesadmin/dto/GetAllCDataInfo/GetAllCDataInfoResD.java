package pnc.mesadmin.dto.GetAllCDataInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/3 10:12
 * @Description:
 */
public class GetAllCDataInfoResD  implements Serializable {
    @JsonProperty("CusDataRd")
    private int CusDataRd;

    @JsonProperty("CusDataName")
    private String CusDataName;

    @JsonIgnore
    public int getCusDataRd() {
        return CusDataRd;
    }
    @JsonIgnore
    public void setCusDataRd(int cusDataRd) {
        CusDataRd = cusDataRd;
    }
    @JsonIgnore
    public String getCusDataName() {
        return CusDataName;
    }
    @JsonIgnore
    public void setCusDataName(String cusDataName) {
        CusDataName = cusDataName;
    }
}
