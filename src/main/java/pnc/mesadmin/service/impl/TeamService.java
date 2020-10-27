package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.GetAllTeamsInfo.GetAllTeamsInfoRes;
import pnc.mesadmin.dto.GetAllTeamsInfo.GetAllTeamsInfoResB;
import pnc.mesadmin.dto.GetAllTeamsInfo.GetAllTeamsInfoResD;
import pnc.mesadmin.dto.GetTeamInfo.*;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveTeamInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.TeamIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 09:10
 * @Description:
 */
@Transactional
@Service
public class TeamService implements TeamIService {
    @Resource
    private BaseIService baseIService;
    @Resource
    private TeamDAO teamDAO;

    @Resource
    private TeamMemberInfoDAO teamMemberInfoDAO;

    @Resource
    private UserDAO userDAO;

    @Resource
    private LineDao lineDao;

    @Resource
    private TeamLineDAO teamLineDAO;

    //获取团队列表
    @Override
    public GetAllTeamsInfoRes QALLTeamInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllTeamsInfoRes getAllTeamsInfoRes=new GetAllTeamsInfoRes();
        GetAllTeamsInfoResB getAllTeamsInfoResB=new GetAllTeamsInfoResB();
        List<GetAllTeamsInfoResD> getAllTeamsInfoResDS=new ArrayList<GetAllTeamsInfoResD>();
        GetAllTeamsInfoResD getAllTeamsInfoResD=null;
        List<TeamInfo> teamInfos= baseIService.QALLBaseInfo(TeamDAO.class, "SelectAllTeamInfo",
                argInitDataFields, argPageInfo);

        if(teamInfos!=null&&teamInfos.size()>0){
            for(TeamInfo teamInfo:teamInfos){
                getAllTeamsInfoResD=new GetAllTeamsInfoResD();
                getAllTeamsInfoResD.setTeamRd(teamInfo.getRuid());
                getAllTeamsInfoResD.setTeamName(teamInfo.getTeamName());
                getAllTeamsInfoResDS.add(getAllTeamsInfoResD);
            }
        }
        getAllTeamsInfoResB.setData(getAllTeamsInfoResDS);
        getAllTeamsInfoRes.setBody(getAllTeamsInfoResB);
        return getAllTeamsInfoRes;
    }

    //获取团队信息
    @Override
    public GetTeamInfoRes GetGetTeamInfoRes(GetTeamInfoReq00 getTeamInfoReq00) {
        GetTeamInfoRes getTeamInfoRes=null;
        GetTeamInfoResB getTeamInfoResB=null;
        GetTeamInfoResD getTeamInfoResD=null;
        List<GetAllTeamsInfoResD> getAllTeamsInfoResDS=null;
        GetAllTeamsInfoResD getAllTeamsInfoResD=null;
        List<GetTeamInfoResDTeamMember> getTeamInfoResDTeamMembers=null;
        GetTeamInfoResDTeamMember getTeamInfoResDTeamMember=null;
        if(getTeamInfoReq00.getTeamRd()==0||"".equals(getTeamInfoReq00.getTeamRd())){
            throw new SystemException("xx","团队信息不能为空");
        }
        TeamInfo teamInfo=teamDAO.SelectTeamInfoByRuid(getTeamInfoReq00.getTeamRd());
        if(teamInfo!=null){
            getTeamInfoRes=new GetTeamInfoRes();
            getTeamInfoResB=new GetTeamInfoResB();
            getTeamInfoResD=new GetTeamInfoResD();
            getTeamInfoResD.setTeamRd(teamInfo.getRuid());
            getTeamInfoResD.setTeamName(teamInfo.getTeamName());
            getTeamInfoResD.setDescription(teamInfo.getDescription());
            getTeamInfoResD.setCreator(teamInfo.getCreator());
            getTeamInfoResD.setCreateTime(DateUtil.format(teamInfo.getCreateTime()));
            getTeamInfoResD.setLastModifyMan(teamInfo.getLastModifyMan());
            getTeamInfoResD.setLastModifyTime(DateUtil.format(teamInfo.getLastModifyTime()));
            getTeamInfoResD.setRemark(teamInfo.getRemark());
            List<TeamMemberInfo> teamMemberInfos=teamMemberInfoDAO.SelectTeamInfoByGuid(teamInfo.getGuid());
            if(teamMemberInfos!=null&&teamMemberInfos.size()>0){
                getTeamInfoResDTeamMembers=new ArrayList<GetTeamInfoResDTeamMember>();
                for(TeamMemberInfo teamMemberInfo:teamMemberInfos){
                    getTeamInfoResDTeamMember=new GetTeamInfoResDTeamMember();
                    UserInfo userInfo=userDAO.SelectUserRd(teamMemberInfo.getUserGd());
                    if(userInfo!=null){
                        getTeamInfoResDTeamMember.setUserRd(userInfo.getRuid());
                        getTeamInfoResDTeamMember.setRealName(userInfo.getRealName());
                        if(userInfo.getMobileNo()==null||"".equals(userInfo.getMobileNo())){
                            getTeamInfoResDTeamMember.setMobileNo("");
                        }else {
                            getTeamInfoResDTeamMember.setMobileNo(userInfo.getMobileNo());
                        }
                        if(userInfo.getEmailAddress()==null||"".equals(userInfo.getEmailAddress())){
                            getTeamInfoResDTeamMember.setEmailAddress("");
                        }else {
                            getTeamInfoResDTeamMember.setEmailAddress(userInfo.getEmailAddress());
                        }
                        //getTeamInfoResDTeamMember.setRealName(userInfo.getRealName());
                        getTeamInfoResDTeamMembers.add(getTeamInfoResDTeamMember);
                        getTeamInfoResD.setTeamMember(getTeamInfoResDTeamMembers);
                    }else{
                        getTeamInfoResD.setTeamMember(null);
                    }
                }
            }else {
                getTeamInfoResD.setTeamMember(getTeamInfoResDTeamMembers);
            }

            List<TeamLineInfo> teamLineInfos=teamLineDAO.SelectAllTeamIineInfoByteamGd(teamInfo.getGuid());
            List<GetTeamInfoResDTeamLineInfo> getTeamInfoResDTeamLineInfos=new ArrayList<GetTeamInfoResDTeamLineInfo>();
            if(teamLineInfos!=null&&teamLineInfos.size()>0){
                for(TeamLineInfo teamLineInfo:teamLineInfos) {
                    GetTeamInfoResDTeamLineInfo getTeamInfoResDTeamLineInfo=new GetTeamInfoResDTeamLineInfo();
                    Lineinfo lineinfo=lineDao.SelectLineInfoByguid(teamLineInfo.getLineGd());
                    if(lineinfo!=null){
                        getTeamInfoResDTeamLineInfo.setLineRd(lineinfo.getRuid());
                        getTeamInfoResDTeamLineInfo.setLineName(lineinfo.getLineName());
                    }
                    getTeamInfoResDTeamLineInfos.add(getTeamInfoResDTeamLineInfo);
                }

            }

            getTeamInfoResD.setLineInfo(getTeamInfoResDTeamLineInfos);

        }


        getTeamInfoResB.setData(getTeamInfoResD);
        getTeamInfoRes.setBody(getTeamInfoResB);

        return getTeamInfoRes;
    }

    //新增团队信息
    @Override
    public SaveTeamInfoRes AddSaveTeamInfoRes(SaveTeamInfoReq00 objSaveTeamInfoReq00) {
        SaveTeamInfoRes saveTeamInfoRes=null;
        SaveTeamInfoResB saveTeamInfoResB=null;
        SaveTeamInfoResD saveTeamInfoResD=null;
        if(objSaveTeamInfoReq00.getTeamName()==null||"".equals(objSaveTeamInfoReq00.getTeamName())){
            throw new SystemException("xx","新增失败，团队名称不能为空");
        }
        TeamInfo teamInfos=teamDAO.SelectTeamInfoByTeamName(objSaveTeamInfoReq00.getTeamName());
        if(teamInfos!=null){
            throw new SystemException("xx","新增失败，团队名称已存在");
        }
        saveTeamInfoRes=new SaveTeamInfoRes();
        saveTeamInfoResB=new SaveTeamInfoResB();
        saveTeamInfoResD=new SaveTeamInfoResD();
        TeamInfo teamInfo=new TeamInfo();
        teamInfo.setGuid(CommonUtils.getRandomNumber());
        teamInfo.setTeamName(objSaveTeamInfoReq00.getTeamName());
        teamInfo.setDescription(objSaveTeamInfoReq00.getDescription());
        teamInfo.setRemark(objSaveTeamInfoReq00.getRemark());
        teamInfo.setCreateTime(new Date());
        teamInfo.setCreator(CommonUtils.readUser().getRealName());
        teamDAO.InsertTeamInfo(teamInfo);
        saveTeamInfoResD.setTeamRd(teamInfo.getRuid());
        if(objSaveTeamInfoReq00.getTeamMember().size()>0){
            TeamMemberInfo teamMemberInfo=null;
            int i=1;
            for(SaveTeamInfoReq00TeamMember obj:objSaveTeamInfoReq00.getTeamMember()){
                teamMemberInfo=new TeamMemberInfo();
                UserInfo userInfo=userDAO.SelectUserInfoByruid(obj.getUserRd());
                if(userInfo!=null){
                    teamMemberInfo.setUserGd(userInfo.getGuid());
                }
                teamMemberInfo.setSequence(i);
                teamMemberInfo.setTeamGd(teamInfo.getGuid());
                teamMemberInfoDAO.InsertTeamMemberInfo(teamMemberInfo);
                i++;
            }
        }

        if(objSaveTeamInfoReq00.getLineInfo().size()>0){
            for(SaveTeamInfoReq00TeamLineInfo saveTeamInfoReq00TeamLineInfo:objSaveTeamInfoReq00.getLineInfo()){
                TeamLineInfo teamLineInfo =new TeamLineInfo();
                teamLineInfo.setTeamGd(teamInfo.getGuid());
                Lineinfo lineinfo=lineDao.SelectLineInfoBygruid(saveTeamInfoReq00TeamLineInfo.getLineRd());
                if(lineinfo!=null){
                    teamLineInfo.setLineGd(lineinfo.getGuid());
                }
                teamLineDAO.InsertTeamIineInfo(teamLineInfo);
            }
        }

        saveTeamInfoResB.setData(saveTeamInfoResD);
        saveTeamInfoRes.setBody(saveTeamInfoResB);
        return saveTeamInfoRes;
    }

    //删除团队信息
    @Override
    public SaveTeamInfoRes RmGetTeamInfoReq00(SaveTeamInfoReq01 saveTeamInfoReq01) {
        SaveTeamInfoRes saveTeamInfoRes=null;
        SaveTeamInfoResB saveTeamInfoResB=null;
        if("".equals(saveTeamInfoReq01.getTeamRd())||saveTeamInfoReq01.getTeamRd()<=0){
            throw new SystemException("xx","删除失败，团队标识不存在");
        }

        TeamInfo teamInfo=teamDAO.SelectTeamInfoByRuid(saveTeamInfoReq01.getTeamRd());
        if(teamInfo!=null){
            saveTeamInfoRes=new SaveTeamInfoRes();
            saveTeamInfoResB=new SaveTeamInfoResB();
            List<TeamMemberInfo> teamMemberInfos=teamMemberInfoDAO.SelectTeamInfoByGuid(teamInfo.getGuid());
            if(teamMemberInfos!=null&&teamMemberInfos.size()>0){
                for(TeamMemberInfo teamMemberInfo:teamMemberInfos){
                    if(teamMemberInfoDAO.DeleteTeamMemberInfoByRuid(teamMemberInfo.getRuid())<=0){
                        throw new SystemException("xx","删除明细信息失败");
                    }
                }
            }

            List<TeamLineInfo> teamLineInfos=teamLineDAO.SelectAllTeamIineInfoByteamGd(teamInfo.getGuid());
            if(teamLineInfos!=null&&teamLineInfos.size()>0){
                  for(TeamLineInfo teamLineInfo:teamLineInfos) {
                      if(teamLineDAO.deleteTeamIineInfo(teamLineInfo.getRuid())<=0){
                          throw new SystemException("xx","删除明细信息失败");
                      }
                  }
            }

            if(teamDAO.DeleteTeamInfoByRuid(teamInfo.getRuid())<=0){
                throw new SystemException("xx","删除信息失败");
            }
        }

        saveTeamInfoRes.setBody(saveTeamInfoResB);
        return saveTeamInfoRes;
    }

    //修改团队信息
    @Override
    public SaveTeamInfoRes ModSaveTeamInfoReq02(SaveTeamInfoReq02 saveTeamInfoReq02) {
        SaveTeamInfoRes saveTeamInfoRes=null;
        SaveTeamInfoResB saveTeamInfoResB=null;
        if("".equals(saveTeamInfoReq02.getTeamRd())||saveTeamInfoReq02.getTeamRd()<=0){
            throw new SystemException("xxx","修改失败，团队标识不存在");
        }
        if(saveTeamInfoReq02.getTeamName()==null||"".equals(saveTeamInfoReq02.getTeamName())){
            throw new SystemException("xx","修改失败，团队名称不能为空");
        }
        TeamInfo teamInfos=teamDAO.SelectTeamInfoByRuid(saveTeamInfoReq02.getTeamRd());
        TeamInfo teamInfos1=null;
        teamInfos1=teamDAO.SelectTeamInfoByTeamName(saveTeamInfoReq02.getTeamName());
        if(teamInfos1!=null&&!teamInfos1.getTeamName().equals(teamInfos.getTeamName())){
            throw new SystemException("xx","修改失败，团队名称已存在");
        }

        if(teamInfos!=null){
            saveTeamInfoRes=new SaveTeamInfoRes();
            saveTeamInfoResB=new SaveTeamInfoResB();
            List<TeamMemberInfo> teamMemberInfos=teamMemberInfoDAO.SelectTeamInfoByGuid(teamInfos.getGuid());
            if(teamMemberInfos!=null&&teamMemberInfos.size()>0){
                for(TeamMemberInfo teamMemberInfo:teamMemberInfos){
                    if(teamMemberInfoDAO.DeleteTeamMemberInfoByRuid(teamMemberInfo.getRuid())<=0){
                        throw new SystemException("xx","删除明细失败");
                    }
                }
            }

            if(saveTeamInfoReq02.getTeamMember().size()>0){
                TeamMemberInfo teamMemberInfo=null;
                int i=1;
                for(SaveTeamInfoReq02TeamMember obj:saveTeamInfoReq02.getTeamMember()){
                    teamMemberInfo=new TeamMemberInfo();
                    UserInfo userInfo=userDAO.SelectUserInfoByruid(obj.getUserRd());
                    if(userInfo!=null){
                        teamMemberInfo.setUserGd(userInfo.getGuid());
                    }
                    teamMemberInfo.setSequence(i);
                    teamMemberInfo.setTeamGd(teamInfos.getGuid());
                    teamMemberInfoDAO.InsertTeamMemberInfo(teamMemberInfo);
                    i++;
                }
            }

            List<TeamLineInfo> teamLineInfos=teamLineDAO.SelectAllTeamIineInfoByteamGd(teamInfos.getGuid());
            if(teamLineInfos!=null&&teamLineInfos.size()>0){
                for(TeamLineInfo teamLineInfo:teamLineInfos) {
                    if(teamLineDAO.deleteTeamIineInfo(teamLineInfo.getRuid())<=0){
                        throw new SystemException("xx","删除明细信息失败");
                    }
                }
            }

            if(saveTeamInfoReq02.getLineInfo().size()>0){
                for(SaveTeamInfoReq00TeamLineInfo saveTeamInfoReq00TeamLineInfo:saveTeamInfoReq02.getLineInfo()){
                    TeamLineInfo teamLineInfo =new TeamLineInfo();
                    teamLineInfo.setTeamGd(teamInfos.getGuid());
                    Lineinfo lineinfo=lineDao.SelectLineInfoBygruid(saveTeamInfoReq00TeamLineInfo.getLineRd());
                    if(lineinfo!=null){
                        teamLineInfo.setLineGd(lineinfo.getGuid());
                    }
                    teamLineDAO.InsertTeamIineInfo(teamLineInfo);
                }
            }


            teamInfos.setTeamName(saveTeamInfoReq02.getTeamName());
            teamInfos.setDescription(saveTeamInfoReq02.getDescription());
            teamInfos.setRemark(saveTeamInfoReq02.getRemark());
            teamInfos.setLastModifyMan(CommonUtils.readUser().getRealName());
            teamInfos.setLastModifyTime(new Date());
            if(teamDAO.UpdateTeamInfo(teamInfos)<=0){
                throw new SystemException("xx","修改团队信息失败");
            }
        }

        saveTeamInfoRes.setBody(saveTeamInfoResB);
        return saveTeamInfoRes;
    }

    //复制团队信息
    @Override
    public SaveTeamInfoRes AddGetTeamInfoReq00(SaveTeamInfoReq01 saveTeamInfoReq01) {
        SaveTeamInfoRes saveTeamInfoRes=null;
        SaveTeamInfoResB saveTeamInfoResB=null;
        SaveTeamInfoResD saveTeamInfoResD=null;
        if("".equals(saveTeamInfoReq01.getTeamRd())||saveTeamInfoReq01.getTeamRd()<=0){
            throw new SystemException("xx","复制失败，团队标识不存在");
        }
        TeamInfo teamInfos=null;
        teamInfos=teamDAO.SelectTeamInfoByRuid(saveTeamInfoReq01.getTeamRd());
        if(teamInfos!=null){
            saveTeamInfoRes=new SaveTeamInfoRes();
            saveTeamInfoResB=new SaveTeamInfoResB();
            saveTeamInfoResD=new SaveTeamInfoResD();

            List<TeamMemberInfo> teamMemberInfos=teamMemberInfoDAO.SelectTeamInfoByGuid(teamInfos.getGuid());
            teamInfos.setGuid(CommonUtils.getRandomNumber());
            if(teamMemberInfos!=null&&teamMemberInfos.size()>0){

                for(TeamMemberInfo obj:teamMemberInfos){
                    obj.setTeamGd(teamInfos.getGuid());
                    teamMemberInfoDAO.InsertTeamMemberInfo(obj);
                }
            }

            List<TeamLineInfo> teamLineInfos=teamLineDAO.SelectAllTeamIineInfoByteamGd(teamInfos.getGuid());
            if(teamLineInfos!=null&&teamLineInfos.size()>0){
                for(TeamLineInfo teamLineInfo:teamLineInfos) {
                    teamLineInfo.setTeamGd(teamInfos.getGuid());
                    teamLineInfo.setLineGd(teamLineInfo.getLineGd());
                    teamLineDAO.InsertTeamIineInfo(teamLineInfo);
                }
            }


            teamInfos.setCreator(CommonUtils.readUser().getRealName());
            teamInfos.setCreateTime(new Date());
            teamDAO.InsertTeamInfo(teamInfos);

        }
        teamInfos.setTeamName(teamInfos.getTeamName()+teamInfos.getRuid());
        teamInfos.setLastModifyMan(CommonUtils.readUser().getRealName());
        teamInfos.setLastModifyTime(new Date());
        if(teamDAO.UpdateTeamInfo(teamInfos)<=0){
           throw new SystemException("xx","修改失败");
        }
        saveTeamInfoResD.setTeamRd(teamInfos.getRuid());
        saveTeamInfoResB.setData(saveTeamInfoResD);
        saveTeamInfoRes.setBody(saveTeamInfoResB);
        return saveTeamInfoRes;
    }
}
