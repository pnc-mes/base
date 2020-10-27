package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.ToolPMInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/13 17:50
 * @Description:
 */
public interface ToolPMDAO {
    List<ToolPMInfo> selectToolPMInfoByToolGd(String toolGd);

    void insertToolPMInfo(ToolPMInfo toolPMInfo);

    int deleteToolPMInfo(int ruid);

    List<ToolPMInfo> selectByToolGdPmType(@Param("toolGd") String toolGd,
                                          @Param("pMType") String pMType);

    List<ToolPMInfo> SelectByPMGd(String PMGd);

    int deleteByPMGd(String PMGd);
}
