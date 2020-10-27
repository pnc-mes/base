package pnc.mesadmin.dto.GetLPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by test on 2017/10/18.
 */
public class GetLPInfoResD implements Serializable {

    @JsonProperty("LabelID")
    private String LabelID;

    @JsonProperty("LabelDes")
    private String LabelDes;

    @JsonIgnore
    public String getLabelID() {
        return LabelID;
    }
    @JsonIgnore
    public void setLabelID(String labelID) {
        LabelID = labelID;
    }
    @JsonIgnore
    public String getLabelDes() {
        return LabelDes;
    }
    @JsonIgnore
    public void setLabelDes(String labelDes) {
        LabelDes = labelDes;
    }
}
