/**
 * 
 */
package strat.mining.stratum.proxy;

import java.util.ArrayList;
import java.util.List;

import strat.mining.stratum.proxy.PoolConfiguration;


/**
 * @author balazs.grill
 *
 */
public class ProxyConfiguration {

	private Integer stratumListenPort;
	private String stratumListenAddress;
	private Integer getworkListenPort;
	private String getworkListenAddress;
	private Integer apiListenPort;
	private String apiListenAddress;

	private Integer poolConnectionRetryDelay;
	private Integer poolReconnectStabilityPeriod;
	private Integer poolNoNotifyTimeout;
	private Boolean rejectReconnectOnDifferentHost;

	private Integer poolHashrateSamplingPeriod;
	private Integer userHashrateSamplingPeriod;
	private Integer connectionHashrateSamplingPeriod;
	
	private Boolean noMidstate;
	private Boolean validateGetworkShares;

	private String poolSwitchingStrategy;
	private Integer weightedRoundRobinRoundDuration;

	private Boolean disableGetwork;
	private Boolean disableStratum;
	private Boolean disableApi;
	private Boolean disableLogAppend;
	
	private Boolean logRealShareDifficulty = false;
	
	private CryptoAlgorithm algo;
	
	private List<PoolConfiguration> pools;
	
	public void setLogRealShareDifficulty(Boolean logRealShareDifficulty) {
		this.logRealShareDifficulty = logRealShareDifficulty;
	}
	
	public Boolean getLogRealShareDifficulty() {
		return logRealShareDifficulty;
	}
	
	public void setAlgo(CryptoAlgorithm algo) {
		this.algo = algo;
	}
	
	public CryptoAlgorithm getAlgo() {
		return algo;
	}
	
	public Integer getStratumListenPort() {
		return stratumListenPort;
	}

	public void setStratumListenPort(Integer stratumListenPort) {
		this.stratumListenPort = stratumListenPort;
	}

	public String getStratumListenAddress() {
		return stratumListenAddress;
	}

	public void setStratumListenAddress(String stratumListenAddress) {
		this.stratumListenAddress = stratumListenAddress;
	}

	public Integer getGetworkListenPort() {
		return getworkListenPort;
	}

	public void setGetworkListenPort(Integer getworkListenPort) {
		this.getworkListenPort = getworkListenPort;
	}

	public String getGetworkListenAddress() {
		return getworkListenAddress;
	}

	public void setGetworkListenAddress(String getworkListenAddress) {
		this.getworkListenAddress = getworkListenAddress;
	}

	public Integer getApiListenPort() {
		return apiListenPort;
	}

	public void setApiListenPort(Integer apiListenPort) {
		this.apiListenPort = apiListenPort;
	}

	public String getApiListenAddress() {
		return apiListenAddress;
	}

	public void setApiListenAddress(String apiListenAddress) {
		this.apiListenAddress = apiListenAddress;
	}

	public Integer getPoolConnectionRetryDelay() {
		return poolConnectionRetryDelay;
	}

	public void setPoolConnectionRetryDelay(Integer poolConnectionRetryDelay) {
		this.poolConnectionRetryDelay = poolConnectionRetryDelay;
	}

	public Integer getPoolReconnectStabilityPeriod() {
		return poolReconnectStabilityPeriod;
	}

	public void setPoolReconnectStabilityPeriod(Integer poolReconnectStabilityPeriod) {
		this.poolReconnectStabilityPeriod = poolReconnectStabilityPeriod;
	}

	public Integer getPoolNoNotifyTimeout() {
		return poolNoNotifyTimeout;
	}

	public void setPoolNoNotifyTimeout(Integer poolNoNotifyTimeout) {
		this.poolNoNotifyTimeout = poolNoNotifyTimeout;
	}

	public Boolean getRejectReconnectOnDifferentHost() {
		return rejectReconnectOnDifferentHost;
	}

	public void setRejectReconnectOnDifferentHost(Boolean rejectReconnectOnDifferentHost) {
		this.rejectReconnectOnDifferentHost = rejectReconnectOnDifferentHost;
	}

	public Integer getPoolHashrateSamplingPeriod() {
		return poolHashrateSamplingPeriod;
	}

	public void setPoolHashrateSamplingPeriod(Integer poolHashrateSamplingPeriod) {
		this.poolHashrateSamplingPeriod = poolHashrateSamplingPeriod;
	}

	public Integer getUserHashrateSamplingPeriod() {
		return userHashrateSamplingPeriod;
	}

	public void setUserHashrateSamplingPeriod(Integer userHashrateSamplingPeriod) {
		this.userHashrateSamplingPeriod = userHashrateSamplingPeriod;
	}

	public Integer getConnectionHashrateSamplingPeriod() {
		return connectionHashrateSamplingPeriod;
	}

	public void setConnectionHashrateSamplingPeriod(Integer connectionHashrateSamplingPeriod) {
		this.connectionHashrateSamplingPeriod = connectionHashrateSamplingPeriod;
	}

	public List<PoolConfiguration> getPools() {
		return pools;
	}

	public void setPools(List<PoolConfiguration> pools) {
		if (pools == null) {
			pools = new ArrayList<>();
		}
		this.pools = pools;
	}
	
	public Boolean getNoMidstate() {
		return noMidstate;
	}

	public void setNoMidstate(Boolean noMidstate) {
		this.noMidstate = noMidstate;
	}

	public Boolean getValidateGetworkShares() {
		return validateGetworkShares;
	}

	public void setValidateGetworkShares(Boolean validateGetworkShares) {
		this.validateGetworkShares = validateGetworkShares;
	}

	public String getPoolSwitchingStrategy() {
		return poolSwitchingStrategy;
	}

	public void setPoolSwitchingStrategy(String poolSwitchingStrategy) {
		this.poolSwitchingStrategy = poolSwitchingStrategy;
	}

	public Integer getWeightedRoundRobinRoundDuration() {
		return weightedRoundRobinRoundDuration;
	}

	public void setWeightedRoundRobinRoundDuration(Integer weightedRoundRobinRoundDuration) {
		this.weightedRoundRobinRoundDuration = weightedRoundRobinRoundDuration;
	}

	public Boolean isDisableGetwork() {
		return disableGetwork;
	}

	public void setDisableGetwork(Boolean disableGetwork) {
		this.disableGetwork = disableGetwork;
	}

	public Boolean isDisableStratum() {
		return disableStratum;
	}

	public void setDisableStratum(Boolean disableStratum) {
		this.disableStratum = disableStratum;
	}

	public Boolean isDisableApi() {
		return disableApi;
	}

	public void setDisableApi(Boolean disableApi) {
		this.disableApi = disableApi;
	}

	public Boolean isDisableLogAppend() {
		return disableLogAppend;
	}

	public void setDisableLogAppend(Boolean disableLogAppend) {
		this.disableLogAppend = disableLogAppend;
	}
	
	public Boolean getDisableLogAppend() {
		return disableLogAppend;
	}
	
}
