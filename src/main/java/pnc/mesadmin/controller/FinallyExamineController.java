package pnc.mesadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/FinallyExamine")
public class FinallyExamineController {
    @RequestMapping(value = "/finallyExaminePG")
    public String getcompanyinfoPage() {
        return "mprocess/finallyexamine/finallyexamine";
    }
}
