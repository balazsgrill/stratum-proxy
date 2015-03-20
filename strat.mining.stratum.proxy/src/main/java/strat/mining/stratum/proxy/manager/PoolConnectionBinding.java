/**
 * 
 */
package strat.mining.stratum.proxy.manager;

import strat.mining.stratum.proxy.exception.AuthorizationException;
import strat.mining.stratum.proxy.exception.NoPoolAvailableException;
import strat.mining.stratum.proxy.json.MiningAuthorizeRequest;
import strat.mining.stratum.proxy.json.MiningSubmitRequest;
import strat.mining.stratum.proxy.json.MiningSubscribeRequest;
import strat.mining.stratum.proxy.pool.Pool;
import strat.mining.stratum.proxy.pool.PoolConnection;
import strat.mining.stratum.proxy.worker.IWorkerConnectionListener;
import strat.mining.stratum.proxy.worker.StratumWorkerConnection;
import strat.mining.stratum.proxy.worker.WorkerConnection;

/**
 * @author balazs.grill
 *
 */
public class PoolConnectionBinding implements IWorkerConnectionListener {

	private final WorkerConnection connection;
	
	private final PoolConnection poolConnection;
	
	
	/**
	 * 
	 */
	public PoolConnectionBinding(PoolConnection poolConnection, WorkerConnection connection) {
		this.connection = connection;
	}

	/* (non-Javadoc)
	 * @see strat.mining.stratum.proxy.worker.IWorkerConnectionListener#onWorkerDisconnection(strat.mining.stratum.proxy.worker.StratumWorkerConnection, java.lang.Throwable)
	 */
	@Override
	public void onWorkerDisconnection(
			StratumWorkerConnection stratumWorkerConnection, Throwable cause) {
		if (poolConnection != null){
			poolConnection.close();
			poolConnection = null;
		}

	}

	/* (non-Javadoc)
	 * @see strat.mining.stratum.proxy.worker.IWorkerConnectionListener#onAuthorizeRequest(strat.mining.stratum.proxy.worker.StratumWorkerConnection, strat.mining.stratum.proxy.json.MiningAuthorizeRequest)
	 */
	@Override
	public void onAuthorizeRequest(
			StratumWorkerConnection stratumWorkerConnection,
			MiningAuthorizeRequest request) throws AuthorizationException {
		proxy.onAuthorizeRequest(stratumWorkerConnection, request);
	}

	/* (non-Javadoc)
	 * @see strat.mining.stratum.proxy.worker.IWorkerConnectionListener#onSubscribeRequest(strat.mining.stratum.proxy.worker.StratumWorkerConnection, strat.mining.stratum.proxy.json.MiningSubscribeRequest)
	 */
	@Override
	public Pool onSubscribeRequest(
			StratumWorkerConnection stratumWorkerConnection,
			MiningSubscribeRequest request) throws NoPoolAvailableException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see strat.mining.stratum.proxy.worker.IWorkerConnectionListener#onSubmitRequest(strat.mining.stratum.proxy.worker.StratumWorkerConnection, strat.mining.stratum.proxy.json.MiningSubmitRequest)
	 */
	@Override
	public void onSubmitRequest(
			StratumWorkerConnection stratumWorkerConnection,
			MiningSubmitRequest request) {
		// TODO Auto-generated method stub

	}

}
