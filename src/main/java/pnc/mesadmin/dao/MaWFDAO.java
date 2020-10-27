package pnc.mesadmin.dao;

import pnc.mesadmin.entity.MaWFInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：物料工艺流程设置信息DAO
 * 创建人：潘俊峰
 * 创建时间：2018-09-03
 * 修改人：
 * 修改时间：
 */
public interface MaWFDAO {

    /**
     * 根据MaVerGd查询物料工艺流程设置信息  (By-pjf)
     * @return
     */
    List<MaWFInfo> SelectByMaVerGd(String argMaVerGd);

    int inseetAll(MaWFInfo maWFInfo);

    //删除工艺流程
    int deleteAll(String MaVerGd);
}
