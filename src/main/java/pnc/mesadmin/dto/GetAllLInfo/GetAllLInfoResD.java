package pnc.mesadmin.dto.GetAllLInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 
 * @ClassName: GetAllLInfoResD 
 * @Description: TODO 库位返回的Data里面的内容
 * @author: linyichun
 * @date: 2017-6-8 下午6:11:23
 */
public class GetAllLInfoResD implements Serializable{

	@JsonProperty("LRd")
	private Integer LRd;

	@JsonProperty("LCode")
	private String LCode;

	@JsonProperty("LName")
	private String LName;

	@JsonIgnore
	public Integer getLRd() {
		return LRd;
	}

	@JsonIgnore
	public void setLRd(Integer lRd) {
		LRd = lRd;
	}

	@JsonIgnore
	public String getLCode() {
		return LCode;
	}

	@JsonIgnore
	public void setLCode(String LCode) {
		this.LCode = LCode;
	}

	@JsonIgnore
	public String getLName() {
		return LName;
	}

	@JsonIgnore
	public void setLName(String lName) {
		LName = lName;
	}
}
