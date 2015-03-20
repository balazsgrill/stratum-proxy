/**
 * 
 */
package strat.mining.stratum.proxy.worker;

import strat.mining.stratum.proxy.exception.AuthorizationException;
import strat.mining.stratum.proxy.exception.NoPoolAvailableException;
import strat.mining.stratum.proxy.json.MiningAuthorizeRequest;
import strat.mining.stratum.proxy.json.MiningSubmitRequest;
import strat.mining.stratum.proxy.json.MiningSubscribeRequest;
import strat.mining.stratum.proxy.pool.Pool;

/**
 * @author balazs.grill
 *
 */
public interface IWorkerConnectionListener {

	void onWorkerDisconnection(StratumWorkerConnection stratumWorkerConnection,
			Throwable cause);

	void onAuthorizeRequest(StratumWorkerConnection stratumWorkerConnection,
			MiningAuthorizeRequest request) throws AuthorizationException;

	Pool onSubscribeRequest(StratumWorkerConnection stratumWorkerConnection,
			MiningSubscribeRequest request) throws NoPoolAvailableException;

	void onSubmitRequest(StratumWorkerConnection stratumWorkerConnection,
			MiningSubmitRequest request);

}
