package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.DCItemInfo;
import pnc.mesadmin.entity.WoInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：数据采集Item信息DAO
 * 创建人：赵超
 * 创建时间：2017-5-24
 * 修改人：
 * 修改时间：
 */
public interface DCItemDAO extends BaseMapper<DCItemInfo> {

    int insertDCItemInfo(DCItemInfo dcItemInfo);

    int deleteDCItemInfoByRuid(int ruid);
    /**
     * 查询数据采集信息默认版本的信息对应的列表，编辑时使用
     */
    List<DCItemInfo> selectDCItemInfosByDCVerGd(String dcVerGd);
    /**
     * 保存版本下面的items
     */
    int insertDCItemInfos(List<DCItemInfo> dcItemInfos);
    /**
     * 根据dcVerInfo的guid删除下面所有的Items
     */
    int deleteDCItemInfoByDCVerGd(String dcVerGd);
    /**
     * 根据ItemRd查询DCItemInfo
     */
    DCItemInfo selectDCItemInfoByItemRd(int itemRd);
    /**
     * 更新DCItemInfo
     */
    int updateDCItemInfo(DCItemInfo objDCItemInfo);

    /**
     * 根据sop物料+工序查询数据采集 (By-pjf)
     * @param argMaGd
     * @param argSpecGd
     * @return
     */
    List<DCItemInfo> SelectByMaGdSpecVerGd(@Param("argMaGd") String argMaGd, @Param("argSpecGd") String argSpecGd);
}
