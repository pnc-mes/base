package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.SOPInfo;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工艺文件信息DAO
 * 创建人：刘福志
 * 创建时间：2017-6-1
 * 修改人：
 * 修改时间：
 */
public interface SOPDAO  extends BaseMapper<SOPInfo> {

    //查询工艺文件信息
    SOPInfo SelectBymaVerGd(String guid);

    //查询工艺文件信息
    SOPInfo SelectByruid(int ruid);

    //根据WFVerGd查询

    //保存工艺文件信息
    int InsertSOPInfo(SOPInfo sopInfo);

    //删除工艺文件信息
    int DeleteSOPInfo(int ruid);

    //更新工艺文件信息
    int UpdateSOPInfo(SOPInfo sopInfo);

    //查询工艺文件信息
    SOPInfo SelectBymaVerGdAndWfGd(SOPInfo sopInfo);
}
