/**
 * 
 */
package strat.mining.stratum.proxy.manager.strategy;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import strat.mining.stratum.proxy.exception.NoPoolAvailableException;
import strat.mining.stratum.proxy.manager.ProxyInstance;
import strat.mining.stratum.proxy.model.User;
import strat.mining.stratum.proxy.pool.Pool;
import strat.mining.stratum.proxy.worker.WorkerConnection;

/**
 * @author balazs.grill
 *
 */
public class WorkerNameOrPortMatchingPoolStrategy implements
		PoolSwitchingStrategyManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(WorkerNameOrPortMatchingPoolStrategy.class);
	
	public static final String NAME = "WorkerName";
	
	private final ProxyInstance proxyManager;
	
	public WorkerNameOrPortMatchingPoolStrategy(ProxyInstance proxyManager) {
		this.proxyManager = proxyManager;
	}
	
	/* (non-Javadoc)
	 * @see strat.mining.stratum.proxy.manager.strategy.PoolSwitchingStrategyManager#stop()
	 */
	@Override
	public void stop() {
	}

	/* (non-Javadoc)
	 * @see strat.mining.stratum.proxy.manager.strategy.PoolSwitchingStrategyManager#onPoolAdded(strat.mining.stratum.proxy.pool.Pool)
	 */
	@Override
	public void onPoolAdded(Pool pool) {
		updateConnections();
	}

	/* (non-Javadoc)
	 * @see strat.mining.stratum.proxy.manager.strategy.PoolSwitchingStrategyManager#onPoolRemoved(strat.mining.stratum.proxy.pool.Pool)
	 */
	@Override
	public void onPoolRemoved(Pool pool) {
		updateConnections();
	}

	/* (non-Javadoc)
	 * @see strat.mining.stratum.proxy.manager.strategy.PoolSwitchingStrategyManager#onPoolUpdated(strat.mining.stratum.proxy.pool.Pool)
	 */
	@Override
	public void onPoolUpdated(Pool pool) {
		updateConnections();
	}

	/* (non-Javadoc)
	 * @see strat.mining.stratum.proxy.manager.strategy.PoolSwitchingStrategyManager#onPoolDown(strat.mining.stratum.proxy.pool.Pool)
	 */
	@Override
	public void onPoolDown(Pool pool) {
		updateConnections();
	}

	/* (non-Javadoc)
	 * @see strat.mining.stratum.proxy.manager.strategy.PoolSwitchingStrategyManager#onPoolUp(strat.mining.stratum.proxy.pool.Pool)
	 */
	@Override
	public void onPoolUp(Pool pool) {
		// Nothing to do
	}

	/* (non-Javadoc)
	 * @see strat.mining.stratum.proxy.manager.strategy.PoolSwitchingStrategyManager#onPoolStable(strat.mining.stratum.proxy.pool.Pool)
	 */
	@Override
	public void onPoolStable(Pool pool) {
		updateConnections();
	}

	private String getPoolID(String poolname){
		int l = poolname.indexOf('@');
		if (l != -1){
			return poolname.substring(0, l);
		}
		return poolname;
	}
	
	private void updateConnections(){
		for(WorkerConnection wc : proxyManager.getWorkerConnections()){
			proxyManager.updatePoolForConnection(wc);
		}
	}
	
	/* (non-Javadoc)
	 * @see strat.mining.stratum.proxy.manager.strategy.PoolSwitchingStrategyManager#getPoolForConnection(strat.mining.stratum.proxy.worker.WorkerConnection)
	 */
	@Override
	public Pool getPoolForConnection(WorkerConnection connection)
			throws NoPoolAvailableException {
		
		Set<String> ids = new HashSet<>(connection.getAuthorizedWorkers().keySet());
		for(User user : proxyManager.getUsers()){
			if (user.getWorkerConnections().contains(connection)){
				ids.add(user.getName());
			}
		}
		
		Integer port = connection.getLocalPort();
		if (port != null){
			ids.add("port:"+port);
		}
		
		Pool selection = null;
		int priority = Integer.MAX_VALUE;
		
		for(Pool pool : proxyManager.getPools()){
			if (pool.isStable()){
				if (ids.contains(getPoolID(pool.getName()))){
					int p = pool.getPriority() == null ? Integer.MAX_VALUE-1 : pool.getPriority().intValue();
					if (selection == null || p < priority){
						selection = pool;
						priority = p;
					}
				}
			}
		}
		
		if (selection == null){
			LOGGER.error("No pool found for ID: "+ids);
			throw new NoPoolAvailableException("No pool found for ID: "+ids);
		}
		
		return selection;
	}

	/* (non-Javadoc)
	 * @see strat.mining.stratum.proxy.manager.strategy.PoolSwitchingStrategyManager#getConfigurationParameters()
	 */
	@Override
	public Map<String, String> getConfigurationParameters() {
		return Collections.emptyMap();
	}

	/* (non-Javadoc)
	 * @see strat.mining.stratum.proxy.manager.strategy.PoolSwitchingStrategyManager#getDetails()
	 */
	@Override
	public Map<String, String> getDetails() {
		return Collections.emptyMap();
	}

	/* (non-Javadoc)
	 * @see strat.mining.stratum.proxy.manager.strategy.PoolSwitchingStrategyManager#getName()
	 */
	@Override
	public String getName() {
		return NAME;
	}

	/* (non-Javadoc)
	 * @see strat.mining.stratum.proxy.manager.strategy.PoolSwitchingStrategyManager#getDescription()
	 */
	@Override
	public String getDescription() {
		return "This strategy maps pools by worker names";
	}

	/* (non-Javadoc)
	 * @see strat.mining.stratum.proxy.manager.strategy.PoolSwitchingStrategyManager#setParameter(java.lang.String, java.lang.String)
	 */
	@Override
	public void setParameter(String parameterKey, String value) {
		// TODO Auto-generated method stub

	}

}
