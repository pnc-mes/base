package pnc.mesadmin.dao;

import pnc.mesadmin.entity.CSConfigInfoV1;

import java.util.List;

/**
 * @program: mesadmin
 * @description: 通用查询 配置 信息DAO
 * @author: yin.yang
 * @create: 2018-11-19
 **/
public interface CSConfigDAOV1 {
    //查询所有
    List<CSConfigInfoV1> SelectCSConfigAll();

    //根据Rd查询
    CSConfigInfoV1 SelectCsConfigByRd(Integer ruid);

    //根据GDchaxun
    CSConfigInfoV1 SelectCsconfigByGD(String guid);

    //新增
    int InsertCsconfig(CSConfigInfoV1 csConfigInfo);

    //根据名称查询
    CSConfigInfoV1 SelectCsconfigByName(String name);

    //新增
    int UpdateCsconfig(CSConfigInfoV1 csConfigInfo);

    //删除
    int DelCsconfigByGuid(String guid);
}
