package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.CustomerDAO;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllCusInfo.GetAllCusInfoRes;
import pnc.mesadmin.dto.GetAllCusInfo.GetAllCusInfoResB;
import pnc.mesadmin.dto.GetAllCusInfo.GetAllCusInfoResD;
import pnc.mesadmin.dto.GetCusInfo.GetCusInfoRes;
import pnc.mesadmin.dto.GetCusInfo.GetCusInfoResB;
import pnc.mesadmin.dto.GetCusInfo.GetCusInfoResD;
import pnc.mesadmin.dto.SaveCusInfo.*;
import pnc.mesadmin.entity.CustomerInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.CustomerIService;
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
 * 子系统名称：客户信息Service实现类
 * 创建人：刘福志
 * 创建时间：2017-5-26
 * 修改人：
 * 修改时间：
 */
@Transactional
@Service
public class CustomerService implements CustomerIService {

    @Resource
    private CustomerDAO customerInfoMapper;

    @Resource
    private BaseIService baseIService;

    @Override
    public PageResult<GetAllCusInfoRes> QALLGetAllCusInfoRes(BaseRequest req) {


        GetAllCusInfoRes objEGetAllCusInfoRes = new GetAllCusInfoRes();

        GetAllCusInfoResB body = new GetAllCusInfoResB();


        List<GetAllCusInfoResD> dataList =  new ArrayList<GetAllCusInfoResD>();
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<CustomerInfo> page = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(req.getCurrent(), req.getSize());

        IPage<CustomerInfo> woInfoIPage = customerInfoMapper.selectPage(new MyPage(req), CommonUtils.getQueryWrapper(req.getFields()));


        List<CustomerInfo> customerInfoList = woInfoIPage.getRecords();
        GetAllCusInfoResD data = null;
        // 判断返回数据采集信息列表是否为空
        if (customerInfoList != null&&customerInfoList.size()>=1 ) {
            for (int i = 0 ; i < customerInfoList.size() ; i ++){
                data = new GetAllCusInfoResD();

                data.setCusRd(customerInfoList.get(i).getRuid());
                data.setCusName(customerInfoList.get(i).getCustomerName());
                data.setContactor(customerInfoList.get(i).getCreator());
                data.setMobile(customerInfoList.get(i).getMobile());
                data.setAddress(customerInfoList.get(i).getAddress());

                dataList.add(data);
            }
        }
        body.setData(dataList);
        objEGetAllCusInfoRes.setBody(body);


        return new PageResult(woInfoIPage.getTotal(), woInfoIPage.getPages(), woInfoIPage.getCurrent(), woInfoIPage.getSize(), dataList);
    }

    //查询客户列表信息
    public GetAllCusInfoRes QALLselectAllCustomerInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException {
        GetAllCusInfoRes objEGetAllCusInfoRes = new GetAllCusInfoRes();

        GetAllCusInfoResB body = new GetAllCusInfoResB();

        List<GetAllCusInfoResD> dataList =  new ArrayList<GetAllCusInfoResD>();

        // 获取客户信息列表
        List<CustomerInfo> customerInfoList = baseIService.QALLBaseInfo(CustomerDAO.class, "SelectAllCustomerInfo",
                argInitDataFields, argPageInfo);

        if(customerInfoList!=null || customerInfoList.size()>0) {

            GetAllCusInfoResD data = null;

            // 循环赋值查询客户列表信息
            for (int i = 0; i < customerInfoList.size(); i++) {
                data = new GetAllCusInfoResD();

                data.setCusRd(customerInfoList.get(i).getRuid());
                data.setCusName(customerInfoList.get(i).getCustomerName());
                data.setContactor(customerInfoList.get(i).getCreator());
                data.setMobile(customerInfoList.get(i).getMobile());
                data.setAddress(customerInfoList.get(i).getAddress());

                dataList.add(data);
            }
        }

        body.setData(dataList);
        objEGetAllCusInfoRes.setBody(body);

        return objEGetAllCusInfoRes;
    }

    //查询客户信息
    public GetCusInfoRes GetselectByRuid(int cusRd) throws SystemException {
        GetCusInfoRes objEGetCusInfoRes = new  GetCusInfoRes();

        GetCusInfoResB body = new GetCusInfoResB();

        GetCusInfoResD data = new GetCusInfoResD();

        // 获取客户信息
        CustomerInfo customerInfo =customerInfoMapper.SelectByRuid(cusRd);

        if(customerInfo!=null) {
            // 赋值查询客户信息
            data.setCusRd(customerInfo.getRuid());
            data.setCusName(customerInfo.getCustomerName());
            data.setContactor(customerInfo.getContactor());
            data.setMobile(customerInfo.getMobile());
            data.setAddress(customerInfo.getAddress());
            data.setCreator(customerInfo.getCreator());
            data.setCreateTime(DateUtil.format(customerInfo.getCreateTime()));
            data.setLastModifyMan(customerInfo.getLastModifyMan());
            data.setLastModifyTime(DateUtil.format(customerInfo.getLastModifyTime()));
            data.setRemark(customerInfo.getRemark());
        }

        body.setData(data);
        objEGetCusInfoRes.setBody(body);

        return objEGetCusInfoRes;
    }

    //保存客户信息
    public SaveCusInfoRes AddinsertCustomerInfo(SaveCusInfoReqBD00 busData00, CustomerInfo customerInfo) throws SystemException {
        SaveCusInfoRes saveCusInfoRes = new SaveCusInfoRes();

        SaveCusInfoResB body = new SaveCusInfoResB();

        SaveCusInfoResD data = new SaveCusInfoResD();

        if(busData00.getCusName()==null||"".equals(busData00.getCusName())){
            throw new SystemException("xxxx","新增失败，名称不能为空");
        }
        if(busData00.getContactor()==null||"".equals(busData00.getContactor())){
            throw new SystemException("xxxx","新增失败，联系人不能为空");
        }
        if(busData00.getAddress()==null||"".equals(busData00.getAddress())){
            throw new SystemException("xxxx","新增失败，地址不能为空");
        }


        //查询客户名称
        List<CustomerInfo> objECustomerInfos=customerInfoMapper.SelectAllCustomerInfo();

        // 赋值新增一条客户信息
        customerInfo.setGuid(CommonUtils.getRandomNumber());

        //逻辑校验保存的客户名称不能相同
        for (CustomerInfo obj:objECustomerInfos){
            if (obj.getCustomerName().equals(busData00.getCusName())){
                throw new SystemException("MG0006F","客户名称已存在");
            }
        }

        customerInfo.setCustomerName(busData00.getCusName());
        customerInfo.setContactor(busData00.getContactor());
        customerInfo.setMobile(busData00.getMobile());

        //赋值新增一条客户信息
        customerInfo.setAddress(busData00.getAddress());
        customerInfo.setCreator(CommonUtils.readUser().getRealName());
        customerInfo.setCreateTime(new Date());
        customerInfo.setRemark(busData00.getRemark());

        // 保存
        customerInfoMapper.InsertCustomerInfo(customerInfo);

        body.setData(data);
        saveCusInfoRes.setBody(body);

        return saveCusInfoRes;
    }

    //更新客户信息
    public SaveCusInfoRes ModupdateCustomerInfo(SaveCusInfoReqBD02 busData02, CustomerInfo customerInfo) throws SystemException {
        SaveCusInfoRes saveCusInfoRes = new SaveCusInfoRes();

        SaveCusInfoResB body = new SaveCusInfoResB();

        SaveCusInfoResD data = new SaveCusInfoResD();
        if(busData02.getCusName()==null||"".equals(busData02.getCusName())){
            throw new SystemException("xxxx","更新失败，名称不能为空");
        }
        if(busData02.getContactor()==null||"".equals(busData02.getContactor())){
            throw new SystemException("xxxx","更新失败，联系人不能为空");
        }
        if(busData02.getAddress()==null||"".equals(busData02.getAddress())){
            throw new SystemException("xxxx","更新失败，地址不能为空");
        }


        // 获取客户信息
        CustomerInfo objecustomerInfo =customerInfoMapper.SelectByRuid(busData02.getCusRd());

        // 判断返回客户是否为空
        if (customerInfo == null){
            throw new SystemException("MG_MES3001311010001F", "查询客户信息为空！");
        }

        //查询供应商名称
        CustomerInfo objECustomerInfo=customerInfoMapper.SelectBycustomerName(busData02.getCusName());

        if(objECustomerInfo!=null && !objECustomerInfo.getCustomerName().equals(objecustomerInfo.getCustomerName())){
            throw new SystemException("MG0006F","更新失败，客户名称已存在");
        }

        // 赋值更新客户信息
        customerInfo.setRuid(busData02.getCusRd());
        customerInfo.setCustomerName(busData02.getCusName());
        customerInfo.setContactor(busData02.getContactor());
        customerInfo.setMobile(busData02.getMobile());
        customerInfo.setAddress(busData02.getAddress());
        customerInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        customerInfo.setLastModifyTime(new Date());
        customerInfo.setRemark(busData02.getRemark());
        // 更新
        int count = customerInfoMapper.UpdateCustomerInfo(customerInfo);

        if(count <=0) throw new SystemException("MG_MES3001312030002F","更新客户信息失败！");

        body.setData(data);
        saveCusInfoRes.setBody(body);

        return saveCusInfoRes;
    }

    //复制客户信息
    public SaveCusInfoRes copyCustomerInfo(SaveCusInfoReqBD03 busData03, CustomerInfo customerInfo) throws SystemException {
        SaveCusInfoRes saveCusInfoRes = new SaveCusInfoRes();

        SaveCusInfoResB body = new SaveCusInfoResB();

        SaveCusInfoResD data = new SaveCusInfoResD();

        customerInfo =customerInfoMapper.SelectByRuid(busData03.getCusRd());

        if (customerInfo==null){
            throw new SystemException("MG_MES3001311010001F","查询客户信息为空！");
        }

        // 赋值复制一条客户信息
        customerInfo.setGuid(CommonUtils.getRandomNumber());
        customerInfo.setCustomerName(customerInfo.getCustomerName());
        customerInfo.setContactor(customerInfo.getContactor());
        customerInfo.setMobile(customerInfo.getMobile());
        customerInfo.setAddress(customerInfo.getAddress());
        customerInfo.setCreator(CommonUtils.readUser().getRealName());
        customerInfo.setCreateTime(new Date());
        customerInfo.setRemark(customerInfo.getRemark());

        // 调用客户信息sql复制一条数据
        int count = customerInfoMapper.InsertCustomerInfo(customerInfo);

        if(count <=0) throw new SystemException("MG_MES3001312040003F","复制客户信息失败！");

        //查询客户信息
        CustomerInfo objEcustomerInfo =customerInfoMapper.SelectByGuid(customerInfo.getGuid());

        objEcustomerInfo.setCustomerName(objEcustomerInfo.getCustomerName()+objEcustomerInfo.getRuid());

        if (customerInfoMapper.UpdateCustomerInfo(objEcustomerInfo)<=0){
            throw new SystemException("MG_MES3001312030002F","复制客户信息失败！");
        }


        body.setData(data);
        saveCusInfoRes.setBody(body);

        return saveCusInfoRes;
    }

    //删除客户信息
    public SaveCusInfoRes RmdeleteCustomerInfo(int ruid) throws SystemException {
        SaveCusInfoRes saveSupInfoRes = new SaveCusInfoRes();

        SaveCusInfoResB body = new SaveCusInfoResB();

        SaveCusInfoResD data = new SaveCusInfoResD();

        // 调用客户信息sql，删除信息
        int count = customerInfoMapper.DeleteCustomerInfo(ruid);

        if(count <=0) throw new SystemException("MG_MES3001312020001F","删除客户信息失败！");

        body.setData(data);
        saveSupInfoRes.setBody(body);

        return saveSupInfoRes;
    }

}
