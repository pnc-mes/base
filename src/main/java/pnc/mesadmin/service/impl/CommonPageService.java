package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import pnc.mesadmin.dao.CommonPageDAO;
import pnc.mesadmin.dao.LineDao;
import pnc.mesadmin.dao.WODAO;
import pnc.mesadmin.dto.CommonPageInfo.CommonPageData;
import pnc.mesadmin.entity.Lineinfo;
import pnc.mesadmin.entity.WoInfo;
import pnc.mesadmin.entity.vo.CommonPageVo;
import pnc.mesadmin.service.CommonPageIService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/2/15 13:46
 * @Description:
 */
@Service
public class CommonPageService implements CommonPageIService {
    @Resource
    CommonPageDAO commonPageDAO;

    @Resource
    WODAO wodao;

    @Resource
    LineDao lineDao;

    @Override
    public List<CommonPageData> selectAllcommonPageDataMap(CommonPageVo commonPageVo) {
        Lineinfo lineinfo=lineDao.SelectLineInfoBygruid(Integer.valueOf(commonPageVo.getLineGd()));
        commonPageVo.setLineGd(lineinfo.getGuid());
        String[] aa=commonPageVo.getWoGd();
        if(aa.length>0){
            for(int i=0; i<aa.length; i++){
                WoInfo woInfo=wodao.SelectWoInfoByruid(Integer.valueOf(aa[i]));
                aa[i] = woInfo.getGuid();
            }
            commonPageVo.setWoGd(aa);
        }



        return commonPageDAO.selectAllcommonPageDataMap(commonPageVo);
    }
}
