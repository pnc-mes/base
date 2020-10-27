package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.dto.newmove.MStoreDTO.GetAllNotInStarage;
import pnc.mesadmin.entity.PackInfo;
import pnc.mesadmin.entity.common.GetAllPackInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：包装信息DAO
 * 创建人：潘俊峰
 * 创建时间：2018-10-11
 * 修改人：
 * 修改时间：
 */
public interface SPackDAO {

    /**
     * 新增包装信息 (By-pjf)
     *
     * @param argPackInfo
     * @return
     */
    int InsertPack(PackInfo argPackInfo);

    /**
     * 根据箱号查询信息
     *
     * @param packCode
     * @return
     */
    PackInfo SelectByPackCode(String packCode);

    int updatePackInfoByRemarkAndRuid(@Param("remark") String remark, @Param("ruid") int ruid);

    List<PackInfo> SelectByListPackCode(String packCode);

    List<GetAllPackInfo> selectAllXiangAndBatch(@Param("batch") String batch, @Param("createtime1") String createtime1,
                                                @Param("createtime2") String createtime2, @Param("pack") String pack
    );

    List<GetAllPackInfo> selectAllXiangAndBatchDetail(@Param("packGd") String packGd);


    /**
     * @Description 查询所有已请检合格待入库的箱子
     * @Author yin.yang
     * @Date 2019/7/11
     * @Param
     * @Return
     * @Exception
     */
    List<GetAllNotInStarage> SelectAllNotInStoragePacks();

}
