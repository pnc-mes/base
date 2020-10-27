package pnc.mesadmin.dto.BaseDto;


import java.io.Serializable;

public class BaseResB implements Serializable {
    private String msgCode;
    private String msgDes;
    private Object data;

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public String getMsgDes() {
        return msgDes;
    }

    public void setMsgDes(String msgDes) {
        this.msgDes = msgDes;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
