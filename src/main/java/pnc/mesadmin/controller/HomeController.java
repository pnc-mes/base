package pnc.mesadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：新UI管理控制器
 * 创建人：潘俊峰
 * 创建时间：2020-09-02
 * 修改人：
 * 修改时间：
 */
@Controller
@RequestMapping("/new")
public class HomeController {

    /**
     * 测试首页
     * @return
     */
    @RequestMapping("/home")
    public String home(){
        return "page/welcome-1";
    }

    /**
     * 导航起始页
     * @return
     */
    @GetMapping("/index")
    public String index(){
        return "page/index";
    }

    /**
     * 工厂管理
     * @return
     */
    @GetMapping("/factory")
    public String factory(){
        return "page/base/factory";
    }

    /**
     * 设备类型
     * @return
     */
    @GetMapping("/devicetype")
    public String devicetype(){
        return "page/devicetype";
    }

    /**
     * 工序管理
     * @return
     */
    @GetMapping("/spec")
    public String spec(){
        return "page/spec";
    }


    /**
     * 工作中心
     * @return
     */
    @GetMapping("/wcenter")
    public String wcenter(){
        return "page/wcenter";
    }

    /**
     * 计量单位管理
     * @return
     */
    @GetMapping("/unit")
    public String unit(){
        return "page/unit";
    }

    /**
     * 班别管理
     * @return
     */
    @GetMapping("/shift")
    public String shift(){
        return "page/base/shift";
    }
    /**
     * 工单类型
     * @return
     */
    @GetMapping("/wotype")
    public String wotype(){
        return "page/wotype";
    }

    /**
     * 线体管理
     * @return
     */
    @GetMapping("/line")
    public String line() {
        return "page/base/line";
    }

    /**
     * 设备管理
     * @return
     */
    @GetMapping("/device")
    public String device(){
        return "page/equipment/deviceinfo";
    }

    /**
     * 设备组
     * @return
     */
    @GetMapping("/deviceGroup")
    public String deviceGroup(){
        return "page/equipment/devicegroup";
    }

    /**
     * 物料类别管理
     * @return
     */
    @GetMapping("/materialType")
    public String materialType() {
        return "page/process/materialType";
    }


    /**
     * 物料管理
     * @return
     */
    @GetMapping("/material")
    public String material(){
        return "page/process/material";
    }

    /**
     * 无源批次创建
     * @return
     */
    @GetMapping("/nsBatch")
    public String nsBatch(){
        return "page/plan/nsbatch";
    }

    /**
     * 序号管理
     * @return
     */
    @GetMapping("/sn")
    public String sn() {
        return "page/base/sn";
    }

    /**
     * 产品批次创建
     * @return
     */
    @GetMapping("/woBatch")
    public String woBatch(){
        return "page/plan/woBatch";
    }

    /**
     * 工序操作
     * @return
     */
    @GetMapping("/specOperate")
    public String specOperate() {
        return "page/plan/specOperate";
    }

    /**
     * 装卸物料
     * @return
     */
    @GetMapping("/setup")
    public String setup(){
        return "page/operation/setup";
    }

    /**
     * 上下料日志查询
     * @return
     */
    @GetMapping("/udmLogs")
    public String udmLogs(){
        return "page/operation/udmateriallogs";
    }

    /**
     * 产线余料查询
     * @return
     */
    @GetMapping("/udmexecte")
    public String udmexecte(){
        return "page/operation/udmaterialexecute";
    }

    /**
     * 产线余料调拨
     * @return
     */
    @GetMapping("/udmaterialallot")
    public String udmaterialallot(){
        return "page/operation/udmaterialallot";
    }

    /**
     * BOM管理
     * @return
     */
    @GetMapping("/bom")
    public String bom(){
        return "page/process/bom";
    }

    /**
     * 工艺流程管理
     * @return
     */
    @GetMapping("/wf")
    public String wf(){
        return "page/process/workflow";
    }

    /**
     * 紧急度代码
     * @return
     */
    @GetMapping("/urgency")
    public String urgency(){
        return "page/urgency";
    }

    //工单管理
    @GetMapping("/wo")
    public String wo(){
        return "page/resource/woinfo";
    }

    //数据采集
    @GetMapping("/dc")
    public String dcinfo(){
        return "page/resource/dcinfo";
    }

    //在线追踪
    @GetMapping("/online")
    public String online(){
        return "page/resource/onlineinfo";
    }

    //生产工艺控制
    @GetMapping("/productcontroller")
    public String productcontroller(){
        return "page/resource/productControllerinfo";
    }

    //作业管理
    @GetMapping("/operateinfo")
    public String operateinfo(){
        return "page/resource/operateinfo";
    }

    //客户管理
    @GetMapping("/customer")
    public String customer(){
        return "page/base/customerinfo";
    }

    //供应商管理
    @GetMapping("/supplier")
    public String supplierinfo(){
        return "page/base/supplierinfo";
    }

    //企业管理
    @GetMapping("/company")
    public String companyinfo(){
        return "page/companyinfo";
    }

    //全局配置
    @GetMapping("/globalsetting")
    public String globalSetting(){
        return "page/resource/globalSetting";
    }

    //不良原因
    @GetMapping("/badreason")
    public String badreason(){
        return "page/resource/badreasoninfo";
    }

    //原因代码管理
    @GetMapping("/reasonCode")
    public String reasonCode(){
        return "page/resource/reasonCodeinfo";
    }

    //原因代码管理
    @GetMapping("/reasonCodeG")
    public String reasonCodeG(){
        return "page/resource/reasonCodeGinfo";
    }

    /**
     * 文件管理
     * @return
     */
    @GetMapping("/file")
    public String file(){
        return "page/process/file";
    }

    /**
     * 打印机管理
     * @return
     */
    @GetMapping("/printer")
    public String printer(){
        return "page/base/printer";
    }

    /**
     * 打印模板管理
     * @return
     */
    @GetMapping("/printT")
    public String printT(){
        return "page/base/printT";
    }


    /**
     * 打印记录查询管理
     * @return
     */
    @GetMapping("/printlog")
    public String HisPrint() { return "page/printlog"; }

    /**
     * 工具信息
     * @return
     */
    @GetMapping("/tool")
    public String tool(){
        return "page/toolinfo";
    }

    /**
     * 载具信息
     * @return
     */
    @GetMapping("/carrier")
    public String carrier(){
        return "page/carrierinfo";
    }

    /**
     * 冻结批次
     * @return
     */
    @GetMapping("/freezeBatch")
    public String freezeBatch() {
        return "page/plan/freezeBatch";
    }

    /**
     * 解冻批次
     * @return
     */
    @GetMapping("/unfreezeBatch")
    public String unfreezeBatch() {
        return "page/plan/unfreezeBatch";
    }

    /**
     * 冻结、解冻记录查询
     * @return
     */
    @GetMapping("/holdLog")
    public String holdLog(){
        return "page/plan/holdLog";
    }

    /**
     * 设备组
     * @return
     */
    @GetMapping("/fileGroup")
    public String fileGroup(){
        return "page/filegroup";
    }

    /**
     * 载具家族
     * @return
     */
    @GetMapping("/carrierFamily")
    public String carrierFamily(){
        return "page/carrierfamily";
    }

    /**
     * 工具家族
     * @return
     */
    @GetMapping("/toolFamily")
    public String toolFamily(){
        return "page/toolFamilyinfo";
    }

    /**
     * 原材料批次拆分
     * @return
     */
    @GetMapping("/splitbatch")
    public String splitbatch(){
        return "page/plan/splitbatch";
    }
}


