package pnc.mesadmin.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.BadInfo;
import pnc.mesadmin.entity.DCInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：导电锌板信息DAO
 * 创建人：潘俊峰
 * 创建时间：2018-09-11
 * 修改人：
 * 修改时间：
 */
public interface BadDAO  extends BaseMapper<BadInfo> {

    /**
     * 根据CZPCode查询导电锌板信息  (By-pjf)
     *
     * @param
     * @return
     */
    List<BadInfo> SelectAllBadInfo();

    BadInfo SelectBadInfoByBadRd(Integer badRd);

    BadInfo SelectBadInfoByBadGd(String badGd);

    List<BadInfo> SelectBadInfoByBadTypeName(String badTypeName);


    int InsertBadInfo(BadInfo model);

    int DelBadInfoByRuid(Integer ruid);

    int UpdateBadInfoByRuid(BadInfo model);
}
