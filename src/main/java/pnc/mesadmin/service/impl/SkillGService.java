package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.DCDAO;
import pnc.mesadmin.dao.SkillGlDAO;
import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.GetAllDcInfo.GetAllDcInfoResD;
import pnc.mesadmin.dto.GetAllSGInfo.GetAllSGInfoRes;
import pnc.mesadmin.dto.GetAllSGInfo.GetAllSGInfoResD;
import pnc.mesadmin.dto.GetSGInfo.GetSGInfoReq00;
import pnc.mesadmin.dto.GetSGInfo.GetSGInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.PageResult;
import pnc.mesadmin.dto.SaveSGInfo.*;
import pnc.mesadmin.entity.DCInfo;
import pnc.mesadmin.entity.DCVerInfo;
import pnc.mesadmin.entity.SkillGlInfo;
import pnc.mesadmin.service.SkillGIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.MyPage;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: mesadmin
 * @description: 技能培训Impl
 * @author: yin.yang
 * @create: 2019-07-16
 **/
@Service
@Transactional
public class SkillGService implements SkillGIService {
    @Resource
    private SkillGlDAO SkillGlDAO;

    @Override
    public GetAllSGInfoRes QALLGetAllSGInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        return null;
    }

    @Override
    public PageResult<GetAllSGInfoResD> QALLGetAllSGNew(BaseRequest req) {
        List<GetAllSGInfoResD> resDList = new ArrayList<GetAllSGInfoResD>();
        GetAllSGInfoResD resD = null;

        IPage<SkillGlInfo> iPage = SkillGlDAO.selectPage(new MyPage(req), CommonUtils.getQueryWrapper(req.getFields()));
        //赋值dto并返回
        for (SkillGlInfo obj : iPage.getRecords()) {
            resD = new GetAllSGInfoResD();
            resD.setSGRd(obj.getRuid());
            resD.setSGName(obj.getSkillGName());
            resDList.add(resD);
        }
        return new PageResult<>(iPage, resDList);
    }

    @Override
    public
    GetSGInfoRes GetGetSGInfoRes(GetSGInfoReq00 argGetSGInfoReq00) {
        return null;
    }

    @Override
    public SaveSGInfoRes AddSaveSGInfoRes(SaveSGInfoReq00 argSaveSGInfoReq00) {
        return null;
    }

    @Override
    public SaveSGInfoRes RmSaveSGInfoRes(SaveSGInfoReq01 argSaveSGInfoReq01) {
        return null;
    }

    @Override
    public SaveSGInfoRes ModSaveSGInfoRes(SaveSGInfoReq02 argSaveSGInfoReq02) {
        return null;
    }

    @Override
    public SaveSGInfoRes AddSaveSGInfoReq03(SaveSGInfoReq03 argSaveSGInfoReq03) {
        return null;
    }
}
