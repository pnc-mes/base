package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.CodeRuleDAO;
import pnc.mesadmin.dao.SupplierDAO;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllSupInfo.GetAllSupInfoRes;
import pnc.mesadmin.dto.GetAllSupInfo.GetAllSupInfoResB;
import pnc.mesadmin.dto.GetAllSupInfo.GetAllSupInfoResD;
import pnc.mesadmin.dto.GetSupInfo.GetSupInfoRes;
import pnc.mesadmin.dto.GetSupInfo.GetSupInfoResB;
import pnc.mesadmin.dto.GetSupInfo.GetSupInfoResD;
import pnc.mesadmin.dto.SaveSupInfo.*;
import pnc.mesadmin.entity.CodeRuleInfo;
import pnc.mesadmin.entity.SupplierInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.GConfigIService;
import pnc.mesadmin.service.SupplierIService;
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
 * 子系统名称：供应商信息Service实现类
 * 创建人：刘福志
 * 创建时间：2017-5-26
 * 修改人：
 * 修改时间：
 */
@Transactional
@Service
public class SupplierService implements SupplierIService {

    @Resource
    private SupplierDAO supplierInfoMapper;

    @Resource
    private BaseIService baseIService;

    @Resource
    private CodeRuleDAO codeRuleDAO;

    @Resource
    private GConfigIService gConfigIService;

    //查询供应商列表信息
    public GetAllSupInfoRes QALLselectAllSupplierInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException {
        GetAllSupInfoRes objEGetAllSupInfoRes = new GetAllSupInfoRes();

        GetAllSupInfoResB body = new GetAllSupInfoResB();

        List<GetAllSupInfoResD> dataList =  new ArrayList<GetAllSupInfoResD>();

        // 获取供应商信息列表信息
        List<SupplierInfo> supplierInfoList = baseIService.QALLBaseInfo(SupplierDAO.class, "SelectAllSupplierInfo",
                argInitDataFields, argPageInfo);//supplierInfoMapper.SelectAllSupplierInfo();

        if(supplierInfoList!=null || supplierInfoList.size()>0) {
            GetAllSupInfoResD data = null;

            // 循环赋值查询所有
            for (int i = 0; i < supplierInfoList.size(); i++) {
                data = new GetAllSupInfoResD();
                data.setSupRd(supplierInfoList.get(i).getRuid());
                data.setSupCode(supplierInfoList.get(i).getSupplierCode());
                data.setSupName(supplierInfoList.get(i).getSupplierName());
                dataList.add(data);
            }
        }

        body.setData(dataList);
        objEGetAllSupInfoRes.setBody(body);

        return objEGetAllSupInfoRes;
    }

    /**
     * 查询供应商列表信息(新)
     * @param req
     * @return
     */
    @Override
    public PageResult<GetAllSupInfoResD> QALLSupplierNew(BaseRequest req) {
        List<GetAllSupInfoResD> resDList =  new ArrayList<GetAllSupInfoResD>();
        GetAllSupInfoResD resD = null;

        IPage<SupplierInfo> iPage = supplierInfoMapper.selectPage(new MyPage(req), CommonUtils.getQueryWrapper(req.getFields()));

        // 循环赋值查询所有
        for (SupplierInfo obj : iPage.getRecords()) {
            resD = new GetAllSupInfoResD();
            resD.setSupRd(obj.getRuid());
            resD.setSupCode(obj.getSupplierCode());
            resD.setSupName(obj.getSupplierName());
            resDList.add(resD);
        }

        return new PageResult<>(iPage, resDList);
    }

    //查询供应商信息
    public GetSupInfoRes GetselectBySuppId(int supRd) throws SystemException {
        GetSupInfoRes objEGetSupInfoRes = new  GetSupInfoRes();

        GetSupInfoResB body = new GetSupInfoResB();

        GetSupInfoResD data = new GetSupInfoResD();

        // 获取供应商信息
        SupplierInfo supplierInfo =supplierInfoMapper.SelectBySuppId(supRd);

        if(supplierInfo!=null) {
            // 赋值
            data.setSupRd(supplierInfo.getRuid());
            data.setSupCode(supplierInfo.getSupplierCode());
            data.setSupName(supplierInfo.getSupplierName());
            data.setSupFName(supplierInfo.getSupplierFName());
            data.setContactor(supplierInfo.getContactor());
            data.setMobile(supplierInfo.getMobile());
            data.setAddress(supplierInfo.getAddress());
            data.setDSource(supplierInfo.getdSource());
            data.setStatus(supplierInfo.getStatus());
            data.setCreator(supplierInfo.getCreator());
            data.setCreateTime(DateUtil.format(supplierInfo.getCreateTime()));
            data.setLastModifyMan(supplierInfo.getLastModifyMan());
            data.setLastModifyTime(DateUtil.format(supplierInfo.getLastModifyTime()));
            data.setRemark(supplierInfo.getRemark());
        }

        body.setData(data);
        objEGetSupInfoRes.setBody(body);

        return objEGetSupInfoRes;
    }

    //保存供应商信息
    public SaveSupInfoRes AddinsertSupplierInfo(SaveSupInfoReqBD00 busData00, SupplierInfo supplierInfo) throws SystemException {
        SaveSupInfoRes saveSupInfoRes = new SaveSupInfoRes();

        SaveSupInfoResB body = new SaveSupInfoResB();

        SaveSupInfoResD data = new SaveSupInfoResD();

        //查询供应商名称
        List<SupplierInfo> objESupplierInfos=supplierInfoMapper.SelectAllSupplierInfo();

        // 赋值新增一条供应商信息
        supplierInfo.setGuid(CommonUtils.getRandomNumber());

        //逻辑校验保存的供应商名称不能相同
        for (SupplierInfo obj:objESupplierInfos){
            if (obj.getSupplierName().equals(busData00.getSupName())){
                throw new SystemException("MG0006F","供应商名称已存在");
            }
            if (!"".equals(busData00.getSupCode())&&obj.getSupplierCode().equals(busData00.getSupCode())){
                throw new SystemException("MG0006F","供应商代码已存在");
            }
        }

        //查询代码生成
        CodeRuleInfo codeRuleInfo=codeRuleDAO.SelectCodeRuleInfocodeType("02");

        String SCode="";

        if(!"".equals(busData00.getSupCode())) {
            supplierInfo.setSupplierCode(busData00.getSupCode());
        }else if(codeRuleInfo!=null && "00".equals(codeRuleInfo.getStatus())){
            SCode=gConfigIService.GetCreateSC(codeRuleInfo);
            supplierInfo.setSupplierCode(SCode);
        }else{
            throw new SystemException("", "请输入{供应商代码}，或请到全局配置进行代码生成配置");
        }

        supplierInfo.setSupplierName(busData00.getSupName());
        supplierInfo.setSupplierFName(busData00.getSupFName());
        supplierInfo.setContactor(busData00.getContactor());
        supplierInfo.setMobile(busData00.getMobile());
        supplierInfo.setAddress(busData00.getAddress());
        supplierInfo.setStatus(busData00.getStatus());
        supplierInfo.setdSource("01");
        supplierInfo.setCreator(CommonUtils.readUser().getRealName());
        supplierInfo.setCreateTime(new Date());
        supplierInfo.setRemark(busData00.getRemark());

        // 保存
        supplierInfoMapper.InsertSupplierInfo(supplierInfo);

        SupplierInfo supplierInfo1=supplierInfoMapper.SelectByGuid(supplierInfo.getGuid());

        data.setSupRd(supplierInfo1.getRuid());
        data.setSupCode(supplierInfo1.getSupplierCode());

        body.setData(data);
        saveSupInfoRes.setBody(body);

        return saveSupInfoRes;
    }

    //更新供应商信息
    public SaveSupInfoRes ModupdateSupplierInfo(SaveSupInfoReqBD02 busData02, SupplierInfo supplierInfo) throws SystemException {
        SaveSupInfoRes saveSupInfoRes = new SaveSupInfoRes();

        SaveSupInfoResB body = new SaveSupInfoResB();

        SaveSupInfoResD data = new SaveSupInfoResD();

        SupplierInfo objEsupplierInfo =supplierInfoMapper.SelectBySuppId(busData02.getSupRd());

        if (objEsupplierInfo==null){
            throw new SystemException("","查询供应商信息为空");
        }

        //查询供应商名称
        SupplierInfo objESupplierInfos=supplierInfoMapper.SelectBysupplierName(busData02.getSupName());

        if(objESupplierInfos!=null&&!objESupplierInfos.getSupplierName().equals(objEsupplierInfo.getSupplierName())){
            throw new SystemException("MG0006F","更新失败，供应商名称已存在");
        }

        if("00".equals(objEsupplierInfo.getdSource())){
            throw new SystemException("","为外部数据不能更新");
        }

        // 赋值更新供应商信息
        supplierInfo.setRuid(busData02.getSupRd());
        supplierInfo.setSupplierName(busData02.getSupName());
        supplierInfo.setSupplierFName(busData02.getSupFName());
        supplierInfo.setContactor(busData02.getContactor());
        supplierInfo.setMobile(busData02.getMobile());
        supplierInfo.setAddress(busData02.getAddress());
        supplierInfo.setStatus(busData02.getStatus());
        supplierInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        supplierInfo.setLastModifyTime(new Date());
        supplierInfo.setRemark(busData02.getRemark());

        // 更新供应商信息
        int count = supplierInfoMapper.UpdateSupplierInfo(supplierInfo);

        if(count <=0) throw new SystemException("","更新供应商信息失败！");

        body.setData(data);
        saveSupInfoRes.setBody(body);

        return saveSupInfoRes;
    }

    //复制供应商信息
    public SaveSupInfoRes copySupplierInfo(SaveSupInfoReqBD03 busData03, SupplierInfo supplierInfo) throws SystemException {
        SaveSupInfoRes saveSupInfoRes = new SaveSupInfoRes();

        SaveSupInfoResB body = new SaveSupInfoResB();

        SaveSupInfoResD data = new SaveSupInfoResD();

        //查询供应商信息
        SupplierInfo objEsupplierInfo =supplierInfoMapper.SelectBySuppId(busData03.getSupRd());

        if (objEsupplierInfo==null){
            throw new SystemException("","查询供应商信息为空");
        }

        // 复制一条供应商信息
        supplierInfo.setGuid(CommonUtils.getRandomNumber());
        supplierInfo.setSupplierCode(objEsupplierInfo.getSupplierCode());
        supplierInfo.setSupplierName(objEsupplierInfo.getSupplierName());
        supplierInfo.setSupplierFName(objEsupplierInfo.getSupplierFName());
        supplierInfo.setContactor(objEsupplierInfo.getContactor());
        supplierInfo.setMobile(objEsupplierInfo.getMobile());
        supplierInfo.setAddress(objEsupplierInfo.getAddress());
        supplierInfo.setStatus(objEsupplierInfo.getStatus());
        supplierInfo.setdSource(objEsupplierInfo.getdSource());
        supplierInfo.setCreator(CommonUtils.readUser().getRealName());
        supplierInfo.setCreateTime(new Date());
        supplierInfo.setRemark(objEsupplierInfo.getRemark());

        // 调用供应商sql复制一条数据
        int count = supplierInfoMapper.InsertSupplierInfo(supplierInfo);

        if(count <=0) throw new SystemException("","复制供应商信息失败！");

        //查询供应商信息
        SupplierInfo objEsupplierInfos =supplierInfoMapper.SelectByGuid(supplierInfo.getGuid());

        objEsupplierInfos.setSupplierName(objEsupplierInfos.getSupplierName()+objEsupplierInfos.getRuid());

        if (supplierInfoMapper.UpdateSupplierInfo(objEsupplierInfos)<=0){
            throw new SystemException("","复制供应商信息失败！");
        }

        body.setData(data);
        saveSupInfoRes.setBody(body);

        return saveSupInfoRes;
    }

    //删除供应商信息
    public SaveSupInfoRes RmdeleteSupplierInfo(int ruid) throws SystemException {
        SaveSupInfoRes saveSupInfoRes = new SaveSupInfoRes();

        SaveSupInfoResB body = new SaveSupInfoResB();

        SaveSupInfoResD data = new SaveSupInfoResD();

        SupplierInfo objEsupplierInfo =supplierInfoMapper.SelectBySuppId(ruid);

        if("00".equals(objEsupplierInfo.getdSource())){
            throw new SystemException("","为外部数据不能更新");
        }

        // 删除供应商信息
        int count = supplierInfoMapper.DeleteSupplierInfo(ruid);

        if(count <=0) throw new SystemException("MG000001","删除供应商信息失败！");

        body.setData(data);
        saveSupInfoRes.setBody(body);

        return saveSupInfoRes;
    }
}
