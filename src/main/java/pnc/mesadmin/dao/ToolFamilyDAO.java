package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.ToolFamilyInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工具家族信息DAO层
 * 创建人：HAOZAN
 * 创建时间：2018-6-13
 * 修改人：
 * 修改时间：
 */
public interface ToolFamilyDAO extends BaseMapper<ToolFamilyInfo> {

    //查询所有工具家族信息
    List<ToolFamilyInfo> selectToolFamilyAll();

    //根据标识查询工具家族信息
    ToolFamilyInfo selectToolFamily(String argGuid);

    //根据ID查询工具家族信息
    ToolFamilyInfo selectToolFamilyID(Integer argRuid);

    //根据工具家族名称查询工具信息
    ToolFamilyInfo SelectToolInfoByToolFaName(String argToolFamilyName);

    //增加工具家族信息
    int InsertToolFamilyInfo(ToolFamilyInfo argToolFamilyinfo);

    //更新工具家族信息
    int UpdateToolFamilyInfoByruid(ToolFamilyInfo argToolFamilyinfo);


    //删除工具家族信息根据工具id
    int DeleteToolFamilyInfoByruid(int argruid);

}
