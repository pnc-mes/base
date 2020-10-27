package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.PackSpPropertyInfo;

import java.util.List;

/**
 * Created by test on 2019/4/25.
 */
public interface PackSpPropertyDAO {
    // 查询包装属性
    List<PackSpPropertyInfo> SelectByPackSpGdAndType(@Param("packSpGd") String packSpGd,
                                                     @Param("type") String type);

    int InsertPackSpProperty(PackSpPropertyInfo packSpPropertyInfo);

    int DeleteByPackSpGd(String packSpGd);
}
