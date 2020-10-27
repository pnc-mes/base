package pnc.mesadmin.dto.ExecActionDto;

public class ExecActionResponseDto {
    // 00#进站、01#上机、02#下机、03#出站、04#打印标签、05#解绑载具、06#漏刷,07#上料,08#下料
    private String runType;
    //执行结果 00#成功   01#失败
    private String msgCode;
    //执行结果信息
    private String msgData;

    public String getRunType() {
        return runType;
    }

    public void setRunType(String runType) {
        this.runType = runType;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public String getMsgData() {
        return msgData;
    }

    public void setMsgData(String msgData) {
        this.msgData = msgData;
    }

}
