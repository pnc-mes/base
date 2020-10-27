package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.dto.GetHoldBatchDTO.GetHoldBatchReq;
import pnc.mesadmin.dto.GetHoldBatchDTO.GetHoldBatchResD;
import pnc.mesadmin.entity.BInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：批次信息DAO
 * 创建人：王怀龙
 * 创建时间：2017-06-08
 * 修改人：
 * 修改时间：
 */
public interface BDAO extends BaseMapper<BInfo> {
    int InsertBatch(BInfo bInfo);

    int DeleteBatchInfoByRuid(int argRuid);

    int UpdateBatchInfoByRuid(BInfo bInfo);

    //根据Guid更新批次
    int UpdateBatchInfoByGuid(BInfo bInfo);

  /*  int UpdateBatchStatusByRuid(BInfo bInfo);

    int UpdateByRuid(BInfo argBInfo);

    int UpdateByNum(BInfo argBInfo);*/

    List<BInfo> SelectAllBatchInfo();
//    BInfo selectBatchByRuid(int Ruid);

    //根据工单号查询批次信息 张亮亮
    List<BInfo> SelectBatchInfoBywoCode(String argwoCode);

    List<BInfo> SelectBatchByGuidAnd00(String argGuid);

    //根据批次的id查询批次信息 张亮亮
    BInfo SelectBatchInfoByruid(int argruid);

    /**
     * 通过Batch查询批次信息
     *
     * @param argBatch
     * @return
     */
    BInfo selectBatchInfoByBatch(String argBatch);

    //根据工单号查询批次  (By-pjf)
    List<BInfo> SelectByWoGd(String argWoGd);

    //根据工单号查询批次  (By-zll)
    List<BInfo> SelectByWoGdANDStatusANDWFStatus(String argWoGd);

    List<BInfo> SelectBatchBySupBatch(String argSupBatch);

    List<BInfo> SelectBatchInfoByBatchList(List<String> batchList);

    List<BInfo> Select(@Param("maVerGd") String maVerGd, @Param("batch") String batchStr);

    List<BInfo> Select2(@Param("maVerGd") String maVerGd, @Param("batch") String batchStr);

    List<BInfo> SelectBInfoBaoBiao(@Param("macode") String macode, @Param("maname") String maname,
                                   @Param("inStockStatus") String inStockStatus,
                                   @Param("createTime") String createTime, @Param("createTime1") String createTime1);

    BInfo SelectByWoSourceAndWoGdAndWoDtlGd(@Param("woSource") String woSource, @Param("woGd") String woGd, @Param("woDlGd") String woDlGd);

    //获取批次号（存储过程）
    Map<String, String> SelectByList(@Param("scriptName") String scriptName,
                                     @Param("fieldCode") String fieldCode,
                                     @Param("lineCode") String lineCode,
                                     @Param("serialRuleGd") String serialRuleGd,
                                     @Param("date") Date date);

    //查询工单线体关系表中线体标识信息
    List<String> SelectLineGd(String Batch);

    //根据输入参数【ProBatch】&& BType= 00  查询【5.1. 批次信息表(tpm_BInfo)】匹配字段【Batch】、【BType】
    BInfo SelectBInfo(@Param("Batch") String Batch, @Param("BType") String BType);

    //根据物料上机明细bath 查询批次信息
    Float SelectBInfoByModevBath(@Param("ExecGd") String ExecGd, @Param("moDevGd") String moDevGd);

    //根据物料工序明细bath 查询批次信息
    Float SelectBInfoByMospecBath(@Param("ExecGd") String ExecGd, @Param("moSpecGd") String moDevGd);

    BInfo SelectByWoRdStatus(@Param("woRd") int woRd);

    //获取档位信息（存储过程）
    Map<String, String> SelectByGear(@Param("scriptName") String scriptName,
                                     @Param("scriptIn") List<String> scriptIn,
                                     @Param("scriptOut") List<String> scriptOut);

    //删除工单下所有批次
    int DeleteByWoGd(@Param("woGd") String woGd,
                     @Param("woSource") String woSource);

    /**
     * 查询所有冻结的批次
     * @param page
     * @param model
     * @return
     */
    IPage<GetHoldBatchResD> selectHoldBatch(IPage page, @Param("model") GetHoldBatchReq model);

    //=======================批次定制操作=====================
    //因批次表数据量庞大，接口循环查询更新速度太慢

    /**
     * 生成请检单（请检验单冻结批次）更新类容 ：status（02）
     * 检验报告确定（解冻）更新类容 ：BeforeCheckQC,AfterCheckQC,Status(04)
     * 确认出库操作 更新类容 ：inStockStatus(01)
     * 无单入库操作 更新类容 ：inStockStatus(00)
     * 无单出库操作 更新类容 ：inStockStatus(01)
     *
     * @param batchs（批量组件） qCheckMaType(00-入库，01-出库) finalResult（是否合格）
     * @return int
     */
    int BaseUpdateByBinfo(@Param("batchs") List<String> batchs,
                          @Param("Status") String Status,
                          @Param("qCheckMaType") String qCheckMaType,
                          @Param("finalResult") String finalResult,
                          @Param("lastModifyMan") String lastModifyMan,
                          @Param("lastModifyTime") Date lastModifyTime,
                          @Param("remark") String remark,
                          @Param("inStockStatus") String inStockStatus);

    /**
     * 根据组件状态查询组件
     *
     * @param status
     * @return String 返回单个组件号
     */
    List<String> BaseSelectAllByBachts(@Param("batchs") List<String> batchs,
                                       @Param("status") String status);

    /**
     * 根据组件在库/离库状态查询组件
     *
     * @param inStockStatus
     * @return String 返回单个组件号
     */
    List<String> BaseSelectAllByBachtsAndInStockStatus(@Param("batchs") List<String> batchs,
                                                       @Param("inStockStatus") String inStockStatus);
}
