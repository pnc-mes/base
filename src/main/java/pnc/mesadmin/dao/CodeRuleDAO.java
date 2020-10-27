package pnc.mesadmin.dao;

import pnc.mesadmin.entity.CodeRuleInfo;

import java.util.List;

/**
 * Created by liufuzhi on 2018/1/5.
 */
public interface CodeRuleDAO {
    //查询代码生成规则列表
    List<CodeRuleInfo> SelectAllCodeRuleInfo();

    //查询代码生成信息
    CodeRuleInfo SelectCodeRuleInfocodeType(String codeType);

    //新增代码生成规则
    int  InsertCodeRuleInfo(CodeRuleInfo codeRuleInfo);

    //生成代码
    int UpdateCodeRuleInfoLastLevel(CodeRuleInfo codeRuleInfo);

    //更新生成代码规则信息
    int UpdateCodeRuleInfo(CodeRuleInfo codeRuleInfo);
}
