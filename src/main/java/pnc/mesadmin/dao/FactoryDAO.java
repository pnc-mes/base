package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.FactoryInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工厂信息Dao层接口
 * 创建人：张亮亮
 * 创建时间：2017-5-27
 * 修改人：
 * 修改时间：
 */
public interface FactoryDAO extends BaseMapper<FactoryInfo> {
    //查询工厂信息
    List<FactoryInfo> SelectAllFactoryInfo();

    //根据ruid查询工厂信息
    FactoryInfo SelectFactoryInfoByruid(int argruid);

    //根据guid查询工厂信息
    FactoryInfo SelectFactoryInfoByguid(String argguid);

    //增加工厂信息
    int InsertFactoryInfo(FactoryInfo argFactoryInfo);

    //删除工厂信息根据工厂id
    int DeleteFactoryInfoByruid(int argruid);

    //更新工厂信息
    int UpdateFactoryInfoByruid(FactoryInfo argFactoryInfo);

    FactoryInfo SelectFactoryInfoByFactoryName(String argFactoryName);
}
