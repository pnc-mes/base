package pnc.mesadmin.dao;

import pnc.mesadmin.entity.CSCTotalRelInfo;

import java.util.List;

/**
 * @program: mesadmin
 * @description: 通用查询配置与统计设置关系信息表
 * @author: yin.yang
 * @create: 2018-12-21
 **/
public interface CSCTotalRelInfoDAO {
    List<CSCTotalRelInfo> SelectAll();

    //配置与统计设置关系
    List<CSCTotalRelInfo> SelectCSCTotalRelInfo(String guid);

    //新增
    int InsertCSCTotalRelInfo(CSCTotalRelInfo model);

    //删除
    int DeleteAllByConfigGd(String configGd);
}
