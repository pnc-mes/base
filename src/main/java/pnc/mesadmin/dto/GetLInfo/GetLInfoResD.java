package pnc.mesadmin.dto.GetLInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName: GetAllLInfoResD 
 * @Description: TODO 库位返回的Data里面的内容
 * @author: linyichun
 * @date: 2017-6-8 下午6:11:23
 */
public class GetLInfoResD implements Serializable{

	@JsonProperty("LRd")
	private int  LRd; //库位id

	@JsonProperty("LCode")
	private String LCode; //库位代码

	@JsonProperty("LName")
	private String LName; //库位名称

	@JsonProperty("StoreRd")
	private int StoreRd;

	@JsonProperty("StoreName")
	private String StoreName;

	@JsonProperty("Creator")
	private String Creator; //创建人

	@JsonProperty("CreateTime")
	private String CreateTime; //创建时间

	@JsonProperty("LastModifyMan")
	private String LastModifyMan; //最后修改人

	@JsonProperty("LastModifyTime")
	private String LastModifyTime; //最后修改时间

	@JsonProperty("Remark")
	private String Remark; //备注

	@JsonIgnore
	public String getStoreName() {
		return StoreName;
	}

	@JsonIgnore
	public void setStoreName(String storeName) {
		StoreName = storeName;
	}

	@JsonIgnore
	public int  getStoreRd() {
		return StoreRd;
	}

	@JsonIgnore
	public void setStoreRd(int  storeRd) {
		StoreRd = storeRd;
	}

	@JsonIgnore
	public int  getLRd() {
		return LRd;
	}

	@JsonIgnore
	public void setLRd(int  lRd) {
		LRd = lRd;
	}

	@JsonIgnore
	public String getLCode() {
		return LCode;
	}

	@JsonIgnore
	public void setLCode(String lCode) {
		LCode = lCode;
	}

	@JsonIgnore
	public String getLName() {
		return LName;
	}

	@JsonIgnore
	public void setLName(String lName) {
		LName = lName;
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
}
