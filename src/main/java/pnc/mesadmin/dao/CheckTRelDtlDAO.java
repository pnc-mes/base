package pnc.mesadmin.dao;

import pnc.mesadmin.entity.CheckTRelDtlInfo;

import java.util.List;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-03-19
 **/
public interface CheckTRelDtlDAO {
    List<CheckTRelDtlInfo> SelectAllByRelGd(String relGd);

    CheckTRelDtlInfo SelectByRuid(Integer ruid);

    CheckTRelDtlInfo SelectByGuid(String guid);

    int Insert(CheckTRelDtlInfo model);

    int DeleteByRelGd(String relGd);
}
