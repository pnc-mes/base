package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.SpecVerInfo;
import pnc.mesadmin.entity.UnitInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工序版本信息DAO
 * 创建人：张亮亮
 * 创建时间：2017-06-02
 * 修改人：
 * 修改时间：
 */
public interface SpecVerDAO extends BaseMapper<SpecVerInfo> {
    //查询工序版本信息
    List<SpecVerInfo> SelectSpecVerInfo();

    //根据specGd标识查询工序信息
    List<SpecVerInfo> SelectSpecVerInfoByspecGd(String argspecGd);

    //查询默认版本工序信息里的
    SpecVerInfo SelectSpecVerInfoByspecGd00(String argspecGd);

    //根据工序版本ID查询工序版本信息
    SpecVerInfo SelectSpecVerInfoByruid(int argruid);

    //根据版本查询工序版本信息
    SpecVerInfo SelectSpecVerInfoByVersion(@Param("version") String argversion, @Param("specGd") String argspecGd);

    //增加工序版本信息
    int InsertSpecVerInfo(SpecVerInfo argSpecVerInfo);

    //更新工序版本信息
    int  UpdateSpecVerInfo(SpecVerInfo argSpecVerInfo);

    //根据guid查询信息
    SpecVerInfo SelectSpecVerInfoByguid(String argguid);

    //删除工序版本信息
    int DeleteSpecVerInfo(int argruid);

    //删除工序版本信息 根据specGd
    int DeleteSpecVerInfoByspecGd(@Param("specGd") String argspecGd, @Param("guid") String argGuid);

    /**
     * 根据工序名称模糊查询 pjf
     * @param argSpecName
     * @param argPageStart
     * @param argPageEnd
     * @return
     */
    List<SpecVerInfo> SelectLikeName(@Param("argSpecName") String argSpecName,
                                     @Param("argPageStart") int argPageStart,
                                     @Param("argPageEnd") int argPageEnd);

    /**
     * 根据工序名称模糊查询 pjf
     * @param argSpecName
     * @return
     */
    int SelectLikeNameCount(@Param("argSpecName") String argSpecName);
}
