package pnc.mesadmin.dao;

import pnc.mesadmin.entity.CSSearchInfo;

import java.util.List;

/**
 * @program: mesadmin
 * @description: 查询设置信息表
 * @author: yin.yang
 * @create: 2018-12-21
 **/
public interface CSSearchInfoDAO {
    List<CSSearchInfo> SelectAll();

    //查询设置信息
    CSSearchInfo SelectCSSearchInfo(String Guid);

    //新增
    int InsertCSSearchInfo(CSSearchInfo model);
}
