package pnc.mesadmin.dto.GetAllRKInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：    获取入库单列表
 * 创建人：张亮亮
 * 创建时间：2017-06-10
 * 修改人：
 * 修改时间：
 */
public class GetAllRKInfoReqBD00 implements Serializable{

    @JsonProperty("RkTCode")
    private String RkTCode;

    @JsonProperty("AssCode")
    private String AssCode;

    @JsonProperty("ExecTime")
    private String ExecTime;


    @JsonProperty("ExecTime1")
    private String ExecTime1;

    @JsonIgnore
    public String getRkTCode() {
        return RkTCode;
    }

    @JsonIgnore
    public void setRkTCode(String rkTCode) {
        RkTCode = rkTCode;
    }

    @JsonIgnore
    public String getAssCode() {
        return AssCode;
    }

    @JsonIgnore
    public void setAssCode(String assCode) {
        AssCode = assCode;
    }

    @JsonIgnore
    public String getExecTime() {
        return ExecTime;
    }

    @JsonIgnore
    public void setExecTime(String execTime) {
        ExecTime = execTime;
    }

    @JsonIgnore
    public String getExecTime1() {
        return ExecTime1;
    }

    @JsonIgnore
    public void setExecTime1(String execTime1) {
        ExecTime1 = execTime1;
    }
}
