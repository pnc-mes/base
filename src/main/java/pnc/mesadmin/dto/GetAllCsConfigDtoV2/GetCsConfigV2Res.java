package pnc.mesadmin.dto.GetAllCsConfigDtoV2;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @program: mesadmin
 * @description: 通用查询DTO
 * @author: yin.yang
 * @create: 2018-12-21
 **/
public class GetCsConfigV2Res {
    @JsonProperty("CSConfigRd")
    private Integer CSConfigRd;
    @JsonProperty("ConfigName")
    private String ConfigName;
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

    @JsonProperty("CSSearch")
    private CSSearch CSSearch;
    @JsonProperty("CSTable")
    private CSTable CSTable;
    @JsonProperty("CSTotal")
    private List<CSTotal> CSTotal;


    //模块一
    public static class CSSearch {
        @JsonProperty("CSSearchRd")
        private Integer CSSearchRd;
        @JsonProperty("ColNum")
        private Integer ColNum;
        @JsonProperty("RowNum")
        private Integer RowNum;
        @JsonProperty("Fields")
        private List<Fields> Fields;

        @JsonIgnore
        public Integer getCSSearchRd() {
            return CSSearchRd;
        }

        @JsonIgnore
        public void setCSSearchRd(Integer CSSearchRd) {
            this.CSSearchRd = CSSearchRd;
        }

        @JsonIgnore
        public Integer getColNum() {
            return ColNum;
        }

        @JsonIgnore
        public void setColNum(Integer colNum) {
            ColNum = colNum;
        }

        @JsonIgnore
        public Integer getRowNum() {
            return RowNum;
        }

        @JsonIgnore
        public void setRowNum(Integer rowNum) {
            RowNum = rowNum;
        }

        @JsonIgnore
        public List<GetCsConfigV2Res.Fields> getFields() {
            return Fields;
        }

        @JsonIgnore
        public void setFields(List<GetCsConfigV2Res.Fields> fields) {
            Fields = fields;
        }
    }

    public static class Fields {
        @JsonProperty("FieldRd")
        private Integer FieldRd;
        @JsonProperty("CdName")
        private String CdName;
        @JsonProperty("AliasName")
        private String AliasName;
        @JsonProperty("CdType")
        private String CdType;
        @JsonProperty("DDs")
        private DDs DDs;

        @JsonIgnore
        public Integer getFieldRd() {
            return FieldRd;
        }

        @JsonIgnore
        public void setFieldRd(Integer fieldRd) {
            FieldRd = fieldRd;
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

        @JsonIgnore
        public String getCdType() {
            return CdType;
        }

        @JsonIgnore
        public void setCdType(String cdType) {
            CdType = cdType;
        }

        @JsonIgnore
        public GetCsConfigV2Res.DDs getDDs() {
            return DDs;
        }

        @JsonIgnore
        public void setDDs(GetCsConfigV2Res.DDs DDs) {
            this.DDs = DDs;
        }
    }

    public static class DDs {
        @JsonProperty("FieldRd")
        private Integer FieldRd;
        @JsonProperty("DisFieldName")
        private String DisFieldName;
        @JsonProperty("ValFiledName")
        private String ValFiledName;
        @JsonProperty("SqlConfig")
        private String SqlConfig;

        @JsonIgnore
        public Integer getFieldRd() {
            return FieldRd;
        }

        @JsonIgnore
        public void setFieldRd(Integer fieldRd) {
            FieldRd = fieldRd;
        }

        @JsonIgnore
        public String getDisFieldName() {
            return DisFieldName;
        }

        @JsonIgnore
        public void setDisFieldName(String disFieldName) {
            DisFieldName = disFieldName;
        }

        @JsonIgnore
        public String getValFiledName() {
            return ValFiledName;
        }

        @JsonIgnore
        public void setValFiledName(String valFiledName) {
            ValFiledName = valFiledName;
        }

        @JsonIgnore
        public String getSqlConfig() {
            return SqlConfig;
        }

        @JsonIgnore
        public void setSqlConfig(String sqlConfig) {
            SqlConfig = sqlConfig;
        }
    }

    //模块二
    public static class CSTable {
        @JsonProperty("CSTableRd")
        private Integer CSTableRd;
        @JsonProperty("SqlConfig")
        private String SqlConfig;
        @JsonProperty("Columns")
        private List<Columns> Columns;
        @JsonProperty("SearchFields")
        private List<SearchFields> SearchFields;

        @JsonIgnore
        public Integer getCSTableRd() {
            return CSTableRd;
        }

        @JsonIgnore
        public void setCSTableRd(Integer CSTableRd) {
            this.CSTableRd = CSTableRd;
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
        public List<GetCsConfigV2Res.Columns> getColumns() {
            return Columns;
        }

        @JsonIgnore
        public void setColumns(List<GetCsConfigV2Res.Columns> columns) {
            Columns = columns;
        }

        @JsonIgnore
        public List<GetCsConfigV2Res.SearchFields> getSearchFields() {
            return SearchFields;
        }

        @JsonIgnore
        public void setSearchFields(List<GetCsConfigV2Res.SearchFields> searchFields) {
            SearchFields = searchFields;
        }
    }

    public static class Columns {
        @JsonProperty("ColumnRd")
        private Integer ColumnRd;
        @JsonProperty("ColumnName")
        private String ColumnName;
        @JsonProperty("AliasName")
        private String AliasName;
        @JsonProperty("ColumnType")
        private String ColumnType;
        @JsonProperty("IsDisplay")
        private String IsDisplay;
        @JsonProperty("ColumnWidth")
        private Integer ColumnWidth;
        @JsonProperty("Truncated")
        private String Truncated;
        @JsonProperty("OutPutText")
        private String OutPutText;

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

        @JsonIgnore
        public String getColumnType() {
            return ColumnType;
        }

        @JsonIgnore
        public void setColumnType(String columnType) {
            ColumnType = columnType;
        }

        @JsonIgnore
        public String getIsDisplay() {
            return IsDisplay;
        }

        @JsonIgnore
        public void setIsDisplay(String isDisplay) {
            IsDisplay = isDisplay;
        }

        @JsonIgnore
        public Integer getColumnWidth() {
            return ColumnWidth;
        }

        @JsonIgnore
        public void setColumnWidth(Integer columnWidth) {
            ColumnWidth = columnWidth;
        }

        @JsonIgnore
        public String getTruncated() {
            return Truncated;
        }

        @JsonIgnore
        public void setTruncated(String truncated) {
            Truncated = truncated;
        }

        @JsonIgnore
        public String getOutPutText() {
            return OutPutText;
        }

        @JsonIgnore
        public void setOutPutText(String outPutText) {
            OutPutText = outPutText;
        }
    }

    public static class SearchFields {
        @JsonProperty("FieldRd")
        private Integer FieldRd;
        @JsonProperty("CdName")
        private String CdName;
        @JsonProperty("AliasName")
        private String AliasName;

        @JsonIgnore
        public Integer getFieldRd() {
            return FieldRd;
        }

        @JsonIgnore
        public void setFieldRd(Integer fieldRd) {
            FieldRd = fieldRd;
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

    //模块三
    public static class CSTotal {
        @JsonProperty("CSTotalRd")
        private Integer CSTotalRd;
        @JsonProperty("ConfigName")
        private String ConfigName;
        @JsonProperty("SqlConfig")
        private String SqlConfig;
        @JsonProperty("Columns")
        private List<Columns> Columns;
        @JsonProperty("SearchFields")
        private List<SearchFields> SearchFields;

        @JsonIgnore
        public Integer getCSTotalRd() {
            return CSTotalRd;
        }

        @JsonIgnore
        public void setCSTotalRd(Integer CSTotalRd) {
            this.CSTotalRd = CSTotalRd;
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
        public List<GetCsConfigV2Res.Columns> getColumns() {
            return Columns;
        }

        @JsonIgnore
        public void setColumns(List<GetCsConfigV2Res.Columns> columns) {
            Columns = columns;
        }

        @JsonIgnore
        public List<GetCsConfigV2Res.SearchFields> getSearchFields() {
            return SearchFields;
        }

        @JsonIgnore
        public void setSearchFields(List<GetCsConfigV2Res.SearchFields> searchFields) {
            SearchFields = searchFields;
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
    public GetCsConfigV2Res.CSSearch getCSSearch() {
        return CSSearch;
    }

    @JsonIgnore
    public void setCSSearch(GetCsConfigV2Res.CSSearch CSSearch) {
        this.CSSearch = CSSearch;
    }

    @JsonIgnore
    public GetCsConfigV2Res.CSTable getCSTable() {
        return CSTable;
    }

    @JsonIgnore
    public void setCSTable(GetCsConfigV2Res.CSTable CSTable) {
        this.CSTable = CSTable;
    }

    @JsonIgnore
    public List<GetCsConfigV2Res.CSTotal> getCSTotal() {
        return CSTotal;
    }

    @JsonIgnore
    public void setCSTotal(List<GetCsConfigV2Res.CSTotal> CSTotal) {
        this.CSTotal = CSTotal;
    }
}
