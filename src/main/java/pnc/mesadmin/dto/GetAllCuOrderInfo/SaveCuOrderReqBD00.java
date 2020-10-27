package pnc.mesadmin.dto.GetAllCuOrderInfo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称:获取工单信息，根据工单ID
 * 创建人：张亮亮
 * 创建时间：2017-06-08
 * 修改人：
 * 修改时间：
 */
public class SaveCuOrderReqBD00 implements Serializable {
    @JsonProperty("CoRd")
    private Integer CoRd;
    @JsonProperty("OrderCode")
    private String OrderCode;
    @JsonProperty("CTRd")
    private Integer CTRd;
    @JsonProperty("MaVerRd")
    private Integer MaVerRd;
    @JsonProperty("Num")
    private Integer Num;
    @JsonProperty("UnitRd")
    private Integer UnitRd;
    @JsonProperty("CustomerRd")
    private Integer CustomerRd;
    @JsonProperty("Remark")
    private String Remark;

    public Integer getCoRd() {
        return CoRd;
    }

    public void setCoRd(Integer coRd) {
        CoRd = coRd;
    }

    public String getOrderCode() {
        return OrderCode;
    }

    public void setOrderCode(String orderCode) {
        OrderCode = orderCode;
    }

    public Integer getCTRd() {
        return CTRd;
    }

    public void setCTRd(Integer CTRd) {
        this.CTRd = CTRd;
    }

    public Integer getMaVerRd() {
        return MaVerRd;
    }

    public void setMaVerRd(Integer maVerRd) {
        MaVerRd = maVerRd;
    }

    public Integer getNum() {
        return Num;
    }

    public void setNum(Integer num) {
        Num = num;
    }

    public Integer getUnitRd() {
        return UnitRd;
    }

    public void setUnitRd(Integer unitRd) {
        UnitRd = unitRd;
    }

    public Integer getCustomerRd() {
        return CustomerRd;
    }

    public void setCustomerRd(Integer customerRd) {
        CustomerRd = customerRd;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
