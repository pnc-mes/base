package pnc.mesadmin.entity.base;

/**
 * 数据库实体类父接口
 * Created by whl on 2018/3/6.
 */
public interface BaseModel {
    int getRuid();
    String getGuid();
    String getMaVerGd();
    float getNum();
    float getScanNum();
    String getUnitName();
    String getRemark();
}
