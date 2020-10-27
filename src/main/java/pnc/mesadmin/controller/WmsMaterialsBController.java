package pnc.mesadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.dto.WmsMaterialsBDTO.*;
import pnc.mesadmin.service.WmsMaterialsBService;
import pnc.mesadmin.utils.BaseResponse;

import javax.annotation.Resource;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：原材料批次控制器
 * 创建人：潘俊峰
 * 创建时间：2019-11-01
 * 修改人：
 * 修改时间：
 */
@Controller
@RequestMapping("/Wms/MaterailsB")
public class WmsMaterialsBController {

    @Resource
    private WmsMaterialsBService wmsMaterialsBService;

    //原材料批次创建
    @RequestMapping(value = "/Add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse AddMaterialsB(@RequestBody WmsMaterialsBAddReq req) {
        try {
            wmsMaterialsBService.AddMaterialsB(req);
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }

        return BaseResponse.success(null);
    }


    //原材料批次拆分
    @RequestMapping(value = "/Split", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse AddSplitBatch(@RequestBody WmsMaterialsBSplitReq req) {
        try {
            wmsMaterialsBService.AddSplitBatch(req);
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }

        return BaseResponse.success(null);
    }

    //原材料批次查询
    @RequestMapping(value = "/GetSplitB", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetSplitBatch(@RequestBody WmsMaterialsBSplitReq req) {
        try {
            return BaseResponse.success(wmsMaterialsBService.GetSplitBatch(req.getBatch()));
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }
}
