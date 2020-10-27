package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.DevPMInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/13 10:54
 * @Description:
 */
public interface DevPMDAO {
    //关联的保养计划
    List<DevPMInfo> selectDevPMInfobyDevGd(String devGd);

    //新增
    void insertDevPMInfo(DevPMInfo devPMInfo);

    //删除
    int deleteDevPMInfo(int ruid);

    List<DevPMInfo> selectByDevGdPMType(@Param("devGd") String devGd,
                                        @Param("pMType") String pMType);

    List<DevPMInfo>  SelectByPMGd(String PMGd);

    int deleteByPMGd(String PMGd);

}
