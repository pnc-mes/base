package pnc.mesadmin.dto.GetAllLinefo;

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
public class GetAllLinefoResD implements Serializable{

	@JsonProperty("LineRd")
	private Integer LineRd;

	@JsonProperty("LineName")
	private String LineName;

	@JsonProperty("LineCode")
	private String LineCode;

	@JsonIgnore
	public Integer getLineRd() {
		return LineRd;
	}

	@JsonIgnore
	public void setLineRd(Integer lineRd) {
		LineRd = lineRd;
	}

	@JsonIgnore
	public String getLineName() {
		return LineName;
	}

	@JsonIgnore
	public void setLineName(String lineName) {
		LineName = lineName;
	}

	@JsonIgnore
	public String getLineCode() {
		return LineCode;
	}

	@JsonIgnore
	public void setLineCode(String lineCode) {
		LineCode = lineCode;
	}
}
