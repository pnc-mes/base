package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.DCVerInfo;
import pnc.mesadmin.entity.OpertInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：数据采集DCVerInfo信息DAO
 * 创建人：赵超
 * 创建时间：2017-5-24
 * 修改人：
 * 修改时间：
 */
public interface DCVerDAO  {

    /**
     * 根据ruid查询DCVerInfo
     */
    DCVerInfo selectDCVerInfoByRuid(int ruid);

    DCVerInfo SelectDCVerInfoByVersionAndDCGd(String version, String dcGd);
    /**
     * 根据dcGd查询下面所有的版本
     */
    List<DCVerInfo> selectDCVerInfosByDcGd(String dcGd);
    /**
     * 根据dcGd查询DCVerInfo
     */
    DCVerInfo selectDCVerInfoByDcGd(String dcGd);
    /**
     * 根据Guid查询DCVerInfo
     * 王怀龙
     */
    DCVerInfo selectDCVerInfoByGuid(String argGuid);
    /**
     * 查询第一条记录的所有版本
     */
    List<DCVerInfo> selectTopDCVerInfosByDcGd(String dcGd);
    /**
     * 查询默认版本信息
     */
    DCVerInfo selectDefaultDCVerInfoByDcGd(String dcGd);
    /**
     * 查询所有版本信息
     */
    List<DCVerInfo> SelectAlldcverinfo();
    /**
     * 查询版本
     */
    DCVerInfo SelectVersion(@Param("dcGd") String dcGd, @Param("version") String version);
    /**
     * 保存默认版本信息
     */
    int insertDCVerInfo(DCVerInfo dcVerInfo);
    /**
     * 根据DCGd删除下面所有的版本
     */
    int deleteDCVerInfoByDCGd(String dcGd);
    /**
     * 把其他的默认版本先置空
     */
    int updateDCVerInfo(DCVerInfo defDCVerInfo);
    /**
     * 删除当前版本
     */
    int deleteDCVerInfoByGuid(String guid);
}
