package pnc.mesadmin.controller;

import com.alibaba.fastjson.JSONArray;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.BaseDto.BaseResB;
import pnc.mesadmin.dto.GetAllToolDevDto.GetToolDevRes;
import pnc.mesadmin.dto.GetAllToolDevDto.SaveToolDevRes00;
import pnc.mesadmin.dto.GetAllToolDevDto.SaveToolDevRes01;
import pnc.mesadmin.dto.SaveDevMaTainDto.SaveDevMaTainRes00;
import pnc.mesadmin.dto.SaveDevMaTainDto.SaveDevMaTainRes01;
import pnc.mesadmin.dto.SaveReq;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.service.DevMaTainService;
import pnc.mesadmin.service.ToolDevService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：设备维护Controller
 * 创建人：yinyang
 * 创建时间：2018-11-08
 * 修改人：
 * 修改时间：
 */

@org.springframework.stereotype.Controller
@Scope("prototype")
@RequestMapping("/DevMaTain")
public class DevMaTainController {

    @Resource
    private DevMaTainService service;


    //设备维修、设备更换界面点击保存时需要调用此接口
    @RequestMapping(value = "/SaveDevOpt", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes SaveDevOpt(SaveReq saveReq) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();

        try {
            if ("00".equals(saveReq.getExecType())) {
                SaveDevMaTainRes00 request = CommonUtils.switchClass(SaveDevMaTainRes00.class, saveReq.getBusData());
                baseResponse = service.AddSaveDevOpt00(request);
            } else if ("01".equals(saveReq.getExecType())) {
                List<SaveDevMaTainRes01> res01 = (List<SaveDevMaTainRes01>) JSONArray.parseArray(saveReq.getBusData(), SaveDevMaTainRes01.class);
                baseResponse = service.AddSaveDevOpt01(res01);
            }

        } catch (SystemException e) {
            baseResBody.setMsgCode(e.getMsgCode());
            baseResBody.setMsgDes(e.getMsgDes());
            baseResponse.setBody(baseResBody);
        }
        baseResponse.setStatus("00");
        return baseResponse;
    }
}
