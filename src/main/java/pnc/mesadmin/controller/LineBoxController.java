package pnc.mesadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/LineBox")
public class LineBoxController {
    @RequestMapping(value = "/lineBoxPG")
    public String getcompanyinfoPage() {
        return "mprocess/lineBox/lineBox";
    }
}
