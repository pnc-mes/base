package pnc.mesadmin.dto.SaveWfInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/11.
 */
public class SaveWfInfoReqBD04WRSpec implements Serializable {

    @JsonProperty("RSpecVerRd")
    private int RSpecVerRd;

    @JsonProperty("Expression")
    private String Expression;

    @JsonIgnore
    public int getRSpecVerRd() {
        return RSpecVerRd;
    }

    @JsonIgnore
    public void setRSpecVerRd(int RSpecVerRd) {
        this.RSpecVerRd = RSpecVerRd;
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
