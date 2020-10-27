package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.dto.SaveUDMaterailDto.GetAllUDMaterialsRespons;
import pnc.mesadmin.entity.ULineMaterialDetailInfo;

import java.util.List;

/**
 * @program: mesadmin
 * @description: 产线执行物料明细信息
 * @author: yin.yang
 * @create: 2019-10-12
 **/
public interface ULineMaterialDetailInfoDao extends BaseMapper<ULineMaterialDetailInfo> {

    /**
     * <p>
     * 查询 : 根据工单+工序查询产线余料，分页显示
     * </p>
     *
     * @param page 分页对象,xml中可以从里面进行取值,传递参数 Page 即自动分页,必须放在第一位(你可以继承Page实现自己的分页对象)
     * @param woCode 工单号
     * @param specGuid 工序ID
     * @return 分页对象
     */
    IPage<GetAllUDMaterialsRespons> selectPageUDMD(Page<?> page, @Param("woCode") String woCode, @Param("specGuid") String specGuid);

}
