package pnc.mesadmin.dto.GetAllWOInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import pnc.mesadmin.dto.BaseResD;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称:获取工单信息列表返回的Body
 * 创建人：张亮亮
 * 创建时间：2017-06-08
 * 修改人：
 * 修改时间：
 */
@Data
public class GetAllWOInfoResD extends BaseResD implements Serializable{
    private int woRd;
    private String woCode;
    private int materialRd;//
    private String materialName;//物料名称
    private String materialCode;//物料代码
    private String maverGd;//

    private int wotRd;
    private String woTName;//工单类型

    private int urcyRd;
    private String urcyDes;//紧急度

    private List<LineInfo> lineInfo;

    private int wfRd;
    private String wfName;//工艺流程

    private String jStartDate;//计划开始时间
    private String jFinishDate;//计划完成时间
    private float num;//开工数量
    private float finishNum;//完成数量
    private float unCBatNum;//未创批数量
    private String status;//工单状态
    private String statusText;//工单状态的中文
    private String unitName;
    private String bomCode;
    private String bomVer;

    @Data
    public static class LineInfo{
        private int lineRd;
        private String lineName;//线别名称
    }
}
