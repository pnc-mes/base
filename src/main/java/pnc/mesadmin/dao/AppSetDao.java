package pnc.mesadmin.dao;

import pnc.mesadmin.entity.AppSetInfo;

public interface AppSetDao {
    AppSetInfo SelectOneByAppSetInfo(Integer ruid);

    int SevaAppSetInfo(AppSetInfo appSetInfo);
}
