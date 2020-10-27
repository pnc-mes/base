package pnc.mesadmin.dto.newmove.NewAddInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class NewAddInfoRes {
    @JsonProperty("OutCode")
    private String OutCode;

    @JsonProperty("OutCodeDetail")
    private List<OutCodeDetail> OutCodeDetail;

    public static class OutCodeDetail{
        @JsonProperty("F1")
        private String F1;

        @JsonProperty("F2")
        private String F2;

        @JsonProperty("F3")
        private String F3;

        @JsonProperty("F4")
        private String F4;

        @JsonProperty("BarCode")
        private String BarCode;
        @JsonIgnore
        public String getF1() {
            return F1;
        }
        @JsonIgnore
        public void setF1(String f1) {
            F1 = f1;
        }
        @JsonIgnore
        public String getF2() {
            return F2;
        }
        @JsonIgnore
        public void setF2(String f2) {
            F2 = f2;
        }
        @JsonIgnore
        public String getF3() {
            return F3;
        }
        @JsonIgnore
        public void setF3(String f3) {
            F3 = f3;
        }
        @JsonIgnore
        public String getF4() {
            return F4;
        }
        @JsonIgnore
        public void setF4(String f4) {
            F4 = f4;
        }
        @JsonIgnore
        public String getBarCode() {
            return BarCode;
        }
        @JsonIgnore
        public void setBarCode(String barCode) {
            BarCode = barCode;
        }
    }
    @JsonIgnore
    public String getOutCode() {
        return OutCode;
    }
    @JsonIgnore
    public void setOutCode(String outCode) {
        OutCode = outCode;
    }
    @JsonIgnore
    public List<NewAddInfoRes.OutCodeDetail> getOutCodeDetail() {
        return OutCodeDetail;
    }
    @JsonIgnore
    public void setOutCodeDetail(List<NewAddInfoRes.OutCodeDetail> outCodeDetail) {
        OutCodeDetail = outCodeDetail;
    }
}
