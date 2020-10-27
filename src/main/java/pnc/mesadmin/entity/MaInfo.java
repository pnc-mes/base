package pnc.mesadmin.entity;

import java.io.Serializable;

/**
 * Created by test on 2017/9/22.
 */
public class MaInfo implements Serializable {
    private String maVerGd;
    private float num;

    public String getMaVerGd() {
        return maVerGd;
    }

    public void setMaVerGd(String maVerGd) {
        this.maVerGd = maVerGd;
    }

    public float getNum() {
        return num;
    }

    public void setNum(float num) {
        this.num = num;
    }
}
