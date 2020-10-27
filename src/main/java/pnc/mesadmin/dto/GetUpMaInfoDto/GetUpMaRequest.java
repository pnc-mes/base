package pnc.mesadmin.dto.GetUpMaInfoDto;

/**
 * @program: mesadmin
 * @description: 物料有效期管控/上料
 * @author: yin.yang
 * @create: 2019-06-04
 **/
public class GetUpMaRequest {
    private String batch;

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }
}
