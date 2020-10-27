package pnc.mesadmin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dto.GetAllTeamsInfo.GetAllTeamsInfoRes;
import pnc.mesadmin.dto.GetTeamInfo.GetTeamInfoReq00;
import pnc.mesadmin.dto.GetTeamInfo.GetTeamInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveTeamInfo.SaveTeamInfoReq00;
import pnc.mesadmin.dto.SaveTeamInfo.SaveTeamInfoReq01;
import pnc.mesadmin.dto.SaveTeamInfo.SaveTeamInfoReq02;
import pnc.mesadmin.dto.SaveTeamInfo.SaveTeamInfoRes;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 09:09
 * @Description:
 */

public interface TeamIService {
    //获取团队列表信息
    GetAllTeamsInfoRes QALLTeamInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //获取团队明细信息
    GetTeamInfoRes GetGetTeamInfoRes(GetTeamInfoReq00 getTeamInfoReq00);

    //新增团队信息
    SaveTeamInfoRes AddSaveTeamInfoRes(SaveTeamInfoReq00 objSaveTeamInfoReq00);

    //删除团队信息
    SaveTeamInfoRes RmGetTeamInfoReq00(SaveTeamInfoReq01 saveTeamInfoReq01);

    //修改团队信息
    SaveTeamInfoRes ModSaveTeamInfoReq02(SaveTeamInfoReq02 saveTeamInfoReq02);

    //复制团队信息
    SaveTeamInfoRes AddGetTeamInfoReq00(SaveTeamInfoReq01 saveTeamInfoReq01);
}
