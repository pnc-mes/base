package pnc.mesadmin.dto.GetCDataInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/3 10:36
 * @Description:
 */
public class GetCDataInfoReq00 implements Serializable {
    @JsonProperty("CusDataRd")
    private int CusDataRd;

    @JsonIgnore
    public int getCusDataRd() {
        return CusDataRd;
    }
    @JsonIgnore
    public void setCusDataRd(int cusDataRd) {
        CusDataRd = cusDataRd;
    }
}
