package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.PdFamilyInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：产品家族信息DAO
 * 创建人：潘俊峰
 * 创建时间：2017-05-26
 * 修改人：
 * 修改时间：
 */
public interface PdFamilyDAO extends BaseMapper<PdFamilyInfo> {

    //新增产品家族信息 (By-pjf)
    int InsertPdFamilyInfo(PdFamilyInfo argPdFamilyInfo);

    //根据ruid删除产品家族信息 (By-pjf)
    int DeletePdFamilyInfo(int argRuid);

    //根据ruid修改产品家族信息 (By-pjf)
    int UpdatePdFamilyInfo(PdFamilyInfo argPdFamilyInfo);

    //获取所有产品家族信息 (By-pjf)
    List<PdFamilyInfo> SelectAllPdFamilyInfo();

    //根据ruid获取产品家族信息 (By-pjf)
    PdFamilyInfo SelectPdFamilyInfo(int argRuid);

    //根据guid获取产品家族信息 (By-pjf)
    PdFamilyInfo SelectByGuid(String argGuid);

    //查询产品家族名称--LFZ
    PdFamilyInfo SelectfamilyName(String familyName);
}
