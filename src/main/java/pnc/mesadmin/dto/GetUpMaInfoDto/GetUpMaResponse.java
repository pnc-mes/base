package pnc.mesadmin.dto.GetUpMaInfoDto;

/**
 * @program: mesadmin
 * @description: 物料有效期管控/上料-返回参数
 * @author: yin.yang
 * @create: 2019-06-04
 **/
public class GetUpMaResponse {
    private String batch;
    private String periodTime;
    private TqInfo tqInfo;
    private GqInfo gqInfo;

    public static class TqInfo {
        private String tqTimer;
        private String tqRevMail;
        private String tqRevContent;

        public String getTqTimer() {
            return tqTimer;
        }

        public void setTqTimer(String tqTimer) {
            this.tqTimer = tqTimer;
        }

        public String getTqRevMail() {
            return tqRevMail;
        }

        public void setTqRevMail(String tqRevMail) {
            this.tqRevMail = tqRevMail;
        }

        public String getTqRevContent() {
            return tqRevContent;
        }

        public void setTqRevContent(String tqRevContent) {
            this.tqRevContent = tqRevContent;
        }
    }

    public static class GqInfo {
        private String gqRevMail;
        private String gqRevContent;

        public String getGqRevMail() {
            return gqRevMail;
        }

        public void setGqRevMail(String gqRevMail) {
            this.gqRevMail = gqRevMail;
        }

        public String getGqRevContent() {
            return gqRevContent;
        }

        public void setGqRevContent(String gqRevContent) {
            this.gqRevContent = gqRevContent;
        }
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getPeriodTime() {
        return periodTime;
    }

    public void setPeriodTime(String periodTime) {
        this.periodTime = periodTime;
    }

    public TqInfo getTqInfo() {
        return tqInfo;
    }

    public void setTqInfo(TqInfo tqInfo) {
        this.tqInfo = tqInfo;
    }

    public GqInfo getGqInfo() {
        return gqInfo;
    }

    public void setGqInfo(GqInfo gqInfo) {
        this.gqInfo = gqInfo;
    }
}
