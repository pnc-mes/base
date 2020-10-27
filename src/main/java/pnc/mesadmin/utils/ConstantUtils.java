package pnc.mesadmin.utils;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/12/27 19:03
 * @Description:常量工具类
 */
public class ConstantUtils implements Serializable {

    //原材料批次状态返回
    public static String resultMaterialStatus(String string){
        String result="";

        if("01".equals(string)){
            result="上料冻结";
        }else if("02".equals(string)){
            result="采购退货冻结";
        }else if("03".equals(string)){
            result="生产退料冻结";
        }else if("04".equals(string)){
            result="消耗完作废";
        } else if("05".equals(string)){
            result="来料入库审核冻结";
        }else if("07".equals(string)){
            result="工单领料冻结";
        }else if("08".equals(string)){
            result="调拨出冻结";
        }else if("09".equals(string)){
            result="调拨入冻结";
        }
        return result;
    }

    //成品箱号状态返回
    public static String resultPackStatus(String string){
        String result="";

        if("01".equals(string)){
            result="成品入库冻结";
        }else if("02".equals(string)){
            result="成品出库冻结";
        }else if("03".equals(string)){
            result="成品调拨入冻结";
        }else if("04".equals(string)){
            result="成品调拨出冻结";
        }else if("05".equals(string)){
            result="返工工单冻结";
        }else if("06".equals(string)){
            result="箱号手动冻结";
        }
        return result;
    }

}
