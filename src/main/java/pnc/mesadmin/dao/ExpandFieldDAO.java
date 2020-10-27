package pnc.mesadmin.dao;

import pnc.mesadmin.entity.ExpandFieldInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/11 17:58
 * @Description:
 */
public interface ExpandFieldDAO {
    List<ExpandFieldInfo> SelectExpandFieldInfoByExpandType(String expandType);
}
