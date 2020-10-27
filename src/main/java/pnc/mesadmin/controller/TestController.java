package pnc.mesadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/7/4 17:13
 * @Description:
 */
@RequestMapping("/report")
@Controller
public class TestController {

    @RequestMapping("/reportinfo")
    public String index(){
        return "report/reportinfo";
    }
}
