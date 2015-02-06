/**
 * 
 */
package strat.mining.stratum.proxy.json;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author balazs.grill
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MiningSuggestDifficultyRequest extends JsonRpcRequest {

	public static final String METHOD_NAME = "mining.suggest_difficulty";
	
	@JsonIgnore
	private Double difficulty;
	
	/**
	 * @param method
	 */
	public MiningSuggestDifficultyRequest() {
		super(METHOD_NAME);
	}
	
	/**
	 * @param request
	 */
	public MiningSuggestDifficultyRequest(JsonRpcRequest request) {
		super(request);
	}
	
	public Double getDifficulty() {
		return difficulty;
	}
	
	public void setDifficulty(Double difficulty) {
		this.difficulty = difficulty;
	}
	
	@Override
	public List<Object> getParams() {
		if (super.getParams() == null) {
			ArrayList<Object> params = new ArrayList<Object>();
			super.setParams(params);
			params.add(difficulty);

		}
		return super.getParams();
	}

	@Override
	public void setParams(List<Object> params) {
		super.setParams(params);
		if (params != null) {
			difficulty = (Double) params.get(0);
		}
	}

}
