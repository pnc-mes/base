package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.GetAllReq;
import pnc.mesadmin.dto.GetWfVTreeInfo.GetWfVTreeInfoRes;
import pnc.mesadmin.dto.SaveRePrInfo.SaveRePrInfoReqBD00;
import pnc.mesadmin.dto.SaveRePrInfo.SaveRePrInfoReqBD01;
import pnc.mesadmin.dto.SaveReq;
import pnc.mesadmin.dto.SaveUnitInfo.SaveUnitInfoRes;
import pnc.mesadmin.dto.SaveUnitInfo.SaveUnitInfoResB;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.service.HisPrintLogIService;
import pnc.mesadmin.utils.BaseResponse;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：打印记录查询Controller
 * 创建人：蒋鑫韡
 * 创建时间：2020-10-13
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/HisPrintLog")
public class HisPrintLogController {
    @Resource
    private HisPrintLogIService hisPrintLogIService;

    //获取打印记录查询页面
    @RequestMapping("/HisPrintLogPG")
    public String HisPrint() { return "base/printlog/printlog"; }

    //获取打印记录
    @RequestMapping(value = "/GetPrintTaskInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetPrintTaskInfo(HttpServletRequest request, @RequestBody BaseRequest req) {
        try {
            return BaseResponse.success(hisPrintLogIService.QALLPrintTaskInfo(req));
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }
    //重打打印记录
    @RequestMapping(value = "/SaveRePrintLogInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse SaveRePrintLogInfo(@RequestBody SaveRePrInfoReqBD01 request){
        try {
            hisPrintLogIService.AddRePr(request);
        }catch (SystemException e){
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
        return BaseResponse.success(null);
    }
}
