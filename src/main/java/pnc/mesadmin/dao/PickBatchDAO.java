package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.PickBatchInfo;

import java.util.List;

/**
 * Created by xfxi on 2017/9/18.
 */
public interface PickBatchDAO {
   //WHL
    int InsertPickBatch(PickBatchInfo batchInfo);

    //根据ID删除批次明细信息
   int DeletePickBatchInfo(int ruid);

    //根据关联单号+批次号查询 (pjf)
    PickBatchInfo SelectByAB(@Param("argAssCode") String argAssCode, @Param("argBatch") String argBatch);

    //根据工单号和类别查询领料批次信息 ZC
    List<PickBatchInfo> SelectPBByAssCodeAndAssSource(@Param("assCode") String assCode, @Param("assSource") String assSource);

    //根据批次号查询 (pjf)
    List<PickBatchInfo> SelectByBatch(String argBatch);

    //根据批次号查询领料批次明细信息  WHL
    PickBatchInfo SelectPickBatchInfoByBatch(String argBatch);

    //查询产线余料报表
    List<PickBatchInfo> SelectPickBatchInfoByBaoBiao(
            @Param("macode") String macode, @Param("maname") String maname, @Param("status") String status,
            @Param("createTime") String createTime, @Param("createTime1") String createTime1, @Param("wocode") String wocode, @Param("batch") String batch);

    /**
     * 根据关联单号+关联呢单号来源+物理版本GD查询
     * @param argAssCode
     * @param argMaVerGd
     * @return
     */
    List<PickBatchInfo> SelectByAssCodeMaVerGd(@Param("argAssCode") String argAssCode,
                                               @Param("argAssSource") String argAssSource,
                                               @Param("argMaVerGd") String argMaVerGd);

    //更新
    int UpdatePickBatchInfo(PickBatchInfo pickBatchInfo);

    /**
     * 根据批次删除领料明细 (pjf)
     * @param argBatch
     * @return
     */
    int DeleteByBatch(String argBatch);
}
