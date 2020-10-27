package pnc.mesadmin.dto.GetCMBBInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：公共接口 请求获取批次信息
 * 创建人：张亮亮
 * 创建时间：2017-07-04
 * 修改人：
 * 修改时间：
 */
public class GetCMBBInfoReq00 implements Serializable
{
    @JsonProperty("Batch")
    private String Batch;

    @JsonIgnore
    public String getBatch() {
        return Batch;
    }

    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
    }
}
