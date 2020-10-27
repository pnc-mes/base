package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.dto.CommonPageInfo.CommonPageData;
import pnc.mesadmin.entity.vo.CommonPageVo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/2/15 13:36
 * @Description:
 */
public interface CommonPageDAO {
    List<CommonPageData> selectAllcommonPageDataMap(@Param("commonPageVo") CommonPageVo commonPageVo);
}
