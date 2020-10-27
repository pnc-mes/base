package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.Lineinfo;

import java.util.List;


/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：线体信息Dao层接口
 * 创建人：郝赞
 * 创建时间：2018-6-7
 * 修改人：
 * 修改时间：
 */
public interface LineDao extends BaseMapper<Lineinfo> {

    //  获取线体列表信息
    List<Lineinfo> SelectAllLineInfo();

    //根据guid查询线体信息
    Lineinfo SelectLineInfoBygruid(int argruid);

    //增加线体信息
    int InsertLineInfo(Lineinfo argLineinfo);

    //根据guid查询线体信息
    Lineinfo SelectLineInfoByguid(String argguid);

    //更新线体信息
    int UpdateLineInfoByruid(Lineinfo argLineinfo);

    //根据线体名称查询线体信息
    Lineinfo SelectLineInfoByLineNameName(String argLineName);

    //删除线体信息根据线体id
    int DeleteLineInfoByruid(int argruid);

    Lineinfo SelectLineInfoByLineCode(String lineCode);
}
