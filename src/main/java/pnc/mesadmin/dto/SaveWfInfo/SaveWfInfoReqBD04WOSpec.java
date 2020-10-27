package pnc.mesadmin.dto.SaveWfInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/11.
 */
public class SaveWfInfoReqBD04WOSpec implements Serializable {

    @JsonProperty("OSpecVerRd")
    private int OSpecVerRd;

    @JsonProperty("Expression")
    private String Expression;

    @JsonIgnore
    public int getOSpecVerRd() {
        return OSpecVerRd;
    }

    @JsonIgnore
    public void setOSpecVerRd(int OSpecVerRd) {
        this.OSpecVerRd = OSpecVerRd;
    }

    @JsonIgnore
    public String getExpression() {
        return Expression;
    }

    @JsonIgnore
    public void setExpression(String expression) {
        Expression = expression;
    }
}
