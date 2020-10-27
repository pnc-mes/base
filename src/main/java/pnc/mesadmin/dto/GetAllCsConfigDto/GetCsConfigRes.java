package pnc.mesadmin.dto.GetAllCsConfigDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @program: mesadmin
 * @description: 通用查询DTO
 * @author: yin.yang
 * @create: 2018-11-20
 **/
public class GetCsConfigRes {
    @JsonProperty("CSConfigRd")
    private Integer CSConfigRd;
    @JsonProperty("ConfigName")
    private String ConfigName;
    @JsonProperty("SqlConfig")
    private String SqlConfig;
    @JsonProperty("Creator")
    private String Creator;
    @JsonProperty("CreateTime")
    private String CreateTime;
    @JsonProperty("LastModifyMan")
    private String LastModifyMan;
    @JsonProperty("LastModifyTime")
    private String LastModifyTime;
    @JsonProperty("Remark")
    private String Remark;
    @JsonProperty("ResName")
    private String ResName;
    @JsonProperty("ModuleRd")
    private Integer ModuleRd;

    @JsonProperty("ColumnsInfo")
    private List<ColumnsInfo> ColumnsInfo;
    @JsonProperty("CdInfo")
    private List<CdInfo> CdInfo;

    public static class ColumnsInfo {
        @JsonProperty("ColumnRd")
        private Integer ColumnRd;
        @JsonProperty("ColumnName")
        private String ColumnName;
        @JsonProperty("AliasName")
        private String AliasName;

        @JsonIgnore
        public Integer getColumnRd() {
            return ColumnRd;
        }

        @JsonIgnore
        public void setColumnRd(Integer columnRd) {
            ColumnRd = columnRd;
        }

        @JsonIgnore
        public String getColumnName() {
            return ColumnName;
        }

        @JsonIgnore
        public void setColumnName(String columnName) {
            ColumnName = columnName;
        }

        @JsonIgnore
        public String getAliasName() {
            return AliasName;
        }

        @JsonIgnore
        public void setAliasName(String aliasName) {
            AliasName = aliasName;
        }
    }

    public static class CdInfo {
        @JsonProperty("CdRd")
        private Integer CdRd;
        @JsonProperty("CdName")
        private String CdName;
        @JsonProperty("AliasName")
        private String AliasName;

        @JsonIgnore
        public Integer getCdRd() {
            return CdRd;
        }

        @JsonIgnore
        public void setCdRd(Integer cdRd) {
            CdRd = cdRd;
        }

        @JsonIgnore
        public String getCdName() {
            return CdName;
        }

        @JsonIgnore
        public void setCdName(String cdName) {
            CdName = cdName;
        }

        @JsonIgnore
        public String getAliasName() {
            return AliasName;
        }

        @JsonIgnore
        public void setAliasName(String aliasName) {
            AliasName = aliasName;
        }
    }


    @JsonIgnore
    public Integer getCSConfigRd() {
        return CSConfigRd;
    }

    @JsonIgnore
    public void setCSConfigRd(Integer CSConfigRd) {
        this.CSConfigRd = CSConfigRd;
    }

    @JsonIgnore
    public String getConfigName() {
        return ConfigName;
    }

    @JsonIgnore
    public void setConfigName(String configName) {
        ConfigName = configName;
    }

    @JsonIgnore
    public String getSqlConfig() {
        return SqlConfig;
    }

    @JsonIgnore
    public void setSqlConfig(String sqlConfig) {
        SqlConfig = sqlConfig;
    }

    @JsonIgnore
    public String getCreator() {
        return Creator;
    }

    @JsonIgnore
    public void setCreator(String creator) {
        Creator = creator;
    }

    @JsonIgnore
    public String getCreateTime() {
        return CreateTime;
    }

    @JsonIgnore
    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    @JsonIgnore
    public String getLastModifyMan() {
        return LastModifyMan;
    }

    @JsonIgnore
    public void setLastModifyMan(String lastModifyMan) {
        LastModifyMan = lastModifyMan;
    }

    @JsonIgnore
    public String getLastModifyTime() {
        return LastModifyTime;
    }

    @JsonIgnore
    public void setLastModifyTime(String lastModifyTime) {
        LastModifyTime = lastModifyTime;
    }

    @JsonIgnore
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }

    @JsonIgnore
    public List<GetCsConfigRes.ColumnsInfo> getColumnsInfo() {
        return ColumnsInfo;
    }

    @JsonIgnore
    public void setColumnsInfo(List<GetCsConfigRes.ColumnsInfo> columnsInfo) {
        ColumnsInfo = columnsInfo;
    }

    @JsonIgnore
    public List<GetCsConfigRes.CdInfo> getCdInfo() {
        return CdInfo;
    }

    @JsonIgnore
    public void setCdInfo(List<GetCsConfigRes.CdInfo> cdInfo) {
        CdInfo = cdInfo;
    }

    @JsonIgnore
    public String getResName() {
        return ResName;
    }

    @JsonIgnore
    public void setResName(String resName) {
        ResName = resName;
    }

    @JsonIgnore
    public Integer getModuleRd() {
        return ModuleRd;
    }

    @JsonIgnore
    public void setModuleRd(Integer moduleRd) {
        ModuleRd = moduleRd;
    }
}
