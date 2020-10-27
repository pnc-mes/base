package pnc.mesadmin.dao;

import pnc.mesadmin.entity.CSSearchDDInfo;

import java.util.List;
import java.util.Map;

/**
 * @program: mesadmin
 * @description: 查询设置条件下拉框设置信息表
 * @author: yin.yang
 * @create: 2018-12-21
 **/
public interface CSSearchDDInfoDAO {
    List<CSSearchDDInfo> SelectAll();

    //查询下拉框信息
    CSSearchDDInfo SelectCSSearchDDInfo(String CSSearchFieldGd);

    //根据sql 查询
    List<Map<String, String>> Select(String sql);

    //新增
    int InsertCSSearchDDInfo(CSSearchDDInfo model);
}
