package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.FactoryDAO;
import pnc.mesadmin.dao.LineDao;
import pnc.mesadmin.dao.OEMLineDAO;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllLinefo.GetAllLinefoRes;
import pnc.mesadmin.dto.GetAllLinefo.GetAllLinefoResB;
import pnc.mesadmin.dto.GetAllLinefo.GetAllLinefoResD;
import pnc.mesadmin.dto.GetLineInfo.*;
import pnc.mesadmin.dto.SaveLineInfo.*;
import pnc.mesadmin.entity.FactoryInfo;
import pnc.mesadmin.entity.Lineinfo;
import pnc.mesadmin.entity.OEMLineinfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.LineIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：线别信息Service层实现层
 * 创建人：郝赞
 * 创建时间：2018-6-12
 * 修改人：
 * 修改时间：
 */
@Transactional
@Service
public class LineService implements LineIService {
    @Resource
    private FactoryDAO factoryDAO;

    @Resource
    private LineDao lineDao;

    @Resource
    private OEMLineDAO oEMLineDAO;

    @Resource
    private BaseIService baseIService;

    //dto  获取线体列表信息
    public GetAllLinefoRes QALLGetLinefoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllLinefoRes data = new GetAllLinefoRes();
        GetAllLinefoResB dataB = new GetAllLinefoResB();
        List<GetAllLinefoResD> dataDs = new ArrayList<GetAllLinefoResD>();

        List<Lineinfo> lists = baseIService.QALLBaseInfo(LineDao.class, "SelectAllLineInfo",
                argInitDataFields, argPageInfo);

        if (lists != null || lists.size() > 0) {

            for (Lineinfo obj : lists) {
                GetAllLinefoResD dataD = new GetAllLinefoResD();
                dataD.setLineRd(obj.getRuid());
                if (obj.getLineName() == null) {
                    break;
                }
                dataD.setLineName(obj.getLineName());
                dataDs.add(dataD);
            }

        }
        dataB.setData(dataDs);
        data.setBody(dataB);

        return data;
    }

    /**
     * 获取线体列表信息（新）
     * @param req
     * @return
     */
    @Override
    public PageResult<GetAllLinefoResD> QALLGetLineNewRes(BaseRequest req) {
        List<GetAllLinefoResD> resDList = new ArrayList<GetAllLinefoResD>();
        GetAllLinefoResD resD = null;

        if(req.getSize() <= 0){
            req.setSize(1000);
        }

        Page<Lineinfo> page = new Page<>(req.getCurrent(), req.getSize());

        IPage<Lineinfo> iPage = lineDao.selectPage(page, CommonUtils.getQueryWrapper(req.getFields()));

        for (Lineinfo obj : iPage.getRecords()) {
            resD = new GetAllLinefoResD();
            resD.setLineRd(obj.getRuid());
            resD.setLineName(obj.getLineName());
            resD.setLineCode(obj.getLineCode());
            resDList.add(resD);
        }

        return new PageResult<GetAllLinefoResD>(iPage.getTotal(), iPage.getPages(), iPage.getCurrent(), iPage.getSize(), resDList);
    }

    //dto查询线体信息 根据传过来的LineRd
    public GetLineInfoRes GetGetLineInfoRes(GetLineInfoReqBD00 argGetLineInfoReqBD00) {
        GetLineInfoRes objEGetFaInfoRes = new GetLineInfoRes();
        GetLineInfoResB objEGetFaInfoResB = new GetLineInfoResB();
        //根据页面传过来的线体id 查询线体信息
        Lineinfo objFactoryInfo = lineDao.SelectLineInfoBygruid(argGetLineInfoReqBD00.getLineRd());

        if (objFactoryInfo != null) {
            GetLineInfoResD objEGetFaInfoResD = new GetLineInfoResD();
            objEGetFaInfoResD.setLineRd(objFactoryInfo.getRuid());
            objEGetFaInfoResD.setLineGd(objFactoryInfo.getGuid());
            objEGetFaInfoResD.setLineCode(objFactoryInfo.getLineCode());
            objEGetFaInfoResD.setLineName(objFactoryInfo.getLineName());
            objEGetFaInfoResD.setLineDes(objFactoryInfo.getLineDes());
            //根据工厂标识 查询工厂信息
            FactoryInfo factoryInfo = factoryDAO.SelectFactoryInfoByguid(objFactoryInfo.getFaGd());
            GetLineInfoResDFactory getLineInfoResDFactory = new GetLineInfoResDFactory();
            if (factoryInfo != null) {
                getLineInfoResDFactory.setFaRd(factoryInfo.getRuid());
                getLineInfoResDFactory.setFaName(factoryInfo.getFactoryName());
                objEGetFaInfoResD.setFactory(getLineInfoResDFactory);
            }
            //线体
            List<GetLineInfoResDB> GetLineInfoResDB = new ArrayList<GetLineInfoResDB>();
            List<OEMLineinfo> OrderList = oEMLineDAO.SelectOEMLineinfoByGui(objFactoryInfo.getGuid());
            if (OrderList != null && OrderList.size() > 0) {
                for (OEMLineinfo obj : OrderList) {
                    GetLineInfoResDB orderLineinfo = new GetLineInfoResDB();
                    Lineinfo lineinfo = lineDao.SelectLineInfoByguid(obj.getLineGD());
                    if (lineinfo != null) {
                        orderLineinfo.setLineRd(lineinfo.getRuid());
                        orderLineinfo.setLineName(lineinfo.getLineName());
                    }
                    GetLineInfoResDB.add(orderLineinfo);
                }
            }
            // objEGetFaInfoResD.setLinecapacity(objFactoryInfo.getLinecapacity());
            // objEGetFaInfoResD.setOEM(objFactoryInfo.isOEM());
            objEGetFaInfoResD.setOEMLine(GetLineInfoResDB);
            objEGetFaInfoResD.setCreator(objFactoryInfo.getCreator());
            objEGetFaInfoResD.setCreateTime(DateUtil.format(objFactoryInfo.getCreateTime()));
            objEGetFaInfoResD.setLastModifyMan(objFactoryInfo.getLastModifyMan());
            objEGetFaInfoResD.setLastModifyTime(DateUtil.format(objFactoryInfo.getLastModifyTime()));
            objEGetFaInfoResD.setRemark(objFactoryInfo.getRemark());
            // objEGetFaInfoResD.setLineList(GetLineInfoResDB);

            objEGetFaInfoResB.setData(objEGetFaInfoResD);
        }
        objEGetFaInfoRes.setBody(objEGetFaInfoResB);

        return objEGetFaInfoRes;
    }

    //dto. 保存线体
    public SaveLineInfoRes AddGetLineInfoRes(SaveLineInfoReqBD00 argSaveLineInfoReqBD00) {

        SaveLineInfoRes objESaveFaInfoRes = new SaveLineInfoRes();
        SaveLineInfoResB objESaveFaInfoResB = new SaveLineInfoResB();
        SaveLineInfoResD objESaveFaInfoResD = new SaveLineInfoResD();
        if (argSaveLineInfoReqBD00.getLineCode() == null || argSaveLineInfoReqBD00.getLineCode().equals("")) {
            throw new SystemException("", "请输入线体代码");
        }

        if (argSaveLineInfoReqBD00.getLineName() == null || "".equals(argSaveLineInfoReqBD00.getLineName())) {
            throw new SystemException("xxx", "新增失败，线体名称不能为空");
        }

        if (argSaveLineInfoReqBD00.getFaRd() <= 0) {
            throw new SystemException("xxx", "新增失败，工厂不能为空");
        }

        Lineinfo lineinfo = lineDao.SelectLineInfoByLineCode(argSaveLineInfoReqBD00.getLineCode());
        if (lineinfo != null) {
            throw new SystemException("xxx", "新增失败，线体代码已存在");
        }


        //创建线体实体类
        Lineinfo objFactoryInfo = new Lineinfo();
        objFactoryInfo.setGuid(CommonUtils.getRandomNumber());

        objFactoryInfo.setLineCode(argSaveLineInfoReqBD00.getLineCode());

        //查询所有，判断名称是否存在
        /*List<Lineinfo> objEFactoryInfo=lineDao.SelectAllLineInfo();
        for(Lineinfo obj:objEFactoryInfo){
            if(obj.getLineName().equals(argSaveLineInfoReqBD00.getLineName())){
                throw new SystemException("MG_MES3001112010001F","新增失败，线体名称已存在");
            }
        }*/

        objFactoryInfo.setLineName(argSaveLineInfoReqBD00.getLineName());
        //根据工厂标识 查询工厂信息

        FactoryInfo factoryInfo = factoryDAO.SelectFactoryInfoByruid(argSaveLineInfoReqBD00.getFaRd());
        if (factoryInfo != null) {
            objFactoryInfo.setFaGd(factoryInfo.getGuid());
        }

        //线体
        if (argSaveLineInfoReqBD00.getOEMLine() != null && argSaveLineInfoReqBD00.getOEMLine().size() > 0) {
            for (SaveOEMLineInfoReq00OEMLineList obj : argSaveLineInfoReqBD00.getOEMLine()) {

                OEMLineinfo objOEMLineinfo = new OEMLineinfo();
                Lineinfo lineinfoS = lineDao.SelectLineInfoBygruid(obj.getLineRd());
                if (lineinfoS != null) {
                        /*if (argSaveLineInfoReqBD00.getLineName().equals(lineinfo.getLineName())){
                            throw new SystemException("MG_MES3001112010002F","名称不能和线体代工名称相同");
                        }*/
                    objOEMLineinfo.setLineGD(lineinfoS.getGuid());
                    objOEMLineinfo.setOEMLineGD(objFactoryInfo.getGuid());
                    oEMLineDAO.InsertOEMLineinfo(objOEMLineinfo);
                }
            }
        }


        objFactoryInfo.setLineDes(argSaveLineInfoReqBD00.getLineDes());
        objFactoryInfo.setCreator(CommonUtils.readUser().getRealName());
        objFactoryInfo.setCreateTime(new Date());
        objFactoryInfo.setRemark(argSaveLineInfoReqBD00.getRemark());

        lineDao.InsertLineInfo(objFactoryInfo);


        objESaveFaInfoResB.setData(objESaveFaInfoResD);
        objESaveFaInfoRes.setBody(objESaveFaInfoResB);

        return objESaveFaInfoRes;
    }

    //dto复制
    public SaveLineInfoRes AddSaveLineInfoRes(SaveLineInfoReqBD03 argSaveLineInfoReqBD03) {
        SaveLineInfoRes objESaveFaInfoRes = new SaveLineInfoRes();
        SaveLineInfoResB objESaveFaInfoResB = new SaveLineInfoResB();
        SaveLineInfoResD objESaveFaInfoResD = new SaveLineInfoResD();

        //根据线体ID查询线体信息
        Lineinfo objFactoryInfo = lineDao.SelectLineInfoBygruid(argSaveLineInfoReqBD03.getLineRd());
        if (objFactoryInfo == null) {
            throw new SystemException("MG_MES3001112040001F", "查询线体信息失败");
        }

        objFactoryInfo.setGuid(CommonUtils.getRandomNumber());
        objFactoryInfo.setCreateTime(new Date());
        objFactoryInfo.setLastModifyTime(new Date());
        objFactoryInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        objFactoryInfo.setCreator(CommonUtils.readUser().getRealName());
        lineDao.InsertLineInfo(objFactoryInfo);
        String i = objFactoryInfo.getGuid();
        Lineinfo objEFactoryInfo = lineDao.SelectLineInfoByguid(objFactoryInfo.getGuid());
        if (objEFactoryInfo == null) {
            throw new SystemException("MG_MES3001112040003F", "查询线体信息失败");
        }

        objEFactoryInfo.setLineName(objEFactoryInfo.getLineName() + objEFactoryInfo.getRuid());
        if (lineDao.UpdateLineInfoByruid(objEFactoryInfo) <= 0) {
            throw new SystemException("MG_MES3001112040004F", "更新线体名称信息失败");
        }

        objESaveFaInfoResB.setData(objESaveFaInfoResD);
        objESaveFaInfoRes.setBody(objESaveFaInfoResB);

        return objESaveFaInfoRes;
    }

    //dto更新
    public SaveLineInfoRes ModSaveLineInfoRes(SaveLineInfoReqBD02 argSaveLineInfoReqBD02) {
        SaveLineInfoRes objESaveFaInfoRes = new SaveLineInfoRes();
        SaveLineInfoResB objESaveFaInfoResB = new SaveLineInfoResB();
        SaveLineInfoResD objESaveFaInfoResD = new SaveLineInfoResD();
        if (argSaveLineInfoReqBD02.getLineCode() == null && argSaveLineInfoReqBD02.getLineCode().equals("")) {
            throw new SystemException("", "请输入线体代码!");
        }

        if (argSaveLineInfoReqBD02.getLineName() == null || "".equals(argSaveLineInfoReqBD02.getLineName())) {
            throw new SystemException("xxx", "更新失败，线体名称不能为空");
        }

        if (argSaveLineInfoReqBD02.getFaRd() <= 0) {
            throw new SystemException("xxx", "更新失败，工厂不能为空");
        }


        //根据线体ID查询线体信息
        Lineinfo objFactoryInfo = lineDao.SelectLineInfoBygruid(argSaveLineInfoReqBD02.getLineRd());
        objFactoryInfo.setRuid(argSaveLineInfoReqBD02.getLineRd());


        //更新的线体名称不允许重复
        Lineinfo factoryInfoname = lineDao.SelectLineInfoByLineCode(argSaveLineInfoReqBD02.getLineCode());

        if (factoryInfoname != null && !factoryInfoname.getLineCode().equals(objFactoryInfo.getLineCode())) {
            throw new SystemException("MG_MES3001013030002F", "更新失败，线体代码已存在");
        }
        objFactoryInfo.setLineName(argSaveLineInfoReqBD02.getLineName());
        //根据工厂标识 查询工厂信息
        //修改线体代码
        objFactoryInfo.setLineCode(argSaveLineInfoReqBD02.getLineCode());
        FactoryInfo factoryInfo = factoryDAO.SelectFactoryInfoByruid(argSaveLineInfoReqBD02.getFaRd());
        if (factoryInfo != null) {
            objFactoryInfo.setFaGd(factoryInfo.getGuid());
        }

        objFactoryInfo.setLineDes(argSaveLineInfoReqBD02.getLineDes());

        objFactoryInfo.setLineName(argSaveLineInfoReqBD02.getLineName());
        objFactoryInfo.setRemark(argSaveLineInfoReqBD02.getRemark());
        //线 体
        List<OEMLineinfo> OEMLineList = oEMLineDAO.SelectOEMLineinfoByGui(objFactoryInfo.getGuid());
        if (OEMLineList != null && OEMLineList.size() > 0) {
            for (OEMLineinfo objOEML : OEMLineList) {
                if (oEMLineDAO.deleteOEMLineinfoByruid(objOEML.getRuid()) <= 0) {
                    throw new SystemException("xxx", "删除关联信息表失败");
                }
            }
        }
        if (argSaveLineInfoReqBD02.getOEMLine() != null && argSaveLineInfoReqBD02.getOEMLine().size() > 0) {

            for (SaveOEMLineinfoReq02OEMLine obj : argSaveLineInfoReqBD02.getOEMLine()) {

                OEMLineinfo objOEMLineinfo = new OEMLineinfo();
                Lineinfo lineinfoS = lineDao.SelectLineInfoBygruid(obj.getLineRd());
                if (lineinfoS != null) {
                        /*if (argSaveLineInfoReqBD00.getLineName().equals(lineinfo.getLineName())){
                            throw new SystemException("MG_MES3001112010002F","名称不能和线体代工名称相同");
                        }*/
                    objOEMLineinfo.setLineGD(lineinfoS.getGuid());
                    objOEMLineinfo.setOEMLineGD(objFactoryInfo.getGuid());
                    oEMLineDAO.InsertOEMLineinfo(objOEMLineinfo);
                }
            }


        }


        /*if(argSaveLineInfoReqBD02.getOEMLineList()!=null){
            for(SaveOEMLineinfoReq02OEMLine obj:argSaveLineInfoReqBD02.getOEMLineList()){
                OEMLineinfo oEMLineinfo = new OEMLineinfo();
               // oEMLineinfo.setLineGD(objFactoryInfo.getGuid());
                Lineinfo lineinfo=lineDao.SelectLineInfoBygruid(obj.getLineRd());
                if(lineinfo!=null){
                    if (argSaveLineInfoReqBD02.getLineName().equals(lineinfo.getLineName())){
                        throw new SystemException("MG_MES3001112010002F","名称不能和线体代工名称相同");
                    }
                    oEMLineinfo.setLineGD(lineinfo.getGuid());
                    oEMLineinfo.setOEMLineGD(objFactoryInfo.getGuid());
                }
                oEMLineDAO.InsertOEMLineinfo(oEMLineinfo);
            }
        }*/

        objFactoryInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        objFactoryInfo.setLastModifyTime(new Date());
        if (lineDao.UpdateLineInfoByruid(objFactoryInfo) <= 0) {
            throw new SystemException("MG_MES3001112030003F", "更新线体信息失败");
        }
        objESaveFaInfoResB.setData(objESaveFaInfoResD);
        objESaveFaInfoRes.setBody(objESaveFaInfoResB);

        return objESaveFaInfoRes;
    }

    //dto删除
    public SaveLineInfoRes RmSaveLineInfoRes(SaveLineInfoReqBD01 argSaveLineInfoReqBD01) {
        SaveLineInfoRes objESaveFaInfoRes = new SaveLineInfoRes();
        SaveLineInfoResB objESaveFaInfoResB = new SaveLineInfoResB();
        SaveLineInfoResD objESaveFaInfoResD = new SaveLineInfoResD();

        if (lineDao.DeleteLineInfoByruid(argSaveLineInfoReqBD01.getLineRd()) <= 0) {
            throw new SystemException("MG_MES3001112020001F", "删除线体信息失败");
        }
        objESaveFaInfoResB.setData(objESaveFaInfoResD);
        objESaveFaInfoRes.setBody(objESaveFaInfoResB);
        return objESaveFaInfoRes;
    }
}
