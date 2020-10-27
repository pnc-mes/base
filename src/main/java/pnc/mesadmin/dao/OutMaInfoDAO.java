package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.OutMaInfo;
import pnc.mesadmin.dto.newmove.GetZOutMa.GetZOutMaReq;
import pnc.mesadmin.entity.common.GetAllXiangAndBatch;
import pnc.mesadmin.entity.common.GetZOutMaRes;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/3/9 13:52
 * @Description:
 */
public interface OutMaInfoDAO {
    List<OutMaInfo> selectAllOutMaInfo();

    OutMaInfo selectOutMaInfoByRuid(int ruid);

    int insertOutMaInfo(OutMaInfo outMaInfo);

    OutMaInfo selectOutMaInfoByoutCode(String outCode);

    int deleteOutMaInfoByRuid(int ruid);

    void updateOutMaInfo(OutMaInfo outMaInfo);

    List<GetZOutMaRes> selectAllGetZOutMaRes(@Param("getZOutMaReq") GetZOutMaReq getZOutMaReq);

    GetZOutMaRes selectGetZOutMaRes(@Param("getZOutMaReq") GetZOutMaReq getZOutMaReq);

    void updateOutMaInfoByGuid(OutMaInfo outMaInfo);

    List<GetAllXiangAndBatch> selectAllXiangAndBatch(@Param("MaVerRd") String MaVerRd, @Param("StoreRd") String StoreRd, @Param("LRd") String LRd,
                                                     @Param("F1") String F1, @Param("F2") String F2, @Param("F3") String F3, @Param("F4") String F4,
                                                     @Param("CreateTime1") String CreateTime1, @Param("CreateTime2") String CreateTime2,
                                                     @Param("Batch") String Batch
    );


    List<GetAllXiangAndBatch> selectAllXiangAndBatch1(@Param("MaVerRd") String MaVerRd, @Param("StoreRd") String StoreRd, @Param("LRd") String LRd,
                                                      @Param("F1") String F1, @Param("F2") String F2, @Param("F3") String F3, @Param("F4") String F4,
                                                      @Param("CreateTime1") String CreateTime1, @Param("CreateTime2") String CreateTime2,
                                                      @Param("Batch") String Batch
    );

    //获取箱子的识别码
    List<String> selectUniqueCode(String barcode);
}
