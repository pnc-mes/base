package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.common.GradeInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/19 13:47
 * @Description:
 */
public interface GradeDAO {

    List<GradeInfo> selectAllGradeInfo();

    int  updateGradeInfo(GradeInfo gradeInfo);

    void insertGradeInfo(GradeInfo gradeInfo);

    GradeInfo selecGradeInfoByGradeNameAndType(@Param("gradeName") String gradeName, @Param("gradeType") String gradeType);

    GradeInfo selecGradeInfoByGuid(String guid);

    GradeInfo selecGradeInfoByRuid(int ruid);

    int deleteGradeInfo(int ruid);

}
