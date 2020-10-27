package pnc.mesadmin.dao;

import pnc.mesadmin.entity.OptInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：操作信息DAO
 * 创建人：潘俊峰
 * 创建时间：2017-05-17
 * 修改人：
 * 修改时间：
 */
public interface OptDAO {
    List<OptInfo> SelectAllOptInfo();

    OptInfo SelectOptInfo(String guid);

    OptInfo SelectOptInfoRuid(int ruid);

    /**
     * 资源下面有的操作
     * @param argResGd
     * @return
     */
    List<OptInfo> SelectOptByResOpt(String argResGd);
}
