package pnc.mesadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.CommonPageInfo.CommonPageData;
import pnc.mesadmin.entity.vo.CommonPageVo;
import pnc.mesadmin.service.CommonPageIService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/2/14 16:01
 * @Description:
 */
@Controller
@RequestMapping("/Zdjlcx")
public class CommonPageController {
    @Resource
    private CommonPageIService commonPageIService;
    //站点记录查询页面
    @RequestMapping("/ZdjlcxPG")
    public String zdjlcx(){
        return "commonpage/commonpageinfo";
    }


    @RequestMapping(value = "/getAllCommonPageInfo",method = RequestMethod.POST)
    @ResponseBody
    public List<CommonPageData> selectAll(@RequestBody CommonPageVo commonPageVo){
        return  commonPageIService.selectAllcommonPageDataMap(commonPageVo);
    }





}
