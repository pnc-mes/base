package pnc.mesadmin.dto.GetWfSInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/9.
 */
public class GetWfSInfoResDCondition implements Serializable{

    @JsonProperty("WfCirRd")
    private int WfCirRd;

    @JsonProperty("Expression")
    private String Expression;

    @JsonProperty("RoutePath")
    private String RoutePath;

    @JsonProperty("RouteType")
    private String RouteType;

    @JsonIgnore
    public int getWfCirRd() {
        return WfCirRd;
    }

    @JsonIgnore
    public void setWfCirRd(int wfCirRd) {
        WfCirRd = wfCirRd;
    }

    @JsonIgnore
    public String getExpression() {
        return Expression;
    }

    @JsonIgnore
    public void setExpression(String expression) {
        Expression = expression;
    }

    @JsonIgnore
    public String getRoutePath() {
        return RoutePath;
    }

    @JsonIgnore
    public void setRoutePath(String routePath) {
        RoutePath = routePath;
    }

    @JsonIgnore
    public String getRouteType() {
        return RouteType;
    }

    @JsonIgnore
    public void setRouteType(String routeType) {
        RouteType = routeType;
    }
}

