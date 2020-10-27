package pnc.mesadmin.dto.newmove.QCheckMaDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-03-26
 **/
public class QCheckMaInfoResponse {
    //请检单号
    @JsonProperty("QCheckMaCode")
    private String QCheckMaCode;

    @JsonIgnore
    public String getQCheckMaCode() {
        return QCheckMaCode;
    }

    @JsonIgnore
    public void setQCheckMaCode(String QCheckMaCode) {
        this.QCheckMaCode = QCheckMaCode;
    }
}
