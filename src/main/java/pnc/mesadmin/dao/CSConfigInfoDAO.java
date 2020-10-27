package pnc.mesadmin.dao;

import pnc.mesadmin.entity.CSConfigInfo;

import java.util.List;

/**
 * @program: mesadmin
 * @description: 通用查询配置信息
 * @author: yin.yang
 * @create: 2018-12-21
 **/
public interface CSConfigInfoDAO {

    List<CSConfigInfo> SelectAll();

    //根据Rd查询
    CSConfigInfo SelectCsConfigByRd(Integer ruid);

    //根据名称查询
    CSConfigInfo SelectCsconfigByName(String name);

    //新增
    int InsertCSConfigInfo(CSConfigInfo model);

    //新增
    int UpdateCsconfig(CSConfigInfo csConfigInfo);

    //删除
    int DelCsconfigByGuid(String guid);
}
