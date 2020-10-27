package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.DCInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：数据采集信息DAO
 * 创建人：赵超
 * 创建时间：2017-5-24
 * 修改人：
 * 修改时间：
 */
public interface DCDAO extends BaseMapper<DCInfo> {
    /**
     * 查询数据采集列表
     */
    public List<DCInfo> selectDCInfos();
    /**
     * 根据dcRd查询数据采集
     */
    DCInfo selectDCInfoByDcRd(int dcRd);
    /**
     * 根据guid查询数据采集
     */
    DCInfo selectByGuid(String guid);
    /**
     * 查询数据采集名称
     */
    DCInfo SelectDcName(String dcName);
    /**
     * 新增保存数据采集信息根节点
     */
    int insertDCInfo(DCInfo objDCInfo);
    /**
     * 根据guid删除数据采集
     */
    int deleteDCInfoByGuid(String guid);

    /**
     * 更新DCInfo
     */
    int updateDCInfo(DCInfo objDCInfo);
}
