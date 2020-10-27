package pnc.mesadmin.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.WoInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工单DAO
 * 创建人：张亮亮
 * 创建时间：2017-06-08
 * 修改人：
 * 修改时间：
 */
public interface WODAO extends BaseMapper<WoInfo> {
    //查询工单列表信息 张亮亮
    List<WoInfo> SelectWoInfo();

    //查询工单信息 张亮亮
    WoInfo SelectWoInfoByruid(int argruid);

    //新增工单信息 张亮亮
    int InsertWoInfo(WoInfo argWoInfo);

    //删除工单信息 张亮亮
    int DeleteWoInfo(int argruid);

    //更新工单信息 张亮亮
    int UpdateWoInfo(WoInfo argWoInfo);

    //根据工单号查询工单信息 张亮亮
    WoInfo SelectWoInfoBywoCode(String argwoCode);

    //根据工单ID查询工单信息 王怀龙
    WoInfo SelectWoInfoByGuid(@Param("argGuid") String argGuid);

    /**
     * 根据工单代码模糊查询 pjf
     * @param argWoCode
     * @param argPageStart
     * @param argPageEnd
     * @return
     */
    List<WoInfo> SelectLikeWoCode(@Param("argWoCode") String argWoCode,
                                  @Param("argPageStart") int argPageStart,
                                  @Param("argPageEnd") int argPageEnd);

    /**
     * 根据工单代码模糊查询 pjf
     * @param argWoCode
     * @return
     */
    int SelectLikeWoCodeCount(@Param("argWoCode") String argWoCode);

    //查询工单列表信息 张亮亮
    List<WoInfo> SelectWoInfoByOrderCode(String OrderCode);

    //查询工单信息 张亮亮
    WoInfo SelectWoInfoByruidStatus(@Param("Ruid") int Ruid);


    //根据根据线体RD查询工单信息
    List<WoInfo> SelectAllWoInfoByLineRd(Integer LineRd);

    //查询所有工单号
    List<WoInfo> SeleAllWoCodeByWoTGd(@Param("woTGd") String woTGd);

}
