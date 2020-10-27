package pnc.mesadmin.dao;

import pnc.mesadmin.entity.CSSearchFieldsInfo;

import java.util.List;

/**
 * @program: mesadmin
 * @description: 查询设置条件字段信息表
 * @author: yin.yang
 * @create: 2018-12-21
 **/
public interface CSSearchFieldsInfoDAO {
    List<CSSearchFieldsInfo> SelectAll();

    //查询设置条件字段
    List<CSSearchFieldsInfo> SelectCSSearchFieldsInfo(String CSSearchGd);

    int InsertCSSearchFieldsInfo(CSSearchFieldsInfo model);
}
