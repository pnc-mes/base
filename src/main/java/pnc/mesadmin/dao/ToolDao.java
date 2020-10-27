package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.ToolInfo;

import java.util.List;


/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工具信息Dao层接口
 * 创建人：郝赞
 * 创建时间：2018-6-12
 * 修改人：
 * 修改时间：
 */
public interface ToolDao extends BaseMapper<ToolInfo> {

    //  获取工具列表信息
    List<ToolInfo> SelectAllToolInfo();

    //根据guid查询工具信息
    ToolInfo SelectToolInfoByruid(int argruid);

    //增加工具信息
    int InsertToolInfo(ToolInfo argToolinfo);

    //根据guid查询工具信息
    ToolInfo SelectToolInfoByguid(String argguid);

    //更新工具信息
    int UpdateToolInfoByruid(ToolInfo argToolInfo);

    //根据工具名称查询工具信息
    ToolInfo SelectToolInfoByFactoryName(String argToolName);

    //删除工具信息根据工具id
    int DeleteToolInfoByruid(int argruid);

    List<ToolInfo> SelectAllByVenSn(String venSn);


}
