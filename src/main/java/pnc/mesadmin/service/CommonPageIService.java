package pnc.mesadmin.service;

import pnc.mesadmin.dto.CommonPageInfo.CommonPageData;
import pnc.mesadmin.entity.vo.CommonPageVo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/2/15 13:45
 * @Description:
 */
public interface CommonPageIService {
    List<CommonPageData> selectAllcommonPageDataMap(CommonPageVo commonPageVo);
}
