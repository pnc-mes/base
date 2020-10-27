package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.CompanyInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：企业信息Dao层接口
 * 创建人：张亮亮
 * 创建时间：2017-5-26
 * 修改人：
 * 修改时间：
 */
public interface CompanyDAO extends BaseMapper<CompanyInfo> {
    //查询企业信息列表
    List<CompanyInfo> SelectAllCompanyInfo();

    //根据ruid查询企业信息
    CompanyInfo SelectCompanyInfoByRuid(int argRuid);

    //增加企业信息
    int InsertCompanyInfo(CompanyInfo argCompanyInfo);

    //删除企业信息
    int DeleteCompanyInfoByruid(Integer argRuid);

    //更新企业信息
    int UpdateConmpanyInfoByruid(CompanyInfo argCompanyInfo);

     //根据guid查询企业信息，此查询用在工厂信息里面，根据工厂里面的企业id查询所属企业
     CompanyInfo  SelectCompanyInfoByGuid(String guid);

     CompanyInfo SelectCompanyInfoByCompanyName(String argCompanyName);

     CompanyInfo SelectCompanyInfoBycompanyCode(String companyCode);
}
