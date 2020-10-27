package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllOpertInfo.GetAllOpertInfoRes;
import pnc.mesadmin.dto.GetAllOpertInfo.GetAllOpertInfoResB;
import pnc.mesadmin.dto.GetAllOpertInfo.GetAllOpertInfoResD;
import pnc.mesadmin.dto.GetAllSpecInfo.GetAllSpecInfoResD;
import pnc.mesadmin.dto.GetOpertInfo.*;
import pnc.mesadmin.dto.SaveOpertInfo.*;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.OpertIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;
import pnc.mesadmin.utils.MyPage;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：作业信息Service
 * 创建人：王怀龙
 * 创建时间：2017-06-01
 * 修改人：
 * 修改时间：
 */
@Transactional
@Service
public class OpertService implements OpertIService {
    @Resource
    private OpertDAO opertDAO;
    @Resource
    private WorkCenterDAO workCenterDAO;
    @Resource
    private BLevelDAO bLevelInfoMapper;

    @Resource
    private ReasongDAO reasongDAO;

    @Resource
    private BaseIService baseIService;


    /**
     * 获取作业列表信息
     */
    public GetAllOpertInfoRes QAllOpertInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllOpertInfoRes getAllOpertInfoRes = new GetAllOpertInfoRes();
        GetAllOpertInfoResB getAllOpertInfoResB = new GetAllOpertInfoResB();
        List<GetAllOpertInfoResD> getAllOpertInfoResDS = new ArrayList<GetAllOpertInfoResD>();

        List<OpertInfo> opertInfoList =  baseIService.QALLBaseInfo(OpertDAO.class, "GetAllOpertInfo",
                argInitDataFields, argPageInfo);

        if (null != opertInfoList&& opertInfoList.size() >= 1) {
            for (int i = 0; i < opertInfoList.size(); i++) {
                WorkCenterInfo workCenterInfo = workCenterDAO.SelectWorkCenterByguid(opertInfoList.get(i).getWcgd());
                GetAllOpertInfoResD getAllOpertInfoResD = new GetAllOpertInfoResD();
                if(workCenterInfo != null){
                    getAllOpertInfoResD.setWCName( workCenterInfo.getCenterName());
                }
                getAllOpertInfoResD.setOpertRd(opertInfoList.get(i).getRuid());
                getAllOpertInfoResD.setOptName(opertInfoList.get(i).getOptname());
                getAllOpertInfoResDS.add(getAllOpertInfoResD);
            }
        }

        getAllOpertInfoResB.setMsgCode("0x00000");
        getAllOpertInfoResB.setMsgDes("成功");
        getAllOpertInfoResB.setData(getAllOpertInfoResDS);

        getAllOpertInfoRes.setBody(getAllOpertInfoResB);
        getAllOpertInfoRes.setStatus("00");

        return getAllOpertInfoRes;
    }

    @Override
    public PageResult<GetAllOpertInfoResD> QALLGetAllOpertNew(BaseRequest req) {
        List<GetAllOpertInfoResD> resDList = new ArrayList<GetAllOpertInfoResD>();
        GetAllOpertInfoResD resD = null;

        IPage<OpertInfo> iPage = opertDAO.selectPage(new MyPage(req), CommonUtils.getQueryWrapper(req.getFields()));

        //赋值dto并返回
        for (OpertInfo obj : iPage.getRecords()) {
            resD = new GetAllOpertInfoResD();
            WorkCenterInfo workCenterInfo = workCenterDAO.SelectWorkCenterByguid(obj.getWcgd());
            if(workCenterInfo != null){
                resD.setWCName( workCenterInfo.getCenterName());
            }
            resD.setOpertRd(obj.getRuid());
            resD.setOptName(obj.getOptname());
            resDList.add(resD);
        }

        return new PageResult<>(iPage, resDList);
    }

    /**
     * 获取作业信息
     * @param argGetOpertInfoReqBD00
     * @return
     */
    public GetOpertInfoRes QOpertInfo(GetOpertInfoReqBD00 argGetOpertInfoReqBD00) {


        GetOpertInfoRes  getOpertInfoRes = new GetOpertInfoRes();
        OpertInfo opertInfo = opertDAO.GetOpertInfoByRuid(argGetOpertInfoReqBD00.getOpertRd());
       if(opertInfo == null){
            throw new SystemException("","作业信息为空");
        }

        WorkCenterInfo workCenterInfo = workCenterDAO.SelectWorkCenterByguid(opertInfo.getWcgd());

        BLevelInfo bLevelInfo = bLevelInfoMapper.SelectByGuid(opertInfo.getbLGd());


        GetOpertInfoResDPack getOpertInfoResDPack = new GetOpertInfoResDPack();
        getOpertInfoResDPack.setCkWeight(opertInfo.getCkweight());
        getOpertInfoResDPack.setPackOpt(opertInfo.getPackopt());
        getOpertInfoResDPack.setPackType(opertInfo.getPacktype());

        GetOpertInfoResD getOpertInfoResD = new GetOpertInfoResD();
        getOpertInfoResD.setOpertRd(opertInfo.getRuid());
        getOpertInfoResD.setOptName(opertInfo.getOptname());
        if(workCenterInfo!=null){
            getOpertInfoResD.setWCRd(workCenterInfo.getRuid());
            getOpertInfoResD.setWCName(workCenterInfo.getCenterName());
        }

        if(bLevelInfo != null){
            getOpertInfoResD.setBLRd(bLevelInfo.getRuid());
            getOpertInfoResD.setBLName(bLevelInfo.getbLName());
        }


        getOpertInfoResD.setSpecOption(opertInfo.getSpecoption());
        getOpertInfoResD.setPackInfo(getOpertInfoResDPack);
        getOpertInfoResD.setBadOutSpec(opertInfo.getBadOutSpec());
        getOpertInfoResD.setCreator(opertInfo.getCreator());
        getOpertInfoResD.setCreateTime(DateUtil.format(opertInfo.getCreatetime()));
        getOpertInfoResD.setLastModifyMan(opertInfo.getLastModifyMan());
        getOpertInfoResD.setLastModifyTime(DateUtil.format(opertInfo.getLastModifyTime()));
        getOpertInfoResD.setRemark(opertInfo.getRemark());

        ReasongInfo objEReasongInfo=reasongDAO.SelectReasongInfoByGuid(opertInfo.getReaCGGd());
        if(objEReasongInfo!=null){
            getOpertInfoResD.setReaCGRd(objEReasongInfo.getRuid());
            getOpertInfoResD.setReaCGName(objEReasongInfo.getReaCGName());
        }
        GetOpertInfoResB getOpertInfoResB = new GetOpertInfoResB();
        getOpertInfoResB.setMsgDes("成功");
        getOpertInfoResB.setMsgCode("0x00000");
        getOpertInfoResB.setData(getOpertInfoResD);

        getOpertInfoRes.setStatus("00");
        getOpertInfoRes.setBody(getOpertInfoResB);


        return getOpertInfoRes;
    }

    public SaveOpertInfoRes SaveOpertInfo(SaveOpertInfoReqBD00 argSaveOpertInfoReqBD00) {
        SaveOpertInfoRes objESaveOpertInfoRes=new SaveOpertInfoRes();

        SaveOpertInfoResB body =new SaveOpertInfoResB();

        SaveOpertInfoResD data=new SaveOpertInfoResD();

        OpertInfo opertInfo = new OpertInfo();

        //校 验操作名称不能重复不能为空
        List<OpertInfo> objEOpertInfo=opertDAO.GetAllOpertInfo();

        //逻辑校验保存的操作名称不能相同
        for (OpertInfo obj:objEOpertInfo){
            if (obj.getOptname().equals(argSaveOpertInfoReqBD00.getOptName())){
                throw new SystemException("MG0006F","作业名称已存在");
            }
        }

        opertInfo.setGuid(CommonUtils.getRandomNumber());
        opertInfo.setOptname(argSaveOpertInfoReqBD00.getOptName());
        //张亮亮改的
        WorkCenterInfo objEWorkCenterInfo=workCenterDAO.SelectWorkCenterByruid(argSaveOpertInfoReqBD00.getWCRd());
        if(objEWorkCenterInfo!=null){
            opertInfo.setWcgd(objEWorkCenterInfo.getGuid());
        }

        BLevelInfo obJEBLevelInfo=bLevelInfoMapper.SelectBybLRd(argSaveOpertInfoReqBD00.getBLRd());
        if(obJEBLevelInfo!=null){
            opertInfo.setbLGd(obJEBLevelInfo.getGuid());
        }else {
            opertInfo.setbLGd("");
        }

        opertInfo.setSpecoption(argSaveOpertInfoReqBD00.getSpecOption());
        opertInfo.setPackopt(argSaveOpertInfoReqBD00.getPackInfo().getPackOpt());
        opertInfo.setCkweight(argSaveOpertInfoReqBD00.getPackInfo().getCkWeight());
        opertInfo.setPacktype(argSaveOpertInfoReqBD00.getPackInfo().getPackType());
        opertInfo.setBadOutSpec(argSaveOpertInfoReqBD00.getBadOutSpec());
        opertInfo.setCreator(CommonUtils.readUser().getRealName());
        opertInfo.setCreatetime(new Date());
        ReasongInfo objEReasongInfo=reasongDAO.SelectReasongInfoByRuid(argSaveOpertInfoReqBD00.getReaCGRd());
        if(objEReasongInfo!=null){
            opertInfo.setReaCGGd(objEReasongInfo.getGuid());
        }
        opertInfo.setRemark(argSaveOpertInfoReqBD00.getRemark());
        opertDAO.insertOpertInfo(opertInfo);


        body.setData(data);
        objESaveOpertInfoRes.setBody(body);

        return objESaveOpertInfoRes;
    }

    /**
     * 删除作业信息
     * @param saveOpertInfoReqBD01
     * @return
     */
    public SaveOpertInfoRes RmSaveOpertInfo(SaveOpertInfoReqBD01 saveOpertInfoReqBD01){

        if(saveOpertInfoReqBD01==null){
            throw new SystemException(":","作业ID为空");
        }

        int resultCode = opertDAO.deleteByRuid(saveOpertInfoReqBD01.getOpertRd());
        if(resultCode<=0){
            throw new SystemException(":","删除作业信息失败");
        }
        SaveOpertInfoRes objSaveOpertInfoRes = saveOutInfo("0x00000","成功","00",null);
        return objSaveOpertInfoRes;
    }

    /**
     * 编辑作业信息
     * @param saveOpertInfoReqBD02
     * @return
     */
    public SaveOpertInfoRes ModSaveOpertInfo(SaveOpertInfoReqBD02 saveOpertInfoReqBD02){
        int resultCode = -1;

        OpertInfo opertInfo = new OpertInfo();

        //校验操作名称更新不能为空不能重复--LFZ
        OpertInfo objEOpertInfo=opertDAO.GetOpertInfoByRuid(saveOpertInfoReqBD02.getOpertRd());

        if(objEOpertInfo==null){
            throw new SystemException("","查询作业信息为空");
        }

        //查询操作名称信息--LFZ
        OpertInfo objeOpertInfo=opertDAO.SelectoptName(saveOpertInfoReqBD02.getOptName());

        if(objeOpertInfo!=null && !objeOpertInfo.getOptname().equals(objEOpertInfo.getOptname())){
            throw new SystemException("MG0006F","更新失败，作业名称已存在");
        }

        opertInfo.setRuid(saveOpertInfoReqBD02.getOpertRd());
        opertInfo.setOptname(saveOpertInfoReqBD02.getOptName());

        WorkCenterInfo objEWorkCenterInfo=workCenterDAO.SelectWorkCenterByruid(saveOpertInfoReqBD02.getWCRd());
        if(objEWorkCenterInfo==null){
            throw new SystemException("","查询工作中心信息为空");
        }
        opertInfo.setWcgd(objEWorkCenterInfo.getGuid());


        BLevelInfo objEBLevelInfo=bLevelInfoMapper.SelectBybLRd(saveOpertInfoReqBD02.getBLRd());
        if(objEBLevelInfo!=null){
            opertInfo.setbLGd(objEBLevelInfo.getGuid());
        }else {
            opertInfo.setbLGd("");
        }


        ReasongInfo objEReasongInfo=reasongDAO.SelectReasongInfoByRuid(saveOpertInfoReqBD02.getReaCGRd());
        if(objEReasongInfo!=null){
            opertInfo.setReaCGGd(objEReasongInfo.getGuid());
        }
        else {
            opertInfo.setReaCGGd("");
        }

        opertInfo.setSpecoption(saveOpertInfoReqBD02.getSpecOption());
        opertInfo.setPackopt(saveOpertInfoReqBD02.getPackInfo().getPackOpt());
        opertInfo.setCkweight(saveOpertInfoReqBD02.getPackInfo().getCkWeight());
        opertInfo.setPacktype(saveOpertInfoReqBD02.getPackInfo().getPackType());
        opertInfo.setBadOutSpec(saveOpertInfoReqBD02.getBadOutSpec());
        opertInfo.setRemark(saveOpertInfoReqBD02.getRemark());
        opertInfo.setLastModifyTime(new Date());
        opertInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        resultCode = opertDAO.editByRuid(opertInfo);
        if(resultCode <= 0){
            throw new SystemException("","编辑作业信息失败");
        }
        SaveOpertInfoRes objSaveOpertInfoRes = saveOutInfo("0x00000","成功","00",null);
        return objSaveOpertInfoRes;
    }

    /**
     * 复制作业信息
     * @param saveOpertInfoReqBD03
     * @return
     */
    public SaveOpertInfoRes SaveOpertInfoCopy(SaveOpertInfoReqBD03 saveOpertInfoReqBD03){

        if(saveOpertInfoReqBD03==null){
            throw new SystemException("","作业ID为空");
        }
        OpertInfo opertInfo = opertDAO.GetOpertInfoByRuid(saveOpertInfoReqBD03.getOpertRd());
        if(opertInfo==null){
            throw new SystemException("","作业信息为空");
        }
        String opertInfoGuid = CommonUtils.getRandomNumber();
        opertInfo.setGuid(opertInfoGuid);
        opertInfo.setRuid(null);
        if(opertDAO.insertOpertInfo(opertInfo)<=0){
            throw new SystemException("","复制作业信息失败");
        }

        OpertInfo newOpertInfo = opertDAO.GetOpertInfoByGuid(opertInfoGuid);
        newOpertInfo.setOptname(newOpertInfo.getOptname()+newOpertInfo.getRuid());
       int resultCode =  opertDAO.editByRuid(newOpertInfo);
        if(resultCode <= 0){
            throw new SystemException("","复制作业信息失败");
        }

        SaveOpertInfoRes objSaveOpertInfoRes = saveOutInfo("0x00000","成功","00",null);

        return objSaveOpertInfoRes;
    }

    /**
     * 集中设置返回值
     * @param MsgCode
     * @param MsgDes
     * @param Status
     * @param saveOpertInfoResD
     * @return
     */
    private SaveOpertInfoRes saveOutInfo(String MsgCode, String MsgDes, String Status, SaveOpertInfoResD saveOpertInfoResD){

        SaveOpertInfoResB saveOpertInfoResB = new SaveOpertInfoResB();
        saveOpertInfoResB.setMsgCode(MsgCode);
        saveOpertInfoResB.setMsgDes(MsgDes);
        if(saveOpertInfoResD!=null){
            saveOpertInfoResB.setData(saveOpertInfoResD);
        }

        SaveOpertInfoRes saveOpertInfoRes = new SaveOpertInfoRes();
        saveOpertInfoRes.setStatus(Status);
        saveOpertInfoRes.setBody(saveOpertInfoResB);

        return saveOpertInfoRes;
    }
}
