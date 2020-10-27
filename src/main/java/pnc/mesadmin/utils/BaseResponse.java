package pnc.mesadmin.utils;


import pnc.mesadmin.dto.BaseDto.BaseResB;

/**
 * @program: mesadmin
 * @description: 通用返回结果集
 * @author: yin.yang
 * @create: 2019-07-16
 **/
public class BaseResponse {
    private String status;
    private BaseResB body;

    public static BaseResponse SUCCESS = new BaseResponse();

    public BaseResponse() {
        BaseResB resB = new BaseResB();
        resB.setMsgCode("0x00000");
        resB.setMsgDes("成功！");
        this.setBody(resB);
        this.setStatus("00");
    }

    public static BaseResponse success(Object o) {
        BaseResponse response = new BaseResponse();
        BaseResB resB = response.getBody();
        resB.setData(o);
        return response;
    }

    public static BaseResponse error(String Code, String Msg) {
        BaseResponse response = new BaseResponse();
        BaseResB resB = new BaseResB();
        resB.setMsgCode(Code == null ? "EEEE" : Code);
        resB.setMsgDes(Msg == null ? "请求异常！" : Msg);
        response.setBody(resB);
        response.setStatus("01");
        return response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BaseResB getBody() {
        return body;
    }

    public void setBody(BaseResB body) {
        this.body = body;
    }
}
