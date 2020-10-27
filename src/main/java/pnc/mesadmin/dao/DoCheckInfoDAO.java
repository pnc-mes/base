package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.DoCheckInfo;
import pnc.mesadmin.entity.common.DJSelectInfo;

import java.util.List;


public interface DoCheckInfoDAO {
    List<DoCheckInfo> SelectDoCheckInfoBy(DoCheckInfo model);

    int InsertDoCheckInfo(DoCheckInfo model);

    List<DoCheckInfo> SelectDoCheckInfosByCheckObjTypeandCheckObjGdandStatus(@Param("CheckObjType") String CheckObjType, @Param("CheckObjGd") String CheckObjGd);



    List<DoCheckInfo> SelectDoCheckInfosByCheckTaskGd(String CheckTaskGd);

    //查询点检查询
    List<DJSelectInfo> SelectAllDJSelectInfo(@Param("checkobjtype") String checkobjtype, @Param("checkobjgd") String checkobjgd,
                                             @Param("status") String status, @Param("shiftname") String shiftname,
                                             @Param("linename") String linename, @Param("createtime1") String createtime1,
                                             @Param("createtime2") String createtime2
    );

    //查询B保养查询
    List<DJSelectInfo> SelectAllBYSelectInfo(@Param("checkobjtype") String checkobjtype, @Param("checkobjgd") String checkobjgd,
                                             @Param("status") String status, @Param("shiftname") String shiftname,
                                             @Param("linename") String linename, @Param("createtime1") String createtime1,
                                             @Param("createtime2") String createtime2
    );



}
