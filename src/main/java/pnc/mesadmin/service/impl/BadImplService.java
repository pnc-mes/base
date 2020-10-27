package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.BadDAO;
import pnc.mesadmin.dao.BadDtDAO;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllBadInfoDto.*;
import pnc.mesadmin.dto.MBaseDto.MBaseResB;
import pnc.mesadmin.dto.SaveDcInfo.SaveDcInfoReqBD01;
import pnc.mesadmin.entity.BadDInfo;
import pnc.mesadmin.entity.BadInfo;
import pnc.mesadmin.service.BadService;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.utils.CommonUtils;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pnc.mesadmin.dto.MBaseDto.MBaseRes;
import pnc.mesadmin.utils.MyPage;

@Service
@Transactional
public class BadImplService implements BadService {
    @Resource
    private BaseIService baseIService;
    @Resource
    BadDAO badDAO;

    @Resource
    BadDtDAO badDtDAO;

    @Override
    public PageResult<MBaseRes> QALLGetAllDcInfoRes(BaseRequest req) {

        MBaseRes mBaseRes  = new MBaseRes();

        MBaseResB mBaseResB = new MBaseResB();

        List<GetAllBadInfoResB> getAllDcInfoResDs = new ArrayList<GetAllBadInfoResB>();

        Page<BadInfo> page = new Page<>(req.getCurrent(), req.getSize());

        IPage<BadInfo> woInfoIPage = badDAO.selectPage(new MyPage(req), CommonUtils.getQueryWrapper(req.getFields()));

        // 调用DAO查询数据采集列表
        List<BadInfo> dcInfos = woInfoIPage.getRecords();

        // 判断返回数据采集信息列表是否为空
        if (dcInfos != null&&dcInfos.size()>=1 ) {
            for (int i = 0 ; i < dcInfos.size() ; i ++){
                GetAllBadInfoResB response = new GetAllBadInfoResB();
                response.setBadRd(dcInfos.get(i).getRuid());
                response.setBadTypeName(dcInfos.get(i).getBadType());
                getAllDcInfoResDs.add(response);
            }
        }
        mBaseResB.setData(getAllDcInfoResDs);

        mBaseRes.setBody(mBaseResB);

        return    new PageResult(woInfoIPage.getTotal(), woInfoIPage.getPages(), woInfoIPage.getCurrent(), woInfoIPage.getSize(), getAllDcInfoResDs);
    }

    @Override
    public MBaseRes GetAllBadInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        MBaseRes baseResponse = new MBaseRes();
        MBaseResB baseResBody = new MBaseResB();
        List<BadInfo> badInfoList = baseIService.QALLBaseInfo(BadDAO.class, "SelectAllBadInfo",
                argInitDataFields, argPageInfo);
        List<GetAllBadInfoResB> responseList = new ArrayList<>();
        for (BadInfo model : badInfoList) {
            GetAllBadInfoResB response = new GetAllBadInfoResB();
            response.setBadRd(model.getRuid());
            response.setBadTypeName(model.getBadType());
            responseList.add(response);
        }
        baseResBody.setData(responseList);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public MBaseRes GetBadInfo(GetAllBadInfoRes request) {
        MBaseRes baseResponse = new MBaseRes();
        MBaseResB baseResBody = new MBaseResB();
        GetBadInfoResB badInfoResB = new GetBadInfoResB();
        BadInfo badInfo = badDAO.SelectBadInfoByBadRd(request.getBadRd());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (badInfo == null) {
            throw new SystemException("EEEE", "暂无不良原因！");
        }
        badInfoResB.setBadRd(badInfo.getRuid());
        badInfoResB.setBadTypeName(badInfo.getBadType());
        badInfoResB.setCreator(badInfo.getCreator());
        badInfoResB.setCreateTime(format.format(badInfo.getCreateTime()));
        if (badInfo.getLastModifyTime() != null) {
            badInfoResB.setLastModifyMan(badInfo.getLastModifyMan());
            badInfoResB.setLastModifyTime(format.format(badInfo.getLastModifyTime()));
        }
        badInfoResB.setRemark(badInfo.getRemark());
        List<GetBadInfoResB.BadDtlInfo> badDtlInfos = new ArrayList<>();
        List<BadDInfo> badDInfoList = badDtDAO.SelectByBadDInfoByBadGd(badInfo.getGuid());
        if (badDInfoList.size() > 0) {
            for (BadDInfo model : badDInfoList) {
                GetBadInfoResB.BadDtlInfo badDtlInfo = new GetBadInfoResB.BadDtlInfo();
                badDtlInfo.setBadCode(model.getBadCode());
                badDtlInfo.setBadDtlRd(model.getRuid());
                badDtlInfo.setBadName(model.getBadName());
                badDtlInfos.add(badDtlInfo);
            }
        }
        badInfoResB.setBadDtlInfo(badDtlInfos);
        baseResBody.setData(badInfoResB);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public MBaseRes AddBadInfo(SaveBadInfoRes reqBD00) {
        MBaseRes baseResponse = new MBaseRes();
        MBaseResB baseResBody = new MBaseResB();
        BadInfo badInfo = new BadInfo();
        badInfo.setBadType(reqBD00.getBadTypeName());
        List<BadInfo> badInfoList = badDAO.SelectBadInfoByBadTypeName(reqBD00.getBadTypeName());
        if (badInfoList.size() > 0) {
            throw new SystemException("EEEE", "不良原因类型出现重复！");
        }
        badInfo.setGuid(CommonUtils.getRandomNumber());
        badInfo.setCreator(CommonUtils.readUser().getRealName());
        badInfo.setCreateTime(new Date());
        badInfo.setRemark(reqBD00.getRemark());
        badDAO.InsertBadInfo(badInfo);

        if (reqBD00.getBadDtlInfo().size() > 0) {
            for (SaveBadInfoRes.BadDtlInfo model : reqBD00.getBadDtlInfo()) {
                if (model.getBadCode() == null || model.getBadCode() == "") {
                    throw new SystemException("EEEE", "不良原因代码不能为空！");
                }
                if (model.getBadName() == null || model.getBadName() == "") {
                    throw new SystemException("EEEE", "不良原因名称不能为空！");
                }
                List<BadDInfo> list = badDtDAO.SelectByBadDInfoByBadCode(model.getBadCode());
                if (list.size() > 0) {
                    throw new SystemException("EEEE", "不良代码出现重复！");
                }
                BadDInfo badDInfo = new BadDInfo();
                badDInfo.setBadCode(model.getBadCode());
                badDInfo.setBadName(model.getBadName());
                badDInfo.setBadGd(badInfo.getGuid());
                badDInfo.setGuid(CommonUtils.getRandomNumber());
                badDInfo.setCreator(CommonUtils.readUser().getRealName());
                badDInfo.setCreateTime(new Date());
                badDtDAO.InsertBadDInfo(badDInfo);
            }

        } else {
            throw new SystemException("EEEE", "不良原因明细不能为空！");
        }
        badInfo = badDAO.SelectBadInfoByBadGd(badInfo.getGuid());
        baseResBody.setData(badInfo);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public MBaseRes RmDelBadInfo(SaveDcInfoReqBD01 reqBD00) {
        MBaseRes baseResponse = new MBaseRes();
        MBaseResB baseResBody = new MBaseResB();
        BadInfo badInfo = badDAO.SelectBadInfoByBadRd(Integer.valueOf(reqBD00.getDcRd()));
        if (badInfo == null) {
            throw new SystemException("EEEE", "暂无不良原因！");
        }
        badDtDAO.DelBadDInfoByRuid(badInfo.getGuid());
        badDAO.DelBadInfoByRuid(badInfo.getRuid());
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public MBaseRes AddSaveBadInfo(SaveBadInfoRes reqBD00) {
        MBaseRes baseResponse = new MBaseRes();
        MBaseResB baseResBody = new MBaseResB();
        BadInfo badInfo = badDAO.SelectBadInfoByBadRd(Integer.valueOf(reqBD00.getBadRd()));
        badInfo.setBadType(reqBD00.getBadTypeName());
        badInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        badInfo.setLastModifyTime(new Date());
        badInfo.setRemark(reqBD00.getRemark());
        List<BadInfo> badInfoList = badDAO.SelectBadInfoByBadTypeName(reqBD00.getBadTypeName());
        if (badInfoList.size() > 0) {
            for (BadInfo model : badInfoList) {
                if (model.getRuid() != Integer.valueOf(reqBD00.getBadRd())) {
                    throw new SystemException("EEEE", "不良原因类型出现重复！");
                }
            }
        }
        badDAO.UpdateBadInfoByRuid(badInfo);

        badDtDAO.DelBadDInfoByRuid(badInfo.getGuid());
        if (reqBD00.getBadDtlInfo().size() > 0) {
            for (SaveBadInfoRes.BadDtlInfo model : reqBD00.getBadDtlInfo()) {
                if (model.getBadCode() == null || model.getBadCode() == "") {
                    throw new SystemException("EEEE", "不良原因代码不能为空！");
                }
                if (model.getBadName() == null || model.getBadName() == "") {
                    throw new SystemException("EEEE", "不良原因名称不能为空！");
                }
                List<BadDInfo> list = badDtDAO.SelectByBadDInfoByBadCode(model.getBadCode());
                if (list.size() > 0) {
                    throw new SystemException("EEEE", "不良代码出现重复！");
                }
                BadDInfo badDInfo = new BadDInfo();
                badDInfo.setBadCode(model.getBadCode());
                badDInfo.setBadName(model.getBadName());
                badDInfo.setBadGd(badInfo.getGuid());
                badDInfo.setGuid(CommonUtils.getRandomNumber());
                badDInfo.setCreator(CommonUtils.readUser().getRealName());
                badDInfo.setCreateTime(new Date());
                badDtDAO.InsertBadDInfo(badDInfo);
            }
        } else {
            throw new SystemException("EEEE", "不良原因明细不能为空！");
        }
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }
}