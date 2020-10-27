package pnc.mesadmin.dto.GetAllBadInfoDto;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Description: mesadmin
 * Created By panjunfeng on 2018/11/2
 */
public class GetAllBadReq {
    @JsonProperty("ExecType")
    private String ExecType;

    @JsonProperty("BusData")
    private GetAllBadInfoRes BusData;

    @JsonProperty("Paging")
    private String Paging;

    @JsonIgnore
    public String getExecType() {
        return ExecType;
    }

    @JsonIgnore
    public void setExecType(String execType) {
        ExecType = execType;
    }

    @JsonIgnore
    public GetAllBadInfoRes getBusData() {
        return BusData;
    }

    @JsonIgnore
    public void setBusData(GetAllBadInfoRes busData) {
        BusData = busData;
    }

    @JsonIgnore
    public String getPaging() {
        return Paging;
    }

    @JsonIgnore
    public void setPaging(String paging) {
        Paging = paging;
    }
}
