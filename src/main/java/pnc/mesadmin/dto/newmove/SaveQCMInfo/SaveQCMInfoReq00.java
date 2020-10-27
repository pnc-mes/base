package pnc.mesadmin.dto.newmove.SaveQCMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2019/4/7.
 */
public class SaveQCMInfoReq00 implements Serializable {

    @JsonProperty("QCheckMaRd")
    private Integer QCheckMaRd;
    //00#合格 01#不合格
    @JsonProperty("FinalResult")
    private String FinalResult;
    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public Integer getQCheckMaRd() {
        return QCheckMaRd;
    }

    @JsonIgnore
    public void setQCheckMaRd(Integer QCheckMaRd) {
        this.QCheckMaRd = QCheckMaRd;
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
    public String getFinalResult() {
        return FinalResult;
    }

    @JsonIgnore
    public void setFinalResult(String finalResult) {
        FinalResult = finalResult;
    }
}
