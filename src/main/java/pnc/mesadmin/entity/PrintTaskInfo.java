package pnc.mesadmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：打印任务信息表实体类
 * 创建人：潘俊峰
 * 创建时间：2017-06-22
 * 修改人：
 * 修改时间：
 */
@TableName(value = "tpm_printtaskinfo")
public class PrintTaskInfo {
    @TableId(value = "ruid", type = IdType.AUTO)
    private int ruid;
    private String guid;
    private String pTCode; //打印任务单
    private String reCode; //关联单号
    private String materialCode; //物料代码
    private String materialName; //物料名称
    private String barCode; //条码
    private String barType; //条码类型 00#砧板 01#箱包 02#批次号 03#物料条码 04#wms批次号
    private String orderType; //单号类型 00#采购单 01#来料收货通知单 02#生产工单 03#盘点单 04#无源单 05#领料单 06#批次拆分
    private int printCount; //打印数量
    private int printCopy; //打印份数
    private String printerName; //打印机实例名
    private String printTGd; //打印模板Gd
    private String pTFileName; //打印模板文件名称
    private String printData; //打印参数
    private String creator;
    private Date createTime;
    private String lastModifyMan;
    private Date lastModifyTime;
    private String remark;

    public int getRuid() {
        return ruid;
    }

    public void setRuid(int ruid) {
        this.ruid = ruid;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getpTCode() {
        return pTCode;
    }

    public void setpTCode(String pTCode) {
        this.pTCode = pTCode;
    }

    public String getReCode() {
        return reCode;
    }

    public void setReCode(String reCode) {
        this.reCode = reCode;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public int getPrintCount() {
        return printCount;
    }

    public void setPrintCount(int printCount) {
        this.printCount = printCount;
    }

    public int getPrintCopy() {
        return printCopy;
    }

    public void setPrintCopy(int printCopy) {
        this.printCopy = printCopy;
    }

    public String getPrinterName() {
        return printerName;
    }

    public void setPrinterName(String printerName) {
        this.printerName = printerName;
    }

    public String getPrintTGd() {
        return printTGd;
    }

    public void setPrintTGd(String printTGd) {
        this.printTGd = printTGd;
    }

    public String getpTFileName() {
        return pTFileName;
    }

    public void setpTFileName(String pTFileName) {
        this.pTFileName = pTFileName;
    }

    public String getPrintData() {
        return printData;
    }

    public void setPrintData(String printData) {
        this.printData = printData;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastModifyMan() {
        return lastModifyMan;
    }

    public void setLastModifyMan(String lastModifyMan) {
        this.lastModifyMan = lastModifyMan;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBarType() {
        return barType;
    }

    public void setBarType(String barType) {
        this.barType = barType;
    }
}
