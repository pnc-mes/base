package pnc.mesadmin.dao;

import pnc.mesadmin.entity.DicInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：字典信息DAO
 * 创建人：赵超
 * 创建时间：2017-5-24
 * 修改人：
 * 修改时间：
 */
public interface DicDAO {

    int getDicCountByLanguage(String language);

    List<String> getLanguageList();

    List<DicInfo> getDicInfoListByLanCode(String lanCode);

    DicInfo getDicInfoByGuid(String dicRd);

    int saveDicInfo(DicInfo dicInfo);

    int deleteDicInfoByRuid(int dicRd);

    int update(DicInfo dicInfo);
}
